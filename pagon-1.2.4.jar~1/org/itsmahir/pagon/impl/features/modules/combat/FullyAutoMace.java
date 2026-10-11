package org.itsmahir.pagon.impl.features.modules.combat;

import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_243;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.FriendManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.movement.FakeFly;
import org.itsmahir.pagon.nativep.FakeFlyNative;

public class FullyAutoMace extends Module {
   public static FullyAutoMace INSTANCE;
   private State state;
   private class_1657 target;
   private boolean wasFakeFlyEnabled;
   private boolean fakeFlyPitchWas;
   private boolean fakeFlyFireworkWas;
   private boolean pressedJump;
   private boolean pressedSneak;
   private final Value<Number> height;
   private final Value<Number> targetRange;
   private final Value<Number> attackRange;
   private final Value<Boolean> silentSwitch;
   private final Value<Number> packetsPerTick;

   public FullyAutoMace() {
      super("FullyAutoMace", Feature.Category.Combat);
      this.state = FullyAutoMace.State.IDLE;
      this.height = (new ValueBuilder<Number>()).withDescriptor("Height").withValue(25).withRange(5, 50).withPlaces(0).register(this);
      this.targetRange = (new ValueBuilder<Number>()).withDescriptor("TargetRange").withValue(50).withRange(10, 200).withPlaces(0).register(this);
      this.attackRange = (new ValueBuilder<Number>()).withDescriptor("AttackRange").withValue((double)4.5F).withRange(1, 8).withPlaces(1).register(this);
      this.silentSwitch = (new ValueBuilder<Boolean>()).withDescriptor("SilentSwitch").withValue(true).register(this);
      this.packetsPerTick = (new ValueBuilder<Number>()).withDescriptor("PacketsPerTick").withValue(3).withRange(1, 20).withPlaces(0).register(this);
      INSTANCE = this;
   }

   public void onEnable() {
      super.onEnable();
      this.target = null;
      this.wasFakeFlyEnabled = FakeFly.INSTANCE.isEnabled();
      this.fakeFlyPitchWas = this.getFakeFlyValue("pitch");
      this.fakeFlyFireworkWas = this.getFakeFlyValue("firework");
      if (!this.wasFakeFlyEnabled && FakeFlyNative.isAvailable()) {
         this.setFakeFlyValue("pitch", true);
         this.setFakeFlyValue("firework", true);
         FakeFly.INSTANCE.toggle();
      }

      this.state = FullyAutoMace.State.IDLE;
   }

   public void onDisable() {
      super.onDisable();
      this.releaseKeys();
      this.target = null;
      if (FakeFly.INSTANCE.isEnabled() && !this.wasFakeFlyEnabled) {
         FakeFly.INSTANCE.toggle();
         this.setFakeFlyValue("pitch", this.fakeFlyPitchWas);
         this.setFakeFlyValue("firework", this.fakeFlyFireworkWas);
      }

   }

   @SubscribeEvent
   public void onPlayerUpdate(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (!mc.field_1724.method_29504() && !mc.field_1724.method_7325()) {
            if (FakeFlyNative.isAvailable()) {
               if (!FakeFly.INSTANCE.isEnabled()) {
                  this.releaseKeys();
                  this.state = FullyAutoMace.State.IDLE;
               } else {
                  if (mc.field_1724.method_24828()) {
                     this.pressJump();
                  }

                  this.updateTarget();
                  switch (this.state.ordinal()) {
                     case 0 -> this.handleIdle();
                     case 1 -> this.handleApproaching();
                     case 2 -> this.handleAscending();
                     case 3 -> this.handleDiving();
                     case 4 -> this.handleAttacking();
                  }

               }
            }
         } else {
            this.releaseKeys();
         }
      }
   }

   private void releaseKeys() {
      if (this.pressedJump) {
         mc.field_1690.field_1903.method_23481(false);
         this.pressedJump = false;
      }

      if (this.pressedSneak) {
         mc.field_1690.field_1832.method_23481(false);
         this.pressedSneak = false;
      }

   }

   private void pressJump() {
      if (!this.pressedJump) {
         mc.field_1690.field_1903.method_23481(true);
         this.pressedJump = true;
      }

   }

   private void releaseJump() {
      if (this.pressedJump) {
         mc.field_1690.field_1903.method_23481(false);
         this.pressedJump = false;
      }

   }

   private void pressSneak() {
      if (!this.pressedSneak) {
         mc.field_1690.field_1832.method_23481(true);
         this.pressedSneak = true;
      }

   }

   private void releaseSneak() {
      if (this.pressedSneak) {
         mc.field_1690.field_1832.method_23481(false);
         this.pressedSneak = false;
      }

   }

   private class_243 predictPos(class_1657 target) {
      class_243 vel = target.method_18798();
      double dist = (double)mc.field_1724.method_5739(target);
      double ticks = Math.min(dist * 0.3, (double)8.0F);
      return target.method_19538().method_1031(vel.field_1352 * ticks, vel.field_1351 * ticks, vel.field_1350 * ticks);
   }

   private void updateTarget() {
      if (mc.field_1687 == null) {
         this.target = null;
      } else {
         double range = ((Number)this.targetRange.getValue()).doubleValue();
         class_1657 best = null;
         double bestDist = Double.MAX_VALUE;

         for(class_1657 p : mc.field_1687.method_18456()) {
            if (p != mc.field_1724 && p.method_5805() && !p.method_7325() && !FriendManager.INSTANCE.isFriend(p)) {
               double d = mc.field_1724.method_5858(p);
               if (d <= range * range && d < bestDist) {
                  bestDist = d;
                  best = p;
               }
            }
         }

         this.target = best;
      }
   }

   private void handleIdle() {
      this.pressJump();
      if (this.target != null) {
         this.state = FullyAutoMace.State.APPROACHING;
         this.handleApproaching();
      }

   }

   private void handleApproaching() {
      if (this.target == null) {
         this.releaseJump();
         this.state = FullyAutoMace.State.IDLE;
      } else {
         double horizDist = this.horizontalDistance(this.target);
         if (horizDist < (double)20.0F) {
            this.state = FullyAutoMace.State.ASCENDING;
            this.handleAscending();
         } else {
            this.releaseJump();
            class_243 pred = this.predictPos(this.target).method_1031((double)0.0F, this.target.method_23320() - (double)this.target.method_31478(), (double)0.0F);
            float[] rot = RotationUtils.getRotationsTo(mc.field_1724.method_33571(), pred);
            RotationUtils.setRotation(rot[0], rot[1], 100);
         }
      }
   }

   private void handleAscending() {
      if (this.target == null) {
         this.releaseKeys();
         this.state = FullyAutoMace.State.IDLE;
      } else {
         double targetY = this.predictPos(this.target).field_1351 + ((Number)this.height.getValue()).doubleValue();
         double horizDist = this.horizontalDistance(this.target);
         if (mc.field_1724.method_23318() >= targetY - (double)1.0F && horizDist < (double)10.0F) {
            this.state = FullyAutoMace.State.DIVING;
            this.handleDiving();
         } else {
            this.pressJump();
            this.releaseSneak();
            class_243 aboveTarget = this.predictPos(this.target).method_1031((double)0.0F, ((Number)this.height.getValue()).doubleValue(), (double)0.0F);
            float[] rot = RotationUtils.getRotationsTo(mc.field_1724.method_33571(), aboveTarget);
            RotationUtils.setRotation(rot[0], rot[1], 100);
         }
      }
   }

   private void handleDiving() {
      if (this.target == null) {
         this.releaseKeys();
         this.state = FullyAutoMace.State.IDLE;
      } else {
         double dist = mc.field_1724.method_5858(this.target);
         double atkRange = ((Number)this.attackRange.getValue()).doubleValue();
         if (dist <= atkRange * atkRange) {
            this.state = FullyAutoMace.State.ATTACKING;
            this.handleAttacking();
         } else {
            this.pressSneak();
            this.releaseJump();
            class_243 pred = this.predictPos(this.target).method_1031((double)0.0F, this.target.method_23320() - (double)this.target.method_31478(), (double)0.0F);
            float[] rot = RotationUtils.getRotationsTo(mc.field_1724.method_33571(), pred);
            RotationUtils.setRotation(rot[0], rot[1], 100);
         }
      }
   }

   private void handleAttacking() {
      this.releaseKeys();
      if (this.target == null) {
         this.state = FullyAutoMace.State.IDLE;
      } else {
         class_243 pred = this.predictPos(this.target).method_1031((double)0.0F, this.target.method_23320() - (double)this.target.method_31478(), (double)0.0F);
         float[] rot = RotationUtils.getRotationsTo(mc.field_1724.method_33571(), pred);
         RotationUtils.doSilentRotate(rot);
         int maceSlot = this.findInHotbar(class_1802.field_49814);
         if (maceSlot < 0) {
            this.state = FullyAutoMace.State.ASCENDING;
         } else {
            int prev = mc.field_1724.method_31548().field_7545;
            mc.field_1724.method_31548().field_7545 = maceSlot;
            int packets = ((Number)this.packetsPerTick.getValue()).intValue();

            for(int i = 0; i < packets; ++i) {
               mc.field_1761.method_2918(mc.field_1724, this.target);
               mc.field_1724.method_6104(class_1268.field_5808);
            }

            if ((Boolean)this.silentSwitch.getValue()) {
               mc.field_1724.method_31548().field_7545 = prev;
            }

            this.state = FullyAutoMace.State.ASCENDING;
         }
      }
   }

   private int findInHotbar(class_1792 item) {
      class_1661 inv = mc.field_1724.method_31548();

      for(int i = 0; i < 9; ++i) {
         if (inv.method_5438(i).method_31574(item)) {
            return i;
         }
      }

      return -1;
   }

   private double horizontalDistance(class_1657 target) {
      double dx = mc.field_1724.method_23317() - target.method_23317();
      double dz = mc.field_1724.method_23321() - target.method_23321();
      return Math.sqrt(dx * dx + dz * dz);
   }

   private boolean getFakeFlyValue(String tag) {
      for(Value<?> v : FakeFly.INSTANCE.getValues()) {
         if (v.getTag().equals(tag)) {
            return (Boolean)v.getValue();
         }
      }

      return false;
   }

   private void setFakeFlyValue(String tag, boolean value) {
      for(Value<?> v : FakeFly.INSTANCE.getValues()) {
         if (v.getTag().equals(tag)) {
            v.setValue(value);
            return;
         }
      }

   }

   public String getHudInfo() {
      return this.state == null ? "IDLE" : this.state.name();
   }

   public String getDescription() {
      return "Fully automatic mace assault using FakeFly native flight";
   }

   private static enum State {
      IDLE,
      APPROACHING,
      ASCENDING,
      DIVING,
      ATTACKING;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{IDLE, APPROACHING, ASCENDING, DIVING, ATTACKING};
      }
   }
}
