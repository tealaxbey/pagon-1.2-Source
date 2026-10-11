package org.itsmahir.pagon.api.utils.render.font.fonts;

import java.util.List;
import net.minecraft.class_2583;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_377;
import net.minecraft.class_379;
import net.minecraft.class_382;
import net.minecraft.class_384;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_5223;
import net.minecraft.class_5224;
import net.minecraft.class_5251;
import net.minecraft.class_327.class_6415;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.mixin.accessor.ITextRenderer;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

public class VanillaTextRenderer implements IMinecraft {
   public void renderTextNoLayer(class_332 context, class_4587 matrices, String text, float x, float y, int color, boolean shadow) {
      if (shadow) {
         this.drawNoLayer(context, text, x + 1.0F, y + 1.0F, color, matrices.method_23760().method_23761(), true);
      }

      this.drawNoLayer(context, text, x, y, color, matrices.method_23760().method_23761(), false);
   }

   public void renderGlow(class_332 context, class_4587 matrices, String text, float x, float y, int color) {
      int r = color >> 16 & 255;
      int g = color >> 8 & 255;
      int b = color & 255;
      int layers = 6;
      float spread = 0.6F;
      Matrix4f matrix = matrices.method_23760().method_23761();

      for(int i = 1; i <= layers; ++i) {
         float off = (float)i * spread;
         int a = Math.max(5, 120 - i * 18);
         int glowColor = a << 24 | r << 16 | g << 8 | b;
         this.drawNoLayer(context, text, x - off, y - off, glowColor, matrix, false);
         this.drawNoLayer(context, text, x, y - off, glowColor, matrix, false);
         this.drawNoLayer(context, text, x + off, y - off, glowColor, matrix, false);
         this.drawNoLayer(context, text, x - off, y, glowColor, matrix, false);
         this.drawNoLayer(context, text, x + off, y, glowColor, matrix, false);
         this.drawNoLayer(context, text, x - off, y + off, glowColor, matrix, false);
         this.drawNoLayer(context, text, x, y + off, glowColor, matrix, false);
         this.drawNoLayer(context, text, x + off, y + off, glowColor, matrix, false);
      }

      int coreR = Math.min(255, r + 150);
      int coreG = Math.min(255, g + 150);
      int coreB = Math.min(255, b + 150);
      int coreColor = -16777216 | coreR << 16 | coreG << 8 | coreB;
      this.drawNoLayer(context, text, x, y, coreColor, matrix, false);
   }

   public void renderGlow(class_4587 matrices, String text, float x, float y, int color) {
      int r = color >> 16 & 255;
      int g = color >> 8 & 255;
      int b = color & 255;
      int layers = 6;
      float spread = 0.6F;

      for(int i = 1; i <= layers; ++i) {
         float off = (float)i * spread;
         int a = Math.max(5, 120 - i * 18);
         int glowColor = a << 24 | r << 16 | g << 8 | b;
         this.draw(matrices, text, x - off, y - off, glowColor, false);
         this.draw(matrices, text, x, y - off, glowColor, false);
         this.draw(matrices, text, x + off, y - off, glowColor, false);
         this.draw(matrices, text, x - off, y, glowColor, false);
         this.draw(matrices, text, x + off, y, glowColor, false);
         this.draw(matrices, text, x - off, y + off, glowColor, false);
         this.draw(matrices, text, x, y + off, glowColor, false);
         this.draw(matrices, text, x + off, y + off, glowColor, false);
      }

      int coreR = Math.min(255, r + 150);
      int coreG = Math.min(255, g + 150);
      int coreB = Math.min(255, b + 150);
      int coreColor = -16777216 | coreR << 16 | coreG << 8 | coreB;
      this.draw(matrices, text, x, y, coreColor, false);
   }

   public void renderCsgoLayer(class_332 context, class_4587 matrices, String text, float x, float y, int color) {
      this.drawNoLayer(context, text, x + 0.4F, y + 0.4F, color, matrices.method_23760().method_23761(), true);
      this.drawNoLayer(context, text, x - 0.4F, y - 0.4F, color, matrices.method_23760().method_23761(), true);
      this.drawNoLayer(context, text, x - 0.4F, y + 0.4F, color, matrices.method_23760().method_23761(), true);
      this.drawNoLayer(context, text, x + 0.4F, y - 0.4F, color, matrices.method_23760().method_23761(), true);
      this.drawNoLayer(context, text, x, y, color, matrices.method_23760().method_23761(), false);
   }

   private void drawNoLayer(class_332 context, String text, float x, float y, int color, Matrix4f matrix, boolean shadow) {
      if (text != null) {
         this.draw(text, x, y, color, shadow, matrix, context.method_51450(), class_6415.field_33993, 0, 15728880);
      }
   }

   public void drawText(class_332 context, class_4587 matrices, String text, float x, float y, int color, boolean shadow) {
      if (shadow) {
         this.draw(context, matrices, text, x + 1.0F, y + 1.0F, color, true);
      }

      this.draw(context, matrices, text, x, y, color, false);
   }

   public void draw(class_332 context, class_4587 matrices, String text, float x, float y, int color, boolean shadow) {
      this.draw(context, text, x, y, color, matrices.method_23760().method_23761(), shadow);
   }

   private void draw(class_332 context, String text, float x, float y, int color, Matrix4f matrix, boolean shadow) {
      if (text != null) {
         this.draw(text, x, y, color, shadow, matrix, context.method_51450(), class_6415.field_33993, 0, 15728880);
         context.method_51450().method_22993();
      }
   }

   public void drawText(class_4587 matrices, String text, float x, float y, int color, boolean shadow) {
      if (shadow) {
         this.draw(matrices, text, x + 1.0F, y + 1.0F, color, true);
      }

      this.draw(matrices, text, x, y, color, false);
   }

   public void draw(class_4587 matrices, String text, float x, float y, int color, boolean shadow) {
      this.draw(text, x, y, color, matrices.method_23760().method_23761(), shadow);
   }

   private void draw(String text, float x, float y, int color, Matrix4f matrix, boolean shadow) {
      if (text != null) {
         class_4597.class_4598 vertexConsumers = mc.method_22940().method_23000();
         this.draw(text, x, y, color, shadow, matrix, vertexConsumers, class_6415.field_33994, 0, 15728880);
         vertexConsumers.method_22993();
      }
   }

   public void draw(String text, float x, float y, int color, boolean shadow, Matrix4f matrix, class_4597 vertexConsumers, class_327.class_6415 layerType, int backgroundColor, int light) {
      this.drawInternal(text, x, y, color, shadow, matrix, vertexConsumers, layerType, backgroundColor, light);
   }

   private void drawInternal(String text, float x, float y, int color, boolean shadow, Matrix4f matrix, class_4597 vertexConsumers, class_327.class_6415 layerType, int backgroundColor, int light) {
      Matrix4f matrix4f = new Matrix4f(matrix);
      this.drawLayer(text, x, y, color, shadow, matrix4f, vertexConsumers, layerType, backgroundColor, light);
   }

   private void drawLayer(String text, float x, float y, int color, boolean shadow, Matrix4f matrix, class_4597 vertexConsumerProvider, class_327.class_6415 layerType, int underlineColor, int light) {
      Drawer drawer = new Drawer(vertexConsumerProvider, x, y, color, shadow, matrix, layerType, light);
      class_5223.method_27479(text, class_2583.field_24360, drawer);
      drawer.drawLayer();
   }

   public static class Drawer implements class_5224 {
      final class_4597 vertexConsumers;
      private final float brightnessMultiplier;
      private final float red;
      private final float green;
      private final float blue;
      private final float alpha;
      private final Matrix4f matrix;
      private final class_327.class_6415 layerType;
      private final int light;
      float x;
      float y;
      private @Nullable List<class_382.class_328> rectangles;

      public Drawer(class_4597 vertexConsumers, float x, float y, int color, boolean shadow, Matrix4f matrix, class_327.class_6415 layerType, int light) {
         this.vertexConsumers = vertexConsumers;
         this.x = x;
         this.y = y;
         this.brightnessMultiplier = shadow ? 0.25F : 1.0F;
         this.red = (float)(color >> 16 & 255) / 255.0F * this.brightnessMultiplier;
         this.green = (float)(color >> 8 & 255) / 255.0F * this.brightnessMultiplier;
         this.blue = (float)(color & 255) / 255.0F * this.brightnessMultiplier;
         this.alpha = (float)(color >> 24 & 255) / 255.0F;
         this.matrix = matrix;
         this.layerType = layerType;
         this.light = light;
      }

      public boolean accept(int i, class_2583 style, int j) {
         class_377 fontStorage = ((ITextRenderer)IMinecraft.mc.field_1772).hookGetFontStorage(style.method_27708());
         class_379 glyph = fontStorage.method_2011(j, ((ITextRenderer)IMinecraft.mc.field_1772).hookGetValidateAdvance());
         class_382 glyphRenderer = style.method_10987() && j != 32 ? fontStorage.method_2013(glyph) : fontStorage.method_2014(j);
         boolean bl = style.method_10984();
         class_5251 textColor = style.method_10973();
         float l;
         float h;
         float g;
         if (textColor != null) {
            int k = textColor.method_27716();
            g = (float)(k >> 16 & 255) / 255.0F * this.brightnessMultiplier;
            h = (float)(k >> 8 & 255) / 255.0F * this.brightnessMultiplier;
            l = (float)(k & 255) / 255.0F * this.brightnessMultiplier;
         } else {
            g = this.red;
            h = this.green;
            l = this.blue;
         }

         if (!(glyphRenderer instanceof class_384)) {
            float m = bl ? glyph.method_16799() : 0.0F;
            ((ITextRenderer)IMinecraft.mc.field_1772).hookDrawGlyph(glyphRenderer, bl, style.method_10966(), m, this.x, this.y, this.matrix, this.vertexConsumers.getBuffer(glyphRenderer.method_24045(this.layerType)), g, h, l, this.alpha, this.light);
         }

         float m = glyph.method_16798(bl);
         this.x += m;
         return true;
      }

      public void drawLayer() {
         if (this.rectangles != null) {
            class_377 fontStorage = ((ITextRenderer)IMinecraft.mc.field_1772).hookGetFontStorage(class_2583.field_24359);
            class_382 glyphRenderer = fontStorage.method_22943();

            for(class_382.class_328 rectangle : this.rectangles) {
               glyphRenderer.method_22944(rectangle, this.matrix, this.vertexConsumers.getBuffer(glyphRenderer.method_24045(this.layerType)), this.light);
            }
         }

      }
   }
}
