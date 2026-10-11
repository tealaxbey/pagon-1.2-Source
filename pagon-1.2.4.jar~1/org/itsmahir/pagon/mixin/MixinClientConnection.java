package org.itsmahir.pagon.mixin;

import net.minecraft.class_2535;
import net.minecraft.class_2547;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.event.events.network.ServerEvent;
import org.itsmahir.pagon.api.management.PacketManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_2535.class})
public class MixinClientConnection {
   @Inject(
      method = {"handlePacket"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static <T extends class_2547> void onHandlePacket(class_2596<T> packet, class_2547 listener, CallbackInfo info) {
      PacketEvent event = new PacketEvent.Receive(packet);
      event.post();
      if (event.isCancelled()) {
         info.cancel();
      }

   }

   @Inject(
      method = {"handlePacket"},
      at = {@At("TAIL")},
      cancellable = true
   )
   private static <T extends class_2547> void onHaneTail(class_2596<T> packet, class_2547 listener, CallbackInfo info) {
      (new PacketEvent.ReceivePost(packet)).post();
   }

   @Inject(
      at = {@At("HEAD")},
      method = {"send(Lnet/minecraft/network/packet/Packet;)V"},
      cancellable = true
   )
   private void onSendPacketHead(class_2596<?> packet, CallbackInfo info) {
      PacketEvent event = new PacketEvent.Send(packet);
      event.post();
      if (event.isCancelled()) {
         info.cancel();
      }

   }

   @Inject(
      at = {@At("RETURN")},
      method = {"send(Lnet/minecraft/network/packet/Packet;)V"}
   )
   private void onSendTail(class_2596<?> packet, CallbackInfo info) {
      (new PacketEvent.SendPost(packet)).post();
      if (PacketManager.INSTANCE.isCached(packet)) {
         PacketManager.INSTANCE.uncache(packet);
      }

   }

   @Inject(
      method = {"disconnect"},
      at = {@At("HEAD")}
   )
   private void hookDisconnect(class_2561 disconnectReason, CallbackInfo ci) {
      ServerEvent.ServerLeft event = new ServerEvent.ServerLeft();
      event.post();
   }
}
