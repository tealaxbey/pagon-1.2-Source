package org.itsmahir.pagon.impl.features.modules.render;

import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class ViewModel extends Module {
   public static ViewModel INSTANCE;
   public Value<String> fovMode = (new ValueBuilder<String>()).withDescriptor("FOV Mode").withValue("None").withModes("None", "Custom").withAction((page) -> this.handleFovPage((String)page.getValue())).register(this);
   public Value<Number> fov = (new ValueBuilder<Number>()).withDescriptor("FOV").withValue(130).withRange(60, 170).register(this);
   public Value<Boolean> items = (new ValueBuilder<Boolean>()).withDescriptor("Items").withValue(true).register(this);
   public Value<Number> itemFov = (new ValueBuilder<Number>()).withDescriptor("Item Fov").withValue(70).withRange(60, 170).register(this);
   public Value<Boolean> translate = (new ValueBuilder<Boolean>()).withDescriptor("Translate").withValue(false).register(this);
   public Value<Number> posX;
   public Value<Number> posY;
   public Value<Number> posZ;
   public Value<Number> scaleX;
   public Value<Number> scaleY;
   public Value<Number> scaleZ;
   public Value<Number> rotateX;
   public Value<Number> rotateY;
   public Value<Number> rotateZ;

   public ViewModel() {
      super("ViewModel", Feature.Category.Render);
      this.posX = (new ValueBuilder<Number>()).withDescriptor("X").withValue(0).withRange((double)-3.0F, (double)3.0F).withPlaces(2).withParent(this.translate).withParentEnabled(true).register(this);
      this.posY = (new ValueBuilder<Number>()).withDescriptor("Y").withValue(0).withRange((double)-3.0F, (double)3.0F).withPlaces(2).withParent(this.translate).withParentEnabled(true).register(this);
      this.posZ = (new ValueBuilder<Number>()).withDescriptor("Z").withValue(0).withRange((double)-3.0F, (double)3.0F).withPlaces(2).withParent(this.translate).withParentEnabled(true).register(this);
      this.scaleX = (new ValueBuilder<Number>()).withDescriptor("Scale X").withValue(0).withRange((double)-3.0F, (double)3.0F).withPlaces(2).withParent(this.translate).withParentEnabled(true).register(this);
      this.scaleY = (new ValueBuilder<Number>()).withDescriptor("Scale Y").withValue((double)1.0F).withRange(0.1, (double)2.0F).withPlaces(1).withParent(this.translate).withParentEnabled(true).register(this);
      this.scaleZ = (new ValueBuilder<Number>()).withDescriptor("Scale Z").withValue((double)1.0F).withRange(0.1, (double)2.0F).withPlaces(1).withParent(this.translate).withParentEnabled(true).register(this);
      this.rotateX = (new ValueBuilder<Number>()).withDescriptor("Rotate X").withValue(0).withRange(-180, 180).withPlaces(0).withParent(this.translate).withParentEnabled(true).register(this);
      this.rotateY = (new ValueBuilder<Number>()).withDescriptor("Rotate Y").withValue(0).withRange(-180, 180).withPlaces(0).withParent(this.translate).withParentEnabled(true).register(this);
      this.rotateZ = (new ValueBuilder<Number>()).withDescriptor("Rotate Z").withValue(0).withRange(-180, 180).withPlaces(0).withParent(this.translate).withParentEnabled(true).register(this);
      INSTANCE = this;
   }

   public void handleFovPage(String page) {
      this.fov.setActive(page.equals("Custom"));
      this.items.setActive(page.equals("Custom"));
      this.itemFov.setActive(page.equals("Custom") && (Boolean)this.items.getValue());
   }

   public String getDescription() {
      return "ViewModel: change where the items render in your hand";
   }
}
