package org.itsmahir.pagon.api.utils.world;

import io.netty.buffer.Unpooled;
import net.minecraft.class_1297;
import net.minecraft.class_2540;
import net.minecraft.class_2824;
import org.itsmahir.pagon.api.wrapper.IMinecraft;

public class PacketUtils implements IMinecraft {
   public static InteractType getInteractType(class_2824 packet) {
      class_2540 packetBuf = new class_2540(Unpooled.buffer());
      packet.method_55976(packetBuf);
      packetBuf.method_10816();
      return (InteractType)packetBuf.method_10818(InteractType.class);
   }

   public static class_1297 getEntity(class_2824 packet) {
      class_2540 packetBuf = new class_2540(Unpooled.buffer());
      packet.method_55976(packetBuf);
      return mc.field_1687.method_8469(packetBuf.method_10816());
   }

   public static enum InteractType {
      INTERACT,
      ATTACK,
      INTERACT_AT;

      // $FF: synthetic method
      private static InteractType[] $values() {
         return new InteractType[]{INTERACT, ATTACK, INTERACT_AT};
      }
   }
}
