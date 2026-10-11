package org.itsmahir.pagon.mixin;

import java.util.List;
import net.minecraft.class_1309;
import net.minecraft.class_1921;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_3887;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_5617;
import net.minecraft.class_583;
import net.minecraft.class_897;
import net.minecraft.class_922;
import org.itsmahir.pagon.api.event.events.render.RenderEntityEvent;
import org.itsmahir.pagon.api.management.RotationManager;
import org.itsmahir.pagon.api.utils.ducks.ILivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_922.class})
public abstract class MixinLivingEntityRenderer<T extends class_1309, M extends class_583<T>> extends class_897<T> {
   @Unique
   private class_1309 pagon_entity;
   @Shadow
   protected M field_4737;
   @Shadow
   @Final
   protected List<class_3887<T, M>> field_4738;

   protected MixinLivingEntityRenderer(class_5617.class_5618 ctx) {
      super(ctx);
   }

   @Inject(
      method = {"render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = {@At("HEAD")}
   )
   private void storeEntity(T livingEntity, float f, float g, class_4587 matrixStack, class_4597 vertexConsumerProvider, int i, CallbackInfo ci) {
      this.pagon_entity = livingEntity;
   }

   @Redirect(
      method = {"render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/util/math/MathHelper;lerpAngleDegrees(FFF)F",
   ordinal = 1
)
   )
   public float modifyYaw(float delta, float start, float end) {
      if (this.pagon_entity == class_310.method_1551().field_1724 && !RotationManager.INSTANCE.FROM_INV) {
         ILivingEntity accessor = (ILivingEntity)this.pagon_entity;
         return class_3532.method_17821(delta, accessor.pagon_getPrevHeadYaw(), accessor.pagon_getHeadYaw());
      } else {
         return class_3532.method_17821(delta, start, end);
      }
   }

   @Redirect(
      method = {"render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/util/math/MathHelper;lerp(FFF)F",
   ordinal = 0
)
   )
   public float modifyPitch(float delta, float start, float end) {
      if (this.pagon_entity == class_310.method_1551().field_1724 && !RotationManager.INSTANCE.FROM_INV) {
         ILivingEntity accessor = (ILivingEntity)this.pagon_entity;
         return class_3532.method_16439(delta, accessor.pagon_getPrevHeadPitch(), accessor.pagon_getHeadPitch());
      } else {
         return class_3532.method_16439(delta, start, end);
      }
   }

   @Shadow
   protected abstract class_1921 method_24302(T var1, boolean var2, boolean var3, boolean var4);

   @Shadow
   protected abstract void method_4058(T var1, class_4587 var2, float var3, float var4, float var5, float var6);

   @Inject(
      method = {"render*"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookRender(class_1309 livingEntity, float f, float g, class_4587 matrixStack, class_4597 vertexConsumerProvider, int i, CallbackInfo ci) {
      RenderEntityEvent event = new RenderEntityEvent(livingEntity, f, g, matrixStack, vertexConsumerProvider, i, this.field_4737, this.method_24302(livingEntity, true, false, false), this.field_4738);
      event.post();
      if (event.isCancelled()) {
         ci.cancel();
      }

   }
}
