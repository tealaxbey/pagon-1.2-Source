package org.itsmahir.pagon.impl.features.modules.movement;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import net.minecraft.class_1268;
import net.minecraft.class_2596;
import net.minecraft.class_2813;
import net.minecraft.class_2828;
import net.minecraft.class_2848;
import net.minecraft.class_2868;
import net.minecraft.class_2886;
import net.minecraft.class_304;
import net.minecraft.class_3675;
import net.minecraft.class_465;
import net.minecraft.class_485;
import net.minecraft.class_7204;
import net.minecraft.class_2848.class_2849;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.management.RotationManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.render.Freecam;
import org.itsmahir.pagon.impl.gui.ClickGui;

public class NoSlow extends Module {
   public boolean cancelDisabler = false;
   public Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Vanilla").withModes("Vanilla", "GrimV2", "GrimV3", "Strict").register(this);
   public Value<Boolean> items = (new ValueBuilder<Boolean>()).withDescriptor("Items").withValue(true).register(this);
   public Value<Boolean> guiMove = (new ValueBuilder<Boolean>()).withDescriptor("Inv Move").withValue(true).register(this);
   public Value<Boolean> webs = (new ValueBuilder<Boolean>()).withDescriptor("Webs").withValue(true).register(this);
   public Value<Boolean> crawling = (new ValueBuilder<Boolean>()).withDescriptor("Crawling").withValue(false).register(this);
   public Value<Boolean> sneak = (new ValueBuilder<Boolean>()).withDescriptor("Sneaking").withValue(false).register(this);
   public static NoSlow INSTANCE;
   private final Queue<class_2596<?>> packets = new LinkedBlockingQueue();

   public NoSlow() {
      super("NoSlow", Feature.Category.Movement);
      INSTANCE = this;
   }

   @SubscribeEvent(4)
   private void onTick(TickEvent.GameRenderTick event) {
      if (!NullUtils.nullCheck()) {
         if ((Boolean)this.guiMove.getValue()) {
            if (mc.field_1755 != null && this.canInvMove()) {
               for(class_304 k : new class_304[]{mc.field_1690.field_1894, mc.field_1690.field_1881, mc.field_1690.field_1913, mc.field_1690.field_1849, mc.field_1690.field_1903, mc.field_1690.field_1867}) {
                  k.method_23481(class_3675.method_15987(mc.method_22683().method_4490(), class_3675.method_15981(k.method_1428()).method_1444()));
               }

               if (class_3675.method_15987(mc.method_22683().method_4490(), 264)) {
                  mc.field_1724.method_36457(mc.field_1724.method_36455() + 5.0F);
               }

               if (class_3675.method_15987(mc.method_22683().method_4490(), 265)) {
                  mc.field_1724.method_36457(mc.field_1724.method_36455() - 5.0F);
               }

               if (class_3675.method_15987(mc.method_22683().method_4490(), 262)) {
                  mc.field_1724.method_36456(mc.field_1724.method_36454() + 5.0F);
               }

               if (class_3675.method_15987(mc.method_22683().method_4490(), 263)) {
                  mc.field_1724.method_36456(mc.field_1724.method_36454() - 5.0F);
               }

               if (mc.field_1724.method_36455() > 90.0F) {
                  mc.field_1724.method_36456(90.0F);
               }

               if (mc.field_1724.method_36455() < -90.0F) {
                  mc.field_1724.method_36456(-90.0F);
               }
            }

         }
      }
   }

   @SubscribeEvent
   public void onPlayerTick(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         switch ((String)this.mode.getValue()) {
            case "GrimV2":
               if (mc.field_1724.method_6115() && !mc.field_1724.method_3144() && !mc.field_1724.method_6128() && !mc.field_1724.method_5715() && mc.field_1724.method_6058() == class_1268.field_5808) {
                  PacketManager.INSTANCE.sendPacket((class_7204)((id) -> new class_2886(class_1268.field_5810, id, RotationManager.INSTANCE.getServerYaw(), RotationManager.INSTANCE.getServerPitch())));
               }
            case "GrimV3":
            case "Strict":
            default:
         }
      }
   }

   public void release() {
      for(class_2596<?> p : this.packets) {
         PacketManager.INSTANCE.sendQuietPacket(p);
      }

      this.packets.clear();
   }

   public boolean canNoSlow() {
      if (!this.isEnabled()) {
         return false;
      } else if (!(Boolean)this.items.getValue()) {
         return false;
      } else if (((String)this.mode.getValue()).equals("GrimV2") && mc.field_1724.method_6058() == class_1268.field_5810) {
         return false;
      } else if (((String)this.mode.getValue()).equals("GrimV3")) {
         if (!mc.field_1724.method_6115()) {
            return false;
         } else if (!mc.field_1724.method_5715() && !mc.field_1724.method_3144()) {
            int timeLeft = mc.field_1724.method_6014();
            int useTime = mc.field_1724.method_6048();
            if (timeLeft < 5) {
               return true;
            } else {
               return useTime > 1 && useTime % 2 != 0;
            }
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Send event) {
      if (!NullUtils.nullCheck()) {
         if (!this.cancelDisabler) {
            switch ((String)this.mode.getValue()) {
               case "Strict":
                  if ((Boolean)this.items.getValue()) {
                     class_2596 var7 = event.getPacket();
                     if (var7 instanceof class_2828) {
                        class_2828 packet = (class_2828)var7;
                        if (mc.field_1724.method_6115() && packet.method_36171()) {
                           mc.field_1724.field_3944.method_52787(new class_2868(mc.field_1724.method_31548().field_7545));
                        }
                        break;
                     }
                  }

                  if ((Boolean)this.guiMove.getValue() && event.getPacket() instanceof class_2813 && PlayerUtils.isMoving()) {
                     this.doStrictPre();
                  }
                  break;
               case "GrimV3":
                  if ((Boolean)this.items.getValue()) {
                     class_2596 var5 = event.getPacket();
                     if (var5 instanceof class_2828) {
                        class_2828 packet = (class_2828)var5;
                        if (mc.field_1724.method_6115() && packet.method_36171() && mc.field_1724.method_6048() > 2) {
                           mc.field_1724.field_3944.method_52787(new class_2868(mc.field_1724.method_31548().field_7545));
                        }
                     }
                  }
            }

         }
      }
   }

   public boolean doStrictPre() {
      if (mc.field_1724.method_5715()) {
         PacketManager.INSTANCE.sendPacket(new class_2848(mc.field_1724, class_2849.field_12984));
      }

      if (mc.field_1724.method_5624()) {
         PacketManager.INSTANCE.sendPacket(new class_2848(mc.field_1724, class_2849.field_12985));
      }

      if (mc.field_1724.method_24828() && !mc.field_1690.field_1903.method_1434() && !mc.field_1687.method_20812(mc.field_1724, mc.field_1724.method_5829().method_989((double)0.0F, 0.0656, (double)0.0F)).iterator().hasNext()) {
         PacketManager.INSTANCE.sendPacket(new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 0.0656, mc.field_1724.method_23321(), false));
         return true;
      } else {
         return false;
      }
   }

   public void doStrictPost() {
      if (mc.field_1724.method_5715()) {
         PacketManager.INSTANCE.sendPacket(new class_2848(mc.field_1724, class_2849.field_12979));
      }

      if (mc.field_1724.method_5624()) {
         PacketManager.INSTANCE.sendPacket(new class_2848(mc.field_1724, class_2849.field_12981));
      }

   }

   @SubscribeEvent
   public void onPacketPost(PacketEvent.SendPost event) {
      if (!NullUtils.nullCheck()) {
         if (!this.cancelDisabler) {
            switch ((String)this.mode.getValue()) {
               case "Strict":
                  if ((Boolean)this.guiMove.getValue() && event.getPacket() instanceof class_2813 && PlayerUtils.isMoving()) {
                     this.doStrictPost();
                  }
               case "GrimV3":
               default:
            }
         }
      }
   }

   public boolean canInvMove() {
      if (Freecam.INSTANCE.isEnabled()) {
         return false;
      } else if (mc.field_1755 instanceof class_485) {
         return true;
      } else if (mc.field_1755 instanceof class_465) {
         return true;
      } else {
         return mc.field_1755 instanceof ClickGui;
      }
   }

   public String getHudInfo() {
      return this.mode.getValue();
   }

   public String getDescription() {
      return "NoSlow: Prevents you from getting slowed from various actions";
   }
}
