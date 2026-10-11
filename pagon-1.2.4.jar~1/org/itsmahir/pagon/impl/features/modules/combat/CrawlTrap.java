package org.itsmahir.pagon.impl.features.modules.combat;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
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

public class CrawlTrap extends Module {
   Timer timer = new Timer();
   Value<Number> range = (new ValueBuilder<Number>()).withDescriptor("Range").withValue(6).withRange((double)1.0F, (double)6.0F).withPlaces(1).register(this);
   Value<Number> delay = (new ValueBuilder<Number>()).withDescriptor("Delay").withValue(0).withRange(0, 1000).withAction((set) -> this.timer.setDelay((long)((Number)set.getValue()).intValue())).register(this);
   Value<Number> targetRange = (new ValueBuilder<Number>()).withDescriptor("Target Range").withValue((double)5.0F).withRange((double)1.0F, (double)10.0F).register(this);
   Value<Boolean> rotate = (new ValueBuilder<Boolean>()).withDescriptor("Rotate").withValue(true).register(this);
   class_1297 target;
   List<class_2338> toPlace = new ArrayList();

   public CrawlTrap() {
      super("CrawlTrap", Feature.Category.Combat);
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         this.target = TargetUtils.getTarget((double)((Number)this.targetRange.getValue()).floatValue());
         if (this.target != null) {
            this.toPlace.clear();
            if (!AutoBreak.INSTANCE.didAction) {
               if (!mc.field_1724.method_6115()) {
                  if (InventoryUtils.getHotbarItemSlot(class_1802.field_8281) != -1) {
                     if (this.timer.isPassed()) {
                        class_2338 pos = this.target.method_24515().method_10084();
                        if (BlockUtils.canPlaceBlockPlatformer(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue()) && pos.method_19770(mc.field_1724.method_33571()) < (double)MathUtil.square(((Number)this.range.getValue()).floatValue())) {
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
               int blockSlot = InventoryUtils.getHotbarItemSlot(class_1802.field_8281);
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

                     BlockUtils.placeBlock(pos, BlockUtils.getPlaceableSide(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue()), true);
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
      return "It automatically places obsidian on players to trap them in a crawl position";
   }
}
