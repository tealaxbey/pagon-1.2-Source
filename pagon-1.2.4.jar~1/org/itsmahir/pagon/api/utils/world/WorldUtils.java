package org.itsmahir.pagon.api.utils.world;

import net.minecraft.class_1937;
import net.minecraft.class_5321;
import org.itsmahir.pagon.api.wrapper.IMinecraft;

public class WorldUtils implements IMinecraft {
   public static int getDimensionId(class_5321<class_1937> type) {
      if (type.equals(class_1937.field_25180)) {
         return -1;
      } else if (type.equals(class_1937.field_25181)) {
         return 1;
      } else {
         return type.equals(class_1937.field_25179) ? 0 : 0;
      }
   }

   public static class_5321<class_1937> getActualDimension() {
      try {
         return mc.field_1687.method_27983();
      } catch (Exception var1) {
         return class_1937.field_25179;
      }
   }
}
