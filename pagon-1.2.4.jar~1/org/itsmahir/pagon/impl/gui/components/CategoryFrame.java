package org.itsmahir.pagon.impl.gui.components;

import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.gui.component.impl.FrameComponent;
import org.itsmahir.pagon.api.gui.helpers.Rect;
import org.itsmahir.pagon.api.management.FeatureManager;
import org.itsmahir.pagon.impl.gui.components.module.FeatureButton;

public class CategoryFrame extends FrameComponent {
   Feature.Category category;

   public CategoryFrame(Feature.Category category, Rect dims) {
      super(category.toString(), dims);
      this.category = category;

      for(Feature feature : FeatureManager.INSTANCE.getFeatures()) {
         if (feature.getCategory() == this.category) {
            this.getFlow().getComponents().add(new FeatureButton(feature, new Rect(0, 0, 0, 0)));
         }
      }

   }

   public Feature.Category getCategory() {
      return this.category;
   }

   public void setCategory(Feature.Category category) {
      this.category = category;
   }
}
