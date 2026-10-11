package org.itsmahir.pagon.impl.features.commands;

import net.minecraft.class_124;
import org.itsmahir.pagon.api.command.Command;
import org.itsmahir.pagon.api.management.WaypointManager;
import org.itsmahir.pagon.api.utils.chat.ChatMessage;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;

public class WaypointCommand extends Command {
   public WaypointCommand() {
      super("Waypoint", "Create a waypoint", new String[]{"waypoint"});
   }

   public void run(String[] args) {
      if (args.length > 2) {
         if (args[1].equalsIgnoreCase("create")) {
            if (args.length == 3) {
               WaypointManager.WayPoint wp = new WaypointManager.WayPoint((int)mc.field_1724.method_23317(), (int)mc.field_1724.method_23318(), (int)mc.field_1724.method_23321(), args[2], mc.method_1542() ? "SinglePlayer" : mc.method_1562().method_45734().field_3761, mc.field_1687.method_27983().method_29177().method_12832());
               WaypointManager.INSTANCE.addWayPoint(wp);
               String var10000 = wp.getName();
               ChatUtils.sendMessage("Created waypoint " + var10000 + " at X: " + (int)mc.field_1724.method_23317() + " Y: " + (int)mc.field_1724.method_23318() + " Z: " + (int)mc.field_1724.method_23321());
            } else if (args.length == 6) {
               try {
                  int x = Integer.parseInt(args[3]);
                  int y = Integer.parseInt(args[4]);
                  int z = Integer.parseInt(args[5]);
                  WaypointManager.WayPoint wp = new WaypointManager.WayPoint(x, y, z, args[2], mc.method_1542() ? "SinglePlayer" : mc.method_1562().method_45734().field_3761, mc.field_1687.method_27983().method_29177().method_12832());
                  WaypointManager.INSTANCE.addWayPoint(wp);
                  ChatUtils.sendMessage("Created waypoint " + wp.getName() + " at X: " + x + " Y: " + y + " Z: " + z);
               } catch (Exception var6) {
                  ChatUtils.sendMessage(String.valueOf(class_124.field_1061) + "Invalid waypoint!");
               }
            } else {
               ChatUtils.sendMessage(new ChatMessage("Invalid format", false, 0));
            }
         } else if (args[1].equalsIgnoreCase("remove")) {
            WaypointManager.INSTANCE.wayPoints.removeIf((value) -> value.getName().equalsIgnoreCase(args[2]));
            ChatUtils.sendMessage(new ChatMessage("Removed waypoint with name: " + args[2], false, 0));
         } else {
            ChatUtils.sendMessage(new ChatMessage("Invalid format", false, 0));
         }
      } else {
         ChatUtils.sendMessage(new ChatMessage("Invalid format", false, 0));
      }

   }

   public String[] getFill(String[] args) {
      if (args.length > 1) {
         if (args[1].equalsIgnoreCase("create")) {
            return new String[]{"{create,remove}", "name", "x", "y", "z"};
         }

         if (args[1].equalsIgnoreCase("remove")) {
            return new String[]{"{create,remove}", "{WAYPOINTS}"};
         }
      }

      return new String[]{"{create,remove}", "name", "x", "y", "z"};
   }
}
