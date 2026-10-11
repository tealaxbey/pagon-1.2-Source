package org.itsmahir.pagon.api.feature.module;

import java.util.Map;
import net.minecraft.class_124;
import net.minecraft.class_327;
import org.itsmahir.pagon.api.binds.IBindable;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.management.BindManager;
import org.itsmahir.pagon.api.utils.chat.ChatMessage;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.api.value.custom.Bind;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.nativep.NativeBridge;

public class Module extends Feature implements IBindable, IMinecraft {
   Bind bind = new Bind();
   Value<Boolean> chatNotify = (new ValueBuilder<Boolean>()).withDescriptor("Chat Notify").withValue(true).register(this);

   public Module(String name, Feature.Category category) {
      super(name, category, Feature.FeatureType.Module);
      BindManager.INSTANCE.getBindables().add(this);
      NativeBridge.registerModule(name, category.name(), this.getDescription());
   }

   public Value<?> register(Value<?> value) {
      this.getValues().add(value);
      return value;
   }

   public Bind getBind() {
      return this.bind;
   }

   public void setBind(Bind bind) {
      this.bind = bind;
   }

   public void onEnable() {
      super.onEnable();
      NativeBridge.setModuleEnabled(this.getName(), true);
      NativeBridge.onEnable(this.getName());
      if (mc.field_1687 != null) {
         class_327 var10001 = mc.field_1772;
         String var10002 = this.getName();
         this.offset = (float)Math.negateExact(var10001.method_1727(var10002 + " [" + this.getHudInfo() + "]"));
      }

      if ((Boolean)this.chatNotify.getValue()) {
         String var1 = String.valueOf(class_124.field_1067);
         ChatUtils.sendMessage(new ChatMessage(var1 + this.getDisplayName() + String.valueOf(class_124.field_1070) + " " + String.valueOf(class_124.field_1060) + "enabled", true, this.getName().hashCode()));
      }

   }

   public void onDisable() {
      super.onDisable();
      NativeBridge.onDisable(this.getName());
      NativeBridge.setModuleEnabled(this.getName(), false);
      if ((Boolean)this.chatNotify.getValue()) {
         String var10002 = String.valueOf(class_124.field_1067);
         ChatUtils.sendMessage(new ChatMessage(var10002 + this.getDisplayName() + String.valueOf(class_124.field_1070) + " " + String.valueOf(class_124.field_1061) + "disabled", true, this.getName().hashCode()));
      }

   }

   public void load(Map<String, Object> objects) {
      super.load(objects);
      if (objects != null && objects.containsKey("bind") && objects.get("bind") != null) {
         Object bindObj = objects.get("bind");
         if (bindObj.toString().contains("MOUSE_")) {
            this.bind.setIsMouse(true);
            this.bind.setKey(Integer.parseInt(bindObj.toString().replace("MOUSE_", "")));
         } else if (bindObj instanceof Number) {
            this.bind.setKey(((Number)bindObj).intValue());
         } else {
            try {
               this.bind.setKey(Integer.parseInt(bindObj.toString()));
            } catch (NumberFormatException var4) {
            }
         }

      }
   }

   public Map<String, Object> save() {
      Map<String, Object> toSave = super.save();
      if (this.bind.getIsMouse()) {
         toSave.put("bind", "MOUSE_" + this.bind.getKey());
      } else {
         toSave.put("bind", this.bind.getKey());
      }

      return toSave;
   }

   public void loadModule(Map<String, Object> objects) {
      super.loadModule(objects);
      if (objects != null && objects.containsKey("bind") && objects.get("bind") != null) {
         Object bindObj = objects.get("bind");
         if (bindObj.toString().contains("MOUSE_")) {
            this.bind.setIsMouse(true);
            this.bind.setKey(Integer.parseInt(bindObj.toString().replace("MOUSE_", "")));
         } else if (bindObj instanceof Number) {
            this.bind.setKey(((Number)bindObj).intValue());
         } else {
            try {
               this.bind.setKey(Integer.parseInt(bindObj.toString()));
            } catch (NumberFormatException var4) {
            }
         }

      }
   }

   public int getKey() {
      return this.bind.getKey();
   }

   public void onKey() {
      if (!this.bind.getIsMouse()) {
         this.toggle();
      }

   }
}
