package org.itsmahir.pagon.impl.features.modules.render;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2703;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_7828;
import net.minecraft.class_2703.class_5893;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.event.events.world.EntityEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.FriendManager;
import org.itsmahir.pagon.api.management.PopManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Pair;
import org.itsmahir.pagon.api.utils.chat.ChatMessage;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.color.TextSection;
import org.itsmahir.pagon.api.utils.render.Interpolator;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.world.RenderType;
import org.itsmahir.pagon.api.utils.render.world.buffers.RenderBuffers;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.mixin.accessor.ILimbAnimator;
import org.lwjgl.opengl.GL11;

public class LogoutSpots extends Module {
   Value<PagonColor> line = (new ValueBuilder<PagonColor>()).withDescriptor("Line Color").withValue(new PagonColor(255, 255, 255, 255)).register(this);
   public Value<Boolean> pops = (new ValueBuilder<Boolean>()).withDescriptor("Pops").withValue(true).register(this);
   public Value<PagonColor> normalColor = (new ValueBuilder<PagonColor>()).withDescriptor("Normal Color").withValue(new PagonColor(0, 255, 255, 255)).register(this);
   public Value<PagonColor> friendsColor = (new ValueBuilder<PagonColor>()).withDescriptor("Friends Color").withValue(new PagonColor(0, 255, 255, 255)).register(this);
   public Value<PagonColor> borderColor = (new ValueBuilder<PagonColor>()).withDescriptor("Border Color").withValue(new PagonColor(255, 0, 0, 255)).register(this);
   public static List<LoggedPlayer> players = new CopyOnWriteArrayList();
   Map<UUID, Pair<class_1657, Long>> lastPlayers = new ConcurrentHashMap();

   public LogoutSpots() {
      super("LogoutESP", Feature.Category.Render);
   }

   @SubscribeEvent
   public void onTick(TickEvent.ClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         for(Map.Entry<UUID, Pair<class_1657, Long>> entry : this.lastPlayers.entrySet()) {
            long time = System.currentTimeMillis() - (Long)((Pair)entry.getValue()).value();
            if (time > 200L) {
               this.lastPlayers.remove(entry.getKey());
            }
         }

      }
   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldEvent event) {
      if (!NullUtils.nullCheck()) {
         players.forEach((loggedPlayer) -> this.renderEntity(event.getMatrices(), loggedPlayer.player, loggedPlayer, event));
      }
   }

   @SubscribeEvent
   public void onEntityRemove(EntityEvent.Remove event) {
      class_1297 var3 = event.getEntity();
      if (var3 instanceof class_1657 entity) {
         this.lastPlayers.put(entity.method_5667(), new Pair(entity, System.currentTimeMillis()));
      }

   }

   public void onDisable() {
      super.onDisable();
      if (!NullUtils.nullCheck()) {
         players.clear();
      }
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Receive event) {
      if (!NullUtils.nullCheck()) {
         class_2596 var3 = event.getPacket();
         if (var3 instanceof class_2703) {
            class_2703 pac = (class_2703)var3;
            if (pac.method_46327().contains(class_5893.field_29136)) {
               for(class_2703.class_2705 ple : pac.method_46330()) {
                  for(LoggedPlayer player : players) {
                     if (player.id.equals(ple.comp_1107().getId())) {
                        String var10002 = String.valueOf(class_124.field_1075);
                        ChatUtils.sendMessage(new ChatMessage("[Logout Spots] " + var10002 + player.playerName + String.valueOf(class_124.field_1070) + " logged back in!", false, 99922));
                        players.remove(player);
                     }
                  }
               }
            }
         }

         var3 = event.getPacket();
         if (var3 instanceof class_7828) {
            class_7828 pac = (class_7828)var3;

            for(UUID uuid2 : pac.comp_1105()) {
               class_1657 playerEntity = mc.field_1687.method_18470(uuid2);
               Pair<class_1657, Long> pair = (Pair)this.lastPlayers.get(uuid2);
               if (playerEntity == null && pair != null) {
                  playerEntity = pair.key();
               }

               if (playerEntity != null) {
                  if (playerEntity == mc.field_1724) {
                     return;
                  }

                  class_1657 entity = new class_1657(mc.field_1687, class_2338.field_10980, playerEntity.method_43078(), new GameProfile(playerEntity.method_5667(), playerEntity.method_5477().getString())) {
                     public boolean method_7325() {
                        return false;
                     }

                     public boolean method_7337() {
                        return false;
                     }
                  };
                  entity.method_5719(playerEntity);
                  entity.field_6283 = playerEntity.method_43078();
                  entity.field_6241 = playerEntity.method_5791();
                  entity.field_6251 = playerEntity.field_6251;
                  entity.field_6279 = playerEntity.field_6279;
                  entity.method_5660(playerEntity.method_5715());
                  entity.field_42108.method_48567(playerEntity.field_42108.method_48566());
                  entity.method_6033(playerEntity.method_6032());
                  entity.method_6073(playerEntity.method_6067());
                  ((ILimbAnimator)entity.field_42108).setLimbPos(playerEntity.field_42108.method_48569());
                  players.add(new LoggedPlayer(entity, uuid2));
                  this.lastPlayers.remove(uuid2);
                  break;
               }
            }
         }

      }
   }

   private void renderEntity(class_4587 matrices, class_1309 entity, LoggedPlayer player, RenderWorldEvent event) {
      RenderUtil.renderBox(RenderType.LINES, entity.method_5829(), ((PagonColor)this.line.getValue()).getColor(), ((PagonColor)this.line.getValue()).getColor());
      if (player != null) {
         RenderBuffers.scheduleRender(() -> {
            this.renderWaypoint(player, event);
            RenderSystem.enableBlend();
         });
      }

   }

   private void renderWaypoint(LoggedPlayer loc, RenderWorldEvent event) {
      class_243 interpolate = Interpolator.getInterpolatedEyePos(mc.method_1560(), event.getTickDelta());
      class_4184 camera = mc.field_1773.method_19418();
      class_243 pos = camera.method_19326();
      double dx = pos.method_10216() - interpolate.method_10216() - loc.player.method_23317();
      double dy = pos.method_10214() - interpolate.method_10214() - (loc.player.method_23318() + (double)loc.player.method_17682() + (double)(loc.player.method_5715() ? 0.4F : 0.43F));
      double dz = pos.method_10215() - interpolate.method_10215() - loc.player.method_23321();
      Math.sqrt(dx * dx + dy * dy + dz * dz);
      TextSection[] text = new TextSection[1];
      text[0] = new TextSection(this.renderEntityName(loc.player, loc), ColorUtil.newAlpha(((PagonColor)this.line.getValue()).getColor(), 255));
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      GL11.glDepthFunc(519);
      RenderUtil.drawWaypoint(text, loc.player.method_23317(), loc.player.method_23318() + (double)loc.player.method_17682() + (double)(loc.player.method_5715() ? 0.4F : 0.43F), loc.player.method_23321(), mc.field_1773.method_19418(), ((PagonColor)this.borderColor.getValue()).getColor());
      GL11.glDepthFunc(515);
      RenderSystem.disableBlend();
   }

   private String renderEntityName(class_1657 entityPlayer, LoggedPlayer player) {
      String var10000 = String.valueOf(class_124.field_1061);
      String s = var10000 + player.playerName + " logout";
      String s2 = class_124.field_1060.toString();
      double ceil;
      if ((ceil = Math.ceil((double)(entityPlayer.method_6032() + entityPlayer.method_6067()))) > (double)0.0F) {
         if (entityPlayer.method_6032() + entityPlayer.method_6067() <= 5.0F) {
            s2 = class_124.field_1061.toString();
         } else if (entityPlayer.method_6032() + entityPlayer.method_6067() > 5.0F && entityPlayer.method_6032() + entityPlayer.method_6067() <= 10.0F) {
            s2 = class_124.field_1065.toString();
         } else if (entityPlayer.method_6032() + entityPlayer.method_6067() > 10.0F && entityPlayer.method_6032() + entityPlayer.method_6067() <= 15.0F) {
            s2 = class_124.field_1054.toString();
         } else if (entityPlayer.method_6032() + entityPlayer.method_6067() > 15.0F && entityPlayer.method_6032() + entityPlayer.method_6067() <= 20.0F) {
            s2 = class_124.field_1077.toString();
         } else if (entityPlayer.method_6032() + entityPlayer.method_6067() > 20.0F) {
            s2 = class_124.field_1060.toString();
         }
      } else {
         s2 = class_124.field_1079.toString();
      }

      int popsForPlayer = PopManager.INSTANCE.getPops(player.playerName);
      var10000 = String.valueOf(class_124.field_1061);
      String popstring = var10000 + " (" + String.valueOf(class_124.field_1068) + class_3532.method_15375(mc.field_1724.method_5739(entityPlayer)) + "m" + String.valueOf(class_124.field_1061) + ")";
      if ((Boolean)this.pops.getValue() && popsForPlayer >= 1) {
         var10000 = String.valueOf(class_124.field_1061);
         popstring = var10000 + " (" + String.valueOf(class_124.field_1068) + class_3532.method_15375(mc.field_1724.method_5739(entityPlayer)) + "m" + String.valueOf(class_124.field_1075) + " -" + popsForPlayer + String.valueOf(class_124.field_1061) + ")";
      }

      return (new StringBuilder()).insert(0, s).append(s2).append(" ").append(ceil > (double)0.0F ? (int)ceil : "0").append(popstring).toString();
   }

   private Color renderPing(class_1657 entityPlayer) {
      if (FriendManager.INSTANCE.isFriend(entityPlayer)) {
         return ((PagonColor)this.friendsColor.getValue()).getColor();
      } else {
         return entityPlayer.method_5767() ? new Color(128, 128, 128) : ((PagonColor)this.normalColor.getValue()).getColor();
      }
   }

   public String getDescription() {
      return "LogoutESP: highlights logged players";
   }

   class LoggedPlayer {
      private final class_1657 player;
      public String playerName;
      public class_238 bb;
      public UUID id;

      public LoggedPlayer(class_1657 player, UUID id) {
         this.player = player;
         this.id = id;
         this.bb = player.method_5829();
         this.playerName = player.method_5477().getString();
      }
   }
}
