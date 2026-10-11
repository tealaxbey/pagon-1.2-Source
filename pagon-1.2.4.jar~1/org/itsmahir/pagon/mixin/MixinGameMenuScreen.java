package org.itsmahir.pagon.mixin;

import java.time.Instant;
import java.util.BitSet;
import net.minecraft.class_2797;
import net.minecraft.class_433;
import net.minecraft.class_7469;
import net.minecraft.class_7635;
import net.minecraft.class_3515.class_7426;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.features.modules.exploit.IllegalDisconnect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_433.class})
public class MixinGameMenuScreen implements IMinecraft {
   @Inject(
      method = {"disconnect"},
      at = {@At(
   value = "HEAD",
   target = "Lnet/minecraft/entity/LivingEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V"
)}
   )
   public void onDisconnect(CallbackInfo ci) {
      if (IllegalDisconnect.INSTANCE.isEnabled() && !mc.method_1542()) {
         PacketManager.INSTANCE.sendQuietPacket(new class_2797("§", Instant.now(), class_7426.method_43531(), (class_7469)null, new class_7635.class_7636(1, new BitSet(2))));
      }

   }
}
