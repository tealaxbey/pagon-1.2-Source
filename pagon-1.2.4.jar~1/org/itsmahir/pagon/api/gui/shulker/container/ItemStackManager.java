package org.itsmahir.pagon.api.gui.shulker.container;

import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_9296;
import net.minecraft.class_9334;

public class ItemStackManager {
   private static Map<ItemStackGrouper, Integer> groupItemStacks(Iterable<class_1799> itemIterable) {
      return (Map)StreamSupport.stream(itemIterable.spliterator(), false).collect(Collectors.groupingBy(ItemStackGrouper::new, Collectors.summingInt(class_1799::method_7947)));
   }

   public static class_1799 getDisplayStackFromIterable(Iterable<class_1799> itemIterable) {
      return (class_1799)groupItemStacks(itemIterable).entrySet().stream().filter((entry) -> (Integer)entry.getValue() >= 1).max(Entry.comparingByValue()).map(Map.Entry::getKey).map((grouper) -> grouper.itemStack).orElse((Object)null);
   }

   private static String getSkullName(class_1799 itemStack) {
      class_9296 profileComponent = (class_9296)itemStack.method_57824(class_9334.field_49617);
      return profileComponent == null ? null : (String)profileComponent.comp_2410().orElse((Object)null);
   }

   private static class ItemStackGrouper {
      public final class_1799 itemStack;
      public String itemString;

      public ItemStackGrouper(class_1799 itemStack) {
         this.itemStack = itemStack;
         this.setItemString();
      }

      private void setItemString() {
         this.itemString = this.itemStack.method_7909().method_7876();
         if (this.itemStack.method_31574(class_1802.field_8575)) {
            String skullName = ItemStackManager.getSkullName(this.itemStack);
            if (skullName != null) {
               this.itemString = this.itemString + skullName;
            }
         }

      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (obj != null && this.getClass() == obj.getClass()) {
            ItemStackGrouper otherGrouper = (ItemStackGrouper)obj;
            return this.itemString.equals(otherGrouper.itemString) && this.itemStack.method_7909().equals(otherGrouper.itemStack.method_7909());
         } else {
            return false;
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.itemString});
      }
   }
}
