package org.itsmahir.pagon.impl.features.modules.misc;

import net.minecraft.class_1810;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class NoEntityTrace extends Module {
   private static NoEntityTrace INSTANCE;
   Value<Boolean> Pickaxe = (new ValueBuilder<Boolean>()).withDescriptor("Pickaxe").withValue(false).withAction((s) -> {
   }).register(this);

   public NoEntityTrace() {
      super("NoEntityTrace", Feature.Category.Misc);
      INSTANCE = this;
   }

   public static boolean spoofTrace() {
      if (!INSTANCE.isEnabled()) {
         return false;
      } else {
         return !(Boolean)INSTANCE.Pickaxe.getValue() || mc.field_1724.method_6047().method_7909() instanceof class_1810;
      }
   }

   public String getDescription() {
      return "NoEntityTrace: Makes your crosshair trace past entity's";
   }
}
