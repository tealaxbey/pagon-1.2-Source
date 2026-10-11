package org.itsmahir.pagon.impl.features.modules.movement;

import com.google.common.collect.Streams;
import net.minecraft.class_238;
import net.minecraft.class_265;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;

public class Parkour extends Module {
   public static Parkour INSTANCE;

   public Parkour() {
      super("Parkour", Feature.Category.Movement);
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (mc.field_1724.method_24828() && !mc.field_1690.field_1903.method_1434()) {
            if (!mc.field_1724.method_5715() && !mc.field_1690.field_1832.method_1434()) {
               class_238 entityBoundingBox = mc.field_1724.method_5829();
               class_238 offsetBox = entityBoundingBox.method_989((double)0.0F, (double)-0.5F, (double)0.0F).method_1009(-0.001, (double)0.0F, -0.001);
               Iterable<class_265> collisionBoxes = mc.field_1687.method_8600(mc.field_1724, offsetBox);
               if (Streams.stream(collisionBoxes).toList().isEmpty()) {
                  mc.field_1724.method_6043();
               }
            }
         }
      }
   }

   public String getDescription() {
      return "Parkour: Jumps at edge of blocks";
   }
}
