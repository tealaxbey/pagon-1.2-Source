package org.itsmahir.pagon.impl.features.commands;

import net.minecraft.class_124;
import org.itsmahir.pagon.api.command.Command;
import org.itsmahir.pagon.api.management.SavableManager;
import org.itsmahir.pagon.api.utils.chat.ChatMessage;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;

public class ConfigCommand extends Command {
   public ConfigCommand() {
      super("Config", "loads and saves configs", new String[]{"config"});
   }

   public void run(String[] args) {
      if (args.length > 2) {
         boolean error = false;
         if (args[1].equalsIgnoreCase("save")) {
            try {
               SavableManager.INSTANCE.saveModuleConfig(args[2]);
            } catch (Exception var53) {
               ChatUtils.sendMessage(new ChatMessage(String.valueOf(class_124.field_1061) + "Failed to save config " + args[2] + ".cfg!", false, 0));
               error = true;
            } finally {
               if (!error) {
                  ChatUtils.sendMessage(new ChatMessage(String.valueOf(class_124.field_1078) + "Successfully saved config " + args[2] + ".cfg!", false, 0));
               }

            }
         } else if (args[1].equalsIgnoreCase("load")) {
            try {
               SavableManager.INSTANCE.loadModuleConfig(args[2]);
            } catch (Exception e) {
               ChatUtils.sendMessage(new ChatMessage(String.valueOf(class_124.field_1061) + e.getMessage(), false, 0));
               error = true;
            } finally {
               if (!error) {
                  ChatUtils.sendMessage(new ChatMessage(String.valueOf(class_124.field_1078) + "Successfully loaded config " + args[2] + ".cfg!", false, 0));
               }

            }
         } else if (args[1].equalsIgnoreCase("softload")) {
            try {
               SavableManager.INSTANCE.softLoadConfig(args[2]);
            } catch (Exception e) {
               ChatUtils.sendMessage(new ChatMessage(String.valueOf(class_124.field_1061) + e.getMessage(), false, 0));
               error = true;
            } finally {
               if (!error) {
                  ChatUtils.sendMessage(new ChatMessage(String.valueOf(class_124.field_1078) + "Successfully soft-loaded config " + args[2] + ".cfg!", false, 0));
               }

            }
         } else if (!args[1].equalsIgnoreCase("del") && !args[1].equalsIgnoreCase("delete")) {
            ChatUtils.sendMessage(new ChatMessage(String.valueOf(class_124.field_1061) + "Bad usage! config <save/del/load/softload/list/folder> <name>", false, 0));
         } else {
            try {
               SavableManager.INSTANCE.deleteModuleConfig(args[2]);
            } catch (Exception e) {
               ChatUtils.sendMessage(new ChatMessage(String.valueOf(class_124.field_1061) + e.getMessage(), false, 0));
               error = true;
            } finally {
               if (!error) {
                  ChatUtils.sendMessage(new ChatMessage(String.valueOf(class_124.field_1078) + "Successfully deleted config " + args[2] + ".cfg!", false, 0));
               }

            }
         }
      } else if (args.length > 1) {
         if (args[1].equalsIgnoreCase("list")) {
            try {
               SavableManager.INSTANCE.listConfigs();
            } catch (Exception var49) {
            }
         } else if (!args[1].equalsIgnoreCase("folder") && !args[1].equalsIgnoreCase("open")) {
            ChatUtils.sendMessage(new ChatMessage(String.valueOf(class_124.field_1061) + "Bad usage! config <save/del/load/list/folder> <name>", false, 0));
         } else {
            try {
               SavableManager.INSTANCE.openFolder();
            } catch (Exception var48) {
            }
         }
      } else {
         ChatUtils.sendMessage(new ChatMessage("Invalid format", false, 0));
      }

   }

   public String[] getFill(String[] args) {
      return new String[]{"{folder,del,save,load,softload}", "{CONFIGFILE}"};
   }
}
