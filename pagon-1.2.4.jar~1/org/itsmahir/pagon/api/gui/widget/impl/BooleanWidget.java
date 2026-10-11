package org.itsmahir.pagon.api.gui.widget.impl;

import java.awt.Color;
import org.itsmahir.pagon.api.gui.component.IComponent;
import org.itsmahir.pagon.api.gui.context.Context;
import org.itsmahir.pagon.api.gui.helpers.MouseHelper;
import org.itsmahir.pagon.api.gui.helpers.Rect;
import org.itsmahir.pagon.api.gui.widget.IWidget;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.render.animation.Easing;
import org.itsmahir.pagon.api.utils.render.animation.type.DynamicColorAnimation;

public class BooleanWidget implements IWidget<Boolean>, IComponent {
   String title;
   Rect dims;
   boolean value;
   public DynamicColorAnimation animation;

   public BooleanWidget(String title, Rect dims) {
      this.title = title;
      this.dims = dims;
      this.value = true;
   }

   public void draw(Context context, MouseHelper mouse) {
      this.getDims().setHeight(context.getMetrics().getButtonHeight());
      Color color = this.getValue() ? context.getColorScheme().getMainColor(0) : context.getColorScheme().getSecondaryBackgroundColor();
      if (this.getDisplayDims().collideWithMouse(mouse)) {
         if (!this.getValue()) {
            color = ColorUtil.newAlpha(Color.WHITE, 10);
         }

         color = ColorUtil.brighten(color, (double)0.1F);
      }

      if (this.animation == null) {
         this.animation = new DynamicColorAnimation(color, Easing.LINEAR, 300L);
      }

      this.animation.gotoColor(color);
      context.getRenderer().renderBooleanWidget(this, context, this.getDisplayDims(), mouse);
   }

   public void click(Context context, MouseHelper mouse, int button) {
      if (this.getDisplayDims().collideWithMouse(mouse) && button == 0) {
         this.setValue(!this.getValue());
      }

   }

   public void release(Context context, MouseHelper mouse, int state) {
   }

   public void key(Context context, int key, char character) {
   }

   public void charTyped(Context context, char character) {
   }

   public int getLevel() {
      return 3;
   }

   public Rect getDims() {
      return this.dims;
   }

   public boolean isDraggable() {
      return false;
   }

   public boolean isActive() {
      return true;
   }

   public Boolean getValue() {
      return this.value;
   }

   public void setValue(Boolean value) {
      this.value = value;
   }

   public String getTitle() {
      return this.title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public Rect getDisplayDims() {
      return this.getDims();
   }
}
