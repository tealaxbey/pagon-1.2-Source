package org.itsmahir.pagon.api.event.events.key;

import com.google.common.base.Preconditions;
import org.itsmahir.pagon.api.event.Event;

public class MouseEvent extends Event {
   int button;
   Type type;

   public MouseEvent(int button, Type type) {
      this.button = button;
      this.type = type;
   }

   public int getButton() {
      return this.button;
   }

   public Type getType() {
      return this.type;
   }

   public static enum Type {
      LIFT,
      CLICK;

      public static Type of(int id) {
         Preconditions.checkElementIndex(id, 2);
         return values()[id];
      }

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{LIFT, CLICK};
      }
   }
}
