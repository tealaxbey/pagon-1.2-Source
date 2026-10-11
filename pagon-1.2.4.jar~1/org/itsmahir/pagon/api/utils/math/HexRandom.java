package org.itsmahir.pagon.api.utils.math;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.itsmahir.pagon.api.wrapper.IMinecraft;

public class HexRandom implements IMinecraft {
   private static final String HEX_CHARS = "0123456789abcdef";
   static Random RANDOM = ThreadLocalRandom.current();

   public static String generateRandomHex(int length) {
      StringBuilder hexString = new StringBuilder(length);

      for(int i = 0; i < length; ++i) {
         int index = RANDOM.nextInt("0123456789abcdef".length());
         hexString.append("0123456789abcdef".charAt(index));
      }

      return hexString.toString();
   }
}
