package org.itsmahir.pagon.mixin.accessor;

import java.util.Optional;
import net.minecraft.class_2960;
import net.minecraft.class_4668;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_4668.class_5939.class})
public interface IRenderPhaseTextureBase {
   @Invoker("getId")
   Optional<class_2960> invokeGetId();
}
