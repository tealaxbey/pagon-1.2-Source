package org.itsmahir.pagon.api.utils.render;

import java.awt.Color;
import net.minecraft.class_1309;
import net.minecraft.class_4587;
import net.minecraft.class_4592;
import net.minecraft.class_4595;
import net.minecraft.class_5597;
import net.minecraft.class_572;
import net.minecraft.class_578;
import net.minecraft.class_583;
import net.minecraft.class_596;
import net.minecraft.class_630;
import org.itsmahir.pagon.api.utils.render.world.RenderType;
import org.itsmahir.pagon.api.utils.render.world.buffers.RenderBuffers;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class WireframeEntityRenderer implements IMinecraft {
   private static class_4587 matrices = new class_4587();
   private static final Vector4f pos1 = new Vector4f();
   private static final Vector4f pos2 = new Vector4f();
   private static final Vector4f pos3 = new Vector4f();
   private static final Vector4f pos4 = new Vector4f();
   private static Color sideColor;
   private static Color lineColor;
   private static RenderType shapeMode;

   private WireframeEntityRenderer() {
   }

   private static void render(class_630 part) {
      if (part.field_3665 && (!part.field_3663.isEmpty() || !part.field_3661.isEmpty())) {
         matrices.method_22903();
         part.method_22703(matrices);

         for(class_630.class_628 cuboid : part.field_3663) {
            render(cuboid);
         }

         for(class_630 child : part.field_3661.values()) {
            render(child);
         }

         matrices.method_22909();
      }
   }

   private static void render(class_630.class_628 cuboid) {
      for(class_630.class_593 quad : cuboid.field_3649) {
         pos1.set(quad.field_3502[0].field_3605.x / 16.0F, quad.field_3502[0].field_3605.y / 16.0F, quad.field_3502[0].field_3605.z / 16.0F, 1.0F);
         pos2.set(quad.field_3502[1].field_3605.x / 16.0F, quad.field_3502[1].field_3605.y / 16.0F, quad.field_3502[1].field_3605.z / 16.0F, 1.0F);
         pos3.set(quad.field_3502[2].field_3605.x / 16.0F, quad.field_3502[2].field_3605.y / 16.0F, quad.field_3502[2].field_3605.z / 16.0F, 1.0F);
         pos4.set(quad.field_3502[3].field_3605.x / 16.0F, quad.field_3502[3].field_3605.y / 16.0F, quad.field_3502[3].field_3605.z / 16.0F, 1.0F);
         if (shapeMode.sides()) {
            Matrix4f matrix4f = matrices.method_23760().method_23761();
            RenderBuffers.QUADS.begin(matrix4f);
            RenderBuffers.QUADS.color(sideColor);
            RenderBuffers.QUADS.vertex(pos1.x, pos1.y, pos1.z);
            RenderBuffers.QUADS.vertex(pos2.x, pos2.y, pos2.z);
            RenderBuffers.QUADS.vertex(pos3.x, pos3.y, pos3.z);
            RenderBuffers.QUADS.vertex(pos4.x, pos4.y, pos4.z);
            RenderBuffers.QUADS.end();
         }

         if (shapeMode.lines()) {
            Matrix4f matrix4f = matrices.method_23760().method_23761();
            RenderBuffers.LINES.begin(matrix4f);
            RenderBuffers.LINES.color(lineColor);
            RenderBuffers.LINES.vertex(pos1.x, pos1.y, pos1.z);
            RenderBuffers.LINES.vertex(pos2.x, pos2.y, pos2.z);
            RenderBuffers.LINES.vertex(pos2.x, pos2.y, pos2.z);
            RenderBuffers.LINES.vertex(pos3.x, pos3.y, pos3.z);
            RenderBuffers.LINES.vertex(pos3.x, pos3.y, pos3.z);
            RenderBuffers.LINES.vertex(pos4.x, pos4.y, pos4.z);
            RenderBuffers.LINES.vertex(pos1.x, pos1.y, pos1.z);
            RenderBuffers.LINES.vertex(pos1.x, pos1.y, pos1.z);
            RenderBuffers.LINES.end();
         }
      }

   }

   public static void renderModel(class_4587 matrix, class_583<class_1309> model, RenderType type, Color sideColor, Color lineColor) {
      matrices = matrix;
      shapeMode = type;
      WireframeEntityRenderer.sideColor = sideColor;
      WireframeEntityRenderer.lineColor = lineColor;
      if (model instanceof class_4592 m) {
         if (m.field_3448) {
            matrices.method_22903();
            if (m.field_20915) {
               float g = 1.5F / m.field_20918;
               matrices.method_22905(g, g, g);
            }

            matrices.method_22904((double)0.0F, (double)(m.field_20916 / 16.0F), (double)(m.field_20917 / 16.0F));
            if (model instanceof class_572) {
               class_572 mo = (class_572)model;
               render(mo.field_3398);
            } else {
               m.method_22946().forEach((modelPart) -> render((class_630)modelPart));
            }

            matrices.method_22909();
            matrices.method_22903();
            float g = 1.0F / m.field_20919;
            matrices.method_22905(g, g, g);
            matrices.method_22904((double)0.0F, (double)(m.field_20920 / 16.0F), (double)0.0F);
            if (model instanceof class_572) {
               class_572 mo = (class_572)model;
               render(mo.field_3391);
               render(mo.field_27433);
               render(mo.field_3401);
               render(mo.field_3397);
               render(mo.field_3392);
            } else {
               m.method_22948().forEach((modelPart) -> render((class_630)modelPart));
            }

            matrices.method_22909();
         } else if (model instanceof class_572) {
            class_572 mo = (class_572)model;
            render(mo.field_3398);
            render(mo.field_3391);
            render(mo.field_27433);
            render(mo.field_3401);
            render(mo.field_3397);
            render(mo.field_3392);
         } else {
            m.method_22946().forEach((modelPart) -> render((class_630)modelPart));
            m.method_22948().forEach((modelPart) -> render((class_630)modelPart));
         }
      } else if (model instanceof class_5597 m) {
         render(m.method_32008());
      } else if (model instanceof class_4595 m) {
         m.method_22960().forEach((modelPart) -> render((class_630)modelPart));
      } else if (model instanceof class_578 m) {
         if (m.field_3448) {
            matrices.method_22903();
            matrices.method_22905(0.71428573F, 0.64935064F, 0.7936508F);
            matrices.method_22904((double)0.0F, (double)1.3125F, (double)0.22F);
            render(m.field_27443);
            matrices.method_22909();
            matrices.method_22903();
            matrices.method_22905(0.625F, 0.45454544F, 0.45454544F);
            matrices.method_22904((double)0.0F, (double)2.0625F, (double)0.0F);
            render(m.field_27444);
            matrices.method_22909();
            matrices.method_22903();
            matrices.method_22905(0.45454544F, 0.41322312F, 0.45454544F);
            matrices.method_22904((double)0.0F, (double)2.0625F, (double)0.0F);
            render(m.field_27445);
            render(m.field_27446);
            render(m.field_27447);
            render(m.field_27448);
            render(m.field_27449);
            render(m.field_27450);
            matrices.method_22909();
         } else {
            render(m.field_27443);
            render(m.field_27444);
            render(m.field_27445);
            render(m.field_27446);
            render(m.field_27447);
            render(m.field_27448);
            render(m.field_27449);
            render(m.field_27450);
         }
      } else if (model instanceof class_596 m) {
         if (m.field_3448) {
            matrices.method_22903();
            matrices.method_22905(0.56666666F, 0.56666666F, 0.56666666F);
            matrices.method_22904((double)0.0F, (double)1.375F, (double)0.125F);
            render(m.field_27486);
            render(m.field_27488);
            render(m.field_27487);
            render(m.field_3530);
            matrices.method_22909();
            matrices.method_22903();
            matrices.method_22905(0.4F, 0.4F, 0.4F);
            matrices.method_22904((double)0.0F, (double)2.25F, (double)0.0F);
            render(m.field_27480);
            render(m.field_27481);
            render(m.field_27482);
            render(m.field_27483);
            render(m.field_3528);
            render(m.field_27484);
            render(m.field_27485);
            render(m.field_3524);
            matrices.method_22909();
         } else {
            matrices.method_22903();
            matrices.method_22905(0.6F, 0.6F, 0.6F);
            matrices.method_22904((double)0.0F, (double)1.0F, (double)0.0F);
            render(m.field_27480);
            render(m.field_27481);
            render(m.field_27482);
            render(m.field_27483);
            render(m.field_3528);
            render(m.field_27484);
            render(m.field_27485);
            render(m.field_27486);
            render(m.field_27487);
            render(m.field_27488);
            render(m.field_3524);
            render(m.field_3530);
            matrices.method_22909();
         }
      }

   }

   public static void renderPart(class_4587 matrix, class_630 part, RenderType type, Color sideColor, Color lineColor) {
      matrices = matrix;
      shapeMode = type;
      WireframeEntityRenderer.sideColor = sideColor;
      WireframeEntityRenderer.lineColor = lineColor;
      matrix.method_22903();
      render(part);
      matrix.method_22909();
   }
}
