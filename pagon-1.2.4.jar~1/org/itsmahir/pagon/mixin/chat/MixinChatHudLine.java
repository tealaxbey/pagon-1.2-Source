package org.itsmahir.pagon.mixin.chat;

import com.mojang.authlib.GameProfile;
import net.minecraft.class_2561;
import net.minecraft.class_303;
import net.minecraft.class_7469;
import net.minecraft.class_7591;
import org.itsmahir.pagon.api.utils.ducks.IChatHudLine;
import org.itsmahir.pagon.api.utils.render.animation.Easing;
import org.itsmahir.pagon.api.utils.render.animation.type.TimeAnimation;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.features.modules.render.BetterChat;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_303.class})
public abstract class MixinChatHudLine implements IChatHudLine {
   @Shadow
   @Final
   private class_2561 comp_893;
   @Unique
   private int id;
   @Unique
   private GameProfile sender;

   public String getMessageText() {
      return this.comp_893.getString();
   }

   public int getOverrideId() {
      return this.id;
   }

   public void setOverrideId(int id) {
      this.id = id;
   }

   @Inject(
      method = {"<init>"},
      at = {@At("RETURN")}
   )
   private void hookCtr(int creationTick, class_2561 text, class_7469 messageSignatureData, class_7591 messageIndicator, CallbackInfo info) {
      BetterChat.INSTANCE.percentComplete = 0.0F;
      BetterChat.INSTANCE.animationMap.put((class_303)class_303.class.cast(this), new TimeAnimation(false, (double)(-IMinecraft.mc.field_1772.method_1727(text.getString())), (double)0.0F, 150.0F, Easing.LINEAR));
   }
}
