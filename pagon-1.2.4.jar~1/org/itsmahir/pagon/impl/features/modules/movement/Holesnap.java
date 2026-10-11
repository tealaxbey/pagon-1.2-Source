package org.itsmahir.pagon.impl.features.modules.movement;

import java.text.DecimalFormat;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_2828;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.move.MoveEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.event.events.network.ServerEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.chat.ChatMessage;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.utils.render.RenderTimer;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.targeting.TargetUtils;
import org.itsmahir.pagon.api.utils.world.HoleUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class Holesnap extends Module {
   public boolean cancel = false;
   public static Holesnap INSTANCE;
   Value<Number> range = (new ValueBuilder<Number>()).withDescriptor("Range").withValue(3).withRange(1, 6).register(this);
   Value<Boolean> postTimer = (new ValueBuilder<Boolean>()).withDescriptor("Use Post Timer").withValue(false).register(this);
   Value<Boolean> boost = (new ValueBuilder<Boolean>()).withDescriptor("Boost").withValue(false).register(this);
   Value<Boolean> useTimer = (new ValueBuilder<Boolean>()).withDescriptor("Use TickSpeed").withValue(false).register(this);
   Value<Number> timerAmount;
   Value<Number> BoostTime;
   Value<Boolean> doubles;
   Value<Boolean> selfHole;
   Value<Boolean> physics;
   Value<Number> physicsTicks;
   Value<String> mode;
   Value<String> stepMode;
   Value<PagonColor> leftColor;
   Value<PagonColor> rightColor;
   float oldTickLength;
   int boostTime;
   DecimalFormat df;
   private int ticks;
   class_2338 startPos;
   public class_243 lastTarget;
   HoleUtils.Hole targetHole;
   boolean disablenexttick;
   int stuckTicks;
   int posttimerticks;
   boolean ranPhysics;

   public Holesnap() {
      super("Holesnap", Feature.Category.Movement);
      this.timerAmount = (new ValueBuilder<Number>()).withDescriptor("Tick Amount").withValue(5).withRange(1, 10).withParent(this.useTimer).withParentEnabled(true).register(this);
      this.BoostTime = (new ValueBuilder<Number>()).withDescriptor("Boost Time").withValue(25).withRange(10, 100).withParent(this.useTimer).withParentEnabled(true).register(this);
      this.doubles = (new ValueBuilder<Boolean>()).withDescriptor("Doubles").withValue(false).register(this);
      this.selfHole = (new ValueBuilder<Boolean>()).withDescriptor("Swapping").withValue(false).register(this);
      this.physics = (new ValueBuilder<Boolean>()).withDescriptor("Physics").withValue(false).register(this);
      this.physicsTicks = (new ValueBuilder<Number>()).withDescriptor("Physics Ticks").withValue(3).withRange(3, 20).withParent(this.physics).withParentEnabled(true).register(this);
      this.mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Normal").withModes("Normal", "Pathfinding").register(this);
      this.stepMode = (new ValueBuilder<String>()).withDescriptor("Step Mode").withValue("Vanilla").withModes("Vanilla", "NCP", "NONE").register(this);
      this.leftColor = (new ValueBuilder<PagonColor>()).withDescriptor("Left Color").withValue(new PagonColor(255, 0, 255, 255)).register(this);
      this.rightColor = (new ValueBuilder<PagonColor>()).withDescriptor("Right Color").withValue(new PagonColor(0, 255, 0)).register(this);
      this.oldTickLength = 50.0F;
      this.boostTime = 0;
      this.df = new DecimalFormat("#.##");
      this.ticks = 0;
      this.startPos = null;
      this.disablenexttick = false;
      this.ranPhysics = false;
      INSTANCE = this;
   }

   public void onEnable() {
      super.onEnable();
      if (!NullUtils.nullCheck()) {
         this.lastTarget = null;
         this.boostTime = 0;
         this.stuckTicks = 0;
         this.targetHole = null;
         this.startPos = PlayerUtils.getPlayerPos();
         this.targetHole = TargetUtils.getTargetHoleVec3D(((Number)this.range.getValue()).doubleValue(), (Boolean)this.selfHole.getValue(), (Boolean)this.doubles.getValue());
         if (this.targetHole == null) {
            NoAccel.paused = false;
            this.cancel = false;
            if ((Boolean)this.useTimer.getValue() && mc.field_1724.field_6012 > 10) {
               RenderTimer.setTickLength(1.0F);
            }

            ChatUtils.sendMessage(new ChatMessage("[Holesnap] Unable to find hole. Disabling", true, 777722));
            this.lastTarget = null;
            this.toggle();
         }

      }
   }

   public void onDisable() {
      super.onDisable();
      if (!NullUtils.nullCheck()) {
         this.ranPhysics = false;
         if (mc.field_1724.field_6012 > 10) {
            RenderTimer.setTickLength(1.0F);
         }

         this.lastTarget = null;
         Step.setStepHeight(0.6F);
         NoAccel.paused = false;
         this.posttimerticks = 0;
      }
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Send event) {
      if (!NullUtils.nullCheck()) {
         if (event.getPacket() instanceof class_2828) {
            ++this.boostTime;
         }

      }
   }

   @SubscribeEvent
   public void onTick(TickEvent.ClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         if (this.boostTime >= ((Number)this.BoostTime.getValue()).intValue() && mc.field_1724.field_6012 > 10) {
            RenderTimer.setTickLength(1.0F);
         }

         if (((String)this.stepMode.getValue()).equals("NCP")) {
            if (this.targetHole == null || this.lastTarget == null) {
               return;
            }

            float yawRad = this.getRotationTo(this.lastTarget, mc.field_1724.method_19538()).field_1343;
            double[] dir = PlayerUtils.forward(0.1, yawRad, true);
            Step.INSTANCE.doNCPStep(dir, true);
         }

      }
   }

   @SubscribeEvent
   public void onMove(MoveEvent event) {
      if (!NullUtils.nullCheck()) {
         if (this.disablenexttick && (Boolean)this.postTimer.getValue()) {
            NoAccel.paused = false;
            this.posttimerticks = 0;
            if (mc.field_1724.field_6012 > 10) {
               RenderTimer.setTickLength(1.0F);
            }

            ChatUtils.sendMessage(new ChatMessage("[Holesnap] Snapped into hole, disabling.", true, 777722));
            this.disablenexttick = false;
            this.toggle();
            this.lastTarget = null;
         } else {
            HoleUtils.Hole doubleHole = HoleUtils.isDoubleHoleFr(PlayerUtils.getPlayerPos());
            if (!HoleUtils.isObbyHole(PlayerUtils.getPlayerPos()) && !HoleUtils.isBedrockHoles(PlayerUtils.getPlayerPos()) && (!(Boolean)this.doubles.getValue() || doubleHole == null || this.lastTarget != null && !(mc.field_1724.method_19538().method_1022(this.lastTarget) < 0.1) && this.targetHole.toTarget == null) || (Boolean)this.selfHole.getValue() && PlayerUtils.getPlayerPos().equals(this.startPos)) {
               if (this.targetHole != null && mc.field_1687.method_8320(this.targetHole.pos1).method_26204() == class_2246.field_10124) {
                  if ((Boolean)this.useTimer.getValue() && ((Number)this.BoostTime.getValue()).intValue() > this.boostTime) {
                     if (mc.field_1724.field_6012 > 10) {
                        RenderTimer.setTickLength(((Number)this.timerAmount.getValue()).floatValue());
                     }
                  } else if (mc.field_1724.field_6012 > 10) {
                     RenderTimer.setTickLength(1.0F);
                  }

                  if ((Boolean)this.physics.getValue() && !this.ranPhysics) {
                     this.ranPhysics = true;

                     for(int i = 0; i < ((Number)this.physicsTicks.getValue()).intValue(); ++i) {
                        if (!this.isEnabled()) {
                           this.setEnabled(false);
                           return;
                        }

                        PlayerUtils.runPhysicsTick();
                     }
                  }

                  NoAccel.paused = true;
                  this.cancel = true;
                  class_243 playerPos = mc.field_1724.method_19538();
                  class_243 targetPos;
                  if (this.targetHole.doubleHole) {
                     if (this.targetHole.toTarget != null) {
                        targetPos = new class_243((double)this.targetHole.toTarget.method_10263() + (double)0.5F, mc.field_1724.method_23318(), (double)this.targetHole.toTarget.method_10260() + (double)0.5F);
                     } else {
                        class_2338 pos1 = this.targetHole.pos1;
                        class_2338 pos2 = this.targetHole.pos2;
                        double centerX = ((double)pos1.method_10263() + (double)0.5F + (double)pos2.method_10263() + (double)0.5F) / (double)2.0F;
                        double centerZ = ((double)pos1.method_10260() + (double)0.5F + (double)pos2.method_10260() + (double)0.5F) / (double)2.0F;
                        targetPos = new class_243(centerX, mc.field_1724.method_23318(), centerZ);
                     }
                  } else {
                     targetPos = new class_243((double)this.targetHole.pos1.method_10263() + (double)0.5F, mc.field_1724.method_23318(), (double)this.targetHole.pos1.method_10260() + (double)0.5F);
                  }

                  this.lastTarget = targetPos;
                  switch ((String)this.mode.getValue()) {
                     case "Normal":
                        double yawRad = Math.toRadians((double)this.getRotationTo(playerPos, targetPos).field_1343);
                        double dist = playerPos.method_1022(targetPos);
                        double speed;
                        if ((Boolean)this.boost.getValue()) {
                           speed = mc.field_1724.method_24828() ? -Math.min(PlayerUtils.getDefaultBaseSpeed(true), dist / (double)2.0F) : -PlayerUtils.getDefaultBaseSpeed(false);
                        } else {
                           speed = mc.field_1724.method_24828() ? -Math.min(0.2805, dist / (double)2.0F) : -PlayerUtils.getDefaultBaseSpeed(false);
                        }

                        event.setX(-Math.sin(yawRad) * speed);
                        event.setZ(Math.cos(yawRad) * speed);
                        if (mc.field_1724.field_5976 && mc.field_1724.method_24828()) {
                           ++this.stuckTicks;
                           if (((String)this.stepMode.getValue()).equals("Vanilla")) {
                              Step.setStepHeight(((Number)Step.INSTANCE.stepHeight.getValue()).floatValue());
                           }
                        } else {
                           this.stuckTicks = 0;
                           Step.setStepHeight(0.6F);
                        }
                  }
               } else {
                  if ((Boolean)this.useTimer.getValue() && mc.field_1724.field_6012 > 10) {
                     RenderTimer.setTickLength(1.0F);
                  }

                  NoAccel.paused = false;
                  this.cancel = false;
                  this.lastTarget = null;
                  ChatUtils.sendMessage(new ChatMessage("[Holesnap] Hole no longer exists, disabling.", true, 777722));
                  this.toggle();
               }

            } else if ((Boolean)this.postTimer.getValue() && !this.disablenexttick) {
               if (mc.field_1724.field_6012 > 10) {
                  RenderTimer.setTickLength(this.oldTickLength / 0.2F);
               }

               ++this.posttimerticks;
               if (this.posttimerticks >= 4) {
                  this.disablenexttick = true;
               }

            } else {
               NoAccel.paused = false;
               this.posttimerticks = 0;
               if (mc.field_1724.field_6012 > 10) {
                  RenderTimer.setTickLength(1.0F);
               }

               ChatUtils.sendMessage(new ChatMessage("[Holesnap] Snapped into hole, disabling.", true, 777722));
               this.posttimerticks = 0;
               this.disablenexttick = false;
               this.toggle();
            }
         }
      }
   }

   @SubscribeEvent
   public void onServerLeft(ServerEvent.ServerLeft event) {
      this.toggle();
   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldEvent event) {
      if (this.targetHole != null && this.lastTarget != null) {
         class_243 targetPos = this.lastTarget;
         RenderUtil.renderLineFromPosToPos(mc.field_1724.method_19538(), targetPos, ((PagonColor)this.rightColor.getValue()).getColor(), ((PagonColor)this.leftColor.getValue()).getColor(), 1.0F);
      }

   }

   public double normalizeAngle(Double angleIn) {
      double angle = angleIn;
      angle %= (double)360.0F;
      if (angle >= (double)180.0F) {
         angle -= (double)360.0F;
      }

      if (angle < (double)-180.0F) {
         angle += (double)360.0F;
      }

      return angle;
   }

   public class_241 getRotationTo(class_243 posTo, class_243 posFrom) {
      return this.getRotationFromVec(posTo.method_1020(posFrom));
   }

   public class_241 getRotationFromVec(class_243 vec) {
      double xz = Math.hypot(vec.field_1352, vec.field_1350);
      float yaw = (float)this.normalizeAngle(Math.toDegrees(Math.atan2(vec.field_1350, vec.field_1352)) - (double)90.0F);
      float pitch = (float)this.normalizeAngle(Math.toDegrees(-Math.atan2(vec.field_1351, xz)));
      return new class_241(yaw, pitch);
   }

   public String getHudInfo() {
      if (this.targetHole != null && this.lastTarget != null) {
         class_243 playerPos = mc.field_1724.method_19538();
         return this.df.format(playerPos.method_1022(this.lastTarget));
      } else {
         return "None";
      }
   }

   public String getDescription() {
      return "Holesnap: Moves and centers yourself into the nearest hole (or teleport with some settings)";
   }
}
