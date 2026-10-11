package org.itsmahir.pagon.impl.features.modules.misc;

import net.minecraft.class_2596;
import net.minecraft.class_2767;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class SoundBlocker extends Module {
   public static SoundBlocker INSTANCE;
   private final Value<Boolean> fireworks = (new ValueBuilder<Boolean>()).withDescriptor("Fireworks").withValue(true).register(this);
   private final Value<Boolean> swap = (new ValueBuilder<Boolean>()).withDescriptor("Swap").withValue(true).register(this);
   private final Value<Boolean> glide = (new ValueBuilder<Boolean>()).withDescriptor("Glide").withValue(true).register(this);

   public SoundBlocker() {
      super("SoundBlocker", Feature.Category.Misc);
      INSTANCE = this;
      super.setEnabled(true);
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Receive event) {
      class_2596 var3 = event.getPacket();
      if (var3 instanceof class_2767 packet) {
         if ((Boolean)this.fireworks.getValue() && this.isFireworkSound(packet)) {
            event.setCancelled(true);
         }

         if ((Boolean)this.swap.getValue() && this.isSwapSound(packet)) {
            event.setCancelled(true);
         }

      }
   }

   @SubscribeEvent
   public void onPlayerTick(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if ((Boolean)this.glide.getValue() && mc.field_1724.method_6128()) {
            mc.method_1483().method_4875(class_3417.field_14572.method_14833(), class_3419.field_15248);
         }

      }
   }

   private boolean isFireworkSound(class_2767 packet) {
      class_3414 sound = (class_3414)packet.method_11894().comp_349();
      return sound == class_3417.field_14702 || sound == class_3417.field_14917 || sound == class_3417.field_15090 || sound == class_3417.field_15188 || sound == class_3417.field_14612 || sound == class_3417.field_14712 || sound == class_3417.field_14800 || sound == class_3417.field_14882;
   }

   private boolean isSwapSound(class_2767 packet) {
      return packet.method_11894() == class_3417.field_14883 || packet.method_11894() == class_3417.field_14966 || packet.method_11894() == class_3417.field_14581 || packet.method_11894() == class_3417.field_15191 || packet.method_11894() == class_3417.field_14862 || packet.method_11894() == class_3417.field_14761 || packet.method_11894() == class_3417.field_15103 || packet.method_11894() == class_3417.field_21866 || packet.method_11894() == class_3417.field_14684 || packet.method_11894() == class_3417.field_47721;
   }

   public String getHudInfo() {
      String var10000 = (Boolean)this.fireworks.getValue() ? "1" : "0";
      return "F:" + var10000 + " S:" + ((Boolean)this.swap.getValue() ? "1" : "0") + " G:" + ((Boolean)this.glide.getValue() ? "1" : "0");
   }

   public String getDescription() {
      return "Blocks firework, armor swap, and elytra glide sounds";
   }
}
