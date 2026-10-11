package org.itsmahir.pagon.api.utils;

import org.itsmahir.pagon.api.wrapper.IMinecraft;

public class NullUtils implements IMinecraft {
   public static boolean nullCheck() {
      return mc.field_1724 == null || mc.field_1687 == null || mc.field_1761 == null;
   }
}
