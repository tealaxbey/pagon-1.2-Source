package org.itsmahir.pagon.mixin.accessor;

import java.net.Proxy;
import net.minecraft.class_310;
import net.minecraft.class_320;
import net.minecraft.class_6360;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_310.class})
public interface IMinecraftClient {
   @Accessor("currentFps")
   static int getFps() {
      return 0;
   }

   @Mutable
   @Accessor("session")
   void setSession(class_320 var1);

   @Accessor("networkProxy")
   Proxy getProxy();

   @Accessor("itemUseCooldown")
   int getItemUseCooldown();

   @Accessor("itemUseCooldown")
   void setItemUseCooldown(int var1);

   @Invoker
   boolean callDoAttack();

   @Invoker
   void callDoItemUse();

   @Accessor("resourceReloadLogger")
   class_6360 getResourceReloadLogger();

   @Accessor("renderTickCounter")
   class_9779.class_9781 getRenderTickCounter();

   @Invoker("doAttack")
   boolean leftClick();
}
