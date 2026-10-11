package org.itsmahir.pagon.impl;

import baritone.api.BaritoneAPI;
import java.util.logging.Logger;
import net.fabricmc.api.ModInitializer;
import org.itsmahir.pagon.api.event.eventbus.EventManager;
import org.itsmahir.pagon.api.management.SavableManager;
import org.spongepowered.asm.launch.MixinBootstrap;

public class PagonMod implements ModInitializer {
   public static Logger LOGGER = Logger.getLogger("pagon");
   public static long START_TIME = System.currentTimeMillis();
   public static String NAME = "Pagon";
   public static final String VERSION = "1.2.4";
   public static final String HASH = "c6d99055e5013a7f";
   public static String NAME_UNICODE = "❅";
   public static EventManager EVENT_BUS;
   public static boolean BARITONE_AVAILABLE;

   public void onInitialize() {
      BARITONE_AVAILABLE = exists("baritone.api.BaritoneAPI");
      LOGGER.info(NAME_UNICODE + "Pagon (1.2.4)");
      Register.INSTANCE = new Register();
      Register.INSTANCE.registerAll();
      SavableManager.INSTANCE.load();
      PagonMod.ShutdownHook.setup();
      System.out.println(this.getClass().getClassLoader());
      System.out.println(MixinBootstrap.class.getClassLoader());
   }

   public static boolean isBaritonePaused() {
      if (!BARITONE_AVAILABLE) {
         return false;
      } else {
         try {
            Object primary = BaritoneAPI.getProvider().getPrimaryBaritone();
            if (primary == null) {
               return false;
            } else {
               Object pathBehavior = primary.getClass().getMethod("getPathingBehavior").invoke(primary);
               return (Boolean)pathBehavior.getClass().getMethod("isPathing").invoke(pathBehavior);
            }
         } catch (Throwable var2) {
            return false;
         }
      }
   }

   private static boolean exists(String name) {
      try {
         Class.forName(name);
         return true;
      } catch (ClassNotFoundException var2) {
         return false;
      }
   }

   static class ShutdownHook extends Thread {
      public static void setup() {
         Runtime.getRuntime().addShutdownHook(new ShutdownHook());
      }

      public void run() {
         super.run();

         try {
            SavableManager.INSTANCE.save();
         } catch (Exception e) {
            e.printStackTrace();
         }

      }
   }
}
