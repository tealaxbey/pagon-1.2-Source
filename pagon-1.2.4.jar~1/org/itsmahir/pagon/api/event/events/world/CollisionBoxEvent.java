package org.itsmahir.pagon.api.event.events.world;

import net.minecraft.class_2338;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import org.itsmahir.pagon.api.event.Event;

public class CollisionBoxEvent extends Event {
   private final class_2338 pos;
   private final class_2680 state;
   private class_265 voxelShape;

   public CollisionBoxEvent(class_265 voxelShape, class_2338 pos, class_2680 state) {
      this.pos = pos;
      this.state = state;
      this.voxelShape = voxelShape;
   }

   public class_2338 getPos() {
      return this.pos;
   }

   public class_2680 getState() {
      return this.state;
   }

   public class_265 getVoxelShape() {
      return this.voxelShape;
   }

   public void setVoxelShape(class_265 voxelShape) {
      this.voxelShape = voxelShape;
   }
}
