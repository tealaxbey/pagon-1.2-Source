package org.itsmahir.pagon.api.utils.players.rotation;

import java.util.Arrays;
import net.minecraft.class_3532;
import org.itsmahir.pagon.api.management.RotationManager;
import org.itsmahir.pagon.api.utils.Pair;
import org.itsmahir.pagon.api.wrapper.IMinecraft;

public class Rotation implements IMinecraft {
   private final int priority;
   private float yaw;
   private float pitch;
   private boolean snap;

   public Rotation(int priority, float yaw, float pitch, boolean snap) {
      this.priority = priority;
      this.yaw = yaw;
      this.pitch = pitch;
      this.snap = snap;
   }

   public Rotation(int priority, float yaw, float pitch) {
      this.priority = priority;
      this.yaw = yaw;
      this.pitch = pitch;
   }

   public Rotation(float yaw, float pitch) {
      this.priority = 100;
      this.yaw = yaw;
      this.pitch = pitch;
   }

   public Rotation correctSensitivity(Rotation prevRotation) {
      Rotation deltaRotation = this.closestDelta(prevRotation);
      Pair<Double, Double>[] cursorDeltas = approximateCursorDeltas(deltaRotation);
      Rotation[] newRotations = (Rotation[])Arrays.stream(cursorDeltas).map((cursorDelta) -> this.calculateNewRotation(prevRotation, cursorDelta)).toArray((x$0) -> new Rotation[x$0]);
      return (Rotation)Arrays.stream(newRotations).min((r1, r2) -> Float.compare(this.fov(r1), this.fov(r2))).orElse(newRotations[0]);
   }

   public Rotation calculateNewRotation(Rotation prevRotation, Pair<Double, Double> cursorDeltas) {
      double gcd = getGcd();
      Rotation rotationChange = new Rotation((float)((Double)cursorDeltas.key() * gcd) * 0.15F, (float)((Double)cursorDeltas.value() * gcd) * 0.15F);
      Rotation newRotation = new Rotation(prevRotation.yaw + rotationChange.yaw, prevRotation.pitch + rotationChange.pitch);
      newRotation = new Rotation(this.priority, newRotation.yaw, Math.max(-90.0F, Math.min(90.0F, newRotation.pitch)));
      return newRotation;
   }

   private float dist(Rotation deltaRotation) {
      return (float)Math.sqrt((double)(deltaRotation.yaw * deltaRotation.yaw + deltaRotation.pitch * deltaRotation.pitch));
   }

   public float fov(Rotation other) {
      return this.dist(this.closestDelta(other));
   }

   private static double getGcd() {
      double sensitivity = (Double)mc.field_1690.method_42495().method_41753() * (double)0.6F + (double)0.2F;
      double sensitivityPow3 = sensitivity * sensitivity * sensitivity;
      double sensitivityPow3Mult8 = sensitivityPow3 * (double)8.0F;
      return mc.field_1690.method_31044().method_31034() && mc.field_1724 != null && mc.field_1724.method_31550() ? sensitivityPow3 : sensitivityPow3Mult8;
   }

   public static Pair<Double, Double>[] approximateCursorDeltas(Rotation deltaRotation) {
      double gcd = getGcd() * (double)0.15F;
      double targetX = (double)(-deltaRotation.yaw) / gcd;
      double targetY = (double)(-deltaRotation.pitch) / gcd;
      return new Pair[]{new Pair(Math.floor(targetX), Math.floor(targetY)), new Pair(Math.ceil(targetX), Math.floor(targetY)), new Pair(Math.ceil(targetX), Math.ceil(targetY)), new Pair(Math.floor(targetX), Math.ceil(targetY))};
   }

   public Rotation closestDelta(Rotation other) {
      return new Rotation(class_3532.method_15393(other.yaw - this.yaw), other.pitch - this.pitch);
   }

   public Rotation fix() {
      Rotation rotation = RotationManager.INSTANCE.getRotation();
      if (rotation == null) {
         rotation = new Rotation(this.priority, RotationManager.INSTANCE.getRotationYaw(), RotationManager.INSTANCE.getRotationPitch());
      }

      return this.correctSensitivity(rotation);
   }

   public int getPriority() {
      return this.priority;
   }

   public float getYaw() {
      return this.yaw;
   }

   public float getPitch() {
      return this.pitch;
   }

   public boolean isSnap() {
      return this.snap;
   }

   public void setYaw(float yaw) {
      this.yaw = yaw;
   }

   public void setPitch(float pitch) {
      this.pitch = pitch;
   }

   public void setSnap(boolean snap) {
      this.snap = snap;
   }
}
