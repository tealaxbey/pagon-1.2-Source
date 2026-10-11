package org.itsmahir.pagon.api.management;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.minecraft.class_124;
import net.minecraft.class_156;
import org.itsmahir.pagon.api.config.ISavable;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;

public class SavableManager implements IMinecraft {
   public static final File MAIN_FOLDER;
   public static SavableManager INSTANCE;
   public boolean isLoading = false;
   public Gson gson;
   final List<ISavable> savables = new ArrayList();
   private ExecutorService saveExecutor = Executors.newSingleThreadExecutor();
   private boolean saveScheduled = false;

   public SavableManager() {
      if (!MAIN_FOLDER.exists()) {
         MAIN_FOLDER.mkdir();
      }

      this.gson = (new GsonBuilder()).setPrettyPrinting().create();
      this.isLoading = false;
   }

   public List<ISavable> getSavables() {
      return this.savables;
   }

   public void load() {
      this.isLoading = true;

      for(ISavable savable : this.getSavables()) {
         try {
            String var10002 = MAIN_FOLDER.getAbsolutePath();
            File dir = new File(var10002 + File.separator + savable.getDirName());
            if (!dir.exists()) {
               dir.mkdirs();
            }

            var10002 = MAIN_FOLDER.getAbsolutePath();
            File file = new File(var10002 + File.separator + savable.getDirName() + File.separator + savable.getFileName());
            if (!file.exists()) {
               file.createNewFile();
            } else {
               FileReader reader = new FileReader(file);
               Map<String, Object> map = null;

               try {
                  map = (Map)this.gson.fromJson(reader, Map.class);
               } catch (Exception e) {
                  e.printStackTrace();
                  System.err.println("[Pagon] Corrupted config file detected, deleting: " + file.getAbsolutePath());
                  reader.close();
                  file.delete();
                  continue;
               }

               savable.load(map);
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

      this.isLoading = false;
   }

   public void save() throws IOException {
      System.out.println("Saving your config");

      for(ISavable savable : this.getSavables()) {
         String var10002 = MAIN_FOLDER.getAbsolutePath();
         File dir = new File(var10002 + File.separator + savable.getDirName());
         if (!dir.exists()) {
            dir.mkdirs();
         }

         var10002 = MAIN_FOLDER.getAbsolutePath();
         File file = new File(var10002 + File.separator + savable.getDirName() + File.separator + savable.getFileName());
         if (!file.exists()) {
            file.createNewFile();
         }

         try {
            FileWriter writer = new FileWriter(file);
            this.gson.toJson(savable.save(), writer);
            writer.close();
         } catch (Throwable exception) {
            exception.printStackTrace();
         }
      }

      SearchManager.INSTANCE.save();
   }

   public void saveAsync() {
      if (!this.saveScheduled) {
         this.saveScheduled = true;
         this.saveExecutor.execute(() -> {
            try {
               Thread.sleep(500L);
               this.save();
            } catch (Exception e) {
               e.printStackTrace();
            } finally {
               this.saveScheduled = false;
            }

         });
      }

   }

   public void saveModuleConfig(String name) throws IOException {
      System.out.println("Saving your config");
      String var10002 = MAIN_FOLDER.getAbsolutePath();
      File dir = new File(var10002 + File.separator + "configs" + File.separator);
      if (!dir.exists()) {
         dir.mkdirs();
      }

      var10002 = MAIN_FOLDER.getAbsolutePath();
      File file = new File(var10002 + File.separator + "configs" + File.separator + name + ".cfg");
      if (!file.exists()) {
         file.createNewFile();
      }

      Map<String, Object> jsonData = new HashMap();

      for(Feature feature : FeatureManager.INSTANCE.getFeatures()) {
         Map<String, Object> featureMap = new HashMap();
         featureMap.putAll(feature.save());
         jsonData.put(feature.getName(), featureMap);
      }

      try {
         FileWriter writer = new FileWriter(file);
         this.gson.toJson(jsonData, writer);
         writer.close();
      } catch (Throwable exception) {
         exception.printStackTrace();
      }

   }

   public void loadModuleConfig(String name) throws Exception {
      System.out.println("Loading config");
      String var10002 = MAIN_FOLDER.getAbsolutePath();
      File file = new File(var10002 + File.separator + "configs" + File.separator + name + ".cfg");
      if (!file.exists()) {
         throw new Exception("Could not find config " + name + ".cfg!");
      } else {
         FileReader reader = new FileReader(file);
         Map<String, Object> map = (Map)this.gson.fromJson(reader, Map.class);

         for(Feature feature : FeatureManager.INSTANCE.getFeatures()) {
            try {
               Map<String, Object> featureMap = (Map)map.get(feature.getName());
               feature.loadModule(featureMap);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }

      }
   }

   public void softLoadConfig(String name) throws Exception {
      System.out.println("Loading config");
      String var10002 = MAIN_FOLDER.getAbsolutePath();
      File file = new File(var10002 + File.separator + "configs" + File.separator + name + ".cfg");
      if (!file.exists()) {
         throw new Exception("Could not find config " + name + ".cfg!");
      } else {
         FileReader reader = new FileReader(file);
         Map<String, Object> map = (Map)this.gson.fromJson(reader, Map.class);

         for(Feature feature : FeatureManager.INSTANCE.getFeatures()) {
            if (!feature.getCategory().equals(Feature.Category.Render) && !feature.getCategory().equals(Feature.Category.Hud) && (!feature.getCategory().equals(Feature.Category.Client) || feature == AntiCheat.INSTANCE)) {
               try {
                  Map<String, Object> featureMap = (Map)map.get(feature.getName());
                  feature.softLoadModule(featureMap);
               } catch (Exception e) {
                  e.printStackTrace();
               }
            }
         }

      }
   }

   public void deleteModuleConfig(String name) throws Exception {
      System.out.println("Loading config");
      String var10002 = MAIN_FOLDER.getAbsolutePath();
      File file = new File(var10002 + File.separator + "configs" + File.separator + name + ".cfg");
      if (!file.exists()) {
         throw new Exception("Could not find config " + name + ".cfg!");
      } else {
         file.delete();
      }
   }

   public void listConfigs() throws Exception {
      String var10002 = MAIN_FOLDER.getAbsolutePath();
      File dir = new File(var10002 + File.separator + "configs");
      File[] directories = dir.listFiles(File::isFile);
      ChatUtils.sendMessage(String.valueOf(class_124.field_1078) + "Available Configs:");

      for(File file : directories) {
         System.out.println("" + file.getName().toString());
         ChatUtils.sendMessage(file.getName());
      }

   }

   public void openFolder() {
      String var10002 = MAIN_FOLDER.getAbsolutePath();
      File dir = new File(var10002 + File.separator + "configs");
      if (!dir.exists()) {
         dir.mkdir();
      }

      class_156.method_668().method_672(dir);
   }

   public static File createFileIfNotExists(String name, String exstension) throws IOException {
      File dir = new File(mc.field_1697.getAbsolutePath(), File.separator + "Pagon" + File.separator);
      if (!dir.exists()) {
         dir.mkdir();
      }

      File namedFile = new File(mc.field_1697.getAbsolutePath(), File.separator + "Pagon" + File.separator + name + "." + exstension);
      if (!namedFile.exists()) {
         namedFile.createNewFile();
      }

      return namedFile;
   }

   public static void createFileIfNotExistsRaw(File file) throws IOException {
      if (!file.exists()) {
         file.createNewFile();
      }

   }

   public static void appendToFile(BufferedWriter bufferedWriter, String text) throws IOException {
      if (bufferedWriter != null) {
         bufferedWriter.append(text + "\r\n");
      }

      assert bufferedWriter != null;

      bufferedWriter.close();
   }

   public static BufferedWriter makeWriter(File file, boolean append) throws IOException {
      return new BufferedWriter(new FileWriter(file, true));
   }

   public static void writeToFile(BufferedWriter bufferedWriter, String text) throws IOException {
      if (bufferedWriter != null) {
         bufferedWriter.write(text + "\r\n");
      }

   }

   public static void closeWriter(BufferedWriter bufferedWriter) throws IOException {
      bufferedWriter.close();
   }

   public static void clearFile(File file) throws IOException {
      PrintWriter printWriter = new PrintWriter(file.getAbsolutePath());
      printWriter.write("");
      printWriter.close();
   }

   static {
      String var10002 = System.getProperty("user.dir");
      MAIN_FOLDER = new File(var10002 + File.separator + "Pagon");
   }
}
