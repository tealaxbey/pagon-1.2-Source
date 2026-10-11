package org.itsmahir.pagon.mixin;

import it.unimi.dsi.fastutil.floats.FloatUnaryOperator;
import net.minecraft.class_9779;
import org.itsmahir.pagon.api.utils.render.RenderTimer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_9779.class_9781.class})
public class MixinRenderTimer {
   @Shadow
   private float field_51958;
   @Shadow
   private float field_51959;
   @Shadow
   private long field_51962;
   @Shadow
   private float field_51964;
   @Shadow
   private FloatUnaryOperator field_51965;

   @Inject(
      method = {"beginRenderTick(J)I"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void beginRenderTick(long timeMillis, CallbackInfoReturnable<Integer> ci) {
      this.field_51958 = (float)(timeMillis - this.field_51962) / this.field_51965.apply(this.field_51964) * RenderTimer.getTickLength();
      this.field_51962 = timeMillis;
      this.field_51959 += this.field_51958;
      int i = (int)this.field_51959;
      this.field_51959 -= (float)i;
      ci.setReturnValue(i);
   }
}
