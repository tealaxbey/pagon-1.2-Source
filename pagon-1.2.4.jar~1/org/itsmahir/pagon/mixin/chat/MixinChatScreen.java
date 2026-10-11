package org.itsmahir.pagon.mixin.chat;

import java.awt.Color;
import java.util.Objects;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_342;
import net.minecraft.class_408;
import net.minecraft.class_437;
import org.itsmahir.pagon.api.command.Command;
import org.itsmahir.pagon.api.event.events.misc.ClientChatEvent;
import org.itsmahir.pagon.api.management.CommandManager;
import org.itsmahir.pagon.api.utils.CommandUtils;
import org.itsmahir.pagon.api.utils.Pair;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_408.class})
public class MixinChatScreen extends class_437 {
   @Shadow
   protected class_342 field_2382;

   protected MixinChatScreen(class_2561 title) {
      super(title);
   }

   @Shadow
   public void method_44056(String chatText, boolean addToHistory) {
   }

   @Inject(
      method = {"sendMessage"},
      at = {@At(
   value = "INVOKE",
   target = "Ljava/lang/String;startsWith(Ljava/lang/String;)Z"
)},
      cancellable = true
   )
   public void hookEventChat(String chatText, boolean addToHistory, CallbackInfo ci) {
      ClientChatEvent eventChat = new ClientChatEvent(chatText);
      eventChat.post();
      if (eventChat.isCancelled()) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"keyPressed"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
      if (this.field_2382 != null && this.field_2382.method_1882().startsWith(CommandManager.INSTANCE.PREFIX) && keyCode == 258) {
         String text = this.field_2382.method_1882();
         String commandText = this.getCommandText(text, false);
         if (!Objects.equals(commandText, "")) {
            this.field_2382.method_1852(text + commandText);
         }

         cir.setReturnValue(true);
      }

   }

   @Inject(
      method = {"render"},
      at = {@At("TAIL")}
   )
   public void renderChat(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      if (this.field_2382 != null) {
         if (this.field_2382.method_1882().startsWith(CommandManager.INSTANCE.PREFIX)) {
            this.processAutoComplete(context, this.field_2382.method_1882());
            RenderUtil.renderOutline(context.method_51448(), (double)2.0F, (double)(this.field_22790 - 14), (double)this.field_2382.method_25368(), (double)this.field_2382.method_25364(), Color.CYAN.getRGB(), true);
         }

      }
   }

   @Unique
   public void processAutoComplete(class_332 context, String text) {
      int x = class_310.method_1551().field_1772.method_1727(text + " ");
      int y = this.field_2382.method_1851() ? this.field_2382.method_46427() + (this.field_2382.method_25364() - 8) / 2 : this.field_2382.method_46427();
      String commandText = this.getCommandText(text, true);
      if (!commandText.isEmpty()) {
         context.method_25303(class_310.method_1551().field_1772, commandText, x, y, (new Color(134, 242, 252)).getRGB());
      }

   }

   @Unique
   public String getCommandText(String text, boolean description) {
      try {
         String sub = text.substring(1);
         if (sub.isEmpty()) {
            return "";
         }

         String[] args = sub.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
         Pair<Command, String> command = null;
         if (args[0] == null) {
            return "";
         }

         command = CommandManager.INSTANCE.findClosestMatchingCommand(args[0]);
         if (command == null) {
            return "";
         }

         if (text.endsWith(" ") && ((String)command.value()).equals(args[0])) {
            return CommandUtils.getAutocomplete(command.key(), "", args.length - 1, true, args);
         }

         if (text.endsWith(" ")) {
            return "";
         }

         if (args.length == 1) {
            String var10000 = ((String)command.value()).substring(args[0].length());
            return var10000 + (description ? String.valueOf(class_124.field_1080) + " (" + ((Command)command.key()).getDesc() + ")" : "");
         }

         if (Objects.equals(command.value(), args[0])) {
            int lengthMinus = args.length - 1;
            String argument = args[lengthMinus];
            String fill = CommandUtils.getAutocomplete(command.key(), argument, lengthMinus - 1, false, args);
            if (!fill.isEmpty()) {
               return fill.substring(argument.length());
            }
         }
      } catch (Exception var9) {
      }

      return "";
   }
}
