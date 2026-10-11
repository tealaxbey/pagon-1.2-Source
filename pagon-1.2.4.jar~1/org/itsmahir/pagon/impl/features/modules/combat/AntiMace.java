package org.itsmahir.pagon.impl.features.modules.combat;

import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2846;
import net.minecraft.class_2846.class_2847;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;
import org.itsmahir.pagon.impl.features.modules.exploit.AirPlace;

public class AntiMace extends Module {
   private final Value<Number> range = (new ValueBuilder<Number>()).withDescriptor("Range").withValue((double)6.0F).withRange((double)1.0F, (double)10.0F).withPlaces(1).register(this);
   private final Value<Boolean> rotate = (new ValueBuilder<Boolean>()).withDescriptor("Rotate").withValue(true).register(this);
   private final Value<Boolean> airPlace = (new ValueBuilder<Boolean>()).withDescriptor("AirPlace").withValue(false).register(this);

   public AntiMace() {
      super("AntiMace", Feature.Category.Combat);
   }

   @SubscribeEvent
   public void onTick(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         class_1657 target = this.findTarget(((Number)this.range.getValue()).doubleValue());
         if (target != null && target.method_6128() && !target.method_24828()) {
            int slot = InventoryUtils.getHotbarItemSlot(class_1802.field_8281);
            if (slot == -1) {
               slot = InventoryUtils.getHotbarItemSlot(class_1802.field_8466);
            }

            if (slot != -1) {
               class_2338 placePos = class_2338.method_49637(target.method_23317(), target.method_23318() - (double)1.0F, target.method_23321());
               if (BlockUtils.isReplaceable(placePos)) {
                  int oldSlot = mc.field_1724.method_31548().field_7545;
                  InventoryUtils.switchToSlot(slot);
                  if ((Boolean)this.airPlace.getValue() && BlockUtils.getPlaceableSide(placePos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue()) == null) {
                     if ((Boolean)this.rotate.getValue()) {
                        RotationUtils.doRotate(placePos, class_2350.field_11033);
                     }

                     if ((Boolean)AirPlace.INSTANCE.grim.getValue()) {
                        PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12969, class_2338.field_10980, class_2350.field_11036));
                     }

                     BlockUtils.placeBlock(placePos, class_2350.field_11033, (Boolean)AirPlace.INSTANCE.grim.getValue() ? class_1268.field_5810 : class_1268.field_5808, false);
                     if ((Boolean)AirPlace.INSTANCE.grim.getValue()) {
                        PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12969, class_2338.field_10980, class_2350.field_11036));
                     }
                  } else {
                     if (BlockUtils.getPlaceableSide(placePos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue()) == null) {
                        InventoryUtils.switchToSlot(oldSlot);
                        return;
                     }

                     if ((Boolean)this.rotate.getValue()) {
                        RotationUtils.doRotate(placePos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue());
                     }

                     BlockUtils.placeBlock(placePos, BlockUtils.getPlaceableSide(placePos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue()), false);
                  }

                  InventoryUtils.switchToSlot(oldSlot);
               }
            }
         }
      }
   }

   private class_1657 findTarget(double range) {
      if (mc.field_1687 == null) {
         return null;
      } else {
         class_1657 best = null;
         double bestDist = Double.MAX_VALUE;

         for(class_1657 player : mc.field_1687.method_18456()) {
            if (player != mc.field_1724 && !player.method_29504() && !player.method_7325()) {
               double dist = (double)mc.field_1724.method_5739(player);
               if (!(dist > range) && dist < bestDist) {
                  bestDist = dist;
                  best = player;
               }
            }
         }

         return best;
      }
   }

   public String getDescription() {
      return "Places obsidian under gliding targets to block mace smash";
   }
}
