package org.itsmahir.pagon.impl.features.modules.movement;

import java.util.Random;
import net.minecraft.class_1268;
import net.minecraft.class_1690;
import net.minecraft.class_243;
import net.minecraft.class_2708;
import net.minecraft.class_2752;
import net.minecraft.class_2824;
import net.minecraft.class_2833;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class EntitySpeed extends Module {
   Value<Number> speed = (new ValueBuilder<Number>()).withDescriptor("Speed").withValue((double)2.0F).withRange(0.1, (double)5.0F).register(this);
   Value<Boolean> strict = (new ValueBuilder<Boolean>()).withDescriptor("Strict").withValue(false).register(this);
   Value<Boolean> packet = (new ValueBuilder<Boolean>()).withDescriptor("Packet").withValue(false).register(this);
   Value<String> bounds;
   public static EntitySpeed INSTANCE;
   private static final Random rand = new Random();

   public EntitySpeed() {
      super("EntitySpeed", Feature.Category.Movement);
      this.bounds = (new ValueBuilder<String>()).withDescriptor("Bounds").withValue("Up").withModes("Up", "Down", "Preserve", "Limit", "Soft", "Snap").withParent(this.packet).withParentEnabled(true).register(this);
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (mc.field_1724.method_3144() && mc.field_1724.method_49694() != null) {
            double d = Math.cos(Math.toRadians((double)(mc.field_1724.method_36454() + 90.0F)));
            double d2 = Math.sin(Math.toRadians((double)(mc.field_1724.method_36454() + 90.0F)));
            if ((Boolean)this.strict.getValue()) {
               PacketManager.INSTANCE.sendPacket(class_2824.method_34207(mc.field_1724.method_49694(), false, class_1268.field_5808));
            }

            this.handleEntityMotion(((Number)this.speed.getValue()).floatValue(), d, d2);
         }

      }
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.PlayerTickEvent.Post event) {
      if (!NullUtils.nullCheck()) {
         if ((Boolean)this.packet.getValue() && mc.field_1724.method_3144() && mc.field_1724.method_49694() != null) {
            class_243 vec3d = mc.field_1724.method_49694().method_19538().method_1031((double)0.0F, this.getBounds(mc.field_1724.method_49694().method_19538()), (double)0.0F);
            class_1690 entityBoat = new class_1690(mc.field_1687, vec3d.field_1352, vec3d.field_1351, vec3d.field_1350);
            entityBoat.method_36456(mc.field_1724.method_49694().method_36454());
            entityBoat.method_36457(mc.field_1724.method_49694().method_36455());
            PacketManager.INSTANCE.sendPacket(new class_2833(entityBoat));
         }

      }
   }

   @SubscribeEvent
   public void onPacketInbound(PacketEvent.Send event) {
      if (!NullUtils.nullCheck()) {
         if (mc.field_1724.method_3144() && !mc.field_1690.field_1832.method_1434() && mc.field_1724.method_49694() != null) {
            if ((Boolean)this.strict.getValue()) {
               if (event.getPacket() instanceof class_2752) {
                  event.setCancelled(true);
               } else if (event.getPacket() instanceof class_2708) {
                  event.setCancelled(true);
               }
            }

         }
      }
   }

   private void handleEntityMotion(float entitySpeed, double d, double d2) {
      class_243 motion = mc.field_1724.method_49694().method_18798();
      float forward = mc.field_1724.field_3913.field_3905;
      float strafe = mc.field_1724.field_3913.field_3907;
      if (forward == 0.0F && strafe == 0.0F) {
         mc.field_1724.method_49694().method_18800((double)0.0F, motion.field_1351, (double)0.0F);
      } else {
         mc.field_1724.method_49694().method_18800((double)(forward * entitySpeed) * d + (double)(strafe * entitySpeed) * d2, motion.field_1351, (double)(forward * entitySpeed) * d2 - (double)(strafe * entitySpeed) * d);
      }
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

   public String getHudInfo() {
      double var10000 = ((Number)this.speed.getValue()).doubleValue();
      return "" + MathUtil.round(var10000, 1);
   }

   public String getDescription() {
      return "EntitySpeed: Rideee (fast) till u cant no more";
   }
}
