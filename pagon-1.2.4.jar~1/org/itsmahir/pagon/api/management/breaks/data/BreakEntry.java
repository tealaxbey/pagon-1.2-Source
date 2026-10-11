package org.itsmahir.pagon.api.management.breaks.data;

import net.minecraft.class_2338;

public class BreakEntry {
   BreakData extraBreak = null;
   BreakData normalBreak = null;
   boolean extra = true;

   public BreakEntry() {
      this.extra = false;
   }

   public void tick() {
      if (this.normalBreak != null && this.normalBreak.tick()) {
         this.normalBreak = null;
      }

      if (this.extraBreak != null && this.extraBreak.tick()) {
         this.extraBreak = null;
      }

   }

   public void startMining(class_2338 pos) {
      if (this.normalBreak == null || !this.normalBreak.getPos().equals(pos)) {
         if (this.extraBreak == null || !this.extraBreak.getPos().equals(pos)) {
            if (this.extra) {
               this.extraBreak = new BreakData(pos);
            } else {
               this.normalBreak = new BreakData(pos);
            }

            this.extra = !this.extra;
         }
      }
   }

   public BreakData getExtraBreak() {
      return this.extraBreak;
   }

   public BreakData getNormalBreak() {
      return this.normalBreak;
   }

   public boolean isExtra() {
      return this.extra;
   }
}
