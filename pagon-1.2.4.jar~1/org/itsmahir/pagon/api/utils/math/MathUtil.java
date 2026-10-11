package org.itsmahir.pagon.api.utils.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.class_1297;
import net.minecraft.class_1667;
import net.minecraft.class_1684;
import net.minecraft.class_1753;
import net.minecraft.class_1764;
import net.minecraft.class_1771;
import net.minecraft.class_1776;
import net.minecraft.class_1779;
import net.minecraft.class_1792;
import net.minecraft.class_1803;
import net.minecraft.class_1823;
import net.minecraft.class_1828;
import net.minecraft.class_1835;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.wrapper.IMinecraft;

public class MathUtil implements IMinecraft {
   public static float rad(float angle) {
      return (float)((double)angle * Math.PI / (double)180.0F);
   }

   public static double normalize(double value, double min, double max) {
      return (value - min) / (max - min);
   }

   public static double roundToPlaces(double number, int places) {
      BigDecimal decimal = new BigDecimal(number);
      decimal = decimal.setScale(places, RoundingMode.HALF_UP);
      return decimal.doubleValue();
   }

   public String getRomanNumber(int number) {
      return String.join("", Collections.nCopies(number, "I")).replace("IIIII", "V").replace("IIII", "IV").replace("VV", "X").replace("VIV", "IX").replace("XXXXX", "L").replace("XXXX", "XL").replace("LL", "C").replace("LXL", "XC").replace("CCCCC", "D").replace("CCCC", "CD").replace("DD", "M").replace("DCD", "CM");
   }

   public static int randomInt(int min, int max) {
      Random rand = new Random();
      return rand.nextInt(max - min + 1) + min;
   }

   public static double random(double min, double max) {
      return Math.random() * (max - min) + min;
   }

   public static float random(float min, float max) {
      return (float)(Math.random() * (double)(max - min) + (double)min);
   }

   public static String getOrdinal(int i) {
      String[] suffixes = new String[]{"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
      switch (i % 100) {
         case 11:
         case 12:
         case 13:
            return "th";
         default:
            return suffixes[i % 10];
      }
   }

   public static class_243 roundVector(class_243 vec3d, int places) {
      return new class_243(roundToPlaces(vec3d.field_1352, places), roundToPlaces(vec3d.field_1351, places), roundToPlaces(vec3d.field_1350, places));
   }

   public static int clamp(int num, int min, int max) {
      return num < min ? min : Math.min(num, max);
   }

   public static float clamp(float num, float min, float max) {
      return num < min ? min : Math.min(num, max);
   }

   public static double clamp(double num, double min, double max) {
      return num < min ? min : Math.min(num, max);
   }

   public static double round(double value, int places) {
      return places < 0 ? value : (new BigDecimal(value)).setScale(places, RoundingMode.HALF_UP).doubleValue();
   }

   public static float round(float value, int places) {
      return places < 0 ? value : (new BigDecimal((double)value)).setScale(places, RoundingMode.HALF_UP).floatValue();
   }

   public static float round(float value, int places, float min, float max) {
      return class_3532.method_15363(places < 0 ? value : (new BigDecimal((double)value)).setScale(places, RoundingMode.HALF_UP).floatValue(), min, max);
   }

   public static long clamp(long num, long min, long max) {
      return num < min ? min : Math.min(num, max);
   }

   public static double square(double input) {
      return input * input;
   }

   public static float square(float input) {
      return input * input;
   }

   public static double distance(float x, float y, float x1, float y1) {
      return Math.sqrt((double)((x - x1) * (x - x1) + (y - y1) * (y - y1)));
   }

   public static double distance(float x, float y, boolean root) {
      double change = (double)Math.abs(x - y);
      return root ? Math.sqrt(square(change)) : change;
   }

   public static double getRandomDoubleInRange(Random p_82716_0_, double p_82716_1_, double p_82716_3_) {
      return p_82716_1_ >= p_82716_3_ ? p_82716_1_ : p_82716_0_.nextDouble() * (p_82716_3_ - p_82716_1_) + p_82716_1_;
   }

   public static float wrap(float valI) {
      float val = valI % 360.0F;
      if (val >= 180.0F) {
         val -= 360.0F;
      }

      if (val < -180.0F) {
         val += 360.0F;
      }

      return val;
   }

   public static class_243 rotatePitch(class_243 vec3d, float pitch) {
      float f = class_3532.method_15362(pitch);
      float f1 = class_3532.method_15374(pitch);
      double d0 = vec3d.field_1352;
      double d1 = vec3d.field_1351 * (double)f + vec3d.field_1350 * (double)f1;
      double d2 = vec3d.field_1350 * (double)f - vec3d.field_1351 * (double)f1;
      return new class_243(d0, d1, d2);
   }

   public static class_243 rotateYaw(class_243 vec3d, float yaw) {
      float f = class_3532.method_15362(yaw);
      float f1 = class_3532.method_15374(yaw);
      double d0 = vec3d.field_1352 * (double)f + vec3d.field_1350 * (double)f1;
      double d1 = vec3d.field_1351;
      double d2 = vec3d.field_1350 * (double)f - vec3d.field_1352 * (double)f1;
      return new class_243(d0, d1, d2);
   }

   public static boolean isThrowable(class_1792 item) {
      return item instanceof class_1776 || item instanceof class_1835 || item instanceof class_1779 || item instanceof class_1823 || item instanceof class_1771 || item instanceof class_1828 || item instanceof class_1803;
   }

   private static float getDistance(class_1792 item) {
      return item instanceof class_1753 ? 1.0F : 0.4F;
   }

   private static float getThrowVelocity(class_1792 item) {
      if (!(item instanceof class_1828) && !(item instanceof class_1803)) {
         if (item instanceof class_1779) {
            return 0.59F;
         } else {
            return item instanceof class_1835 ? 2.0F : 1.5F;
         }
      } else {
         return 0.5F;
      }
   }

   private static int getThrowPitch(class_1792 item) {
      return !(item instanceof class_1828) && !(item instanceof class_1803) && !(item instanceof class_1779) ? 0 : 20;
   }

   public static Result calcTrajectory(class_1792 item, float yaw) {
      List<class_243> points = new ArrayList();
      double x = RenderUtil.interpolate(mc.field_1724.field_6014, mc.field_1724.method_23317(), (double)mc.method_60646().method_60637(false));
      double y = RenderUtil.interpolate(mc.field_1724.field_6036, mc.field_1724.method_23318(), (double)mc.method_60646().method_60637(false));
      double z = RenderUtil.interpolate(mc.field_1724.field_5969, mc.field_1724.method_23321(), (double)mc.method_60646().method_60637(false));
      y = y + (double)mc.field_1724.method_18381(mc.field_1724.method_18376()) - 0.1000000014901161;
      if (item == mc.field_1724.method_6047().method_7909()) {
         x -= (double)(class_3532.method_15362(yaw / 180.0F * (float)Math.PI) * 0.16F);
         z -= (double)(class_3532.method_15374(yaw / 180.0F * (float)Math.PI) * 0.16F);
      } else {
         x += (double)(class_3532.method_15362(yaw / 180.0F * (float)Math.PI) * 0.16F);
         z += (double)(class_3532.method_15374(yaw / 180.0F * (float)Math.PI) * 0.16F);
      }

      float maxDist = getDistance(item);
      double motionX = (double)(-class_3532.method_15374(yaw / 180.0F * (float)Math.PI) * class_3532.method_15362(mc.field_1724.method_36455() / 180.0F * (float)Math.PI) * maxDist);
      double motionY = (double)(-class_3532.method_15374((RotationUtils.getActualPitch() - (float)getThrowPitch(item)) / 180.0F * 3.141593F) * maxDist);
      double motionZ = (double)(class_3532.method_15362(yaw / 180.0F * (float)Math.PI) * class_3532.method_15362(mc.field_1724.method_36455() / 180.0F * (float)Math.PI) * maxDist);
      float power = (float)mc.field_1724.method_6048() / 20.0F;
      power = (power * power + power * 2.0F) / 3.0F;
      if (power > 1.0F || power == 0.0F) {
         power = 1.0F;
      }

      float distance = class_3532.method_15355((float)(motionX * motionX + motionY * motionY + motionZ * motionZ));
      motionX /= (double)distance;
      motionY /= (double)distance;
      motionZ /= (double)distance;
      float pow = (item instanceof class_1753 ? power * 2.0F : (item instanceof class_1764 ? 2.2F : 1.0F)) * getThrowVelocity(item);
      motionX *= (double)pow;
      motionY *= (double)pow;
      motionZ *= (double)pow;
      if (!mc.field_1724.method_24828()) {
         motionY += mc.field_1724.method_18798().method_10214();
      }

      points.add(new class_243(x, y, z));

      for(int i = 0; i < 300; ++i) {
         class_243 lastPos = new class_243(x, y, z);
         x += motionX;
         y += motionY;
         z += motionZ;
         if (mc.field_1687.method_8320(new class_2338((int)x, (int)y, (int)z)).method_26204() == class_2246.field_10382) {
            motionX *= 0.8;
            motionY *= 0.8;
            motionZ *= 0.8;
         } else {
            motionX *= 0.99;
            motionY *= 0.99;
            motionZ *= 0.99;
         }

         if (item instanceof class_1753) {
            motionY -= (double)0.05F;
         } else if (mc.field_1724.method_6047().method_7909() instanceof class_1764) {
            motionY -= (double)0.05F;
         } else {
            motionY -= (double)0.03F;
         }

         class_243 pos = new class_243(x, y, z);

         for(class_1297 ent : mc.field_1687.method_18112()) {
            if (!(ent instanceof class_1667) && !ent.equals(mc.field_1724) && ent.method_5829().method_994(new class_238(x - 0.3, y - 0.3, z - 0.3, x + 0.3, y + 0.3, z + 0.3))) {
               points.add(pos);
               return new Result(points, new class_3966(ent, pos));
            }
         }

         class_3965 bhr = mc.field_1687.method_17742(new class_3959(lastPos, pos, class_3960.field_17559, class_242.field_1348, mc.field_1724));
         if (bhr != null && bhr.method_17783() == class_240.field_1332) {
            points.add(bhr.method_17784());
            return new Result(points, bhr);
         }

         if (y <= (double)-65.0F) {
            break;
         }

         if (motionX != (double)0.0F || motionY != (double)0.0F || motionZ != (double)0.0F) {
            points.add(pos);
         }
      }

      return new Result(points, (class_239)null);
   }

   public static Result calcTrajectory(class_1684 entity) {
      List<class_243> points = new ArrayList();
      double x = RenderUtil.interpolate(entity.field_6014, entity.method_23317(), (double)mc.method_60646().method_60637(false));
      double y = RenderUtil.interpolate(entity.field_6036, entity.method_23318(), (double)mc.method_60646().method_60637(false));
      double z = RenderUtil.interpolate(entity.field_5969, entity.method_23321(), (double)mc.method_60646().method_60637(false));
      double motionX = entity.method_18798().field_1352;
      double motionY = entity.method_18798().field_1351;
      double motionZ = entity.method_18798().field_1350;
      points.add(new class_243(x, y, z));

      for(int i = 0; i < 300; ++i) {
         class_243 lastPos = new class_243(x, y, z);
         x += motionX;
         y += motionY;
         z += motionZ;
         if (mc.field_1687.method_8320(new class_2338((int)x, (int)y, (int)z)).method_26204() == class_2246.field_10382) {
            motionX *= 0.8;
            motionY *= 0.8;
            motionZ *= 0.8;
         } else {
            motionX *= 0.99;
            motionY *= 0.99;
            motionZ *= 0.99;
         }

         motionY -= (double)0.03F;
         class_243 pos = new class_243(x, y, z);

         for(class_1297 ent : mc.field_1687.method_18112()) {
            if (!(ent instanceof class_1667) && !ent.equals(entity) && ent.method_5829().method_994(new class_238(x - 0.3, y - 0.3, z - 0.3, x + 0.3, y + 0.3, z + 0.3))) {
               points.add(pos);
               return new Result(points, new class_3966(ent, pos));
            }
         }

         class_3965 bhr = mc.field_1687.method_17742(new class_3959(lastPos, pos, class_3960.field_17559, class_242.field_1348, entity));
         if (bhr != null && bhr.method_17783() == class_240.field_1332) {
            points.add(bhr.method_17784());
            return new Result(points, bhr);
         }

         if (y <= (double)-65.0F) {
            break;
         }

         if (motionX != (double)0.0F || motionY != (double)0.0F || motionZ != (double)0.0F) {
            points.add(pos);
         }
      }

      return new Result(points, (class_239)null);
   }

   public static double magnitudeOfAcceleration(double changeX, double changeY, double time) {
      double top = (double)class_3532.method_15355((float)(Math.pow(changeX, (double)2.0F) + Math.pow(changeY, (double)2.0F)));
      return top / time;
   }

   public static double fix(double angle) {
      return angle < (double)0.0F ? angle + (double)180.0F : angle;
   }

   public static class Result {
      private final List<class_243> points;
      private class_239 hitResult;

      private Result(List<class_243> points, class_239 result) {
         this.points = points;
         this.hitResult = result;
      }

      public List<class_243> getPoints() {
         return this.points;
      }

      public class_239 getHitResult() {
         return this.hitResult;
      }

      public void setHitResult(class_239 hitResult) {
         this.hitResult = hitResult;
      }
   }
}
