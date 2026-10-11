package org.itsmahir.pagon.mixin;

import net.minecraft.class_3572;
import org.itsmahir.pagon.impl.features.modules.render.NoRender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_3572.class})
public class MixinChunkSkylightProvider {
   @Inject(
      method = {"method_51531"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookRecalculateLevel(long blockPos, long l, int lightLevel, CallbackInfo ci) {
      if ((Boolean)NoRender.INSTANCE.skyLight.getValue() && NoRender.INSTANCE.isEnabled()) {
         ci.cancel();
      }

   }
}
