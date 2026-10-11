package org.itsmahir.pagon.impl.gui.components.value;

import org.itsmahir.pagon.api.gui.context.Context;
import org.itsmahir.pagon.api.gui.helpers.MouseHelper;
import org.itsmahir.pagon.api.gui.helpers.Rect;
import org.itsmahir.pagon.api.gui.widget.impl.BooleanWidget;
import org.itsmahir.pagon.api.value.Value;

public class BooleanComponent extends BooleanWidget {
   Value<Boolean> value;

   public BooleanComponent(Value<Boolean> booleanValue) {
      super(booleanValue.getName(), new Rect(0, 0, 0, 0));
      this.value = booleanValue;
   }

   public void draw(Context context, MouseHelper mouse) {
      this.setValue(this.value.getValue());
      super.draw(context, mouse);
   }

   public void setValue(Boolean value) {
      super.setValue(value);
      this.value.setValue(value);
   }

   public boolean isActive() {
      return this.value.isActive();
   }
}
