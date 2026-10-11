package org.itsmahir.pagon.mixin;

import java.util.stream.Stream;
import net.minecraft.class_355;
import org.itsmahir.pagon.impl.features.modules.render.ExtraTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({class_355.class})
public abstract class MixinPlayerListHud {
   @Redirect(
      method = {"collectPlayerEntries"},
      at = @At(
   value = "INVOKE",
   target = "Ljava/util/stream/Stream;limit(J)Ljava/util/stream/Stream;"
)
   )
   public Stream subList(Stream instance, long l) {
      return instance.limit(ExtraTab.INSTANCE.isEnabled() ? 250L : l);
   }
}
