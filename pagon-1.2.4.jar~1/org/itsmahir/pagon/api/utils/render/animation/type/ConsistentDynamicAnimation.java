package org.itsmahir.pagon.api.utils.render.animation.type;

import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.render.Interpolator;
import org.itsmahir.pagon.api.utils.render.animation.Animation;
import org.itsmahir.pagon.api.utils.render.animation.Easing;

public class ConsistentDynamicAnimation extends Animation {
   float value;
   float lastValue;
   float unitsPer100;
   Easing oldEasing;

   public ConsistentDynamicAnimation(float value, Easing easing, long unitsPer100) {
      super(easing, 100L, true);
      this.value = value;
      this.lastValue = value;
      this.unitsPer100 = (float)unitsPer100;
      this.oldEasing = easing;
   }

   public void goal(float goalValue) {
      if (this.getAnimationValue() == this.value && this.oldEasing != null) {
         this.easing = this.oldEasing;
         this.oldEasing = null;
      }

      if (this.value != goalValue) {
         this.lastValue = this.getAnimationValue();
         this.animationTime = (long)(MathUtil.distance(this.lastValue, goalValue, false) / (double)this.unitsPer100 * (double)100.0F);
         this.time = System.currentTimeMillis();
         this.value = goalValue;
      }
   }

   public void updateLastValue(float newLastValue) {
      if (this.lastValue != newLastValue) {
         this.lastValue = newLastValue;
         this.animationTime = (long)(MathUtil.distance(this.lastValue, this.value, false) / (double)this.unitsPer100 * (double)100.0F);
         this.time = System.currentTimeMillis();
         if (this.easing.equals(Easing.CUBIC_IN_OUT)) {
            this.oldEasing = this.easing;
            this.easing = Easing.CUBIC_OUT;
         }

      }
   }

   public void setEasing(Easing easing) {
      if (this.oldEasing != null) {
         this.oldEasing = easing;
      } else {
         super.setEasing(easing);
      }
   }

   public boolean isFinished() {
      if (System.currentTimeMillis() - this.time > this.animationTime) {
         if (this.oldEasing != null) {
            this.easing = this.oldEasing;
            this.oldEasing = null;
         }

         return true;
      } else {
         return false;
      }
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

   public void setUnitsPer100(float unitsPer100) {
      this.unitsPer100 = unitsPer100;
   }

   public void setOldEasing(Easing oldEasing) {
      this.oldEasing = oldEasing;
   }
}
