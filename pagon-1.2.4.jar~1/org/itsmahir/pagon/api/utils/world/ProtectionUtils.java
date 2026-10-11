package org.itsmahir.pagon.api.utils.world;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_1297;
import net.minecraft.class_1303;
import net.minecraft.class_1511;
import net.minecraft.class_1542;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.features.modules.misc.autobreak.AutoBreak;

public class ProtectionUtils implements IMinecraft {
   public static List<class_2338> getSurroundPlacements(boolean selfTrap, boolean dynamic, boolean extend, boolean ignoreCrystals, boolean strictDirection) {
      class_2338 playerPos = PlayerUtils.getPlayerPos();
      List<class_2338> positions = getSurroundPositions(playerPos, dynamic, extend, ignoreCrystals, strictDirection);
      if (selfTrap && !mc.field_1724.method_20448()) {
         positions.addAll(getSurroundPositions(playerPos.method_10084(), dynamic, extend, ignoreCrystals, strictDirection));
      }

      return positions;
   }

   public static List<class_2338> getSurroundPositions(class_2338 pos, boolean dynamic, boolean extend, boolean ignoreCrystals, boolean strictDirection) {
      List<class_2338> entities = getSurroundEntities(mc.field_1724, pos, dynamic, extend, ignoreCrystals);
      List<class_2338> blocks = new CopyOnWriteArrayList();

      for(class_2338 epos : entities) {
         for(class_2350 dir2 : class_2350.values()) {
            if (dir2.method_10166().method_10179()) {
               class_2338 pos2 = epos.method_10081(dir2.method_10163());
               if (!entities.contains(pos2) && !blocks.contains(pos2)) {
                  double dist = mc.field_1724.method_5707(pos2.method_46558());
                  if (!(dist > (double)MathUtil.square(6.0F))) {
                     blocks.add(pos2);
                  }
               }
            }
         }
      }

      for(class_2338 block : blocks) {
         class_2350 direction = BlockUtils.getPlaceableSide(block, strictDirection);
         if (direction == null) {
            blocks.add(block.method_10074());
         }
      }

      if (AutoBreak.INSTANCE.mineDownTimer.isPassed()) {
         for(class_2338 entityPos : entities) {
            if (entityPos != pos) {
               blocks.add(entityPos.method_10074());
            }
         }
      }

      Collections.reverse(blocks);
      blocks.sort(Comparator.comparingInt((surroundPos) -> -mc.field_1687.method_8335((class_1297)null, new class_238(surroundPos)).stream().filter(CrystalUtil::isEndCrystal).toList().size()));
      return blocks;
   }

   public static List<class_2338> getAutoCityBlocks(class_1297 entity, class_2338 pos) {
      List<class_2338> entities = getSurroundEntities(entity, pos, true, false, true);
      List<class_2338> blocks = new CopyOnWriteArrayList();

      for(class_2338 epos : entities) {
         for(class_2350 dir2 : class_2350.values()) {
            if (dir2.method_10166().method_10179()) {
               class_2338 pos2 = epos.method_10081(dir2.method_10163());
               if (!entities.contains(pos2) && !blocks.contains(pos2)) {
                  double dist = mc.field_1724.method_5707(pos2.method_46558());
                  if (!(dist > (double)MathUtil.square(6.0F))) {
                     blocks.add(pos2);
                  }
               }
            }
         }
      }

      blocks.sort(Comparator.comparingDouble((mod) -> mc.field_1724.method_19538().method_1022(mod.method_46558())));
      return blocks;
   }

   public static List<class_2338> getSurroundEntities(class_1297 entity, boolean dynamic) {
      List<class_2338> entities = new LinkedList();
      entities.add(entity.method_24515());
      if (dynamic) {
         for(class_2350 dir : class_2350.values()) {
            if (dir.method_10166().method_10179()) {
               entities.addAll(BlockUtils.getAllInBox(entity.method_5829(), entity.method_24515()));
            }
         }
      }

      return entities;
   }

   public static List<class_2338> getSurroundEntities(class_1297 target, class_2338 pos, boolean dynamic, boolean extend, boolean overplace) {
      List<class_2338> entities = new LinkedList();
      entities.add(pos);
      if (extend || dynamic) {
         for(class_2350 dir : class_2350.values()) {
            if (dir.method_10166().method_10179()) {
               class_2338 pos1 = pos.method_10081(dir.method_10163());
               if (extend) {
                  List<class_1297> box = mc.field_1687.method_8335(!dynamic ? target : null, new class_238(pos1)).stream().filter((e) -> !isEntityBlockingSurround(e, overplace)).toList();
                  if (!box.isEmpty()) {
                     for(class_1297 entity : box) {
                        entities.addAll(BlockUtils.getAllInBox(entity.method_5829(), pos));
                     }
                  }
               } else {
                  entities.addAll(BlockUtils.getAllInBox(target.method_5829(), pos));
               }
            }
         }
      }

      return entities;
   }

   public static boolean isEntityBlockingSurround(class_1297 entity, boolean ignoreCrystals) {
      return entity instanceof class_1542 || entity instanceof class_1303 || entity instanceof class_1511 && !ignoreCrystals;
   }
}
