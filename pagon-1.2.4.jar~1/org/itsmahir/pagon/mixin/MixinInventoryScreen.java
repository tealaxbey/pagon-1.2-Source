package org.itsmahir.pagon.mixin;

import net.minecraft.class_1309;
import net.minecraft.class_332;
import net.minecraft.class_490;
import org.itsmahir.pagon.api.management.RotationManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_490.class})
public class MixinInventoryScreen {
   @Inject(
      method = {"drawEntity(Lnet/minecraft/client/gui/DrawContext;IIIIIFFFLnet/minecraft/entity/LivingEntity;)V"},
      at = {@At("HEAD")}
   )
   private static void clearHeadRotation(class_332 context, int x1, int y1, int x2, int y2, int size, float f, float mouseX, float mouseY, class_1309 entity, CallbackInfo ci) {
      RotationManager.INSTANCE.FROM_INV = true;
   }

   @Inject(
      method = {"drawEntity(Lnet/minecraft/client/gui/DrawContext;IIIIIFFFLnet/minecraft/entity/LivingEntity;)V"},
      at = {@At("TAIL")}
   )
   private static void resetHeadRotation(class_332 context, int x1, int y1, int x2, int y2, int size, float f, float mouseX, float mouseY, class_1309 entity, CallbackInfo ci) {
      RotationManager.INSTANCE.FROM_INV = false;
   }
}
