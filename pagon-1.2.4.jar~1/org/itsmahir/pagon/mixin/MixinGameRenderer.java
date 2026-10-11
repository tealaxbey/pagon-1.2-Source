package org.itsmahir.pagon.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_3965;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_5636;
import net.minecraft.class_757;
import net.minecraft.class_9779;
import org.itsmahir.pagon.api.event.events.render.RenderHandEvent;
import org.itsmahir.pagon.api.utils.ducks.IVec3d;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.features.modules.misc.NoEntityTrace;
import org.itsmahir.pagon.impl.features.modules.render.BlockHighlight;
import org.itsmahir.pagon.impl.features.modules.render.Freecam;
import org.itsmahir.pagon.impl.features.modules.render.NoRender;
import org.itsmahir.pagon.impl.features.modules.render.ViewModel;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_757.class})
public abstract class MixinGameRenderer implements IMinecraft {
   @Shadow
   private float field_4005;
   @Shadow
   private float field_3988;
   @Shadow
   private float field_4004;
   @Shadow
   private float field_4025;
   @Shadow
   private float field_4019;
   @Shadow
   private float field_3999;
   @Shadow
   private boolean field_4001;
   private boolean freecamSet = false;

   @Shadow
   public abstract void method_3190(float var1);

   @Inject(
      method = {"renderNausea"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookRenderNausea(class_332 context, float distortionStrength, CallbackInfo ci) {
      if ((Boolean)NoRender.INSTANCE.portal.getValue() && NoRender.INSTANCE.isEnabled()) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"tiltViewWhenHurt"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookTiltViewWhenHurt(class_4587 matrices, float tickDelta, CallbackInfo ci) {
      if ((Boolean)NoRender.INSTANCE.noHurtCam.getValue() && NoRender.INSTANCE.isEnabled()) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"renderWorld"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/GameRenderer;renderHand(Lnet/minecraft/client/render/Camera;FLorg/joml/Matrix4f;)V",
   shift = Shift.AFTER
)}
   )
   public void hookRenderWorld$2(class_9779 tickCounter, CallbackInfo ci, @Local(ordinal = 1) Matrix4f matrix4f2, @Local(ordinal = 1) float tickDelta, @Local class_4587 matrixStack) {
      RenderHandEvent event = new RenderHandEvent(matrixStack, mc.method_60646().method_60637(false));
      event.post();
   }

   @Inject(
      method = {"findCrosshairTarget"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void findCrosshairTargetHook(class_1297 camera, double blockInteractionRange, double entityInteractionRange, float tickDelta, CallbackInfoReturnable<class_239> cir) {
      if (NoEntityTrace.spoofTrace()) {
         double d = Math.max(blockInteractionRange, entityInteractionRange);
         class_243 vec3d = camera.method_5836(tickDelta);
         class_239 hitResult = camera.method_5745(d, tickDelta, false);
         cir.setReturnValue(this.ensureTargetInRangeCustom(hitResult, vec3d, blockInteractionRange));
      }

   }

   @Unique
   private class_239 ensureTargetInRangeCustom(class_239 hitResult, class_243 cameraPos, double interactionRange) {
      class_243 vec3d = hitResult.method_17784();
      if (!vec3d.method_24802(cameraPos, interactionRange)) {
         class_243 vec3d2 = hitResult.method_17784();
         class_2350 direction = class_2350.method_10142(vec3d2.field_1352 - cameraPos.field_1352, vec3d2.field_1351 - cameraPos.field_1351, vec3d2.field_1350 - cameraPos.field_1350);
         return class_3965.method_17778(vec3d2, direction, class_2338.method_49638(vec3d2));
      } else {
         return hitResult;
      }
   }

   @Inject(
      method = {"updateCrosshairTarget"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void updateTargetedEntityInvoke(float tickDelta, CallbackInfo info) {
      Freecam freecam = Freecam.INSTANCE;
      if (freecam.isEnabled() && mc.method_1560() != null && !this.freecamSet) {
         info.cancel();
         class_1297 cameraE = mc.method_1560();
         double x = cameraE.method_23317();
         double y = cameraE.method_23318();
         double z = cameraE.method_23321();
         double prevX = cameraE.field_6014;
         double prevY = cameraE.field_6036;
         double prevZ = cameraE.field_5969;
         float yaw = cameraE.method_36454();
         float pitch = cameraE.method_36455();
         float prevYaw = cameraE.field_5982;
         float prevPitch = cameraE.field_6004;
         ((IVec3d)cameraE.method_19538()).set(freecam.pos.x, freecam.pos.y - (double)cameraE.method_18381(cameraE.method_18376()), freecam.pos.z);
         cameraE.field_6014 = freecam.prevPos.x;
         cameraE.field_6036 = freecam.prevPos.y - (double)cameraE.method_18381(cameraE.method_18376());
         cameraE.field_5969 = freecam.prevPos.z;
         cameraE.method_36456(freecam.yaw);
         cameraE.method_36457(freecam.pitch);
         cameraE.field_5982 = freecam.prevYaw;
         cameraE.field_6004 = freecam.prevPitch;
         this.freecamSet = true;
         this.method_3190(tickDelta);
         this.freecamSet = false;
         ((IVec3d)cameraE.method_19538()).set(x, y, z);
         cameraE.field_6014 = prevX;
         cameraE.field_6036 = prevY;
         cameraE.field_5969 = prevZ;
         cameraE.method_36456(yaw);
         cameraE.method_36457(pitch);
         cameraE.field_5982 = prevYaw;
         cameraE.field_6004 = prevPitch;
      }

   }

   @Inject(
      method = {"getFov(Lnet/minecraft/client/render/Camera;FZ)D"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void getFov(class_4184 camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Double> ci) {
      if (!this.field_4001 && ViewModel.INSTANCE.isEnabled()) {
         double d = (double)70.0F;
         if (changingFov) {
            if (ViewModel.INSTANCE.isEnabled() && !((String)ViewModel.INSTANCE.fovMode.getValue()).equals("None")) {
               ci.setReturnValue(((Number)ViewModel.INSTANCE.fov.getValue()).doubleValue());
               return;
            }

            d = (double)(Integer)mc.field_1690.method_41808().method_41753();
            d *= (double)class_3532.method_16439(tickDelta, this.field_3999, this.field_4019);
         } else if (ViewModel.INSTANCE.isEnabled() && !((String)ViewModel.INSTANCE.fovMode.getValue()).equals("None") && (Boolean)ViewModel.INSTANCE.items.getValue()) {
            ci.setReturnValue(((Number)ViewModel.INSTANCE.itemFov.getValue()).doubleValue());
            return;
         }

         if (camera.method_19331() instanceof class_1309 && ((class_1309)camera.method_19331()).method_29504()) {
            float f = Math.min((float)((class_1309)camera.method_19331()).field_6213 + tickDelta, 20.0F);
            d /= (double)((1.0F - 500.0F / (f + 500.0F)) * 2.0F + 1.0F);
         }

         class_5636 cameraSubmersionType = camera.method_19334();
         if (cameraSubmersionType == class_5636.field_27885 || cameraSubmersionType == class_5636.field_27886) {
            d *= class_3532.method_16436((Double)mc.field_1690.method_42454().method_41753(), (double)1.0F, (double)0.85714287F);
         }

         ci.setReturnValue(d);
      }

   }

   @Inject(
      method = {"showFloatingItem"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void showFloatingItemHook(class_1799 floatingItem, CallbackInfo info) {
      if (NoRender.INSTANCE.isEnabled() && (Boolean)NoRender.INSTANCE.totem.getValue()) {
         info.cancel();
      }

   }

   @Inject(
      method = {"renderFloatingItem"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderFloatingItemHook(class_332 context, float tickDelta, CallbackInfo ci) {
      if (NoRender.INSTANCE.isEnabled() && (Boolean)NoRender.INSTANCE.totem.getValue()) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"shouldRenderBlockOutline"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookShouldRenderBlockOutline(CallbackInfoReturnable<Boolean> cir) {
      if (BlockHighlight.INSTANCE.isEnabled()) {
         cir.setReturnValue(false);
         cir.cancel();
      }

   }
}
