package org.itsmahir.pagon.impl.features.commands;

import org.itsmahir.pagon.api.command.Command;
import org.itsmahir.pagon.api.management.CommandManager;
import org.itsmahir.pagon.api.utils.chat.ChatMessage;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;

public class HelpCommand extends Command {
   public HelpCommand() {
      super("Help", "shows you all the commands", new String[]{"help", "commands"});
   }

   public void run(String[] args) {
      for(Command command : CommandManager.INSTANCE.getCommands()) {
         ChatUtils.sendMessage(new ChatMessage(command.getName() + " - " + command.getDesc(), false, 0));
      }

   }
}
