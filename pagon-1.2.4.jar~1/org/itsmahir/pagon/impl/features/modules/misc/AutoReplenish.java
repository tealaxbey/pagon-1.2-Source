package org.itsmahir.pagon.impl.features.modules.misc;

import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_490;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class AutoReplenish extends Module {
   Timer timer = new Timer();
   Value<Number> percent = (new ValueBuilder<Number>()).withDescriptor("Percent").withValue(32).withRange(0, 100).withPlaces(0).register(this);
   Value<Number> delay = (new ValueBuilder<Number>()).withDescriptor("Delay").withValue(200).withRange(0, 500).withAction((set) -> this.timer.setDelay(((Number)set.getValue()).longValue())).register(this);
   class_1792[] cache = new class_1792[9];

   public AutoReplenish() {
      super("AutoReplenish", Feature.Category.Misc);
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (((Number)this.percent.getValue()).intValue() == 0) {
            if (mc.field_1755 != null) {
               for(int i = 0; i < 9; ++i) {
                  this.cache[i] = mc.field_1724.method_31548().method_5438(i).method_7909();
               }

               return;
            }

            if (mc.field_1724.method_29504()) {
               this.cache = new class_1792[9];
               return;
            }

            int index = 0;
            if (this.timer.isPassed()) {
               for(class_1792 item : this.cache) {
                  if (item != null && !item.equals(class_1802.field_8162) && mc.field_1724.method_31548().method_5438(index).method_7960()) {
                     try {
                        int slot = this.getSlot(item);
                        if (slot != -1) {
                           InventoryUtils.moveItem(slot, index);
                           this.timer.resetDelay();
                           return;
                        }
                     } catch (Exception var8) {
                     }
                  }

                  ++index;
               }
            }

            for(int i = 0; i < 9; ++i) {
               this.cache[i] = mc.field_1724.method_31548().method_5438(i).method_7909();
            }
         } else {
            if (mc.field_1755 != null && !(mc.field_1755 instanceof class_490)) {
               return;
            }

            if (this.timer.isPassed()) {
               for(int i = 0; i < 9; ++i) {
                  class_1799 stack = mc.field_1724.method_31548().method_5438(i);
                  if (!stack.method_7960() && stack.method_7946()) {
                     double stackPercent = (double)((float)stack.method_7947() / (float)stack.method_7914() * 100.0F);
                     if ((stack.method_7947() == 1 || stackPercent <= (double)Math.max(((Number)this.percent.getValue()).floatValue(), 5.0F)) && this.replenishStack(stack, i)) {
                        return;
                     }
                  }
               }
            }
         }

      }
   }

   int getSlot(class_1792 item) {
      int slot = -1;

      for(int i = 45; i > 9; --i) {
         if (mc.field_1724.method_31548().method_5438(i).method_7909().equals(item)) {
            slot = i;
            break;
         }
      }

      return slot;
   }

   private boolean replenishStack(class_1799 item, int hotbarSlot) {
      int total = item.method_7947();

      for(int i = 9; i < 36; ++i) {
         class_1799 stack = mc.field_1724.method_31548().method_5438(i);
         if (stack.method_7964().equals(item.method_7964())) {
            class_1792 var8 = stack.method_7909();
            if (var8 instanceof class_1747) {
               class_1747 blockItem = (class_1747)var8;
               var8 = item.method_7909();
               if (!(var8 instanceof class_1747)) {
                  continue;
               }

               class_1747 blockItem1 = (class_1747)var8;
               if (blockItem.method_7711() != blockItem1.method_7711()) {
                  continue;
               }
            }

            if (stack.method_7909() == item.method_7909() && total < stack.method_7914()) {
               InventoryUtils.pickupSlot(i);
               InventoryUtils.pickupSlot(hotbarSlot + 36);
               if (!mc.field_1724.field_7512.method_34255().method_7960()) {
                  InventoryUtils.pickupSlot(i);
               }

               this.timer.resetDelay();
               return true;
            }
         }
      }

      return false;
   }

   public String getDescription() {
      return "AutoReplenish: Refills items in your hotbar when you run out of them";
   }
}
