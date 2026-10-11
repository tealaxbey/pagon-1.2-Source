package org.itsmahir.pagon.api.utils.targeting;

import java.util.ArrayList;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1429;
import net.minecraft.class_1588;
import net.minecraft.class_1690;
import net.minecraft.class_1695;
import net.minecraft.class_243;
import org.itsmahir.pagon.api.management.FriendManager;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.wrapper.IMinecraft;

public class EntityTargeter implements IMinecraft {
   class_1297 targetFrom;
   Sorting mode;
   float range;
   ArrayList<class_1299> toTarget;
   double maxFovRange;

   public EntityTargeter(class_1297 targetFrom, Sorting mode, float range, ArrayList<class_1299> toTarget) {
      this.targetFrom = targetFrom;
      this.mode = mode;
      this.range = range;
      this.toTarget = toTarget;
      this.maxFovRange = Double.MAX_VALUE;
   }

   public EntityTargeter(class_1297 targetFrom, Sorting mode, float range, ArrayList<class_1299> toTarget, double maxFovRange) {
      this.targetFrom = targetFrom;
      this.mode = mode;
      this.range = range;
      this.toTarget = toTarget;
      this.maxFovRange = maxFovRange;
   }

   public class_1297 findTarget(class_243 pos) {
      class_1297 target = null;
      double maxDist = (double)this.range;
      double maxFovChange = this.maxFovRange;

      for(class_1297 e : mc.field_1687.method_18112()) {
         if (e != null && this.canTarget(e) && this.isValid(e)) {
            double currentDist = pos.method_1022(e.method_33571());
            if (currentDist <= maxDist) {
               if (this.mode.equals(Sorting.DISTANCE)) {
                  maxDist = currentDist;
                  target = e;
               } else if (this.mode.equals(Sorting.FOV)) {
                  double change;
                  if (maxFovChange > (change = Math.abs(PlayerUtils.fovFromEntity(e)))) {
                     target = e;
                     maxFovChange = change;
                  }
               } else if (this.mode.equals(Sorting.HEALTH)) {
                  float health = ((class_1309)e).method_6032() + ((class_1309)e).method_6067();
                  float var10000;
                  if (target instanceof class_1309) {
                     class_1309 t = (class_1309)target;
                     var10000 = t.method_6032() + t.method_6067();
                  } else {
                     var10000 = Float.MAX_VALUE;
                  }

                  float lowestHealth = var10000;
                  if (health < lowestHealth) {
                     target = e;
                  }
               }
            }
         }
      }

      return target;
   }

   private boolean canTarget(class_1297 entity) {
      if (FriendManager.INSTANCE.isFriend(entity)) {
         return false;
      } else if (this.toTarget.contains(class_1299.field_6051) && entity instanceof class_1588) {
         return true;
      } else if (this.toTarget.contains(class_1299.field_6093) && entity instanceof class_1429) {
         return true;
      } else {
         return (entity instanceof class_1690 || entity instanceof class_1695) && this.toTarget.contains(class_1299.field_6121) ? true : this.toTarget.contains(entity.method_5864());
      }
   }

   public boolean isValid(class_1297 entity) {
      if (entity instanceof class_1309 ent) {
         if (!(ent.method_6032() <= 0.0F) && !ent.method_29504()) {
            return entity != this.targetFrom;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }
}
