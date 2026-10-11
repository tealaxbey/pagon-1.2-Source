package org.itsmahir.pagon.impl.gui.components.value;

import org.itsmahir.pagon.api.gui.context.Context;
import org.itsmahir.pagon.api.gui.helpers.MouseHelper;
import org.itsmahir.pagon.api.gui.helpers.Rect;
import org.itsmahir.pagon.api.gui.widget.impl.ComboBoxWidget;
import org.itsmahir.pagon.api.value.Value;

public class ComboBoxComponent extends ComboBoxWidget {
   Value<String> value;

   public ComboBoxComponent(Value<String> value) {
      super(value.getName(), value.getValue(), value.getModes(), new Rect(0, 0, 0, 0));
      this.value = value;
   }

   public void draw(Context context, MouseHelper mouse) {
      this.setValue(this.value.getValue());
      super.draw(context, mouse);
   }

   public void click(Context context, MouseHelper mouse, int button) {
      super.click(context, mouse, button);
   }

   public boolean isActive() {
      return this.value.isActive();
   }

   public void setValue(String value) {
      super.setValue(value);
      this.value.setValue(value);
   }
}
