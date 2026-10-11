package org.itsmahir.pagon.mixin;

import net.minecraft.class_702;
import net.minecraft.class_703;
import org.itsmahir.pagon.api.event.events.render.ParticleEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_702.class})
public class MixinParticleManager {
   @Inject(
      at = {@At("HEAD")},
      method = {"addParticle(Lnet/minecraft/client/particle/Particle;)V"},
      cancellable = true
   )
   public void addParticleHook(class_703 particle, CallbackInfo ci) {
      ParticleEvent event = new ParticleEvent(particle);
      event.post();
      if (event.isCancelled()) {
         ci.cancel();
      }

   }
}
