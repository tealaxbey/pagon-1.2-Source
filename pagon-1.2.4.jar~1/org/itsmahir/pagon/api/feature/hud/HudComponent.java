package org.itsmahir.pagon.api.feature.hud;

import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.render.RenderGameOverlayEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.gui.hudeditor.HudEditorGUI;
import org.itsmahir.pagon.api.utils.render.ScaledResolution;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.gui.ClickGui;

public class HudComponent extends Feature implements IMinecraft {
   public Value<Number> xPos = (new ValueBuilder<Number>()).withDescriptor("X Pos").withValue(100).withRange(0, 1000).register(this);
   public Value<Number> yPos = (new ValueBuilder<Number>()).withDescriptor("Y Pos").withValue(10).withRange(0, 1000).register(this);
   public boolean immovable;
   protected int width = 30;
   protected int height = 9;
   public ScaledResolution sr;

   public HudComponent(String name) {
      super(name, Feature.Category.Hud, Feature.FeatureType.Hud);
   }

   @SubscribeEvent
   public void draw(RenderGameOverlayEvent.Text event) {
      this.sr = new ScaledResolution(mc);
      this.xPos.setMax(this.sr.getScaledWidth());
      this.yPos.setMax(this.sr.getScaledHeight() - ClickGui.CONTEXT.getRenderer().getTextHeight("AAA"));
   }

   public boolean renderCheck(RenderGameOverlayEvent.Text event) {
      return mc.field_1755 instanceof HudEditorGUI && event.getCounter() != null;
   }

   public void setWidth(int width) {
      this.width = width;
   }

   public int getWidth() {
      return this.width;
   }

   public void setHeight(int height) {
      this.height = height;
   }

   public int getHeight() {
      return this.height;
   }
}
