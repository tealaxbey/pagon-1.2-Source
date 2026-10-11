package org.itsmahir.pagon.api.gui.font;

import java.awt.Color;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.Objects;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.render.font.fonts.CustomFontRenderer;
import org.itsmahir.pagon.api.utils.render.font.fonts.VanillaTextRenderer;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.jetbrains.annotations.NotNull;

public class Fonts implements IMinecraft {
   public static final VanillaTextRenderer VANILLA = new VanillaTextRenderer();
   public static CustomFontRenderer CUSTOM;

   public static @NotNull CustomFontRenderer create(float size) throws IOException, FontFormatException {
      return new CustomFontRenderer(size / 2.0F);
   }

   private static void renderCustomGlow(class_4587 stack, String text, float x, float y, Color color) {
      int layers = 6;
      float spread = 0.6F;

      for(int i = 1; i <= layers; ++i) {
         float off = (float)i * spread;
         int alpha = Math.max(5, 120 - i * 18);
         Color glowColor = ColorUtil.newAlpha(color, alpha);
         CUSTOM.drawText(stack, text, (double)(x - off), (double)(y - off), glowColor, false);
         CUSTOM.drawText(stack, text, (double)x, (double)(y - off), glowColor, false);
         CUSTOM.drawText(stack, text, (double)(x + off), (double)(y - off), glowColor, false);
         CUSTOM.drawText(stack, text, (double)(x - off), (double)y, glowColor, false);
         CUSTOM.drawText(stack, text, (double)(x + off), (double)y, glowColor, false);
         CUSTOM.drawText(stack, text, (double)(x - off), (double)(y + off), glowColor, false);
         CUSTOM.drawText(stack, text, (double)x, (double)(y + off), glowColor, false);
         CUSTOM.drawText(stack, text, (double)(x + off), (double)(y + off), glowColor, false);
      }

      Color coreColor = new Color(Math.min(255, color.getRed() + 150), Math.min(255, color.getGreen() + 150), Math.min(255, color.getBlue() + 150), 255);
      CUSTOM.drawText(stack, text, (double)x, (double)y, coreColor, false);
   }

   private static void renderGlow(class_4587 stack, String text, float x, float y, Color color) {
      if (org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.isEnabled()) {
         ensureCustom();
         if (CUSTOM != null) {
            renderCustomGlow(stack, text, x, y, color);
            return;
         }
      }

      VANILLA.renderGlow(stack, text, x, y, color.getRGB());
   }

   public static void renderText(class_4587 stack, String text, float x, float y, Color color, boolean shadow) {
      if ((Boolean)org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.glow.getValue()) {
         renderGlow(stack, text, x, y, color);
      } else {
         if (org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.isEnabled()) {
            ensureCustom();
            if (CUSTOM != null) {
               CUSTOM.drawText(stack, text, (double)x, (double)y, color, shadow);
               return;
            }
         }

         VANILLA.drawText(stack, text, x, y, color.getRGB(), shadow);
      }
   }

   public static void renderText(class_332 context, String text, float x, float y, Color color, boolean shadow) {
      if ((Boolean)org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.glow.getValue()) {
         renderGlow(context.method_51448(), text, x, y, color);
      } else {
         if (org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.isEnabled()) {
            ensureCustom();
            if (CUSTOM != null) {
               CUSTOM.drawText(context.method_51448(), text, (double)x, (double)y, color, shadow);
               return;
            }
         }

         VANILLA.drawText(context, context.method_51448(), text, x, y, color.getRGB(), shadow);
      }
   }

   public static void doOneText(class_332 context, String text, float x, float y, Color color, boolean shadow) {
      if ((Boolean)org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.glow.getValue()) {
         renderGlow(context.method_51448(), text, x, y, color);
      } else {
         if (org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.isEnabled()) {
            ensureCustom();
            if (CUSTOM != null) {
               CUSTOM.drawText(context.method_51448(), text, (double)x, (double)y, color, shadow);
               return;
            }
         }

         if ((Boolean)org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.contrast.getValue()) {
            VANILLA.renderCsgoLayer(context, context.method_51448(), text, x, y, color.getRGB());
         } else {
            VANILLA.renderTextNoLayer(context, context.method_51448(), text, x, y, color.getRGB(), shadow);
         }

      }
   }

   public static void doOneHUd(class_332 context, String text, float x, float y, Color color, boolean shadow) {
      if ((Boolean)org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.glow.getValue()) {
         renderGlow(context.method_51448(), text, x, y, color);
      } else {
         if (org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.isEnabled()) {
            ensureCustom();
            if (CUSTOM != null) {
               CUSTOM.drawText(context.method_51448(), text, (double)x, (double)y, color, shadow);
               return;
            }
         }

         VANILLA.renderTextNoLayer(context, context.method_51448(), text, x, y, color.getRGB(), shadow);
      }
   }

   public static void doOneTextNoGlow(class_332 context, String text, float x, float y, Color color, boolean shadow) {
      if (org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.isEnabled()) {
         ensureCustom();
         if (CUSTOM != null) {
            CUSTOM.drawText(context.method_51448(), text, (double)x, (double)y, color, shadow);
            return;
         }
      }

      if ((Boolean)org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.contrast.getValue()) {
         VANILLA.renderCsgoLayer(context, context.method_51448(), text, x, y, color.getRGB());
      } else {
         VANILLA.renderTextNoLayer(context, context.method_51448(), text, x, y, color.getRGB(), shadow);
      }

   }

   public static void doOneVanilla(class_332 context, String text, float x, float y, Color color, boolean shadow) {
      VANILLA.renderTextNoLayer(context, context.method_51448(), text, x, y, color.getRGB(), shadow);
   }

   public static float getTextWidth(String text) {
      if (org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.isEnabled()) {
         ensureCustom();
         if (CUSTOM != null) {
            return CUSTOM.getStringWidth(text);
         }
      }

      return (float)mc.field_1772.method_1727(text);
   }

   public static float getTextHeight(String text) {
      if (org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.isEnabled()) {
         ensureCustom();
         if (CUSTOM != null) {
            return CUSTOM.getFontHeight();
         }
      }

      Objects.requireNonNull(mc.field_1772);
      return 9.0F;
   }

   public static void refresh() {
      try {
         if (CUSTOM != null) {
            CUSTOM.close();
         }

         CUSTOM = create((float)((Number)org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.fontSize.getValue()).intValue());
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   private static void ensureCustom() {
      if (CUSTOM == null) {
         refresh();
      }

   }
}
