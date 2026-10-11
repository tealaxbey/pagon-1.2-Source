package org.itsmahir.pagon.impl.features.modules.client;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class Baritone extends Module {
   public Value<Number> range = (new ValueBuilder<Number>()).withDescriptor("Range").withValue(4.0F).withRange(1.0F, 6.0F).withPlaces(1).register(this);
   public Value<Boolean> place = (new ValueBuilder<Boolean>()).withDescriptor("Place").withValue(true).register(this);
   public Value<Boolean> breakSetting = (new ValueBuilder<Boolean>()).withDescriptor("Break").withValue(true).register(this);
   public Value<Boolean> sprint = (new ValueBuilder<Boolean>()).withDescriptor("Sprint").withValue(true).register(this);
   public Value<Boolean> inventory = (new ValueBuilder<Boolean>()).withDescriptor("UseInventory").withValue(false).register(this);
   public Value<Boolean> vines = (new ValueBuilder<Boolean>()).withDescriptor("Vines").withValue(true).register(this);
   public Value<Boolean> jump256 = (new ValueBuilder<Boolean>()).withDescriptor("JumpAt256").withValue(false).register(this);
   public Value<Boolean> waterBucketFall = (new ValueBuilder<Boolean>()).withDescriptor("WaterBucketFall").withValue(false).register(this);
   public Value<Boolean> parkour = (new ValueBuilder<Boolean>()).withDescriptor("Parkour").withValue(true).register(this);
   public Value<Boolean> parkourPlace = (new ValueBuilder<Boolean>()).withDescriptor("ParkourPlace").withValue(false).register(this);
   public Value<Boolean> parkourAscend = (new ValueBuilder<Boolean>()).withDescriptor("ParkourAscend").withValue(true).register(this);
   public Value<Boolean> diagonalAscend = (new ValueBuilder<Boolean>()).withDescriptor("DiagonalAscend").withValue(false).register(this);
   public Value<Boolean> diagonalDescend = (new ValueBuilder<Boolean>()).withDescriptor("DiagonalDescend").withValue(false).register(this);
   public Value<Boolean> mineDown = (new ValueBuilder<Boolean>()).withDescriptor("MineDownward").withValue(true).register(this);
   public Value<Boolean> legitMine = (new ValueBuilder<Boolean>()).withDescriptor("LegitMine").withValue(false).register(this);
   public Value<Boolean> logOnArrival = (new ValueBuilder<Boolean>()).withDescriptor("LogOnArrival").withValue(false).register(this);
   public Value<Boolean> freeLook = (new ValueBuilder<Boolean>()).withDescriptor("FreeLook").withValue(true).register(this);
   public Value<Boolean> antiCheat = (new ValueBuilder<Boolean>()).withDescriptor("AntiCheat").withValue(false).register(this);
   public Value<Boolean> strictLiquid = (new ValueBuilder<Boolean>()).withDescriptor("Strict-Liquid").withValue(false).register(this);
   public Value<Boolean> censorCoords = (new ValueBuilder<Boolean>()).withDescriptor("CensorCoords").withValue(false).register(this);
   public Value<Boolean> censorCommands = (new ValueBuilder<Boolean>()).withDescriptor("CensorCommands").withValue(false).register(this);
   public Value<Boolean> debug = (new ValueBuilder<Boolean>()).withDescriptor("Debug").withValue(false).register(this);
   public static Baritone INSTANCE;
   private Object baritoneSettings;
   private boolean baritoneApiChecked;

   public Baritone() {
      super("Baritone", Feature.Category.Client);
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onTick(TickEvent.PlayerTickEvent.Pre event) {
      if (!this.baritoneApiChecked) {
         this.baritoneApiChecked = true;

         try {
            Class<?> apiClass = Class.forName("baritone.api.BaritoneAPI");
            Method getSettings = apiClass.getMethod("getSettings");
            this.baritoneSettings = getSettings.invoke((Object)null);
         } catch (Throwable var5) {
            this.baritoneSettings = null;
         }
      }

      if (this.baritoneSettings != null) {
         try {
            this.setFloatField("blockReachDistance", ((Number)this.range.getValue()).floatValue());
            this.setBooleanField("allowPlace", (Boolean)this.place.getValue());
            this.setBooleanField("allowBreak", (Boolean)this.breakSetting.getValue());
            this.setBooleanField("allowSprint", (Boolean)this.sprint.getValue());
            this.setBooleanField("allowInventory", (Boolean)this.inventory.getValue());
            this.setBooleanField("allowVines", (Boolean)this.vines.getValue());
            this.setBooleanField("allowJumpAt256", (Boolean)this.jump256.getValue());
            this.setBooleanField("allowWaterBucketFall", (Boolean)this.waterBucketFall.getValue());
            this.setBooleanField("allowParkour", (Boolean)this.parkour.getValue());
            this.setBooleanField("allowParkourPlace", (Boolean)this.parkourPlace.getValue());
            this.setBooleanField("allowParkourAscend", (Boolean)this.parkourAscend.getValue());
            this.setBooleanField("allowDiagonalAscend", (Boolean)this.diagonalAscend.getValue());
            this.setBooleanField("allowDiagonalDescend", (Boolean)this.diagonalDescend.getValue());
            this.setBooleanField("allowDownward", (Boolean)this.mineDown.getValue());
            this.setBooleanField("legitMine", (Boolean)this.legitMine.getValue());
            this.setBooleanField("disconnectOnArrival", (Boolean)this.logOnArrival.getValue());
            this.setBooleanField("freeLook", (Boolean)this.freeLook.getValue());
            this.setBooleanField("antiCheatCompatibility", (Boolean)this.antiCheat.getValue());
            this.setBooleanField("strictLiquidCheck", (Boolean)this.strictLiquid.getValue());
            this.setBooleanField("censorCoordinates", (Boolean)this.censorCoords.getValue());
            this.setBooleanField("censorRanCommands", (Boolean)this.censorCommands.getValue());
            this.setBooleanField("chatDebug", (Boolean)this.debug.getValue());
         } catch (Throwable var4) {
         }

      }
   }

   public void doObstaclePass() {
      try {
         Class<?> apiClass = Class.forName("baritone.api.BaritoneAPI");
         Object provider = apiClass.getMethod("getProvider").invoke((Object)null);
         Object baritone = provider.getClass().getMethod("getPrimaryBaritone").invoke(provider);
         Object goalProcess = baritone.getClass().getMethod("getCustomGoalProcess").invoke(baritone);
         Class<?> goalXZClass = Class.forName("baritone.api.pathing.goals.GoalXZ");
         Object goal = null;

         for(Method m : goalXZClass.getMethods()) {
            if (m.getName().equals("fromDirection") && m.getParameterCount() == 3) {
               goal = m.invoke((Object)null, mc.field_1724.method_19538(), mc.field_1724.method_36454(), 10);
               break;
            }
         }

         if (goal != null) {
            goalProcess.getClass().getMethod("setGoalAndPath", goal.getClass()).invoke(goalProcess, goal);
         }
      } catch (Throwable var11) {
      }

   }

   private void setBooleanField(String name, boolean value) throws Exception {
      Field settingField = this.baritoneSettings.getClass().getField(name);
      Object setting = settingField.get(this.baritoneSettings);
      setting.getClass().getField("value").set(setting, value);
   }

   private void setFloatField(String name, float value) throws Exception {
      Field settingField = this.baritoneSettings.getClass().getField(name);
      Object setting = settingField.get(this.baritoneSettings);
      setting.getClass().getField("value").set(setting, value);
   }

   public String getDescription() {
      return "Baritone: Manages baritone api settings";
   }
}
