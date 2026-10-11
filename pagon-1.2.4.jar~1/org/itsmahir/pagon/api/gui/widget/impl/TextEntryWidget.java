package org.itsmahir.pagon.api.gui.widget.impl;

import net.minecraft.class_3675;
import net.minecraft.class_3728;
import org.itsmahir.pagon.api.gui.component.IComponent;
import org.itsmahir.pagon.api.gui.context.Context;
import org.itsmahir.pagon.api.gui.helpers.MouseHelper;
import org.itsmahir.pagon.api.gui.helpers.Rect;
import org.itsmahir.pagon.api.gui.widget.IWidget;
import org.itsmahir.pagon.api.utils.StringUtils;
import org.itsmahir.pagon.api.wrapper.IMinecraft;

public class TextEntryWidget implements IWidget<String>, IComponent {
   Rect dims;
   String value;
   public boolean typing = false;

   public TextEntryWidget(Rect dims, String value) {
      this.dims = dims;
      this.value = value;
   }

   public void draw(Context context, MouseHelper mouse) {
      if (!this.getDims().collideWithMouse(mouse)) {
         this.typing = false;
      }

      this.getDims().setHeight(context.getMetrics().getButtonHeight());
      context.getRenderer().renderStringWidget(this, context, this.getDims(), mouse);
   }

   public void click(Context context, MouseHelper mouse, int button) {
      if (this.getDims().collideWithMouse(mouse) && button == 0) {
         this.typing = !this.typing;
      } else {
         this.typing = false;
      }

   }

   public void release(Context context, MouseHelper mouse, int state) {
   }

   public void key(Context context, int key, char character) {
      if (this.typing) {
         switch (key) {
            case 86:
               if (class_3675.method_15987(IMinecraft.mc.method_22683().method_4490(), 341)) {
                  String var10001 = this.getValue();
                  this.setValue(var10001 + class_3728.method_27556(IMinecraft.mc));
               }
               break;
            case 256:
            case 257:
            case 335:
               this.typing = false;
               break;
            case 259:
               this.setValue(StringUtils.removeLastChar(this.getValue()));
         }

      }
   }

   public void charTyped(Context context, char character) {
      if (this.typing) {
         String var10001 = this.getValue();
         this.setValue(var10001 + character);
      }

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

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getTitle() {
      return "";
   }

   public void setTitle(String title) {
   }

   public Rect getDisplayDims() {
      return this.getDims();
   }
}
