package org.itsmahir.pagon.api.utils.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1533;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_2189;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2358;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_2404;
import net.minecraft.class_243;
import net.minecraft.class_2586;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2818;
import net.minecraft.class_2848;
import net.minecraft.class_2879;
import net.minecraft.class_2885;
import net.minecraft.class_3532;
import net.minecraft.class_3965;
import net.minecraft.class_7204;
import net.minecraft.class_2848.class_2849;
import org.itsmahir.pagon.api.management.HitboxManager;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.management.breaks.BreakManager;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;
import org.itsmahir.pagon.impl.features.modules.combat.AutoCrystal;
import org.itsmahir.pagon.mixin.accessor.IClientPlayerInteractionManager;

public class BlockUtils implements IMinecraft {
   public static Set<class_2248> SNEAK_BLOCKS;
   public static Set<class_2248> SHULKER_BLOCKS;
   public static final class_238 EMPTY_BOX;

   public static List<class_2338> sphere(double range, class_2338 pos, boolean sphere, boolean hollow) {
      List<class_2338> circleblocks = new ArrayList();
      int cx = pos.method_10263();
      int cy = pos.method_10264();
      int cz = pos.method_10260();

      for(int x = cx - (int)range; (double)x <= (double)cx + range; ++x) {
         for(int z = cz - (int)range; (double)z <= (double)cz + range; ++z) {
            for(int y = sphere ? cy - (int)range : cy; (double)y < (double)cy + range; ++y) {
               double dist = (double)((cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? (cy - y) * (cy - y) : 0));
               if (dist < range * range && (!hollow || !(dist < (range - (double)1.0F) * (range - (double)1.0F)))) {
                  class_2338 l = new class_2338(x, y, z);
                  circleblocks.add(l);
               }
            }
         }
      }

      return circleblocks;
   }

   public static class_238 getCombinedBox(class_2338 pos) {
      class_265 shape = mc.field_1687.method_8320(pos).method_26220(mc.field_1687, pos).method_1096((double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260());
      class_238 combined = new class_238(pos);

      for(class_238 box : shape.method_1090()) {
         double minX = Math.max(box.field_1323, combined.field_1323);
         double minY = Math.max(box.field_1322, combined.field_1322);
         double minZ = Math.max(box.field_1321, combined.field_1321);
         double maxX = Math.min(box.field_1320, combined.field_1320);
         double maxY = Math.min(box.field_1325, combined.field_1325);
         double maxZ = Math.min(box.field_1324, combined.field_1324);
         combined = new class_238(minX, minY, minZ, maxX, maxY, maxZ);
      }

      return combined;
   }

   public static boolean placeTrace(class_2338 pos) {
      for(class_2350 facing : class_2350.values()) {
         class_243 vec = new class_243((double)pos.method_10263() + (double)0.5F + (double)((float)facing.method_10148() / 2.0F), (double)pos.method_10264() + (double)0.5F + (double)((float)facing.method_10164() / 2.0F), (double)pos.method_10260() + (double)0.5F + (double)((float)facing.method_10165() / 2.0F));
         class_3965 result = RaytraceUtils.getResult(vec);
         if (result != null && result.method_17777().equals(pos)) {
            return false;
         }
      }

      return true;
   }

   public static List<class_238> subtract(class_238 bb, class_238 other) {
      List<class_238> result = new ArrayList();
      if (!bb.method_994(other)) {
         result.add(bb);
         return result;
      } else {
         double x1 = Math.max(bb.field_1323, other.field_1323);
         double y1 = Math.max(bb.field_1322, other.field_1322);
         double z1 = Math.max(bb.field_1321, other.field_1321);
         double x2 = Math.min(bb.field_1320, other.field_1320);
         double y2 = Math.min(bb.field_1325, other.field_1325);
         double z2 = Math.min(bb.field_1324, other.field_1324);
         if (!(x1 >= x2) && !(y1 >= y2) && !(z1 >= z2)) {
            if (bb.field_1323 < other.field_1323) {
               result.add(new class_238(bb.field_1323, bb.field_1322, bb.field_1321, other.field_1323, bb.field_1325, bb.field_1324));
            }

            if (bb.field_1320 > other.field_1320) {
               result.add(new class_238(other.field_1320, bb.field_1322, bb.field_1321, bb.field_1320, bb.field_1325, bb.field_1324));
            }

            if (bb.field_1322 < other.field_1322) {
               result.add(new class_238(Math.max(bb.field_1323, other.field_1323), bb.field_1322, bb.field_1321, Math.min(bb.field_1320, other.field_1320), other.field_1322, bb.field_1324));
            }

            if (bb.field_1325 > other.field_1325) {
               result.add(new class_238(Math.max(bb.field_1323, other.field_1323), other.field_1325, bb.field_1321, Math.min(bb.field_1320, other.field_1320), bb.field_1325, bb.field_1324));
            }

            if (bb.field_1321 < other.field_1321) {
               result.add(new class_238(Math.max(bb.field_1323, other.field_1323), Math.max(bb.field_1322, other.field_1322), bb.field_1321, Math.min(bb.field_1320, other.field_1320), Math.min(bb.field_1325, other.field_1325), other.field_1321));
            }

            if (bb.field_1324 > other.field_1324) {
               result.add(new class_238(Math.max(bb.field_1323, other.field_1323), Math.max(bb.field_1322, other.field_1322), other.field_1324, Math.min(bb.field_1320, other.field_1320), Math.min(bb.field_1325, other.field_1325), bb.field_1324));
            }

            return result;
         } else {
            result.add(bb);
            return result;
         }
      }
   }

   public static boolean canIgnite(class_2338 pos, boolean strictDirection) {
      if (mc.field_1687.method_8320(pos).method_26204() instanceof class_2358) {
         return false;
      } else if (!mc.field_1687.method_8320(pos).method_45474()) {
         return false;
      } else {
         return getPlaceableSide(pos, strictDirection) != null;
      }
   }

   public static boolean canPlaceBlockIgnore(class_2338 pos, boolean strictDirection) {
      if (!mc.field_1687.method_8320(pos).method_45474()) {
         return false;
      } else {
         return getPlaceableSide(pos, strictDirection) != null;
      }
   }

   public static boolean canPlaceItemFrame(class_2338 pos, class_2350 direction, boolean strictDirection) {
      if (!mc.field_1687.method_8320(pos.method_10093(direction)).method_45474()) {
         return false;
      } else if (mc.field_1687.method_8320(pos).method_45474()) {
         return false;
      } else if (strictDirection && !canSeeFace(pos, direction)) {
         return false;
      } else {
         class_2338 frameBlock = pos.method_10093(direction);

         for(class_1297 entity : mc.field_1687.method_18467(class_1297.class, new class_238(frameBlock))) {
            if (entity instanceof class_1533) {
               class_1533 frame = (class_1533)entity;
               if (frame.method_58149().equals(direction)) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   public static class_1533 getItemFrame(class_2338 pos, class_2350 direction) {
      class_2338 frameBlock = pos.method_10093(direction);

      for(class_1297 entity : mc.field_1687.method_18467(class_1297.class, new class_238(frameBlock))) {
         if (entity instanceof class_1533 frame) {
            if (frame.method_58149().equals(direction)) {
               return frame;
            }
         }
      }

      return null;
   }

   public static boolean canPlaceBlock(class_2338 pos) {
      if (!mc.field_1687.method_8320(pos).method_45474()) {
         return false;
      } else {
         for(class_1297 entity : mc.field_1687.method_18467(class_1297.class, new class_238(pos))) {
            if (entity instanceof class_1657) {
               return false;
            }
         }

         return true;
      }
   }

   public static boolean canPlaceBlock(class_2338 pos, boolean strictDirection) {
      if (!mc.field_1687.method_8320(pos).method_45474()) {
         return false;
      } else if (getPlaceableSide(pos, strictDirection) == null) {
         return false;
      } else {
         for(class_1297 entity : mc.field_1687.method_18467(class_1297.class, new class_238(pos))) {
            if (entity instanceof class_1657) {
               return false;
            }
         }

         return true;
      }
   }

   public static boolean canPlaceBlockPlatformer(class_2338 pos, boolean strictDirection) {
      if (isUnbreakable(mc.field_1687.method_8320(pos))) {
         return false;
      } else if (getPlaceableSide(pos, strictDirection) == null) {
         return false;
      } else {
         for(class_1297 entity : mc.field_1687.method_18467(class_1297.class, new class_238(pos))) {
            if (entity instanceof class_1657) {
               if (!HitboxManager.INSTANCE.isServerCrawling(entity)) {
                  return false;
               }

               if ((new class_238(pos)).method_994(HitboxManager.INSTANCE.getCrawlingBoundingBox(entity))) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   public static boolean canPlaceBlockIfBeingMined(class_2338 pos, boolean strictDirection) {
      if (!BreakManager.INSTANCE.isPassed(pos, 0.5F, false)) {
         return false;
      } else {
         return getPlaceableSide(pos, strictDirection) != null;
      }
   }

   public static boolean canPlaceBlock(class_2338 pos, boolean strictDirection, class_2338 pretend) {
      if (!mc.field_1687.method_8320(pos).method_45474()) {
         return false;
      } else if (getPlaceableSide(pos, strictDirection, pretend) == null) {
         return false;
      } else {
         for(class_1297 entity : mc.field_1687.method_18467(class_1297.class, new class_238(pos))) {
            if (entity instanceof class_1657) {
               return false;
            }
         }

         return true;
      }
   }

   public static boolean canPlaceBlock(class_2338 pos, boolean strictDirection, boolean isAir) {
      if (!mc.field_1687.method_8320(pos).method_45474()) {
         return isAir;
      } else if (getPlaceableSide(pos, strictDirection) == null) {
         return false;
      } else {
         for(class_1297 entity : mc.field_1687.method_18467(class_1297.class, new class_238(pos))) {
            if (entity instanceof class_1657) {
               return false;
            }
         }

         return true;
      }
   }

   public static class_2350 getPlaceableSideCrystal(class_2338 pos, boolean strict) {
      if (mc.field_1724.method_24515().method_10084().equals(pos) && !mc.field_1724.method_20448()) {
         return class_2350.field_11036;
      } else {
         double bestDist = (double)99999.0F;
         class_2350 bestSide = null;

         for(class_2350 side : class_2350.values()) {
            class_2338 neighbour = pos.method_10093(side);
            class_2680 blockState = mc.field_1687.method_8320(neighbour);
            if (!strict || canSeeFace(pos, side) && blockState.method_45474()) {
               if (side.equals(class_2350.field_11036)) {
                  return side;
               }

               double dist = distanceTo(pos, side);
               if (bestSide == null || dist < bestDist) {
                  bestDist = dist;
                  bestSide = side;
               }
            }
         }

         return bestSide;
      }
   }

   public static class_2350 getPlaceableSideCrystal(class_2338 pos, boolean strict, class_2338 ignore) {
      if (mc.field_1724.method_24515().method_10084().equals(pos) && !mc.field_1724.method_20448()) {
         return class_2350.field_11036;
      } else {
         double bestDist = (double)99999.0F;
         class_2350 bestSide = null;

         for(class_2350 side : class_2350.values()) {
            class_2338 neighbour = pos.method_10093(side);
            class_2680 blockState = mc.field_1687.method_8320(neighbour);
            if (!strict || canSeeFace(pos, side) && (blockState.method_45474() || pos == ignore)) {
               if (side.equals(class_2350.field_11036)) {
                  return side;
               }

               double dist = distanceTo(pos, side);
               if (bestSide == null || dist < bestDist) {
                  bestDist = dist;
                  bestSide = side;
               }
            }
         }

         return bestSide;
      }
   }

   public static class_2350 getClosestSide(class_2338 pos) {
      double bestDist = (double)99999.0F;
      class_2350 bestSide = null;

      for(class_2350 side : class_2350.values()) {
         double dist = distanceTo(pos, side);
         if (bestSide == null || dist < bestDist) {
            bestDist = dist;
            bestSide = side;
         }
      }

      return bestSide;
   }

   public static boolean placeBlock(class_2338 pos, class_2350 direction, class_1268 hand, boolean packet) {
      return placeBlock(pos, direction, hand, packet, true);
   }

   public static boolean placeBlock(class_2338 pos, class_2350 direction, boolean packet, boolean swing) {
      return placeBlock(pos, direction, class_1268.field_5808, packet, swing);
   }

   public static boolean placeBlock(class_2338 pos, class_2350 direction, boolean packet) {
      return placeBlock(pos, direction, class_1268.field_5808, packet, true);
   }

   public static boolean placeBlock(class_2338 pos, class_2350 direction, class_1268 hand, boolean packet, boolean swing) {
      if (direction == null) {
         return false;
      } else {
         class_2338 neighbour = pos.method_10093(direction);
         class_2350 opposite = direction.method_10153();
         class_243 hitVec = neighbour.method_46558().method_1019((new class_243(opposite.method_23955())).method_1021((double)0.5F));
         class_3965 hitResult = new class_3965(hitVec, opposite, neighbour, false);
         AntiCheat.INSTANCE.handleMultiTask();
         boolean unShift = false;
         if (!mc.field_1724.method_5715() && SNEAK_BLOCKS.contains(mc.field_1687.method_8320(neighbour).method_26204())) {
            PacketManager.INSTANCE.sendPacket(new class_2848(mc.field_1724, class_2849.field_12979));
            unShift = true;
         }

         if (packet) {
            PacketManager.INSTANCE.sendPacket((class_7204)((id) -> new class_2885(hand, hitResult, id)));
            if (swing) {
               mc.field_1724.method_6104(hand);
            } else {
               PacketManager.INSTANCE.sendPacket(new class_2879(hand));
            }

            if (unShift) {
               PacketManager.INSTANCE.sendPacket(new class_2848(mc.field_1724, class_2849.field_12984));
            }

            return true;
         } else {
            class_1269 result = mc.field_1761.method_2896(mc.field_1724, hand, hitResult);
            if (swing) {
               mc.field_1724.method_6104(hand);
            } else {
               PacketManager.INSTANCE.sendPacket(new class_2879(hand));
            }

            if (unShift) {
               PacketManager.INSTANCE.sendPacket(new class_2848(mc.field_1724, class_2849.field_12984));
            }

            return result.method_23665();
         }
      }
   }

   public static boolean placeFrame(class_2338 pos, class_2350 direction, class_1268 hand, boolean packet, boolean swing) {
      if (direction == null) {
         return false;
      } else {
         class_243 hitVec = pos.method_46558().method_1019((new class_243(direction.method_23955())).method_1021((double)0.5F));
         class_3965 hitResult = new class_3965(hitVec, direction, pos, false);
         AntiCheat.INSTANCE.handleMultiTask();
         boolean unShift = false;
         if (!mc.field_1724.method_5715() && SNEAK_BLOCKS.contains(mc.field_1687.method_8320(pos).method_26204())) {
            PacketManager.INSTANCE.sendPacket(new class_2848(mc.field_1724, class_2849.field_12979));
            unShift = true;
         }

         if (packet) {
            PacketManager.INSTANCE.sendPacket((class_7204)((id) -> new class_2885(hand, hitResult, id)));
            if (swing) {
               mc.field_1724.method_6104(hand);
            } else {
               PacketManager.INSTANCE.sendPacket(new class_2879(hand));
            }

            if (unShift) {
               PacketManager.INSTANCE.sendPacket(new class_2848(mc.field_1724, class_2849.field_12984));
            }

            return true;
         } else {
            class_1269 result = mc.field_1761.method_2896(mc.field_1724, hand, hitResult);
            if (swing) {
               mc.field_1724.method_6104(hand);
            } else {
               PacketManager.INSTANCE.sendPacket(new class_2879(hand));
            }

            if (unShift) {
               PacketManager.INSTANCE.sendPacket(new class_2848(mc.field_1724, class_2849.field_12984));
            }

            return result.method_23665();
         }
      }
   }

   public static boolean placeBlock(class_2338 pos, class_2350 direction, class_1268 hand, boolean packet, boolean swing, class_243 vec) {
      if (direction == null) {
         return false;
      } else {
         class_2338 neighbour = pos.method_10093(direction);
         class_2350 opposite = direction.method_10153();
         class_3965 hitResult = new class_3965(vec, opposite, neighbour, false);
         AntiCheat.INSTANCE.handleMultiTask();
         boolean unShift = false;
         if (!mc.field_1724.method_5715() && SNEAK_BLOCKS.contains(mc.field_1687.method_8320(neighbour).method_26204())) {
            PacketManager.INSTANCE.sendPacket(new class_2848(mc.field_1724, class_2849.field_12979));
            unShift = true;
         }

         if (packet) {
            PacketManager.INSTANCE.sendPacket((class_7204)((id) -> new class_2885(hand, hitResult, id)));
            if (swing) {
               mc.field_1724.method_6104(hand);
            } else {
               PacketManager.INSTANCE.sendPacket((class_7204)((id) -> new class_2879(hand)));
            }

            if (unShift) {
               PacketManager.INSTANCE.sendPacket(new class_2848(mc.field_1724, class_2849.field_12984));
            }

            return true;
         } else {
            class_1269 result = mc.field_1761.method_2896(mc.field_1724, hand, hitResult);
            if (swing) {
               mc.field_1724.method_6104(hand);
            } else {
               PacketManager.INSTANCE.sendPacket((class_7204)((id) -> new class_2879(hand)));
            }

            if (unShift) {
               PacketManager.INSTANCE.sendPacket(new class_2848(mc.field_1724, class_2849.field_12984));
            }

            return result.method_23665();
         }
      }
   }

   public static void openBlock(class_2338 pos) {
      class_2350 direction = getMineableSide(pos, false);
      if (direction != null) {
         rightClick(pos, direction, class_1268.field_5808);
      }

   }

   public static void rightClick(class_2338 pos, class_2350 direction, class_1268 hand) {
      class_243 hitVec = pos.method_46558().method_1019((new class_243(direction.method_23955())).method_1021((double)0.5F));
      class_3965 hitResult = new class_3965(hitVec, direction, pos, false);
      mc.field_1761.method_2896(mc.field_1724, hand, hitResult);
      mc.field_1724.method_6104(hand);
   }

   public static void interactInternal(class_2338 pos, class_2350 direction, class_1268 hand, class_243 vec) {
      class_2338 neighbour = pos.method_10093(direction);
      class_2350 opposite = direction.method_10153();
      class_3965 hitResult = new class_3965(vec, opposite, neighbour, false);
      ((IClientPlayerInteractionManager)mc.field_1761).invokeInteractInternal(mc.field_1724, hand, hitResult);
   }

   public static class_2338 getRoundedBlockPos(double x, double y, double z) {
      int flooredX = class_3532.method_15357(x);
      int flooredY = (int)Math.round(y);
      int flooredZ = class_3532.method_15357(z);
      return new class_2338(flooredX, flooredY, flooredZ);
   }

   public static class_2350 getMineableSide(class_2338 pos, boolean strict) {
      class_2350 bestSide = null;

      for(class_2350 side : class_2350.values()) {
         if (!strict || canSeeFace(pos, side)) {
            if (side.equals(class_2350.field_11036)) {
               return side;
            }

            bestSide = side;
         }
      }

      return bestSide;
   }

   public static double distanceTo(class_2338 pos, class_2350 direction) {
      return mc.field_1724.method_33571().method_1022((new class_243((double)((float)pos.method_10263() + 0.5F), (double)((float)pos.method_10264() + 0.5F), (double)((float)pos.method_10260() + 0.5F))).method_1019(class_243.method_24954(direction.method_10163())));
   }

   public static class_2680 getBlockState(class_2338 pos) {
      return mc.field_1687.method_8320(pos);
   }

   public static boolean canSeeFace(class_2338 target, class_2350 face) {
      class_243 eyes = mc.field_1724.method_33571();
      class_243 centered = new class_243((double)target.method_10263() + (double)0.5F, (double)target.method_10264() + (double)0.5F, (double)target.method_10260() + (double)0.5F);
      class_2382 dir = face.method_10163();
      class_243 offset = new class_243((double)dir.method_10263(), (double)dir.method_10264(), (double)dir.method_10260());
      class_243 scaled = new class_243(offset.field_1352 * (double)0.5F, offset.field_1351 * (double)0.5F, offset.field_1350 * (double)0.5F);
      class_243 pos = centered.method_1019(scaled);
      switch (face) {
         case field_11043:
            return eyes.field_1350 < pos.field_1350;
         case field_11034:
            return eyes.field_1352 > pos.field_1352;
         case field_11035:
            return eyes.field_1350 > pos.field_1350;
         case field_11039:
            return eyes.field_1352 < pos.field_1352;
         case field_11036:
            if (((String)AntiCheat.INSTANCE.acMode.getValue()).equals("Strong")) {
               return eyes.field_1351 > pos.field_1351;
            }

            return eyes.field_1351 + (double)0.5F > pos.field_1351;
         case field_11033:
            return eyes.field_1351 < pos.field_1351;
         default:
            return false;
      }
   }

   public static boolean isUnbreakable(class_2680 state) {
      return state.method_26204().method_36555() == -1.0F && !state.method_26215() && !mc.field_1724.method_7337();
   }

   public static boolean isMineable(class_2680 state) {
      return state.method_26204().method_36555() != -1.0F && !state.method_26215() && !mc.field_1724.method_7337();
   }

   public static boolean canSeeFaceMotion(class_2338 target, class_2350 face) {
      class_243 eyes = mc.field_1724.method_33571();
      eyes = eyes.method_1031(mc.field_1724.method_18798().field_1352, mc.field_1724.method_18798().field_1351, mc.field_1724.method_18798().field_1350);
      class_243 centered = new class_243((double)target.method_10263() + (double)0.5F, (double)target.method_10264() + (double)0.5F, (double)target.method_10260() + (double)0.5F);
      class_2382 dir = face.method_10163();
      class_243 offset = new class_243((double)dir.method_10263(), (double)dir.method_10264(), (double)dir.method_10260());
      class_243 scaled = new class_243(offset.field_1352 * (double)0.5F, offset.field_1351 * (double)0.5F, offset.field_1350 * (double)0.5F);
      class_243 pos = centered.method_1019(scaled);
      switch (face) {
         case field_11043:
            return eyes.field_1350 < pos.field_1350;
         case field_11034:
            return eyes.field_1352 > pos.field_1352;
         case field_11035:
            return eyes.field_1350 > pos.field_1350;
         case field_11039:
            return eyes.field_1352 < pos.field_1352;
         case field_11036:
            if (((String)AntiCheat.INSTANCE.acMode.getValue()).equals("Strong")) {
               return eyes.field_1351 > pos.field_1351;
            }

            return eyes.field_1351 + (double)0.5F > pos.field_1351;
         case field_11033:
            return eyes.field_1351 < pos.field_1351;
         default:
            return false;
      }
   }

   public static double getDistanceSq(class_2338 pos) {
      return pos.method_10268(mc.field_1724.method_23317(), mc.field_1724.method_33571().field_1351, mc.field_1724.method_23321());
   }

   public static double getDistanceSq(class_1297 from, class_2338 to) {
      return from.method_5707(new class_243((double)to.method_10263(), (double)to.method_10264(), (double)to.method_10260()));
   }

   public static List<class_2338> getAllInBox(class_238 box, class_2338 pos) {
      List<class_2338> intersections = new ArrayList();

      for(int x = (int)Math.floor(box.field_1323); (double)x < Math.ceil(box.field_1320); ++x) {
         for(int z = (int)Math.floor(box.field_1321); (double)z < Math.ceil(box.field_1324); ++z) {
            intersections.add(new class_2338(x, pos.method_10264(), z));
         }
      }

      return intersections;
   }

   public static List<class_2338> getAllInBox(class_238 box) {
      List<class_2338> intersections = new ArrayList();

      for(int x = (int)Math.floor(box.field_1323); (double)x < Math.ceil(box.field_1320); ++x) {
         for(int y = (int)Math.floor(box.field_1322); (double)y < Math.ceil(box.field_1325); ++y) {
            for(int z = (int)Math.floor(box.field_1321); (double)z < Math.ceil(box.field_1324); ++z) {
               intersections.add(new class_2338(x, y, z));
            }
         }
      }

      return intersections;
   }

   public static List<class_2338> isInBlock(class_238 box, boolean noSelf) {
      List<class_2338> blockingBlocks = new ArrayList();

      for(int x = (int)Math.floor(box.field_1323); (double)x < Math.ceil(box.field_1320); ++x) {
         for(int y = (int)Math.floor(box.field_1322); (double)y < Math.ceil(box.field_1325); ++y) {
            for(int z = (int)Math.floor(box.field_1321); (double)z < Math.ceil(box.field_1324); ++z) {
               class_2338 pos = new class_2338(x, y, z);
               if ((!noSelf || !pos.equals(mc.field_1724.method_24515().method_10084())) && mc.field_1687.method_8320(pos).method_51366()) {
                  blockingBlocks.add(pos);
               }
            }
         }
      }

      return blockingBlocks;
   }

   public static class_2350 getPlaceableSideMotion(class_2338 pos, boolean strict) {
      for(class_2350 direction : class_2350.values()) {
         class_2338 targetPos = pos.method_10093(direction);
         class_2680 state = mc.field_1687.method_8320(pos.method_10093(direction));
         if (!state.method_45474() && (!strict || canSeeFaceMotion(targetPos, direction.method_10153()))) {
            return direction;
         }
      }

      return null;
   }

   public static class_2350 getPlaceableSide(class_2338 pos, boolean strict) {
      for(class_2350 direction : class_2350.values()) {
         class_2338 targetPos = pos.method_10093(direction);
         class_2680 state = mc.field_1687.method_8320(targetPos);
         if (!state.method_45474() && (!strict || canSeeFace(targetPos, direction.method_10153()))) {
            return direction;
         }
      }

      return null;
   }

   public static class_2350 getPlaceableAirplace(class_2338 pos, boolean strict) {
      for(class_2350 direction : class_2350.values()) {
         class_2338 targetPos = pos.method_10093(direction);
         if (!strict || canSeeFace(targetPos, direction.method_10153())) {
            return direction;
         }
      }

      return null;
   }

   public static boolean canPlaceBlock(class_2338 pos, boolean strictDirection, Set<class_2338> placed) {
      boolean allow = true;
      if (!isReplaceable(pos)) {
         return false;
      } else if (getPlaceableSide(pos, strictDirection, placed) == null) {
         return false;
      } else {
         for(class_1297 entity : mc.field_1687.method_18467(class_1297.class, new class_238(pos))) {
            if (entity instanceof class_1657) {
               allow = false;
               break;
            }
         }

         return allow;
      }
   }

   public static boolean isBlockedOff(class_2338 pos) {
      for(class_1297 entity : mc.field_1687.method_18467(class_1297.class, new class_238(pos))) {
         if (entity instanceof class_1657) {
            return true;
         }
      }

      return false;
   }

   public static class_2350 getPlaceableSide(class_2338 pos, boolean strict, List<class_2338> placed) {
      for(class_2350 direction : class_2350.values()) {
         class_2338 targetPos = pos.method_10093(direction);
         class_2680 state = mc.field_1687.method_8320(targetPos);
         if ((!state.method_45474() || placed.contains(targetPos)) && !SNEAK_BLOCKS.contains(state.method_26204()) && (!strict || canSeeFace(targetPos, direction.method_10153()))) {
            return direction;
         }
      }

      return null;
   }

   public static class_2350 getPlaceableSide(class_2338 pos, boolean strict, Set<class_2338> placed) {
      for(class_2350 direction : class_2350.values()) {
         class_2338 targetPos = pos.method_10093(direction);
         class_2680 state = mc.field_1687.method_8320(targetPos);
         if ((!state.method_45474() || placed.contains(targetPos)) && (!strict || canSeeFace(targetPos, direction.method_10153()))) {
            return direction;
         }
      }

      return null;
   }

   public static class_2350 getPlaceableSide(class_2338 pos, boolean strict, class_2338 pretend) {
      for(class_2350 side : class_2350.values()) {
         class_2338 neighbour = pos.method_10093(side);
         class_2680 blockState = mc.field_1687.method_8320(neighbour);
         if (!SNEAK_BLOCKS.contains(blockState.method_26204()) && (pretend.equals(neighbour) || blockState.method_45474()) && (!strict || canSeeFace(neighbour, side.method_10153()))) {
            if (pretend.equals(neighbour)) {
               return side;
            }

            if (!blockState.method_45474()) {
               return side;
            }
         }
      }

      return null;
   }

   public static class_2350 getFacing(class_2338 pos) {
      for(class_2350 facing : class_2350.values()) {
         if (!mc.field_1687.method_8320(pos.method_10093(facing)).method_45474() && !SNEAK_BLOCKS.contains(mc.field_1687.method_8320(pos.method_10093(facing)).method_26204())) {
            return facing;
         }
      }

      return null;
   }

   public static boolean isReplaceable(class_2338 pos) {
      return mc.field_1687.method_8320(pos).method_45474();
   }

   public static boolean isInterceptedByCrystal(class_2338 pos) {
      for(class_1297 entity : mc.field_1687.method_18112()) {
         if (CrystalUtil.isEndCrystal(entity) && (new class_238(pos)).method_994(entity.method_5829())) {
            return true;
         }
      }

      return false;
   }

   public static boolean isInterceptedByCrystalThatIsntHit(class_2338 pos) {
      for(class_1297 entity : mc.field_1687.method_18112()) {
         if (CrystalUtil.isEndCrystal(entity) && (new class_238(pos)).method_994(entity.method_5829()) && !AutoCrystal.INSTANCE.hitCrystals.containsKey(entity.method_5628())) {
            return true;
         }
      }

      return false;
   }

   public static boolean isInterceptedBy(class_2338 pos, class_1297 e) {
      return (new class_238(pos)).method_994(e.method_5829());
   }

   public static class_1511 attackInPos(class_2338 pos, int ticksExisted) {
      class_1511 crystal = null;
      double bestDistance = (double)9999.0F;

      for(class_1297 entity : mc.field_1687.method_18112()) {
         if (entity != null && !(mc.field_1724.method_5739(entity) > 3.0F) && CrystalUtil.isEndCrystal(entity) && entity.method_5805() && (ticksExisted == 0 || entity.field_6012 > ticksExisted)) {
            double distance = (double)mc.field_1724.method_5739(entity);
            if (bestDistance > distance) {
               crystal = (class_1511)entity;
               bestDistance = distance;
            }
         }
      }

      if (crystal != null) {
         PlayerUtils.attackTarget(crystal);
         return crystal;
      } else {
         return null;
      }
   }

   public static boolean isInterceptedByOtherPlayer(class_2338 pos) {
      for(class_1297 entity : mc.field_1687.method_18112()) {
         if (entity instanceof class_1657 && entity != mc.field_1724 && (new class_238(pos)).method_994(entity.method_5829())) {
            return true;
         }
      }

      return false;
   }

   public static boolean isBlockedByPlayer(class_2338 pos) {
      for(class_1297 entity : mc.field_1687.method_18467(class_1297.class, new class_238(pos))) {
         if (entity instanceof class_1657) {
            return true;
         }
      }

      return false;
   }

   public static boolean isBlockedByIgnoreEntity(class_2338 pos) {
      for(class_1297 entity : mc.field_1687.method_18467(class_1297.class, new class_238(pos))) {
         if (!(entity instanceof class_1511) && !(entity instanceof class_1542)) {
            return true;
         }
      }

      return false;
   }

   public static boolean isInRange(class_2338 pos, double distance) {
      return !(getDistanceSq(pos) > MathUtil.square(distance));
   }

   public static boolean canPlaceBlockPretendDistance(class_2338 pos, boolean strictDirection, class_2338 pretend, double distance) {
      boolean allow = true;
      class_2248 block = mc.field_1687.method_8320(pos).method_26204();
      if (!(block instanceof class_2189) && !(block instanceof class_2404)) {
         return false;
      } else if (!mc.field_1687.method_8320(pos).method_45474()) {
         return false;
      } else if (getPlaceableSide(pos, strictDirection, pretend) == null) {
         return false;
      } else {
         for(class_1297 entity : mc.field_1687.method_18467(class_1297.class, new class_238(pos))) {
            if (entity instanceof class_1657) {
               allow = false;
               break;
            }
         }

         return getDistanceSq(pos) > MathUtil.square(distance) ? false : allow;
      }
   }

   public static boolean willPlaceVertical(class_2338 pos) {
      if (Math.abs(mc.field_1724.method_23317() - (double)((float)pos.method_10263() + 0.5F)) < (double)2.0F && Math.abs(mc.field_1724.method_23321() - (double)((float)pos.method_10260() + 0.5F)) < (double)2.0F) {
         double y = mc.field_1724.method_23318() + (double)mc.field_1724.method_18381(mc.field_1724.method_18376());
         if (y - (double)pos.method_10264() > (double)2.0F) {
            return true;
         } else {
            return (double)pos.method_10264() - y > (double)0.0F;
         }
      } else {
         return false;
      }
   }

   public static boolean canPlaceWeb(class_2338 pos, boolean strictDirection) {
      boolean allow = true;
      class_2248 block = mc.field_1687.method_8320(pos).method_26204();
      if (!mc.field_1687.method_8320(pos).method_45474()) {
         allow = false;
         return allow;
      } else if (getPlaceableSide(pos, strictDirection) == null) {
         allow = false;
         return allow;
      } else {
         return allow;
      }
   }

   public static List<class_2818> getLoadedChunks() {
      List<class_2818> chunks = new ArrayList();
      int viewDist = (Integer)mc.field_1690.method_42503().method_41753();

      for(int x = -viewDist; x <= viewDist; ++x) {
         for(int z = -viewDist; z <= viewDist; ++z) {
            class_2818 chunk = mc.field_1687.method_2935().method_21730((int)mc.field_1724.method_23317() / 16 + x, (int)mc.field_1724.method_23321() / 16 + z);
            if (chunk != null) {
               chunks.add(chunk);
            }
         }
      }

      return chunks;
   }

   public static List<class_2586> getBlockEntities() {
      List<class_2586> list = new ArrayList();

      for(class_2818 chunk : getLoadedChunks()) {
         list.addAll(chunk.method_12214().values());
      }

      return list;
   }

   static {
      SNEAK_BLOCKS = Set.of(class_2246.field_10034, class_2246.field_10443, class_2246.field_10380, class_2246.field_9980, class_2246.field_10181, class_2246.field_16333, class_2246.field_16331, class_2246.field_16336, class_2246.field_10485, class_2246.field_16329, class_2246.field_16335, class_2246.field_10535, class_2246.field_10105, class_2246.field_10414, class_2246.field_10223, class_2246.field_10179, class_2246.field_10200, class_2246.field_10312);
      SHULKER_BLOCKS = Set.of(class_2246.field_10371, class_2246.field_10605, class_2246.field_10373, class_2246.field_10532, class_2246.field_10140, class_2246.field_10055, class_2246.field_10203, class_2246.field_10275, class_2246.field_10063, class_2246.field_10407, class_2246.field_10051, class_2246.field_10268, class_2246.field_10068, class_2246.field_10199, class_2246.field_10600, class_2246.field_10320);
      EMPTY_BOX = new class_238((double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, (double)1.0F, (double)1.0F);
   }
}
