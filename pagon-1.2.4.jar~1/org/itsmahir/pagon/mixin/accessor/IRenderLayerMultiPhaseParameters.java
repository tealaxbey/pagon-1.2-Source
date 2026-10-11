package org.itsmahir.pagon.mixin.accessor;

import net.minecraft.class_1921;
import net.minecraft.class_4668;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_1921.class_4688.class})
public interface IRenderLayerMultiPhaseParameters {
   @Accessor("outlineMode")
   class_1921.class_4750 getOutlineMode();

   @Accessor("texture")
   class_4668.class_5939 getTexture();
}
