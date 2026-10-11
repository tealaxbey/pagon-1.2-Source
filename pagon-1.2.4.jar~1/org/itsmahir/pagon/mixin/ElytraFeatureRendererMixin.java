package org.itsmahir.pagon.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.class_1309;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3883;
import net.minecraft.class_3887;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_583;
import net.minecraft.class_742;
import net.minecraft.class_979;
import org.itsmahir.pagon.impl.features.modules.client.Capes;
import org.itsmahir.pagon.impl.features.modules.movement.FakeFly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_979.class})
public abstract class ElytraFeatureRendererMixin<T extends class_1309, M extends class_583<T>> extends class_3887<T, M> {
   public ElytraFeatureRendererMixin(class_3883<T, M> context) {
      super(context);
   }

   @Inject(
      method = {"render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hideElytraOnFakeFly(class_4587 matrixStack, class_4597 vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
      if (livingEntity instanceof class_742 playerEntity) {
         if (FakeFly.INSTANCE.isEnabled() && playerEntity.method_7334() != null && playerEntity.method_7334().getId() != null && class_310.method_1551().method_1548().method_44717().toString().equals(playerEntity.method_7334().getId().toString())) {
            ci.cancel();
         }

      }
   }

   @ModifyExpressionValue(
      method = {"render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/util/SkinTextures;capeTexture()Lnet/minecraft/util/Identifier;"
)}
   )
   private class_2960 modifyCapeTexture(class_2960 original, class_4587 matrixStack, class_4597 vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
      if (livingEntity instanceof class_742 playerEntity) {
         return Capes.INSTANCE.isEnabled() && playerEntity.method_7334() != null && playerEntity.method_7334().getId() != null && class_310.method_1551().method_1548().method_44717().toString().equals(playerEntity.method_7334().getId().toString()) ? class_2960.method_60655("pagon", "capes/" + ((String)Capes.INSTANCE.capeMode.getValue()).toLowerCase() + ".png") : original;
      } else {
         return original;
      }
   }
}
