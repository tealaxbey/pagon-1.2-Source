package org.itsmahir.pagon.api.utils.render.animation;

public class Animation {
   public long animationTime;
   public Easing easing;
   public long time;
   public boolean state;

   public Animation(Easing easing, long animationTime, boolean state) {
      this.easing = easing;
      this.animationTime = animationTime;
      this.state = state;
   }

   public Animation(Easing easing, long animationTime) {
      this(easing, animationTime, false);
   }

   public Animation(Easing easing) {
      this(easing, 300L, false);
   }

   public float getScaledTime() {
      double linear = (double)((float)(System.currentTimeMillis() - this.time) / (float)this.animationTime);
      if (!this.state) {
         linear = (double)1.0F - linear;
      }

      return (float)Math.min(Math.max(this.easing.ease(linear), (double)0.0F), (double)1.0F);
   }

   public boolean getState() {
      return this.state;
   }

   public void setState(boolean state) {
      if (this.state != state) {
         this.state = state;
         this.time = System.currentTimeMillis();
      }
   }

   public void setStateHard(boolean state) {
      this.state = state;
      this.time = 0L;
   }

   public void setAnimationTime(long animationTime) {
      this.animationTime = animationTime;
   }

   public void setEasing(Easing easing) {
      this.easing = easing;
   }

   public void setTime(long time) {
      this.time = time;
   }
}
