package org.itsmahir.pagon.mixin;

import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1271;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1934;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_2886;
import net.minecraft.class_636;
import net.minecraft.class_638;
import net.minecraft.class_7204;
import org.apache.commons.lang3.mutable.MutableObject;
import org.itsmahir.pagon.api.event.events.LivingEvent;
import org.itsmahir.pagon.api.management.RotationManager;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_636.class})
public abstract class MixinClientPlayerInteractionManager implements IMinecraft {
   @Shadow
   private class_1934 field_3719;

   @Shadow
   protected abstract void method_2911();

   @Shadow
   protected abstract void method_41931(class_638 var1, class_7204 var2);

   @Inject(
      method = {"attackBlock"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookAttackBlock(class_2338 pos, class_2350 direction, CallbackInfoReturnable<Boolean> ci) {
      class_2680 state = mc.field_1687.method_8320(pos);
      LivingEvent.AttackBlock event = new LivingEvent.AttackBlock(pos, state, direction);
      event.post();
      if (event.isCancelled()) {
         ci.cancel();
         ci.setReturnValue(false);
      }

   }

   @Inject(
      method = {"interactItem"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void hookInteractItem(class_1657 player, class_1268 hand, CallbackInfoReturnable<class_1269> cir) {
      cir.cancel();
      if (this.field_3719 == class_1934.field_9219) {
         cir.setReturnValue(class_1269.field_5811);
      }

      this.method_2911();
      float yaw = RotationManager.INSTANCE.isRotating() ? RotationManager.INSTANCE.getRotationYaw() : mc.field_1724.method_36454();
      float pitch = RotationManager.INSTANCE.isRotating() ? RotationManager.INSTANCE.getRotationPitch() : mc.field_1724.method_36455();
      MutableObject mutableObject = new MutableObject();
      this.method_41931(mc.field_1687, (sequence) -> {
         class_2886 playerInteractItemC2SPacket = new class_2886(hand, sequence, yaw, pitch);
         class_1799 itemStack = player.method_5998(hand);
         if (player.method_7357().method_7904(itemStack.method_7909())) {
            mutableObject.setValue(class_1269.field_5811);
            return playerInteractItemC2SPacket;
         } else {
            class_1271<class_1799> typedActionResult = itemStack.method_7913(mc.field_1687, player, hand);
            class_1799 itemStack2 = (class_1799)typedActionResult.method_5466();
            if (itemStack2 != itemStack) {
               player.method_6122(hand, itemStack2);
            }

            mutableObject.setValue(typedActionResult.method_5467());
            return playerInteractItemC2SPacket;
         }
      });
      cir.setReturnValue((class_1269)mutableObject.getValue());
   }

   @Inject(
      method = {"breakBlock"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookBreakBlock(class_2338 pos, CallbackInfoReturnable<Boolean> cir) {
      LivingEvent.BreakBlock event = new LivingEvent.BreakBlock(pos);
      event.post();
      if (event.isCancelled()) {
         cir.setReturnValue(false);
         cir.cancel();
      }

   }
}
