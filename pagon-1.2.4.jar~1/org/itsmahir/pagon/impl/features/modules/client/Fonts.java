package org.itsmahir.pagon.impl.features.modules.client;

import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class Fonts extends Module {
   public Value<Boolean> textShadow = (new ValueBuilder<Boolean>()).withDescriptor("Shadow").withValue(true).register(this);
   public Value<Boolean> contrast;
   public Value<Boolean> glow;
   public Value<Boolean> antiAlias;
   public Value<Number> fontSize;
   public static Fonts INSTANCE;
   int lastValue;
   boolean lastAlias;

   public Fonts() {
      super("Font", Feature.Category.Client);
      this.contrast = (new ValueBuilder<Boolean>()).withDescriptor("Contrast").withValue(false).withParent(this.textShadow).withParentEnabled(true).register(this);
      this.glow = (new ValueBuilder<Boolean>()).withDescriptor("Glow").withValue(false).register(this);
      this.antiAlias = (new ValueBuilder<Boolean>()).withDescriptor("AntiAlias").withValue(true).withAction((s) -> this.doRefresh((Boolean)s.getValue())).register(this);
      this.fontSize = (new ValueBuilder<Number>()).withDescriptor("Size").withValue(18).withRange(10, 25).withPlaces(0).withAction((s) -> this.doRefresh(((Number)s.getValue()).intValue())).register(this);
      this.lastAlias = false;
      INSTANCE = this;
      this.setEnabled(true);
   }

   public String getDescription() {
      return "Changes the client text to custom font rendering";
   }

   public void onEnable() {
      super.onEnable();
      this.lastAlias = (Boolean)this.antiAlias.getValue();
      this.lastValue = ((Number)this.fontSize.getValue()).intValue();
   }

   public void doRefresh(int value) {
      if (this.lastValue != value) {
         org.itsmahir.pagon.api.gui.font.Fonts.refresh();
      }

      this.lastValue = value;
   }

   public void doRefresh(boolean value) {
      if (this.lastAlias != value) {
         org.itsmahir.pagon.api.gui.font.Fonts.refresh();
      }

      this.lastAlias = value;
   }
}
