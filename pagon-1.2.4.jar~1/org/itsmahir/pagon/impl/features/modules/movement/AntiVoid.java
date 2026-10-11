package org.itsmahir.pagon.impl.features.modules.movement;

import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2828;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class AntiVoid extends Module {
   Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Fling").withModes("Fling", "Float", "Bounce", "Packet").register(this);

   public AntiVoid() {
      super("AntiVoid", Feature.Category.Movement);
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.ClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         class_2338 pos = mc.field_1724.method_24515();
         class_2338 pos2 = new class_2338(mc.field_1724.method_31477(), mc.field_1687.method_31607(), mc.field_1724.method_31479());
         if (mc.field_1687.method_8320(pos2).method_26204() == class_2246.field_10124 && pos.equals(pos2)) {
            switch ((String)this.mode.getValue()) {
               case "Fling" -> PlayerUtils.setMotionY((double)1.0F);
               case "Bounce" -> mc.field_1724.method_6043();
               case "Float" -> PlayerUtils.setMotionY((double)0.0F);
               case "Packet" -> PacketManager.INSTANCE.sendPacket(new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() + (double)3.0F, mc.field_1724.method_23321(), false));
            }
         }

      }
   }

   public String getHudInfo() {
      return this.mode.getValue();
   }

   public String getDescription() {
      return "AntiVoid: Prevents you from falling in the deep dark and scary void";
   }
}
