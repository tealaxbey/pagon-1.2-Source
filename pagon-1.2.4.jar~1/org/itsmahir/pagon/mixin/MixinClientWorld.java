package org.itsmahir.pagon.mixin;

import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_638;
import org.itsmahir.pagon.api.event.events.world.EntityEvent;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.impl.features.modules.render.SkyBox;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_638.class})
public abstract class MixinClientWorld {
   @Shadow
   public abstract @Nullable class_1297 method_8469(int var1);

   @Inject(
      method = {"addEntity"},
      at = {@At("RETURN")}
   )
   private void hookAddEntity(class_1297 entity, CallbackInfo ci) {
      (new EntityEvent.Add(entity)).post();
   }

   @Inject(
      method = {"removeEntity"},
      at = {@At("HEAD")}
   )
   private void hookRemoveEntity(int entityId, class_1297.class_5529 removalReason, CallbackInfo ci) {
      class_1297 entity = this.method_8469(entityId);
      if (entity != null) {
         (new EntityEvent.Remove(entity, removalReason)).post();
      }
   }

   @Inject(
      method = {"getSkyColor"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void getSkyColorHook(class_243 cameraPos, float tickDelta, CallbackInfoReturnable<class_243> cir) {
      if (SkyBox.INSTANCE.isEnabled()) {
         cir.setReturnValue(new class_243((double)((PagonColor)SkyBox.INSTANCE.fogColor.getValue()).getGlRed(), (double)((PagonColor)SkyBox.INSTANCE.fogColor.getValue()).getGlGreen(), (double)((PagonColor)SkyBox.INSTANCE.fogColor.getValue()).getGlBlue()));
      }

   }
}
