package org.itsmahir.pagon.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_742;
import net.minecraft.class_8685;
import org.itsmahir.pagon.impl.features.modules.client.Media;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({class_742.class})
public class MixinAbstractClientPlayerEntity {
   @ModifyReturnValue(
      method = {"getSkinTextures"},
      at = {@At("RETURN")}
   )
   private class_8685 modifySkinTextures(class_8685 original) {
      return Media.INSTANCE != null && Media.INSTANCE.isEnabled() && (Boolean)Media.INSTANCE.spoofSkin.getValue() && this == class_310.method_1551().field_1724 ? new class_8685(class_2960.method_60655("pagon", "textures/skin.png"), original.comp_1911(), original.comp_1627(), original.comp_1628(), original.comp_1629(), true) : original;
   }
}
