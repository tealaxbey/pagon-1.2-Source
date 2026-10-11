package org.itsmahir.pagon.api.event.events.misc;

import org.itsmahir.pagon.api.event.Event;

public class ClientChatEvent extends Event {
   public String message;

   public String getMessage() {
      return this.message;
   }

   public ClientChatEvent(String message) {
      this.message = message;
   }
}
