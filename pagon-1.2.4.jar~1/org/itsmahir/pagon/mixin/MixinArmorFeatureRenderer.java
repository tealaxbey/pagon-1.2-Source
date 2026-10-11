package org.itsmahir.pagon.mixin;

import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_572;
import net.minecraft.class_970;
import org.itsmahir.pagon.impl.features.modules.render.NoRender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_970.class})
public class MixinArmorFeatureRenderer {
   @Inject(
      method = {"renderArmor"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookRenderArmor(class_4587 matrices, class_4597 vertexConsumers, class_1309 entity, class_1304 armorSlot, int light, class_572<?> model, CallbackInfo ci) {
      if (NoRender.INSTANCE.isEnabled() && (Boolean)NoRender.INSTANCE.armor.getValue()) {
         ci.cancel();
      }

   }
}
