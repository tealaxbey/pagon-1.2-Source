package org.itsmahir.pagon.api.event.events.render;

import org.itsmahir.pagon.api.event.Event;

public class LightmapGammaEvent extends Event {
   private int gamma;

   public LightmapGammaEvent(int gamma) {
      this.gamma = gamma;
   }

   public int getGamma() {
      return this.gamma;
   }

   public void setGamma(int gamma) {
      this.gamma = gamma;
   }
}
