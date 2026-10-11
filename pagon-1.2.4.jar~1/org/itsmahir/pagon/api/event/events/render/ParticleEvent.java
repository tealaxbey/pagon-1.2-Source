package org.itsmahir.pagon.api.event.events.render;

import net.minecraft.class_703;
import org.itsmahir.pagon.api.event.Event;

public class ParticleEvent extends Event {
   private final class_703 particle;

   public ParticleEvent(class_703 particle) {
      this.particle = particle;
   }

   public class_703 getParticle() {
      return this.particle;
   }
}
