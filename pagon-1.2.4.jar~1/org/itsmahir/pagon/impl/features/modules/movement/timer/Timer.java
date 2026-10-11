package org.itsmahir.pagon.impl.features.modules.movement.timer;

import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.render.RenderTimer;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class Timer extends Module {
   Value<Number> timerAmount = (new ValueBuilder<Number>()).withDescriptor("Timer Amount").withValue(5).withRange(0.1, 10).register(this);
   float oldTickLength = 1.0F;
   int boostTime;
   int boostSpaceTime;
   boolean doLoop;

   public Timer() {
      super("Timer", Feature.Category.Movement);
   }

   public void onEnable() {
      super.onEnable();
      if (!NullUtils.nullCheck()) {
         this.oldTickLength = RenderTimer.getTickLength();
         this.boostTime = 0;
         this.doLoop = false;
         this.boostSpaceTime = 0;
      }
   }

   public void onDisable() {
      super.onDisable();
      if (!NullUtils.nullCheck()) {
         if (mc.method_60646() != null) {
            RenderTimer.setTickLength(this.oldTickLength);
         }
      }
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.ClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         if (mc.method_60646() != null) {
            RenderTimer.setTickLength(((Number)this.timerAmount.getValue()).floatValue());
         }
      }
   }

   public String getHudInfo() {
      float var10000 = ((Number)this.timerAmount.getValue()).floatValue();
      return "" + MathUtil.round(var10000, 1);
   }

   public String getDescription() {
      return "Timer: Decreases/Increases the amount of ticks in a second";
   }
}
