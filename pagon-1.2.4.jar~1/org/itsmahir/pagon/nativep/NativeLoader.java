package org.itsmahir.pagon.nativep;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import org.itsmahir.pagon.api.wrapper.IMinecraft;

public class NativeLoader implements IMinecraft {
   private static boolean loaded = false;

   public static void load() {
      if (!loaded) {
         try {
            String os = System.getProperty("os.name").toLowerCase();
            String libName;
            String resourcePath;
            if (os.contains("win")) {
               libName = "pagon_native.dll";
               resourcePath = "/natives/windows-x86-64/pagon_native.dll";
            } else {
               if (!os.contains("nix") && !os.contains("nux") && !os.contains("mac")) {
                  throw new UnsupportedOperationException("Unsupported OS: " + os);
               }

               libName = "libpagon_native.so";
               resourcePath = "/natives/linux-x86-64/libpagon_native.so";
            }

            File tempDir = new File(mc.field_1697, "pagon_natives");
            tempDir.mkdirs();
            File libFile = new File(tempDir, libName);
            InputStream in = NativeLoader.class.getResourceAsStream(resourcePath);

            try {
               if (in == null) {
                  throw new FileNotFoundException("Native library not found: " + resourcePath);
               }

               Files.copy(in, libFile.toPath(), new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
            } catch (Throwable var9) {
               if (in != null) {
                  try {
                     in.close();
                  } catch (Throwable var8) {
                     var9.addSuppressed(var8);
                  }
               }

               throw var9;
            }

            if (in != null) {
               in.close();
            }

            System.load(libFile.getAbsolutePath());
            loaded = true;
            System.out.println("[Pagon] Native library loaded: " + libFile.getAbsolutePath());
         } catch (Exception e) {
            throw new RuntimeException("[Pagon] Failed to load native library!", e);
         }
      }
   }

   public static boolean isLoaded() {
      return loaded;
   }
}
