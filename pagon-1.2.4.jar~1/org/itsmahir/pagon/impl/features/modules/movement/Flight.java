package org.itsmahir.pagon.impl.features.modules.movement;

import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.move.MoveEvent;
import org.itsmahir.pagon.api.event.events.move.TravelEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class Flight extends Module {
   public static Flight INSTANCE;
   public Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Creative").withModes("Creative").withAction((val) -> this.handlePage((String)val.getValue())).register(this);
   Value<Number> horizontalSpeed = (new ValueBuilder<Number>()).withDescriptor("Horizontal").withValue(3.0F).withRange(1, 5).register(this);
   Value<Number> verticalSpeed = (new ValueBuilder<Number>()).withDescriptor("Vertical").withValue(3.0F).withRange(1, 5).register(this);
   Value<Boolean> antiKick = (new ValueBuilder<Boolean>()).withDescriptor("AntiKick").withValue(false).register(this);
   Timer antiKickTimer = new Timer(3800L);

   public Flight() {
      super("Flight", Feature.Category.Movement);
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onPlayerMove(MoveEvent event) {
      switch ((String)this.mode.getValue()) {
         case "Creative":
            event.motionY((double)0.0F);
            if (this.antiKickTimer.isPassed() && (Boolean)this.antiKick.getValue()) {
               event.motionY(-0.04);
               this.antiKickTimer.resetDelay();
            } else if (mc.field_1690.field_1903.method_1434()) {
               event.motionY((double)((Number)this.verticalSpeed.getValue()).floatValue());
            } else if (mc.field_1690.field_1832.method_1434()) {
               event.motionY((double)(-((Number)this.verticalSpeed.getValue()).floatValue()));
            }

            float speed = ((Number)this.horizontalSpeed.getValue()).floatValue();
            float forward = mc.field_1724.field_3913.field_3905;
            float strafe = mc.field_1724.field_3913.field_3907;
            float yaw = mc.field_1724.method_36454();
            if (forward == 0.0F && strafe == 0.0F) {
               event.motionX((double)0.0F);
               event.motionZ((double)0.0F);
               return;
            } else {
               double rx = Math.cos(Math.toRadians((double)(yaw + 90.0F)));
               double rz = Math.sin(Math.toRadians((double)(yaw + 90.0F)));
               event.motionX((double)(forward * speed) * rx + (double)(strafe * speed) * rz);
               event.motionZ((double)(forward * speed) * rz - (double)(strafe * speed) * rx);
            }
         default:
      }
   }

   @SubscribeEvent
   public void onTravel(TravelEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         switch ((String)this.mode.getValue()) {
            case "Creative":
            default:
         }
      }
   }

   public void handlePage(String page) {
      this.horizontalSpeed.setActive(page.equals("Creative"));
      this.verticalSpeed.setActive(page.equals("Creative"));
      this.antiKick.setActive(page.equals("Creative"));
   }

   public String getHudInfo() {
      return this.mode.getValue();
   }

   public String getDescription() {
      return "Flight: I just flew 100 blocks thanks to sn0wgod.cc! (or now catogod.cc)";
   }
}
