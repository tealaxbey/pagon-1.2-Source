package org.itsmahir.pagon.impl.features.commands;

import org.itsmahir.pagon.api.command.Command;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.FeatureManager;
import org.itsmahir.pagon.api.utils.chat.ChatMessage;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;

public class ToggleCommand extends Command {
   public ToggleCommand() {
      super("Toggle", "toggles a meowdule", new String[]{"toggle"});
   }

   public void run(String[] args) {
      if (args.length > 1) {
         for(Feature feature : FeatureManager.INSTANCE.getFeatures()) {
            if (feature instanceof Module) {
               Module module = (Module)feature;
               String modName = module.getName().replace(" ", "");
               if (modName.equalsIgnoreCase(args[1])) {
                  module.toggle();
                  return;
               }
            }
         }
      } else {
         ChatUtils.sendMessage(new ChatMessage("Please input a valid meowdule", false, 0));
      }

   }

   public String[] getFill(String[] args) {
      return new String[]{"{FEATURE}"};
   }
}
