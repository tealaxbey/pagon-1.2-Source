package org.itsmahir.pagon.api.utils.chat;

public class ChatMessage {
   int messageID;
   boolean override;
   String text;

   public ChatMessage(String text, boolean override, int messageID) {
      this.text = text;
      this.override = override;
      this.messageID = messageID;
   }

   public boolean doesOverride() {
      return this.override;
   }

   public int getMessageID() {
      return this.messageID;
   }

   public boolean isOverride() {
      return this.override;
   }

   public String getText() {
      return this.text;
   }
}
