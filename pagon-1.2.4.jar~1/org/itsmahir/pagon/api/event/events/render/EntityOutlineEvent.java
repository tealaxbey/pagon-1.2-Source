package org.itsmahir.pagon.api.event.events.render;

import net.minecraft.class_1297;
import org.itsmahir.pagon.api.event.Event;

public class EntityOutlineEvent extends Event {
   private final class_1297 entity;

   public EntityOutlineEvent(class_1297 entity) {
      this.entity = entity;
   }

   public class_1297 getEntity() {
      return this.entity;
   }
}
