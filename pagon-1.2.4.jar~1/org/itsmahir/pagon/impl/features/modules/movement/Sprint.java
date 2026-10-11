package org.itsmahir.pagon.impl.features.modules.movement;

import net.minecraft.class_1294;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.LivingEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class Sprint extends Module {
   public static Sprint INSTANCE;
   public Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Vanilla").withModes("Vanilla", "Rage").register(this);
   Value<Boolean> rotate;

   public Sprint() {
      super("Sprint", Feature.Category.Movement);
      this.rotate = (new ValueBuilder<Boolean>()).withDescriptor("Rotate").withValue(false).withPageParent(this.mode).withPage("Rage").register(this);
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onPlayerTick(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (((String)this.mode.getValue()).equals("Rage") && (Boolean)this.rotate.getValue() && mc.field_1755 == null) {
            RotationUtils.setRotation(new float[]{PlayerUtils.getMoveYaw(mc.field_1724.method_36454()), mc.field_1724.method_36455()}, 2);
         }

      }
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.ClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         if (PlayerUtils.isMoving() && !mc.field_1724.method_5715() && !mc.field_1724.method_3144() && !mc.field_1724.method_5799() && !mc.field_1724.method_5771() && !mc.field_1724.method_21754() && !mc.field_1724.method_6059(class_1294.field_5919) && (float)mc.field_1724.method_7344().method_7586() > 6.0F) {
            switch ((String)this.mode.getValue()) {
               case "Vanilla":
                  if (mc.field_1724.field_3913.method_20622() && (!mc.field_1724.field_5976 || mc.field_1724.field_34927)) {
                     mc.field_1724.method_5728(true);
                  }
                  break;
               case "Rage":
                  if ((Boolean)this.rotate.getValue()) {
                     this.doVanilla();
                  } else {
                     mc.field_1724.method_5728(true);
                  }
            }
         }

      }
   }

   public void doVanilla() {
      if (mc.field_1724.field_3913.method_20622() && (!mc.field_1724.field_5976 || mc.field_1724.field_34927)) {
         mc.field_1724.method_5728(true);
      }

   }

   @SubscribeEvent
   public void onTick(LivingEvent.SetSprinting event) {
      if (PlayerUtils.isMoving() && !(Boolean)this.rotate.getValue() && !mc.field_1724.method_5715() && !mc.field_1724.method_3144() && !mc.field_1724.method_5799() && !mc.field_1724.method_5771() && !mc.field_1724.method_21754() && !mc.field_1724.method_6059(class_1294.field_5919) && (float)mc.field_1724.method_7344().method_7586() > 6.0F && ((String)this.mode.getValue()).equals("Rage")) {
         event.setCancelled(true);
      }

   }

   public String getHudInfo() {
      return this.mode.getValue();
   }

   public String getDescription() {
      return "Sprint: go 22.22 to the left with this module";
   }
}
