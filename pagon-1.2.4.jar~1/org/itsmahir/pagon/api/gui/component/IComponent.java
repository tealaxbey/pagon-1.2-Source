package org.itsmahir.pagon.api.gui.component;

import org.itsmahir.pagon.api.gui.context.Context;
import org.itsmahir.pagon.api.gui.helpers.MouseHelper;
import org.itsmahir.pagon.api.gui.helpers.Rect;

public interface IComponent {
   void draw(Context var1, MouseHelper var2);

   void click(Context var1, MouseHelper var2, int var3);

   void release(Context var1, MouseHelper var2, int var3);

   void key(Context var1, int var2, char var3);

   void charTyped(Context var1, char var2);

   int getLevel();

   Rect getDims();

   boolean isDraggable();

   boolean isActive();
}
