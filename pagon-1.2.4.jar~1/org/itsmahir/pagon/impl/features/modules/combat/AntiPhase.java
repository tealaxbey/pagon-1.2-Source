package org.itsmahir.pagon.impl.features.modules.combat;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1533;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2824;
import net.minecraft.class_2879;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.utils.targeting.TargetUtils;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;
import org.itsmahir.pagon.impl.features.modules.misc.autobreak.AutoBreak;

public class AntiPhase extends Module {
   Timer timer = new Timer();
   Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Scaffolding").withModes("Scaffolding", "Frames").register(this);
   Value<Number> range = (new ValueBuilder<Number>()).withDescriptor("Range").withValue(6).withRange((double)1.0F, (double)6.0F).withPlaces(1).register(this);
   Value<Number> delay = (new ValueBuilder<Number>()).withDescriptor("Delay").withValue(0).withRange(0, 1000).withAction((set) -> this.timer.setDelay((long)((Number)set.getValue()).intValue())).register(this);
   Value<Number> targetRange = (new ValueBuilder<Number>()).withDescriptor("Target Range").withValue((double)5.0F).withRange((double)1.0F, (double)10.0F).register(this);
   Value<Boolean> rotate = (new ValueBuilder<Boolean>()).withDescriptor("Rotate").withValue(true).register(this);
   class_1533 placeNextTick = null;
   int oldSlotTick = -1;
   class_1297 target;
   List<class_2338> toPlace = new ArrayList();

   public AntiPhase() {
      super("AntiPhase", Feature.Category.Combat);
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         this.target = TargetUtils.getTarget((double)((Number)this.targetRange.getValue()).floatValue());
         if (this.target != null) {
            if (this.placeNextTick != null) {
               if (mc.field_1724.method_31548().method_7391().method_7909().equals(class_1802.field_8143)) {
                  PacketManager.INSTANCE.sendPacket(class_2824.method_34207(this.placeNextTick, mc.field_1724.method_5715(), class_1268.field_5808));
                  PacketManager.INSTANCE.sendPacket(new class_2879(class_1268.field_5808));
                  InventoryUtils.switchToSlot(this.oldSlotTick);
               }

               this.placeNextTick = null;
            }

            this.toPlace.clear();
            if (!AutoBreak.INSTANCE.didAction) {
               if (!mc.field_1724.method_6115()) {
                  if (InventoryUtils.getHotbarItemSlot(this.getItem()) != -1) {
                     if (this.timer.isPassed()) {
                        class_2338 pos = this.target.method_24515();
                        if (((String)this.mode.getValue()).equals("Frames")) {
                           pos = pos.method_10074();
                        }

                        if (pos.method_19770(mc.field_1724.method_33571()) < (double)MathUtil.square(((Number)this.range.getValue()).floatValue())) {
                           if (((String)this.mode.getValue()).equals("Scaffolding") && !BlockUtils.canPlaceBlockIgnore(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue())) {
                              return;
                           }

                           if (((String)this.mode.getValue()).equals("Frames") && !BlockUtils.canPlaceItemFrame(pos, class_2350.field_11036, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue())) {
                              class_1533 entity = BlockUtils.getItemFrame(pos, class_2350.field_11036);
                              if (entity != null && entity.method_6940().method_7960()) {
                                 int oldSlot = mc.field_1724.method_31548().field_7545;
                                 InventoryUtils.switchToSlot(this.getItem());
                                 this.placeNextTick = entity;
                                 this.oldSlotTick = oldSlot;
                              }

                              return;
                           }

                           this.toPlace.add(pos);
                           this.timer.resetDelay();
                           if (!(Boolean)AntiCheat.INSTANCE.grimRotations.getValue()) {
                              this.doRotate(pos);
                           }
                        }
                     }

                  }
               }
            }
         }
      }
   }

   @SubscribeEvent
   public void onPlayerUpdate(TickEvent.AfterClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         if (!AutoBreak.INSTANCE.didAction) {
            if (!mc.field_1724.method_6115()) {
               int blockSlot = InventoryUtils.getHotbarItemSlot(this.getItem());
               int oldSlot = mc.field_1724.method_31548().field_7545;
               boolean switched = false;
               if (blockSlot != -1) {
                  for(class_2338 pos : this.toPlace) {
                     if (blockSlot != mc.field_1724.method_31548().field_7545) {
                        InventoryUtils.switchToSlot(blockSlot);
                        switched = true;
                     }

                     if ((Boolean)this.rotate.getValue() && (Boolean)AntiCheat.INSTANCE.grimRotations.getValue()) {
                        RotationUtils.doSilentRotate(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue());
                     }

                     if (((String)this.mode.getValue()).equals("Frames")) {
                        BlockUtils.placeFrame(pos, class_2350.field_11036, class_1268.field_5808, true, true);
                     } else {
                        BlockUtils.placeBlock(pos, BlockUtils.getPlaceableSide(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue()), true);
                     }
                  }

                  if (!this.toPlace.isEmpty()) {
                     RotationUtils.silentSync();
                  }

                  if (switched) {
                     InventoryUtils.switchToSlot(oldSlot);
                  }

                  this.toPlace.clear();
               }
            }
         }
      }
   }

   public class_1792 getItem() {
      switch ((String)this.mode.getValue()) {
         case "Scaffolding" -> {
            return class_1802.field_16482;
         }
         case "Frames" -> {
            return class_1802.field_8143;
         }
         default -> {
            return class_1802.field_16482;
         }
      }
   }

   public void doRotate(class_2338 pos) {
      if ((Boolean)this.rotate.getValue()) {
         float[] rots = RotationUtils.getBlockRotations(pos, BlockUtils.getPlaceableSide(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue()));
         if (rots != null) {
            RotationUtils.setRotation(rots);
         }

      }
   }

   public void onDisable() {
      super.onDisable();
      if (!NullUtils.nullCheck()) {
         ;
      }
   }

   public void onEnable() {
      super.onEnable();
      if (!NullUtils.nullCheck()) {
         ;
      }
   }

   public String getDescription() {
      return "Prevents players from phasing by placing blocks or item frames in the block they are in";
   }
}
