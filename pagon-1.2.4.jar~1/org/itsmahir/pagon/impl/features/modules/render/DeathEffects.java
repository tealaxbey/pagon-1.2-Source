package org.itsmahir.pagon.impl.features.modules.render;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1538;
import net.minecraft.class_1657;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2663;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_3417;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_5602;
import net.minecraft.class_5617;
import net.minecraft.class_591;
import net.minecraft.class_742;
import net.minecraft.class_757;
import net.minecraft.class_7833;
import net.minecraft.class_8685;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_8685.class_7920;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.WireframeEntityRenderer;
import org.itsmahir.pagon.api.utils.render.world.RenderType;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.mixin.accessor.ILimbAnimator;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class DeathEffects extends Module {
   public static DeathEffects INSTANCE;
   private static final class_2960 CIRCLE_TEXTURE = class_2960.method_60655("pagon", "textures/circle.png");
   public Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Thunder").withModes("Thunder", "Circle", "Ghost").register(this);
   private final CopyOnWriteArrayList<DeathCircle> circles = new CopyOnWriteArrayList();
   private final CopyOnWriteArrayList<DeathGhost> ghosts = new CopyOnWriteArrayList();

   public DeathEffects() {
      super("DeathEffects", Feature.Category.Render);
      INSTANCE = this;
   }

   @SubscribeEvent
   private void onDeath(PacketEvent.Receive event) {
      class_2596 var3 = event.getPacket();
      if (var3 instanceof class_2663 pac) {
         if (pac.method_11470() == 3) {
            class_1297 var4 = pac.method_11469(mc.field_1687);
            if (!(var4 instanceof class_1657)) {
               return;
            }

            class_1657 player = (class_1657)var4;
            this.doEffect(player);
         }
      }

   }

   private void doEffect(class_1657 player) {
      switch ((String)this.mode.getValue()) {
         case "Thunder" -> this.doThunder(player);
         case "Circle" -> this.doCircle(player);
         case "Ghost" -> this.doGhost(player);
      }

   }

   private void doThunder(class_1657 player) {
      class_1538 lightning = new class_1538(class_1299.field_6112, mc.field_1687);
      lightning.method_24203(player.method_23317(), player.method_23318(), player.method_23321());
      mc.field_1687.method_53875(lightning);
      mc.field_1724.method_5783(class_3417.field_14865, 0.3F, 1.0F);
   }

   private void doCircle(class_1657 player) {
      this.circles.add(new DeathCircle(player.method_19538()));
   }

   private void doGhost(class_1657 player) {
      class_1657 ghost = new class_1657(mc.field_1687, class_2338.field_10980, player.method_43078(), new GameProfile(player.method_5667(), player.method_5477().getString())) {
         public boolean method_7325() {
            return false;
         }

         public boolean method_7337() {
            return false;
         }
      };
      ghost.method_5719(player);
      ghost.field_6283 = player.method_43078();
      ghost.field_6241 = player.method_5791();
      ghost.field_6251 = player.field_6251;
      ghost.field_6279 = player.field_6279;
      ghost.method_5660(player.method_5715());
      ghost.field_42108.method_48567(player.field_42108.method_48566());
      ((ILimbAnimator)ghost.field_42108).setLimbPos(player.field_42108.method_48569());
      this.ghosts.add(new DeathGhost(ghost, ((class_742)player).method_52814()));
   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldEvent event) {
      if (!NullUtils.nullCheck()) {
         float tickDelta = event.getTickDelta();
         class_4587 matrices = event.getMatrices();
         if (((String)this.mode.getValue()).equals("Circle")) {
            this.renderCircles(matrices, tickDelta);
         }

         if (((String)this.mode.getValue()).equals("Ghost")) {
            this.renderGhosts(matrices, tickDelta);
         }

      }
   }

   private void renderCircles(class_4587 matrices, float tickDelta) {
      long now = System.currentTimeMillis();
      Iterator<DeathCircle> it = this.circles.iterator();

      while(it.hasNext()) {
         DeathCircle c = (DeathCircle)it.next();
         long elapsed = now - c.startTime;
         if (elapsed > 2000L) {
            it.remove();
         } else {
            float progress = (float)elapsed / 2000.0F;
            float alpha;
            if (progress < 0.2F) {
               alpha = progress / 0.2F;
            } else if (progress > 0.8F) {
               alpha = (1.0F - progress) / 0.2F;
            } else {
               alpha = 1.0F;
            }

            alpha = class_3532.method_15363(alpha, 0.0F, 1.0F);
            this.renderCircle(matrices, c.pos, alpha);
         }
      }

   }

   private void renderCircle(class_4587 matrices, class_243 pos, float alpha) {
      class_4587 localMatrices = RenderUtil.matrixFrom(pos.field_1352, pos.field_1351 + (double)0.1F, pos.field_1350);
      localMatrices.method_22903();
      localMatrices.method_22907(class_7833.field_40716.rotationDegrees(-mc.field_1773.method_19418().method_19330()));
      localMatrices.method_22907(class_7833.field_40714.rotationDegrees(mc.field_1773.method_19418().method_19329()));
      float size = 2.0F;
      RenderSystem.setShaderTexture(0, CIRCLE_TEXTURE);
      RenderSystem.setShader(class_757::method_34543);
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.depthMask(false);
      RenderSystem.enableDepthTest();
      Matrix4f matrix = localMatrices.method_23760().method_23761();
      class_289 tessellator = RenderSystem.renderThreadTesselator();
      class_287 buffer = tessellator.method_60827(class_5596.field_27382, class_290.field_1575);
      buffer.method_22918(matrix, -size, -size, 0.0F).method_22913(0.0F, 1.0F).method_22915(1.0F, 1.0F, 1.0F, alpha);
      buffer.method_22918(matrix, size, -size, 0.0F).method_22913(1.0F, 1.0F).method_22915(1.0F, 1.0F, 1.0F, alpha);
      buffer.method_22918(matrix, size, size, 0.0F).method_22913(1.0F, 0.0F).method_22915(1.0F, 1.0F, 1.0F, alpha);
      buffer.method_22918(matrix, -size, size, 0.0F).method_22913(0.0F, 0.0F).method_22915(1.0F, 1.0F, 1.0F, alpha);
      class_286.method_43433(buffer.method_60800());
      RenderSystem.depthMask(true);
      RenderSystem.disableBlend();
      localMatrices.method_22909();
   }

   private void renderGhosts(class_4587 matrices, float tickDelta) {
      long now = System.currentTimeMillis();
      Iterator<DeathGhost> it = this.ghosts.iterator();

      while(it.hasNext()) {
         DeathGhost g = (DeathGhost)it.next();
         long elapsed = now - g.startTime;
         if (elapsed > 1000L) {
            it.remove();
         } else {
            float progress = (float)elapsed / 1000.0F;
            this.renderGhost(matrices, g, progress, tickDelta);
         }
      }

   }

   private void renderGhost(class_4587 matrices, DeathGhost g, float progress, float tickDelta) {
      class_1657 entity = g.player;
      double x = entity.method_23317();
      double y = entity.method_23318() + (double)progress * (double)2.0F;
      double z = entity.method_23321();
      float alpha = 1.0F - progress;
      g.model.field_3482.field_3665 = false;
      g.model.field_3479.field_3665 = false;
      g.model.field_3484.field_3665 = false;
      g.model.field_3486.field_3665 = false;
      g.model.field_3483.field_3665 = false;
      g.model.field_3394.field_3665 = false;
      class_4587 localMatrices = RenderUtil.matrixFrom(x, y, z);
      localMatrices.method_22903();
      localMatrices.method_22907(class_7833.field_40716.rotation((float)Math.toRadians((double)(180.0F - entity.field_6283))));
      localMatrices.method_22905(-1.0F, -1.0F, 1.0F);
      localMatrices.method_22905(1.6F, 1.8F, 1.6F);
      localMatrices.method_46416(0.0F, -1.501F, 0.0F);
      g.model.method_17086(entity, entity.field_42108.method_48569(), entity.field_42108.method_48566(), tickDelta);
      float limbSpeed = Math.min(entity.field_42108.method_48566(), 1.0F);
      g.model.method_17087(entity, entity.field_42108.method_48569(), limbSpeed, (float)entity.field_6012, entity.field_6241 - entity.field_6283, entity.method_36455());
      int fillA = (int)(60.0F * alpha);
      int lineA = (int)(255.0F * alpha);
      RenderSystem.setShaderTexture(0, g.texture);
      WireframeEntityRenderer.renderModel(localMatrices, g.model, RenderType.BOTH, ColorUtil.newAlpha(new Color(0, 255, 218), fillA), ColorUtil.newAlpha(new Color(255, 255, 255), lineA));
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      localMatrices.method_22909();
   }

   public String getDescription() {
      return "DeathEffects: visual effects when someone dies";
   }

   private static class DeathCircle {
      final class_243 pos;
      final long startTime;

      DeathCircle(class_243 pos) {
         this.pos = pos;
         this.startTime = System.currentTimeMillis();
      }
   }

   private static class DeathGhost {
      final class_1657 player;
      final class_591<class_1657> model;
      final class_2960 texture;
      final long startTime;

      DeathGhost(class_1657 player, class_8685 skin) {
         this.player = player;
         this.model = new class_591((new class_5617.class_5618(IMinecraft.mc.method_1561(), IMinecraft.mc.method_1480(), IMinecraft.mc.method_1541(), IMinecraft.mc.method_1561().method_43336(), IMinecraft.mc.method_1478(), IMinecraft.mc.method_31974(), IMinecraft.mc.field_1772)).method_32167(class_5602.field_27577), skin.comp_1629().equals(class_7920.field_41122));
         this.model.method_2838().method_41924(new Vector3f(-0.3F, -0.3F, -0.3F));
         this.texture = skin.comp_1626();
         this.startTime = System.currentTimeMillis();
      }
   }
}
