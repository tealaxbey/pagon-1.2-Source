package org.itsmahir.pagon.api.utils.render.animation.type;

import java.awt.Color;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.render.animation.Animation;
import org.itsmahir.pagon.api.utils.render.animation.Easing;

public class ColorAnimation extends Animation {
   Color normalColor;
   Color activeColor;

   public ColorAnimation(Color normalColor, Color activeColor, Easing easing, long animationTime, boolean state) {
      super(easing, animationTime, state);
      this.normalColor = normalColor;
      this.activeColor = activeColor;
   }

   public Color getColor() {
      return ColorUtil.interpolate(this.getScaledTime(), this.normalColor, this.activeColor);
   }
}
