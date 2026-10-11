package org.itsmahir.pagon.mixin.accessor;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import java.util.Set;
import net.minecraft.class_2586;
import net.minecraft.class_3191;
import net.minecraft.class_4599;
import net.minecraft.class_4604;
import net.minecraft.class_761;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_761.class})
public interface IWorldRenderer {
   @Accessor("frustum")
   class_4604 getFrustum();

   @Accessor("blockBreakingInfos")
   Int2ObjectMap<class_3191> getBlockBreakingProgressions();

   @Accessor("bufferBuilders")
   class_4599 hookGetBufferBuilders();

   @Accessor("noCullingBlockEntities")
   Set<class_2586> hookGetNoCullingBlockEntities();
}
