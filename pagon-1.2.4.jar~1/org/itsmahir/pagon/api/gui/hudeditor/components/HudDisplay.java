package org.itsmahir.pagon.api.gui.hudeditor.components;

import java.awt.Color;
import net.minecraft.class_9779;
import org.itsmahir.pagon.api.event.events.render.RenderGameOverlayEvent;
import org.itsmahir.pagon.api.feature.hud.HudComponent;
import org.itsmahir.pagon.api.gui.component.IComponent;
import org.itsmahir.pagon.api.gui.context.Context;
import org.itsmahir.pagon.api.gui.flow.Flow;
import org.itsmahir.pagon.api.gui.flow.impl.LinearFlow;
import org.itsmahir.pagon.api.gui.helpers.IDraggable;
import org.itsmahir.pagon.api.gui.helpers.MouseHelper;
import org.itsmahir.pagon.api.gui.helpers.Rect;
import org.itsmahir.pagon.api.gui.hudeditor.HudEditorGUI;
import org.itsmahir.pagon.api.gui.render.IRenderer;
import org.itsmahir.pagon.impl.gui.ClickGui;

public class HudDisplay implements IComponent, IDraggable {
   String name;
   Rect dims;
   Rect displayRect;
   boolean dragging;
   HudComponent hudComponent;
   int dragX;
   int dragY;
   Flow flow;

   public HudDisplay(Rect dims, HudComponent hudComponent) {
      this.name = hudComponent.getName();
      dims.setX(((Number)hudComponent.xPos.getValue()).intValue());
      dims.setY(((Number)hudComponent.yPos.getValue()).intValue());
      this.dims = dims;
      this.displayRect = dims;
      this.hudComponent = hudComponent;
      this.dragging = false;
      this.flow = new LinearFlow(new Rect(0, 0, 0, 0), 1);
   }

   public String getName() {
      return this.name;
   }

   public void draw(Context context, MouseHelper mouse) {
      this.getDims().setWidth(this.hudComponent.getWidth());
      this.getDims().setHeight(this.hudComponent.getHeight());
      this.getDims().setX(((Number)this.hudComponent.xPos.getValue()).intValue());
      this.getDims().setY(((Number)this.hudComponent.yPos.getValue()).intValue());
      if (this.hudComponent.isEnabled() && !this.hudComponent.immovable) {
         if (this.dragging) {
            this.dragEditor(this.getDims(), this.dragX, this.dragY, mouse, this.hudComponent);
         }

         this.getDims().setX(this.getDims().getX());
         this.flow.getDims().setY(this.getDims().getY());
         this.flow.positionComponents(context);
         this.displayRect = new Rect(this.getDims().getX(), this.getDims().getY(), this.getDims().getWidth(), this.getDims().getHeight());
         HudEditorGUI.CONTEXT.getRenderer().renderRect(this.getDims().fixRect(), this.getDims().collideWithMouse(mouse) ? new Color(255, 255, 255, 100) : new Color(0, 0, 0, 100), this.getDims().collideWithMouse(mouse) ? new Color(255, 255, 255, 100) : new Color(0, 0, 0, 100), IRenderer.RectMode.Fill, HudEditorGUI.CONTEXT);
         this.hudComponent.xPos.setValue(this.getDims().getX());
         this.hudComponent.yPos.setValue(this.getDims().getY());
         this.hudComponent.draw(new RenderGameOverlayEvent.Text(context.getDrawContext(), (class_9779)null));
         if (this.getDims().collideWithMouse(mouse)) {
            context.getDrawContext().method_51448().method_22903();
            context.getDrawContext().method_51448().method_46416(0.0F, 0.0F, 3000.0F);
            HudEditorGUI.CONTEXT.getRenderer().renderText(context.getDrawContext(), this.hudComponent.getName(), (float)(mouse.getX() + 10), (float)mouse.getY(), Color.WHITE, ClickGui.CONTEXT.getColorScheme().doesTextShadow());
            context.getDrawContext().method_51448().method_22909();
         }
      }

   }

   public void click(Context context, MouseHelper mouse, int button) {
      if (this.displayRect.collideWithMouse(mouse) && !this.hudComponent.immovable && button == 0 && this.isDraggable()) {
         this.dragging = true;
         this.dragX = mouse.getX() - this.getDims().getX();
         this.dragY = mouse.getY() - this.getDims().getY();
      }

      if (!this.hudComponent.immovable) {
         this.flow.click(context, mouse, button);
      }

   }

   public void release(Context context, MouseHelper mouse, int state) {
      this.dragging = false;
      if (!this.hudComponent.immovable) {
         this.flow.release(context, mouse, state);
      }

   }

   public void key(Context context, int key, char keycode) {
      if (!this.hudComponent.immovable) {
         this.flow.key(context, key, keycode);
      }

   }

   public void charTyped(Context context, char character) {
   }

   public int getLevel() {
      return 0;
   }

   public Rect getDims() {
      return this.dims;
   }

   public Flow getFlow() {
      return this.flow;
   }

   public void setFlow(Flow flow) {
      this.flow = flow;
   }

   public boolean isDraggable() {
      return true;
   }

   public boolean isActive() {
      return true;
   }
}
