package org.itsmahir.pagon.impl.features.commands;

import net.minecraft.class_3532;
import org.itsmahir.pagon.api.command.Command;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;
import org.itsmahir.pagon.api.utils.math.MathUtil;

public class MathCommand extends Command {
   public MathCommand() {
      super("Math", "does itsmahirs ap physics", new String[]{"math"});
   }

   public void run(String[] args) {
      try {
         double x1 = Double.parseDouble(args[1]);
         double o1 = (double)MathUtil.rad((float)Double.parseDouble(args[2]));
         double x2 = Double.parseDouble(args[3]);
         double o2 = (double)MathUtil.rad((float)Double.parseDouble(args[4]));
         double time = Double.parseDouble(args[5]);
         double x1x = x1 * (double)class_3532.method_15362((float)o1);
         double x1y = x1 * (double)class_3532.method_15374((float)o1);
         double x2x = x2 * (double)class_3532.method_15362((float)o2);
         double x2y = x2 * (double)class_3532.method_15374((float)o2);
         double changeX = x2x - x1x;
         double changeY = x2y - x1y;
         ChatUtils.sendMessage("Change X: " + changeX);
         ChatUtils.sendMessage("Change Y: " + changeY);
         double var10000 = MathUtil.magnitudeOfAcceleration(changeX, changeY, time);
         ChatUtils.sendMessage("magoA: " + var10000);
         ChatUtils.sendMessage("Angle: " + MathUtil.fix(Math.toDegrees(Math.atan(changeY / changeX))));
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   public String[] getFill(String[] args) {
      return new String[]{"x1", "o1", "x2", "o2", "time"};
   }
}
