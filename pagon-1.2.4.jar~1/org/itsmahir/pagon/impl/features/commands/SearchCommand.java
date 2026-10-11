package org.itsmahir.pagon.impl.features.commands;

import net.minecraft.class_124;
import net.minecraft.class_2248;
import org.itsmahir.pagon.api.command.Command;
import org.itsmahir.pagon.api.management.CommandManager;
import org.itsmahir.pagon.api.management.SearchManager;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;

public class SearchCommand extends Command {
   public SearchCommand() {
      super("Search", "add blocks to search esp", new String[]{"search"});
   }

   public void run(String[] args) {
      try {
         boolean[] correction = this.setIncorrect(args);
         boolean incorrect = correction[0];
         if (incorrect) {
            this.handleIncorrectUsage();
            return;
         }

         switch (args[1].toLowerCase()) {
            case "add" -> this.handleAddBlock(args);
            case "del" -> this.handleRemoveBlock(args);
            default -> this.handleIncorrectUsage();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   private boolean[] setIncorrect(String[] args) {
      boolean incorrect = false;
      if (args.length < 2) {
         incorrect = true;
      }

      if (!incorrect) {
         if (args[1].equalsIgnoreCase("add")) {
            if (args.length < 4 && args.length != 3) {
               incorrect = true;
            }
         } else if (args[1].equalsIgnoreCase("del") && args.length < 3) {
            incorrect = true;
         }
      }

      return new boolean[]{incorrect};
   }

   private void handleIncorrectUsage() {
      ChatUtils.sendMessage(String.valueOf(class_124.field_1061) + "Incorrect usage!");
      String var10000 = String.valueOf(class_124.field_1061);
      ChatUtils.sendMessage(var10000 + "Usage: " + CommandManager.INSTANCE.PREFIX + "Search <add/del> <blockname>");
   }

   private void handleAddBlock(String[] args) {
      class_2248 block = SearchManager.INSTANCE.findBlock(args[2].toLowerCase());
      if (block == null) {
         ChatUtils.sendMessage("Block does not exist.");
      } else {
         String name = block.method_9518().getString();
         if (!SearchManager.INSTANCE.hasBlock(block)) {
            SearchManager.INSTANCE.addBlock(args[2]);
            ChatUtils.sendMessage("Block " + name + " added.");
            mc.field_1769.method_3279();
         } else {
            ChatUtils.sendMessage("Block " + name + " is already in the Search list.");
         }

      }
   }

   private void handleRemoveBlock(String[] args) {
      class_2248 block = SearchManager.INSTANCE.findBlock(args[2]);
      if (block == null) {
         ChatUtils.sendMessage("Block does not exist.");
      } else {
         String name = block.method_9518().getString();
         if (SearchManager.INSTANCE.hasBlock(block)) {
            SearchManager.INSTANCE.removeBlock(block);
            ChatUtils.sendMessage("Block " + name + " removed.");
            mc.field_1769.method_3279();
         } else {
            ChatUtils.sendMessage("Block " + name + " is not in the Search list.");
         }

      }
   }

   public String[] getFill(String[] args) {
      return args.length == 1 ? new String[]{"{add,del}", "block"} : new String[]{"{add,del}"};
   }
}
