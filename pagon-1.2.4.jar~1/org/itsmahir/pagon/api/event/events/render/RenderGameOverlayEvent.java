package org.itsmahir.pagon.api.event.events.render;

import java.util.Iterator;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_345;
import net.minecraft.class_9779;
import org.itsmahir.pagon.api.event.Event;

public class RenderGameOverlayEvent extends Event {
   class_332 context;

   public RenderGameOverlayEvent(class_332 context) {
      this.context = context;
   }

   public class_332 getContext() {
      return this.context;
   }

   public void setContext(class_332 context) {
      this.context = context;
   }

   public static class Text extends RenderGameOverlayEvent {
      class_9779 counter;

      public Text(class_332 context, class_9779 tickCounter) {
         super(context);
         this.counter = tickCounter;
      }

      public class_9779 getCounter() {
         return this.counter;
      }
   }

   public static class BossBar {
      public static class Text extends Event {
         public class_345 bossBar;
         public class_2561 name;

         public Text(class_2561 name, class_345 bossBar) {
            this.name = name;
            this.bossBar = bossBar;
         }

         public class_345 getBossBar() {
            return this.bossBar;
         }

         public class_2561 getName() {
            return this.name;
         }
      }

      public static class Iterate extends Event {
         public Iterator<class_345> iterator;

         public Iterate(Iterator<class_345> iterator) {
            this.iterator = iterator;
         }

         public Iterator<class_345> getIterator() {
            return this.iterator;
         }
      }
   }
}
