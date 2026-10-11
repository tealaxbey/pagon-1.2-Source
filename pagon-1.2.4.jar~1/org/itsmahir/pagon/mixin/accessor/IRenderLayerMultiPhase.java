package org.itsmahir.pagon.mixin.accessor;

import net.minecraft.class_1921;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_1921.class_4687.class})
public interface IRenderLayerMultiPhase {
   @Invoker("getPhases")
   class_1921.class_4688 invokeGetPhases();
}
