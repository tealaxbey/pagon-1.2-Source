package org.itsmahir.pagon.api.event.events.key;

import net.minecraft.class_744;
import org.itsmahir.pagon.api.event.Event;

public class InputEvent extends Event {
   public class_744 input;

   public InputEvent(class_744 input) {
      this.input = input;
   }
}
