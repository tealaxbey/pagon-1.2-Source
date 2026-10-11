package org.itsmahir.pagon.impl.features.commands;

import net.minecraft.class_1297;
import net.minecraft.class_1690;
import net.minecraft.class_243;
import net.minecraft.class_2833;
import org.itsmahir.pagon.api.command.Command;
import org.itsmahir.pagon.api.management.PacketManager;

public class BoatKillCommand extends Command {
   public BoatKillCommand() {
      super("BoatKill", "kills you with boats", new String[]{"boatkill"});
   }

   public void run(String[] args) {
      class_1297 var3 = mc.field_1724.method_5854();
      if (var3 instanceof class_1690 boat) {
         class_243 var7 = boat.method_19538();
         boat.method_33574(var7.method_1031((double)0.0F, 0.05, (double)0.0F));
         class_2833 groundPacket = new class_2833(boat);
         boat.method_33574(var7.method_1031((double)0.0F, (double)20.0F, (double)0.0F));
         class_2833 skyPacket = new class_2833(boat);
         boat.method_33574(var7);

         for(int i = 0; i < 20; ++i) {
            PacketManager.INSTANCE.sendPacket(skyPacket);
            PacketManager.INSTANCE.sendPacket(groundPacket);
         }

         PacketManager.INSTANCE.sendPacket(new class_2833(boat));
      }
   }
}
