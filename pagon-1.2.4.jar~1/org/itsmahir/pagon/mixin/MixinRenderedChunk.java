package org.itsmahir.pagon.mixin;

import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import org.itsmahir.pagon.api.event.events.render.RenderBlockEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(
   targets = {"net.minecraft.client.render.chunk.RenderedChunk"}
)
public class MixinRenderedChunk {
   @Inject(
      method = {"getBlockState"},
      at = {@At("RETURN")},
      cancellable = true
   )
   public void hookEventRenderBlockModel(class_2338 pos, CallbackInfoReturnable<class_2680> cir) {
      class_2680 state = (class_2680)cir.getReturnValue();
      RenderBlockEvent event = new RenderBlockEvent(pos, state);
      event.post();
      if (event.isCancelled()) {
         cir.setReturnValue(class_2246.field_10124.method_9564());
      }

   }
}
