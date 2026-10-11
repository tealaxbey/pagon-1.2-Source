package org.itsmahir.pagon.api.gui.widget;

import org.itsmahir.pagon.api.gui.helpers.Rect;

public interface IWidget<Type> {
   Type getValue();

   void setValue(Type var1);

   String getTitle();

   void setTitle(String var1);

   Rect getDisplayDims();
}
