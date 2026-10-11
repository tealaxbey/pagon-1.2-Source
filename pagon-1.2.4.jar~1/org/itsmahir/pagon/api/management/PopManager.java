package org.itsmahir.pagon.api.management;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_2596;
import net.minecraft.class_2663;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.LivingEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.event.events.player.PopEvent;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.PagonMod;

public class PopManager implements IMinecraft {
   public static PopManager INSTANCE;
   public final Object2IntOpenHashMap<String> registry = new Object2IntOpenHashMap();

   public PopManager() {
      PagonMod.EVENT_BUS.register(this);
   }

   @SubscribeEvent(1)
   public void onPacket(PacketEvent.Receive event) {
      class_2596 var3 = event.getPacket();
      if (var3 instanceof class_2663 packet) {
         if (packet.method_11470() == 35) {
            class_1297 entity = packet.method_11469(mc.field_1687);
            if (entity != null) {
               this.onPop(entity);
            }
         }
      }

   }

   @SubscribeEvent
   private void onDeath(PacketEvent.Receive event) {
      if (!NullUtils.nullCheck()) {
         class_2596 var3 = event.getPacket();
         if (var3 instanceof class_2663) {
            class_2663 pac = (class_2663)var3;
            if (pac.method_11470() == 3) {
               class_1297 entity = pac.method_11469(mc.field_1687);
               if (entity != null) {
                  (new LivingEvent.Death(entity)).post();
               }
            }
         }

      }
   }

   public void onPop(class_1297 entity) {
      if (!NullUtils.nullCheck()) {
         String name = entity.method_5477().getString();
         this.registry.put(name, this.registry.getInt(name) + 1);
         (new PopEvent.TotemPopEvent(entity, this.registry.getInt(name))).post();
      }
   }

   public void onDeath(class_1657 player) {
      if (!NullUtils.nullCheck()) {
         String name = player.method_5477().getString();
         if (this.registry.containsKey(name)) {
            int pops = this.registry.getInt(name);
            this.registry.removeInt(name);
            (new PopEvent.DeathPopEvent(player, pops)).post();
         }

      }
   }

   @SubscribeEvent
   public void onTick(TickEvent.AfterClientTickEvent e) {
      if (!NullUtils.nullCheck()) {
         for(class_1297 player : mc.field_1687.method_18112()) {
            if (player instanceof class_1657 && !(((class_1657)player).method_6032() > 0.0F)) {
               this.onDeath((class_1657)player);
            }
         }

      }
   }

   public int getPops(class_1297 entity) {
      return this.registry.getInt(entity.method_5477().getString());
   }

   public int getPops(String name) {
      return this.registry.getInt(name);
   }

   public void clearPops(String name) {
      if (this.registry.containsKey(name)) {
         this.registry.put(name, 0);
      }

   }
}
