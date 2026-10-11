package org.itsmahir.pagon.api.event.events.render;

import net.minecraft.class_4587;
import org.itsmahir.pagon.api.event.Event;

public class RenderShaderEvent extends Event {
   private final class_4587 matrices;
   private final float tickDelta;

   public RenderShaderEvent(class_4587 matrices, float tickDelta) {
      this.matrices = matrices;
      this.tickDelta = tickDelta;
   }

   public class_4587 getMatrices() {
      return this.matrices;
   }

   public float getTickDelta() {
      return this.tickDelta;
   }
}
