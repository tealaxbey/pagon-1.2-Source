package org.itsmahir.pagon.api.utils.world.ca;

import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_2338;

public class Result {
   public class_1657 target;
   public double bestPlaceDMG;
   public double bestBreakDMG;
   public class_2338 calcPos;
   public class_1297 calcCrystal;
   public float[] placeRots;
   public float[] breakRots;
   public class_2338 explodePos;

   public Result(class_1657 target, double bestPlaceDMG, class_2338 calcPos, float[] placeRots, class_1297 calcCrystal, double bestBreakDMG, float[] breakRots) {
      this.target = target;
      this.bestPlaceDMG = bestPlaceDMG;
      this.calcPos = calcPos;
      this.calcCrystal = calcCrystal;
      this.bestBreakDMG = bestBreakDMG;
      this.placeRots = placeRots;
      this.breakRots = breakRots;
   }

   public Result(BreakResult breakResult, PlaceResult placeResult) {
      this.target = breakResult.target;
      this.bestPlaceDMG = placeResult.bestDMG;
      this.calcPos = placeResult.calcPos;
      this.calcCrystal = breakResult.calcCrystal;
      this.bestBreakDMG = breakResult.bestDMG;
      this.placeRots = placeResult.rots;
      this.breakRots = breakResult.rots;
   }

   public Result(PlaceResult explodeResult, PlaceResult placeResult) {
      this.target = placeResult.target;
      this.bestPlaceDMG = placeResult.bestDMG;
      this.calcPos = placeResult.calcPos;
      this.bestBreakDMG = explodeResult.bestDMG;
      this.explodePos = explodeResult.calcPos;
      this.placeRots = placeResult.rots;
      this.breakRots = explodeResult.rots;
   }

   public double getDamage() {
      return Math.max(this.bestBreakDMG, this.bestPlaceDMG);
   }
}
