package org.itsmahir.pagon.impl.features.modules.misc;

import net.minecraft.class_2246;
import net.minecraft.class_2680;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.LivingEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.world.MineUtils;
import org.itsmahir.pagon.mixin.accessor.IClientPlayerInteractionManager;

public class AutoTool extends Module {
   public AutoTool() {
      super("AutoTool", Feature.Category.Misc);
   }

   @SubscribeEvent
   public void onBreakBlock(LivingEvent.AttackBlock event) {
      if (!NullUtils.nullCheck()) {
         class_2680 state = event.getState();
         if (state != null) {
            if (state.method_26204() != class_2246.field_10124 && MineUtils.canBreak(event.getPos())) {
               int slot = InventoryUtils.getBestToolSlot(state.method_26204());
               if (slot != -1 && mc.field_1724.method_31548().field_7545 != slot) {
                  mc.field_1724.method_31548().field_7545 = InventoryUtils.getBestToolSlot(state.method_26204());
                  ((IClientPlayerInteractionManager)mc.field_1761).doSyncSelectedSlot();
               }
            }

         }
      }
   }

   public String getDescription() {
      return "AutoTool: Swaps to the best tool you have to mine a block";
   }
}
