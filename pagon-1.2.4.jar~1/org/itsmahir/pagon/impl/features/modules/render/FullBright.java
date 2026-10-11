package org.itsmahir.pagon.impl.features.modules.render;

import net.minecraft.class_1293;
import net.minecraft.class_1294;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.render.LightmapGammaEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class FullBright extends Module {
   public Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Gamma").withModes("Gamma", "Potion").register(this);

   public FullBright() {
      super("FullBright", Feature.Category.Render);
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.ClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         if (((String)this.mode.getValue()).equals("Potion") && !mc.field_1724.method_6059(class_1294.field_5925)) {
            mc.field_1724.method_6092(new class_1293(class_1294.field_5925, -1, 0));
         }

      }
   }

   public void onDisable() {
      super.onDisable();
      if (!NullUtils.nullCheck()) {
         if (((String)this.mode.getValue()).equals("Potion")) {
            mc.field_1724.method_6016(class_1294.field_5925);
         }

      }
   }

   @SubscribeEvent
   public void onLightmapGamma(LightmapGammaEvent event) {
      if (((String)this.mode.getValue()).equals("Gamma")) {
         event.setCancelled(true);
         event.setGamma(-1);
      }

   }

   public void onEnable() {
      super.onEnable();
   }

   public String getDescription() {
      return "FullBright: Brights up the entire world";
   }
}
