package org.itsmahir.pagon.api.utils.render.animation.type;

import java.awt.Color;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.render.animation.Animation;
import org.itsmahir.pagon.api.utils.render.animation.Easing;

public class DynamicColorAnimation extends Animation {
   Color normalColor;
   Color lastColor;

   public DynamicColorAnimation(Color normalColor, Easing easing, long animationTime) {
      super(easing, animationTime, true);
      this.normalColor = normalColor;
      this.lastColor = normalColor;
   }

   public void gotoColor(Color color) {
      if (!this.normalColor.equals(color)) {
         this.lastColor = this.normalColor;
         this.normalColor = color;
         this.time = System.currentTimeMillis();
      }
   }

   public Color getColor() {
      return ColorUtil.interpolate(this.getScaledTime(), this.normalColor, this.lastColor);
   }

   public void setNormalColor(Color normalColor) {
      this.normalColor = normalColor;
   }

   public void setLastColor(Color lastColor) {
      this.lastColor = lastColor;
   }
}
