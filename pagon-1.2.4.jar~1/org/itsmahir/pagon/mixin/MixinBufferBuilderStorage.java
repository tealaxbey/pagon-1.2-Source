package org.itsmahir.pagon.mixin;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.minecraft.class_1921;
import net.minecraft.class_4599;
import org.itsmahir.pagon.api.utils.render.world.layer.PagonLayers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_4599.class})
public abstract class MixinBufferBuilderStorage {
   @Inject(
      method = {"method_54639(Lit/unimi/dsi/fastutil/objects/Object2ObjectLinkedOpenHashMap;)V"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/BufferBuilderStorage;assignBufferBuilder(Lit/unimi/dsi/fastutil/objects/Object2ObjectLinkedOpenHashMap;Lnet/minecraft/client/render/RenderLayer;)V"
)}
   )
   private void hookInit(Object2ObjectLinkedOpenHashMap map, CallbackInfo ci) {
      method_23798(map, PagonLayers.ENCHANT);
   }

   @Shadow
   private static void method_23798(Object2ObjectLinkedOpenHashMap map, class_1921 renderLayer) {
   }
}
