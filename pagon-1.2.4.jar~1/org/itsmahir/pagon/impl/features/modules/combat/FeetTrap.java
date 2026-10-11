package org.itsmahir.pagon.impl.features.modules.combat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_259;
import net.minecraft.class_2596;
import net.minecraft.class_2626;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.event.events.world.CollisionBoxEvent;
import org.itsmahir.pagon.api.event.events.world.EntityEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.CrystalManager;
import org.itsmahir.pagon.api.management.PriorityManager;
import org.itsmahir.pagon.api.management.RotationManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.utils.players.rotation.Rotation;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.utils.world.CrystalUtil;
import org.itsmahir.pagon.api.utils.world.ProtectionUtils;
import org.itsmahir.pagon.api.utils.world.RaytraceUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;
import org.itsmahir.pagon.impl.features.modules.misc.autobreak.AutoBreak;

public class FeetTrap extends Module {
   public static FeetTrap INSTANCE;
   Timer timer = new Timer();
   Timer hitcrystalCooldown = new Timer();
   Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Dynamic").withModes("Dynamic", "Feet").register(this);
   Value<Number> delay = (new ValueBuilder<Number>()).withDescriptor("Delay").withValue(0).withRange(0, 1000).withAction((set) -> this.timer.setDelay(((Number)set.getValue()).longValue())).register(this);
   Value<Boolean> adaptiveBox = (new ValueBuilder<Boolean>()).withDescriptor("Adaptive").withValue(true).register(this);
   Value<Boolean> breakCrystal = (new ValueBuilder<Boolean>()).withDescriptor("Break").withValue(true).register(this);
   Value<Boolean> predict;
   Value<Number> breakDelay;
   public Value<Number> range;
   Value<Number> blocksPerTick;
   Value<Boolean> rotate;
   Value<Boolean> sequential;
   Value<Boolean> jumpDisable;
   public Value<Boolean> render;
   public Value<PagonColor> fill;
   public Value<PagonColor> line;
   public Value<Number> fadeTime;
   public Map<class_2338, Long> renderPositions;
   public Map<class_2338, Long> placed;
   List<class_2338> toPlace;
   double startY;
   Timer past;
   boolean rotateFlag;

   public FeetTrap() {
      super("FeetTrap", Feature.Category.Combat);
      this.predict = (new ValueBuilder<Boolean>()).withDescriptor("Predict").withValue(false).withParent(this.breakCrystal).withParentEnabled(true).register(this);
      this.breakDelay = (new ValueBuilder<Number>()).withDescriptor("Break Delay").withValue(0).withRange(0, 1000).withAction((set) -> this.hitcrystalCooldown.setDelay(((Number)set.getValue()).longValue())).register(this);
      this.range = (new ValueBuilder<Number>()).withDescriptor("Range").withValue(6).withRange(3, 6).withPlaces(1).register(this);
      this.blocksPerTick = (new ValueBuilder<Number>()).withDescriptor("Blocks").withValue(10).withRange(1, 10).withPlaces(0).register(this);
      this.rotate = (new ValueBuilder<Boolean>()).withDescriptor("Rotate").withValue(false).register(this);
      this.sequential = (new ValueBuilder<Boolean>()).withDescriptor("Overplace").withValue(false).register(this);
      this.jumpDisable = (new ValueBuilder<Boolean>()).withDescriptor("Disable").withValue(true).register(this);
      this.render = (new ValueBuilder<Boolean>()).withDescriptor("Render").withValue(true).register(this);
      this.fill = (new ValueBuilder<PagonColor>()).withDescriptor("Fill").withValue(new PagonColor(255, 0, 0, 25)).withParentEnabled(true).withParent(this.render).register(this);
      this.line = (new ValueBuilder<PagonColor>()).withDescriptor("Line").withValue(new PagonColor(255, 0, 0, 255)).withParentEnabled(true).withParent(this.render).register(this);
      this.fadeTime = (new ValueBuilder<Number>()).withDescriptor("Fade Time").withValue(200).withRange(0, 1000).withParentEnabled(true).withParent(this.render).register(this);
      this.renderPositions = new HashMap();
      this.placed = new HashMap();
      this.toPlace = new ArrayList();
      this.startY = (double)0.0F;
      this.past = new Timer();
      this.rotateFlag = false;
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onPlayerUpdate(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         this.past.setDelay(600L);
         if (!PriorityManager.INSTANCE.isUsageLocked() || Objects.equals(PriorityManager.INSTANCE.usageLockCause, "FeetTrap")) {
            if (!AutoBreak.INSTANCE.didAction) {
               if (this.getSlot() == -1) {
                  if (this.past.isPassed()) {
                     this.setEnabled(false);
                  }

               } else {
                  this.past.resetDelay();
                  if ((Boolean)this.jumpDisable.getValue() && !mc.field_1724.method_5771() && !mc.field_1724.method_5869() && !mc.field_1724.method_24828() && Math.abs(mc.field_1724.method_23318() - this.startY) > (double)0.5F) {
                     this.setEnabled(false);
                  } else {
                     Iterator<Map.Entry<class_2338, Long>> iterator = this.placed.entrySet().iterator();

                     while(iterator.hasNext()) {
                        Map.Entry<class_2338, Long> entry = (Map.Entry)iterator.next();
                        if (System.currentTimeMillis() - (Long)entry.getValue() > 200L) {
                           iterator.remove();
                        }
                     }

                     this.toPlace.clear();
                     int blocksInTick = 0;
                     if (this.timer.isPassed()) {
                        for(class_2338 targetPos : ProtectionUtils.getSurroundPlacements(false, ((String)this.mode.getValue()).equals("Dynamic"), (Boolean)this.adaptiveBox.getValue(), !this.canOverplace(), (Boolean)AntiCheat.INSTANCE.strictDirection.getValue())) {
                           if (blocksInTick >= ((Number)this.blocksPerTick.getValue()).intValue()) {
                              break;
                           }

                           if (!this.toPlace.contains(targetPos)) {
                              Set<class_2338> keySet = new HashSet(Set.copyOf(this.placed.keySet()));
                              keySet.addAll(this.toPlace);
                              if (BlockUtils.canPlaceBlock(targetPos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue(), keySet)) {
                                 if (this.placed.containsKey(targetPos)) {
                                    if (CrystalManager.INSTANCE.isRecentlyBlocked(targetPos)) {
                                       this.placed.remove(targetPos);
                                    } else if (System.currentTimeMillis() - (Long)this.placed.get(targetPos) < 60L) {
                                       continue;
                                    }
                                 }

                                 if (blocksInTick == 0 && (Boolean)this.rotate.getValue() && !(Boolean)AntiCheat.INSTANCE.grimRotations.getValue()) {
                                    RotationUtils.doRotate(targetPos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue());
                                 }

                                 this.toPlace.add(targetPos);
                                 PriorityManager.INSTANCE.lockUsageLock("FeetTrap");
                                 ++blocksInTick;
                                 this.timer.resetDelay();
                              }
                           }
                        }
                     }

                     if (blocksInTick == 0) {
                        PriorityManager.INSTANCE.unlockUsageLock();
                     }

                     this.doAttack();
                     if ((Boolean)AntiCheat.INSTANCE.grimRotations.getValue()) {
                        this.doPlace();
                     }

                  }
               }
            }
         }
      }
   }

   @SubscribeEvent
   public void onPlayerUpdate(TickEvent.PlayerTickEvent.Post event) {
      if (!NullUtils.nullCheck()) {
         if (!(Boolean)AntiCheat.INSTANCE.grimRotations.getValue()) {
            this.doPlace();
         }

      }
   }

   boolean hitCrystal(class_2338 pos) {
      if (!this.hitcrystalCooldown.isPassed()) {
         return false;
      } else {
         for(class_1297 entity : mc.field_1687.method_18467(class_1297.class, new class_238(pos))) {
            if (CrystalUtil.isEndCrystal(entity) && (Boolean)this.breakCrystal.getValue() && entity.method_5805()) {
               if ((Boolean)AutoCrystal.INSTANCE.rotate.getValue()) {
                  float[] rots = new float[]{RotationUtils.getActualYaw(), RotationUtils.getActualPitch()};
                  if (RotationManager.INSTANCE.isRotating()) {
                     if (!RotationManager.INSTANCE.requests.isEmpty()) {
                        Rotation rotation = RotationManager.INSTANCE.getRotationRequest();
                        rots = new float[]{rotation.getYaw(), rotation.getPitch()};
                     } else {
                        rots = new float[]{RotationManager.INSTANCE.getRotation().getYaw(), RotationManager.INSTANCE.getRotation().getPitch()};
                     }
                  }

                  if (RaytraceUtils.isLookingResult(mc.field_1724, entity, mc.field_1724.method_33571(), rots, 6.0F) == null) {
                     float[] breakRots = RotationUtils.getRotationsTo(mc.field_1724.method_33571(), new class_243(entity.method_23317(), entity.method_23318(), entity.method_23321()));
                     if ((Boolean)AntiCheat.INSTANCE.grimRotations.getValue()) {
                        RotationUtils.doSilentRotate(breakRots);
                        this.rotateFlag = true;
                     } else if (((String)AntiCheat.INSTANCE.acMode.getValue()).equals("Vanilla")) {
                        RotationUtils.packetRotate(breakRots);
                     }
                  }
               }

               PlayerUtils.attackTarget(entity);
               this.hitcrystalCooldown.resetDelay();
               return true;
            }
         }

         return false;
      }
   }

   @SubscribeEvent
   public void onCollision(CollisionBoxEvent event) {
      if (!NullUtils.nullCheck()) {
         if (this.placed.containsKey(event.getPos())) {
            event.setCancelled(true);
            event.setVoxelShape(class_259.method_1078(new class_238((double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, (double)1.0F, (double)1.0F)));
         }

      }
   }

   public void onEnable() {
      super.onEnable();
      if (!NullUtils.nullCheck()) {
         this.startY = mc.field_1724.method_23318();
         this.placed.clear();
      }
   }

   public void onDisable() {
      super.onDisable();
      if (!NullUtils.nullCheck()) {
         if (PriorityManager.INSTANCE.isUsageLocked() && PriorityManager.INSTANCE.usageLockCause.equals("FeetTrap")) {
            PriorityManager.INSTANCE.unlockUsageLock();
         }

         this.placed.clear();
      }
   }

   public void doAttack() {
      if (!NullUtils.nullCheck()) {
         for(class_2338 pos : this.toPlace) {
            if (this.hitCrystal(pos)) {
               break;
            }
         }

      }
   }

   public void doPlace() {
      if (!NullUtils.nullCheck()) {
         int blockSlot = this.getSlot();
         int oldSlot = mc.field_1724.method_31548().field_7545;
         boolean switched = false;

         for(class_2338 pos : this.toPlace) {
            if (blockSlot != mc.field_1724.method_31548().field_7545) {
               InventoryUtils.switchToSlot(blockSlot);
               switched = true;
            }

            this.placed.put(pos, System.currentTimeMillis());
            if ((Boolean)this.rotate.getValue() && (Boolean)AntiCheat.INSTANCE.grimRotations.getValue()) {
               RotationUtils.doSilentRotate(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue());
            }

            BlockUtils.placeBlock(pos, BlockUtils.getPlaceableSide(pos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue(), this.placed.keySet()), !mc.field_1724.method_6047().method_7909().equals(class_1802.field_8466));
            if ((Boolean)this.render.getValue()) {
               this.renderPositions.put(pos, System.currentTimeMillis());
            }
         }

         if (switched) {
            InventoryUtils.switchToSlot(oldSlot);
         }

         if (!this.toPlace.isEmpty() && (Boolean)AntiCheat.INSTANCE.grimRotations.getValue() && (Boolean)this.rotate.getValue() || this.rotateFlag) {
            RotationUtils.silentSync();
         }

         this.toPlace.clear();
      }
   }

   int getSlot() {
      int slot = -1;
      slot = InventoryUtils.getHotbarItemSlot(class_1802.field_8281);
      if (slot == -1) {
         slot = InventoryUtils.getHotbarItemSlot(class_1802.field_8466);
      }

      return slot;
   }

   @SubscribeEvent
   public void onEntityAdd(EntityEvent.Add event) {
      if (!NullUtils.nullCheck()) {
         if (!PriorityManager.INSTANCE.isUsageLocked() || Objects.equals(PriorityManager.INSTANCE.usageLockCause, "FeetTrap")) {
            if ((Boolean)this.breakCrystal.getValue() && (Boolean)this.predict.getValue()) {
               class_1297 var3 = event.getEntity();
               if (var3 instanceof class_1511) {
                  class_1511 entity = (class_1511)var3;
                  if (this.timer.isPassed() && this.hitcrystalCooldown.isPassed()) {
                     this.doPredict(entity);
                  }
               }
            }

         }
      }
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Receive event) {
      if (!NullUtils.nullCheck()) {
         class_2596 var3 = event.getPacket();
         if (var3 instanceof class_2626) {
            class_2626 packet = (class_2626)var3;
            class_2338 targetPos = packet.method_11309();
            if (this.placed.containsKey(targetPos)) {
               this.placed.remove(targetPos);
            }
         }

      }
   }

   public void doPredict(class_1511 entity) {
      int blockSlot = this.getSlot();
      int oldSlot = mc.field_1724.method_31548().field_7545;
      boolean switched = false;
      if (blockSlot != -1) {
         for(class_2338 targetPos : ProtectionUtils.getSurroundPlacements(false, ((String)this.mode.getValue()).equals("Dynamic"), (Boolean)this.adaptiveBox.getValue(), true, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue())) {
            if (BlockUtils.isReplaceable(targetPos) && BlockUtils.isInterceptedBy(targetPos, entity)) {
               if (BlockUtils.isBlockedByIgnoreEntity(targetPos)) {
                  return;
               }

               boolean rotated = false;
               if ((Boolean)AutoCrystal.INSTANCE.rotate.getValue()) {
                  float[] rots = new float[]{RotationUtils.getActualYaw(), RotationUtils.getActualPitch()};
                  if (RotationManager.INSTANCE.isRotating()) {
                     if (!RotationManager.INSTANCE.requests.isEmpty()) {
                        Rotation rotation = RotationManager.INSTANCE.getRotationRequest();
                        rots = new float[]{rotation.getYaw(), rotation.getPitch()};
                     } else {
                        rots = new float[]{RotationManager.INSTANCE.getRotation().getYaw(), RotationManager.INSTANCE.getRotation().getPitch()};
                     }
                  }

                  if (RaytraceUtils.isLookingResult(mc.field_1724, entity, mc.field_1724.method_33571(), rots, 6.0F) == null) {
                     float[] breakRots = RotationUtils.getRotationsTo(mc.field_1724.method_33571(), new class_243(entity.method_23317(), entity.method_23318(), entity.method_23321()));
                     if ((Boolean)AutoCrystal.INSTANCE.rotate.getValue()) {
                        RotationUtils.doSilentRotate(breakRots);
                        rotated = true;
                     }
                  }
               }

               PlayerUtils.attackTarget(entity);
               this.hitcrystalCooldown.resetDelay();
               if (blockSlot != mc.field_1724.method_31548().field_7545) {
                  InventoryUtils.switchToSlot(blockSlot);
                  switched = true;
               }

               if ((Boolean)this.rotate.getValue() && (Boolean)AntiCheat.INSTANCE.grimRotations.getValue()) {
                  RotationUtils.doSilentRotate(targetPos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue());
                  rotated = true;
               }

               BlockUtils.placeBlock(targetPos, BlockUtils.getPlaceableSide(targetPos, (Boolean)AntiCheat.INSTANCE.strictDirection.getValue()), !mc.field_1724.method_6047().method_7909().equals(class_1802.field_8466));
               if ((Boolean)this.render.getValue()) {
                  this.renderPositions.put(targetPos, System.currentTimeMillis());
               }

               if (switched) {
                  InventoryUtils.switchToSlot(oldSlot);
               }

               if (rotated) {
                  RotationUtils.silentSync();
               }

               return;
            }
         }

      }
   }

   public boolean canOverplace() {
      if ((Boolean)this.sequential.getValue()) {
         return true;
      } else {
         return (Boolean)this.breakCrystal.getValue() && this.hitcrystalCooldown.isPassed();
      }
   }

   public String getDescription() {
      return "Surrounds feet with obsidian";
   }
}
