package org.itsmahir.pagon.api.value;

import java.util.function.Consumer;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.gui.component.IComponent;
import org.itsmahir.pagon.api.management.SavableManager;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.nativep.NativeBridge;

public class Value<Type> {
   String name;
   String tag;
   Type min;
   Type max;
   String[] modes;
   int places = 9999;
   boolean active = true;
   IComponent component;
   Consumer<Value<Type>> action;
   boolean enabled;
   Type value;
   public Feature parentFeature;

   public Type getValue() {
      return this.value;
   }

   public <T> T getValueSafe() {
      return (T)(this.value != null ? this.value : null);
   }

   public Type getOrDefault(Type defaultValue) {
      return (Type)(this.value != null ? this.value : defaultValue);
   }

   public String getName() {
      return this.name;
   }

   public String getTag() {
      return this.tag;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setTag(String tag) {
      this.tag = tag;
   }

   public IComponent getComponent() {
      return this.component;
   }

   public void setComponent(IComponent component) {
      this.component = component;
   }

   public void setValue(Type value) {
      if (value instanceof Number && this.places != 9999) {
         Number newVal = 0;
         Number var4 = MathUtil.roundToPlaces((double)((Number)value).floatValue(), this.places);
         value = var4;
      }

      this.value = value;
      if (this.parentFeature != null) {
         NativeBridge.setValue(this.parentFeature.getName(), this.getName(), value != null ? value.toString() : "");
      }

      if (this.action != null) {
         this.action.accept(this);
      }

      try {
         if (SavableManager.INSTANCE != null && !SavableManager.INSTANCE.isLoading) {
            SavableManager.INSTANCE.saveAsync();
         }
      } catch (Exception var3) {
      }

   }

   public Type getMin() {
      return this.min;
   }

   public Type getMax() {
      return this.max;
   }

   public void setMin(Type min) {
      this.min = min;
   }

   public void setMax(Type max) {
      this.max = max;
   }

   public void setPlaces(int places) {
      this.places = places;
   }

   public String[] getModes() {
      return this.modes;
   }

   public void setModes(String[] modes) {
      this.modes = modes;
   }

   public Consumer<Value<Type>> getAction() {
      return this.action;
   }

   public void setAction(Consumer<Value<Type>> action) {
      this.action = action;
   }

   public boolean isActive() {
      return this.active;
   }

   public void setActive(boolean active) {
      this.active = active;
   }
}
