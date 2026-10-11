package org.itsmahir.pagon.impl.features.modules.client;

import net.minecraft.class_1268;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class AntiCheat extends Module {
   public Value<String> acMode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Vanilla").withModes("Vanilla", "Strong").register(this);
   public Value<Boolean> strafeFix = (new ValueBuilder<Boolean>()).withDescriptor("Strafe Fix").withValue(true).register(this);
   public Value<String> multiTask = (new ValueBuilder<String>()).withDescriptor("MultiTask").withValue("None").withModes("None", "Soft", "Strong").register(this);
   public Value<Boolean> grimRotations = (new ValueBuilder<Boolean>()).withDescriptor("GrimRotations").withValue(false).register(this);
   public Value<Boolean> strictDirection = (new ValueBuilder<Boolean>()).withDescriptor("Strict Direction").withValue(false).register(this);
   public static AntiCheat INSTANCE;

   public AntiCheat() {
      super("AntiCheat", Feature.Category.Client);
      INSTANCE = this;
      super.setEnabled(true);
   }

   public void setEnabled(boolean enabled) {
      if (enabled) {
         super.setEnabled(true);
      }
   }

   public void setEnabledA(boolean enabled) {
      if (enabled) {
         super.setEnabledA(true);
      }
   }

   public void toggle() {
      if (!this.isEnabled()) {
         super.setEnabled(true);
      }

   }

   public String getDescription() {
      return "AntiCheat: manage global anticheat settings";
   }

   public void handleMultiTask() {
      if (!((String)this.multiTask.getValue()).equals("None")) {
         if (mc.field_1724.method_6115()) {
            mc.field_1761.method_2897(mc.field_1724);
         }

      }
   }

   public boolean isMultiTask() {
      if (!mc.field_1724.method_6115()) {
         return false;
      } else if (((String)this.multiTask.getValue()).equals("Strong")) {
         return true;
      } else {
         return ((String)this.multiTask.getValue()).equals("Soft") && !mc.field_1724.method_6058().equals(class_1268.field_5810);
      }
   }
}
