package org.itsmahir.pagon.impl.gui.components.value;

import org.itsmahir.pagon.api.gui.helpers.Rect;
import org.itsmahir.pagon.api.gui.widget.impl.TextEntryWidget;
import org.itsmahir.pagon.api.value.Value;

public class StringComponent extends TextEntryWidget {
   Value<String> stringValue;

   public StringComponent(Value<String> stringValue) {
      super(new Rect(0, 0, 0, 0), stringValue.getValue());
      this.stringValue = stringValue;
   }

   public String getValue() {
      return this.stringValue.getValue();
   }

   public void setValue(String value) {
      this.stringValue.setValue(value);
      super.setValue(value);
   }

   public boolean isActive() {
      return this.stringValue.isActive();
   }
}
