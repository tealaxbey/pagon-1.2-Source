package org.itsmahir.pagon.api.management;

import java.util.Arrays;
import java.util.Objects;
import net.minecraft.class_2761;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.event.events.network.ServerEvent;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.impl.PagonMod;

public class TPSManager {
   private float[] tpsCounts = new float[10];
   private float ticksPerSecond = 20.0F;
   private int incPackets = 0;
   private int sendPackets = 0;
   private int time = 0;
   private long day = 0L;
   private final int NORMALTPS = 20;
   Timer packetTimer;
   public static TPSManager INSTANCE;
   private long lastUpdate = -1L;

   public TPSManager() {
      PagonMod.EVENT_BUS.register(this);
      this.packetTimer = new Timer();
      this.packetTimer.setDelay(1000L);
      Arrays.fill(this.tpsCounts, 20.0F);
   }

   @SubscribeEvent
   public void onReceivePacket(PacketEvent.Receive event) {
      if (event.getPacket() instanceof class_2761) {
         this.onTimeUpdate();
      }

      ++this.incPackets;
   }

   @SubscribeEvent
   public void onSendPacket(PacketEvent.Send event) {
      if (event.getPacket() instanceof class_2761) {
         this.onTimeUpdate();
      }

      ++this.sendPackets;
   }

   @SubscribeEvent
   public void onTick(TickEvent.ClientTickEvent event) {
      if (this.packetTimer.isPassed()) {
         this.incPackets = 0;
         this.sendPackets = 0;
         this.packetTimer.resetDelay();
      }

   }

   @SubscribeEvent
   public void onDisconnect(ServerEvent.ServerLeft event) {
      this.reset();
   }

   private int getNextStep(int time) {
      if (time > 23000) {
         return 24000;
      } else if (time > 13000) {
         return 23000;
      } else {
         return time > 12000 ? 13000 : 12000;
      }
   }

   public void reset() {
      this.ticksPerSecond = 20.0F;
   }

   public float getTickRate() {
      return this.ticksPerSecond;
   }

   public Long getLastResponse() {
      return this.lastUpdate;
   }

   public float getAverage() {
      float total = 0.0F;

      for(float j : this.tpsCounts) {
         total += j;
      }

      return total / (float)this.tpsCounts.length;
   }

   public void onTimeUpdate() {
      long currentTime = System.currentTimeMillis();
      if (this.lastUpdate == -1L) {
         this.lastUpdate = currentTime;
      } else {
         long timeDiff = currentTime - this.lastUpdate;
         float tickTime = (float)timeDiff / 20.0F;
         if (tickTime == 0.0F) {
            tickTime = 50.0F;
         }

         float tps = 1000.0F / tickTime;
         System.arraycopy(this.tpsCounts, 0, this.tpsCounts, 1, this.tpsCounts.length - 1);
         this.tpsCounts[0] = tps;
         this.ticksPerSecond = tps;
         this.lastUpdate = currentTime;
      }
   }

   public String getTimeOverTps() {
      if (this.time == 0) {
         return "0/0";
      } else {
         int var10000 = this.time / 20;
         return var10000 + "/" + this.getNextStep(this.time) / 20;
      }
   }

   public String getTimePhase() {
      if (this.time > 23000) {
         return "Dawn";
      } else if (this.time > 18500) {
         return "Night";
      } else if (this.time > 17500) {
         return "Midnight";
      } else if (this.time > 13000) {
         return "Evening";
      } else if (this.time > 12000) {
         return "Dusk";
      } else if (this.time > 6500) {
         return "Afternoon";
      } else {
         return this.time > 5500 ? "Noon" : "Morning";
      }
   }

   public float[] getTpsCounts() {
      return this.tpsCounts;
   }

   public float getTicksPerSecond() {
      return this.ticksPerSecond;
   }

   public int getIncPackets() {
      return this.incPackets;
   }

   public int getSendPackets() {
      return this.sendPackets;
   }

   public int getTime() {
      return this.time;
   }

   public int getNORMALTPS() {
      Objects.requireNonNull(this);
      return 20;
   }

   public Timer getPacketTimer() {
      return this.packetTimer;
   }

   public long getLastUpdate() {
      return this.lastUpdate;
   }

   public long getDay() {
      return this.day;
   }
}
