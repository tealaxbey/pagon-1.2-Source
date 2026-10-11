package org.itsmahir.pagon.impl.features.modules.render;

import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class UltraFog extends Module {
   public static UltraFog INSTANCE;
   public Value<Number> fogStart = (new ValueBuilder<Number>()).withDescriptor("Start Multiplier").withValue(0.1F).withRange(0.0F, 10.0F).register(this);
   public Value<Number> fogEnd = (new ValueBuilder<Number>()).withDescriptor("End Multiplier").withValue(1.0F).withRange(0.1F, 10.0F).register(this);
   public Value<Boolean> customColor = (new ValueBuilder<Boolean>()).withDescriptor("Custom Color").withValue(false).register(this);
   public Value<PagonColor> color;

   public UltraFog() {
      super("UltraFog", Feature.Category.Render);
      this.color = (new ValueBuilder<PagonColor>()).withDescriptor("Color").withValue(new PagonColor(255, 255, 255)).withParent(this.customColor).register(this);
      INSTANCE = this;
   }
}
