package org.itsmahir.pagon.api.utils.world;

import net.minecraft.class_1294;
import net.minecraft.class_1304;
import net.minecraft.class_1799;
import net.minecraft.class_1893;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.wrapper.IMinecraft;

public class MineUtils implements IMinecraft {
   public static boolean canHarvestBlock(class_2680 state, class_1799 stack) {
      if (state.method_29291()) {
         return true;
      } else {
         return stack.method_7960() ? stack.method_7909().method_58405(stack, state) : false;
      }
   }

   public static int findBestTool(class_2338 pos) {
      return findBestTool(pos, mc.field_1687.method_8320(pos));
   }

   public static int findBestTool(class_2338 pos, class_2680 state) {
      int result = mc.field_1724.method_31548().field_7545;
      if (state.method_26214(mc.field_1687, pos) > 0.0F) {
         double speed = getSpeed(state, mc.field_1724.method_6047());

         for(int i = 0; i < 9; ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            double stackSpeed = getSpeed(state, stack);
            if (stackSpeed > speed) {
               speed = stackSpeed;
               result = i;
            }
         }
      }

      return result;
   }

   public static double getSpeed(class_2680 state, class_1799 stack) {
      double str = (double)stack.method_7924(state);
      int effect = InventoryUtils.getEnchantmentLevel(stack, class_1893.field_9131);
      return Math.max(str + (str > (double)1.0F ? (double)(effect * effect) + (double)1.0F : (double)0.0F), (double)0.0F);
   }

   public static float getDamage(class_1799 stack, class_2338 pos, boolean auto, boolean onGround) {
      class_2680 state = mc.field_1687.method_8320(pos);
      return getDamage(state, stack, auto, onGround);
   }

   public static float getDamage(class_2680 state, class_1799 stack, boolean auto, boolean onGround) {
      float hardness = state.method_26204().method_36555();
      return hardness == -1.0F ? 0.0F : getDigSpeed(stack, state, auto, onGround) / (hardness * (float)(canHarvestBlock(state, stack) ? 30 : 100));
   }

   public static float getDamage(class_2680 state, class_1799 stack, class_2338 pos, boolean onGround, boolean isOnGround) {
      float hardness = state.method_26214(mc.field_1687, pos);
      return hardness == -1.0F ? 0.0F : getDigSpeed(stack, state, onGround, isOnGround) / (hardness * (float)(canHarvestBlock(state, stack) ? 30 : 100));
   }

   private static float getDigSpeed(class_1799 stack, class_2680 state, boolean auto, boolean onGround) {
      float digSpeed = 1.0F;
      if (!stack.method_7960()) {
         digSpeed *= stack.method_7924(state);
      }

      if (digSpeed > 1.0F) {
         int i = InventoryUtils.getEnchantmentLevel(stack, class_1893.field_9131);
         if (i > 0 && !stack.method_7960()) {
            digSpeed += (float)(i * i + 1);
         }
      }

      if (mc.field_1724.method_6059(class_1294.field_5917)) {
         digSpeed *= 1.0F + (float)(mc.field_1724.method_6112(class_1294.field_5917).method_5578() + 1) * 0.2F;
      }

      if (mc.field_1724.method_6059(class_1294.field_5901)) {
         float miningFatigue;
         switch (mc.field_1724.method_6112(class_1294.field_5901).method_5578()) {
            case 0:
               miningFatigue = 0.3F;
               break;
            case 1:
               miningFatigue = 0.09F;
               break;
            case 2:
               miningFatigue = 0.0027F;
               break;
            case 3:
            default:
               miningFatigue = 8.1E-4F;
         }

         digSpeed *= miningFatigue;
      }

      if (mc.field_1724.method_5816() && InventoryUtils.getEnchantmentLevel(mc.field_1724.method_6118(class_1304.field_6169), class_1893.field_9105) <= 0) {
         digSpeed /= 5.0F;
      }

      if (auto && !mc.field_1724.method_24828() || !auto && !onGround) {
         digSpeed /= 5.0F;
      }

      return digSpeed < 0.0F ? 0.0F : digSpeed;
   }

   public static boolean canBreak(class_2338 pos) {
      return canBreak(mc.field_1687.method_8320(pos), pos);
   }

   public static boolean canBreak(class_2680 state, class_2338 pos) {
      return state.method_26214(mc.field_1687, pos) != -1.0F && state.method_26204() != class_2246.field_10124 && !state.method_51176();
   }
}
