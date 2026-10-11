package org.itsmahir.pagon.impl.features.modules.movement;

import net.minecraft.class_1313;
import net.minecraft.class_1802;
import net.minecraft.class_243;
import net.minecraft.class_2708;
import net.minecraft.class_2848;
import net.minecraft.class_2848.class_2849;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.LivingEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.move.MoveEvent;
import org.itsmahir.pagon.api.event.events.move.TravelEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.PagonMod;
import org.itsmahir.pagon.impl.features.modules.client.Baritone;

public class ElytraFly extends Module {
   public static ElytraFly INSTANCE;
   public Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Creative").withModes("Creative", "Bounce", "Control", "Accel", "Factor").withAction((val) -> this.handlePage((String)val.getValue())).register(this);
   public Value<Number> horizontalSpeed = (new ValueBuilder<Number>()).withDescriptor("Horizontal").withValue(3.0F).withRange(1, 5).register(this);
   Value<Number> verticalSpeed = (new ValueBuilder<Number>()).withDescriptor("Vertical").withValue(3.0F).withRange(1, 5).register(this);
   Value<Boolean> antiKick;
   Value<Number> factor;
   Value<Number> upFactor;
   Value<Number> downFactor;
   Value<Boolean> infDura;
   Value<Boolean> accelerate;
   Value<String> strictMode;
   Value<Boolean> boostControl;
   Value<Boolean> pitch;
   Value<Boolean> boostBounce;
   Value<Boolean> groundBoost;
   Value<Boolean> highwayDodge;
   private boolean rSpeed;
   private double curSpeed;
   double GRIM_AIR_FRICTION;
   class_243 lastPos;

   public ElytraFly() {
      super("ElytraFly", Feature.Category.Movement);
      this.antiKick = (new ValueBuilder<Boolean>()).withDescriptor("Anti Kick").withValue(true).withPageParent(this.mode).withPage("Factor").register(this);
      this.factor = (new ValueBuilder<Number>()).withDescriptor("Factor").withValue(1.5F).withRange(0.1, 50.0F).withPageParent(this.mode).withPage("Factor").register(this);
      this.upFactor = (new ValueBuilder<Number>()).withDescriptor("Up Factor").withValue(1.0F).withRange(0, 10.0F).withPageParent(this.mode).withPage("Factor").register(this);
      this.downFactor = (new ValueBuilder<Number>()).withDescriptor("Down Factor").withValue(1.0F).withRange(0, 10.0F).withPageParent(this.mode).withPage("Factor").register(this);
      this.infDura = (new ValueBuilder<Boolean>()).withDescriptor("Inf Dura").withValue(true).withPageParent(this.mode).withPage("Factor").register(this);
      this.accelerate = (new ValueBuilder<Boolean>()).withDescriptor("Accelerate").withValue(true).withPageParent(this.mode).withPage("Factor").register(this);
      this.strictMode = (new ValueBuilder<String>()).withDescriptor("Strict").withValue("None").withModes("None", "Normal", "NCP", "Glide").withPageParent(this.mode).withPage("Factor").register(this);
      this.boostControl = (new ValueBuilder<Boolean>()).withDescriptor("Accel", "boostControl").withValue(true).withPageParent(this.mode).withPage("Control").register(this);
      this.pitch = (new ValueBuilder<Boolean>()).withDescriptor("Pitch").withValue(false).register(this);
      this.boostBounce = (new ValueBuilder<Boolean>()).withDescriptor("Accel", "boostBounce").withValue(true).withPageParent(this.mode).withPage("Bounce").register(this);
      this.groundBoost = (new ValueBuilder<Boolean>()).withDescriptor("OnGround").withValue(true).withPageParent(this.mode).withPage("Bounce").register(this);
      this.highwayDodge = (new ValueBuilder<Boolean>()).withDescriptor("Highway").withValue(false).register(this);
      this.GRIM_AIR_FRICTION = 0.0264444413;
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onPlayerTick(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (!LongJump.isGrimJumping()) {
            if (PagonMod.BARITONE_AVAILABLE) {
               if ((Boolean)this.highwayDodge.getValue()) {
                  this.doObstaclePass();
               }

               if (PagonMod.isBaritonePaused()) {
                  return;
               }
            }

            switch ((String)this.mode.getValue()) {
               case "Creative":
               default:
                  break;
               case "Bounce":
                  if ((Boolean)this.pitch.getValue()) {
                     RotationUtils.setRotation(new float[]{mc.field_1724.method_36454(), 85.0F});
                  }
                  break;
               case "Control":
                  if (!mc.field_1724.method_6128()) {
                     return;
                  }

                  RotationUtils.setRotation(new float[]{PlayerUtils.getMoveYaw(mc.field_1724.method_36454()), this.getControlPitch()}, 99);
            }

         }
      }
   }

   @SubscribeEvent
   public void onPlayerMove(MoveEvent event) {
      if (!PagonMod.isBaritonePaused()) {
         if (!LongJump.isGrimJumping()) {
            if (mc.field_1724.method_31548().method_7372(2).method_7909().equals(class_1802.field_8833)) {
               switch ((String)this.mode.getValue()) {
                  case "Creative":
                     if (!mc.field_1724.method_6128()) {
                        return;
                     }

                     event.motionY((double)0.0F);
                     if (mc.field_1690.field_1903.method_1434()) {
                        event.motionY((double)((Number)this.verticalSpeed.getValue()).floatValue());
                     } else if (mc.field_1690.field_1832.method_1434()) {
                        event.motionY((double)(-((Number)this.verticalSpeed.getValue()).floatValue()));
                     }

                     float forward = mc.field_1724.field_3913.field_3905;
                     float strafe = mc.field_1724.field_3913.field_3907;
                     if (forward == 0.0F && strafe == 0.0F) {
                        event.motionX((double)0.0F);
                        event.motionZ((double)0.0F);
                        return;
                     }

                     PlayerUtils.setSpeed((double)((Number)this.horizontalSpeed.getValue()).floatValue(), event);
                     break;
                  case "Bounce":
                     if (!mc.field_1724.method_6128()) {
                        return;
                     }

                     if (!event.getType().equals(class_1313.field_6308)) {
                        return;
                     }

                     if ((Boolean)this.boostBounce.getValue()) {
                        this.doBoost(event);
                     }

                     this.lastPos = mc.field_1724.method_19538();
                     break;
                  case "Control":
                     if (!mc.field_1724.method_6128()) {
                        return;
                     }

                     if ((Boolean)this.boostControl.getValue()) {
                        this.doBoost(event);
                     }
                     break;
                  case "Accel":
                     if (!mc.field_1724.method_6128()) {
                        return;
                     }

                     this.doBoost(event);
                     break;
                  case "Factor":
                     if (!mc.field_1724.method_6128()) {
                        PacketManager.INSTANCE.sendPacket(new class_2848(mc.field_1724, class_2849.field_12982));
                     }

                     if (mc.field_1724.field_5976) {
                        this.rSpeed = true;
                     }

                     if ((Boolean)this.accelerate.getValue()) {
                        if (this.rSpeed) {
                           this.curSpeed = (double)1.0F;
                           this.rSpeed = false;
                        }

                        if (this.curSpeed < (double)((Number)this.factor.getValue()).floatValue()) {
                           this.curSpeed += 0.1;
                        }

                        if (this.curSpeed - 0.1 > (double)((Number)this.factor.getValue()).floatValue()) {
                           this.curSpeed -= 0.1;
                        }
                     } else {
                        this.curSpeed = (double)((Number)this.factor.getValue()).floatValue();
                     }

                     if (mc.field_1690.field_1903.method_1434()) {
                        event.motionY((double)((Number)this.upFactor.getValue()).floatValue());
                     } else if (mc.field_1690.field_1832.method_1434()) {
                        event.motionY((double)(-((Number)this.downFactor.getValue()).floatValue()));
                     } else if (!((String)this.strictMode.getValue()).equals("Normal")) {
                        if (((String)this.strictMode.getValue()).equals("Glide")) {
                           event.motionY((double)-1.0E-5F);
                        } else {
                           event.motionY((double)0.0F);
                        }
                     } else if (mc.field_1724.field_6012 % 32 != 0 || this.rSpeed || !(Math.abs(event.getX()) >= 0.05) && !(Math.abs(event.getZ()) >= 0.05)) {
                        event.motionY(-2.0E-4);
                     } else {
                        PlayerUtils.setMotionY(-2.0E-4);
                        event.setY(0.006200000000000001);
                     }

                     event.setX(event.getX() * (this.rSpeed ? (double)0.0F : this.curSpeed));
                     event.setZ(event.getZ() * (this.rSpeed ? (double)0.0F : this.curSpeed));
                     if ((Boolean)this.antiKick.getValue() && event.getX() == (double)0.0F && event.getZ() == (double)0.0F && !this.rSpeed) {
                        event.setX(Math.sin(Math.toRadians((double)(mc.field_1724.field_6012 % 360))) * 0.03);
                        event.setZ(Math.cos(Math.toRadians((double)(mc.field_1724.field_6012 % 360))) * 0.03);
                     }

                     event.setCancelled(true);
               }

            }
         }
      }
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Receive event) {
      if (!NullUtils.nullCheck()) {
         if (!PagonMod.isBaritonePaused()) {
            if (!LongJump.isGrimJumping()) {
               if (((String)this.mode.getValue()).equals("Factor") && event.getPacket() instanceof class_2708) {
                  this.rSpeed = true;
               }

            }
         }
      }
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Send event) {
      if (!NullUtils.nullCheck()) {
         if (!PagonMod.isBaritonePaused()) {
            if (!LongJump.isGrimJumping()) {
               ;
            }
         }
      }
   }

   public static boolean isPacketFlying() {
      return INSTANCE.isEnabled() && ((String)INSTANCE.mode.getValue()).equals("Factor");
   }

   public float getControlPitch() {
      if (PlayerUtils.isBoostedByFirework()) {
         if (mc.field_1690.field_1903.method_1434() && !mc.field_1690.field_1832.method_1434()) {
            return PlayerUtils.isMoving() ? -50.0F : -90.0F;
         } else if (mc.field_1690.field_1832.method_1434() && !mc.field_1690.field_1903.method_1434()) {
            return PlayerUtils.isMoving() ? 50.0F : 90.0F;
         } else {
            return 0.0F;
         }
      } else if (mc.field_1690.field_1903.method_1434() && !mc.field_1690.field_1832.method_1434()) {
         return -50.0F;
      } else {
         return mc.field_1690.field_1832.method_1434() && !mc.field_1690.field_1903.method_1434() ? 50.0F : 0.1F;
      }
   }

   public void onEnable() {
      super.onEnable();
      if (!NullUtils.nullCheck()) {
         this.rSpeed = false;
         this.curSpeed = (double)1.0F;
      }
   }

   @SubscribeEvent
   public void onTravel(TravelEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (!LongJump.isGrimJumping()) {
            if (!PagonMod.isBaritonePaused()) {
               if (mc.field_1724.method_31548().method_7372(2).method_7909().equals(class_1802.field_8833)) {
                  switch ((String)this.mode.getValue()) {
                     case "Bounce":
                     case "Control":
                        if (((String)this.mode.getValue()).equals("Control") && !mc.field_1724.method_6128()) {
                           return;
                        }

                        if (((String)this.mode.getValue()).equals("Control") && !PlayerUtils.isMoving() && (!mc.field_1690.field_1903.method_1434() || mc.field_1690.field_1832.method_1434()) && (!mc.field_1690.field_1832.method_1434() || mc.field_1690.field_1903.method_1434())) {
                           event.setCancelled(true);
                           return;
                        }

                        if (!mc.field_1724.method_6128()) {
                           PacketManager.INSTANCE.sendPacket(new class_2848(mc.field_1724, class_2849.field_12982));
                           mc.field_1724.method_23669();
                        }

                        if (mc.field_1724.method_24828()) {
                           PlayerUtils.clientJump();
                           if (this.lastPos != null && (Boolean)this.groundBoost.getValue()) {
                              double speedBps = mc.field_1724.method_19538().method_1020(this.lastPos).method_18805((double)20.0F, (double)0.0F, (double)20.0F).method_1033();
                              if (mc.field_1724.method_24828() && mc.field_1724.method_5624() && speedBps > (double)20.0F) {
                                 PlayerUtils.setMotionY((double)0.0F);
                              }
                           }
                        }
                     case "Creative":
                  }

               }
            }
         }
      }
   }

   public void doBoost(MoveEvent event) {
      float yaw = RotationUtils.getActualYaw();
      double x = this.GRIM_AIR_FRICTION * Math.cos(Math.toRadians((double)(yaw + 90.0F)));
      double z = this.GRIM_AIR_FRICTION * Math.sin(Math.toRadians((double)(yaw + 90.0F)));
      event.motionX(event.getX() + x);
      event.motionZ(event.getZ() + z);
   }

   public class_243 glide(float speed) {
      class_243 glide = this.getGlideVec(speed / 50.0F);
      class_243 motion = mc.field_1724.method_18798();
      return new class_243(motion.field_1352 + glide.field_1352, (double)0.0F, motion.field_1350 + glide.field_1350);
   }

   public class_243 getGlideVec(float speed) {
      float yaw = mc.field_1724.method_36454();
      double x = (double)speed * Math.cos(Math.toRadians((double)(yaw + 90.0F)));
      double z = (double)speed * Math.sin(Math.toRadians((double)(yaw + 90.0F)));
      return new class_243(x, (double)0.0F, z);
   }

   @SubscribeEvent
   public void onActionJump(LivingEvent.Jump event) {
      if (!NullUtils.nullCheck()) {
         if (!LongJump.isGrimJumping()) {
            if (!PagonMod.isBaritonePaused()) {
               if (((String)this.mode.getValue()).equals("Bounce")) {
                  event.setCancelled(true);
               }

            }
         }
      }
   }

   public void doObstaclePass() {
      if (!PagonMod.isBaritonePaused() && mc.field_1724.field_5976) {
         Baritone.INSTANCE.doObstaclePass();
      }

   }

   public void handlePage(String page) {
      this.horizontalSpeed.setActive(page.equals("Creative"));
      this.verticalSpeed.setActive(page.equals("Creative"));
      this.pitch.setActive(page.equals("Bounce"));
   }

   public String getHudInfo() {
      return this.mode.getValue();
   }

   public String getDescription() {
      return "ElytraFly: I just flew 300 blocks (like a butterfly this time) thanks to sn0wgod.cc";
   }
}
