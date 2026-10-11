package org.itsmahir.pagon.api.utils;

import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;

public class Pair<K, V> {
   private final K key;
   private final V value;

   public Pair(K key, V value) {
      this.key = key;
      this.value = value;
   }

   public K key() {
      return this.key;
   }

   public V value() {
      return this.value;
   }

   public static class BlockPair extends Pair<class_2338, class_2350> {
      public BlockPair(class_2338 pos, class_2350 face) {
         super(pos, face);
      }
   }

   public static class BreakPair extends Pair<class_2338, class_2350> {
      public boolean reset;

      public BreakPair(class_2338 pos, class_2350 face) {
         super(pos, face);
         this.reset = false;
      }

      public BreakPair(class_2338 pos, class_2350 face, boolean reset) {
         super(pos, face);
         this.reset = reset;
      }
   }

   public static class BlockPairTime extends Pair<class_2338, Long> {
      public BlockPairTime(class_2338 pos) {
         super(pos, System.currentTimeMillis());
      }
   }

   public static class BoxPair extends Pair<class_238, Long> {
      public BoxPair(class_238 box) {
         super(box, System.currentTimeMillis());
      }
   }
}
