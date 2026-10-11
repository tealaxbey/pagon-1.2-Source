package org.itsmahir.pagon.api.event.events.render;

import net.minecraft.class_2338;
import net.minecraft.class_2680;
import org.itsmahir.pagon.api.event.Event;

public class RenderBlockEvent extends Event {
   class_2338 pos;
   class_2680 state;

   public RenderBlockEvent(class_2338 pos, class_2680 state) {
      this.pos = pos;
      this.state = state;
   }

   public class_2338 getPos() {
      return this.pos;
   }

   public class_2680 getState() {
      return this.state;
   }
}
