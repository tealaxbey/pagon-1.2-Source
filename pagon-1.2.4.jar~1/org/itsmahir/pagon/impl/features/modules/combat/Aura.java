package org.itsmahir.pagon.impl.features.modules.combat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1657;
import net.minecraft.class_1743;
import net.minecraft.class_1799;
import net.minecraft.class_1829;
import net.minecraft.class_1835;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_9362;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PriorityManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.utils.render.Interpolator;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.world.RenderType;
import org.itsmahir.pagon.api.utils.targeting.EntityTargeter;
import org.itsmahir.pagon.api.utils.targeting.Sorting;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;

public class Aura extends Module {
   public static Aura INSTANCE;
   Value<String> delay = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Cooldown").withModes("Cooldown", "Delay").register(this);
   public Value<Number> attackSpeed;
   Value<Boolean> vanillaRange;
   public Value<Number> range;
   public Value<Number> wallsRange;
   Value<Boolean> autoSwitch;
   Value<Boolean> noGapSwitch;
   Value<Boolean> onlySword;
   Value<String> weaponMode;
   Value<Boolean> players;
   Value<Boolean> mobs;
   Value<Boolean> animals;
   Value<Boolean> vehicles;
   Value<String> sorting;
   Value<Boolean> rotate;
   Value<Boolean> yawStep;
   Value<Boolean> silent;
   Value<String> rotateType;
   Value<Boolean> esp;
   Value<PagonColor> fill;
   Value<PagonColor> line;
   Timer hitTimer;
   public class_1297 target;

   public Aura() {
      super("Aura", Feature.Category.Combat);
      this.attackSpeed = (new ValueBuilder<Number>()).withDescriptor("Attack Speed").withValue(20).withRange(0.1, 20).withPlaces(1).withPageParent(this.delay).withPage("Delay").register(this);
      this.vanillaRange = (new ValueBuilder<Boolean>()).withDescriptor("VanillaRange").withValue(false).register(this);
      this.range = (new ValueBuilder<Number>()).withDescriptor("Range").withValue(6).withRange(3, 6).withPlaces(1).withParent(this.vanillaRange).withParentEnabled(false).register(this);
      this.wallsRange = (new ValueBuilder<Number>()).withDescriptor("WallRange").withValue(3).withRange(3, 6).withPlaces(1).withParent(this.vanillaRange).withParentEnabled(false).register(this);
      this.autoSwitch = (new ValueBuilder<Boolean>()).withDescriptor("Switch").withValue(false).register(this);
      this.noGapSwitch = (new ValueBuilder<Boolean>()).withDescriptor("Pause on Eat").withValue(false).withParentEnabled(true).withParent(this.autoSwitch).register(this);
      this.onlySword = (new ValueBuilder<Boolean>()).withDescriptor("Only Weapon").withValue(true).register(this);
      this.weaponMode = (new ValueBuilder<String>()).withDescriptor("Weapon Mode").withValue("All").withModes("All", "Sword", "Axe", "Mace", "Trident").withParent(this.onlySword).withParentEnabled(true).register(this);
      this.players = (new ValueBuilder<Boolean>()).withDescriptor("Players").withValue(true).register(this);
      this.mobs = (new ValueBuilder<Boolean>()).withDescriptor("Mobs").withValue(false).register(this);
      this.animals = (new ValueBuilder<Boolean>()).withDescriptor("Animals").withValue(false).register(this);
      this.vehicles = (new ValueBuilder<Boolean>()).withDescriptor("Vehicles").withValue(false).register(this);
      this.sorting = (new ValueBuilder<String>()).withDescriptor("Priority").withValue("Distance").withModes("Distance", "FOV", "Health").register(this);
      this.rotate = (new ValueBuilder<Boolean>()).withDescriptor("Rotate").withValue(true).register(this);
      this.yawStep = (new ValueBuilder<Boolean>()).withDescriptor("Yaw Step").withValue(true).withParent(this.rotate).withParentEnabled(true).register(this);
      this.silent = (new ValueBuilder<Boolean>()).withDescriptor("Continuous").withValue(false).withParent(this.rotate).withParentEnabled(true).register(this);
      this.rotateType = (new ValueBuilder<String>()).withDescriptor("Aim Target").withValue("Auto").withModes("Auto", "Head", "Torso", "Feet").withParent(this.rotate).withParentEnabled(true).register(this);
      this.esp = (new ValueBuilder<Boolean>()).withDescriptor("Render").withValue(false).register(this);
      this.fill = (new ValueBuilder<PagonColor>()).withDescriptor("Fill").withValue(new PagonColor(255, 62, 62, 25)).withParent(this.esp).withParentEnabled(true).register(this);
      this.line = (new ValueBuilder<PagonColor>()).withDescriptor("Line").withValue(new PagonColor(255, 62, 62, 255)).withParent(this.esp).withParentEnabled(true).register(this);
      this.hitTimer = new Timer();
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onPlayerUpdate(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (!PriorityManager.INSTANCE.isUsageLocked()) {
            this.hitTimer.setDelayCPS(((Number)this.attackSpeed.getValue()).floatValue());
            ArrayList<class_1299> toTarget = new ArrayList();
            if ((Boolean)this.players.getValue()) {
               toTarget.add(class_1299.field_6097);
            }

            if ((Boolean)this.mobs.getValue()) {
               toTarget.add(class_1299.field_6051);
            }

            if ((Boolean)this.animals.getValue()) {
               toTarget.add(class_1299.field_6093);
            }

            if ((Boolean)this.vehicles.getValue()) {
               toTarget.add(class_1299.field_6096);
            }

            float currentRange = (Boolean)this.vanillaRange.getValue() ? 3.0F : ((Number)this.range.getValue()).floatValue();
            Sorting sortMode = ((String)this.sorting.getValue()).equals("Distance") ? Sorting.DISTANCE : (((String)this.sorting.getValue()).equals("FOV") ? Sorting.FOV : Sorting.HEALTH);
            class_1297 newTarget = (new EntityTargeter(mc.field_1724, sortMode, currentRange, toTarget)).findTarget(mc.field_1724.method_33571());
            if (newTarget == null) {
               this.target = null;
            } else if (!AutoCrystal.INSTANCE.isEnabled() || AutoCrystal.INSTANCE.calcCrystal == null && AutoCrystal.INSTANCE.calcPos == null) {
               if ((Boolean)this.onlySword.getValue()) {
                  class_1799 held = mc.field_1724.method_6047();
                  boolean var10000;
                  switch ((String)this.weaponMode.getValue()) {
                     case "Sword" -> var10000 = held.method_7909() instanceof class_1829;
                     case "Axe" -> var10000 = held.method_7909() instanceof class_1743;
                     case "Mace" -> var10000 = held.method_7909() instanceof class_9362;
                     case "Trident" -> var10000 = held.method_7909() instanceof class_1835;
                     default -> var10000 = held.method_7909() instanceof class_1829 || held.method_7909() instanceof class_1743 || held.method_7909() instanceof class_9362 || held.method_7909() instanceof class_1835;
                  }

                  boolean valid = var10000;
                  if (!valid) {
                     this.target = null;
                     return;
                  }
               } else if ((Boolean)this.autoSwitch.getValue() && (!(Boolean)this.noGapSwitch.getValue() || !PlayerUtils.isEatingGap())) {
                  equipBestWeapon();
               }

               this.target = newTarget;
               if (this.isInAttackRange(mc.field_1724.method_33571(), this.target)) {
                  if (AntiCheat.INSTANCE.isMultiTask()) {
                     this.target = null;
                  } else {
                     boolean canAttack = false;
                     if (((String)this.delay.getValue()).equals("Cooldown")) {
                        float progress = mc.field_1724.method_7261(0.0F);
                        canAttack = progress >= 1.0F;
                     } else if (this.hitTimer.isPassed()) {
                        canAttack = true;
                        this.hitTimer.resetDelay();
                     }

                     if ((Boolean)this.rotate.getValue() && ((Boolean)this.silent.getValue() || canAttack)) {
                        class_1297 var14 = this.target;
                        if (var14 instanceof class_1657) {
                           class_1657 entity = (class_1657)var14;
                           if (PlayerUtils.isBoostedByFirework() && (Boolean)AntiCheat.INSTANCE.strafeFix.getValue() && !entity.method_6128()) {
                              return;
                           }
                        }

                        float[] rotation = RotationUtils.getRotationsTo(mc.field_1724.method_33571(), this.getAttackRotateVec(this.target));
                        RotationUtils.setRotation(rotation);
                     }

                     if (canAttack) {
                        if (mc.field_1724.method_6047().method_7909() instanceof class_9362 && mc.field_1724.field_6017 <= 1.5F && ((String)this.weaponMode.getValue()).equals("Mace")) {
                           return;
                        }

                        PlayerUtils.attackTarget(this.target);
                     }

                  }
               }
            } else {
               this.target = null;
            }
         }
      }
   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldEvent event) {
      if (!NullUtils.nullCheck()) {
         if (!PriorityManager.INSTANCE.isUsageLocked()) {
            if ((Boolean)this.esp.getValue()) {
               if (this.target != null) {
                  class_238 bb = Interpolator.getInterpolatedEntityBox(this.target);
                  RenderUtil.renderBox(RenderType.FILL, bb, ((PagonColor)this.fill.getValue()).getColor(), ((PagonColor)this.fill.getValue()).getColor());
                  RenderUtil.renderBox(RenderType.LINES, bb, ((PagonColor)this.line.getValue()).getColor(), ((PagonColor)this.line.getValue()).getColor());
               }

            }
         }
      }
   }

   public static void equipBestWeapon() {
      int slot = InventoryUtils.getSwordSlot();
      if (slot != -1 && slot != mc.field_1724.method_31548().field_7545) {
         InventoryUtils.switchToSlot(slot);
      }
   }

   public boolean isInAttackRange(class_243 pos, class_1297 entity) {
      class_243 entityPos = this.getAttackRotateVec(entity);
      double dist = pos.method_1022(entityPos);
      return this.isInAttackRange(dist, pos, entityPos);
   }

   public boolean isInAttackRange(double dist, class_243 pos, class_243 entityPos) {
      float currentRange = (Boolean)this.vanillaRange.getValue() ? 3.0F : ((Number)this.range.getValue()).floatValue();
      float currentWallsRange = (Boolean)this.vanillaRange.getValue() ? 3.0F : ((Number)this.wallsRange.getValue()).floatValue();
      if (dist > (double)currentRange) {
         return false;
      } else {
         class_3965 result = mc.field_1687.method_17742(new class_3959(pos, entityPos, class_3960.field_17558, class_242.field_1348, mc.field_1724));
         return result == null || !(dist > (double)currentWallsRange);
      }
   }

   public class_243 getAttackRotateVec(class_1297 entity) {
      class_243 feetPos = entity.method_19538();
      class_243 var10000;
      switch ((String)this.rotateType.getValue()) {
         case "Feet":
            var10000 = feetPos;
            break;
         case "Torso":
            var10000 = feetPos.method_1031((double)0.0F, (double)(entity.method_17682() / 2.0F), (double)0.0F);
            break;
         case "Head":
            var10000 = entity.method_33571();
            break;
         case "Auto":
            class_243 torsoPos = feetPos.method_1031((double)0.0F, (double)(entity.method_17682() / 2.0F), (double)0.0F);
            class_243 eyesPos = entity.method_33571();
            var10000 = mc.field_1724.method_33571().method_1025(eyesPos) < (double)0.2F ? feetPos : (class_243)Stream.of(feetPos, torsoPos, eyesPos).min(Comparator.comparing((b) -> mc.field_1724.method_33571().method_1025(b))).orElse(eyesPos);
            break;
         default:
            throw new IllegalStateException("Unexpected value: " + (String)this.rotateType.getValue());
      }

      return var10000;
   }

   public String getHudInfo() {
      return "Single";
   }

   public String getDescription() {
      return "Attacks nearby entities";
   }
}
