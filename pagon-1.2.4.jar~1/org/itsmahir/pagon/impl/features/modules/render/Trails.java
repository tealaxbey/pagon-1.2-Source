package org.itsmahir.pagon.impl.features.modules.render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.minecraft.class_1297;
import net.minecraft.class_1667;
import net.minecraft.class_1683;
import net.minecraft.class_1684;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class Trails extends Module {
   Value<Number> lineWidth = (new ValueBuilder<Number>()).withDescriptor("Line Width").withValue(2).withRange(0.1, 5).register(this);
   Value<Number> lifetime = (new ValueBuilder<Number>()).withDescriptor("Lifetime").withValue(1000).withRange(0, 5000).register(this);
   Value<Boolean> fade = (new ValueBuilder<Boolean>()).withDescriptor("Fade").withValue(true).register(this);
   Value<Boolean> xp = (new ValueBuilder<Boolean>()).withDescriptor("XP").withValue(false).register(this);
   Value<Boolean> arrow = (new ValueBuilder<Boolean>()).withDescriptor("Arrow").withValue(false).register(this);
   Value<PagonColor> startColor = (new ValueBuilder<PagonColor>()).withDescriptor("Start Color").withValue(new PagonColor(255, 255, 255)).register(this);
   Value<PagonColor> endColor = (new ValueBuilder<PagonColor>()).withDescriptor("End Color").withValue(new PagonColor(0, 255, 72)).register(this);
   Value<Boolean> self = (new ValueBuilder<Boolean>()).withDescriptor("Self").withValue(false).register(this);
   Value<Number> selfTime = (new ValueBuilder<Number>()).withDescriptor("Self Time").withValue(1000).withRange(0, 2000).register(this);
   Map<Integer, ItemTrail> trails = new HashMap();

   public Trails() {
      super("Trails", Feature.Category.Render);
   }

   @SubscribeEvent
   public void onTick(TickEvent.AfterClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         for(class_1297 entity : mc.field_1687.method_18112()) {
            if (this.allowEntity(entity)) {
               if (this.trails.containsKey(entity.method_5628())) {
                  if (!entity.method_5805()) {
                     if (((ItemTrail)this.trails.get(entity.method_5628())).timer.isPaused()) {
                        ((ItemTrail)this.trails.get(entity.method_5628())).timer.resetDelay();
                     }

                     ((ItemTrail)this.trails.get(entity.method_5628())).timer.setPaused(false);
                  } else {
                     ((ItemTrail)this.trails.get(entity.method_5628())).positions.add(new Position(entity.method_19538()));
                  }
               } else {
                  this.trails.put(entity.method_5628(), new ItemTrail(entity));
               }
            }
         }

         if ((Boolean)this.self.getValue()) {
            if (this.trails.containsKey(mc.field_1724.method_5628())) {
               ItemTrail playerTrail = (ItemTrail)this.trails.get(mc.field_1724.method_5628());
               playerTrail.timer.resetDelay();
               List<Position> toRemove = new ArrayList();

               for(Position position : playerTrail.positions) {
                  if (System.currentTimeMillis() - position.time > ((Number)this.selfTime.getValue()).longValue()) {
                     toRemove.add(position);
                  }
               }

               playerTrail.positions.removeAll(toRemove);
               playerTrail.positions.add(new Position(mc.field_1724.method_19538()));
            } else {
               this.trails.put(mc.field_1724.method_5628(), new ItemTrail(mc.field_1724));
            }
         } else if (this.trails.containsKey(mc.field_1724.method_5628())) {
            this.trails.remove(mc.field_1724.method_5628());
         }

      }
   }

   @SubscribeEvent
   public void onRender3D(RenderWorldEvent event) {
      if (!NullUtils.nullCheck()) {
         for(Map.Entry<Integer, ItemTrail> entry : this.trails.entrySet()) {
            if (!((ItemTrail)entry.getValue()).entity.method_5805() || mc.field_1687.method_8469((Integer)entry.getKey()) == null) {
               if (((ItemTrail)entry.getValue()).timer.isPaused()) {
                  ((ItemTrail)entry.getValue()).timer.resetDelay();
                  ((ItemTrail)entry.getValue()).timer.setDelay(((Number)this.lifetime.getValue()).longValue());
               }

               ((ItemTrail)entry.getValue()).timer.setPaused(false);
               if (((ItemTrail)entry.getValue()).timer.isPassed(((Number)this.lifetime.getValue()).longValue() + (long)((Boolean)this.fade.getValue() ? 500 : 0))) {
                  this.trails.remove(entry.getKey());
                  continue;
               }
            }

            this.drawTrail((ItemTrail)entry.getValue());
         }

      }
   }

   public void drawTrail(ItemTrail trail) {
      if (!trail.positions.isEmpty()) {
         int startAlpha = ((PagonColor)this.startColor.getValue()).getAlpha();
         int endAlpha = ((PagonColor)this.endColor.getValue()).getAlpha();
         if ((Boolean)this.fade.getValue() && !trail.timer.isPaused()) {
            double normal = this.normalize((double)System.currentTimeMillis() - ((double)trail.timer.getStartTime() + ((Number)this.lifetime.getValue()).doubleValue()), (double)0.0F, (double)500.0F);
            normal = class_3532.method_15350(normal, (double)0.0F, (double)1.0F);
            normal = -normal;
            ++normal;
            startAlpha = (int)((double)startAlpha * normal);
            endAlpha = (int)((double)endAlpha * normal);
         }

         Color start = ColorUtil.newAlpha(((PagonColor)this.startColor.getValue()).getColor(), startAlpha);
         Color end = ColorUtil.newAlpha(((PagonColor)this.endColor.getValue()).getColor(), endAlpha);
         this.renderTrail(trail, start, end, ((Position)trail.positions.get(0)).pos);
      }
   }

   public void renderTrail(ItemTrail trail, Color start, Color end, class_243 first) {
      class_243 lastPos = first;

      for(Position p : trail.positions) {
         double value = this.normalize((double)trail.positions.indexOf(p), (double)0.0F, (double)trail.positions.size());
         RenderUtil.renderLineFromPosToPos(lastPos, p.pos, ColorUtil.interpolate((float)value, start, end), ColorUtil.interpolate((float)value, start, end), ((Number)this.lineWidth.getValue()).floatValue());
         lastPos = p.pos;
      }

   }

   boolean allowEntity(class_1297 e) {
      if (!mc.field_1687.method_8621().method_61115(e.method_19538())) {
         return false;
      } else {
         return e instanceof class_1684 || e instanceof class_1683 && (Boolean)this.xp.getValue() || e instanceof class_1667 && (Boolean)this.arrow.getValue();
      }
   }

   double normalize(double value, double min, double max) {
      return (value - min) / (max - min);
   }

   public String getDescription() {
      return "Trails: renders trails/breadcrumbs behind various things";
   }

   public class ItemTrail {
      public class_1297 entity;
      public List<Position> positions;
      public Timer timer;

      public ItemTrail(class_1297 entity) {
         this.entity = entity;
         this.positions = new ArrayList();
         this.timer = new Timer();
         this.timer.setDelay(((Number)Trails.this.lifetime.getValue()).longValue());
         this.timer.setPaused(true);
      }
   }

   public static class Position {
      public class_243 pos;
      public long time;

      public Position(class_243 pos) {
         this.pos = pos;
         this.time = System.currentTimeMillis();
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            Position position = (Position)o;
            return this.time == position.time && Objects.equals(this.pos, position.pos);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.pos, this.time});
      }
   }
}
