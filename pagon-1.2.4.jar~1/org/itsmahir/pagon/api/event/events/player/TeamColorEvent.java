package org.itsmahir.pagon.api.event.events.player;

import net.minecraft.class_1297;
import org.itsmahir.pagon.api.event.Event;

public class TeamColorEvent extends Event {
   private final class_1297 entity;
   private int color;

   public TeamColorEvent(class_1297 entity) {
      this.entity = entity;
   }

   public class_1297 getEntity() {
      return this.entity;
   }

   public int getColor() {
      return this.color;
   }

   public void setColor(int color) {
      this.color = color;
   }
}
