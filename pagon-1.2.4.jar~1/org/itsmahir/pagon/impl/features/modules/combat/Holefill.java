package org.itsmahir.pagon.impl.features.modules.combat;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PriorityManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.world.RenderType;
import org.itsmahir.pagon.api.utils.targeting.TargetUtils;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.utils.world.HoleUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;
import org.itsmahir.pagon.impl.features.modules.exploit.Blink;
import org.itsmahir.pagon.impl.features.modules.misc.autobreak.AutoBreak;

public class Holefill extends Module {
   Value<Number> range = (new ValueBuilder<Number>()).withDescriptor("Range").withValue((double)4.0F).withRange((double)1.0F, 6).withPlaces(1).register(this);
   Value<Number> wallsRange = (new ValueBuilder<Number>()).withDescriptor("WallsRange").withValue(3).withRange((double)1.0F, 6).withPlaces(1).register(this);
   Value<Number> delay = (new ValueBuilder<Number>()).withDescriptor("Delay").withValue(1).withRange(0, 1000).register(this);
   Value<Number> blocksPerTick = (new ValueBuilder<Number>()).withDescriptor("Blocks").withValue(1).withRange(1, 10).register(this);
   Value<Boolean> disableAfter = (new ValueBuilder<Boolean>()).withDescriptor("Disable").withValue(true).register(this);
   Value<String> rotateMode = (new ValueBuilder<String>()).withDescriptor("Rotate").withValue("None").withModes("None", "Normal").register(this);
   Value<Boolean> doubles = (new ValueBuilder<Boolean>()).withDescriptor("Doubles").withValue(true).register(this);
   Value<Boolean> smart = (new ValueBuilder<Boolean>()).withDescriptor("Smart", "smart").withValue(false).withAction((s) -> this.handleSmartPage((Boolean)s.getValue())).register(this);
   Value<Boolean> webs = (new ValueBuilder<Boolean>()).withDescriptor("Webs").withValue(false).register(this);
   Value<Number> smartTargetRange = (new ValueBuilder<Number>()).withDescriptor("Target Range", "targetRange").withValue((double)5.0F).withRange((double)1.0F, (double)10.0F).register(this);
   Value<Number> smartBlockRange = (new ValueBuilder<Number>()).withDescriptor("Activate Range", "smartBlockRange").withValue((double)1.0F).withRange(0.3, (double)4.0F).register(this);
   Value<Boolean> renderHoles = (new ValueBuilder<Boolean>()).withDescriptor("Render").withValue(true).withAction((s) -> this.handleRender((Boolean)s.getValue())).register(this);
   Value<PagonColor> fill = (new ValueBuilder<PagonColor>()).withDescriptor("Fill").withValue(new PagonColor(255, 0, 0, 25)).register(this);
   Value<PagonColor> line = (new ValueBuilder<PagonColor>()).withDescriptor("Line").withValue(new PagonColor(255, 0, 0, 255)).register(this);
   Value<Number> fadeTime = (new ValueBuilder<Number>()).withDescriptor("Fade Time").withValue(200).withRange(0, 1000).register(this);
   Timer timeSystem = new Timer();
   List<HoleUtils.Hole> holes = new ArrayList();
   Map<HoleUtils.Hole, Long> renderPositions = new HashMap();
   class_1297 target;
   List<HoleUtils.Hole> toPlace = new ArrayList();

   public Holefill() {
      super("HoleFill", Feature.Category.Combat);
   }

   public void onEnable() {
      super.onEnable();
      if (!NullUtils.nullCheck()) {
         ;
      }
   }

   void handleSmartPage(boolean show) {
      this.smartBlockRange.setActive(show);
      this.smartTargetRange.setActive(show);
   }

   void handleRender(boolean show) {
      this.fill.setActive(show);
      this.line.setActive(show);
      this.fadeTime.setActive(show);
   }

   @SubscribeEvent
   public void onUpdatePre(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (!PriorityManager.INSTANCE.usageLock || PriorityManager.INSTANCE.usageLockCause == "HoleFill") {
            if (!AutoBreak.INSTANCE.didAction) {
               if (!Blink.INSTANCE.isEnabled()) {
                  this.target = TargetUtils.getTargetHolefill(((Number)this.smartTargetRange.getValue()).doubleValue());
                  this.timeSystem.setDelay(((Number)this.delay.getValue()).longValue());
                  int blocksPlaced = 0;
                  HoleUtils.Hole targetHole = null;
                  if (this.timeSystem.isPassed()) {
                     this.getHoles();
                     if (this.holes == null || this.holes.size() == 0) {
                        if ((Boolean)this.disableAfter.getValue()) {
                           this.setEnabled(false);
                        }

                        if (Objects.equals(PriorityManager.INSTANCE.usageLockCause, "HoleFill")) {
                           PriorityManager.INSTANCE.unlockUsageLock();
                        }

                        return;
                     }

                     for(HoleUtils.Hole hole : this.holes) {
                        if (!this.canPlaceBlock(hole.pos1)) {
                           return;
                        }

                        if (!(Boolean)AntiCheat.INSTANCE.grimRotations.getValue()) {
                           this.doRotate(hole.pos1);
                        }

                        int blockSlot = InventoryUtils.getHotbarItemSlot(this.getBlockType());
                        if (blockSlot == -1) {
                           return;
                        }

                        this.toPlace.add(hole);
                        this.timeSystem.resetDelay();
                        PriorityManager.INSTANCE.lockUsageLock("HoleFill");
                        ++blocksPlaced;
                        if (blocksPlaced >= ((Number)this.blocksPerTick.getValue()).intValue()) {
                           break;
                        }
                     }
                  }

               }
            }
         }
      }
   }

   @SubscribeEvent
   public void onPlayerUpdate(TickEvent.MovementTickEvent.Post event) {
      if (!NullUtils.nullCheck()) {
         if (!this.toPlace.isEmpty()) {
            int blockSlot = InventoryUtils.getHotbarItemSlot(this.getBlockType());
            int oldSlot = mc.field_1724.method_31548().field_7545;
            boolean switched = false;

            for(HoleUtils.Hole targetHole : this.toPlace) {
               InventoryUtils.switchToSlot(blockSlot);
               switched = true;
               class_2350 direction = BlockUtils.getPlaceableSide(targetHole.pos1, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue());
               if (((String)this.rotateMode.getValue()).equals("Normal") && (Boolean)AntiCheat.INSTANCE.grimRotations.getValue()) {
                  RotationUtils.doSilentRotate(targetHole.pos1, direction);
               }

               BlockUtils.placeBlock(targetHole.pos1, direction, true);
               this.renderPositions.put(targetHole, System.currentTimeMillis());
            }

            if (((String)this.rotateMode.getValue()).equals("Normal") && (Boolean)AntiCheat.INSTANCE.grimRotations.getValue() && !this.toPlace.isEmpty()) {
               RotationUtils.silentSync();
            }

            if (switched) {
               InventoryUtils.switchToSlot(oldSlot);
            }

            this.toPlace.clear();
         }
      }
   }

   boolean canPlaceBlock(class_2338 pos) {
      boolean allow = true;
      if (!mc.field_1687.method_8320(pos).method_45474()) {
         return false;
      } else if (BlockUtils.getPlaceableSide(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue()) == null) {
         return false;
      } else {
         for(class_1297 entity : mc.field_1687.method_18467(class_1297.class, new class_238(pos))) {
            if (entity instanceof class_1657 || entity instanceof class_1511) {
               return false;
            }
         }

         if (BlockUtils.placeTrace(pos) && BlockUtils.getDistanceSq(mc.field_1724, pos) > MathUtil.square(((Number)this.wallsRange.getValue()).doubleValue())) {
            return false;
         } else {
            double distance = mc.field_1724.method_33571().method_1025(new class_243((double)pos.method_10263() + (double)0.5F, (double)pos.method_10264() + (double)0.5F, (double)pos.method_10260() + (double)0.5F));
            if (distance > MathUtil.square(((Number)this.range.getValue()).doubleValue())) {
               return false;
            } else {
               return true;
            }
         }
      }
   }

   @SubscribeEvent
   public void onRender(RenderWorldEvent event) {
      if (!NullUtils.nullCheck()) {
         if ((Boolean)this.renderHoles.getValue()) {
            for(Map.Entry<HoleUtils.Hole, Long> entry : this.renderPositions.entrySet()) {
               int fillAlpha = ((PagonColor)this.fill.getValue()).getAlpha();
               int lineAlpha = ((PagonColor)this.line.getValue()).getAlpha();
               long time = System.currentTimeMillis() - (Long)entry.getValue();
               double normal = MathUtil.normalize((double)time, (double)0.0F, ((Number)this.fadeTime.getValue()).doubleValue());
               normal = class_3532.method_15350(normal, (double)0.0F, (double)1.0F);
               normal = -normal;
               ++normal;
               fillAlpha = (int)((double)fillAlpha * normal);
               lineAlpha = (int)((double)lineAlpha * normal);
               Color fillColor = ColorUtil.newAlpha(((PagonColor)this.fill.getValue()).getColor(), fillAlpha);
               Color lineColor = ColorUtil.newAlpha(((PagonColor)this.line.getValue()).getColor(), lineAlpha);
               HoleUtils.Hole hole = (HoleUtils.Hole)entry.getKey();
               class_238 bb = new class_238(hole.pos1);
               RenderUtil.renderBox(RenderType.FILL, bb, fillColor, fillColor);
               RenderUtil.renderBox(RenderType.LINES, bb, lineColor, lineColor);
               if (fillAlpha == 0 && lineAlpha == 0) {
                  this.renderPositions.remove(entry.getKey());
               }
            }

         }
      }
   }

   public void getHoles() {
      this.loadHoles();
   }

   public void loadHoles() {
      this.holes = (List)HoleUtils.getHoles(((Number)this.range.getValue()).doubleValue(), mc.field_1724.method_24515(), (Boolean)this.doubles.getValue()).stream().filter((hole) -> {
         boolean isAllowedHole = true;
         class_238 bb = hole.doubleHole ? new class_238((double)hole.pos1.method_10263(), (double)hole.pos1.method_10264(), (double)hole.pos1.method_10260(), (double)(hole.pos2.method_10263() + 1), (double)(hole.pos2.method_10264() + 1), (double)(hole.pos2.method_10260() + 1)) : new class_238(hole.pos1);

         for(class_1297 entity : mc.field_1687.method_18467(class_1297.class, bb)) {
            if (entity instanceof class_1657 || entity instanceof class_1511) {
               isAllowedHole = false;
               break;
            }
         }

         return isAllowedHole;
      }).filter((hole) -> {
         boolean isAllowedSmart = false;
         if ((Boolean)this.smart.getValue()) {
            if (this.target != null && this.target.method_5649((double)hole.pos1.method_10263() + (double)0.5F, (double)(hole.pos1.method_10264() + 1), (double)hole.pos1.method_10260() + (double)0.5F) < MathUtil.square(((Number)this.smartBlockRange.getValue()).doubleValue())) {
               isAllowedSmart = true;
            }
         } else {
            isAllowedSmart = true;
         }

         return isAllowedSmart;
      }).collect(Collectors.toList());
   }

   public String getHudInfo() {
      return (Boolean)this.smart.getValue() ? "Smart" : "Normal";
   }

   public void doRotate(class_2338 pos) {
      if (!((String)this.rotateMode.getValue()).equals("None")) {
         float[] rots = RotationUtils.getBlockRotations(pos, BlockUtils.getPlaceableSide(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue()));
         if (rots != null) {
            RotationUtils.setRotation(rots);
         }

      }
   }

   public void onDisable() {
      super.onDisable();
      if (!NullUtils.nullCheck()) {
         if (PriorityManager.INSTANCE.isUsageLocked() && Objects.equals(PriorityManager.INSTANCE.usageLockCause, "HoleFill")) {
            PriorityManager.INSTANCE.unlockUsageLock();
         }

      }
   }

   public class_1792 getBlockType() {
      return (Boolean)this.webs.getValue() ? class_1802.field_8786 : class_1802.field_8281;
   }

   public String getDescription() {
      return "Fills nearby safe holes to prevent other players from entering them";
   }
}
