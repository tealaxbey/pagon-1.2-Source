package org.itsmahir.pagon.api.event.events.world;

import net.minecraft.class_1297;
import org.itsmahir.pagon.api.event.Event;

public class EntityEvent {
   public static class Add extends Event {
      class_1297 entity;

      public Add(class_1297 entity) {
         this.entity = entity;
      }

      public class_1297 getEntity() {
         return this.entity;
      }

      public void setEntity(class_1297 entity) {
         this.entity = entity;
      }
   }

   public static class Remove extends Event {
      class_1297 entity;
      class_1297.class_5529 reason;

      public Remove(class_1297 entity, class_1297.class_5529 reason) {
         this.entity = entity;
         this.reason = reason;
      }

      public class_1297 getEntity() {
         return this.entity;
      }

      public class_1297.class_5529 getReason() {
         return this.reason;
      }

      public void setEntity(class_1297 entity) {
         this.entity = entity;
      }

      public void setReason(class_1297.class_5529 reason) {
         this.reason = reason;
      }
   }
}
