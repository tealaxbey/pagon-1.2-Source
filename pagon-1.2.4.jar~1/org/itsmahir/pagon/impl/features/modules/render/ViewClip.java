package org.itsmahir.pagon.impl.features.modules.render;

import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class ViewClip extends Module {
   public static ViewClip INSTANCE;
   public Value<Number> distance = (new ValueBuilder<Number>()).withDescriptor("Distance").withValue((double)4.0F).withRange((double)1.0F, (double)10.0F).register(this);

   public ViewClip() {
      super("ViewClip", Feature.Category.Render);
      INSTANCE = this;
   }

   public String getDescription() {
      return "ViewClip: makes your third person go through blocks";
   }
}
