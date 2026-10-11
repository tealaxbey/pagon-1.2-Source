package org.itsmahir.pagon.impl.features.modules.misc;

import net.minecraft.class_2596;
import net.minecraft.class_2797;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.PagonMod;
import org.itsmahir.pagon.mixin.accessor.IChatMessageC2SPacket;

public class ChatSuffix extends Module {
   Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("pagon").register(this);
   String[] filters = new String[]{".", "/", ",", ":", "`", "-"};

   public ChatSuffix() {
      super("ChatSuffix", Feature.Category.Misc);
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Send event) {
      if (!NullUtils.nullCheck()) {
         class_2596 var3 = event.getPacket();
         if (var3 instanceof class_2797) {
            class_2797 packet = (class_2797)var3;
            if (this.allowMessage(packet.comp_945())) {
               String newMessage = this.getText(packet.comp_945());
               if (newMessage.length() >= 254) {
                  return;
               }

               ((IChatMessageC2SPacket)event.getPacket()).setMessage(newMessage);
            }
         }

      }
   }

   public String getText(String message) {
      return ((String)this.mode.getValue()).equals("SN0WUNICODE") ? message + " " + PagonMod.NAME_UNICODE : message + ChatUtils.hephaestus(" | " + ((String)this.mode.getValue()).toLowerCase());
   }

   boolean allowMessage(String message) {
      boolean allow = true;

      for(String s : this.filters) {
         if (message.startsWith(s)) {
            allow = false;
            break;
         }
      }

      return allow;
   }

   public String getDescription() {
      return "ChatSuffix: append stuff after ur chat message";
   }
}
