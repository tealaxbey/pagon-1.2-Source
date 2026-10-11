package org.itsmahir.pagon.mixin;

import net.minecraft.class_304;
import net.minecraft.class_743;
import net.minecraft.class_744;
import org.itsmahir.pagon.api.event.events.key.InputEvent;
import org.itsmahir.pagon.api.event.events.move.SneakEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_743.class})
public class MixinKeyboardInput extends class_744 {
   @Inject(
      method = {"tick"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookTick$Pre(boolean slowDown, float slowDownFactor, CallbackInfo info) {
      InputEvent event = new InputEvent(this);
      event.post();
      if (event.isCancelled()) {
         info.cancel();
      }

   }

   @Redirect(
      method = {"tick"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/option/KeyBinding;isPressed()Z",
   ordinal = 5
)
   )
   private boolean sneakHook(class_304 instance) {
      SneakEvent event = new SneakEvent();
      event.post();
      return event.isCancelled() ? false : instance.method_1434();
   }

   @Inject(
      method = {"tick"},
      at = {@At(
   value = "FIELD",
   target = "Lnet/minecraft/client/input/KeyboardInput;sneaking:Z",
   shift = Shift.BEFORE
)},
      cancellable = true
   )
   private void hookTick$Post(boolean slowDown, float f, CallbackInfo ci) {
      InputEvent event = new InputEvent(this);
      event.post();
      if (event.isCancelled()) {
         ci.cancel();
      }

   }
}
