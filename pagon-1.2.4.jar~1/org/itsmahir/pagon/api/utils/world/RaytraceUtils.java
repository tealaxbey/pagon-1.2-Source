package org.itsmahir.pagon.api.utils.world;

import java.util.Optional;
import net.minecraft.class_1297;
import net.minecraft.class_1675;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_5134;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.jetbrains.annotations.NotNull;

public class RaytraceUtils implements IMinecraft {
   public static class_3965 getResult(class_243 vec) {
      float[] rotation = RotationUtils.getRotationsTo(mc.field_1724.method_33571(), vec);
      return getBlockHitResult(rotation[0], rotation[1], 7.0F);
   }

   public static class_3965 getBlockHitResult(float yaw, float pitch, class_3959.class_3960 type) {
      return getBlockHitResult(yaw, pitch, (float)mc.field_1724.method_6127().method_26852(class_5134.field_47758), mc.field_1724, type);
   }

   public static class_3965 getBlockHitResult(float yaw, float pitch) {
      return getBlockHitResult(yaw, pitch, (float)mc.field_1724.method_6127().method_26852(class_5134.field_47758));
   }

   public static class_3965 getBlockHitResultWithEntity(float yaw, float pitch, class_1297 from) {
      return getBlockHitResult(yaw, pitch, (float)mc.field_1724.method_6127().method_26852(class_5134.field_47758), from, class_3960.field_23142);
   }

   public static class_3965 getBlockHitResult(float yaw, float pitch, float distance) {
      return getBlockHitResult(yaw, pitch, distance, mc.field_1724, class_3960.field_23142);
   }

   public static class_3965 getBlockHitResult(float yaw, float pitch, float d, class_1297 from, class_3959.class_3960 shapeType) {
      class_243 vec3d = mc.field_1724.method_33571();
      class_243 lookVec = RotationUtils.getRotationVector(yaw, pitch);
      class_243 rotations = vec3d.method_1031(lookVec.field_1352 * (double)d, lookVec.field_1351 * (double)d, lookVec.field_1350 * (double)d);
      return (class_3965)Optional.ofNullable(mc.field_1687.method_17742(new class_3959(vec3d, rotations, shapeType, class_242.field_1348, from))).orElseGet(() -> new class_3965(new class_243((double)0.5F, (double)1.0F, (double)0.5F), class_2350.field_11036, class_2338.field_10980, false));
   }

   public static class_239 rayCast(double reach, float[] angles) {
      double eyeHeight = (double)mc.field_1724.method_5751();
      class_243 eyes = new class_243(mc.field_1724.method_23317(), mc.field_1724.method_23318() + eyeHeight, mc.field_1724.method_23321());
      return rayCast(reach, eyes, angles);
   }

   public static class_239 rayCast(double reach, class_243 position, float[] angles) {
      if (!Float.isNaN(angles[0]) && !Float.isNaN(angles[1])) {
         class_243 rotationVector = RotationUtils.getRotationVector(angles[1], angles[0]);
         return mc.field_1687.method_17742(new class_3959(position, position.method_1031(rotationVector.field_1352 * reach, rotationVector.field_1351 * reach, rotationVector.field_1350 * reach), class_3960.field_17558, class_242.field_1348, mc.field_1724));
      } else {
         return null;
      }
   }

   public static class_239 isLookingResult(class_1297 camera, class_1297 target, class_243 position, float[] angles, float reach) {
      double d = (double)Math.max(reach, reach);
      double e = class_3532.method_33723(d);
      class_243 vec3d2 = RotationUtils.getRotationVector(angles[0], angles[1]);
      class_243 vec3d3 = position.method_1031(vec3d2.field_1352 * d, vec3d2.field_1351 * d, vec3d2.field_1350 * d);
      class_238 box = camera.method_5829().method_18804(vec3d2.method_1021(d)).method_1009((double)1.0F, (double)1.0F, (double)1.0F);
      return class_1675.method_18075(camera, position, vec3d3, box, (entity) -> entity.method_5863() && entity.equals(target), e);
   }

   public static class_243 getVisibleDirectionPoint(@NotNull class_2350 dir, @NotNull class_2338 bp, float wallRange, float range) {
      class_238 brutBox = getDirectionBox(dir);
      if (brutBox.field_1320 - brutBox.field_1323 == (double)0.0F) {
         for(double y = brutBox.field_1322; y < brutBox.field_1325; y += (double)0.1F) {
            for(double z = brutBox.field_1321; z < brutBox.field_1324; z += (double)0.1F) {
               class_243 point = new class_243((double)bp.method_10263() + brutBox.field_1323, (double)bp.method_10264() + y, (double)bp.method_10260() + z);
               if (!shouldSkipPoint(point, bp, dir, wallRange, range)) {
                  return point;
               }
            }
         }
      }

      if (brutBox.field_1325 - brutBox.field_1322 == (double)0.0F) {
         for(double x = brutBox.field_1323; x < brutBox.field_1320; x += (double)0.1F) {
            for(double z = brutBox.field_1321; z < brutBox.field_1324; z += (double)0.1F) {
               class_243 point = new class_243((double)bp.method_10263() + x, (double)bp.method_10264() + brutBox.field_1322, (double)bp.method_10260() + z);
               if (!shouldSkipPoint(point, bp, dir, wallRange, range)) {
                  return point;
               }
            }
         }
      }

      if (brutBox.field_1324 - brutBox.field_1321 == (double)0.0F) {
         for(double x = brutBox.field_1323; x < brutBox.field_1320; x += (double)0.1F) {
            for(double y = brutBox.field_1322; y < brutBox.field_1325; y += (double)0.1F) {
               class_243 point = new class_243((double)bp.method_10263() + x, (double)bp.method_10264() + y, (double)bp.method_10260() + brutBox.field_1321);
               if (!shouldSkipPoint(point, bp, dir, wallRange, range)) {
                  return point;
               }
            }
         }
      }

      return null;
   }

   private static @NotNull class_238 getDirectionBox(class_2350 dir) {
      class_238 var10000;
      switch (dir) {
         case field_11036 -> var10000 = new class_238((double)0.15F, (double)1.0F, (double)0.15F, (double)0.85F, (double)1.0F, (double)0.85F);
         case field_11033 -> var10000 = new class_238((double)0.15F, (double)0.0F, (double)0.15F, (double)0.85F, (double)0.0F, (double)0.85F);
         case field_11034 -> var10000 = new class_238((double)1.0F, (double)0.15F, (double)0.15F, (double)1.0F, (double)0.85F, (double)0.85F);
         case field_11039 -> var10000 = new class_238((double)0.0F, (double)0.15F, (double)0.15F, (double)0.0F, (double)0.85F, (double)0.85F);
         case field_11043 -> var10000 = new class_238((double)0.15F, (double)0.15F, (double)0.0F, (double)0.85F, (double)0.85F, (double)0.0F);
         case field_11035 -> var10000 = new class_238((double)0.15F, (double)0.15F, (double)1.0F, (double)0.85F, (double)0.85F, (double)1.0F);
         default -> throw new MatchException((String)null, (Throwable)null);
      }

      return var10000;
   }

   private static boolean shouldSkipPoint(class_243 point, class_2338 bp, class_2350 dir, float wallRange, float range) {
      class_3959 context = new class_3959(getEyesPos(mc.field_1724), point, class_3960.field_17558, class_242.field_1348, mc.field_1724);
      class_3965 result = mc.field_1687.method_17742(context);
      float dst = squaredDistanceFromEyes(point);
      if (result != null && result.method_17783() == class_240.field_1332 && !result.method_17777().equals(bp) && dst > wallRange * wallRange) {
         return true;
      } else {
         return dst > range * range;
      }
   }

   public static float squaredDistanceFromEyes(@NotNull class_243 vec) {
      double d0 = vec.field_1352 - mc.field_1724.method_23317();
      double d1 = vec.field_1350 - mc.field_1724.method_23321();
      double d2 = vec.field_1351 - (mc.field_1724.method_23318() + (double)mc.field_1724.method_18381(mc.field_1724.method_18376()));
      return (float)(d0 * d0 + d1 * d1 + d2 * d2);
   }

   public static class_243 getEyesPos(@NotNull class_1297 entity) {
      return entity.method_19538().method_1031((double)0.0F, (double)entity.method_18381(entity.method_18376()), (double)0.0F);
   }
}
