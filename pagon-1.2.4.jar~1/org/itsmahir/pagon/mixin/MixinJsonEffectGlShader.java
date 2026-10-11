package org.itsmahir.pagon.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.class_280;
import net.minecraft.class_281;
import net.minecraft.class_2960;
import net.minecraft.class_5912;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({class_280.class})
public class MixinJsonEffectGlShader {
   @WrapOperation(
      method = {"<init>"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/util/Identifier;ofVanilla(Ljava/lang/String;)Lnet/minecraft/util/Identifier;"
)}
   )
   private class_2960 ofVanilla0(String path, Operation<class_2960> original, class_5912 unused, String name) {
      if (!name.contains(":")) {
         return (class_2960)original.call(new Object[]{path});
      } else {
         class_2960 split = class_2960.method_60654(name);
         return class_2960.method_60655(split.method_12836(), "shaders/program/" + split.method_12832() + ".json");
      }
   }

   @WrapOperation(
      method = {"loadEffect"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/util/Identifier;ofVanilla(Ljava/lang/String;)Lnet/minecraft/util/Identifier;"
)}
   )
   private static class_2960 ofVanilla1(String path, Operation<class_2960> original, class_5912 unused, class_281.class_282 shaderType, String name) {
      if (!name.contains(":")) {
         return (class_2960)original.call(new Object[]{path});
      } else {
         class_2960 split = class_2960.method_60654(name);
         String var10000 = split.method_12836();
         String var10001 = split.method_12832();
         return class_2960.method_60655(var10000, "shaders/program/" + var10001 + shaderType.method_1284());
      }
   }
}
