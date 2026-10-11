package org.itsmahir.pagon.mixin;

import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2560;
import net.minecraft.class_2680;
import net.minecraft.class_2846;
import net.minecraft.class_310;
import net.minecraft.class_2846.class_2847;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.impl.features.modules.movement.NoSlow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_2560.class})
public class MixinCobwebBlock {
   @Inject(
      method = {"onEntityCollision"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void onEntityCollisionHook(class_2680 state, class_1937 world, class_2338 pos, class_1297 entity, CallbackInfo ci) {
      if (class_310.method_1551().field_1724 != null && state.method_26204().equals(class_2246.field_10343) && entity.method_5829().method_994(new class_238(pos)) && NoSlow.INSTANCE.isEnabled() && (Boolean)NoSlow.INSTANCE.webs.getValue() && entity.equals(class_310.method_1551().field_1724)) {
         ci.cancel();
         if (((String)NoSlow.INSTANCE.mode.getValue()).equals("Meow") || ((String)NoSlow.INSTANCE.mode.getValue()).equals("Grim")) {
            PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12973, pos, class_2350.field_11033));
         }
      }

   }
}
