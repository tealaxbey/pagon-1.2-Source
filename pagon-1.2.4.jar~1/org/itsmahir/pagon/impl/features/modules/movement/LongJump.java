package org.itsmahir.pagon.impl.features.modules.movement;

import com.google.common.collect.Streams;
import net.minecraft.class_1294;
import net.minecraft.class_1802;
import net.minecraft.class_2708;
import net.minecraft.class_2848;
import net.minecraft.class_2848.class_2849;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.LivingEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.move.MoveEvent;
import org.itsmahir.pagon.api.event.events.move.TravelEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.BoostManager;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.management.RotationManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.player.MiddleClick;

public class LongJump extends Module {
   public static LongJump INSTANCE;
   public Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Boost").withModes("Boost", "Grim", "Strict", "StrictHigh").register(this);
   Value<Number> boost = (new ValueBuilder<Number>()).withDescriptor("Boost").withValue(0.4).withRange(0.2, 10).register(this);
   Value<Boolean> autoDisable = (new ValueBuilder<Boolean>()).withDescriptor("Auto Disable").withValue(true).register(this);
   Value<Boolean> onGround = (new ValueBuilder<Boolean>()).withDescriptor("Friction").withValue(true).register(this);
   boolean hasBeenGrounded = false;
   private int stage = 0;
   private double moveSpeed;
   private double lastDist;

   public LongJump() {
      super("LongJump", Feature.Category.Movement);
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onPlayerTick(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (((String)this.mode.getValue()).equals("Grim")) {
            RotationUtils.setRotation(new float[]{PlayerUtils.getMoveYaw(mc.field_1724.method_36454()), 62.8F}, 99);
         }

      }
   }

   @SubscribeEvent
   public void onPlayerMove(MoveEvent event) {
      switch ((String)this.mode.getValue()) {
         case "Grim":
            if (!mc.field_1724.method_6128()) {
               return;
            }

            if ((Boolean)ElytraFly.INSTANCE.boostControl.getValue()) {
               ElytraFly.INSTANCE.doBoost(event);
            }
            break;
         default:
            if (!this.hasBeenGrounded && (Boolean)this.onGround.getValue()) {
               return;
            }

            if (((String)this.mode.getValue()).contains("Strict") && !BoostManager.INSTANCE.canDoLongjump()) {
               return;
            }

            if (mc.field_1724.method_6128() || mc.field_1724.method_5771() || mc.field_1724.method_5869()) {
               return;
            }

            switch (this.stage) {
               case 0:
                  ++this.stage;
                  this.lastDist = (double)0.0F;
                  break;
               case 1:
               default:
                  if ((!Streams.stream(mc.field_1687.method_8600(mc.field_1724, mc.field_1724.method_5829().method_989((double)0.0F, mc.field_1724.method_18798().field_1351, (double)0.0F))).toList().isEmpty() || mc.field_1724.field_5992) && this.stage > 0) {
                     this.stage = mc.field_1724.field_3913.field_3905 == 0.0F && mc.field_1724.field_3913.field_3907 == 0.0F ? 0 : 1;
                  }

                  this.moveSpeed = this.lastDist - this.lastDist / (double)159.0F;
                  break;
               case 2:
                  double motionY = 0.40123128;
                  if ((mc.field_1724.field_3913.field_3905 != 0.0F || mc.field_1724.field_3913.field_3907 != 0.0F) && mc.field_1724.method_24828()) {
                     if (mc.field_1724.method_6059(class_1294.field_5913)) {
                        motionY += (double)((float)(mc.field_1724.method_6112(class_1294.field_5913).method_5578() + 1) * 0.1F);
                     }

                     event.motionY(motionY);
                     this.moveSpeed *= 2.149;
                  }
                  break;
               case 3:
                  this.moveSpeed = this.lastDist - 0.76 * (this.lastDist - this.getBaseMoveSpeed());
            }

            this.moveSpeed = Math.max(this.moveSpeed, this.getBaseMoveSpeed());
            double forward = (double)mc.field_1724.field_3913.field_3905;
            double strafe = (double)mc.field_1724.field_3913.field_3907;
            double yaw = (double)mc.field_1724.method_36454();
            if (forward != (double)0.0F && strafe != (double)0.0F) {
               forward *= Math.sin((Math.PI / 4D));
               strafe *= Math.cos((Math.PI / 4D));
            } else {
               event.setX((double)0.0F);
               event.setZ((double)0.0F);
            }

            event.setX((forward * this.moveSpeed * -Math.sin(Math.toRadians(yaw)) + strafe * this.moveSpeed * Math.cos(Math.toRadians(yaw))) * 0.99);
            event.setZ((forward * this.moveSpeed * Math.cos(Math.toRadians(yaw)) - strafe * this.moveSpeed * -Math.sin(Math.toRadians(yaw))) * 0.99);
            ++this.stage;
      }

   }

   @SubscribeEvent
   public void onTravel(TravelEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         switch ((String)this.mode.getValue()) {
            case "Grim":
               int slot = InventoryUtils.getInventoryItemSlot(class_1802.field_8833);
               if (slot == -1) {
                  return;
               } else if (RotationManager.INSTANCE.isRotationBlocked(99)) {
                  return;
               } else {
                  if (!mc.field_1724.method_6128()) {
                     InventoryUtils.swapArmor(2, slot);
                     PacketManager.INSTANCE.sendPacket(new class_2848(mc.field_1724, class_2849.field_12982));
                     mc.field_1724.method_23669();
                     if (MiddleClick.INSTANCE.fireworkSchedule) {
                        MiddleClick.INSTANCE.doFirework();
                        MiddleClick.INSTANCE.fireworkSchedule = false;
                     }

                     InventoryUtils.swapArmor(2, slot);
                  }

                  if (mc.field_1724.method_24828()) {
                     PlayerUtils.clientJump();
                  }
               }
            default:
         }
      }
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.ClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         if (!this.hasBeenGrounded) {
            this.hasBeenGrounded = mc.field_1724.method_24828();
         }

         if (this.hasBeenGrounded || !(Boolean)this.onGround.getValue()) {
            this.lastDist = Math.sqrt((mc.field_1724.method_23317() - mc.field_1724.field_6014) * (mc.field_1724.method_23317() - mc.field_1724.field_6014) + (mc.field_1724.method_23321() - mc.field_1724.field_5969) * (mc.field_1724.method_23321() - mc.field_1724.field_5969));
            if (this.canSprint()) {
               mc.field_1724.method_5728(true);
            }

         }
      }
   }

   public void onEnable() {
      super.onEnable();
      this.hasBeenGrounded = false;
      if (!NullUtils.nullCheck()) {
         if (!NoAccel.paused) {
            NoAccel.paused = true;
         }

      }
   }

   public void onDisable() {
      super.onDisable();
      if (!NullUtils.nullCheck()) {
         if (NoAccel.paused) {
            NoAccel.paused = false;
         }

      }
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Receive event) {
      if (!NullUtils.nullCheck()) {
         if (event.getPacket() instanceof class_2708 && (Boolean)this.autoDisable.getValue()) {
            this.toggle();
         }

      }
   }

   private double getBaseMoveSpeed() {
      double baseSpeed = 0.272;
      if (mc.field_1724.method_6059(class_1294.field_5904)) {
         int amplifier = mc.field_1724.method_6112(class_1294.field_5904).method_5578();
         baseSpeed *= (double)1.0F + 0.2 * (double)(amplifier + 1);
      }

      return baseSpeed * ((Number)this.boost.getValue()).doubleValue();
   }

   @SubscribeEvent
   public void onTravel(TravelEvent.Post event) {
      if (!NullUtils.nullCheck()) {
         ;
      }
   }

   @SubscribeEvent
   public void onActionJump(LivingEvent.Jump event) {
      if (!NullUtils.nullCheck()) {
         int slot = InventoryUtils.getInventoryItemSlot(class_1802.field_8833);
         if (slot != -1) {
            if (((String)this.mode.getValue()).equals("Grim")) {
               event.setCancelled(true);
            }

         }
      }
   }

   public static boolean isGrimJumping() {
      if (!INSTANCE.isEnabled()) {
         return false;
      } else {
         int slot = InventoryUtils.getInventoryItemSlot(class_1802.field_8833);
         return slot == -1 ? false : ((String)INSTANCE.mode.getValue()).equals("Grim");
      }
   }

   private boolean canSprint() {
      return (mc.field_1724.field_3913.field_3907 != 0.0F || mc.field_1724.field_3913.field_3905 != 0.0F) && !mc.field_1724.method_6039() && !mc.field_1724.method_6101() && !mc.field_1724.field_5976 && mc.field_1724.method_7344().method_7586() > 6;
   }

   public String getHudInfo() {
      return this.mode.getValue();
   }

   public String getDescription() {
      return "LongJump: jump but long and fast";
   }
}
