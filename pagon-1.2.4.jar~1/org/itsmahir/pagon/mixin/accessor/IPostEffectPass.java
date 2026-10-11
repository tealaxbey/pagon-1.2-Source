package org.itsmahir.pagon.mixin.accessor;

import net.minecraft.class_276;
import net.minecraft.class_283;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_283.class})
public interface IPostEffectPass {
   @Mutable
   @Accessor("input")
   void hookSetInput(class_276 var1);

   @Mutable
   @Accessor("output")
   void hookSetOutput(class_276 var1);
}
