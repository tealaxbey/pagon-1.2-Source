package org.itsmahir.pagon.impl.features.modules.movement;

import net.minecraft.class_238;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class FastFall extends Module {
   Value<Number> height = (new ValueBuilder<Number>()).withDescriptor("Height").withValue(2).withRange(1, 10).register(this);
   Value<Boolean> teleport = (new ValueBuilder<Boolean>()).withDescriptor("Teleport").withValue(false).register(this);
   Value<Number> teleportHeight;
   private boolean prevOnGround;

   public FastFall() {
      super("FastFall", Feature.Category.Movement);
      this.teleportHeight = (new ValueBuilder<Number>()).withDescriptor("Tele-Height").withValue(2).withRange(1, 5).withParent(this.teleport).withParentEnabled(true).register(this);
      this.prevOnGround = false;
   }

   @SubscribeEvent
   public void onTick(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (!mc.field_1724.method_3144() && !mc.field_1724.method_6128() && !mc.field_1724.method_21754() && !mc.field_1724.method_5771() && !mc.field_1724.method_5799() && !mc.field_1724.field_3913.field_3904 && !mc.field_1724.field_3913.field_3903) {
            if (!this.movementModuleCheck()) {
               if (mc.field_1724.method_24828() && this.isNearestBlockWithinHeight((double)((Number)this.height.getValue()).floatValue())) {
                  PlayerUtils.setMotionY((double)-3.0F);
               }

               if ((Boolean)this.teleport.getValue() && (double)mc.field_1724.field_6017 < (double)0.5F && this.prevOnGround && mc.field_1724.method_24828()) {
                  double downTrace = getGroundLevel();
                  if (mc.field_1724.method_23318() - downTrace <= (double)((Number)this.teleportHeight.getValue()).floatValue()) {
                     class_238 mybb = mc.field_1724.method_5829();
                     if (!isColliding(new class_238(mybb.field_1323, downTrace, mybb.field_1321, mybb.field_1320, downTrace + (double)mc.field_1724.method_17682(), mybb.field_1324)) && !(downTrace > mc.field_1724.method_23318())) {
                        mc.field_1724.method_5814(mc.field_1724.method_23317(), downTrace, mc.field_1724.method_23321());
                     }
                  }
               }

               this.prevOnGround = mc.field_1724.method_24828();
            }
         }
      }
   }

   private boolean isNearestBlockWithinHeight(double height) {
      class_238 bb = mc.field_1724.method_5829();

      for(double i = (double)0.0F; i < height + (double)0.5F; i += 0.01) {
         if (!mc.field_1687.method_8587(mc.field_1724, bb.method_989((double)0.0F, -i, (double)0.0F))) {
            return true;
         }
      }

      return false;
   }

   public boolean movementModuleCheck() {
      return Speed.INSTANCE.isEnabled() || Holesnap.INSTANCE.isEnabled() || PhaseWalk.INSTANCE.isEnabled() || Flight.INSTANCE.isEnabled() || LongJump.INSTANCE.isEnabled();
   }

   public static double getGroundLevel() {
      for(int i = (int)Math.round(mc.field_1724.method_23318()); i > mc.field_1687.method_31607(); --i) {
         class_238 mybb = mc.field_1724.method_5829();
         class_238 box = new class_238(mybb.field_1323, (double)(i - 1), mybb.field_1321, mybb.field_1320, (double)i, mybb.field_1324);
         if (isColliding(box) && box.field_1322 <= mc.field_1724.method_23318()) {
            return (double)i;
         }
      }

      return mc.field_1724.method_23318();
   }

   public static boolean isColliding(class_238 box) {
      return mc.field_1687.method_20812(mc.field_1724, box).iterator().hasNext();
   }

   public String getDescription() {
      return "FastFall/ReverseStep: \"Falls Down\" faster";
   }
}
