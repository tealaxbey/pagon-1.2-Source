package org.itsmahir.pagon.api.gui;

import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;
import org.itsmahir.pagon.api.gui.component.IComponent;
import org.itsmahir.pagon.api.gui.context.Context;
import org.itsmahir.pagon.api.gui.helpers.MouseHelper;
import org.itsmahir.pagon.api.gui.render.IRenderer;
import org.itsmahir.pagon.api.gui.theme.IColorScheme;
import org.itsmahir.pagon.api.gui.theme.IMetrics;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.wrapper.IMinecraft;

public class GUI extends class_437 implements IMinecraft {
   Context context;
   Timer typingTimer = new Timer();
   public static boolean typeCounter = false;
   public static int bindCounter = 0;

   public GUI(Context context) {
      super(class_2561.method_30163("Pagon"));
      this.context = context;
      this.addComponents();
   }

   public void addComponents() {
   }

   public void method_25394(class_332 drawContext, int mouseX, int mouseY, float delta) {
      if (this.typingTimer.isPassed(500L)) {
         typeCounter = !typeCounter;
         this.typingTimer.resetDelay();
         if (bindCounter == 3) {
            bindCounter = 0;
         }

         ++bindCounter;
      }

      this.context.setDrawContext(drawContext);
      MouseHelper mouse = new MouseHelper(mouseX, mouseY);
      this.context.setHelper(mouse);
      this.context.setPartialTicks(delta);
      this.getContext().getRenderer().renderBackground(this.getContext());

      for(IComponent component : this.context.getComponents()) {
         if (component.isActive()) {
            component.draw(this.context, mouse);
         }

         drawContext.method_51452();
      }

      this.getContext().getRenderer().renderLast(this.getContext());
      drawContext.method_51452();
   }

   public boolean method_25401(double mouseX, double mouseY, double amount, double horizontal) {
      MouseHelper mouse = new MouseHelper((int)mouseX, (int)mouseY);
      if (this.context.getHovering(mouse) != null) {
         this.context.getHovering(mouse).getDims().setY(this.context.getHovering(mouse).getDims().getY() + (int)horizontal * 10);
      }

      return super.method_25401(mouseX, mouseY, amount, horizontal);
   }

   public boolean method_25402(double mouseX, double mouseY, int button) {
      MouseHelper mouse = new MouseHelper((int)mouseX, (int)mouseY);
      IComponent hovered = this.context.getHovering(mouse);
      if (hovered != null && hovered.isActive()) {
         hovered.click(this.context, mouse, button);
      }

      return super.method_25402(mouseX, mouseY, button);
   }

   public boolean method_25406(double mouseX, double mouseY, int button) {
      MouseHelper mouse = new MouseHelper((int)mouseX, (int)mouseY);

      for(IComponent component : this.context.getComponents()) {
         if (component.isActive()) {
            component.release(this.context, mouse, button);
         }
      }

      return super.method_25406(mouseX, mouseY, button);
   }

   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
      for(IComponent component : this.context.getComponents()) {
         if (component.isActive()) {
            component.key(this.context, keyCode, (char)keyCode);
         }
      }

      return super.method_25404(keyCode, scanCode, modifiers);
   }

   public void onCharTyped(char character) {
      for(IComponent component : this.context.getComponents()) {
         if (component.isActive()) {
            component.charTyped(this.context, character);
         }
      }

   }

   public boolean method_25400(char chr, int modifiers) {
      this.onCharTyped(chr);
      return super.method_25400(chr, modifiers);
   }

   public void updateGUI(IColorScheme colorScheme, IMetrics metrics, IRenderer renderer) {
      this.context.setColorScheme(colorScheme);
      this.context.setMetrics(metrics);
      this.context.setRenderer(renderer);
   }

   public void enterGui(IColorScheme colorScheme, IMetrics metrics, IRenderer renderer) {
      this.context.setColorScheme(colorScheme);
      this.context.setMetrics(metrics);
      this.context.setRenderer(renderer);
      mc.method_1507(this);
   }

   public boolean method_25421() {
      return false;
   }

   public Context getContext() {
      return this.context;
   }
}
