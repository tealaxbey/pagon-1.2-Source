package org.itsmahir.pagon.api.management;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_2868;
import net.minecraft.class_3532;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.LivingEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.key.InputEvent;
import org.itsmahir.pagon.api.event.events.move.LookEvent;
import org.itsmahir.pagon.api.event.events.move.MovementPacketsEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.utils.ducks.IClientPlayerEntity;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.rotation.Rotation;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.PagonMod;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;
import org.itsmahir.pagon.impl.features.modules.exploit.GrimDisabler;

public class RotationManager implements IMinecraft {
   public static RotationManager INSTANCE;
   public final List<Rotation> requests = new ArrayList();
   private float serverYaw;
   private float serverPitch;
   private float lastServerYaw;
   private float lastServerPitch;
   private float prevJumpYaw;
   private float prevYaw;
   private float prevPitch;
   boolean rotate;
   public int serverSlot = -1;
   public boolean FROM_INV = false;
   public static final List<InventoryUtils.PreSwapData> swapData = new CopyOnWriteArrayList();
   private Rotation rotation;
   private int rotateTicks;
   private static final List<Runnable> afterTick = new ArrayList();

   public RotationManager() {
      PagonMod.EVENT_BUS.register(this);
   }

   @SubscribeEvent(1)
   public void onPacketOutbound(PacketEvent.Send event) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         class_2596 packetYaw = event.getPacket();
         if (packetYaw instanceof class_2828) {
            class_2828 packet = (class_2828)packetYaw;
            if (packet.method_36172()) {
               float packetYaw = packet.method_12271(0.0F);
               float packetPitch = packet.method_12270(0.0F);
               this.serverYaw = packetYaw;
               this.serverPitch = packetPitch;
            }
         }

         packetYaw = event.getPacket();
         if (packetYaw instanceof class_2868) {
            class_2868 packet = (class_2868)packetYaw;
            if (this.serverSlot == packet.method_12442()) {
               event.setCancelled(true);
            }

            this.serverSlot = packet.method_12442();
         }

      }
   }

   public static void scheduleNextTick(Runnable callback) {
      afterTick.add(callback);
   }

   @SubscribeEvent
   public void playerTickEvent(TickEvent.PlayerTickEvent.Post event) {
      for(Runnable callback : afterTick) {
         callback.run();
      }

      afterTick.clear();
   }

   public void onUpdate() {
      if (this.requests.isEmpty()) {
         this.rotation = null;
      } else {
         Rotation request = this.getRotationRequest();
         if (request == null) {
            this.rotation = null;
         } else {
            this.rotation = request;
            if (this.rotation != null) {
               this.rotate = true;
            }
         }
      }
   }

   @SubscribeEvent(1)
   public void onMovementPackets(MovementPacketsEvent event) {
      if (this.rotation != null) {
         if (this.rotate) {
            this.removeRotation(this.rotation);
            event.setCancelled(true);
            event.setYaw(this.rotation.getYaw());
            event.setPitch(this.rotation.getPitch());
            this.rotate = false;
         }

         if (this.rotation.isSnap()) {
            this.rotation = null;
         }
      }

   }

   @SubscribeEvent
   public void onTick(TickEvent.ClientTickEvent event) {
      swapData.removeIf(InventoryUtils.PreSwapData::isPassedClearTime);
   }

   @SubscribeEvent
   public void onMovementTick(TickEvent.MovementTickEvent.Post event) {
      this.lastServerYaw = ((IClientPlayerEntity)mc.field_1724).getLastSpoofedYaw();
      this.lastServerPitch = ((IClientPlayerEntity)mc.field_1724).getLastSpoofedPitch();
   }

   @SubscribeEvent
   public void onKeyboardTick(InputEvent event) {
      if (this.rotation != null && mc.field_1724 != null && (Boolean)AntiCheat.INSTANCE.strafeFix.getValue() && !GrimDisabler.INSTANCE.isEnabled()) {
         if (PagonMod.isBaritonePaused()) {
            return;
         }

         float forward = mc.field_1724.field_3913.field_3905;
         float sideways = mc.field_1724.field_3913.field_3907;
         float delta = (mc.field_1724.method_36454() - this.rotation.getYaw()) * ((float)Math.PI / 180F);
         float cos = class_3532.method_15362(delta);
         float sin = class_3532.method_15374(delta);
         mc.field_1724.field_3913.field_3907 = (float)Math.round(sideways * cos - forward * sin);
         mc.field_1724.field_3913.field_3905 = (float)Math.round(forward * cos + sideways * sin);
      }

   }

   @SubscribeEvent
   public void onUpdateVelocity(LookEvent.LookVelocityEvent event) {
      if (!PagonMod.isBaritonePaused()) {
         if (this.rotation != null && mc.field_1724 != null && (Boolean)AntiCheat.INSTANCE.strafeFix.getValue() && !GrimDisabler.INSTANCE.isEnabled()) {
            event.setVelocity(this.movementInputToVelocity(this.rotation.getYaw(), event.getMovementInput(), event.getSpeed()));
            event.setCancelled(true);
         }

      }
   }

   @SubscribeEvent
   public void onPlayerJump(LivingEvent.Jump event) {
      if (!PagonMod.isBaritonePaused()) {
         if (this.rotation != null && (Boolean)AntiCheat.INSTANCE.strafeFix.getValue()) {
            event.setYaw(this.rotation.getYaw());
         }
      }
   }

   public final class_243 getRotationVector() {
      if (this.rotation == null) {
         return null;
      } else {
         float f = this.rotation.getPitch() * ((float)Math.PI / 180F);
         float g = -this.rotation.getYaw() * ((float)Math.PI / 180F);
         float h = class_3532.method_15362(g);
         float i = class_3532.method_15374(g);
         float j = class_3532.method_15362(f);
         float k = class_3532.method_15374(f);
         return new class_243((double)(i * j), (double)(-k), (double)(h * j));
      }
   }

   public void rotateTo(Rotation in) {
      Rotation rotation = in.fix();
      if (rotation.getPriority() == Integer.MAX_VALUE) {
         this.rotation = rotation;
      }

      Rotation request = (Rotation)this.requests.stream().filter((r) -> rotation.getPriority() == r.getPriority()).findFirst().orElse((Object)null);
      if (request == null) {
         this.requests.add(rotation);
      } else {
         request.setYaw(rotation.getYaw());
         request.setPitch(rotation.getPitch());
      }

   }

   public void setRotationClient(float yaw, float pitch) {
      if (mc.field_1724 != null) {
         mc.field_1724.method_36456(yaw);
         mc.field_1724.method_36457(pitch);
      }
   }

   public boolean removeRotation(Rotation request) {
      return this.requests.remove(request);
   }

   public boolean isRotationBlocked(int priority) {
      return this.rotation != null && priority < this.rotation.getPriority();
   }

   public boolean isRotating() {
      return this.rotation != null || !this.requests.isEmpty();
   }

   public float getRotationYaw() {
      return this.rotation == null ? mc.field_1724.method_36454() : this.rotation.getYaw();
   }

   public float getRotationPitch() {
      return this.rotation == null ? mc.field_1724.method_36455() : this.rotation.getPitch();
   }

   public float getWrappedYaw() {
      return class_3532.method_15393(this.serverYaw);
   }

   private class_243 movementInputToVelocity(float yaw, class_243 movementInput, float speed) {
      double d = movementInput.method_1027();
      if (d < 1.0E-7) {
         return class_243.field_1353;
      } else {
         class_243 vec3d = (d > (double)1.0F ? movementInput.method_1029() : movementInput).method_1021((double)speed);
         float f = class_3532.method_15374(yaw * ((float)Math.PI / 180F));
         float g = class_3532.method_15362(yaw * ((float)Math.PI / 180F));
         return new class_243(vec3d.field_1352 * (double)g - vec3d.field_1350 * (double)f, vec3d.field_1351, vec3d.field_1350 * (double)g + vec3d.field_1352 * (double)f);
      }
   }

   public Rotation getRotationRequest() {
      Rotation rotationRequest = null;
      int priority = 0;

      for(Rotation request : this.requests) {
         if (request.getPriority() > priority) {
            rotationRequest = request;
            priority = request.getPriority();
         }
      }

      return rotationRequest;
   }

   public float getServerYaw() {
      return this.serverYaw;
   }

   public float getServerPitch() {
      return this.serverPitch;
   }

   public float getLastServerYaw() {
      return this.lastServerYaw;
   }

   public float getLastServerPitch() {
      return this.lastServerPitch;
   }

   public float getPrevJumpYaw() {
      return this.prevJumpYaw;
   }

   public float getPrevYaw() {
      return this.prevYaw;
   }

   public float getPrevPitch() {
      return this.prevPitch;
   }

   public Rotation getRotation() {
      return this.rotation;
   }
}
