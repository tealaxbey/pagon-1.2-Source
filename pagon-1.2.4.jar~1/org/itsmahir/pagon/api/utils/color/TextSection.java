package org.itsmahir.pagon.api.utils.color;

import java.awt.Color;

public class TextSection {
   String text;
   Color color;

   public TextSection(String text, Color color) {
      this.text = text;
      this.color = color;
   }

   public String getText() {
      return this.text;
   }

   public Color getColor() {
      return this.color;
   }
}
