package org.itsmahir.pagon.api.utils.render.particle;

import java.awt.Color;
import java.util.Random;
import net.minecraft.class_332;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.math.MathUtil;

public class SnowParticle {
   private static final Random RANDOM = new Random();
   private float x;
   private float y;
   private final float speed;
   private final int size;
   private float velocityX;
   private float velocityY;

   public void onRender(class_332 context, double mouseX, double mouseY) {
      double dx = (double)this.x - mouseX;
      double dy = (double)this.y - mouseY;
      double distanceSq = MathUtil.square(dx) + MathUtil.square(dy);
      if (distanceSq < (double)MathUtil.square(25.0F) && distanceSq > (double)0.0F) {
         double distance = Math.sqrt(distanceSq);
         double force = (double)2.5F / distance;
         this.velocityX += (float)(dx * force);
         this.velocityY += (float)(dy * force);
      }

      this.x += this.velocityX;
      this.y += this.speed + this.velocityY;
      this.velocityX *= 0.9F;
      this.velocityY *= 0.9F;
      if (this.y > (float)context.method_51443()) {
         this.y = (float)(-this.size);
         this.x = (float)RANDOM.nextInt(context.method_51421());
         this.velocityX = 0.0F;
         this.velocityY = 0.0F;
      }

      if (this.x < 0.0F) {
         this.x = (float)context.method_51421();
      }

      if (this.x > (float)context.method_51421()) {
         this.x = 0.0F;
      }

      context.method_25294((int)this.x, (int)this.y, (int)this.x + this.size, (int)this.y + this.size, ColorUtil.newAlpha(Color.WHITE, 125).getRGB());
   }

   public static SnowParticle random(int screenWidth, int screenHeight) {
      return new SnowParticle((float)((int)(RANDOM.nextFloat() * (float)screenWidth)), (float)RANDOM.nextInt(screenHeight), 0.5F + RANDOM.nextFloat() * 1.5F, 1 + RANDOM.nextInt(3), 0.0F, 0.0F);
   }

   public float getX() {
      return this.x;
   }

   public float getY() {
      return this.y;
   }

   public float getSpeed() {
      return this.speed;
   }

   public int getSize() {
      return this.size;
   }

   public float getVelocityX() {
      return this.velocityX;
   }

   public float getVelocityY() {
      return this.velocityY;
   }

   public SnowParticle(float x, float y, float speed, int size, float velocityX, float velocityY) {
      this.x = x;
      this.y = y;
      this.speed = speed;
      this.size = size;
      this.velocityX = velocityX;
      this.velocityY = velocityY;
   }
}
