package org.itsmahir.pagon.api.management;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.world.EntityEvent;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Pair;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.PagonMod;

public class CrystalManager implements IMinecraft {
   public static CrystalManager INSTANCE;
   public static Map<class_2338, Pair.BoxPair> crystalBoxes = new ConcurrentHashMap();

   public CrystalManager() {
      PagonMod.EVENT_BUS.register(this);
   }

   @SubscribeEvent
   public void onTick(TickEvent.ClientTickEvent event) {
      if (NullUtils.nullCheck()) {
         crystalBoxes.clear();
      } else {
         for(class_1297 entity : mc.field_1687.method_18112()) {
            if (entity instanceof class_1511) {
               class_1511 crystal = (class_1511)entity;
               if (entity.method_5805() && !(mc.field_1724.method_5739(entity) > 16.0F) && !crystalBoxes.containsKey(crystal.method_24515())) {
                  crystalBoxes.put(entity.method_24515(), new Pair.BoxPair(entity.method_5829()));
               }
            }
         }

         long currentTime = System.currentTimeMillis();

         for(Map.Entry<class_2338, Pair.BoxPair> entry : crystalBoxes.entrySet()) {
            class_2338 pos = (class_2338)entry.getKey();
            Pair.BoxPair pair = (Pair.BoxPair)entry.getValue();
            if (mc.field_1724.method_19538().method_1025(pos.method_46558()) > (double)MathUtil.square(16.0F)) {
               crystalBoxes.remove(pos);
               return;
            }

            class_1511 crystal = null;

            for(class_1511 entity : mc.field_1687.method_18467(class_1511.class, (class_238)pair.key())) {
               if (entity.method_24515().equals(pos)) {
                  crystal = entity;
                  break;
               }
            }

            if (crystal == null && currentTime - (Long)pair.value() > 600L) {
               crystalBoxes.remove(pos);
            } else if (crystal != null) {
               crystalBoxes.put(pos, new Pair.BoxPair(crystal.method_5829()));
            }
         }

      }
   }

   @SubscribeEvent
   public void onEntityAdd(EntityEvent.Add event) {
      class_1297 var3 = event.getEntity();
      if (var3 instanceof class_1511 entity) {
         if (mc.field_1724.method_5739(entity) > 16.0F) {
            return;
         }

         crystalBoxes.put(entity.method_24515(), new Pair.BoxPair(entity.method_5829()));
      }

   }

   public boolean isRecentlyBlocked(class_2338 pos) {
      class_238 blockBox = new class_238(pos);

      for(class_1297 entity : mc.field_1687.method_18467(class_1297.class, blockBox)) {
         if (entity instanceof class_1511) {
            return true;
         }
      }

      for(Map.Entry<class_2338, Pair.BoxPair> entry : crystalBoxes.entrySet()) {
         Pair.BoxPair pair = (Pair.BoxPair)entry.getValue();
         class_238 box = (class_238)pair.key();
         if (box.method_994(blockBox)) {
            return true;
         }
      }

      return false;
   }
}
