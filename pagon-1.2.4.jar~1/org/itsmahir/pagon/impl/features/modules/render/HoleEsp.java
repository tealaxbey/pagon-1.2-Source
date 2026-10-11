package org.itsmahir.pagon.impl.features.modules.render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_3532;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.world.RenderType;
import org.itsmahir.pagon.api.utils.world.HoleUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class HoleEsp extends Module {
   Value<String> glowMode = (new ValueBuilder<String>()).withDescriptor("Glow Mode").withValue("Fade").withModes("Fade", "None").register(this);
   Value<String> animation = (new ValueBuilder<String>()).withDescriptor("Animation").withValue("None").withModes("None", "Fade", "Height").register(this);
   Value<Number> height = (new ValueBuilder<Number>()).withDescriptor("Height").withValue(1).withRange(0, 2).register(this);
   Value<Number> range = (new ValueBuilder<Number>()).withDescriptor("Range").withValue((double)5.0F).withRange((double)1.0F, (double)30.0F).register(this);
   Value<Boolean> doubles = (new ValueBuilder<Boolean>()).withDescriptor("Doubles").withValue(true).register(this);
   Value<PagonColor> bedrockFill = (new ValueBuilder<PagonColor>()).withDescriptor("Bedrock Fill").withValue(new PagonColor(0, 255, 0, 100)).register(this);
   Value<PagonColor> bedrockLine = (new ValueBuilder<PagonColor>()).withDescriptor("Bedrock Line").withValue(new PagonColor(255, 255, 255, 255)).register(this);
   Value<PagonColor> obbyFill = (new ValueBuilder<PagonColor>()).withDescriptor("Obby Fill").withValue(new PagonColor(0, 255, 218, 100)).register(this);
   Value<PagonColor> obbyLine = (new ValueBuilder<PagonColor>()).withDescriptor("Obby Line").withValue(new PagonColor(255, 255, 255, 255)).register(this);
   Value<PagonColor> doubleFill = (new ValueBuilder<PagonColor>()).withDescriptor("Double Fill").withValue(new PagonColor(255, 0, 11, 100)).register(this);
   Value<PagonColor> doubleLine = (new ValueBuilder<PagonColor>()).withDescriptor("Double Line").withValue(new PagonColor(255, 255, 255, 255)).register(this);
   Value<PagonColor> bedrockFill2;
   Value<PagonColor> bedrockLine2;
   Value<PagonColor> obbyFill2;
   Value<PagonColor> obbyLine2;
   Value<PagonColor> doubleFill2;
   Value<PagonColor> doubleLine2;
   Value<Boolean> voidHoles;
   Value<PagonColor> voidSafeFill;
   Value<PagonColor> voidSafeLine;
   Value<PagonColor> voidFill;
   Value<PagonColor> voidLine;
   Value<Number> voidHeight;
   ExecutorService service;
   volatile List<HoleUtils.Hole> holes;
   volatile List<class_2338> voidPositions;

   public HoleEsp() {
      super("HoleESP", Feature.Category.Render);
      this.bedrockFill2 = (new ValueBuilder<PagonColor>()).withDescriptor("Bedrock Fill2").withValue(new PagonColor(0, 255, 0, 0)).withPage("Fade").withPageParent(this.glowMode).register(this);
      this.bedrockLine2 = (new ValueBuilder<PagonColor>()).withDescriptor("Bedrock Line2").withValue(new PagonColor(255, 255, 255, 0)).withPage("Fade").withPageParent(this.glowMode).register(this);
      this.obbyFill2 = (new ValueBuilder<PagonColor>()).withDescriptor("Obby Fill2").withValue(new PagonColor(0, 255, 218, 0)).withPage("Fade").withPageParent(this.glowMode).register(this);
      this.obbyLine2 = (new ValueBuilder<PagonColor>()).withDescriptor("Obby Line2").withValue(new PagonColor(255, 255, 255, 0)).withPage("Fade").withPageParent(this.glowMode).register(this);
      this.doubleFill2 = (new ValueBuilder<PagonColor>()).withDescriptor("Double Fill2").withValue(new PagonColor(255, 0, 11, 0)).withPage("Fade").withPageParent(this.glowMode).register(this);
      this.doubleLine2 = (new ValueBuilder<PagonColor>()).withDescriptor("Double Line2").withValue(new PagonColor(255, 255, 255, 0)).withPage("Fade").withPageParent(this.glowMode).register(this);
      this.voidHoles = (new ValueBuilder<Boolean>()).withDescriptor("Void Holes").withValue(false).register(this);
      this.voidSafeFill = (new ValueBuilder<PagonColor>()).withDescriptor("Void Safe Fill").withValue(new PagonColor(170, 0, 255, 25)).register(this);
      this.voidSafeLine = (new ValueBuilder<PagonColor>()).withDescriptor("Void Safe Line").withValue(new PagonColor(170, 0, 255, 255)).register(this);
      this.voidFill = (new ValueBuilder<PagonColor>()).withDescriptor("Void Fill").withValue(new PagonColor(255, 1, 242, 25)).register(this);
      this.voidLine = (new ValueBuilder<PagonColor>()).withDescriptor("Void Line").withValue(new PagonColor(255, 0, 251, 255)).register(this);
      this.voidHeight = (new ValueBuilder<Number>()).withDescriptor("Void Height").withValue(0.2).withRange(0.1, (double)2.0F).register(this);
      this.service = Executors.newCachedThreadPool();
      this.holes = new ArrayList();
      this.voidPositions = new ArrayList();
   }

   @SubscribeEvent
   public void onTick(TickEvent.ClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         this.service.submit(() -> {
            this.holes = HoleUtils.getHoles((double)((Number)this.range.getValue()).floatValue(), mc.field_1724.method_24515(), (Boolean)this.doubles.getValue());
            if ((Boolean)this.voidHoles.getValue()) {
               this.voidPositions = this.getVoidHoles();
            }

         });
      }
   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldEvent event) {
      if (!NullUtils.nullCheck()) {
         if (!this.holes.isEmpty()) {
            for(HoleUtils.Hole hole : this.holes) {
               class_238 holeBB = hole.doubleHole ? new class_238((double)hole.pos1.method_10263(), (double)hole.pos1.method_10264(), (double)hole.pos1.method_10260(), (double)(hole.pos2.method_10263() + 1), (double)(hole.pos2.method_10264() + 1), (double)(hole.pos2.method_10260() + 1)) : new class_238(hole.pos1);
               Color fillColor = hole.bedrock ? ((PagonColor)this.bedrockFill.getValue()).getColor() : (hole.doubleHole ? ((PagonColor)this.doubleFill.getValue()).getColor() : ((PagonColor)this.obbyFill.getValue()).getColor());
               Color fillColor2 = hole.bedrock ? ((PagonColor)this.bedrockFill2.getValue()).getColor() : (hole.doubleHole ? ((PagonColor)this.doubleFill2.getValue()).getColor() : ((PagonColor)this.obbyFill2.getValue()).getColor());
               Color outlineColor = hole.bedrock ? ((PagonColor)this.bedrockLine.getValue()).getColor() : (hole.doubleHole ? ((PagonColor)this.doubleLine.getValue()).getColor() : ((PagonColor)this.obbyLine.getValue()).getColor());
               Color outlineColor2 = hole.bedrock ? ((PagonColor)this.bedrockLine2.getValue()).getColor() : (hole.doubleHole ? ((PagonColor)this.doubleLine2.getValue()).getColor() : ((PagonColor)this.obbyLine2.getValue()).getColor());
               double renderHeight = ((Number)this.height.getValue()).doubleValue();
               if (!((String)this.animation.getValue()).equals("None")) {
                  double fadeRange = (double)((Number)this.range.getValue()).floatValue() - (double)1.0F;
                  double rangeAnimation = class_3532.method_15350((fadeRange + (double)1.0F - mc.field_1724.method_33571().method_1022(holeBB.method_1005())) / fadeRange, (double)0.0F, (double)1.0F);
                  switch ((String)this.animation.getValue()) {
                     case "Fade":
                        fillColor = ColorUtil.newAlpha(fillColor, (int)((double)fillColor.getAlpha() * rangeAnimation));
                        fillColor2 = ColorUtil.newAlpha(fillColor2, (int)((double)fillColor2.getAlpha() * rangeAnimation));
                        outlineColor = ColorUtil.newAlpha(outlineColor, (int)((double)outlineColor.getAlpha() * rangeAnimation));
                        outlineColor2 = ColorUtil.newAlpha(outlineColor2, (int)((double)outlineColor2.getAlpha() * rangeAnimation));
                        break;
                     case "Height":
                        renderHeight = ((Number)this.height.getValue()).doubleValue() * rangeAnimation;
                        if (renderHeight == (double)0.0F) {
                           continue;
                        }
                  }
               }

               holeBB = new class_238(holeBB.field_1323, holeBB.field_1322, holeBB.field_1321, holeBB.field_1320, holeBB.field_1322 + renderHeight, holeBB.field_1324);
               switch ((String)this.glowMode.getValue()) {
                  case "None":
                     RenderUtil.renderBox(RenderType.FILL, holeBB, fillColor, fillColor);
                     RenderUtil.renderBox(RenderType.LINES, holeBB, outlineColor, outlineColor);
                     break;
                  case "Fade":
                     RenderUtil.renderBox(RenderType.LINES, holeBB, outlineColor, outlineColor2);
                     RenderUtil.renderBox(RenderType.FILL, holeBB, fillColor, fillColor2);
               }
            }

            if ((Boolean)this.voidHoles.getValue()) {
               for(class_2338 pos : this.voidPositions) {
                  boolean safe = mc.field_1687.method_8320(pos).method_26204() != class_2246.field_10124;
                  Color fill = safe ? ((PagonColor)this.voidSafeFill.getValue()).getColor() : ((PagonColor)this.voidFill.getValue()).getColor();
                  Color line = safe ? ((PagonColor)this.voidSafeLine.getValue()).getColor() : ((PagonColor)this.voidLine.getValue()).getColor();
                  class_238 bb = new class_238(pos);
                  bb = new class_238(bb.field_1323, bb.field_1322, bb.field_1321, bb.field_1320, bb.field_1322 + (double)((Number)this.voidHeight.getValue()).floatValue(), bb.field_1324);
                  RenderUtil.renderBox(RenderType.FILL, bb, fill, fill);
                  RenderUtil.renderBox(RenderType.LINES, bb, line, line);
               }
            }

         }
      }
   }

   public List<class_2338> getVoidHoles() {
      List<class_2338> voids = new ArrayList();

      for(int x = -((Number)this.range.getValue()).intValue(); x < ((Number)this.range.getValue()).intValue(); ++x) {
         for(int z = -((Number)this.range.getValue()).intValue(); z < ((Number)this.range.getValue()).intValue(); ++z) {
            class_2338 pos = new class_2338((int)(mc.field_1724.method_23317() + (double)x), mc.field_1687.method_31607(), (int)(mc.field_1724.method_23321() + (double)z));
            if (mc.field_1687.method_8320(pos).method_26204() != class_2246.field_9987) {
               voids.add(pos);
            }
         }
      }

      return voids;
   }

   public String getDescription() {
      return "HoleESP: see safe holes that prevents crystal damage";
   }
}
