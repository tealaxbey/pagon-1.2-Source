package org.itsmahir.pagon.impl.features.modules.render.fader;

import java.awt.Color;
import java.util.Map;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_3532;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.world.RenderType;
import org.itsmahir.pagon.impl.PagonMod;
import org.itsmahir.pagon.impl.features.modules.combat.SelfTrap;
import org.itsmahir.pagon.impl.features.modules.player.Scaffold;

public class FadeRenderer {
   public static FadeRenderer INSTANCE;

   public FadeRenderer() {
      PagonMod.EVENT_BUS.register(this);
   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldEvent event) {
      this.doFeetTrapRender();
      this.doSelfTrapRender();
      this.doScaffoldRender();
   }

   public void doFeetTrapRender() {
   }

   public void doSelfTrapRender() {
      if ((Boolean)SelfTrap.INSTANCE.render.getValue()) {
         for(Map.Entry<class_2338, Long> entry : SelfTrap.INSTANCE.renderPositions.entrySet()) {
            int fillAlpha = ((PagonColor)SelfTrap.INSTANCE.fill.getValue()).getAlpha();
            int lineAlpha = ((PagonColor)SelfTrap.INSTANCE.line.getValue()).getAlpha();
            long time = System.currentTimeMillis() - (Long)entry.getValue();
            double normal = MathUtil.normalize((double)time, (double)0.0F, ((Number)SelfTrap.INSTANCE.fadeTime.getValue()).doubleValue());
            normal = class_3532.method_15350(normal, (double)0.0F, (double)1.0F);
            normal = -normal;
            ++normal;
            fillAlpha = (int)((double)fillAlpha * normal);
            lineAlpha = (int)((double)lineAlpha * normal);
            Color fillColor = ColorUtil.newAlpha(((PagonColor)SelfTrap.INSTANCE.fill.getValue()).getColor(), fillAlpha);
            Color lineColor = ColorUtil.newAlpha(((PagonColor)SelfTrap.INSTANCE.line.getValue()).getColor(), lineAlpha);
            class_238 bb = new class_238((class_2338)entry.getKey());
            RenderUtil.renderBox(RenderType.FILL, bb, fillColor, fillColor);
            RenderUtil.renderBox(RenderType.LINES, bb, lineColor, lineColor);
            if (fillAlpha == 0 && lineAlpha == 0) {
               SelfTrap.INSTANCE.renderPositions.remove(entry.getKey());
            }
         }

      }
   }

   public void doScaffoldRender() {
      if ((Boolean)Scaffold.INSTANCE.render.getValue()) {
         for(Map.Entry<class_2338, Long> entry : Scaffold.INSTANCE.renderPositions.entrySet()) {
            int fillAlpha = ((PagonColor)Scaffold.INSTANCE.fill.getValue()).getAlpha();
            int lineAlpha = ((PagonColor)Scaffold.INSTANCE.line.getValue()).getAlpha();
            long time = System.currentTimeMillis() - (Long)entry.getValue();
            double normal = MathUtil.normalize((double)time, (double)0.0F, ((Number)Scaffold.INSTANCE.fadeTime.getValue()).doubleValue());
            normal = class_3532.method_15350(normal, (double)0.0F, (double)1.0F);
            normal = -normal;
            ++normal;
            fillAlpha = (int)((double)fillAlpha * normal);
            lineAlpha = (int)((double)lineAlpha * normal);
            Color fillColor = ColorUtil.newAlpha(((PagonColor)Scaffold.INSTANCE.fill.getValue()).getColor(), fillAlpha);
            Color lineColor = ColorUtil.newAlpha(((PagonColor)Scaffold.INSTANCE.line.getValue()).getColor(), lineAlpha);
            class_238 bb = new class_238((class_2338)entry.getKey());
            RenderUtil.renderBox(RenderType.FILL, bb, fillColor, fillColor);
            RenderUtil.renderBox(RenderType.LINES, bb, lineColor, lineColor);
            if (fillAlpha == 0 && lineAlpha == 0) {
               Scaffold.INSTANCE.renderPositions.remove(entry.getKey());
            }
         }

      }
   }
}
