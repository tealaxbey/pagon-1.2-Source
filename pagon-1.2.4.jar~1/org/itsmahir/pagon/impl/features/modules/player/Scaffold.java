package org.itsmahir.pagon.impl.features.modules.player;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.minecraft.class_1268;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_239;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3965;
import net.minecraft.class_239.class_240;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.move.MoveEvent;
import org.itsmahir.pagon.api.event.events.move.SneakEvent;
import org.itsmahir.pagon.api.event.events.render.RenderGameOverlayEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.PriorityManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Pair;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.players.InventoryUtils;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.ScaledResolution;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.utils.world.RaytraceUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.client.AntiCheat;
import org.itsmahir.pagon.impl.gui.ClickGui;

public class Scaffold extends Module {
   public static Scaffold INSTANCE;
   Value<String> stage = (new ValueBuilder<String>()).withDescriptor("Event").withValue("Post").withModes("Post", "Pre").register(this);
   Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Silent").withModes("Silent", "Swap").register(this);
   Value<String> tower = (new ValueBuilder<String>()).withDescriptor("Tower", "towerMode").withValue("None").withModes("None", "Normal", "Move").withAction((s) -> this.handleTowerPage((String)s.getValue())).register(this);
   Value<Boolean> limit = (new ValueBuilder<Boolean>()).withDescriptor("Limit").withValue(false).register(this);
   public Value<Boolean> grim = (new ValueBuilder<Boolean>()).withDescriptor("Grim").withValue(false).register(this);
   Value<Boolean> rotate = (new ValueBuilder<Boolean>()).withDescriptor("Rotate").withValue(false).register(this);
   Value<Boolean> swing = (new ValueBuilder<Boolean>()).withDescriptor("Swing").withValue(false).register(this);
   Value<Boolean> downwards = (new ValueBuilder<Boolean>()).withDescriptor("Downwards").withValue(false).register(this);
   Value<String> safeWalk = (new ValueBuilder<String>()).withDescriptor("SafeWalk").withValue("None").withModes("None", "Soft", "Strong").register(this);
   Value<String> expand = (new ValueBuilder<String>()).withDescriptor("Expand", "ExpandMode").withValue("Off").withModes("Off", "On", "Dynamic").register(this);
   Value<Number> placeDelay = (new ValueBuilder<Number>()).withDescriptor("Place Delay").withValue(0).withRange(0, 1000).withPlaces(0).register(this);
   Value<String> blockCounter = (new ValueBuilder<String>()).withDescriptor("Block Counter").withValue("None").withModes("None", "Text", "Bar").withAction((s) -> this.handleBlockCounterPage((String)s.getValue())).register(this);
   Value<Boolean> blockText = (new ValueBuilder<Boolean>()).withDescriptor("Bar Text").withValue(false).register(this);
   Value<String> barColor = (new ValueBuilder<String>()).withDescriptor("Bar Color").withValue("Scissor").withModes("Scissor", "Custom").register(this);
   Value<Number> barThickness = (new ValueBuilder<Number>()).withDescriptor("Thickness").withValue(1).withRange(0.5F, 15).withPlaces(1).register(this);
   Value<PagonColor> leftColor = (new ValueBuilder<PagonColor>()).withDescriptor("Left Color").withValue(new PagonColor(255, 0, 255, 255)).register(this);
   Value<PagonColor> rightColor = (new ValueBuilder<PagonColor>()).withDescriptor("Right Color").withValue(new PagonColor(0, 255, 0)).register(this);
   Value<Boolean> renderRays = (new ValueBuilder<Boolean>()).withDescriptor("Visualize").withValue(false).register(this);
   public Value<Boolean> render = (new ValueBuilder<Boolean>()).withDescriptor("Render").withValue(true).register(this);
   public Value<PagonColor> fill;
   public Value<PagonColor> line;
   public Value<Number> fadeTime;
   public Map<class_2338, Long> renderPositions;
   Timer placeTimer;
   Timer towerTimer;
   int blockCount;
   protected float[] rotations;
   protected Pair.BlockPair pos;
   private static final List<class_2248> blacklistedBlocks = new ArrayList();
   class_243 posAtTime;
   class_3965 lastResult;
   private boolean isSneaking;

   public Scaffold() {
      super("Scaffold", Feature.Category.Movement);
      this.fill = (new ValueBuilder<PagonColor>()).withDescriptor("Fill").withValue(new PagonColor(255, 0, 0, 25)).withParentEnabled(true).withParent(this.render).register(this);
      this.line = (new ValueBuilder<PagonColor>()).withDescriptor("Line").withValue(new PagonColor(255, 0, 0, 255)).withParentEnabled(true).withParent(this.render).register(this);
      this.fadeTime = (new ValueBuilder<Number>()).withDescriptor("Fade Time").withValue(200).withRange(0, 1000).withParentEnabled(true).withParent(this.render).register(this);
      this.renderPositions = new HashMap();
      this.placeTimer = new Timer();
      this.towerTimer = new Timer();
      this.blockCount = 0;
      INSTANCE = this;
   }

   public void handleBlockCounterPage(String page) {
      this.barColor.setActive(page.equals("Bar"));
      this.barThickness.setActive(page.equals("Bar"));
      this.blockText.setActive(page.equals("Bar"));
      this.leftColor.setActive(page.equals("Bar") && ((String)this.barColor.getValue()).equals("Custom"));
      this.rightColor.setActive(page.equals("Bar") && ((String)this.barColor.getValue()).equals("Custom"));
   }

   public void handleTowerPage(String page) {
      this.limit.setActive(!page.equals("None"));
   }

   public void onEnable() {
      super.onEnable();
      if (!NullUtils.nullCheck()) {
         this.blockCount = this.getBlockCount();
         this.pos = null;
         this.lastResult = null;
      }
   }

   public void onDisable() {
      super.onDisable();
      if (!NullUtils.nullCheck()) {
         if (PriorityManager.INSTANCE.isUsageLocked() && Objects.equals(PriorityManager.INSTANCE.usageLockCause, "Scaffold")) {
            PriorityManager.INSTANCE.unlockUsageLock();
         }

         if (this.isSneaking) {
            mc.field_1690.field_1832.method_23481(false);
         }

         this.isSneaking = false;
         this.pos = null;
         this.lastResult = null;
      }
   }

   @SubscribeEvent
   public void onSneak(SneakEvent event) {
      if (!this.canSneak()) {
         event.setCancelled(true);
      }

   }

   public boolean canSneak() {
      return !(Boolean)this.downwards.getValue() || !mc.field_1690.field_1832.method_1434() || mc.field_1690.field_1903.method_1434();
   }

   @SubscribeEvent
   public void onPlayerUpdate(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if (!PriorityManager.INSTANCE.isUsageLocked() || Objects.equals(PriorityManager.INSTANCE.usageLockCause, "Scaffold")) {
            this.placeTimer.setDelay(((Number)this.placeDelay.getValue()).longValue());
            this.blockCount = this.getBlockCount();
            if (this.getBlockSlot() == -1) {
               this.pos = null;
            } else {
               if (((String)this.mode.getValue()).equals("Swap")) {
                  mc.field_1724.method_31548().field_7545 = this.getBlockSlot();
               }

               this.pos = null;
               this.pos = this.findNextPos();
               if (this.pos != null) {
                  this.setRotations((class_2338)this.pos.key(), (class_2350)this.pos.value());
                  this.calcVecs();
                  if (!((String)this.tower.getValue()).equals("None")) {
                     class_2338 under = BlockUtils.getRoundedBlockPos(mc.field_1724.method_23317(), mc.field_1724.method_23318() - 1.6, mc.field_1724.method_23321());
                     if (mc.field_1690.field_1903.method_1434() && !mc.field_1687.method_8320(under).method_45474()) {
                        if (PlayerUtils.isMoving() && !((String)this.tower.getValue()).equals("Move")) {
                           this.towerTimer.resetDelay();
                        } else {
                           if (mc.field_1724.method_18798().field_1351 != (double)0.42F) {
                              PlayerUtils.setMotionY((double)0.42F);
                           }

                           if (this.towerTimer.isPassed(1500L) && (Boolean)this.limit.getValue()) {
                              PlayerUtils.setMotionY((double)-0.28F);
                              this.towerTimer.resetDelay();
                           }

                           PlayerUtils.setMotionXZ(mc.field_1724.method_18798().field_1352 * 0.9, mc.field_1724.method_18798().field_1350 * 0.9);
                        }
                     } else if (!mc.field_1690.field_1903.method_1434()) {
                        this.towerTimer.resetDelay();
                     }
                  }
               } else if (!mc.field_1690.field_1903.method_1434()) {
                  this.towerTimer.resetDelay();
               }

               if (this.pos == null && (Boolean)this.grim.getValue()) {
                  RotationUtils.setRotation(PlayerUtils.getMoveYaw(mc.field_1724.method_36454()), 90.0F, 7);
               }

               if (((String)this.stage.getValue()).equals("Pre")) {
                  this.doPlace();
               }

            }
         }
      }
   }

   @SubscribeEvent
   public void onPlayerUpdate(TickEvent.PlayerTickEvent.Post event) {
      if (!NullUtils.nullCheck()) {
         if (!PriorityManager.INSTANCE.isUsageLocked() || Objects.equals(PriorityManager.INSTANCE.usageLockCause, "Scaffold")) {
            if (((String)this.stage.getValue()).equals("Post")) {
               this.doPlace();
            }

            if (this.pos == null) {
               PriorityManager.INSTANCE.unlockUsageLock();
            }

         }
      }
   }

   @SubscribeEvent
   public void draw(RenderGameOverlayEvent.Text event) {
      if (!NullUtils.nullCheck()) {
         if (!((String)this.blockCounter.getValue()).equals("None")) {
            Color blockColor = this.getBlockColor();
            ScaledResolution resolution = new ScaledResolution(mc);
            int slot = this.getBlockSlot();
            switch ((String)this.blockCounter.getValue()) {
               case "Bar":
                  if (this.blockCount == 0 || MiddleClick.INSTANCE.startedTimer) {
                     return;
                  }

                  float x = (float)resolution.getScaledWidth() / 2.0F;
                  float y = (float)resolution.getScaledHeight() / 2.0F + 13.0F;
                  float thickness = ((Number)this.barThickness.getValue()).floatValue();
                  float percentage = Math.min(1.0F, (float)this.blockCount / 64.0F);
                  float width = 80.0F;
                  float half = width / 2.0F;
                  RenderUtil.renderRect(event.getContext().method_51448(), (double)(x - half) - (double)0.5F, (double)y - (double)0.5F, (double)(half * 2.0F + 1.0F), (double)(thickness + 1.0F), 2013265920);
                  RenderUtil.renderGradient(event.getContext().method_51448(), (double)(x - half) - (double)0.5F, (double)y - (double)0.5F, (double)(width * percentage + 1.0F), (double)(thickness + 1.0F), ((String)this.barColor.getValue()).equals("Scissor") ? Color.RED.darker().getRGB() : ((PagonColor)this.leftColor.getValue()).getColor().darker().getRGB(), ((String)this.barColor.getValue()).equals("Scissor") ? blockColor.darker().getRGB() : ((PagonColor)this.rightColor.getValue()).getColor().darker().getRGB(), true);
                  RenderUtil.renderGradient(event.getContext().method_51448(), (double)(x - half), (double)y, (double)(width * percentage), (double)thickness, ((String)this.barColor.getValue()).equals("Scissor") ? Color.RED.getRGB() : ((PagonColor)this.leftColor.getValue()).getColor().getRGB(), ((String)this.barColor.getValue()).equals("Scissor") ? blockColor.getRGB() : ((PagonColor)this.rightColor.getValue()).getColor().getRGB(), true);
                  if (slot != -1 && (Boolean)this.blockText.getValue()) {
                     class_1799 blockStack = new class_1799(mc.field_1724.method_31548().method_5438(slot).method_7909());
                     RenderUtil.renderItemWithCount(event.getContext(), blockStack, new Point((int)(x - half + width * percentage) - 8, (int)(y + thickness / 2.0F) - 8), this.blockCount, ((String)this.barColor.getValue()).equals("Scissor") ? blockColor : ((PagonColor)this.rightColor.getValue()).getColor(), true);
                  }
                  break;
               case "Text":
                  if (slot != -1) {
                     class_1799 blockStack = new class_1799(mc.field_1724.method_31548().method_5438(slot).method_7909());
                     RenderUtil.renderItemWithCount(event.getContext(), blockStack, new Point((int)((float)resolution.getScaledWidth() / 2.0F + 1.0F - 8.0F), (int)((float)resolution.getScaledHeight() / 2.0F + 10.0F)), this.blockCount, blockColor, true);
                  } else {
                     ClickGui.CONTEXT.getRenderer().renderText(event.getContext(), Integer.toString(this.blockCount), (float)resolution.getScaledWidth() / 2.0F + 1.0F - (float)ClickGui.CONTEXT.getRenderer().getTextWidth(Integer.toString(this.blockCount)) / 2.0F, (float)resolution.getScaledHeight() / 2.0F + 10.0F, blockColor, true);
                  }
            }

         }
      }
   }

   @SubscribeEvent
   public void onMove(MoveEvent event) {
      if (!NullUtils.nullCheck()) {
         if (!((String)this.safeWalk.getValue()).equals("None") && PlayerUtils.isMoving() && mc.field_1724.method_24828()) {
            class_241 safeMove = PlayerUtils.safeWalk(event.getX(), event.getZ());
            if (((String)this.safeWalk.getValue()).equals("Strong")) {
               this.isSneaking = PlayerUtils.canSneak(safeMove);
               mc.field_1690.field_1832.method_23481(this.isSneaking);
               return;
            }

            event.setX((double)safeMove.field_1343);
            event.setZ((double)safeMove.field_1342);
         }

      }
   }

   private int getBlockSlot() {
      for(int i = 0; i < 9; ++i) {
         if (mc.field_1724.method_31548().method_5438(i).method_7947() != 0 && mc.field_1724.method_31548().method_5438(i).method_7909() instanceof class_1747 && this.isItemValid(mc.field_1724.method_31548().method_5438(i).method_7909())) {
            return i;
         }
      }

      return -1;
   }

   private int getBlockCount() {
      int blockCount = 0;

      for(int i = 0; i < 9; ++i) {
         if (mc.field_1724.method_31548().method_5438(i).method_7947() != 0 && mc.field_1724.method_31548().method_5438(i).method_7909() instanceof class_1747 && this.isItemValid(mc.field_1724.method_31548().method_5438(i).method_7909())) {
            blockCount += mc.field_1724.method_31548().method_5438(i).method_7947();
         }
      }

      return blockCount;
   }

   private void calcVecs() {
      this.lastResult = null;
      class_239 result = RaytraceUtils.getBlockHitResult(this.rotations[0], this.rotations[1]);
      if (result instanceof class_3965 hitResult) {
         if (class_240.field_1333 == hitResult.method_17783()) {
            return;
         }

         this.posAtTime = mc.field_1724.method_33571();
         this.lastResult = hitResult;
      }

   }

   private Color getBlockColor() {
      float f2 = 64.0F;
      float f3 = Math.max(0.0F, Math.min((float)this.blockCount, f2) / f2);
      return new Color(Color.HSBtoRGB(f3 / 3.0F, 1.0F, 1.0F) | -16777216);
   }

   private boolean isItemValid(class_1792 item) {
      if (item instanceof class_1747 itemBlock) {
         if (!blacklistedBlocks.contains(itemBlock.method_7711())) {
            class_2680 baseState = itemBlock.method_7711().method_9564();
            if (!baseState.method_45474()) {
               class_2338 outOfBounds = new class_2338(mc.field_1724.method_31477() + 4158, mc.field_1724.method_31478() + 4158, mc.field_1724.method_31479() + 4158);
               class_265 voxelShape = baseState.method_26220(mc.field_1687, outOfBounds);
               return voxelShape.equals(class_259.method_1077());
            }
         }
      }

      return false;
   }

   public void doPlace() {
      if (this.pos != null) {
         if (this.getBlockSlot() != -1) {
            if (this.placeTimer.isPassed()) {
               int oldSlot = mc.field_1724.method_31548().field_7545;
               if (((String)this.mode.getValue()).equals("Silent")) {
                  InventoryUtils.switchToSlot(this.getBlockSlot());
               }

               if (this.rotations != null && (Boolean)AntiCheat.INSTANCE.grimRotations.getValue() && (Boolean)this.rotate.getValue()) {
                  RotationUtils.doSilentRotate(this.rotations);
               }

               if (BlockUtils.placeBlock((class_2338)this.pos.key(), (class_2350)this.pos.value(), class_1268.field_5808, false, (Boolean)this.swing.getValue()) && (Boolean)this.render.getValue()) {
                  this.renderPositions.put((class_2338)this.pos.key(), System.currentTimeMillis());
               }

               if (this.rotations != null && (Boolean)AntiCheat.INSTANCE.grimRotations.getValue() && (Boolean)this.rotate.getValue()) {
                  RotationUtils.silentSync();
               }

               PriorityManager.INSTANCE.lockUsageLock("Scaffold");
               this.placeTimer.resetDelay();
               if (((String)this.mode.getValue()).equals("Silent")) {
                  InventoryUtils.switchToSlot(oldSlot);
               }
            }

         }
      }
   }

   public Pair.BlockPair getHelping(class_2338 pos) {
      for(class_2350 facing : class_2350.values()) {
         class_2338 p = pos.method_10093(facing);
         if (!p.equals(mc.field_1724.method_24515())) {
            class_2350 f = BlockUtils.getFacing(p);
            if (f != null) {
               return new Pair.BlockPair(p, f);
            }
         }
      }

      for(class_2350 facing : class_2350.values()) {
         class_2338 p = pos.method_10093(facing);
         if (!p.equals(mc.field_1724.method_24515()) && mc.field_1687.method_8320(p).method_45474()) {
            for(class_2350 direction1 : class_2350.values()) {
               class_2338 neighbor1 = p.method_10093(direction1);
               if (!neighbor1.equals(mc.field_1724.method_24515()) && !mc.field_1687.method_8320(neighbor1).method_45474()) {
                  class_2350 f = BlockUtils.getFacing(neighbor1);
                  if (f != null) {
                     return new Pair.BlockPair(neighbor1, f);
                  }
               }
            }
         }
      }

      return null;
   }

   private void setRotations(class_2338 pos, class_2350 facing) {
      this.rotations = RotationUtils.getBlockRotations(pos.method_10093(facing), facing.method_10153());
      if ((Boolean)this.rotate.getValue() && this.rotations != null) {
         RotationUtils.setRotation(this.rotations);
      }

   }

   public Pair.BlockPair findNextPos() {
      class_2338 target = this.getTargetBlock();
      if (target == null) {
         return null;
      } else {
         class_2350 direction = BlockUtils.getFacing(target);
         if (direction != null) {
            return new Pair.BlockPair(target, direction);
         } else {
            Pair.BlockPair assist = this.getHelping(target);
            if (assist != null) {
               return assist;
            } else {
               if (!mc.field_1724.method_24828()) {
                  class_2338 underPos = target.method_10074();
                  if (mc.field_1687.method_8320(underPos).method_45474()) {
                     class_2350 underDirection = BlockUtils.getFacing(underPos);
                     if (underDirection != null) {
                        return new Pair.BlockPair(underPos, underDirection);
                     }

                     Pair.BlockPair underAssist = this.getHelping(underPos);
                     return underAssist;
                  }
               }

               return null;
            }
         }
      }
   }

   public class_2338 getTargetBlock() {
      class_2338 underPos = mc.field_1724.method_24515().method_10074();
      if (!this.canSneak()) {
         underPos = underPos.method_10074();
      }

      if (mc.field_1687.method_8320(underPos).method_45474()) {
         return underPos;
      } else if (!((String)this.expand.getValue()).equals("Off") && (!((String)this.expand.getValue()).equals("Dynamic") || mc.field_1690.field_1903.method_1434())) {
         if (mc.field_1690.field_1894.method_1434() && !mc.field_1690.field_1881.method_1434()) {
            class_2338 forwardPos = underPos.method_10093(mc.field_1724.method_5735());
            if (mc.field_1687.method_8320(forwardPos).method_45474()) {
               return forwardPos;
            }
         } else if (mc.field_1690.field_1881.method_1434() && !mc.field_1690.field_1894.method_1434()) {
            class_2338 backPos = underPos.method_10093(mc.field_1724.method_5735().method_10153());
            if (mc.field_1687.method_8320(backPos).method_45474()) {
               return backPos;
            }
         }

         if (mc.field_1690.field_1849.method_1434() && !mc.field_1690.field_1913.method_1434()) {
            class_2338 rightPos = underPos.method_10093(mc.field_1724.method_5735().method_10170());
            if (mc.field_1687.method_8320(rightPos).method_45474()) {
               return rightPos;
            }
         } else if (mc.field_1690.field_1913.method_1434() && !mc.field_1690.field_1849.method_1434()) {
            class_2338 leftPos = underPos.method_10093(mc.field_1724.method_5735().method_10160());
            if (mc.field_1687.method_8320(leftPos).method_45474()) {
               return leftPos;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldEvent event) {
      if (!NullUtils.nullCheck()) {
         if ((Boolean)this.renderRays.getValue() && this.lastResult != null) {
            RenderUtil.renderLineFromPosToPos(this.posAtTime, this.lastResult.method_17784(), ((PagonColor)this.rightColor.getValue()).getColor(), ((PagonColor)this.leftColor.getValue()).getColor(), 1.0F);
         }

      }
   }

   public String getHudInfo() {
      return "Cato";
   }

   public String getDescription() {
      return "Scaffold: Places blocks under you or bridges fast";
   }

   static {
      blacklistedBlocks.add(class_2246.field_10371);
      blacklistedBlocks.add(class_2246.field_10605);
      blacklistedBlocks.add(class_2246.field_10373);
      blacklistedBlocks.add(class_2246.field_10532);
      blacklistedBlocks.add(class_2246.field_10140);
      blacklistedBlocks.add(class_2246.field_10055);
      blacklistedBlocks.add(class_2246.field_10203);
      blacklistedBlocks.add(class_2246.field_10275);
      blacklistedBlocks.add(class_2246.field_10063);
      blacklistedBlocks.add(class_2246.field_10407);
      blacklistedBlocks.add(class_2246.field_10051);
      blacklistedBlocks.add(class_2246.field_10268);
      blacklistedBlocks.add(class_2246.field_10068);
      blacklistedBlocks.add(class_2246.field_10320);
      blacklistedBlocks.add(class_2246.field_10199);
      blacklistedBlocks.add(class_2246.field_10600);
      blacklistedBlocks.add(class_2246.field_10535);
      blacklistedBlocks.add(class_2246.field_10255);
      blacklistedBlocks.add(class_2246.field_10102);
      blacklistedBlocks.add(class_2246.field_10256);
      blacklistedBlocks.add(class_2246.field_10142);
      blacklistedBlocks.add(class_2246.field_10569);
      blacklistedBlocks.add(class_2246.field_10440);
      blacklistedBlocks.add(class_2246.field_10392);
      blacklistedBlocks.add(class_2246.field_10408);
      blacklistedBlocks.add(class_2246.field_10089);
      blacklistedBlocks.add(class_2246.field_10616);
      blacklistedBlocks.add(class_2246.field_10122);
      blacklistedBlocks.add(class_2246.field_10159);
      blacklistedBlocks.add(class_2246.field_10563);
      blacklistedBlocks.add(class_2246.field_9992);
      blacklistedBlocks.add(class_2246.field_10451);
      blacklistedBlocks.add(class_2246.field_10420);
      blacklistedBlocks.add(class_2246.field_10528);
      blacklistedBlocks.add(class_2246.field_10312);
      blacklistedBlocks.add(class_2246.field_10343);
      blacklistedBlocks.add(class_2246.field_10560);
      blacklistedBlocks.add(class_2246.field_10615);
      blacklistedBlocks.add(class_2246.field_10002);
   }
}
