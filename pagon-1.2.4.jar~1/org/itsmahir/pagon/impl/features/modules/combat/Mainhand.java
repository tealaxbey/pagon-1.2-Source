package org.itsmahir.pagon.impl.features.modules.combat;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1268;
import net.minecraft.class_1511;
import net.minecraft.class_1802;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_490;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.render.ScreenEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.world.CrystalUtil;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.movement.NoSlow;

public class Mainhand extends Module {
   Value<Boolean> hurtTime = (new ValueBuilder<Boolean>()).withDescriptor("Hurt").withValue(false).register(this);
   Value<Number> delay;
   Value<Boolean> swapBack;
   Value<Number> health;
   Value<Boolean> autoRefill;
   Value<Number> refillSlot;
   Value<Number> speed;
   Value<Boolean> onlyLethal;
   Value<Number> maxSelfDmg;
   Value<Boolean> pause;
   Value<Boolean> inventory;
   Value<Boolean> openTotem;
   Timer crystalTimer;
   Timer swapBackTimer;
   Timer refillTimer;
   boolean lastHurtTime;
   int oldSlot;

   public Mainhand() {
      super("Mainhand", Feature.Category.Combat);
      this.delay = (new ValueBuilder<Number>()).withDescriptor("Delay").withValue(0).withRange(0, 300).withParent(this.hurtTime).withParentEnabled(true).register(this);
      this.swapBack = (new ValueBuilder<Boolean>()).withDescriptor("Swap Back").withValue(false).register(this);
      this.health = (new ValueBuilder<Number>()).withDescriptor("Health").withValue(15).withPlaces(1).withRange(0, 36).register(this);
      this.autoRefill = (new ValueBuilder<Boolean>()).withDescriptor("Refill").withValue(false).register(this);
      this.refillSlot = (new ValueBuilder<Number>()).withDescriptor("Slot").withValue(1).withRange(1, 9).withPlaces(0).withParent(this.autoRefill).withParentEnabled(true).register(this);
      this.speed = (new ValueBuilder<Number>()).withDescriptor("Speed").withValue(0).withRange(0, 300).withParent(this.autoRefill).withParentEnabled(true).register(this);
      this.onlyLethal = (new ValueBuilder<Boolean>()).withDescriptor("Only-Lethal").withValue(false).register(this);
      this.maxSelfDmg = (new ValueBuilder<Number>()).withDescriptor("Max Damage").withValue(4).withRange(0, 36).register(this);
      this.pause = (new ValueBuilder<Boolean>()).withDescriptor("Pause").withValue(false).register(this);
      this.inventory = (new ValueBuilder<Boolean>()).withDescriptor("Inventory").withValue(false).register(this);
      this.openTotem = (new ValueBuilder<Boolean>()).withDescriptor("Open-Totem").withValue(false).register(this);
      this.crystalTimer = new Timer();
      this.swapBackTimer = new Timer();
      this.refillTimer = new Timer();
      this.lastHurtTime = true;
      this.oldSlot = -1;
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.PlayerTickEvent.Post event) {
      if (!NullUtils.nullCheck()) {
         if ((Boolean)this.pause.getValue() && mc.field_1724.method_6115() && mc.field_1724.method_6058().equals(class_1268.field_5808)) {
            this.oldSlot = -1;
         } else if ((Boolean)this.inventory.getValue() || !NoSlow.INSTANCE.canInvMove()) {
            this.crystalTimer.setDelay(((Number)this.delay.getValue()).longValue());
            this.swapBackTimer.setDelay(250L);
            this.refillTimer.setDelay(((Number)this.speed.getValue()).longValue());
            int totemSlot = InventoryUtils.getHotbarItemSlot(class_1802.field_8288);
            this.doRefill();
            boolean curHurtTime = mc.field_1724.field_6235 != 0;
            if (((Number)this.delay.getValue()).intValue() != 0 && !this.lastHurtTime && curHurtTime) {
               this.crystalTimer.resetDelay();
            }

            this.lastHurtTime = curHurtTime;
            if (!(Boolean)this.hurtTime.getValue() || ((Number)this.delay.getValue()).intValue() == 0 || this.crystalTimer.isPassed()) {
               boolean lethal = true;
               if ((Boolean)this.onlyLethal.getValue()) {
                  lethal = false;
                  List<class_1511> crystals = this.getNearByCrystals();
                  ArrayList<class_243> crystalsPos = new ArrayList();
                  crystals.forEach((e) -> crystalsPos.add(e.method_19538()));

                  for(class_243 pos : crystalsPos) {
                     double damage = (double)CrystalUtil.calculateDamage(mc.field_1724, pos, false, false);
                     if (damage > (double)((Number)this.maxSelfDmg.getValue()).floatValue() || damage + (double)2.0F > (double)(mc.field_1724.method_6032() + mc.field_1724.method_6067())) {
                        lethal = true;
                        break;
                     }
                  }
               }

               if (totemSlot != -1 && (curHurtTime || !(Boolean)this.hurtTime.getValue()) && mc.field_1724.method_6032() + mc.field_1724.method_6067() < (float)((Number)this.health.getValue()).longValue() && lethal) {
                  if (mc.field_1724.method_6047().method_7909() != class_1802.field_8288) {
                     this.oldSlot = mc.field_1724.method_31548().field_7545;
                  }

                  InventoryUtils.switchToSlot(totemSlot);
                  this.swapBackTimer.resetDelay();
               } else if ((Boolean)this.swapBack.getValue() && this.swapBackTimer.isPassed() && this.oldSlot != -1) {
                  InventoryUtils.switchToSlot(this.oldSlot);
                  this.oldSlot = -1;
               } else if (this.oldSlot != -1 && totemSlot != -1) {
                  if (mc.field_1724.method_6047().method_7909() != class_1802.field_8288) {
                     this.oldSlot = mc.field_1724.method_31548().field_7545;
                  }

                  InventoryUtils.switchToSlot(totemSlot);
               }

            }
         }
      }
   }

   private List<class_1511> getNearByCrystals() {
      class_243 pos = mc.field_1724.method_19538();
      return mc.field_1687.method_8390(class_1511.class, new class_238(pos.method_1031((double)-4.0F, (double)-4.0F, (double)-4.0F), pos.method_1031((double)4.0F, (double)4.0F, (double)4.0F)), (a) -> true);
   }

   public void doRefill() {
      if ((Boolean)this.autoRefill.getValue()) {
         if (this.refillTimer.isPassed()) {
            int slot = ((Number)this.refillSlot.getValue()).intValue() - 1;
            if (mc.field_1724.method_31548().method_5438(slot).method_7909() != class_1802.field_8288) {
               int totemSlot = InventoryUtils.getInventoryItemSlot(class_1802.field_8288);
               if (totemSlot != -1) {
                  if (totemSlot < 9) {
                     totemSlot += 36;
                  }

                  InventoryUtils.swap(totemSlot, slot);
                  this.refillTimer.resetDelay();
               }
            }

         }
      }
   }

   @SubscribeEvent
   public void onScreenEvent(ScreenEvent.SetScreen event) {
      if (event.getGuiScreen() instanceof class_490 && (Boolean)this.openTotem.getValue()) {
         int totemSlot = InventoryUtils.getHotbarItemSlot(class_1802.field_8288);
         if (totemSlot != -1) {
            InventoryUtils.switchToSlot(totemSlot);
         }
      }

   }

   public String getDescription() {
      return "Mainhands a totem when you can get dtapped";
   }
}
