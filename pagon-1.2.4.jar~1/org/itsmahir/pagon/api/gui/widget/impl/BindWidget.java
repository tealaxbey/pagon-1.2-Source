package org.itsmahir.pagon.api.gui.widget.impl;

import org.itsmahir.pagon.api.gui.component.IComponent;
import org.itsmahir.pagon.api.gui.context.Context;
import org.itsmahir.pagon.api.gui.helpers.MouseHelper;
import org.itsmahir.pagon.api.gui.helpers.Rect;
import org.itsmahir.pagon.api.gui.widget.IWidget;
import org.itsmahir.pagon.api.management.SavableManager;
import org.itsmahir.pagon.api.value.custom.Bind;

public class BindWidget implements IWidget<Bind>, IComponent {
   Bind value;
   String title;
   Rect dims;
   boolean binding;

   public BindWidget(String title, Bind value) {
      this.title = title;
      this.value = value;
      this.dims = new Rect(0, 0, 0, 0);
   }

   public Bind getValue() {
      return this.value;
   }

   public void setValue(Bind value) {
      this.value = value;
   }

   public String getTitle() {
      return this.title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public Rect getDisplayDims() {
      return this.dims;
   }

   public void draw(Context context, MouseHelper mouse) {
      this.getDims().setHeight(context.getMetrics().getButtonHeight());
      context.getRenderer().renderBindWidget(this, context, this.getDisplayDims(), mouse);
   }

   public void click(Context context, MouseHelper mouse, int button) {
      if (!this.binding) {
         if (this.getDims().collideWithMouse(mouse)) {
            if (button == 0) {
               this.binding = true;
            }

            if (button == 1) {
               this.getValue().setKey(-1);
               this.getValue().setIsMouse(false);

               try {
                  if (SavableManager.INSTANCE != null && !SavableManager.INSTANCE.isLoading) {
                     SavableManager.INSTANCE.saveAsync();
                  }
               } catch (Exception var6) {
               }
            }
         }

      } else {
         if (button == 0 && this.getDims().collideWithMouse(mouse)) {
            this.binding = false;
         } else if (button != 0) {
            this.getValue().setKey(button);
            this.getValue().setIsMouse(true);
            this.binding = false;

            try {
               if (SavableManager.INSTANCE != null && !SavableManager.INSTANCE.isLoading) {
                  SavableManager.INSTANCE.saveAsync();
               }
            } catch (Exception var5) {
            }
         }

      }
   }

   public void release(Context context, MouseHelper mouse, int state) {
   }

   public void key(Context context, int key, char character) {
      if (this.binding) {
         if (key != 256) {
            this.getValue().setKey(key);
            this.getValue().setIsMouse(false);

            try {
               if (SavableManager.INSTANCE != null && !SavableManager.INSTANCE.isLoading) {
                  SavableManager.INSTANCE.saveAsync();
               }
            } catch (Exception var5) {
            }
         }

         this.binding = false;
      }

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

   public boolean isBinding() {
      return this.binding;
   }

   public void setBinding(boolean binding) {
      this.binding = binding;
   }
}
