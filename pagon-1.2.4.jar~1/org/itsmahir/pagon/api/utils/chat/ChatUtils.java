package org.itsmahir.pagon.api.utils.chat;

import java.awt.Color;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.ducks.IChatHud;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.PagonMod;

public class ChatUtils implements IMinecraft {
   public static String CLIENT_NAME;

   public static void sendMessage(ChatMessage message) {
      if (!NullUtils.nullCheck() && mc.field_1705 != null && mc.field_1705.method_1743() != null) {
         String var10000 = String.valueOf(class_124.field_1075);
         CLIENT_NAME = var10000 + "[" + PagonMod.NAME + "]" + String.valueOf(class_124.field_1070);
         if (message.doesOverride()) {
            IChatHud var1 = (IChatHud)mc.field_1705.method_1743();
            String var10001 = CLIENT_NAME;
            var1.addChatMessageWithId(class_2561.method_30163(var10001 + " " + message.getText()), message.getMessageID());
         } else {
            IChatHud var2 = (IChatHud)mc.field_1705.method_1743();
            String var3 = CLIENT_NAME;
            var2.addChatMessageNoId(class_2561.method_30163(var3 + " " + message.getText()));
         }

      }
   }

   public static void sendMessageNoPrefix(ChatMessage message) {
      if (!NullUtils.nullCheck() && mc.field_1705 != null && mc.field_1705.method_1743() != null) {
         String var10000 = String.valueOf(class_124.field_1075);
         CLIENT_NAME = var10000 + "[" + PagonMod.NAME + "]" + String.valueOf(class_124.field_1070);
         if (message.doesOverride()) {
            ((IChatHud)mc.field_1705.method_1743()).addChatMessageWithId(class_2561.method_30163(message.getText()), message.getMessageID());
         } else {
            ((IChatHud)mc.field_1705.method_1743()).addChatMessageNoId(class_2561.method_30163(message.getText()));
         }

      }
   }

   public static void sendMessage(String message) {
      if (!NullUtils.nullCheck() && mc.field_1705 != null && mc.field_1705.method_1743() != null) {
         String var10000 = String.valueOf(class_124.field_1075);
         CLIENT_NAME = var10000 + "[" + PagonMod.NAME + "]" + String.valueOf(class_124.field_1070);
         ((IChatHud)mc.field_1705.method_1743()).addChatMessageNoId(class_2561.method_30163(CLIENT_NAME + " " + message));
      }
   }

   public static void sendMessage(class_2561 message, boolean override, int id) {
      if (!NullUtils.nullCheck() && mc.field_1705 != null && mc.field_1705.method_1743() != null) {
         String var10000 = String.valueOf(class_124.field_1075);
         CLIENT_NAME = var10000 + "[" + PagonMod.NAME + "]" + String.valueOf(class_124.field_1070);
         if (override) {
            ((IChatHud)mc.field_1705.method_1743()).addChatMessageWithId(class_2561.method_30163(CLIENT_NAME + " ").method_27661().method_10852(message), id);
         } else {
            ((IChatHud)mc.field_1705.method_1743()).addChatMessageNoId(class_2561.method_30163(CLIENT_NAME + " ").method_27661().method_10852(message));
         }

      }
   }

   public static void sendChatMessage(String message) {
      if (message.length() < 254) {
         mc.field_1705.method_1743().method_1803(message);
         if (message.startsWith("/")) {
            mc.field_1724.field_3944.method_45730(message.substring(1));
         } else {
            mc.field_1724.field_3944.method_45729(message);
         }

      }
   }

   public static String hephaestus(String string) {
      String str = string.replace("a", "ᴀ");
      str = str.replace("b", "ʙ");
      str = str.replace("c", "ᴄ");
      str = str.replace("d", "ᴅ");
      str = str.replace("e", "ᴇ");
      str = str.replace("f", "ғ");
      str = str.replace("g", "ɢ");
      str = str.replace("h", "ʜ");
      str = str.replace("i", "ɪ");
      str = str.replace("j", "ᴊ");
      str = str.replace("k", "ᴋ");
      str = str.replace("l", "ʟ");
      str = str.replace("m", "ᴍ");
      str = str.replace("n", "ɴ");
      str = str.replace("o", "ᴏ");
      str = str.replace("p", "ᴘ");
      str = str.replace("q", "ǫ");
      str = str.replace("r", "ʀ");
      str = str.replace("s", "ѕ");
      str = str.replace("t", "ᴛ");
      str = str.replace("u", "ᴜ");
      str = str.replace("v", "ᴠ");
      str = str.replace("w", "ᴡ");
      str = str.replace("x", "х");
      str = str.replace("y", "ʏ");
      str = str.replace("z", "ᴢ");
      str = str.replace("|", "⏐");
      return str;
   }

   public static class_2561 withStyle(String message, Color color) {
      class_2561 text = class_2561.method_30163(message);
      return text.method_27661().method_10862(text.method_10866().method_36139(color.getRGB()));
   }

   static {
      String var10000 = String.valueOf(class_124.field_1075);
      CLIENT_NAME = var10000 + "[" + PagonMod.NAME + "]" + String.valueOf(class_124.field_1070);
   }
}
