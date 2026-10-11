package org.itsmahir.pagon.impl.features.modules.combat;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PriorityManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.utils.targeting.TargetUtils;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;

public class AutoWeb extends Module {
   public static AutoWeb INSTANCE;
   Timer timer = new Timer();
   Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Target").withModes("Target", "Self").register(this);
   Value<Number> delay;
   Value<Number> targetRange;
   Value<Boolean> ghost;
   Value<Boolean> rotate;
   Value<Boolean> toggleWhenDone;
   class_1297 target;
   List<class_2338> toPlace;

   public AutoWeb() {
      super("AutoWeb", Feature.Category.Combat);
      this.delay = (new ValueBuilder<Number>()).withDescriptor("Delay").withValue(0).withRange(0, 1000).withPageParent(this.mode).withPage("Target").withAction((set) -> this.timer.setDelay((long)((Number)set.getValue()).intValue())).register(this);
      this.targetRange = (new ValueBuilder<Number>()).withDescriptor("Target Range").withValue((double)5.0F).withRange((double)1.0F, (double)10.0F).withPageParent(this.mode).withPage("Target").register(this);
      this.ghost = (new ValueBuilder<Boolean>()).withDescriptor("Ghost Switch").withValue(false).register(this);
      this.rotate = (new ValueBuilder<Boolean>()).withDescriptor("Rotate").withValue(false).register(this);
      this.toggleWhenDone = (new ValueBuilder<Boolean>()).withDescriptor("Disable").withValue(false).withPageParent(this.mode).withPage("Target").register(this);
      this.toPlace = new ArrayList();
      INSTANCE = this;
   }

   public void onEnable() {
      super.onEnable();
      if (!NullUtils.nullCheck()) {
         this.target = null;
      }
   }

   public void onDisable() {
      super.onDisable();
      if (!NullUtils.nullCheck()) {
         this.target = null;
         this.unlock();
      }
   }

   @SubscribeEvent
   public void onPlayerUpdate(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         boolean offhand = ((class_1799)mc.field_1724.method_31548().field_7544.get(0)).method_7909() == class_1802.field_8786;
         if (((String)this.mode.getValue()).equals("Target")) {
            if (this.timer.isPassed()) {
               this.target = TargetUtils.getTarget(((Number)this.targetRange.getValue()).doubleValue());
               if (this.target == null) {
                  return;
               }

               class_2338 feet = this.target.method_24515();
               if (feet != null && this.canPlaceBlock(feet) && mc.field_1687.method_8320(feet).method_26204() != class_2246.field_10343) {
                  if (!offhand && InventoryUtils.getHotbarItemSlot(class_1802.field_8786) == -1) {
                     return;
                  }

                  if ((Boolean)this.rotate.getValue()) {
                     RotationUtils.doRotate(feet, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue());
                  }

                  this.toPlace.add(feet);
                  if ((Boolean)this.rotate.getValue()) {
                     PriorityManager.INSTANCE.lockUsageLock("AutoWeb");
                  }
               } else {
                  this.unlock();
               }
            } else {
               this.unlock();
            }
         } else if (this.canPlaceBlock(mc.field_1724.method_24515())) {
            RotationUtils.doRotate(mc.field_1724.method_24515(), (Boolean)AntiCheat.INSTANCE.strictDirection.getValue());
            this.toPlace.add(mc.field_1724.method_24515());
         } else {
            this.setEnabled(false);
         }

      }
   }

   @SubscribeEvent
   public void onPlayerUpdate(TickEvent.MovementTickEvent.Post event) {
      if (!NullUtils.nullCheck()) {
         int webSlot = InventoryUtils.getHotbarItemSlot(class_1802.field_8786);
         if (webSlot == -1) {
            this.toPlace.clear();
            if (((String)this.mode.getValue()).equals("Self")) {
               this.setEnabled(false);
            }

         } else {
            int oldSlot = mc.field_1724.method_31548().field_7545;
            boolean switched = false;

            for(class_2338 pos : this.toPlace) {
               if (webSlot != mc.field_1724.method_31548().field_7545) {
                  InventoryUtils.switchToSlot(webSlot);
                  switched = true;
               }

               BlockUtils.placeBlock(pos, BlockUtils.getPlaceableSide(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue()), true);
               if (((String)this.mode.getValue()).equals("Self")) {
                  this.setEnabled(false);
               }
            }

            if (switched) {
               InventoryUtils.switchToSlot(oldSlot);
            }

            this.toPlace.clear();
         }
      }
   }

   public void unlock() {
      if (PriorityManager.INSTANCE.isUsageLocked() && PriorityManager.INSTANCE.usageLockCause.equals("AutoWeb")) {
         PriorityManager.INSTANCE.unlockUsageLock();
      }

   }

   boolean canPlaceBlock(class_2338 pos) {
      boolean allow = true;
      if (!mc.field_1687.method_8320(pos).method_45474()) {
         allow = false;
      }

      if (!BlockUtils.canPlaceWeb(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue())) {
         allow = false;
      }

      return allow;
   }

   public String getHudInfo() {
      if (((String)this.mode.getValue()).equals("Target")) {
         return this.target != null ? this.target.method_5477().getString() : "";
      } else {
         return "Self";
      }
   }

   public String getDescription() {
      return "Automatically places webs on players to prevent them from moving";
   }
}
