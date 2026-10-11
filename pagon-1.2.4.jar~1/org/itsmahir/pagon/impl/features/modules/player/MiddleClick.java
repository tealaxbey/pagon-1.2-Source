package org.itsmahir.pagon.impl.features.modules.player;

import java.awt.Color;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.render.RenderGameOverlayEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.gui.font.Fonts;
import org.itsmahir.pagon.api.management.FriendManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.chat.ChatMessage;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.ScaledResolution;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.client.gui.Hud;
import org.itsmahir.pagon.impl.features.modules.combat.AutoXP;
import org.itsmahir.pagon.impl.features.modules.exploit.FastProjectile;
import org.itsmahir.pagon.impl.features.modules.movement.FakeFly;
import org.itsmahir.pagon.impl.features.modules.movement.LongJump;

public class MiddleClick extends Module {
   public static MiddleClick INSTANCE;
   Value<Boolean> friend = (new ValueBuilder<Boolean>()).withDescriptor("Friend").withValue(true).register(this);
   Value<Boolean> pearl = (new ValueBuilder<Boolean>()).withDescriptor("Pearl").withValue(true).register(this);
   Value<Boolean> inventory;
   Value<Boolean> fastProjectile;
   Value<Boolean> fireworks;
   boolean hasPressed;
   boolean startedTimer;
   long startTime;
   public boolean fireworkSchedule;

   public MiddleClick() {
      super("MiddleClick", Feature.Category.Player);
      this.inventory = (new ValueBuilder<Boolean>()).withDescriptor("Inventory").withValue(false).withParentEnabled(true).withParent(this.pearl).register(this);
      this.fastProjectile = (new ValueBuilder<Boolean>()).withDescriptor("Charge").withValue(true).withParent(this.pearl).withParentEnabled(true).register(this);
      this.fireworks = (new ValueBuilder<Boolean>()).withDescriptor("Fireworks").withValue(true).register(this);
      this.hasPressed = false;
      this.startedTimer = false;
      this.startTime = 0L;
      this.fireworkSchedule = false;
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onTickPre(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (!mc.field_1729.method_35707()) {
            if ((Boolean)this.pearl.getValue() && (Boolean)this.fastProjectile.getValue() && this.startedTimer) {
               this.doPearl();
            }

            this.startedTimer = false;
            this.hasPressed = false;
         } else {
            if (!this.isAutoXP() && (!(Boolean)this.fireworks.getValue() || !mc.field_1724.method_6128()) && (Boolean)this.pearl.getValue() && (Boolean)this.fastProjectile.getValue() && !this.startedTimer) {
               this.startTime = System.currentTimeMillis();
               this.startedTimer = true;
            }

            class_1297 pointed = mc.field_1692;
            if (!this.hasPressed) {
               if (this.startedTimer) {
                  return;
               }

               if (this.isAutoXP()) {
                  this.hasPressed = true;
                  return;
               }

               if ((Boolean)this.friend.getValue() && pointed != null && pointed instanceof class_1657) {
                  if (FriendManager.INSTANCE.isFriend(pointed)) {
                     FriendManager.INSTANCE.removeFriend(pointed);
                     ChatUtils.sendMessage(new ChatMessage("Removed " + pointed.method_5477().getString() + " from friends", false, 0));
                     this.hasPressed = true;
                  } else {
                     FriendManager.INSTANCE.addFriend(pointed);
                     ChatUtils.sendMessage(new ChatMessage("Added " + pointed.method_5477().getString() + " from friends", false, 0));
                     this.hasPressed = true;
                  }
               }

               if (pointed == null) {
                  if ((Boolean)this.fireworks.getValue()) {
                     if (FakeFly.isFakeFlying() || LongJump.isGrimJumping()) {
                        this.hasPressed = true;
                        this.fireworkSchedule = true;
                        return;
                     }

                     if (mc.field_1724.method_6128()) {
                        this.doFirework();
                        this.hasPressed = true;
                        return;
                     }
                  }

                  if ((Boolean)this.pearl.getValue() && !(Boolean)this.fastProjectile.getValue()) {
                     this.doPearl();
                  }
               }
            }
         }

      }
   }

   public void doPearl() {
      int pearlSlot = InventoryUtils.getHotbarItemSlot(class_1802.field_8634);
      this.hasPressed = true;
      if (pearlSlot == -1) {
         if (!(Boolean)this.inventory.getValue()) {
            ChatUtils.sendMessage(new ChatMessage("No pearls in hotbar", false, 0));
         } else {
            int pearlInv = InventoryUtils.getInventoryItemSlot(class_1802.field_8634);
            if (pearlInv == -1) {
               ChatUtils.sendMessage(new ChatMessage("No fireworks in inv", false, 0));
               return;
            }

            InventoryUtils.swap(pearlInv, mc.field_1724.method_31548().field_7545);
            PlayerUtils.use();
            InventoryUtils.swap(pearlInv, mc.field_1724.method_31548().field_7545);
         }

      } else {
         this.switchAndUse(pearlSlot, true);
      }
   }

   public void doFirework() {
      int fireworkHotbar = InventoryUtils.getHotbarItemSlot(class_1802.field_8639);
      if (fireworkHotbar != -1) {
         this.switchAndUse(fireworkHotbar, false);
      } else {
         int fireworkInv = InventoryUtils.getInventoryItemSlot(class_1802.field_8639);
         if (fireworkInv == -1) {
            ChatUtils.sendMessage(new ChatMessage("No fireworks in inv", false, 0));
            return;
         }

         InventoryUtils.swap(fireworkInv, mc.field_1724.method_31548().field_7545);
         PlayerUtils.use();
         InventoryUtils.swap(fireworkInv, mc.field_1724.method_31548().field_7545);
      }

   }

   public void switchAndUse(int slot, boolean pearl) {
      int oldSlot = mc.field_1724.method_31548().field_7545;
      InventoryUtils.switchToSlotGhost(slot);
      if (pearl && (Boolean)this.fastProjectile.getValue() && System.currentTimeMillis() - this.startTime > 500L) {
         FastProjectile.INSTANCE.sendPackets();
      }

      PlayerUtils.use();
      InventoryUtils.switchToSlotGhost(oldSlot);
   }

   @SubscribeEvent
   public void draw(RenderGameOverlayEvent.Text event) {
      if (!NullUtils.nullCheck()) {
         if (this.startedTimer) {
            ScaledResolution resolution = new ScaledResolution(mc);
            float x = (float)resolution.getScaledWidth() / 2.0F;
            float y = (float)resolution.getScaledHeight() / 2.0F + 13.0F;
            float thickness = 1.0F;
            float percentage = Math.min(1.0F, (float)(System.currentTimeMillis() - this.startTime) / 500.0F);
            float width = 80.0F;
            float half = 40.0F;
            Color left = Hud.getTextColor(0);
            Color right = Hud.getTextColor((int)Fonts.getTextHeight("AA"));
            RenderUtil.renderRect(event.getContext().method_51448(), (double)(x - 40.0F) - (double)0.5F, (double)y - (double)0.5F, (double)81.0F, (double)2.0F, 2013265920);
            RenderUtil.renderGradient(event.getContext().method_51448(), (double)(x - 40.0F) - (double)0.5F, (double)y - (double)0.5F, (double)(80.0F * percentage + 1.0F), (double)2.0F, left.darker().getRGB(), right.darker().getRGB(), true);
            RenderUtil.renderGradient(event.getContext().method_51448(), (double)(x - 40.0F), (double)y, (double)(80.0F * percentage), (double)1.0F, left.getRGB(), right.getRGB(), true);
         }

      }
   }

   boolean isAutoXP() {
      return AutoXP.INSTANCE.isEnabled() && (Boolean)AutoXP.INSTANCE.middleClick.getValue() ? AutoXP.INSTANCE.isXping() : false;
   }

   public String getHudInfo() {
      if (this.isAutoXP()) {
         return "XP";
      } else {
         return mc.field_1724.method_6128() && (Boolean)this.fireworks.getValue() ? "Firework" : "Pearl";
      }
   }

   public String getDescription() {
      return "MiddleClick: does various actions on middle click";
   }
}
