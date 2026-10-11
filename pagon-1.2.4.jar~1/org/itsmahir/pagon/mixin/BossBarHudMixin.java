package org.itsmahir.pagon.mixin;

import java.util.Collection;
import java.util.Iterator;
import net.minecraft.class_2561;
import net.minecraft.class_337;
import net.minecraft.class_345;
import org.itsmahir.pagon.api.event.events.render.RenderGameOverlayEvent;
import org.itsmahir.pagon.impl.features.modules.render.NoRender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_337.class})
public class BossBarHudMixin {
   @Inject(
      method = {"render"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onRender(CallbackInfo info) {
      if (NoRender.INSTANCE.isEnabled() && ((String)NoRender.INSTANCE.boss.getValue()).equals("None")) {
         info.cancel();
      }

   }

   @Redirect(
      method = {"render"},
      at = @At(
   value = "INVOKE",
   target = "Ljava/util/Collection;iterator()Ljava/util/Iterator;"
)
   )
   public Iterator<class_345> onRender(Collection<class_345> collection) {
      RenderGameOverlayEvent.BossBar.Iterate event = new RenderGameOverlayEvent.BossBar.Iterate(collection.iterator());
      event.post();
      return event.getIterator();
   }

   @Redirect(
      method = {"render"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/gui/hud/ClientBossBar;getName()Lnet/minecraft/text/Text;"
)
   )
   public class_2561 onAsFormattedString(class_345 clientBossBar) {
      RenderGameOverlayEvent.BossBar.Text event = new RenderGameOverlayEvent.BossBar.Text(clientBossBar.method_5414(), clientBossBar);
      event.post();
      return event.name;
   }
}
