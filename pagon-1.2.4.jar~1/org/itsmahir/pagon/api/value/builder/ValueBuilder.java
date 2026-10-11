package org.itsmahir.pagon.api.value.builder;

import java.util.function.Consumer;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.gui.component.IComponent;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.impl.gui.components.value.ICustomComponent;
import org.itsmahir.pagon.nativep.NativeBridge;

public class ValueBuilder<Type> extends Value<Type> {
   Value<String> parent;
   Value<Boolean> booleanParent;
   String page;
   boolean parentEnabled;

   public ValueBuilder<Type> withDescriptor(String name, String tag) {
      this.setName(name);
      this.setTag(tag);
      return this;
   }

   public ValueBuilder<Type> withDescriptor(String name) {
      this.setName(name);
      String camelCase = name.replace(" ", "");
      if (!camelCase.isEmpty()) {
         char[] chars = camelCase.toCharArray();
         chars[0] = Character.toLowerCase(chars[0]);
         camelCase = new String(chars);
      }

      this.setTag(camelCase);
      return this;
   }

   public ValueBuilder<Type> withValue(Type value) {
      this.setValue(value);
      return this;
   }

   public ValueBuilder<Type> withAction(Consumer<Value<Type>> action) {
      this.setAction(action);
      return this;
   }

   public ValueBuilder<Type> withRange(Type min, Type max) {
      this.setMin(min);
      this.setMax(max);
      return this;
   }

   public ValueBuilder<Type> withPlaces(int places) {
      this.setPlaces(places);
      return this;
   }

   public ValueBuilder<Type> withPageParent(Value<String> parent) {
      this.parent = parent;
      return this;
   }

   public ValueBuilder<Type> withParent(Value<Boolean> parent) {
      this.booleanParent = parent;
      return this;
   }

   public ValueBuilder<Type> withPage(String page) {
      this.page = page;
      return this;
   }

   public ValueBuilder<Type> withParentEnabled(boolean parentEnabled) {
      this.parentEnabled = parentEnabled;
      return this;
   }

   public boolean isActive() {
      boolean active = true;
      if (this.parent != null && !((String)this.parent.getValue()).equals(this.page)) {
         active = false;
      }

      if (this.booleanParent != null && active) {
         try {
            if ((Boolean)this.booleanParent.getValue() != this.parentEnabled) {
               active = false;
            }
         } catch (Exception var3) {
            ChatUtils.sendMessage(this.getName());
         }
      }

      return super.isActive() && active;
   }

   public ValueBuilder<Type> withModes(String... modes) {
      this.setModes(modes);
      return this;
   }

   public ValueBuilder<Type> withComponent(IComponent widget) {
      if (widget instanceof ICustomComponent comp) {
         comp.setValue(this);
         this.setComponent(widget);
         return this;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public Value<Type> register(Feature feature) {
      feature.getValues().add(this);
      this.parentFeature = feature;
      String typeName = "unknown";
      Type val = (Type)this.getValue();
      if (val instanceof Boolean) {
         typeName = "Boolean";
      } else if (val instanceof Number) {
         typeName = "Number";
      } else if (val instanceof String) {
         typeName = "String";
      } else if (val instanceof PagonColor) {
         typeName = "Color";
      }

      String defStr = val != null ? val.toString() : "";
      NativeBridge.registerValue(feature.getName(), this.getName(), typeName, defStr, this.getModes());
      return this;
   }
}
