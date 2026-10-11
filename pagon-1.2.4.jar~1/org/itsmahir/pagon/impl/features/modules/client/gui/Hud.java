package org.itsmahir.pagon.impl.features.modules.client.gui;

import java.awt.Color;
import java.util.Objects;
import net.minecraft.class_332;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.gui.context.Context;
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
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.color.RainbowUtil;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.PagonMod;
import org.itsmahir.pagon.impl.gui.components.module.FeatureButton;
import org.itsmahir.pagon.impl.gui.components.value.EditorButtonComponent;

public class Hud extends Module implements IColorScheme, IMetrics, IRenderer {
   public static Hud INSTANCE;
   public Value<Boolean> editorButton = (new ValueBuilder<Boolean>()).withDescriptor("").withValue(false).withComponent(new EditorButtonComponent(new Rect(0, 0, 0, 0))).register(this);
   public Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode", "colorMode").withValue("Static").withModes("Static", "Step", "TriStep", "Rainbow").withAction((s) -> this.handlePage((String)s.getValue())).register(this);
   public Value<Number> saturation = (new ValueBuilder<Number>()).withDescriptor("Saturation").withValue(108).withRange(1, 255).register(this);
   public Value<Number> brightness = (new ValueBuilder<Number>()).withDescriptor("Brightness").withValue(255).withRange(1, 255).register(this);
   public Value<Number> rainbowLength = (new ValueBuilder<Number>()).withDescriptor("Rainbow Length").withValue(35).withRange(1, 100).register(this);
   public Value<Number> rainbowSpeed = (new ValueBuilder<Number>()).withDescriptor("Rainbow Speed").withValue(1).withPlaces(1).withRange(0.1, 4).register(this);
   public Value<Number> stepLength = (new ValueBuilder<Number>()).withDescriptor("Step Length").withValue(0.1).withRange(0.1, 2).register(this);
   public Value<Number> stepSpeed = (new ValueBuilder<Number>()).withDescriptor("Step Speed").withValue(1).withRange(1, 10).withPlaces(1).register(this);
   Value<PagonColor> mainColor = (new ValueBuilder<PagonColor>()).withDescriptor("Main Color").withValue(new PagonColor(0, 150, 255, true)).register(this);
   Value<PagonColor> stepColor = (new ValueBuilder<PagonColor>()).withDescriptor("Step Color").withValue(new PagonColor(0, 150, 255, true)).register(this);
   Value<PagonColor> endColor = (new ValueBuilder<PagonColor>()).withDescriptor("End Color").withValue(new PagonColor(0, 150, 255, true)).register(this);

   public Hud() {
      super("Hud", Feature.Category.Client);
      INSTANCE = this;
      this.handlePage("Static");
   }

   public void handlePage(String page) {
      this.mainColor.setActive(true);
      this.mode.setActive(true);
      this.saturation.setActive(page.equals("Rainbow"));
      this.brightness.setActive(page.equals("Rainbow"));
      this.rainbowSpeed.setActive(page.equals("Rainbow"));
      this.rainbowLength.setActive(page.equals("Rainbow"));
      this.stepLength.setActive(page.equals("Step") || page.equals("TriStep"));
      this.stepSpeed.setActive(page.equals("Step") || page.equals("TriStep"));
      this.stepColor.setActive(page.equals("Step") || page.equals("TriStep"));
      this.endColor.setActive(page.equals("TriStep"));
   }

   public static Color getTextColor(int y) {
      switch ((String)INSTANCE.mode.getValue()) {
         case "Step":
            double roundY = Math.cos(Math.toRadians((double)((float)y / ((Number)INSTANCE.stepLength.getValue()).floatValue()) + (double)System.currentTimeMillis() * 0.1 * (double)((Number)INSTANCE.stepSpeed.getValue()).floatValue()));
            return ColorUtil.interpolate((float)MathUtil.normalize(roundY, (double)-1.0F, (double)1.0F), ((PagonColor)INSTANCE.mainColor.getValue()).getColor(), ((PagonColor)INSTANCE.stepColor.getValue()).getColor());
         case "TriStep":
            float realOffset = (float)(Math.toRadians((double)((float)y / (((Number)INSTANCE.stepLength.getValue()).floatValue() * 2.0F))) + (double)(System.currentTimeMillis() - PagonMod.START_TIME) * 1.0E-4 * (double)(((Number)INSTANCE.stepSpeed.getValue()).floatValue() * 2.0F));
            return ColorUtil.realInterp(realOffset % 1.5F, ((PagonColor)INSTANCE.mainColor.getValue()).getColor(), ((PagonColor)INSTANCE.stepColor.getValue()).getColor(), ((PagonColor)INSTANCE.endColor.getValue()).getColor());
         case "Rainbow":
            return RainbowUtil.effect((long)(-((double)y * ((Number)INSTANCE.rainbowLength.getValue()).doubleValue() * (double)10000.0F)), ((Number)INSTANCE.saturation.getValue()).floatValue() / 255.0F, ((Number)INSTANCE.brightness.getValue()).floatValue() / 255.0F);
         case "Static":
            return ((PagonColor)INSTANCE.mainColor.getValue()).getColor();
         default:
            return ((PagonColor)INSTANCE.mainColor.getValue()).getColor();
      }
   }

   public void registerGUI() {
      HudEditorGUI.INSTANCE.updateGUI(this, this, this);
   }

   public void renderBackground(Context context) {
      this.renderRect(new Rect(0, 0, context.getScaledResolution().getScaledWidth(), context.getScaledResolution().getScaledHeight()), ((PagonColor)ClickGui.INSTANCE.gradientTop.getValue()).getColor(), ((PagonColor)ClickGui.INSTANCE.gradentBottom.getValue()).getColor(), IRenderer.RectMode.Fill, context);
   }

   public void renderLast(Context context) {
      ClickGui.INSTANCE.renderLast(context);
   }

   public void renderFrameTitle(Context context, Rect rect, MouseHelper mouse, String title, boolean open) {
      ClickGui.INSTANCE.renderFrameTitle(context, rect, mouse, title, open);
   }

   public void renderFrameOutline(Context context, Rect rect, MouseHelper mouse) {
      ClickGui.INSTANCE.renderFrameOutline(context, rect, mouse);
   }

   public void renderFrame(Context context, Rect rect, MouseHelper mouse) {
      ClickGui.INSTANCE.renderFrame(context, rect, mouse);
   }

   public void renderBooleanWidget(BooleanWidget widget, Context context, Rect rect, MouseHelper mouse) {
      ClickGui.INSTANCE.renderBooleanWidget(widget, context, rect, mouse);
   }

   public void renderBindWidget(BindWidget widget, Context context, Rect rect, MouseHelper mouse) {
      ClickGui.INSTANCE.renderBindWidget(widget, context, rect, mouse);
   }

   public void renderFeatureButton(FeatureButton widget, Context context, Rect rect, MouseHelper mouse) {
      ClickGui.INSTANCE.renderFeatureButton(widget, context, rect, mouse);
   }

   public void renderComboBox(ComboBoxWidget widget, Context context, Rect rect, MouseHelper mouseHelper) {
      ClickGui.INSTANCE.renderComboBox(widget, context, rect, mouseHelper);
   }

   public void renderSliderWidget(SliderWidget widget, Context context, Rect rect, Rect sliderRect, MouseHelper mouse) {
      ClickGui.INSTANCE.renderSliderWidget(widget, context, rect, sliderRect, mouse);
   }

   public float getTextWidthFloat(String text) {
      return ClickGui.INSTANCE.getTextWidthFloat(text);
   }

   public void renderColorWidget(ColorWidget widget, Context context, boolean open, Rect headerRect, Rect dims, Rect container, Rect alphaSlider, Rect hueSlider, Rect colorSquare) {
      ClickGui.INSTANCE.renderColorWidget(widget, context, open, headerRect, dims, container, alphaSlider, hueSlider, colorSquare);
   }

   public void renderStringWidget(TextEntryWidget widget, Context context, Rect rect, MouseHelper mouse) {
      ClickGui.INSTANCE.renderStringWidget(widget, context, rect, mouse);
   }

   public int getTextWidth(String text) {
      return mc.field_1772.method_1727(text);
   }

   public int getTextHeight(String text) {
      Objects.requireNonNull(mc.field_1772);
      return 9;
   }

   public void renderText(class_332 context, String text, float x, float y, Color color, boolean shadow) {
      ClickGui.INSTANCE.renderText(context, text, x, y, color, shadow);
   }

   public void renderRect(Rect rect, Color inputTop, Color inputBottom, IRenderer.RectMode mode, Context context) {
      ClickGui.INSTANCE.renderRect(rect, inputTop, inputBottom, mode, context);
   }

   public void scissorRect(Rect dims) {
      ClickGui.INSTANCE.scissorRect(dims);
   }

   public Color getMainColor(int pos) {
      return ClickGui.INSTANCE.getMainColor(pos);
   }

   public Color getOutlineColor() {
      return ClickGui.INSTANCE.getOutlineColor();
   }

   public Color getButtonColor() {
      return ClickGui.INSTANCE.getButtonColor();
   }

   public Color getBackgroundColor() {
      return ClickGui.INSTANCE.getBackgroundColor();
   }

   public Color getSecondaryBackgroundColor() {
      return ClickGui.INSTANCE.getSecondaryBackgroundColor();
   }

   public Color getTertiaryBackgroundColor() {
      return ClickGui.INSTANCE.getTertiaryBackgroundColor();
   }

   public Color getTextColor() {
      return ClickGui.INSTANCE.getTextColor();
   }

   public Color getTextColorHighlight() {
      return ClickGui.INSTANCE.getTextColorHighlight();
   }

   public Color getTextColorActive() {
      return ClickGui.INSTANCE.getTextColorActive();
   }

   public boolean doesTextShadow() {
      return ClickGui.INSTANCE.doesTextShadow();
   }

   public int getSpacing() {
      return ClickGui.INSTANCE.getSpacing();
   }

   public int getBetweenSpacing() {
      return ClickGui.INSTANCE.getBetweenSpacing();
   }

   public int getSettingSpacing() {
      return ClickGui.INSTANCE.getSettingSpacing();
   }

   public int getFrameWidth() {
      return ClickGui.INSTANCE.getFrameWidth();
   }

   public int getButtonHeight() {
      return ClickGui.INSTANCE.getButtonHeight();
   }

   public int getFrameHeight() {
      return ClickGui.INSTANCE.getFrameHeight();
   }

   public String getDescription() {
      return "Hud: edit hud colors and open the hud editor";
   }
}
