package org.itsmahir.pagon.mixin.accessor;

import net.minecraft.class_419;
import net.minecraft.class_437;
import net.minecraft.class_9812;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_419.class})
public interface IDisconnectedScreen {
   @Accessor("parent")
   class_437 getParent();

   @Accessor("info")
   class_9812 getDisconnectionInfo();
}
