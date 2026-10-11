package org.itsmahir.pagon.impl.features.modules.movement;

import com.google.common.collect.Streams;
import net.minecraft.class_2828;
import net.minecraft.class_5134;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.render.RenderTimer;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.combat.FeetTrap;

public class Step extends Module {
   Value<Number> stepHeight = (new ValueBuilder<Number>()).withDescriptor("Step Height").withValue(2.1).withRange(0.1, 7).register(this);
   Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Vanilla").withModes("Vanilla", "Normal").register(this);
   Value<Boolean> timer;
   Value<Boolean> extra;
   Value<Boolean> alternate;
   public static Step INSTANCE;
   Timer stepTimer;
   private boolean cancelTimer;
   Timer grimTimer;
   boolean runPhysics;

   public Step() {
      super("Step", Feature.Category.Movement);
      this.timer = (new ValueBuilder<Boolean>()).withDescriptor("Use Timer").withValue(false).withPageParent(this.mode).withPage("Normal").register(this);
      this.extra = (new ValueBuilder<Boolean>()).withDescriptor("Extra").withValue(false).withPageParent(this.mode).withPage("Normal").register(this);
      this.alternate = (new ValueBuilder<Boolean>()).withDescriptor("Alternate").withValue(false).withPageParent(this.mode).withPage("Normal").register(this);
      this.stepTimer = new Timer();
      this.grimTimer = new Timer();
      this.runPhysics = false;
      INSTANCE = this;
   }

   public void onDisable() {
      super.onDisable();
      if (!NullUtils.nullCheck()) {
         setStepHeight(0.6F);
         if ((Boolean)this.timer.getValue()) {
            RenderTimer.setTickLength(1.0F);
         }

         this.stepTimer.setDelay(50L);
         this.grimTimer.setDelay(200L);
      }
   }

   @SubscribeEvent
   public void onPlayerUpdate(TickEvent.MovementTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (((String)this.mode.getValue()).equals("Normal")) {
            double height = mc.field_1724.method_23318() - mc.field_1724.field_6036;
            if (height <= (double)0.5F || height > ((Number)this.stepHeight.getValue()).doubleValue()) {
               return;
            }

            if ((Boolean)this.alternate.getValue() && height <= (double)1.5F) {
               return;
            }

            double[] offs = this.getStepOffsets(height);
            if ((Boolean)this.timer.getValue()) {
               RenderTimer.setTickLength(height > (double)1.0F ? 0.15F : 0.35F);
               this.cancelTimer = true;
            }

            for(double off : offs) {
               PacketManager.INSTANCE.sendPacket(new class_2828.class_2829(mc.field_1724.field_6014, mc.field_1724.field_6036 + off, mc.field_1724.field_5969, false));
            }

            this.stepTimer.resetDelay();
            if (FeetTrap.INSTANCE.isEnabled()) {
               FeetTrap.INSTANCE.setEnabled(false);
            }
         }

      }
   }

   public void doNCPStep(double[] dir, boolean forceStep) {
      boolean twofive = false;
      boolean two = false;
      boolean onefive = false;
      boolean one = false;
      if (Streams.stream(mc.field_1687.method_8600(mc.field_1724, mc.field_1724.method_5829().method_989(dir[0], (double)21.0F, dir[1]))).toList().isEmpty() && !Streams.stream(mc.field_1687.method_8600(mc.field_1724, mc.field_1724.method_5829().method_989(dir[0], 2.4, dir[1]))).toList().isEmpty()) {
         two = true;
      }

      if (Streams.stream(mc.field_1687.method_8600(mc.field_1724, mc.field_1724.method_5829().method_989(dir[0], 2.1, dir[1]))).toList().isEmpty() && !Streams.stream(mc.field_1687.method_8600(mc.field_1724, mc.field_1724.method_5829().method_989(dir[0], 1.9, dir[1]))).toList().isEmpty()) {
         two = true;
      }

      if (Streams.stream(mc.field_1687.method_8600(mc.field_1724, mc.field_1724.method_5829().method_989(dir[0], 1.6, dir[1]))).toList().isEmpty() && !Streams.stream(mc.field_1687.method_8600(mc.field_1724, mc.field_1724.method_5829().method_989(dir[0], 1.4, dir[1]))).toList().isEmpty()) {
         onefive = true;
      }

      if (Streams.stream(mc.field_1687.method_8600(mc.field_1724, mc.field_1724.method_5829().method_989(dir[0], (double)1.0F, dir[1]))).toList().isEmpty() && !Streams.stream(mc.field_1687.method_8600(mc.field_1724, mc.field_1724.method_5829().method_989(dir[0], 0.6, dir[1]))).toList().isEmpty()) {
         one = true;
      }

      if (mc.field_1724.field_5976 && (mc.field_1724.field_3913.field_3905 != 0.0F || mc.field_1724.field_3913.field_3907 != 0.0F || forceStep) && mc.field_1724.method_24828()) {
         if (one && ((Number)this.stepHeight.getValue()).doubleValue() >= (double)1.0F) {
            double[] oneOffset = this.getStepOffsets((double)1.0F);

            for(double v : oneOffset) {
               PacketManager.INSTANCE.sendPacket(new class_2828.class_2829(mc.field_1724.field_6014, mc.field_1724.field_6036 + v, mc.field_1724.field_5969, false));
            }

            mc.field_1724.method_5814(mc.field_1724.method_23317(), mc.field_1724.method_23318() + (double)1.0F, mc.field_1724.method_23321());
         }

         if (onefive && ((Number)this.stepHeight.getValue()).doubleValue() >= (double)1.5F) {
            double[] oneFiveOffset = this.getStepOffsets((double)1.5F);

            for(double v : oneFiveOffset) {
               PacketManager.INSTANCE.sendPacket(new class_2828.class_2829(mc.field_1724.field_6014, mc.field_1724.field_6036 + v, mc.field_1724.field_5969, false));
            }

            mc.field_1724.method_5814(mc.field_1724.method_23317(), mc.field_1724.method_23318() + (double)1.5F, mc.field_1724.method_23321());
         }

         if (two && ((Number)this.stepHeight.getValue()).doubleValue() >= (double)2.0F) {
            double[] twoOffset = this.getStepOffsets((double)2.0F);

            for(double v : twoOffset) {
               PacketManager.INSTANCE.sendPacket(new class_2828.class_2829(mc.field_1724.field_6014, mc.field_1724.field_6036 + v, mc.field_1724.field_5969, false));
            }

            mc.field_1724.method_5814(mc.field_1724.method_23317(), mc.field_1724.method_23318() + (double)2.0F, mc.field_1724.method_23321());
         }

         if (twofive && ((Number)this.stepHeight.getValue()).doubleValue() >= (double)2.5F) {
            double[] twoFiveOffset = this.getStepOffsets((double)2.5F);

            for(double v : twoFiveOffset) {
               PacketManager.INSTANCE.sendPacket(new class_2828.class_2829(mc.field_1724.field_6014, mc.field_1724.field_6036 + v, mc.field_1724.field_5969, false));
            }

            mc.field_1724.method_5814(mc.field_1724.method_23317(), mc.field_1724.method_23318() + (double)2.5F, mc.field_1724.method_23321());
         }
      }

   }

   @SubscribeEvent
   public void onTick(TickEvent.ClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         this.stepTimer.setDelay(50L);
         if (!mc.field_1724.method_5799() && !mc.field_1724.method_5771() && !mc.field_1724.method_6128()) {
            if (this.cancelTimer && mc.field_1724.method_24828()) {
               if ((Boolean)this.timer.getValue()) {
                  RenderTimer.setTickLength(1.0F);
               }

               this.cancelTimer = false;
            }

            if (mc.field_1724.method_24828() && this.stepTimer.isPassed()) {
               setStepHeight(((Number)this.stepHeight.getValue()).floatValue());
            } else {
               setStepHeight(0.6F);
            }

         } else {
            if ((Boolean)this.timer.getValue()) {
               RenderTimer.setTickLength(1.0F);
            }

            setStepHeight(0.6F);
         }
      }
   }

   private double[] getStepOffsets(double stepHeight) {
      double[] offsets = new double[0];
      if ((Boolean)this.extra.getValue()) {
         if (stepHeight > 1.1661) {
            offsets = new double[]{0.42, 0.7532, 1.001, 1.1661, stepHeight};
         } else if (stepHeight > 1.015) {
            offsets = new double[]{0.42, 0.7532, 1.001, stepHeight};
         } else if (stepHeight > 0.6) {
            offsets = new double[]{0.42 * stepHeight, 0.7532 * stepHeight, stepHeight};
         }

         return offsets;
      } else {
         if (stepHeight > 2.019) {
            offsets = new double[]{0.425, 0.821, 0.699, 0.599, 1.022, 1.372, 1.652, 1.869, 2.019, 1.919};
         } else if (stepHeight > (double)1.5F) {
            offsets = new double[]{0.42, 0.78, 0.63, 0.51, 0.9, 1.21, 1.45, 1.43};
         } else if (stepHeight > 1.015) {
            offsets = new double[]{0.42, 0.7532, 1.01, 1.093, 1.015};
         } else if (stepHeight > 0.6) {
            offsets = new double[]{0.42 * stepHeight, 0.7532 * stepHeight};
         }

         return offsets;
      }
   }

   public static void setStepHeight(float height) {
      mc.field_1724.method_5996(class_5134.field_47761).method_6192((double)height);
   }

   public String getHudInfo() {
      return this.mode.getValue();
   }

   public String getDescription() {
      return "Step: Steps up blocks faster/higher then you normally can";
   }
}
