package org.itsmahir.pagon.api.event.events.key;

import org.itsmahir.pagon.api.event.Event;

public class CharTypeEvent extends Event {
   private final char character;

   public CharTypeEvent(char character) {
      this.character = character;
   }

   public char getCharacter() {
      return this.character;
   }
}
