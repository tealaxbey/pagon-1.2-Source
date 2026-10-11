package org.itsmahir.pagon.mixin.accessor;

import net.minecraft.class_2828;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_2828.class})
public interface IPlayerMoveC2SPacket {
   @Accessor("x")
   @Mutable
   void setX(double var1);

   @Accessor("x")
   double getX();

   @Accessor("y")
   @Mutable
   void setY(double var1);

   @Accessor("y")
   double getY();

   @Accessor("z")
   @Mutable
   void setZ(double var1);

   @Accessor("z")
   double getZ();

   @Mutable
   @Accessor("onGround")
   void setOnGround(boolean var1);

   @Mutable
   @Accessor("yaw")
   void setYaw(float var1);

   @Mutable
   @Accessor("yaw")
   float getYaw();

   @Mutable
   @Accessor("pitch")
   void setPitch(float var1);

   @Mutable
   @Accessor("pitch")
   float getPitch();
}
