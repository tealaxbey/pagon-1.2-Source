package org.itsmahir.pagon.impl.features.modules.combat;

import java.awt.Color;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_2885;
import net.minecraft.class_3965;
import net.minecraft.class_4969;
import net.minecraft.class_7204;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.management.PriorityManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.world.RenderType;
import org.itsmahir.pagon.api.utils.targeting.TargetUtils;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.utils.world.CrystalUtil;
import org.itsmahir.pagon.api.utils.world.ca.PlaceResult;
import org.itsmahir.pagon.api.utils.world.ca.Result;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;
import org.itsmahir.pagon.impl.features.modules.exploit.Blink;
import org.itsmahir.pagon.impl.features.modules.exploit.Phase;
import org.itsmahir.pagon.impl.features.modules.misc.autobreak.AutoBreak;

public class AutoAnchor extends Module {
   public static AutoAnchor INSTANCE;
   private final ExecutorService executor = Executors.newFixedThreadPool(1);
   Value<Number> placeRange = (new ValueBuilder<Number>()).withDescriptor("Range").withValue(6).withRange((double)1.0F, (double)6.0F).withPlaces(1).register(this);
   Value<Number> placeWallsRange = (new ValueBuilder<Number>()).withDescriptor("WallRange").withValue((double)3.0F).withRange((double)1.0F, (double)6.0F).withPlaces(1).register(this);
   Value<String> autoSwitch = (new ValueBuilder<String>()).withDescriptor("Switch").withValue("None").withModes("None", "Normal", "Silent", "AltSwap").withAction((s) -> this.handleSwitchChange((String)s.getValue())).register(this);
   Value<Number> placeDelay = (new ValueBuilder<Number>()).withDescriptor("Place Delay").withValue(1).withRange(0, 1000).register(this);
   Value<Number> explodeDelay = (new ValueBuilder<Number>()).withDescriptor("Break Delay").withValue(1).withRange(0, 1000).register(this);
   Value<String> timing = (new ValueBuilder<String>()).withDescriptor("Timing").withValue("Pre").withModes("Pre", "Post").register(this);
   Value<String> targetSorting = (new ValueBuilder<String>()).withDescriptor("Priority").withValue("Damage").withModes("Damage", "Distance").register(this);
   Value<Number> targetRange = (new ValueBuilder<Number>()).withDescriptor("Target Range").withValue((double)7.0F).withRange((double)3.0F, (double)20.0F).register(this);
   public Value<Number> minDmg = (new ValueBuilder<Number>()).withDescriptor("Min Damage").withValue(2).withRange(0, 36).register(this);
   Value<Number> maxSelfDmg = (new ValueBuilder<Number>()).withDescriptor("Max Self Damage").withValue(4).withRange(0, 36).register(this);
   Value<Boolean> noSuicide = (new ValueBuilder<Boolean>()).withDescriptor("Anti Suicide").withValue(false).register(this);
   Value<Boolean> terrain = (new ValueBuilder<Boolean>()).withDescriptor("Terrain").withValue(false).register(this);
   public Value<Boolean> armorAssume = (new ValueBuilder<Boolean>()).withDescriptor("AssumeBestArmor").withValue(false).register(this);
   Value<Boolean> noGapSwitch = (new ValueBuilder<Boolean>()).withDescriptor("Pause on Eat").withValue(false).register(this);
   Value<Boolean> rotate = (new ValueBuilder<Boolean>()).withDescriptor("Rotate").withValue(false).register(this);
   Value<String> rotationsType;
   Value<Number> swapWaitDelay;
   Value<String> swapWait;
   Value<Boolean> ignoreMine;
   Value<Boolean> render;
   Value<Boolean> fill;
   public Value<PagonColor> fillColorS;
   Value<Boolean> outline;
   public Value<PagonColor> lineColorS;
   public class_2338 renderPos;
   public class_2338 calcPos;
   float[] placeTargetRot;
   float[] explodeTargetRot;
   public class_1657 target;
   Timer placeTimer;
   Timer explodeTimer;
   public class_2338 explodePos;
   public double renderDMG;

   public AutoAnchor() {
      super("AutoAnchor", Feature.Category.Combat);
      this.rotationsType = (new ValueBuilder<String>()).withDescriptor("Type", "rotationsType").withValue("Vanilla").withModes("Vanilla", "NCP", "Grim").withParent(this.rotate).withParentEnabled(true).register(this);
      this.swapWaitDelay = (new ValueBuilder<Number>()).withDescriptor("Swap Wait Delay").withValue(300).withRange(50, 500).register(this);
      this.swapWait = (new ValueBuilder<String>()).withDescriptor("Swap Delay").withValue("None").withModes("None", "Semi", "Full").withAction((s) -> this.swapWaitDelay.setActive(!s.equals("None"))).register(this);
      this.ignoreMine = (new ValueBuilder<Boolean>()).withDescriptor("IgnoreMine").withValue(false).register(this);
      this.render = (new ValueBuilder<Boolean>()).withDescriptor("Render").withValue(false).register(this);
      this.fill = (new ValueBuilder<Boolean>()).withDescriptor("Fill").withValue(true).withParent(this.render).withParentEnabled(true).register(this);
      this.fillColorS = (new ValueBuilder<PagonColor>()).withDescriptor("Fill Color").withValue(new PagonColor(0, 0, 0, 100)).withParent(this.fill).withParentEnabled(true).register(this);
      this.outline = (new ValueBuilder<Boolean>()).withDescriptor("Outline").withValue(true).withParent(this.render).withParentEnabled(true).register(this);
      this.lineColorS = (new ValueBuilder<PagonColor>()).withDescriptor("Outline Color").withValue(new PagonColor(255, 255, 255, 255)).withParent(this.outline).withParentEnabled(true).register(this);
      this.placeTargetRot = null;
      this.explodeTargetRot = null;
      this.placeTimer = new Timer();
      this.explodeTimer = new Timer();
      INSTANCE = this;
      this.noGapSwitch.setActive(((String)this.autoSwitch.getValue()).equals("Normal"));
      this.swapWaitDelay.setActive(!((String)this.swapWait.getValue()).equals("None"));
   }

   private void handleSwitchChange(String value) {
      this.noGapSwitch.setActive(value.equals("Normal"));
   }

   private boolean isSilentMode() {
      String val = this.autoSwitch.getValue();
      return val.equals("Silent") || val.equals("AltSwap");
   }

   private boolean isAltSwap() {
      return ((String)this.autoSwitch.getValue()).equals("AltSwap");
   }

   @SubscribeEvent(4)
   public void onPlayerUpdate(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (!Phase.INSTANCE.isEnabled()) {
            if (!PriorityManager.INSTANCE.isUsageLocked()) {
               if (!Blink.INSTANCE.isEnabled()) {
                  if (!AutoBreak.INSTANCE.didAction) {
                     if (mc.field_1687.method_27983() != class_1937.field_25180) {
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
            if (!PriorityManager.INSTANCE.isUsageLocked()) {
               if (!Blink.INSTANCE.isEnabled()) {
                  if (!AutoBreak.INSTANCE.didAction) {
                     if (mc.field_1687.method_27983() != class_1937.field_25180) {
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

   public void interact() {
      this.placeAnchor();
      this.explodeAnchor();
      if (this.explodePos == null && this.calcPos == null) {
         this.renderPos = null;
      } else if (this.explodePos != null && this.calcPos == null) {
         this.renderPos = this.explodePos;
      }

   }

   public boolean canRender() {
      if (this.calcPos == null) {
         return false;
      } else {
         int anchorSlot = InventoryUtils.getHotbarItemSlot(class_1802.field_23141);
         return anchorSlot != -1;
      }
   }

   public PlaceResult getBestPlaceResult(class_1657 targetPlayer) {
      class_2338 bestPos = null;
      List<class_2338> sphere = BlockUtils.sphere(((Number)this.placeRange.getValue()).doubleValue() + (double)1.0F, mc.field_1724.method_24515(), true, false);
      double bestDMG = (double)0.5F;

      for(class_2338 pos : sphere) {
         if (BlockUtils.canPlaceBlock(pos) || mc.field_1687.method_8320(pos).method_26204() instanceof class_4969) {
            class_2350 side = BlockUtils.getPlaceableSide(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue());
            if (side != null) {
               class_243 vec = pos.method_46558().method_1031((double)side.method_10163().method_10263() * (double)0.5F, (double)side.method_10163().method_10264() * (double)0.5F, (double)side.method_10163().method_10260() * (double)0.5F);
               double distanceSq = vec.method_1028(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321());
               if (!(distanceSq > MathUtil.square(((Number)this.placeRange.getValue()).doubleValue()))) {
                  boolean trace = BlockUtils.placeTrace(pos);
                  if (!trace || !(distanceSq > MathUtil.square(((Number)this.placeWallsRange.getValue()).doubleValue()))) {
                     float targetDamage = CrystalUtil.calculateDamage(targetPlayer, pos.method_46558(), (Boolean)this.terrain.getValue(), false, pos);
                     if (bestDMG < (double)targetDamage && (double)targetDamage > ((Number)this.minDmg.getValue()).doubleValue()) {
                        float selfDamage = CrystalUtil.calculateDamage(mc.field_1724, pos.method_46558(), (Boolean)this.terrain.getValue(), false, pos);
                        if (!((double)selfDamage > ((Number)this.maxSelfDmg.getValue()).doubleValue()) && (!(selfDamage + 2.0F > mc.field_1724.method_6032() + mc.field_1724.method_6067()) || !(Boolean)this.noSuicide.getValue())) {
                           bestDMG = (double)targetDamage;
                           bestPos = pos;
                        }
                     }
                  }
               }
            }
         }
      }

      if (bestDMG == (double)0.5F) {
         bestDMG = (double)0.0F;
      }

      return new PlaceResult(targetPlayer, bestDMG, bestPos, this.getPlaceRot(bestPos));
   }

   public void placeAnchor() {
      if (this.calcPos != null) {
         if ((Boolean)this.rotate.getValue() && this.placeTargetRot == null) {
            return;
         }

         if (this.placeTimer.isPassed()) {
            boolean doSilent = false;
            int anchorSlot = InventoryUtils.getHotbarItemSlot(class_1802.field_23141);
            if (anchorSlot == -1) {
               this.renderPos = null;
               return;
            }

            if (BlockUtils.getBlockState(this.calcPos).method_26204() instanceof class_4969) {
               return;
            }

            if (((String)this.autoSwitch.getValue()).equals("Normal")) {
               if ((Boolean)this.noGapSwitch.getValue()) {
                  if (!PlayerUtils.isEatingGap()) {
                     InventoryUtils.switchToSlot(anchorSlot);
                  }
               } else {
                  InventoryUtils.switchToSlot(anchorSlot);
               }
            }

            if (this.isSilentMode() && !mc.field_1724.method_31548().method_7391().method_7909().equals(class_1802.field_23141)) {
               doSilent = true;
            }

            if (!((String)this.autoSwitch.getValue()).equals("Silent") && !((String)this.autoSwitch.getValue()).equals("AltSwap") && !mc.field_1724.method_31548().method_7391().method_7909().equals(class_1802.field_23141)) {
               this.renderPos = null;
               return;
            }

            int oldSlot = mc.field_1724.method_31548().field_7545;
            if (doSilent) {
               if (this.isAltSwap()) {
                  InventoryUtils.switchToBypass(InventoryUtils.hotbarToInventory(anchorSlot), false);
               } else {
                  InventoryUtils.switchToSlot(anchorSlot);
               }
            }

            class_2350 side = BlockUtils.getPlaceableSide(this.calcPos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue());
            if (side != null) {
               BlockUtils.placeBlock(this.calcPos, side, false);
               if (doSilent) {
                  if (this.isAltSwap()) {
                     InventoryUtils.switchToBypass(InventoryUtils.hotbarToInventory(anchorSlot), true);
                  } else {
                     InventoryUtils.switchToSlot(oldSlot);
                  }
               }

               if (AntiCheat.INSTANCE.isMultiTask()) {
                  return;
               }

               this.placeTimer.resetDelay();
            }
         }

         this.renderPos = this.calcPos;
      }

   }

   public void explodeAnchor() {
      if (this.explodePos != null) {
         if ((Boolean)this.rotate.getValue() && this.explodeTargetRot == null) {
            return;
         }

         if (this.explodeTimer.isPassed()) {
            boolean doSilent = false;
            class_2680 state = BlockUtils.getBlockState(this.explodePos);
            if (state == null || !(state.method_26204() instanceof class_4969)) {
               return;
            }

            int slot = -1;
            boolean charge = (Integer)state.method_11654(class_4969.field_23153) == 0;
            if (charge) {
               slot = InventoryUtils.getHotbarItemSlot(class_1802.field_8801);
            } else {
               int totemSlot = InventoryUtils.getHotbarItemSlot(class_1802.field_8288);
               if (totemSlot != -1) {
                  slot = totemSlot;
               } else {
                  slot = InventoryUtils.getNonBlockInHotbar();
               }
            }

            if (slot == -1) {
               return;
            }

            if (((String)this.autoSwitch.getValue()).equals("Normal")) {
               if ((Boolean)this.noGapSwitch.getValue()) {
                  if (!PlayerUtils.isEatingGap()) {
                     InventoryUtils.switchToSlot(slot);
                  }
               } else {
                  InventoryUtils.switchToSlot(slot);
               }
            }

            if (charge) {
               if (this.isSilentMode() && !mc.field_1724.method_31548().method_7391().method_7909().equals(class_1802.field_8801)) {
                  doSilent = true;
               }

               if (!((String)this.autoSwitch.getValue()).equals("Silent") && !((String)this.autoSwitch.getValue()).equals("AltSwap") && !mc.field_1724.method_31548().method_7391().method_7909().equals(class_1802.field_8801)) {
                  return;
               }
            }

            int oldSlot = mc.field_1724.method_31548().field_7545;
            if (doSilent) {
               if (this.isAltSwap()) {
                  InventoryUtils.switchToBypass(InventoryUtils.hotbarToInventory(slot), false);
               } else {
                  InventoryUtils.switchToSlot(slot);
               }
            }

            class_2350 side = BlockUtils.getPlaceableSideCrystal(this.explodePos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue());
            if (side != null) {
               class_243 vec = this.explodePos.method_46558().method_1031((double)side.method_10163().method_10263() * (double)0.5F, (double)side.method_10163().method_10264() * (double)0.5F, (double)side.method_10163().method_10260() * (double)0.5F);
               class_3965 result = new class_3965(vec, side, this.explodePos, false);
               PacketManager.INSTANCE.sendPacket((class_7204)((id) -> new class_2885(class_1268.field_5808, result, id)));
               mc.field_1724.method_6104(class_1268.field_5808);
               if (doSilent) {
                  if (this.isAltSwap()) {
                     InventoryUtils.switchToBypass(InventoryUtils.hotbarToInventory(slot), true);
                  } else {
                     InventoryUtils.switchToSlot(oldSlot);
                  }
               }

               if (AntiCheat.INSTANCE.isMultiTask()) {
                  return;
               }

               this.placeTimer.resetDelay();
            }

            this.renderPos = this.explodePos;
         }
      }

   }

   public PlaceResult getBestExplodeResult(class_1657 targetPlayer) {
      class_2338 bestPos = null;
      List<class_2338> sphere = BlockUtils.sphere(((Number)this.placeRange.getValue()).doubleValue() + (double)1.0F, mc.field_1724.method_24515(), true, false);
      double bestDMG = (double)0.5F;

      for(class_2338 pos : sphere) {
         class_2680 state = BlockUtils.getBlockState(pos);
         if (state != null && state.method_26204() instanceof class_4969) {
            class_2350 side = BlockUtils.getPlaceableSideCrystal(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue());
            if (side != null) {
               class_243 vec = pos.method_46558().method_1031((double)side.method_10163().method_10263() * (double)0.5F, (double)side.method_10163().method_10264() * (double)0.5F, (double)side.method_10163().method_10260() * (double)0.5F);
               double distanceSq = vec.method_1028(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321());
               if (!(distanceSq > MathUtil.square(((Number)this.placeRange.getValue()).doubleValue()))) {
                  boolean trace = BlockUtils.placeTrace(pos);
                  if (!trace || !(distanceSq > MathUtil.square(((Number)this.placeWallsRange.getValue()).doubleValue()))) {
                     float targetDamage = CrystalUtil.calculateDamage(targetPlayer, pos.method_46558(), (Boolean)this.terrain.getValue(), false, pos);
                     if (bestDMG < (double)targetDamage && (double)targetDamage > ((Number)this.minDmg.getValue()).doubleValue()) {
                        float selfDamage = CrystalUtil.calculateDamage(mc.field_1724, pos.method_46558(), (Boolean)this.terrain.getValue(), false, pos);
                        if (!((double)selfDamage > ((Number)this.maxSelfDmg.getValue()).doubleValue()) && (!(selfDamage + 2.0F > mc.field_1724.method_6032() + mc.field_1724.method_6067()) || !(Boolean)this.noSuicide.getValue())) {
                           bestDMG = (double)targetDamage;
                           bestPos = pos;
                        }
                     }
                  }
               }
            }
         }
      }

      if (bestDMG == (double)0.5F) {
         bestDMG = (double)0.0F;
      }

      return new PlaceResult(targetPlayer, bestDMG, bestPos, this.getPlaceRot(bestPos));
   }

   public void prepare() {
      this.placeTimer.setDelay(((Number)this.placeDelay.getValue()).longValue());
      this.explodeTimer.setDelay(((Number)this.explodeDelay.getValue()).longValue());
      this.placeTargetRot = null;
      this.explodeTargetRot = null;
      Future<Result> resultFuture = this.executor.submit(new ResultTask());
      Result result = null;

      try {
         result = (Result)resultFuture.get();
      } catch (Exception e) {
         e.printStackTrace();
         String var10000 = String.valueOf(class_124.field_1061);
         ChatUtils.sendMessage(var10000 + e.getCause().getMessage() + ": please send your log to me!!");
      }

      this.calcPos = null;
      this.explodePos = null;
      this.load(result);
      if ((Boolean)this.rotate.getValue()) {
         if (this.target == null || !PlayerUtils.isBoostedByFirework() || !(Boolean)AntiCheat.INSTANCE.strafeFix.getValue() || this.target.method_6128()) {
            if (this.placeTargetRot != null && this.explodeTargetRot == null) {
               RotationUtils.setRotation(this.placeTargetRot);
            } else if (this.placeTargetRot == null && this.explodeTargetRot != null) {
               RotationUtils.setRotation(this.explodeTargetRot);
            } else if (this.placeTargetRot != null) {
               RotationUtils.setRotation(this.placeTargetRot);
            }

         }
      }
   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldEvent event) {
      if (!NullUtils.nullCheck()) {
         if ((Boolean)this.render.getValue()) {
            if (this.target != null && this.renderPos != null) {
               if (this.renderPos != null) {
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
   }

   public Result getResult(class_1657 player) {
      return new Result(this.getBestExplodeResult(player), this.getBestPlaceResult(player));
   }

   public void load(Result result) {
      this.target = result.target;
      boolean offhand = mc.field_1724.method_6079().method_7909() == class_1802.field_8301;
      if (InventoryUtils.getHotbarItemSlot(class_1802.field_8301) == -1 && !offhand) {
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

      if (result.explodePos != null) {
         this.explodePos = result.explodePos;
         this.explodeTargetRot = result.breakRots;
      }

      if (result.calcPos == null && result.explodePos == null) {
         this.renderPos = null;
      }

   }

   public void onDisable() {
      super.onDisable();
      if (!NullUtils.nullCheck()) {
         this.renderPos = null;
         this.target = null;
         this.calcPos = null;
         this.explodePos = null;
         this.explodeTargetRot = null;
         this.placeTargetRot = null;
      }
   }

   public float[] getPlaceRot(class_2338 pos) {
      if (pos == null) {
         return null;
      } else if (!(Boolean)this.rotate.getValue()) {
         return null;
      } else {
         class_2350 side = BlockUtils.getPlaceableSide(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue());
         return RotationUtils.getBlockRotations(pos, side);
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

   public void reset() {
      this.explodePos = null;
      this.calcPos = null;
      this.explodeTargetRot = null;
      this.placeTargetRot = null;
   }

   public String getDescription() {
      return "Automatically places and explodes respawn anchors";
   }

   private class ResultTask implements Callable<Result> {
      public ResultTask() {
      }

      public Result call() throws Exception {
         return AutoAnchor.this.getTargetResult();
      }
   }
}
