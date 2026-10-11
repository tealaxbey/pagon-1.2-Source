package org.itsmahir.pagon.impl.features.modules.movement;

import java.util.Random;
import net.minecraft.class_1297;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class BoatFly extends Module {
   Value<Number> speed = (new ValueBuilder<Number>()).withDescriptor("Speed").withValue(0.9).withPlaces(1).withRange(0.1, (double)12.0F).register(this);
   Value<Boolean> glide = (new ValueBuilder<Boolean>()).withDescriptor("Glide").withValue(false).register(this);
   Value<Number> fallSpeed;
   private static final Random rand = new Random();
   public static BoatFly INSTANCE;

   public BoatFly() {
      super("BoatFly", Feature.Category.Movement);
      this.fallSpeed = (new ValueBuilder<Number>()).withDescriptor("Fall Speed").withValue((double)0.0F).withPlaces(1).withRange((double)0.0F, (double)2.0F).withParent(this.glide).withParentEnabled(true).register(this);
      INSTANCE = this;
   }

   @SubscribeEvent
   private void onTick(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (mc.field_1724.method_49694() != null) {
            class_1297 entity = mc.field_1724.method_49694();
            if (mc.field_1690.field_1903.method_1434() && !mc.field_1690.field_1832.method_1434()) {
               entity.method_18800(entity.method_18798().field_1352, (double)((Number)this.speed.getValue()).floatValue(), entity.method_18798().field_1350);
            } else if (mc.field_1690.field_1832.method_1434() && !mc.field_1690.field_1903.method_1434()) {
               entity.method_18800(entity.method_18798().field_1352, (double)(-((Number)this.speed.getValue()).floatValue()), entity.method_18798().field_1350);
            } else if ((Boolean)this.glide.getValue()) {
               entity.method_18800(entity.method_18798().field_1352, (double)(-((Number)this.fallSpeed.getValue()).floatValue()), entity.method_18798().field_1350);
            } else {
               entity.method_18800(entity.method_18798().field_1352, (double)0.0F, entity.method_18798().field_1350);
            }

            entity.method_36456(mc.field_1724.method_36454());
         }
      }
   }

   public String getHudInfo() {
      return "Strong";
   }

   public String getDescription() {
      return "BoatFly: Fly On Entitys";
   }
}
