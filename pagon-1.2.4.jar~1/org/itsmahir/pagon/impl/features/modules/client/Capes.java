package org.itsmahir.pagon.impl.features.modules.client;

import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class Capes extends Module {
   public static Capes INSTANCE;
   public Value<String> capeMode = (new ValueBuilder<String>()).withDescriptor("Cape Mode", "capeMode").withValue("Dark").withModes("Dark", "NN", "Turtle").register(this);

   public Capes() {
      super("Capes", Feature.Category.Client);
      INSTANCE = this;
   }

   public String getDescription() {
      return "Capes: Gives you cool capes that other pagon users can see";
   }
}
