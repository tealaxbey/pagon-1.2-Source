package org.itsmahir.pagon.api.utils.render.animation.type;

import org.itsmahir.pagon.api.utils.render.Interpolator;
import org.itsmahir.pagon.api.utils.render.animation.Animation;
import org.itsmahir.pagon.api.utils.render.animation.Easing;

public class DynamicAnimation extends Animation {
   float value;
   float lastValue;

   public DynamicAnimation(float value, Easing easing, long animationTime) {
      super(easing, animationTime, true);
      this.value = value;
      this.lastValue = value;
   }

   public void goal(float goalValue) {
      if (this.value != goalValue) {
         this.lastValue = this.getAnimationValue();
         this.time = System.currentTimeMillis();
         this.value = goalValue;
      }
   }

   public boolean isFinished() {
      return this.getScaledTime() == 1.0F;
   }

   public float getAnimationValue() {
      return Interpolator.interpolateFloat(this.lastValue, this.value, this.getScaledTime());
   }

   public void setValue(float value) {
      this.value = value;
   }

   public void setLastValue(float lastValue) {
      this.lastValue = lastValue;
   }
}
