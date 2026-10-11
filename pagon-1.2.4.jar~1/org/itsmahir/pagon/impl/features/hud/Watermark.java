package org.itsmahir.pagon.impl.features.hud;

import net.minecraft.class_124;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.render.RenderGameOverlayEvent;
import org.itsmahir.pagon.api.feature.hud.HudComponent;
import org.itsmahir.pagon.api.gui.font.Fonts;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.color.RainbowUtil;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.client.gui.Hud;
import org.itsmahir.pagon.impl.gui.ClickGui;

public class Watermark extends HudComponent {
   public static Watermark INSTANCE;
   Value<Boolean> wave = (new ValueBuilder<Boolean>()).withDescriptor("Wave").withValue(false).register(this);

   public Watermark() {
      super("Watermark");
      INSTANCE = this;
   }

   @SubscribeEvent
   public void draw(RenderGameOverlayEvent.Text event) {
      super.draw(event);
      if (!NullUtils.nullCheck() && !this.renderCheck(event)) {
         this.width = ClickGui.CONTEXT.getRenderer().getTextWidth(this.getWatermarkText());
         this.height = ClickGui.CONTEXT.getRenderer().getTextHeight("AAA");
         String text = this.getWatermarkText();
         if ((Boolean)this.wave.getValue()) {
            RainbowUtil.renderWave(event.getContext(), text, ((Number)this.xPos.getValue()).floatValue(), ((Number)this.yPos.getValue()).floatValue());
         } else {
            Fonts.doOneText(event.getContext(), text, ((Number)this.xPos.getValue()).floatValue(), ((Number)this.yPos.getValue()).floatValue(), Hud.getTextColor(((Number)this.yPos.getValue()).intValue()), (Boolean)org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.textShadow.getValue());
         }

      }
   }

   private String getWatermarkText() {
      return "Pagon" + String.valueOf(class_124.field_1068) + " Beta-1.2.4";
   }

   public String getDescription() {
      return "Watermark: Displays client name and version";
   }
}
