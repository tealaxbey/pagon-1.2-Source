package org.itsmahir.pagon.mixin;

import net.minecraft.class_332;
import net.minecraft.class_374;
import org.itsmahir.pagon.impl.features.modules.render.NoRender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_374.class})
public class MixinToastManager {
   @Inject(
      method = {"draw"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookDraw(class_332 context, CallbackInfo ci) {
      if ((Boolean)NoRender.INSTANCE.advancements.getValue()) {
         ci.cancel();
      }

   }
}
