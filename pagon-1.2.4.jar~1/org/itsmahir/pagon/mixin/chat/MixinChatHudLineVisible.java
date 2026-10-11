package org.itsmahir.pagon.mixin.chat;

import net.minecraft.class_303;
import net.minecraft.class_5481;
import org.itsmahir.pagon.api.utils.ducks.IChatHudLine;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin({class_303.class_7590.class})
public abstract class MixinChatHudLineVisible implements IChatHudLine {
   @Shadow
   @Final
   private class_5481 comp_896;
   @Unique
   private int id;

   public String getMessageText() {
      StringBuilder sb = new StringBuilder();
      this.comp_896.accept((index, style, codePoint) -> {
         sb.appendCodePoint(codePoint);
         return true;
      });
      return sb.toString();
   }

   public int getOverrideId() {
      return this.id;
   }

   public void setOverrideId(int id) {
      this.id = id;
   }
}
