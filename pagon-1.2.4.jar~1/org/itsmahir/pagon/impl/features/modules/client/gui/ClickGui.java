package org.itsmahir.pagon.impl.features.modules.client.gui;

import java.awt.Color;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;
import net.minecraft.class_124;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.gui.GUI;
import org.itsmahir.pagon.api.gui.context.Context;
import org.itsmahir.pagon.api.gui.font.Fonts;
import org.itsmahir.pagon.api.gui.helpers.MouseHelper;
import org.itsmahir.pagon.api.gui.helpers.Rect;
import org.itsmahir.pagon.api.gui.hudeditor.HudEditorGUI;
import org.itsmahir.pagon.api.gui.render.IRenderer;
import org.itsmahir.pagon.api.gui.theme.IColorScheme;
import org.itsmahir.pagon.api.gui.theme.IMetrics;
import org.itsmahir.pagon.api.gui.widget.impl.BindWidget;
import org.itsmahir.pagon.api.gui.widget.impl.BooleanWidget;
import org.itsmahir.pagon.api.gui.widget.impl.ColorWidget;
import org.itsmahir.pagon.api.gui.widget.impl.ComboBoxWidget;
import org.itsmahir.pagon.api.gui.widget.impl.SliderWidget;
import org.itsmahir.pagon.api.gui.widget.impl.TextEntryWidget;
import org.itsmahir.pagon.api.management.SoundManager;
import org.itsmahir.pagon.api.management.shaders.ShaderManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.StringUtils;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.ScaledResolution;
import org.itsmahir.pagon.api.utils.render.animation.Easing;
import org.itsmahir.pagon.api.utils.render.animation.type.DynamicColorAnimation;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.gui.components.module.FeatureButton;
import org.ladysnake.satin.api.managed.ManagedShaderEffect;

public class ClickGui extends Module implements IColorScheme, IMetrics, IRenderer {
   public static ClickGui INSTANCE;
   Value<PagonColor> mainColor = (new ValueBuilder<PagonColor>()).withDescriptor("Color").withValue(new PagonColor(64, 150, 255)).register(this);
   Value<PagonColor> buttonColor = (new ValueBuilder<PagonColor>()).withDescriptor("Button").withValue(new PagonColor(64, 150, 255, 0)).register(this);
   Value<PagonColor> textColor = (new ValueBuilder<PagonColor>()).withDescriptor("Text Color").withValue(new PagonColor(255, 255, 255)).register(this);
   Value<PagonColor> disabledText = (new ValueBuilder<PagonColor>()).withDescriptor("Disabled Text").withValue(new PagonColor(140, 140, 160)).register(this);
   Value<PagonColor> outlineColor = (new ValueBuilder<PagonColor>()).withDescriptor("Outline").withValue(new PagonColor(64, 150, 255, 120)).register(this);
   Value<Number> scale = (new ValueBuilder<Number>()).withDescriptor("Scale").withValue(1).withRange((double)0.5F, 2).withPlaces(1).register(this);
   float cachedScale = 1.0F;
   Value<PagonColor> gradientTop = (new ValueBuilder<PagonColor>()).withDescriptor("Gradient Top").withValue(new PagonColor(30, 60, 120, 40)).register(this);
   Value<PagonColor> gradentBottom = (new ValueBuilder<PagonColor>()).withDescriptor("Gradient Bottom").withValue(new PagonColor(20, 60, 120, 80)).register(this);
   Value<Boolean> blurEnabled = (new ValueBuilder<Boolean>()).withDescriptor("Blur").withValue(true).register(this);
   Value<Number> blurRadius;
   Value<Boolean> soundEnabled;
   Value<Number> soundVolume;
   String lastDescriptionText;
   String lastHoveredModule;
   long lastHoverTime;
   DynamicColorAnimation colorAnimation;
   boolean hasDescriptionToRender;
   boolean hasHovered;

   public ClickGui() {
      super("UI", Feature.Category.Client);
      this.blurRadius = (new ValueBuilder<Number>()).withDescriptor("Blur Radius").withValue(12).withRange(1, 30).withPlaces(0).withParent(this.blurEnabled).withParentEnabled(true).register(this);
      this.soundEnabled = (new ValueBuilder<Boolean>()).withDescriptor("Sound").withValue(true).register(this);
      this.soundVolume = (new ValueBuilder<Number>()).withDescriptor("Volume").withValue((double)0.5F).withRange(0, 1).withPlaces(1).withParent(this.soundEnabled).withParentEnabled(true).register(this);
      this.lastDescriptionText = "";
      this.lastHoveredModule = "";
      this.lastHoverTime = 0L;
      this.colorAnimation = new DynamicColorAnimation(Color.WHITE, Easing.CUBIC_IN_OUT, 300L);
      this.hasDescriptionToRender = false;
      this.hasHovered = false;
      this.getBind().setKey(344);
      INSTANCE = this;
   }

   public void onEnable() {
      if (!NullUtils.nullCheck()) {
         super.onEnable();
         this.cachedScale = ((Number)this.scale.getValue()).floatValue();
         org.itsmahir.pagon.impl.gui.ClickGui.INSTANCE.enterGui(this, this, this);
      }
   }

   @SubscribeEvent
   public void onTick(TickEvent.ClientTickEvent event) {
      if (!(mc.field_1755 instanceof org.itsmahir.pagon.impl.gui.ClickGui) && !(mc.field_1755 instanceof HudEditorGUI)) {
         this.setEnabled(false);
      }

   }

   public void renderBackground(Context context) {
      if ((Boolean)this.blurEnabled.getValue() && mc.field_1755 instanceof org.itsmahir.pagon.impl.gui.ClickGui) {
         ManagedShaderEffect blur = ShaderManager.INSTANCE.getBlurShaderEffect();
         blur.setUniformValue("BlurRadius", ((Number)this.blurRadius.getValue()).floatValue());
         blur.render(context.getPartialTicks());
      }

      this.renderRect(new Rect(0, 0, context.getScaledResolution().getScaledWidth(), context.getScaledResolution().getScaledHeight()), ((PagonColor)this.gradientTop.getValue()).getColor(), ((PagonColor)this.gradentBottom.getValue()).getColor(), IRenderer.RectMode.Fill, context);
   }

   public void renderLast(Context context) {
      if (this.hasDescriptionToRender) {
         this.colorAnimation.gotoColor(Color.WHITE);
      } else {
         this.colorAnimation.gotoColor(ColorUtil.newAlpha(Color.WHITE, 0));
      }

      this.renderText(context.getDrawContext(), this.lastDescriptionText, (float)(context.getScaledResolution().getScaledWidth() - org.itsmahir.pagon.impl.gui.ClickGui.CONTEXT.getRenderer().getTextWidth(this.lastDescriptionText)) / 2.0F, 3.0F, this.colorAnimation.getColor(), org.itsmahir.pagon.impl.gui.ClickGui.CONTEXT.getColorScheme().doesTextShadow());
      if (!this.hasHovered) {
         this.lastHoveredModule = "";
      }

      this.hasHovered = false;
      this.hasDescriptionToRender = false;
   }

   public void renderFrameTitle(Context context, Rect rect, MouseHelper mouse, String title, boolean open) {
      Color main = context.getColorScheme().getMainColor(rect.getY());
      Color mainEnd = new Color(Math.max(0, main.getRed() - 25), Math.max(0, main.getGreen() - 25), Math.max(0, main.getBlue() - 25), main.getAlpha());
      this.renderRect(rect, mainEnd, main, IRenderer.RectMode.Fill, context);
      int centerY = (rect.getHeight() - this.getTextHeight(title)) / 2;
      this.renderText(context.getDrawContext(), title, (float)(rect.getX() + 4), (float)(rect.getY() + centerY + 1), context.getColorScheme().getTextColor(), context.getColorScheme().doesTextShadow());
   }

   public void renderFrameOutline(Context context, Rect rect, MouseHelper mouse) {
      this.renderRect(rect, this.getOutlineColor(), this.getOutlineColor(), IRenderer.RectMode.Outline, context);
   }

   public void renderFrame(Context context, Rect rect, MouseHelper mouse) {
      Color bg = context.getColorScheme().getBackgroundColor();
      Color bgBottom = new Color(Math.max(0, bg.getRed() - 8), Math.max(0, bg.getGreen() - 8), Math.max(0, bg.getBlue() - 8), bg.getAlpha());
      this.renderRect(rect, bg, bgBottom, IRenderer.RectMode.Fill, context);
      Rect topGlow = new Rect(rect.getX(), rect.getY(), rect.getWidth(), 1);
      this.renderRect(topGlow, new Color(255, 255, 255, 8), new Color(255, 255, 255, 8), IRenderer.RectMode.Fill, context);
   }

   public void renderBooleanWidget(BooleanWidget widget, Context context, Rect rect, MouseHelper mouse) {
      Color text = widget.getValue() ? this.getTextColor() : ((PagonColor)this.disabledText.getValue()).getColor();
      int centerY = (rect.getHeight() - context.getRenderer().getTextHeight(widget.getTitle())) / 2;
      context.getRenderer().renderText(context.getDrawContext(), widget.getTitle(), (float)(rect.getX() + 2), (float)(rect.getY() + centerY), text, context.getColorScheme().doesTextShadow());
   }

   public void renderBindWidget(BindWidget widget, Context context, Rect rect, MouseHelper mouse) {
      context.getRenderer().renderRect(rect, context.getColorScheme().getTertiaryBackgroundColor(), context.getColorScheme().getTertiaryBackgroundColor(), IRenderer.RectMode.Fill, context);
      context.getRenderer().renderRect(rect, context.getColorScheme().getOutlineColor(), context.getColorScheme().getOutlineColor(), IRenderer.RectMode.Outline, context);
      float centerY = (float)(rect.getHeight() - context.getRenderer().getTextHeight(widget.getTitle())) / 2.0F + 1.0F;
      String keyName = StringUtils.getKeyName(widget.getValue().getKey(), widget.getValue());
      StringBuilder dots = new StringBuilder();
      dots.append(".".repeat(Math.max(0, GUI.bindCounter)));
      String text = widget.isBinding() ? "Binding" + String.valueOf(dots) : "Bind: " + String.valueOf(class_124.field_1080) + keyName;
      context.getRenderer().renderText(context.getDrawContext(), text, (float)(rect.getX() + 2), (float)rect.getY() + centerY, context.getColorScheme().getTextColor(), context.getColorScheme().doesTextShadow());
   }

   public void renderFeatureButton(FeatureButton widget, Context context, Rect rect, MouseHelper mouse) {
      Color text = widget.getValue() ? this.getTextColor() : ((PagonColor)this.disabledText.getValue()).getColor();
      this.renderRect(rect, ((PagonColor)this.buttonColor.getValue()).getColor(), ((PagonColor)this.buttonColor.getValue()).getColor(), IRenderer.RectMode.Fill, context);
      this.renderRect(rect, widget.animation.getColor(), widget.animation.getColor(), IRenderer.RectMode.Fill, context);
      int xOffset = 0;
      if (rect.collideWithMouse(mouse)) {
         if (!widget.getFeature().getDescription().isEmpty() && !this.hasDescriptionToRender) {
            String desc = widget.getFeature().getDescription();
            if (desc.contains(": ")) {
               desc = desc.substring(desc.indexOf(": ") + 2);
            }

            this.lastDescriptionText = desc;
            this.hasDescriptionToRender = true;
         }

         xOffset = 1;
         if (!Objects.equals(this.lastHoveredModule, widget.getFeature().getName()) && !this.hasHovered) {
            if ((Boolean)this.soundEnabled.getValue()) {
               long now = System.currentTimeMillis();
               if (now - this.lastHoverTime > 100L) {
                  SoundManager.INSTANCE.play(SoundManager.INSTANCE.CLICK_SOUND, ((Number)this.soundVolume.getValue()).floatValue());
                  this.lastHoverTime = now;
               }
            }

            this.lastHoveredModule = widget.getFeature().getName();
         }

         this.hasHovered = true;
      }

      int centerY = (rect.getHeight() - this.getTextHeight(widget.getTitle())) / 2 + 1;
      float leftX = (float)(rect.getX() + 2 + xOffset);
      float centerX = (float)rect.getX() + (float)rect.getWidth() / 2.0F - (float)this.getTextWidth(widget.getTitle()) / 2.0F;
      float titleX = leftX + (centerX - leftX) * widget.titleOffsetAnim.getAnimationValue();
      this.renderText(context.getDrawContext(), widget.getTitle(), titleX, (float)(rect.getY() + centerY), text, context.getColorScheme().doesTextShadow());
      String openStr = widget.open ? "-" : "+";
      int rightX = rect.getX() + (rect.getWidth() - this.getTextWidth(openStr) - 1);
      this.renderText(context.getDrawContext(), openStr, (float)rightX, (float)(rect.getY() + centerY), text, context.getColorScheme().doesTextShadow());
   }

   public void renderComboBox(ComboBoxWidget widget, Context context, Rect rect, MouseHelper mouseHelper) {
      this.renderRect(rect, context.getColorScheme().getTertiaryBackgroundColor(), context.getColorScheme().getTertiaryBackgroundColor(), IRenderer.RectMode.Fill, context);
      String var10000 = widget.getTitle();
      String text = var10000 + ": " + String.valueOf(class_124.field_1080) + widget.getValue();
      int centerY = (rect.getHeight() - this.getTextHeight(text)) / 2 + 1;
      this.renderText(context.getDrawContext(), text, (float)(rect.getX() + 2), (float)(rect.getY() + centerY), context.getColorScheme().getTextColor(), context.getColorScheme().doesTextShadow());
   }

   public void renderSliderWidget(SliderWidget widget, Context context, Rect rect, Rect sliderRect, MouseHelper mouse) {
      this.renderRect(rect, context.getColorScheme().getTertiaryBackgroundColor(), context.getColorScheme().getTertiaryBackgroundColor(), IRenderer.RectMode.Fill, context);
      sliderRect.setHeight(1);
      sliderRect.setY(rect.getY() + rect.getHeight() - sliderRect.getHeight());
      this.renderRect(sliderRect, context.getColorScheme().getMainColor(0), context.getColorScheme().getMainColor(0), IRenderer.RectMode.Fill, context);
      this.renderRect(sliderRect, context.getColorScheme().getOutlineColor(), context.getColorScheme().getOutlineColor(), IRenderer.RectMode.OutlineNoRasturize, context);
      DecimalFormat df = new DecimalFormat("#.####");
      df.setRoundingMode(RoundingMode.CEILING);
      String var10000 = widget.getTitle();
      String text = var10000 + ": " + String.valueOf(class_124.field_1080) + df.format(widget.getValue().doubleValue());
      int centerY = (rect.getHeight() - this.getTextHeight(text)) / 2 + 1;
      this.renderText(context.getDrawContext(), text, (float)(rect.getX() + 2), (float)(rect.getY() + centerY), context.getColorScheme().getTextColor(), context.getColorScheme().doesTextShadow());
   }

   public float getTextWidthFloat(String text) {
      return Fonts.getTextWidth(text);
   }

   public void renderColorWidget(ColorWidget widget, Context context, boolean open, Rect headerRect, Rect dims, Rect container, Rect alphaSlider, Rect hueSlider, Rect colorSquare) {
      int sliderWidth = 2;
      this.renderRect(open ? dims : headerRect, context.getColorScheme().getTertiaryBackgroundColor(), context.getColorScheme().getTertiaryBackgroundColor(), IRenderer.RectMode.Fill, context);
      String text = widget.getTitle();
      int centerY = (headerRect.getHeight() - this.getTextHeight(text)) / 2 + 1;
      int size = headerRect.getHeight() - 2;
      int rightX = headerRect.getX() + (headerRect.getWidth() - size - 1);
      Rect colorRect = new Rect(rightX, headerRect.getY() + 1, size, size);
      this.renderText(context.getDrawContext(), text, (float)(headerRect.getX() + 2), (float)(headerRect.getY() + centerY), context.getColorScheme().getTextColor(), context.getColorScheme().doesTextShadow());
      this.renderRect(colorRect, widget.getValue(), widget.getValue(), IRenderer.RectMode.Fill, context);
      this.renderRect(colorRect, this.getOutlineColor(), this.getOutlineColor(), IRenderer.RectMode.Outline, context);
      this.renderText(context.getDrawContext(), "S", (float)colorRect.getX() + ((float)colorRect.getWidth() / 2.0F - (float)this.getTextWidth("S") / 2.0F), (float)headerRect.getY() + 2.2F, widget.animation.getColor(), true);
      if (open) {
         int hueSegments = 30;

         for(int i = 0; i <= hueSegments; ++i) {
            double normal = MathUtil.normalize((double)i, (double)0.0F, (double)hueSegments);
            Color color = Color.getHSBColor((float)normal, 1.0F, 1.0F);
            double normal2 = MathUtil.normalize((double)(i + 1), (double)0.0F, (double)hueSegments);
            Color color2 = Color.getHSBColor((float)normal2, 1.0F, 1.0F);
            double curX = (double)hueSlider.getWidth() / ((double)hueSegments / (double)i);
            double width = (double)hueSlider.getWidth() / (double)hueSegments;
            double x = (double)hueSlider.getX() + curX;
            double y = (double)((float)hueSlider.getY() + (float)hueSlider.getHeight() / 2.0F - 3.0F);
            double actualWidth = Math.min(width, (double)hueSlider.getWidth() - curX);
            RenderUtil.renderGradient(context.getDrawContext().method_51448(), x, y, actualWidth, (double)6.0F, color.getRGB(), color2.getRGB(), true);
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
      this.renderRect(rect, context.getColorScheme().getOutlineColor(), context.getColorScheme().getOutlineColor(), IRenderer.RectMode.Outline, context);
      String renderText = widget.typing ? widget.getValue() + (GUI.typeCounter ? "" : "_") : widget.getValue();
      int centerY = (rect.getHeight() - this.getTextHeight(renderText)) / 2;
      this.renderText(context.getDrawContext(), renderText, (float)(rect.getX() + 2), (float)(rect.getY() + centerY + 1), context.getColorScheme().getTextColor(), context.getColorScheme().doesTextShadow());
   }

   public int getTextWidth(String text) {
      return (int)Fonts.getTextWidth(text);
   }

   public int getTextHeight(String text) {
      return (int)Fonts.getTextHeight(text);
   }

   public void renderText(class_332 context, String text, float x, float y, Color color, boolean shadow) {
      Fonts.doOneTextNoGlow(context, text, x, y, color, shadow);
   }

   public void renderRect(Rect rect, Color inputTop, Color inputBottom, IRenderer.RectMode mode, Context context) {
      if (mode == IRenderer.RectMode.Fill) {
         RenderUtil.renderGradient(context.getDrawContext().method_51448(), (double)rect.getX(), (double)rect.getY(), (double)rect.getWidth(), (double)rect.getHeight(), inputTop.getRGB(), inputBottom.getRGB(), false);
      }

      if (mode == IRenderer.RectMode.FillHorizontal) {
         RenderUtil.renderGradient(context.getDrawContext().method_51448(), (double)rect.getX(), (double)rect.getY(), (double)rect.getWidth(), (double)rect.getHeight(), inputTop.getRGB(), inputBottom.getRGB(), true);
      }

      if (mode == IRenderer.RectMode.Outline) {
         RenderUtil.renderOutline(context.getDrawContext().method_51448(), (double)rect.getX(), (double)rect.getY(), (double)rect.getWidth(), (double)rect.getHeight(), inputTop.getRGB(), true);
      }

      if (mode == IRenderer.RectMode.OutlineNoRasturize) {
         RenderUtil.renderOutline(context.getDrawContext().method_51448(), (double)rect.getX(), (double)rect.getY(), (double)rect.getWidth(), (double)rect.getHeight(), inputTop.getRGB(), false);
      }

   }

   public void scissorRect(Rect dims) {
      ScaledResolution sr = new ScaledResolution(mc);
      double factor = sr.getScaleFactor();
   }

   public Color getMainColor(int pos) {
      return ((PagonColor)this.mainColor.getValue()).getColor();
   }

   public Color getOutlineColor() {
      return ((PagonColor)this.outlineColor.getValue()).getColor();
   }

   public Color getButtonColor() {
      return new Color(0, 0, 0, 0);
   }

   public Color getBackgroundColor() {
      return ColorUtil.newAlpha(((PagonColor)this.mainColor.getValue()).getColor(), 60);
   }

   public Color getSecondaryBackgroundColor() {
      return new Color(0, 0, 0, 0);
   }

   public Color getTertiaryBackgroundColor() {
      return new Color(0, 0, 0, 0);
   }

   public Color getTextColor() {
      return ((PagonColor)this.textColor.getValue()).getColor();
   }

   public Color getTextColorHighlight() {
      return this.getTextColor();
   }

   public Color getTextColorActive() {
      return this.getTextColor();
   }

   public boolean doesTextShadow() {
      return (Boolean)org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.textShadow.getValue();
   }

   public int getSpacing() {
      return 1;
   }

   public int getBetweenSpacing() {
      return 1;
   }

   public int getSettingSpacing() {
      return this.getBetweenSpacing();
   }

   public int getFrameWidth() {
      return (int)(100.0F * this.cachedScale);
   }

   public int getButtonHeight() {
      return (int)(12.0F * this.cachedScale);
   }

   public int getFrameHeight() {
      return this.getButtonHeight() + 2;
   }

   public void registerGUI() {
      org.itsmahir.pagon.impl.gui.ClickGui.INSTANCE.updateGUI(this, this, this);
   }

   public String getDescription() {
      return "UI: Main pagon gui";
   }
}
