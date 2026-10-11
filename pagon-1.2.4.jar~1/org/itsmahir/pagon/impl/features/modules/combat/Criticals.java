package org.itsmahir.pagon.impl.features.modules.combat;

import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2664;
import net.minecraft.class_2743;
import net.minecraft.class_2824;
import net.minecraft.class_2828;
import net.minecraft.class_2848;
import net.minecraft.class_2848.class_2849;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.LivingEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.event.events.network.ServerEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.utils.players.Utils32k;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.utils.world.PacketUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class Criticals extends Module {
   Timer timer = new Timer();
   public Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Packet").withModes("Packet", "Strict", "Jump", "MiniJump", "Grim", "GrimV2", "GrimCC").register(this);
   Value<Number> delay = (new ValueBuilder<Number>()).withDescriptor("Delay").withValue(250).withRange(250, 1500).withAction((set) -> this.timer.setDelay(((Number)set.getValue()).longValue())).register(this);
   Value<Boolean> onlyKa = (new ValueBuilder<Boolean>()).withDescriptor("Only Aura").withValue(false).register(this);
   boolean resetSprint = false;
   boolean groundMe = false;
   boolean canGrimCrit = false;

   public Criticals() {
      super("Criticals", Feature.Category.Combat);
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Send event) {
      if (!NullUtils.nullCheck()) {
         class_2596 var3 = event.getPacket();
         if (var3 instanceof class_2824) {
            class_2824 packet = (class_2824)var3;
            if (!this.timer.isPassed()) {
               return;
            }

            if (PacketUtils.getInteractType(packet) != PacketUtils.InteractType.ATTACK) {
               return;
            }

            class_1297 entity = PacketUtils.getEntity(packet);
            if (entity == null || entity instanceof class_1511) {
               return;
            }

            if (Utils32k.isHolding32k(mc.field_1724)) {
               return;
            }

            if ((Boolean)this.onlyKa.getValue() && !Aura.INSTANCE.isEnabled()) {
               return;
            }

            if (!mc.field_1724.method_31549().field_7479 && !mc.field_1724.method_5771() && !mc.field_1724.method_5869()) {
               if (((String)this.mode.getValue()).equals("GrimCC") && !this.isHeadBlocked() && !mc.field_1724.method_20448()) {
                  return;
               }

               if (((String)this.mode.getValue()).equals("GrimV2") && mc.field_1724.method_24828()) {
                  return;
               }

               if (!mc.field_1724.method_24828()) {
                  return;
               }

               if (mc.field_1724.method_5624()) {
                  PacketManager.INSTANCE.sendPacket(new class_2848(mc.field_1724, class_2849.field_12985));
                  this.resetSprint = true;
               }

               switch ((String)this.mode.getValue()) {
                  case "Strict":
                     PacketManager.INSTANCE.sendPacket(new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 1.1E-7, mc.field_1724.method_23321(), false));
                     PacketManager.INSTANCE.sendPacket(new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 1.0E-8, mc.field_1724.method_23321(), false));
                     this.groundMe = true;
                     break;
                  case "Packet":
                     PacketManager.INSTANCE.sendPacket(new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() + (double)0.0625F, mc.field_1724.method_23321(), false));
                     PacketManager.INSTANCE.sendPacket(new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321(), false));
                     break;
                  case "Jump":
                     if (mc.field_1724.method_24828()) {
                        mc.field_1724.method_6043();
                     }
                     break;
                  case "MiniJump":
                     if (mc.field_1724.method_24828()) {
                        PlayerUtils.setMotionY(0.3425);
                     }
                     break;
                  case "Grim":
                     PacketManager.INSTANCE.sendPacket(new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() + (double)0.0625F, mc.field_1724.method_23321(), false));
                     PacketManager.INSTANCE.sendPacket(new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 0.04535, mc.field_1724.method_23321(), false));
                     break;
                  case "GrimV2":
                     PacketManager.INSTANCE.sendPacket(new class_2828.class_2830(mc.field_1724.method_23317(), mc.field_1724.method_23318() - (double)1.0E-4F, mc.field_1724.method_23321(), RotationUtils.getActualYaw(), RotationUtils.getActualPitch(), false));
                     break;
                  case "GrimCC":
                     PacketManager.INSTANCE.sendPacket(new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() + (double)0.0625F, mc.field_1724.method_23321(), false));
                     PacketManager.INSTANCE.sendPacket(new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 0.0625013579, mc.field_1724.method_23321(), false));
                     PacketManager.INSTANCE.sendPacket(new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 1.3579E-6, mc.field_1724.method_23321(), false));
               }

               this.timer.resetDelay();
            }
         }

      }
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Receive event) {
      if (!NullUtils.nullCheck()) {
         class_2596 explosionVec = event.getPacket();
         if (explosionVec instanceof class_2664) {
            class_2664 packet = (class_2664)explosionVec;
            class_243 explosionVec = new class_243(packet.method_11475(), packet.method_11477(), packet.method_11478());
            if (explosionVec.method_1022(mc.field_1724.method_19538()) < (double)6.0F) {
               this.canGrimCrit = true;
            }
         }

         explosionVec = event.getPacket();
         if (explosionVec instanceof class_2743) {
            class_2743 packet = (class_2743)explosionVec;
            if (packet.method_11818() == mc.field_1724.method_5628()) {
               this.canGrimCrit = true;
            }
         }

      }
   }

   @SubscribeEvent
   public void onServerLeft(ServerEvent.ServerLeft event) {
      this.canGrimCrit = false;
   }

   @SubscribeEvent
   public void onDeathEvent(LivingEvent.Death event) {
      if (mc.field_1724 == event.getEntity()) {
         this.canGrimCrit = false;
      }

   }

   public boolean isClipped() {
      class_238 bb = mc.field_1724.method_5829();

      for(class_2338 pos : BlockUtils.getAllInBox(bb.method_1002((double)0.0F, 1.2, (double)0.0F).method_989((double)0.0F, (double)1.0F, (double)0.0F).method_1009(0.01, (double)0.0F, 0.01))) {
         if (mc.field_1687.method_8320(pos).method_51366()) {
            return true;
         }
      }

      return false;
   }

   public boolean isHeadBlocked() {
      class_238 bb = mc.field_1724.method_5829();

      for(class_2338 pos : BlockUtils.getAllInBox(bb.method_1002((double)0.0F, 1.2, (double)0.0F).method_989((double)0.0F, (double)1.0F, (double)0.0F))) {
         if (mc.field_1687.method_8320(pos).method_51366()) {
            return true;
         }
      }

      return false;
   }

   public boolean isBlockAbove() {
      return !BlockUtils.isInBlock(mc.field_1724.method_5829().method_989((double)0.0F, (double)1.0F, (double)0.0F), true).isEmpty();
   }

   @SubscribeEvent
   public void postSendPacket(PacketEvent.SendPost event) {
      if (!NullUtils.nullCheck()) {
         if (event.getPacket() instanceof class_2824) {
            if (this.groundMe) {
               PacketManager.INSTANCE.sendPacket(new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321(), true));
               this.groundMe = false;
            }

            if (this.resetSprint) {
               PacketManager.INSTANCE.sendPacket(new class_2848(mc.field_1724, class_2849.field_12985));
               this.resetSprint = false;
            }
         }

      }
   }

   public String getHudInfo() {
      return this.mode.getValue();
   }

   public String getDescription() {
      return "Performs critical attacks when you hit your target";
   }
}
