package org.itsmahir.pagon.api.management;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_238;
import net.minecraft.class_2596;
import net.minecraft.class_2739;
import net.minecraft.class_2945;
import net.minecraft.class_4050;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.PagonMod;

public class HitboxManager implements IMinecraft {
   public static HitboxManager INSTANCE;
   private final List<class_1297> serverCrawling = new CopyOnWriteArrayList();

   public HitboxManager() {
      PagonMod.EVENT_BUS.register(this);
   }

   @SubscribeEvent
   public void onPacketInbound(PacketEvent.Receive event) {
      if (!NullUtils.nullCheck()) {
         class_2596 var3 = event.getPacket();
         if (var3 instanceof class_2739) {
            class_2739 packet = (class_2739)var3;
            class_1297 entity = mc.field_1687.method_8469(packet.comp_1127());
            if (!(entity instanceof class_1657)) {
               return;
            }

            for(class_2945.class_7834<?> serializedEntry : packet.comp_1128()) {
               class_2945.class_2946<?> entry = entity.method_5841().field_13331[serializedEntry.comp_1115()];
               if (entry.method_12797().equals(class_1297.field_18064)) {
                  if (serializedEntry.comp_1117().equals(class_4050.field_18079)) {
                     if (!this.serverCrawling.contains(entity)) {
                        this.serverCrawling.add(entity);
                     }
                  } else {
                     this.serverCrawling.remove(entity);
                  }
               }
            }
         }

      }
   }

   public boolean isServerCrawling(class_1297 entity) {
      return this.serverCrawling.contains(entity);
   }

   public class_238 getCrawlingBoundingBox(class_1297 entity) {
      return entity.method_18377(class_4050.field_18079).method_30757(entity.method_19538());
   }
}
