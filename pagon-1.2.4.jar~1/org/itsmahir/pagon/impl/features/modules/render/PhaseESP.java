package org.itsmahir.pagon.impl.features.modules.render;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.world.RenderType;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class PhaseESP extends Module {
   public static PhaseESP INSTANCE;
   Value<PagonColor> fill = (new ValueBuilder<PagonColor>()).withDescriptor("Fill").withValue(new PagonColor(0, 255, 218, 100)).register(this);
   Value<PagonColor> line = (new ValueBuilder<PagonColor>()).withDescriptor("Line").withValue(new PagonColor(255, 255, 255, 255)).register(this);
   Value<Number> height = (new ValueBuilder<Number>()).withDescriptor("Height").withValue(1).withRange(0, 2).register(this);
   public List<class_2338> phases = new ArrayList();

   public PhaseESP() {
      super("PhaseESP", Feature.Category.Render);
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onTick(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         this.phases.clear();
         this.phases.addAll(this.getPhaseBlocks());
      }
   }

   public List<class_2338> getPhaseBlocks() {
      List<class_2338> blocks = new ArrayList();
      class_238 bb = mc.field_1724.method_5829();

      for(class_2338 pos : BlockUtils.getAllInBox(bb.method_1002((double)0.0F, 1.2, (double)0.0F).method_1009(0.1, (double)0.0F, 0.1))) {
         if (!pos.equals(mc.field_1724.method_24515()) && mc.field_1687.method_8320(pos).method_51366() && !mc.field_1687.method_8320(pos.method_10074()).method_51366() && mc.field_1687.method_24794(pos.method_10074())) {
            blocks.add(pos);
         }
      }

      return blocks;
   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldEvent event) {
      if (!NullUtils.nullCheck()) {
         if (!this.phases.isEmpty()) {
            for(class_2338 pos : this.phases) {
               class_238 holeBB = new class_238(pos);
               holeBB = new class_238(holeBB.field_1323, holeBB.field_1322, holeBB.field_1321, holeBB.field_1320, holeBB.field_1322 + ((Number)this.height.getValue()).doubleValue(), holeBB.field_1324);
               RenderUtil.renderBox(RenderType.FILL, holeBB, ((PagonColor)this.fill.getValue()).getColor(), ((PagonColor)this.fill.getValue()).getColor());
               RenderUtil.renderBox(RenderType.LINES, holeBB, ((PagonColor)this.line.getValue()).getColor(), ((PagonColor)this.line.getValue()).getColor());
            }

         }
      }
   }

   public String getDescription() {
      return "PhaseESP: Highlight block ur colliding with if have air under them";
   }
}
