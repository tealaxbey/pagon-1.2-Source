package org.itsmahir.pagon.impl.features.modules.render;

import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;

public class ExtraTab extends Module {
   public static ExtraTab INSTANCE;

   public ExtraTab() {
      super("ExtraTab", Feature.Category.Render);
      INSTANCE = this;
   }

   public String getDescription() {
      return "ExtraTab: Modifies the tab so you can see everyone online";
   }
}
