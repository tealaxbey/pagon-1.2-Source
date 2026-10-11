package org.itsmahir.pagon.impl.features.modules.player;

import net.minecraft.class_1268;
import net.minecraft.class_1747;
import net.minecraft.class_1764;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2846;
import net.minecraft.class_2886;
import net.minecraft.class_7204;
import net.minecraft.class_2846.class_2847;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.mixin.accessor.IMinecraftClient;

public class FastUse extends Module {
   private int currentItem = 0;
   Value<Number> delay = (new ValueBuilder<Number>()).withDescriptor("Delay").withValue(0).withRange(0, 10).withPlaces(0).register(this);
   Value<Boolean> blocks = (new ValueBuilder<Boolean>()).withDescriptor("Blocks").withValue(false).register(this);
   Value<Boolean> xp = (new ValueBuilder<Boolean>()).withDescriptor("XP").withValue(false).register(this);
   Value<Boolean> crystals = (new ValueBuilder<Boolean>()).withDescriptor("Crystals").withValue(false).register(this);
   Value<Boolean> all = (new ValueBuilder<Boolean>()).withDescriptor("All").withValue(false).register(this);
   Value<Boolean> ghostFix = (new ValueBuilder<Boolean>()).withDescriptor("Ghost Fix").withValue(false).register(this);
   Value<Boolean> bows = (new ValueBuilder<Boolean>()).withDescriptor("Fast Bow").withValue(false).register(this);
   Value<Number> bowDelay = (new ValueBuilder<Number>()).withDescriptor("Bow Delay").withValue(3).withRange(3, 25).withPlaces(0).register(this);
   Value<Boolean> crossBow = (new ValueBuilder<Boolean>()).withDescriptor("Fast Crossbow").withValue(false).register(this);
   public static FastUse INSTANCE;

   public FastUse() {
      super("FastUse", Feature.Category.Player);
      INSTANCE = this;
   }

   private void handleBow() {
      class_1799 mainhand = mc.field_1724.method_6047();
      if (mainhand.method_7909() == class_1802.field_8102 && (Boolean)this.bows.getValue()) {
         if (mc.field_1724.method_6048() >= ((Number)this.bowDelay.getValue()).intValue()) {
            PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12974, class_2338.field_10980, class_2350.field_11033));
            mc.field_1724.method_6075();
         }
      } else if ((Boolean)this.crossBow.getValue() && mainhand.method_7909() == class_1802.field_8399 && (float)mc.field_1724.method_6048() / (float)class_1764.method_7775(mc.field_1724.method_6047(), mc.field_1724) > 1.0F) {
         PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12974, class_2338.field_10980, class_2350.field_11033));
         mc.field_1724.method_6075();
         mc.field_1761.method_2919(mc.field_1724, class_1268.field_5808);
      }

   }

   @SubscribeEvent
   public void onTick(TickEvent.AfterClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         this.handleBow();
         this.handleItems();
      }
   }

   public void handleItems() {
      if ((float)((IMinecraftClient)mc).getItemUseCooldown() > ((Number)this.delay.getValue()).floatValue() && this.check(mc.field_1724.method_6047().method_7909())) {
         if ((Boolean)this.ghostFix.getValue()) {
            PacketManager.INSTANCE.sendPacket((class_7204)((id) -> new class_2886(mc.field_1724.method_6058(), id, RotationUtils.getActualYaw(), RotationUtils.getActualPitch())));
         }

         ((IMinecraftClient)mc).setItemUseCooldown(((Number)this.delay.getValue()).intValue());
      }

   }

   public boolean check(class_1792 item) {
      return item instanceof class_1747 && (Boolean)this.blocks.getValue() || item == class_1802.field_8301 && (Boolean)this.crystals.getValue() || item == class_1802.field_8287 && (Boolean)this.xp.getValue() || (Boolean)this.all.getValue();
   }

   public String getHudInfo() {
      if (NullUtils.nullCheck()) {
         return "not loaded";
      } else {
         class_1799 mainhand = mc.field_1724.method_6047();
         if (mainhand.method_7909() == class_1802.field_8102) {
            return mc.field_1724.method_6058() != null ? "" + mc.field_1724.method_6014() : "";
         } else {
            return "" + ((IMinecraftClient)mc).getItemUseCooldown();
         }
      }
   }

   public String getDescription() {
      return "FastUse: Use items or release bows faster";
   }
}
