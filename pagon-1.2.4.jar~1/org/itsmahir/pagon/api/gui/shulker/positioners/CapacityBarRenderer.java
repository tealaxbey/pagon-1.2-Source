package org.itsmahir.pagon.api.gui.shulker.positioners;

import net.minecraft.class_1799;
import net.minecraft.class_1921;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import org.itsmahir.pagon.api.gui.shulker.GlobalValues;
import org.itsmahir.pagon.api.gui.shulker.container.ContainerManager;

public class CapacityBarRenderer extends OverlayRenderer {
   public static final int CAPACITY_BAR_COLOR_FILL = class_3532.method_15353(0.4F, 0.4F, 1.0F);
   public static final int CAPACITY_BAR_COLOR_BACK = -16777216;
   float capacity;
   public int xBackgroundStart;
   public int yBackgroundStart;
   public int xBackgroundEnd;
   public int yBackgroundEnd;
   public int xCapacityStart;
   public int yCapacityStart;
   public int xCapacityEnd;
   public int yCapacityEnd;

   public CapacityBarRenderer(ContainerManager containerParser, class_1799 stack, int x, int y) {
      super(stack, x, y);
      this.capacity = containerParser.getCapacity();
   }

   protected boolean canDisplay() {
      return (!GlobalValues.hideWhenEmpty || this.capacity > 0.0F) && (!GlobalValues.hideWhenFull || this.capacity < 1.0F);
   }

   protected void calculatePositions() {
      int step = (int)((float)GlobalValues.length * this.capacity);
      int shadowHeight = GlobalValues.displayShadow ? 1 : 0;
      this.xBackgroundStart = this.stackX + GlobalValues.translateX;
      this.yBackgroundStart = this.stackY + GlobalValues.translateY;
      this.xBackgroundEnd = this.xBackgroundStart + GlobalValues.length;
      this.yBackgroundEnd = this.yBackgroundStart + GlobalValues.width + shadowHeight;
      this.xCapacityStart = this.xBackgroundStart;
      this.yCapacityStart = this.yBackgroundStart;
      this.xCapacityEnd = this.xBackgroundStart + step;
      this.yCapacityEnd = this.yCapacityStart + GlobalValues.width;
   }

   protected void render(class_332 context) {
      context.method_51739(class_1921.method_51785(), this.xBackgroundStart, this.yBackgroundStart, this.xBackgroundEnd, this.yBackgroundEnd, -16777216);
      context.method_51739(class_1921.method_51785(), this.xCapacityStart, this.yCapacityStart, this.xCapacityEnd, this.yCapacityEnd, CAPACITY_BAR_COLOR_FILL | -16777216);
   }

   public void renderOptional(class_332 context) {
      if (this.canDisplay()) {
         this.calculatePositions();
         this.render(context);
      }

   }
}
