package org.itsmahir.pagon.impl.features.hud;

import java.text.DecimalFormat;
import net.minecraft.class_124;
import net.minecraft.class_3532;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.render.RenderGameOverlayEvent;
import org.itsmahir.pagon.api.feature.hud.HudComponent;
import org.itsmahir.pagon.api.gui.font.Fonts;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.management.TPSManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.client.gui.Hud;
import org.itsmahir.pagon.impl.gui.ClickGui;

public class Info extends HudComponent {
   public static Info INSTANCE;
   Value<Boolean> fps = (new ValueBuilder<Boolean>()).withDescriptor("FPS").withValue(true).register(this);
   Value<Boolean> tps = (new ValueBuilder<Boolean>()).withDescriptor("TPS").withValue(true).register(this);
   Value<Boolean> ping = (new ValueBuilder<Boolean>()).withDescriptor("Ping").withValue(true).register(this);
   Value<Boolean> speed = (new ValueBuilder<Boolean>()).withDescriptor("Speed").withValue(true).register(this);
   Value<Boolean> serverBrand = (new ValueBuilder<Boolean>()).withDescriptor("Server Brand").withValue(true).register(this);
   DecimalFormat df = new DecimalFormat("0.0");

   public Info() {
      super("Info");
      INSTANCE = this;
   }

   @SubscribeEvent
   public void draw(RenderGameOverlayEvent.Text event) {
      super.draw(event);
      if (!NullUtils.nullCheck() && !this.renderCheck(event)) {
         int yOff = 0;
         if ((Boolean)this.fps.getValue()) {
            String var10000 = String.valueOf(class_124.field_1068);
            String text = "FPS" + var10000 + " " + mc.method_47599();
            Fonts.doOneText(event.getContext(), text, ((Number)this.xPos.getValue()).floatValue(), ((Number)this.yPos.getValue()).floatValue() + (float)yOff, Hud.getTextColor(((Number)this.yPos.getValue()).intValue() + yOff), (Boolean)org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.textShadow.getValue());
            yOff += ClickGui.CONTEXT.getRenderer().getTextHeight(text);
         }

         if ((Boolean)this.tps.getValue()) {
            String var14 = String.valueOf(class_124.field_1068);
            String text = "TPS" + var14 + " " + this.df.format((double)TPSManager.INSTANCE.getTickRate());
            Fonts.doOneText(event.getContext(), text, ((Number)this.xPos.getValue()).floatValue(), ((Number)this.yPos.getValue()).floatValue() + (float)yOff, Hud.getTextColor(((Number)this.yPos.getValue()).intValue() + yOff), (Boolean)org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.textShadow.getValue());
            yOff += ClickGui.CONTEXT.getRenderer().getTextHeight(text);
         }

         if ((Boolean)this.ping.getValue()) {
            String var15 = String.valueOf(class_124.field_1068);
            String text = "Ping" + var15 + " " + PacketManager.INSTANCE.getClientLatency() + "ms";
            Fonts.doOneText(event.getContext(), text, ((Number)this.xPos.getValue()).floatValue(), ((Number)this.yPos.getValue()).floatValue() + (float)yOff, Hud.getTextColor(((Number)this.yPos.getValue()).intValue() + yOff), (Boolean)org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.textShadow.getValue());
            yOff += ClickGui.CONTEXT.getRenderer().getTextHeight(text);
         }

         if ((Boolean)this.speed.getValue()) {
            double x = mc.field_1724.method_23317() - mc.field_1724.field_6014;
            double z = mc.field_1724.method_23321() - mc.field_1724.field_5969;
            double kmh = (double)class_3532.method_15355((float)(x * x + z * z)) / 0.05 * 3.6;
            String var16 = String.valueOf(class_124.field_1068);
            String text = "Speed" + var16 + " " + this.df.format(kmh) + " km/h";
            Fonts.doOneText(event.getContext(), text, ((Number)this.xPos.getValue()).floatValue(), ((Number)this.yPos.getValue()).floatValue() + (float)yOff, Hud.getTextColor(((Number)this.yPos.getValue()).intValue() + yOff), (Boolean)org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.textShadow.getValue());
            yOff += ClickGui.CONTEXT.getRenderer().getTextHeight(text);
         }

         if ((Boolean)this.serverBrand.getValue() && mc.method_1562() != null && mc.method_1562().method_48296() != null) {
            String brand = mc.method_1562().method_52790();
            if (brand != null) {
               Fonts.doOneText(event.getContext(), brand, ((Number)this.xPos.getValue()).floatValue(), ((Number)this.yPos.getValue()).floatValue() + (float)yOff, Hud.getTextColor(((Number)this.yPos.getValue()).intValue() + yOff), (Boolean)org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.textShadow.getValue());
               yOff += ClickGui.CONTEXT.getRenderer().getTextHeight(brand);
            }
         }

         this.width = 50;
         this.height = yOff;
      }
   }

   public String getDescription() {
      return "Info: Displays FPS, TPS, Ping, Speed, Server Brand";
   }
}
