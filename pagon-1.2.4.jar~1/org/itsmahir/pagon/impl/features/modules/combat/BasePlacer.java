package org.itsmahir.pagon.impl.features.modules.combat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PriorityManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.animation.Easing;
import org.itsmahir.pagon.api.utils.render.animation.type.ConsistentDynamicAnimation;
import org.itsmahir.pagon.api.utils.render.world.RenderType;
import org.itsmahir.pagon.api.utils.targeting.TargetUtils;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.utils.world.CrystalUtil;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;
import org.itsmahir.pagon.impl.features.modules.misc.autobreak.AutoBreak;

public class BasePlacer extends Module {
   public static BasePlacer INSTANCE;
   public Value<Number> placeRange = (new ValueBuilder<Number>()).withDescriptor("Place Range").withValue((double)6.0F).withRange((double)0.0F, (double)10.0F).register(this);
   public Value<Number> enemyRange = (new ValueBuilder<Number>()).withDescriptor("Enemy Range").withValue((double)15.0F).withRange(0.1, (double)30.0F).register(this);
   public Value<Number> shiftDelay = (new ValueBuilder<Number>()).withDescriptor("Shift Delay (ms)").withValue(0).withRange(0, 1000).register(this);
   public Value<Number> minDmg = (new ValueBuilder<Number>()).withDescriptor("Min Damage").withValue((double)10.0F).withRange((double)1.0F, (double)36.0F).register(this);
   public Value<Boolean> assumeArmor = (new ValueBuilder<Boolean>()).withDescriptor("Assume Max Armor").withValue(false).register(this);
   public Value<Boolean> rotate = (new ValueBuilder<Boolean>()).withDescriptor("Rotate").withValue(false).register(this);
   public Value<Boolean> render = (new ValueBuilder<Boolean>()).withDescriptor("Render").withValue(true).register(this);
   public Value<PagonColor> fill;
   public Value<PagonColor> line;
   public Value<Number> fadeTime;
   private class_2338 crystalBase;
   private final Map<class_2338, Long> packets;
   private final Map<class_2338, ConsistentDynamicAnimation> fadeList;

   public BasePlacer() {
      super("BasePlacer", Feature.Category.Combat);
      this.fill = (new ValueBuilder<PagonColor>()).withDescriptor("Fill").withValue(new PagonColor(255, 0, 0, 40)).withParentEnabled(true).withParent(this.render).register(this);
      this.line = (new ValueBuilder<PagonColor>()).withDescriptor("Line").withValue(new PagonColor(255, 0, 0, 100)).withParentEnabled(true).withParent(this.render).register(this);
      this.fadeTime = (new ValueBuilder<Number>()).withDescriptor("Fade Time").withValue(250).withRange(0, 1000).withParentEnabled(true).withParent(this.render).register(this);
      this.packets = new HashMap();
      this.fadeList = new HashMap();
      INSTANCE = this;
   }

   public void onDisable() {
      super.onDisable();
      this.crystalBase = null;
      this.packets.clear();
      this.fadeList.clear();
      if (PriorityManager.INSTANCE.isUsageLocked() && "BasePlacer".equals(PriorityManager.INSTANCE.usageLockCause)) {
         PriorityManager.INSTANCE.unlockUsageLock();
      }

   }

   @SubscribeEvent
   public void onPlayerTick(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (!PriorityManager.INSTANCE.isUsageLocked() || Objects.equals(PriorityManager.INSTANCE.usageLockCause, "BasePlacer")) {
            if (!AutoBreak.INSTANCE.didAction) {
               if (AutoCrystal.INSTANCE.isEnabled()) {
                  class_1657 target = (class_1657)TargetUtils.getTarget((double)((Number)this.enemyRange.getValue()).floatValue());
                  if (target != null) {
                     this.crystalBase = this.getCrystalBase(target);
                     if (this.crystalBase == null) {
                        if (PriorityManager.INSTANCE.isUsageLocked() && "BasePlacer".equals(PriorityManager.INSTANCE.usageLockCause)) {
                           PriorityManager.INSTANCE.unlockUsageLock();
                        }

                     } else {
                        int slot = InventoryUtils.getHotbarItemSlot(class_1802.field_8281);
                        if (slot != -1) {
                           if (mc.field_1687.method_8320(this.crystalBase).method_45474()) {
                              PriorityManager.INSTANCE.lockUsageLock("BasePlacer");
                              this.doPlaceBlock(this.crystalBase, slot);
                           } else if (PriorityManager.INSTANCE.isUsageLocked() && "BasePlacer".equals(PriorityManager.INSTANCE.usageLockCause)) {
                              PriorityManager.INSTANCE.unlockUsageLock();
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
   public void onPlayerTickPost(TickEvent.PlayerTickEvent.Post event) {
      if (!NullUtils.nullCheck()) {
         if (PriorityManager.INSTANCE.isUsageLocked() && "BasePlacer".equals(PriorityManager.INSTANCE.usageLockCause)) {
            PriorityManager.INSTANCE.unlockUsageLock();
         }

      }
   }

   private void doPlaceBlock(class_2338 pos, int slot) {
      int oldSlot = mc.field_1724.method_31548().field_7545;
      boolean switched = false;
      if (slot != mc.field_1724.method_31548().field_7545) {
         InventoryUtils.switchToSlot(slot);
         switched = true;
      }

      if ((Boolean)this.rotate.getValue() && (Boolean)AntiCheat.INSTANCE.grimRotations.getValue()) {
         RotationUtils.doSilentRotate(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue());
      }

      BlockUtils.placeBlock(pos, BlockUtils.getPlaceableSide(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue(), this.packets.keySet()), !mc.field_1724.method_6047().method_7909().equals(class_1802.field_8466));
      this.packets.put(pos, System.currentTimeMillis());
      if (switched) {
         InventoryUtils.switchToSlot(oldSlot);
      }

      if ((Boolean)this.rotate.getValue()) {
         RotationUtils.silentSync();
      }

   }

   private class_2338 getCrystalBase(class_1657 player) {
      List<class_2338> targetBlocks = this.getSphere(((Number)this.placeRange.getValue()).doubleValue(), mc.field_1724.method_33571());
      double bestDamage = (double)0.0F;
      class_2338 bestBase = null;

      for(class_2338 pos : targetBlocks) {
         class_2338 basePos = pos.method_10074();
         if ((long)basePos.method_10264() < Math.round(player.method_23318())) {
            Long placed = (Long)this.packets.get(basePos);
            if ((((Number)this.shiftDelay.getValue()).longValue() <= 0L || placed == null || System.currentTimeMillis() - placed >= ((Number)this.shiftDelay.getValue()).longValue()) && CrystalUtil.canPlaceCrystalAir(basePos)) {
               double dist = mc.field_1724.method_5707(basePos.method_46558());
               if (!(dist > MathUtil.square(((Number)this.placeRange.getValue()).doubleValue())) && !BlockUtils.isBlockedOff(basePos) && !BlockUtils.isBlockedOff(pos)) {
                  if (BlockUtils.isReplaceable(basePos)) {
                     if (!BlockUtils.canPlaceBlock(basePos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue(), this.packets.keySet())) {
                        continue;
                     }
                  } else if (!CrystalUtil.canPlaceCrystal(basePos, ((String)AutoCrystal.INSTANCE.placements.getValue()).equals("Protocol"))) {
                     continue;
                  }

                  double dmg = (double)CrystalUtil.calculateDamage(player, pos.method_46558(), (Boolean)AutoCrystal.INSTANCE.terrain.getValue(), (Boolean)this.assumeArmor.getValue());
                  if (!(dmg < ((Number)this.minDmg.getValue()).doubleValue()) && dmg > bestDamage) {
                     bestBase = basePos;
                     bestDamage = dmg;
                  }
               }
            }
         }
      }

      return bestBase;
   }

   private List<class_2338> getSphere(double rad, class_243 origin) {
      List<class_2338> sphere = new ArrayList();
      int iRad = (int)rad;

      for(double x = (double)(-iRad); x <= (double)iRad; ++x) {
         for(double y = (double)(-iRad); y <= (double)iRad; ++y) {
            for(double z = (double)(-iRad); z <= (double)iRad; ++z) {
               sphere.add(new class_2338(new class_2382((int)(origin.method_10216() + x), (int)(origin.method_10214() + y), (int)(origin.method_10215() + z))));
            }
         }
      }

      return sphere;
   }

   @SubscribeEvent
   public void onRender(RenderWorldEvent event) {
      if (!NullUtils.nullCheck()) {
         if ((Boolean)this.render.getValue()) {
            if (this.crystalBase != null && mc.field_1687.method_8320(this.crystalBase).method_45474()) {
               if (!this.fadeList.containsKey(this.crystalBase)) {
                  ConsistentDynamicAnimation anim = new ConsistentDynamicAnimation(1.0F, Easing.LINEAR, ((Number)this.fadeTime.getValue()).longValue());
                  anim.goal(0.0F);
                  this.fadeList.put(this.crystalBase, anim);
               } else {
                  ConsistentDynamicAnimation anim = (ConsistentDynamicAnimation)this.fadeList.get(this.crystalBase);
                  anim.goal(1.0F);
                  anim.setLastValue(1.0F);
               }
            }

            Iterator<Map.Entry<class_2338, ConsistentDynamicAnimation>> iterator = this.fadeList.entrySet().iterator();

            while(iterator.hasNext()) {
               Map.Entry<class_2338, ConsistentDynamicAnimation> entry = (Map.Entry)iterator.next();
               class_2338 pos = (class_2338)entry.getKey();
               ConsistentDynamicAnimation animation = (ConsistentDynamicAnimation)entry.getValue();
               if (!pos.equals(this.crystalBase) || !mc.field_1687.method_8320(pos).method_45474()) {
                  animation.goal(0.0F);
               }

               float factor = animation.getAnimationValue();
               if (factor <= 0.05F) {
                  iterator.remove();
               } else {
                  PagonColor fc = this.fill.getValue();
                  PagonColor lc = this.line.getValue();
                  PagonColor boxColor = new PagonColor(fc.getColor().getRed(), fc.getColor().getGreen(), fc.getColor().getBlue(), (int)((float)fc.getColor().getAlpha() * factor));
                  PagonColor lineColor = new PagonColor(lc.getColor().getRed(), lc.getColor().getGreen(), lc.getColor().getBlue(), (int)((float)lc.getColor().getAlpha() * factor));
                  class_238 bb = new class_238(pos);
                  RenderUtil.renderBox(RenderType.FILL, bb, boxColor.getColor(), boxColor.getColor());
                  RenderUtil.renderBox(RenderType.LINES, bb, lineColor.getColor(), lineColor.getColor());
               }
            }
         }

      }
   }

   public String getDescription() {
      return "Places obsidian for crystal placements";
   }
}
