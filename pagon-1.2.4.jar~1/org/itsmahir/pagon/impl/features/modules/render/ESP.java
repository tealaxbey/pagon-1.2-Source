package org.itsmahir.pagon.impl.features.modules.render;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1684;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_259;
import net.minecraft.class_2596;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2767;
import net.minecraft.class_3191;
import net.minecraft.class_3417;
import net.minecraft.class_3532;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.event.events.player.TeamColorEvent;
import org.itsmahir.pagon.api.event.events.render.EntityOutlineEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.event.events.world.EntityEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.FriendManager;
import org.itsmahir.pagon.api.management.breaks.BreakManager;
import org.itsmahir.pagon.api.management.breaks.data.BreakData;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.render.Interpolator;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.world.RenderType;
import org.itsmahir.pagon.api.utils.render.world.buffers.RenderBuffers;
import org.itsmahir.pagon.api.utils.world.BlockUtils;
import org.itsmahir.pagon.api.utils.world.EntityUtils;
import org.itsmahir.pagon.api.utils.world.HoleUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.client.gui.Hud;
import org.itsmahir.pagon.mixin.accessor.IWorldRenderer;

public class ESP extends Module {
   private final Map<class_1657, class_2338> burrowedPlayers = new HashMap();
   Value<String> page = (new ValueBuilder<String>()).withDescriptor("Page").withValue("Basic").withModes("Basic", "Break", "Items", "Chorus", "Burrows", "Crawl", "Pearl").register(this);
   Value<Boolean> players;
   Value<Boolean> animals;
   Value<Boolean> monsters;
   Value<Boolean> vehicles;
   Value<Boolean> breakESP;
   Value<Boolean> predictBreak;
   Value<Number> breakRange;
   Value<PagonColor> breakFill;
   Value<PagonColor> breakLine;
   Value<PagonColor> breakFill2;
   Value<PagonColor> breakLine2;
   Value<Boolean> itemTags;
   Value<Boolean> itemBox;
   Value<PagonColor> itemFill;
   Value<PagonColor> itemLine;
   Value<Boolean> chorus;
   Value<String> chorusMode;
   Value<PagonColor> chorusFill;
   Value<PagonColor> chorusLine;
   Value<Number> fadeStart;
   Value<Number> fadeTime;
   Value<Boolean> crawlEsp;
   Value<PagonColor> crawlFill;
   Value<PagonColor> crawlLine;
   Value<Boolean> pearls;
   Value<Boolean> burrowESP;
   Value<PagonColor> burrowFill;
   Value<PagonColor> burrowLine;
   public static List<ChorusPos> chorusPosList = new CopyOnWriteArrayList();
   Map<Integer, String> pearlTags;

   public ESP() {
      super("ESP", Feature.Category.Render);
      this.players = (new ValueBuilder<Boolean>()).withDescriptor("Players").withValue(false).withPageParent(this.page).withPage("Basic").register(this);
      this.animals = (new ValueBuilder<Boolean>()).withDescriptor("Animals").withValue(true).withPageParent(this.page).withPage("Basic").register(this);
      this.monsters = (new ValueBuilder<Boolean>()).withDescriptor("Monsters").withValue(true).withPageParent(this.page).withPage("Basic").register(this);
      this.vehicles = (new ValueBuilder<Boolean>()).withDescriptor("Rideables").withValue(false).withPageParent(this.page).withPage("Basic").register(this);
      this.breakESP = (new ValueBuilder<Boolean>()).withDescriptor("Breaks").withValue(false).withPageParent(this.page).withPage("Break").register(this);
      this.predictBreak = (new ValueBuilder<Boolean>()).withDescriptor("Predictions").withValue(false).withPageParent(this.page).withPage("Break").register(this);
      this.breakRange = (new ValueBuilder<Number>()).withDescriptor("Range", "breakRange").withValue(6).withRange((double)1.0F, (double)15.0F).withPlaces(1).withPageParent(this.page).withPage("Break").register(this);
      this.breakFill = (new ValueBuilder<PagonColor>()).withDescriptor("Fill", "breakFillStart").withValue(new PagonColor(255, 0, 0, 81)).withPageParent(this.page).withPage("Break").register(this);
      this.breakLine = (new ValueBuilder<PagonColor>()).withDescriptor("Line", "breakLineStart").withValue(new PagonColor(255, 0, 0, 255)).withPageParent(this.page).withPage("Break").register(this);
      this.breakFill2 = (new ValueBuilder<PagonColor>()).withDescriptor("Ready Fill", "breakFillReady").withValue(new PagonColor(0, 255, 0, 81)).withPageParent(this.page).withPage("Break").register(this);
      this.breakLine2 = (new ValueBuilder<PagonColor>()).withDescriptor("Ready Line", "breakLineFinished").withValue(new PagonColor(0, 255, 0, 255)).withPageParent(this.page).withPage("Break").register(this);
      this.itemTags = (new ValueBuilder<Boolean>()).withDescriptor("Nametags", "itemNametag").withValue(false).withPageParent(this.page).withPage("Items").register(this);
      this.itemBox = (new ValueBuilder<Boolean>()).withDescriptor("Item Box", "itemBox").withValue(false).withPageParent(this.page).withPage("Items").register(this);
      this.itemFill = (new ValueBuilder<PagonColor>()).withDescriptor("Fill", "itemFill").withValue(new PagonColor(0, 0, 255, 25)).withPageParent(this.page).withPage("Items").register(this);
      this.itemLine = (new ValueBuilder<PagonColor>()).withDescriptor("Line", "itemLine").withValue(new PagonColor(0, 0, 255, 255)).withPageParent(this.page).withPage("Items").register(this);
      this.chorus = (new ValueBuilder<Boolean>()).withDescriptor("Chorus").withValue(false).withPageParent(this.page).withPage("Chorus").register(this);
      this.chorusMode = (new ValueBuilder<String>()).withDescriptor("Mode", "chorusMode").withValue("Tag").withModes("Tag", "BoundingBox").withPageParent(this.page).withPage("Chorus").register(this);
      this.chorusFill = (new ValueBuilder<PagonColor>()).withDescriptor("Fill", "chorusFill").withValue(new PagonColor(255, 0, 255, 25)).withPageParent(this.page).withPage("Chorus").register(this);
      this.chorusLine = (new ValueBuilder<PagonColor>()).withDescriptor("Line", "chorusLine").withValue(new PagonColor(255, 0, 255, 255)).withPageParent(this.page).withPage("Chorus").register(this);
      this.fadeStart = (new ValueBuilder<Number>()).withDescriptor("Fade Start").withValue(500).withRange(1, 5000).withPageParent(this.page).withPage("Chorus").register(this);
      this.fadeTime = (new ValueBuilder<Number>()).withDescriptor("Fade Time").withValue(500).withRange(1, 2000).withPageParent(this.page).withPage("Chorus").register(this);
      this.crawlEsp = (new ValueBuilder<Boolean>()).withDescriptor("Crawls").withValue(false).withPageParent(this.page).withPage("Crawl").register(this);
      this.crawlFill = (new ValueBuilder<PagonColor>()).withDescriptor("Fill", "crawlFill").withValue(new PagonColor(0, 0, 255, 25)).withPageParent(this.page).withPage("Crawl").register(this);
      this.crawlLine = (new ValueBuilder<PagonColor>()).withDescriptor("Line", "crawlLine").withValue(new PagonColor(0, 0, 255, 255)).withPageParent(this.page).withPage("Crawl").register(this);
      this.pearls = (new ValueBuilder<Boolean>()).withDescriptor("Pearls").withValue(false).withPageParent(this.page).withPage("Pearl").register(this);
      this.burrowESP = (new ValueBuilder<Boolean>()).withDescriptor("Burrows").withValue(false).withPageParent(this.page).withPage("Burrows").register(this);
      this.burrowFill = (new ValueBuilder<PagonColor>()).withDescriptor("Fill", "burrowFill").withValue(new PagonColor(255, 62, 62, 25)).withPageParent(this.page).withPage("Burrows").register(this);
      this.burrowLine = (new ValueBuilder<PagonColor>()).withDescriptor("Line", "burrowLine").withValue(new PagonColor(255, 62, 62, 255)).withPageParent(this.page).withPage("Burrows").register(this);
      this.pearlTags = new ConcurrentHashMap();
   }

   @SubscribeEvent
   public void onTick(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if ((Boolean)this.burrowESP.getValue()) {
            this.getPlayers();
         }

      }
   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldEvent event) {
      if (!NullUtils.nullCheck()) {
         if ((Boolean)this.breakESP.getValue()) {
            if (!(Boolean)this.predictBreak.getValue()) {
               Int2ObjectMap<class_3191> blockBreakProgressions = ((IWorldRenderer)mc.field_1769).getBlockBreakingProgressions();
               ObjectIterator var3 = Int2ObjectMaps.fastIterable(blockBreakProgressions).iterator();

               while(var3.hasNext()) {
                  Int2ObjectMap.Entry<class_3191> info = (Int2ObjectMap.Entry)var3.next();
                  class_2338 pos = ((class_3191)info.getValue()).method_13991();
                  double dist = mc.field_1724.method_5707(pos.method_46558());
                  if (!(dist > (double)MathUtil.square(((Number)this.breakRange.getValue()).floatValue()))) {
                     int damage = ((class_3191)info.getValue()).method_13988();
                     class_2680 state = mc.field_1687.method_8320(pos);
                     class_265 outlineShape = state.method_26218(mc.field_1687, pos);
                     if (!outlineShape.method_1110()) {
                        class_238 bb = outlineShape.method_1107();
                        bb = new class_238((double)pos.method_10263() + bb.field_1323, (double)pos.method_10264() + bb.field_1322, (double)pos.method_10260() + bb.field_1321, (double)pos.method_10263() + bb.field_1320, (double)pos.method_10264() + bb.field_1325, (double)pos.method_10260() + bb.field_1324);
                        double x = bb.field_1323 + (bb.field_1320 - bb.field_1323) / (double)2.0F;
                        double y = bb.field_1322 + (bb.field_1325 - bb.field_1322) / (double)2.0F;
                        double z = bb.field_1321 + (bb.field_1324 - bb.field_1321) / (double)2.0F;
                        double sizeX = (double)damage * ((bb.field_1320 - x) / (double)9.0F);
                        double sizeY = (double)damage * ((bb.field_1325 - y) / (double)9.0F);
                        double sizeZ = (double)damage * ((bb.field_1324 - z) / (double)9.0F);
                        class_238 render = new class_238(x - sizeX, y - sizeY, z - sizeZ, x + sizeX, y + sizeY, z + sizeZ);
                        RenderUtil.renderBox(RenderType.FILL, render, ((PagonColor)this.breakFill.getValue()).getColor(), ((PagonColor)this.breakFill.getValue()).getColor());
                        RenderUtil.renderBox(RenderType.LINES, render, ((PagonColor)this.breakLine.getValue()).getColor(), ((PagonColor)this.breakLine.getValue()).getColor());
                     }
                  }
               }
            } else {
               for(BreakData data : BreakManager.INSTANCE.getBreakDatas()) {
                  if (data.canRender()) {
                     this.renderData(data);
                  }
               }
            }
         }

         if ((Boolean)this.chorus.getValue() && !chorusPosList.isEmpty()) {
            for(ChorusPos pos : chorusPosList) {
               int lineA = ((PagonColor)this.chorusFill.getValue()).getColor().getAlpha();
               int fillA = ((PagonColor)this.chorusLine.getValue()).getColor().getAlpha();
               int tagA = 255;
               if (System.currentTimeMillis() - pos.startTime > ((Number)this.fadeStart.getValue()).longValue()) {
                  long time = System.currentTimeMillis() - pos.startTime - ((Number)this.fadeStart.getValue()).longValue();
                  double normal = MathUtil.normalize((double)time, (double)0.0F, ((Number)this.fadeTime.getValue()).doubleValue());
                  normal = class_3532.method_15350(normal, (double)0.0F, (double)1.0F);
                  normal = -normal + (double)1.0F;
                  lineA = (int)(normal * (double)lineA);
                  fillA = (int)(normal * (double)fillA);
                  tagA = (int)(normal * (double)tagA);
               }

               if (lineA == 0 && fillA == 0) {
                  chorusPosList.remove(pos);
               } else {
                  Color fill = ColorUtil.newAlpha(((PagonColor)this.chorusFill.getValue()).getColor(), fillA);
                  Color line = ColorUtil.newAlpha(((PagonColor)this.chorusLine.getValue()).getColor(), lineA);
                  if (((String)this.chorusMode.getValue()).equals("Tag")) {
                     RenderBuffers.schedulePreRender(() -> RenderUtil.drawText("*chorus*", new class_243(pos.x, pos.y, pos.z), 2.0F));
                  } else if (((String)this.chorusMode.getValue()).equals("BoundingBox")) {
                     RenderUtil.renderBox(RenderType.FILL, pos.bb, fill, fill);
                     RenderUtil.renderBox(RenderType.LINES, pos.bb, line, line);
                  }
               }
            }
         }

         if ((Boolean)this.itemTags.getValue() || (Boolean)this.itemBox.getValue()) {
            for(class_1297 entity : mc.field_1687.method_18112()) {
               if (entity instanceof class_1542 && entity.method_5805() && entity.method_5739(mc.field_1724) <= 20.0F) {
                  class_238 bb = entity.method_5829();
                  if ((Boolean)this.itemBox.getValue()) {
                     RenderUtil.renderBox(RenderType.FILL, bb, ((PagonColor)this.itemFill.getValue()).getColor(), ((PagonColor)this.itemFill.getValue()).getColor());
                     RenderUtil.renderBox(RenderType.LINES, bb, ((PagonColor)this.itemLine.getValue()).getColor(), ((PagonColor)this.itemLine.getValue()).getColor());
                  }

                  if ((Boolean)this.itemTags.getValue()) {
                     RenderBuffers.schedulePreRender(() -> RenderUtil.drawText(entity.method_5476().getString() + (((class_1542)entity).method_6983().method_7947() != 1 ? " x" + ((class_1542)entity).method_6983().method_7947() : ""), bb.method_1005(), 1.4F));
                  }
               }
            }
         }

         if ((Boolean)this.crawlEsp.getValue() && mc.field_1724.method_20448()) {
            class_2338 playerPos = mc.field_1724.method_24515();

            for(class_2350 direction : class_2350.values()) {
               if (!direction.method_10166().method_10178()) {
                  class_2338 pos = playerPos.method_10093(direction);
                  if (!mc.field_1687.method_8320(pos).method_45474() && mc.field_1687.method_8320(pos.method_10084()).method_26215() && mc.field_1687.method_8320(pos.method_10084().method_10084()).method_26215()) {
                     class_238 box = new class_238(pos);
                     box = new class_238(box.field_1323, box.field_1325 - 0.2, box.field_1321, box.field_1320, box.field_1325, box.field_1324);
                     RenderUtil.renderBox(RenderType.FILL, box, ((PagonColor)this.crawlFill.getValue()).getColor(), ((PagonColor)this.crawlFill.getValue()).getColor());
                     RenderUtil.renderBox(RenderType.LINES, box, ((PagonColor)this.crawlLine.getValue()).getColor(), ((PagonColor)this.crawlLine.getValue()).getColor());
                  }
               }
            }
         }

         if ((Boolean)this.pearls.getValue()) {
            for(Map.Entry<Integer, String> tag : this.pearlTags.entrySet()) {
               class_1297 entity = mc.field_1687.method_8469((Integer)tag.getKey());
               if (!(entity instanceof class_1684)) {
                  this.pearlTags.remove(tag.getKey());
               } else {
                  class_238 bb = Interpolator.getInterpolatedEntityBox(entity);
                  RenderBuffers.schedulePreRender(() -> RenderUtil.drawText((String)tag.getValue(), bb.method_1005(), 1.6F));
               }
            }
         }

         if ((Boolean)this.burrowESP.getValue()) {
            for(Map.Entry<class_1657, class_2338> entry : this.burrowedPlayers.entrySet()) {
               class_238 box = new class_238((class_2338)entry.getValue());
               RenderUtil.renderBox(RenderType.FILL, box, ((PagonColor)this.burrowFill.getValue()).getColor(), ((PagonColor)this.burrowFill.getValue()).getColor());
               RenderUtil.renderBox(RenderType.LINES, box, ((PagonColor)this.burrowLine.getValue()).getColor(), ((PagonColor)this.burrowLine.getValue()).getColor());
            }
         }

      }
   }

   private void getPlayers() {
      this.burrowedPlayers.clear();

      for(class_1657 player : mc.field_1687.method_18456()) {
         if (player != mc.field_1724 && !FriendManager.INSTANCE.isFriend(player) && HoleUtils.isBurrowed(player)) {
            this.burrowedPlayers.put(player, player.method_24515());
         }
      }

   }

   public void renderData(BreakData data) {
      float maxBreak = 1.0F;
      float color = maxBreak - 0.3F;
      class_2338 mining = data.getPos();
      class_265 outlineShape = class_259.method_1077();
      outlineShape = BlockUtils.getBlockState(data.getPos()).method_26218(mc.field_1687, mining);
      outlineShape = outlineShape.method_1110() ? class_259.method_1077() : outlineShape;
      class_238 render1 = outlineShape.method_1107();
      class_238 render = new class_238((double)mining.method_10263() + render1.field_1323, (double)mining.method_10264() + render1.field_1322, (double)mining.method_10260() + render1.field_1321, (double)mining.method_10263() + render1.field_1320, (double)mining.method_10264() + render1.field_1325, (double)mining.method_10260() + render1.field_1324);
      class_243 center = render.method_1005();
      float scale = class_3532.method_15363((float)(System.currentTimeMillis() - data.getTimeout().getStartTime()) / 1000.0F, 0.0F, 1.0F);
      double dx = (render1.field_1320 - render1.field_1323) / (double)2.0F;
      double dy = (render1.field_1325 - render1.field_1322) / (double)2.0F;
      double dz = (render1.field_1324 - render1.field_1321) / (double)2.0F;
      class_238 scaled = (new class_238(center, center)).method_1009(dx * (double)scale, dy * (double)scale, dz * (double)scale);
      Color fillColorInterp = ((PagonColor)this.breakFill.getValue()).getColor();
      Color lineColorInterp = ((PagonColor)this.breakLine.getValue()).getColor();
      if (data.getBestDamage() > color) {
         float progress = (float)class_3532.method_15350(MathUtil.normalize((double)(data.getBestDamage() - color), (double)0.0F, 0.3), (double)0.0F, (double)1.0F);
         fillColorInterp = ColorUtil.interpolate(progress, ((PagonColor)this.breakFill2.getValue()).getColor(), fillColorInterp);
         lineColorInterp = ColorUtil.interpolate(progress, ((PagonColor)this.breakLine2.getValue()).getColor(), lineColorInterp);
      }

      RenderUtil.renderBox(RenderType.FILL, scaled, fillColorInterp, fillColorInterp);
      RenderUtil.renderBox(RenderType.LINES, scaled, lineColorInterp, lineColorInterp);
   }

   @SubscribeEvent
   public void onSpawnEntity(EntityEvent.Add event) {
      if (!NullUtils.nullCheck()) {
         class_1297 var3 = event.getEntity();
         if (var3 instanceof class_1684) {
            class_1684 pearl = (class_1684)var3;
            class_1297 player = mc.field_1687.method_18460(event.getEntity(), (double)3.0F);
            if (player != null) {
               this.pearlTags.put(pearl.method_5628(), player.method_5477().getString());
            }
         }

      }
   }

   @SubscribeEvent
   public void onRemoveEntity(EntityEvent.Remove event) {
      if (!NullUtils.nullCheck()) {
         class_1297 var3 = event.getEntity();
         if (var3 instanceof class_1684) {
            class_1684 pearl = (class_1684)var3;
            if (this.pearlTags.get(pearl.method_5628()) != null) {
               this.pearlTags.remove(pearl.method_5628());
            }
         }

      }
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Receive event) {
      if (!NullUtils.nullCheck()) {
         class_2596 var3 = event.getPacket();
         if (var3 instanceof class_2767) {
            class_2767 packet = (class_2767)var3;
            if ((Boolean)this.chorus.getValue() && packet.method_11894().comp_349() == class_3417.field_14890) {
               class_238 playerBB = mc.field_1724.method_5829();
               double widthX = (playerBB.field_1320 - playerBB.field_1323) / (double)2.0F;
               double height = playerBB.field_1325 - playerBB.field_1322;
               double widthZ = (playerBB.field_1324 - playerBB.field_1321) / (double)2.0F;
               class_238 renderBB = new class_238(packet.method_11890() - widthX, packet.method_11889(), packet.method_11893() - widthZ, packet.method_11890() + widthX, packet.method_11889() + height, packet.method_11893() + widthZ);
               chorusPosList.add(new ChorusPos(renderBB, System.currentTimeMillis(), packet.method_11890(), packet.method_11889(), packet.method_11893()));
            }
         }

      }
   }

   @SubscribeEvent
   public void onEntityOutline(EntityOutlineEvent event) {
      if (this.checkESP(event.getEntity())) {
         event.setCancelled(true);
      }

   }

   @SubscribeEvent
   public void onTeamColor(TeamColorEvent event) {
      if (this.checkESP(event.getEntity())) {
         event.setCancelled(true);
         event.setColor(this.getESPColor(event.getEntity()).getRGB());
      }

   }

   public Color getESPColor(class_1297 entity) {
      if (entity instanceof class_1657) {
         return Hud.getTextColor(0);
      } else if (EntityUtils.isMonster(entity)) {
         return Color.RED;
      } else {
         return !EntityUtils.isNeutral(entity) && !EntityUtils.isPassive(entity) ? null : Color.GREEN;
      }
   }

   public boolean checkESP(class_1297 entity) {
      return entity != mc.field_1724 && entity instanceof class_1657 && (Boolean)this.players.getValue() || EntityUtils.isMonster(entity) && (Boolean)this.monsters.getValue() || (EntityUtils.isNeutral(entity) || EntityUtils.isPassive(entity)) && (Boolean)this.animals.getValue() || EntityUtils.isVehicle(entity) && (Boolean)this.vehicles.getValue();
   }

   private double getProgress(double delta) {
      return (double)1.0F - Math.pow((double)1.0F - delta, (double)5.0F);
   }

   private class_238 getBox(class_2338 pos, double progress) {
      return new class_238((double)pos.method_10263() + (double)0.5F - progress / (double)2.0F, (double)pos.method_10264() + (double)0.5F - progress / (double)2.0F, (double)pos.method_10260() + (double)0.5F - progress / (double)2.0F, (double)pos.method_10263() + (double)0.5F + progress / (double)2.0F, (double)pos.method_10264() + (double)0.5F + progress / (double)2.0F, (double)pos.method_10260() + (double)0.5F + progress / (double)2.0F);
   }

   public String getDescription() {
      return "ESP: see stuff you wouldnt be normally able to see and stuff";
   }

   class ChorusPos {
      public class_238 bb;
      public long startTime;
      public double x;
      public double y;
      public double z;

      public ChorusPos(class_238 bb, long startTime, double x, double y, double z) {
         this.bb = bb;
         this.startTime = startTime;
         this.x = x;
         this.y = y;
         this.z = z;
      }
   }
}
