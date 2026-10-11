package org.itsmahir.pagon.nativep;

public class NativeAPI {
   public static native void nativeRegisterModule(String var0, String var1, String var2);

   public static native void nativeSetModuleEnabled(String var0, boolean var1);

   public static native boolean nativeIsModuleEnabled(String var0);

   public static native void nativeRegisterValue(String var0, String var1, String var2, String var3, String[] var4);

   public static native void nativeSetValue(String var0, String var1, String var2);

   public static native String nativeGetValue(String var0, String var1);

   public static native void nativeOnTick(String var0);

   public static native void nativeOnRender(String var0, float var1);

   public static native void nativeOnEnable(String var0);

   public static native void nativeOnDisable(String var0);

   public static native String[] nativeGetAllModules();

   public static native byte[] nativeDecrypt(byte[] var0, int var1);
}
