package org.itsmahir.pagon.impl.features.modules.misc;

import net.minecraft.class_124;
import net.minecraft.class_1802;
import net.minecraft.class_2848;
import net.minecraft.class_2848.class_2849;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.chat.ChatMessage;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class ChestSwap extends Module {
   Value<Boolean> instaFly = (new ValueBuilder<Boolean>()).withDescriptor("Auto-Fly").withValue(false).register(this);
   Value<Boolean> firework;
   int offgroundTicks;

   public ChestSwap() {
      super("ChestSwap", Feature.Category.Misc);
      this.firework = (new ValueBuilder<Boolean>()).withDescriptor("Firework").withValue(false).withParent(this.instaFly).withParentEnabled(true).register(this);
      this.offgroundTicks = 0;
   }

   public void onEnable() {
      super.onEnable();
      if (!NullUtils.nullCheck()) {
         this.doSwap();
      }
   }

   public void doSwap() {
      if (!PlayerUtils.isElytraEquipped()) {
         int slot = InventoryUtils.getInventoryItemSlot(class_1802.field_8833);
         if (slot != -1) {
            InventoryUtils.swapArmor(2, slot);
            ChatUtils.sendMessage(new ChatMessage("[ChestSwap] Equipping " + String.valueOf(class_124.field_1063) + "Elytra" + String.valueOf(class_124.field_1070) + "!", true, 50016));
            if ((Boolean)this.instaFly.getValue() && !mc.field_1724.method_24828()) {
               PacketManager.INSTANCE.sendPacket(new class_2848(mc.field_1724, class_2849.field_12982));
               mc.field_1724.method_23669();
               if ((Boolean)this.firework.getValue()) {
                  PlayerUtils.doFirework();
               }
            }
         } else {
            ChatUtils.sendMessage(new ChatMessage("[ChestSwap] elytraless dog", true, 50016));
         }
      } else {
         int slot = InventoryUtils.findChestplate();
         if (slot != -1) {
            InventoryUtils.swapArmor(2, slot);
            ChatUtils.sendMessage(new ChatMessage("[ChestSwap] Equipping " + String.valueOf(class_124.field_1075) + "Chestplate" + String.valueOf(class_124.field_1070) + "!", true, 50016));
         } else {
            ChatUtils.sendMessage(new ChatMessage("[ChestSwap] chestplateless dog", true, 50016));
         }
      }

      this.setEnabled(false);
   }

   public String getDescription() {
      return "ChestSwap: swaps ur chestplate with ur elytra";
   }
}
