package org.itsmahir.pagon.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_757;
import net.minecraft.class_761;
import net.minecraft.class_765;
import net.minecraft.class_7833;
import net.minecraft.class_898;
import net.minecraft.class_9779;
import net.minecraft.class_293.class_5596;
import org.itsmahir.pagon.api.event.events.render.RenderShaderEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.utils.render.world.buffers.RenderBuffers;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.features.modules.render.Freecam;
import org.itsmahir.pagon.impl.features.modules.render.SkyBox;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_761.class})
public class MixinWorldRenderer implements IMinecraft {
   @Shadow
   @Final
   private class_898 field_4109;

   @Inject(
      method = {"render"},
      at = {@At("TAIL")}
   )
   private void hookRender(class_9779 tickCounter, boolean renderBlockOutline, class_4184 camera, class_757 gameRenderer, class_765 lightmapTextureManager, Matrix4f matrix4f, Matrix4f matrix4f2, CallbackInfo ci, @Local class_4587 matrixStack) {
      RenderBuffers.preRender();
      class_243 pos = camera.method_19326().method_22882();
      matrixStack.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
      matrixStack.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
      matrixStack.method_22904(pos.field_1352, pos.field_1351, pos.field_1350);
      (new RenderWorldEvent(matrixStack, tickCounter.method_60637(false))).post();
      RenderBuffers.postRender();
      RenderBuffers.process();
   }

   @Inject(
      method = {"render"},
      at = {@At("RETURN")}
   )
   private void hookRender$1(class_9779 tickCounter, boolean renderBlockOutline, class_4184 camera, class_757 gameRenderer, class_765 lightmapTextureManager, Matrix4f matrix4f, Matrix4f matrix4f2, CallbackInfo ci) {
      class_4587 matrixStack = new class_4587();
      matrixStack.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
      matrixStack.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
      (new RenderShaderEvent(matrixStack, tickCounter.method_60637(true))).post();
   }

   @ModifyArg(
      method = {"render"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/WorldRenderer;setupTerrain(Lnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/Frustum;ZZ)V"
),
      index = 3
   )
   private boolean renderSetupTerrainModifyArg(boolean spectator) {
      return Freecam.INSTANCE.isEnabled() || spectator;
   }

   @Inject(
      method = {"renderSky"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookRenderSkybox(Matrix4f matrix, Matrix4f projectionMatrix, float tickDelta, class_4184 camera, boolean thickFog, Runnable fogCallback, CallbackInfo ci) {
      if (SkyBox.INSTANCE != null && SkyBox.INSTANCE.isEnabled() && SkyBox.INSTANCE.isTextureMode()) {
         fogCallback.run();
         ci.cancel();
         if (!thickFog) {
            RenderSystem.depthMask(false);
            RenderSystem.disableDepthTest();
            RenderSystem.disableCull();
            RenderSystem.setShader(class_757::method_34543);
            RenderSystem.setShaderTexture(0, SkyBox.SKYBOX_TEXTURE);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            float r = 1.0F;
            float g = 1.0F;
            float b = 1.0F;
            class_4587 matrices = new class_4587();
            matrices.method_34425(matrix);
            Matrix4f skyMatrix = matrices.method_23760().method_23761();
            class_289 tessellator = RenderSystem.renderThreadTesselator();
            class_287 buffer = tessellator.method_60827(class_5596.field_27382, class_290.field_1575);
            this.renderSkyTile(buffer, skyMatrix, -1.0F, -1.0F, -1.0F, 1.0F, -1.0F, -1.0F, 1.0F, 1.0F, -1.0F, -1.0F, 1.0F, -1.0F, 2, 0, r, g, b);
            this.renderSkyTile(buffer, skyMatrix, 1.0F, -1.0F, 1.0F, -1.0F, -1.0F, 1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1, 1, r, g, b);
            this.renderSkyTile(buffer, skyMatrix, -1.0F, -1.0F, 1.0F, -1.0F, -1.0F, -1.0F, -1.0F, 1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2, 1, r, g, b);
            this.renderSkyTile(buffer, skyMatrix, 1.0F, -1.0F, -1.0F, 1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, -1.0F, 0, 1, r, g, b);
            this.renderSkyTile(buffer, skyMatrix, 1.0F, 1.0F, 1.0F, -1.0F, 1.0F, 1.0F, -1.0F, 1.0F, -1.0F, 1.0F, 1.0F, -1.0F, 1, 0, r, g, b);
            this.renderSkyTile(buffer, skyMatrix, 1.0F, -1.0F, -1.0F, -1.0F, -1.0F, -1.0F, -1.0F, -1.0F, 1.0F, 1.0F, -1.0F, 1.0F, 0, 0, r, g, b);
            class_286.method_43433(buffer.method_60800());
            RenderSystem.enableCull();
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask(true);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         }
      }
   }

   @Inject(
      method = {"renderClouds"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookRenderClouds(class_4587 matrices, Matrix4f matrix, Matrix4f projectionMatrix, float tickDelta, double cameraX, double cameraY, double cameraZ, CallbackInfo ci) {
      if (SkyBox.INSTANCE != null && SkyBox.INSTANCE.isEnabled() && SkyBox.INSTANCE.isTextureMode()) {
         ci.cancel();
      }

   }

   @Unique
   private void renderSkyTile(class_287 buffer, Matrix4f matrix, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4, int tileX, int tileY, float r, float g, float b) {
      float size = 100.0F;
      float inset = 5.0E-4F;
      float u0 = (float)tileX / 3.0F + inset;
      float v0 = (float)tileY / 2.0F + inset;
      float u1 = (float)(tileX + 1) / 3.0F - inset;
      float v1 = (float)(tileY + 1) / 2.0F - inset;
      buffer.method_22918(matrix, x1 * size, y1 * size, z1 * size).method_22913(u0, v1).method_22915(r, g, b, 1.0F);
      buffer.method_22918(matrix, x2 * size, y2 * size, z2 * size).method_22913(u1, v1).method_22915(r, g, b, 1.0F);
      buffer.method_22918(matrix, x3 * size, y3 * size, z3 * size).method_22913(u1, v0).method_22915(r, g, b, 1.0F);
      buffer.method_22918(matrix, x4 * size, y4 * size, z4 * size).method_22913(u0, v0).method_22915(r, g, b, 1.0F);
   }
}
