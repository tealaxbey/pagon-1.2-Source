package org.itsmahir.pagon.impl.gui.components.value;

import java.awt.Color;
import org.itsmahir.pagon.api.gui.component.IComponent;
import org.itsmahir.pagon.api.gui.context.Context;
import org.itsmahir.pagon.api.gui.helpers.MouseHelper;
import org.itsmahir.pagon.api.gui.helpers.Rect;
import org.itsmahir.pagon.api.gui.hudeditor.HudEditorGUI;
import org.itsmahir.pagon.api.gui.render.IRenderer;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.impl.features.modules.client.gui.Hud;

public class EditorButtonComponent implements IComponent, ICustomComponent<Boolean> {
   Value<Boolean> value;
   Rect dims;

   public EditorButtonComponent(Rect dims) {
      this.dims = dims;
   }

   public void draw(Context context, MouseHelper mouse) {
      this.getDims().setHeight(context.getMetrics().getButtonHeight());
      Color bgColor = this.getDims().collideWithMouse(mouse) ? context.getColorScheme().getMainColor(0) : context.getColorScheme().getButtonColor();
      context.getRenderer().renderRect(this.getDims(), bgColor, bgColor, IRenderer.RectMode.Fill, context);
      String text = "Open Editor";
      int centerY = (this.getDims().getHeight() - context.getRenderer().getTextHeight(text)) / 2 + 1;
      context.getRenderer().renderText(context.getDrawContext(), text, (float)(this.getDims().getX() + 2), (float)(this.getDims().getY() + centerY), context.getColorScheme().getTextColor(), context.getColorScheme().doesTextShadow());
   }

   public void click(Context context, MouseHelper mouse, int button) {
      if (this.getDims().collideWithMouse(mouse) && button == 0) {
         if (NullUtils.nullCheck()) {
            return;
         }

         HudEditorGUI.INSTANCE.enterGui(Hud.INSTANCE, Hud.INSTANCE, Hud.INSTANCE);
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
      return this.value == null || this.value.isActive();
   }

   public void setValue(Value<Boolean> value) {
      this.value = value;
   }
}
