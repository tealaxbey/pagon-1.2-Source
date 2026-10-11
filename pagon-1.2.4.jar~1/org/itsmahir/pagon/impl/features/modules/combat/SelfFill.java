package org.itsmahir.pagon.impl.features.modules.combat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1542;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2189;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2404;
import net.minecraft.class_2828;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.move.MovementPacketsEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.management.PriorityManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.chat.ChatMessage;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;
import org.itsmahir.pagon.impl.features.modules.exploit.Blink;

public class SelfFill extends Module {
   Timer hitcrystalCooldown = new Timer();
   Timer burrowCooldown = new Timer();
   Value<String> rotate = (new ValueBuilder<String>()).withDescriptor("Rotate", "RotateNewEnum").withValue("None").withModes("None", "Normal").register(this);
   Value<Boolean> breakCrystal = (new ValueBuilder<Boolean>()).withDescriptor("Break").withValue(false).register(this);
   Value<Number> breakDelay;
   Value<Number> ticksExisted;
   Value<Boolean> await;
   Value<Number> burrowDelay;
   Value<Boolean> breakSync;
   Value<String> block;
   Value<Boolean> dynamic;
   Value<Boolean> sixB;
   class_2338 originalPos;
   List<class_2338> placedPositions;
   double startY;
   boolean didBreak;

   public SelfFill() {
      super("SelfFill", Feature.Category.Combat);
      this.breakDelay = (new ValueBuilder<Number>()).withDescriptor("Break Delay").withValue(0).withRange(0, 1000).withAction((set) -> this.hitcrystalCooldown.setDelay(((Number)set.getValue()).longValue())).withParent(this.breakCrystal).withParentEnabled(true).register(this);
      this.ticksExisted = (new ValueBuilder<Number>()).withDescriptor("Ticks Existed").withValue(0.0F).withRange(0.0F, 10.0F).withPlaces(0).withParent(this.breakCrystal).withParentEnabled(true).register(this);
      this.await = (new ValueBuilder<Boolean>()).withDescriptor("Await").withValue(false).register(this);
      this.burrowDelay = (new ValueBuilder<Number>()).withDescriptor("Burrow Delay").withValue(100).withRange(0, 1000).withAction((set) -> this.burrowCooldown.setDelay(((Number)set.getValue()).longValue())).withParent(this.await).withParentEnabled(true).register(this);
      this.breakSync = (new ValueBuilder<Boolean>()).withDescriptor("Sync").withValue(false).withParent(this.await).withParentEnabled(true).register(this);
      this.block = (new ValueBuilder<String>()).withDescriptor("Block").withValue("EnderChest").withModes("EnderChest", "Obsidian", "Piston", "Dynamic").register(this);
      this.dynamic = (new ValueBuilder<Boolean>()).withDescriptor("Dynamic").withValue(false).register(this);
      this.sixB = (new ValueBuilder<Boolean>()).withDescriptor("6b6t").withValue(false).register(this);
      this.placedPositions = new ArrayList();
      this.startY = Double.NaN;
      this.didBreak = false;
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (!Blink.INSTANCE.isEnabled()) {
            if (mc.field_1724.method_24828()) {
               if (Double.isNaN(this.startY)) {
                  this.startY = mc.field_1724.method_23318();
               }

               if (!Double.isNaN(this.startY) && mc.field_1724.method_23318() > this.startY) {
                  this.setEnabled(false);
                  this.startY = Double.NaN;
               } else if (InventoryUtils.getHotbarItemSlot(this.getSelectedItem()) == -1) {
                  this.setEnabled(false);
                  ChatUtils.sendMessage(new ChatMessage(String.valueOf(class_124.field_1061) + "Could not find block", true, 64583));
               } else {
                  for(class_2338 position : this.placedPositions) {
                     if (!BlockUtils.isReplaceable(position)) {
                        this.setEnabled(false);
                        return;
                     }
                  }

                  class_2338 pos = this.getPlayerPos();
                  if (this.canPlaceBurrow(pos, false, false)) {
                     boolean intersectsWithCrystal = BlockUtils.isInterceptedByCrystal(pos);
                     if ((Boolean)this.breakCrystal.getValue() && this.hitcrystalCooldown.isPassed()) {
                        class_1511 crystal = BlockUtils.attackInPos(pos, ((Number)this.ticksExisted.getValue()).intValue());
                        this.didBreak = crystal != null;
                        if (this.didBreak) {
                           this.hitcrystalCooldown.resetDelay();
                           if (!((String)this.rotate.getValue()).equals("None")) {
                              float[] rots = RotationUtils.getRotationsTo(mc.field_1724.method_33571(), crystal.method_19538().method_1023((double)0.0F, 1.1, (double)0.0F));
                              RotationUtils.setRotation(rots);
                              PriorityManager.INSTANCE.lockUsageLock("SelfFill");
                           }
                        }
                     }

                     if ((Boolean)this.breakSync.getValue() && (Boolean)this.await.getValue()) {
                        if (this.didBreak || !intersectsWithCrystal) {
                           PriorityManager.INSTANCE.lockUsageLock("SelfFill");
                           if (((String)this.rotate.getValue()).equals("Normal") && !this.didBreak) {
                              RotationUtils.doRotate(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue());
                           }
                        }
                     } else {
                        PriorityManager.INSTANCE.lockUsageLock("SelfFill");
                        if (((String)this.rotate.getValue()).equals("Normal") && !this.didBreak) {
                           RotationUtils.doRotate(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue());
                        }
                     }
                  }

               }
            }
         }
      }
   }

   class_1792 getSelectedItem() {
      String b = this.block.getValue();
      class_1792 selectedItem = class_1802.field_8281;
      switch (b) {
         case "Piston":
            selectedItem = class_1802.field_8249;
            break;
         case "EnderChest":
            selectedItem = class_1802.field_8466;
            break;
         case "Obsidian":
            selectedItem = class_1802.field_8281;
            break;
         case "Dynamic":
            if (InventoryUtils.getHotbarItemSlot(class_1802.field_8281) == -1) {
               return class_1802.field_8466;
            }
      }

      return selectedItem;
   }

   public void doRotate(class_2338 pos, MovementPacketsEvent event) {
      float[] rots = RotationUtils.getBlockRotations(pos, BlockUtils.getPlaceableSide(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue()));
      if (rots != null) {
         event.setCancelled(true);
         event.setYaw(rots[0]);
         event.setPitch(rots[1]);
         RotationUtils.setRotation(rots);
      }

   }

   public void doRotate(class_2338 pos, boolean packet) {
      float[] rots = RotationUtils.getBlockRotations(pos, BlockUtils.getPlaceableSide(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue()));
      if (packet) {
         PacketManager.INSTANCE.sendPacket(new class_2828.class_2831(rots[0], rots[1], false));
      } else {
         RotationUtils.setRotation(rots);
      }

   }

   public void doRotate(float[] rots, MovementPacketsEvent event) {
      event.setCancelled(true);
      event.setYaw(rots[0]);
      event.setPitch(rots[1]);
      RotationUtils.setRotation(rots);
   }

   public void doRotate(float[] rots, boolean packet) {
      if (packet) {
         PacketManager.INSTANCE.sendPacket(new class_2828.class_2831(rots[0], rots[1], false));
      } else {
         RotationUtils.setRotation(rots);
      }

   }

   @SubscribeEvent
   public void onInput(TickEvent.MovementTickEvent.Post event) {
      if (!NullUtils.nullCheck()) {
         if (!Blink.INSTANCE.isEnabled()) {
            if (mc.field_1724.method_24828()) {
               class_2338 burrowPos = this.getPlayerPos();
               if (this.canPlaceBurrow(burrowPos, true, (Boolean)this.await.getValue() && (Boolean)this.breakSync.getValue())) {
                  if (!BlockUtils.isInterceptedByCrystal(burrowPos)) {
                     this.didBreak = false;
                  }

                  int old = mc.field_1724.method_31548().field_7545;
                  if (Double.isNaN(this.startY) && mc.field_1724.method_24828()) {
                     this.startY = mc.field_1724.method_23318();
                  }

                  InventoryUtils.switchToSlot(InventoryUtils.getHotbarItemSlot(this.getSelectedItem()));
                  this.resetPos();
                  if (!((String)this.rotate.getValue()).equals("None") && this.didBreak) {
                     this.doRotate(burrowPos, true);
                  }

                  BlockUtils.placeBlock(burrowPos, BlockUtils.getPlaceableSide(burrowPos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue()), true);
                  if (!this.placedPositions.contains(burrowPos)) {
                     this.placedPositions.add(burrowPos);
                  }

                  mc.field_1724.method_5814(mc.field_1724.method_23317(), mc.field_1724.method_23318() - 1.170010501788138, mc.field_1724.method_23321());
                  if ((Boolean)this.sixB.getValue()) {
                     this.movePacket(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 1.01, mc.field_1724.method_23321(), false);
                  } else {
                     this.movePacket(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 1.242610501394747, mc.field_1724.method_23321(), false);
                     this.movePacket(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 2.340020003576277, mc.field_1724.method_23321(), false);
                  }

                  this.didBreak = false;
                  InventoryUtils.switchToSlot(old);
                  this.burrowCooldown.resetDelay();
                  if (!(Boolean)this.await.getValue()) {
                     this.setEnabled(false);
                  }

               }
            }
         }
      }
   }

   public void resetPos() {
      this.movePacket(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 0.41999848688698, mc.field_1724.method_23321(), false);
      this.movePacket(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 0.7500015, mc.field_1724.method_23321(), false);
      this.movePacket(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 0.999997, mc.field_1724.method_23321(), false);
      this.movePacket(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 1.17000300178814, mc.field_1724.method_23321(), false);
      this.movePacket(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 1.170010501788138, mc.field_1724.method_23321(), false);
      mc.field_1724.method_5814(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 1.170010501788138, mc.field_1724.method_23321());
   }

   private void movePacket(double x, double y, double z, boolean ground) {
      mc.field_1724.field_3944.method_52787(new class_2828.class_2829(x, y, z, ground));
   }

   public void onDisable() {
      super.onDisable();
      this.placedPositions.clear();
      if (PriorityManager.INSTANCE.isUsageLocked() && Objects.equals(PriorityManager.INSTANCE.usageLockCause, "SelfFill")) {
         PriorityManager.INSTANCE.unlockUsageLock();
      }

      if (!NullUtils.nullCheck()) {
         this.startY = Double.NaN;
      }
   }

   public void onEnable() {
      super.onEnable();
      this.placedPositions.clear();
      if (!NullUtils.nullCheck()) {
         this.startY = Double.NaN;
         this.originalPos = this.getPlayerPos();
         if (mc.field_1687.method_8320(this.originalPos).method_26204().equals(class_2246.field_10540) || this.intersectsWithEntity(this.getPlayerPos())) {
            this.setEnabled(false);
         }

         if (PriorityManager.INSTANCE.isUsageLocked() && !Objects.equals(PriorityManager.INSTANCE.usageLockCause, "SelfFill")) {
            PriorityManager.INSTANCE.usageLockCause = "SelfFill";
         }

      }
   }

   public boolean canPlaceBurrow(class_2338 burrowPos, Boolean disable, boolean doSync) {
      if (mc.field_1687.method_8320(burrowPos.method_10074()).method_26204() instanceof class_2189) {
         if (!(Boolean)this.await.getValue() && disable) {
            this.setEnabled(false);
         }

         return false;
      } else if (BlockUtils.isInterceptedByOtherPlayer(burrowPos)) {
         this.setEnabled(false);
         return false;
      } else if ((!mc.field_1687.method_8320(burrowPos).method_45474() || mc.field_1687.method_8320(burrowPos).method_26204() instanceof class_2404) && (Boolean)this.await.getValue()) {
         this.setEnabled(false);
         return false;
      } else if (!mc.field_1687.method_8320(burrowPos.method_10086(2)).method_45474()) {
         if (!(Boolean)this.await.getValue() && disable) {
            this.setEnabled(false);
         }

         return false;
      } else if ((Boolean)this.await.getValue() && !this.burrowCooldown.isPassed()) {
         return false;
      } else {
         if (doSync) {
            boolean intercepted = BlockUtils.isInterceptedByCrystal(burrowPos);
            if (!this.didBreak && intercepted) {
               return false;
            }
         }

         return true;
      }
   }

   boolean intersectsWithEntity(class_2338 pos) {
      for(class_1297 entity : mc.field_1687.method_18112()) {
         if (!entity.equals(mc.field_1724) && !(entity instanceof class_1542) && (!(entity instanceof class_1511) || !(Boolean)this.breakCrystal.getValue() && !(Boolean)this.await.getValue()) && (new class_238(pos)).method_994(entity.method_5829())) {
            return true;
         }
      }

      return false;
   }

   class_2338 getPlayerPos() {
      double decimalPoint = mc.field_1724.method_23318() - Math.floor(mc.field_1724.method_23318());
      return new class_2338(mc.field_1724.method_24515().method_10263(), (int)(decimalPoint > 0.8 ? Math.floor(mc.field_1724.method_23318()) + (double)1.0F : Math.floor(mc.field_1724.method_23318())), mc.field_1724.method_24515().method_10260());
   }

   public String getDescription() {
      return "Attempts to clip you into a block";
   }
}
