package org.itsmahir.pagon.api.utils.world;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1657;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import org.itsmahir.pagon.api.wrapper.IMinecraft;

public class HoleUtils implements IMinecraft {
   public static class_2338[] holeOffsets = new class_2338[]{new class_2338(1, 0, 0), new class_2338(-1, 0, 0), new class_2338(0, 0, 1), new class_2338(0, 0, -1), new class_2338(0, -1, 0)};
   public static class_2338[] surroundOffsets = new class_2338[]{new class_2338(1, 0, 0), new class_2338(-1, 0, 0), new class_2338(0, 0, 1), new class_2338(0, 0, -1)};

   public static boolean isHole(class_2338 pos) {
      boolean isHole = false;
      int amount = 0;

      for(class_2338 p : holeOffsets) {
         if (!mc.field_1687.method_8320(pos.method_10081(p)).method_45474()) {
            ++amount;
         }
      }

      if (amount == 5) {
         isHole = true;
      }

      return isHole;
   }

   public static boolean isSurrounded(class_2338 pos) {
      boolean isHole = false;
      int amount = 0;

      for(class_2338 p : surroundOffsets) {
         if (!mc.field_1687.method_8320(pos.method_10081(p)).method_45474()) {
            ++amount;
         }
      }

      if (amount == 4) {
         isHole = true;
      }

      return isHole;
   }

   public static boolean isBurrowed(class_1657 entityPlayer) {
      class_2338 blockPos = new class_2338((int)Math.floor(entityPlayer.method_23317()), (int)Math.floor(entityPlayer.method_23318() + 0.2), (int)Math.floor(entityPlayer.method_23321()));
      return mc.field_1687.method_8320(blockPos).method_26204() == class_2246.field_10443 || mc.field_1687.method_8320(blockPos).method_26204() == class_2246.field_10540 || mc.field_1687.method_8320(blockPos).method_26204() == class_2246.field_10034;
   }

   public static boolean isInBlock(class_1657 entityPlayer) {
      class_2338 blockPos = new class_2338((int)Math.floor(entityPlayer.method_23317()), (int)Math.floor(entityPlayer.method_23318() + 0.2), (int)Math.floor(entityPlayer.method_23321()));
      return mc.field_1687.method_8320(blockPos).method_26204() != class_2246.field_10124;
   }

   public static boolean isObbyHole(class_2338 pos) {
      boolean isHole = true;
      int bedrock = 0;

      for(class_2338 off : holeOffsets) {
         class_2248 b = mc.field_1687.method_8320(pos.method_10081(off)).method_26204();
         if (!isSafeBlock(pos.method_10081(off))) {
            isHole = false;
         } else if (b == class_2246.field_10540 || b == class_2246.field_10443 || b == class_2246.field_10535) {
            ++bedrock;
         }
      }

      if (mc.field_1687.method_8320(pos.method_10069(0, 2, 0)).method_26204() != class_2246.field_10124 || mc.field_1687.method_8320(pos.method_10069(0, 1, 0)).method_26204() != class_2246.field_10124) {
         isHole = false;
      }

      if (bedrock < 1) {
         isHole = false;
      }

      return isHole;
   }

   public static boolean isBedrockHoles(class_2338 pos) {
      boolean isHole = true;

      for(class_2338 off : holeOffsets) {
         class_2248 b = mc.field_1687.method_8320(pos.method_10081(off)).method_26204();
         if (b != class_2246.field_9987) {
            isHole = false;
         }
      }

      if (mc.field_1687.method_8320(pos.method_10069(0, 2, 0)).method_26204() != class_2246.field_10124 || mc.field_1687.method_8320(pos.method_10069(0, 1, 0)).method_26204() != class_2246.field_10124) {
         isHole = false;
      }

      return isHole;
   }

   public static Hole isDoubleHole(class_2338 pos) {
      if (checkOffset(pos, 1, 0)) {
         return new Hole(false, true, pos, pos.method_10069(1, 0, 0));
      } else {
         return checkOffset(pos, 0, 1) ? new Hole(false, true, pos, pos.method_10069(0, 0, 1)) : null;
      }
   }

   public static Hole isDoubleHoleFr(class_2338 pos) {
      if (checkOffset(pos, 1, 0)) {
         return new Hole(false, true, pos, pos.method_10069(1, 0, 0));
      } else if (checkOffset(pos, 0, 1)) {
         return new Hole(false, true, pos, pos.method_10069(0, 0, 1));
      } else if (checkOffset(pos, -1, 0)) {
         return new Hole(false, true, pos, pos.method_10069(-1, 0, 0));
      } else {
         return checkOffset(pos, 0, -1) ? new Hole(false, true, pos, pos.method_10069(0, 0, -1)) : null;
      }
   }

   public static boolean checkOffset(class_2338 pos, int offX, int offZ) {
      return mc.field_1687.method_8320(pos).method_26204() == class_2246.field_10124 && mc.field_1687.method_8320(pos.method_10069(offX, 0, offZ)).method_26204() == class_2246.field_10124 && isSafeBlock(pos.method_10069(0, -1, 0)) && isSafeBlock(pos.method_10069(offX, -1, offZ)) && isSafeBlock(pos.method_10069(offX * 2, 0, offZ * 2)) && isSafeBlock(pos.method_10069(-offX, 0, -offZ)) && isSafeBlock(pos.method_10069(offZ, 0, offX)) && isSafeBlock(pos.method_10069(-offZ, 0, -offX)) && isSafeBlock(pos.method_10069(offX, 0, offZ).method_10069(offZ, 0, offX)) && isSafeBlock(pos.method_10069(offX, 0, offZ).method_10069(-offZ, 0, -offX));
   }

   static boolean isSafeBlock(class_2338 pos) {
      return mc.field_1687.method_8320(pos).method_26204() == class_2246.field_10540 || mc.field_1687.method_8320(pos).method_26204() == class_2246.field_9987 || mc.field_1687.method_8320(pos).method_26204() == class_2246.field_10443;
   }

   public static List<Hole> getHoles(double range, class_2338 playerPos, boolean doubles) {
      ArrayList<Hole> holes = new ArrayList();

      for(class_2338 pos : BlockUtils.sphere(range, playerPos, true, false)) {
         if (mc.field_1687.method_8320(pos).method_26204() == class_2246.field_10124) {
            if (isObbyHole(pos)) {
               holes.add(new Hole(false, false, pos));
            } else if (isBedrockHoles(pos)) {
               holes.add(new Hole(true, false, pos));
            } else if (doubles) {
               Hole dh = isDoubleHole(pos);
               if (dh != null && (mc.field_1687.method_8320(dh.pos1.method_10069(0, 1, 0)).method_26204() == class_2246.field_10124 || mc.field_1687.method_8320(dh.pos2.method_10069(0, 1, 0)).method_26204() == class_2246.field_10124)) {
                  holes.add(dh);
               }
            }
         }
      }

      return holes;
   }

   public static List<Hole> getHoles(double range, class_2338 playerPos, boolean doubles, boolean self) {
      ArrayList<Hole> holes = new ArrayList();

      for(class_2338 pos : BlockUtils.sphere(range, playerPos, true, false)) {
         if ((!pos.equals(playerPos) || !self) && mc.field_1687.method_8320(pos).method_26204() == class_2246.field_10124) {
            if (isObbyHole(pos)) {
               holes.add(new Hole(false, false, pos));
            } else if (isBedrockHoles(pos)) {
               holes.add(new Hole(true, false, pos));
            } else if (doubles) {
               Hole dh = isDoubleHole(pos);
               if (dh != null && (mc.field_1687.method_8320(dh.pos1.method_10069(0, 1, 0)).method_26204() == class_2246.field_10124 || mc.field_1687.method_8320(dh.pos2.method_10069(0, 1, 0)).method_26204() == class_2246.field_10124)) {
                  holes.add(dh);
               }
            }
         }
      }

      return holes;
   }

   public static List<Hole> getHolesHolesnap(double range, class_2338 playerPos, boolean doubles, boolean self) {
      ArrayList<Hole> holes = new ArrayList();

      for(class_2338 pos : BlockUtils.sphere(range, playerPos, true, false)) {
         if ((!pos.equals(playerPos) || !self) && mc.field_1687.method_8320(pos).method_26204() == class_2246.field_10124) {
            if (isObbyHole(pos)) {
               if (!skyCheck(pos)) {
                  holes.add(new Hole(false, false, pos));
               }
            } else if (isBedrockHoles(pos)) {
               if (!skyCheck(pos)) {
                  holes.add(new Hole(true, false, pos));
               }
            } else if (doubles) {
               Hole dh = isDoubleHole(pos);
               if (dh != null && (!dh.pos2.equals(playerPos) || !self)) {
                  boolean flag = mc.field_1687.method_8320(dh.pos1.method_10069(0, 1, 0)).method_26204() == class_2246.field_10124;
                  boolean flag2 = mc.field_1687.method_8320(dh.pos2.method_10069(0, 1, 0)).method_26204() == class_2246.field_10124;
                  if (flag || flag2) {
                     if (!flag) {
                        dh.toTarget = dh.pos2;
                     } else if (!flag2) {
                        dh.toTarget = dh.pos1;
                     }

                     if ((dh.toTarget == null || !skyCheck(dh.toTarget)) && (dh.toTarget != null || !skyCheck(dh.pos1) && !skyCheck(dh.pos2))) {
                        holes.add(dh);
                     }
                  }
               }
            }
         }
      }

      return holes;
   }

   public static boolean skyCheck(class_2338 pos) {
      for(int i = 1; i < 4; ++i) {
         if (mc.field_1687.method_8320(pos.method_10086(i)).method_26204() != class_2246.field_10124) {
            return true;
         }
      }

      return false;
   }

   public static double distanceTo(Hole hole) {
      class_243 targetPos;
      if (hole.doubleHole) {
         if (hole.toTarget != null) {
            targetPos = new class_243((double)hole.toTarget.method_10263() + (double)0.5F, mc.field_1724.method_23318(), (double)hole.toTarget.method_10260() + (double)0.5F);
         } else {
            class_2338 pos1 = hole.pos1;
            class_2338 pos2 = hole.pos2;
            double centerX = ((double)pos1.method_10263() + (double)0.5F + (double)pos2.method_10263() + (double)0.5F) / (double)2.0F;
            double centerZ = ((double)pos1.method_10260() + (double)0.5F + (double)pos2.method_10260() + (double)0.5F) / (double)2.0F;
            targetPos = new class_243(centerX, mc.field_1724.method_23318(), centerZ);
         }
      } else {
         targetPos = new class_243((double)hole.pos1.method_10263() + (double)0.5F, mc.field_1724.method_23318(), (double)hole.pos1.method_10260() + (double)0.5F);
      }

      return mc.field_1724.method_19538().method_1022(targetPos);
   }

   public static class Hole {
      public boolean bedrock;
      public boolean doubleHole;
      public class_2338 pos1;
      public class_2338 pos2;
      public class_2338 toTarget;

      public Hole(boolean bedrock, boolean doubleHole, class_2338 pos1, class_2338 pos2) {
         this.bedrock = bedrock;
         this.doubleHole = doubleHole;
         this.pos1 = pos1;
         this.pos2 = pos2;
      }

      public Hole(boolean bedrock, boolean doubleHole, class_2338 pos1, class_2338 pos2, class_2338 toTarget) {
         this.bedrock = bedrock;
         this.doubleHole = doubleHole;
         this.pos1 = pos1;
         this.pos2 = pos2;
         this.toTarget = toTarget;
      }

      public Hole(boolean bedrock, boolean doubleHole, class_2338 pos1) {
         this.bedrock = bedrock;
         this.doubleHole = doubleHole;
         this.pos1 = pos1;
      }
   }
}
