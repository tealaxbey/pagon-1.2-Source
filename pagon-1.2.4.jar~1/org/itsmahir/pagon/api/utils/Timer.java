package org.itsmahir.pagon.api.utils;

import org.itsmahir.pagon.api.utils.math.MathUtil;

public class Timer {
   long startTime = System.currentTimeMillis();
   long lastResetTime = 0L;
   long delay = 0L;
   boolean paused = false;

   public Timer() {
   }

   public Timer(long delay) {
      this.delay = delay;
   }

   public boolean isPassed() {
      if (this.delay <= 1L && !this.paused) {
         return true;
      } else {
         return !this.paused && System.currentTimeMillis() - this.startTime >= this.delay;
      }
   }

   public boolean isPassed(long delay) {
      if (delay <= 1L && !this.paused) {
         return true;
      } else {
         return !this.paused && System.currentTimeMillis() - this.startTime >= delay;
      }
   }

   public long getTime() {
      return System.currentTimeMillis() - this.startTime;
   }

   public double getTimeUntilDone() {
      return MathUtil.round((double)(this.startTime + this.delay - System.currentTimeMillis()) / (double)1000.0F, 1);
   }

   private double timeDifference() {
      return MathUtil.round((double)(System.currentTimeMillis() - this.startTime) / (double)1000.0F, 1);
   }

   public void resetDelay() {
      long current = System.currentTimeMillis();
      this.lastResetTime = current - this.startTime;
      this.startTime = current;
   }

   public void setDelayCPS(float cps) {
      long delay = (long)(1000.0F / cps);
      if ((double)cps == (double)20.0F) {
         delay = 0L;
      }

      this.setDelay(delay - 10L);
   }

   public void setPaused(boolean paused) {
      this.paused = paused;
   }

   public boolean isPaused() {
      return this.paused;
   }

   public long getStartTime() {
      return this.startTime;
   }

   public long getResetTime() {
      return this.lastResetTime;
   }

   public void setDelay(long delay) {
      this.delay = delay;
   }
}
