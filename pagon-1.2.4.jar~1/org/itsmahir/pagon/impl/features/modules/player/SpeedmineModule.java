package org.itsmahir.pagon.impl.features.modules.player;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.class_1268;
import net.minecraft.class_1292;
import net.minecraft.class_1294;
import net.minecraft.class_1893;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_259;
import net.minecraft.class_2596;
import net.minecraft.class_2626;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2846;
import net.minecraft.class_2868;
import net.minecraft.class_2879;
import net.minecraft.class_3532;
import net.minecraft.class_7204;
import net.minecraft.class_8042;
import net.minecraft.class_2846.class_2847;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.LivingEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.management.RotationManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.world.RenderType;
import org.itsmahir.pagon.api.utils.render.world.buffers.RenderBuffers;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;
import org.itsmahir.pagon.impl.features.modules.combat.AutoMineModule;
import org.itsmahir.pagon.mixin.accessor.IClientPlayerInteractionManager;

public class SpeedmineModule extends Module {
   private static SpeedmineModule INSTANCE;
   Value<String> modeConfig = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("PACKET").withModes("PACKET", "DAMAGE").register(this);
   Value<Boolean> doubleBreakConfig = (new ValueBuilder<Boolean>()).withDescriptor("DoubleBreak").withValue(false).register(this);
   Value<Number> rangeConfig = (new ValueBuilder<Number>()).withDescriptor("Range").withValue((double)4.0F).withRange(0.1, (double)6.0F).withPlaces(1).register(this);
   Value<Number> speedConfig = (new ValueBuilder<Number>()).withDescriptor("Speed").withValue((double)1.0F).withRange(0.1, (double)1.0F).withPlaces(1).register(this);
   Value<Boolean> instantConfig = (new ValueBuilder<Boolean>()).withDescriptor("Instant").withValue(false).register(this);
   Value<String> swapConfig = (new ValueBuilder<String>()).withDescriptor("AutoSwap").withValue("SILENT").withModes("NORMAL", "SILENT", "SILENT_ALT", "OFF").register(this);
   Value<Boolean> rotateConfig = (new ValueBuilder<Boolean>()).withDescriptor("Rotate").withValue(true).register(this);
   Value<Boolean> switchResetConfig = (new ValueBuilder<Boolean>()).withDescriptor("SwitchReset").withValue(false).register(this);
   Value<Boolean> grimConfig = (new ValueBuilder<Boolean>()).withDescriptor("Grim").withValue(false).register(this);
   Value<Boolean> grimNewConfig = (new ValueBuilder<Boolean>()).withDescriptor("GrimV3").withValue(false).register(this);
   Value<PagonColor> colorConfig = (new ValueBuilder<PagonColor>()).withDescriptor("MineColor").withValue(new PagonColor(255, 0, 0, 81)).register(this);
   Value<PagonColor> colorDoneConfig = (new ValueBuilder<PagonColor>()).withDescriptor("DoneColor").withValue(new PagonColor(0, 255, 0, 81)).register(this);
   Value<Number> fadeTimeConfig = (new ValueBuilder<Number>()).withDescriptor("Fade-Time").withValue(250).withRange(0, 1000).withPlaces(0).register(this);
   Value<Boolean> smoothColorConfig = (new ValueBuilder<Boolean>()).withDescriptor("ColorSmooth").withValue(false).register(this);
   private final Map<MiningData, Long> fadeList = new HashMap();
   private ArrayDeque<MiningData> miningQueue = new ArrayDeque();
   private long lastBreak;

   public SpeedmineModule() {
      super("Speedmine", Feature.Category.Player);
      INSTANCE = this;
   }

   public static SpeedmineModule getInstance() {
      return INSTANCE;
   }

   public String getHudInfo() {
      if (((String)this.modeConfig.getValue()).equals("PACKET")) {
         MiningData data = (MiningData)this.miningQueue.peek();
         if (data != null) {
            return String.format("%.1f", Math.min(data.getBlockDamage(), 1.0F));
         }
      }

      return super.getHudInfo();
   }

   public void onDisable() {
      super.onDisable();
      this.miningQueue.clear();
      this.fadeList.clear();
      InventoryUtils.syncItem();
   }

   public void onEnable() {
      super.onEnable();
   }

   @SubscribeEvent
   public void onPlayerTick(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (!mc.field_1724.method_7337() && !mc.field_1724.method_7325()) {
            if (((String)this.modeConfig.getValue()).equals("DAMAGE")) {
               IClientPlayerInteractionManager im = (IClientPlayerInteractionManager)mc.field_1761;
               if (im.getCurrentBreakProgress() >= ((Number)this.speedConfig.getValue()).floatValue()) {
                  im.setCurrentBreakingProgress(1.0F);
               }

            } else if (!AutoMineModule.getInstance().isEnabled()) {
               if (!this.miningQueue.isEmpty()) {
                  Iterator<MiningData> it = this.miningQueue.iterator();

                  while(it.hasNext()) {
                     MiningData data = (MiningData)it.next();
                     if (data.getState().method_26215()) {
                        data.resetBreakTime();
                     }

                     if (!this.isDataPacketMine(data) || !data.getState().method_26215() && (!data.hasAttemptedBreak() || !data.passedAttemptedBreakTime(500L))) {
                        float delta = this.calcBlockBreakingDelta(data.getState(), data.getPos());
                        data.addDamage(delta);
                        if (this.isDataPacketMine(data) && data.getBlockDamage() >= 1.0F && data.getBestSlot() != -1) {
                           if (mc.field_1724.method_6115() && ((String)AntiCheat.INSTANCE.multiTask.getValue()).equals("None")) {
                              return;
                           }

                           if (data.getBestSlot() != RotationManager.INSTANCE.serverSlot) {
                              InventoryUtils.switchToSlot(data.getBestSlot());
                           }

                           if (!data.hasAttemptedBreak()) {
                              data.setAttemptedBreak(true);
                           }
                        }
                     } else {
                        InventoryUtils.syncItem();
                        it.remove();
                     }
                  }

                  if (!this.miningQueue.isEmpty()) {
                     MiningData first = (MiningData)this.miningQueue.getFirst();
                     double dist = mc.field_1724.method_33571().method_1025(first.getPos().method_46558());
                     if (dist > (double)MathUtil.square(((Number)this.rangeConfig.getValue()).floatValue())) {
                        this.miningQueue.remove(first);
                     } else if (!first.getState().method_26215()) {
                        if (first.getBlockDamage() >= ((Number)this.speedConfig.getValue()).floatValue() && first.hasAttemptedBreak() && first.passedAttemptedBreakTime(500L)) {
                           this.abortMining(first);
                           this.miningQueue.remove(first);
                        }

                        if (first.getBlockDamage() >= ((Number)this.speedConfig.getValue()).floatValue()) {
                           if (mc.field_1724.method_6115() && ((String)AntiCheat.INSTANCE.multiTask.getValue()).equals("None")) {
                              return;
                           }

                           this.stopMining(first);
                           if (!(Boolean)this.instantConfig.getValue()) {
                              this.miningQueue.remove(first);
                           }

                           if (!first.hasAttemptedBreak()) {
                              first.setAttemptedBreak(true);
                           }
                        }

                     }
                  }
               }
            }
         }
      }
   }

   @SubscribeEvent
   public void onAttackBlock(LivingEvent.AttackBlock event) {
      if (!NullUtils.nullCheck()) {
         if (!mc.field_1724.method_7337() && !mc.field_1724.method_7325() && ((String)this.modeConfig.getValue()).equals("PACKET")) {
            if (!AutoMineModule.getInstance().isEnabled()) {
               event.setCancelled(true);
               if (event.getState().method_26204().method_36555() != -1.0F && !event.getState().method_26215()) {
                  this.startManualMine(event.getPos(), event.getDirection());
                  mc.field_1724.method_6104(class_1268.field_5808);
               }
            }
         }
      }
   }

   @SubscribeEvent
   public void onPacketSend(PacketEvent.Send event) {
      if (!NullUtils.nullCheck()) {
         class_2596 var3 = event.getPacket();
         if (var3 instanceof class_2846) {
            class_2846 packet = (class_2846)var3;
            if (packet.method_12363() == class_2847.field_12973 && ((String)this.modeConfig.getValue()).equals("DAMAGE") && (Boolean)this.grimConfig.getValue()) {
               PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12971, packet.method_12362().method_10086(500), packet.method_12360()));
            }
         }

         if (event.getPacket() instanceof class_2868 && (Boolean)this.switchResetConfig.getValue() && ((String)this.modeConfig.getValue()).equals("PACKET")) {
            for(MiningData data : this.miningQueue) {
               data.resetDamage();
            }
         }

      }
   }

   @SubscribeEvent
   public void onPacketReceive(PacketEvent.Receive event) {
      if (!NullUtils.nullCheck() && ((String)this.modeConfig.getValue()).equals("PACKET")) {
         if (!AutoMineModule.getInstance().isEnabled()) {
            class_2596 var4 = event.getPacket();
            if (var4 instanceof class_2626) {
               class_2626 packet = (class_2626)var4;
               this.handleBlockUpdatePacket(packet);
            } else {
               var4 = event.getPacket();
               if (var4 instanceof class_8042) {
                  class_8042 bundle = (class_8042)var4;

                  for(class_2596<?> p : bundle.method_48324()) {
                     if (p instanceof class_2626) {
                        class_2626 packet = (class_2626)p;
                        this.handleBlockUpdatePacket(packet);
                     }
                  }
               }
            }

         }
      }
   }

   private void handleBlockUpdatePacket(class_2626 packet) {
      if (packet.method_11308().method_26215()) {
         for(MiningData data : this.miningQueue) {
            if (data.hasAttemptedBreak() && data.getPos().equals(packet.method_11309())) {
               data.setAttemptedBreak(false);
            }
         }

      }
   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldEvent event) {
      if (!NullUtils.nullCheck()) {
         if (!mc.field_1724.method_7337() && ((String)this.modeConfig.getValue()).equals("PACKET")) {
            if (!AutoMineModule.getInstance().isEnabled()) {
               RenderBuffers.preRender();

               for(Map.Entry<MiningData, Long> entry : this.fadeList.entrySet()) {
                  MiningData data = (MiningData)entry.getKey();
                  long elapsed = System.currentTimeMillis() - (Long)entry.getValue();
                  int fadeMs = ((Number)this.fadeTimeConfig.getValue()).intValue();
                  float factor = fadeMs > 0 ? class_3532.method_15363(1.0F - (float)elapsed / (float)fadeMs, 0.0F, 1.0F) : 0.0F;
                  if (!(factor <= 0.01F)) {
                     int boxAlpha = (int)(40.0F * factor);
                     int lineAlpha = (int)(100.0F * factor);
                     int boxColor;
                     int lineColor;
                     if ((Boolean)this.smoothColorConfig.getValue()) {
                        boxColor = data.getState().method_26215() ? ColorUtil.newAlpha(((PagonColor)this.colorDoneConfig.getValue()).getColor(), boxAlpha).getRGB() : ColorUtil.interpolate(Math.min(data.getBlockDamage(), 1.0F), ColorUtil.newAlpha(((PagonColor)this.colorDoneConfig.getValue()).getColor(), boxAlpha), ColorUtil.newAlpha(((PagonColor)this.colorConfig.getValue()).getColor(), boxAlpha)).getRGB();
                        lineColor = data.getState().method_26215() ? ColorUtil.newAlpha(((PagonColor)this.colorDoneConfig.getValue()).getColor(), lineAlpha).getRGB() : ColorUtil.interpolate(Math.min(data.getBlockDamage(), 1.0F), ColorUtil.newAlpha(((PagonColor)this.colorDoneConfig.getValue()).getColor(), lineAlpha), ColorUtil.newAlpha(((PagonColor)this.colorConfig.getValue()).getColor(), lineAlpha)).getRGB();
                     } else {
                        Color c = !(data.getBlockDamage() >= 0.95F) && !data.getState().method_26215() ? ((PagonColor)this.colorConfig.getValue()).getColor() : ((PagonColor)this.colorDoneConfig.getValue()).getColor();
                        boxColor = ColorUtil.newAlpha(c, boxAlpha).getRGB();
                        lineColor = ColorUtil.newAlpha(c, lineAlpha).getRGB();
                     }

                     class_2338 pos = data.getPos();
                     class_265 shape = data.getState().method_26218(mc.field_1687, pos);
                     shape = shape.method_1110() ? class_259.method_1077() : shape;
                     class_238 r1 = shape.method_1107();
                     class_238 render = new class_238((double)pos.method_10263() + r1.field_1323, (double)pos.method_10264() + r1.field_1322, (double)pos.method_10260() + r1.field_1321, (double)pos.method_10263() + r1.field_1320, (double)pos.method_10264() + r1.field_1325, (double)pos.method_10260() + r1.field_1324);
                     class_243 center = render.method_1005();
                     float total = this.isDataPacketMine(data) ? 1.0F : ((Number)this.speedConfig.getValue()).floatValue();
                     float scale = data.getState().method_26215() ? 1.0F : class_3532.method_15363((data.getBlockDamage() + (data.getBlockDamage() - data.getLastDamage()) * event.getTickDelta()) / total, 0.0F, 1.0F);
                     double dx = (r1.field_1320 - r1.field_1323) / (double)2.0F;
                     double dy = (r1.field_1325 - r1.field_1322) / (double)2.0F;
                     double dz = (r1.field_1324 - r1.field_1321) / (double)2.0F;
                     class_238 scaled = (new class_238(center, center)).method_1009(dx * (double)scale, dy * (double)scale, dz * (double)scale);
                     RenderUtil.renderBox(RenderType.FILL, scaled, new Color(boxColor, true), new Color(boxColor, true));
                     RenderUtil.renderBox(RenderType.LINES, scaled, new Color(lineColor, true), new Color(lineColor, true));
                  }
               }

               for(MiningData data : this.miningQueue) {
                  if (!data.getState().method_26215()) {
                     this.fadeList.put(data, System.currentTimeMillis());
                  }
               }

               this.fadeList.entrySet().removeIf((e) -> {
                  int fadeMs = ((Number)this.fadeTimeConfig.getValue()).intValue();
                  return fadeMs > 0 && System.currentTimeMillis() - (Long)e.getValue() >= (long)fadeMs;
               });
               RenderBuffers.postRender();
            }
         }
      }
   }

   private void startManualMine(class_2338 pos, class_2350 direction) {
      this.clickMine(new MiningData(pos, direction));
   }

   public void clickMine(MiningData data) {
      if (this.miningQueue.size() <= 2) {
         this.queueMiningData(data);
      }

   }

   private void queueMiningData(MiningData data) {
      if (!data.getState().method_26215()) {
         if (this.startMining(data)) {
            if (this.miningQueue.stream().anyMatch((p) -> data.getPos().equals(p.getPos()))) {
               return;
            }

            this.miningQueue.addFirst(data);
            int max = (Boolean)this.doubleBreakConfig.getValue() ? 2 : 1;

            while(this.miningQueue.size() > max) {
               this.miningQueue.removeLast();
            }
         }

      }
   }

   private boolean startMining(MiningData data) {
      if (data.isStarted()) {
         return false;
      } else {
         data.setStarted();
         if ((Boolean)this.grimNewConfig.getValue()) {
            if (!(Boolean)AntiCheat.INSTANCE.grimRotations.getValue()) {
               PacketManager.INSTANCE.sendPacket((class_7204)((seq) -> new class_2846(class_2847.field_12973, data.getPos(), data.getDirection(), seq)));
               PacketManager.INSTANCE.sendPacket((class_7204)((seq) -> new class_2846(class_2847.field_12968, data.getPos(), data.getDirection(), seq)));
               PacketManager.INSTANCE.sendPacket((class_7204)((seq) -> new class_2846(class_2847.field_12971, data.getPos(), data.getDirection(), seq)));
            } else {
               PacketManager.INSTANCE.sendPacket((class_7204)((seq) -> new class_2846(class_2847.field_12968, data.getPos(), data.getDirection(), seq)));
            }

            PacketManager.INSTANCE.sendPacket((class_7204)((seq) -> new class_2846(class_2847.field_12973, data.getPos(), data.getDirection(), seq)));
            PacketManager.INSTANCE.sendPacket(new class_2879(class_1268.field_5808));
            PacketManager.INSTANCE.sendPacket(new class_2879(class_1268.field_5808));
            PacketManager.INSTANCE.sendPacket(new class_2879(class_1268.field_5808));
            return true;
         } else {
            PacketManager.INSTANCE.sendPacket((class_7204)((seq) -> new class_2846(class_2847.field_12968, data.getPos(), data.getDirection(), seq)));
            PacketManager.INSTANCE.sendPacket((class_7204)((seq) -> new class_2846(class_2847.field_12971, data.getPos(), data.getDirection(), seq)));
            PacketManager.INSTANCE.sendPacket((class_7204)((seq) -> new class_2846(class_2847.field_12973, data.getPos(), data.getDirection(), seq)));
            PacketManager.INSTANCE.sendPacket(new class_2879(class_1268.field_5808));
            PacketManager.INSTANCE.sendPacket((class_7204)((seq) -> new class_2846(class_2847.field_12968, data.getPos(), data.getDirection(), seq)));
            PacketManager.INSTANCE.sendPacket((class_7204)((seq) -> new class_2846(class_2847.field_12971, data.getPos(), data.getDirection(), seq)));
            PacketManager.INSTANCE.sendPacket((class_7204)((seq) -> new class_2846(class_2847.field_12973, data.getPos(), data.getDirection(), seq)));
            PacketManager.INSTANCE.sendPacket(new class_2879(class_1268.field_5808));
            return true;
         }
      }
   }

   private void abortMining(MiningData data) {
      if (data.isStarted() && !data.getState().method_26215()) {
         PacketManager.INSTANCE.sendPacket((class_7204)((seq) -> new class_2846(class_2847.field_12971, data.getPos(), data.getDirection(), seq)));
         InventoryUtils.syncItem();
      }
   }

   private void stopMining(MiningData data) {
      if (data.isStarted() && !data.getState().method_26215()) {
         if ((Boolean)this.rotateConfig.getValue()) {
            float[] rots = RotationUtils.getRotationsTo(mc.field_1724.method_33571(), data.getPos().method_46558());
            if ((Boolean)this.grimConfig.getValue()) {
               RotationUtils.doSilentRotate(rots);
            } else {
               RotationUtils.setRotation(rots[0], rots[1]);
            }
         }

         int slot = data.getBestSlot();
         boolean canSwap = slot != -1 && slot != RotationManager.INSTANCE.serverSlot;
         if (canSwap) {
            this.swapTo(slot);
         }

         this.stopMiningInternal(data);
         this.lastBreak = System.currentTimeMillis();
         if (canSwap) {
            this.swapSync(slot);
         }

         if ((Boolean)this.rotateConfig.getValue()) {
            RotationUtils.silentSync();
         }

      }
   }

   private void swapTo(int slot) {
      switch ((String)this.swapConfig.getValue()) {
         case "NORMAL" -> mc.field_1724.method_31548().field_7545 = slot;
         case "SILENT" -> InventoryUtils.switchToSlot(slot);
         case "SILENT_ALT" -> InventoryUtils.switchToBypass(InventoryUtils.hotbarToInventory(slot), false);
      }

   }

   private void swapSync(int slot) {
      switch ((String)this.swapConfig.getValue()) {
         case "SILENT" -> InventoryUtils.syncItem();
         case "SILENT_ALT" -> InventoryUtils.switchToBypass(InventoryUtils.hotbarToInventory(slot), true);
      }

   }

   private void stopMiningInternal(MiningData data) {
      PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12973, data.getPos(), data.getDirection()));
      PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12971, data.getPos(), data.getDirection()));
   }

   public boolean isBlockDelayGrim() {
      return System.currentTimeMillis() - this.lastBreak <= 280L && (Boolean)this.grimConfig.getValue();
   }

   private boolean isDataPacketMine(MiningData data) {
      return this.miningQueue.size() == 2 && data == this.miningQueue.getLast();
   }

   public float calcBlockBreakingDelta(class_2680 state, class_2338 pos) {
      if (((String)this.swapConfig.getValue()).equals("OFF")) {
         return state.method_26165(mc.field_1724, mc.field_1687, pos);
      } else {
         float f = state.method_26214(mc.field_1687, pos);
         if (f == -1.0F) {
            return 0.0F;
         } else {
            int i = this.canHarvest(state) ? 30 : 100;
            return this.getBlockBreakingSpeed(state) / f / (float)i;
         }
      }
   }

   private float getBlockBreakingSpeed(class_2680 block) {
      int tool = InventoryUtils.getBestToolSlot(block.method_26204());
      float f = mc.field_1724.method_31548().method_5438(tool).method_7924(block);
      if (f > 1.0F) {
         int eff = InventoryUtils.getEnchantmentLevel(mc.field_1724.method_31548().method_5438(tool), class_1893.field_9131);
         if (eff > 0) {
            f += (float)(eff * eff + 1);
         }
      }

      if (class_1292.method_5576(mc.field_1724)) {
         f *= 1.0F + (float)(class_1292.method_5575(mc.field_1724) + 1) * 0.2F;
      }

      if (mc.field_1724.method_6059(class_1294.field_5901)) {
         int amp = mc.field_1724.method_6112(class_1294.field_5901).method_5578();
         float var10000;
         switch (amp) {
            case 0 -> var10000 = 0.3F;
            case 1 -> var10000 = 0.09F;
            case 2 -> var10000 = 0.0027F;
            default -> var10000 = 8.1E-4F;
         }

         float g = var10000;
         f *= g;
      }

      if (!mc.field_1724.method_24828()) {
         f /= 5.0F;
      }

      return f;
   }

   private boolean canHarvest(class_2680 state) {
      if (state.method_29291()) {
         int tool = InventoryUtils.getBestToolSlot(state.method_26204());
         return mc.field_1724.method_31548().method_5438(tool).method_7951(state);
      } else {
         return true;
      }
   }

   public boolean isMining() {
      return !this.miningQueue.isEmpty();
   }

   public static class MiningData {
      private boolean attemptedBreak;
      private long breakTime;
      private final class_2338 pos;
      private final class_2350 direction;
      private float lastDamage;
      private float blockDamage;
      private boolean started;

      public MiningData(class_2338 pos, class_2350 direction) {
         this.pos = pos;
         this.direction = direction;
      }

      public void setAttemptedBreak(boolean v) {
         this.attemptedBreak = v;
         if (v) {
            this.resetBreakTime();
         }

      }

      public void resetBreakTime() {
         this.breakTime = System.currentTimeMillis();
      }

      public boolean hasAttemptedBreak() {
         return this.attemptedBreak;
      }

      public boolean passedAttemptedBreakTime(long t) {
         return System.currentTimeMillis() - this.breakTime >= t;
      }

      public float addDamage(float d) {
         this.lastDamage = this.blockDamage;
         this.blockDamage += d;
         return this.blockDamage;
      }

      public void setDamage(float d) {
         this.blockDamage = d;
      }

      public void resetDamage() {
         this.started = false;
         this.blockDamage = 0.0F;
      }

      public class_2338 getPos() {
         return this.pos;
      }

      public class_2350 getDirection() {
         return this.direction;
      }

      public int getBestSlot() {
         return InventoryUtils.getBestToolSlot(this.getState().method_26204());
      }

      public class_2680 getState() {
         return BlockUtils.getBlockState(this.pos);
      }

      public float getBlockDamage() {
         return this.blockDamage;
      }

      public float getLastDamage() {
         return this.lastDamage;
      }

      public boolean isStarted() {
         return this.started;
      }

      public void setStarted() {
         this.started = true;
      }
   }
}
