package org.itsmahir.pagon.api.gui.widget.impl;

import net.minecraft.class_3532;
import org.itsmahir.pagon.api.gui.component.IComponent;
import org.itsmahir.pagon.api.gui.context.Context;
import org.itsmahir.pagon.api.gui.helpers.MouseHelper;
import org.itsmahir.pagon.api.gui.helpers.Rect;
import org.itsmahir.pagon.api.gui.widget.IWidget;

public class SliderWidget implements IWidget<Number>, IComponent {
   int scroll = 1;
   String title;
   public Number value;
   public Number min;
   public Number max;
   boolean dragging;
   boolean locked = true;
   Rect dims;
   Rect slider;

   public SliderWidget(String title, Number number, Number min, Number max) {
      this.title = title;
      this.value = number;
      this.min = min;
      this.max = max;
      this.dims = new Rect(0, 0, 0, 0);
      this.slider = new Rect(0, 0, 0, 0);
   }

   public Number getValue() {
      return this.value;
   }

   public void setValue(Number value) {
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

   public void draw(Context context, MouseHelper mouse) {
      this.getDims().setHeight(context.getMetrics().getButtonHeight());
      this.slider.setHeight(this.getDims().getHeight());
      this.slider.setX(this.getDims().getX());
      this.slider.setY(this.getDims().getY());
      double sliderWidth = this.normalize(this.getValue().doubleValue(), this.min.doubleValue(), this.max.doubleValue()) * (double)this.getDims().getWidth();
      this.slider.setWidth((int)sliderWidth);
      if (this.dragging) {
         Number newVal = 0;
         Number difference = this.max.doubleValue() - this.min.doubleValue();
         Number var7 = this.min.doubleValue() + class_3532.method_15350(this.normalize((double)(mouse.getX() - this.getDims().getX()), (double)0.0F, (double)this.getDims().getWidth()), (double)0.0F, (double)1.0F) * difference.doubleValue();
         this.setValue((Number)var7);
      }

      context.getRenderer().renderSliderWidget(this, context, this.getDisplayDims(), this.slider, mouse);
   }

   public void click(Context context, MouseHelper mouse, int button) {
      if (this.getDims().collideWithMouse(mouse) && button == 0) {
         this.dragging = true;
      }

   }

   public void release(Context context, MouseHelper mouse, int state) {
      this.dragging = false;
   }

   public void key(Context context, int key, char character) {
      if (key == 341) {
         this.locked = true;
      }

   }

   public void charTyped(Context context, char character) {
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

   double normalize(double value, double min, double max) {
      return (value - min) / (max - min);
   }

   public int getLevel() {
      return 3;
   }
}
