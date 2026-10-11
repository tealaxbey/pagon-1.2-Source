package org.itsmahir.pagon.impl.features.modules.combat;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1774;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2824;
import net.minecraft.class_2868;
import net.minecraft.class_2885;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_7204;
import net.minecraft.class_1297.class_5529;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.event.events.render.FrameEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.event.events.world.EntityEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.management.PriorityManager;
import org.itsmahir.pagon.api.management.RotationManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.world.RenderType;
import org.itsmahir.pagon.api.utils.targeting.TargetUtils;
import org.itsmahir.pagon.api.utils.world.AntiFeetPlaceResult;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.utils.world.CrystalUtil;
import org.itsmahir.pagon.api.utils.world.RaytraceUtils;
import org.itsmahir.pagon.api.utils.world.ca.BreakResult;
import org.itsmahir.pagon.api.utils.world.ca.PlaceResult;
import org.itsmahir.pagon.api.utils.world.ca.Result;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;
import org.itsmahir.pagon.impl.features.modules.exploit.Blink;
import org.itsmahir.pagon.impl.features.modules.exploit.Phase;
import org.itsmahir.pagon.impl.features.modules.misc.autobreak.AutoBreak;

public class AutoCrystal extends Module {
   public static AutoCrystal INSTANCE;
   Value<Number> placeRange = (new ValueBuilder<Number>()).withDescriptor("PlaceRange").withValue(6).withRange((double)1.0F, (double)6.0F).withPlaces(1).register(this);
   Value<Number> placeWallsRange = (new ValueBuilder<Number>()).withDescriptor("PlaceWallRange").withValue((double)3.0F).withRange((double)1.0F, (double)6.0F).withPlaces(1).register(this);
   Value<Number> breakRange = (new ValueBuilder<Number>()).withDescriptor("Break Range").withValue(6).withRange((double)1.0F, (double)6.0F).withPlaces(1).register(this);
   Value<Number> breakWallsRange = (new ValueBuilder<Number>()).withDescriptor("BreakWallRange").withValue((double)3.0F).withPlaces(1).withRange((double)1.0F, (double)6.0F).register(this);
   Value<String> antiWeakness = (new ValueBuilder<String>()).withDescriptor("AntiWeakness").withValue("None").withModes("None", "Normal", "Silent").register(this);
   Value<Number> placeDelay = (new ValueBuilder<Number>()).withDescriptor("Place Delay").withValue(1).withRange(0, 1000).register(this);
   Value<Number> breakDelay = (new ValueBuilder<Number>()).withDescriptor("Break Delay").withValue(1).withRange(0, 1000).register(this);
   Value<String> timing = (new ValueBuilder<String>()).withDescriptor("Timing").withValue("Pre").withModes("Pre", "Post").register(this);
   Value<String> sequence = (new ValueBuilder<String>()).withDescriptor("Sequential").withValue("None").withModes("None", "Normal", "Strong").register(this);
   Value<Number> ticksExisted = (new ValueBuilder<Number>()).withDescriptor("Ticks Existed").withValue(0.0F).withRange(0.0F, 10.0F).withPlaces(0).register(this);
   Value<Boolean> inhibit = (new ValueBuilder<Boolean>()).withDescriptor("Inhibit").withValue(false).register(this);
   Value<String> swapWait = (new ValueBuilder<String>()).withDescriptor("Swap Delay").withValue("None").withModes("None", "Semi", "Full").register(this);
   Value<Number> swapWaitDelay = (new ValueBuilder<Number>()).withDescriptor("Swap Wait Delay").withValue(300).withRange(50, 500).register(this);
   Value<String> targetSorting = (new ValueBuilder<String>()).withDescriptor("Priority").withValue("Damage").withModes("Damage", "Distance").register(this);
   Value<Number> targetRange = (new ValueBuilder<Number>()).withDescriptor("Target Range").withValue((double)7.0F).withRange((double)3.0F, (double)20.0F).register(this);
   public Value<Number> minDmg = (new ValueBuilder<Number>()).withDescriptor("Min Damage").withValue(2).withRange(0, 36).register(this);
   Value<Number> maxSelfDmg = (new ValueBuilder<Number>()).withDescriptor("Max Self Damage").withValue(4).withRange(0, 36).register(this);
   Value<Boolean> noSuicide = (new ValueBuilder<Boolean>()).withDescriptor("Anti Suicide").withValue(false).register(this);
   Value<Boolean> terrain = (new ValueBuilder<Boolean>()).withDescriptor("Terrain").withValue(false).register(this);
   public Value<Boolean> armorAssume = (new ValueBuilder<Boolean>()).withDescriptor("AssumeBestArmor").withValue(false).register(this);
   public Value<String> placements = (new ValueBuilder<String>()).withDescriptor("Placements").withValue("Native").withModes("Native", "Protocol").register(this);
   Value<Boolean> antiSurround = (new ValueBuilder<Boolean>()).withDescriptor("AntiSurround").withValue(false).register(this);
   Value<Boolean> limit = (new ValueBuilder<Boolean>()).withDescriptor("Limit").withValue(false).register(this);
   Value<Boolean> predict = (new ValueBuilder<Boolean>()).withDescriptor("Boost").withValue(false).register(this);
   Value<Boolean> simulate = (new ValueBuilder<Boolean>()).withDescriptor("Simulate").withValue(false).register(this);
   Value<Boolean> noGapSwitch = (new ValueBuilder<Boolean>()).withDescriptor("Pause on Eat").withValue(false).register(this);
   Value<String> autoSwitch = (new ValueBuilder<String>()).withDescriptor("Switch").withValue("None").withModes("None", "Normal", "Silent", "AltSwap").withAction((s) -> this.noGapSwitch.setActive(s.equals("Normal"))).register(this);
   Value<Boolean> rotate = (new ValueBuilder<Boolean>()).withDescriptor("Rotate").withValue(false).register(this);
   Value<String> rotationsType;
   Value<Boolean> ignoreMine;
   Value<Boolean> render;
   Value<Boolean> fill;
   public Value<PagonColor> fillColorS;
   Value<Boolean> outline;
   public Value<PagonColor> lineColorS;
   boolean placedOnspawn;
   public final Map<Integer, Long> hitCrystals;
   protected final LinkedList<Long> brokeCrystals;
   public class_2338 renderPos;
   public class_2338 calcPos;
   public class_2338 lastPos;
   float[] placeTargetRot;
   float[] breakTargetRot;
   boolean breaking;
   boolean swapStop;
   class_1297 swapStopCrystal;
   int CRYSTALS_PER_SECOND;
   public class_1657 target;
   public boolean offhand;
   boolean placeFlag;
   boolean breakFlag;
   Timer placeTimer;
   Timer breakTimer;
   Timer swapTimer;
   Timer cooldown;
   public class_1297 calcCrystal;
   public double renderDMG;
   String lastTargetName;
   class_2350 overide;
   float lastTime;
   boolean hasRotated;

   public AutoCrystal() {
      super("AutoCrystal", Feature.Category.Combat);
      this.rotationsType = (new ValueBuilder<String>()).withDescriptor("Type", "rotationsType").withValue("Vanilla").withModes("Vanilla", "NCP", "Grim", "Silent").withParent(this.rotate).withParentEnabled(true).register(this);
      this.ignoreMine = (new ValueBuilder<Boolean>()).withDescriptor("IgnoreMine").withValue(false).register(this);
      this.render = (new ValueBuilder<Boolean>()).withDescriptor("Render").withValue(false).register(this);
      this.fill = (new ValueBuilder<Boolean>()).withDescriptor("Fill").withValue(true).withParent(this.render).withParentEnabled(true).register(this);
      this.fillColorS = (new ValueBuilder<PagonColor>()).withDescriptor("Fill Color").withValue(new PagonColor(0, 0, 0, 100)).withParent(this.fill).withParentEnabled(true).register(this);
      this.outline = (new ValueBuilder<Boolean>()).withDescriptor("Outline").withValue(true).withParent(this.render).withParentEnabled(true).register(this);
      this.lineColorS = (new ValueBuilder<PagonColor>()).withDescriptor("Outline Color").withValue(new PagonColor(255, 255, 255, 255)).withParent(this.outline).withParentEnabled(true).register(this);
      this.placedOnspawn = false;
      this.hitCrystals = new ConcurrentHashMap();
      this.brokeCrystals = new LinkedList();
      this.placeTargetRot = null;
      this.breakTargetRot = null;
      this.breaking = false;
      this.swapStop = false;
      this.swapStopCrystal = null;
      this.CRYSTALS_PER_SECOND = 0;
      this.placeFlag = false;
      this.breakFlag = false;
      this.placeTimer = new Timer();
      this.breakTimer = new Timer();
      this.swapTimer = new Timer();
      this.cooldown = new Timer();
      this.lastTargetName = null;
      this.overide = null;
      this.lastTime = 0.0F;
      this.hasRotated = false;
      INSTANCE = this;
      this.noGapSwitch.setActive(((String)this.autoSwitch.getValue()).equals("Normal"));
      this.swapWaitDelay.setActive(!((String)this.swapWait.getValue()).equals("None"));
   }

   private boolean isSilentMode() {
      String val = this.autoSwitch.getValue();
      return val.equals("Silent") || val.equals("AltSwap");
   }

   private boolean isAltSwap() {
      return ((String)this.autoSwitch.getValue()).equals("AltSwap");
   }

   @SubscribeEvent
   public void onEntityRemove(EntityEvent.Remove event) {
      if (event.getEntity() instanceof class_1511 && this.hitCrystals.containsKey(event.getEntity().method_5628())) {
         this.brokeCrystals.add(System.currentTimeMillis());
         this.hitCrystals.remove(event.getEntity().method_5628());
      }

   }

   @SubscribeEvent
   public void onEntityAdd(EntityEvent.Add event) {
      if (!NullUtils.nullCheck()) {
         if (!(Boolean)this.rotate.getValue() || !PriorityManager.INSTANCE.isUsageLocked()) {
            if (!AntiCheat.INSTANCE.isMultiTask()) {
               if ((Boolean)this.predict.getValue()) {
                  class_1297 var3 = event.getEntity();
                  if (var3 instanceof class_1511) {
                     class_1511 entity = (class_1511)var3;
                     this.doPredict(entity);
                  }
               }

            }
         }
      }
   }

   @SubscribeEvent(0)
   public void onPacket(PacketEvent.Send event) {
      if (!NullUtils.nullCheck()) {
         class_2596 var3 = event.getPacket();
         if (var3 instanceof class_2868) {
            class_2868 packet = (class_2868)var3;
            if (packet.method_12442() == RotationManager.INSTANCE.serverSlot) {
               return;
            }

            if (!((String)this.swapWait.getValue()).equals("None")) {
               class_1293 weaknessEffect = mc.field_1724.method_6112(class_1294.field_5911);
               class_1293 strengthEffect = mc.field_1724.method_6112(class_1294.field_5910);
               if (!((String)this.antiWeakness.getValue()).equals("None") && weaknessEffect != null && (strengthEffect == null || strengthEffect.method_5578() <= weaknessEffect.method_5578())) {
                  return;
               }

               this.swapTimer.resetDelay();
            }
         }

      }
   }

   public void doPredict(class_1511 entity) {
      if (mc.field_1724.method_5649(entity.method_23317(), entity.method_23318(), entity.method_23321()) <= MathUtil.square(((Number)this.breakRange.getValue()).doubleValue())) {
         float targetDamage = CrystalUtil.calculateDamage(this.target, new class_243(entity.method_23317(), entity.method_23318(), entity.method_23321()), (Boolean)this.terrain.getValue(), false);
         if ((double)targetDamage > ((Number)this.minDmg.getValue()).doubleValue()) {
            if (((String)this.rotationsType.getValue()).equals("Silent")) {
               RotationUtils.doSilentRotate(RotationUtils.getRotationsTo(mc.field_1724.method_33571(), new class_243(entity.method_23317(), entity.method_23320(), entity.method_23321())));
               this.hasRotated = true;
            }

            class_2824 interactentity = class_2824.method_34206(entity, mc.field_1724.method_5715());
            PacketManager.INSTANCE.sendPacket(interactentity);
            mc.field_1724.method_6104(this.offhand ? class_1268.field_5810 : class_1268.field_5808);
            mc.field_1724.method_7350();
            this.hitCrystals.put(entity.method_5628(), System.currentTimeMillis());
            this.breakTimer.resetDelay();
            if (AntiCheat.INSTANCE.isMultiTask()) {
               return;
            }

            if (((String)this.sequence.getValue()).equals("Strong")) {
               int crystalSlot = InventoryUtils.getHotbarItemSlot(class_1802.field_8301);
               if (crystalSlot == -1 && !this.offhand) {
                  if (this.hasRotated) {
                     this.hasRotated = false;
                     RotationUtils.silentSync();
                  }

                  return;
               }

               if (this.calcPos != null) {
                  this.placedOnspawn = false;
                  this.placeCrystal();
                  this.placedOnspawn = true;
                  if (this.hasRotated) {
                     this.hasRotated = false;
                     RotationUtils.silentSync();
                  }
               }
            }
         }
      }

   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldEvent event) {
      if (!NullUtils.nullCheck()) {
         if ((Boolean)this.render.getValue()) {
            if (this.target != null && this.renderPos != null) {
               if (this.canRender()) {
                  Color fillC = ((PagonColor)this.fillColorS.getValue()).getColor();
                  Color lineC = ((PagonColor)this.lineColorS.getValue()).getColor();
                  if ((Boolean)this.fill.getValue()) {
                     RenderUtil.renderBox(RenderType.FILL, new class_238(this.renderPos), fillC, fillC);
                  }

                  if ((Boolean)this.outline.getValue()) {
                     RenderUtil.renderBox(RenderType.LINES, new class_238(this.renderPos), lineC, lineC);
                  }

               }
            }
         }
      }
   }

   public Result getTargetResult() {
      switch ((String)this.targetSorting.getValue()) {
         case "Damage":
            List<class_1297> targets = TargetUtils.getTargets(((Number)this.targetRange.getValue()).doubleValue()).toList();
            Result bestResult = null;

            for(class_1297 target : targets) {
               if (target != mc.field_1724) {
                  Result currentResult = this.getResult((class_1657)target);
                  if (bestResult == null) {
                     bestResult = currentResult;
                  } else if (currentResult.getDamage() > bestResult.getDamage()) {
                     bestResult = currentResult;
                  }
               }
            }

            if (bestResult == null) {
               this.reset();
               return null;
            }

            return bestResult;
         case "Distance":
            this.target = (class_1657)TargetUtils.getTarget(((Number)this.targetRange.getValue()).doubleValue());
            if (this.target == null) {
               this.reset();
               return null;
            }

            return this.getResult(this.target);
         default:
            return null;
      }
   }

   public void prepare() {
      this.offhand = mc.field_1724.method_6079().method_7909() == class_1802.field_8301;
      this.placeTimer.setDelay(((Number)this.placeDelay.getValue()).longValue());
      this.breakTimer.setDelay(((Number)this.breakDelay.getValue()).longValue());
      this.swapTimer.setDelay(((Number)this.swapWaitDelay.getValue()).longValue());
      this.cooldown.setDelay(800L);
      this.placeTargetRot = null;
      this.breakTargetRot = null;
      long nanoPre = System.nanoTime();
      Result result = this.getTargetResult();
      long nanoPost = System.nanoTime() - nanoPre;
      this.calcPos = null;
      this.calcCrystal = null;
      this.load(result);
      if ((Boolean)this.inhibit.getValue() && this.calcCrystal != null && this.hitCrystals.containsKey(this.calcCrystal.method_5628())) {
         this.breakTimer.setDelay(((Number)this.breakDelay.getValue()).longValue() + 100L);
      }

      if ((Boolean)this.rotate.getValue()) {
         this.overide = null;
         if (!((String)this.autoSwitch.getValue()).equals("None") || this.breakTargetRot != null || this.offhand || mc.field_1724.method_31548().method_7391().method_7909() == class_1802.field_8301) {
            if (this.target == null || !PlayerUtils.isBoostedByFirework() || !(Boolean)AntiCheat.INSTANCE.strafeFix.getValue() || this.target.method_6128()) {
               if (!((String)this.rotationsType.getValue()).equals("Silent")) {
                  if (this.placeTargetRot != null && this.breakTargetRot == null) {
                     RotationUtils.setRotation(this.placeTargetRot);
                  } else if (this.placeTargetRot == null && this.breakTargetRot != null) {
                     RotationUtils.setRotation(this.breakTargetRot);
                  } else if (this.placeTargetRot != null) {
                     this.handleSequence();
                  }
               }

            }
         }
      }
   }

   public boolean breakCrystal() {
      if (this.breakFlag) {
         this.breakFlag = false;
         return true;
      } else if (this.calcCrystal != null) {
         if ((Boolean)this.rotate.getValue() && this.breakTargetRot == null) {
            return true;
         } else if (this.breakTimer.isPassed()) {
            if (!((String)this.swapWait.getValue()).equals("None") && !this.swapTimer.isPassed()) {
               return true;
            } else {
               class_1293 weaknessEffect = mc.field_1724.method_6112(class_1294.field_5911);
               class_1293 strengthEffect = mc.field_1724.method_6112(class_1294.field_5910);
               boolean swapBack = false;
               int curSlot = mc.field_1724.method_31548().field_7545;
               if (!((String)this.antiWeakness.getValue()).equals("None") && weaknessEffect != null && (strengthEffect == null || strengthEffect.method_5578() <= weaknessEffect.method_5578())) {
                  int bestWeapon = InventoryUtils.getSwordSlot();
                  if (bestWeapon != -1 && mc.field_1724.method_31548().field_7545 != bestWeapon) {
                     switch ((String)this.antiWeakness.getValue()) {
                        case "Normal":
                           InventoryUtils.switchToSlot(bestWeapon);
                           break;
                        case "Silent":
                           InventoryUtils.switchToSlot(bestWeapon);
                           swapBack = true;
                     }
                  }
               }

               if (((String)this.rotationsType.getValue()).equals("Silent")) {
                  RotationUtils.doSilentRotate(this.breakTargetRot);
                  this.hasRotated = true;
               }

               class_2824 packet = class_2824.method_34206(this.calcCrystal, mc.field_1724.method_5715());
               PacketManager.INSTANCE.sendPacket(packet);
               mc.field_1724.method_6104(this.offhand ? class_1268.field_5810 : class_1268.field_5808);
               mc.field_1724.method_7350();
               if (!mc.field_1724.method_24828()) {
                  mc.field_1724.method_7277(this.calcCrystal);
               }

               this.hitCrystals.put(this.calcCrystal.method_5628(), System.currentTimeMillis());
               if (AntiCheat.INSTANCE.isMultiTask()) {
                  return false;
               } else {
                  if (swapBack) {
                     InventoryUtils.switchToSlot(curSlot);
                  }

                  this.breakTimer.resetDelay();
                  if ((Boolean)this.simulate.getValue()) {
                     mc.method_40000(() -> mc.field_1687.method_2945(this.calcCrystal.method_5628(), class_5529.field_26998));
                  }

                  return false;
               }
            }
         } else {
            return true;
         }
      } else {
         return true;
      }
   }

   public void placeCrystal() {
      if (this.placeFlag) {
         this.placeFlag = false;
      } else if (!this.placedOnspawn) {
         if (this.calcPos != null) {
            if ((Boolean)this.rotate.getValue() && this.placeTargetRot == null) {
               return;
            }

            if (this.placeTimer.isPassed()) {
               if (((String)this.swapWait.getValue()).equals("Full") && !this.swapTimer.isPassed()) {
                  return;
               }

               boolean doSilent = false;
               int crystalSlot = InventoryUtils.getHotbarItemSlot(class_1802.field_8301);
               if (crystalSlot == -1 && !this.offhand) {
                  this.renderPos = null;
                  return;
               }

               if (!this.offhand && ((String)this.autoSwitch.getValue()).equals("Normal")) {
                  if ((Boolean)this.noGapSwitch.getValue()) {
                     if (!PlayerUtils.isEatingGap()) {
                        InventoryUtils.switchToSlot(crystalSlot);
                     }
                  } else {
                     InventoryUtils.switchToSlot(crystalSlot);
                  }
               }

               if (!this.offhand && this.isSilentMode() && !(mc.field_1724.method_31548().method_7391().method_7909() instanceof class_1774)) {
                  doSilent = true;
               }

               if (!this.offhand && !((String)this.autoSwitch.getValue()).equals("Silent") && !((String)this.autoSwitch.getValue()).equals("AltSwap") && !(mc.field_1724.method_31548().method_7391().method_7909() instanceof class_1774)) {
                  this.renderPos = null;
                  return;
               }

               int oldSlot = mc.field_1724.method_31548().field_7545;
               if (doSilent) {
                  if (this.isAltSwap()) {
                     InventoryUtils.switchToBypass(InventoryUtils.hotbarToInventory(crystalSlot), false);
                  } else {
                     InventoryUtils.switchToSlot(crystalSlot);
                  }
               }

               boolean protocol = ((String)this.placements.getValue()).equals("Protocol");
               class_2350 side = BlockUtils.getPlaceableSideCrystal(this.calcPos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue());
               if (side != null) {
                  class_243 vec = this.calcPos.method_46558().method_1031((double)side.method_10163().method_10263() * (double)0.5F, (double)side.method_10163().method_10264() * (double)0.5F, (double)side.method_10163().method_10260() * (double)0.5F);
                  if (side.method_10166().method_10179()) {
                     vec = vec.method_1031((double)0.0F, 0.45, (double)0.0F);
                  }

                  if (((String)this.rotationsType.getValue()).equals("Silent")) {
                     RotationUtils.doSilentRotate(this.placeTargetRot);
                     this.hasRotated = true;
                  }

                  class_3965 result = new class_3965(vec, side, this.calcPos, false);
                  PacketManager.INSTANCE.sendPacket((class_7204)((id) -> new class_2885(this.offhand ? class_1268.field_5810 : class_1268.field_5808, result, id)));
                  mc.field_1724.method_6104(this.offhand ? class_1268.field_5810 : class_1268.field_5808);
                  if (doSilent) {
                     if (this.isAltSwap()) {
                        InventoryUtils.switchToBypass(InventoryUtils.hotbarToInventory(crystalSlot), true);
                     } else {
                        InventoryUtils.switchToSlot(oldSlot);
                     }
                  }

                  if (AntiCheat.INSTANCE.isMultiTask()) {
                     return;
                  }

                  this.lastPos = this.calcPos;
                  this.placeTimer.resetDelay();
               }
            }

            this.renderPos = this.calcPos;
         }

      }
   }

   public PlaceResult getBestPlaceResult(class_1657 targetPlayer, class_1297 currentCalcCrystal) {
      class_2338 bestPos = null;
      class_2338 bestAntiFeetPlacePos = null;
      double bestAntiFeetPlaceDamage = (double)0.5F;
      List<class_2338> sphere = BlockUtils.sphere(((Number)this.placeRange.getValue()).doubleValue() + (double)1.0F, mc.field_1724.method_24515(), true, false);
      double bestDMG = (double)0.5F;
      boolean protocol = ((String)this.placements.getValue()).equals("Protocol");

      for(class_2338 pos : sphere) {
         if (CrystalUtil.canPlaceCrystal(pos, protocol)) {
            AntiFeetPlaceResult feetPlaceResult = this.handleBBCrystal(pos, this.lastPos, currentCalcCrystal);
            if (feetPlaceResult.isPlaceAvailable() && this.checkPlace(pos, false)) {
               boolean doMiningIgnore = (Boolean)this.ignoreMine.getValue();
               float targetDamage = CrystalUtil.calculateDamage(targetPlayer, this.crystalDamageVec(pos), (Boolean)this.terrain.getValue(), doMiningIgnore);
               if (bestDMG < (double)targetDamage && (double)targetDamage > ((Number)this.minDmg.getValue()).doubleValue()) {
                  float selfDamage = CrystalUtil.calculateDamage(mc.field_1724, this.crystalDamageVec(pos), (Boolean)this.terrain.getValue(), doMiningIgnore);
                  if (!((double)selfDamage > ((Number)this.maxSelfDmg.getValue()).doubleValue()) && (!(selfDamage + 2.0F > mc.field_1724.method_6032() + mc.field_1724.method_6067()) || !(Boolean)this.noSuicide.getValue())) {
                     if (feetPlaceResult.isAntiFeetPlace()) {
                        bestAntiFeetPlaceDamage = (double)targetDamage;
                        bestAntiFeetPlacePos = pos;
                     } else {
                        bestDMG = (double)targetDamage;
                        bestPos = pos;
                     }
                  }
               }
            }
         }
      }

      if (bestDMG == (double)0.5F) {
         bestDMG = (double)0.0F;
      }

      if (bestPos == null && bestAntiFeetPlacePos != null) {
         bestPos = bestAntiFeetPlacePos;
         bestDMG = bestAntiFeetPlaceDamage;
      }

      return new PlaceResult(targetPlayer, bestDMG, bestPos, this.getPlaceRot(bestPos));
   }

   public Result getResult(class_1657 player) {
      BreakResult breakResult = this.getBestBreakResult(player);
      class_1297 currentCalcCrystal = breakResult.calcCrystal;
      return new Result(breakResult, this.getBestPlaceResult(player, currentCalcCrystal));
   }

   public boolean checkPlace(class_2338 pos, boolean ignore) {
      class_2350 side = ignore ? BlockUtils.getPlaceableSideCrystal(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue(), pos.method_10084()) : BlockUtils.getPlaceableSideCrystal(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue());
      if (side == null) {
         return false;
      } else {
         class_243 vec = pos.method_46558().method_1031((double)side.method_10163().method_10263() * (double)0.5F, (double)side.method_10163().method_10264() * (double)0.5F, (double)side.method_10163().method_10260() * (double)0.5F);
         double distanceSq = vec.method_1028(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321());
         if (distanceSq > MathUtil.square(((Number)this.placeRange.getValue()).doubleValue())) {
            return false;
         } else {
            boolean trace = BlockUtils.placeTrace(pos);
            if (trace && distanceSq > MathUtil.square(((Number)this.placeWallsRange.getValue()).doubleValue())) {
               return false;
            } else {
               double crystalDistance = mc.field_1724.method_33571().method_1022(pos.method_46558().method_1031((double)0.0F, 2.20000004768, (double)0.0F));
               if (crystalDistance > ((Number)this.breakRange.getValue()).doubleValue()) {
                  return false;
               } else {
                  class_243 raytrace = pos.method_46558().method_1031((double)0.0F, 2.20000004768, (double)0.0F);
                  if (crystalDistance > ((Number)this.breakWallsRange.getValue()).doubleValue()) {
                     class_3965 result = mc.field_1687.method_17742(new class_3959(mc.field_1724.method_33571(), raytrace, class_3960.field_17558, class_242.field_1348, mc.field_1724));
                     return result.method_17783() == class_240.field_1333;
                  } else {
                     return true;
                  }
               }
            }
         }
      }
   }

   public void load(Result result) {
      this.target = result.target;
      if (InventoryUtils.getHotbarItemSlot(class_1802.field_8301) == -1 && !this.offhand) {
         this.calcPos = null;
         this.renderPos = null;
         this.placeTargetRot = null;
         this.renderDMG = (double)0.0F;
      } else if (result.calcPos != null) {
         this.calcPos = result.calcPos;
         this.placeTargetRot = result.placeRots;
         this.renderDMG = result.bestPlaceDMG;
      } else if (result.bestPlaceDMG == (double)0.0F) {
         this.renderPos = null;
      }

      if (result.calcCrystal != null) {
         this.calcCrystal = result.calcCrystal;
         this.breakTargetRot = result.breakRots;
      }

   }

   public void strongSequence() {
      boolean breakPassed = this.breakTimer.isPassed();
      boolean placePassed = this.placeTimer.isPassed();
      if (this.calcPos != null || this.calcCrystal != null) {
         if (breakPassed && !placePassed) {
            RotationUtils.setRotation(this.breakTargetRot);
         } else if (placePassed && !breakPassed) {
            RotationUtils.setRotation(this.placeTargetRot);
         } else {
            if (this.calcPos == null) {
               RotationUtils.setRotation(this.breakTargetRot);
               return;
            }

            if (this.calcCrystal == null || !placePassed) {
               RotationUtils.setRotation(this.placeTargetRot);
               return;
            }

            switch ((String)this.rotationsType.getValue()) {
               case "NCP":
                  if (mc.field_1687.method_18467(class_1297.class, new class_238(this.calcPos.method_10069(0, 1, 0))).contains(this.calcCrystal)) {
                     if (RaytraceUtils.isLookingResult(mc.field_1724, this.calcCrystal, mc.field_1724.method_33571(), this.placeTargetRot, 6.0F) != null) {
                        RotationUtils.setRotation(this.placeTargetRot);
                     } else {
                        RotationUtils.setRotation(this.breakTargetRot);
                        this.placeFlag = true;
                     }
                  } else {
                     RotationUtils.setRotation(this.breakTargetRot);
                     this.placeFlag = true;
                  }
                  break;
               case "Grim":
                  RotationUtils.setRotation(this.breakTargetRot);
                  break;
               case "Vanilla":
                  if (mc.field_1687.method_18467(class_1297.class, new class_238(this.calcPos.method_10069(0, 1, 0))).contains(this.calcCrystal)) {
                     RotationUtils.setRotation(this.placeTargetRot);
                  } else if (this.calcCrystal.method_24515().method_10074().equals(this.calcPos)) {
                     RotationUtils.setRotation(this.placeTargetRot);
                  } else {
                     RotationUtils.setRotation(this.breakTargetRot);
                  }
            }
         }

      }
   }

   public class_243 crystalDamageVec(class_2338 pos) {
      return class_243.method_24954(pos).method_1031((double)0.5F, (double)1.0F, (double)0.5F);
   }

   public float[] getPlaceRot(class_2338 pos) {
      if (pos == null) {
         return null;
      } else if (!(Boolean)this.rotate.getValue()) {
         return null;
      } else {
         class_2350 side = BlockUtils.getPlaceableSideCrystal(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue());
         class_243 vec = pos.method_46558().method_1031((double)side.method_10163().method_10263() * (double)0.5F, (double)side.method_10163().method_10264() * (double)0.5F, (double)side.method_10163().method_10260() * (double)0.5F);
         if (side.method_10166().method_10179()) {
            vec = vec.method_1031((double)0.0F, 0.45, (double)0.0F);
         }

         return RotationUtils.getRotationsTo(mc.field_1724.method_33571(), vec);
      }
   }

   public void handleSequence() {
      switch ((String)this.sequence.getValue()) {
         case "None":
            RotationUtils.setRotation(this.breakTargetRot);
            this.placeFlag = true;
            break;
         case "Normal":
         case "Strong":
            this.strongSequence();
      }

   }

   public void reset() {
      this.calcCrystal = null;
      this.calcPos = null;
      this.breaking = false;
      this.breakTargetRot = null;
      this.placeTargetRot = null;
   }

   public AntiFeetPlaceResult handleBBCrystal(class_2338 pos, class_2338 lastPos, class_1297 currentCalcCrystal) {
      class_238 bb = new class_238((double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, (double)2.0F, (double)1.0F);
      class_2338 pos2 = pos.method_10084();
      double d = (double)pos2.method_10263();
      double e = (double)pos2.method_10264();
      double f = (double)pos2.method_10260();
      bb = new class_238(d, e, f, d + bb.field_1320, e + bb.field_1325, f + bb.field_1324);
      boolean isAntiFeetPlace = false;
      if (lastPos != null && lastPos.equals(pos)) {
         for(class_1297 entity : mc.field_1687.method_18467(class_1297.class, bb)) {
            if (!(entity instanceof class_1511) || entity.method_5805() && !entity.method_24515().method_10074().equals(pos) && !entity.equals(currentCalcCrystal)) {
               if (entity instanceof class_1657) {
                  if (!(Boolean)this.antiSurround.getValue()) {
                     return new AntiFeetPlaceResult(false, isAntiFeetPlace);
                  }

                  isAntiFeetPlace = true;
               } else if ((Boolean)this.antiSurround.getValue() && entity instanceof class_1542 && AutoBreak.INSTANCE.isInstantMining(entity.method_24515())) {
                  isAntiFeetPlace = true;
               } else if (entity.method_5805()) {
                  return new AntiFeetPlaceResult(false, isAntiFeetPlace);
               }
            }
         }
      } else {
         for(class_1297 entity : mc.field_1687.method_18467(class_1297.class, bb)) {
            if (entity instanceof class_1657) {
               if (!(Boolean)this.antiSurround.getValue()) {
                  return new AntiFeetPlaceResult(false, isAntiFeetPlace);
               }

               isAntiFeetPlace = true;
            } else if ((Boolean)this.antiSurround.getValue() && entity instanceof class_1542 && AutoBreak.INSTANCE.isInstantMining(entity.method_24515())) {
               isAntiFeetPlace = true;
            } else if (entity.method_5805() && !entity.equals(currentCalcCrystal)) {
               return new AntiFeetPlaceResult(false, isAntiFeetPlace);
            }
         }
      }

      return new AntiFeetPlaceResult(true, isAntiFeetPlace);
   }

   public BreakResult getBestBreakResult(class_1657 targetPlayer) {
      class_1297 bestCrystal = null;
      double maxDamage = (double)0.5F;

      for(class_1297 entity : mc.field_1687.method_18112()) {
         if (entity instanceof class_1511 crystal) {
            if (this.canBreakCrystal(crystal)) {
               float targetDamage = CrystalUtil.calculateDamage(targetPlayer, entity.method_19538(), (Boolean)this.terrain.getValue(), false);
               if (maxDamage < (double)targetDamage && (double)targetDamage > ((Number)this.minDmg.getValue()).doubleValue()) {
                  float selfDamage = CrystalUtil.calculateDamage(mc.field_1724, entity.method_19538(), (Boolean)this.terrain.getValue(), false);
                  if (!((double)selfDamage > ((Number)this.maxSelfDmg.getValue()).doubleValue()) && (!(selfDamage + 2.0F > mc.field_1724.method_6032() + mc.field_1724.method_6067()) || !(Boolean)this.noSuicide.getValue())) {
                     maxDamage = (double)targetDamage;
                     bestCrystal = entity;
                  }
               }
            }
         }
      }

      float[] breakRots = null;
      if (bestCrystal != null && (Boolean)this.rotate.getValue()) {
         breakRots = RotationUtils.getRotationsTo(mc.field_1724.method_33571(), new class_243(bestCrystal.method_23317(), bestCrystal.method_23320(), bestCrystal.method_23321()));
      }

      return new BreakResult(targetPlayer, maxDamage, bestCrystal, breakRots);
   }

   public boolean canRender() {
      if (this.calcPos == null) {
         return false;
      } else {
         int crystalSlot = InventoryUtils.getHotbarItemSlot(class_1802.field_8301);
         return crystalSlot != -1 || this.offhand;
      }
   }

   public boolean canBreakCrystal(class_1511 entity) {
      double distance = mc.field_1724.method_33571().method_1022(entity.method_19538().method_1031((double)0.0F, 1.700000047683716, (double)0.0F));
      if (((Number)this.ticksExisted.getValue()).intValue() != 0 && entity.field_6012 <= ((Number)this.ticksExisted.getValue()).intValue()) {
         return false;
      } else if (distance > ((Number)this.breakRange.getValue()).doubleValue()) {
         return false;
      } else {
         return PlayerUtils.canEntityBeSeen(entity) || distance <= ((Number)this.breakWallsRange.getValue()).doubleValue();
      }
   }

   @SubscribeEvent
   public void onFrameFlip(FrameEvent.FrameFlipEvent event) {
      long currentTimeMs = System.currentTimeMillis();
      if (!this.brokeCrystals.isEmpty()) {
         try {
            while(!this.brokeCrystals.isEmpty()) {
               long firstCrystal = (Long)this.brokeCrystals.getFirst();
               long second = 1000L;
               if (currentTimeMs - firstCrystal <= 1000L) {
                  break;
               }

               this.brokeCrystals.remove();
            }
         } catch (Exception var8) {
            this.CRYSTALS_PER_SECOND = 0;
         }
      }

      this.CRYSTALS_PER_SECOND = this.brokeCrystals.size();
   }

   @SubscribeEvent(4)
   public void onPlayerUpdate(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (!Phase.INSTANCE.isEnabled()) {
            if (!(Boolean)this.rotate.getValue() || !PriorityManager.INSTANCE.isUsageLocked()) {
               if (!Blink.INSTANCE.isEnabled()) {
                  if (!(Boolean)this.rotate.getValue() || !AutoBreak.INSTANCE.didAction) {
                     if (AntiCheat.INSTANCE.isMultiTask()) {
                        this.reset();
                     } else {
                        this.prepare();
                        if (((String)this.timing.getValue()).equals("Pre")) {
                           this.interact();
                        }

                     }
                  }
               }
            }
         }
      }
   }

   @SubscribeEvent(4)
   public void onPlayerUpdate(TickEvent.MovementTickEvent.Post event) {
      if (!NullUtils.nullCheck()) {
         if (!Phase.INSTANCE.isEnabled()) {
            if (!(Boolean)this.rotate.getValue() || !PriorityManager.INSTANCE.isUsageLocked()) {
               if (!Blink.INSTANCE.isEnabled()) {
                  if (!(Boolean)this.rotate.getValue() || !AutoBreak.INSTANCE.didAction) {
                     if (!AntiCheat.INSTANCE.isMultiTask()) {
                        if (((String)this.timing.getValue()).equals("Post")) {
                           this.interact();
                        }

                     }
                  }
               }
            }
         }
      }
   }

   public void onEnable() {
      super.onEnable();
      if (!NullUtils.nullCheck()) {
         this.renderPos = null;
         this.target = null;
         this.swapTimer.setDelay(((Number)this.swapWaitDelay.getValue()).longValue());
         this.calcPos = null;
         this.calcCrystal = null;
         this.breaking = false;
         this.swapStopCrystal = null;
         this.swapStop = false;
         this.breakTargetRot = null;
         this.placeTargetRot = null;
      }
   }

   public void onDisable() {
      super.onDisable();
      if (!NullUtils.nullCheck()) {
         this.renderPos = null;
         this.target = null;
         this.swapTimer.setDelay(((Number)this.swapWaitDelay.getValue()).longValue());
         this.calcPos = null;
         this.calcCrystal = null;
         this.breaking = false;
         this.swapStopCrystal = null;
         this.swapStop = false;
         this.breakTargetRot = null;
         this.placeTargetRot = null;
      }
   }

   public void interact() {
      boolean bc = this.breakCrystal();
      int crystalSlot = InventoryUtils.getHotbarItemSlot(class_1802.field_8301);
      if ((crystalSlot != -1 || this.offhand) && (!(Boolean)this.limit.getValue() || !bc || this.calcCrystal == null)) {
         this.placeCrystal();
      }

      if (this.placedOnspawn) {
         this.placedOnspawn = false;
      }

      if (this.hasRotated) {
         RotationUtils.silentSync();
         this.hasRotated = false;
      }

   }

   public String getHudInfo() {
      if (this.target == null && this.lastTargetName == null) {
         String var1 = String.valueOf(class_124.field_1080);
         return "0.0ms" + var1 + ", " + String.valueOf(class_124.field_1068) + "1" + String.valueOf(class_124.field_1080) + ", " + String.valueOf(class_124.field_1068) + (new DecimalFormat("0.0")).format(this.renderDMG) + String.valueOf(class_124.field_1080) + ", " + String.valueOf(class_124.field_1068) + this.CRYSTALS_PER_SECOND;
      } else {
         if (this.target != null) {
            this.lastTargetName = this.target.method_5477().getString();
         }

         String var10000 = (new DecimalFormat("0.##")).format((double)this.lastTime);
         return var10000 + "ms" + String.valueOf(class_124.field_1080) + ", " + String.valueOf(class_124.field_1068) + (this.calcPos != null ? this.breakTimer.getResetTime() : 1L) + String.valueOf(class_124.field_1080) + ", " + String.valueOf(class_124.field_1068) + (new DecimalFormat("0.##")).format(this.renderDMG) + String.valueOf(class_124.field_1080) + ", " + String.valueOf(class_124.field_1068) + this.CRYSTALS_PER_SECOND;
      }
   }

   public String getDescription() {
      return "Places and breaks endcrystals to kill players";
   }
}
