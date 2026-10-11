package org.itsmahir.pagon.impl.features.modules.movement;

import net.minecraft.class_1268;
import net.minecraft.class_1747;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2480;
import net.minecraft.class_2596;
import net.minecraft.class_2645;
import net.minecraft.class_2653;
import net.minecraft.class_2815;
import net.minecraft.class_2868;
import net.minecraft.class_3944;
import net.minecraft.class_3965;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class ShulkerFly extends Module {
   public static ShulkerFly INSTANCE;
   Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Normal").withModes("Normal", "Dynamic").register(this);
   Value<Number> speed = (new ValueBuilder<Number>()).withDescriptor("Speed").withValue((double)0.5F).withRange(0.1, (double)2.0F).withPlaces(1).register(this);
   Value<Number> antiKickInterval = (new ValueBuilder<Number>()).withDescriptor("AntiKick").withValue(40).withRange(10, 80).withPlaces(0).register(this);
   Value<Number> openTicks = (new ValueBuilder<Number>()).withDescriptor("OpenTicks").withValue(6).withRange(2, 12).withPlaces(0).register(this);
   Value<Number> closeTicks = (new ValueBuilder<Number>()).withDescriptor("CloseTicks").withValue(13).withRange(8, 20).withPlaces(0).register(this);
   Value<Boolean> lockY = (new ValueBuilder<Boolean>()).withDescriptor("LockY").withValue(false).register(this);
   Value<Boolean> autoFlyUp = (new ValueBuilder<Boolean>()).withDescriptor("AutoFlyUp").withValue(false).register(this);
   Value<Number> autoFlyUpY;
   Value<Number> dynamicBase;
   Value<Number> dynamicMultiplier;
   Value<Number> dynamicJumpTime;
   Value<Number> dynamicCap;
   private State state;
   private class_2338 shulkerPos;
   private int tickCounter;
   private int savedSlot;
   private int shulkerContainerId;
   private int antiKickCounter;
   private boolean swappedChestplate;
   private double dynamicSpeed;
   private int dynamicTickCounter;

   public ShulkerFly() {
      super("ShulkerFly", Feature.Category.Movement);
      this.autoFlyUpY = (new ValueBuilder<Number>()).withDescriptor("StartY").withValue(365).withRange(0, 500).withPlaces(0).withParent(this.autoFlyUp).withParentEnabled(true).register(this);
      this.dynamicBase = (new ValueBuilder<Number>()).withDescriptor("DynBase").withValue(0.42).withRange(0.1, (double)1.5F).withPlaces(2).withPageParent(this.mode).withPage("Dynamic").register(this);
      this.dynamicMultiplier = (new ValueBuilder<Number>()).withDescriptor("DynMultiplier").withValue(1.42).withRange(1.01, (double)3.0F).withPlaces(2).withPageParent(this.mode).withPage("Dynamic").register(this);
      this.dynamicJumpTime = (new ValueBuilder<Number>()).withDescriptor("DynJumpTime").withValue(12).withRange(4, 40).withPlaces(0).withPageParent(this.mode).withPage("Dynamic").register(this);
      this.dynamicCap = (new ValueBuilder<Number>()).withDescriptor("DynCap").withValue((double)0.0F).withRange((double)0.0F, (double)50.0F).withPlaces(1).withPageParent(this.mode).withPage("Dynamic").register(this);
      this.state = ShulkerFly.State.IDLE;
      this.shulkerPos = null;
      this.tickCounter = 0;
      this.savedSlot = -1;
      this.shulkerContainerId = -1;
      this.antiKickCounter = 0;
      this.swappedChestplate = false;
      this.dynamicSpeed = (double)0.0F;
      this.dynamicTickCounter = 0;
      INSTANCE = this;
   }

   public void onEnable() {
      this.state = ShulkerFly.State.IDLE;
      this.shulkerPos = null;
      this.tickCounter = 0;
      this.savedSlot = -1;
      this.shulkerContainerId = -1;
      this.antiKickCounter = 0;
      this.swappedChestplate = false;
      this.dynamicSpeed = (double)0.0F;
      this.dynamicTickCounter = 0;
      this.swappedChestplate = this.trySwapChestplate();
   }

   @SubscribeEvent
   public void onPreTick(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (this.state == ShulkerFly.State.IDLE) {
            this.shulkerPos = this.findShulkerBelow();
            if (this.shulkerPos != null) {
               this.swapToSafeSlot();
               this.openShulker();
               this.state = ShulkerFly.State.OPENING;
               this.tickCounter = 0;
            }
         } else {
            ++this.tickCounter;
            if (this.state == ShulkerFly.State.OPENING) {
               if (this.tickCounter >= ((Number)this.openTicks.getValue()).intValue()) {
                  this.closeShulker();
                  this.state = ShulkerFly.State.CLOSING;
                  this.tickCounter = 0;
               }
            } else if (this.state == ShulkerFly.State.CLOSING && this.tickCounter >= ((Number)this.closeTicks.getValue()).intValue()) {
               this.openShulker();
               this.state = ShulkerFly.State.OPENING;
               this.tickCounter = 0;
            }

            mc.field_1724.field_6017 = 0.0F;
            mc.field_1724.method_5875(true);
            double vx = (double)0.0F;
            double vy = (double)0.0F;
            double vz = (double)0.0F;
            double s = this.getEffectiveSpeed();
            if ((Boolean)this.autoFlyUp.getValue() && mc.field_1724.method_23318() >= ((Number)this.autoFlyUpY.getValue()).doubleValue()) {
               vy = s;
            } else if (mc.field_1690.field_1903.method_1434()) {
               vy = s;
            } else if (mc.field_1690.field_1832.method_1434()) {
               vy = -s;
            }

            float yaw = mc.field_1724.method_36454();
            double forward = (double)0.0F;
            double strafe = (double)0.0F;
            if (mc.field_1690.field_1894.method_1434()) {
               ++forward;
            }

            if (mc.field_1690.field_1881.method_1434()) {
               --forward;
            }

            if (mc.field_1690.field_1913.method_1434()) {
               ++strafe;
            }

            if (mc.field_1690.field_1849.method_1434()) {
               --strafe;
            }

            if (forward != (double)0.0F || strafe != (double)0.0F) {
               double angle = Math.toRadians((double)yaw);
               double dist = Math.sqrt(forward * forward + strafe * strafe);
               forward /= dist;
               strafe /= dist;
               vx = (strafe * Math.cos(angle) - forward * Math.sin(angle)) * s;
               vz = (forward * Math.cos(angle) + strafe * Math.sin(angle)) * s;
            }

            if ((Boolean)this.lockY.getValue() && !mc.field_1690.field_1903.method_1434() && !mc.field_1690.field_1832.method_1434()) {
               vy = (double)0.0F;
            }

            ++this.antiKickCounter;
            if (this.antiKickCounter >= ((Number)this.antiKickInterval.getValue()).intValue()) {
               vy = -0.04;
               this.antiKickCounter = 0;
            }

            mc.field_1724.method_18800(vx, vy, vz);
         }
      }
   }

   private double getEffectiveSpeed() {
      if (!((String)this.mode.getValue()).equals("Dynamic")) {
         return ((Number)this.speed.getValue()).doubleValue();
      } else {
         ++this.dynamicTickCounter;
         double mult = ((Number)this.dynamicMultiplier.getValue()).doubleValue();
         int interval = Math.max(1, (int)((double)((Number)this.dynamicJumpTime.getValue()).intValue() / (double)2.0F / mult));
         if (this.dynamicTickCounter >= interval) {
            this.dynamicTickCounter = 0;
            if (this.dynamicSpeed == (double)0.0F) {
               this.dynamicSpeed = ((Number)this.dynamicBase.getValue()).doubleValue();
            } else {
               this.dynamicSpeed *= mult;
            }

            double cap = ((Number)this.dynamicCap.getValue()).doubleValue();
            if (cap > (double)0.0F && this.dynamicSpeed > cap) {
               this.dynamicSpeed = cap;
            }
         }

         if (this.dynamicSpeed == (double)0.0F) {
            this.dynamicSpeed = ((Number)this.dynamicBase.getValue()).doubleValue();
         }

         return this.dynamicSpeed;
      }
   }

   public String getHudInfo() {
      return ((String)this.mode.getValue()).equals("Dynamic") ? String.format("%.2f b/t", this.dynamicSpeed) : null;
   }

   private void openShulker() {
      class_243 hitVec = class_243.method_24953(this.shulkerPos).method_1031((double)0.0F, (double)0.5F, (double)0.0F);
      class_3965 hit = new class_3965(hitVec, class_2350.field_11036, this.shulkerPos, false);
      mc.field_1761.method_2896(mc.field_1724, class_1268.field_5808, hit);
   }

   private void closeShulker() {
      if (this.shulkerContainerId >= 0) {
         PacketManager.INSTANCE.sendPacket(new class_2815(this.shulkerContainerId));
         this.shulkerContainerId = -1;
      }

   }

   private void swapToSafeSlot() {
      this.savedSlot = mc.field_1724.method_31548().field_7545;

      for(int i = 0; i < 9; ++i) {
         if (mc.field_1724.method_31548().method_5438(i).method_7960()) {
            this.switchSlot(i);
            return;
         }
      }

      for(int i = 0; i < 9; ++i) {
         if (!(mc.field_1724.method_31548().method_5438(i).method_7909() instanceof class_1747)) {
            this.switchSlot(i);
            return;
         }
      }

   }

   private void switchSlot(int slot) {
      mc.field_1724.method_31548().field_7545 = slot;
      PacketManager.INSTANCE.sendPacket(new class_2868(slot));
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Receive event) {
      if (this.state != ShulkerFly.State.IDLE) {
         class_2596 var3 = event.getPacket();
         if (var3 instanceof class_3944) {
            class_3944 openPacket = (class_3944)var3;
            this.shulkerContainerId = openPacket.method_17592();
            event.setCancelled(true);
         } else if (event.getPacket() instanceof class_2653) {
            event.setCancelled(true);
         } else if (event.getPacket() instanceof class_2645) {
            event.setCancelled(true);
         }

      }
   }

   public void onDisable() {
      if (mc.field_1724 != null) {
         mc.field_1724.method_5875(false);
         mc.field_1724.method_18799(class_243.field_1353);
         if (this.swappedChestplate) {
            PlayerUtils.equipElytra();
            this.swappedChestplate = false;
         }
      }

      if (this.state != ShulkerFly.State.IDLE && mc.field_1724 != null && mc.method_1562() != null) {
         this.closeShulker();
         if (this.savedSlot >= 0) {
            this.switchSlot(this.savedSlot);
         }
      }

      this.state = ShulkerFly.State.IDLE;
      this.shulkerPos = null;
      this.tickCounter = 0;
      this.savedSlot = -1;
      this.shulkerContainerId = -1;
      this.antiKickCounter = 0;
   }

   private class_2338 findShulkerBelow() {
      class_2338 playerPos = mc.field_1724.method_24515();

      for(int dy = 0; dy >= -2; --dy) {
         class_2338 check = playerPos.method_10069(0, dy, 0);
         if (mc.field_1687.method_8320(check).method_26204() instanceof class_2480) {
            return check;
         }
      }

      return null;
   }

   private boolean trySwapChestplate() {
      if (!PlayerUtils.isElytraEquipped()) {
         return false;
      } else {
         int slot = InventoryUtils.findChestplate();
         if (slot == -1) {
            return false;
         } else {
            InventoryUtils.swapArmor(2, slot);
            return true;
         }
      }
   }

   public String getDescription() {
      return "ShulkerFly: Fly by cycling shulker lid to bypass anti-cheat.";
   }

   private static enum State {
      IDLE,
      OPENING,
      CLOSING;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{IDLE, OPENING, CLOSING};
      }
   }
}
