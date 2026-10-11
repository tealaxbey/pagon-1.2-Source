package org.itsmahir.pagon.api.gui.flow.impl;

import org.itsmahir.pagon.api.gui.component.IComponent;
import org.itsmahir.pagon.api.gui.context.Context;
import org.itsmahir.pagon.api.gui.flow.Flow;
import org.itsmahir.pagon.api.gui.helpers.Rect;

public class LinearFlow extends Flow {
   boolean setLength = true;

   public LinearFlow(Rect dims, int level) {
      super(dims, level);
   }

   public boolean doesAutoLength() {
      return this.setLength;
   }

   public void setAutoLength(boolean setLength) {
      this.setLength = setLength;
   }

   public void positionComponents(Context context) {
      super.positionComponents(context);
      int index = 0;
      int offset = 0;
      int betweenOffset = this.getLevel() > 1 ? context.getMetrics().getSettingSpacing() : context.getMetrics().getBetweenSpacing();
      int endOffset = this.getLevel() > 1 ? -context.getMetrics().getBetweenSpacing() : context.getMetrics().getBetweenSpacing();

      for(IComponent component : this.getComponents()) {
         component.getDims().setX(this.dims.getX());
         component.getDims().setY(this.dims.getY() + offset);
         component.getDims().setWidth(this.dims.getWidth());
         if (component.isActive()) {
            offset += component.getDims().getHeight() + betweenOffset;
         }

         ++index;
      }

      if (this.doesAutoLength()) {
         this.getDims().setHeight(offset - endOffset);
      }

   }
}
