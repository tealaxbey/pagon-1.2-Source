package org.itsmahir.pagon.api.gui.shulker.positioners;

import net.minecraft.class_1799;
import net.minecraft.class_332;
import org.itsmahir.pagon.api.gui.shulker.GlobalValues;
import org.itsmahir.pagon.api.gui.shulker.container.ContainerManager;
import org.itsmahir.pagon.api.gui.shulker.data.IconScale;
import org.itsmahir.pagon.api.utils.ducks.IDrawContext;

public class IconRenderer extends OverlayRenderer {
   IconScale iconPositionOptions;
   public float scale;
   public float xOffset;
   public float yOffset;
   public float zOffset;

   public IconRenderer(ContainerManager containerParser, class_1799 displayStack, int x, int y) {
      super(displayStack, x, y);
      this.setPositionOptions(containerParser);
   }

   private void setPositionOptions(ContainerManager containerParser) {
      switch (containerParser.getContainerType()) {
         case SHULKER_BOX:
            this.iconPositionOptions = containerParser.getStackSize() > 1 ? GlobalValues.iconPositionOptionsStacked : GlobalValues.iconPositionOptionsGeneral;
            break;
         case BUNDLE:
            this.iconPositionOptions = GlobalValues.iconPositionOptionsBundle;
            break;
         case OTHER:
            this.iconPositionOptions = GlobalValues.iconPositionOptionsGeneral;
         case NONE:
      }

   }

   protected void calculatePositions() {
      this.xOffset = (float)this.iconPositionOptions.translateX - 8.0F;
      this.yOffset = (float)this.iconPositionOptions.translateY - 8.0F;
      this.zOffset = 100.0F + (float)(this.iconPositionOptions.translateZ * 10);
      this.scale = (float)this.iconPositionOptions.scale;
   }

   protected void render(class_332 context) {
      ((IDrawContext)context).adjustSize(true);
      context.method_51445(this.stack, this.stackX, this.stackY);
      ((IDrawContext)context).adjustSize(false);
   }

   public void renderOptional(class_332 context) {
      if (this.canDisplay()) {
         this.calculatePositions();
         this.render(context);
      }

   }

   protected boolean canDisplay() {
      return this.stack != null && this.stack.method_7909() != null;
   }
}
