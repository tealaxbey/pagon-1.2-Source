package org.itsmahir.pagon.impl.features.modules.combat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PriorityManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.utils.targeting.TargetUtils;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;
import org.itsmahir.pagon.impl.features.modules.misc.autobreak.AutoBreak;

public class AutoTrap extends Module {
   Timer timer = new Timer();
   Value<Number> delay = (new ValueBuilder<Number>()).withDescriptor("Delay").withValue(0).withRange(0, 1000).withAction((set) -> this.timer.setDelay((long)((Number)set.getValue()).intValue())).register(this);
   Value<String> trapMode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Top").withModes("Full", "City", "Top", "None").register(this);
   Value<Number> range = (new ValueBuilder<Number>()).withDescriptor("Range").withValue(6).withRange((double)1.0F, (double)6.0F).withPlaces(1).register(this);
   Value<Number> blocksPerTick = (new ValueBuilder<Number>()).withDescriptor("Blocks").withValue(1).withRange(1, 10).withPlaces(0).register(this);
   Value<Number> targetRange = (new ValueBuilder<Number>()).withDescriptor("Target Range").withValue((double)5.0F).withRange((double)1.0F, (double)10.0F).register(this);
   Value<Boolean> disable = (new ValueBuilder<Boolean>()).withDescriptor("Disable").withValue(false).register(this);
   Value<Boolean> rotate = (new ValueBuilder<Boolean>()).withDescriptor("Rotate", "rotate").withValue(true).register(this);
   Value<Boolean> echest = (new ValueBuilder<Boolean>()).withDescriptor("EChest").withValue(false).register(this);
   class_1297 target;
   List<class_2338> toPlace = new ArrayList();
   private class_2338 startPos = null;
   public class_2338[] fullOffsets = new class_2338[]{new class_2338(1, 0, 0), new class_2338(0, 0, 1), new class_2338(-1, 0, 0), new class_2338(0, 0, -1), new class_2338(-1, 1, 0), new class_2338(1, 1, 0), new class_2338(0, 1, 1), new class_2338(0, 1, -1)};
   public class_2338[] cityOffsets = new class_2338[]{new class_2338(1, 1, 0), new class_2338(0, 1, 1), new class_2338(-1, 1, 0), new class_2338(0, 1, -1)};
   class_2338[] surroundOffsets = new class_2338[]{new class_2338(1, 0, 0), new class_2338(0, 0, 1), new class_2338(-1, 0, 0), new class_2338(0, 0, -1), new class_2338(0, 0, 0)};

   public AutoTrap() {
      super("AutoTrap", Feature.Category.Combat);
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (this.startPos == null) {
            this.toggle();
         } else {
            this.target = TargetUtils.getTarget(((Number)this.targetRange.getValue()).doubleValue());
            if (this.target == null) {
               if (PriorityManager.INSTANCE.isUsageLocked() && PriorityManager.INSTANCE.usageLockCause.equals("AutoTrap")) {
                  PriorityManager.INSTANCE.unlockUsageLock();
               }

            } else if (!this.startPos.equals(PlayerUtils.getPlayerPos()) && (Boolean)this.disable.getValue()) {
               this.setEnabled(false);
            } else {
               this.toPlace.clear();
               int blocksInTick = 0;
               if (InventoryUtils.getHotbarItemSlot((Boolean)this.echest.getValue() ? class_1802.field_8466 : class_1802.field_8281) != -1) {
                  if (!((String)this.trapMode.getValue()).equalsIgnoreCase("None")) {
                     class_2338[] offsets = this.getTrapOffsets();

                     for(class_2338 pos : offsets) {
                        if (this.canPlaceBlock(pos) && (!AutoBreak.INSTANCE.isAnyMining(pos) || !BlockUtils.isInterceptedByCrystalThatIsntHit(pos.method_10084()))) {
                           if ((Boolean)this.rotate.getValue()) {
                              RotationUtils.doRotate(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue());
                           }

                           if (!this.timer.isPassed()) {
                              break;
                           }

                           this.toPlace.add(pos);
                           this.timer.resetDelay();
                           ++blocksInTick;
                           PriorityManager.INSTANCE.lockUsageLock("AutoTrap");
                           if (blocksInTick >= ((Number)this.blocksPerTick.getValue()).intValue()) {
                              break;
                           }
                        }
                     }
                  }

                  if (blocksInTick == 0 && PriorityManager.INSTANCE.isUsageLocked() && PriorityManager.INSTANCE.usageLockCause.equals("AutoTrap")) {
                     PriorityManager.INSTANCE.unlockUsageLock();
                  }

               }
            }
         }
      }
   }

   @SubscribeEvent
   public void onPlayerUpdate(TickEvent.MovementTickEvent.Post event) {
      if (!NullUtils.nullCheck()) {
         int blockSlot = InventoryUtils.getHotbarItemSlot((Boolean)this.echest.getValue() ? class_1802.field_8466 : class_1802.field_8281);
         int oldSlot = mc.field_1724.method_31548().field_7545;
         boolean switched = false;

         for(class_2338 pos : this.toPlace) {
            if (blockSlot != mc.field_1724.method_31548().field_7545) {
               InventoryUtils.switchToSlot(blockSlot);
               switched = true;
            }

            BlockUtils.placeBlock(pos, BlockUtils.getPlaceableSide(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue()), false);
         }

         if (switched) {
            InventoryUtils.switchToSlot(oldSlot);
         }

         this.toPlace.clear();
      }
   }

   boolean canPlaceBlock(class_2338 pos) {
      boolean allow = true;
      if (!BlockUtils.isReplaceable(pos)) {
         return false;
      } else if (BlockUtils.getPlaceableSide(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue()) == null) {
         return false;
      } else {
         double distance = mc.field_1724.method_33571().method_1025(new class_243((double)pos.method_10263() + (double)0.5F, (double)pos.method_10264() + (double)0.5F, (double)pos.method_10260() + (double)0.5F));
         return distance > MathUtil.square(((Number)this.range.getValue()).doubleValue()) ? false : allow;
      }
   }

   class_2338[] offsetBlocks(class_2338[] toOffset, class_2338 offsetPlace) {
      class_2338[] offsets = new class_2338[toOffset.length];
      int index = 0;

      for(class_2338 blockPos : toOffset) {
         offsets[index] = offsetPlace.method_10081(blockPos);
         ++index;
      }

      return offsets;
   }

   public class_2338[] getTrapOffsets() {
      ArrayList<class_2338> toPlace = new ArrayList();
      switch ((String)this.trapMode.getValue()) {
         case "Full" -> toPlace.addAll(Arrays.asList(this.offsetBlocks(this.fullOffsets, PlayerUtils.getPos(this.target))));
         case "City" -> toPlace.addAll(Arrays.asList(this.offsetBlocks(this.cityOffsets, PlayerUtils.getPos(this.target))));
         case "Top" -> {
            return this.getObbyToHead(PlayerUtils.getPos(this.target));
         }
      }

      toPlace.addAll(Arrays.asList(this.getObbyToHead(PlayerUtils.getPos(this.target))));
      return (class_2338[])toPlace.toArray(new class_2338[0]);
   }

   public class_2338[] getObbyToHead(class_2338 feet) {
      ArrayList<class_2338> obbyToHead = new ArrayList();
      class_2338 head = feet.method_10081(new class_2338(0, 1, 0));
      if (!BlockUtils.isReplaceable(head.method_10084())) {
         return new class_2338[0];
      } else if (BlockUtils.canPlaceBlock(head.method_10084(), (Boolean)AntiCheat.INSTANCE.strictDirection.getValue())) {
         obbyToHead.add(head.method_10084());
         class_2338[] blocks = new class_2338[obbyToHead.size()];
         return (class_2338[])obbyToHead.toArray(blocks);
      } else {
         for(class_2350 direction : class_2350.values()) {
            if (!direction.method_10166().method_10178() && this.verifyHeadTrapDirection(direction, head)) {
               if (BlockUtils.canPlaceBlock(head.method_10093(direction), (Boolean)AntiCheat.INSTANCE.strictDirection.getValue())) {
                  obbyToHead.add(head.method_10093(direction));
               }

               if (BlockUtils.canPlaceBlock(head.method_10093(direction).method_10084(), (Boolean)AntiCheat.INSTANCE.strictDirection.getValue())) {
                  obbyToHead.add(head.method_10093(direction).method_10084());
               }

               if (BlockUtils.canPlaceBlock(head.method_10084(), (Boolean)AntiCheat.INSTANCE.strictDirection.getValue())) {
                  obbyToHead.add(head.method_10084());
               }
               break;
            }
         }

         class_2338[] blocks = new class_2338[obbyToHead.size()];
         return (class_2338[])obbyToHead.toArray(blocks);
      }
   }

   public boolean verifyHeadTrapDirection(class_2350 direction, class_2338 head) {
      class_2338 headOffset = head.method_10093(direction);
      if (!BlockUtils.canPlaceBlock(headOffset, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue(), true)) {
         return false;
      } else if (!BlockUtils.canPlaceBlock(headOffset.method_10084(), (Boolean)AntiCheat.INSTANCE.strictDirection.getValue(), headOffset)) {
         return false;
      } else {
         return BlockUtils.canPlaceBlock(head.method_10084(), (Boolean)AntiCheat.INSTANCE.strictDirection.getValue(), headOffset.method_10084());
      }
   }

   public class_2338 getSurroundedBlock(class_2338 feet) {
      for(class_2338 offset : this.surroundOffsets) {
         class_2680 blockState = mc.field_1687.method_8320(feet.method_10081(offset));
         if (!blockState.method_45474()) {
            return feet.method_10081(offset);
         }
      }

      return null;
   }

   public void onDisable() {
      super.onDisable();
      if (PriorityManager.INSTANCE.isUsageLocked() && PriorityManager.INSTANCE.usageLockCause.equals("AutoTrap")) {
         PriorityManager.INSTANCE.unlockUsageLock();
      }

      if (!NullUtils.nullCheck()) {
         ;
      }
   }

   public void onEnable() {
      super.onEnable();
      if (!NullUtils.nullCheck()) {
         this.startPos = PlayerUtils.getPlayerPos();
      }
   }

   public String getDescription() {
      return "Fully traps enemies with blocks";
   }
}
