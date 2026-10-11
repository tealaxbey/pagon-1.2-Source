package org.itsmahir.pagon.api.management;

import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2664;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.PagonMod;

public class BoostManager implements IMinecraft {
   public static BoostManager INSTANCE;
   Timer explosionTimer = new Timer();
   Timer longjumpTimer = new Timer();
   double boostExplosionSpeed;
   boolean canLongjump = false;

   public BoostManager() {
      PagonMod.EVENT_BUS.register(this);
      this.boostExplosionSpeed = (double)0.0F;
   }

   @SubscribeEvent(1)
   public void onPacket(PacketEvent.Receive event) {
      if (!NullUtils.nullCheck()) {
         class_2596 var3 = event.getPacket();
         if (var3 instanceof class_2664) {
            class_2664 packet = (class_2664)var3;
            if (mc.field_1724.method_19538().method_1022(new class_243(packet.method_11475(), packet.method_11477(), packet.method_11478())) <= (double)6.0F && (packet.method_11472() != 0.0F || packet.method_11474() != 0.0F)) {
               this.explosionTimer.resetDelay();
               this.boostExplosionSpeed = Math.hypot((double)packet.method_11472(), (double)packet.method_11474());
               this.canLongjump = true;
               this.longjumpTimer.resetDelay();
            }
         }

      }
   }

   @SubscribeEvent
   public void onTick(TickEvent.ClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         this.explosionTimer.setDelay(400L);
         this.longjumpTimer.setDelay(500L);
         if (!PlayerUtils.isMoving()) {
            this.boostExplosionSpeed = (double)0.0F;
            this.canLongjump = false;
         }

         if (this.explosionTimer.isPassed()) {
            this.boostExplosionSpeed = (double)0.0F;
         }

         if (this.longjumpTimer.isPassed()) {
            this.canLongjump = false;
         }

      }
   }

   public double getBoostSpeed(boolean slow) {
      return slow && this.boostExplosionSpeed != (double)0.0F ? (double)0.5F : this.boostExplosionSpeed;
   }

   public boolean canDoLongjump() {
      return this.canLongjump;
   }
}
