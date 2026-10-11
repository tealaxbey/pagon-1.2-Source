package org.itsmahir.pagon.api.gui.theme;

import java.awt.Color;

public interface IColorScheme {
   Color getMainColor(int var1);

   Color getOutlineColor();

   Color getButtonColor();

   Color getBackgroundColor();

   Color getSecondaryBackgroundColor();

   Color getTertiaryBackgroundColor();

   Color getTextColor();

   Color getTextColorHighlight();

   Color getTextColorActive();

   boolean doesTextShadow();
}
