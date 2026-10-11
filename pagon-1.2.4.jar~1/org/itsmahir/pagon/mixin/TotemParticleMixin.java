package org.itsmahir.pagon.mixin;

import java.awt.Color;
import net.minecraft.class_4002;
import net.minecraft.class_638;
import net.minecraft.class_708;
import net.minecraft.class_734;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.impl.features.modules.render.Particles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_734.class})
public class TotemParticleMixin extends class_708 {
   protected TotemParticleMixin(class_638 world, double x, double y, double z, class_4002 spriteProvider, float upwardsAcceleration) {
      super(world, x, y, z, spriteProvider, upwardsAcceleration);
   }

   @Inject(
      at = {@At("TAIL")},
      method = {"<init>"}
   )
   private void colorChanger(class_638 world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, class_4002 spriteProvider, CallbackInfo ci) {
      if (Particles.INSTANCE.isEnabled()) {
         Color color = ((PagonColor)Particles.INSTANCE.colorOne.getValue()).getColor();
         if ((Boolean)Particles.INSTANCE.doubleColor.getValue()) {
            Color colorTwo = ((PagonColor)Particles.INSTANCE.colorTwo.getValue()).getColor();
            if (this.field_3840.method_43048(2) == 0) {
               this.method_3084((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F);
            } else {
               this.method_3084((float)colorTwo.getRed() / 255.0F, (float)colorTwo.getGreen() / 255.0F, (float)colorTwo.getBlue() / 255.0F);
            }
         } else {
            this.method_3084((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F);
         }
      }

   }
}
