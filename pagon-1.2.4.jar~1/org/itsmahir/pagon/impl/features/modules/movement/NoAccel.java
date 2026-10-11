package org.itsmahir.pagon.impl.features.modules.movement;

import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1531;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2777;
import net.minecraft.class_3532;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.move.MoveEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.misc.FakePlayer;

public class NoAccel extends Module {
   Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Strict").withModes("Strict", "Max", "Collide").register(this);
   public static boolean paused;
   Timer timer = new Timer();

   public NoAccel() {
      super("NoAccel", Feature.Category.Movement);
   }

   @SubscribeEvent
   public void onMove(MoveEvent event) {
      if (!NullUtils.nullCheck()) {
         this.timer.setDelay(1000L);
         if (!((String)this.mode.getValue()).equals("Collide")) {
            if (mc.field_1724.method_6128() || mc.field_1724.method_31549().field_7479 || paused) {
               return;
            }

            if (mc.field_1724.method_5715() || mc.field_1724.method_5869() || mc.field_1724.method_5771()) {
               return;
            }

            if (((String)this.mode.getValue()).equals("Strict") && (!mc.field_1724.method_24828() || mc.field_1690.field_1903.method_1434())) {
               return;
            }

            if (PlayerUtils.isMoving()) {
               PlayerUtils.doStrafe(event, PlayerUtils.getStrictBaseSpeed(0.2873F));
            } else {
               event.motionX((double)0.0F);
               event.motionZ((double)0.0F);
            }
         } else {
            if (!this.timer.isPassed()) {
               return;
            }

            if (PlayerUtils.isMoving()) {
               int collisions = 0;

               for(class_1297 entity : mc.field_1687.method_18112()) {
                  if (this.checkIsCollidingEntity(entity) && (double)class_3532.method_15355((float)mc.field_1724.method_5858(entity)) <= (double)1.5F) {
                     ++collisions;
                  }
               }

               if (collisions > 0) {
                  class_243 velocity = mc.field_1724.method_18798();
                  double factor = 0.07 * (double)collisions;
                  class_241 strafe = PlayerUtils.getStrafeVec((float)factor);
                  mc.field_1724.method_18800(velocity.field_1352 + (double)strafe.field_1343, velocity.field_1351, velocity.field_1350 + (double)strafe.field_1342);
               }
            }
         }

      }
   }

   public boolean checkIsCollidingEntity(class_1297 entity) {
      return entity != null && entity != mc.field_1724 && entity instanceof class_1309 && (FakePlayer.INSTANCE.fakePlayer == null || FakePlayer.INSTANCE.fakePlayer != entity) && !(entity instanceof class_1531);
   }

   @SubscribeEvent
   public void onPacketReceive(PacketEvent.Receive event) {
      class_2596 var3 = event.getPacket();
      if (var3 instanceof class_2777 packet) {
         if (packet.method_11916() == mc.field_1724.method_5628()) {
            this.timer.resetDelay();
         }
      }

   }

   public String getHudInfo() {
      return this.mode.getValue();
   }

   public String getDescription() {
      return "NoAccel: Removes the acceleration when you run so you are always as fast as possible";
   }
}
