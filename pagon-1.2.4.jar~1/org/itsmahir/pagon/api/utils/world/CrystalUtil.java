package org.itsmahir.pagon.api.utils.world;

import java.util.List;
import java.util.function.BiFunction;
import net.minecraft.class_1280;
import net.minecraft.class_1282;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1511;
import net.minecraft.class_1738;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1890;
import net.minecraft.class_1893;
import net.minecraft.class_1922;
import net.minecraft.class_1927;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_3965;
import net.minecraft.class_5134;
import net.minecraft.class_6880;
import org.apache.commons.lang3.mutable.MutableInt;
import org.itsmahir.pagon.api.management.breaks.BreakManager;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.features.modules.combat.AutoCrystal;
import org.itsmahir.pagon.impl.features.modules.misc.autobreak.AutoBreak;

public class CrystalUtil implements IMinecraft {
   public static boolean canPlaceCrystal(class_2338 pos, boolean protocol) {
      class_2680 state = mc.field_1687.method_8320(pos);
      if (!state.method_26204().equals(class_2246.field_10540) && !state.method_26204().equals(class_2246.field_9987)) {
         return false;
      } else {
         class_2680 crystal = mc.field_1687.method_8320(pos.method_10084());
         class_2680 above = mc.field_1687.method_8320(pos.method_10086(2));
         if (crystal.method_26204() != class_2246.field_10124) {
            return false;
         } else {
            return !protocol || above.method_26204() == class_2246.field_10124;
         }
      }
   }

   public static boolean canPlaceCrystalServer(class_2338 block) {
      class_2680 blockState = mc.field_1687.method_8320(block);
      if (!blockState.method_27852(class_2246.field_10540) && !blockState.method_27852(class_2246.field_9987)) {
         return false;
      } else {
         class_2338 blockPos2 = block.method_10084();
         if (!mc.field_1687.method_22347(blockPos2)) {
            return false;
         } else {
            double d = (double)blockPos2.method_10263();
            double e = (double)blockPos2.method_10264();
            double f = (double)blockPos2.method_10260();
            List<class_1297> list = mc.field_1687.method_8335((class_1297)null, new class_238(d, e, f, d + (double)1.0F, e + (double)2.0F, f + (double)1.0F));
            return list.isEmpty();
         }
      }
   }

   public static boolean canPlaceCrystalAir(class_2338 block) {
      class_2338 blockPos2 = block.method_10084();
      if (!mc.field_1687.method_22347(blockPos2)) {
         return false;
      } else {
         double d = (double)blockPos2.method_10263();
         double e = (double)blockPos2.method_10264();
         double f = (double)blockPos2.method_10260();
         List<class_1297> list = mc.field_1687.method_8335((class_1297)null, new class_238(d, e, f, d + (double)1.0F, e + (double)2.0F, f + (double)1.0F));
         return list.isEmpty();
      }
   }

   public static boolean canPlaceCrystalIgnore(class_2338 pos, boolean protocol) {
      class_2680 state = mc.field_1687.method_8320(pos);
      if (!state.method_26204().equals(class_2246.field_10540) && !state.method_26204().equals(class_2246.field_9987)) {
         return false;
      } else {
         class_2680 above = mc.field_1687.method_8320(pos.method_10086(2));
         return !protocol || above.method_26204() == class_2246.field_10124;
      }
   }

   public static float calculateDamage(class_1309 entity, class_243 crystal, boolean ignoreTerrain, boolean miningIgnore) {
      double maxDist = (double)12.0F;
      double dist = Math.sqrt(entity.method_5707(crystal));
      if (dist >= maxDist) {
         return 0.0F;
      } else {
         double exposure = (double)getExposure(crystal, (class_1297)entity, ignoreTerrain, miningIgnore, (class_2338)null);
         double impact = ((double)1.0F - dist / maxDist) * exposure;
         float rawDmg = (float)((impact * impact + impact) / (double)2.0F * (double)7.0F * maxDist + (double)1.0F);
         float dmg = calculateReductions(rawDmg, entity, mc.field_1687.method_48963().method_48807((class_1927)null));
         return Math.max(0.0F, dmg);
      }
   }

   public static float calculateDamage(class_1309 entity, class_243 crystal, boolean ignoreTerrain, boolean miningIgnore, class_2338 ignore) {
      double maxDist = (double)12.0F;
      double dist = Math.sqrt(entity.method_5707(crystal));
      if (dist >= maxDist) {
         return 0.0F;
      } else {
         double exposure = (double)getExposure(crystal, (class_1297)entity, ignoreTerrain, miningIgnore, ignore);
         double impact = ((double)1.0F - dist / maxDist) * exposure;
         float rawDmg = (float)((impact * impact + impact) / (double)2.0F * (double)7.0F * maxDist + (double)1.0F);
         float dmg = calculateReductions(rawDmg, entity, mc.field_1687.method_48963().method_48807((class_1927)null));
         return Math.max(0.0F, dmg);
      }
   }

   public static float calculateReductions(float damage, class_1309 entity, class_1282 damageSource) {
      if (damageSource.method_5514()) {
         switch (mc.field_1687.method_8407()) {
            case field_5805 -> damage = Math.min(damage / 2.0F + 1.0F, damage);
            case field_5807 -> damage *= 1.5F;
         }
      }

      damage = class_1280.method_5496(entity, damage, damageSource, (float)entity.method_6096(), (float)entity.method_45325(class_5134.field_23725));
      damage = resistanceReduction(entity, damage);
      float protAmount = (float)getProtectionAmount(entity.method_5661());
      if (protAmount > 0.0F) {
         damage = class_1280.method_5497(damage, protAmount);
      }

      return Math.max(damage, 0.0F);
   }

   public static int getProtectionAmount(Iterable<class_1799> equipment) {
      MutableInt mutableInt = new MutableInt();
      equipment.forEach((i) -> mutableInt.add(getProtectionAmount(i)));
      return mutableInt.intValue();
   }

   public static int getProtectionAmount(class_1799 stack) {
      if ((Boolean)AutoCrystal.INSTANCE.armorAssume.getValue()) {
         if (stack.method_7942()) {
            class_1792 var4 = stack.method_7909();
            if (var4 instanceof class_1738) {
               class_1738 armorItem = (class_1738)var4;
               byte var10000;
               switch (armorItem.method_48398()) {
                  case field_41936 -> var10000 = 8;
                  case field_48838 -> var10000 = 0;
                  default -> var10000 = 4;
               }

               return var10000;
            }
         }

         return 0;
      } else {
         int modifierBlast = class_1890.method_8225((class_6880)mc.field_1687.method_30349().method_30530(class_1893.field_9107.method_58273()).method_40264(class_1893.field_9107).get(), stack);
         int modifier = class_1890.method_8225((class_6880)mc.field_1687.method_30349().method_30530(class_1893.field_9111.method_58273()).method_40264(class_1893.field_9111).get(), stack);
         return modifierBlast * 2 + modifier;
      }
   }

   private static float resistanceReduction(class_1309 player, float damage) {
      class_1293 resistance = player.method_6112(class_1294.field_5907);
      if (resistance != null) {
         int lvl = resistance.method_5578() + 1;
         damage *= 1.0F - (float)lvl * 0.2F;
      }

      return Math.max(damage, 0.0F);
   }

   private static float getExposure(class_243 source, class_1297 entity, boolean ignoreTerrain, boolean miningIgnore, class_2338 ignorePos) {
      class_238 box = entity.method_5829();
      return getExposure(source, box, ignoreTerrain, miningIgnore, ignorePos);
   }

   private static float getExposure(class_243 source, class_238 box, boolean ignoreTerrain, boolean miningIgnore, class_2338 ignorePos) {
      RaycastFactory raycastFactory = getRaycastFactory(ignoreTerrain, miningIgnore, ignorePos);
      double xDiff = box.field_1320 - box.field_1323;
      double yDiff = box.field_1325 - box.field_1322;
      double zDiff = box.field_1324 - box.field_1321;
      double xStep = (double)1.0F / (xDiff * (double)2.0F + (double)1.0F);
      double yStep = (double)1.0F / (yDiff * (double)2.0F + (double)1.0F);
      double zStep = (double)1.0F / (zDiff * (double)2.0F + (double)1.0F);
      if (xStep > (double)0.0F && yStep > (double)0.0F && zStep > (double)0.0F) {
         int misses = 0;
         int hits = 0;
         double xOffset = ((double)1.0F - Math.floor((double)1.0F / xStep) * xStep) * (double)0.5F;
         double zOffset = ((double)1.0F - Math.floor((double)1.0F / zStep) * zStep) * (double)0.5F;
         xStep *= xDiff;
         yStep *= yDiff;
         zStep *= zDiff;
         double startX = box.field_1323 + xOffset;
         double startY = box.field_1322;
         double startZ = box.field_1321 + zOffset;
         double endX = box.field_1320 + xOffset;
         double endY = box.field_1325;
         double endZ = box.field_1324 + zOffset;

         for(double x = startX; x <= endX; x += xStep) {
            for(double y = startY; y <= endY; y += yStep) {
               for(double z = startZ; z <= endZ; z += zStep) {
                  class_243 position = new class_243(x, y, z);
                  if (raycast(new ExposureRaycastContext(position, source), raycastFactory) == null) {
                     ++misses;
                  }

                  ++hits;
               }
            }
         }

         return (float)misses / (float)hits;
      } else {
         return 0.0F;
      }
   }

   private static RaycastFactory getRaycastFactory(boolean ignoreTerrain, boolean miningIgnore, class_2338 ignorePos) {
      return (context, blockPos) -> {
         class_2680 blockState = mc.field_1687.method_8320(blockPos);
         if (ignorePos != null && blockPos.equals(ignorePos)) {
            return null;
         } else if (ignoreTerrain && blockState.method_26204().method_9520() < 600.0F) {
            return null;
         } else {
            if (miningIgnore) {
               if (BreakManager.INSTANCE.isPassed(blockPos, 0.7F, true)) {
                  return null;
               }

               if (AutoBreak.INSTANCE.isAboutToBreak(blockPos)) {
                  return null;
               }

               if (AutoBreak.INSTANCE.isInstantMining(blockPos)) {
                  return null;
               }
            }

            return blockState.method_26220(mc.field_1687, blockPos).method_1092(context.start(), context.end(), blockPos);
         }
      };
   }

   private static class_3965 raycast(ExposureRaycastContext context, RaycastFactory raycastFactory) {
      return (class_3965)class_1922.method_17744(context.start, context.end, context, raycastFactory, (ctx) -> null);
   }

   public static boolean isEndCrystal(class_1297 entity) {
      return entity instanceof class_1511;
   }

   public static record ExposureRaycastContext(class_243 start, class_243 end) {
   }

   @FunctionalInterface
   public interface RaycastFactory extends BiFunction<ExposureRaycastContext, class_2338, class_3965> {
   }
}
