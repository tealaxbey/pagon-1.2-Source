package org.itsmahir.pagon.mixin;

import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_4050;
import net.minecraft.class_5134;
import org.itsmahir.pagon.api.event.events.LivingEvent;
import org.itsmahir.pagon.api.event.events.move.TravelEvent;
import org.itsmahir.pagon.api.event.events.player.ReachEvent;
import org.itsmahir.pagon.api.management.RotationManager;
import org.itsmahir.pagon.api.utils.ducks.ILivingEntity;
import org.itsmahir.pagon.api.utils.players.rotation.Rotation;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.features.modules.movement.BoatFly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1657.class})
public abstract class MixinPlayerEntity extends class_1309 implements IMinecraft {
   @Shadow
   public abstract boolean method_7337();

   @Shadow
   public abstract double method_55755();

   @Shadow
   protected abstract boolean method_52558(class_4050 var1);

   protected MixinPlayerEntity(class_1299<? extends class_1309> entityType, class_1937 world) {
      super(entityType, world);
   }

   @Inject(
      method = {"tickNewAi"},
      at = {@At(
   value = "FIELD",
   target = "Lnet/minecraft/entity/player/PlayerEntity;headYaw:F"
)}
   )
   public void updateHeadRotation(CallbackInfo ci) {
      Rotation rotation = RotationManager.INSTANCE.getRotation();
      float yaw = this.method_36454();
      float pitch = this.method_36455();
      if (rotation != null && this == class_310.method_1551().field_1724 && rotation != null) {
         yaw = rotation.getYaw();
         pitch = rotation.getPitch();
      }

      ((ILivingEntity)this).pagon_setHeadYaw(yaw);
      ((ILivingEntity)this).pagon_setHeadPitch(pitch);
   }

   @Inject(
      method = {"getBlockInteractionRange"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void doReachDistance(CallbackInfoReturnable<Double> cir) {
      ReachEvent reachEvent = new ReachEvent((float)this.method_45325(class_5134.field_47759));
      reachEvent.post();
      if (reachEvent.isCancelled()) {
         cir.cancel();
         cir.setReturnValue((double)reachEvent.getReach());
      }

   }

   @Inject(
      method = {"getEntityInteractionRange"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void doEntityReachDistance(CallbackInfoReturnable<Double> cir) {
      ReachEvent reachEvent = new ReachEvent((float)this.method_45325(class_5134.field_47759));
      reachEvent.post();
      if (reachEvent.isCancelled()) {
         cir.cancel();
         cir.setReturnValue((double)reachEvent.getReach());
      }

   }

   @Inject(
      method = {"travel"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookTravelHead(class_243 movementInput, CallbackInfo ci) {
      TravelEvent.Pre event = new TravelEvent.Pre(movementInput);
      event.post();
      if (event.isCancelled()) {
         ci.cancel();
      }

   }

   @Redirect(
      method = {"updatePose"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/player/PlayerEntity;canChangeIntoPose(Lnet/minecraft/entity/EntityPose;)Z",
   ordinal = 0
)
   )
   private boolean canEnterPose1(class_1657 instance, class_4050 pose) {
      return this.method_52558(pose);
   }

   @Redirect(
      method = {"updatePose"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/player/PlayerEntity;canChangeIntoPose(Lnet/minecraft/entity/EntityPose;)Z",
   ordinal = 1
)
   )
   private boolean canEnterPose2(class_1657 instance, class_4050 pose) {
      return this.method_52558(pose);
   }

   @Inject(
      method = {"travel"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void hookTravelTail(class_243 movementInput, CallbackInfo ci) {
      TravelEvent.Post event = new TravelEvent.Post(movementInput);
      event.post();
   }

   @Inject(
      method = {"attack"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onAttack(class_1297 target, CallbackInfo ci) {
      LivingEvent.Attack event = new LivingEvent.Attack(target);
      event.post();
      if (event.isCancelled()) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"shouldDismount"},
      at = {@At("HEAD")},
      cancellable = true
   )
   protected void shouldDismountHook(CallbackInfoReturnable<Boolean> cir) {
      if (BoatFly.INSTANCE.isEnabled()) {
         cir.setReturnValue(false);
      }

   }
}
