package org.itsmahir.pagon.impl.features.modules.movement;

import net.minecraft.class_1297;
import net.minecraft.class_1690;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2404;
import net.minecraft.class_259;
import net.minecraft.class_2680;
import net.minecraft.class_2828;
import net.minecraft.class_3532;
import net.minecraft.class_3612;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.event.events.world.CollisionBoxEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.mixin.accessor.IPlayerMoveC2SPacket;

public class Jesus extends Module {
   public static Jesus INSTANCE;
   Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Solid").withModes("Solid", "Bounce", "Dolphin", "Dynamic").register(this);
   Timer timer = new Timer();
   boolean grounded;
   boolean jumping;

   public Jesus() {
      super("Jesus", Feature.Category.Movement);
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.ClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         if (this.getMode().equals("Solid") && PlayerUtils.isInWater(mc.field_1724) && !mc.field_1724.method_5715()) {
            PlayerUtils.setMotionY(0.1);
            if (mc.field_1724.method_5854() != null && !(mc.field_1724.method_5854() instanceof class_1690)) {
               mc.field_1724.method_5854().method_18800(mc.field_1724.method_5854().method_18798().field_1352, 0.3, mc.field_1724.method_5854().method_18798().field_1350);
            }
         }

      }
   }

   @SubscribeEvent
   public void onPlayerPre(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (((String)this.mode.getValue()).equals("Dynamic") && !mc.field_1690.field_1903.method_1434()) {
            this.grounded = false;
            this.jumping = false;
         }

         if (this.getMode().equals("Bounce")) {
            this.doTrampoline();
         }

         if (this.getMode().equals("Dolphin") && PlayerUtils.isAboveWater(mc.field_1724) && !mc.field_1724.method_5715() && !mc.field_1690.field_1903.method_1434()) {
            PlayerUtils.setMotionY(0.1);
         }

      }
   }

   @SubscribeEvent
   public void onCollision(CollisionBoxEvent event) {
      if (!NullUtils.nullCheck()) {
         class_2680 state = event.getState();
         if (((String)this.mode.getValue()).equals("Solid") && (state.method_26204() == class_2246.field_10382 | state.method_26227().method_15772() == class_3612.field_15910 || state.method_26204() == class_2246.field_10164)) {
            event.setCancelled(true);
            event.setVoxelShape(class_259.method_1078(new class_238((double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, 0.99, (double)1.0F)));
         }

      }
   }

   private static boolean isAboveLand(class_1297 entity) {
      if (entity == null) {
         return false;
      } else {
         double y = entity.method_23318() - 0.01;

         for(int x = class_3532.method_15357(entity.method_23317()); x < class_3532.method_15384(entity.method_23317()); ++x) {
            for(int z = class_3532.method_15357(entity.method_23321()); z < class_3532.method_15384(entity.method_23321()); ++z) {
               class_2338 pos = new class_2338(x, class_3532.method_15357(y), z);
               if (mc.field_1687.method_8320(pos).method_26234(mc.field_1687, pos)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Send event) {
      if (!NullUtils.nullCheck()) {
         if (this.getMode().equals("Solid") && event.getPacket() instanceof class_2828 && PlayerUtils.isAboveWater(mc.field_1724, true) && !PlayerUtils.isInWater(mc.field_1724) && !isAboveLand(mc.field_1724)) {
            int ticks = mc.field_1724.field_6012 % 2;
            if (ticks == 0) {
               class_2828 packet = (class_2828)event.getPacket();
               IPlayerMoveC2SPacket inter = (IPlayerMoveC2SPacket)packet;
               inter.setY(inter.getY() + 0.02);
            }
         }

      }
   }

   private void doTrampoline() {
      if (!mc.field_1724.method_5715()) {
         int minY = class_3532.method_15357(mc.field_1724.method_5829().field_1322 - 0.2);
         boolean inLiquid = checkIfBlockInBB(class_2404.class, minY) != null;
         if (inLiquid && !mc.field_1724.method_5715()) {
            mc.field_1724.method_24830(false);
         }

         class_2248 block = mc.field_1687.method_8320(new class_2338((int)Math.floor(mc.field_1724.method_23317()), (int)Math.floor(mc.field_1724.method_23318()), (int)Math.floor(mc.field_1724.method_23321()))).method_26204();
         if (this.jumping && !mc.field_1724.method_31549().field_7479 && !mc.field_1724.method_5869()) {
            if (mc.field_1724.method_18798().field_1351 < -0.3 || mc.field_1724.method_24828() || mc.field_1724.method_21754()) {
               this.jumping = false;
               return;
            }

            PlayerUtils.setMotionY(mc.field_1724.method_18798().field_1351 / (double)0.98F + 0.08);
            PlayerUtils.setMotionY(mc.field_1724.method_18798().field_1351 - 0.03120000000005);
         }

         if (mc.field_1724.method_5869() || mc.field_1724.method_5771()) {
            PlayerUtils.setMotionY(0.1);
         }

         if (!mc.field_1724.method_5771() && !mc.field_1724.method_5869() && block instanceof class_2404 && mc.field_1724.method_18798().field_1351 < 0.2) {
            PlayerUtils.setMotionY((double)0.5F);
            this.jumping = true;
         }

      }
   }

   public static class_2680 checkIfBlockInBB(Class<? extends class_2248> blockClass, int minY) {
      for(int iX = class_3532.method_15357(mc.field_1724.method_5829().field_1323); iX < class_3532.method_15384(mc.field_1724.method_5829().field_1320); ++iX) {
         for(int iZ = class_3532.method_15357(mc.field_1724.method_5829().field_1321); iZ < class_3532.method_15384(mc.field_1724.method_5829().field_1324); ++iZ) {
            class_2680 state = mc.field_1687.method_8320(new class_2338(iX, minY, iZ));
            if (blockClass.isInstance(state.method_26204())) {
               return state;
            }
         }
      }

      return null;
   }

   public String getMode() {
      if (!NullUtils.nullCheck() && ((String)this.mode.getValue()).equals("Dynamic")) {
         return mc.field_1690.field_1903.method_1434() ? "Bounce" : "Solid";
      } else {
         return this.mode.getValue();
      }
   }

   public String getHudInfo() {
      return this.getMode();
   }

   public String getDescription() {
      return "Jesus: Walk on water like jesus";
   }
}
