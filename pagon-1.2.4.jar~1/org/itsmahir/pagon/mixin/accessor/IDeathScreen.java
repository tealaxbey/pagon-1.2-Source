package org.itsmahir.pagon.mixin.accessor;

import net.minecraft.class_2561;
import net.minecraft.class_418;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_418.class})
public interface IDeathScreen {
   @Accessor("message")
   class_2561 getDeathMessage();

   @Accessor("isHardcore")
   boolean isScreenHardcore();
}
