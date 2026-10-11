package org.itsmahir.pagon.impl.features.modules.movement;

import com.google.common.collect.Streams;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2708;
import net.minecraft.class_2793;
import net.minecraft.class_2828;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.move.MoveEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.event.events.network.ServerEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.mixin.accessor.IPlayerPositionLookS2CPacket;

public class PacketFly extends Module {
   public static PacketFly INSTANCE;
   Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Factorize").withModes("Factorize", "Fast", "Lagback").register(this);
   Value<Number> factor = (new ValueBuilder<Number>()).withDescriptor("Factor").withValue((double)1.5F).withRange(0.1, 4.0F).withPlaces(1).register(this);
   Value<String> phase = (new ValueBuilder<String>()).withDescriptor("Phase").withValue("Full").withModes("Full", "Semi", "None").register(this);
   Value<String> bounds = (new ValueBuilder<String>()).withDescriptor("Bounds").withValue("Up").withModes("Up", "Down", "Preserve", "Limit", "Soft", "Snap").register(this);
   Value<Boolean> antiKick = (new ValueBuilder<Boolean>()).withDescriptor("AntiKick").withValue(true).register(this);
   Value<Boolean> phased = (new ValueBuilder<Boolean>()).withDescriptor("Phased").withValue(true).register(this);
   Value<String> limit = (new ValueBuilder<String>()).withDescriptor("Limit", "LimitMode").withValue("None").withModes("None", "Axis", "Ticks", "Strict", "All").register(this);
   Value<Boolean> dupe = (new ValueBuilder<Boolean>()).withDescriptor("Confirm").withValue(false).register(this);
   private final Map<Integer, class_243> predictions = new HashMap();
   private int tpId = 0;
   private int lagTime = 0;
   private static final double CONCEAL = 0.0624;
   private static final double MOVE_FACTOR = (double)1.0F / StrictMath.sqrt((double)2.0F);
   float factorBuffer = 0.0F;
   private static final Random rand = new Random();

   public PacketFly() {
      super("PacketFly", Feature.Category.Movement);
      INSTANCE = this;
   }

   public void onDisable() {
      super.onDisable();
      if (!NullUtils.nullCheck()) {
         ;
      }
   }

   public void onEnable() {
      super.onEnable();
      if (!NullUtils.nullCheck()) {
         ;
      }
   }

   @SubscribeEvent
   public void onMove(MoveEvent event) {
      if (!NullUtils.nullCheck()) {
         int loops;
         if (!((String)this.mode.getValue()).equals("Factorize") || mc.field_1724.field_6012 % 3 != 0 && (((String)this.limit.getValue()).equals("Ticks") || ((String)this.limit.getValue()).equals("All"))) {
            loops = 1;
            if ((((String)this.mode.getValue()).equals("Fast") || ((String)this.limit.getValue()).equals("Strict") && this.canJitter()) && (((String)this.limit.getValue()).equals("Ticks") || ((String)this.limit.getValue()).equals("All") || ((String)this.limit.getValue()).equals("Strict") && this.canJitter()) && mc.field_1724.field_6012 % 3 == 0) {
               loops = 0;
            }
         } else {
            float rawFactor = ((Number)this.factor.getValue()).floatValue();
            loops = (int)Math.floor((double)rawFactor);
            float extraFactor = rawFactor - (float)loops;
            this.factorBuffer -= 0.1F;
            if (this.factorBuffer <= extraFactor) {
               this.factorBuffer = 1.0F;
               ++loops;
            }

            if (((String)this.bounds.getValue()).equals("Soft") && this.canJitter()) {
               loops = 1;
               if (((String)this.limit.getValue()).equals("Strict") && mc.field_1724.field_6012 % 3 == 0) {
                  loops = 0;
               }
            }
         }

         double moveSpeed = !(Boolean)this.phased.getValue() && --this.lagTime <= 0 && !this.isPhased() ? 0.2873 : 0.0624;
         double motionY = (double)0.0F;
         boolean doAntiKick = false;
         if (mc.field_1690.field_1903.method_1434()) {
            if (!((String)this.limit.getValue()).equals("Axis") && !((String)this.limit.getValue()).equals("All")) {
               motionY = 0.0624;
               if (PlayerUtils.isMoving()) {
                  moveSpeed *= MOVE_FACTOR;
                  motionY *= MOVE_FACTOR;
               }
            } else if (PlayerUtils.isMoving()) {
               if (mc.field_1724.field_6012 % 2 == 0) {
                  motionY = 0.0624;
                  moveSpeed = (double)0.0F;
               } else {
                  moveSpeed *= MOVE_FACTOR;
               }
            } else {
               motionY = 0.0624;
            }

            doAntiKick = (Boolean)this.antiKick.getValue() && mc.field_1724.field_6012 % 20 == 0 && !this.isPhased() && !mc.field_1687.method_18026(mc.field_1724.method_5829());
            if (doAntiKick) {
               loops = 1;
               motionY = -0.04;
            }
         } else if (mc.field_1690.field_1832.method_1434()) {
            if (!((String)this.limit.getValue()).equals("All") && !((String)this.limit.getValue()).equals("Axis")) {
               motionY = -0.0624;
               if (PlayerUtils.isMoving()) {
                  moveSpeed *= MOVE_FACTOR;
                  motionY *= MOVE_FACTOR;
               }
            } else if (PlayerUtils.isMoving()) {
               if (mc.field_1724.field_6012 % 2 == 0) {
                  motionY = -0.0624;
                  moveSpeed = (double)0.0F;
               } else {
                  moveSpeed *= MOVE_FACTOR;
               }
            } else {
               motionY = -0.0624;
            }
         } else {
            doAntiKick = (Boolean)this.antiKick.getValue() && mc.field_1724.field_6012 % 20 == 0 && !this.isPhased() && !mc.field_1687.method_8587(mc.field_1724, mc.field_1724.method_5829());
            if (doAntiKick) {
               loops = 1;
               motionY = -0.04;
            }
         }

         this.sendMovePackets(loops, moveSpeed, motionY, doAntiKick);
         event.setX(mc.field_1724.method_18798().field_1352);
         event.setY(mc.field_1724.method_18798().field_1351);
         event.setZ(mc.field_1724.method_18798().field_1350);
         this.doPhase();
      }
   }

   boolean canJitter() {
      return this.isPhased() || mc.field_1724.field_5976 || mc.field_1724.field_5992;
   }

   public void doPhase() {
      switch ((String)this.phase.getValue()) {
         case "Full":
            if (!mc.field_1724.field_5960) {
               mc.field_1724.field_5960 = true;
            }
            break;
         case "Semi":
            if (!this.isPhased() && !mc.field_1724.field_5992) {
               if (mc.field_1724.field_5960) {
                  mc.field_1724.field_5960 = false;
               }
            } else {
               mc.field_1724.field_5960 = true;
            }
            break;
         case "None":
            if (mc.field_1724.field_5960) {
               mc.field_1724.field_5960 = false;
            }
      }

   }

   private void sendMovePackets(int loops, double moveSpeed, double motionY, boolean antiKick) {
      if (loops == 0) {
         mc.field_1724.method_18800((double)0.0F, (double)0.0F, (double)0.0F);
      } else {
         double[] strafe = PlayerUtils.getMoveSpeed(moveSpeed);

         for(int i = 1; i < loops + 1; ++i) {
            double motionX = strafe[0] * (double)i;
            double motionZ = strafe[1] * (double)i;
            double velY = motionY;
            if (!antiKick) {
               velY = motionY * (double)i;
            }

            PlayerUtils.setMotionX(motionX);
            PlayerUtils.setMotionY(velY);
            PlayerUtils.setMotionZ(motionZ);
            class_243 posVec = mc.field_1724.method_19538();
            class_243 moveVec = posVec.method_1031(motionX, velY, motionZ);
            this.sendMovePackets(moveVec);
            if (((String)this.bounds.getValue()).equals("Soft") && this.canJitter()) {
               this.sendMovePackets(new class_243(posVec.field_1352 + motionX, posVec.field_1351 + randomBounds(), posVec.field_1350 + motionZ));
            } else {
               this.sendMovePackets(this.modify(posVec));
            }

            this.doPrediction(moveVec);
         }

      }
   }

   public void doPrediction(class_243 moveVec) {
      if (this.tpId != 0 && !((String)this.mode.getValue()).equals("Lagback")) {
         this.predictions.put(++this.tpId, moveVec);
         PacketManager.INSTANCE.sendQuietPacket(new class_2793(this.tpId));
      }

   }

   private boolean isPhased() {
      return !Streams.stream(mc.field_1687.method_8600(mc.field_1724, mc.field_1724.method_5829().method_1009((double)-0.0625F, (double)-0.0625F, (double)-0.0625F))).toList().isEmpty();
   }

   @SubscribeEvent
   public void onDisconnect(ServerEvent.ServerLeft event) {
      if (this.isEnabled()) {
         this.setEnabled(false);
      }

   }

   @SubscribeEvent
   public void onJoin(ServerEvent.ServerJoined event) {
      if (this.isEnabled()) {
         this.setEnabled(false);
      }

   }

   public class_243 modify(class_243 in) {
      return new class_243(in.field_1352, this.getBounds(in), in.field_1350);
   }

   public static double randomBounds() {
      int randomValue = rand.nextInt(22) + 70;
      return rand.nextBoolean() ? (double)randomValue : (double)(-randomValue);
   }

   public double getBounds(class_243 in) {
      switch ((String)this.bounds.getValue()) {
         case "Up":
            return in.field_1351 + (double)1337.0F;
         case "Down":
            return in.field_1351 + (double)-1337.0F;
         case "Preserve":
            int n = rand.nextInt(29000000);
            if (rand.nextBoolean()) {
               return (double)n;
            }

            return in.field_1351 + (double)(-n);
         case "Limit":
            int j = rand.nextInt(22) + 70;
            return in.field_1351 + (double)j;
         case "Soft":
         case "Snap":
            return (double)0.0F;
         default:
            return in.field_1351 + (double)-1337.0F;
      }
   }

   public void sendMovePackets(class_243 vec) {
      PacketManager.INSTANCE.sendQuietPacket(new class_2828.class_2829(vec.field_1352, vec.field_1351, vec.field_1350, true));
   }

   @SubscribeEvent
   public void onPacketReceive(PacketEvent.Receive event) {
      if (!NullUtils.nullCheck()) {
         class_2596 var3 = event.getPacket();
         if (var3 instanceof class_2708) {
            class_2708 packet = (class_2708)var3;
            class_243 prediction = (class_243)this.predictions.get(packet.method_11737());
            if (prediction != null && prediction.field_1352 == packet.method_11734() && prediction.field_1351 == packet.method_11735() && prediction.field_1350 == packet.method_11738()) {
               if (!((String)this.mode.getValue()).equals("Lagback")) {
                  event.setCancelled(true);
               }

               if ((Boolean)this.dupe.getValue() || mc.field_1724.field_6012 % 2 == 0) {
                  PacketManager.INSTANCE.sendQuietPacket(new class_2793(packet.method_11737()));
               }

               this.predictions.remove(packet.method_11737());
               return;
            }

            ((IPlayerPositionLookS2CPacket)packet).setYaw(mc.field_1724.method_36454());
            ((IPlayerPositionLookS2CPacket)packet).setPitch(mc.field_1724.method_36455());
            PacketManager.INSTANCE.sendQuietPacket(new class_2793(packet.method_11737()));
            this.lagTime = 10;
            this.tpId = packet.method_11737();
         }

      }
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Send event) {
      if (!NullUtils.nullCheck()) {
         if (mc.method_1542()) {
            if (event.getPacket() instanceof class_2793 || event.getPacket() instanceof class_2828) {
               event.setCancelled(true);
            }

         }
      }
   }

   public String getDescription() {
      return "PacketFly: manipulates confirm teleport packets to fly and phase";
   }

   public String getHudInfo() {
      return this.mode.getValue();
   }
}
