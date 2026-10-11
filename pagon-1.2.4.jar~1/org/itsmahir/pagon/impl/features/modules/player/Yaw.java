package org.itsmahir.pagon.impl.features.modules.player;

import net.minecraft.class_3532;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;

public class Yaw extends Module {
   public static Yaw INSTANCE;

   public Yaw() {
      super("Yaw", Feature.Category.Player);
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.ClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         float diff = 45.0F;
         float yaw = mc.field_1724.method_36454() + 180.0F;
         yaw = (float)Math.round(yaw / diff) * diff;
         yaw -= 180.0F;
         mc.field_1724.method_36456(yaw);
      }
   }

   public String getHudInfo() {
      return NullUtils.nullCheck() ? null : "" + class_3532.method_15375(class_3532.method_15393(mc.field_1724.method_36454()));
   }

   public String getDescription() {
      return "Yaw: Locks your yaw to go straight in one direction";
   }
}
