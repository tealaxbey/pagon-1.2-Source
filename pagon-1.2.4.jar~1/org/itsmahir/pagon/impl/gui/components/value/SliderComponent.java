package org.itsmahir.pagon.impl.gui.components.value;

import org.itsmahir.pagon.api.gui.context.Context;
import org.itsmahir.pagon.api.gui.helpers.MouseHelper;
import org.itsmahir.pagon.api.gui.widget.impl.SliderWidget;
import org.itsmahir.pagon.api.value.Value;

public class SliderComponent extends SliderWidget {
   Value<Number> val;

   public SliderComponent(Value<Number> value) {
      super(value.getName(), value.getValue(), value.getMin(), value.getMax());
      this.val = value;
   }

   public void draw(Context context, MouseHelper mouse) {
      super.draw(context, mouse);
      this.setValue(this.val.getValue());
      this.min = this.val.getMin();
      this.max = this.val.getMax();
   }

   public void setValue(Number value) {
      super.setValue(value);
      this.val.setValue(value);
   }

   public boolean isActive() {
      return this.val.isActive();
   }
}
