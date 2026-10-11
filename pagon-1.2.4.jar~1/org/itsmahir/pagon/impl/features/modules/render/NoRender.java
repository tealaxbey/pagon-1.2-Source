package org.itsmahir.pagon.impl.features.modules.render;

import java.util.HashMap;
import java.util.WeakHashMap;
import net.minecraft.class_345;
import net.minecraft.class_3486;
import net.minecraft.class_3937;
import net.minecraft.class_677;
import net.minecraft.class_689;
import net.minecraft.class_691;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.render.ParticleEvent;
import org.itsmahir.pagon.api.event.events.render.RenderFogEvent;
import org.itsmahir.pagon.api.event.events.render.RenderGameOverlayEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class NoRender extends Module {
   public static NoRender INSTANCE;
   public Value<Boolean> blockInside = (new ValueBuilder<Boolean>()).withDescriptor("Block Inside").withValue(false).register(this);
   public Value<Boolean> fire = (new ValueBuilder<Boolean>()).withDescriptor("Fire").withValue(false).register(this);
   public Value<Boolean> armor = (new ValueBuilder<Boolean>()).withDescriptor("Armor").withValue(false).register(this);
   public Value<Boolean> portal = (new ValueBuilder<Boolean>()).withDescriptor("Swirls").withValue(false).register(this);
   public Value<Boolean> pumpkin = (new ValueBuilder<Boolean>()).withDescriptor("Pumpkin").withValue(false).register(this);
   public Value<Boolean> potionGui = (new ValueBuilder<Boolean>()).withDescriptor("Potion Gui").withValue(false).register(this);
   public Value<Boolean> advancements = (new ValueBuilder<Boolean>()).withDescriptor("Advancements").withValue(false).register(this);
   public Value<Boolean> skyLight = (new ValueBuilder<Boolean>()).withDescriptor("SkyLight").withValue(false).register(this);
   public Value<Boolean> enchantmentTable = (new ValueBuilder<Boolean>()).withDescriptor("Enchant Tables").withValue(false).register(this);
   public Value<Boolean> noHurtCam = (new ValueBuilder<Boolean>()).withDescriptor("Hurt Cam").withValue(false).register(this);
   public Value<Boolean> explosions = (new ValueBuilder<Boolean>()).withDescriptor("Explosions").withValue(false).register(this);
   public Value<Boolean> liquidVision = (new ValueBuilder<Boolean>()).withDescriptor("LiquidVision").withValue(false).register(this);
   Value<Boolean> fireworks = (new ValueBuilder<Boolean>()).withDescriptor("Fireworks").withValue(false).register(this);
   public Value<Boolean> frost = (new ValueBuilder<Boolean>()).withDescriptor("Frostbite").withValue(false).register(this);
   public Value<Boolean> campFire = (new ValueBuilder<Boolean>()).withDescriptor("Camp Fires").withValue(false).register(this);
   public Value<Boolean> noFog = (new ValueBuilder<Boolean>()).withDescriptor("Fog").withValue(false).register(this);
   public Value<String> boss = (new ValueBuilder<String>()).withDescriptor("BossBar").withValue("None").withModes("None", "Normal", "Stack").register(this);
   public Value<Boolean> itemFrame = (new ValueBuilder<Boolean>()).withDescriptor("Frames").withValue(false).register(this);
   public Value<Boolean> totem = (new ValueBuilder<Boolean>()).withDescriptor("Totem Pops").withValue(false).register(this);
   public static final WeakHashMap<class_345, Integer> barMap = new WeakHashMap();

   public NoRender() {
      super("NoRender", Feature.Category.Render);
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onParticle(ParticleEvent event) {
      if (!NullUtils.nullCheck()) {
         if ((Boolean)this.explosions.getValue() && (event.getParticle() instanceof class_691 || event.getParticle() instanceof class_689) || (Boolean)this.fireworks.getValue() && (event.getParticle() instanceof class_677.class_681 || event.getParticle() instanceof class_677.class_678) || (Boolean)this.campFire.getValue() && event.getParticle() instanceof class_3937) {
            event.setCancelled(true);
         }

      }
   }

   @SubscribeEvent
   public void onRenderFog(RenderFogEvent event) {
      if (!NullUtils.nullCheck()) {
         if (mc.field_1724.method_5777(class_3486.field_15518)) {
            if ((Boolean)this.liquidVision.getValue()) {
               event.setCancelled(true);
            }
         } else if ((Boolean)this.noFog.getValue()) {
            event.setCancelled(true);
         }

      }
   }

   @SubscribeEvent
   private void onBossText(RenderGameOverlayEvent.BossBar.Text event) {
      if (!NullUtils.nullCheck()) {
         if (!barMap.isEmpty() && ((String)this.boss.getValue()).equals("Stack")) {
            class_345 bar = event.bossBar;
            Integer integer = (Integer)barMap.get(bar);
            barMap.remove(bar);
            if (integer != null) {
               event.name = event.name.method_27661().method_27693(" x" + integer);
            }

         }
      }
   }

   @SubscribeEvent
   private void onBossIterator(RenderGameOverlayEvent.BossBar.Iterate event) {
      if (!NullUtils.nullCheck()) {
         if (((String)this.boss.getValue()).equals("Stack")) {
            HashMap<String, class_345> chosenBarMap = new HashMap();
            event.iterator.forEachRemaining((bar) -> {
               String name = bar.method_5414().getString();
               if (chosenBarMap.containsKey(name)) {
                  barMap.compute((class_345)chosenBarMap.get(name), (clientBossBar, integer) -> integer == null ? 2 : integer + 1);
               } else {
                  chosenBarMap.put(name, bar);
               }

            });
            event.iterator = chosenBarMap.values().iterator();
         }

      }
   }

   public String getDescription() {
      return "NoRender: stops rendering various things";
   }
}
