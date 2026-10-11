package org.itsmahir.pagon.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_4184;
import net.minecraft.class_758;
import net.minecraft.class_758.class_4596;
import org.itsmahir.pagon.api.event.events.render.RenderFogEvent;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.impl.features.modules.render.SkyBox;
import org.itsmahir.pagon.impl.features.modules.render.UltraFog;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_758.class})
public class MixinBackgroundRenderer {
   @Inject(
      method = {"applyFog"},
      at = {@At("TAIL")}
   )
   private static void hookApplyFog(class_4184 camera, class_758.class_4596 fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
      if (fogType == class_4596.field_20946) {
         RenderFogEvent event = new RenderFogEvent();
         event.post();
         if (event.isCancelled()) {
            RenderSystem.setShaderFogStart(viewDistance * 4.0F);
            RenderSystem.setShaderFogEnd(viewDistance * 4.25F);
            return;
         }
      }

      if (UltraFog.INSTANCE != null && UltraFog.INSTANCE.isEnabled()) {
         float start = viewDistance * ((Number)UltraFog.INSTANCE.fogStart.getValue()).floatValue();
         float end = viewDistance * ((Number)UltraFog.INSTANCE.fogEnd.getValue()).floatValue();
         RenderSystem.setShaderFogStart(start);
         RenderSystem.setShaderFogEnd(end);
         if ((Boolean)UltraFog.INSTANCE.customColor.getValue()) {
            float r = ((PagonColor)UltraFog.INSTANCE.color.getValue()).getGlRed();
            float g = ((PagonColor)UltraFog.INSTANCE.color.getValue()).getGlGreen();
            float b = ((PagonColor)UltraFog.INSTANCE.color.getValue()).getGlBlue();
            RenderSystem.setShaderFogColor(r, g, b);
            return;
         }
      }

      if (SkyBox.INSTANCE != null && SkyBox.INSTANCE.isEnabled()) {
         float r = ((PagonColor)SkyBox.INSTANCE.fogColor.getValue()).getGlRed();
         float g = ((PagonColor)SkyBox.INSTANCE.fogColor.getValue()).getGlGreen();
         float b = ((PagonColor)SkyBox.INSTANCE.fogColor.getValue()).getGlBlue();
         RenderSystem.setShaderFogColor(r, g, b);
      }

   }
}
