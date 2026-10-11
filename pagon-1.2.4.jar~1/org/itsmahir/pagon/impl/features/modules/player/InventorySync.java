package org.itsmahir.pagon.impl.features.modules.player;

import net.minecraft.class_1799;
import net.minecraft.class_2596;
import net.minecraft.class_2653;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.RotationManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class InventorySync extends Module {
   public static InventorySync INSTANCE;
   public Value<Boolean> force = (new ValueBuilder<Boolean>()).withDescriptor("No-Verify").withValue(false).withParentEnabled(true).register(this);

   public InventorySync() {
      super("InventorySync", Feature.Category.Player);
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Receive event) {
      if (!NullUtils.nullCheck()) {
         if (!event.isBundled()) {
            if ((Boolean)this.force.getValue()) {
               class_2596 var3 = event.getPacket();
               if (var3 instanceof class_2653) {
                  class_2653 packet = (class_2653)var3;
                  int slot = packet.method_11450() - 36;
                  if (slot < 0 || slot > 8) {
                     return;
                  }

                  if (packet.method_11449().method_7960()) {
                     return;
                  }

                  for(InventoryUtils.PreSwapData data : RotationManager.swapData) {
                     if (data.getSlot() == slot || data.getStarting() == slot) {
                        class_1799 preStack = data.getPreHolding(slot);
                        if (!this.isEqual(preStack, packet.method_11449())) {
                           event.setCancelled(true);
                           break;
                        }
                     }
                  }
               }
            }
         } else if (event.getPacket() instanceof class_2653) {
            event.setCancelled(true);
         }

      }
   }

   private boolean isEqual(class_1799 stack1, class_1799 stack2) {
      return stack1.method_7909().equals(stack2.method_7909()) && stack1.method_7964().equals(stack2.method_7964());
   }

   public String getDescription() {
      return "InventorySync: experimental module u probably should not use this ";
   }
}
