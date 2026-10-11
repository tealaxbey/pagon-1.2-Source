package org.itsmahir.pagon.impl.features.modules.render;

import java.awt.Color;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1684;
import net.minecraft.class_1753;
import net.minecraft.class_1764;
import net.minecraft.class_1799;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_239.class_240;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.world.RenderType;
import org.itsmahir.pagon.api.utils.render.world.buffers.RenderBuffers;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class Trajectories extends Module {
   Value<Number> lineWidth = (new ValueBuilder<Number>()).withDescriptor("Line Width").withValue(2).withRange(0.1, 5).register(this);
   Value<PagonColor> startColor = (new ValueBuilder<PagonColor>()).withDescriptor("Start Color").withValue(new PagonColor(255, 255, 255)).register(this);
   Value<PagonColor> endColor = (new ValueBuilder<PagonColor>()).withDescriptor("End Color").withValue(new PagonColor(0, 255, 72)).register(this);
   Value<Boolean> pearls = (new ValueBuilder<Boolean>()).withDescriptor("Pearls").withValue(true).register(this);
   private MathUtil.Result result = null;

   public Trajectories() {
      super("Trajectories", Feature.Category.Render);
   }

   @SubscribeEvent
   public void onTick(TickEvent.ClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         ;
      }
   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldEvent event) {
      if (!NullUtils.nullCheck()) {
         if ((Boolean)this.pearls.getValue()) {
            for(class_1297 entity : mc.field_1687.method_18112()) {
               if (entity instanceof class_1684) {
                  class_1684 pearl = (class_1684)entity;
                  if (!mc.field_1687.method_8621().method_61115(pearl.method_19538())) {
                     return;
                  }

                  MathUtil.Result result = MathUtil.calcTrajectory(pearl);
                  if (result != null) {
                     this.drawTrail(result);
                     if (result.getHitResult() != null) {
                        class_239 hitResult = result.getHitResult();
                        if (!hitResult.method_17783().equals(class_240.field_1333)) {
                           if (hitResult instanceof class_3965) {
                              class_3965 blockHitResult = (class_3965)hitResult;
                              RenderUtil.drawCircle(RenderBuffers.TRIANGLES, 0.3F, 130, blockHitResult.method_17784(), blockHitResult.method_17780(), ColorUtil.newAlpha(((PagonColor)this.endColor.getValue()).getColor(), 25));
                              RenderUtil.drawCircle(RenderBuffers.LINES, 0.3F, 130, blockHitResult.method_17784(), blockHitResult.method_17780(), ((PagonColor)this.endColor.getValue()).getColor());
                           } else if (hitResult instanceof class_3966) {
                              class_3966 entityHitResult = (class_3966)hitResult;
                              RenderUtil.renderBox(RenderType.FILL, entityHitResult.method_17782().method_5829(), ColorUtil.newAlpha(((PagonColor)this.endColor.getValue()).getColor().brighter(), 100), ColorUtil.newAlpha(((PagonColor)this.endColor.getValue()).getColor().brighter(), 100));
                           }
                        }
                     }
                  }
               }
            }
         }

         class_1799 mainHand = mc.field_1724.method_6047();
         class_1799 offHand = mc.field_1724.method_6079();
         class_1268 hand;
         if (!(mainHand.method_7909() instanceof class_1753) && !(mainHand.method_7909() instanceof class_1764) && !MathUtil.isThrowable(mainHand.method_7909())) {
            if (!(offHand.method_7909() instanceof class_1753) && !(offHand.method_7909() instanceof class_1764) && !MathUtil.isThrowable(offHand.method_7909())) {
               return;
            }

            hand = class_1268.field_5810;
         } else {
            hand = class_1268.field_5808;
         }

         MathUtil.Result result = MathUtil.calcTrajectory(hand == class_1268.field_5810 ? offHand.method_7909() : mainHand.method_7909(), RotationUtils.getActualYaw());
         if (result != null) {
            this.drawTrail(result);
            if (result.getHitResult() != null) {
               class_239 hitResult = result.getHitResult();
               if (!hitResult.method_17783().equals(class_240.field_1333)) {
                  if (hitResult instanceof class_3965) {
                     class_3965 blockHitResult = (class_3965)hitResult;
                     RenderUtil.drawCircle(RenderBuffers.TRIANGLES, 0.3F, 130, blockHitResult.method_17784(), blockHitResult.method_17780(), ColorUtil.newAlpha(((PagonColor)this.endColor.getValue()).getColor(), 25));
                     RenderUtil.drawCircle(RenderBuffers.LINES, 0.3F, 130, blockHitResult.method_17784(), blockHitResult.method_17780(), ((PagonColor)this.endColor.getValue()).getColor());
                  } else if (hitResult instanceof class_3966) {
                     class_3966 entityHitResult = (class_3966)hitResult;
                     RenderUtil.renderBox(RenderType.FILL, entityHitResult.method_17782().method_5829(), ColorUtil.newAlpha(((PagonColor)this.endColor.getValue()).getColor().brighter(), 100), ColorUtil.newAlpha(((PagonColor)this.endColor.getValue()).getColor().brighter(), 100));
                  }
               }
            }
         }

      }
   }

   public void drawTrail(MathUtil.Result result) {
      if (!result.getPoints().isEmpty()) {
         this.renderTrail(result, ((PagonColor)this.endColor.getValue()).getColor(), ((PagonColor)this.startColor.getValue()).getColor(), (class_243)result.getPoints().get(0));
      }
   }

   public void renderTrail(MathUtil.Result result, Color start, Color end, class_243 first) {
      class_243 lastPos = first;

      for(class_243 p : result.getPoints()) {
         double value = this.normalize((double)result.getPoints().indexOf(p), (double)0.0F, (double)result.getPoints().size());
         RenderUtil.renderLineFromPosToPos(lastPos, p, ColorUtil.interpolate((float)value, start, end), ColorUtil.interpolate((float)value, start, end), ((Number)this.lineWidth.getValue()).floatValue());
         lastPos = p;
      }

   }

   double normalize(double value, double min, double max) {
      return (value - min) / (max - min);
   }

   public String getDescription() {
      return "Trajectories: renders where your projectiles (bow, pearls, etc) are gonna land";
   }
}
