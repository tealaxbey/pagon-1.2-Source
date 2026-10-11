package org.itsmahir.pagon.api.utils.players;

import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1671;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2404;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_266;
import net.minecraft.class_269;
import net.minecraft.class_2824;
import net.minecraft.class_2828;
import net.minecraft.class_2848;
import net.minecraft.class_2886;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_638;
import net.minecraft.class_7202;
import net.minecraft.class_7204;
import net.minecraft.class_744;
import net.minecraft.class_746;
import net.minecraft.class_8646;
import net.minecraft.class_9013;
import net.minecraft.class_9015;
import net.minecraft.class_239.class_240;
import net.minecraft.class_2848.class_2849;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.itsmahir.pagon.api.event.events.move.MoveEvent;
import org.itsmahir.pagon.api.management.BoostManager;
import org.itsmahir.pagon.api.management.FriendManager;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.management.RotationManager;
import org.itsmahir.pagon.api.utils.chat.ChatMessage;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;
import org.itsmahir.pagon.api.utils.ducks.IClientPlayerEntity;
import org.itsmahir.pagon.api.utils.ducks.IVec3d;
import org.itsmahir.pagon.api.utils.players.rotation.Rotation;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;
import org.itsmahir.pagon.impl.features.modules.combat.Pusher;
import org.itsmahir.pagon.impl.features.modules.movement.PhaseWalk;
import org.itsmahir.pagon.mixin.accessor.IClientWorld;
import org.itsmahir.pagon.mixin.accessor.IFireworkRocketEntity;
import org.itsmahir.pagon.mixin.accessor.ILivingEntity;

public class PlayerUtils implements IMinecraft {
   public static boolean runningPhysics = false;

   public static String getColoredHealth(class_1657 entityPlayer, boolean friends) {
      double ceil = Math.ceil((double)(entityPlayer.method_6032() + entityPlayer.method_6067()));
      String var10000 = String.valueOf(getHealthColor(entityPlayer, friends, false));
      return var10000 + (int)ceil;
   }

   public static class_124 getHealthColor(class_1657 entityPlayer, boolean friends, boolean fromTab) {
      float health = fromTab ? (float)getTabHealth(entityPlayer.method_5477().getString()) : entityPlayer.method_6032() + entityPlayer.method_6067();
      if (friends && FriendManager.INSTANCE.isFriend(entityPlayer)) {
         return class_124.field_1078;
      } else {
         return health > 18.0F ? class_124.field_1060 : (health > 16.0F ? class_124.field_1077 : (health > 12.0F ? class_124.field_1054 : (health > 8.0F ? class_124.field_1065 : (health > 5.0F ? class_124.field_1061 : class_124.field_1079))));
      }
   }

   public static class_2338 getPlayerPos() {
      return BlockUtils.getRoundedBlockPos(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321());
   }

   public static class_2338 getPos(class_1297 entity) {
      return BlockUtils.getRoundedBlockPos(entity.method_23317(), entity.method_23318(), entity.method_23321());
   }

   public static float getStrictBaseSpeed(float baseSpeed) {
      float maxModifier = baseSpeed;
      if (mc.field_1724.method_6059(class_1294.field_5904)) {
         maxModifier = (float)((double)baseSpeed * ((double)1.0F + 0.1 * (double)mc.field_1724.method_6112(class_1294.field_5904).method_5578()));
      }

      return maxModifier;
   }

   public static boolean isBoostedByFirework() {
      for(class_1297 entity : mc.field_1687.method_18112()) {
         if (entity instanceof class_1671 firework) {
            if (mc.field_1724.equals(((IFireworkRocketEntity)firework).getShooter())) {
               return true;
            }
         }
      }

      return false;
   }

   public static String getColoredDistance(class_1657 entityPlayer) {
      float distance = mc.field_1724.method_5739(entityPlayer);
      if (distance <= 5.0F) {
         return class_124.field_1061.toString();
      } else if (distance > 5.0F && distance <= 10.0F) {
         return class_124.field_1065.toString();
      } else if (distance > 10.0F && distance <= 15.0F) {
         return class_124.field_1054.toString();
      } else {
         return distance > 15.0F && distance <= 20.0F ? class_124.field_1077.toString() : class_124.field_1060.toString();
      }
   }

   public static float getBaseSpeed(float baseSpeed) {
      float maxModifier = baseSpeed;
      if (mc.field_1724.method_6059(class_1294.field_5904)) {
         maxModifier = (float)((double)baseSpeed * ((double)1.0F + 0.2 * (double)mc.field_1724.method_6112(class_1294.field_5904).method_5578()));
      }

      return maxModifier;
   }

   public static double getDefaultBaseSpeed(boolean boost) {
      double baseSpeed = 0.2873;
      if (mc.field_1724 != null && mc.field_1724.method_6059(class_1294.field_5904)) {
         int amplifier = mc.field_1724.method_6112(class_1294.field_5904).method_5578();
         baseSpeed *= (double)1.0F + 0.2 * (double)(amplifier + 1);
      }

      if (boost) {
         baseSpeed += BoostManager.INSTANCE.getBoostSpeed(false);
         baseSpeed = Math.min(baseSpeed, 0.465);
      }

      return baseSpeed;
   }

   public static void doStrafe(MoveEvent event, float speed) {
      if (isMoving()) {
         class_241 strafe = getStrafeVec(speed);
         event.setX((double)strafe.field_1343);
         event.setZ((double)strafe.field_1342);
      } else {
         event.setX((double)0.0F);
         event.setZ((double)0.0F);
      }

   }

   public static double distanceTo(Pusher.HoleCampBlock blockPos) {
      class_2338 pos = blockPos.getPos().method_10093(blockPos.getDirection());
      return pos.method_46558().method_1025(mc.field_1724.method_33571());
   }

   public static double[] getMoveSpeed(double speed) {
      float forward = mc.field_1724.field_3913.field_3905;
      float strafe = mc.field_1724.field_3913.field_3907;
      float yaw = mc.field_1724.method_36454();
      if (!isMoving()) {
         return new double[]{(double)0.0F, (double)0.0F};
      } else {
         if (forward != 0.0F) {
            if (strafe >= 1.0F) {
               yaw += (float)(forward > 0.0F ? -45 : 45);
               strafe = 0.0F;
            } else if (strafe <= -1.0F) {
               yaw += (float)(forward > 0.0F ? 45 : -45);
               strafe = 0.0F;
            }

            if (forward > 0.0F) {
               forward = 1.0F;
            } else if (forward < 0.0F) {
               forward = -1.0F;
            }
         }

         double sin = Math.sin(Math.toRadians((double)(yaw + 90.0F)));
         double cos = Math.cos(Math.toRadians((double)(yaw + 90.0F)));
         double motionX = (double)forward * speed * cos + (double)strafe * speed * sin;
         double motionZ = (double)forward * speed * sin - (double)strafe * speed * cos;
         return new double[]{motionX, motionZ};
      }
   }

   public static double fovFromEntity(class_1297 en) {
      return ((double)(mc.field_1724.method_36454() - fovToEntity(en)) % (double)360.0F + (double)540.0F) % (double)360.0F - (double)180.0F;
   }

   public static float fovToEntity(class_1297 ent) {
      double x = ent.method_23317() - mc.field_1724.method_23317();
      double z = ent.method_23321() - mc.field_1724.method_23321();
      double yaw = Math.atan2(x, z) * 57.2957795;
      return (float)(yaw * (double)-1.0F);
   }

   public static class_241 getStrafeVec(float speed) {
      float forward = mc.field_1724.field_3913.field_3905;
      float strafe = mc.field_1724.field_3913.field_3907;
      float yaw = mc.field_1724.field_5982 + (mc.field_1724.method_36454() - mc.field_1724.field_5982) * mc.method_60646().method_60637(false);
      if ((Boolean)AntiCheat.INSTANCE.strafeFix.getValue()) {
         yaw = RotationUtils.getActualYaw();
      }

      if (forward == 0.0F && strafe == 0.0F) {
         return class_241.field_1340;
      } else {
         if (forward != 0.0F) {
            if (strafe >= 1.0F) {
               yaw += forward > 0.0F ? -45.0F : 45.0F;
               strafe = 0.0F;
            } else if (strafe <= -1.0F) {
               yaw += forward > 0.0F ? 45.0F : -45.0F;
               strafe = 0.0F;
            }

            if (forward > 0.0F) {
               forward = 1.0F;
            } else if (forward < 0.0F) {
               forward = -1.0F;
            }
         }

         float rx = (float)Math.cos(Math.toRadians((double)yaw));
         float rz = (float)(-Math.sin(Math.toRadians((double)yaw)));
         return new class_241(forward * speed * rz + strafe * speed * rx, forward * speed * rx - strafe * speed * rz);
      }
   }

   public static boolean canSneak(class_241 vec) {
      class_243 velocity = mc.field_1724.method_18798();
      double shiftX = velocity.method_10216() - (double)vec.field_1343;
      double shiftZ = velocity.method_10215() - (double)vec.field_1342;
      return Math.abs(shiftX) > 9.0E-4 || Math.abs(shiftZ) > 9.0E-4;
   }

   public static void setSpeed(double speed) {
      double[] dir = forward(speed);
      setMotionX(dir[0]);
      setMotionZ(dir[1]);
   }

   public static void setSpeed(double speed, MoveEvent event) {
      double[] dir = forward(speed);
      event.motionX(dir[0]);
      event.motionZ(dir[1]);
   }

   public static float getYawOffset(class_744 input, float rotationYaw) {
      if (input.field_3905 < 0.0F) {
         rotationYaw += 180.0F;
      }

      float forward = 1.0F;
      if (input.field_3905 < 0.0F) {
         forward = -0.5F;
      } else if (input.field_3905 > 0.0F) {
         forward = 0.5F;
      }

      float strafe = input.field_3907;
      if (strafe > 0.0F) {
         rotationYaw -= 90.0F * forward;
      }

      if (strafe < 0.0F) {
         rotationYaw += 90.0F * forward;
      }

      return rotationYaw;
   }

   public static double getGroundLevel() {
      for(int i = (int)Math.round(mc.field_1724.method_23318()); i > mc.field_1687.method_31607(); --i) {
         class_238 mybb = mc.field_1724.method_5829();
         class_238 box = new class_238(mybb.field_1323, (double)(i - 1), mybb.field_1321, mybb.field_1320, (double)i, mybb.field_1324);
         if (mc.field_1687.method_39454(mc.field_1724, box) && box.field_1322 <= mc.field_1724.method_23318()) {
            return (double)i;
         }
      }

      return (double)mc.field_1687.method_31607();
   }

   public static double getGroundLevel(class_1297 entity) {
      for(int i = (int)Math.round(entity.method_23318()); i > mc.field_1687.method_31607(); --i) {
         class_238 mybb = entity.method_5829();
         class_238 box = new class_238(mybb.field_1323, (double)(i - 1), mybb.field_1321, mybb.field_1320, (double)i, mybb.field_1324);
         if (mc.field_1687.method_39454(entity, box) && box.field_1322 <= entity.method_23318()) {
            return (double)i;
         }
      }

      return (double)mc.field_1687.method_31607();
   }

   public static float getMoveYaw(float yaw) {
      if (mc.field_1690.field_1894.method_1434() && !mc.field_1690.field_1881.method_1434()) {
         if (mc.field_1690.field_1913.method_1434() && !mc.field_1690.field_1849.method_1434()) {
            yaw -= 45.0F;
         } else if (mc.field_1690.field_1849.method_1434() && !mc.field_1690.field_1913.method_1434()) {
            yaw += 45.0F;
         }
      } else if (mc.field_1690.field_1881.method_1434() && !mc.field_1690.field_1894.method_1434()) {
         yaw += 180.0F;
         if (mc.field_1690.field_1913.method_1434() && !mc.field_1690.field_1849.method_1434()) {
            yaw += 45.0F;
         } else if (mc.field_1690.field_1849.method_1434() && !mc.field_1690.field_1913.method_1434()) {
            yaw -= 45.0F;
         }
      } else if (mc.field_1690.field_1913.method_1434() && !mc.field_1690.field_1849.method_1434()) {
         yaw -= 90.0F;
      } else if (mc.field_1690.field_1849.method_1434() && !mc.field_1690.field_1913.method_1434()) {
         yaw += 90.0F;
      }

      return RotationUtils.fixYaw(class_3532.method_15393(yaw));
   }

   public static boolean isMoving() {
      class_746 player = mc.field_1724;
      if (player != null) {
         return player.field_3913.method_3128().method_35587() != 0.0F;
      } else {
         return false;
      }
   }

   public static boolean isMovingOrSneaking() {
      class_746 player = mc.field_1724;
      if (mc.field_1724.field_3913.field_3904 && !mc.field_1724.field_3913.field_3903) {
         return true;
      } else if (mc.field_1724.field_3913.field_3903 && mc.field_1724.field_3913.field_3904) {
         return true;
      } else if (player != null) {
         return player.field_3913.method_3128().method_35587() != 0.0F;
      } else {
         return false;
      }
   }

   public static boolean isActuallyMoving() {
      return mc.field_1724.field_3913.field_3905 != 0.0F || mc.field_1724.field_3913.field_3907 != 0.0F || mc.field_1724.method_18798().field_1351 != (double)0.0F;
   }

   public static String getDimensionNameFromID(int id) {
      switch (id) {
         case -1 -> {
            return "Nether";
         }
         case 0 -> {
            return "Overworld";
         }
         case 1 -> {
            return "End";
         }
         default -> {
            return "subhumaninterdimensional";
         }
      }
   }

   public static void attackTarget(class_1297 entity) {
      if (mc.field_1724.method_5624() && ((String)AntiCheat.INSTANCE.acMode.getValue()).equals("Strong")) {
         mc.field_1724.method_5728(false);
      }

      Rotation rot = RotationManager.INSTANCE.getRotation();
      if (rot != null) {
         PacketManager.INSTANCE.sendPacket(new class_2828.class_2831(rot.getYaw(), rot.getPitch(), mc.field_1724.method_24828()));
      }

      class_2824 packet = class_2824.method_34206(entity, mc.field_1724.method_5715());
      PacketManager.INSTANCE.sendPacket(packet);
      mc.field_1724.method_7324(entity);
      mc.field_1724.method_6104(class_1268.field_5808);
      mc.field_1724.method_7350();
   }

   public static class_241 safeWalk(double motionX, double motionZ) {
      double offset = 0.05;
      double moveX = motionX;
      double moveZ = motionZ;
      float fallDist = -mc.field_1724.method_49476();
      if (!mc.field_1724.method_24828()) {
         fallDist = -1.5F;
      }

      while(moveX != (double)0.0F && mc.field_1687.method_8587(mc.field_1724, mc.field_1724.method_5829().method_989(moveX, (double)fallDist, (double)0.0F))) {
         if (moveX < 0.05 && moveX >= -0.05) {
            moveX = (double)0.0F;
         } else if (moveX > (double)0.0F) {
            moveX -= 0.05;
         } else {
            moveX += 0.05;
         }
      }

      while(moveZ != (double)0.0F && mc.field_1687.method_8587(mc.field_1724, mc.field_1724.method_5829().method_989((double)0.0F, (double)fallDist, moveZ))) {
         if (moveZ < 0.05 && moveZ >= -0.05) {
            moveZ = (double)0.0F;
         } else if (moveZ > (double)0.0F) {
            moveZ -= 0.05;
         } else {
            moveZ += 0.05;
         }
      }

      while(moveX != (double)0.0F && moveZ != (double)0.0F && mc.field_1687.method_8587(mc.field_1724, mc.field_1724.method_5829().method_989(moveX, (double)fallDist, moveZ))) {
         if (moveX < 0.05 && moveX >= -0.05) {
            moveX = (double)0.0F;
         } else if (moveX > (double)0.0F) {
            moveX -= 0.05;
         } else {
            moveX += 0.05;
         }

         if (moveZ < 0.05 && moveZ >= -0.05) {
            moveZ = (double)0.0F;
         } else if (moveZ > (double)0.0F) {
            moveZ -= 0.05;
         } else {
            moveZ += 0.05;
         }
      }

      return new class_241((float)moveX, (float)moveZ);
   }

   public static boolean canEntityBeSeen(class_1297 entity) {
      class_243 entityPos = new class_243(entity.method_23317(), entity.method_23320(), entity.method_23321());
      return entity instanceof class_1511 && canVecBeSeen(entity.method_19538().method_1031((double)0.0F, 1.700000047683716, (double)0.0F)) ? true : canVecBeSeen(entityPos);
   }

   public static boolean canVecBeSeen(class_243 vec) {
      class_3965 result = mc.field_1687.method_17742(new class_3959(mc.field_1724.method_33571(), vec, class_3960.field_17558, class_242.field_1348, mc.field_1724));
      return result.method_17783() == class_240.field_1333;
   }

   public static void moveTowards(MoveEvent event, class_243 vec3d, double speed, boolean vertical) {
      float[] rots = RotationUtils.getRotationsTo(mc.field_1724.method_33571(), vec3d);
      double angle = Math.toRadians((double)rots[0]);
      double x = -Math.sin(angle) * speed;
      double y = (double)(-class_3532.method_15374((float)Math.toRadians((double)rots[1]))) * speed;
      double z = Math.cos(angle) * speed;
      double[] difference = new double[]{vec3d.field_1352 - mc.field_1724.method_23317(), vec3d.field_1351 - mc.field_1724.method_23318(), vec3d.field_1350 - mc.field_1724.method_23321()};
      event.setMovement(new class_243(Math.abs(x) < Math.abs(difference[0]) ? x : difference[0], event.getMovement().method_10214(), event.getMovement().method_10215()));
      if (vertical) {
         event.setMovement(new class_243(event.getMovement().method_10216(), Math.abs(y) < Math.abs(difference[1]) ? y : difference[1], event.getMovement().method_10215()));
      }

      event.setMovement(new class_243(event.getMovement().method_10216(), event.getMovement().method_10214(), Math.abs(z) < Math.abs(difference[2]) ? z : difference[2]));
      ((IVec3d)mc.field_1724.method_18798()).set((double)0.0F, mc.field_1724.method_18798().field_1351, (double)0.0F);
      event.setCancelled(true);
   }

   public static void setMotionXZ(double x, double z) {
      class_243 motion = mc.field_1724.method_18798();
      mc.field_1724.method_18800(x, motion.field_1351, z);
   }

   public static class_241 computeMovementInput() {
      float forward = 0.0F;
      if (mc.field_1690.field_1894.method_1434()) {
         ++forward;
      }

      if (mc.field_1690.field_1881.method_1434()) {
         --forward;
      }

      float sideways = 0.0F;
      if (mc.field_1690.field_1913.method_1434()) {
         ++sideways;
      }

      if (mc.field_1690.field_1849.method_1434()) {
         --sideways;
      }

      return new class_241(forward, sideways);
   }

   public void doYBoost(float maxSpeed) {
      if (mc.field_1724.method_18798().field_1351 < (double)0.0F) {
         class_243 current = mc.field_1724.method_18798();
         class_243 glide = this.glide(0.6F);
         double currentSpeed = Math.hypot(current.field_1352, current.field_1350);
         double glideSpeed = Math.hypot(glide.field_1352, glide.field_1350);
         if (currentSpeed < glideSpeed) {
            setMotionXZ(glide.field_1352, glide.field_1350);
            double postGlideSpeed = Math.hypot(mc.field_1724.method_18798().field_1352, mc.field_1724.method_18798().field_1350);
            if (postGlideSpeed > (double)maxSpeed) {
               setMotionXZ(mc.field_1724.method_18798().field_1352 * (double)maxSpeed / postGlideSpeed, mc.field_1724.method_18798().field_1350 * (double)maxSpeed / postGlideSpeed);
               if (currentSpeed < Math.hypot(mc.field_1724.method_18798().field_1352, mc.field_1724.method_18798().field_1350)) {
                  mc.field_1724.method_18799(current);
               }
            }
         }
      }

   }

   public class_243 glide(float speed) {
      class_243 glide = this.getGlideVec(speed / 50.0F);
      class_243 motion = mc.field_1724.method_18798();
      return new class_243(motion.field_1352 + glide.field_1352, (double)0.0F, motion.field_1350 + glide.field_1350);
   }

   public class_243 getGlideVec(float speed) {
      float yaw = mc.field_1724.method_36454();
      double x = (double)speed * Math.cos(Math.toRadians((double)(yaw + 90.0F)));
      double z = (double)speed * Math.sin(Math.toRadians((double)(yaw + 90.0F)));
      return new class_243(x, (double)0.0F, z);
   }

   private static class_7202 getUpdateManager(class_638 world) {
      return ((IClientWorld)world).accquirePendingUpdateManager();
   }

   public static void clientJump() {
      float f = mc.field_1724.method_6106();
      if (!(f <= 1.0E-5F)) {
         class_243 vec3d = mc.field_1724.method_18798();
         mc.field_1724.method_18800(vec3d.field_1352, (double)f, vec3d.field_1350);
         if (mc.field_1724.method_5624()) {
            float g = RotationUtils.getActualYaw() * ((float)Math.PI / 180F);
            mc.field_1724.method_45319(new class_243((double)(-class_3532.method_15374(g)) * 0.2, (double)0.0F, (double)class_3532.method_15362(g) * 0.2));
         }

         mc.field_1724.field_6007 = true;
      }

   }

   public static double[] forward(double d) {
      return forward(d, mc.field_1724.method_36454(), false);
   }

   public static double[] forward(double distance, float iYaw, boolean override) {
      float forward = override ? 1.0F : mc.field_1724.field_3913.field_3905;
      float side = override ? 0.0F : mc.field_1724.field_3913.field_3907;
      float yaw = iYaw;
      if (forward != 0.0F) {
         if (side > 0.0F) {
            yaw = iYaw + (float)(forward > 0.0F ? -45 : 45);
         } else if (side < 0.0F) {
            yaw = iYaw + (float)(forward > 0.0F ? 45 : -45);
         }

         side = 0.0F;
         if (forward > 0.0F) {
            forward = 1.0F;
         } else if (forward < 0.0F) {
            forward = -1.0F;
         }
      }

      double sin = Math.sin(Math.toRadians((double)(yaw + 90.0F)));
      double cos = Math.cos(Math.toRadians((double)(yaw + 90.0F)));
      double posX = (double)forward * distance * cos + (double)side * distance * sin;
      double posZ = (double)forward * distance * sin - (double)side * distance * cos;
      return new double[]{posX, posZ};
   }

   public static boolean isAboveWater(class_1297 entity) {
      return isAboveWater(entity, false);
   }

   public static boolean isAboveWater(class_1297 entity, boolean packet) {
      if (entity == null) {
         return false;
      } else {
         double y = entity.method_23318() - (packet ? 0.03 : (entity instanceof class_1657 ? 0.2 : (double)0.5F));

         for(int x = class_3532.method_15357(entity.method_23317()); x < class_3532.method_15384(entity.method_23317()); ++x) {
            for(int z = class_3532.method_15357(entity.method_23321()); z < class_3532.method_15384(entity.method_23321()); ++z) {
               class_2338 pos = new class_2338(x, class_3532.method_15357(y), z);
               if (mc.field_1687.method_8320(pos).method_26204() instanceof class_2404) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public static boolean isCloseToAboveWater(class_1297 entity, boolean packet) {
      if (entity == null) {
         return false;
      } else {
         double y = entity.method_23318() - (packet ? 0.1 : (entity instanceof class_1657 ? 0.2 : (double)0.5F));

         for(int x = class_3532.method_15357(entity.method_23317()); x < class_3532.method_15384(entity.method_23317()); ++x) {
            for(int z = class_3532.method_15357(entity.method_23321()); z < class_3532.method_15384(entity.method_23321()); ++z) {
               class_2338 pos = new class_2338(x, class_3532.method_15357(y), z);
               if (mc.field_1687.method_8320(pos).method_26204() instanceof class_2404) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public static boolean isInWater(class_1297 entity) {
      if (entity == null) {
         return false;
      } else {
         double y = entity.method_23318() + 0.01;

         for(int x = class_3532.method_15357(entity.method_23317()); x < class_3532.method_15384(entity.method_23317()); ++x) {
            for(int z = class_3532.method_15357(entity.method_23321()); z < class_3532.method_15384(entity.method_23321()); ++z) {
               class_2338 pos = new class_2338(x, (int)y, z);
               if (mc.field_1687.method_8320(pos).method_26204() instanceof class_2404) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public static void setMotionX(double x) {
      mc.field_1724.method_18800(x, mc.field_1724.method_18798().field_1351, mc.field_1724.method_18798().field_1350);
   }

   public static void setMotionY(double y) {
      mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, y, mc.field_1724.method_18798().field_1350);
   }

   public static void setMotionZ(double z) {
      mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, mc.field_1724.method_18798().field_1351, z);
   }

   public static boolean isEatingGap() {
      return (mc.field_1724.method_6047().method_7909().equals(class_1802.field_8463) || mc.field_1724.method_6047().method_7909().equals(class_1802.field_8367)) && mc.field_1724.method_6115();
   }

   public static boolean isElytraEquipped() {
      class_1799 stack = mc.field_1724.method_31548().method_7372(2);
      if (stack == null) {
         return false;
      } else {
         class_1792 chestplate = stack.method_7909();
         return chestplate.equals(class_1802.field_8833);
      }
   }

   public static void phaseSpeed(MoveEvent event, float speed, float factor) {
      if (isMoving()) {
         float boost = 0.0F;
         if (!mc.field_1724.field_5976 && PhaseWalk.INSTANCE.isPhasing() && mc.field_1724.method_24828()) {
            boost = factor * 0.2F + 0.1F;
         }

         class_241 strafe = getStrafeVec(speed + boost);
         event.motionX((double)strafe.field_1343);
         event.motionZ((double)strafe.field_1342);
      } else {
         event.setX((double)0.0F);
         event.setZ((double)0.0F);
      }

   }

   public static void runPhysicsTick() {
      if (!runningPhysics) {
         runningPhysics = true;
         int lastSwing = ((ILivingEntity)mc.field_1724).getLastAttackedTicks();
         int hurtTime = mc.field_1724.field_6235;
         float prevSwingProgress = mc.field_1724.field_6229;
         float swingProgress = mc.field_1724.field_6251;
         int swingProgressInt = mc.field_1724.field_6279;
         boolean isSwingInProgress = mc.field_1724.field_6252;
         float rotationYaw = mc.field_1724.method_36454();
         float prevRotationYaw = mc.field_1724.field_5982;
         float renderYawOffset = mc.field_1724.field_3932;
         float prevRenderYawOffset = mc.field_1724.field_3931;
         float rotationYawHead = mc.field_1724.field_6241;
         float prevRotationYawHead = mc.field_1724.field_6259;
         float cameraYaw = mc.field_1724.field_3932;
         float prevCameraYaw = mc.field_1724.field_3931;
         float walk = mc.field_1724.field_5994;
         float limbSwing = mc.field_1724.field_42108.method_48566();
         ((IClientPlayerEntity)mc.field_1724).doTick();
         ((ILivingEntity)mc.field_1724).setLastAttackedTicks(lastSwing);
         mc.field_1724.field_6235 = hurtTime;
         mc.field_1724.field_6229 = prevSwingProgress;
         mc.field_1724.field_6251 = swingProgress;
         mc.field_1724.field_6279 = swingProgressInt;
         mc.field_1724.field_6252 = isSwingInProgress;
         mc.field_1724.method_36456(rotationYaw);
         mc.field_1724.field_5982 = prevRotationYaw;
         mc.field_1724.field_6241 = rotationYawHead;
         mc.field_1724.field_6259 = prevRotationYawHead;
         mc.field_1724.field_3932 = cameraYaw;
         mc.field_1724.field_3931 = prevCameraYaw;
         mc.field_1724.field_5994 = walk;
         ((IClientPlayerEntity)mc.field_1724).doSendMovementPackets();
         runningPhysics = false;
      }
   }

   public static void use() {
      PacketManager.INSTANCE.sendPacket((class_7204)((id) -> new class_2886(class_1268.field_5808, id, RotationUtils.getActualYaw(), RotationUtils.getActualPitch())));
   }

   public static void switchAndUse(int slot) {
      int oldSlot = mc.field_1724.method_31548().field_7545;
      InventoryUtils.switchToSlot(slot);
      use();
      InventoryUtils.switchToSlot(oldSlot);
   }

   public static void doFirework() {
      int fireworkHotbar = InventoryUtils.getHotbarItemSlot(class_1802.field_8639);
      if (fireworkHotbar != -1) {
         switchAndUse(fireworkHotbar);
      } else {
         int fireworkInv = InventoryUtils.getInventoryItemSlot(class_1802.field_8639);
         if (fireworkInv == -1) {
            ChatUtils.sendMessage(new ChatMessage("No fireworks in inv", false, 0));
            return;
         }

         InventoryUtils.swap(fireworkInv, mc.field_1724.method_31548().field_7545);
         use();
         InventoryUtils.swap(fireworkInv, mc.field_1724.method_31548().field_7545);
      }

   }

   public static class_2338 getAnvilPos(class_1657 player) {
      double decimalPoint = mc.field_1724.method_23318() - Math.floor(mc.field_1724.method_23318());
      return new class_2338(player.method_31477(), (int)(decimalPoint > 0.8 ? Math.floor(mc.field_1724.method_23318()) + (double)1.0F : Math.floor(mc.field_1724.method_23318())), player.method_31479());
   }

   public static void equipElytra() {
      if (!isElytraEquipped()) {
         int slot = InventoryUtils.getInventoryItemSlot(class_1802.field_8833);
         if (slot != -1) {
            InventoryUtils.swapArmor(2, slot);
            PacketManager.INSTANCE.sendPacket(new class_2848(mc.field_1724, class_2849.field_12982));
            mc.field_1724.method_23669();
         }
      }

   }

   public static void disEquipElytra() {
      if (isElytraEquipped()) {
         int slot = InventoryUtils.findChestplate();
         if (slot != -1) {
            InventoryUtils.swapArmor(2, slot);
         }
      }

   }

   public static class_2338 getHighestPlaceableAnvilPos(class_1657 player) {
      class_2338 pos = null;

      for(int i = (int)player.method_23318(); i < 255; ++i) {
         class_2338 currentPos = new class_2338(player.method_31477(), i, player.method_31479());
         if (currentPos != null && BlockUtils.canPlaceBlock(currentPos, false) && mc.field_1724.method_33571().method_1022(currentPos.method_46558()) < (double)5.0F) {
            pos = currentPos;
         }
      }

      return pos;
   }

   public static int getTabHealth(String player) {
      int score = 0;
      class_269 scoreboard = mc.field_1687.method_8428();
      if (scoreboard == null) {
         return 0;
      } else {
         class_266 obj = scoreboard.method_1189(class_8646.field_45156);
         class_9013 playerScore = scoreboard.method_55430(class_9015.method_55422(player), obj);
         if (playerScore != null) {
            score = playerScore.method_55397();
         }

         return score;
      }
   }
}
