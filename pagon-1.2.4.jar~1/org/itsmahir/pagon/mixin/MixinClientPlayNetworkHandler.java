package org.itsmahir.pagon.mixin;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import net.minecraft.class_243;
import net.minecraft.class_2535;
import net.minecraft.class_2596;
import net.minecraft.class_2600;
import net.minecraft.class_2602;
import net.minecraft.class_2664;
import net.minecraft.class_2672;
import net.minecraft.class_2678;
import net.minecraft.class_2708;
import net.minecraft.class_2709;
import net.minecraft.class_2743;
import net.minecraft.class_2793;
import net.minecraft.class_2828;
import net.minecraft.class_310;
import net.minecraft.class_634;
import net.minecraft.class_638;
import net.minecraft.class_746;
import net.minecraft.class_7633;
import net.minecraft.class_7648;
import net.minecraft.class_8042;
import net.minecraft.class_8673;
import net.minecraft.class_8675;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.event.events.network.ServerEvent;
import org.itsmahir.pagon.api.event.events.world.ChunkDataEvent;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.utils.ducks.IClientPlayNetworkHandler;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.mixin.accessor.IClientConnection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_634.class})
public abstract class MixinClientPlayNetworkHandler extends class_8673 implements class_2602, class_7633, IClientPlayNetworkHandler, IMinecraft {
   @Shadow
   private class_638 field_3699;
   @Unique
   private boolean worldNotNull;

   @Shadow
   public abstract void method_54134();

   @Shadow
   public abstract class_2535 method_48296();

   public MixinClientPlayNetworkHandler(class_310 client, class_2535 clientConnection, class_8675 clientConnectionState) {
      super(client, clientConnection, clientConnectionState);
   }

   @Inject(
      method = {"onGameJoin"},
      at = {@At("HEAD")}
   )
   private void onGameJoinHead(class_2678 packet, CallbackInfo info) {
      this.worldNotNull = this.field_3699 != null;
   }

   @Inject(
      method = {"onChunkData"},
      at = {@At("RETURN")}
   )
   public void onChunkData(class_2672 packet, CallbackInfo ci, @Share("seenChunk") LocalBooleanRef seenChunkRef) {
      (new ChunkDataEvent(this.field_3699.method_8497(packet.method_11523(), packet.method_11524()), seenChunkRef.get())).post();
   }

   @Inject(
      method = {"onGameJoin"},
      at = {@At("TAIL")}
   )
   private void onGameJoinTail(class_2678 packet, CallbackInfo info) {
      if (this.worldNotNull) {
         (new ServerEvent.ServerLeft()).post();
      }

      (new ServerEvent.ServerJoined()).post();
   }

   @Overwrite
   public void method_48294(class_8042 packet) {
      class_2600.method_11074(packet, this, this.field_45588);

      for(class_2596<? super class_2602> packet2 : packet.method_48324()) {
         PacketEvent.Receive event = new PacketEvent.Receive(packet2, packet);
         event.post();
         if (!event.isCancelled()) {
            packet2.method_11054(this);
         }
      }

   }

   @Inject(
      method = {"onEntityVelocityUpdate"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/Entity;setVelocityClient(DDD)V",
   shift = Shift.BEFORE
)},
      cancellable = true
   )
   public void onEntityVelocityPacket(class_2743 packet, CallbackInfo ci) {
      if (PacketManager.CANCELATHANDLE.contains(packet)) {
         PacketManager.CANCELATHANDLE.remove(packet);
         ci.cancel();
      }

   }

   @Inject(
      method = {"onExplosion"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/ClientPlayerEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V",
   shift = Shift.BEFORE
)},
      cancellable = true
   )
   public void onEntityVelocityPacket(class_2664 packet, CallbackInfo ci) {
      if (PacketManager.CANCELATHANDLE.contains(packet)) {
         PacketManager.CANCELATHANDLE.remove(packet);
         ci.cancel();
      }

   }

   @Overwrite
   public void method_11157(class_2708 packet) {
      class_2600.method_11074(packet, this, this.field_45588);
      class_746 playerEntity = this.field_45588.field_1724;
      class_243 vec3d = playerEntity.method_18798();
      boolean bl = packet.method_11733().contains(class_2709.field_12400);
      boolean bl2 = packet.method_11733().contains(class_2709.field_12398);
      boolean bl3 = packet.method_11733().contains(class_2709.field_12403);
      double e;
      double d;
      if (bl) {
         d = vec3d.method_10216();
         e = playerEntity.method_23317() + packet.method_11734();
         playerEntity.field_6038 += packet.method_11734();
         playerEntity.field_6014 += packet.method_11734();
      } else {
         d = (double)0.0F;
         playerEntity.field_6038 = e = packet.method_11734();
         playerEntity.field_6014 = e;
      }

      double g;
      double f;
      if (bl2) {
         f = vec3d.method_10214();
         g = playerEntity.method_23318() + packet.method_11735();
         playerEntity.field_5971 += packet.method_11735();
         playerEntity.field_6036 += packet.method_11735();
      } else {
         f = (double)0.0F;
         playerEntity.field_5971 = g = packet.method_11735();
         playerEntity.field_6036 = g;
      }

      double i;
      double h;
      if (bl3) {
         h = vec3d.method_10215();
         i = playerEntity.method_23321() + packet.method_11738();
         playerEntity.field_5989 += packet.method_11738();
         playerEntity.field_5969 += packet.method_11738();
      } else {
         h = (double)0.0F;
         playerEntity.field_5989 = i = packet.method_11738();
         playerEntity.field_5969 = i;
      }

      playerEntity.method_5814(e, g, i);
      playerEntity.method_18800(d, f, h);
      float j = packet.method_11736();
      float k = packet.method_11739();
      if (packet.method_11733().contains(class_2709.field_12397)) {
         float pitch = RotationUtils.getActualPitch() + k;
         playerEntity.method_36457(playerEntity.method_36455() + k);
         playerEntity.field_6004 += k;
      } else {
         playerEntity.method_36457(k);
         playerEntity.field_6004 = k;
      }

      if (packet.method_11733().contains(class_2709.field_12401)) {
         float yaw = playerEntity.method_36454() + j;
         playerEntity.method_36456(playerEntity.method_36454() + j);
         playerEntity.field_5982 += j;
      } else {
         playerEntity.method_36456(j);
         playerEntity.field_5982 = j;
      }

      this.field_45589.method_10743(new class_2793(packet.method_11737()));
      this.field_45589.method_10743(new class_2828.class_2830(playerEntity.method_23317(), playerEntity.method_23318(), playerEntity.method_23321(), playerEntity.method_36454(), playerEntity.method_36455(), false));
   }

   public void sendQuietPacket(class_2596<?> packet) {
      ((IClientConnection)this.method_48296()).sendQuietPacket(packet, (class_7648)null, true);
   }
}
