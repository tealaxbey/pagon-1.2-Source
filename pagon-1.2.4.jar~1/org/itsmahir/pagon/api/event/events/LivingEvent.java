package org.itsmahir.pagon.api.event.events;

import net.minecraft.class_1297;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import org.itsmahir.pagon.api.event.Event;

public class LivingEvent {
   public static class Jump extends Event {
      float yaw;

      public Jump(float yaw) {
         this.yaw = yaw;
      }

      public float getYaw() {
         return this.yaw;
      }

      public void setYaw(float yaw) {
         this.yaw = yaw;
      }
   }

   public static class SetSprinting extends Event {
   }

   public static class Death extends Event {
      class_1297 entity;

      public Death(class_1297 entity) {
         this.entity = entity;
      }

      public class_1297 getEntity() {
         return this.entity;
      }
   }

   public static class AttackBlock extends Event {
      private final class_2338 pos;
      private final class_2680 state;
      private final class_2350 direction;

      public AttackBlock(class_2338 pos, class_2680 state, class_2350 direction) {
         this.pos = pos;
         this.state = state;
         this.direction = direction;
      }

      public class_2338 getPos() {
         return this.pos;
      }

      public class_2680 getState() {
         return this.state;
      }

      public class_2350 getDirection() {
         return this.direction;
      }
   }

   public static class BreakBlock extends Event {
      private final class_2338 pos;

      public BreakBlock(class_2338 pos) {
         this.pos = pos;
      }

      public class_2338 getPos() {
         return this.pos;
      }
   }

   public static class Eat extends Event {
      private final class_1799 stack;

      public Eat(class_1799 stack) {
         this.stack = stack;
      }

      public class_1792 getItem() {
         return this.stack.method_7909();
      }

      public class_1799 getStack() {
         return this.stack;
      }
   }

   public static class Attack extends Event {
      private class_1297 entity;

      public Attack(class_1297 entity) {
         this.entity = entity;
      }

      public class_1297 getEntity() {
         return this.entity;
      }
   }

   public static class BlockSlowdown extends Event {
      private final class_2680 state;

      public BlockSlowdown(class_2680 state) {
         this.state = state;
      }

      public class_2680 getState() {
         return this.state;
      }
   }
}
