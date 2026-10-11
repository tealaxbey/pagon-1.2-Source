package org.itsmahir.pagon.impl.features.modules.movement;

import com.google.common.collect.Streams;
import java.util.List;
import net.minecraft.class_1294;
import net.minecraft.class_2596;
import net.minecraft.class_265;
import net.minecraft.class_2777;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.move.MoveEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.BoostManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.utils.render.RenderTimer;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class Speed extends Module {
   Value<String> speedMode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Strafe").withModes("Strafe", "StrafeFast", "StrafeStrict", "StrictFast", "StrafeInsano", "Vanilla").withAction((s) -> this.handlePage((String)s.getValue())).register(this);
   Value<String> timerMode = (new ValueBuilder<String>()).withDescriptor("Timer", "TimerMode").withValue("None").withModes("None", "0888", "Fast").register(this);
   Value<Boolean> buffer = (new ValueBuilder<Boolean>()).withDescriptor("Buffer").withValue(false).register(this);
   Value<Boolean> boost = (new ValueBuilder<Boolean>()).withDescriptor("Boost").withValue(false).register(this);
   Value<Number> speed;
   public static Speed INSTANCE;
   private int strafeStage;
   private double ncpPrevMotion;
   private double horizontal;
   double BUNNY_DIV_FRICTION;
   private double currentSpeed;
   private double prevMotion;
   private boolean accelerate;
   private int state;
   private int aacCounter;
   float oldTickLength;

   public Speed() {
      super("Speed", Feature.Category.Movement);
      this.speed = (new ValueBuilder<Number>()).withDescriptor("Speed").withValue(1).withRange(0.01, 2).withPlaces(2).withPageParent(this.speedMode).withPage("Vanilla").register(this);
      this.strafeStage = 1;
      this.ncpPrevMotion = (double)0.0F;
      this.BUNNY_DIV_FRICTION = 159.077;
      this.currentSpeed = (double)0.0F;
      this.prevMotion = (double)0.0F;
      this.accelerate = false;
      this.state = 4;
      this.oldTickLength = 1.0F;
      INSTANCE = this;
   }

   void handlePage(String page) {
      boolean isStrafe = this.isStrafeSpeed(page);
      this.timerMode.setActive(isStrafe);
      this.boost.setActive(isStrafe);
      this.buffer.setActive(((String)this.speedMode.getValue()).equals("Strafe"));
   }

   public void onEnable() {
      super.onEnable();
      this.oldTickLength = 1.0F;
      this.reset();
   }

   public void onDisable() {
      super.onDisable();
      if (!((String)this.timerMode.getValue()).equals("None")) {
         RenderTimer.setTickLength(this.oldTickLength);
      }

      this.reset();
   }

   public void reset() {
      this.ncpPrevMotion = (double)0.0F;
      this.currentSpeed = (double)0.0F;
      this.horizontal = (double)0.0F;
      this.state = 4;
      this.prevMotion = (double)0.0F;
      this.aacCounter = 0;
      this.accelerate = false;
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.ClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         if (!mc.field_1724.method_6128()) {
            if (LongJump.INSTANCE.isEnabled() && ((String)LongJump.INSTANCE.mode.getValue()).contains("Strict") && BoostManager.INSTANCE.canDoLongjump()) {
               this.reset();
            } else {
               if (this.isStrafeSpeed()) {
                  if (mc.method_60646() != null && !((String)this.timerMode.getValue()).equals("None")) {
                  }

                  if (PlayerUtils.isMoving()) {
                     mc.field_1724.method_5728(true);
                  }

                  this.ncpPrevMotion = Math.sqrt((mc.field_1724.method_23317() - mc.field_1724.field_6014) * (mc.field_1724.method_23317() - mc.field_1724.field_6014) + (mc.field_1724.method_23321() - mc.field_1724.field_5969) * (mc.field_1724.method_23321() - mc.field_1724.field_5969));
               }

            }
         }
      }
   }

   public boolean isStrafeSpeed() {
      return ((String)this.speedMode.getValue()).contains("Strafe") || ((String)this.speedMode.getValue()).equals("StrictFast");
   }

   public boolean isStrafeSpeed(String page) {
      return page.contains("Strafe") || page.equals("StrictFast");
   }

   @SubscribeEvent
   public void onUpdateWalkingPlayerPre(TickEvent.MovementTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (!mc.field_1724.method_6128()) {
            if (LongJump.INSTANCE.isEnabled() && ((String)LongJump.INSTANCE.mode.getValue()).contains("Strict") && BoostManager.INSTANCE.canDoLongjump()) {
               this.reset();
            } else {
               if (this.isStrafeSpeed()) {
                  double dX = mc.field_1724.method_23317() - mc.field_1724.field_6014;
                  double dZ = mc.field_1724.method_23321() - mc.field_1724.field_5969;
                  this.prevMotion = Math.sqrt(dX * dX + dZ * dZ);
               }

            }
         }
      }
   }

   @SubscribeEvent
   public void onMove(MoveEvent event) {
      if (!NullUtils.nullCheck()) {
         if (!mc.field_1724.method_6128()) {
            if (LongJump.INSTANCE.isEnabled() && ((String)LongJump.INSTANCE.mode.getValue()).contains("Strict") && BoostManager.INSTANCE.canDoLongjump()) {
               this.reset();
            } else {
               switch ((String)this.speedMode.getValue()) {
                  case "Strafe":
                     this.doStrafe(event);
                     break;
                  case "StrafeFast":
                     this.doStrafe(event);
                     break;
                  case "StrafeStrict":
                     this.doRestrictedSpeed(event, 0.465, 0.44, false);
                     break;
                  case "StrictFast":
                     if (mc.field_1724.method_6059(class_1294.field_5904)) {
                        this.doStrafe(event);
                     } else {
                        this.doRestrictedSpeed(event, 0.465, 0.44, true);
                     }
                     break;
                  case "Vanilla":
                     PlayerUtils.setSpeed(((Number)this.speed.getValue()).doubleValue(), event);
               }

            }
         }
      }
   }

   public void doStrafe(MoveEvent event) {
      if (this.state != 1 || mc.field_1724.field_3913.field_3905 == 0.0F && mc.field_1724.field_3913.field_3907 == 0.0F) {
         if (this.state == 2 && (mc.field_1724.field_3913.field_3905 != 0.0F || mc.field_1724.field_3913.field_3907 != 0.0F) && mc.field_1724.method_24828()) {
            double jumpSpeed = (double)0.0F;
            if (mc.field_1724.method_6059(class_1294.field_5913)) {
               jumpSpeed += (double)((float)(mc.field_1724.method_6112(class_1294.field_5913).method_5578() + 1) * 0.1F);
            }

            event.motionY((double)0.39999995F + jumpSpeed);
            if ((Boolean)this.buffer.getValue() && !((String)this.speedMode.getValue()).equals("StrictFast")) {
               this.currentSpeed *= this.accelerate ? 1.6835 : 1.395;
            } else {
               this.currentSpeed *= 1.535;
            }

            if ((Boolean)this.boost.getValue()) {
               this.currentSpeed = Math.max(BoostManager.INSTANCE.getBoostSpeed(true), this.currentSpeed);
            }
         } else if (this.state == 3) {
            double adjustedMotion = 0.66 * (this.prevMotion - this.getBaseMotionSpeed());
            this.currentSpeed = this.prevMotion - adjustedMotion;
            this.accelerate = !this.accelerate;
            if ((Boolean)this.boost.getValue()) {
               this.currentSpeed = Math.max(BoostManager.INSTANCE.getBoostSpeed(true), this.currentSpeed);
            }
         } else {
            List<class_265> collisionBoxes = Streams.stream(mc.field_1687.method_8600(mc.field_1724, mc.field_1724.method_5829().method_989((double)0.0F, mc.field_1724.method_18798().field_1351, (double)0.0F))).toList();
            if ((collisionBoxes.size() > 0 || mc.field_1724.field_5992) && this.state > 0) {
               this.state = mc.field_1724.field_3913.field_3905 == 0.0F && mc.field_1724.field_3913.field_3907 == 0.0F ? 0 : 1;
            }

            this.currentSpeed = this.prevMotion - this.prevMotion / (double)159.0F;
         }
      } else {
         this.currentSpeed = 1.35 * this.getBaseMotionSpeed() - 0.01;
      }

      this.currentSpeed = Math.max(this.currentSpeed, this.getBaseMotionSpeed());
      double forward = (double)mc.field_1724.field_3913.field_3905;
      double strafe = (double)mc.field_1724.field_3913.field_3907;
      float yaw = mc.field_1724.method_36454();
      if (forward == (double)0.0F && strafe == (double)0.0F) {
         event.setX((double)0.0F);
         event.setZ((double)0.0F);
      } else {
         if (forward != (double)0.0F) {
            if (strafe > (double)0.0F) {
               yaw += (float)(forward > (double)0.0F ? -45 : 45);
            } else if (strafe < (double)0.0F) {
               yaw += (float)(forward > (double)0.0F ? 45 : -45);
            }

            strafe = (double)0.0F;
            if (forward > (double)0.0F) {
               forward = (double)1.0F;
            } else if (forward < (double)0.0F) {
               forward = (double)-1.0F;
            }
         }

         double cos = Math.cos(Math.toRadians((double)(yaw + 90.0F)));
         double sin = Math.sin(Math.toRadians((double)(yaw + 90.0F)));
         event.setX(forward * this.currentSpeed * cos + strafe * this.currentSpeed * sin);
         event.setZ(forward * this.currentSpeed * sin - strafe * this.currentSpeed * cos);
      }

      if (mc.field_1724.field_3913.field_3905 != 0.0F || mc.field_1724.field_3913.field_3907 != 0.0F) {
         ++this.state;
      }
   }

   public void doRestrictedSpeed(MoveEvent event, double baseRestriction, double actualRestriction, boolean fast) {
      if (PlayerUtils.isMoving()) {
         if (fast && MathUtil.round(mc.field_1724.method_23318() - (double)((int)mc.field_1724.method_23318()), 3) == MathUtil.round(0.138, 3)) {
            PlayerUtils.setMotionY(mc.field_1724.method_18798().field_1351 - 0.08);
            event.setY(event.getY() - 0.09316090325960147);
            mc.field_1724.method_23327(mc.field_1724.method_19538().field_1352, mc.field_1724.method_19538().field_1351 - 0.09316090325960147, mc.field_1724.method_19538().field_1350);
         }

         if (this.strafeStage == 1) {
            this.currentSpeed = fast ? 1.38 : 1.35 * this.getBaseMotionSpeedNoPot() - 0.01;
         } else if (this.strafeStage == 2 && mc.field_1724.method_24828()) {
            double jumpSpeed = (double)0.42F;
            if (mc.field_1724.method_6059(class_1294.field_5913)) {
               double amplifier = (double)mc.field_1724.method_6112(class_1294.field_5913).method_5578();
               jumpSpeed += (amplifier + (double)1.0F) * 0.1;
            }

            event.motionY(jumpSpeed);
            double acceleration = 2.149;
            if ((Boolean)this.boost.getValue()) {
               this.currentSpeed += BoostManager.INSTANCE.getBoostSpeed(false);
            }

            this.currentSpeed *= acceleration;
         } else if (this.strafeStage == 3) {
            double scaledcurrentSpeed = 0.66 * (this.prevMotion - this.getBaseMotionSpeedNoPot());
            this.currentSpeed = this.prevMotion - scaledcurrentSpeed;
            if ((Boolean)this.boost.getValue()) {
               this.currentSpeed += BoostManager.INSTANCE.getBoostSpeed(false);
            }
         } else {
            if ((!Streams.stream(mc.field_1687.method_8600(mc.field_1724, mc.field_1724.method_5829().method_989((double)0.0F, mc.field_1724.method_18798().field_1351, (double)0.0F))).toList().isEmpty() || mc.field_1724.field_5992) && this.strafeStage > 0) {
               this.strafeStage = PlayerUtils.isMoving() ? 1 : 0;
            }

            this.currentSpeed = this.prevMotion - this.prevMotion / this.BUNNY_DIV_FRICTION;
         }

         this.currentSpeed = Math.max(this.currentSpeed, this.getBaseMotionSpeedNoPot());
         double baseStrictSpeed = baseRestriction;
         double baseRestrictedSpeed = actualRestriction;
         if (mc.field_1724.method_6059(class_1294.field_5904)) {
            double amplifier = (double)mc.field_1724.method_6112(class_1294.field_5904).method_5578();
            baseStrictSpeed = baseRestriction * ((double)1.0F + 0.2 * (amplifier + (double)1.0F));
            baseRestrictedSpeed = actualRestriction * ((double)1.0F + 0.2 * (amplifier + (double)1.0F));
         }

         if (mc.field_1724.method_6059(class_1294.field_5909)) {
            double amplifier = (double)mc.field_1724.method_6112(class_1294.field_5909).method_5578();
            baseStrictSpeed /= (double)1.0F + 0.2 * (amplifier + (double)1.0F);
            baseRestrictedSpeed /= (double)1.0F + 0.2 * (amplifier + (double)1.0F);
         }

         this.currentSpeed = Math.min(this.currentSpeed, this.aacCounter > 25 ? baseStrictSpeed : baseRestrictedSpeed);
         ++this.aacCounter;
         if (this.aacCounter > 50) {
            this.aacCounter = 0;
         }

         float forward = mc.field_1724.field_3913.field_3905;
         float strafe = mc.field_1724.field_3913.field_3907;
         if (mc.field_1724.method_5715()) {
            strafe = 0.0F;
         }

         float yaw = mc.field_1724.field_5982 + (mc.field_1724.method_36454() - mc.field_1724.field_5982) * mc.method_60646().method_60637(false);
         if (!PlayerUtils.isMoving()) {
            event.setX((double)0.0F);
            event.setZ((double)0.0F);
         } else if (forward != 0.0F) {
            if (strafe >= 1.0F) {
               yaw += (float)(forward > 0.0F ? -45 : 45);
               strafe = 0.0F;
            } else if (strafe <= -1.0F) {
               yaw += (float)(forward > 0.0F ? 45 : -45);
               strafe = 0.0F;
            }

            if (forward > 0.0F) {
               forward = 1.0F;
            } else if (forward < 0.0F) {
               forward = -1.0F;
            }
         }

         double cos = Math.cos(Math.toRadians((double)yaw));
         double sin = -Math.sin(Math.toRadians((double)yaw));
         event.setX((double)forward * this.currentSpeed * sin + (double)strafe * this.currentSpeed * cos);
         event.setZ((double)forward * this.currentSpeed * cos - (double)strafe * this.currentSpeed * sin);
         ++this.strafeStage;
      } else {
         event.setX((double)0.0F);
         event.setZ((double)0.0F);
      }

   }

   private double getBaseMotionSpeed() {
      double baseSpeed = 0.2873;
      if (((String)this.speedMode.getValue()).equalsIgnoreCase("StrafeFast")) {
         baseSpeed = 0.31;
      }

      if (mc.field_1724.method_6059(class_1294.field_5904)) {
         if (((String)this.speedMode.getValue()).equalsIgnoreCase("StrafeFast")) {
            baseSpeed = 0.32;
         }

         int amplifier = mc.field_1724.method_6112(class_1294.field_5904).method_5578();
         baseSpeed *= (double)1.0F + 0.1 * ((double)amplifier + (double)1.0F);
      }

      return baseSpeed;
   }

   private double getBaseMotionSpeedNoPot() {
      return 0.2873;
   }

   @SubscribeEvent
   public void onPacketReceive(PacketEvent.Receive event) {
      class_2596 var3 = event.getPacket();
      if (var3 instanceof class_2777 packet) {
         if (packet.method_11916() == mc.field_1724.method_5628()) {
            this.ncpPrevMotion = (double)0.0F;
            this.currentSpeed = (double)0.0F;
            this.horizontal = (double)0.0F;
            this.state = 4;
            this.prevMotion = (double)0.0F;
            this.aacCounter = 0;
         }
      }

   }

   public String getHudInfo() {
      return this.speedMode.getValue();
   }

   public String getDescription() {
      return "Speed: Move around at rapid speeds";
   }
}
