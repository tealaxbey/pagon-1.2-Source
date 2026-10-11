package org.itsmahir.pagon.api.utils.world.ca;

import net.minecraft.class_1657;
import net.minecraft.class_2338;

public class PlaceResult {
   class_1657 target;
   double bestDMG;
   class_2338 calcPos;
   float[] rots;

   public PlaceResult(class_1657 target, double bestDMG, class_2338 calcPos, float[] rots) {
      this.target = target;
      this.bestDMG = bestDMG;
      this.calcPos = calcPos;
      this.rots = rots;
   }
}
