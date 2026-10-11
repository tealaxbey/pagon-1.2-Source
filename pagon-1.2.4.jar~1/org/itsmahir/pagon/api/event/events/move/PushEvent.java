package org.itsmahir.pagon.api.event.events.move;

import net.minecraft.class_1297;
import org.itsmahir.pagon.api.event.Event;

public class PushEvent {
   public static class Entities extends Event {
      private final class_1297 pushed;
      private final class_1297 pusher;

      public Entities(class_1297 pushed, class_1297 pusher) {
         this.pushed = pushed;
         this.pusher = pusher;
      }

      public class_1297 getPushed() {
         return this.pushed;
      }

      public class_1297 getPusher() {
         return this.pusher;
      }
   }

   public static class Blocks extends Event {
   }
}
