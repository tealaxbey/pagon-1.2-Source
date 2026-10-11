package org.itsmahir.pagon.mixin;

import net.minecraft.class_1297;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_4050;
import net.minecraft.class_5712;
import net.minecraft.class_6880;
import org.itsmahir.pagon.api.event.events.LivingEvent;
import org.itsmahir.pagon.api.event.events.move.LookEvent;
import org.itsmahir.pagon.api.event.events.move.PushEvent;
import org.itsmahir.pagon.api.event.events.player.TeamColorEvent;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.features.modules.movement.ElytraFly;
import org.itsmahir.pagon.impl.features.modules.movement.FakeFly;
import org.itsmahir.pagon.impl.features.modules.movement.LongJump;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1297.class})
public abstract class MixinEntity implements IMinecraft {
   @Shadow
   public boolean field_6007;

   @Shadow
   private static class_243 method_18795(class_243 movementInput, float speed, float yaw) {
      return null;
   }

   @Shadow
   public abstract class_238 method_5829();

   @Shadow
   public abstract class_243 method_18798();

   @Shadow
   public abstract void method_18799(class_243 var1);

   @Shadow
   public abstract boolean method_5624();

   @Shadow
   public abstract void method_5724(float var1, class_243 var2);

   @Shadow
   public abstract void method_32876(class_6880<class_5712> var1);

   @Shadow
   public abstract boolean method_5715();

   @Inject(
      method = {"pushAwayFrom"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookPushAwayFrom(class_1297 entity, CallbackInfo ci) {
      PushEvent.Entities event = new PushEvent.Entities((class_1297)this, entity);
      event.post();
      if (event.isCancelled()) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"updateVelocity"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookUpdateVelocity(float speed, class_243 movementInput, CallbackInfo ci) {
      if (this == mc.field_1724) {
         LookEvent.LookVelocityEvent updateVelocityEvent = new LookEvent.LookVelocityEvent(movementInput, speed, mc.field_1724.method_36454(), method_18795(movementInput, speed, mc.field_1724.method_36454()));
         updateVelocityEvent.post();
         if (updateVelocityEvent.isCancelled()) {
            ci.cancel();
            mc.field_1724.method_18799(mc.field_1724.method_18798().method_1019(updateVelocityEvent.getVelocity()));
         }
      }

   }

   @Inject(
      method = {"getPose"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void getPoseHook(CallbackInfoReturnable<class_4050> info) {
      if (this == mc.field_1724 && (LongJump.isGrimJumping() || FakeFly.isFakeFlying() || ElytraFly.isPacketFlying())) {
         info.setReturnValue(class_4050.field_18076);
      }

   }

   @Inject(
      method = {"isCrawling"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void isCrawlingHook(CallbackInfoReturnable<Boolean> info) {
   }

   @Inject(
      method = {"getTeamColorValue"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookGetTeamColorValue(CallbackInfoReturnable<Integer> cir) {
      TeamColorEvent event = new TeamColorEvent((class_1297)this);
      event.post();
      if (event.isCancelled()) {
         cir.setReturnValue(event.getColor());
         cir.cancel();
      }

   }

   @Inject(
      method = {"slowMovement"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookSlowMovement(class_2680 state, class_243 multiplier, CallbackInfo ci) {
      if (this == mc.field_1724) {
         LivingEvent.BlockSlowdown event = new LivingEvent.BlockSlowdown(state);
         event.post();
         if (event.isCancelled()) {
            ci.cancel();
         }

      }
   }
}
