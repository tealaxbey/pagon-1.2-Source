package org.itsmahir.pagon.impl.features.commands;

import java.io.File;
import net.minecraft.class_156;
import org.itsmahir.pagon.api.command.Command;
import org.itsmahir.pagon.api.management.SavableManager;

public class FolderCommand extends Command {
   public FolderCommand() {
      super("Folder", "opens Pagon folder", new String[]{"folder"});
   }

   public void run(String[] args) {
      File dir = new File(SavableManager.MAIN_FOLDER.getAbsolutePath());
      if (!dir.exists()) {
         dir.mkdir();
      }

      class_156.method_668().method_672(SavableManager.MAIN_FOLDER.getAbsoluteFile());
   }
}
