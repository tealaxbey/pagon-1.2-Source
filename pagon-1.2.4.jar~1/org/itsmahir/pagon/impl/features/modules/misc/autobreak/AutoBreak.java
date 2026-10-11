package org.itsmahir.pagon.impl.features.modules.misc.autobreak;

import net.minecraft.class_2338;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.Timer;

public class AutoBreak extends Module {
   public static AutoBreak INSTANCE;
   public boolean didAction = false;
   public Timer mineDownTimer = new Timer();

   public AutoBreak() {
      super("AutoBreak", Feature.Category.Misc);
      INSTANCE = this;
   }

   public boolean isInstantMining(class_2338 pos) {
      return false;
   }

   public boolean isAboutToBreak(class_2338 pos) {
      return false;
   }

   public boolean isAnyMining(class_2338 pos) {
      return false;
   }

   public String getDescription() {
      return "Automatically breaks blocks";
   }
}
