package org.itsmahir.pagon.mixin;

import net.minecraft.class_1309;
import net.minecraft.class_310;
import net.minecraft.class_4592;
import net.minecraft.class_572;
import net.minecraft.class_630;
import org.itsmahir.pagon.impl.features.modules.movement.FakeFly;
import org.itsmahir.pagon.impl.features.modules.movement.LongJump;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({class_572.class})
public class MixinBipedEntityModel<T extends class_1309> extends class_4592<T> {
   @Redirect(
      method = {"setAngles"},
      at = @At(
   value = "INVOKE",
   target = "net/minecraft/entity/LivingEntity.getFallFlyingTicks ()I"
)
   )
   int setAngles(T livingEntity) {
      return livingEntity != class_310.method_1551().field_1724 || !LongJump.isGrimJumping() && !FakeFly.isFakeFlying() ? livingEntity.method_6003() : 0;
   }

   @Shadow
   public Iterable<class_630> method_22946() {
      return null;
   }

   @Shadow
   public Iterable<class_630> method_22948() {
      return null;
   }

   @Shadow
   public void method_17087(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
   }
}
