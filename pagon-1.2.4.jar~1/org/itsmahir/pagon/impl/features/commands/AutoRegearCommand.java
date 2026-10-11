package org.itsmahir.pagon.impl.features.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_124;
import net.minecraft.class_1799;
import org.itsmahir.pagon.api.command.Command;
import org.itsmahir.pagon.api.utils.chat.ChatMessage;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;

public class AutoRegearCommand extends Command {
   private static final Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
   public static final File pathSave;

   public AutoRegearCommand() {
      super("AutoRegear", "lets u save kits", new String[]{"autoregear"});
   }

   public void run(String[] args) {
      if (args.length >= 1) {
         switch (args[1].toLowerCase()) {
            case "list":
               if (args.length == 2) {
                  this.sendList();
               } else {
                  errorMessage("cope");
               }
               break;
            case "set":
               if (args.length == 3) {
                  this.set(args[2]);
               } else {
                  errorMessage(String.valueOf(class_124.field_1061) + "Invalid syntax! Syntax is: regear set/create/list/del kit");
               }
               break;
            case "save":
            case "create":
               if (args.length == 3) {
                  save(args[2]);
               } else {
                  errorMessage(String.valueOf(class_124.field_1061) + "Invalid syntax! Syntax is: regear set/create/list/del kit");
               }
               break;
            case "del":
               if (args.length == 3) {
                  this.delete(args[2]);
               } else {
                  errorMessage(String.valueOf(class_124.field_1061) + "Invalid syntax! Syntax is: regear set/create/list/del kit");
               }
               break;
            case "":
            case "help":
            default:
               errorMessage(String.valueOf(class_124.field_1061) + "Invalid syntax! Syntax is: regear set/create/list/del kit");
         }

      }
   }

   private void sendList() {
      try {
         FileReader reader = new FileReader(pathSave);
         Map<String, Object> map = (Map)gson.fromJson(reader, Map.class);
         int lengt = map.entrySet().size();

         for(int i = 0; i < lengt; ++i) {
            String item = (String)((Map.Entry)map.entrySet().stream().toList().get(i)).getKey();
            if (!item.equals("pointer")) {
               ChatUtils.sendMessage(new ChatMessage("Kit: " + item, false, 1777));
            }
         }

         reader.close();
      } catch (IOException var6) {
      }

   }

   private void set(String name) {
      try {
         FileReader reader = new FileReader(pathSave);
         Map<String, Object> map = (Map)gson.fromJson(reader, Map.class);
         reader.close();
         if (map != null && map.get(name) != null && !name.equals("pointer")) {
            map.put("pointer", name);
            saveFile(map, name, "selected");
         } else {
            errorMessage("NoEx");
         }
      } catch (IOException var4) {
         errorMessage("NoEx");
      }

   }

   private void delete(String name) {
      try {
         FileReader reader = new FileReader(pathSave);
         Map<String, Object> map = (Map)gson.fromJson(reader, Map.class);
         reader.close();
         if (map != null && map.get(name) != null && !name.equals("pointer")) {
            map.remove(name);
            if (map.get("pointer").toString().equals(name)) {
               map.put("pointer", "none");
            }

            saveFile(map, name, "has been deleted!");
         }
      } catch (IOException var4) {
      }

   }

   public static void save(String name) {
      new HashMap();

      Object map;
      try {
         FileReader reader = new FileReader(pathSave);
         map = (Map)gson.fromJson(reader, Map.class);
         reader.close();
         if (map != null && ((Map)map).get(name) != null && !name.equals("pointer")) {
            String var10000 = String.valueOf(class_124.field_1078);
            errorMessage(var10000 + "Kit " + String.valueOf(class_124.field_1075) + name + String.valueOf(class_124.field_1078) + " already exists!");
            return;
         }
      } catch (IOException var5) {
         map = new HashMap();
         ((Map)map).put("pointer", "none");
      }

      if (map == null) {
         map = new HashMap();
      }

      StringBuilder jsonInventory = new StringBuilder();

      for(class_1799 item : mc.field_1724.method_31548().field_7547) {
         jsonInventory.append(item.method_7909().method_7854().method_7922()).append(" ");
      }

      ((Map)map).put(name, jsonInventory.toString());
      saveFile((Map)map, name, "has been saved!");
   }

   public static void errorMessage(String error) {
      ChatUtils.sendMessage(new ChatMessage(error, false, 1777));
   }

   public static void sendMessage(String message) {
      ChatUtils.sendMessage(new ChatMessage(String.valueOf(class_124.field_1061) + message, false, 1777));
   }

   public static void saveFile(Map<String, Object> map, String name, String operation) {
      try {
         FileWriter writer = new FileWriter(pathSave);
         gson.toJson(map, writer);
         writer.close();
         String var10000 = String.valueOf(class_124.field_1075);
         sendMessage(var10000 + name + " " + String.valueOf(class_124.field_1078) + operation);
      } catch (IOException var4) {
         errorMessage(String.valueOf(class_124.field_1061) + "Error saving kit :( please try again!");
      }

   }

   public static String getCurrentSet() {
      try {
         FileReader reader = new FileReader(pathSave);
         Map<String, Object> map = (Map)gson.fromJson(reader, Map.class);
         reader.close();
         if (map != null && !map.get("pointer").equals("none")) {
            return map.get("pointer").toString();
         }
      } catch (IOException var2) {
      }

      return "";
   }

   public static String getInventoryKit(String kit) {
      try {
         FileReader reader = new FileReader(pathSave);
         Map<String, Object> map = (Map)gson.fromJson(reader, Map.class);
         reader.close();
         if (map != null) {
            return map.get(kit).toString();
         }
      } catch (IOException var3) {
      }

      return "";
   }

   public String[] getFill(String[] args) {
      return new String[]{"{set,create,del,help}", "name"};
   }

   static {
      String var10002 = System.getProperty("user.dir");
      pathSave = new File(var10002 + File.separator + "Pagon" + File.separator + "misc" + File.separator + "kits.cfg");
   }
}
