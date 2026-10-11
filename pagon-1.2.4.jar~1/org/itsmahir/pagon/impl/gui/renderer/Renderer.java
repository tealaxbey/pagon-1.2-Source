package org.itsmahir.pagon.impl.gui.renderer;

import java.awt.Color;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import org.itsmahir.pagon.api.gui.context.Context;
import org.itsmahir.pagon.api.gui.font.Fonts;
import org.itsmahir.pagon.api.gui.helpers.MouseHelper;
import org.itsmahir.pagon.api.gui.helpers.Rect;
import org.itsmahir.pagon.api.gui.render.IRenderer;
import org.itsmahir.pagon.api.gui.widget.impl.BindWidget;
import org.itsmahir.pagon.api.gui.widget.impl.BooleanWidget;
import org.itsmahir.pagon.api.gui.widget.impl.ColorWidget;
import org.itsmahir.pagon.api.gui.widget.impl.ComboBoxWidget;
import org.itsmahir.pagon.api.gui.widget.impl.SliderWidget;
import org.itsmahir.pagon.api.gui.widget.impl.TextEntryWidget;
import org.itsmahir.pagon.api.utils.StringUtils;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.features.modules.client.gui.ClickGui;
import org.itsmahir.pagon.impl.gui.components.module.FeatureButton;

public class Renderer implements IRenderer, IMinecraft {
   public void renderBackground(Context context) {
   }

   public void renderLast(Context context) {
   }

   public void renderFrameTitle(Context context, Rect rect, MouseHelper mouse, String title, boolean open) {
      Color main = context.getColorScheme().getMainColor(rect.getX() + rect.getY());
      Color mainEnd = new Color(Math.max(0, main.getRed() - 25), Math.max(0, main.getGreen() - 25), Math.max(0, main.getBlue() - 25), main.getAlpha());
      this.renderRect(rect, main, mainEnd, IRenderer.RectMode.Fill, context);
      Rect accentLine = new Rect(rect.getX() + 2, rect.getY() + rect.getHeight() - 1, rect.getWidth() - 4, 1);
      this.renderRect(accentLine, new Color(255, 255, 255, 50), new Color(255, 255, 255, 50), IRenderer.RectMode.Fill, context);
      int centerY = (rect.getHeight() - this.getTextHeight(title)) / 2 + 1;
      this.renderText(context.getDrawContext(), title, (float)(rect.getX() + 4), (float)(rect.getY() + centerY), context.getColorScheme().getTextColorHighlight(), context.getColorScheme().doesTextShadow());
      String openString = open ? "−" : "+";
      int rightX = rect.getX() + rect.getWidth() - this.getTextWidth(openString) - 4;
      this.renderText(context.getDrawContext(), openString, (float)rightX, (float)(rect.getY() + centerY), context.getColorScheme().getTextColorHighlight(), context.getColorScheme().doesTextShadow());
   }

   public void renderFrameOutline(Context context, Rect rect, MouseHelper mouse) {
      Color outline = ClickGui.INSTANCE.getOutlineColor();
      this.renderRect(rect, outline, outline, IRenderer.RectMode.Outline, context);
      Rect innerRect = new Rect(rect.getX() + 1, rect.getY() + 1, rect.getWidth() - 2, rect.getHeight() - 2);
      this.renderRect(innerRect, new Color(255, 255, 255, 12), new Color(255, 255, 255, 12), IRenderer.RectMode.Outline, context);
   }

   public void renderFrame(Context context, Rect rect, MouseHelper mouse) {
      Color bg = context.getColorScheme().getBackgroundColor();
      Color bgBottom = new Color(Math.max(0, bg.getRed() - 8), Math.max(0, bg.getGreen() - 8), Math.max(0, bg.getBlue() - 8), bg.getAlpha());
      this.renderRect(rect, bg, bgBottom, IRenderer.RectMode.Fill, context);
      Rect topGlow = new Rect(rect.getX(), rect.getY(), rect.getWidth(), 1);
      this.renderRect(topGlow, new Color(255, 255, 255, 8), new Color(255, 255, 255, 8), IRenderer.RectMode.Fill, context);
   }

   public void renderBooleanWidget(BooleanWidget widget, Context context, Rect rect, MouseHelper mouse) {
      Color color = widget.getValue() ? context.getColorScheme().getMainColor(rect.getX() + rect.getY()) : context.getColorScheme().getTertiaryBackgroundColor();
      Color color2 = widget.getValue() ? context.getColorScheme().getMainColor(rect.getX() + rect.getWidth() + rect.getY() + rect.getHeight()) : context.getColorScheme().getTertiaryBackgroundColor();
      this.renderRect(rect, color, color2, IRenderer.RectMode.Fill, context);
      int centerX = (rect.getWidth() - this.getTextWidth(widget.getTitle())) / 2;
      int centerY = (rect.getHeight() - this.getTextHeight(widget.getTitle())) / 2 + 1;
      this.renderText(context.getDrawContext(), widget.getTitle(), (float)(rect.getX() + centerX), (float)(rect.getY() + centerY), context.getColorScheme().getTextColor(), context.getColorScheme().doesTextShadow());
   }

   public void renderBindWidget(BindWidget widget, Context context, Rect rect, MouseHelper mouse) {
      this.renderRect(rect, context.getColorScheme().getTertiaryBackgroundColor(), context.getColorScheme().getTertiaryBackgroundColor(), IRenderer.RectMode.Fill, context);
      float centerY = (float)(rect.getHeight() - this.getTextHeight(widget.getTitle())) / 2.0F + 1.0F;
      String keyName = widget.getValue().getKey() != -1 ? StringUtils.getKeyName(widget.getValue().getKey(), widget.getValue()) : "NONE";
      String text = widget.isBinding() ? "Binding..." : "Bind: " + keyName;
      this.renderText(context.getDrawContext(), text, (float)(rect.getX() + 2), (float)rect.getY() + centerY, context.getColorScheme().getTextColor(), context.getColorScheme().doesTextShadow());
   }

   public void renderFeatureButton(FeatureButton widget, Context context, Rect rect, MouseHelper mouse) {
      Color color = widget.getValue() ? context.getColorScheme().getMainColor(0) : context.getColorScheme().getSecondaryBackgroundColor();
      this.renderRect(rect, color, color, IRenderer.RectMode.Fill, context);
      int centerY = (rect.getHeight() - this.getTextHeight(widget.getTitle())) / 2 + 1;
      this.renderText(context.getDrawContext(), widget.getTitle(), (float)(rect.getX() + 2), (float)(rect.getY() + centerY), context.getColorScheme().getTextColor(), context.getColorScheme().doesTextShadow());
   }

   public void renderComboBox(ComboBoxWidget widget, Context context, Rect rect, MouseHelper mouseHelper) {
      this.renderRect(rect, context.getColorScheme().getTertiaryBackgroundColor(), context.getColorScheme().getTertiaryBackgroundColor(), IRenderer.RectMode.Fill, context);
      String var10000 = widget.getTitle();
      String text = var10000 + ": " + widget.getValue();
      int centerX = (rect.getWidth() - this.getTextWidth(text)) / 2;
      int centerY = (rect.getHeight() - this.getTextHeight(text)) / 2 + 1;
      this.renderText(context.getDrawContext(), text, (float)(rect.getX() + centerX), (float)(rect.getY() + centerY), context.getColorScheme().getTextColor(), context.getColorScheme().doesTextShadow());
   }

   public void renderSliderWidget(SliderWidget widget, Context context, Rect rect, Rect sliderRect, MouseHelper mouse) {
      this.renderRect(rect, context.getColorScheme().getTertiaryBackgroundColor(), context.getColorScheme().getTertiaryBackgroundColor(), IRenderer.RectMode.Fill, context);
      this.renderRect(sliderRect, context.getColorScheme().getMainColor(0), context.getColorScheme().getMainColor(0), IRenderer.RectMode.Fill, context);
      DecimalFormat df = new DecimalFormat("#.##");
      df.setRoundingMode(RoundingMode.CEILING);
      String var10000 = widget.getTitle();
      String text = var10000 + ": " + df.format(widget.getValue().doubleValue());
      int centerX = (rect.getWidth() - this.getTextWidth(text)) / 2;
      int centerY = (rect.getHeight() - this.getTextHeight(text)) / 2 + 1;
      this.renderText(context.getDrawContext(), text, (float)(rect.getX() + centerX), (float)(rect.getY() + centerY), context.getColorScheme().getTextColor(), context.getColorScheme().doesTextShadow());
   }

   public void renderColorWidget(ColorWidget widget, Context context, boolean open, Rect headerRect, Rect dims, Rect container, Rect alphaSlider, Rect hueSlider, Rect colorSquare) {
      int sliderWidth = 2;
      this.renderRect(open ? dims : headerRect, context.getColorScheme().getTertiaryBackgroundColor(), context.getColorScheme().getTertiaryBackgroundColor(), IRenderer.RectMode.Fill, context);
      String text = widget.getTitle();
      int centerX = (headerRect.getWidth() - this.getTextWidth(text)) / 2;
      int centerY = (headerRect.getHeight() - this.getTextHeight(text)) / 2 + 1;
      String openString = open ? "-" : "+";
      int rightX = headerRect.getWidth() - this.getTextWidth(openString) - 2;
      this.renderText(context.getDrawContext(), text, (float)(headerRect.getX() + centerX), (float)(headerRect.getY() + centerY), context.getColorScheme().getTextColor(), context.getColorScheme().doesTextShadow());
      this.renderText(context.getDrawContext(), openString, (float)(headerRect.getX() + rightX), (float)(headerRect.getY() + centerY), widget.getValue(), context.getColorScheme().doesTextShadow());
      if (open) {
         int hueSegments = 30;

         for(int i = 0; i <= hueSegments; ++i) {
            double normal = MathUtil.normalize((double)i, (double)0.0F, (double)hueSegments);
            Color color = Color.getHSBColor((float)normal, 1.0F, 1.0F);
            double normal2 = MathUtil.normalize((double)(i + 1), (double)0.0F, (double)hueSegments);
            Color color2 = Color.getHSBColor((float)normal2, 1.0F, 1.0F);
            double curX = (double)hueSlider.getWidth() / ((double)hueSegments / (double)i);
            double width = (double)hueSlider.getWidth() / (double)hueSegments;
            Rect hueSegment = new Rect((int)((double)hueSlider.getX() + curX), (int)((float)hueSlider.getY() + (float)hueSlider.getHeight() / 2.0F - 3.0F), (int)Math.min(width + (double)1.0F, (double)hueSlider.getWidth() - curX), 6);
            this.renderRect(hueSegment, color, color2, IRenderer.RectMode.FillHorizontal, context);
         }

         float[] hsb = Color.RGBtoHSB(widget.getValue().getRed(), widget.getValue().getGreen(), widget.getValue().getBlue(), (float[])null);
         int hueOffset = (int)(hsb[0] * (float)hueSlider.getWidth());
         hueOffset = class_3532.method_15340(hueOffset, 0, hueSlider.getWidth());
         Rect huePicker = new Rect(hueSlider.getX() + hueOffset - sliderWidth / 2, hueSlider.getY(), sliderWidth, hueSlider.getHeight());
         this.renderRect(huePicker, new Color(255, 255, 255), new Color(255, 255, 255), IRenderer.RectMode.Fill, context);
         this.renderRect(colorSquare, new Color(255, 255, 255), Color.getHSBColor(hsb[0], 1.0F, 1.0F), IRenderer.RectMode.FillHorizontal, context);
         this.renderRect(colorSquare, new Color(0, 0, 0, 0), new Color(0, 0, 0, 255), IRenderer.RectMode.Fill, context);
         this.renderRect(colorSquare, new Color(0, 0, 0, 0), new Color(0, 0, 0, 255), IRenderer.RectMode.Fill, context);
         int pickerSize = 2;
         int pickerOffsetX = class_3532.method_15340((int)(hsb[1] * (float)colorSquare.getWidth()), 0, colorSquare.getWidth());
         int pickerOffsetY = class_3532.method_15340((int)(hsb[2] * (float)colorSquare.getHeight()), 0, colorSquare.getHeight());
         Rect pickerRect = new Rect(colorSquare.getX() + pickerOffsetX - pickerSize / 2, colorSquare.getY() + colorSquare.getHeight() - pickerOffsetY - pickerSize / 2, pickerSize, pickerSize);
         this.renderRect(pickerRect, new Color(255, 255, 255), new Color(255, 255, 255), IRenderer.RectMode.Fill, context);
         this.renderRect(pickerRect, new Color(0, 0, 0), new Color(0, 0, 0), IRenderer.RectMode.Outline, context);
         this.renderRect(alphaSlider, Color.WHITE, Color.WHITE, IRenderer.RectMode.Fill, context);
         this.renderRect(alphaSlider, ColorUtil.newAlpha(Color.getHSBColor(hsb[0], 1.0F, 1.0F), 24), ColorUtil.newAlpha(Color.getHSBColor(hsb[0], 1.0F, 1.0F), 255), IRenderer.RectMode.Fill, context);
         double alphaNormal = class_3532.method_15350(MathUtil.normalize((double)widget.getValue().getAlpha(), (double)0.0F, (double)255.0F), (double)0.0F, (double)1.0F);
         int alphaOffset = (int)(alphaNormal * (double)alphaSlider.getHeight());
         Rect alphaPicker = new Rect(alphaSlider.getX(), alphaSlider.getY() + alphaOffset - sliderWidth / 2, alphaSlider.getWidth(), sliderWidth);
         this.renderRect(alphaPicker, new Color(255, 255, 255), new Color(255, 255, 255), IRenderer.RectMode.Fill, context);
      }

   }

   public void renderStringWidget(TextEntryWidget widget, Context context, Rect rect, MouseHelper mouse) {
      this.renderRect(rect, context.getColorScheme().getTertiaryBackgroundColor(), context.getColorScheme().getTertiaryBackgroundColor(), IRenderer.RectMode.Fill, context);
      String renderText = widget.typing ? widget.getValue() + "|" : widget.getValue();
      int centerY = (rect.getHeight() - this.getTextHeight(renderText)) / 2;
      this.renderText(context.getDrawContext(), renderText, (float)(rect.getX() + 2), (float)(rect.getY() + centerY + 1), context.getColorScheme().getTextColor(), context.getColorScheme().doesTextShadow());
   }

   public int getTextWidth(String text) {
      return mc.field_1772.method_1727(text);
   }

   public float getTextWidthFloat(String text) {
      return (float)mc.field_1772.method_1727(text);
   }

   public int getTextHeight(String text) {
      return 9;
   }

   public void renderText(class_332 context, String text, float x, float y, Color color, boolean shadow) {
      Fonts.VANILLA.drawText(context, context.method_51448(), text, x, y, color.getRGB(), shadow);
   }

   public void renderRect(Rect rect, Color color, Color bottom, IRenderer.RectMode mode, Context context) {
      if (mode == IRenderer.RectMode.Fill) {
         RenderUtil.renderGradient(context.getDrawContext().method_51448(), (double)rect.getX(), (double)rect.getY(), (double)rect.getWidth(), (double)rect.getHeight(), color.getRGB(), bottom.getRGB(), false);
      }

      if (mode == IRenderer.RectMode.FillHorizontal) {
         RenderUtil.renderGradient(context.getDrawContext().method_51448(), (double)rect.getX(), (double)rect.getY(), (double)rect.getWidth(), (double)rect.getHeight(), color.getRGB(), bottom.getRGB(), true);
      }

      if (mode == IRenderer.RectMode.Outline) {
         RenderUtil.renderOutline(context.getDrawContext().method_51448(), (double)rect.getX(), (double)rect.getY(), (double)rect.getWidth(), (double)rect.getHeight(), color.getRGB(), true);
      }

      if (mode == IRenderer.RectMode.OutlineNoRasturize) {
         RenderUtil.renderOutline(context.getDrawContext().method_51448(), (double)rect.getX(), (double)rect.getY(), (double)rect.getWidth(), (double)rect.getHeight(), color.getRGB(), false);
      }

   }

   public void scissorRect(Rect dims) {
   }
}
