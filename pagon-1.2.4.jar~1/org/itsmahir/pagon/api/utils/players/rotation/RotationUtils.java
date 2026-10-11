package org.itsmahir.pagon.api.utils.players.rotation;

import net.minecraft.class_1297;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2828;
import net.minecraft.class_3532;
import net.minecraft.class_638;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.management.RotationManager;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.wrapper.IMinecraft;

public class RotationUtils implements IMinecraft {
   public static float[] getRotationsTo(class_243 src, class_243 dest) {
      float yaw = (float)(Math.toDegrees(Math.atan2(dest.method_1020(src).field_1350, dest.method_1020(src).field_1352)) - (double)90.0F);
      float pitch = (float)Math.toDegrees(-Math.atan2(dest.method_1020(src).field_1351, Math.hypot(dest.method_1020(src).field_1352, dest.method_1020(src).field_1350)));
      return new float[]{class_3532.method_15393(yaw), class_3532.method_15393(pitch)};
   }

   public static double lookDistanceTo(float yaw, float pitch) {
      double yawChange = (double)Math.abs(yaw - mc.field_1724.method_36454());
      double pitchChange = (double)Math.abs(pitch - mc.field_1724.method_36455());
      return Math.sqrt(yawChange * yawChange + pitchChange * pitchChange);
   }

   public static void setRotation(float[] rotation) {
      RotationManager.INSTANCE.rotateTo(new Rotation(100, rotation[0], rotation[1]));
   }

   public static void setRotation(Rotation rotation) {
      RotationManager.INSTANCE.rotateTo(rotation);
   }

   public static void setRotation(float[] rotation, int priority) {
      RotationManager.INSTANCE.rotateTo(new Rotation(priority, rotation[0], rotation[1]));
   }

   public static void setRotation(float yaw, float pitch, int priority) {
      RotationManager.INSTANCE.rotateTo(new Rotation(priority, yaw, pitch));
   }

   public static void setRotation(float yaw, float pitch) {
      RotationManager.INSTANCE.rotateTo(new Rotation(100, yaw, pitch));
   }

   public static float[] getBlockRotations(class_2338 pos, class_2350 facing) {
      return facing == null ? null : getBlockRotations(pos, facing, mc.field_1724);
   }

   public static float fixYaw(float yaw) {
      float prevYaw = RotationManager.INSTANCE.getServerYaw();
      float diff = yaw - prevYaw;
      if (diff < -180.0F || diff > 180.0F) {
         float round = (float)Math.round(Math.abs(diff / 360.0F));
         if (diff < 0.0F) {
            float var10000 = diff + 360.0F * round;
         } else {
            float var4 = diff - 360.0F * round;
         }
      }

      return yaw;
   }

   public static void doSilentRotate(Rotation rotation) {
      PacketManager.INSTANCE.sendQuietPacket(new class_2828.class_2830(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321(), rotation.getYaw(), rotation.getPitch(), mc.field_1724.method_24828()));
   }

   public static void doSilentRotate(float[] rotation) {
      PacketManager.INSTANCE.sendQuietPacket(new class_2828.class_2830(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321(), rotation[0], rotation[1], mc.field_1724.method_24828()));
   }

   public static void silentSync() {
      PacketManager.INSTANCE.sendQuietPacket(new class_2828.class_2830(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321(), getActualYaw(), getActualPitch(), mc.field_1724.method_24828()));
   }

   public static void doRotate(class_2338 pos, boolean strictDirection) {
      float[] rots = getBlockRotations(pos, BlockUtils.getPlaceableSide(pos, strictDirection));
      if (rots != null) {
         setRotation(rots);
      }

   }

   public static void doSilentRotate(class_2338 pos, boolean strictDirection) {
      float[] rots = getBlockRotations(pos, BlockUtils.getPlaceableSide(pos, strictDirection));
      if (rots != null) {
         doSilentRotate(rots);
      }

   }

   public static void doSilentRotate(class_2338 pos, class_2350 direction) {
      float[] rots = getBlockRotations(pos, direction);
      if (rots != null) {
         doSilentRotate(rots);
      }

   }

   public static void doRotate(class_2338 pos, class_2350 direction) {
      float[] rots = getBlockRotations(pos, direction);
      if (rots != null) {
         setRotation(rots);
      }

   }

   public static void doRotate(class_2338 pos) {
      float[] rots = getRotationsTo(mc.field_1724.method_33571(), pos.method_46558());
      if (rots != null) {
         setRotation(rots);
      }

   }

   public static float[] getBlockRotations(class_2338 pos, class_2350 facing, class_1297 from) {
      class_243 hitVec = pos.method_46558().method_1019((new class_243(facing.method_23955())).method_1021((double)0.5F));
      return getRotationsTo(from.method_33571(), hitVec);
   }

   public static float[] getRotations(double x, double y, double z, class_1297 f) {
      class_243 eyePos = f.method_33571();
      return getBlockRotations(x, y, z, (double)((float)eyePos.field_1352), (double)((float)eyePos.field_1351), (double)((float)eyePos.field_1350));
   }

   public static float[] getRotations(class_2338 pos, class_2350 facing, class_1297 from, class_638 world, class_2680 state) {
      class_265 shape = state.method_26220(world, pos);
      class_238 bb = !shape.method_1110() ? shape.method_1107() : BlockUtils.EMPTY_BOX;
      double x = (double)pos.method_10263() + (bb.field_1323 + bb.field_1320) / (double)2.0F;
      double y = (double)pos.method_10264() + (bb.field_1322 + bb.field_1325) / (double)2.0F;
      double z = (double)pos.method_10260() + (bb.field_1321 + bb.field_1324) / (double)2.0F;
      if (facing != null) {
         x += (double)facing.method_10148() * ((bb.field_1323 + bb.field_1320) / (double)2.0F);
         y += (double)facing.method_10164() * ((bb.field_1322 + bb.field_1325) / (double)2.0F);
         z += (double)facing.method_10165() * ((bb.field_1321 + bb.field_1324) / (double)2.0F);
      }

      return getRotations(x, y, z, from);
   }

   public static float[] getBlockRotations(double x, double y, double z, double fromX, double fromY, double fromZ) {
      double xDiff = x - fromX;
      double yDiff = y - fromY;
      double zDiff = z - fromZ;
      double dist = (double)class_3532.method_15355((float)(xDiff * xDiff + zDiff * zDiff));
      float yaw = (float)(Math.atan2(zDiff, xDiff) * (double)180.0F / Math.PI) - 90.0F;
      float pitch = (float)(-(Math.atan2(yDiff, dist) * (double)180.0F / Math.PI));
      return new float[]{fixYaw(yaw), pitch};
   }

   public static class_243 getRotationVector(float yaw, float pitch) {
      float vx = -class_3532.method_15374(MathUtil.rad(yaw)) * class_3532.method_15362(MathUtil.rad(pitch));
      float vz = class_3532.method_15362(MathUtil.rad(yaw)) * class_3532.method_15362(MathUtil.rad(pitch));
      float vy = -class_3532.method_15374(MathUtil.rad(pitch));
      return new class_243((double)vx, (double)vy, (double)vz);
   }

   public static float getActualYaw() {
      return RotationManager.INSTANCE.getRotation() != null ? RotationManager.INSTANCE.getRotation().getYaw() : mc.field_1724.method_36454();
   }

   public static float getActualPitch() {
      return RotationManager.INSTANCE.getRotation() != null ? RotationManager.INSTANCE.getRotation().getPitch() : mc.field_1724.method_36455();
   }

   public static void packetRotate(float[] rots) {
      PacketManager.INSTANCE.sendPacket(new class_2828.class_2831(rots[0], rots[1], mc.field_1724.method_24828()));
   }

   public static void packetRotate(float yaw, float pitch) {
      PacketManager.INSTANCE.sendPacket(new class_2828.class_2831(yaw, pitch, mc.field_1724.method_24828()));
   }
}
