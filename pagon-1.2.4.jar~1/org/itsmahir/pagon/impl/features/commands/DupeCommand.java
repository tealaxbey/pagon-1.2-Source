package org.itsmahir.pagon.impl.features.commands;

import java.util.Objects;
import net.minecraft.class_1268;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import org.itsmahir.pagon.api.command.Command;

public class DupeCommand extends Command {
   public DupeCommand() {
      super("Dupe", "teleports u forward", new String[]{"dupe"});
   }

   public void run(String[] args) {
      if (Objects.equals(args[1], "desync")) {
         mc.field_1724.method_6122(class_1268.field_5808, class_1799.field_8037);
      }

      if (Objects.equals(args[1], "retrieve")) {
         class_1799 stack = class_1802.field_8070.method_7854();
         stack.method_7939(64);
         mc.field_1724.method_6122(class_1268.field_5808, stack);
      }

   }
}
