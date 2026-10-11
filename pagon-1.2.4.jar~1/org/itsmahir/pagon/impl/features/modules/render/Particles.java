package org.itsmahir.pagon.impl.features.modules.render;

import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class Particles extends Module {
   public Value<PagonColor> colorOne = (new ValueBuilder<PagonColor>()).withDescriptor("Color").withValue(new PagonColor(255, 255, 255)).register(this);
   public Value<Boolean> doubleColor = (new ValueBuilder<Boolean>()).withDescriptor("Two Color").withValue(false).register(this);
   public Value<PagonColor> colorTwo;
   public static Particles INSTANCE;

   public Particles() {
      super("Particles", Feature.Category.Render);
      this.colorTwo = (new ValueBuilder<PagonColor>()).withDescriptor("Second Color").withValue(new PagonColor(0, 255, 72)).withParent(this.doubleColor).withParentEnabled(true).register(this);
      INSTANCE = this;
   }

   public String getDescription() {
      return "Particles: change the colors of various particles";
   }
}
