package org.itsmahir.pagon.impl.features.modules.combat;

import net.minecraft.class_1268;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_239;
import net.minecraft.class_2886;
import net.minecraft.class_7204;
import net.minecraft.class_239.class_240;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.management.RotationManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.rotation.Rotation;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class AutoXP extends Module {
   public static AutoXP INSTANCE;
   public Value<Number> xpSpeed = (new ValueBuilder<Number>()).withDescriptor("Speed").withValue(20).withRange(0.1, 20).withPlaces(1).register(this);
   public Value<Number> boost = (new ValueBuilder<Number>()).withDescriptor("Boost").withValue(0).withRange(0, 5).withPlaces(0).register(this);
   Value<String> rotate = (new ValueBuilder<String>()).withDescriptor("Rotate").withValue("None").withModes("None", "Packet", "Both").register(this);
   public Value<Boolean> middleClick = (new ValueBuilder<Boolean>()).withDescriptor("Middle Click").withValue(true).register(this);
   public Value<Boolean> mend = (new ValueBuilder<Boolean>()).withDescriptor("Mend").withValue(true).register(this);
   Timer speed = new Timer();

   public AutoXP() {
      super("AutoXP", Feature.Category.Combat);
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onTickPre(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         this.speed.setDelayCPS(((Number)this.xpSpeed.getValue()).floatValue());
         if (this.canXp()) {
            if (((String)this.rotate.getValue()).equals("Both")) {
               RotationManager.INSTANCE.rotateTo(new Rotation(mc.field_1724.method_36454(), 90.0F));
            }

            int slot = InventoryUtils.getHotbarItemSlot(class_1802.field_8287);
            boolean swap = slot != mc.field_1724.method_31548().field_7545;
            int oldSlot = mc.field_1724.method_31548().field_7545;
            if (swap) {
               InventoryUtils.switchToSlot(slot);
            }

            for(int i = 0; i < 1 + ((Number)this.boost.getValue()).intValue(); ++i) {
               PacketManager.INSTANCE.sendPacket((class_7204)((id) -> new class_2886(class_1268.field_5808, id, mc.field_1724.method_36454(), !((String)this.rotate.getValue()).equals("None") ? 90.0F : mc.field_1724.method_36455())));
               mc.field_1724.method_6104(class_1268.field_5808);
            }

            if (swap) {
               InventoryUtils.switchToSlot(oldSlot);
            }

            this.speed.resetDelay();
         }
      }
   }

   public boolean canXp() {
      return !this.speed.isPassed() ? false : this.isXping();
   }

   public boolean isXping() {
      if ((Boolean)this.middleClick.getValue()) {
         if (!mc.field_1729.method_35707()) {
            return false;
         }

         class_239 mouseOver = mc.field_1765;
         if (mouseOver == null || mouseOver.method_17783() != class_240.field_1332) {
            return false;
         }
      }

      int slot = InventoryUtils.getHotbarItemSlot(class_1802.field_8287);
      if (slot == -1) {
         return false;
      } else {
         if ((Boolean)this.mend.getValue()) {
            for(class_1799 stack : mc.field_1724.method_31548().field_7548) {
               if (stack != class_1799.field_8037) {
                  String armor = InventoryUtils.getArmorPieceName(stack);
                  if (armor != null) {
                     float green = ((float)stack.method_7936() - (float)stack.method_7919()) / (float)stack.method_7936();
                     float red = 1.0F - green;
                     int dmg = 100 - (int)(red * 100.0F);
                     if (dmg <= 98) {
                        return true;
                     }
                  }
               }
            }
         }

         return !(Boolean)this.mend.getValue();
      }
   }

   @SubscribeEvent
   public void onTickPost(TickEvent.MovementTickEvent.Post event) {
      if (!NullUtils.nullCheck()) {
         if (this.canXp()) {
            ;
         }
      }
   }

   public String getHudInfo() {
      return NullUtils.nullCheck() ? "" : "" + InventoryUtils.getItemCount(class_1802.field_8287);
   }

   public String getDescription() {
      return "Automatically throws xp silently.";
   }
}
