package org.itsmahir.pagon.mixin.sodium;

import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import org.itsmahir.pagon.api.event.events.render.RenderBlockEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(
   targets = {"net.caffeinemc.mods.sodium.client.world.LevelSlice"}
)
public class MixinLevelSlice {
   @Inject(
      method = {"getBlockState(III)Lnet/minecraft/block/BlockState;"},
      at = {@At("RETURN")},
      cancellable = true
   )
   public void renderBlockModel(int x, int y, int z, CallbackInfoReturnable<class_2680> cir) {
      class_2680 state = (class_2680)cir.getReturnValue();
      RenderBlockEvent event = new RenderBlockEvent(new class_2338(x, y, z), state);
      event.post();
      if (event.isCancelled()) {
         cir.setReturnValue(class_2246.field_10124.method_9564());
      }

   }
}
