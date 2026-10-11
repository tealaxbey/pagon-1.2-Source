package org.itsmahir.pagon.api.gui.helpers;

import org.itsmahir.pagon.api.feature.hud.HudComponent;

public interface IDraggable {
   default void drag(Rect dims, int dragX, int dragY, MouseHelper mouse) {
      dims.setX(mouse.getX() - dragX);
      dims.setY(mouse.getY() - dragY);
   }

   default void dragEditor(Rect dims, int dragX, int dragY, MouseHelper mouse, HudComponent hudComponent) {
      if (mouse.getX() - dragX >= ((Number)hudComponent.xPos.getMin()).intValue() && mouse.getX() - dragX <= ((Number)hudComponent.xPos.getMax()).intValue()) {
         dims.setX(mouse.getX() - dragX);
         hudComponent.xPos.setValue(mouse.getX() - dragX);
      }

      if (mouse.getY() - dragY >= ((Number)hudComponent.yPos.getMin()).intValue() && mouse.getY() - dragY <= ((Number)hudComponent.yPos.getMax()).intValue()) {
         dims.setY(mouse.getY() - dragY);
         hudComponent.yPos.setValue(mouse.getY() - dragY);
      }

   }
}
