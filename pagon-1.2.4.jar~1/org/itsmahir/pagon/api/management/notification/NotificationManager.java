package org.itsmahir.pagon.api.management.notification;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_332;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.render.RenderGameOverlayEvent;
import org.itsmahir.pagon.api.management.notification.types.CrosshairNotification;
import org.itsmahir.pagon.api.management.notification.types.TopNotification;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.render.ScaledResolution;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.PagonMod;

public class NotificationManager implements IMinecraft {
   public static NotificationManager INSTANCE;
   public List<CrosshairNotification> crosshairNotifications;
   public List<TopNotification> topNotifications;
   ScaledResolution resolution;

   public NotificationManager() {
      INSTANCE = this;
      PagonMod.EVENT_BUS.register(this);
      this.crosshairNotifications = new ArrayList();
      this.topNotifications = new ArrayList();
   }

   @SubscribeEvent
   public void draw(RenderGameOverlayEvent.Text event) {
      if (!NullUtils.nullCheck()) {
         this.resolution = new ScaledResolution(mc);
         this.crosshairNotifications.removeIf(CrosshairNotification::isComplete);
         this.topNotifications.removeIf(TopNotification::isComplete);
         this.doCrosshairs(event.getContext());
         this.doTop(event.getContext());
      }
   }

   public void doCrosshairs(class_332 context) {
      if (!this.crosshairNotifications.isEmpty()) {
         int index = 0;

         for(CrosshairNotification notification : this.crosshairNotifications) {
            notification.draw(context, index, this.resolution);
            if (!notification.getTimer().isPassed()) {
               ++index;
            }
         }
      }

   }

   public void doTop(class_332 context) {
      int index = 0;
      if (!this.topNotifications.isEmpty()) {
         for(TopNotification notification : this.topNotifications) {
            notification.draw(context, index, this.resolution);
            ++index;
         }
      }

   }

   public void addNotification(CrosshairNotification notification) {
      this.crosshairNotifications.add(notification);
   }

   public void addNotification(TopNotification notification) {
      this.topNotifications.add(notification);
   }
}
