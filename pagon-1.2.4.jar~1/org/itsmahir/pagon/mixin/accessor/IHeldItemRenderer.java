package org.itsmahir.pagon.mixin.accessor;

import net.minecraft.class_759;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_759.class})
public interface IHeldItemRenderer {
   @Accessor("equipProgressMainHand")
   void setEquippedProgressMainHand(float var1);

   @Accessor("equipProgressMainHand")
   float getEquippedProgressMainHand();

   @Accessor("equipProgressOffHand")
   void setEquippedProgressOffHand(float var1);

   @Accessor("equipProgressOffHand")
   float getEquippedProgressOffHand();
}
