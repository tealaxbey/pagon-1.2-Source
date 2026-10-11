package org.itsmahir.pagon.api.utils.color;

import java.awt.Color;
import net.minecraft.class_1291;
import net.minecraft.class_3532;
import net.minecraft.class_4588;
import net.minecraft.class_5253.class_5254;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.lwjgl.opengl.GL11;

public class ColorUtil {
   public static Color newAlpha(Color color, int alpha) {
      return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
   }

   public static int newAlpha(int in, int alpha) {
      Color color = new Color(in);
      return (new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha)).getRGB();
   }

   public static class_4588 color(class_4588 consumer, Color color) {
      int rgb = color.getRGB();
      float f = (float)class_5254.method_27762(rgb) / 255.0F;
      float g = (float)class_5254.method_27765(rgb) / 255.0F;
      float h = (float)class_5254.method_27766(rgb) / 255.0F;
      float j = (float)class_5254.method_27767(rgb) / 255.0F;
      return consumer.method_22915(g, h, j, f);
   }

   public static Color brighten(Color color, double fraction) {
      int red = (int)Math.round(Math.min((double)255.0F, (double)color.getRed() + (double)255.0F * fraction));
      int green = (int)Math.round(Math.min((double)255.0F, (double)color.getGreen() + (double)255.0F * fraction));
      int blue = (int)Math.round(Math.min((double)255.0F, (double)color.getBlue() + (double)255.0F * fraction));
      int alpha = color.getAlpha();
      return new Color(red, green, blue, alpha);
   }

   public static void reset() {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public static Color interpolate(float value, Color start, Color end) {
      float sr = (float)start.getRed() / 255.0F;
      float sg = (float)start.getGreen() / 255.0F;
      float sb = (float)start.getBlue() / 255.0F;
      float sa = (float)start.getAlpha() / 255.0F;
      float er = (float)end.getRed() / 255.0F;
      float eg = (float)end.getGreen() / 255.0F;
      float eb = (float)end.getBlue() / 255.0F;
      float ea = (float)end.getAlpha() / 255.0F;
      float r = sr * value + er * (1.0F - value);
      float g = sg * value + eg * (1.0F - value);
      float b = sb * value + eb * (1.0F - value);
      float a = sa * value + ea * (1.0F - value);
      return new Color(r, g, b, a);
   }

   public static Color interpolate(float value, Color start, Color middle, Color end) {
      return value < 0.5F ? interpolate((float)class_3532.method_15350(MathUtil.normalize((double)value, (double)0.0F, (double)0.5F), (double)0.0F, (double)1.0F), middle, start) : interpolate((float)class_3532.method_15350(MathUtil.normalize((double)value, (double)0.5F, (double)1.0F), (double)0.0F, (double)1.0F), end, middle);
   }

   public static Color realInterp(float value, Color start, Color middle, Color end) {
      if (value < 0.5F) {
         return interpolate((float)class_3532.method_15350(MathUtil.normalize((double)value, (double)0.0F, (double)0.5F), (double)0.0F, (double)1.0F), middle, start);
      } else {
         return value < 1.0F ? interpolate((float)class_3532.method_15350(MathUtil.normalize((double)value, (double)0.5F, (double)1.0F), (double)0.0F, (double)1.0F), end, middle) : interpolate((float)class_3532.method_15350(MathUtil.normalize((double)value, (double)1.0F, (double)1.5F), (double)0.0F, (double)1.0F), start, end);
      }
   }

   public static Color hslToColor(float f, float f2, float f3, float f4) {
      if (!(f2 < 0.0F) && !(f2 > 100.0F)) {
         if (!(f3 < 0.0F) && !(f3 > 100.0F)) {
            if (!(f4 < 0.0F) && !(f4 > 1.0F)) {
               f %= 360.0F;
               float f5 = 0.0F;
               float var8;
               f5 = (double)f3 < (double)0.5F ? f3 * (1.0F + f2) : (f3 /= 100.0F) + (var8 = f2 / 100.0F) - var8 * f3;
               f2 = 2.0F * f3 - f5;
               float var7;
               f3 = Math.max(0.0F, colorCalc(f2, f5, (var7 = f / 360.0F) + 0.33333334F));
               float f6 = Math.max(0.0F, colorCalc(f2, f5, var7));
               f2 = Math.max(0.0F, colorCalc(f2, f5, var7 - 0.33333334F));
               f3 = Math.min(f3, 1.0F);
               f6 = Math.min(f6, 1.0F);
               f2 = Math.min(f2, 1.0F);
               return new Color(f3, f6, f2, f4);
            } else {
               throw new IllegalArgumentException("Color parameter outside of expected range - Alpha");
            }
         } else {
            throw new IllegalArgumentException("Color parameter outside of expected range - Lightness");
         }
      } else {
         throw new IllegalArgumentException("Color parameter outside of expected range - Saturation");
      }
   }

   private static float colorCalc(float f, float f2, float f3) {
      if (f3 < 0.0F) {
         ++f3;
      }

      if (f3 > 1.0F) {
         --f3;
      }

      if (6.0F * f3 < 1.0F) {
         return f + (f2 - f) * 6.0F * f3;
      } else if (2.0F * f3 < 1.0F) {
         return f2;
      } else {
         return 3.0F * f3 < 2.0F ? f + (f2 - f) * 6.0F * (0.6666667F - f3) : f;
      }
   }

   public static Color getLiquidColor(String name, class_1291 effect) {
      Color var10000;
      switch (name) {
         case "speed" -> var10000 = new Color(8171462);
         case "slowness" -> var10000 = new Color(5926017);
         case "haste" -> var10000 = new Color(14270531);
         case "mining_fatigue" -> var10000 = new Color(4866583);
         case "strength" -> var10000 = new Color(9643043);
         case "instant_health" -> var10000 = new Color(16262179);
         case "instant_damage" -> var10000 = new Color(4393481);
         case "jump_boost" -> var10000 = new Color(2293580);
         case "nausea" -> var10000 = new Color(5578058);
         case "regeneration" -> var10000 = new Color(13458603);
         case "resistance" -> var10000 = new Color(10044730);
         case "fire_resistance" -> var10000 = new Color(14981690);
         case "water_breathing" -> var10000 = new Color(3035801);
         case "invisibility" -> var10000 = new Color(8356754);
         case "blindness" -> var10000 = new Color(2039587);
         case "night_vision" -> var10000 = new Color(2039713);
         case "hunger" -> var10000 = new Color(5797459);
         case "weakness" -> var10000 = new Color(4738376);
         case "poison" -> var10000 = new Color(5149489);
         case "wither" -> var10000 = new Color(3484199);
         case "health_boost" -> var10000 = new Color(16284963);
         case "absorption" -> var10000 = new Color(2445989);
         case "saturation" -> var10000 = new Color(16262179);
         case "glowing" -> var10000 = new Color(9740385);
         case "levitation" -> var10000 = new Color(13565951);
         case "luck" -> var10000 = new Color(3381504);
         case "unluck" -> var10000 = new Color(12624973);
         default -> var10000 = new Color(effect.method_5556());
      }

      return var10000;
   }
}
