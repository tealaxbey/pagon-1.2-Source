package org.itsmahir.pagon.impl.features.modules.misc;

import com.mojang.authlib.GameProfile;
import java.awt.Color;
import java.util.UUID;
import net.minecraft.class_1268;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2663;
import net.minecraft.class_2664;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_745;
import net.minecraft.class_1297.class_5529;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.LivingEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PopManager;
import org.itsmahir.pagon.api.management.notification.NotificationManager;
import org.itsmahir.pagon.api.management.notification.types.TopNotification;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.world.CrystalUtil;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class FakePlayer extends Module {
   public static FakePlayer INSTANCE;
   Value<String> fakeName = (new ValueBuilder<String>()).withDescriptor("Ghost Name").withValue("PagonHater").register(this);
   public class_1657 fakePlayer;
   boolean crystalAvailable = false;

   public FakePlayer() {
      super("FakePlayer", Feature.Category.Misc);
      INSTANCE = this;
   }

   public void onEnable() {
      super.onEnable();
      if (!NullUtils.nullCheck()) {
         NotificationManager.INSTANCE.addNotification(new TopNotification("Spawned ghost " + (String)this.fakeName.getValue() + "!", 1000L, 200L, Color.GREEN));
         this.fakePlayer = new class_745(mc.field_1687, new GameProfile(UUID.fromString("5300a928-b781-440a-8581-19343b39d29d"), this.fakeName.getValue()));
         this.fakePlayer.method_5719(mc.field_1724);
         this.fakePlayer.field_6283 = mc.field_1724.method_43078();
         this.fakePlayer.field_6241 = mc.field_1724.method_5791();
         this.fakePlayer.method_6033(36.0F);
         this.fakePlayer.method_6122(class_1268.field_5808, mc.field_1724.method_6047().method_7972());
         this.fakePlayer.method_31548().method_5447(36, mc.field_1724.method_31548().method_5438(36).method_7972());
         this.fakePlayer.method_31548().method_5447(37, mc.field_1724.method_31548().method_5438(37).method_7972());
         this.fakePlayer.method_31548().method_5447(38, mc.field_1724.method_31548().method_5438(38).method_7972());
         this.fakePlayer.method_31548().method_5447(39, mc.field_1724.method_31548().method_5438(39).method_7972());
         mc.field_1687.method_53875(this.fakePlayer);
         this.fakePlayer.method_6092(new class_1293(class_1294.field_5924, 9999, 2));
         this.fakePlayer.method_6092(new class_1293(class_1294.field_5898, 9999, 4));
         this.fakePlayer.method_6092(new class_1293(class_1294.field_5907, 9999, 1));
      }
   }

   public void onDisable() {
      super.onDisable();
      if (!NullUtils.nullCheck()) {
         if (this.fakePlayer != null) {
            this.fakePlayer.method_5650(class_5529.field_26999);
            NotificationManager.INSTANCE.addNotification(new TopNotification("Removed ghost " + (String)this.fakeName.getValue() + "!", 1000L, 200L, new Color(255, 85, 85)));
         }

      }
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.ClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         if (this.fakePlayer != null) {
            if (this.fakePlayer.method_6079().method_7909() != class_1802.field_8288) {
               this.fakePlayer.method_6122(class_1268.field_5810, new class_1799(class_1802.field_8288));
            }

            if (this.fakePlayer.method_29504()) {
               (new PacketEvent.Receive(new class_2663(this.fakePlayer, (byte)3))).post();
               NotificationManager.INSTANCE.addNotification(new TopNotification("Ghost " + this.fakeName.getName() + " died!", 1000L, 200L, new Color(255, 85, 85)));
               this.toggle();
            }

         }
      }
   }

   @SubscribeEvent
   public void onAttack(LivingEvent.Attack event) {
      if (!NullUtils.nullCheck()) {
         if (this.fakePlayer != null) {
            if (event.getEntity() == this.fakePlayer && this.fakePlayer.field_6235 == 0) {
               mc.field_1687.method_43128(mc.field_1724, this.fakePlayer.method_23317(), this.fakePlayer.method_23318(), this.fakePlayer.method_23321(), class_3417.field_15115, class_3419.field_15248, 1.0F, 1.0F);
               if (mc.field_1724.field_6017 > 0.0F) {
                  mc.field_1687.method_43128(mc.field_1724, this.fakePlayer.method_23317(), this.fakePlayer.method_23318(), this.fakePlayer.method_23321(), class_3417.field_15016, class_3419.field_15248, 1.0F, 1.0F);
               }

               this.fakePlayer.method_48922(mc.field_1687.method_48963().method_48830());
               this.crystalAvailable = true;
               if ((double)mc.field_1724.method_7261(20.0F) >= 0.85) {
                  this.fakePlayer.method_6033(this.fakePlayer.method_6032() + this.fakePlayer.method_6067() - InventoryUtils.getHitDamage(mc.field_1724.method_6047(), this.fakePlayer));
               } else {
                  this.fakePlayer.method_6033(this.fakePlayer.method_6032() + this.fakePlayer.method_6067() - 1.0F);
               }

               if (this.fakePlayer.method_29504() && this.fakePlayer.method_6095(mc.field_1687.method_48963().method_48830())) {
                  this.fakePlayer.method_6033(10.0F);
                  PopManager.INSTANCE.onPop(this.fakePlayer);
                  (new class_2663(this.fakePlayer, (byte)35)).method_11471(mc.field_1724.field_3944);
               }
            }

         }
      }
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Receive event) {
      if (!NullUtils.nullCheck()) {
         if (this.fakePlayer != null) {
            class_2596 var3 = event.getPacket();
            if (var3 instanceof class_2664) {
               class_2664 explosion = (class_2664)var3;
               this.onExplosion(explosion);
            }

         }
      }
   }

   public void onExplosion(class_2664 explosion) {
      if (this.fakePlayer.field_6235 == 0 || this.crystalAvailable) {
         if (!this.crystalAvailable) {
            this.fakePlayer.method_48922(mc.field_1687.method_48963().method_48830());
         }

         this.fakePlayer.method_6033(this.fakePlayer.method_6032() + this.fakePlayer.method_6067() - CrystalUtil.calculateDamage(this.fakePlayer, new class_243(explosion.method_11475(), explosion.method_11477(), explosion.method_11478()), false, false));
         this.crystalAvailable = false;
         if (this.fakePlayer.method_29504() && this.fakePlayer.method_6095(mc.field_1687.method_48963().method_48830())) {
            PopManager.INSTANCE.onPop(this.fakePlayer);
            this.fakePlayer.method_6033(10.0F);
            (new class_2663(this.fakePlayer, (byte)35)).method_11471(mc.field_1724.field_3944);
         }
      }

   }

   public String getDescription() {
      return "FakePlayer: Spawns a fake player or ghost";
   }
}
