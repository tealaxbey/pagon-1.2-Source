package org.itsmahir.pagon.api.utils.render.animation.type;

import java.awt.Color;
import org.itsmahir.pagon.api.utils.render.animation.Animation;
import org.itsmahir.pagon.api.utils.render.animation.Easing;

public class OffsetAnimation extends Animation {
   Color normalColor;
   Color middleColor;
   Color lastColor;

   public OffsetAnimation(Color normalColor, Color middleColor, Color centerColor, Easing easing, long animationTime, boolean state) {
      super(easing, animationTime, state);
   }

   public void tick() {
      if (this.getColorTime() == 1.5F && this.state) {
         this.time = System.currentTimeMillis();
      }

   }

   public float getColorTime() {
      double linear = (double)((float)(System.currentTimeMillis() - this.time) / (float)this.animationTime);
      if (!this.state) {
         linear = (double)1.5F - linear;
      }

      return (float)Math.min(Math.max(this.easing.ease(linear), (double)0.0F), (double)1.5F);
   }
}
