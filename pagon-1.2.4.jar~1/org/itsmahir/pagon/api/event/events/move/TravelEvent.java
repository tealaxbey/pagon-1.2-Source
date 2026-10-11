package org.itsmahir.pagon.api.event.events.move;

import net.minecraft.class_243;
import org.itsmahir.pagon.api.event.Event;

public class TravelEvent {
   public static class Pre extends Event {
      private class_243 input;

      public Pre(class_243 input) {
         this.input = input;
      }
   }

   public static class Post extends Event {
      private class_243 input;

      public Post(class_243 input) {
         this.input = input;
      }

      public class_243 getInput() {
         return this.input;
      }
   }
}
