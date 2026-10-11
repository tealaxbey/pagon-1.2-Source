package org.itsmahir.pagon.api.utils.color;

import java.awt.Color;
import java.util.regex.Pattern;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import org.itsmahir.pagon.api.gui.font.Fonts;
import org.itsmahir.pagon.impl.features.modules.client.gui.Hud;
import org.itsmahir.pagon.impl.gui.ClickGui;

public class RainbowUtil {
   private static final Pattern COLOR_CODE_PATTERN = Pattern.compile("§[0123456789abcdefklmnor]");
   private static final int[] colorCodes = new int[]{0, 170, 43520, 43690, 11141120, 11141290, 16755200, 11184810, 5592405, 5592575, 5635925, 5636095, 16733525, 16733695, 16777045, 16777215};

   public static void renderWave(class_332 stack, String s, Float x, Float y) {
      Float updateX = x;
      char[] characters = s.toCharArray();
      int height = ClickGui.CONTEXT.getRenderer().getTextHeight("A");
      int displayColor = 0;
      String[] parts = COLOR_CODE_PATTERN.split(s);

      for(int i = 0; i < s.length(); ++i) {
         char colorCode = characters[i];
         if (colorCode == 167) {
            char colorChar = characters[i + 1];
            int codeIndex = "0123456789abcdef".indexOf(colorChar);
            if (codeIndex < 0) {
               if (colorChar == 'r') {
                  displayColor = 0;
               }
            } else {
               displayColor = colorCodes[codeIndex];
            }

            i += 2;
         }

         Color rgb;
         if (displayColor == 0) {
            if (((String)Hud.INSTANCE.mode.getValue()).equals("Rainbow")) {
               rgb = effect((long)((double)((float)(i * 3) + x) * ((Number)Hud.INSTANCE.rainbowLength.getValue()).doubleValue() * (double)10000.0F), ((Number)Hud.INSTANCE.saturation.getValue()).floatValue() / 255.0F, ((Number)Hud.INSTANCE.brightness.getValue()).floatValue() / 255.0F);
            } else {
               rgb = Hud.getTextColor((int)((float)(i * height) + x));
            }
         } else {
            rgb = new Color(displayColor);
         }

         try {
            char var10000 = s.charAt(i);
            String str = "" + var10000;
            Fonts.doOneText(stack, str, updateX, y, rgb, ClickGui.CONTEXT.getColorScheme().doesTextShadow());
            updateX = updateX + Fonts.getTextWidth(str);
         } catch (Exception var14) {
         }
      }

   }

   public static void render32k(class_332 context, String s, Float x, Float y) {
      render32k(context.method_51448(), s, x, y);
   }

   public static void render32k(class_4587 stack, String s, Float x, Float y) {
      Float updateX = x;
      char[] characters = s.toCharArray();
      int displayColor = 0;

      for(int i = 0; i < s.length(); ++i) {
         char colorCode = characters[i];
         if (colorCode == 167) {
            char colorChar = characters[i + 1];
            int codeIndex = "0123456789abcdef".indexOf(colorChar);
            if (codeIndex < 0) {
               if (colorChar == 'r') {
                  displayColor = 0;
               }
            } else {
               displayColor = colorCodes[codeIndex];
            }

            i += 2;
         }

         Color rgb;
         if (displayColor == 0) {
            rgb = effect((long)(((float)((long)i * 6L) + x) * 1000000.0F), 0.39215687F, 1.0F);
         } else {
            rgb = new Color(displayColor);
         }

         char var10000 = s.charAt(i);
         String str = "" + var10000;
         Fonts.renderText(stack, str, updateX, y, rgb, ClickGui.CONTEXT.getColorScheme().doesTextShadow());
         updateX = updateX + Fonts.getTextWidth(str);
      }

   }

   public static Color effect(long offset, float saturation, float brightness) {
      float hue = ((float)System.nanoTime() * ((Number)Hud.INSTANCE.rainbowSpeed.getValue()).floatValue() + (float)(offset * 100L)) / 1.0E10F % 1.0F;
      long color = Long.parseLong(Integer.toHexString(Color.HSBtoRGB(hue, saturation, brightness)), 16);
      Color c = new Color((int)color);
      return new Color((float)c.getRed() / 255.0F, (float)c.getGreen() / 255.0F, (float)c.getBlue() / 255.0F, (float)c.getAlpha() / 255.0F);
   }
}
