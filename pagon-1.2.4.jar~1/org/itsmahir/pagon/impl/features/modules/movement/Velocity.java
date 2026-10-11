package org.itsmahir.pagon.impl.features.modules.movement;

import net.minecraft.class_124;
import net.minecraft.class_2350;
import net.minecraft.class_2596;
import net.minecraft.class_2664;
import net.minecraft.class_2708;
import net.minecraft.class_2743;
import net.minecraft.class_2828;
import net.minecraft.class_2846;
import net.minecraft.class_8143;
import net.minecraft.class_2846.class_2847;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.move.PushEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.management.RotationManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.mixin.accessor.IEntityVelocityUpdateS2CPacket;
import org.itsmahir.pagon.mixin.accessor.IExplosionS2CPacket;

public class Velocity extends Module {
   Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Velocity").withValue("Vanilla").withModes("Vanilla", "GrimV2", "Walls").register(this);
   public Value<Number> horizontal;
   public Value<Number> vertical;
   public Value<Boolean> push;
   public Value<Boolean> onlyInBlocks;
   public Value<Boolean> grim;
   public static Velocity INSTANCE;
   int timeout;
   boolean sendVelo;
   boolean flag;

   public Velocity() {
      super("Velocity", Feature.Category.Movement);
      this.horizontal = (new ValueBuilder<Number>()).withDescriptor("Horizontal").withValue(0).withRange(0.0F, 100.0F).withPlaces(1).withPage("Vanilla").withPageParent(this.mode).register(this);
      this.vertical = (new ValueBuilder<Number>()).withDescriptor("Vertical").withValue(0).withRange(0.0F, 100.0F).withPlaces(0).withPage("Vanilla").withPageParent(this.mode).register(this);
      this.push = (new ValueBuilder<Boolean>()).withDescriptor("Push").withValue(true).register(this);
      this.onlyInBlocks = (new ValueBuilder<Boolean>()).withDescriptor("Only Blocks").withValue(false).register(this);
      this.grim = (new ValueBuilder<Boolean>()).withDescriptor("Grim").withValue(false).register(this);
      this.timeout = 0;
      this.sendVelo = false;
      this.flag = false;
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Receive event) {
      if (!NullUtils.nullCheck()) {
         switch ((String)this.mode.getValue()) {
            case "Vanilla":
               if ((Boolean)this.onlyInBlocks.getValue() && !PhaseWalk.INSTANCE.isPhasing()) {
                  return;
               }

               if ((Boolean)this.grim.getValue() && event.getPacket() instanceof class_2708) {
                  this.flag = true;
               }

               class_2596 var15 = event.getPacket();
               if (var15 instanceof class_2743) {
                  class_2743 packet = (class_2743)var15;
                  if (packet.method_11818() != mc.field_1724.method_5628()) {
                     return;
                  }

                  if (this.flag && packet.method_11815() == (double)0.0F && packet.method_11819() == (double)0.0F && packet.method_11819() == (double)0.0F) {
                     this.flag = false;
                     return;
                  }

                  if (((Number)this.horizontal.getValue()).intValue() == 0 && ((Number)this.vertical.getValue()).intValue() == 0) {
                     PacketManager.INSTANCE.specialCaseCancel(packet);
                     return;
                  }

                  ((IEntityVelocityUpdateS2CPacket)packet).setVelocityX((int)(packet.method_11815() * (double)(((Number)this.horizontal.getValue()).floatValue() / 100.0F)));
                  ((IEntityVelocityUpdateS2CPacket)packet).setVelocityY((int)(packet.method_11816() * (double)(((Number)this.vertical.getValue()).floatValue() / 100.0F)));
                  ((IEntityVelocityUpdateS2CPacket)packet).setVelocityZ((int)(packet.method_11819() * (double)(((Number)this.horizontal.getValue()).floatValue() / 100.0F)));
               }

               var15 = event.getPacket();
               if (var15 instanceof class_2664) {
                  class_2664 packet = (class_2664)var15;
                  if (((Number)this.horizontal.getValue()).intValue() == 0 && ((Number)this.vertical.getValue()).intValue() == 0) {
                     PacketManager.INSTANCE.specialCaseCancel(packet);
                     return;
                  }

                  ((IExplosionS2CPacket)packet).setPlayerVelocityX(packet.method_11472() * (((Number)this.horizontal.getValue()).floatValue() / 100.0F));
                  ((IExplosionS2CPacket)packet).setPlayerVelocityY(packet.method_11473() * (((Number)this.vertical.getValue()).floatValue() / 100.0F));
                  ((IExplosionS2CPacket)packet).setPlayerVelocityZ(packet.method_11474() * (((Number)this.horizontal.getValue()).floatValue() / 100.0F));
               }
               break;
            case "GrimV2":
               if (event.isBundled()) {
                  return;
               }

               if ((Boolean)this.onlyInBlocks.getValue() && !PhaseWalk.INSTANCE.isPhasing()) {
                  return;
               }

               if (event.getPacket() instanceof class_2708) {
                  this.timeout = 25;
               } else {
                  --this.timeout;
                  if (this.timeout >= 0) {
                     return;
                  }

                  class_2596 var13 = event.getPacket();
                  if (var13 instanceof class_2743) {
                     class_2743 packet = (class_2743)var13;
                     if (packet.method_11818() != mc.field_1724.method_5628()) {
                        return;
                     }

                     this.sendVelo = true;
                     PacketManager.INSTANCE.specialCaseCancel(packet);
                  }

                  var13 = event.getPacket();
                  if (var13 instanceof class_2664) {
                     class_2664 packet = (class_2664)var13;
                     PacketManager.INSTANCE.specialCaseCancel(packet);
                     this.sendVelo = true;
                  }
               }
               break;
            case "Walls":
               if ((Boolean)this.onlyInBlocks.getValue() && !PhaseWalk.INSTANCE.isPhasing()) {
                  return;
               }

               if (event.isBundled()) {
                  return;
               }

               class_2596 var5 = event.getPacket();
               if (var5 instanceof class_8143) {
                  class_8143 packet = (class_8143)var5;
                  if (packet.comp_1267() == mc.field_1724.method_5628()) {
                     PacketManager.INSTANCE.sendPacket(new class_2828.class_5911(false));
                     PacketManager.INSTANCE.sendPacket(new class_2828.class_5911(true));
                     return;
                  }
               }

               if (event.getPacket() instanceof class_2708) {
                  this.timeout = 25;
               } else {
                  --this.timeout;
                  if (this.timeout >= 0) {
                     return;
                  }

                  class_2596 var6 = event.getPacket();
                  if (var6 instanceof class_2743) {
                     class_2743 packet = (class_2743)var6;
                     if (packet.method_11818() != mc.field_1724.method_5628()) {
                        return;
                     }

                     PacketManager.INSTANCE.specialCaseCancel(packet);
                     this.sendVelo = true;
                  }

                  var6 = event.getPacket();
                  if (var6 instanceof class_2664) {
                     class_2664 packet = (class_2664)var6;
                     this.sendVelo = true;
                     PacketManager.INSTANCE.specialCaseCancel(packet);
                  }
               }
         }

      }
   }

   @SubscribeEvent
   public void onEntityPushEvent(PushEvent.Entities event) {
      if ((Boolean)this.push.getValue()) {
         event.setCancelled(true);
      }

   }

   @SubscribeEvent
   public void onBlockPushEvent(PushEvent.Blocks event) {
      if ((Boolean)this.push.getValue()) {
         event.setCancelled(true);
      }

   }

   @SubscribeEvent
   private void onTick(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         this.flag = false;
         if (this.sendVelo) {
            mc.method_1562().method_52787(new class_2828.class_2830(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321(), RotationManager.INSTANCE.getServerYaw(), RotationManager.INSTANCE.getServerPitch(), mc.field_1724.method_24828()));
            if (mc.field_1724.method_20448()) {
               mc.method_1562().method_52787(new class_2846(class_2847.field_12973, mc.field_1724.method_24515(), class_2350.field_11033));
            } else {
               mc.method_1562().method_52787(new class_2846(class_2847.field_12973, mc.field_1724.method_24515().method_10084(), class_2350.field_11033));
            }

            this.sendVelo = false;
         }

      }
   }

   public String getHudInfo() {
      if (((String)this.mode.getValue()).equals("Vanilla")) {
         float var10000 = ((Number)this.horizontal.getValue()).floatValue();
         return "H" + var10000 + "%" + String.valueOf(class_124.field_1080) + "|" + String.valueOf(class_124.field_1068) + "V" + ((Number)this.vertical.getValue()).floatValue() + "%";
      } else {
         return "Grim";
      }
   }

   public String getDescription() {
      return "Velocity: take no knockback and become immovable from various things";
   }
}
