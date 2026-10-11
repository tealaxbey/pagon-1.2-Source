package org.itsmahir.pagon.mixin;

import net.minecraft.class_1041;
import org.itsmahir.pagon.impl.features.modules.client.Renders;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1041.class})
public class MixinWindow {
   @Inject(
      method = {"<init>"},
      at = {@At("HEAD")}
   )
   private static void onWindowInit(CallbackInfo ci) {
      int samples = 0;

      try {
         if (Renders.INSTANCE != null) {
            samples = Renders.INSTANCE.getMsaaSamples();
         }
      } catch (Exception var3) {
      }

      if (samples > 0) {
         GLFW.glfwWindowHint(135181, samples);
      }

   }
}
