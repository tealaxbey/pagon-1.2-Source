package org.itsmahir.pagon.api.gui.widget.impl;

import java.awt.Color;
import net.minecraft.class_124;
import net.minecraft.class_3532;
import net.minecraft.class_3675;
import org.itsmahir.pagon.api.gui.component.IComponent;
import org.itsmahir.pagon.api.gui.context.Context;
import org.itsmahir.pagon.api.gui.helpers.MouseHelper;
import org.itsmahir.pagon.api.gui.helpers.Rect;
import org.itsmahir.pagon.api.gui.widget.IWidget;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.render.animation.Easing;
import org.itsmahir.pagon.api.utils.render.animation.type.DynamicColorAnimation;
import org.itsmahir.pagon.api.wrapper.IMinecraft;

public abstract class ColorWidget implements IWidget<Color>, IComponent {
   PagonColor value;
   String title;
   boolean open = false;
   Rect dims;
   Rect displayDims;
   Rect insideDims;
   Rect pickerRect;
   Rect alphaRect;
   Rect hueRect;
   Rect colorSquare;
   boolean draggingHue;
   boolean draggingColor;
   boolean draggingAlpha;
   boolean hovering;
   public DynamicColorAnimation animation;
   private static Color COPIED_COLOR = null;

   public ColorWidget(String title, PagonColor value, Rect dims) {
      this.title = title;
      this.value = value;
      this.dims = dims;
      this.displayDims = new Rect(0, 0, 0, 0);
      this.pickerRect = new Rect(0, 0, 0, 0);
      this.alphaRect = new Rect(0, 0, 0, 0);
      this.insideDims = new Rect(0, 0, 0, 0);
      this.hueRect = new Rect(0, 0, 0, 0);
      this.colorSquare = new Rect(0, 0, 0, 0);
      this.animation = new DynamicColorAnimation(ColorUtil.newAlpha(Color.WHITE, 0), Easing.CUBIC_IN_OUT, 300L);
   }

   public Color getValue() {
      return this.value.getColor();
   }

   public void setSyncing(boolean sync) {
      this.value.setSync(!this.value.isSyncing());
   }

   public boolean getSyncing() {
      return this.value.isSyncing();
   }

   public boolean getUnsyncable() {
      return this.value.isUnsyncable();
   }

   public void setValue(Color value) {
      this.value.setColor(value);
   }

   public String getTitle() {
      return this.title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public Rect getDisplayDims() {
      return this.open ? this.dims : this.displayDims;
   }

   public void draw(Context context, MouseHelper mouse) {
      this.displayDims.setX(this.getDims().getX());
      this.displayDims.setY(this.getDims().getY());
      this.displayDims.setWidth(this.getDims().getWidth());
      this.displayDims.setHeight(context.getMetrics().getButtonHeight());
      float[] hsb = Color.RGBtoHSB(this.getValue().getRed(), this.getValue().getGreen(), this.getValue().getBlue(), (float[])null);
      int spacing = 1;
      int alphaSliderWidth = context.getMetrics().getButtonHeight();
      int squareSize = this.insideDims.getWidth() - (alphaSliderWidth + spacing * 3);
      this.hovering = false;
      int size = this.displayDims.getHeight() - 2;
      int rightX = this.displayDims.getX() + (this.displayDims.getWidth() - size - 1);
      Rect colorRect = new Rect(rightX, this.displayDims.getY() + 1, size, size);
      Color mainColor = this.getUnsyncable() ? Color.RED : Color.WHITE;
      Color goal = ColorUtil.newAlpha(mainColor, 0);
      if (this.getSyncing()) {
         goal = mainColor;
      } else if (colorRect.collideWithMouse(context.getHelper())) {
         if (this.getUnsyncable()) {
            goal = mainColor;
         } else {
            goal = ColorUtil.newAlpha(mainColor, 100);
         }
      }

      this.animation.gotoColor(goal);
      this.insideDims.setX(this.getDims().getX() + context.getMetrics().getSpacing());
      this.insideDims.setY(this.getDims().getY() + this.displayDims.getHeight() + context.getMetrics().getSpacing());
      this.insideDims.setWidth(this.getDims().getWidth() - context.getMetrics().getSpacing() * 2);
      this.insideDims.setHeight(this.getDims().getHeight() - this.displayDims.getHeight() - context.getMetrics().getSpacing() * 2);
      this.hueRect.setX(this.insideDims.getX() + spacing);
      this.hueRect.setWidth(this.insideDims.getWidth() - spacing * 2);
      this.hueRect.setY(this.insideDims.getY() + spacing + squareSize + spacing);
      this.hueRect.setHeight(context.getMetrics().getButtonHeight());
      this.alphaRect.setX(this.colorSquare.getX() + this.colorSquare.getWidth() + spacing);
      this.alphaRect.setY(this.colorSquare.getY());
      this.alphaRect.setWidth(alphaSliderWidth);
      this.alphaRect.setHeight(this.colorSquare.getHeight());
      this.colorSquare.setX(this.insideDims.getX() + spacing);
      this.colorSquare.setY(this.insideDims.getY() + spacing);
      this.colorSquare.setWidth(squareSize);
      this.colorSquare.setHeight(squareSize);
      this.dims.setHeight(this.displayDims.getHeight() + squareSize + this.hueRect.getHeight() + context.getMetrics().getSpacing() * 4);
      if (this.draggingHue) {
         int clampMouseX = class_3532.method_15340(mouse.getX(), this.hueRect.getX(), this.hueRect.getX() + this.hueRect.getWidth());
         float normal = (float)MathUtil.normalize((double)clampMouseX, (double)this.hueRect.getX(), (double)(this.hueRect.getX() + this.hueRect.getWidth()));
         this.setValue(ColorUtil.newAlpha(Color.getHSBColor(class_3532.method_15363(normal, 0.0F, 1.0F), hsb[1], hsb[2]), this.getValue().getAlpha()));
      }

      if (this.draggingColor) {
         int clampMouseX = class_3532.method_15340(mouse.getX(), this.colorSquare.getX(), this.colorSquare.getX() + this.colorSquare.getWidth());
         float normalX = (float)MathUtil.normalize((double)clampMouseX, (double)this.colorSquare.getX(), (double)(this.colorSquare.getX() + this.colorSquare.getWidth()));
         int clampMouseY = class_3532.method_15340(mouse.getY(), this.colorSquare.getY(), this.colorSquare.getY() + this.colorSquare.getHeight());
         float normalY = (float)MathUtil.normalize((double)clampMouseY, (double)this.colorSquare.getY(), (double)(this.colorSquare.getY() + this.colorSquare.getHeight()));
         normalY = -normalY + 1.0F;
         normalY = class_3532.method_15363(normalY, 0.0F, 1.0F);
         this.setValue(ColorUtil.newAlpha(Color.getHSBColor(hsb[0], normalX, normalY), this.getValue().getAlpha()));
      }

      if (this.draggingAlpha) {
         int clampMouseY = class_3532.method_15340(mouse.getY(), this.alphaRect.getY(), this.alphaRect.getY() + this.alphaRect.getHeight());
         float normal = (float)MathUtil.normalize((double)clampMouseY, (double)this.alphaRect.getY(), (double)(this.alphaRect.getY() + this.alphaRect.getHeight()));
         this.setValue(ColorUtil.newAlpha(this.getValue(), (int)class_3532.method_15363(normal * 255.0F, 0.0F, 255.0F)));
      }

      context.getRenderer().renderColorWidget(this, context, this.open, this.displayDims, this.dims, this.insideDims, this.alphaRect, this.hueRect, this.colorSquare);
      if (this.colorSquare.collideWithMouse(mouse) && this.getDims().collideWithMouse(mouse)) {
         this.hovering = true;
      }

   }

   public void click(Context context, MouseHelper mouse, int button) {
      if (this.displayDims.collideWithMouse(mouse)) {
         int size = this.displayDims.getHeight() - 2;
         int rightX = this.displayDims.getX() + (this.displayDims.getWidth() - size - 1);
         Rect colorRect = new Rect(rightX, this.displayDims.getY() + 1, size, size);
         if (colorRect.collideWithMouse(mouse) && button == 0 && !this.getUnsyncable()) {
            this.setSyncing(!this.getSyncing());
         } else if (button == 1) {
            this.open = !this.open;
         }
      }

      if (this.open && button == 0) {
         if (this.hueRect.collideWithMouse(mouse)) {
            this.draggingHue = true;
         }

         if (this.colorSquare.collideWithMouse(mouse)) {
            this.draggingColor = true;
         }

         if (this.alphaRect.collideWithMouse(mouse)) {
            this.draggingAlpha = true;
         }
      }

   }

   public void release(Context context, MouseHelper mouse, int state) {
      this.draggingHue = false;
      this.draggingColor = false;
      this.draggingAlpha = false;
   }

   public void key(Context context, int key, char character) {
      if (this.hovering && class_3675.method_15987(IMinecraft.mc.method_22683().method_4490(), 341) && this.open) {
         if (key == 67) {
            int var10000 = this.getValue().getRed();
            ChatUtils.sendMessage("Copied Color! " + var10000 + " " + this.getValue().getGreen() + " " + this.getValue().getBlue() + " ");
            COPIED_COLOR = this.getValue();
         } else if (key == 86) {
            if (COPIED_COLOR == null) {
               ChatUtils.sendMessage(String.valueOf(class_124.field_1061) + "No color copied!");
            } else {
               this.setValue(COPIED_COLOR);
               int var4 = this.getValue().getRed();
               ChatUtils.sendMessage("Set Color! " + var4 + " " + this.getValue().getGreen() + " " + this.getValue().getBlue() + " ");
            }
         }
      }

   }

   public void charTyped(Context context, char character) {
   }

   public int getLevel() {
      return 3;
   }

   public Rect getDims() {
      return this.open ? this.dims : this.displayDims;
   }

   public boolean isDraggable() {
      return false;
   }

   public boolean isActive() {
      return true;
   }
}
