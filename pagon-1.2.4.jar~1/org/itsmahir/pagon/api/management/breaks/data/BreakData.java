package org.itsmahir.pagon.api.management.breaks.data;

import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1893;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_7924;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.utils.world.MineUtils;
import org.itsmahir.pagon.api.wrapper.IMinecraft;

public class BreakData implements IMinecraft {
   private final class_2338 pos;
   float bestDamage;
   Timer timeout;
   boolean render = true;

   public BreakData(class_2338 pos) {
      this.pos = pos;
      this.bestDamage = 0.0F;
      this.timeout = new Timer(4000L);
      this.timeout.resetDelay();
      this.render = true;
   }

   public boolean canRender() {
      return this.render;
   }

   public void updateDamage(class_2680 state) {
      class_1799 stack = class_1802.field_22024.method_7854();
      stack.method_7978(mc.field_1687.method_30349().method_30530(class_7924.field_41265).method_40290(class_1893.field_9131), 5);
      float damage = MineUtils.getDamage(state, stack, false, true);
      this.bestDamage = MathUtil.clamp(this.bestDamage + damage, 0.0F, Float.MAX_VALUE);
   }

   public boolean tick() {
      if (this.timeout.isPassed()) {
         return true;
      } else {
         class_2680 state = BlockUtils.getBlockState(this.pos);
         if (state.method_26215()) {
            this.render = false;
            return false;
         } else if (!BlockUtils.isMineable(state)) {
            this.render = false;
            return false;
         } else {
            this.updateDamage(state);
            return false;
         }
      }
   }

   public class_2338 getPos() {
      return this.pos;
   }

   public float getBestDamage() {
      return this.bestDamage;
   }

   public Timer getTimeout() {
      return this.timeout;
   }

   public boolean isRender() {
      return this.render;
   }
}
