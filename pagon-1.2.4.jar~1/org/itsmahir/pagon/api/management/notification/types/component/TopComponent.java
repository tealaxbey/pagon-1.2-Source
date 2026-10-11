package org.itsmahir.pagon.api.management.notification.types.component;

import java.awt.Color;
import net.minecraft.class_332;
import org.itsmahir.pagon.api.gui.font.Fonts;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.render.ScaledResolution;
import org.itsmahir.pagon.api.utils.render.animation.Easing;
import org.itsmahir.pagon.api.utils.render.animation.type.DynamicAnimation;
import org.itsmahir.pagon.api.utils.render.animation.type.DynamicColorAnimation;

public class TopComponent {
   private String text;
   private DynamicColorAnimation animation;
   private DynamicAnimation verticalAnimation;
   boolean setIndex;
   private Color color;
   boolean visible;

   public TopComponent(String text, long animationSpeed, Color color) {
      this.text = text;
      this.color = color;
      this.visible = false;
      this.animation = new DynamicColorAnimation(ColorUtil.newAlpha(color, 0), Easing.CUBIC_IN_OUT, animationSpeed);
      this.animation.setState(true);
      this.setIndex = false;
      this.verticalAnimation = new DynamicAnimation(0.0F, Easing.EXPO_IN_OUT, 200L);
      this.verticalAnimation.setStateHard(false);
   }

   public void draw(class_332 context, int index, ScaledResolution resolution) {
      this.animation(index);
      if (this.animation.getColor().getAlpha() == 0) {
         this.setIndex = false;
      } else {
         float offset = (float)resolution.getScaledWidth() / 2.0F + 1.0F - Fonts.getTextWidth(this.text) / 2.0F;
         float textY = 12.0F + this.verticalAnimation.getAnimationValue();
         Fonts.doOneText(context, this.text, offset, textY, this.animation.getColor(), true);
      }
   }

   public void animation(int index) {
      this.animation.gotoColor(this.visible ? this.color : ColorUtil.newAlpha(this.color, 0));
      if (!this.setIndex) {
         this.verticalAnimation.goal((float)index * Fonts.getTextHeight("AA"));
         this.verticalAnimation.setStateHard(true);
         this.setIndex = true;
      } else {
         this.verticalAnimation.goal((float)index * Fonts.getTextHeight("AA"));
      }
   }

   public boolean isComplete() {
      return this.animation.getColor().getAlpha() == 0 && !this.visible;
   }

   public String getText() {
      return this.text;
   }

   public DynamicColorAnimation getAnimation() {
      return this.animation;
   }

   public DynamicAnimation getVerticalAnimation() {
      return this.verticalAnimation;
   }

   public boolean isSetIndex() {
      return this.setIndex;
   }

   public Color getColor() {
      return this.color;
   }

   public boolean isVisible() {
      return this.visible;
   }

   public void setText(String text) {
      this.text = text;
   }

   public void setAnimation(DynamicColorAnimation animation) {
      this.animation = animation;
   }

   public void setVerticalAnimation(DynamicAnimation verticalAnimation) {
      this.verticalAnimation = verticalAnimation;
   }

   public void setSetIndex(boolean setIndex) {
      this.setIndex = setIndex;
   }

   public void setColor(Color color) {
      this.color = color;
   }

   public void setVisible(boolean visible) {
      this.visible = visible;
   }
}
