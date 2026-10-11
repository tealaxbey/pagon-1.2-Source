package org.itsmahir.pagon.mixin;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_276;
import net.minecraft.class_279;
import net.minecraft.class_283;
import org.itsmahir.pagon.api.utils.ducks.IPostEffectProcessor;
import org.itsmahir.pagon.mixin.accessor.IPostEffectPass;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_279.class})
public class MixinPostEffectProcessor implements IPostEffectProcessor {
   @Shadow
   @Final
   private List<class_283> field_1497;
   @Shadow
   @Final
   private Map<String, class_276> field_1495;
   @Unique
   private final List<String> bufferTargets = new CopyOnWriteArrayList();

   public void overwriteBuffer(String name, class_276 buffer) {
      class_276 target = (class_276)this.field_1495.get(name);
      if (target != buffer) {
         if (target != null) {
            for(class_283 pass : this.field_1497) {
               if (pass.field_1536 == target) {
                  ((IPostEffectPass)pass).hookSetInput(buffer);
               }

               if (pass.field_1538 == target) {
                  ((IPostEffectPass)pass).hookSetOutput(buffer);
               }
            }

            this.field_1495.remove(name);
            this.bufferTargets.remove(name);
         }

         this.field_1495.put(name, buffer);
         this.bufferTargets.add(name);
      }
   }

   @Inject(
      method = {"close"},
      at = {@At("HEAD")}
   )
   private void hookClose(CallbackInfo ci) {
      for(String fakedBufferName : this.bufferTargets) {
         this.field_1495.remove(fakedBufferName);
      }

   }
}
