package org.itsmahir.pagon.mixin;

import net.minecraft.class_309;
import net.minecraft.class_310;
import net.minecraft.class_437;
import org.itsmahir.pagon.api.event.events.key.CharTypeEvent;
import org.itsmahir.pagon.api.event.events.key.KeyboardEvent;
import org.itsmahir.pagon.api.gui.GUI;
import org.itsmahir.pagon.api.gui.hudeditor.HudEditorGUI;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_309.class})
public class MixinKeyboard {
   @Shadow
   @Final
   private class_310 field_1678;

   @Inject(
      method = {"onKey"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
      if (key != -1) {
         KeyboardEvent event = new KeyboardEvent(false, key, action);
         event.post();
         if (event.isCancelled()) {
            ci.cancel();
         }
      }

   }

   @Inject(
      method = {"onChar"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onChar(long window, int codePoint, int modifiers, CallbackInfo ci) {
      if (window == this.field_1678.method_22683().method_4490()) {
         class_437 screen = this.field_1678.field_1755;
         if (screen != null && (screen instanceof GUI || screen instanceof HudEditorGUI)) {
            if (Character.charCount(codePoint) == 1) {
               (new CharTypeEvent((char)codePoint)).post();
               ci.cancel();
            } else {
               char[] var6 = Character.toChars(codePoint);

               for(char c : var6) {
                  (new CharTypeEvent(c)).post();
                  ci.cancel();
               }
            }
         }
      }

   }
}
