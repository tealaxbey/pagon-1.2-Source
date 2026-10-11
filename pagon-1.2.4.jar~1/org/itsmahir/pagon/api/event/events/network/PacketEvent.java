package org.itsmahir.pagon.api.event.events.network;

import net.minecraft.class_2596;
import net.minecraft.class_8042;
import org.itsmahir.pagon.api.event.Event;

public class PacketEvent extends Event {
   private class_2596<?> packet;

   public PacketEvent(class_2596<?> packet) {
      this.packet = packet;
   }

   public class_2596<?> getPacket() {
      return this.packet;
   }

   public static class Receive extends PacketEvent {
      class_8042 bundleS2CPacket;

      public Receive(class_2596<?> packet) {
         super(packet);
         this.bundleS2CPacket = null;
      }

      public Receive(class_2596<?> packet, class_8042 bundleS2CPacket) {
         super(packet);
         this.bundleS2CPacket = bundleS2CPacket;
      }

      public boolean isBundled() {
         return this.bundleS2CPacket != null;
      }

      public class_8042 getBundleS2CPacket() {
         return this.bundleS2CPacket;
      }
   }

   public static class ReceivePost extends PacketEvent {
      public ReceivePost(class_2596<?> packet) {
         super(packet);
      }
   }

   public static class Send extends PacketEvent {
      public Send(class_2596<?> packet) {
         super(packet);
      }
   }

   public static class SendPost extends PacketEvent {
      public SendPost(class_2596<?> packet) {
         super(packet);
      }
   }
}
