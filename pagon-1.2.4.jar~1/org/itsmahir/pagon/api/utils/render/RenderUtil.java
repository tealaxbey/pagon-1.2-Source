package org.itsmahir.pagon.api.utils.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.awt.Point;
import java.util.Objects;
import net.minecraft.class_1087;
import net.minecraft.class_1091;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1921;
import net.minecraft.class_1937;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_4696;
import net.minecraft.class_4720;
import net.minecraft.class_757;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_5253.class_5254;
import org.itsmahir.pagon.api.gui.font.Fonts;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.color.TextSection;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.render.world.RenderType;
import org.itsmahir.pagon.api.utils.render.world.buffers.RenderBuffers;
import org.itsmahir.pagon.api.utils.render.world.layer.PagonLayers;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.features.modules.render.Nametags;
import org.itsmahir.pagon.mixin.accessor.IItemRenderer;
import org.itsmahir.pagon.mixin.accessor.IWorldRenderer;
import org.joml.Matrix4f;
import org.joml.Vector3d;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

public class RenderUtil {
   public static final class_289 TESSELLATOR = RenderSystem.renderThreadTesselator();

   public static void renderRect(class_4587 matrices, double x1, double y1, double x2, double y2, int color) {
      renderRect(matrices, x1, y1, x2, y2, (double)0.0F, color);
   }

   public static double interpolate(double oldValue, double newValue, double interpolationValue) {
      return oldValue + (newValue - oldValue) * interpolationValue;
   }

   public static void renderItem(class_1799 stack, class_811 renderMode, class_4587 matrices, class_4597 vertexConsumers, class_1937 world, int seed) {
      class_1087 bakedModel = IMinecraft.mc.method_1480().method_4019(stack, (class_1937)null, (class_1309)null, seed);
      if (!stack.method_7960()) {
         matrices.method_22903();
         boolean bl = renderMode == class_811.field_4317 || renderMode == class_811.field_4318 || renderMode == class_811.field_4319;
         if (bl) {
            if (stack.method_31574(class_1802.field_8547)) {
               bakedModel = IMinecraft.mc.method_1480().method_4012().method_3303().method_4742(class_1091.method_45910("trident", "inventory"));
            } else if (stack.method_31574(class_1802.field_27070)) {
               bakedModel = IMinecraft.mc.method_1480().method_4012().method_3303().method_4742(class_1091.method_45910("spyglass", "inventory"));
            }
         }

         bakedModel.method_4709().method_3503(renderMode).method_23075(false, matrices);
         matrices.method_46416(-0.5F, -0.5F, -0.5F);
         if (!bakedModel.method_4713() && (!stack.method_31574(class_1802.field_8547) || bl)) {
            ((IItemRenderer)IMinecraft.mc.method_1480()).renderBakedItemModelAccess(bakedModel, stack, 15728895, class_4608.field_21444, matrices, getItemGlintConsumer(vertexConsumers, class_4696.method_23678(stack, false), stack.method_7958()));
         } else {
            ((IItemRenderer)IMinecraft.mc.method_1480()).getBuiltInModelItemRenderer().method_3166(stack, renderMode, matrices, vertexConsumers, 15728895, class_4608.field_21444);
         }

         matrices.method_22909();
      }
   }

   public static class_4588 getItemGlintConsumer(class_4597 vertexConsumers, class_1921 layer, boolean glint) {
      return glint ? class_4720.method_24037(vertexConsumers.getBuffer(PagonLayers.ENCHANT), vertexConsumers.getBuffer(layer)) : vertexConsumers.getBuffer(layer);
   }

   public static void renderItemWithCount(class_332 context, class_1799 item, Point pos, int count, Color textColor, boolean always) {
      context.method_51427(item, pos.x, pos.y);
      context.method_51431(IMinecraft.mc.field_1772, item, pos.x, pos.y);
      context.method_51448().method_22903();
      context.method_51448().method_46416(0.0F, 0.0F, 600.0F);
      if (count > 1 || always) {
         if (count >= 1000) {
            Fonts.renderText(context.method_51448(), String.valueOf(count), (float)(pos.x + 19) - Fonts.getTextWidth(String.valueOf(count)), (float)(pos.y + 9), textColor, true);
         } else {
            Fonts.renderText(context.method_51448(), String.valueOf(count), (float)(pos.x + 19 - 2) - Fonts.getTextWidth(String.valueOf(count)), (float)(pos.y + 9), textColor, true);
         }
      }

      context.method_51448().method_22909();
   }

   public static void renderOutline(class_4587 matrices, double x1, double y1, double x2, double y2, int color, boolean rasturize) {
      renderOutlineRect(matrices, x1, y1, x2, y2, (double)0.0F, color, rasturize);
   }

   public static void renderGradient(class_4587 matrices, double startX, double startY, double endX, double endY, int colorStart, int colorEnd, boolean horizontal) {
      renderGradient(matrices, startX, startY, endX, endY, colorStart, colorEnd, horizontal, 0);
   }

   public static void renderGradient(class_4587 matrices, double startX, double startY, double endX, double endY, int colorStart, int colorEnd, boolean horizontal, int z) {
      RenderSystem.enableBlend();
      if (RenderSystem.getShader() != class_757.method_34540()) {
         RenderSystem.setShader(class_757::method_34540);
      }

      class_289 tessellator = class_289.method_1348();
      class_287 builder = tessellator.method_60827(class_5596.field_27382, class_290.field_1576);
      renderGradient(matrices.method_23760().method_23761(), builder, startX, startY, endX, endY, (double)z, colorStart, colorEnd, horizontal);
      class_286.method_43433(builder.method_60800());
      RenderSystem.disableBlend();
   }

   public static void renderGradient(Matrix4f matrix, class_287 builder, double startX, double startY, double endX, double endY, double z, int colorStart, int colorEnd, boolean horizontal) {
      endX += startX;
      endY += startY;
      float f = (float)class_5254.method_27762(colorStart) / 255.0F;
      float g = (float)class_5254.method_27765(colorStart) / 255.0F;
      float h = (float)class_5254.method_27766(colorStart) / 255.0F;
      float i = (float)class_5254.method_27767(colorStart) / 255.0F;
      float j = (float)class_5254.method_27762(colorEnd) / 255.0F;
      float k = (float)class_5254.method_27765(colorEnd) / 255.0F;
      float l = (float)class_5254.method_27766(colorEnd) / 255.0F;
      float m = (float)class_5254.method_27767(colorEnd) / 255.0F;
      if (horizontal) {
         builder.method_22918(matrix, (float)startX, (float)startY, (float)z).method_22915(g, h, i, f);
         builder.method_22918(matrix, (float)startX, (float)endY, (float)z).method_22915(g, h, i, f);
         builder.method_22918(matrix, (float)endX, (float)endY, (float)z).method_22915(k, l, m, j);
         builder.method_22918(matrix, (float)endX, (float)startY, (float)z).method_22915(k, l, m, j);
      } else {
         builder.method_22918(matrix, (float)startX, (float)startY, (float)z).method_22915(g, h, i, f);
         builder.method_22918(matrix, (float)startX, (float)endY, (float)z).method_22915(k, l, m, j);
         builder.method_22918(matrix, (float)endX, (float)endY, (float)z).method_22915(k, l, m, j);
         builder.method_22918(matrix, (float)endX, (float)startY, (float)z).method_22915(g, h, i, f);
      }

   }

   public static void renderRect(class_4587 matrices, double x1, double y1, double x2, double y2, double z, Color color) {
      renderRect(matrices, x1, y1, x2, y2, z, color.getRGB());
   }

   public static void renderRect(class_4587 matrices, double x1, double y1, double x2, double y2, double z, int color) {
      x2 += x1;
      y2 += y1;
      Matrix4f matrix4f = matrices.method_23760().method_23761();
      if (x1 < x2) {
         double i = x1;
         x1 = x2;
         x2 = i;
      }

      if (y1 < y2) {
         double i = y1;
         y1 = y2;
         y2 = i;
      }

      float f = (float)class_5254.method_27762(color) / 255.0F;
      float g = (float)class_5254.method_27765(color) / 255.0F;
      float h = (float)class_5254.method_27766(color) / 255.0F;
      float j = (float)class_5254.method_27767(color) / 255.0F;
      RenderSystem.enableBlend();
      if (RenderSystem.getShader() != class_757.method_34540()) {
         RenderSystem.setShader(class_757::method_34540);
      }

      class_287 buffer = TESSELLATOR.method_60827(class_5596.field_27382, class_290.field_1576);
      buffer.method_22918(matrix4f, (float)x1, (float)y1, (float)z).method_22915(g, h, j, f);
      buffer.method_22918(matrix4f, (float)x1, (float)y2, (float)z).method_22915(g, h, j, f);
      buffer.method_22918(matrix4f, (float)x2, (float)y2, (float)z).method_22915(g, h, j, f);
      buffer.method_22918(matrix4f, (float)x2, (float)y1, (float)z).method_22915(g, h, j, f);
      class_286.method_43433(buffer.method_60800());
      RenderSystem.disableBlend();
   }

   public static void drawText(String text, class_243 renderPos, float size) {
      class_4184 camera = IMinecraft.mc.field_1773.method_19418();
      class_243 pos = camera.method_19326();
      class_4587 matrixStack = new class_4587();
      matrixStack.method_22903();
      matrixStack.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
      matrixStack.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
      matrixStack.method_22904(renderPos.field_1352 - pos.method_10216(), renderPos.field_1351 - pos.method_10214(), renderPos.field_1350 - pos.method_10215());
      matrixStack.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
      matrixStack.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      matrixStack.method_22905(-0.01F * size, -0.01F * size, -1.0F);
      float distance = (float)renderPos.method_1022(IMinecraft.mc.field_1724.method_19538());
      float scaleDistance = distance / 2.0F / (2.0F + (2.0F - size));
      if (scaleDistance < 1.0F) {
         scaleDistance = 1.0F;
      }

      matrixStack.method_22905(scaleDistance, scaleDistance, scaleDistance);
      GL11.glDepthFunc(519);
      float hwidth = Fonts.getTextWidth(text) / 2.0F;
      Fonts.renderText(matrixStack, text, -hwidth, 0.0F, Color.WHITE, true);
      GL11.glDepthFunc(515);
      RenderSystem.disableBlend();
      matrixStack.method_22909();
   }

   public static void renderOutlineRect(class_4587 matrices, double x1, double y1, double width, double height, double z, Color color, boolean rasturize) {
      renderOutlineRect(matrices, x1, y1, width, height, z, color.getRGB(), rasturize);
   }

   public static void renderOutlineRect(class_4587 matrices, double x1, double y1, double width, double height, double z, int color, boolean rasturize) {
      float x2 = (float)(x1 + width);
      float y2 = (float)(y1 + height);
      if (rasturize) {
         y2 -= 0.1F;
      }

      Matrix4f matrix4f = matrices.method_23760().method_23761();
      float f = (float)class_5254.method_27762(color) / 255.0F;
      float g = (float)class_5254.method_27765(color) / 255.0F;
      float h = (float)class_5254.method_27766(color) / 255.0F;
      float j = (float)class_5254.method_27767(color) / 255.0F;
      RenderSystem.enableBlend();
      if (RenderSystem.getShader() != class_757.method_34540()) {
         RenderSystem.setShader(class_757::method_34540);
      }

      class_287 buffer = TESSELLATOR.method_60827(class_5596.field_29344, class_290.field_1576);
      buffer.method_22918(matrix4f, (float)x1, (float)y1, (float)z).method_22915(g, h, j, f);
      buffer.method_22918(matrix4f, (float)x1, y2, (float)z).method_22915(g, h, j, f);
      buffer.method_22918(matrix4f, (float)x1, y2, (float)z).method_22915(g, h, j, f);
      buffer.method_22918(matrix4f, x2, y2, (float)z).method_22915(g, h, j, f);
      buffer.method_22918(matrix4f, x2, y2, (float)z).method_22915(g, h, j, f);
      buffer.method_22918(matrix4f, x2, (float)y1, (float)z).method_22915(g, h, j, f);
      buffer.method_22918(matrix4f, x2, (float)y1, (float)z).method_22915(g, h, j, f);
      buffer.method_22918(matrix4f, (float)x1, (float)y1, (float)z).method_22915(g, h, j, f);
      class_286.method_43433(buffer.method_60800());
      RenderSystem.disableBlend();
   }

   public static void renderBox(RenderType type, class_238 box, Color top, Color bottom) {
      switch (type) {
         case FILL -> renderFillBox(box, top, bottom);
         case LINES -> renderLinesBox(box, top, bottom);
      }

   }

   public static void renderLinesBox(class_238 box, Color top, Color bottom) {
      class_4587 stack = matrixFrom(box.field_1323, box.field_1322, box.field_1321);
      stack.method_22903();
      drawOutlineBox(stack, box.method_997((new class_243(box.field_1323, box.field_1322, box.field_1321)).method_22882()), top, bottom);
      stack.method_22909();
   }

   public static boolean isFrustumVisible(class_238 box) {
      return ((IWorldRenderer)IMinecraft.mc.field_1769).getFrustum().method_23093(box);
   }

   public static void renderFillBox(class_238 box, Color top, Color bottom) {
      class_4587 stack = matrixFrom(box.field_1323, box.field_1322, box.field_1321);
      stack.method_22903();
      drawBox(matrixFrom(box.field_1323, box.field_1322, box.field_1321), box.method_997((new class_243(box.field_1323, box.field_1322, box.field_1321)).method_22882()), top, bottom);
      stack.method_22909();
   }

   public static void drawBox(class_4587 matrices, class_238 box, Color top, Color bottom) {
      drawBox(matrices, box.field_1323, box.field_1322, box.field_1321, box.field_1320, box.field_1325, box.field_1324, top, bottom);
   }

   public static void drawBox(double x1, double y1, double z1, double x2, double y2, double z2, Color color) {
      class_4587 stack = matrixFrom(x1, y1, z2);
      stack.method_22903();
      drawBox(stack, x1, y1, z1, x2, y2, z2, color, color);
      stack.method_22909();
   }

   public static class_4587 matrixFrom(double x, double y, double z) {
      class_4587 matrices = new class_4587();
      class_4184 camera = IMinecraft.mc.field_1773.method_19418();
      matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
      matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
      matrices.method_22904(x - camera.method_19326().field_1352, y - camera.method_19326().field_1351, z - camera.method_19326().field_1350);
      return matrices;
   }

   public static void drawLine(class_4587 matrices, double x1, double y1, double z1, double x2, double y2, double z2, Color top, Color bottom) {
      Matrix4f matrix4f = matrices.method_23760().method_23761();
      Vector3f normalVec = getNormal((float)x1, (float)y1, (float)z1, (float)x2, (float)y2, (float)z2);
      RenderBuffers.LINE.begin(matrix4f);
      RenderBuffers.LINE.color(bottom);
      RenderBuffers.LINE.vertex(x1, y1, z1).buffer.method_60831(matrices.method_23760(), normalVec.x(), normalVec.y(), normalVec.z());
      RenderBuffers.LINE.color(top);
      RenderBuffers.LINE.vertex(x2, y2, z2).buffer.method_60831(matrices.method_23760(), normalVec.x(), normalVec.y(), normalVec.z());
      RenderBuffers.LINE.end();
   }

   public static Vector3f getNormal(float x1, float y1, float z1, float x2, float y2, float z2) {
      float xNormal = x2 - x1;
      float yNormal = y2 - y1;
      float zNormal = z2 - z1;
      float normalSqrt = class_3532.method_15355(xNormal * xNormal + yNormal * yNormal + zNormal * zNormal);
      return new Vector3f(xNormal / normalSqrt, yNormal / normalSqrt, zNormal / normalSqrt);
   }

   public static void drawBox(class_4587 matrices, double x1, double y1, double z1, double x2, double y2, double z2, Color top, Color bottom) {
      Matrix4f matrix4f = matrices.method_23760().method_23761();
      RenderBuffers.QUADS.begin(matrix4f);
      RenderBuffers.QUADS.color(bottom);
      RenderBuffers.QUADS.vertex(x1, y1, z1).vertex(x2, y1, z1).vertex(x2, y1, z2).vertex(x1, y1, z2);
      RenderBuffers.QUADS.color(top);
      RenderBuffers.QUADS.vertex(x1, y2, z1).vertex(x1, y2, z2).vertex(x2, y2, z2).vertex(x2, y2, z1);
      RenderBuffers.QUADS.color(bottom);
      RenderBuffers.QUADS.vertex(x1, y1, z1);
      RenderBuffers.QUADS.color(top);
      RenderBuffers.QUADS.vertex(x1, y2, z1).vertex(x2, y2, z1);
      RenderBuffers.QUADS.color(bottom);
      RenderBuffers.QUADS.vertex(x2, y1, z1);
      RenderBuffers.QUADS.vertex(x2, y1, z1);
      RenderBuffers.QUADS.color(top);
      RenderBuffers.QUADS.vertex(x2, y2, z1).vertex(x2, y2, z2);
      RenderBuffers.QUADS.color(bottom);
      RenderBuffers.QUADS.vertex(x2, y1, z2);
      RenderBuffers.QUADS.vertex(x1, y1, z2).vertex(x2, y1, z2);
      RenderBuffers.QUADS.color(top);
      RenderBuffers.QUADS.vertex(x2, y2, z2).vertex(x1, y2, z2);
      RenderBuffers.QUADS.color(bottom);
      RenderBuffers.QUADS.vertex(x1, y1, z1).vertex(x1, y1, z2);
      RenderBuffers.QUADS.color(top);
      RenderBuffers.QUADS.vertex(x1, y2, z2).vertex(x1, y2, z1);
      RenderBuffers.QUADS.end();
   }

   public static void renderTracerLine(class_243 from, class_243 to, Color top, Color bottom, float lineWidth) {
      class_243 eyes = MathUtil.rotateYaw(MathUtil.rotatePitch(new class_243((double)0.0F, (double)0.0F, (double)1.0F), -((float)Math.toRadians((double)IMinecraft.mc.field_1724.method_36455()))), -((float)Math.toRadians((double)IMinecraft.mc.field_1724.method_36454())));
      renderLine(eyes, from.field_1352, from.field_1351, from.field_1350, to.field_1352, to.field_1351, to.field_1350, top, bottom, lineWidth);
   }

   public static void renderLineFromPosToPos(class_243 from, class_243 to, Color top, Color bottom, float lineWidth) {
      renderLine(new class_243((double)0.0F, (double)0.0F, (double)0.0F), from.field_1352, from.field_1351, from.field_1350, to.field_1352, to.field_1351, to.field_1350, top, bottom, lineWidth);
   }

   public static void renderLine(class_243 offset, double x1, double y1, double z1, double x2, double y2, double z2, Color top, Color bottom, float width) {
      float old = RenderSystem.getShaderLineWidth();
      RenderSystem.lineWidth(width);
      class_4587 matrices = matrixFrom(x1, y1, z1);
      matrices.method_22903();
      drawLine(matrices, offset.field_1352, offset.field_1351, offset.field_1350, (double)((float)(x2 - x1)), (double)((float)(y2 - y1)), (double)((float)(z2 - z1)), top, bottom);
      matrices.method_22909();
      RenderSystem.lineWidth(old);
   }

   public static void drawOutlineBox(class_4587 matrices, class_238 box, Color top, Color bottom) {
      renderLinesBox(matrices, box.field_1323, box.field_1322, box.field_1321, box.field_1320, box.field_1325, box.field_1324, top, bottom);
   }

   public static void drawCircle(RenderBuffers.Buffer buffer, float radius, int slices, class_243 pos, class_2350 direction, Color color) {
      class_4587 matrices = matrixFrom(pos.field_1352, pos.field_1351, pos.field_1350);
      matrices.method_22903();
      Matrix4f matrix = matrices.method_23760().method_23761();
      buffer.begin(matrix);
      buffer.color(color);

      for(int i = 0; i <= slices; ++i) {
         double circleTwo = (double)radius * Math.cos((double)i * 12.566370614359172 / (double)slices);
         double circleOne = (double)radius * Math.sin((double)i * 12.566370614359172 / (double)slices);
         switch (direction) {
            case field_11036:
            case field_11033:
               buffer.vertex((float)circleTwo, 0.0F, (float)circleOne);
               break;
            case field_11034:
            case field_11039:
               buffer.vertex(0.0F, (float)circleTwo, (float)circleOne);
               break;
            case field_11035:
            case field_11043:
               buffer.vertex((float)circleOne, (float)circleTwo, 0.0F);
         }
      }

      buffer.end();
      matrices.method_22909();
   }

   public static void renderLinesBox(class_4587 matrices, double x1, double y1, double z1, double x2, double y2, double z2, Color top, Color bottom) {
      Matrix4f matrix4f = matrices.method_23760().method_23761();
      RenderBuffers.LINES.begin(matrix4f);
      RenderBuffers.LINES.color(bottom);
      RenderBuffers.LINES.vertex(x1, y1, z1).vertex(x2, y1, z1);
      RenderBuffers.LINES.vertex(x2, y1, z1).vertex(x2, y1, z2);
      RenderBuffers.LINES.vertex(x2, y1, z2).vertex(x1, y1, z2);
      RenderBuffers.LINES.vertex(x1, y1, z2).vertex(x1, y1, z1);
      RenderBuffers.LINES.vertex(x1, y1, z1);
      RenderBuffers.LINES.color(top);
      RenderBuffers.LINES.vertex(x1, y2, z1);
      RenderBuffers.LINES.color(bottom);
      RenderBuffers.LINES.vertex(x2, y1, z1);
      RenderBuffers.LINES.color(top);
      RenderBuffers.LINES.vertex(x2, y2, z1);
      RenderBuffers.LINES.color(bottom);
      RenderBuffers.LINES.vertex(x2, y1, z2);
      RenderBuffers.LINES.color(top);
      RenderBuffers.LINES.vertex(x2, y2, z2);
      RenderBuffers.LINES.color(bottom);
      RenderBuffers.LINES.vertex(x1, y1, z2);
      RenderBuffers.LINES.color(top);
      RenderBuffers.LINES.vertex(x1, y2, z2);
      RenderBuffers.LINES.vertex(x1, y2, z1).vertex(x2, y2, z1);
      RenderBuffers.LINES.vertex(x2, y2, z1).vertex(x2, y2, z2);
      RenderBuffers.LINES.vertex(x2, y2, z2).vertex(x1, y2, z2);
      RenderBuffers.LINES.vertex(x1, y2, z2).vertex(x1, y2, z1);
      RenderBuffers.LINES.end();
   }

   public static Vector3d set(Vector3d vec, class_1297 entity, double tickDelta) {
      vec.x = class_3532.method_16436(tickDelta, entity.field_6038, entity.method_23317());
      vec.y = class_3532.method_16436(tickDelta, entity.field_5971, entity.method_23318());
      vec.z = class_3532.method_16436(tickDelta, entity.field_5989, entity.method_23321());
      return vec;
   }

   public static Vector3d set(Vector3d vec, class_243 v) {
      vec.x = v.field_1352;
      vec.y = v.field_1351;
      vec.z = v.field_1350;
      return vec;
   }

   public static void drawWaypoint(TextSection[] sections, double x, double y, double z, class_4184 camera, Color borderColor) {
      float width = 0.0F;

      for(TextSection section : sections) {
         width += (float)((int)Fonts.getTextWidth(section.getText()));
      }

      width /= 2.0F;
      class_243 pos = camera.method_19326();
      class_4587 matrices = new class_4587();
      double maxRenderDistance = (double)((Integer)IMinecraft.mc.field_1690.method_42503().method_41753() << 4);
      class_243 waypointVec = new class_243(x, y, z);
      class_243 playerPos = Interpolator.getInterpolatedPosition(IMinecraft.mc.method_1560(), IMinecraft.mc.method_60646().method_60637(false));
      if (playerPos.method_1022(waypointVec) > maxRenderDistance) {
         class_243 delta = waypointVec.method_1020(playerPos).method_1029();
         waypointVec = new class_243(delta.field_1352 * maxRenderDistance, delta.field_1351 * maxRenderDistance, delta.field_1350 * maxRenderDistance);
         waypointVec = playerPos.method_1019(waypointVec);
      }

      x = waypointVec.field_1352;
      y = waypointVec.field_1351;
      z = waypointVec.field_1350;
      class_243 interpolate = Interpolator.getInterpolatedEyePos(IMinecraft.mc.method_1560(), IMinecraft.mc.method_60646().method_60637(false));
      double dx = pos.method_10216() - interpolate.method_10216() - x;
      double dy = pos.method_10214() - interpolate.method_10214() - y;
      double dz = pos.method_10215() - interpolate.method_10215() - z;
      double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
      float scaling = 1.0F;
      scaling = (float)((double)0.0018F + (double)0.003F * dist);
      if (dist <= (double)8.0F) {
         scaling = 0.0245F;
      }

      matrices.method_22903();
      matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
      matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
      matrices.method_22904(x - pos.method_10216(), y - pos.method_10214(), z - pos.method_10215());
      matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
      matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
      matrices.method_22905(-scaling, -scaling, -1.0F);
      double var10001 = (double)(-width - 1.0F);
      double var10003 = (double)(width * 2.0F + 2.0F);
      Objects.requireNonNull(IMinecraft.mc.field_1772);
      renderRect(matrices, var10001, (double)-1.0F, var10003, (double)(9.0F + 1.5F), (double)0.0F, ((PagonColor)Nametags.INSTANCE.boxColor.getValue()).getColor());
      var10001 = (double)(-width - 1.0F);
      var10003 = (double)(width * 2.0F + 2.0F);
      Objects.requireNonNull(IMinecraft.mc.field_1772);
      renderOutline(matrices, var10001, (double)-1.0F, var10003, (double)(9.0F + 1.5F), borderColor.getRGB(), true);
      drawSections(sections, matrices, -width, 0.0F);
      matrices.method_22909();
   }

   public static void drawSections(TextSection[] sections, class_4587 matrices, float x, float y) {
      float width = 0.0F;

      for(TextSection section : sections) {
         Fonts.renderText(matrices, section.getText(), x + width, y, section.getColor(), (Boolean)org.itsmahir.pagon.impl.features.modules.client.Fonts.INSTANCE.textShadow.getValue());
         width += Fonts.getTextWidth(section.getText());
      }

   }
}
