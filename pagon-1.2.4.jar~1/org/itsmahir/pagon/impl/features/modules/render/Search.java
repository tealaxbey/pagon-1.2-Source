package org.itsmahir.pagon.impl.features.modules.render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2480;
import net.minecraft.class_2680;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.render.RenderBlockEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.SearchManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Pair;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.world.RenderType;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class Search extends Module {
   private ArrayList<class_2248> defaultBlocks;
   Value<PagonColor> defaultColor = (new ValueBuilder<PagonColor>()).withDescriptor("Color").withValue(new PagonColor(255, 0, 251, 255)).register(this);
   Value<Boolean> tracers = (new ValueBuilder<Boolean>()).withDescriptor("Tracers").withValue(false).withParentEnabled(false).register(this);
   Value<Number> lineWidth;
   public static Search INSTANCE;
   private CopyOnWriteArrayList<Pair<class_2338, class_2680>> foundBlocks;

   public Search() {
      super("Search", Feature.Category.Render);
      this.lineWidth = (new ValueBuilder<Number>()).withDescriptor("Width").withValue(0.8).withRange(0.1, 2).withParent(this.tracers).withParentEnabled(true).register(this);
      this.foundBlocks = new CopyOnWriteArrayList();
      INSTANCE = this;
   }

   public String getHudInfo() {
      return (Boolean)this.tracers.getValue() ? "Lines" : "Fill";
   }

   @SubscribeEvent
   public void onTick(TickEvent.ClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         if (!this.foundBlocks.isEmpty()) {
            this.foundBlocks.removeIf((e) -> mc.field_1687.method_8320((class_2338)e.key()).method_26204() != ((class_2680)e.value()).method_26204() || !SearchManager.INSTANCE.hasBlock(((class_2680)e.value()).method_26204()));
         }
      }
   }

   @SubscribeEvent
   public void onRenderBlock(RenderBlockEvent event) {
      if (SearchManager.INSTANCE.hasBlock(event.getState().method_26204()) && this.foundBlocks.stream().noneMatch((pair) -> ((class_2338)pair.key()).equals(event.getPos()))) {
         this.foundBlocks.add(new Pair(new class_2338(event.getPos()), event.getState()));
      }

   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldEvent event) {
      if (!NullUtils.nullCheck()) {
         if (!this.foundBlocks.isEmpty()) {
            for(Pair<class_2338, class_2680> pair : this.foundBlocks) {
               Color blockColor = this.getColor(((class_2680)pair.value()).method_26204());
               class_2338 pos = pair.key();
               class_238 renderBB = new class_238(pos);
               RenderUtil.renderBox(RenderType.FILL, renderBB, ColorUtil.newAlpha(blockColor, ((PagonColor)this.defaultColor.getValue()).getColor().getAlpha()), ColorUtil.newAlpha(blockColor, ((PagonColor)this.defaultColor.getValue()).getColor().getAlpha()));
               RenderUtil.renderBox(RenderType.LINES, renderBB, ColorUtil.newAlpha(blockColor, 255), ColorUtil.newAlpha(blockColor, 255));
               if ((Boolean)this.tracers.getValue()) {
                  RenderUtil.renderTracerLine(mc.field_1773.method_19418().method_19326(), renderBB.method_1005(), ColorUtil.newAlpha(blockColor, 255), ColorUtil.newAlpha(blockColor, 255), ((Number)this.lineWidth.getValue()).floatValue());
               }
            }

         }
      }
   }

   public Color getColor(class_2248 block) {
      if (block == class_2246.field_10540) {
         return new Color(90, 51, 160);
      } else if (block == class_2246.field_22423) {
         return (new Color(0, 0, 100)).brighter();
      } else if (block == class_2246.field_10316) {
         return new Color(87, 41, 255);
      } else if (block == class_2246.field_10443) {
         return (new Color(90, 51, 160)).brighter().brighter();
      } else if (block == class_2246.field_10034) {
         return new Color(200, 120, 50);
      } else if (block == class_2246.field_10380) {
         return new Color(200, 0, 50);
      } else if (block == class_2246.field_10363) {
         return new Color(255, 0, 0);
      } else if (block == class_2246.field_10343) {
         return new Color(255, 255, 255);
      } else if (block == class_2246.field_10181) {
         return new Color(100, 100, 100);
      } else if (block != class_2246.field_10442 && block != class_2246.field_29029) {
         if (block != class_2246.field_10571 && block != class_2246.field_29026 && block != class_2246.field_23077) {
            if (block != class_2246.field_10212 && block != class_2246.field_29027) {
               if (block != class_2246.field_10090 && block != class_2246.field_29028) {
                  if (block != class_2246.field_10080 && block != class_2246.field_29030) {
                     if (block != class_2246.field_27120 && block != class_2246.field_29221) {
                        if (block != class_2246.field_10013 && block != class_2246.field_29220) {
                           if (block != class_2246.field_10418 && block != class_2246.field_29219) {
                              if (block == class_2246.field_10213) {
                                 return Color.WHITE;
                              } else {
                                 return block instanceof class_2480 ? new Color(((class_2480)block).method_10528().method_7787()) : ((PagonColor)this.defaultColor.getValue()).getColor();
                              }
                           } else {
                              return Color.BLACK;
                           }
                        } else {
                           return Color.GREEN;
                        }
                     } else {
                        return Color.ORANGE;
                     }
                  } else {
                     return Color.RED;
                  }
               } else {
                  return Color.BLUE;
               }
            } else {
               return new Color(161, 157, 148);
            }
         } else {
            return new Color(255, 215, 0);
         }
      } else {
         return Color.CYAN;
      }
   }

   public void onEnable() {
      super.onEnable();
      if (!NullUtils.nullCheck()) {
         mc.field_1769.method_3279();
      }
   }

   public String getDescription() {
      return "Search: highlight any block in the world! for example: \"-search add deepslate_diamond_ore\"";
   }
}
