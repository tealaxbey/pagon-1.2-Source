package org.itsmahir.pagon.mixin;

import net.minecraft.class_2960;
import net.minecraft.class_329;
import net.minecraft.class_332;
import net.minecraft.class_9779;
import org.itsmahir.pagon.api.event.events.render.RenderGameOverlayEvent;
import org.itsmahir.pagon.impl.features.modules.render.NoRender;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_329.class})
public class MixinInGameHud {
   @Shadow
   @Final
   private static class_2960 field_2019;
   @Shadow
   @Final
   private static class_2960 field_27960;

   @Inject(
      at = {@At("RETURN")},
      method = {"renderMainHud"}
   )
   public void renderMainHud(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      (new RenderGameOverlayEvent.Text(context, tickCounter)).post();
      context.method_51452();
   }

   @Inject(
      method = {"renderOverlay"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookRenderOverlay(class_332 context, class_2960 texture, float opacity, CallbackInfo ci) {
      if (NoRender.INSTANCE.isEnabled()) {
         if (texture.method_12832().equals(field_2019.method_12832())) {
            if ((Boolean)NoRender.INSTANCE.pumpkin.getValue()) {
               ci.cancel();
            }
         } else if (texture.method_12832().equals(field_27960.method_12832()) && (Boolean)NoRender.INSTANCE.frost.getValue()) {
            ci.cancel();
         }
      }

   }

   @Inject(
      method = {"renderStatusEffectOverlay"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookRenderStatusEffectOverlay(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      if (NoRender.INSTANCE.isEnabled() && (Boolean)NoRender.INSTANCE.potionGui.getValue()) {
         ci.cancel();
      }

   }
}
