package org.itsmahir.pagon.mixin;

import net.minecraft.class_1297;
import net.minecraft.class_1922;
import net.minecraft.class_4184;
import org.itsmahir.pagon.impl.features.modules.render.Freecam;
import org.itsmahir.pagon.impl.features.modules.render.ViewClip;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin({class_4184.class})
public abstract class MixinCamera {
   @Shadow
   private boolean field_18719;
   @Unique
   private float tickDelta;

   @Shadow
   protected abstract float method_19318(float var1);

   @ModifyArgs(
      method = {"update"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/Camera;moveBy(FFF)V",
   ordinal = 0
)
   )
   private void modifyCameraDistance(Args args) {
      if (ViewClip.INSTANCE.isEnabled()) {
         args.set(0, -this.method_19318(((Number)ViewClip.INSTANCE.distance.getValue()).floatValue()));
      }

   }

   @Inject(
      method = {"clipToSpace"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onClipToSpace(float f, CallbackInfoReturnable<Float> cir) {
      if (ViewClip.INSTANCE.isEnabled()) {
         cir.setReturnValue(((Number)ViewClip.INSTANCE.distance.getValue()).floatValue());
      }

   }

   @Inject(
      method = {"update"},
      at = {@At("TAIL")}
   )
   private void updateHook(class_1922 area, class_1297 focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci) {
      if (Freecam.INSTANCE.isEnabled()) {
         this.field_18719 = true;
      }

   }

   @Inject(
      method = {"update"},
      at = {@At("HEAD")}
   )
   private void onUpdateHead(class_1922 area, class_1297 focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo info) {
      this.tickDelta = tickDelta;
   }

   @Inject(
      method = {"update"},
      at = {@At("TAIL")}
   )
   private void onUpdateTail(class_1922 area, class_1297 focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo info) {
      if (Freecam.INSTANCE.isEnabled()) {
         this.field_18719 = true;
      }

   }

   @ModifyArgs(
      method = {"update"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/Camera;setPos(DDD)V"
)
   )
   private void onUpdateSetPosArgs(Args args) {
      Freecam freecam = Freecam.INSTANCE;
      if (freecam.isEnabled()) {
         args.set(0, freecam.getX(this.tickDelta));
         args.set(1, freecam.getY(this.tickDelta));
         args.set(2, freecam.getZ(this.tickDelta));
      }

   }

   @ModifyArgs(
      method = {"update"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/Camera;setRotation(FF)V"
)
   )
   private void onUpdateSetRotationArgs(Args args) {
      Freecam freecam = Freecam.INSTANCE;
      if (freecam.isEnabled()) {
         args.set(0, (float)freecam.getYaw(this.tickDelta));
         args.set(1, (float)freecam.getPitch(this.tickDelta));
      }

   }
}
