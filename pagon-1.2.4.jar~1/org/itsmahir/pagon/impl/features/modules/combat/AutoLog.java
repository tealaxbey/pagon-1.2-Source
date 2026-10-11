package org.itsmahir.pagon.impl.features.modules.combat;

import java.time.Instant;
import java.util.BitSet;
import net.minecraft.class_1802;
import net.minecraft.class_2561;
import net.minecraft.class_2797;
import net.minecraft.class_742;
import net.minecraft.class_7469;
import net.minecraft.class_7635;
import net.minecraft.class_3515.class_7426;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.FriendManager;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.misc.FakePlayer;

public class AutoLog extends Module {
   Value<Boolean> onRender = (new ValueBuilder<Boolean>()).withDescriptor("Visual Range").withValue(false).register(this);
   Value<Boolean> illegalDisconnect = (new ValueBuilder<Boolean>()).withDescriptor("Illegal").withValue(false).register(this);
   Value<Boolean> logOnHealth = (new ValueBuilder<Boolean>()).withDescriptor("Lethal").withValue(false).register(this);
   Value<Number> logHealth;
   Value<Number> totemAmount;
   Value<Boolean> noTotems;

   public AutoLog() {
      super("AutoLog", Feature.Category.Combat);
      this.logHealth = (new ValueBuilder<Number>()).withDescriptor("Health").withValue(15).withRange(0, 36).withPlaces(1).withParent(this.logOnHealth).withParentEnabled(true).register(this);
      this.totemAmount = (new ValueBuilder<Number>()).withDescriptor("Totems").withValue(0).withRange(0, 5).withPlaces(0).register(this);
      this.noTotems = (new ValueBuilder<Boolean>()).withDescriptor("No Totems").withValue(false).register(this);
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if ((Boolean)this.onRender.getValue()) {
            class_742 player = (class_742)mc.field_1687.method_18456().stream().filter((p) -> this.checkEnemy(p)).findFirst().orElse((Object)null);
            if (player != null) {
               this.playerDisconnect("[AutoLog] %s came into render distance.", player.method_5477().getString());
               return;
            }
         }

         float health = mc.field_1724.method_6032() + mc.field_1724.method_6067();
         int totems = InventoryUtils.getItemCount(class_1802.field_8288);
         boolean b2 = (float)totems <= ((Number)this.totemAmount.getValue()).floatValue();
         if (health <= ((Number)this.logHealth.getValue()).floatValue()) {
            if (!(Boolean)this.logOnHealth.getValue()) {
               this.playerDisconnect("[AutoLog] logged out with %d hearts remaining.", (int)health);
               return;
            }

            if (b2) {
               this.playerDisconnect("[AutoLog] logged out with %d totems and %d hearts remaining.", totems, (int)health);
               return;
            }
         }

         if (b2 && (Boolean)this.noTotems.getValue()) {
            this.playerDisconnect("[AutoLog] logged out with %d totems remaining.", totems);
         }

      }
   }

   private void playerDisconnect(String disconnectReason, Object... args) {
      if ((Boolean)this.illegalDisconnect.getValue()) {
         PacketManager.INSTANCE.sendQuietPacket(new class_2797("§", Instant.now(), class_7426.method_43531(), (class_7469)null, new class_7635.class_7636(1, new BitSet(2))));
         this.toggle();
      } else if (mc.method_1562() == null) {
         mc.field_1687.method_8525();
         this.toggle();
      } else {
         disconnectReason = String.format(disconnectReason, args);
         mc.method_1562().method_48296().method_10747(class_2561.method_30163(disconnectReason));
         this.toggle();
      }
   }

   private boolean checkEnemy(class_742 player) {
      return player.method_5476() != null && !FriendManager.INSTANCE.isFriend(player) && !player.equals(FakePlayer.INSTANCE.fakePlayer);
   }

   public String getDescription() {
      return "AutoLog: Attempts disconnect when needed";
   }
}
