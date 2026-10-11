package org.itsmahir.pagon.mixin;

import net.minecraft.class_1309;
import net.minecraft.class_1671;
import net.minecraft.class_243;
import net.minecraft.class_310;
import org.itsmahir.pagon.api.management.RotationManager;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;
import org.itsmahir.pagon.impl.features.modules.exploit.RocketExtender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({class_1671.class})
public class MixinFireworkRocketEntity implements IMinecraft {
   @Redirect(
      method = {"tick"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/LivingEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V"
)
   )
   public void setVelocity(class_1309 instance, class_243 vec3d) {
      if (instance == class_310.method_1551().field_1724 && RocketExtender.INSTANCE.isEnabled()) {
         class_243 rotationVector = mc.field_1724.method_5720();
         if ((Boolean)AntiCheat.INSTANCE.strafeFix.getValue() && RotationManager.INSTANCE.getRotation() != null) {
            rotationVector = RotationManager.INSTANCE.getRotationVector();
         }

         double d = (double)1.5F;
         double e = (double)RocketExtender.INSTANCE.getSpeed();
         class_243 vec3d2 = instance.method_18798();
         instance.method_18799(vec3d2.method_1031(rotationVector.field_1352 * e + (rotationVector.field_1352 * d - vec3d2.field_1352) * (double)0.5F, rotationVector.field_1351 * e + (rotationVector.field_1351 * d - vec3d2.field_1351) * (double)0.5F, rotationVector.field_1350 * e + (rotationVector.field_1350 * d - vec3d2.field_1350) * (double)0.5F));
      } else {
         instance.method_18799(vec3d);
      }
   }

   @Redirect(
      method = {"tick"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/LivingEntity;getRotationVector()Lnet/minecraft/util/math/Vec3d;"
)
   )
   public class_243 replaceVelocity(class_1309 instance) {
      return instance == class_310.method_1551().field_1724 && (Boolean)AntiCheat.INSTANCE.strafeFix.getValue() && class_310.method_1551().field_1724 != null && RotationManager.INSTANCE.getRotation() != null ? RotationManager.INSTANCE.getRotationVector() : instance.method_5720();
   }
}
