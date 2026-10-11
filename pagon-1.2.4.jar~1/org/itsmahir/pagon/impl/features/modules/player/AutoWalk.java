package org.itsmahir.pagon.impl.features.modules.player;

import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.key.InputEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;

public class AutoWalk extends Module {
   public AutoWalk() {
      super("AutoWalk", Feature.Category.Player);
   }

   @SubscribeEvent
   public void onUpdate(InputEvent event) {
      if (!NullUtils.nullCheck()) {
         ++event.input.field_3905;
      }
   }

   public String getDescription() {
      return "AutoWalk: Walks forward automatically";
   }
}
