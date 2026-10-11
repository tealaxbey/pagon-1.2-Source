package org.itsmahir.pagon.impl.features.modules.misc;

import java.awt.Color;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_412;
import net.minecraft.class_419;
import net.minecraft.class_442;
import net.minecraft.class_639;
import net.minecraft.class_642;
import net.minecraft.class_9112;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.network.ServerEvent;
import org.itsmahir.pagon.api.event.events.render.ScreenEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.Pair;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.mixin.accessor.IDisconnectedScreen;

public class AutoReconnect extends Module {
   Value<Number> distance = (new ValueBuilder<Number>()).withDescriptor("Time").withValue(1).withRange(1, 10).register(this);
   public Pair<class_639, class_642> lastServerConnection;

   public AutoReconnect() {
      super("AutoReconnect", Feature.Category.Misc);
   }

   @SubscribeEvent
   public void onAttempt(ServerEvent.AttemptConnect event) {
      this.lastServerConnection = new Pair<class_639, class_642>(event.address, event.info);
   }

   @SubscribeEvent
   public void onGuiOpen(ScreenEvent.SetScreen event) {
      if (event.getGuiScreen() instanceof class_419) {
         event.setGuiScreen(new AutoReconnectScreen((IDisconnectedScreen)event.getGuiScreen()));
      }

   }

   public String getDescription() {
      return "AutoReconnect: automatically rejoins the server if you get kicked/disconnected";
   }

   public class AutoReconnectScreen extends class_419 {
      private final long finalTime;

      public AutoReconnectScreen(IDisconnectedScreen screen) {
         super(screen.getParent(), class_2561.method_30163("AutoReconnect"), screen.getDisconnectionInfo());
         this.finalTime = System.currentTimeMillis() + ((Number)AutoReconnect.this.distance.getValue()).longValue() * 1000L;
      }

      public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
         super.method_25394(context, mouseX, mouseY, delta);
         long time = this.finalTime - System.currentTimeMillis();
         if (AutoReconnect.this.lastServerConnection != null) {
            if (time <= 0L) {
               class_412.method_36877(new class_442(), IMinecraft.mc, AutoReconnect.this.lastServerConnection.key(), AutoReconnect.this.lastServerConnection.value(), false, (class_9112)null);
            }

            String text = "Reconnecting in " + time / 1000L + "s";
            context.method_51433(IMinecraft.mc.field_1772, text, this.field_22789 / 2 - IMinecraft.mc.field_1772.method_1727(text) / 2, 8, Color.WHITE.getRGB(), true);
         }
      }
   }
}
