package org.itsmahir.pagon.impl.features.modules.client;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_1041;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_757;
import net.minecraft.class_293.class_5596;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;

public class Renders extends Module {
   public static Renders INSTANCE;
   public Value<Boolean> depressed = (new ValueBuilder<Boolean>()).withDescriptor("Depressed").withValue(false).register(this);
   public Value<String> msaa = (new ValueBuilder<String>()).withDescriptor("MSAA").withValue("None").withModes("None", "X2", "X4", "X8", "X16").register(this);
   public Value<Boolean> bps = (new ValueBuilder<Boolean>()).withDescriptor("BPS").withValue(false).register(this);
   private boolean wasBpsActive = false;
   private int savedWindowX = -1;
   private int savedWindowY = -1;
   private int savedWindowWidth = -1;
   private int savedWindowHeight = -1;
   private String lastMsaaMode = "None";

   public Renders() {
      super("Renders", Feature.Category.Client);
      INSTANCE = this;
   }

   public String getDescription() {
      return "Modifies general render settings";
   }

   public int getMsaaSamples() {
      if (!this.isEnabled()) {
         return 0;
      } else {
         byte var10000;
         switch ((String)this.msaa.getValue()) {
            case "X2" -> var10000 = 2;
            case "X4" -> var10000 = 4;
            case "X8" -> var10000 = 8;
            case "X16" -> var10000 = 16;
            default -> var10000 = 0;
         }

         return var10000;
      }
   }

   public void onEnable() {
      super.onEnable();
      this.lastMsaaMode = this.msaa.getValue();
   }

   public void onDisable() {
      super.onDisable();
      if (this.wasBpsActive) {
         this.restoreWindow();
         this.wasBpsActive = false;
      }

      if (class_310.method_1551().method_22683() != null) {
         GL11.glDisable(32925);
      }

   }

   @SubscribeEvent
   public void onTick(TickEvent.ClientTickEvent event) {
      class_310 mc = class_310.method_1551();
      if (mc.method_22683() != null) {
         this.handleBPS(mc);
         this.handleMSAA();
      }
   }

   private void handleBPS(class_310 mc) {
      class_1041 window = mc.method_22683();
      long handle = window.method_4490();
      if ((Boolean)this.bps.getValue() && !this.wasBpsActive) {
         int[] xBuf = new int[1];
         int[] yBuf = new int[1];
         GLFW.glfwGetWindowPos(handle, xBuf, yBuf);
         this.savedWindowX = xBuf[0];
         this.savedWindowY = yBuf[0];
         this.savedWindowWidth = window.method_4480();
         this.savedWindowHeight = window.method_4507();
         long monitor = GLFW.glfwGetPrimaryMonitor();
         if (monitor != 0L) {
            GLFWVidMode vidMode = GLFW.glfwGetVideoMode(monitor);
            if (vidMode != null) {
               GLFW.glfwSetWindowAttrib(handle, 131077, 0);
               GLFW.glfwSetWindowPos(handle, 0, 0);
               GLFW.glfwSetWindowSize(handle, vidMode.width(), vidMode.height());
            }
         }

         this.wasBpsActive = true;
      } else if (!(Boolean)this.bps.getValue() && this.wasBpsActive) {
         this.restoreWindow();
         this.wasBpsActive = false;
      }

   }

   private void restoreWindow() {
      class_310 mc = class_310.method_1551();
      if (mc.method_22683() != null) {
         long handle = mc.method_22683().method_4490();
         GLFW.glfwSetWindowAttrib(handle, 131077, 1);
         if (this.savedWindowWidth > 0 && this.savedWindowHeight > 0) {
            GLFW.glfwSetWindowSize(handle, this.savedWindowWidth, this.savedWindowHeight);
            GLFW.glfwSetWindowPos(handle, this.savedWindowX, this.savedWindowY);
         }

      }
   }

   private void handleMSAA() {
      String currentMode = this.msaa.getValue();
      if (!"None".equals(currentMode)) {
         GL11.glEnable(32925);
      } else {
         GL11.glDisable(32925);
      }

      if (!currentMode.equals(this.lastMsaaMode)) {
         this.lastMsaaMode = currentMode;
      }

   }

   @SubscribeEvent(0)
   public void onRenderWorld(RenderWorldEvent event) {
      if ((Boolean)this.depressed.getValue()) {
         class_310 mc = class_310.method_1551();
         if (mc.method_22683() == null) {
            return;
         }

         int width = mc.method_22683().method_4486();
         int height = mc.method_22683().method_4502();
         Color gloomyColor = new Color(10, 10, 15, 120);
         float r = (float)gloomyColor.getRed() / 255.0F;
         float g = (float)gloomyColor.getGreen() / 255.0F;
         float b = (float)gloomyColor.getBlue() / 255.0F;
         float a = (float)gloomyColor.getAlpha() / 255.0F;
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableDepthTest();
         RenderSystem.depthMask(false);
         RenderSystem.disableCull();
         RenderSystem.setShader(class_757::method_34540);
         Matrix4f matrix4f = (new Matrix4f()).translate(0.0F, 0.0F, -0.5F);
         float size = 100.0F;
         class_289 tessellator = RenderSystem.renderThreadTesselator();
         class_287 buffer = tessellator.method_60827(class_5596.field_27382, class_290.field_1576);
         buffer.method_22918(matrix4f, -size, -size, 0.0F).method_22915(r, g, b, a);
         buffer.method_22918(matrix4f, size, -size, 0.0F).method_22915(r, g, b, a);
         buffer.method_22918(matrix4f, size, size, 0.0F).method_22915(r, g, b, a);
         buffer.method_22918(matrix4f, -size, size, 0.0F).method_22915(r, g, b, a);
         class_286.method_43433(buffer.method_60800());
         RenderSystem.depthMask(true);
         RenderSystem.enableDepthTest();
         RenderSystem.disableBlend();
         RenderSystem.enableCull();
      }

   }
}
