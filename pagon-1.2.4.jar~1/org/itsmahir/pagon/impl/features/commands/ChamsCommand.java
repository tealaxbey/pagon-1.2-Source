package org.itsmahir.pagon.impl.features.commands;

import java.io.File;
import java.util.Objects;
import net.minecraft.class_124;
import net.minecraft.class_156;
import net.minecraft.class_310;
import org.itsmahir.pagon.api.command.Command;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;

public class ChamsCommand extends Command {
   String lastFile = "";

   public ChamsCommand() {
      super("Chams", "set image", new String[]{"chams"});
   }

   public void run(String[] args) {
      if (Objects.equals(args[1], "folder")) {
         openDirectory();
      } else if (Objects.equals(args[1], "load")) {
         if (args.length == 3) {
            this.handleSetFile(args);
         } else {
            ChatUtils.sendMessage(String.valueOf(class_124.field_1061) + "Incorrect usage: chams load <filename>");
         }
      }

   }

   private static void openDirectory() {
      File chamsFile = new File(class_310.method_1551().field_1697.getAbsolutePath(), File.separator + "Pagon" + File.separator + "chams" + File.separator);
      if (!chamsFile.exists()) {
         chamsFile.mkdir();
      }

      class_156.method_668().method_672(chamsFile);
   }

   private void handleSetFile(String[] args) {
      ChatUtils.sendMessage(String.valueOf(class_124.field_1061) + "Image shader mode has been removed.");
   }

   public String[] getFill(String[] args) {
      return new String[]{"{file,folder,load}", "{CHAMSFILE}"};
   }
}
