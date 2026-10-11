package org.itsmahir.pagon.api.utils.players;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_1280;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1657;
import net.minecraft.class_1703;
import net.minecraft.class_1713;
import net.minecraft.class_1743;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1829;
import net.minecraft.class_1835;
import net.minecraft.class_1887;
import net.minecraft.class_1893;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2813;
import net.minecraft.class_2868;
import net.minecraft.class_5134;
import net.minecraft.class_5321;
import net.minecraft.class_6880;
import net.minecraft.class_9285;
import net.minecraft.class_9304;
import net.minecraft.class_9334;
import net.minecraft.class_9362;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.management.RotationManager;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.mixin.accessor.IClientPlayerInteractionManager;
import org.jetbrains.annotations.NotNull;

public class InventoryUtils implements IMinecraft {
   public static final List<Integer> invalidSlots = Arrays.asList(0, 5, 6, 7, 8);
   public static class_1792[] CHESTPLATE_ITEMS;
   static int lastSlot;

   public static int getHotbarItemSlot(class_1792 item) {
      int slot = -1;

      for(int i = 0; i < 9; ++i) {
         if (mc.field_1724.method_31548().method_5438(i).method_7909().equals(item)) {
            slot = i;
            break;
         }
      }

      return slot;
   }

   public static int getNonBlockInHotbar() {
      int slot = -1;

      for(int i = 0; i < 9; ++i) {
         class_1792 item = mc.field_1724.method_31548().method_5438(i).method_7909();
         if (item == null || !(item instanceof class_1747) && !item.method_57347().method_57832(class_9334.field_50075)) {
            slot = i;
            break;
         }
      }

      return slot;
   }

   public static void pickupSlot(int slot) {
      mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, slot, 0, class_1713.field_7790, mc.field_1724);
   }

   public static void swapArmor(int armorSlot, int inSlot) {
      int slot = inSlot;
      if (inSlot < 9) {
         slot = inSlot + 36;
      }

      class_1799 stack = mc.field_1724.method_31548().method_7372(armorSlot);
      armorSlot = 8 - armorSlot;
      pickupSlot(slot);
      boolean rt = !stack.method_7960();
      pickupSlot(armorSlot);
      if (rt) {
         pickupSlot(slot);
      }

   }

   public static void pickArmorSlot(int armorSlot) {
      pickupSlot(8 - armorSlot);
   }

   public static int findShulker() {
      int shulker = -1;

      for(int i = 0; i < 9; ++i) {
         class_1799 stack = mc.field_1724.method_31548().method_5438(i);
         if (stack != class_1799.field_8037 && stack.method_7909() instanceof class_1747) {
            class_2248 block = ((class_1747)stack.method_7909()).method_7711();
            if (BlockUtils.SHULKER_BLOCKS.contains(block)) {
               shulker = i;
               break;
            }
         }
      }

      return shulker;
   }

   public static int findBlockInHotbar(class_2248 bc) {
      int slot = -1;

      for(int i = 0; i < 9; ++i) {
         class_1799 stack = mc.field_1724.method_31548().method_5438(i);
         if (stack != class_1799.field_8037 && stack.method_7909() instanceof class_1747) {
            class_2248 block = ((class_1747)stack.method_7909()).method_7711();
            if (block == bc) {
               slot = i;
               break;
            }
         }
      }

      return slot;
   }

   public static void switchToSlot(boolean silent, int slot) {
      if (silent) {
         switchToSlotGhost(slot);
         mc.field_1724.method_31548().field_7545 = slot;
      } else if (mc.field_1724.method_31548().field_7545 != slot) {
         class_1799[] hotbarCopy = new class_1799[9];

         for(int i = 0; i < 9; ++i) {
            hotbarCopy[i] = mc.field_1724.method_31548().method_5438(i);
         }

         RotationManager.swapData.add(new PreSwapData(hotbarCopy, mc.field_1724.method_31548().field_7545, slot));
         mc.field_1724.method_31548().field_7545 = slot;
      }

   }

   public static int findChestplate() {
      for(class_1792 item : CHESTPLATE_ITEMS) {
         int slot = getInventoryItemSlot(item);
         if (slot != -1) {
            return slot;
         }
      }

      return -1;
   }

   public static int getBestToolSlot(class_2248 block) {
      float maxSpeed = -1.0F;
      int slot = -1;
      class_1799 stackCur = mc.field_1724.method_31548().method_5438(mc.field_1724.method_31548().field_7545);
      float normalDestroySpeed = stackCur.method_7909().method_58404(stackCur, block.method_9564());

      for(int i = 0; i < 9; ++i) {
         class_1799 itemStack = mc.field_1724.method_31548().method_5438(i);
         if (!itemStack.method_7960()) {
            float speed = itemStack.method_7909().method_58404(itemStack, block.method_9564());
            if (speed > maxSpeed) {
               maxSpeed = speed;
               slot = i;
            }
         }
      }

      if (normalDestroySpeed == maxSpeed) {
         return -1;
      } else {
         return slot;
      }
   }

   public static int getHotbarItemSlot2(class_1792 item) {
      int slot = getHotbarItemSlot(item);
      return slot == -1 ? mc.field_1724.method_31548().field_7545 : slot;
   }

   public static int hotbarToInventory(int slot) {
      if (slot == -2) {
         return 45;
      } else {
         return slot > -1 && slot < 9 ? 36 + slot : slot;
      }
   }

   public static void syncItem() {
      ((IClientPlayerInteractionManager)mc.field_1761).doSyncSelectedSlot();
   }

   public static void switchToBypass(int slot, boolean resync) {
      if (slot != -1) {
         if (resync) {
            resyncClick(slot, mc.field_1724.method_31548().field_7545, class_1713.field_7791);
         } else {
            normalPacketClick(slot, mc.field_1724.method_31548().field_7545, class_1713.field_7791);
         }
      }

   }

   public static void resyncClick(int slotId, int mouseButton, class_1713 type) {
      packetInvSwap(slotId, mouseButton, type, new class_1799(class_2246.field_10201, 64), mc.field_1724.field_7512);
   }

   public static void normalPacketClick(int slotId, int mouseButton, class_1713 type) {
      packetInvSwap(slotId, mouseButton, type, mc.field_1724.method_31548().method_5438(slotId), mc.field_1724.field_7512);
   }

   public static void packetInvSwap(int slotId, int mouseButton, class_1713 type, class_1799 stack, class_1703 handler) {
      Int2ObjectArrayMap<class_1799> map = new Int2ObjectArrayMap();
      map.put(mouseButton, handler.method_7611(slotId).method_7677());
      mc.field_1724.field_3944.method_52787(new class_2813(handler.field_7763, handler.method_37421(), slotId, mouseButton, type, stack, map));
   }

   public static void throwSlot(int slot) {
      if (slot != -1) {
         mc.field_1761.method_2906(0, slot, 0, class_1713.field_7795, mc.field_1724);
      }

   }

   public static void switchToSlot(int slot) {
      mc.field_1724.method_31548().field_7545 = slot;
      syncItem();
   }

   public static double getAttackDamage(class_1799 stack) {
      for(class_9285.class_9287 entry : ((class_9285)stack.method_7909().method_57347().method_57829(class_9334.field_49636)).comp_2393()) {
         if (entry.comp_2396().method_60718(class_1792.field_8006)) {
            return entry.comp_2396().comp_2449();
         }
      }

      return (double)0.0F;
   }

   public static float getHitDamage(@NotNull class_1799 weapon, class_1657 ent) {
      if (mc.field_1724 == null) {
         return 0.0F;
      } else {
         float baseDamage = 1.0F;
         class_1792 var4 = weapon.method_7909();
         if (var4 instanceof class_1829) {
            class_1829 swordItem = (class_1829)var4;
            baseDamage = 7.0F;
         }

         var4 = weapon.method_7909();
         if (var4 instanceof class_1743) {
            class_1743 axeItem = (class_1743)var4;
            baseDamage = 9.0F;
         }

         if (weapon.method_7909() instanceof class_9362) {
            baseDamage = 6.0F;
            if (mc.field_1724.field_6017 > 1.5F) {
               baseDamage += mc.field_1724.field_6017 * 3.0F;
            }
         }

         if (mc.field_1724.field_6017 > 0.0F && !(weapon.method_7909() instanceof class_9362)) {
            baseDamage += baseDamage / 2.0F;
         }

         if (mc.field_1724.method_6059(class_1294.field_5910)) {
            int strength = ((class_1293)Objects.requireNonNull(mc.field_1724.method_6112(class_1294.field_5910))).method_5578() + 1;
            baseDamage += (float)(3 * strength);
         }

         baseDamage = class_1280.method_5496(ent, baseDamage, mc.field_1687.method_48963().method_48830(), (float)ent.method_6096(), (float)ent.method_5996(class_5134.field_23725).method_6194());
         return baseDamage;
      }
   }

   public static void getEnchantments(class_1799 itemStack, Object2IntMap<class_6880<class_1887>> enchantments) {
      enchantments.clear();
      if (!itemStack.method_7960()) {
         for(Object2IntMap.Entry<class_6880<class_1887>> entry : itemStack.method_7909() == class_1802.field_8598 ? ((class_9304)itemStack.method_57824(class_9334.field_49643)).method_57539() : itemStack.method_58657().method_57539()) {
            enchantments.put((class_6880)entry.getKey(), entry.getIntValue());
         }
      }

   }

   public static int getEnchantmentLevel(class_1799 itemStack, class_5321<class_1887> enchantment) {
      if (itemStack.method_7960()) {
         return 0;
      } else {
         Object2IntMap<class_6880<class_1887>> itemEnchantments = new Object2IntArrayMap();
         getEnchantments(itemStack, itemEnchantments);
         return getEnchantmentLevel(itemEnchantments, enchantment);
      }
   }

   public static int getEnchantmentLevel(Object2IntMap<class_6880<class_1887>> itemEnchantments, class_5321<class_1887> enchantment) {
      ObjectIterator var2 = Object2IntMaps.fastIterable(itemEnchantments).iterator();

      while(var2.hasNext()) {
         Object2IntMap.Entry<class_6880<class_1887>> entry = (Object2IntMap.Entry)var2.next();
         if (((class_6880)entry.getKey()).method_40225(enchantment)) {
            return entry.getIntValue();
         }
      }

      return 0;
   }

   public static int getSwordSlot() {
      float sharp = 0.0F;
      int slot = -1;
      float bestAxeDmg = 0.0F;
      int bestAxe = -1;

      for(int i = 0; i < 9; ++i) {
         class_1799 stack = mc.field_1724.method_31548().method_5438(i);
         if (stack.method_7909() instanceof class_1835 || stack.method_7909() instanceof class_1743 || stack.method_7909() instanceof class_1829) {
            float sharpness = (float)getEnchantmentLevel(stack, class_1893.field_9118) * 0.5F + 0.5F;
            float dmg = (float)(getAttackDamage(stack) + (double)sharpness);
            if (stack.method_7909() instanceof class_1829) {
               if (dmg > sharp) {
                  sharp = dmg;
                  slot = i;
               }
            } else if (dmg > bestAxeDmg) {
               bestAxeDmg = dmg;
               bestAxe = i;
            }
         }
      }

      if (slot == -1) {
         return bestAxe;
      } else {
         return slot;
      }
   }

   public static void switchToSlot(class_1792 item) {
      mc.field_1724.method_31548().field_7545 = getHotbarItemSlot2(item);
   }

   public static void switchToSlotGhost(int slot) {
      if (slot != -1) {
         class_1799[] hotbarCopy = new class_1799[9];

         for(int i = 0; i < 9; ++i) {
            hotbarCopy[i] = mc.field_1724.method_31548().method_5438(i);
         }

         RotationManager.swapData.add(new PreSwapData(hotbarCopy, RotationManager.INSTANCE.serverSlot, slot));
         PacketManager.INSTANCE.sendPacket(new class_2868(slot));
      }

   }

   public static void switchToSlotGhost(class_1792 item) {
      switchToSlotGhost(getHotbarItemSlot2(item));
   }

   public static int getItemCount(class_1792 item) {
      int count = 0;
      count = mc.field_1724.method_31548().field_7547.stream().filter((itemStack) -> itemStack.method_7909().equals(item)).mapToInt(class_1799::method_7947).sum();
      count += mc.field_1724.method_31548().field_7544.stream().filter((itemStack) -> itemStack.method_7909().equals(item)).mapToInt(class_1799::method_7947).sum();
      return count;
   }

   public static int getItemCount(class_1792 item, List<class_1799> items) {
      int count = 0;
      count = items.stream().filter((itemStack) -> itemStack.method_7909().equals(item)).mapToInt(class_1799::method_7947).sum();
      count += mc.field_1724.method_31548().field_7544.stream().filter((itemStack) -> itemStack.method_7909().equals(item)).mapToInt(class_1799::method_7947).sum();
      return count;
   }

   public static String getArmorPieceName(class_1799 stack) {
      if (stack.method_7909() != class_1802.field_22027 && stack.method_7909() != class_1802.field_8090 && stack.method_7909() != class_1802.field_8805 && stack.method_7909() != class_1802.field_8862 && stack.method_7909() != class_1802.field_8743 && stack.method_7909() != class_1802.field_8283 && stack.method_7909() != class_1802.field_8267) {
         if (stack.method_7909() != class_1802.field_22028 && stack.method_7909() != class_1802.field_8058 && stack.method_7909() != class_1802.field_8678 && stack.method_7909() != class_1802.field_8523 && stack.method_7909() != class_1802.field_8873 && stack.method_7909() != class_1802.field_8577) {
            if (stack.method_7909() != class_1802.field_22029 && stack.method_7909() != class_1802.field_8348 && stack.method_7909() != class_1802.field_8416 && stack.method_7909() != class_1802.field_8396 && stack.method_7909() != class_1802.field_8218 && stack.method_7909() != class_1802.field_8570) {
               return stack.method_7909() != class_1802.field_22030 && stack.method_7909() != class_1802.field_8285 && stack.method_7909() != class_1802.field_8753 && stack.method_7909() != class_1802.field_8660 && stack.method_7909() != class_1802.field_8313 && stack.method_7909() != class_1802.field_8370 ? null : "boots";
            } else {
               return "leggings";
            }
         } else {
            return "chest";
         }
      } else {
         return "helmet";
      }
   }

   public static int getItemSlotSmart(class_1792 item) {
      int slot = -1;

      for(int i = 36; i >= 0; --i) {
         if (mc.field_1724.method_31548().method_5438(i).method_7909().equals(item)) {
            slot = i;
            break;
         }
      }

      return slot;
   }

   public static int getInventoryItemSlot(class_1792 item) {
      int slot = -1;

      for(int i = 36; i >= 0; --i) {
         if (mc.field_1724.method_31548().method_5438(i).method_7909().equals(item)) {
            slot = i;
            break;
         }
      }

      return slot;
   }

   public static void moveItemToOffhand(int iSlot, boolean fast) {
      int slot = iSlot;
      if (iSlot < 9) {
         slot = iSlot + 36;
      }

      if (fast) {
         mc.field_1761.method_2906(0, slot, 40, class_1713.field_7791, mc.field_1724);
      } else {
         int returnSlot = 0;
         if (slot != -1) {
            mc.field_1761.method_2906(0, slot < 9 ? slot + 36 : slot, 0, class_1713.field_7790, mc.field_1724);
            mc.field_1761.method_2906(0, 45, 0, class_1713.field_7790, mc.field_1724);

            for(int i = 0; i < 45; ++i) {
               if (mc.field_1724.method_31548().method_5438(i).method_7960()) {
                  returnSlot = i;
                  break;
               }
            }

            if (returnSlot != -1) {
               mc.field_1761.method_2906(0, returnSlot < 9 ? returnSlot + 36 : returnSlot, 0, class_1713.field_7790, mc.field_1724);
            }

         }
      }
   }

   public static boolean moveItemToOffhand(int slot, int stage) {
      int returnSlot = 0;
      if (slot == -1) {
         return false;
      } else {
         if (stage == 1) {
            mc.field_1761.method_2906(0, slot < 9 ? slot + 36 : slot, 0, class_1713.field_7790, mc.field_1724);
         }

         if (stage == 2) {
            mc.field_1761.method_2906(0, 45, 0, class_1713.field_7790, mc.field_1724);
         }

         if (stage != 3) {
            return false;
         } else {
            for(int i = 0; i < 45; ++i) {
               if (mc.field_1724.method_31548().method_5438(i).method_7960()) {
                  returnSlot = i;
                  break;
               }
            }

            if (returnSlot != -1) {
               mc.field_1761.method_2906(0, returnSlot < 9 ? returnSlot + 36 : returnSlot, 0, class_1713.field_7790, mc.field_1724);
            }

            return true;
         }
      }
   }

   public static void moveItemToOffhand(class_1792 item, boolean fast) {
      int slot = getInventoryItemSlot(item);
      if (lastSlot != -1 && mc.field_1724.method_31548().method_5438(lastSlot).method_7909().equals(item)) {
         slot = lastSlot;
      }

      if (slot != -1) {
         lastSlot = slot;
         moveItemToOffhand(slot, fast);
      }

   }

   public static void swap(int invSlot, int hotbarSlot) {
      mc.field_1761.method_2906(0, invSlot, hotbarSlot, class_1713.field_7791, mc.field_1724);
   }

   public static boolean moveItemToOffhand(class_1792 item, int stage) {
      int slot = getInventoryItemSlot(item);
      return slot != -1 ? moveItemToOffhand(slot, stage) : false;
   }

   public static void moveItem(int slot, int slotOut) {
      mc.field_1761.method_2906(0, slot < 9 ? slot + 36 : slot, 0, class_1713.field_7790, mc.field_1724);
      mc.field_1761.method_2906(0, slotOut < 9 ? slotOut + 36 : slotOut, 0, class_1713.field_7790, mc.field_1724);
      mc.field_1761.method_2906(0, slot < 9 ? slot + 36 : slot, 0, class_1713.field_7790, mc.field_1724);
   }

   public static void moveItem(class_1792 item, int slot) {
      moveItem(getInventoryItemSlot(item), slot);
   }

   static {
      CHESTPLATE_ITEMS = new class_1792[]{class_1802.field_22028, class_1802.field_8058, class_1802.field_8873, class_1802.field_8523, class_1802.field_8678, class_1802.field_8577};
      lastSlot = -1;
   }

   public static class PreSwapData {
      private final class_1799[] preHotbar;
      private final int starting;
      private final int swapTo;
      private Timer clearTime;

      public PreSwapData(class_1799[] preHotbar, int start, int swapTo) {
         this.preHotbar = preHotbar;
         this.starting = start;
         this.swapTo = swapTo;
      }

      public void beginClear() {
         this.clearTime = new Timer();
         this.clearTime.resetDelay();
      }

      public boolean isPassedClearTime() {
         return this.clearTime != null && this.clearTime.isPassed(300L);
      }

      public class_1799 getPreHolding(int i) {
         return this.preHotbar[i];
      }

      public int getStarting() {
         return this.starting;
      }

      public int getSlot() {
         return this.swapTo;
      }
   }
}
