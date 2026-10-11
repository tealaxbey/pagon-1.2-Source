package org.itsmahir.pagon.api.event.events.render;

import net.minecraft.class_437;
import org.itsmahir.pagon.api.event.Event;

public class ScreenEvent {
   public static class SetScreen extends Event {
      class_437 guiScreen;

      public SetScreen(class_437 guiScreen) {
         this.guiScreen = guiScreen;
      }

      public void setGuiScreen(class_437 guiScreen) {
         this.guiScreen = guiScreen;
      }

      public class_437 getGuiScreen() {
         return this.guiScreen;
      }
   }
}
