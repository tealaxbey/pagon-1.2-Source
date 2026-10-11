package org.itsmahir.pagon.api.utils;

import java.io.File;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_1657;
import net.minecraft.class_310;
import net.minecraft.class_742;
import org.itsmahir.pagon.api.command.Command;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.friends.Friend;
import org.itsmahir.pagon.api.management.FeatureManager;
import org.itsmahir.pagon.api.management.FriendManager;
import org.itsmahir.pagon.api.management.WaypointManager;
import org.itsmahir.pagon.api.wrapper.IMinecraft;

public class CommandUtils implements IMinecraft {
   public static String getAutocomplete(Command command, String argument, int part, boolean endsWith, String[] args) {
      String[] fill = command.getFill(args);
      if (fill.length == 0) {
         return "";
      } else if (fill.length < part + 1) {
         return "";
      } else {
         String fillArgument = fill[part];
         return handleFill(fillArgument, argument);
      }
   }

   public static String handleFill(String fillArgument, String argument) {
      if (fillArgument == null) {
         return "";
      } else {
         switch (fillArgument) {
            case "{PLAYERS}":
               class_1657 player = getClosestMatchingPlayer(argument, false);
               if (player != null) {
                  return player.method_5477().getString();
               }
               break;
            case "{FEATURE}":
               Feature feature = FeatureManager.INSTANCE.getClosestMatchingFeature(argument);
               if (feature != null) {
                  return feature.getName();
               }
               break;
            case "{NONFRIENDS}":
               class_1657 nonFriendedPlayer = getClosestMatchingPlayer(argument, true);
               if (nonFriendedPlayer != null) {
                  return nonFriendedPlayer.method_5477().getString();
               }
               break;
            case "{FRIENDS}":
               Friend friend = FriendManager.INSTANCE.getClosestMatchingFriend(argument);
               if (friend != null) {
                  return friend.toString();
               }
               break;
            case "{WAYPOINTS}":
               WaypointManager.WayPoint wayPoint = WaypointManager.INSTANCE.getClosestMatchingWaypoint(argument);
               if (wayPoint != null) {
                  return wayPoint.getName();
               }
               break;
            case "{SPAMMERFILE}":
               return getClosestMatchingSpammerFile(argument);
            case "{CHAMSFILE}":
               return getClosestMatchingCham(argument);
            case "{CONFIGFILE}":
               return getClosestMatchingConfigFile(argument);
            default:
               if (fillArgument.contains(",")) {
                  return getMultiFillArgument(fillArgument, argument);
               }

               if (fillArgument.startsWith(argument)) {
                  return fillArgument;
               }
         }

         return "";
      }
   }

   public static String getClosestMatchingSpammerFile(String text) {
      File spammerFolder = new File(class_310.method_1551().field_1697.getAbsolutePath(), File.separator + "Pagon" + File.separator + "spammer" + File.separator);
      if (!spammerFolder.exists()) {
         return "";
      } else {
         File[] spammers = spammerFolder.listFiles();
         if (spammers.length == 0) {
            return "";
         } else {
            String bestSpammer = "";
            double lowestLength = Double.MAX_VALUE;

            for(File spammer : spammers) {
               if (Objects.equals(text, "")) {
                  return spammer.getName();
               }

               if (text.equals(spammer.getName())) {
                  return spammer.getName();
               }

               if (text.length() <= spammer.getName().length() && spammer.getName().startsWith(text) && lowestLength > (double)spammer.getName().length()) {
                  lowestLength = (double)spammer.getName().length();
                  bestSpammer = spammer.getName();
               }
            }

            return bestSpammer;
         }
      }
   }

   public static String getClosestMatchingConfigFile(String text) {
      File configFolder = new File(mc.field_1697.getAbsolutePath(), File.separator + "Pagon" + File.separator + "configs" + File.separator);
      if (!configFolder.exists()) {
         return "";
      } else {
         File[] configs = configFolder.listFiles();
         if (configs.length == 0) {
            return "";
         } else {
            String bestConfig = "";
            double lowestLength = Double.MAX_VALUE;

            for(File config : configs) {
               String name = config.getName().replace(".cfg", "");
               if (Objects.equals(text, "")) {
                  return name;
               }

               if (text.equals(name)) {
                  return name;
               }

               if (text.length() <= name.length() && name.startsWith(text) && lowestLength > (double)name.length()) {
                  lowestLength = (double)name.length();
                  bestConfig = name;
               }
            }

            return bestConfig;
         }
      }
   }

   public static String getClosestMatchingCham(String text) {
      File chams = new File(mc.field_1697.getAbsolutePath(), File.separator + "Pagon" + File.separator + "chams" + File.separator);
      if (!chams.exists()) {
         return "";
      } else {
         File[] configs = chams.listFiles();
         if (configs.length == 0) {
            return "";
         } else {
            String bestConfig = "";
            double lowestLength = Double.MAX_VALUE;

            for(File cham : configs) {
               String name = cham.getName();
               if (Objects.equals(text, "")) {
                  return name;
               }

               if (text.equals(name)) {
                  return name;
               }

               if (text.length() <= name.length() && name.startsWith(text) && lowestLength > (double)name.length()) {
                  lowestLength = (double)name.length();
                  bestConfig = name;
               }
            }

            return bestConfig;
         }
      }
   }

   public static class_1657 getClosestMatchingPlayer(String text, boolean onlyNonFriends) {
      List<class_742> players = mc.field_1687.method_18456();
      class_1657 bestPlayer = null;
      double lowestLength = Double.MAX_VALUE;

      for(class_1657 player : players) {
         if (!player.equals(mc.field_1724) && (!onlyNonFriends || !FriendManager.INSTANCE.isFriend(player))) {
            if (Objects.equals(text, "")) {
               return player;
            }

            if (text.equals(player.method_5477().getString())) {
               return player;
            }

            if (text.length() <= player.method_5477().getString().length() && player.method_5477().getString().startsWith(text) && lowestLength > (double)player.method_5477().getString().length()) {
               lowestLength = (double)player.method_5477().getString().length();
               bestPlayer = player;
            }
         }
      }

      return bestPlayer;
   }

   public static String getMultiFillArgument(String fix, String argument) {
      String formatted = fix.replaceAll("\\{", "");
      formatted = formatted.replaceAll("}", "");
      String[] fill = formatted.split(",");
      String text = "";
      boolean both = argument == "";
      if (both) {
         text = "<";

         for(int i = 0; i < fill.length; ++i) {
            text = text + fill[i];
            if (i < fill.length - 1) {
               text = text + "|";
            }
         }

         text = text + ">";
      } else {
         for(String toFill : fill) {
            if (toFill.startsWith(argument)) {
               text = toFill;
               break;
            }
         }
      }

      return text;
   }
}
