package org.itsmahir.pagon.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_742;
import net.minecraft.class_972;
import org.itsmahir.pagon.impl.features.modules.client.Capes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({class_972.class})
public class CapeFeatureRendererMixin {
   @ModifyExpressionValue(
      method = {"render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/network/AbstractClientPlayerEntity;FFFFFF)V"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/util/SkinTextures;capeTexture()Lnet/minecraft/util/Identifier;"
)}
   )
   private class_2960 modifyCapeTexture(class_2960 original, class_4587 matrixStack, class_4597 vertexConsumerProvider, int i, class_742 abstractClientPlayerEntity, float f, float g, float h, float j, float k, float l) {
      return Capes.INSTANCE.isEnabled() && abstractClientPlayerEntity.method_7334() != null && abstractClientPlayerEntity.method_7334().getId() != null && class_310.method_1551().method_1548().method_1676().equals(abstractClientPlayerEntity.method_7334().getName()) ? class_2960.method_60655("pagon", "capes/" + ((String)Capes.INSTANCE.capeMode.getValue()).toLowerCase() + ".png") : original;
   }
}
