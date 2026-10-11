package org.itsmahir.pagon.api.management;

import java.util.ArrayList;
import java.util.List;
import org.itsmahir.pagon.api.binds.IBindable;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.key.KeyboardEvent;
import org.itsmahir.pagon.api.event.events.key.MouseEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.PagonMod;

public class BindManager implements IMinecraft {
   public static BindManager INSTANCE;
   List<IBindable> bindables = new ArrayList();

   public BindManager() {
      PagonMod.EVENT_BUS.register(this);
   }

   public List<IBindable> getBindables() {
      return this.bindables;
   }

   public void setBindables(List<IBindable> bindables) {
      this.bindables = bindables;
   }

   @SubscribeEvent
   public void onKey(KeyboardEvent event) {
      if (mc.field_1755 == null) {
         for(IBindable bindable : this.getBindables()) {
            if (bindable.getKey() == event.getKey() && event.getAction() == 1) {
               bindable.onKey();
            }
         }
      }

   }

   @SubscribeEvent
   public void onKey(MouseEvent event) {
      if (event.getType().equals(MouseEvent.Type.CLICK)) {
         for(Feature feature : FeatureManager.INSTANCE.getFeatures()) {
            if (feature instanceof Module) {
               Module module = (Module)feature;
               if (module.getBind().getIsMouse() && module.getBind().getKey() == event.getButton()) {
                  module.toggle();
               }
            }
         }

      }
   }
}
