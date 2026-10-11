package org.itsmahir.pagon.impl.features.modules.combat;

import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_243;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.FriendManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class SemiAutoMace extends Module {
   public static SemiAutoMace INSTANCE;
   private boolean attacked = false;
   private final Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Vanilla").withModes("Vanilla", "Rage").register(this);
   private final Value<Number> attackRange = (new ValueBuilder<Number>()).withDescriptor("AttackRange").withValue((double)4.5F).withRange((double)1.0F, (double)8.0F).withPlaces(1).register(this);
   private final Value<Number> packetsPerTick = (new ValueBuilder<Number>()).withDescriptor("PacketsPerTick").withValue(3).withRange(1, 20).withPlaces(0).register(this);
   private final Value<Boolean> silentSwitch = (new ValueBuilder<Boolean>()).withDescriptor("SilentSwitch").withValue(true).register(this);
   private final Value<Boolean> rotate = (new ValueBuilder<Boolean>()).withDescriptor("Rotate").withValue(true).register(this);
   private final Value<Boolean> autoElytra = (new ValueBuilder<Boolean>()).withDescriptor("AutoElytra").withValue(true).register(this);

   public SemiAutoMace() {
      super("SemiAutoMace", Feature.Category.Combat);
      INSTANCE = this;
   }

   public String getDescription() {
      return "Automatically attacks players with a mace when they are in range. §cNO AURA MODULE NEEDED!";
   }

   public String getHudInfo() {
      return this.mode.getValue();
   }

   public void onEnable() {
      super.onEnable();
      this.attacked = false;
   }

   public void onDisable() {
      super.onDisable();
      if (this.attacked) {
         if (((String)this.mode.getValue()).equals("Vanilla")) {
            this.equipElytra();
         }

         this.attacked = false;
      }

   }

   @SubscribeEvent
   public void onPlayerUpdate(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (!mc.field_1724.method_29504() && !mc.field_1724.method_7325()) {
            class_1657 target = this.findTarget();
            if (target == null) {
               if (this.attacked) {
                  if (((String)this.mode.getValue()).equals("Vanilla")) {
                     this.equipElytra();
                  }

                  this.attacked = false;
               }

            } else {
               double range = ((Number)this.attackRange.getValue()).doubleValue();
               if (mc.field_1724.method_5858(target) > range * range) {
                  if (this.attacked) {
                     if (((String)this.mode.getValue()).equals("Vanilla")) {
                        this.equipElytra();
                     }

                     this.attacked = false;
                  }

               } else {
                  if (!this.attacked) {
                     if (((String)this.mode.getValue()).equals("Vanilla")) {
                        int chestplateSlot = InventoryUtils.findChestplate();
                        if (chestplateSlot != -1) {
                           InventoryUtils.swapArmor(2, chestplateSlot);
                        }
                     }

                     this.attacked = true;
                  }

                  if ((Boolean)this.rotate.getValue()) {
                     this.rotateToward(target);
                  }

                  int maceSlot = this.findInHotbar(class_1802.field_49814);
                  if (maceSlot >= 0) {
                     int prev = mc.field_1724.method_31548().field_7545;
                     mc.field_1724.method_31548().field_7545 = maceSlot;
                     int packets = ((Number)this.packetsPerTick.getValue()).intValue();

                     for(int i = 0; i < packets; ++i) {
                        mc.field_1761.method_2918(mc.field_1724, target);
                        mc.field_1724.method_6104(class_1268.field_5808);
                     }

                     if ((Boolean)this.silentSwitch.getValue()) {
                        mc.field_1724.method_31548().field_7545 = prev;
                     }

                  }
               }
            }
         }
      }
   }

   private void rotateToward(class_1657 target) {
      class_243 diff = target.method_33571().method_1020(mc.field_1724.method_33571());
      float yaw = (float)Math.toDegrees(Math.atan2(-diff.field_1352, diff.field_1350));
      float pitch = (float)(-Math.toDegrees(Math.atan2(diff.field_1351, Math.sqrt(diff.field_1352 * diff.field_1352 + diff.field_1350 * diff.field_1350))));
      RotationUtils.doSilentRotate(new float[]{yaw, pitch});
   }

   private class_1657 findTarget() {
      if (mc.field_1687 == null) {
         return null;
      } else {
         float r = ((Number)this.attackRange.getValue()).floatValue();
         class_1657 best = null;
         double bestDist = Double.MAX_VALUE;

         for(class_1657 p : mc.field_1687.method_18456()) {
            if (p != mc.field_1724 && p.method_5805() && !p.method_7325() && !FriendManager.INSTANCE.isFriend(p)) {
               double d = mc.field_1724.method_5858(p);
               if (d <= (double)(r * r) && d < bestDist) {
                  bestDist = d;
                  best = p;
               }
            }
         }

         return best;
      }
   }

   private int findInHotbar(class_1792 item) {
      class_1661 inv = mc.field_1724.method_31548();

      for(int i = 0; i < 9; ++i) {
         if (inv.method_5438(i).method_31574(item)) {
            return i;
         }
      }

      return -1;
   }

   private void equipElytra() {
      int elytraSlot = InventoryUtils.getInventoryItemSlot(class_1802.field_8833);
      if (elytraSlot != -1) {
         InventoryUtils.swapArmor(2, elytraSlot);
      }

   }
}
