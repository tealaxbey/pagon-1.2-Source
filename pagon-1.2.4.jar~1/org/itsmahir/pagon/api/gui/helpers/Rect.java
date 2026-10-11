package org.itsmahir.pagon.api.gui.helpers;

import org.itsmahir.pagon.impl.gui.ClickGui;

public class Rect {
   int x;
   int y;
   int width;
   int height;

   public Rect(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
   }

   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }

   public int getWidth() {
      return this.width;
   }

   public int getHeight() {
      return this.height;
   }

   public void setX(int x) {
      this.x = x;
   }

   public void setY(int y) {
      this.y = y;
   }

   public void setWidth(int width) {
      this.width = width;
   }

   public void setHeight(int height) {
      this.height = height;
   }

   public boolean collideWithMouse(MouseHelper mouse) {
      int posY = this.height < 0 ? this.y + ClickGui.CONTEXT.getRenderer().getTextHeight("AAA") : this.y;
      boolean withinXBounds;
      if (this.width < 0) {
         withinXBounds = mouse.getX() <= this.getX() && mouse.getX() >= this.getX() + this.width;
      } else {
         withinXBounds = mouse.getX() >= this.getX() && mouse.getX() <= this.getX() + this.width;
      }

      boolean withinYBounds;
      if (this.height < 0) {
         withinYBounds = mouse.getY() <= posY && mouse.getY() >= posY + this.height;
      } else {
         withinYBounds = mouse.getY() >= posY && mouse.getY() <= posY + this.height;
      }

      return withinXBounds && withinYBounds;
   }

   public Rect fixRect() {
      int posY = this.height < 0 ? this.y + ClickGui.CONTEXT.getRenderer().getTextHeight("AAA") : this.y;
      Rect rect = new Rect(this.x, this.y, this.width, this.height);
      if (this.width < 0) {
         rect.setX(rect.x + rect.getWidth());
         rect.setWidth(-rect.getWidth());
      }

      if (this.height < 0) {
         rect.setY(posY + rect.getHeight());
         rect.setHeight(-rect.getHeight());
      }

      return rect;
   }
}
