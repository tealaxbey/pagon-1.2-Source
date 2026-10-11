package org.itsmahir.pagon.mixin;

import net.minecraft.class_1041;
import net.minecraft.class_1297;
import net.minecraft.class_310;
import net.minecraft.class_315;
import net.minecraft.class_437;
import net.minecraft.class_542;
import net.minecraft.class_636;
import net.minecraft.class_746;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.render.EntityOutlineEvent;
import org.itsmahir.pagon.api.event.events.render.FrameEvent;
import org.itsmahir.pagon.api.event.events.render.ScreenEvent;
import org.itsmahir.pagon.api.gui.font.Fonts;
import org.itsmahir.pagon.api.utils.render.WindowResizeCallback;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_310.class})
public abstract class MixinMinecraftClient {
   @Shadow
   @Final
   private class_1041 field_1704;
   @Shadow
   @Final
   public class_315 field_1690;

   @Shadow
   public abstract boolean method_1569();

   @Inject(
      method = {"render"},
      at = {@At("HEAD")}
   )
   public void render(CallbackInfo ci) {
      (new FrameEvent.FrameFlipEvent()).post();
   }

   @Inject(
      method = {"tick"},
      at = {@At("HEAD")}
   )
   public void tickPre(CallbackInfo ci) {
      (new TickEvent.ClientTickEvent()).post();
   }

   @Inject(
      method = {"tick"},
      at = {@At("TAIL")}
   )
   public void tickPost(CallbackInfo ci) {
      (new TickEvent.AfterClientTickEvent()).post();
   }

   @Inject(
      method = {"handleInputEvents"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"
)},
      slice = {@Slice(
   to = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/MinecraftClient;doAttack()Z"
)
)}
   )
   public void hookEventAttack(CallbackInfo ci) {
      (new TickEvent.VanillaTick()).post();
   }

   @Inject(
      method = {"Lnet/minecraft/client/MinecraftClient;tick()V"},
      at = {@At(
   value = "FIELD",
   target = "Lnet/minecraft/client/MinecraftClient;overlay:Lnet/minecraft/client/gui/screen/Overlay;"
)}
   )
   public void hookInputTick(CallbackInfo ci) {
      (new TickEvent.InputTick()).post();
   }

   @Inject(
      method = {"<init>"},
      at = {@At("TAIL")}
   )
   void postWindowInit(class_542 args, CallbackInfo ci) {
      try {
         Fonts.CUSTOM = Fonts.create((float)((Number)org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.fontSize.getValue()).intValue());
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   @Redirect(
      method = {"handleBlockBreaking"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"
)
   )
   private boolean hookIsUsingItem(class_746 instance) {
      return ((String)AntiCheat.INSTANCE.multiTask.getValue()).equals("None") && instance.method_6115();
   }

   @Redirect(
      method = {"doItemUse"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;isBreakingBlock()Z"
)
   )
   private boolean hookIsBreakingBlock(class_636 instance) {
      return ((String)AntiCheat.INSTANCE.multiTask.getValue()).equals("None") && instance.method_2923();
   }

   @Inject(
      method = {"getFramerateLimit"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onGetFramerateLimit(CallbackInfoReturnable<Integer> info) {
   }

   @Inject(
      method = {"hasOutline"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookHasOutline(class_1297 entity, CallbackInfoReturnable<Boolean> cir) {
      EntityOutlineEvent entityOutlineEvent = new EntityOutlineEvent(entity);
      entityOutlineEvent.post();
      if (entityOutlineEvent.isCancelled()) {
         cir.cancel();
         cir.setReturnValue(true);
      }

   }

   @Inject(
      method = {"Lnet/minecraft/client/MinecraftClient;tick()V"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/GameRenderer;tick()V"
)}
   )
   public void hookGameRenderTick(CallbackInfo ci) {
      (new TickEvent.GameRenderTick()).post();
   }

   @Inject(
      method = {"onResolutionChanged"},
      at = {@At("TAIL")}
   )
   private void captureResize(CallbackInfo ci) {
      ((WindowResizeCallback)WindowResizeCallback.EVENT.invoker()).onResized((class_310)this, this.field_1704);
   }

   @ModifyVariable(
      method = {"setScreen"},
      at = @At("HEAD"),
      argsOnly = true
   )
   private class_437 modifyScreen(class_437 value) {
      ScreenEvent.SetScreen event = new ScreenEvent.SetScreen(value);
      event.post();
      return event.getGuiScreen();
   }
}
