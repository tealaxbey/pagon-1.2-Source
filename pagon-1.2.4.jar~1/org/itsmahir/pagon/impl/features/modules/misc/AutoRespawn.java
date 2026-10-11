package org.itsmahir.pagon.impl.features.modules.misc;

import java.awt.Color;
import net.minecraft.class_332;
import net.minecraft.class_418;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.render.ScreenEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.mixin.accessor.IDeathScreen;

public class AutoRespawn extends Module {
   Value<Number> time = (new ValueBuilder<Number>()).withDescriptor("Time").withValue(1).withRange(0.1, 5).withPlaces(1).register(this);

   public AutoRespawn() {
      super("AutoRespawn", Feature.Category.Misc);
   }

   @SubscribeEvent
   public void onGuiOpen(ScreenEvent.SetScreen event) {
      if (event.getGuiScreen() instanceof class_418) {
         event.setGuiScreen(new ScreenAutoRespawn((IDeathScreen)event.getGuiScreen()));
      }

   }

   public String getDescription() {
      return "AutoRespawn: respawns for you when you die";
   }

   public class ScreenAutoRespawn extends class_418 {
      private final long finalTime;

      public ScreenAutoRespawn(IDeathScreen screen) {
         super(screen.getDeathMessage(), screen.isScreenHardcore());
         this.finalTime = System.currentTimeMillis() + ((Number)AutoRespawn.this.time.getValue()).longValue() * 1000L;
      }

      public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
         super.method_25394(context, mouseX, mouseY, delta);
         float time = (float)(this.finalTime - System.currentTimeMillis());
         if (IMinecraft.mc.field_1724 != null && time <= 0.0F) {
            IMinecraft.mc.field_1724.method_7331();
         }

         float timeLeft = MathUtil.round(time / 1000.0F, 1);
         if (timeLeft >= 0.0F) {
            String text = "Respawning in " + timeLeft + "s";
            context.method_51433(IMinecraft.mc.field_1772, text, this.field_22789 / 2 - IMinecraft.mc.field_1772.method_1727(text) / 2, 8, Color.WHITE.getRGB(), true);
         }

      }
   }
}
