package org.itsmahir.pagon.api.feature;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.itsmahir.pagon.api.config.ISavable;
import org.itsmahir.pagon.api.gui.font.Fonts;
import org.itsmahir.pagon.api.management.SavableManager;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.render.animation.Easing;
import org.itsmahir.pagon.api.utils.render.animation.type.ConsistentDynamicAnimation;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.PagonMod;

public class Feature implements ISavable {
   String name;
   boolean enabled;
   FeatureType type;
   List<Value<?>> values;
   Category category;
   public Value<Boolean> visible;
   public float offset;
   public String lastHudInfo;
   public final ConsistentDynamicAnimation animation;
   public double x;
   public long startTime;
   public double endpoint;
   private int prevTextWidth;
   private boolean wasDrawing;

   public Feature(String name, Category category, FeatureType type) {
      this.animation = new ConsistentDynamicAnimation(0.0F, Easing.LINEAR, 50L);
      this.name = name;
      this.enabled = false;
      this.type = type;
      this.values = new ArrayList();
      this.category = category;
      SavableManager.INSTANCE.getSavables().add(this);
      this.visible = (new ValueBuilder<Boolean>()).withDescriptor("Drawn").withValue(true).register(this);
      this.offset = -30.0F;
      this.lastHudInfo = "";
   }

   public void animation() {
      String var10000 = this.getDisplayName();
      String text = var10000 + (!Objects.equals(this.getHudInfo(), "") ? " [" + this.getHudInfo() + "]" : "");
      int textWidth = (int)Fonts.getTextWidth(text);
      boolean drawing = this.isEnabled() && (Boolean)this.visible.getValue();
      if (drawing != this.wasDrawing || this.prevTextWidth != textWidth) {
         this.startTime = System.currentTimeMillis();
         if (drawing) {
            this.endpoint = (double)(-textWidth);
         } else {
            this.endpoint = (double)0.0F;
         }

         this.wasDrawing = drawing;
         this.prevTextWidth = textWidth;
      }

      double animationProgress = Math.min((double)((float)(System.currentTimeMillis() - this.startTime) / 1000.0F), (double)1.0F);
      double factor = Easing.LINEAR.ease(animationProgress);
      this.x = this.x * ((double)1.0F - factor) + this.endpoint * factor;
   }

   public double getOffset() {
      return this.x;
   }

   public void animationIcky() {
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDisplayName() {
      return this.name;
   }

   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
      if (this.enabled) {
         this.onEnable();
      } else {
         this.onDisable();
      }

      try {
         if (SavableManager.INSTANCE != null && !SavableManager.INSTANCE.isLoading) {
            SavableManager.INSTANCE.saveAsync();
         }
      } catch (Exception var3) {
      }

   }

   public void setEnabledA(boolean enabled) {
      if (this.enabled != enabled) {
         this.enabled = enabled;
         if (this.enabled) {
            this.onEnable();
         } else {
            this.onDisable();
         }

      }
   }

   public Category getCategory() {
      return this.category;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void onEnable() {
      this.animationIcky();
      PagonMod.EVENT_BUS.register(this);
   }

   public void onDisable() {
      this.animationIcky();
      PagonMod.EVENT_BUS.unregister(this);
   }

   public void toggle() {
      this.setEnabled(!this.isEnabled());
   }

   public FeatureType getType() {
      return this.type;
   }

   public void setType(FeatureType type) {
      this.type = type;
   }

   public List<Value<?>> getValues() {
      return this.values;
   }

   public void setValues(List<Value<?>> values) {
      this.values = values;
   }

   public String getHudInfo() {
      return "";
   }

   public String getDescription() {
      return "";
   }

   public void load(Map<String, Object> objects) {
      if (objects != null) {
         Object e = objects.get("enabled");
         if (e != null) {
            this.setEnabled((Boolean)e);
         }

         for(Value value : this.getValues()) {
            Object o = objects.get(value.getTag());
            if (o != null) {
               try {
                  if (value.getValue() instanceof PagonColor) {
                     Map<String, Object> map = (Map)o;
                     PagonColor sn0wColor = new PagonColor(new Color((Integer)map.get("red"), (Integer)map.get("green"), (Integer)map.get("blue"), (Integer)map.get("alpha")), (Boolean)map.get("sync"));
                     value.setValue(sn0wColor);
                  } else {
                     Object current = value.getValue();
                     if (current instanceof Boolean && o instanceof String) {
                        value.setValue(Boolean.parseBoolean((String)o));
                     } else if (current instanceof Number && o instanceof String) {
                        value.setValue(Double.parseDouble((String)o));
                     } else {
                        value.setValue(o);
                     }
                  }
               } catch (Exception var8) {
               }
            }
         }

      }
   }

   public void loadModule(Map<String, Object> objects) {
      Object e = objects.get("enabled");
      if (e != null) {
         this.setEnabledA((Boolean)e);
      }

      this.setEnabledA(objects.get("enabled") != null ? (Boolean)objects.get("enabled") : this.isEnabled());

      for(Value value : this.getValues()) {
         Object o = objects.get(value.getTag());
         if (o != null) {
            try {
               if (value.getValue() instanceof PagonColor) {
                  Map<String, Object> map = (Map)o;
                  PagonColor sn0wColor = new PagonColor(new Color((Integer)map.get("red"), (Integer)map.get("green"), (Integer)map.get("blue"), (Integer)map.get("alpha")), (Boolean)map.get("sync"));
                  value.setValue(sn0wColor);
               } else {
                  value.setValue(o);
               }
            } catch (Exception var8) {
            }
         }
      }

   }

   public void softLoadModule(Map<String, Object> objects) {
      Object e = objects.get("enabled");
      if (e != null) {
         this.setEnabledA((Boolean)e);
      }

      this.setEnabledA(objects.get("enabled") != null ? (Boolean)objects.get("enabled") : this.isEnabled());

      for(Value value : this.getValues()) {
         Object o = objects.get(value.getTag());
         if (!Objects.equals(value.getTag(), "bind") && o != null) {
            try {
               if (!(value.getValue() instanceof PagonColor)) {
                  value.setValue(o);
               }
            } catch (Exception var7) {
            }
         }
      }

   }

   public Map<String, Object> save() {
      Map<String, Object> toSave = new HashMap();
      toSave.put("enabled", this.enabled);

      for(Value<?> value : this.getValues()) {
         if (value.getValue() instanceof PagonColor) {
            Map<String, Object> color = new HashMap();
            color.put("red", ((PagonColor)value.getValue()).getColor().getRed());
            color.put("green", ((PagonColor)value.getValue()).getColor().getGreen());
            color.put("blue", ((PagonColor)value.getValue()).getColor().getBlue());
            color.put("alpha", ((PagonColor)value.getValue()).getColor().getAlpha());
            color.put("sync", ((PagonColor)value.getValue()).isSyncing());
            toSave.put(value.getTag(), color);
         } else {
            toSave.put(value.getTag(), value.getValue());
         }
      }

      return toSave;
   }

   public String getFileName() {
      return this.getName() + ".cfg";
   }

   public String getDirName() {
      return "features";
   }

   public static enum FeatureType {
      Module,
      Hud;

      // $FF: synthetic method
      private static FeatureType[] $values() {
         return new FeatureType[]{Module, Hud};
      }
   }

   public static enum Category {
      Combat,
      Exploit,
      Render,
      Misc,
      Player,
      Movement,
      Client,
      Hud;

      // $FF: synthetic method
      private static Category[] $values() {
         return new Category[]{Combat, Exploit, Render, Misc, Player, Movement, Client, Hud};
      }
   }
}
