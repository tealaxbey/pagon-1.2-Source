package org.itsmahir.pagon.api.event.events.move;

import net.minecraft.class_243;
import org.itsmahir.pagon.api.event.Event;

public class LookEvent {
   public static class LookVelocityEvent extends Event {
      private final class_243 movementInput;
      private final float speed;
      private final float yaw;
      private class_243 velocity;

      public LookVelocityEvent(class_243 movementInput, float speed, float yaw, class_243 velocity) {
         this.movementInput = movementInput;
         this.speed = speed;
         this.yaw = yaw;
         this.velocity = velocity;
      }

      public class_243 getMovementInput() {
         return this.movementInput;
      }

      public float getSpeed() {
         return this.speed;
      }

      public float getYaw() {
         return this.yaw;
      }

      public class_243 getVelocity() {
         return this.velocity;
      }

      public void setVelocity(class_243 velocity) {
         this.velocity = velocity;
      }
   }

   public static class RotationSetEvent extends Event {
      float yaw;
      float pitch;

      public RotationSetEvent(float yaw, float pitch) {
         this.yaw = yaw;
         this.pitch = pitch;
      }

      public float getYaw() {
         return this.yaw;
      }

      public float getPitch() {
         return this.pitch;
      }

      public void setYaw(float yaw) {
         this.yaw = yaw;
      }

      public void setPitch(float pitch) {
         this.pitch = pitch;
      }
   }
}
