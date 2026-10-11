package org.itsmahir.pagon.impl.features.modules.movement;

import net.minecraft.class_2338;
import net.minecraft.class_2510;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;

public class FastClimb extends Module {
   public static FastClimb INSTANCE;

   public FastClimb() {
      super("FastClimb", Feature.Category.Movement);
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if ((double)mc.field_1724.field_3913.field_3905 > 0.01 && mc.field_1724.method_24828() && mc.field_1687.method_8320(new class_2338((int)mc.field_1724.method_23317(), (int)(mc.field_1724.method_23318() - (double)1.0F), (int)mc.field_1724.method_23321())).method_26204() instanceof class_2510) {
            mc.field_1724.method_6043();
         }

      }
   }

   public String getDescription() {
      return "FastClimb: Climbs up stairs fast";
   }
}
