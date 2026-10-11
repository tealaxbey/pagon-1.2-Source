package org.itsmahir.pagon.mixin;

import net.minecraft.class_312;
import net.minecraft.class_746;
import org.itsmahir.pagon.api.event.events.key.MouseEvent;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.features.modules.render.Freecam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_312.class})
public class MixinMouse implements IMinecraft {
   @Inject(
      method = {"onMouseButton"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void mouseEvent(long window, int button, int action, int mods, CallbackInfo ci) {
      if (window == mc.method_22683().method_4490()) {
         MouseEvent event = new MouseEvent(button, MouseEvent.Type.of(action));
         event.post();
         if (event.isCancelled()) {
            ci.cancel();
         }
      }

   }

   @Redirect(
      method = {"updateMouse"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/ClientPlayerEntity;changeLookDirection(DD)V"
)
   )
   private void updateMouseChangeLookDirection(class_746 player, double cursorDeltaX, double cursorDeltaY) {
      if (Freecam.INSTANCE.isEnabled()) {
         Freecam.INSTANCE.changeLookDirection(cursorDeltaX * 0.15, cursorDeltaY * 0.15);
      } else {
         player.method_5872(cursorDeltaX, cursorDeltaY);
      }

   }
}
