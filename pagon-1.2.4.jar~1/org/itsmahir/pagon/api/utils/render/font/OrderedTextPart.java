package org.itsmahir.pagon.api.utils.render.font;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.class_2583;
import net.minecraft.class_5481;

public class OrderedTextPart {
   private final class_2583 style;
   private final StringBuilder text = new StringBuilder();

   public String getText() {
      return this.text.toString();
   }

   public void append(char c) {
      this.text.append(c);
   }

   public static List<OrderedTextPart> getParts(class_5481 orderedText) {
      List<OrderedTextPart> parts = new ArrayList();
      AtomicReference<class_2583> styleRef = new AtomicReference((Object)null);
      orderedText.accept((charIndex, style, codePoint) -> {
         char c = (char)codePoint;
         if (styleRef.get() == null || !((class_2583)styleRef.get()).equals(style)) {
            styleRef.set(style);
            parts.add(new OrderedTextPart(style));
         }

         ((OrderedTextPart)parts.get(parts.size() - 1)).append(c);
         return true;
      });
      return parts;
   }

   public class_2583 getStyle() {
      return this.style;
   }

   public OrderedTextPart(class_2583 style) {
      this.style = style;
   }
}
