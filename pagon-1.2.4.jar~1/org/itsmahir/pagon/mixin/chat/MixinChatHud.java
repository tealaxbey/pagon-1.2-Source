package org.itsmahir.pagon.mixin.chat;

import com.llamalad7.mixinextras.sugar.Local;
import java.awt.Color;
import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_2477;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_303;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_338;
import net.minecraft.class_5250;
import net.minecraft.class_5481;
import net.minecraft.class_7469;
import net.minecraft.class_7591;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;
import org.itsmahir.pagon.api.utils.ducks.IChatHud;
import org.itsmahir.pagon.api.utils.ducks.IChatHudLine;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.render.animation.type.TimeAnimation;
import org.itsmahir.pagon.api.utils.render.font.OrderedTextPart;
import org.itsmahir.pagon.impl.features.modules.client.gui.Hud;
import org.itsmahir.pagon.impl.features.modules.render.BetterChat;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_338.class})
public abstract class MixinChatHud implements IChatHud {
   @Shadow
   @Final
   private List<class_303.class_7590> field_2064;
   @Shadow
   @Final
   private List<class_303> field_2061;
   @Shadow
   @Final
   private class_310 field_2062;
   @Unique
   private int nextId;
   @Unique
   public long prevMillis = -1L;
   private class_303 current = null;
   @Unique
   float percent;

   @Inject(
      method = {"render"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/gui/hud/ChatHudLine$Visible;addedTime()I"
)}
   )
   private void hookTimeAdded(CallbackInfo ci, @Local(ordinal = 13) int chatLineIndex) {
      try {
         this.current = (class_303)this.field_2061.get(chatLineIndex);
      } catch (Exception var4) {
      }

   }

   public void updatePercentage(long diff) {
      if (BetterChat.INSTANCE.percentComplete < 1.0F) {
         BetterChat var10000 = BetterChat.INSTANCE;
         var10000.percentComplete += 0.004F * (float)diff;
      }

      BetterChat.INSTANCE.percentComplete = MathUtil.clamp(BetterChat.INSTANCE.percentComplete, 0.0F, 1.0F);
   }

   @Redirect(
      method = {"render"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/gui/hud/MessageIndicator;indicatorColor()I"
)
   )
   private int getIndicatorColor(class_7591 instance, @Local(index = 31) int x) {
      return instance.comp_899() == (new Color(48, 184, 203, 0)).getRGB() ? Hud.getTextColor(x).getRGB() : instance.comp_899();
   }

   @Redirect(
      method = {"render"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/OrderedText;III)I"
)
   )
   private int drawTextWithShadowHook(class_332 instance, class_327 textRenderer, class_5481 text, int x, int y, int color) {
      TimeAnimation animation = null;
      if (this.current != null && BetterChat.INSTANCE.animationMap.containsKey(this.current)) {
         animation = (TimeAnimation)BetterChat.INSTANCE.animationMap.get(this.current);
      }

      if (animation != null) {
         animation.setState(true);
      }

      List<OrderedTextPart> parts = OrderedTextPart.getParts(text);
      class_5250 mutable = class_2561.method_43473();

      for(OrderedTextPart part : parts) {
         String l = part.getText();
         class_2583 style = part.getStyle();
         if (style.method_10973() != null && style.method_10973().method_27716() == (new Color(48, 184, 203, 0)).getRGB()) {
            mutable.method_10852(class_2561.method_43470(l).method_10862(style.method_36139(Hud.getTextColor(y).getRGB())));
         } else {
            mutable.method_10852(class_2561.method_43470(l).method_10862(style));
         }
      }

      text = class_2477.method_10517().method_30934(mutable);
      int slideOffset = animation != null && BetterChat.INSTANCE.isEnabled() && (Boolean)BetterChat.INSTANCE.animations.getValue() ? (int)animation.getCurrent() : 0;
      return instance.method_35720(textRenderer, text, x + slideOffset, y, color);
   }

   @Inject(
      method = {"render"},
      at = {@At("HEAD")}
   )
   private void render(class_332 context, int currentTick, int mouseX, int mouseY, boolean focused, CallbackInfo ci) {
      long current = System.currentTimeMillis();
      long diff = current - this.prevMillis;
      this.prevMillis = current;
      this.updatePercentage(diff);
      float t = BetterChat.INSTANCE.percentComplete;
      this.percent = 1.0F - --t * t * t * t;
      this.percent = MathUtil.clamp(this.percent, 0.0F, 1.0F);
   }

   @Shadow
   public abstract void method_1812(class_2561 var1);

   @Shadow
   public abstract void method_44811(class_2561 var1, class_7469 var2, class_7591 var3);

   @Shadow
   public abstract double method_1814();

   public void addChatMessageWithId(class_2561 message, int id) {
      this.nextId = id;
      class_7591 indicator = new class_7591(class_124.field_1075.method_532(), (class_7591.class_7592)null, (class_2561)null, (String)null);
      this.method_44811(message, (class_7469)null, indicator);
      this.nextId = 0;
   }

   public void addChatMessageNoId(class_2561 message) {
      class_7591 indicator = null;
      indicator = new class_7591(class_124.field_1075.method_532(), (class_7591.class_7592)null, (class_2561)null, (String)null);
      this.method_44811(message, (class_7469)null, indicator);
   }

   @Inject(
      method = {"addVisibleMessage"},
      at = {@At(
   value = "INVOKE",
   target = "Ljava/util/List;add(ILjava/lang/Object;)V",
   shift = Shift.AFTER
)}
   )
   private void onAddMessageAfterNewChatHudLineVisible(class_303 message, CallbackInfo ci) {
      try {
         ((IChatHudLine)this.field_2064.get(0)).setOverrideId(this.nextId);
      } catch (Exception e) {
         ChatUtils.sendMessage(String.valueOf(class_124.field_1061) + "Unexpected message state! send your game log to itsmahir if you see this!");
         e.printStackTrace();
      }

   }

   @Inject(
      method = {"addMessage(Lnet/minecraft/client/gui/hud/ChatHudLine;)V"},
      at = {@At(
   value = "INVOKE",
   target = "Ljava/util/List;add(ILjava/lang/Object;)V",
   shift = Shift.AFTER
)}
   )
   private void onAddMessageAfterNewChatHudLine(class_303 message, CallbackInfo ci) {
      try {
         ((IChatHudLine)this.field_2061.get(0)).setOverrideId(this.nextId);
      } catch (Exception e) {
         ChatUtils.sendMessage(String.valueOf(class_124.field_1061) + "Unexpected message id state! send your game log to itsmahir if you see this!");
         e.printStackTrace();
      }

   }

   @Inject(
      at = {@At("HEAD")},
      method = {"addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V"},
      cancellable = true
   )
   private void onAddMessage(class_2561 message, class_7469 signatureData, class_7591 indicator, CallbackInfo ci) {
      this.field_2064.removeIf((msg) -> ((IChatHudLine)msg).getOverrideId() == this.nextId && this.nextId != 0);

      for(int i = this.field_2061.size() - 1; i > -1; --i) {
         if (((IChatHudLine)this.field_2061.get(i)).getOverrideId() == this.nextId && this.nextId != 0) {
            this.field_2061.remove(i);
         }
      }

   }

   @Inject(
      method = {"clear"},
      at = {@At("HEAD")}
   )
   private void onClear(boolean clearHistory, CallbackInfo ci) {
   }

   @Inject(
      method = {"refresh"},
      at = {@At("HEAD")}
   )
   private void onRefresh(CallbackInfo ci) {
   }
}
