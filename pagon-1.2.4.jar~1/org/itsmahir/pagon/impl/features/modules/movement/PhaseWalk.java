package org.itsmahir.pagon.impl.features.modules.movement;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_1294;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2708;
import net.minecraft.class_2793;
import net.minecraft.class_2828;
import net.minecraft.class_2848;
import net.minecraft.class_3532;
import net.minecraft.class_2848.class_2849;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.move.MoveEvent;
import org.itsmahir.pagon.api.event.events.move.SneakEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.event.events.render.FrameEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.management.RotationManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.mixin.accessor.IPlayerMoveC2SPacket;

public class PhaseWalk extends Module {
   Timer timer = new Timer();
   Timer packetTimer = new Timer();
   Value<String> phaseMode = (new ValueBuilder<String>()).withDescriptor("Phase").withValue("Clip").withModes("Clip", "Smooth").register(this);
   Value<Number> delay = (new ValueBuilder<Number>()).withDescriptor("Delay").withValue(200).withRange(0, 1000).withAction((s) -> this.timer.setDelay(((Number)s.getValue()).longValue())).register(this);
   Value<String> handleTeleport = (new ValueBuilder<String>()).withDescriptor("Handle Teleport").withValue("All").withModes("All", "Above", "Predict", "None").register(this);
   Value<Boolean> onlyInBlock = (new ValueBuilder<Boolean>()).withDescriptor("Only In Block").withValue(false).register(this);
   Value<String> inhibit = (new ValueBuilder<String>()).withDescriptor("Inhibit", "inhibitMode").withValue("None").withModes("None", "Rotate", "Necessary", "Both").register(this);
   Value<String> motion = (new ValueBuilder<String>()).withDescriptor("Speed").withValue("Slow").withModes("Slow", "Soft", "Factor").register(this);
   Value<Number> factor;
   Value<Boolean> down;
   Value<Boolean> noVoid;
   Value<Boolean> boost;
   Value<String> limit;
   Value<Number> packetMax;
   Value<Number> resetDelay;
   public static PhaseWalk INSTANCE;
   private final Map<Integer, class_243> predictions;
   boolean extra;
   boolean dontTouchRots;
   int tpId;
   int packets;

   public PhaseWalk() {
      super("PhaseWalk", Feature.Category.Movement);
      this.factor = (new ValueBuilder<Number>()).withDescriptor("Factor").withValue(1).withPageParent(this.motion).withPage("Factor").withRange(0, 5).withPlaces(2).register(this);
      this.down = (new ValueBuilder<Boolean>()).withDescriptor("Down").withValue(true).register(this);
      this.noVoid = (new ValueBuilder<Boolean>()).withDescriptor("Anti Void").withValue(false).withParent(this.down).withParentEnabled(true).register(this);
      this.boost = (new ValueBuilder<Boolean>()).withDescriptor("Boost").withValue(false).register(this);
      this.limit = (new ValueBuilder<String>()).withDescriptor("Limit", "limitMode").withValue("None").withModes("None", "Packets").register(this);
      this.packetMax = (new ValueBuilder<Number>()).withDescriptor("Max Packets").withValue(1).withPageParent(this.limit).withPage("Packets").withRange(1, 100).withPlaces(0).register(this);
      this.resetDelay = (new ValueBuilder<Number>()).withDescriptor("Reset").withValue(300).withPageParent(this.limit).withPage("Packets").withRange(1, 1000).withPlaces(0).withAction((s) -> this.packetTimer.setDelay(((Number)s.getValue()).longValue())).register(this);
      this.predictions = new HashMap();
      this.extra = false;
      this.dontTouchRots = false;
      this.tpId = 0;
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onGameLoop(FrameEvent.FrameFlipEvent event) {
      if (!NullUtils.nullCheck()) {
         if (this.extra) {
            PlayerUtils.runPhysicsTick();
            this.extra = false;
         }

      }
   }

   @SubscribeEvent
   public void onMove(MoveEvent event) {
      if (!NullUtils.nullCheck()) {
         if (((String)this.phaseMode.getValue()).equals("Clip")) {
            this.doPhase(event);
         }

         if (!((String)this.motion.getValue()).equals("Slow")) {
            if (!mc.field_1724.method_5869() && !mc.field_1724.method_5771()) {
               if (((String)this.motion.getValue()).equals("Factor") && mc.field_1724.method_6059(class_1294.field_5904)) {
                  PlayerUtils.phaseSpeed(event, PlayerUtils.getStrictBaseSpeed(0.2873F), ((Number)this.factor.getValue()).floatValue());
               } else {
                  PlayerUtils.setSpeed((double)PlayerUtils.getStrictBaseSpeed(0.2873F), event);
               }
            } else if (mc.field_1724.field_3913.field_3905 == 0.0F && mc.field_1724.field_3913.field_3907 == 0.0F) {
               event.motionX((double)0.0F);
               event.motionZ((double)0.0F);
            }
         }

         if (((String)this.phaseMode.getValue()).equals("Smooth")) {
            this.doPhase(event);
         }

      }
   }

   public void doPhase(MoveEvent event) {
      if (!((String)this.limit.getValue()).equals("Packets") || (long)this.packets <= ((Number)this.packetMax.getValue()).longValue()) {
         switch ((String)this.phaseMode.getValue()) {
            case "Clip":
               boolean flag = false;
               if (this.shouldPacket() || (flag = (Boolean)this.down.getValue() && mc.field_1690.field_1832.method_1434() && this.isInBlock())) {
                  double[] forward = PlayerUtils.forward(0.001);
                  if (!flag || !(Boolean)this.noVoid.getValue() || !(mc.field_1724.method_23318() <= (double)1.0F)) {
                     if (flag && !mc.field_1724.field_5992) {
                        return;
                     } else if (this.timer.isPassed()) {
                        this.sendPackets(mc.field_1724.method_23317() + forward[0], mc.field_1724.method_23318() + this.getUpMovement(), mc.field_1724.method_23321() + forward[1]);
                        this.timer.resetDelay();
                        this.extra = (Boolean)this.boost.getValue();
                     }
                  }
               }
            default:
         }
      }
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.ClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         if (this.packetTimer.isPassed()) {
            this.packets = 0;
            this.packetTimer.resetDelay();
         }

         if (Speed.INSTANCE.isEnabled()) {
            this.setEnabled(false);
         }

      }
   }

   @SubscribeEvent(5)
   public void onUpdate(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         this.dontTouchRots = RotationManager.INSTANCE.isRotating();
      }
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Send event) {
      if (!NullUtils.nullCheck()) {
         if ((((String)this.inhibit.getValue()).equals("Both") || ((String)this.inhibit.getValue()).equals("Rotate")) && this.isInBlock()) {
            if (event.getPacket() instanceof class_2828.class_2831 && !this.dontTouchRots) {
               event.setCancelled(true);
            }

            class_2596 var3 = event.getPacket();
            if (var3 instanceof class_2828.class_2830) {
               class_2828.class_2830 movePacket = (class_2828.class_2830)var3;
               if (!this.dontTouchRots) {
                  event.setCancelled(true);
                  IPlayerMoveC2SPacket packet = (IPlayerMoveC2SPacket)movePacket;
                  PacketManager.INSTANCE.sendPacket(new class_2828.class_2829(packet.getX(), packet.getY(), packet.getZ(), movePacket.method_12273()));
               }
            }
         }

         if (!event.isCancelled()) {
            ++this.packets;
         }

      }
   }

   @SubscribeEvent
   public void onPacketRecieve(PacketEvent.Receive event) {
      class_2596 prediction = event.getPacket();
      if (prediction instanceof class_2708 packet) {
         if (((String)this.handleTeleport.getValue()).contains("Predict")) {
            class_243 prediction = (class_243)this.predictions.get(packet.method_11737());
            if (prediction != null && prediction.field_1352 == packet.method_11734() && prediction.field_1351 == packet.method_11735() && prediction.field_1350 == packet.method_11738()) {
               event.setCancelled(true);
               this.predictions.remove(packet.method_11737());
               PacketManager.INSTANCE.sendPacket(new class_2793(packet.method_11737()));
               return;
            }

            this.tpId = packet.method_11737();
            return;
         }

         if (((String)this.handleTeleport.getValue()).equals("All")) {
            PacketManager.INSTANCE.sendPacket(new class_2793(this.tpId - 1));
            PacketManager.INSTANCE.sendPacket(new class_2793(this.tpId));
            PacketManager.INSTANCE.sendPacket(new class_2793(this.tpId + 1));
         }

         if (((String)this.handleTeleport.getValue()).equals("Above")) {
            PacketManager.INSTANCE.sendPacket(new class_2793(this.tpId + 1));
         }
      }

      if (((String)this.inhibit.getValue()).equals("Both") || ((String)this.inhibit.getValue()).equals("Necessary")) {
         prediction = event.getPacket();
         if (prediction instanceof class_2848) {
            class_2848 packet = (class_2848)prediction;
            if (packet.method_12365().equals(class_2849.field_12981)) {
               event.setCancelled(true);
            } else if (packet.method_12365().equals(class_2849.field_12985)) {
               event.setCancelled(true);
            }
         }
      }

   }

   double getUpMovement() {
      return (Boolean)this.noVoid.getValue() && mc.field_1724.method_23318() <= (double)1.0F ? (double)(mc.field_1690.field_1903.method_1434() ? 1 : 0) * this.getSpeed() : (double)(mc.field_1690.field_1903.method_1434() ? 1 : (mc.field_1690.field_1832.method_1434() ? -1 : 0)) * this.getSpeed();
   }

   public void sendPackets(double x, double y, double z) {
      PacketManager.INSTANCE.sendPacket(new class_2828.class_2829(x, y, z, mc.field_1724.method_24828()));
      this.sendMovePackets(new class_243(mc.field_1724.method_23317(), mc.field_1724.method_23318() - (double)85.0F, mc.field_1724.method_23321()));
      if (((String)this.handleTeleport.getValue()).contains("Predict")) {
         mc.field_1724.method_5814(x, y, z);
         this.predictions.put(++this.tpId, new class_243(x, y, z));
         PacketManager.INSTANCE.sendPacket(new class_2793(this.tpId));
      }

   }

   public void sendMovePackets(class_243 vec) {
      PacketManager.INSTANCE.sendQuietPacket(new class_2828.class_2829(vec.field_1352, vec.field_1351, vec.field_1350, mc.field_1724.method_24828()));
   }

   public void onEnable() {
      super.onEnable();
      if (!NullUtils.nullCheck()) {
         ;
      }
   }

   @SubscribeEvent
   public void onSneak(SneakEvent event) {
      if (PlayerUtils.isMoving() && mc.field_1690.field_1832.method_1434()) {
         event.setCancelled(true);
      }

   }

   double getSpeed() {
      return (double)PlayerUtils.getBaseSpeed(0.2873F) / (double)10.0F;
   }

   boolean shouldPacket() {
      return mc.field_1724.field_5976 && (!(Boolean)this.onlyInBlock.getValue() || this.isPhasing());
   }

   boolean isInBlock() {
      return !(Boolean)this.onlyInBlock.getValue() || this.isPhasing();
   }

   public boolean isPhasing() {
      class_238 bb = mc.field_1724.method_5829();

      for(int x = class_3532.method_15357(bb.field_1323); x < class_3532.method_15357(bb.field_1320) + 1; ++x) {
         for(int y = class_3532.method_15357(bb.field_1322); y < class_3532.method_15357(bb.field_1325) + 1; ++y) {
            for(int z = class_3532.method_15357(bb.field_1321); z < class_3532.method_15357(bb.field_1324) + 1; ++z) {
               if (mc.field_1687.method_8320(new class_2338(x, y, z)).method_51366() && bb.method_994(new class_238((double)x, (double)y, (double)z, (double)(x + 1), (double)(y + 1), (double)(z + 1)))) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public String getDescription() {
      return "PhaseWalk: phases through blocks with the packetfly exploit";
   }

   public String getHudInfo() {
      return this.motion.getValue();
   }
}
