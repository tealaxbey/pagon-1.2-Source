package org.itsmahir.pagon.impl.features.modules.player;

import net.minecraft.class_1937;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.mixin.accessor.IPlayerMoveC2SPacket;

public class NoFall extends Module {
   Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Packet").withModes("Packet", "Future", "AAC", "Predict", "Grim", "Latency", "NoGround").register(this);
   double predictY;

   public NoFall() {
      super("NoFall", Feature.Category.Player);
   }

   @SubscribeEvent
   public void onPlayerTick(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (this.isFalling()) {
            switch ((String)this.mode.getValue()) {
               case "Latency":
                  if (mc.field_1687.method_27983() == class_1937.field_25180) {
                     PacketManager.INSTANCE.sendPacket(new class_2828.class_2829(mc.field_1724.method_23317(), (double)0.0F, mc.field_1724.method_23321(), true));
                  } else {
                     PacketManager.INSTANCE.sendPacket(new class_2828.class_2829((double)0.0F, (double)64.0F, (double)0.0F, true));
                  }

                  mc.field_1724.field_6017 = 0.0F;
                  break;
               case "Grim":
                  PacketManager.INSTANCE.sendPacket(new class_2828.class_2830(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 1.0E-9, mc.field_1724.method_23321(), mc.field_1724.method_36454(), mc.field_1724.method_36455(), true));
                  mc.field_1724.method_38785();
            }

         }
      }
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Send event) {
      if (!NullUtils.nullCheck()) {
         if (!event.isCancelled()) {
            class_2596 var3 = event.getPacket();
            if (var3 instanceof class_2828) {
               class_2828 eventPacket = (class_2828)var3;
               IPlayerMoveC2SPacket packet = (IPlayerMoveC2SPacket)eventPacket;
               if (((String)this.mode.getValue()).equals("NoGround")) {
                  packet.setOnGround(false);
                  return;
               }

               if (!this.isFalling()) {
                  return;
               }

               switch ((String)this.mode.getValue()) {
                  case "Packet":
                     packet.setOnGround(true);
                     break;
                  case "Future":
                     packet.setY(mc.field_1724.method_23318() + 0.1);
                     break;
                  case "AAC":
                     mc.field_1724.method_24830(true);
                     mc.field_1724.method_31549().field_7479 = true;
                     mc.field_1724.method_31549().field_7478 = true;
                     packet.setOnGround(false);
                     mc.field_1724.field_6037 = true;
                     mc.field_1724.method_31549().field_7479 = false;
                     mc.field_1724.method_31549().field_7478 = false;
                     mc.field_1724.method_6043();
                     break;
                  case "Predict":
                     if (this.predict() && mc.field_1724.field_6017 >= 3.0F) {
                        PlayerUtils.setMotionY((double)0.0F);
                        packet.setY(this.predictY);
                        mc.field_1724.field_6017 = 0.0F;
                     }
               }
            }

         }
      }
   }

   public boolean isFalling() {
      return mc.field_1724.field_6017 > 3.0F && !PlayerUtils.isAboveWater(mc.field_1724) && !PlayerUtils.isInWater(mc.field_1724);
   }

   public boolean predict() {
      this.predictY = PlayerUtils.getGroundLevel() - 0.1;
      return mc.field_1724.method_23318() - this.predictY < (double)3.0F;
   }

   public String getHudInfo() {
      return this.mode.getValue();
   }

   public String getDescription() {
      return "NoFall: Become a cat with 9(9999) lives!";
   }
}
