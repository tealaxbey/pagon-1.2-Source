package org.itsmahir.pagon.api.event.events;

import org.itsmahir.pagon.api.event.Event;

public class TickEvent {
   public static class ClientTickEvent extends Event {
   }

   public static class AfterClientTickEvent extends Event {
   }

   public static class InputTick extends Event {
   }

   public static class GameRenderTick extends Event {
   }

   public static class VanillaTick extends Event {
   }

   public static class MovementTickEvent extends Event {
      public static class Pre extends Event {
      }

      public static class Post extends Event {
      }
   }

   public static class PlayerTickEvent extends Event {
      public static class Pre extends Event {
      }

      public static class Post extends Event {
      }
   }
}
