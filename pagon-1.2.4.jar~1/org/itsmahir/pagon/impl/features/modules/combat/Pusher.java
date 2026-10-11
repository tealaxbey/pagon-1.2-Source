package org.itsmahir.pagon.impl.features.modules.combat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.utils.targeting.TargetUtils;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;
import org.itsmahir.pagon.impl.features.modules.misc.autobreak.AutoBreak;

public class Pusher extends Module {
   Timer timer = new Timer();
   Value<Number> range = (new ValueBuilder<Number>()).withDescriptor("Range").withValue(6).withRange((double)1.0F, (double)6.0F).withPlaces(1).register(this);
   public Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Torch").withModes("Torch", "Block").register(this);
   Value<Number> delay = (new ValueBuilder<Number>()).withDescriptor("Delay").withValue(0).withRange(0, 1000).withAction((set) -> this.timer.setDelay((long)((Number)set.getValue()).intValue())).register(this);
   Value<Number> targetRange = (new ValueBuilder<Number>()).withDescriptor("Target Range").withValue((double)5.0F).withRange((double)1.0F, (double)10.0F).register(this);
   Value<Boolean> rotate = (new ValueBuilder<Boolean>()).withDescriptor("Rotate").withValue(true).register(this);
   HoleCampBlock currentBlock;
   class_1297 target;
   class_2338 toPlace;
   int slot;
   boolean didPiston;

   public Pusher() {
      super("Pusher", Feature.Category.Combat);
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (!AutoBreak.INSTANCE.didAction) {
            if ((this.target = TargetUtils.getTarget(((Number)this.targetRange.getValue()).doubleValue())) != null) {
               int var10000;
               switch ((String)this.mode.getValue()) {
                  case "Torch" -> var10000 = InventoryUtils.getHotbarItemSlot(class_1802.field_8530);
                  case "Block" -> var10000 = InventoryUtils.getHotbarItemSlot(class_1802.field_8793);
                  default -> var10000 = -1;
               }

               int redstoneSlot = var10000;
               int pistonSlot = InventoryUtils.getHotbarItemSlot(class_1802.field_8249);
               if (pistonSlot == -1) {
                  pistonSlot = InventoryUtils.getHotbarItemSlot(class_1802.field_8105);
               }

               if (redstoneSlot != -1 && pistonSlot != -1) {
                  if (this.currentBlock == null) {
                     this.currentBlock = this.getBestPos(this.target);
                  }

                  if (this.currentBlock != null) {
                     class_2338 pistonPos = this.currentBlock.getPos();
                     class_2338 redstonePos = pistonPos.method_10093(this.currentBlock.getRedstoneDirection());
                     if (!this.didPiston) {
                        this.slot = pistonSlot;
                        this.toPlace = pistonPos;
                        if ((Boolean)this.rotate.getValue()) {
                           RotationUtils.doRotate(pistonPos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue());
                        }
                     } else {
                        this.slot = redstoneSlot;
                        this.toPlace = redstonePos;
                     }
                  }

               } else {
                  ChatUtils.sendMessage("[Pusher] You don't have the required items in your hotbar!");
                  this.toggle();
               }
            }
         }
      }
   }

   @SubscribeEvent
   public void onPlayerUpdate(TickEvent.MovementTickEvent.Post event) {
      if (!NullUtils.nullCheck()) {
         if (this.currentBlock != null) {
            if (this.target != null) {
               if (this.toPlace != null) {
                  if (!this.didPiston) {
                     if (this.currentBlock.getDirection() == class_2350.field_11043) {
                        RotationUtils.packetRotate(180.0F, 0.0F);
                     } else if (this.currentBlock.getDirection() == class_2350.field_11035) {
                        RotationUtils.packetRotate(0.0F, 0.0F);
                     } else if (this.currentBlock.getDirection() == class_2350.field_11039) {
                        RotationUtils.packetRotate(90.0F, 0.0F);
                     } else if (this.currentBlock.getDirection() == class_2350.field_11034) {
                        RotationUtils.packetRotate(-90.0F, 0.0F);
                     }
                  }

                  int oldSlot = mc.field_1724.method_31548().field_7545;
                  InventoryUtils.switchToSlot(this.slot);
                  BlockUtils.placeBlock(this.toPlace, BlockUtils.getPlaceableSide(this.toPlace, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue()), false);
                  if (oldSlot != mc.field_1724.method_31548().field_7545) {
                     InventoryUtils.switchToSlot(oldSlot);
                  }

                  if (!this.didPiston) {
                     this.didPiston = true;
                  } else {
                     this.toggle();
                  }
               }

               this.toPlace = null;
            }
         }
      }
   }

   public void onEnable() {
      super.onEnable();
      if (!NullUtils.nullCheck()) {
         this.currentBlock = null;
         this.didPiston = false;
         this.toPlace = null;
      }
   }

   public HoleCampBlock getBestPos(class_1297 player) {
      List<HoleCampBlock> posList = this.getAvailablePos(player);
      posList.sort(Comparator.comparingDouble(PlayerUtils::distanceTo));
      return posList.isEmpty() ? null : (HoleCampBlock)posList.get(0);
   }

   public List<HoleCampBlock> getAvailablePos(class_1297 player) {
      List<HoleCampBlock> positions = new ArrayList();
      class_2338 pos = player.method_24515().method_10084();

      for(class_2350 direction : class_2350.values()) {
         if (!direction.method_10166().method_10178()) {
            class_2338 pistonPos = pos.method_10093(direction);
            if (this.canPistonPos(pistonPos, direction)) {
               for(class_2350 redstoneDir : class_2350.values()) {
                  if (!redstoneDir.equals(direction.method_10153())) {
                     class_2338 redstonePos = pistonPos.method_10093(redstoneDir);
                     if (BlockUtils.canPlaceBlock(redstonePos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue(), pistonPos) && BlockUtils.isInRange(redstonePos, (double)((Number)this.range.getValue()).floatValue())) {
                        positions.add(new HoleCampBlock(pistonPos, direction, redstoneDir));
                     }
                  }
               }
            }
         }
      }

      return positions;
   }

   public boolean canPistonPos(class_2338 pos, class_2350 offset) {
      if (!BlockUtils.canPlaceBlock(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue())) {
         return false;
      } else if (!BlockUtils.isInRange(pos, (double)((Number)this.range.getValue()).floatValue())) {
         return false;
      } else {
         class_2338 opposite = pos.method_10079(offset.method_10153(), 2);
         if (!mc.field_1687.method_8320(opposite).method_26215()) {
            return false;
         } else {
            boolean allow = false;

            for(class_2350 dir : class_2350.values()) {
               if (!dir.equals(offset.method_10153())) {
                  class_2338 redstonePos = pos.method_10093(dir);
                  if (BlockUtils.canPlaceBlock(redstonePos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue(), pos) && BlockUtils.isInRange(redstonePos, (double)((Number)this.range.getValue()).floatValue())) {
                     allow = true;
                     break;
                  }
               }
            }

            return allow;
         }
      }
   }

   public String getDescription() {
      return "Kicks people out of there safeholes using pistons";
   }

   public static enum PlacePhase {
      PISTON,
      REDSTONE;

      // $FF: synthetic method
      private static PlacePhase[] $values() {
         return new PlacePhase[]{PISTON, REDSTONE};
      }
   }

   public class HoleCampBlock {
      class_2338 pos;
      class_2350 direction;
      class_2350 redstoneDirection;

      public HoleCampBlock(class_2338 pos, class_2350 direction, class_2350 redstoneDirection) {
         this.pos = pos;
         this.direction = direction;
         this.redstoneDirection = redstoneDirection;
      }

      public class_2338 getPos() {
         return this.pos;
      }

      public class_2350 getDirection() {
         return this.direction;
      }

      public class_2350 getRedstoneDirection() {
         return this.redstoneDirection;
      }

      public void setPos(class_2338 pos) {
         this.pos = pos;
      }

      public void setDirection(class_2350 direction) {
         this.direction = direction;
      }

      public void setRedstoneDirection(class_2350 redstoneDirection) {
         this.redstoneDirection = redstoneDirection;
      }
   }
}
