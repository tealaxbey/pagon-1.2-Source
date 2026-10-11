package org.itsmahir.pagon.impl.features.modules.combat;

import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.utils.targeting.TargetUtils;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;

public class AutoAnvil extends Module {
   Timer timer = new Timer();
   Value<Number> delay = (new ValueBuilder<Number>()).withDescriptor("Delay").withValue(250).withRange(250, 1500).withAction((set) -> this.timer.setDelay(((Number)set.getValue()).longValue())).register(this);
   Value<Boolean> rotate = (new ValueBuilder<Boolean>()).withDescriptor("Rotate").withValue(true).register(this);
   class_1297 target;

   public AutoAnvil() {
      super("AutoAnvil", Feature.Category.Combat);
   }

   @SubscribeEvent
   public void onUpdatePre(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         this.target = TargetUtils.getTarget((double)100.0F);
         if (this.target != null) {
            int AnvilSlot = InventoryUtils.getHotbarItemSlot(class_1802.field_8782);
            if (AnvilSlot == -1) {
               this.setEnabled(false);
            } else {
               if (PlayerUtils.getHighestPlaceableAnvilPos((class_1657)this.target) != null && this.timer.isPassed()) {
                  class_2338 pos = PlayerUtils.getHighestPlaceableAnvilPos((class_1657)this.target);
                  class_2350 direction;
                  if ((direction = BlockUtils.getPlaceableSide(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue())) != null) {
                     InventoryUtils.switchToSlot(AnvilSlot);
                     this.doRotate(pos);
                     BlockUtils.placeBlock(pos, direction, false);
                     this.timer.resetDelay();
                  }
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

   public String getDescription() {
      return "Automatically places anvils on top of players to suffocate them";
   }
}
