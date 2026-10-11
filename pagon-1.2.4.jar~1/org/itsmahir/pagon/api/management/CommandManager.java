package org.itsmahir.pagon.api.management;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_124;
import org.itsmahir.pagon.api.command.Command;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.misc.ClientChatEvent;
import org.itsmahir.pagon.api.utils.Pair;
import org.itsmahir.pagon.api.utils.chat.ChatMessage;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.PagonMod;

public class CommandManager implements IMinecraft {
   public static CommandManager INSTANCE;
   public String PREFIX = "-";
   List<Command> commands = new ArrayList();

   public CommandManager() {
      PagonMod.EVENT_BUS.register(this);
   }

   @SubscribeEvent
   public void onChat(ClientChatEvent event) {
      if (event.getMessage().startsWith(this.PREFIX)) {
         event.setCancelled(true);
         mc.field_1705.method_1743().method_1803(event.getMessage());
         String sub = event.getMessage().substring(1);
         String[] args = sub.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
         if (args.length > 0) {
            Boolean hasRan = false;

            for(Command command : this.commands) {
               for(String s : command.getAlias()) {
                  if (s.equalsIgnoreCase(args[0])) {
                     command.run(args);
                     hasRan = true;
                     break;
                  }
               }
            }

            if (!hasRan) {
               ChatUtils.sendMessage(new ChatMessage(String.valueOf(class_124.field_1061) + "Invalid command! Type " + this.PREFIX + "help for a list of commands!", false, 0));
            }
         } else {
            ChatUtils.sendMessage(new ChatMessage(String.valueOf(class_124.field_1061) + "Please type a command! Type " + this.PREFIX + "help for a list of commands!", false, 0));
         }
      }

   }

   public Pair<Command, String> findClosestMatchingCommand(String string) {
      for(Command command : this.commands) {
         for(String s : command.getAlias()) {
            if (string == s) {
               return new Pair<Command, String>(command, s);
            }

            if (string.length() <= s.length() && s.startsWith(string)) {
               return new Pair<Command, String>(command, s);
            }
         }
      }

      return null;
   }

   public List<Command> getCommands() {
      return this.commands;
   }
}
