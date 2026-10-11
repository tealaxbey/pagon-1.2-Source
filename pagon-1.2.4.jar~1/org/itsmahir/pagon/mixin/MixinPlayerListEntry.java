package org.itsmahir.pagon.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.class_2960;
import net.minecraft.class_640;
import net.minecraft.class_8685;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.features.modules.client.Capes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_640.class})
public class MixinPlayerListEntry implements IMinecraft {
   @Shadow
   @Final
   private GameProfile field_3741;

   @Inject(
      method = {"getSkinTextures"},
      at = {@At("TAIL")},
      cancellable = true
   )
   private void getSkinTextures(CallbackInfoReturnable<class_8685> info) {
      if (this.field_3741.getName().equals(mc.field_1724.method_7334().getName()) && this.field_3741.getId().equals(mc.field_1724.method_7334().getId()) && Capes.INSTANCE.isEnabled()) {
         class_2960 identifier = class_2960.method_60655("pagon", "capes/" + ((String)Capes.INSTANCE.capeMode.getValue()).toLowerCase() + ".png");
         class_8685 texture = (class_8685)info.getReturnValue();
         info.setReturnValue(new class_8685(texture.comp_1626(), texture.comp_1911(), identifier, identifier, texture.comp_1629(), texture.comp_1630()));
      }

   }
}
