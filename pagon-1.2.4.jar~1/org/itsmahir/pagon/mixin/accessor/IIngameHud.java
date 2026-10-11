package org.itsmahir.pagon.mixin.accessor;

import net.minecraft.class_1657;
import net.minecraft.class_329;
import net.minecraft.class_332;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_329.class})
public interface IIngameHud {
   @Invoker("renderHealthBar")
   void doRenderHealth(class_332 var1, class_1657 var2, int var3, int var4, int var5, int var6, float var7, int var8, int var9, int var10, boolean var11);

   @Accessor("heartJumpEndTick")
   long getHeartJumpEndTick();

   @Accessor("ticks")
   int getTicks();

   @Accessor("renderHealthValue")
   int getRenderHealthValue();
}
