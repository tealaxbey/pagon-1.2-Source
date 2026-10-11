package org.itsmahir.pagon.api.event.eventbus;

public class Priority {
   public static final byte SUPER_FIRST = 0;
   public static final byte MANAGER_FIRST = 1;
   public static final byte MODULE_FIRST = 2;
   public static final byte NORMAL = 3;
   public static final byte MODULE_LAST = 4;
   public static final byte MANAGER_LAST = 5;
   public static final byte[] VALUE_ARRAY = new byte[]{0, 1, 2, 3, 4, 5};
}
