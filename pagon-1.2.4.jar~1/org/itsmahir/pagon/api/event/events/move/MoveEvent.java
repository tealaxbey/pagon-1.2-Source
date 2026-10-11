package org.itsmahir.pagon.api.event.events.move;

import net.minecraft.class_1313;
import net.minecraft.class_243;
import org.itsmahir.pagon.api.event.Event;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;

public class MoveEvent extends Event {
   private final class_1313 type;
   private double x;
   private double y;
   private double z;

   public MoveEvent(class_1313 type, class_243 movement) {
      this.type = type;
      this.x = movement.field_1352;
      this.y = movement.field_1351;
      this.z = movement.field_1350;
   }

   public class_243 getMovement() {
      return new class_243(this.x, this.y, this.z);
   }

   public void motionX(double x) {
      PlayerUtils.setMotionX(x);
      this.setX(x);
   }

   public void motionY(double y) {
      PlayerUtils.setMotionY(y);
      this.setY(y);
   }

   public void motionZ(double z) {
      PlayerUtils.setMotionZ(z);
      this.setZ(z);
   }

   public void setMovement(class_243 vec3d) {
      this.setX(vec3d.field_1352);
      this.setY(vec3d.field_1351);
      this.setZ(vec3d.field_1350);
   }

   public class_1313 getType() {
      return this.type;
   }

   public double getX() {
      return this.x;
   }

   public double getY() {
      return this.y;
   }

   public double getZ() {
      return this.z;
   }

   public void setX(double x) {
      this.x = x;
   }

   public void setY(double y) {
      this.y = y;
   }

   public void setZ(double z) {
      this.z = z;
   }
}
