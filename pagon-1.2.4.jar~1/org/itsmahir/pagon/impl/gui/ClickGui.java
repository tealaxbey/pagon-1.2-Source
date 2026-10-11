package org.itsmahir.pagon.impl.gui;

import net.minecraft.class_332;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.gui.GUI;
import org.itsmahir.pagon.api.gui.component.IComponent;
import org.itsmahir.pagon.api.gui.context.Context;
import org.itsmahir.pagon.api.gui.helpers.MouseHelper;
import org.itsmahir.pagon.api.gui.helpers.Rect;
import org.itsmahir.pagon.api.gui.render.IRenderer;
import org.itsmahir.pagon.api.gui.theme.IColorScheme;
import org.itsmahir.pagon.api.gui.theme.IMetrics;
import org.itsmahir.pagon.impl.gui.components.CategoryFrame;
import org.itsmahir.pagon.impl.gui.renderer.Renderer;

public class ClickGui extends GUI {
   public static Context CONTEXT;
   public static ClickGui INSTANCE;

   public ClickGui() {
      super(CONTEXT);
   }

   public void addComponents() {
      super.addComponents();
      int offset = 100;

      for(Feature.Category category : Feature.Category.values()) {
         if (!category.equals(Feature.Category.Hud)) {
            this.getContext().getComponents().add(new CategoryFrame(category, new Rect(offset, 40, 100, 200)));
            offset += this.getContext().getMetrics().getFrameWidth() + 5;
         }
      }

   }

   public void enterGui(IColorScheme colorScheme, IMetrics metrics, IRenderer renderer) {
      super.enterGui(colorScheme, metrics, renderer);
      this.repositionFrames();
   }

   public void method_25394(class_332 drawContext, int mouseX, int mouseY, float delta) {
      super.method_25394(drawContext, mouseX, mouseY, delta);
   }

   public void repositionFrames() {
      int frameWidth = this.getContext().getMetrics().getFrameWidth();
      int offset = 10;

      for(IComponent component : this.getContext().getComponents()) {
         if (component instanceof CategoryFrame frame) {
            frame.getDims().setX(offset);
            frame.getDims().setY((int)((double)10.0F * ((double)frameWidth / (double)100.0F)) + 12);
            offset += frameWidth + (int)((double)5.0F * ((double)frameWidth / (double)100.0F));
         }
      }

   }

   static {
      CONTEXT = new Context((class_332)null, org.itsmahir.pagon.impl.features.modules.client.gui.ClickGui.INSTANCE, org.itsmahir.pagon.impl.features.modules.client.gui.ClickGui.INSTANCE, new Renderer(), (MouseHelper)null);
   }
}
