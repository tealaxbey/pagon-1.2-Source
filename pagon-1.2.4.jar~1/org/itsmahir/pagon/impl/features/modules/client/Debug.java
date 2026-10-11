package org.itsmahir.pagon.impl.features.modules.client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import net.minecraft.class_124;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_746;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.LivingEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.move.LookEvent;
import org.itsmahir.pagon.api.event.events.move.MoveEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.SavableManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.combat.SemiAutoMace;
import org.itsmahir.pagon.impl.features.modules.movement.FakeFly;

public class Debug extends Module {
   public static Debug INSTANCE;
   private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
   private BufferedWriter writer;
   private File logFileHandle;
   private class_243 lastPos;
   private float lastYaw;
   private float lastPitch;
   private boolean lastOnGround;
   private boolean lastFallFlying;
   private boolean lastElytraEquipped;
   private boolean lastMoving;
   private boolean lastJumpPressed;
   private boolean lastSneakPressed;
   private int tickCounter;
   private double totalVerticalAscended;
   private int takeoffCount;
   private int landingCount;
   private int attackCount;
   private int rotationChangeCount;
   public Value<Boolean> logChat = (new ValueBuilder<Boolean>()).withDescriptor("LogChat").withValue(false).register(this);
   public Value<Boolean> logFile = (new ValueBuilder<Boolean>()).withDescriptor("LogFile").withValue(true).register(this);
   public Value<Boolean> logRotation = (new ValueBuilder<Boolean>()).withDescriptor("LogRotation").withValue(true).register(this);
   public Value<Boolean> logMovement = (new ValueBuilder<Boolean>()).withDescriptor("LogMovement").withValue(true).register(this);
   public Value<Boolean> logAttacks = (new ValueBuilder<Boolean>()).withDescriptor("LogAttacks").withValue(true).register(this);
   public Value<Boolean> logSummary = (new ValueBuilder<Boolean>()).withDescriptor("LogSummary").withValue(true).register(this);
   public Value<Number> logInterval = (new ValueBuilder<Number>()).withDescriptor("LogInterval").withValue(1).withRange(1, 100).withPlaces(0).register(this);

   public Debug() {
      super("Debug", Feature.Category.Client);
      INSTANCE = this;
   }

   public void onEnable() {
      super.onEnable();
      this.tickCounter = 0;
      this.totalVerticalAscended = (double)0.0F;
      this.takeoffCount = 0;
      this.landingCount = 0;
      this.attackCount = 0;
      this.rotationChangeCount = 0;
      this.lastPos = null;
      this.lastYaw = this.lastPitch = Float.NaN;
      this.lastOnGround = false;
      this.lastFallFlying = false;
      this.lastElytraEquipped = false;
      this.lastMoving = false;
      this.lastJumpPressed = this.lastSneakPressed = false;
      if ((Boolean)this.logFile.getValue()) {
         this.openLogFile();
      }

      this.log("Debug basladi: FakeFly + SemiAutoMace monitor aktif");
   }

   public void onDisable() {
      super.onDisable();
      if ((Boolean)this.logSummary.getValue()) {
         this.log("=== DEBUG OZETI ===");
         this.log("Toplam tick: " + this.tickCounter);
         this.log("Kalkis sayisi: " + this.takeoffCount);
         this.log("Inis sayisi: " + this.landingCount);
         this.log("Saldiri sayisi: " + this.attackCount);
         this.log("Rotasyon degisimi: " + this.rotationChangeCount);
         Object[] var10002 = new Object[]{this.totalVerticalAscended};
         this.log("Toplam dikey yukselis: " + String.format("%.2f", var10002));
         this.log("=== DEBUG SONU ===");
      }

      this.closeLogFile();
      this.lastPos = null;
   }

   @SubscribeEvent
   public void onTick(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (FakeFly.INSTANCE.isEnabled() || SemiAutoMace.INSTANCE.isEnabled()) {
            ++this.tickCounter;
            int interval = ((Number)this.logInterval.getValue()).intValue();
            if (this.tickCounter % interval == 0) {
               boolean ff = FakeFly.INSTANCE.isEnabled();
               boolean sam = SemiAutoMace.INSTANCE.isEnabled();
               class_243 pos = mc.field_1724.method_19538();
               class_243 vel = mc.field_1724.method_18798();
               float yaw = mc.field_1724.method_36454();
               float pitch = mc.field_1724.method_36455();
               boolean onGround = mc.field_1724.method_24828();
               boolean fallFlying = mc.field_1724.method_6128();
               boolean elytraEquipped = PlayerUtils.isElytraEquipped();
               boolean moving = PlayerUtils.isMoving();
               boolean jumpPressed = mc.field_1690.field_1903.method_1434();
               boolean sneakPressed = mc.field_1690.field_1832.method_1434();
               StringBuilder sb = new StringBuilder();
               sb.append("T[").append(this.tickCounter).append("] ");
               sb.append("FF:").append(ff ? "1" : "0").append(" ");
               sb.append("SAM:").append(sam ? "1" : "0").append(" ");
               sb.append("POS:").append(String.format("%.2f", pos.field_1352)).append("/").append(String.format("%.2f", pos.field_1351)).append("/").append(String.format("%.2f", pos.field_1350)).append(" ");
               sb.append("VEL:").append(String.format("%.3f", vel.field_1352)).append("/").append(String.format("%.3f", vel.field_1351)).append("/").append(String.format("%.3f", vel.field_1350)).append(" ");
               sb.append("YAW:").append(String.format("%.1f", yaw)).append(" ");
               sb.append("PITCH:").append(String.format("%.1f", pitch)).append(" ");
               sb.append("GRD:").append(onGround ? "1" : "0").append(" ");
               sb.append("FLY:").append(fallFlying ? "1" : "0").append(" ");
               sb.append("ELYT:").append(elytraEquipped ? "1" : "0").append(" ");
               sb.append("MOV:").append(moving ? "1" : "0").append(" ");
               sb.append("JMP:").append(jumpPressed ? "1" : "0").append(" ");
               sb.append("SNK:").append(sneakPressed ? "1" : "0");
               if ((Boolean)this.logMovement.getValue()) {
                  if (this.lastPos != null) {
                     double dy = pos.field_1351 - this.lastPos.field_1351;
                     if (dy > (double)0.0F) {
                        this.totalVerticalAscended += dy;
                     }

                     double dx = pos.field_1352 - this.lastPos.field_1352;
                     double dz = pos.field_1350 - this.lastPos.field_1350;
                     double horizSpeed = Math.sqrt(dx * dx + dz * dz);
                     sb.append(" SPD:").append(String.format("%.3f", horizSpeed));
                  }

                  if (!this.lastFallFlying && fallFlying) {
                     ++this.takeoffCount;
                     sb.append(" [KALKIS-" + this.takeoffCount + "]");
                  }

                  if (!this.lastOnGround && onGround) {
                     ++this.landingCount;
                     sb.append(" [INIS-" + this.landingCount + "]");
                  }
               }

               if ((Boolean)this.logRotation.getValue() && !Float.isNaN(this.lastYaw) && !Float.isNaN(this.lastPitch)) {
                  float yawDiff = Math.abs(yaw - this.lastYaw);
                  float pitchDiff = Math.abs(pitch - this.lastPitch);
                  if (yawDiff > 5.0F || pitchDiff > 5.0F) {
                     ++this.rotationChangeCount;
                     sb.append(" ROT:").append(String.format("%.1f/%.1f", yawDiff, pitchDiff));
                  }
               }

               if (sam) {
                  class_1657 target = this.findSAMTarget();
                  if (target != null) {
                     double dist = mc.field_1724.method_5858(target);
                     sb.append(" TGT:").append(target.method_5477().getString());
                     sb.append(" DIST:").append(String.format("%.2f", Math.sqrt(dist)));
                  }
               }

               if ((Boolean)this.logAttacks.getValue() && sam) {
                  double range = (double)4.5F;
                  class_1657 target = this.findSAMTarget();
                  if (target != null && mc.field_1724.method_5858(target) <= range * range) {
                     ++this.attackCount;
                     sb.append(" [SALDIRI-" + this.attackCount + "]");
                  }
               }

               this.lastPos = pos;
               this.lastYaw = yaw;
               this.lastPitch = pitch;
               this.lastOnGround = onGround;
               this.lastFallFlying = fallFlying;
               this.lastElytraEquipped = elytraEquipped;
               this.lastMoving = moving;
               this.lastJumpPressed = jumpPressed;
               this.lastSneakPressed = sneakPressed;
               this.log(sb.toString());
            }
         }
      }
   }

   @SubscribeEvent
   public void onMove(MoveEvent event) {
      if (!NullUtils.nullCheck() && this.isEnabled()) {
         if (FakeFly.INSTANCE.isEnabled() || SemiAutoMace.INSTANCE.isEnabled()) {
            if ((Boolean)this.logMovement.getValue()) {
               class_243 movement = event.getMovement();
               String msg = String.format("  MOVE: type=%s x=%.4f y=%.4f z=%.4f", event.getType(), movement.field_1352, movement.field_1351, movement.field_1350);
               this.log(msg);
            }

         }
      }
   }

   @SubscribeEvent
   public void onJump(LivingEvent.Jump event) {
      if (!NullUtils.nullCheck() && this.isEnabled()) {
         if (FakeFly.INSTANCE.isEnabled()) {
            Object[] var10002 = new Object[]{event.getYaw()};
            this.log("  JUMP: yaw=" + String.format("%.1f", var10002));
         }
      }
   }

   @SubscribeEvent
   public void onRotationSet(LookEvent.RotationSetEvent event) {
      if (!NullUtils.nullCheck() && this.isEnabled()) {
         if (FakeFly.INSTANCE.isEnabled() || SemiAutoMace.INSTANCE.isEnabled()) {
            if ((Boolean)this.logRotation.getValue()) {
               float yaw = event.getYaw();
               float pitch = event.getPitch();
               String var10001 = String.format("%.2f", yaw);
               this.log("  ROT_SET: yaw=" + var10001 + " pitch=" + String.format("%.2f", pitch));
            }

         }
      }
   }

   @SubscribeEvent
   public void onAttack(LivingEvent.Attack event) {
      if (!NullUtils.nullCheck() && this.isEnabled()) {
         if ((Boolean)this.logAttacks.getValue()) {
            String name = event.getEntity() != null ? event.getEntity().method_5477().getString() : "unknown";
            class_243 pos = mc.field_1724.method_19538();
            this.log("  MANUAL_ATK: target=" + name + " pos=" + String.format("%.2f/%.2f/%.2f", pos.field_1352, pos.field_1351, pos.field_1350));
         }
      }
   }

   private class_1657 findSAMTarget() {
      if (mc.field_1687 == null) {
         return null;
      } else {
         float range = 6.0F;
         class_1657 best = null;
         double bestDist = Double.MAX_VALUE;

         for(class_1657 p : mc.field_1687.method_18456()) {
            if (p != mc.field_1724 && p.method_5805() && !p.method_7325()) {
               double d = mc.field_1724.method_5858(p);
               if (d <= (double)(range * range) && d < bestDist) {
                  bestDist = d;
                  best = p;
               }
            }
         }

         return best;
      }
   }

   private void log(String message) {
      String timestamp = LocalDateTime.now().format(this.dtf);
      String line = "[" + timestamp + "] " + message;
      if ((Boolean)this.logChat.getValue() && mc.field_1724 != null) {
         class_746 var10000 = mc.field_1724;
         String var10001 = String.valueOf(class_124.field_1080);
         var10000.method_7353(class_2561.method_43470(var10001 + "[Debug] " + String.valueOf(class_124.field_1070) + message), false);
      }

      if ((Boolean)this.logFile.getValue() && this.writer != null) {
         try {
            this.writer.write(line);
            this.writer.newLine();
            this.writer.flush();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }

   }

   private void openLogFile() {
      try {
         LocalDateTime var10000 = LocalDateTime.now();
         String fileName = "debug_" + var10000.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".log";
         this.logFileHandle = SavableManager.createFileIfNotExists(fileName, "log");
         this.writer = SavableManager.makeWriter(this.logFileHandle, true);
         this.log("[DEBUG] Dosya olusturuldu: " + this.logFileHandle.getAbsolutePath());
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   private void closeLogFile() {
      try {
         if (this.writer != null) {
            this.writer.close();
            this.writer = null;
         }
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public String getHudInfo() {
      StringBuilder sb = new StringBuilder();
      if (FakeFly.INSTANCE.isEnabled()) {
         sb.append("FF ");
      }

      if (SemiAutoMace.INSTANCE.isEnabled()) {
         sb.append("SAM ");
      }

      if (this.tickCounter > 0) {
         sb.append("T:").append(this.tickCounter);
      }

      return sb.toString().trim();
   }

   public String getDescription() {
      return "gongo bunu aciyon oynoyon ondon sonro bobopiro oloyon";
   }
}
