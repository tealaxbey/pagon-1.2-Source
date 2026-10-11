package org.itsmahir.pagon.api.management;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_7923;
import org.itsmahir.pagon.api.utils.StringUtils;
import org.itsmahir.pagon.api.utils.world.BlockUtils;

public class SearchManager {
   private final List<class_2248> SEARCH_BLOCKS = new ArrayList();
   public static SearchManager INSTANCE;
   public static final File SEARCH_FILE;
   private final Gson gson = (new GsonBuilder()).setPrettyPrinting().create();

   public SearchManager() {
      this.addBlocks();

      try {
         if (!SEARCH_FILE.exists()) {
            SEARCH_FILE.createNewFile();
         }

         this.intializeBlocks();
      } catch (Exception exception) {
         exception.printStackTrace();
      }

   }

   public void addBlocks() {
      this.SEARCH_BLOCKS.add(class_2246.field_10316);
      this.SEARCH_BLOCKS.add(class_2246.field_10443);
      this.SEARCH_BLOCKS.add(class_2246.field_10034);
      this.SEARCH_BLOCKS.add(class_2246.field_10200);
      this.SEARCH_BLOCKS.add(class_2246.field_10380);
      this.SEARCH_BLOCKS.addAll(BlockUtils.SHULKER_BLOCKS);
   }

   public boolean hasBlock(class_2248 block) {
      return this.SEARCH_BLOCKS.contains(block);
   }

   public class_2248 findBlock(String name) {
      return (class_2248)StringUtils.parseId(class_7923.field_41175, name);
   }

   public void intializeBlocks() throws IOException {
      FileReader reader = new FileReader(SEARCH_FILE);
      Map<String, Map<String, Object>> jsonObj = (Map)this.gson.fromJson(reader, Map.class);
      if (jsonObj != null) {
         for(Map.Entry<String, Map<String, Object>> pathEntry : jsonObj.entrySet()) {
            this.addBlock(String.valueOf(pathEntry.getValue()));
         }
      }

   }

   public void addBlock(String name) {
      class_2248 block;
      if ((block = (class_2248)StringUtils.parseId(class_7923.field_41175, name)) != null && !this.SEARCH_BLOCKS.contains(block)) {
         this.SEARCH_BLOCKS.add(block);
      }

   }

   public void removeBlock(class_2248 block) {
      this.SEARCH_BLOCKS.remove(block);
   }

   public void save() throws IOException {
      SavableManager.clearFile(SEARCH_FILE);
      Map<String, Object> jsonData = new HashMap();

      for(class_2248 block : this.SEARCH_BLOCKS) {
         String name = block.method_9518().getString();
         jsonData.put(name, class_7923.field_41175.method_10221(block).toString());
      }

      FileWriter writer = new FileWriter(SEARCH_FILE);
      this.gson.toJson(jsonData, writer);
      writer.close();
   }

   static {
      String var10002 = System.getProperty("user.dir");
      SEARCH_FILE = new File(var10002 + File.separator + "Pagon" + File.separator + "misc" + File.separator + "search.cfg");
   }
}
