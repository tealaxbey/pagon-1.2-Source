package org.itsmahir.pagon.impl.features.hud;

import java.text.DecimalFormat;
import net.minecraft.class_124;
import net.minecraft.class_1937;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.render.RenderGameOverlayEvent;
import org.itsmahir.pagon.api.feature.hud.HudComponent;
import org.itsmahir.pagon.api.gui.GUI;
import org.itsmahir.pagon.api.gui.font.Fonts;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.client.gui.Hud;
import org.itsmahir.pagon.impl.gui.ClickGui;

public class Coords extends HudComponent {
   public static Coords INSTANCE;
   Value<Boolean> autoPos = (new ValueBuilder<Boolean>()).withDescriptor("Auto Pos").withValue(true).withAction((s) -> {
      this.xPos.setActive(!(Boolean)s.getValue());
      this.yPos.setActive(!(Boolean)s.getValue());
   }).register(this);
   DecimalFormat df = new DecimalFormat("#.#");

   public Coords() {
      super("Coords");
      INSTANCE = this;
   }

   @SubscribeEvent
   public void draw(RenderGameOverlayEvent.Text event) {
      super.draw(event);
      if (!NullUtils.nullCheck() && !this.renderCheck(event)) {
         if (!(mc.field_1755 instanceof GUI)) {
            String coords = this.getCoords();
            this.width = ClickGui.CONTEXT.getRenderer().getTextWidth(coords);
            this.height = ClickGui.CONTEXT.getRenderer().getTextHeight(coords);
            if ((Boolean)this.autoPos.getValue()) {
               this.xPos.setValue(1);
               this.yPos.setValue(1 + ClickGui.CONTEXT.getRenderer().getTextHeight("A"));
            }

            Fonts.doOneText(event.getContext(), coords, ((Number)this.xPos.getValue()).floatValue(), ((Number)this.yPos.getValue()).floatValue(), Hud.getTextColor(((Number)this.yPos.getValue()).intValue()), (Boolean)org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.textShadow.getValue());
         }
      }
   }

   public String getCoords() {
      double x = mc.field_1724.method_23317();
      double y = mc.field_1724.method_23318();
      double z = mc.field_1724.method_23321();
      if (mc.field_1687.method_27983() == class_1937.field_25180) {
         String var8 = String.valueOf(class_124.field_1068);
         return "XYZ " + var8 + this.df.format(x) + String.valueOf(class_124.field_1070) + ", " + String.valueOf(class_124.field_1068) + this.df.format(y) + String.valueOf(class_124.field_1070) + ", " + String.valueOf(class_124.field_1068) + this.df.format(z) + String.valueOf(class_124.field_1070) + " [" + String.valueOf(class_124.field_1068) + this.df.format(x * (double)8.0F) + String.valueOf(class_124.field_1070) + ", " + String.valueOf(class_124.field_1068) + this.df.format(z * (double)8.0F) + String.valueOf(class_124.field_1070) + "]";
      } else if (mc.field_1687.method_27983() == class_1937.field_25181) {
         String var7 = String.valueOf(class_124.field_1068);
         return "XYZ " + var7 + this.df.format(x) + String.valueOf(class_124.field_1070) + ", " + String.valueOf(class_124.field_1068) + this.df.format(y) + String.valueOf(class_124.field_1070) + ", " + String.valueOf(class_124.field_1068) + this.df.format(z);
      } else {
         String var10000 = String.valueOf(class_124.field_1068);
         return "XYZ " + var10000 + this.df.format(x) + String.valueOf(class_124.field_1070) + ", " + String.valueOf(class_124.field_1068) + this.df.format(y) + String.valueOf(class_124.field_1070) + ", " + String.valueOf(class_124.field_1068) + this.df.format(z) + String.valueOf(class_124.field_1070) + " [" + String.valueOf(class_124.field_1068) + this.df.format(x / (double)8.0F) + String.valueOf(class_124.field_1070) + ", " + String.valueOf(class_124.field_1068) + this.df.format(z / (double)8.0F) + String.valueOf(class_124.field_1070) + "]";
      }
   }

   public String getDescription() {
      return "Coords: Display your location";
   }
}
