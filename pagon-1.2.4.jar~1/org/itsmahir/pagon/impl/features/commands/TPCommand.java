package org.itsmahir.pagon.impl.features.commands;

import org.itsmahir.pagon.api.command.Command;
import org.itsmahir.pagon.api.utils.chat.ChatMessage;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;

public class TPCommand extends Command {
   public TPCommand() {
      super("Tp", "teleports you to x y z", new String[]{"tp"});
   }

   public void run(String[] args) {
      if (args.length == 4) {
         mc.field_1724.method_5814(Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]));
      } else {
         ChatUtils.sendMessage(new ChatMessage("Please input a valid command", false, 0));
      }

   }

   public String[] getFill(String[] args) {
      return new String[]{"x", "y", "z"};
   }
}
