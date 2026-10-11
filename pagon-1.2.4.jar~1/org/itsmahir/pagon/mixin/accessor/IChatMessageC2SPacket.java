package org.itsmahir.pagon.mixin.accessor;

import net.minecraft.class_2797;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_2797.class})
public interface IChatMessageC2SPacket {
   @Accessor("chatMessage")
   @Mutable
   void setMessage(String var1);
}
