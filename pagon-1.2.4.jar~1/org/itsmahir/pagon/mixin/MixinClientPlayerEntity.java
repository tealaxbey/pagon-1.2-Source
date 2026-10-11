package org.itsmahir.pagon.mixin;

import net.minecraft.class_1313;
import net.minecraft.class_243;
import net.minecraft.class_2828;
import net.minecraft.class_2848;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_634;
import net.minecraft.class_742;
import net.minecraft.class_744;
import net.minecraft.class_746;
import net.minecraft.class_2848.class_2849;
import org.itsmahir.pagon.api.event.events.LivingEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.move.MoveEvent;
import org.itsmahir.pagon.api.event.events.move.MovementPacketsEvent;
import org.itsmahir.pagon.api.event.events.move.PushEvent;
import org.itsmahir.pagon.api.management.RotationManager;
import org.itsmahir.pagon.api.utils.ducks.IClientPlayerEntity;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;
import org.itsmahir.pagon.impl.features.modules.exploit.PortalGui;
import org.itsmahir.pagon.impl.features.modules.movement.NoSlow;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_746.class})
public abstract class MixinClientPlayerEntity extends class_742 implements IMinecraft, IClientPlayerEntity {
   @Shadow
   @Final
   public class_634 field_3944;
   @Shadow
   public double field_3926;
   @Shadow
   public double field_3940;
   @Shadow
   public double field_3924;
   @Shadow
   public class_744 field_3913;
   @Shadow
   @Final
   protected class_310 field_3937;
   @Shadow
   private boolean field_3936;
   @Shadow
   private float field_3941;
   @Shadow
   private float field_3925;
   @Shadow
   private boolean field_3920;
   @Shadow
   private int field_3923;
   @Shadow
   private boolean field_3927;
   @Unique
   private boolean ticking;

   public MixinClientPlayerEntity() {
      super(class_310.method_1551().field_1687, class_310.method_1551().field_1724.method_7334());
   }

   @Shadow
   protected abstract void method_46742();

   @Shadow
   public abstract boolean method_5715();

   @Shadow
   protected abstract boolean method_3134();

   @Shadow
   protected abstract void method_3148(float var1, float var2);

   @Shadow
   public abstract void method_5773();

   public void doTick() {
      super.method_5773();
   }

   public void doSendMovementPackets() {
      this.method_3136();
   }

   @Shadow
   protected abstract void method_3136();

   @Inject(
      method = {"sendMovementPackets"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookSendMovementPackets(CallbackInfo ci) {
      if (((String)AntiCheat.INSTANCE.acMode.getValue()).equals("Vanilla")) {
         TickEvent.PlayerTickEvent.Pre event = new TickEvent.PlayerTickEvent.Pre();
         event.post();
         RotationManager.INSTANCE.onUpdate();
      }

      (new TickEvent.MovementTickEvent.Pre()).post();
      MovementPacketsEvent movementPacketsEvent = new MovementPacketsEvent(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321(), mc.field_1724.method_36454(), mc.field_1724.method_36455(), mc.field_1724.method_24828());
      movementPacketsEvent.post();
      double x = movementPacketsEvent.getX();
      double y = movementPacketsEvent.getY();
      double z = movementPacketsEvent.getZ();
      float yaw = movementPacketsEvent.getYaw();
      float pitch = movementPacketsEvent.getPitch();
      boolean ground = movementPacketsEvent.isOnGround();
      if (movementPacketsEvent.isCancelled()) {
         ci.cancel();
         this.method_46742();
         boolean bl = this.method_5715();
         if (bl != this.field_3936) {
            class_2848.class_2849 mode = bl ? class_2849.field_12979 : class_2849.field_12984;
            this.field_3944.method_52787(new class_2848(this, mode));
            this.field_3936 = bl;
         }

         if (this.method_3134()) {
            double d = x - this.field_3926;
            double e = y - this.field_3940;
            double f = z - this.field_3924;
            double g = (double)(yaw - this.field_3941);
            double h = (double)(pitch - this.field_3925);
            ++this.field_3923;
            boolean bl2 = class_3532.method_41190(d, e, f) > class_3532.method_33723(2.0E-4) || this.field_3923 >= 20;
            boolean bl3 = g != (double)0.0F || h != (double)0.0F;
            if (this.method_5765()) {
               class_243 vec3d = this.method_18798();
               this.field_3944.method_52787(new class_2828.class_2830(vec3d.field_1352, (double)-999.0F, vec3d.field_1350, this.method_36454(), this.method_36455(), ground));
               bl2 = false;
            } else if (bl2 && bl3) {
               this.field_3944.method_52787(new class_2828.class_2830(x, y, z, yaw, pitch, ground));
            } else if (bl2) {
               this.field_3944.method_52787(new class_2828.class_2829(x, y, z, ground));
            } else if (bl3) {
               this.field_3944.method_52787(new class_2828.class_2831(yaw, pitch, ground));
            } else if (this.field_3920 != this.method_24828()) {
               this.field_3944.method_52787(new class_2828.class_5911(ground));
            }

            if (bl2) {
               this.field_3926 = x;
               this.field_3940 = y;
               this.field_3924 = z;
               this.field_3923 = 0;
            }

            if (bl3) {
               this.field_3941 = yaw;
               this.field_3925 = pitch;
            }

            this.field_3920 = ground;
            this.field_3927 = (Boolean)this.field_3937.field_1690.method_42423().method_41753();
         }

         (new TickEvent.MovementTickEvent.Post()).post();
         if (((String)AntiCheat.INSTANCE.acMode.getValue()).equals("Vanilla")) {
            TickEvent.PlayerTickEvent.Post event = new TickEvent.PlayerTickEvent.Post();
            event.post();
         }
      }

   }

   @Inject(
      method = {"sendMovementPackets"},
      at = {@At("RETURN")}
   )
   private void sendMovementPackets(CallbackInfo ci) {
      (new TickEvent.MovementTickEvent.Post()).post();
      if (((String)AntiCheat.INSTANCE.acMode.getValue()).equals("Vanilla")) {
         TickEvent.PlayerTickEvent.Post event = new TickEvent.PlayerTickEvent.Post();
         event.post();
      }

   }

   @Inject(
      method = {"move"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookMove(class_1313 movementType, class_243 movement, CallbackInfo ci) {
      MoveEvent event = new MoveEvent(movementType, movement);
      event.post();
      if (!event.getMovement().equals(movement)) {
         ci.cancel();
         double d = this.method_23317();
         double e = this.method_23321();
         super.method_5784(movementType, event.getMovement());
         this.method_3148((float)(this.method_23317() - d), (float)(this.method_23321() - e));
      }

   }

   @Inject(
      method = {"tick"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;tick()V",
   shift = Shift.BEFORE,
   ordinal = 0
)}
   )
   private void hookTickPre(CallbackInfo ci) {
      if (((String)AntiCheat.INSTANCE.acMode.getValue()).equals("Strong")) {
         TickEvent.PlayerTickEvent.Pre event = new TickEvent.PlayerTickEvent.Pre();
         event.post();
         RotationManager.INSTANCE.onUpdate();
      }

   }

   @Inject(
      method = {"tick"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;tick()V",
   shift = Shift.AFTER,
   ordinal = 0
)}
   )
   private void hookTickPost(CallbackInfo ci) {
      if (((String)AntiCheat.INSTANCE.acMode.getValue()).equals("Strong")) {
         TickEvent.PlayerTickEvent.Post event = new TickEvent.PlayerTickEvent.Post();
         event.post();
      }

   }

   @Inject(
      method = {"pushOutOfBlocks"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onPushOutOfBlocks(double x, double z, CallbackInfo ci) {
      PushEvent.Blocks event = new PushEvent.Blocks();
      event.post();
      if (event.isCancelled()) {
         ci.cancel();
      }

   }

   @Redirect(
      method = {"tickMovement"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/ClientPlayerEntity;setSprinting(Z)V",
   ordinal = 3
)
   )
   private void hookSetSprinting(class_746 instance, boolean b) {
      LivingEvent.SetSprinting sprintEvent = new LivingEvent.SetSprinting();
      sprintEvent.post();
      if (sprintEvent.isCancelled()) {
         instance.method_5728(true);
      } else {
         instance.method_5728(b);
      }

   }

   @Inject(
      method = {"isInSneakingPose"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void isSneaking(CallbackInfoReturnable<Boolean> cir) {
   }

   @Inject(
      method = {"shouldSlowDown"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void shouldSlowDownHook(CallbackInfoReturnable<Boolean> cir) {
      if (NoSlow.INSTANCE.isEnabled()) {
         if (this.method_20448()) {
            if ((Boolean)NoSlow.INSTANCE.crawling.getValue()) {
               cir.setReturnValue(false);
            }
         } else if ((Boolean)NoSlow.INSTANCE.sneak.getValue()) {
            cir.setReturnValue(false);
         }
      }

   }

   @Redirect(
      method = {"tickMovement"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"
)
   )
   private boolean redirectUsingItem(class_746 player) {
      return NoSlow.INSTANCE.canNoSlow() ? false : player.method_6115();
   }

   @Inject(
      method = {"tickNausea"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void updateNauseaHook(CallbackInfo ci) {
      if (PortalGui.INSTANCE.isEnabled()) {
         ci.cancel();
      }

   }
}
