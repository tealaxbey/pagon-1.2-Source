package org.itsmahir.pagon.impl.gui.components.module;

import java.awt.Color;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.gui.component.IComponent;
import org.itsmahir.pagon.api.gui.context.Context;
import org.itsmahir.pagon.api.gui.flow.Flow;
import org.itsmahir.pagon.api.gui.flow.impl.LinearFlow;
import org.itsmahir.pagon.api.gui.helpers.MouseHelper;
import org.itsmahir.pagon.api.gui.helpers.Rect;
import org.itsmahir.pagon.api.gui.widget.impl.BindWidget;
import org.itsmahir.pagon.api.gui.widget.impl.BooleanWidget;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.render.animation.Easing;
import org.itsmahir.pagon.api.utils.render.animation.type.DynamicAnimation;
import org.itsmahir.pagon.api.utils.render.animation.type.DynamicColorAnimation;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.impl.gui.components.value.BooleanComponent;
import org.itsmahir.pagon.impl.gui.components.value.ColorComponent;
import org.itsmahir.pagon.impl.gui.components.value.ComboBoxComponent;
import org.itsmahir.pagon.impl.gui.components.value.SliderComponent;
import org.itsmahir.pagon.impl.gui.components.value.StringComponent;

public class FeatureButton extends BooleanWidget {
   Feature feature;
   public boolean open;
   Rect header;
   public Flow flow;
   public DynamicColorAnimation animation;
   public DynamicAnimation titleOffsetAnim;

   public FeatureButton(Feature feature, Rect dims) {
      super(feature.getName(), dims);
      this.feature = feature;
      this.open = false;
      this.header = new Rect(0, 0, 0, 0);
      this.flow = new LinearFlow(new Rect(0, 0, 0, 0), 2);
      this.titleOffsetAnim = new DynamicAnimation(0.0F, Easing.CUBIC_IN_OUT, 300L);
      if (feature instanceof Module) {
         this.flow.getComponents().add(new BindWidget("Module Bind", ((Module)feature).getBind()));
      }

      for(Value<?> value : feature.getValues()) {
         IComponent component = value.getComponent();
         if (component == null) {
            if (value.getValue() instanceof Boolean) {
               this.flow.getComponents().add(new BooleanComponent(value));
            }

            if (value.getValue() instanceof String) {
               if (value.getModes() != null) {
                  this.flow.getComponents().add(new ComboBoxComponent(value));
               } else {
                  this.flow.getComponents().add(new StringComponent(value));
               }
            }

            if (value.getValue() instanceof Number) {
               this.flow.getComponents().add(new SliderComponent(value));
            }

            if (value.getValue() instanceof PagonColor) {
               this.flow.getComponents().add(new ColorComponent(value));
            }
         } else {
            this.flow.getComponents().add(component);
         }
      }

   }

   public void draw(Context context, MouseHelper mouse) {
      this.setValue(this.feature.isEnabled());
      this.updateDims(context);
      Color color = this.getValue() ? context.getColorScheme().getMainColor(0) : context.getColorScheme().getSecondaryBackgroundColor();
      if (this.getDisplayDims().collideWithMouse(mouse)) {
         if (!this.getValue()) {
            color = ColorUtil.newAlpha(Color.WHITE, 10);
         }

         color = ColorUtil.brighten(color, (double)0.1F);
      }

      if (this.animation == null) {
         this.animation = new DynamicColorAnimation(color, Easing.LINEAR, 300L);
      }

      this.animation.gotoColor(color);
      context.getRenderer().renderFeatureButton(this, context, this.getDisplayDims(), mouse);
      if (this.open) {
         this.flow.draw(context, mouse);
      }

   }

   public void updateDims(Context context) {
      this.header.setX(this.getDims().getX());
      this.header.setY(this.getDims().getY());
      this.header.setWidth(this.getDims().getWidth());
      this.header.setHeight(context.getMetrics().getButtonHeight());
      this.flow.getDims().setX(this.getDims().getX() + context.getMetrics().getSpacing());
      this.flow.getDims().setY(this.getDims().getY() + this.header.getHeight() + context.getMetrics().getBetweenSpacing());
      this.flow.getDims().setWidth(this.getDims().getWidth() - context.getMetrics().getSpacing() * 2);
      this.flow.positionComponents(context);
      this.getDims().setHeight(context.getMetrics().getButtonHeight());
      if (this.open) {
         this.getDims().setHeight(this.header.getHeight() + this.flow.getDims().getHeight());
      }

   }

   public void click(Context context, MouseHelper mouse, int button) {
      super.click(context, mouse, button);
      if (this.getDisplayDims().collideWithMouse(mouse)) {
         if (button == 0) {
            this.feature.setEnabled(this.getValue());
         }

         if (button == 1) {
            this.open = !this.open;
            this.titleOffsetAnim.goal(this.open ? 1.0F : 0.0F);
         }
      }

      if (this.open) {
         this.flow.click(context, mouse, button);
      }

   }

   public void release(Context context, MouseHelper mouse, int state) {
      super.release(context, mouse, state);
      if (this.open) {
         this.flow.release(context, mouse, state);
      }

   }

   public void key(Context context, int key, char character) {
      super.key(context, key, character);
      if (this.open) {
         this.flow.key(context, key, character);
      }

   }

   public void charTyped(Context context, char character) {
      super.charTyped(context, character);
      if (this.open) {
         this.flow.charTyped(context, character);
      }

   }

   public Rect getDims() {
      return super.getDims();
   }

   public Feature getFeature() {
      return this.feature;
   }

   public Rect getDisplayDims() {
      return this.header;
   }
}
