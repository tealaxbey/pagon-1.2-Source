package org.itsmahir.pagon.mixin;

import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3726;
import net.minecraft.class_5329;
import org.itsmahir.pagon.api.event.events.world.CollisionBoxEvent;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({class_5329.class})
public class MixinBlockCollisionSpliterator implements IMinecraft {
   @Redirect(
      method = {"computeNext"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/block/BlockState;getCollisionShape(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/ShapeContext;)Lnet/minecraft/util/shape/VoxelShape;"
)
   )
   private class_265 hookGetCollisionShape(class_2680 instance, class_1922 blockView, class_2338 blockPos, class_3726 shapeContext) {
      class_265 voxelShape = instance.method_26194(blockView, blockPos, shapeContext);
      if (blockView != mc.field_1687) {
         return voxelShape;
      } else {
         CollisionBoxEvent blockCollisionEvent = new CollisionBoxEvent(voxelShape, blockPos, instance);
         blockCollisionEvent.post();
         return blockCollisionEvent.isCancelled() ? blockCollisionEvent.getVoxelShape() : voxelShape;
      }
   }
}
