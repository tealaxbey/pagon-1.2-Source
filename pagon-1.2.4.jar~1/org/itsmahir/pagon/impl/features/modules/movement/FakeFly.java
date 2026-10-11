package org.itsmahir.pagon.impl.features.modules.movement;

import net.minecraft.class_1802;
import net.minecraft.class_2848;
import net.minecraft.class_9334;
import net.minecraft.class_2848.class_2849;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.LivingEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.move.MoveEvent;
import org.itsmahir.pagon.api.event.events.move.TravelEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.player.MiddleClick;
import org.itsmahir.pagon.nativep.FakeFlyNative;

public class FakeFly extends Module {
   public static FakeFly INSTANCE;
   Value<Boolean> pitch = (new ValueBuilder<Boolean>()).withDescriptor("Pitch").withValue(false).register(this);
   Value<Boolean> firework = (new ValueBuilder<Boolean>()).withDescriptor("Firework").withValue(false).register(this);
   private static TickEvent.PlayerTickEvent.Pre stashTick;
   private static TravelEvent.Pre stashTravel;
   private static LivingEvent.Jump stashJump;

   public FakeFly() {
      super("FakeFly", Feature.Category.Movement);
      INSTANCE = this;
   }

   public void onEnable() {
      super.onEnable();
      if (FakeFlyNative.isAvailable()) {
         FakeFlyNative.nativeOnEnable();
      }

   }

   public void onDisable() {
      super.onDisable();
      if (FakeFlyNative.isAvailable()) {
         FakeFlyNative.nativeOnDisable();
      }

   }

   @SubscribeEvent
   public void onPlayerTick(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck() && FakeFlyNative.isAvailable()) {
         stashTick = event;
         boolean eating = mc.field_1724.method_6115() && mc.field_1724.method_6030().method_57826(class_9334.field_50075);
         FakeFlyNative.nativeHandleTick(PlayerUtils.isElytraEquipped(), mc.field_1724.method_6128(), mc.field_1724.method_24828(), PlayerUtils.isMoving(), mc.field_1690.field_1903.method_1434(), mc.field_1690.field_1832.method_1434(), eating);
         stashTick = null;
      }
   }

   @SubscribeEvent
   public void onPlayerMove(MoveEvent event) {
      if (FakeFlyNative.isAvailable()) {
         FakeFlyNative.nativeHandleMove();
      }
   }

   @SubscribeEvent
   public void onTravel(TravelEvent.Pre event) {
      if (!NullUtils.nullCheck() && FakeFlyNative.isAvailable()) {
         stashTravel = event;
         boolean eating = mc.field_1724.method_6115() && mc.field_1724.method_6030().method_57826(class_9334.field_50075);
         FakeFlyNative.nativeHandleTravel(PlayerUtils.isElytraEquipped(), mc.field_1724.method_6128(), mc.field_1724.method_24828(), PlayerUtils.isMoving(), mc.field_1690.field_1903.method_1434(), mc.field_1690.field_1832.method_1434(), InventoryUtils.getInventoryItemSlot(class_1802.field_8833), (Boolean)this.firework.getValue(), eating);
         stashTravel = null;
      }
   }

   @SubscribeEvent
   public void onActionJump(LivingEvent.Jump event) {
      if (!NullUtils.nullCheck() && FakeFlyNative.isAvailable()) {
         stashJump = event;
         FakeFlyNative.nativeHandleJump();
         stashJump = null;
      }
   }

   public static void cbCancelTravel() {
      if (stashTravel != null) {
         stashTravel.setCancelled(true);
      }

   }

   public static void cbCancelJump() {
      if (stashJump != null) {
         stashJump.setCancelled(true);
      }

   }

   public static void cbDisarmElytra() {
      PlayerUtils.disEquipElytra();
   }

   public static void cbSwapElytra(int slot) {
      if (slot >= 0) {
         InventoryUtils.swapArmor(2, slot);
      }

   }

   public static void cbStartFlying() {
      PacketManager.INSTANCE.sendPacket(new class_2848(mc.field_1724, class_2849.field_12982));
      mc.field_1724.method_23669();
   }

   public static void cbFirework() {
      PlayerUtils.doFirework();
   }

   public static void cbMcFirework() {
      if (MiddleClick.INSTANCE != null) {
         MiddleClick.INSTANCE.doFirework();
         MiddleClick.INSTANCE.fireworkSchedule = false;
      }

   }

   public static void cbJump() {
      PlayerUtils.clientJump();
   }

   public static void cbRotate() {
      RotationUtils.setRotation(new float[]{PlayerUtils.getMoveYaw(mc.field_1724.method_36454()), ElytraFly.INSTANCE.getControlPitch()}, 99);
   }

   public static boolean isFakeFlying() {
      if (!INSTANCE.isEnabled()) {
         return false;
      } else {
         return InventoryUtils.getInventoryItemSlot(class_1802.field_8833) != -1;
      }
   }

   public String getHudInfo() {
      return "FakeFly";
   }

   public String getDescription() {
      return "Fake elytra flight for grim bypass";
   }
}
