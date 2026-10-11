package org.itsmahir.pagon.impl.features.commands;

import net.minecraft.class_1297;
import org.itsmahir.pagon.api.command.Command;

public class HClipCommand extends Command {
   public HClipCommand() {
      super("HClip", "teleports u forward", new String[]{"hclip"});
   }

   public void run(String[] args) {
      if (args.length != 1) {
         if (mc.field_1724 != null) {
            double h = Double.parseDouble(args[1]);
            class_1297 entity = mc.field_1724.method_5854();
            if (entity == null) {
               entity = mc.field_1724;
            }

            double yaw = Math.cos(Math.toRadians((double)(mc.field_1724.method_36454() + 90.0F)));
            double pit = Math.sin(Math.toRadians((double)(mc.field_1724.method_36454() + 90.0F)));
            entity.method_5814(entity.method_23317() + h * yaw, entity.method_23318(), entity.method_23321() + h * pit);
         }
      }
   }

   public String[] getFill(String[] args) {
      return args.length == 1 ? new String[]{"Distance"} : new String[0];
   }
}
