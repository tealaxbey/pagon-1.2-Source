package org.itsmahir.pagon.api.gui.flow;

import java.util.ArrayList;
import java.util.List;
import org.itsmahir.pagon.api.gui.component.IComponent;
import org.itsmahir.pagon.api.gui.context.Context;
import org.itsmahir.pagon.api.gui.helpers.MouseHelper;
import org.itsmahir.pagon.api.gui.helpers.Rect;

public abstract class Flow implements IComponent {
   public List<IComponent> components;
   public Rect dims;
   int level;

   public Flow(Rect dims, int level) {
      this.dims = dims;
      this.components = new ArrayList();
      this.level = level;
   }

   public void positionComponents(Context context) {
   }

   public void draw(Context context, MouseHelper mouse) {
      for(IComponent component : this.getComponents()) {
         if (component.isActive()) {
            component.draw(context, mouse);
         }
      }

   }

   public void click(Context context, MouseHelper mouse, int button) {
      for(IComponent component : this.getComponents()) {
         if (component.isActive()) {
            component.click(context, mouse, button);
         }
      }

   }

   public void release(Context context, MouseHelper mouse, int state) {
      for(IComponent component : this.getComponents()) {
         if (component.isActive()) {
            component.release(context, mouse, state);
         }
      }

   }

   public void key(Context context, int key, char character) {
      for(IComponent component : this.getComponents()) {
         if (component.isActive()) {
            component.key(context, key, character);
         }
      }

   }

   public void charTyped(Context context, char character) {
      for(IComponent component : this.getComponents()) {
         if (component.isActive()) {
            component.charTyped(context, character);
         }
      }

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

   public List<IComponent> getComponents() {
      return this.components;
   }

   public int getLevel() {
      return this.level;
   }
}
