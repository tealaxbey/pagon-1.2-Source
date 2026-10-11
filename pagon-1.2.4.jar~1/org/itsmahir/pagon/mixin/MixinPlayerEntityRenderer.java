package org.itsmahir.pagon.mixin;

import net.minecraft.class_1007;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_5617;
import net.minecraft.class_591;
import net.minecraft.class_742;
import net.minecraft.class_922;
import org.itsmahir.pagon.impl.features.modules.movement.FakeFly;
import org.itsmahir.pagon.impl.features.modules.movement.LongJump;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1007.class})
public class MixinPlayerEntityRenderer extends class_922<class_742, class_591<class_742>> {
   public MixinPlayerEntityRenderer(class_5617.class_5618 ctx, class_591<class_742> model, float shadowRadius) {
      super(ctx, model, shadowRadius);
   }

   @Inject(
      method = {"Lnet/minecraft/client/render/entity/PlayerEntityRenderer;setupTransforms(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/client/util/math/MatrixStack;FFFF)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   void setupTransforms(class_742 abstractClientPlayerEntity, class_4587 matrixStack, float f, float g, float h, float i, CallbackInfo ci) {
      if (abstractClientPlayerEntity == class_310.method_1551().field_1724 && (LongJump.isGrimJumping() || FakeFly.isFakeFlying())) {
         super.method_4058(abstractClientPlayerEntity, matrixStack, f, g, h, i);
         ci.cancel();
      }

   }

   @Shadow
   public class_2960 method_4216(class_742 entity) {
      return null;
   }
}
