package org.itsmahir.pagon.impl.features.commands;

import net.minecraft.class_124;
import org.itsmahir.pagon.api.command.Command;
import org.itsmahir.pagon.api.management.PopManager;
import org.itsmahir.pagon.api.utils.chat.ChatMessage;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;

public class ClearPopsCommand extends Command {
   public ClearPopsCommand() {
      super("ClearPops", "Clears pops of a player", new String[]{"clearpops"});
   }

   public void run(String[] args) {
      if (args.length == 2) {
         if (!PopManager.INSTANCE.registry.containsKey(args[1])) {
            ChatUtils.sendMessage(new ChatMessage(String.valueOf(class_124.field_1061) + args[1] + " has not popped a single time!", false, 0));
         } else {
            PopManager.INSTANCE.clearPops(args[1]);
            ChatUtils.sendMessage(new ChatMessage(String.valueOf(class_124.field_1075) + "Reset " + args[1] + " to 0 pops!", false, 0));
         }
      } else {
         ChatUtils.sendMessage(new ChatMessage("Please input a real player", false, 0));
      }

   }

   public String[] getFill(String[] args) {
      return new String[]{"{PLAYERS}"};
   }
}
