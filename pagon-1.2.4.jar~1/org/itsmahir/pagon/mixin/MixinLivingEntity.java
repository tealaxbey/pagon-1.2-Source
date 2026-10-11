package org.itsmahir.pagon.mixin;

import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_2604;
import net.minecraft.class_2848;
import net.minecraft.class_2945;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_2848.class_2849;
import org.itsmahir.pagon.api.event.events.LivingEvent;
import org.itsmahir.pagon.api.event.events.move.FallFlyingEvent;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.management.RotationManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.ducks.ILivingEntity;
import org.itsmahir.pagon.api.utils.players.rotation.Rotation;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.PagonMod;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;
import org.itsmahir.pagon.impl.features.modules.movement.ElytraFly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1309.class})
public abstract class MixinLivingEntity extends class_1297 implements ILivingEntity {
   @Shadow
   public int field_6210;
   @Shadow
   public double field_6224;
   @Shadow
   public double field_6245;
   @Shadow
   public double field_6263;
   @Shadow
   public double field_6284;
   @Shadow
   protected double field_6221;
   @Shadow
   protected double field_45123;
   @Shadow
   protected int field_6265;
   @Unique
   private class_243 pagon_oldServerPos;
   @Unique
   private float pagon_headPitch;
   @Unique
   private float pagon_prevHeadPitch;
   @Unique
   private float pagon_headYaw;
   @Unique
   private float pagon_prevHeadYaw;
   @Unique
   private boolean pagon_inInventory = false;
   @Shadow
   protected class_1799 field_6277;
   @Unique
   private float originalYaw;
   @Unique
   private boolean prevFlying = false;

   public MixinLivingEntity(class_1299<?> type, class_1937 world) {
      super(type, world);
   }

   @Shadow
   public abstract float method_5705(float var1);

   @Shadow
   protected abstract void method_5693(class_2945.class_9222 var1);

   @Inject(
      method = {"jump"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void hookEventJumpPre(CallbackInfo ci) {
      if (this == class_310.method_1551().field_1724) {
         LivingEvent.Jump jumpEvent = new LivingEvent.Jump(this.originalYaw = this.method_36454());
         jumpEvent.post();
         if (jumpEvent.isCancelled()) {
            ci.cancel();
            return;
         }

         this.method_36456(jumpEvent.getYaw());
      }

   }

   @Inject(
      method = {"jump"},
      at = {@At("TAIL")}
   )
   public void hookEventJumpPost(CallbackInfo ci) {
      if (this == class_310.method_1551().field_1724) {
         this.method_36456(this.originalYaw);
      }

   }

   @Inject(
      method = {"consumeItem"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/item/ItemStack;finishUsing(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;)Lnet/minecraft/item/ItemStack;",
   shift = Shift.AFTER
)}
   )
   private void hookConsumeItem(CallbackInfo ci) {
      if (this == IMinecraft.mc.field_1724) {
         LivingEvent.Eat event = new LivingEvent.Eat(this.field_6277);
         event.post();
      }
   }

   @Redirect(
      method = {"travel"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/LivingEntity;isFallFlying()Z"
)
   )
   public boolean redirectFallFlying(class_1309 instance) {
      if (instance.equals(class_310.method_1551().field_1724)) {
         FallFlyingEvent event = new FallFlyingEvent();
         event.post();
         if (event.isCancelled()) {
            return true;
         }
      }

      return instance.method_6128();
   }

   @Redirect(
      method = {"tick"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/LivingEntity;getYaw()F"
),
      slice = @Slice(
   from = @At(
   value = "FIELD",
   target = "Lnet/minecraft/entity/LivingEntity;prevStepBobbingAmount:F"
),
   to = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/LivingEntity;turnHead(FF)F"
)
)
   )
   public float replaceYaw_tick(class_1309 instance) {
      Rotation rotation = RotationManager.INSTANCE.getRotation();
      return this == class_310.method_1551().field_1724 && rotation != null ? rotation.getYaw() : instance.method_36454();
   }

   @Redirect(
      method = {"travel"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/LivingEntity;getPitch()F"
)
   )
   public float replacePitch(class_1309 instance) {
      return this == class_310.method_1551().field_1724 && (Boolean)AntiCheat.INSTANCE.strafeFix.getValue() && class_310.method_1551().field_1724 != null && RotationManager.INSTANCE.getRotation() != null ? RotationManager.INSTANCE.getRotation().getPitch() : instance.method_36455();
   }

   @Redirect(
      method = {"travel"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/LivingEntity;getRotationVector()Lnet/minecraft/util/math/Vec3d;"
)
   )
   public class_243 replaceVelocity(class_1309 instance) {
      return this == class_310.method_1551().field_1724 && (Boolean)AntiCheat.INSTANCE.strafeFix.getValue() && class_310.method_1551().field_1724 != null && RotationManager.INSTANCE.getRotation() != null ? RotationManager.INSTANCE.getRotationVector() : instance.method_5720();
   }

   @Redirect(
      method = {"turnHead"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/LivingEntity;getYaw()F"
)
   )
   public float replaceYaw_turnHead(class_1309 instance) {
      Rotation rotation = RotationManager.INSTANCE.getRotation();
      return this == class_310.method_1551().field_1724 && rotation != null ? rotation.getYaw() : instance.method_36454();
   }

   @Inject(
      method = {"tickMovement"},
      at = {@At(
   value = "FIELD",
   target = "Lnet/minecraft/entity/LivingEntity;headTrackingIncrements:I",
   ordinal = 2
)}
   )
   public void respectServerHeadYaw(CallbackInfo ci) {
      this.pagon_headYaw += (float)class_3532.method_15338(this.field_45123 - (double)this.pagon_headYaw) / (float)this.field_6265;
   }

   @Inject(
      method = {"<init>"},
      at = {@At(
   value = "FIELD",
   target = "Lnet/minecraft/entity/LivingEntity;headYaw:F"
)}
   )
   public void setHeadRotation(class_1299<?> entityType, class_1937 world, CallbackInfo ci) {
      this.pagon_headYaw = this.method_36454();
      this.pagon_headPitch = this.method_36455();
   }

   @Inject(
      method = {"isFallFlying"},
      at = {@At("TAIL")},
      cancellable = true
   )
   public void tryReFlyOnLand(CallbackInfoReturnable<Boolean> cir) {
      if (!NullUtils.nullCheck()) {
         boolean flying = (Boolean)cir.getReturnValue();
         boolean stoppedFlying = this.prevFlying && !flying;
         if (ElytraFly.INSTANCE.isEnabled() && ((String)ElytraFly.INSTANCE.mode.getValue()).equals("Bounce") && stoppedFlying && !PagonMod.isBaritonePaused()) {
            IMinecraft.mc.field_1724.method_23669();
            PacketManager.INSTANCE.sendPacket(new class_2848(IMinecraft.mc.field_1724, class_2849.field_12982));
            cir.setReturnValue(true);
         }

         this.prevFlying = flying;
      }
   }

   @Inject(
      method = {"onSpawnPacket"},
      at = {@At(
   value = "FIELD",
   target = "Lnet/minecraft/entity/LivingEntity;headYaw:F",
   ordinal = 1
)}
   )
   public void setHeadRotation(class_2604 packet, CallbackInfo ci) {
      this.pagon_headYaw = packet.method_43233();
      this.pagon_prevHeadYaw = this.pagon_headYaw;
      this.pagon_headPitch = packet.method_11171();
      this.pagon_prevHeadPitch = this.pagon_headPitch;
   }

   @Inject(
      method = {"baseTick"},
      at = {@At(
   value = "FIELD",
   target = "Lnet/minecraft/entity/LivingEntity;prevHeadYaw:F"
)}
   )
   public void updateHeadRotation(CallbackInfo ci) {
      this.pagon_prevHeadYaw = this.pagon_headYaw;
      this.pagon_prevHeadPitch = this.pagon_headPitch;
   }

   @Inject(
      method = {"updateTrackedPositionAndAngles"},
      at = {@At("HEAD")}
   )
   public void saveOldServerPos(double x, double y, double z, float yaw, float pitch, int interpolationSteps, CallbackInfo ci) {
      this.pagon_oldServerPos = new class_243(this.field_6224, this.field_6245, this.field_6263);
   }

   public class_243 pagon_prevServerPos() {
      return this.pagon_oldServerPos;
   }

   public float pagon_getHeadPitch() {
      return this.pagon_headPitch;
   }

   public void pagon_setHeadPitch(float headPitch) {
      this.pagon_headPitch = headPitch;
   }

   public float pagon_getPrevHeadPitch() {
      return this.pagon_prevHeadPitch;
   }

   public void pagon_setPrevHeadPitch(float prevHeadPitch) {
      this.pagon_prevHeadPitch = prevHeadPitch;
   }

   public float pagon_getHeadYaw() {
      return this.pagon_headYaw;
   }

   public void pagon_setHeadYaw(float headYaw) {
      this.pagon_headYaw = headYaw;
   }

   public float pagon_getPrevHeadYaw() {
      return this.pagon_prevHeadYaw;
   }

   public void pagon_setPrevHeadYaw(float prevHeadYaw) {
      this.pagon_prevHeadYaw = prevHeadYaw;
   }

   public boolean pagon_isInInventory() {
      return this.pagon_inInventory;
   }

   public void pagon_setInInventory(boolean inInventory) {
      this.pagon_inInventory = inInventory;
   }
}
