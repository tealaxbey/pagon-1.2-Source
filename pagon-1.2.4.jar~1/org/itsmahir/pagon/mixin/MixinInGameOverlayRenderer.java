package org.itsmahir.pagon.mixin;

import net.minecraft.class_1058;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4603;
import org.itsmahir.pagon.impl.features.modules.render.NoRender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_4603.class})
public class MixinInGameOverlayRenderer {
   @Inject(
      method = {"renderFireOverlay"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void hookRenderFireOverlay(class_310 client, class_4587 matrices, CallbackInfo ci) {
      if (NoRender.INSTANCE.isEnabled() && (Boolean)NoRender.INSTANCE.fire.getValue()) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"renderUnderwaterOverlay"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void hookRenderUnderwaterOverlay(class_310 client, class_4587 matrices, CallbackInfo ci) {
      if (NoRender.INSTANCE.isEnabled() && (Boolean)NoRender.INSTANCE.liquidVision.getValue()) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"renderInWallOverlay"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void hookRenderFireOverlay(class_1058 sprite, class_4587 matrices, CallbackInfo ci) {
      if (NoRender.INSTANCE.isEnabled() && (Boolean)NoRender.INSTANCE.blockInside.getValue()) {
         ci.cancel();
      }

   }
}
