package org.itsmahir.pagon.api.management.notification.types;

import java.awt.Color;
import net.minecraft.class_332;
import org.itsmahir.pagon.api.gui.font.Fonts;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.render.ScaledResolution;
import org.itsmahir.pagon.api.utils.render.animation.Easing;
import org.itsmahir.pagon.api.utils.render.animation.type.ColorAnimation;
import org.itsmahir.pagon.api.utils.render.animation.type.DynamicAnimation;

public class TopNotification {
   private String text;
   private Timer timer = new Timer();
   private ColorAnimation animation;
   private DynamicAnimation verticalAnimation;
   boolean complete;
   boolean setIndex;
   private Color color;

   public TopNotification(String text, long disableTime, long animationSpeed, Color color) {
      this.text = text;
      this.color = color;
      this.timer.setDelay(disableTime);
      this.timer.resetDelay();
      this.animation = new ColorAnimation(color, ColorUtil.newAlpha(color, 0), Easing.CUBIC_IN_OUT, animationSpeed, false);
      this.animation.setState(true);
      this.complete = false;
      this.setIndex = false;
      this.verticalAnimation = new DynamicAnimation(0.0F, Easing.EXPO_IN_OUT, 200L);
      this.verticalAnimation.setStateHard(false);
   }

   public void draw(class_332 context, int index, ScaledResolution resolution) {
      this.animation(index);
      float offset = (float)resolution.getScaledWidth() / 2.0F + 1.0F - Fonts.getTextWidth(this.text) / 2.0F;
      float textY = 12.0F + this.verticalAnimation.getAnimationValue();
      Fonts.doOneText(context, this.text, offset, textY, this.animation.getColor(), true);
   }

   public void animation(int index) {
      this.animation.setState(!this.timer.isPassed());
      if (this.timer.isPassed() && this.animation.getScaledTime() == 0.0F) {
         this.complete = true;
      }

      if (!this.setIndex) {
         this.verticalAnimation.goal((float)index * Fonts.getTextHeight("AA"));
         this.verticalAnimation.setStateHard(true);
         this.setIndex = true;
      } else {
         this.verticalAnimation.goal((float)index * Fonts.getTextHeight("AA"));
      }
   }

   public String getText() {
      return this.text;
   }

   public Timer getTimer() {
      return this.timer;
   }

   public ColorAnimation getAnimation() {
      return this.animation;
   }

   public DynamicAnimation getVerticalAnimation() {
      return this.verticalAnimation;
   }

   public boolean isComplete() {
      return this.complete;
   }

   public boolean isSetIndex() {
      return this.setIndex;
   }

   public Color getColor() {
      return this.color;
   }

   public void setText(String text) {
      this.text = text;
   }

   public void setTimer(Timer timer) {
      this.timer = timer;
   }

   public void setAnimation(ColorAnimation animation) {
      this.animation = animation;
   }

   public void setVerticalAnimation(DynamicAnimation verticalAnimation) {
      this.verticalAnimation = verticalAnimation;
   }

   public void setComplete(boolean complete) {
      this.complete = complete;
   }

   public void setSetIndex(boolean setIndex) {
      this.setIndex = setIndex;
   }

   public void setColor(Color color) {
      this.color = color;
   }
}
