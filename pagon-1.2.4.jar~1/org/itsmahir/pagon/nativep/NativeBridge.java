package org.itsmahir.pagon.nativep;

import java.util.HashMap;
import java.util.Map;

public class NativeBridge {
   private static boolean initialized = false;
   private static boolean nativeAvailable = false;
   private static final Map<String, Boolean> fallbackStates = new HashMap();
   private static final Map<String, Map<String, String>> fallbackValues = new HashMap();

   public static void init() {
      if (!initialized) {
         initialized = true;

         try {
            NativeLoader.load();
            nativeAvailable = true;
         } catch (Throwable var1) {
            nativeAvailable = false;
         }

      }
   }

   public static boolean isNativeAvailable() {
      return nativeAvailable;
   }

   public static void registerModule(String name, String category, String description) {
      init();
      if (nativeAvailable) {
         NativeAPI.nativeRegisterModule(name, category, description);
      }

   }

   public static boolean isModuleEnabled(String name) {
      init();
      return nativeAvailable ? NativeAPI.nativeIsModuleEnabled(name) : (Boolean)fallbackStates.getOrDefault(name, false);
   }

   public static void setModuleEnabled(String name, boolean enabled) {
      init();
      if (nativeAvailable) {
         NativeAPI.nativeSetModuleEnabled(name, enabled);
      } else {
         fallbackStates.put(name, enabled);
      }

   }

   public static void registerValue(String moduleName, String valueName, String type, String defaultValue, String[] modes) {
      init();
      if (nativeAvailable) {
         NativeAPI.nativeRegisterValue(moduleName, valueName, type, defaultValue, modes);
      }

   }

   public static String getValue(String moduleName, String valueName) {
      init();
      if (nativeAvailable) {
         return NativeAPI.nativeGetValue(moduleName, valueName);
      } else {
         Map<String, String> vals = (Map)fallbackValues.get(moduleName);
         return vals != null ? (String)vals.getOrDefault(valueName, "") : "";
      }
   }

   public static void setValue(String moduleName, String valueName, String value) {
      init();
      if (nativeAvailable) {
         NativeAPI.nativeSetValue(moduleName, valueName, value);
      } else {
         ((Map)fallbackValues.computeIfAbsent(moduleName, (k) -> new HashMap())).put(valueName, value);
      }

   }

   public static void onEnable(String moduleName) {
      init();
      if (nativeAvailable) {
         NativeAPI.nativeOnEnable(moduleName);
      }

   }

   public static void onDisable(String moduleName) {
      init();
      if (nativeAvailable) {
         NativeAPI.nativeOnDisable(moduleName);
      }

   }

   public static void onTick(String moduleName) {
      if (nativeAvailable) {
         NativeAPI.nativeOnTick(moduleName);
      }

   }

   public static void onRender(String moduleName, float partialTicks) {
      if (nativeAvailable) {
         NativeAPI.nativeOnRender(moduleName, partialTicks);
      }

   }
}
