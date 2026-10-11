package org.itsmahir.pagon.api.management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.itsmahir.pagon.api.feature.Feature;

public class FeatureManager {
   public static FeatureManager INSTANCE;
   List<Feature> features = new ArrayList();
   public HashMap<String, Integer> MODULE_COUNTS = new HashMap();

   public Feature getClosestMatchingFeature(String text) {
      Feature bestFeature = null;
      double lowestLength = Double.MAX_VALUE;

      for(Feature feature : this.features) {
         if (Objects.equals(text, "")) {
            return feature;
         }

         if (text.equals(feature.getName())) {
            return feature;
         }

         if (text.length() <= feature.getName().length() && feature.getName().startsWith(text) && lowestLength > (double)feature.getName().length()) {
            lowestLength = (double)feature.getName().length();
            bestFeature = feature;
         }
      }

      return bestFeature;
   }

   public void initModuleCounts() {
      for(Feature feature : INSTANCE.getFeatures()) {
         this.MODULE_COUNTS.put(feature.getCategory().toString().toLowerCase(), (Integer)this.MODULE_COUNTS.getOrDefault(feature.getCategory().toString().toLowerCase(), 0) + 1);
      }

   }

   public void setFeatures(List<Feature> features) {
      this.features = features;
   }

   public void setMODULE_COUNTS(HashMap<String, Integer> MODULE_COUNTS) {
      this.MODULE_COUNTS = MODULE_COUNTS;
   }

   public List<Feature> getFeatures() {
      return this.features;
   }

   public HashMap<String, Integer> getMODULE_COUNTS() {
      return this.MODULE_COUNTS;
   }
}
