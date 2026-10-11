package org.itsmahir.pagon.mixin.accessor;

import net.minecraft.class_8080;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_8080.class})
public interface ILimbAnimator {
   @Accessor("pos")
   void setLimbPos(float var1);
}
