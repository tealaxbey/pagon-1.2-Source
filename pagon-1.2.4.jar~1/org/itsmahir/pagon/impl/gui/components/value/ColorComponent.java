package org.itsmahir.pagon.impl.gui.components.value;

import java.awt.Color;
import org.itsmahir.pagon.api.gui.helpers.Rect;
import org.itsmahir.pagon.api.gui.widget.impl.ColorWidget;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.value.Value;

public class ColorComponent extends ColorWidget {
   Value<PagonColor> colorValue;

   public ColorComponent(Value<PagonColor> colorValue) {
      super(colorValue.getName(), colorValue.getValue(), new Rect(0, 0, 0, 0));
      this.colorValue = colorValue;
   }

   public Color getValue() {
      return ((PagonColor)this.colorValue.getValue()).getColor();
   }

   public void setSyncing(boolean syncing) {
      ((PagonColor)this.colorValue.getValue()).setSync(syncing);
      this.colorValue.setValue(this.colorValue.getValue());
   }

   public boolean getSyncing() {
      return ((PagonColor)this.colorValue.getValue()).isSyncing();
   }

   public void setValue(Color value) {
      ((PagonColor)this.colorValue.getValue()).setColor(value);
      this.colorValue.setValue(this.colorValue.getValue());
   }

   public boolean isActive() {
      return this.colorValue.isActive();
   }
}
