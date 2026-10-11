package org.itsmahir.pagon.mixin;

import net.minecraft.class_1268;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_742;
import net.minecraft.class_759;
import net.minecraft.class_7833;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.features.modules.render.ViewModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_759.class})
public abstract class MixinHeldItemRenderer implements IMinecraft {
   @Inject(
      method = {"renderFirstPersonItem"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"
)}
   )
   private void hookRenderFirstPersonItem(class_742 player, float tickDelta, float pitch, class_1268 hand, float swingProgress, class_1799 item, float equipProgress, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
      if (ViewModel.INSTANCE.isEnabled() && (Boolean)ViewModel.INSTANCE.translate.getValue()) {
         matrices.method_22905(((Number)ViewModel.INSTANCE.scaleX.getValue()).floatValue(), ((Number)ViewModel.INSTANCE.scaleY.getValue()).floatValue(), ((Number)ViewModel.INSTANCE.scaleZ.getValue()).floatValue());
         matrices.method_22907(class_7833.field_40714.rotationDegrees(((Number)ViewModel.INSTANCE.rotateX.getValue()).floatValue()));
         if (hand == class_1268.field_5808) {
            matrices.method_46416(((Number)ViewModel.INSTANCE.posX.getValue()).floatValue(), ((Number)ViewModel.INSTANCE.posY.getValue()).floatValue(), ((Number)ViewModel.INSTANCE.posZ.getValue()).floatValue());
            matrices.method_22907(class_7833.field_40716.rotationDegrees(((Number)ViewModel.INSTANCE.rotateY.getValue()).floatValue()));
            matrices.method_22907(class_7833.field_40718.rotationDegrees(((Number)ViewModel.INSTANCE.rotateZ.getValue()).floatValue()));
         } else {
            matrices.method_46416(-((Number)ViewModel.INSTANCE.posX.getValue()).floatValue(), ((Number)ViewModel.INSTANCE.posY.getValue()).floatValue(), ((Number)ViewModel.INSTANCE.posZ.getValue()).floatValue());
            matrices.method_22907(class_7833.field_40716.rotationDegrees(-((Number)ViewModel.INSTANCE.rotateY.getValue()).floatValue()));
            matrices.method_22907(class_7833.field_40718.rotationDegrees(-((Number)ViewModel.INSTANCE.rotateZ.getValue()).floatValue()));
         }
      }

   }
}
