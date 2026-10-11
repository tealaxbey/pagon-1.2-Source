package org.itsmahir.pagon.impl.features.modules.render;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_303;
import net.minecraft.class_7439;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.render.animation.type.TimeAnimation;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.mixin.accessor.IGameMessageS2CPacket;

public class BetterChat extends Module {
   public static BetterChat INSTANCE;
   Value<Boolean> timestamps = (new ValueBuilder<Boolean>()).withDescriptor("Timestamps").withValue(false).register(this);
   public Value<Boolean> animations = (new ValueBuilder<Boolean>()).withDescriptor("Animations").withValue(false).register(this);
   public float percentComplete = 0.0F;
   public final Map<class_303, TimeAnimation> animationMap = new HashMap();

   public BetterChat() {
      super("BetterChat", Feature.Category.Render);
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Receive event) {
      class_2596 var3 = event.getPacket();
      if (var3 instanceof class_7439 packet) {
         if ((Boolean)this.timestamps.getValue()) {
            IGameMessageS2CPacket var10000 = (IGameMessageS2CPacket)event.getPacket();
            String var10001 = String.valueOf(class_124.field_1078);
            var10000.setContent(class_2561.method_30163(var10001 + "<" + String.valueOf(class_124.field_1075) + (new SimpleDateFormat("k:mm")).format(new Date()) + String.valueOf(class_124.field_1078) + ">" + String.valueOf(class_124.field_1070) + " ").method_27661().method_10852(packet.comp_763()));
         }
      }

   }

   public String getDescription() {
      return "BetterChat: Modifies the chat in various ways to improve or show various things";
   }
}
