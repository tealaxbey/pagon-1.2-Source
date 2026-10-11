package org.itsmahir.pagon.impl.features.modules.player;

import net.minecraft.class_2596;
import net.minecraft.class_2708;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.mixin.accessor.IPlayerPositionLookS2CPacket;

public class NoRotate extends Module {
   public static NoRotate INSTANCE;

   public NoRotate() {
      super("NoRotate", Feature.Category.Player);
      INSTANCE = this;
      super.setEnabled(true);
   }

   @SubscribeEvent
   public void onPacketReceive(PacketEvent.Receive event) {
      class_2596 var3 = event.getPacket();
      if (var3 instanceof class_2708 packet) {
         IPlayerPositionLookS2CPacket accessor = (IPlayerPositionLookS2CPacket)packet;
         accessor.setYaw(mc.field_1724.method_36454());
         accessor.setPitch(mc.field_1724.method_36455());
      }

   }

   public String getDescription() {
      return "Prevents server from forcing client rotations";
   }
}
