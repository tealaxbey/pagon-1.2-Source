package org.itsmahir.pagon.nativep;

public class FakeFlyNative {
   public static native void nativeOnEnable();

   public static native void nativeOnDisable();

   public static native void nativeHandleTick(boolean var0, boolean var1, boolean var2, boolean var3, boolean var4, boolean var5, boolean var6);

   public static native void nativeHandleMove();

   public static native void nativeHandleTravel(boolean var0, boolean var1, boolean var2, boolean var3, boolean var4, boolean var5, int var6, boolean var7, boolean var8);

   public static native void nativeHandleJump();

   public static boolean isAvailable() {
      try {
         return NativeLoader.isLoaded();
      } catch (Throwable var1) {
         return false;
      }
   }

   static {
      try {
         NativeLoader.load();
      } catch (Throwable t) {
         System.err.println("[FakeFlyNative] Failed to load native library: " + t.getMessage());
      }

   }
}
