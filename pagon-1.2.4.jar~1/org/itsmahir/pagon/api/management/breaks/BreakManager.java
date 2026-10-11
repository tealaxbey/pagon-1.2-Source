package org.itsmahir.pagon.api.management.breaks;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.class_2338;
import net.minecraft.class_2596;
import net.minecraft.class_2620;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.management.breaks.data.BreakData;
import org.itsmahir.pagon.api.management.breaks.data.BreakEntry;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.PagonMod;

public class BreakManager implements IMinecraft {
   public static BreakManager INSTANCE;
   private final ConcurrentHashMap<Integer, BreakEntry> breakPositions = new ConcurrentHashMap();

   public BreakManager() {
      PagonMod.EVENT_BUS.register(this);
   }

   @SubscribeEvent
   public void onTick(TickEvent.ClientTickEvent event) {
      if (NullUtils.nullCheck()) {
         this.breakPositions.clear();
      } else {
         for(Map.Entry<Integer, BreakEntry> entry : this.breakPositions.entrySet()) {
            BreakEntry breakEntry = (BreakEntry)entry.getValue();
            ((BreakEntry)entry.getValue()).tick();
            if (breakEntry.getExtraBreak() == null && breakEntry.getNormalBreak() == null) {
               this.breakPositions.remove(entry.getKey());
            }
         }

      }
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Receive event) {
      if (!NullUtils.nullCheck()) {
         class_2596 var3 = event.getPacket();
         if (var3 instanceof class_2620) {
            class_2620 packet = (class_2620)var3;
            BreakEntry data = (BreakEntry)this.breakPositions.computeIfAbsent(packet.method_11280(), (k) -> new BreakEntry());
            data.startMining(packet.method_11277());
         }

      }
   }

   public boolean isBreaking(class_2338 pos) {
      return this.getBreakData(pos) != null;
   }

   public boolean isPassed(class_2338 pos, float progress, boolean canRender) {
      BreakData data = this.getBreakData(pos);
      if (data == null) {
         return false;
      } else if (canRender && !data.canRender()) {
         return false;
      } else {
         return data.getBestDamage() >= progress;
      }
   }

   public BreakData getBreakData(class_2338 pos) {
      for(Map.Entry<Integer, BreakEntry> entry : this.breakPositions.entrySet()) {
         BreakEntry breakEntry = (BreakEntry)entry.getValue();
         BreakData extra = breakEntry.getExtraBreak();
         BreakData data = breakEntry.getNormalBreak();
         if (extra != null && extra.getPos().equals(pos)) {
            return extra;
         }

         if (data != null && data.getPos().equals(pos)) {
            return data;
         }
      }

      return null;
   }

   public List<BreakData> getBreakDatas() {
      List<BreakData> datas = new LinkedList();

      for(Map.Entry<Integer, BreakEntry> entry : this.breakPositions.entrySet()) {
         BreakEntry breakEntry = (BreakEntry)entry.getValue();
         BreakData extra = breakEntry.getExtraBreak();
         BreakData data = breakEntry.getNormalBreak();
         if (extra != null) {
            datas.add(extra);
         }

         if (data != null) {
            datas.add(data);
         }
      }

      return datas;
   }
}
