package org.itsmahir.pagon.impl.features.hud;

import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.render.RenderGameOverlayEvent;
import org.itsmahir.pagon.api.feature.hud.HudComponent;
import org.itsmahir.pagon.api.gui.GUI;
import org.itsmahir.pagon.api.gui.font.Fonts;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.render.ScaledResolution;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.client.gui.Hud;
import org.itsmahir.pagon.impl.gui.ClickGui;

public class Welcomer extends HudComponent {
   Value<Boolean> autoPos = (new ValueBuilder<Boolean>()).withDescriptor("Auto Pos").withValue(true).withAction((s) -> {
      this.xPos.setActive(!(Boolean)s.getValue());
      this.yPos.setActive(!(Boolean)s.getValue());
   }).register(this);
   public static Welcomer INSTANCE;

   public Welcomer() {
      super("Welcomer");
      INSTANCE = this;
   }

   @SubscribeEvent
   public void draw(RenderGameOverlayEvent.Text event) {
      super.draw(event);
      if (!NullUtils.nullCheck() && !this.renderCheck(event)) {
         if (!(mc.field_1755 instanceof GUI)) {
            String text = "hi " + mc.field_1724.method_5477().getString() + "!";
            this.width = ClickGui.CONTEXT.getRenderer().getTextWidth(text);
            if ((Boolean)this.autoPos.getValue()) {
               ScaledResolution sr = new ScaledResolution(mc);
               this.xPos.setValue((sr.getScaledWidth() - ClickGui.CONTEXT.getRenderer().getTextWidth(text)) / 2);
               this.yPos.setValue(1);
            }

            Fonts.doOneText(event.getContext(), text, ((Number)this.xPos.getValue()).floatValue(), ((Number)this.yPos.getValue()).floatValue() + 1.0F, Hud.getTextColor(((Number)this.yPos.getValue()).intValue()), (Boolean)org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.textShadow.getValue());
         }
      }
   }

   public String getDescription() {
      return "Welcomer: Welcomes you";
   }
}
