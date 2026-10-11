package org.itsmahir.pagon.impl.features.modules.combat;

import net.minecraft.class_1713;
import net.minecraft.class_1738;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1893;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class AutoArmor extends Module {
   public static AutoArmor INSTANCE;
   Value<Number> delay = (new ValueBuilder<Number>()).withDescriptor("Delay", "delay").withValue(100).withRange(1, 1000).register(this);
   Value<Boolean> pantsBlastPrio = (new ValueBuilder<Boolean>()).withDescriptor("BlastPriority").withValue(false).register(this);
   Timer delayTimer = new Timer();

   public AutoArmor() {
      super("AutoArmor", Feature.Category.Combat);
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.ClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         for(int i = 3; i >= 0 && (!((class_1799)mc.field_1724.method_31548().field_7548.get(i)).method_7960() || !this.equipArmor(i)); --i) {
         }

      }
   }

   private boolean equipArmor(int slot) {
      ArmorType armorType = this.getArmorTypeFromSlot(slot);
      int bestSlot = -1;
      int bestRating = -1;

      for(int i = 0; i < 35; ++i) {
         class_1792 item = mc.field_1724.method_31548().method_5438(i).method_7909();
         if (item instanceof class_1738 && this.getTypeFromItem(item) == armorType) {
            int damageReduction = ((class_1738)item).method_7687();
            if (this.getTypeFromItem(item) == AutoArmor.ArmorType.PANTS && (Boolean)this.pantsBlastPrio.getValue()) {
               if (InventoryUtils.getEnchantmentLevel(mc.field_1724.method_31548().method_5438(i), class_1893.field_9107) > 0) {
                  bestSlot = i;
                  bestRating = damageReduction;
               }
            } else if (damageReduction >= bestRating) {
               bestSlot = i;
               bestRating = damageReduction;
            }
         }
      }

      if (bestSlot != -1 && bestRating != -1 && this.delayTimer.isPassed(((Number)this.delay.getValue()).longValue())) {
         if (bestSlot < 9) {
            bestSlot = InventoryUtils.hotbarToInventory(bestSlot);
         }

         mc.field_1761.method_2906(0, bestSlot, 0, class_1713.field_7794, mc.field_1724);
         this.delayTimer.resetDelay();
         return true;
      } else {
         return false;
      }
   }

   private ArmorType getTypeFromItem(class_1792 item) {
      if (!class_1802.field_22027.equals(item) && !class_1802.field_8805.equals(item) && !class_1802.field_8862.equals(item) && !class_1802.field_8743.equals(item) && !class_1802.field_8283.equals(item) && !class_1802.field_8267.equals(item)) {
         if (!class_1802.field_22028.equals(item) && !class_1802.field_8058.equals(item) && !class_1802.field_8678.equals(item) && !class_1802.field_8523.equals(item) && !class_1802.field_8873.equals(item) && !class_1802.field_8577.equals(item)) {
            if (!class_1802.field_22029.equals(item) && !class_1802.field_8348.equals(item) && !class_1802.field_8416.equals(item) && !class_1802.field_8396.equals(item) && !class_1802.field_8218.equals(item) && !class_1802.field_8570.equals(item)) {
               return !class_1802.field_22030.equals(item) && !class_1802.field_8285.equals(item) && !class_1802.field_8753.equals(item) && !class_1802.field_8660.equals(item) && !class_1802.field_8313.equals(item) && !class_1802.field_8370.equals(item) ? null : AutoArmor.ArmorType.BOOTS;
            } else {
               return AutoArmor.ArmorType.PANTS;
            }
         } else {
            return AutoArmor.ArmorType.CHESTPLATE;
         }
      } else {
         return AutoArmor.ArmorType.HELMET;
      }
   }

   private ArmorType getArmorTypeFromSlot(int slot) {
      switch (slot) {
         case 0 -> {
            return AutoArmor.ArmorType.BOOTS;
         }
         case 1 -> {
            return AutoArmor.ArmorType.PANTS;
         }
         case 2 -> {
            return AutoArmor.ArmorType.CHESTPLATE;
         }
         case 3 -> {
            return AutoArmor.ArmorType.HELMET;
         }
         default -> {
            return null;
         }
      }
   }

   private int getSlotByType(ArmorType type) {
      switch (type.ordinal()) {
         case 0 -> {
            return 5;
         }
         case 1 -> {
            return 6;
         }
         case 2 -> {
            return 7;
         }
         case 3 -> {
            return 8;
         }
         default -> {
            return -1;
         }
      }
   }

   public String getDescription() {
      return "Equips armor automatically";
   }

   private static enum ArmorType {
      HELMET,
      CHESTPLATE,
      PANTS,
      BOOTS;

      // $FF: synthetic method
      private static ArmorType[] $values() {
         return new ArmorType[]{HELMET, CHESTPLATE, PANTS, BOOTS};
      }
   }
}
