package org.itsmahir.pagon.api.gui.render;

import java.awt.Color;
import net.minecraft.class_332;
import org.itsmahir.pagon.api.gui.context.Context;
import org.itsmahir.pagon.api.gui.helpers.MouseHelper;
import org.itsmahir.pagon.api.gui.helpers.Rect;
import org.itsmahir.pagon.api.gui.widget.impl.BindWidget;
import org.itsmahir.pagon.api.gui.widget.impl.BooleanWidget;
import org.itsmahir.pagon.api.gui.widget.impl.ColorWidget;
import org.itsmahir.pagon.api.gui.widget.impl.ComboBoxWidget;
import org.itsmahir.pagon.api.gui.widget.impl.SliderWidget;
import org.itsmahir.pagon.api.gui.widget.impl.TextEntryWidget;
import org.itsmahir.pagon.impl.gui.components.module.FeatureButton;

public interface IRenderer {
   void renderBackground(Context var1);

   void renderLast(Context var1);

   void renderFrameTitle(Context var1, Rect var2, MouseHelper var3, String var4, boolean var5);

   void renderFrameOutline(Context var1, Rect var2, MouseHelper var3);

   void renderFrame(Context var1, Rect var2, MouseHelper var3);

   void renderBooleanWidget(BooleanWidget var1, Context var2, Rect var3, MouseHelper var4);

   void renderBindWidget(BindWidget var1, Context var2, Rect var3, MouseHelper var4);

   void renderFeatureButton(FeatureButton var1, Context var2, Rect var3, MouseHelper var4);

   void renderComboBox(ComboBoxWidget var1, Context var2, Rect var3, MouseHelper var4);

   void renderSliderWidget(SliderWidget var1, Context var2, Rect var3, Rect var4, MouseHelper var5);

   void renderColorWidget(ColorWidget var1, Context var2, boolean var3, Rect var4, Rect var5, Rect var6, Rect var7, Rect var8, Rect var9);

   void renderStringWidget(TextEntryWidget var1, Context var2, Rect var3, MouseHelper var4);

   int getTextWidth(String var1);

   float getTextWidthFloat(String var1);

   int getTextHeight(String var1);

   void renderText(class_332 var1, String var2, float var3, float var4, Color var5, boolean var6);

   void renderRect(Rect var1, Color var2, Color var3, RectMode var4, Context var5);

   void scissorRect(Rect var1);

   public static enum RectMode {
      Fill,
      FillHorizontal,
      Outline,
      OutlineNoRasturize;

      // $FF: synthetic method
      private static RectMode[] $values() {
         return new RectMode[]{Fill, FillHorizontal, Outline, OutlineNoRasturize};
      }
   }
}
