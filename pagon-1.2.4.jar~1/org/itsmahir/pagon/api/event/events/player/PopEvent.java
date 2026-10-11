package org.itsmahir.pagon.api.event.events.player;

import net.minecraft.class_1297;
import org.itsmahir.pagon.api.event.Event;

public class PopEvent {
   public static class TotemPopEvent extends Event {
      class_1297 entity;
      int totalPops;

      public TotemPopEvent(class_1297 entity, int totalPops) {
         this.entity = entity;
         this.totalPops = totalPops;
      }

      public class_1297 getEntity() {
         return this.entity;
      }

      public int getTotalPops() {
         return this.totalPops;
      }
   }

   public static class DeathPopEvent extends Event {
      class_1297 entity;
      int totalPops;

      public DeathPopEvent(class_1297 entity, int totalPops) {
         this.entity = entity;
         this.totalPops = totalPops;
      }

      public class_1297 getEntity() {
         return this.entity;
      }

      public int getTotalPops() {
         return this.totalPops;
      }
   }
}
