package org.itsmahir.pagon.impl.features.modules.player;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1533;
import net.minecraft.class_2248;
import net.minecraft.class_2302;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2404;
import net.minecraft.class_2480;
import net.minecraft.class_2596;
import net.minecraft.class_2626;
import net.minecraft.class_2680;
import net.minecraft.class_2824;
import net.minecraft.class_2879;
import net.minecraft.class_2885;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.event.events.world.EntityEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PacketManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class Nuker extends Module {
   Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("All").withModes("All", "Flatten", "Selective", "Farm", "Pearl").register(this);
   Value<Boolean> predict;
   Value<Number> breakRange;
   Value<Boolean> rotate;
   Value<Boolean> strict;
   private class_2338 current;
   private final Set<class_2338> shulkerBlackList;

   public Nuker() {
      super("Nuker", Feature.Category.Player);
      this.predict = (new ValueBuilder<Boolean>()).withDescriptor("Predict").withValue(true).withPage("Pearl").withPageParent(this.mode).register(this);
      this.breakRange = (new ValueBuilder<Number>()).withDescriptor("Range").withValue((double)5.0F).withRange((double)1.0F, (double)6.0F).withPlaces(1).register(this);
      this.rotate = (new ValueBuilder<Boolean>()).withDescriptor("Rotate").withValue(true).register(this);
      this.strict = (new ValueBuilder<Boolean>()).withDescriptor("Strict").withValue(true).register(this);
      this.shulkerBlackList = new HashSet();
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         class_2338 last = this.current;
         if (!((String)this.mode.getValue()).equals("Pearl")) {
            if (last == null || !this.canMine(last)) {
               this.current = this.getNukerBlock();
            }

            if (this.current != null) {
               class_2350 side = BlockUtils.getMineableSide(this.current, (Boolean)this.strict.getValue());
               if (side == null) {
                  return;
               }

               if ((Boolean)this.rotate.getValue()) {
                  RotationUtils.doRotate(this.current, side);
               }

               mc.field_1761.method_2902(this.current, side);
               mc.field_1724.method_6104(class_1268.field_5808);
            } else if (last != null && mc.field_1761.method_2923()) {
               mc.field_1761.method_2925();
            }
         } else {
            for(class_1297 entity : mc.field_1687.method_8335((class_1297)null, mc.field_1724.method_5829())) {
               if (entity instanceof class_1533) {
                  if (!((class_1533)entity).method_6940().method_7960()) {
                     PacketManager.INSTANCE.sendPacket(class_2824.method_34206(entity, mc.field_1724.method_5715()));
                     PacketManager.INSTANCE.sendPacket(new class_2879(class_1268.field_5808));
                  }

                  PacketManager.INSTANCE.sendPacket(class_2824.method_34206(entity, mc.field_1724.method_5715()));
                  PacketManager.INSTANCE.sendPacket(new class_2879(class_1268.field_5808));
               }
            }
         }

      }
   }

   @SubscribeEvent
   public void onEntityAdd(EntityEvent.Add event) {
      if (!NullUtils.nullCheck()) {
         if ((Boolean)this.predict.getValue()) {
            class_1297 var3 = event.getEntity();
            if (var3 instanceof class_1533) {
               class_1533 entity = (class_1533)var3;
               if ((new class_238(mc.field_1724.method_24515())).method_1009((double)0.0F, 0.2, (double)0.0F).method_994(entity.method_5829())) {
                  if (!entity.method_6940().method_7960()) {
                     PacketManager.INSTANCE.sendPacket(class_2824.method_34206(entity, mc.field_1724.method_5715()));
                     PacketManager.INSTANCE.sendPacket(new class_2879(class_1268.field_5808));
                  }

                  PacketManager.INSTANCE.sendPacket(class_2824.method_34206(entity, mc.field_1724.method_5715()));
                  PacketManager.INSTANCE.sendPacket(new class_2879(class_1268.field_5808));
               }
            }

         }
      }
   }

   public class_2338 getNukerBlock() {
      class_2338 nukerBlock = null;
      float range = ((Number)this.breakRange.getValue()).floatValue();
      double bestDistance = (double)9999.0F;

      for(float x = range; x >= -range; --x) {
         for(float y = range; y >= (((String)this.mode.getValue()).equals("Flatten") ? 0.0F : -range); --y) {
            for(float z = range; z >= -range; --z) {
               class_2338 pos = class_2338.method_49637((double)x, (double)y, (double)z);
               pos = mc.field_1724.method_24515().method_10081(pos);
               class_2680 state = mc.field_1687.method_8320(pos);
               double distance = mc.field_1724.method_5707(pos.method_46558());
               if (!(distance >= bestDistance) && this.canMine(pos)) {
                  bestDistance = distance;
                  nukerBlock = pos;
               }
            }
         }
      }

      return nukerBlock;
   }

   public boolean canMine(class_2338 pos) {
      class_2680 state = mc.field_1687.method_8320(pos);
      double distance = mc.field_1724.method_5707(pos.method_46558());
      if (((String)this.mode.getValue()).equals("Farm")) {
         class_2248 var6 = state.method_26204();
         if (!(var6 instanceof class_2302)) {
            return false;
         }

         class_2302 crop = (class_2302)var6;
         int age = crop.method_9829(state);
         if (age != crop.method_9827()) {
            return false;
         }
      } else if (((String)this.mode.getValue()).equals("Selective") && !(state.method_26204() instanceof class_2480)) {
         return false;
      }

      return !state.method_26215() && !(state.method_26204() instanceof class_2404) && !(distance > (double)MathUtil.square(((Number)this.breakRange.getValue()).floatValue())) && BlockUtils.isMineable(state);
   }

   @SubscribeEvent
   public void onPacketSend(PacketEvent.Send event) {
      if (!NullUtils.nullCheck()) {
         if (((String)this.mode.getValue()).equals("Selective")) {
            class_2596 var3 = event.getPacket();
            if (var3 instanceof class_2885) {
               class_2885 packet = (class_2885)var3;
               if (BlockUtils.SHULKER_BLOCKS.contains(mc.field_1724.method_5998(packet.method_12546()).method_7909())) {
                  this.shulkerBlackList.add(packet.method_12543().method_17777().method_10093(packet.method_12543().method_17780()));
               }
            }
         }

      }
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Receive event) {
      if (!NullUtils.nullCheck()) {
         if (((String)this.mode.getValue()).equals("Selective")) {
            class_2596 var3 = event.getPacket();
            if (var3 instanceof class_2626) {
               class_2626 packet = (class_2626)var3;
               if (this.shulkerBlackList.contains(packet.method_11309()) && !BlockUtils.SHULKER_BLOCKS.contains(packet.method_11308().method_26204())) {
                  this.shulkerBlackList.remove(packet.method_11309());
               }
            }
         }

      }
   }

   public String getHudInfo() {
      return this.mode.getValue();
   }

   public String getDescription() {
      return "Nuker: Breaks blocks";
   }
}
