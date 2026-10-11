package org.itsmahir.pagon.mixin.accessor;

import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_3965;
import net.minecraft.class_636;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_636.class})
public interface IClientPlayerInteractionManager {
   @Invoker("syncSelectedSlot")
   void doSyncSelectedSlot();

   @Accessor("currentBreakingProgress")
   float getCurrentBreakProgress();

   @Accessor("currentBreakingProgress")
   void setCurrentBreakingProgress(float var1);

   @Invoker("interactBlockInternal")
   class_1269 invokeInteractInternal(class_746 var1, class_1268 var2, class_3965 var3);
}
