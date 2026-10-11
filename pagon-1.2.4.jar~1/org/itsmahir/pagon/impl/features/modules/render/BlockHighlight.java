package org.itsmahir.pagon.impl.features.modules.render;

import java.text.DecimalFormat;
import net.minecraft.class_1297;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_239.class_240;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.world.RenderType;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class BlockHighlight extends Module {
   public static BlockHighlight INSTANCE;
   Value<PagonColor> fill = (new ValueBuilder<PagonColor>()).withDescriptor("Fill").withValue(new PagonColor(255, 62, 62, 25)).register(this);
   Value<PagonColor> line = (new ValueBuilder<PagonColor>()).withDescriptor("Line").withValue(new PagonColor(255, 62, 62, 255)).register(this);
   Value<Boolean> entity = (new ValueBuilder<Boolean>()).withDescriptor("Entity").withValue(false).register(this);
   Value<PagonColor> entityFill;
   Value<PagonColor> entityLine;
   private double distance;

   public BlockHighlight() {
      super("BlockHighlight", Feature.Category.Render);
      this.entityFill = (new ValueBuilder<PagonColor>()).withDescriptor("Entity Fill").withValue(new PagonColor(255, 62, 62, 25)).withParentEnabled(true).withParent(this.entity).register(this);
      this.entityLine = (new ValueBuilder<PagonColor>()).withDescriptor("Entity Line").withValue(new PagonColor(255, 62, 62, 255)).withParent(this.entity).withParentEnabled(true).register(this);
      this.distance = (double)0.0F;
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldEvent event) {
      if (!NullUtils.nullCheck()) {
         class_238 render = null;
         class_239 result = mc.field_1765;
         if (result != null) {
            class_243 pos = mc.field_1724.method_33571();
            if ((Boolean)this.entity.getValue() && result.method_17783() == class_240.field_1331) {
               class_1297 entity = ((class_3966)result).method_17782();
               render = entity.method_5829();
               this.distance = pos.method_1022(entity.method_19538());
            } else if (result.method_17783() == class_240.field_1332) {
               class_2338 hpos = ((class_3965)result).method_17777();
               class_2680 state = mc.field_1687.method_8320(hpos);
               class_265 outlineShape = state.method_26218(mc.field_1687, hpos);
               if (outlineShape.method_1110()) {
                  return;
               }

               class_238 render1 = outlineShape.method_1107();
               render = new class_238((double)hpos.method_10263() + render1.field_1323, (double)hpos.method_10264() + render1.field_1322, (double)hpos.method_10260() + render1.field_1321, (double)hpos.method_10263() + render1.field_1320, (double)hpos.method_10264() + render1.field_1325, (double)hpos.method_10260() + render1.field_1324);
               this.distance = pos.method_1022(hpos.method_46558());
            }
         }

         if (render != null) {
            if (result.method_17783() == class_240.field_1331) {
               RenderUtil.renderBox(RenderType.FILL, render, ((PagonColor)this.entityFill.getValue()).getColor(), ((PagonColor)this.entityFill.getValue()).getColor());
               RenderUtil.renderBox(RenderType.LINES, render, ((PagonColor)this.entityLine.getValue()).getColor(), ((PagonColor)this.entityLine.getValue()).getColor());
            } else {
               RenderUtil.renderBox(RenderType.FILL, render, ((PagonColor)this.fill.getValue()).getColor(), ((PagonColor)this.fill.getValue()).getColor());
               RenderUtil.renderBox(RenderType.LINES, render, ((PagonColor)this.line.getValue()).getColor(), ((PagonColor)this.line.getValue()).getColor());
            }
         }

      }
   }

   public String getDescription() {
      return "BlockHighlight: highlights the block you are looking at";
   }

   public String getHudInfo() {
      DecimalFormat decimal = new DecimalFormat("0.0");
      return decimal.format(this.distance);
   }
}
