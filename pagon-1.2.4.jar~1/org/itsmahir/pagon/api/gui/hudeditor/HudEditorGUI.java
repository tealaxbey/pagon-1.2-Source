package org.itsmahir.pagon.api.gui.hudeditor;

import net.minecraft.class_332;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.hud.HudComponent;
import org.itsmahir.pagon.api.gui.context.Context;
import org.itsmahir.pagon.api.gui.helpers.MouseHelper;
import org.itsmahir.pagon.api.gui.helpers.Rect;
import org.itsmahir.pagon.api.gui.hudeditor.components.HudDisplay;
import org.itsmahir.pagon.api.management.FeatureManager;
import org.itsmahir.pagon.impl.features.modules.client.gui.ClickGui;
import org.itsmahir.pagon.impl.gui.components.CategoryFrame;
import org.itsmahir.pagon.impl.gui.renderer.Renderer;

public class HudEditorGUI extends HudEditor {
   public static Context CONTEXT;
   public static HudEditorGUI INSTANCE;

   public HudEditorGUI() {
      super(CONTEXT);
   }

   public void addComponents() {
      super.addComponents();
      int offset = 100;

      for(Feature.Category category : Feature.Category.values()) {
         if (category == Feature.Category.Hud) {
            this.getContext().getComponents().add(new CategoryFrame(category, new Rect(offset, 40, 100, 200)));
            offset += this.getContext().getMetrics().getFrameWidth() + 10;
         }
      }

      for(Feature feature : FeatureManager.INSTANCE.getFeatures()) {
         if (feature.getCategory() == Feature.Category.Hud) {
            HudComponent hudModule = (HudComponent)feature;
            this.getContext().getComponents().add(new HudDisplay(new Rect(((Number)hudModule.xPos.getValue()).intValue(), ((Number)hudModule.yPos.getValue()).intValue(), hudModule.getWidth(), hudModule.getHeight()), hudModule));
         }
      }

   }

   static {
      CONTEXT = new Context((class_332)null, ClickGui.INSTANCE, ClickGui.INSTANCE, new Renderer(), (MouseHelper)null);
   }
}
