package org.itsmahir.pagon.api.management;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.class_2596;
import net.minecraft.class_2792;
import net.minecraft.class_639;
import net.minecraft.class_640;
import net.minecraft.class_642;
import net.minecraft.class_7202;
import net.minecraft.class_7204;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.network.ServerEvent;
import org.itsmahir.pagon.api.utils.ducks.IClientPlayNetworkHandler;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.PagonMod;
import org.itsmahir.pagon.mixin.accessor.IClientWorld;

public class PacketManager implements IMinecraft {
   public static PacketManager INSTANCE;
   private static final Set<class_2596<?>> PACKET_CACHE = new HashSet();
   private class_639 address;
   public static final Set<class_2596<?>> CANCELATHANDLE = new HashSet();
   private class_642 info;

   public PacketManager() {
      PagonMod.EVENT_BUS.register(this);
   }

   @SubscribeEvent
   public void onDisconnect(ServerEvent.ServerLeft event) {
      PACKET_CACHE.clear();
   }

   public void sendPacket(class_2596<?> p) {
      if (mc.method_1562() != null) {
         PACKET_CACHE.add(p);
         mc.method_1562().method_52787(p);
      }

   }

   public void sendQuietPacket(class_2596<?> p) {
      if (mc.method_1562() != null) {
         PACKET_CACHE.add(p);
         ((IClientPlayNetworkHandler)mc.method_1562()).sendQuietPacket(p);
      }

   }

   public void specialCaseCancel(class_2596<?> packet) {
      CANCELATHANDLE.add(packet);
   }

   public void sendPacket(class_7204 p) {
      if (mc.field_1687 != null) {
         class_7202 updater = ((IClientWorld)mc.field_1687).accquirePendingUpdateManager().method_41937();

         try {
            int i = updater.method_41942();
            class_2596<class_2792> packet = p.predict(i);
            this.sendPacket(packet);
         } catch (Throwable var6) {
            var6.printStackTrace();
            if (updater != null) {
               try {
                  updater.close();
               } catch (Throwable e1) {
                  e1.printStackTrace();
                  var6.addSuppressed(e1);
               }
            }

            throw var6;
         }

         if (updater != null) {
            updater.close();
         }
      }

   }

   public int getClientLatency() {
      if (mc.method_1562() != null) {
         class_640 playerEntry = mc.method_1562().method_2871(mc.field_1724.method_5667());
         if (playerEntry != null) {
            return playerEntry.method_2959();
         }
      }

      return 0;
   }

   public class_639 getAddress() {
      return this.address;
   }

   public void setAddress(class_639 address) {
      this.address = address;
   }

   public class_642 getInfo() {
      return this.info;
   }

   public void setInfo(class_642 info) {
      this.info = info;
   }

   public boolean isCrystalPvpCC() {
      if (this.info == null) {
         return false;
      } else {
         return this.info.field_3761.equalsIgnoreCase("us.crystalpvp.cc") || this.info.field_3761.equalsIgnoreCase("crystalpvp.cc");
      }
   }

   public boolean isGrimCC() {
      return this.info != null && this.info.field_3761.equalsIgnoreCase("grim.crystalpvp.cc");
   }

   public boolean isCached(class_2596<?> p) {
      return PACKET_CACHE.contains(p);
   }

   public boolean uncache(class_2596<?> p) {
      return PACKET_CACHE.remove(p);
   }
}
