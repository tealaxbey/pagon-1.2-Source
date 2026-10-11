package org.itsmahir.pagon.api.utils.color;

import java.awt.Color;
import org.itsmahir.pagon.impl.features.modules.client.gui.Hud;

public class PagonColor {
   Color color;
   boolean sync;
   boolean unsyncable;

   public PagonColor(Color color, boolean sync) {
      this.sync = sync;
      this.color = color;
   }

   public PagonColor(Color color) {
      this.sync = false;
      this.color = color;
   }

   public PagonColor(int r, int g, int b) {
      this.color = new Color(r, g, b);
      this.sync = false;
   }

   public PagonColor(int r, int g, int b, int a) {
      this.color = new Color(r, g, b, a);
      this.sync = false;
   }

   public PagonColor(int r, int g, int b, int a, boolean unsyncable) {
      this.color = new Color(r, g, b, a);
      this.sync = false;
      this.unsyncable = unsyncable;
   }

   public PagonColor(int r, int g, int b, boolean unsyncable) {
      this.color = new Color(r, g, b);
      this.sync = false;
      this.unsyncable = unsyncable;
   }

   public Color getColor() {
      return this.sync && !this.unsyncable ? ColorUtil.newAlpha(Hud.getTextColor(0), this.color.getAlpha()) : this.color;
   }

   public Color getColorChatSync() {
      return this.sync && !this.unsyncable ? new Color(48, 184, 203, 0) : this.color;
   }

   public int getRed() {
      return this.getColor().getRed();
   }

   public int getGreen() {
      return this.getColor().getGreen();
   }

   public int getBlue() {
      return this.getColor().getBlue();
   }

   public float getGlRed() {
      return (float)this.getRed() / 255.0F;
   }

   public float getGlBlue() {
      return (float)this.getBlue() / 255.0F;
   }

   public float getGlGreen() {
      return (float)this.getGreen() / 255.0F;
   }

   public float getGlAlpha() {
      return (float)this.getAlpha() / 255.0F;
   }

   public int getAlpha() {
      return this.getColor().getAlpha();
   }

   public boolean isSyncing() {
      return this.sync;
   }

   public boolean isUnsyncable() {
      return this.unsyncable;
   }

   public void setColor(Color color) {
      this.color = color;
   }

   public void setSync(boolean sync) {
      this.sync = sync;
   }

   public void setUnsyncable(boolean unsyncable) {
      this.unsyncable = unsyncable;
   }
}
