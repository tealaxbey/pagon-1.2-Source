package org.itsmahir.pagon.impl.features.modules.misc;

import java.util.Random;
import net.minecraft.class_1268;
import net.minecraft.class_304;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class AntiAFK extends Module {
   public static AntiAFK INSTANCE;
   Value<Boolean> walk = (new ValueBuilder<Boolean>()).withDescriptor("Walk").withValue(true).register(this);
   Value<Boolean> punch = (new ValueBuilder<Boolean>()).withDescriptor("Punch").withValue(true).register(this);
   Value<Boolean> jump = (new ValueBuilder<Boolean>()).withDescriptor("Jump").withValue(false).register(this);
   Value<Boolean> look = (new ValueBuilder<Boolean>()).withDescriptor("Look").withValue(false).register(this);
   Random random;

   public AntiAFK() {
      super("AntiAFK", Feature.Category.Misc);
      INSTANCE = this;
      this.random = new Random();
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.ClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         this.randomAction();
      }
   }

   public void randomAction() {
      if (mc.field_1724.field_6012 % 30 == 0 && (Boolean)this.look.getValue()) {
         mc.field_1724.method_36456((float)(this.random.nextInt(360) - 180));
      }

      if (mc.field_1724.field_6012 % 40 == 0 && (Boolean)this.jump.getValue() && mc.field_1724.method_24828()) {
         mc.field_1724.method_6043();
      }

      if (mc.field_1724.field_6012 % 45 == 0 && (Boolean)this.punch.getValue()) {
         mc.field_1724.method_6104(class_1268.field_5808);
      }

      if ((Boolean)this.walk.getValue()) {
         class_304.method_1416(mc.field_1690.field_1894.method_1429(), this.random.nextBoolean());
         class_304.method_1416(mc.field_1690.field_1881.method_1429(), this.random.nextBoolean());
         class_304.method_1416(mc.field_1690.field_1913.method_1429(), this.random.nextBoolean());
         class_304.method_1416(mc.field_1690.field_1849.method_1429(), this.random.nextBoolean());
      }

   }

   public String getDescription() {
      return "AntiAFK: Moves around so u cant get kicked for afk";
   }
}
