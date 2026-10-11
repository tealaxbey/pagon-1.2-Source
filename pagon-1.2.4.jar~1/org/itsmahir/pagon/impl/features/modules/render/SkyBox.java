package org.itsmahir.pagon.impl.features.modules.render;

import net.minecraft.class_2960;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class SkyBox extends Module {
   public static SkyBox INSTANCE;
   public static final class_2960 SKYBOX_TEXTURE = class_2960.method_60655("pagon", "textures/skybox.png");
   public Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Texture").withModes("Color", "Texture").register(this);
   public Value<PagonColor> fogColor;

   public SkyBox() {
      super("SkyBox", Feature.Category.Render);
      this.fogColor = (new ValueBuilder<PagonColor>()).withDescriptor("Color").withValue(new PagonColor(0, 255, 255, 255)).withPageParent(this.mode).withPage("Color").register(this);
      INSTANCE = this;
   }

   public boolean isTextureMode() {
      return ((String)this.mode.getValue()).equals("Texture");
   }

   public String getDescription() {
      return "SkyBox: changes the color of the sky";
   }
}
