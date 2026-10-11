package org.itsmahir.pagon.api.event.events.key;

import org.itsmahir.pagon.api.event.Event;

public class KeyboardEvent extends Event {
   private final boolean state;
   private final int key;
   private final int action;

   public KeyboardEvent(boolean state, int key, int action) {
      this.state = state;
      this.key = key;
      this.action = action;
   }

   public boolean isState() {
      return this.state;
   }

   public int getKey() {
      return this.key;
   }

   public int getAction() {
      return this.action;
   }
}
