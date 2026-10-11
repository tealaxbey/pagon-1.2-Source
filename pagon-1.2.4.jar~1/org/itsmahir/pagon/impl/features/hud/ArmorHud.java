package org.itsmahir.pagon.impl.features.hud;

import java.awt.Color;
import net.minecraft.class_1799;
import net.minecraft.class_332;
import net.minecraft.class_3486;
import net.minecraft.class_3532;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.render.RenderGameOverlayEvent;
import org.itsmahir.pagon.api.feature.hud.HudComponent;
import org.itsmahir.pagon.api.gui.font.Fonts;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.render.ScaledResolution;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.client.gui.Hud;
import org.itsmahir.pagon.impl.gui.ClickGui;

public class ArmorHud extends HudComponent {
   Value<Boolean> percentIcon = (new ValueBuilder<Boolean>()).withDescriptor("Percent").withValue(false).register(this);
   Value<Boolean> small = (new ValueBuilder<Boolean>()).withDescriptor("Small").withValue(false).register(this);
   Value<Boolean> triColor = (new ValueBuilder<Boolean>()).withDescriptor("Tri Color").withValue(false).register(this);
   public Value<PagonColor> armorColorA;
   public Value<PagonColor> armorColorB;
   public Value<PagonColor> armorColorC;

   public ArmorHud() {
      super("ArmorHud");
      this.armorColorA = (new ValueBuilder<PagonColor>()).withDescriptor("High Color").withValue(new PagonColor(0, 255, 255, 255)).withParent(this.triColor).withParentEnabled(true).register(this);
      this.armorColorB = (new ValueBuilder<PagonColor>()).withDescriptor("Middle Color").withValue(new PagonColor(0, 0, 255, 255)).withParent(this.triColor).withParentEnabled(true).register(this);
      this.armorColorC = (new ValueBuilder<PagonColor>()).withDescriptor("Low Color").withValue(new PagonColor(87, 8, 97)).withParent(this.triColor).withParentEnabled(true).register(this);
      this.immovable = true;
   }

   @SubscribeEvent
   public void draw(RenderGameOverlayEvent.Text event) {
      super.draw(event);
      if (!NullUtils.nullCheck() && !this.renderCheck(event)) {
         this.renderArmorHUD(event.getContext(), true);
      }
   }

   public void renderArmorHUD(class_332 context, boolean percent) {
      ScaledResolution resolution = new ScaledResolution(mc);
      int width = resolution.getScaledWidth();
      int height = resolution.getScaledHeight();
      int i = width / 2;
      int iteration = 0;
      int y = height - 55 - (mc.field_1724.method_5777(class_3486.field_15517) ? 10 : 0);

      for(class_1799 is : mc.field_1724.method_31548().field_7548) {
         ++iteration;
         if (!is.method_7960()) {
            int x = i - 90 + (9 - iteration) * 20 + 2;
            context.method_51427(is, x, y);
            context.method_51431(mc.field_1772, is, x, y);
            String s = is.method_7947() > 1 ? "" + is.method_7947() : "";
            Fonts.doOneText(context, s, (float)(x + 19 - 2 - ClickGui.CONTEXT.getRenderer().getTextWidth(s)), (float)(y + 9), Hud.getTextColor(y + 9), true);
            if (percent) {
               float green = ((float)is.method_7936() - (float)is.method_7919()) / (float)is.method_7936();
               float red = 1.0F - green;
               int dmg = 100 - (int)(red * 100.0F);
               if ((Boolean)this.small.getValue()) {
                  context.method_51448().method_22903();
                  context.method_51448().method_22905(0.625F, 0.625F, 1.0F);
                  Fonts.doOneText(context, dmg + ((Boolean)this.percentIcon.getValue() ? "%" : ""), (float)(x + 6) * 1.6F - (float)ClickGui.CONTEXT.getRenderer().getTextWidth(dmg + ((Boolean)this.percentIcon.getValue() ? "%" : "")) / 2.0F * 0.6F, (float)y * 1.6F - 11.0F, this.getArmorColor(dmg, y + 9), true);
                  context.method_51448().method_22909();
               } else {
                  Fonts.doOneText(context, dmg + ((Boolean)this.percentIcon.getValue() ? "%" : ""), (float)(x + 8) - (float)ClickGui.CONTEXT.getRenderer().getTextWidth(dmg + ((Boolean)this.percentIcon.getValue() ? "%" : "")) / 2.0F, (float)(y - 9), this.getArmorColor(dmg, y + 9), true);
               }
            }
         }
      }

      class_1799 offHand = mc.field_1724.method_6079();
      if (!offHand.method_7960()) {
         int x = i + 90 + 2;
         context.method_51427(offHand, x, y);
         context.method_51431(mc.field_1772, offHand, x, y);
         if (percent && offHand.method_7963()) {
            float green = ((float)offHand.method_7936() - (float)offHand.method_7919()) / (float)offHand.method_7936();
            int dmg = 100 - (int)((1.0F - green) * 100.0F);
            Fonts.doOneText(context, dmg + ((Boolean)this.percentIcon.getValue() ? "%" : ""), (float)(x + 8) - (float)ClickGui.CONTEXT.getRenderer().getTextWidth(dmg + ((Boolean)this.percentIcon.getValue() ? "%" : "")) / 2.0F, (float)(y - 9), this.getArmorColor(dmg, y + 9), true);
         }
      }

   }

   public Color getArmorColor(int dmg, int y) {
      if ((Boolean)this.triColor.getValue()) {
         return dmg < 50 ? ColorUtil.interpolate((float)class_3532.method_15350(MathUtil.normalize((double)dmg, (double)1.0F, (double)50.0F), (double)0.0F, (double)1.0F), ((PagonColor)this.armorColorB.getValue()).getColor(), ((PagonColor)this.armorColorC.getValue()).getColor()) : ColorUtil.interpolate((float)class_3532.method_15350(MathUtil.normalize((double)dmg, (double)50.0F, (double)100.0F), (double)0.0F, (double)1.0F), ((PagonColor)this.armorColorA.getValue()).getColor(), ((PagonColor)this.armorColorB.getValue()).getColor());
      } else {
         return Hud.getTextColor(y);
      }
   }

   public String getDescription() {
      return "ArmorHud: Renders your Armor on screen";
   }
}
