package org.itsmahir.pagon.mixin;

import net.minecraft.class_1533;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_915;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.features.modules.render.NoRender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_915.class})
public class MixinRenderItemFrame<T extends class_1533> implements IMinecraft {
   @Inject(
      method = {"render(Lnet/minecraft/entity/decoration/ItemFrameEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void doRender(T itemFrameEntity, float f, float g, class_4587 matrixStack, class_4597 vertexConsumerProvider, int i, CallbackInfo ci) {
      if (NoRender.INSTANCE.isEnabled() && (Boolean)NoRender.INSTANCE.itemFrame.getValue()) {
         if (mc.field_1724 != null && (double)mc.field_1724.method_5739(itemFrameEntity) < (double)6.0F) {
            return;
         }

         ci.cancel();
      }

   }
}
