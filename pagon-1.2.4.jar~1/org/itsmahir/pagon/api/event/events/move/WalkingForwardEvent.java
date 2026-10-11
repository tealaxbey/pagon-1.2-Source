package org.itsmahir.pagon.api.event.events.move;

import org.itsmahir.pagon.api.event.Event;

public class WalkingForwardEvent extends Event {
   boolean walksForward;

   public WalkingForwardEvent(boolean walksForward) {
      this.walksForward = walksForward;
   }

   public boolean isWalksForward() {
      return this.walksForward;
   }

   public void setWalksForward(boolean walksForward) {
      this.walksForward = walksForward;
   }
}
