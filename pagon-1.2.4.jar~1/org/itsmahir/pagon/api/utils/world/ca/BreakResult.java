package org.itsmahir.pagon.api.utils.world.ca;

import net.minecraft.class_1297;
import net.minecraft.class_1657;

public class BreakResult {
   class_1657 target;
   double bestDMG;
   public class_1297 calcCrystal;
   float[] rots;

   public BreakResult(class_1657 target, double bestDMG, class_1297 calcCrystal, float[] rots) {
      this.target = target;
      this.bestDMG = bestDMG;
      this.calcCrystal = calcCrystal;
      this.rots = rots;
   }
}
