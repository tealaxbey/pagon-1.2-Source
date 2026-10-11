package org.itsmahir.pagon.api.utils.players;

import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1829;
import net.minecraft.class_1893;
import org.itsmahir.pagon.api.wrapper.IMinecraft;

public class Utils32k implements IMinecraft {
   public static boolean is32kInHotbar() {
      for(int i = 0; i < 9; ++i) {
         class_1799 stack = mc.field_1724.method_31548().method_5438(i);
         if (stack != class_1799.field_8037 && checkSharpness(stack)) {
            return true;
         }
      }

      return false;
   }

   public static int findReverted32ks() {
      for(int i = 0; i < 9; ++i) {
         class_1799 stack = mc.field_1724.method_31548().method_5438(i);
         if (stack != class_1799.field_8037 && stack.method_7909() instanceof class_1829 && !checkSharpness(stack)) {
            return i;
         }
      }

      return -1;
   }

   public static boolean isHolding32k(class_1657 player) {
      return checkSharpness(player.method_6047());
   }

   public static boolean checkSharpness(class_1799 stack) {
      if (stack.method_7960()) {
         return false;
      } else if (!stack.method_7942()) {
         return false;
      } else {
         float level = (float)InventoryUtils.getEnchantmentLevel(stack, class_1893.field_9118);
         return level > 42.0F;
      }
   }

   public static boolean checkEnchant(class_1799 stack) {
      if (stack.method_7960()) {
         return false;
      } else if (!stack.method_7942()) {
         return false;
      } else {
         float level = (float)InventoryUtils.getEnchantmentLevel(stack, class_1893.field_9118);
         return level > 42.0F;
      }
   }
}
