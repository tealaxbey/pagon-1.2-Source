package org.itsmahir.pagon.api.gui.context;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1041;
import net.minecraft.class_310;
import net.minecraft.class_332;
import org.itsmahir.pagon.api.gui.component.IComponent;
import org.itsmahir.pagon.api.gui.helpers.MouseHelper;
import org.itsmahir.pagon.api.gui.render.IRenderer;
import org.itsmahir.pagon.api.gui.theme.IColorScheme;
import org.itsmahir.pagon.api.gui.theme.IMetrics;
import org.itsmahir.pagon.api.utils.render.ScaledResolution;

public class Context {
   List<IComponent> components;
   IColorScheme colorScheme;
   IMetrics metrics;
   IRenderer renderer;
   float partialTicks = 0.0F;
   class_332 drawContext;
   MouseHelper helper;

   public Context(class_332 context, IColorScheme colorScheme, IMetrics metrics, IRenderer renderer, MouseHelper helper) {
      this.drawContext = context;
      this.components = new ArrayList();
      this.colorScheme = colorScheme;
      this.metrics = metrics;
      this.renderer = renderer;
      this.helper = helper;
   }

   public IComponent getHovering(MouseHelper mouseHelper) {
      IComponent hovering = null;

      for(IComponent component : this.getComponents()) {
         if (component.getDims().collideWithMouse(mouseHelper)) {
            hovering = component;
         }
      }

      return hovering;
   }

   public ScaledResolution getScaledResolution() {
      class_1041 window = class_310.method_1551().method_22683();
      return new ScaledResolution(window.method_4486(), window.method_4502());
   }

   public List<IComponent> getComponents() {
      return this.components;
   }

   public IColorScheme getColorScheme() {
      return this.colorScheme;
   }

   public IMetrics getMetrics() {
      return this.metrics;
   }

   public IRenderer getRenderer() {
      return this.renderer;
   }

   public float getPartialTicks() {
      return this.partialTicks;
   }

   public class_332 getDrawContext() {
      return this.drawContext;
   }

   public MouseHelper getHelper() {
      return this.helper;
   }

   public void setComponents(List<IComponent> components) {
      this.components = components;
   }

   public void setColorScheme(IColorScheme colorScheme) {
      this.colorScheme = colorScheme;
   }

   public void setMetrics(IMetrics metrics) {
      this.metrics = metrics;
   }

   public void setRenderer(IRenderer renderer) {
      this.renderer = renderer;
   }

   public void setPartialTicks(float partialTicks) {
      this.partialTicks = partialTicks;
   }

   public void setDrawContext(class_332 drawContext) {
      this.drawContext = drawContext;
   }

   public void setHelper(MouseHelper helper) {
      this.helper = helper;
   }
}
