package org.itsmahir.pagon.api.event.events.network;

import net.minecraft.class_639;
import net.minecraft.class_642;
import org.itsmahir.pagon.api.event.Event;

public class ServerEvent {
   public static class ServerLeft extends Event {
   }

   public static class ServerJoined extends Event {
   }

   public static class AttemptConnect extends Event {
      public class_639 address;
      public class_642 info;

      public AttemptConnect(class_639 address, class_642 info) {
         this.address = address;
         this.info = info;
      }
   }
}
