package org.itsmahir.pagon.api.gui.widget.impl;

import org.itsmahir.pagon.api.gui.component.IComponent;
import org.itsmahir.pagon.api.gui.context.Context;
import org.itsmahir.pagon.api.gui.helpers.MouseHelper;
import org.itsmahir.pagon.api.gui.helpers.Rect;
import org.itsmahir.pagon.api.gui.widget.IWidget;

public class ComboBoxWidget implements IComponent, IWidget<String> {
   String title;
   String[] modes;
   String theValue;
   Rect dims;

   public ComboBoxWidget(String title, String theValue, String[] modes, Rect dims) {
      this.title = title;
      this.modes = modes;
      this.theValue = theValue;
      this.dims = dims;
   }

   public void draw(Context context, MouseHelper mouse) {
      this.getDims().setHeight(context.getMetrics().getButtonHeight());
      context.getRenderer().renderComboBox(this, context, this.getDisplayDims(), mouse);
   }

   public void click(Context context, MouseHelper mouse, int button) {
      if (this.getDisplayDims().collideWithMouse(mouse)) {
         int current = 0;
         int index = 0;

         for(String s : this.modes) {
            if (s.equals(this.getValue())) {
               current = index;
            }

            ++index;
         }

         int amount = 1;
         if (current + amount > this.modes.length - 1) {
            this.setValue(this.modes[0]);
         } else {
            this.setValue(this.modes[current + amount]);
         }
      }

   }

   public void release(Context context, MouseHelper mouse, int state) {
   }

   public void key(Context context, int key, char character) {
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

   public String getValue() {
      return this.theValue;
   }

   public void setValue(String value) {
      this.theValue = value;
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

   public int getLevel() {
      return 3;
   }
}
