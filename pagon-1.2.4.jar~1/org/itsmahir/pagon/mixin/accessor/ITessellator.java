package org.itsmahir.pagon.mixin.accessor;

import net.minecraft.class_289;
import net.minecraft.class_9799;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_289.class})
public interface ITessellator {
   @Accessor("allocator")
   class_9799 getAllocator();
}
