package org.itsmahir.pagon.api.utils.render;

import net.minecraft.class_310;

public class ScaledResolution {
   public int scaledWidth;
   public int scaledHeight;
   double scaleFactor;

   public ScaledResolution(int scaledWidth, int scaledHeight) {
      this.scaledWidth = scaledWidth;
      this.scaledHeight = scaledHeight;
      this.scaleFactor = (double)1.0F;
   }

   public ScaledResolution(class_310 client) {
      this.scaledWidth = client.method_22683().method_4486();
      this.scaledHeight = client.method_22683().method_4502();
      this.scaleFactor = client.method_22683().method_4495();
   }

   public int getScaledWidth() {
      return this.scaledWidth;
   }

   public int getScaledHeight() {
      return this.scaledHeight;
   }

   public double getScaleFactor() {
      return this.scaleFactor;
   }

   public void setScaledWidth(int scaledWidth) {
      this.scaledWidth = scaledWidth;
   }

   public void setScaledHeight(int scaledHeight) {
      this.scaledHeight = scaledHeight;
   }

   public void setScaleFactor(double scaleFactor) {
      this.scaleFactor = scaleFactor;
   }
}
