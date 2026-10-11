package org.itsmahir.pagon.impl.features.modules.combat;

import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_476;
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

public class Offhand extends Module {
   Value<Boolean> instaSwap = (new ValueBuilder<Boolean>()).withDescriptor("Fast").withValue(false).register(this);
   Value<String> item = (new ValueBuilder<String>()).withDescriptor("Item").withValue("Totem").withModes("Totem", "Gapple", "Crystal").register(this);
   Value<Number> totemHealth = (new ValueBuilder<Number>()).withDescriptor("Totem Health").withValue(15).withPlaces(1).withRange(0, 36).register(this);
   Value<Boolean> swordGap = (new ValueBuilder<Boolean>()).withDescriptor("Sword Gap").withValue(false).register(this);
   Value<Number> totemSwordGap;
   Value<Number> delay;
   Timer swapTimer;
   int stage;
   int currentSlot;

   public Offhand() {
      super("Offhand", Feature.Category.Combat);
      this.totemSwordGap = (new ValueBuilder<Number>()).withDescriptor("Sword Health").withValue(15).withRange(0, 36).withPlaces(1).withParent(this.swordGap).withParentEnabled(true).register(this);
      this.delay = (new ValueBuilder<Number>()).withDescriptor("Delay").withValue(0).withRange(0, 300).register(this);
      this.swapTimer = new Timer();
      this.currentSlot = -1;
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         this.swapTimer.setDelay(((Number)this.delay.getValue()).longValue());
         if (!(mc.field_1755 instanceof class_476) || mc.field_1755 instanceof class_490) {
            class_1792 item = this.getItem();
            if (mc.field_1724.method_6079().method_7909() != item && this.swapTimer.isPassed()) {
               InventoryUtils.moveItemToOffhand(this.getItem(), (Boolean)this.instaSwap.getValue());
               this.swapTimer.resetDelay();
            }

         }
      }
   }

   public class_1792 getItem() {
      if ((Boolean)this.swordGap.getValue() && (mc.field_1724.method_31548().method_7391().method_7909().equals(class_1802.field_8802) || mc.field_1724.method_31548().method_7391().method_7909().equals(class_1802.field_22022)) && mc.field_1690.field_1904.method_1434() && mc.field_1755 == null && (double)(mc.field_1724.method_6032() + mc.field_1724.method_6067()) > ((Number)this.totemSwordGap.getValue()).doubleValue()) {
         return class_1802.field_8367;
      } else {
         return (double)(mc.field_1724.method_6032() + mc.field_1724.method_6067()) > ((Number)this.totemHealth.getValue()).doubleValue() ? this.getSelectedItem() : class_1802.field_8288;
      }
   }

   public class_1792 getSelectedItem() {
      class_1792 i = null;
      if (((String)this.item.getValue()).equals("Totem")) {
         i = class_1802.field_8288;
      } else if (((String)this.item.getValue()).equals("Crystal")) {
         i = class_1802.field_8301;
      } else if (((String)this.item.getValue()).equals("Gapple")) {
         i = class_1802.field_8367;
      }

      return i;
   }

   public String getHudInfo() {
      return NullUtils.nullCheck() ? "" : "" + InventoryUtils.getItemCount(class_1802.field_8288);
   }

   public String getDescription() {
      return "Automatically puts whatever item into your offhand when needed";
   }
}
