package org.itsmahir.pagon.impl.features.modules.client;

import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class Media extends Module {
   public static Media INSTANCE;
   public Value<String> replacement = (new ValueBuilder<String>()).withDescriptor("Spoof Name").withValue("PagonUser").register(this);
   public Value<Boolean> spoofSkin = (new ValueBuilder<Boolean>()).withDescriptor("Spoof Skin").withValue(false).register(this);

   public Media() {
      super("Media", Feature.Category.Client);
      INSTANCE = this;
   }

   public String getDescription() {
      return "";
   }
}
