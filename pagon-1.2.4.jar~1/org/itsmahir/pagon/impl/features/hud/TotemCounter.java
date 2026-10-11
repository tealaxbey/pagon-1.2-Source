package org.itsmahir.pagon.impl.features.hud;

import java.awt.Point;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.render.RenderGameOverlayEvent;
import org.itsmahir.pagon.api.feature.hud.HudComponent;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.ScaledResolution;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class TotemCounter extends HudComponent {
   Value<Boolean> autoPos = (new ValueBuilder<Boolean>()).withDescriptor("Auto Pos").withValue(true).withAction((s) -> {
      this.xPos.setActive(!(Boolean)s.getValue());
      this.yPos.setActive(!(Boolean)s.getValue());
   }).register(this);
   public Value<PagonColor> textColor = (new ValueBuilder<PagonColor>()).withDescriptor("Text Color").withValue(new PagonColor(255, 255, 255)).register(this);

   public TotemCounter() {
      super("TotemCounter");
   }

   @SubscribeEvent
   public void draw(RenderGameOverlayEvent.Text event) {
      super.draw(event);
      if (!NullUtils.nullCheck() && !this.renderCheck(event)) {
         this.width = 16;
         this.height = 16;
         ScaledResolution sr = new ScaledResolution(mc);
         if ((Boolean)this.autoPos.getValue()) {
            int i = sr.getScaledWidth() / 2;
            this.xPos.setValue(i - 189 + 180 + 2);
            this.yPos.setValue(sr.getScaledHeight() - 55 - (mc.field_1724.method_5869() ? 10 : 0));
         }

         int count = InventoryUtils.getItemCount(class_1802.field_8288);
         if (count != 0) {
            RenderUtil.renderItemWithCount(event.getContext(), new class_1799(class_1802.field_8288), new Point(((Number)this.xPos.getValue()).intValue(), ((Number)this.yPos.getValue()).intValue()), count, ((PagonColor)this.textColor.getValue()).getColor(), false);
         }
      }
   }

   public String getDescription() {
      return "TotemCounter: displays how many totems you have";
   }
}
