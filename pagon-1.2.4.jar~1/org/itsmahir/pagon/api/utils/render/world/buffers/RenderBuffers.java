package org.itsmahir.pagon.api.utils.render.world.buffers;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_4587;
import net.minecraft.class_757;
import net.minecraft.class_9801;
import net.minecraft.class_293.class_5596;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

public class RenderBuffers {
   public static final Buffer QUADS;
   public static final Buffer LINES;
   public static final Buffer LINE;
   public static final Buffer TRIANGLES;
   private static boolean isSetup;
   private static final List<Runnable> processes;
   private static final List<Runnable> before;
   private static final List<Runnable> midways;

   public static void preRender() {
      GL11.glEnable(2848);
      RenderSystem.disableCull();
      RenderSystem.enableBlend();
      GL11.glBlendFunc(770, 771);
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableDepthTest();
      isSetup = true;

      for(Runnable callback : midways) {
         callback.run();
      }

      midways.clear();
   }

   public static void process() {
      for(Runnable callback : before) {
         callback.run();
      }

      before.clear();

      for(Runnable callback : processes) {
         callback.run();
      }

      processes.clear();
   }

   public static void postRender() {
      QUADS.draw();
      TRIANGLES.draw();
      LINES.draw();
      GL11.glDisable(2848);
      LINE.draw();
      RenderSystem.enableDepthTest();
      RenderSystem.disableBlend();
      RenderSystem.enableCull();
      GL11.glDisable(2848);
      GL11.glDepthFunc(515);
      isSetup = false;
   }

   public static void scheduleMidway(Runnable callback) {
      midways.add(callback);
   }

   public static void scheduleRender(Runnable callback) {
      processes.add(callback);
   }

   public static void schedulePreRender(Runnable callback) {
      before.add(callback);
   }

   static {
      QUADS = new Buffer(class_5596.field_27382, class_290.field_1576);
      LINES = new Buffer(class_5596.field_29344, class_290.field_1576);
      LINE = new Buffer(class_5596.field_27377, class_290.field_29337);
      TRIANGLES = new Buffer(class_5596.field_27381, class_290.field_1576);
      isSetup = false;
      processes = new ArrayList();
      before = new ArrayList();
      midways = new ArrayList();
   }

   public static class Buffer {
      public class_287 buffer;
      private Matrix4f positionMatrix;
      class_293.class_5596 drawMode;
      class_293 vertexFormat;
      Color currentColor;

      public Buffer(class_293.class_5596 drawMode, class_293 vertexFormat) {
         this.drawMode = drawMode;
         this.vertexFormat = vertexFormat;
      }

      public void begin(Matrix4f positionMatrix) {
         this.positionMatrix = positionMatrix;
         this.buffer = RenderSystem.renderThreadTesselator().method_60827(this.drawMode, this.vertexFormat);
      }

      public void begin(class_4587 positionMatrix) {
         this.positionMatrix = positionMatrix.method_23760().method_23761();
         this.buffer = RenderSystem.renderThreadTesselator().method_60827(this.drawMode, this.vertexFormat);
      }

      public void end() {
         this.draw();
      }

      public Buffer vertex(double x, double y, double z) {
         return this.vertex((float)x, (float)y, (float)z);
      }

      public Buffer vertex(float x, float y, float z) {
         if (this.currentColor == null) {
            this.currentColor = Color.WHITE;
         }

         ColorUtil.color(this.buffer.method_22918(this.positionMatrix, x, y, z), this.currentColor);
         return this;
      }

      public Buffer vertex(Matrix4f stack, float x, float y, float z) {
         if (this.currentColor == null) {
            this.currentColor = Color.WHITE;
         }

         ColorUtil.color(this.buffer.method_22918(stack, x, y, z), this.currentColor);
         return this;
      }

      public void color(Color color) {
         this.currentColor = color;
      }

      public void color(int color) {
         this.currentColor = new Color(color);
      }

      public void draw() {
         if (this.buffer != null) {
            if (this.buffer.field_1556) {
               class_9801 builtBuffer = this.buffer.method_60794();
               if (builtBuffer != null) {
                  if (this.vertexFormat == class_290.field_29337) {
                     RenderSystem.setShader(class_757::method_34535);
                  } else {
                     RenderSystem.setShader(class_757::method_34540);
                  }

                  class_286.method_43433(builtBuffer);
               }

            }
         }
      }

      public void vertexLine(double v, double v1, double v2, double v3, double v4, double v5) {
         this.vertex(v, v1, v2);
         this.vertex(v3, v4, v5);
      }
   }
}
