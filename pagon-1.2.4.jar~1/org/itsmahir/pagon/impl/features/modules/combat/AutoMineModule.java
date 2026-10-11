package org.itsmahir.pagon.impl.features.modules.combat;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
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
import net.minecraft.class_3726;
import net.minecraft.class_4050;
import net.minecraft.class_2846.class_2847;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.LivingEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.world.RenderType;
import org.itsmahir.pagon.api.utils.render.world.buffers.RenderBuffers;
import org.itsmahir.pagon.api.utils.targeting.TargetUtils;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.utils.world.ProtectionUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;
import org.itsmahir.pagon.impl.features.modules.player.SpeedmineModule;

public class AutoMineModule extends Module {
   private static AutoMineModule INSTANCE;
   Value<Boolean> autoConfig = (new ValueBuilder<Boolean>()).withDescriptor("Auto").withValue(false).register(this);
   Value<String> selectionConfig = (new ValueBuilder<String>()).withDescriptor("Selection").withValue("ALL").withModes("WHITELIST", "BLACKLIST", "ALL").register(this);
   Value<Boolean> avoidSelfConfig = (new ValueBuilder<Boolean>()).withDescriptor("AvoidSelf").withValue(false).register(this);
   Value<Number> enemyRangeConfig = (new ValueBuilder<Number>()).withDescriptor("EnemyRange").withValue((double)5.0F).withRange((double)1.0F, (double)10.0F).withPlaces(1).register(this);
   Value<Boolean> antiCrawlConfig = (new ValueBuilder<Boolean>()).withDescriptor("AntiCrawl").withValue(false).register(this);
   Value<Boolean> headConfig = (new ValueBuilder<Boolean>()).withDescriptor("TargetBody").withValue(false).register(this);
   Value<Boolean> aboveHeadConfig = (new ValueBuilder<Boolean>()).withDescriptor("TargetHead").withValue(false).register(this);
   Value<Boolean> doubleBreakConfig = (new ValueBuilder<Boolean>()).withDescriptor("DoubleBreak").withValue(false).register(this);
   Value<Number> mineTicksConfig = (new ValueBuilder<Number>()).withDescriptor("MiningTicks").withValue(20).withRange(5, 60).withPlaces(0).register(this);
   Value<String> remineConfig = (new ValueBuilder<String>()).withDescriptor("Remine").withValue("NORMAL").withModes("INSTANT", "NORMAL", "FAST").register(this);
   Value<Boolean> packetInstantConfig = (new ValueBuilder<Boolean>()).withDescriptor("Fast").withValue(false).register(this);
   Value<Number> rangeConfig = (new ValueBuilder<Number>()).withDescriptor("Range").withValue((double)4.0F).withRange(0.1, (double)6.0F).withPlaces(1).register(this);
   Value<Number> speedConfig = (new ValueBuilder<Number>()).withDescriptor("Speed").withValue((double)1.0F).withRange(0.1, (double)1.0F).withPlaces(1).register(this);
   Value<String> swapConfig = (new ValueBuilder<String>()).withDescriptor("AutoSwap").withValue("SILENT").withModes("NORMAL", "SILENT", "SILENT_ALT", "OFF").register(this);
   Value<Boolean> swapBeforeConfig = (new ValueBuilder<Boolean>()).withDescriptor("SwapBefore").withValue(false).register(this);
   Value<Boolean> rotateConfig = (new ValueBuilder<Boolean>()).withDescriptor("Rotate").withValue(true).register(this);
   Value<Boolean> switchResetConfig = (new ValueBuilder<Boolean>()).withDescriptor("SwitchReset").withValue(false).register(this);
   Value<Boolean> grimConfig = (new ValueBuilder<Boolean>()).withDescriptor("Grim").withValue(false).register(this);
   Value<Boolean> grimNewConfig = (new ValueBuilder<Boolean>()).withDescriptor("GrimV3").withValue(false).register(this);
   Value<PagonColor> colorConfig = (new ValueBuilder<PagonColor>()).withDescriptor("MineColor").withValue(new PagonColor(255, 0, 0, 81)).register(this);
   Value<PagonColor> colorDoneConfig = (new ValueBuilder<PagonColor>()).withDescriptor("DoneColor").withValue(new PagonColor(0, 255, 0, 81)).register(this);
   Value<Number> fadeTimeConfig = (new ValueBuilder<Number>()).withDescriptor("Fade-Time").withValue(250).withRange(0, 1000).withPlaces(0).register(this);
   Value<Boolean> smoothColorConfig = (new ValueBuilder<Boolean>()).withDescriptor("SmoothColor").withValue(false).register(this);
   private class_1657 playerTarget;
   private MineData packetMine;
   private MineData instantMine;
   private boolean packetSwapBack;
   private boolean manualOverride;
   private final Timer remineTimer = new Timer();
   private boolean changedInstantMine;
   private boolean waitForPacketMine;
   private boolean packetMineStuck;
   private boolean antiCrawlOverride;
   private int antiCrawlTicks;
   private final Queue<MineData> autoMineQueue = new ArrayDeque();
   private int autoMineTickDelay;
   private MineAnimation packetMineAnim = new MineAnimation(AutoMineModule.MineData.empty(), new SimpleAnimation(true, 200));
   private MineAnimation instantMineAnim = new MineAnimation(AutoMineModule.MineData.empty(), new SimpleAnimation(true, 200));

   public AutoMineModule() {
      super("AutoMine", Feature.Category.Combat);
      INSTANCE = this;
   }

   public static AutoMineModule getInstance() {
      return INSTANCE;
   }

   public String getHudInfo() {
      return this.instantMine != null ? String.format("%.1f", Math.min(this.instantMine.getBlockDamage(), 1.0F)) : super.getHudInfo();
   }

   public void onDisable() {
      super.onDisable();
      if (!this.NullCheck()) {
         this.autoMineQueue.clear();
         this.playerTarget = null;
         this.packetMine = null;
         if (this.instantMine != null) {
            this.abortMining(this.instantMine);
            this.instantMine = null;
         }

         this.packetMineAnim = new MineAnimation(AutoMineModule.MineData.empty(), new SimpleAnimation(true, 200));
         this.instantMineAnim = new MineAnimation(AutoMineModule.MineData.empty(), new SimpleAnimation(true, 200));
         this.autoMineTickDelay = 0;
         this.antiCrawlTicks = 0;
         this.manualOverride = false;
         this.antiCrawlOverride = false;
         this.waitForPacketMine = false;
         this.packetMineStuck = false;
         if (this.packetSwapBack) {
            InventoryUtils.syncItem();
            this.packetSwapBack = false;
         }

      }
   }

   @SubscribeEvent
   public void onTick(TickEvent.PlayerTickEvent.Pre event) {
      if (!this.NullCheck()) {
         if (!mc.field_1724.method_7337() && !mc.field_1724.method_7325()) {
            class_1657 currentTarget = (class_1657)TargetUtils.getTarget((double)((Number)this.enemyRangeConfig.getValue()).floatValue());
            boolean targetChanged = this.playerTarget != null && this.playerTarget != currentTarget;
            this.playerTarget = currentTarget;
            if (this.isInstantMineComplete()) {
               if (this.changedInstantMine) {
                  this.changedInstantMine = false;
               }

               if (this.waitForPacketMine) {
                  this.waitForPacketMine = false;
               }
            }

            --this.autoMineTickDelay;
            --this.antiCrawlTicks;
            if (this.packetMine != null && this.packetMine.getTicksMining() > ((Number)this.mineTicksConfig.getValue()).intValue()) {
               this.packetMineStuck = true;
               this.packetMineAnim.animation().setState(false);
               if (this.packetSwapBack) {
                  InventoryUtils.syncItem();
                  this.packetSwapBack = false;
               }

               this.packetMine = null;
               if (!this.isInstantMineComplete()) {
                  this.waitForPacketMine = true;
               }
            }

            if (this.packetMine != null) {
               float delta = SpeedmineModule.getInstance().calcBlockBreakingDelta(this.packetMine.getState(), this.packetMine.getPos());
               this.packetMine.addBlockDamage(delta);
               int slot = this.packetMine.getBestSlot();
               float damageDone = this.packetMine.getBlockDamage() + (!(Boolean)this.swapBeforeConfig.getValue() && !this.packetMineStuck ? 0.0F : delta);
               if (damageDone >= 1.0F && slot != -1 && !mc.field_1724.method_6115()) {
                  InventoryUtils.switchToSlot(slot);
                  this.packetSwapBack = true;
                  if (this.packetMineStuck) {
                     this.packetMineStuck = false;
                  }
               }
            }

            if (this.packetSwapBack) {
               if (this.packetMine != null && this.canMine(this.packetMine.getState())) {
                  this.packetMine.markAttemptedMine();
               } else {
                  InventoryUtils.syncItem();
                  this.packetSwapBack = false;
                  this.packetMineAnim.animation().setState(false);
                  this.packetMine = null;
                  if (!this.isInstantMineComplete()) {
                     this.waitForPacketMine = true;
                  }
               }
            }

            if (this.instantMine != null) {
               double dist = mc.field_1724.method_33571().method_1025(this.instantMine.getPos().method_46558());
               if (dist > ((Number)this.rangeConfig.getValue()).doubleValue() * ((Number)this.rangeConfig.getValue()).doubleValue() || this.instantMine.getTicksMining() > ((Number)this.mineTicksConfig.getValue()).intValue()) {
                  this.abortMining(this.instantMine);
                  this.instantMineAnim.animation().setState(false);
                  this.instantMine = null;
               }
            }

            if (this.instantMine != null) {
               float delta = SpeedmineModule.getInstance().calcBlockBreakingDelta(this.instantMine.getState(), this.instantMine.getPos());
               this.instantMine.addBlockDamage(delta);
               if (this.instantMine.getBlockDamage() >= ((Number)this.speedConfig.getValue()).floatValue()) {
                  boolean canMineState = this.canMine(this.instantMine.getState());
                  boolean canPlace = mc.field_1687.method_8628(this.instantMine.getState(), this.instantMine.getPos(), class_3726.method_16194());
                  if (canMineState) {
                     this.instantMine.markAttemptedMine();
                  } else {
                     this.instantMine.resetMiningTicks();
                     if (((String)this.remineConfig.getValue()).equals("NORMAL") || ((String)this.remineConfig.getValue()).equals("FAST")) {
                        this.instantMine.setTotalBlockDamage(0.0F, 0.0F);
                     }

                     if (this.manualOverride) {
                        this.manualOverride = false;
                        this.abortMining(this.instantMine);
                        this.instantMineAnim.animation().setState(false);
                        this.instantMine = null;
                     }
                  }

                  boolean passedRemine = ((String)this.remineConfig.getValue()).equals("INSTANT") || this.remineTimer.passed(500L);
                  if (this.instantMine != null && (((String)this.remineConfig.getValue()).equals("INSTANT") && (Boolean)this.packetInstantConfig.getValue() && this.packetMine == null && canPlace || canMineState && passedRemine) && (!mc.field_1724.method_6115() || !((String)AntiCheat.INSTANCE.multiTask.getValue()).equals("None") || ((String)this.swapConfig.getValue()).equals("OFF"))) {
                     this.stopMining(this.instantMine);
                     this.remineTimer.reset();
                     if (AutoCrystal.INSTANCE != null && AutoCrystal.INSTANCE.isEnabled()) {
                        AutoCrystal.INSTANCE.placeCrystal();
                     }

                     if (((String)this.remineConfig.getValue()).equals("FAST")) {
                        this.startMining(this.instantMine);
                     }
                  }
               }
            }

            if (this.manualOverride && (this.instantMine == null || this.instantMine.getGoal() != AutoMineModule.MiningGoal.MANUAL)) {
               this.manualOverride = false;
            }

            if (this.antiCrawlOverride && (this.instantMine == null || this.instantMine.getGoal() != AutoMineModule.MiningGoal.PREVENT_CRAWL)) {
               this.antiCrawlOverride = false;
            }

            if ((Boolean)this.autoConfig.getValue()) {
               if (!this.autoMineQueue.isEmpty() && this.autoMineTickDelay <= 0) {
                  MineData next = (MineData)this.autoMineQueue.poll();
                  if (next != null) {
                     this.startMining(next);
                     this.autoMineTickDelay = 5;
                  }
               }

               class_2338 antiCrawlPos = this.getAntiCrawlPos(this.playerTarget);
               if (this.antiCrawlOverride) {
                  if (mc.field_1724.method_18376().equals(class_4050.field_18079)) {
                     this.antiCrawlTicks = 10;
                  }

                  if (this.antiCrawlTicks <= 0 || !this.isInstantMineComplete() && antiCrawlPos != null && !this.instantMine.getPos().equals(antiCrawlPos)) {
                     this.antiCrawlOverride = false;
                  }
               }

               if (this.autoMineQueue.isEmpty() && !this.manualOverride && !this.antiCrawlOverride) {
                  if ((Boolean)this.antiCrawlConfig.getValue() && mc.field_1724.method_18376().equals(class_4050.field_18079) && antiCrawlPos != null) {
                     MineData data = new MineData(antiCrawlPos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue() ? BlockUtils.getMineableSide(antiCrawlPos, false) : class_2350.field_11036, AutoMineModule.MiningGoal.PREVENT_CRAWL);
                     if (this.isInstantMineComplete() || !this.instantMine.equals(data)) {
                        this.startAutoMine(data);
                        this.antiCrawlOverride = true;
                     }
                  } else if (this.playerTarget != null && !targetChanged) {
                     class_2338 targetPos = BlockUtils.getRoundedBlockPos(this.playerTarget.method_23317(), this.playerTarget.method_23318(), this.playerTarget.method_23321());
                     boolean bedrockPhased = this.isBedrock(this.playerTarget, targetPos) && !this.playerTarget.method_20448();
                     if (!this.isInstantMineComplete() && this.checkDataY(this.instantMine, targetPos, bedrockPhased)) {
                        this.abortMining(this.instantMine);
                        this.instantMineAnim.animation().setState(false);
                        this.instantMine = null;
                     } else if (this.packetMine != null && this.checkDataY(this.packetMine, targetPos, bedrockPhased)) {
                        this.packetMineAnim.animation().setState(false);
                        if (this.packetSwapBack) {
                           InventoryUtils.syncItem();
                           this.packetSwapBack = false;
                        }

                        this.packetMine = null;
                        this.waitForPacketMine = false;
                     } else {
                        List<class_2338> phasedBlocks = this.getPhaseBlocks(this.playerTarget, targetPos, bedrockPhased);
                        if (!phasedBlocks.isEmpty()) {
                           class_2338 pos1 = (class_2338)phasedBlocks.removeFirst();
                           MineData bestMine = new MineData(pos1, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue() ? BlockUtils.getMineableSide(pos1, false) : class_2350.field_11036);
                           if (this.packetMine == null && (Boolean)this.doubleBreakConfig.getValue() || this.isInstantMineComplete()) {
                              this.startAutoMine(bestMine);
                           }
                        } else {
                           List<class_2338> miningBlocks = this.getMiningBlocks(this.playerTarget, targetPos, bedrockPhased);
                           MineData bestMine = this.getInstantMine(miningBlocks, bedrockPhased);
                           if (bestMine != null && (this.packetMine == null && !this.changedInstantMine && (Boolean)this.doubleBreakConfig.getValue() || this.isInstantMineComplete())) {
                              this.startAutoMine(bestMine);
                           }
                        }
                     }
                  } else {
                     if (!this.isInstantMineComplete() && this.instantMine.getGoal() == AutoMineModule.MiningGoal.MINING_ENEMY) {
                        this.abortMining(this.instantMine);
                        this.instantMineAnim.animation().setState(false);
                        this.instantMine = null;
                     }

                     if (this.packetMine != null && this.packetMine.getGoal() == AutoMineModule.MiningGoal.MINING_ENEMY) {
                        this.packetMineAnim.animation().setState(false);
                        if (this.packetSwapBack) {
                           InventoryUtils.syncItem();
                           this.packetSwapBack = false;
                        }

                        this.packetMine = null;
                        this.waitForPacketMine = false;
                     }
                  }
               }
            }

         }
      }
   }

   @SubscribeEvent
   public void onAttackBlock(LivingEvent.AttackBlock event) {
      if (!this.NullCheck()) {
         if (!mc.field_1724.method_7337() && !mc.field_1724.method_7325()) {
            event.setCancelled(true);
            if (event.getState().method_26204().method_36555() != -1.0F && this.canMine(event.getState()) && !this.isMining(event.getPos())) {
               MineData data = new MineData(event.getPos(), event.getDirection(), AutoMineModule.MiningGoal.MANUAL);
               if (this.instantMine != null && this.instantMine.getGoal() == AutoMineModule.MiningGoal.MINING_ENEMY || this.packetMine != null && this.packetMine.getGoal() == AutoMineModule.MiningGoal.MINING_ENEMY) {
                  this.manualOverride = true;
               }

               if (!(Boolean)this.doubleBreakConfig.getValue()) {
                  this.instantMine = data;
                  this.startMining(this.instantMine);
                  mc.field_1724.method_6104(class_1268.field_5808);
               } else {
                  boolean updateChanged = false;
                  if (!this.isInstantMineComplete() && !this.changedInstantMine) {
                     if (this.packetMine == null) {
                        this.packetMine = this.instantMine.copy();
                        this.packetMineAnim = new MineAnimation(this.packetMine, new SimpleAnimation(true, ((Number)this.fadeTimeConfig.getValue()).intValue()));
                     } else {
                        updateChanged = true;
                     }
                  }

                  this.instantMine = data;
                  this.startMining(this.instantMine);
                  mc.field_1724.method_6104(class_1268.field_5808);
                  if (updateChanged) {
                     this.changedInstantMine = true;
                  }

               }
            }
         }
      }
   }

   @SubscribeEvent
   public void onPacketSend(PacketEvent.Send event) {
      if (!this.NullCheck()) {
         if (event.getPacket() instanceof class_2868 && (Boolean)this.switchResetConfig.getValue() && this.instantMine != null) {
            this.instantMine.setTotalBlockDamage(0.0F, 0.0F);
         }

      }
   }

   @SubscribeEvent
   public void onPacketReceive(PacketEvent.Receive event) {
      if (!this.NullCheck()) {
         class_2596 var3 = event.getPacket();
         if (var3 instanceof class_2626) {
            class_2626 packet = (class_2626)var3;
            if (this.canMine(packet.method_11308()) && this.antiCrawlOverride && packet.method_11309().equals(this.getAntiCrawlPos(this.playerTarget))) {
               this.antiCrawlTicks = 10;
            }
         }

      }
   }

   public void startAutoMine(MineData data) {
      if (this.canMine(data.getState()) && !this.isMining(data.getPos())) {
         if (!(Boolean)this.doubleBreakConfig.getValue()) {
            this.instantMine = data;
            this.autoMineQueue.offer(data);
         } else if ((!this.changedInstantMine || this.isInstantMineComplete()) && !this.waitForPacketMine) {
            boolean updateChanged = false;
            if (!this.isInstantMineComplete() && !this.changedInstantMine) {
               if (this.packetMine == null) {
                  this.packetMine = this.instantMine.copy();
                  this.packetMineAnim = new MineAnimation(this.packetMine, new SimpleAnimation(true, ((Number)this.fadeTimeConfig.getValue()).intValue()));
               } else {
                  updateChanged = true;
               }
            }

            this.instantMine = data;
            this.autoMineQueue.offer(data);
            if (updateChanged) {
               this.changedInstantMine = true;
            }

         }
      }
   }

   public MineData getInstantMine(List<class_2338> miningBlocks, boolean bedrockPhased) {
      PriorityQueue<MineData> valid = new PriorityQueue();

      for(class_2338 pos : miningBlocks) {
         class_2680 state = mc.field_1687.method_8320(pos);
         if (this.isAutoMineBlock(state.method_26204())) {
            double dist = mc.field_1724.method_33571().method_1025(pos.method_46558());
            if (!(dist > ((Number)this.rangeConfig.getValue()).doubleValue() * ((Number)this.rangeConfig.getValue()).doubleValue())) {
               class_2680 below = mc.field_1687.method_8320(pos.method_10074());
               if (bedrockPhased || below.method_27852(class_2246.field_10540) || below.method_27852(class_2246.field_9987)) {
                  class_2350 dir = (Boolean)AntiCheat.INSTANCE.strictDirection.getValue() ? BlockUtils.getMineableSide(pos, false) : class_2350.field_11036;
                  valid.add(new MineData(pos, dir));
               }
            }
         }
      }

      return valid.isEmpty() ? null : (MineData)valid.peek();
   }

   public List<class_2338> getPhaseBlocks(class_1657 player, class_2338 playerPos, boolean targetBedrockPhased) {
      List<class_2338> phaseBlocks = BlockUtils.getAllInBox(player.method_5829(), targetBedrockPhased && (Boolean)this.headConfig.getValue() ? playerPos.method_10084() : playerPos);
      phaseBlocks.removeIf((p) -> {
         class_2680 state = mc.field_1687.method_8320(p);
         if (this.isAutoMineBlock(state.method_26204()) && this.canMine(state) && !this.isMining(p)) {
            double dist = mc.field_1724.method_33571().method_1025(p.method_46558());
            return dist > ((Number)this.rangeConfig.getValue()).doubleValue() * ((Number)this.rangeConfig.getValue()).doubleValue() || (Boolean)this.avoidSelfConfig.getValue() && this.intersectsPlayer(p);
         } else {
            return true;
         }
      });
      if (targetBedrockPhased && (Boolean)this.aboveHeadConfig.getValue()) {
         phaseBlocks.add(playerPos.method_10086(2));
      }

      return phaseBlocks;
   }

   public List<class_2338> getMiningBlocks(class_1657 player, class_2338 playerPos, boolean bedrockPhased) {
      List<class_2338> surround = ProtectionUtils.getSurroundEntities(player, true);
      List<class_2338> miningBlocks;
      if (bedrockPhased) {
         miningBlocks = new ArrayList();
         if ((Boolean)this.headConfig.getValue()) {
            miningBlocks.addAll(surround.stream().map(class_2338::method_10084).toList());
         }

         class_2680 below = mc.field_1687.method_8320(playerPos.method_10074());
         if (this.canMine(below)) {
            miningBlocks.add(playerPos.method_10074());
         }
      } else {
         miningBlocks = new ArrayList(surround);
      }

      miningBlocks.removeIf((p) -> (Boolean)this.avoidSelfConfig.getValue() && this.intersectsPlayer(p));
      return miningBlocks;
   }

   private class_2338 getAntiCrawlPos(class_1657 playerTarget) {
      if (!mc.field_1724.method_24828()) {
         return null;
      } else {
         class_2338 crawlingPos = BlockUtils.getRoundedBlockPos(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321());
         boolean playerBelow = playerTarget != null && BlockUtils.getRoundedBlockPos(playerTarget.method_23317(), playerTarget.method_23318(), playerTarget.method_23321()).method_10264() < crawlingPos.method_10264();
         if (playerBelow) {
            class_2680 state = mc.field_1687.method_8320(crawlingPos.method_10074());
            if (this.isAutoMineBlock(state.method_26204()) && this.canMine(state)) {
               return crawlingPos.method_10074();
            }
         } else {
            class_2680 state = mc.field_1687.method_8320(crawlingPos.method_10084());
            if (this.isAutoMineBlock(state.method_26204()) && this.canMine(state)) {
               return crawlingPos.method_10084();
            }
         }

         return null;
      }
   }

   private boolean checkDataY(MineData data, class_2338 targetPos, boolean bedrockPhased) {
      return data.getGoal() == AutoMineModule.MiningGoal.MINING_ENEMY && !bedrockPhased && data.getPos().method_10264() != targetPos.method_10264();
   }

   private boolean intersectsPlayer(class_2338 pos) {
      List<class_2338> playerBlocks = BlockUtils.getAllInBox(mc.field_1724.method_5829(), mc.field_1724.method_24515());
      List<class_2338> surround = ProtectionUtils.getSurroundEntities(mc.field_1724, true);
      return playerBlocks.contains(pos) || surround.contains(pos);
   }

   private boolean isBedrock(class_1657 player, class_2338 pos) {
      for(class_2338 p : BlockUtils.getAllInBox(player.method_5829(), pos)) {
         class_2680 state = mc.field_1687.method_8320(p);
         if (state.method_27852(class_2246.field_9987) || BlockUtils.isUnbreakable(state)) {
            return true;
         }
      }

      return false;
   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldEvent event) {
      if (!this.NullCheck()) {
         if (!mc.field_1724.method_7337() && !mc.field_1724.method_7325()) {
            RenderBuffers.preRender();
            if (this.instantMineAnim != null && this.instantMineAnim.animation().getFactor() > 0.01F) {
               this.renderMiningData(event, this.instantMineAnim, true);
            }

            if ((Boolean)this.doubleBreakConfig.getValue() && this.packetMineAnim != null && this.packetMineAnim.animation().getFactor() > 0.01F) {
               this.renderMiningData(event, this.packetMineAnim, false);
            }

            RenderBuffers.postRender();
         }
      }
   }

   void renderMiningData(RenderWorldEvent event, MineAnimation mineAnimation, boolean isInstant) {
      MineData data = mineAnimation.data();
      SimpleAnimation animation = mineAnimation.animation();
      int boxAlpha = (int)(40.0F * animation.getFactor());
      int lineAlpha = (int)(100.0F * animation.getFactor());
      Color boxC;
      Color lineC;
      if ((Boolean)this.smoothColorConfig.getValue()) {
         boxC = !this.canMine(data.getState()) ? ColorUtil.newAlpha(((PagonColor)this.colorDoneConfig.getValue()).getColor(), boxAlpha) : ColorUtil.interpolate(Math.min(data.getBlockDamage(), 1.0F), ColorUtil.newAlpha(((PagonColor)this.colorDoneConfig.getValue()).getColor(), boxAlpha), ColorUtil.newAlpha(((PagonColor)this.colorConfig.getValue()).getColor(), boxAlpha));
         lineC = !this.canMine(data.getState()) ? ColorUtil.newAlpha(((PagonColor)this.colorDoneConfig.getValue()).getColor(), lineAlpha) : ColorUtil.interpolate(Math.min(data.getBlockDamage(), 1.0F), ColorUtil.newAlpha(((PagonColor)this.colorDoneConfig.getValue()).getColor(), lineAlpha), ColorUtil.newAlpha(((PagonColor)this.colorConfig.getValue()).getColor(), lineAlpha));
      } else {
         Color base = !(data.getBlockDamage() >= 0.95F) && this.canMine(data.getState()) ? ((PagonColor)this.colorConfig.getValue()).getColor() : ((PagonColor)this.colorDoneConfig.getValue()).getColor();
         boxC = ColorUtil.newAlpha(base, boxAlpha);
         lineC = ColorUtil.newAlpha(base, lineAlpha);
      }

      class_2338 pos = data.getPos();
      class_265 shape = class_259.method_1077();
      if (!isInstant || data.getBlockDamage() < ((Number)this.speedConfig.getValue()).floatValue()) {
         shape = data.getState().method_26218(mc.field_1687, pos);
         shape = shape.method_1110() ? class_259.method_1077() : shape;
      }

      class_238 r1 = shape.method_1107();
      class_243 center = r1.method_996(pos).method_1005();
      float total = isInstant ? ((Number)this.speedConfig.getValue()).floatValue() : 1.0F;
      float scale = (!isInstant || !(data.getBlockDamage() >= ((Number)this.speedConfig.getValue()).floatValue())) && this.canMine(data.getState()) ? class_3532.method_15363((data.getBlockDamage() + (data.getBlockDamage() - data.getLastDamage()) * event.getTickDelta()) / total, 0.0F, 1.0F) : 1.0F;
      double dx = (r1.field_1320 - r1.field_1323) / (double)2.0F;
      double dy = (r1.field_1325 - r1.field_1322) / (double)2.0F;
      double dz = (r1.field_1324 - r1.field_1321) / (double)2.0F;
      class_238 scaled = (new class_238(center, center)).method_1009(dx * (double)scale, dy * (double)scale, dz * (double)scale);
      RenderUtil.renderBox(RenderType.FILL, scaled, boxC, boxC);
      RenderUtil.renderBox(RenderType.LINES, scaled, lineC, lineC);
   }

   public void startMining(MineData data) {
      if ((Boolean)this.rotateConfig.getValue()) {
         float[] rots = RotationUtils.getRotationsTo(mc.field_1724.method_33571(), data.getPos().method_46558());
         if ((Boolean)this.grimConfig.getValue()) {
            RotationUtils.doSilentRotate(rots);
         } else {
            RotationUtils.setRotation(rots[0], rots[1]);
         }
      }

      if ((Boolean)this.doubleBreakConfig.getValue()) {
         if ((Boolean)this.grimNewConfig.getValue()) {
            if (!(Boolean)AntiCheat.INSTANCE.grimRotations.getValue()) {
               PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12973, data.getPos(), data.getDirection()));
               PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12968, data.getPos(), data.getDirection()));
               PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12971, data.getPos(), data.getDirection()));
            } else {
               PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12968, data.getPos(), data.getDirection()));
            }

            PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12973, data.getPos(), data.getDirection()));
            PacketManager.INSTANCE.sendPacket(new class_2879(class_1268.field_5808));
            PacketManager.INSTANCE.sendPacket(new class_2879(class_1268.field_5808));
            PacketManager.INSTANCE.sendPacket(new class_2879(class_1268.field_5808));
         } else {
            PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12973, data.getPos(), data.getDirection()));
            PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12968, data.getPos(), data.getDirection()));
            PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12973, data.getPos(), data.getDirection()));
            PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12973, data.getPos(), data.getDirection()));
            PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12968, data.getPos(), data.getDirection()));
            PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12973, data.getPos(), data.getDirection()));
            PacketManager.INSTANCE.sendPacket(new class_2879(class_1268.field_5808));
         }
      } else {
         PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12968, data.getPos(), data.getDirection()));
      }

      if ((Boolean)this.rotateConfig.getValue() && (Boolean)this.grimConfig.getValue()) {
         RotationUtils.silentSync();
      }

      this.instantMineAnim = new MineAnimation(data, new SimpleAnimation(true, ((Number)this.fadeTimeConfig.getValue()).intValue()));
   }

   public void abortMining(MineData data) {
      PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12971, data.getPos(), data.getDirection()));
   }

   public void stopMining(MineData data) {
      if ((Boolean)this.rotateConfig.getValue()) {
         float[] rots = RotationUtils.getRotationsTo(mc.field_1724.method_33571(), data.getPos().method_46558());
         if ((Boolean)this.grimConfig.getValue()) {
            RotationUtils.doSilentRotate(rots);
         } else {
            RotationUtils.setRotation(rots[0], rots[1]);
         }
      }

      int slot = data.getBestSlot();
      if (slot != -1) {
         this.swapTo(slot);
      }

      this.stopMiningInternal(data);
      if (slot != -1) {
         this.swapSync(slot);
      }

      if ((Boolean)this.rotateConfig.getValue() && (Boolean)this.grimConfig.getValue()) {
         RotationUtils.silentSync();
      }

   }

   private void stopMiningInternal(MineData data) {
      PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12973, data.getPos(), data.getDirection()));
      PacketManager.INSTANCE.sendPacket(new class_2846(class_2847.field_12971, data.getPos(), data.getDirection()));
   }

   public boolean isInstantMineComplete() {
      return this.instantMine == null || this.instantMine.getBlockDamage() >= ((Number)this.speedConfig.getValue()).floatValue() && !this.canMine(this.instantMine.getState());
   }

   public class_2338 getMiningBlock() {
      if (this.instantMine != null) {
         double damage = (double)this.instantMine.getBlockDamage() / ((Number)this.speedConfig.getValue()).doubleValue();
         if (damage > (double)0.75F) {
            return this.instantMine.getPos();
         }
      }

      return null;
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

   public boolean isSilentSwapping() {
      return this.packetSwapBack;
   }

   private boolean isMining(class_2338 pos) {
      return this.instantMine != null && this.instantMine.getPos().equals(pos) || this.packetMine != null && this.packetMine.getPos().equals(pos);
   }

   private boolean isAutoMineBlock(class_2248 block) {
      if (BlockUtils.isUnbreakable(block.method_9564())) {
         return false;
      } else {
         boolean var10000;
         switch ((String)this.selectionConfig.getValue()) {
            case "WHITELIST" -> var10000 = true;
            case "BLACKLIST" -> var10000 = true;
            default -> var10000 = true;
         }

         return var10000;
      }
   }

   public boolean canMine(class_2680 state) {
      return !state.method_26215() && state.method_26227().method_15769();
   }

   private boolean NullCheck() {
      return mc.field_1724 == null || mc.field_1687 == null;
   }

   public static class MineData implements Comparable<MineData> {
      private final class_2338 pos;
      private final class_2350 direction;
      private final MiningGoal goal;
      private int ticksMining;
      private float blockDamage;
      private float lastDamage;

      public MineData(class_2338 pos, class_2350 direction) {
         this.pos = pos;
         this.direction = direction;
         this.goal = AutoMineModule.MiningGoal.MINING_ENEMY;
      }

      public MineData(class_2338 pos, class_2350 direction, MiningGoal goal) {
         this.pos = pos;
         this.direction = direction;
         this.goal = goal;
      }

      private double getPriority() {
         double dist = IMinecraft.mc.field_1724.method_33571().method_1025(this.pos.method_10074().method_46558());
         return AutoCrystal.INSTANCE != null && dist <= ((Number)AutoCrystal.INSTANCE.placeRange.getValue()).doubleValue() ? (double)10.0F : (double)0.0F;
      }

      public int compareTo(MineData o) {
         return Double.compare(this.getPriority(), o.getPriority());
      }

      public boolean equals(Object obj) {
         boolean var10000;
         if (obj instanceof MineData d) {
            if (d.pos.equals(this.pos)) {
               var10000 = true;
               return var10000;
            }
         }

         var10000 = false;
         return var10000;
      }

      public void resetMiningTicks() {
         this.ticksMining = 0;
      }

      public void markAttemptedMine() {
         ++this.ticksMining;
      }

      public void addBlockDamage(float d) {
         this.lastDamage = this.blockDamage;
         this.blockDamage += d;
      }

      public void setTotalBlockDamage(float d, float l) {
         this.blockDamage = d;
         this.lastDamage = l;
      }

      public class_2338 getPos() {
         return this.pos;
      }

      public class_2350 getDirection() {
         return this.direction;
      }

      public MiningGoal getGoal() {
         return this.goal;
      }

      public int getTicksMining() {
         return this.ticksMining;
      }

      public float getBlockDamage() {
         return this.blockDamage;
      }

      public float getLastDamage() {
         return this.lastDamage;
      }

      public static MineData empty() {
         return new MineData(class_2338.field_10980, class_2350.field_11036);
      }

      public MineData copy() {
         MineData d = new MineData(this.pos, this.direction, this.goal);
         d.setTotalBlockDamage(this.blockDamage, this.lastDamage);
         return d;
      }

      public class_2680 getState() {
         return IMinecraft.mc.field_1687.method_8320(this.pos);
      }

      public int getBestSlot() {
         return InventoryUtils.getBestToolSlot(this.getState().method_26204());
      }
   }

   public static record MineAnimation(MineData data, SimpleAnimation animation) {
   }

   public static class SimpleAnimation {
      private long startTime = System.currentTimeMillis();
      private final int duration;
      private boolean forward;

      public SimpleAnimation(boolean forward, int duration) {
         this.duration = duration;
         this.forward = forward;
      }

      public float getFactor() {
         long elapsed = System.currentTimeMillis() - this.startTime;
         float progress = this.duration > 0 ? class_3532.method_15363((float)elapsed / (float)this.duration, 0.0F, 1.0F) : 1.0F;
         return this.forward ? progress : 1.0F - progress;
      }

      public void setState(boolean state) {
         if (state != this.forward) {
            this.forward = state;
            this.startTime = System.currentTimeMillis();
         }

      }
   }

   public static enum MiningGoal {
      MANUAL,
      MINING_ENEMY,
      PREVENT_CRAWL;

      // $FF: synthetic method
      private static MiningGoal[] $values() {
         return new MiningGoal[]{MANUAL, MINING_ENEMY, PREVENT_CRAWL};
      }
   }

   public static enum RemineMode {
      INSTANT,
      NORMAL,
      FAST;

      // $FF: synthetic method
      private static RemineMode[] $values() {
         return new RemineMode[]{INSTANT, NORMAL, FAST};
      }
   }

   public static enum Selection {
      WHITELIST,
      BLACKLIST,
      ALL;

      // $FF: synthetic method
      private static Selection[] $values() {
         return new Selection[]{WHITELIST, BLACKLIST, ALL};
      }
   }

   public static enum Swap {
      NORMAL,
      SILENT,
      SILENT_ALT,
      OFF;

      // $FF: synthetic method
      private static Swap[] $values() {
         return new Swap[]{NORMAL, SILENT, SILENT_ALT, OFF};
      }
   }

   public static class Timer {
      private long last = System.currentTimeMillis();

      public void reset() {
         this.last = System.currentTimeMillis();
      }

      public boolean passed(long ms) {
         return System.currentTimeMillis() - this.last >= ms;
      }
   }
}
