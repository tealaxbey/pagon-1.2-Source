package org.itsmahir.pagon.mixin;

import net.minecraft.class_1937;
import org.itsmahir.pagon.impl.features.modules.render.SkyBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1937.class})
public abstract class MixinWorld {
   @Inject(
      method = {"getTimeOfDay"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void getTimeOfDayHook(CallbackInfoReturnable<Long> cir) {
      if (SkyBox.INSTANCE.isEnabled()) {
         cir.setReturnValue(1000L);
      }

   }
}
