package org.itsmahir.pagon.api.utils.render;

import com.google.common.base.MoreObjects;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_1007;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1306;
import net.minecraft.class_1309;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1764;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4050;
import net.minecraft.class_4587;
import net.minecraft.class_4592;
import net.minecraft.class_4595;
import net.minecraft.class_5597;
import net.minecraft.class_572;
import net.minecraft.class_583;
import net.minecraft.class_591;
import net.minecraft.class_630;
import net.minecraft.class_742;
import net.minecraft.class_757;
import net.minecraft.class_7833;
import net.minecraft.class_892;
import net.minecraft.class_897;
import net.minecraft.class_922;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_5253.class_5254;
import net.minecraft.class_572.class_573;
import org.itsmahir.pagon.api.management.RotationManager;
import org.itsmahir.pagon.api.utils.ducks.ILivingEntity;
import org.itsmahir.pagon.api.utils.render.world.buffers.RenderBuffers;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector4f;

public class ChamsModelRenderer implements IMinecraft {
   private static final float SINE_45_DEGREES = (float)Math.sin((Math.PI / 4D));
   private static final class_4587 matrices = new class_4587();
   private static final Vector4f pos1 = new Vector4f();
   private static final Vector4f pos2 = new Vector4f();
   private static final Vector4f pos3 = new Vector4f();
   private static final Vector4f pos4 = new Vector4f();

   private static class_2960 getEntityTexture(class_1297 entity) {
      if (entity instanceof class_742 player) {
         return player.method_52814().comp_1626();
      } else {
         class_897 renderer = class_310.method_1551().method_1561().method_3953(entity);
         return renderer != null ? renderer.method_3931(entity) : null;
      }
   }

   public static void setupPlayerTransforms(class_742 abstractClientPlayerEntity, class_4587 matrixStack, float f, float g, float h) {
      float i = abstractClientPlayerEntity.method_6024(h);
      float j = abstractClientPlayerEntity.method_5695(h);
      if (abstractClientPlayerEntity.method_6128()) {
         setupTransforms(abstractClientPlayerEntity, matrixStack, f, g, h);
         float k = (float)abstractClientPlayerEntity.method_6003() + h;
         float l = class_3532.method_15363(k * k / 100.0F, 0.0F, 1.0F);
         if (!abstractClientPlayerEntity.method_6123()) {
            matrixStack.method_22907(class_7833.field_40714.rotationDegrees(l * (-90.0F - j)));
         }

         class_243 vec3d = abstractClientPlayerEntity.method_5828(h);
         class_243 vec3d2 = abstractClientPlayerEntity.method_49339(h);
         double d = vec3d2.method_37268();
         double e = vec3d.method_37268();
         if (d > (double)0.0F && e > (double)0.0F) {
            double m = (vec3d2.field_1352 * vec3d.field_1352 + vec3d2.field_1350 * vec3d.field_1350) / Math.sqrt(d * e);
            double n = vec3d2.field_1352 * vec3d.field_1350 - vec3d2.field_1350 * vec3d.field_1352;
            matrixStack.method_22907(class_7833.field_40716.rotation((float)(Math.signum(n) * Math.acos(m))));
         }
      } else if (i > 0.0F) {
         setupTransforms(abstractClientPlayerEntity, matrixStack, f, g, h);
         float k = abstractClientPlayerEntity.method_5799() ? -90.0F - j : -90.0F;
         float l = class_3532.method_16439(i, 0.0F, k);
         matrixStack.method_22907(class_7833.field_40714.rotationDegrees(l));
         if (abstractClientPlayerEntity.method_20232()) {
            matrixStack.method_46416(0.0F, -1.0F, 0.3F);
         }
      } else {
         setupTransforms(abstractClientPlayerEntity, matrixStack, f, g, h);
      }

   }

   public static void setupTransforms(class_1309 entity, class_4587 matrixStack, float animationProgress, float bodyYaw, float tickDelta) {
      if (entity.method_32314()) {
         bodyYaw += (float)(Math.cos((double)entity.field_6012 * (double)3.25F) * Math.PI * (double)0.4F);
      }

      if (!entity.method_41328(class_4050.field_18078)) {
         matrixStack.method_22907(class_7833.field_40716.rotationDegrees(180.0F - bodyYaw));
      }

      if (entity.field_6213 > 0) {
         float f = ((float)entity.field_6213 + tickDelta - 1.0F) / 20.0F * 1.6F;
         if ((f = class_3532.method_15355(f)) > 1.0F) {
            f = 1.0F;
         }

         matrixStack.method_22907(class_7833.field_40718.rotationDegrees(f * 90.0F));
      } else if (entity.method_6123()) {
         matrixStack.method_22907(class_7833.field_40714.rotationDegrees(-90.0F - entity.method_36455()));
         matrixStack.method_22907(class_7833.field_40716.rotationDegrees(((float)entity.field_6012 + tickDelta) * -75.0F));
      } else if (entity.method_41328(class_4050.field_18078)) {
         class_2350 direction = entity.method_18401();
         float g = direction != null ? getYaw(direction) : bodyYaw;
         matrixStack.method_22907(class_7833.field_40716.rotationDegrees(g));
         matrixStack.method_22907(class_7833.field_40718.rotationDegrees(90.0F));
         matrixStack.method_22907(class_7833.field_40716.rotationDegrees(270.0F));
      } else if (class_922.method_38563(entity)) {
         matrixStack.method_46416(0.0F, entity.method_17682() + 0.1F, 0.0F);
         matrixStack.method_22907(class_7833.field_40718.rotationDegrees(180.0F));
      }

   }

   private static float getYaw(class_2350 direction) {
      float var10000;
      switch (direction) {
         case field_11035 -> var10000 = 90.0F;
         case field_11039 -> var10000 = 0.0F;
         case field_11043 -> var10000 = 270.0F;
         case field_11034 -> var10000 = 180.0F;
         default -> var10000 = 0.0F;
      }

      return var10000;
   }

   public static void render(class_4587 matrixStack, class_1297 entity, float tickDelta, int color, int lineColor, float lineWidth, boolean lines, boolean fill, boolean shine) {
      class_2960 textureId = shine ? getEntityTexture(entity) : null;
      double offsetX = class_3532.method_16436((double)tickDelta, entity.field_6038, entity.method_23317());
      double offsetY = class_3532.method_16436((double)tickDelta, entity.field_5971, entity.method_23318());
      double offsetZ = class_3532.method_16436((double)tickDelta, entity.field_5989, entity.method_23321());
      matrices.method_22903();
      class_897<?> entityRenderer = mc.method_1561().method_3953(entity);
      if (entityRenderer instanceof class_922 renderer) {
         class_1309 livingEntity = (class_1309)entity;
         class_583<class_1309> model = renderer.method_4038();
         if (entityRenderer instanceof class_1007 r) {
            class_591<class_742> playerModel = (class_591)r.method_4038();
            playerModel.field_3400 = entity.method_18276();
            class_572.class_573 armPose = class_1007.method_4210((class_742)entity, class_1268.field_5808);
            class_572.class_573 armPose2 = class_1007.method_4210((class_742)entity, class_1268.field_5810);
            if (armPose.method_30156()) {
               armPose2 = livingEntity.method_6079().method_7960() ? class_573.field_3409 : class_573.field_3410;
            }

            if (livingEntity.method_6068() == class_1306.field_6183) {
               playerModel.field_3395 = armPose;
               playerModel.field_3399 = armPose2;
            } else {
               playerModel.field_3395 = armPose2;
               playerModel.field_3399 = armPose;
            }
         }

         model.field_3447 = livingEntity.method_6055(tickDelta);
         model.field_3449 = livingEntity.method_5765();
         model.field_3448 = livingEntity.method_6109();
         boolean rotating = entity == mc.field_1724 && !RotationManager.INSTANCE.FROM_INV;
         float bodyYaw = class_3532.method_17821(tickDelta, livingEntity.field_6220, livingEntity.field_6283);
         float headYaw = class_3532.method_17821(tickDelta, livingEntity.field_6259, livingEntity.field_6241);
         if (rotating) {
            ILivingEntity accessor = (ILivingEntity)livingEntity;
            headYaw = class_3532.method_17821(tickDelta, accessor.pagon_getPrevHeadYaw(), accessor.pagon_getHeadYaw());
         }

         float yaw = headYaw - bodyYaw;
         if (livingEntity.method_5765()) {
            class_1297 var26 = livingEntity.method_5854();
            if (var26 instanceof class_1309) {
               class_1309 livingEntity2 = (class_1309)var26;
               bodyYaw = class_3532.method_17821(tickDelta, livingEntity2.field_6220, livingEntity2.field_6283);
               yaw = headYaw - bodyYaw;
               float animationProgress = class_3532.method_15393(yaw);
               if (animationProgress < -85.0F) {
                  animationProgress = -85.0F;
               }

               if (animationProgress >= 85.0F) {
                  animationProgress = 85.0F;
               }

               bodyYaw = headYaw - animationProgress;
               if (animationProgress * animationProgress > 2500.0F) {
                  bodyYaw = (float)((double)bodyYaw + (double)animationProgress * 0.2);
               }

               yaw = headYaw - bodyYaw;
            }
         }

         float pitch = class_3532.method_16439(tickDelta, livingEntity.field_6004, livingEntity.method_36455());
         if (rotating) {
            ILivingEntity accessor = (ILivingEntity)livingEntity;
            pitch = class_3532.method_17821(tickDelta, accessor.pagon_getPrevHeadPitch(), accessor.pagon_getHeadPitch());
         }

         float animationProgress = renderer.method_4045(livingEntity, tickDelta);
         float limbDistance = 0.0F;
         float limbAngle = 0.0F;
         if (!livingEntity.method_5765() && livingEntity.method_5805()) {
            limbDistance = livingEntity.field_42108.method_48570(tickDelta);
            limbAngle = livingEntity.field_42108.method_48572(tickDelta);
            if (livingEntity.method_6109()) {
               limbAngle *= 3.0F;
            }

            if (limbDistance > 1.0F) {
               limbDistance = 1.0F;
            }
         }

         model.method_2816(livingEntity, limbAngle, limbDistance, tickDelta);
         model.method_2819(livingEntity, limbAngle, limbDistance, animationProgress, yaw, pitch);
         renderer.method_4058(livingEntity, matrices, animationProgress, bodyYaw, tickDelta, 1.0F);
         matrices.method_22905(-1.0F, -1.0F, 1.0F);
         renderer.method_4042(livingEntity, matrices, tickDelta);
         matrices.method_22904((double)0.0F, (double)-1.501F, (double)0.0F);
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
                  render(matrixStack, mo.field_3398, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
               } else {
                  m.method_22946().forEach((modelPart) -> render(matrixStack, (class_630)modelPart, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId));
               }

               matrices.method_22909();
               matrices.method_22903();
               float g = 1.0F / m.field_20919;
               matrices.method_22905(g, g, g);
               matrices.method_22904((double)0.0F, (double)(m.field_20920 / 16.0F), (double)0.0F);
               if (model instanceof class_572) {
                  class_572 mo = (class_572)model;
                  render(matrixStack, mo.field_3391, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
                  render(matrixStack, mo.field_27433, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
                  render(matrixStack, mo.field_3401, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
                  render(matrixStack, mo.field_3397, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
                  render(matrixStack, mo.field_3392, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
               } else {
                  m.method_22948().forEach((modelPart) -> render(matrixStack, (class_630)modelPart, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId));
               }

               matrices.method_22909();
            } else if (model instanceof class_572) {
               class_572 mo = (class_572)model;
               render(matrixStack, mo.field_3398, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
               render(matrixStack, mo.field_3391, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
               render(matrixStack, mo.field_27433, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
               render(matrixStack, mo.field_3401, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
               render(matrixStack, mo.field_3397, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
               render(matrixStack, mo.field_3392, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
               if (model instanceof class_591) {
                  class_591<class_1309> pm = (class_591)model;
                  pm.field_3394.field_3665 = true;
                  pm.field_3486.field_3665 = true;
                  pm.field_3484.field_3665 = true;
                  pm.field_3483.field_3665 = true;
                  pm.field_3482.field_3665 = true;
                  pm.field_3479.field_3665 = true;
                  render(matrixStack, pm.field_3394, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
                  render(matrixStack, pm.field_3486, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
                  render(matrixStack, pm.field_3484, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
                  render(matrixStack, pm.field_3483, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
                  render(matrixStack, pm.field_3482, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
                  render(matrixStack, pm.field_3479, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
               }
            } else {
               m.method_22946().forEach((modelPart) -> render(matrixStack, (class_630)modelPart, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId));
               m.method_22948().forEach((modelPart) -> render(matrixStack, (class_630)modelPart, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId));
            }
         } else if (model instanceof class_5597 m) {
            render(matrixStack, m.method_32008(), offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
         } else if (model instanceof class_4595 m) {
            m.method_22960().forEach((modelPart) -> render(matrixStack, (class_630)modelPart, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId));
         }
      }

      if (entityRenderer instanceof class_892 renderer) {
         class_1511 crystalEntity = (class_1511)entity;
         matrices.method_22903();
         float h = class_892.method_23155(crystalEntity, tickDelta);
         float j = ((float)crystalEntity.field_7034 + tickDelta) * 3.0F;
         matrices.method_22903();
         matrices.method_22905(2.0F, 2.0F, 2.0F);
         matrices.method_46416(0.0F, -0.5F, 0.0F);
         matrices.method_22907(class_7833.field_40716.rotationDegrees(j));
         matrices.method_22904((double)0.0F, (double)(1.5F + h / 2.0F), (double)0.0F);
         matrices.method_22907((new Quaternionf()).setAngleAxis(((float)Math.PI / 3F), SINE_45_DEGREES, 0.0F, SINE_45_DEGREES));
         render(matrixStack, renderer.field_21004, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
         matrices.method_22905(0.875F, 0.875F, 0.875F);
         matrices.method_22907((new Quaternionf()).setAngleAxis(((float)Math.PI / 3F), SINE_45_DEGREES, 0.0F, SINE_45_DEGREES));
         matrices.method_22907(class_7833.field_40716.rotationDegrees(j));
         render(matrixStack, renderer.field_21004, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
         matrices.method_22905(0.875F, 0.875F, 0.875F);
         matrices.method_22907((new Quaternionf()).setAngleAxis(((float)Math.PI / 3F), SINE_45_DEGREES, 0.0F, SINE_45_DEGREES));
         matrices.method_22907(class_7833.field_40716.rotationDegrees(j));
         matrices.method_22909();
         matrices.method_22909();
      }

      matrices.method_22909();
   }

   public static void render(class_4587 matrixStack, class_630 part, double offsetX, double offsetY, double offsetZ, int color, int lineColor, float lineWidth, boolean lines, boolean fill, boolean shine, class_2960 textureId) {
      if (part.field_3665 && (!part.field_3663.isEmpty() || !part.field_3661.isEmpty())) {
         matrices.method_22903();
         part.method_22703(matrices);

         for(class_630.class_628 cuboid : part.field_3663) {
            renderModelPart(matrixStack, cuboid, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
         }

         for(class_630 child : part.field_3661.values()) {
            render(matrixStack, child, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
         }

         matrices.method_22909();
      }
   }

   private static void renderModelPart(class_4587 matrixStack, class_630.class_628 cuboid, double offsetX, double offsetY, double offsetZ, int color, int lineColor, float lineWidth, boolean lines, boolean fill, boolean shine, class_2960 textureId) {
      Matrix4f matrix = matrices.method_23760().method_23761();

      for(class_630.class_593 quad : cuboid.field_3649) {
         pos1.set(quad.field_3502[0].field_3605.x / 16.0F, quad.field_3502[0].field_3605.y / 16.0F, quad.field_3502[0].field_3605.z / 16.0F, 1.0F);
         pos1.mul(matrix);
         pos2.set(quad.field_3502[1].field_3605.x / 16.0F, quad.field_3502[1].field_3605.y / 16.0F, quad.field_3502[1].field_3605.z / 16.0F, 1.0F);
         pos2.mul(matrix);
         pos3.set(quad.field_3502[2].field_3605.x / 16.0F, quad.field_3502[2].field_3605.y / 16.0F, quad.field_3502[2].field_3605.z / 16.0F, 1.0F);
         pos3.mul(matrix);
         pos4.set(quad.field_3502[3].field_3605.x / 16.0F, quad.field_3502[3].field_3605.y / 16.0F, quad.field_3502[3].field_3605.z / 16.0F, 1.0F);
         pos4.mul(matrix);
         if (fill) {
            RenderBuffers.QUADS.begin(matrixStack);
            RenderBuffers.QUADS.color(color);
            RenderBuffers.QUADS.vertex(offsetX + (double)pos1.x, offsetY + (double)pos1.y, offsetZ + (double)pos1.z).vertex(offsetX + (double)pos2.x, offsetY + (double)pos2.y, offsetZ + (double)pos2.z).vertex(offsetX + (double)pos3.x, offsetY + (double)pos3.y, offsetZ + (double)pos3.z).vertex(offsetX + (double)pos4.x, offsetY + (double)pos4.y, offsetZ + (double)pos4.z);
            RenderBuffers.QUADS.end();
            if (shine && textureId != null) {
               RenderSystem.setShaderTexture(0, textureId);
               RenderSystem.setShader(class_757::method_34543);
               class_289 tessellator = RenderSystem.renderThreadTesselator();
               class_287 buffer = tessellator.method_60827(class_5596.field_27382, class_290.field_1575);
               float r = (float)class_5254.method_27765(color) / 255.0F;
               float g = (float)class_5254.method_27766(color) / 255.0F;
               float b = (float)class_5254.method_27767(color) / 255.0F;
               float a = (float)class_5254.method_27762(color) / 255.0F;
               buffer.method_22918(matrix, (float)(offsetX + (double)pos1.x), (float)(offsetY + (double)pos1.y), (float)(offsetZ + (double)pos1.z)).method_22913(quad.field_3502[0].field_3604, quad.field_3502[0].field_3603).method_22915(r, g, b, a);
               buffer.method_22918(matrix, (float)(offsetX + (double)pos2.x), (float)(offsetY + (double)pos2.y), (float)(offsetZ + (double)pos2.z)).method_22913(quad.field_3502[1].field_3604, quad.field_3502[1].field_3603).method_22915(r, g, b, a);
               buffer.method_22918(matrix, (float)(offsetX + (double)pos3.x), (float)(offsetY + (double)pos3.y), (float)(offsetZ + (double)pos3.z)).method_22913(quad.field_3502[2].field_3604, quad.field_3502[2].field_3603).method_22915(r, g, b, a);
               buffer.method_22918(matrix, (float)(offsetX + (double)pos4.x), (float)(offsetY + (double)pos4.y), (float)(offsetZ + (double)pos4.z)).method_22913(quad.field_3502[3].field_3604, quad.field_3502[3].field_3603).method_22915(r, g, b, a);
               class_286.method_43433(buffer.method_60800());
            }
         }

         if (lines) {
            RenderBuffers.LINES.begin(matrixStack);
            RenderSystem.lineWidth(lineWidth);
            RenderBuffers.LINES.color(lineColor);
            RenderBuffers.LINES.vertexLine(offsetX + (double)pos1.x, offsetY + (double)pos1.y, offsetZ + (double)pos1.z, offsetX + (double)pos2.x, offsetY + (double)pos2.y, offsetZ + (double)pos2.z);
            RenderBuffers.LINES.vertexLine(offsetX + (double)pos2.x, offsetY + (double)pos2.y, offsetZ + (double)pos2.z, offsetX + (double)pos3.x, offsetY + (double)pos3.y, offsetZ + (double)pos3.z);
            RenderBuffers.LINES.vertexLine(offsetX + (double)pos3.x, offsetY + (double)pos3.y, offsetZ + (double)pos3.z, offsetX + (double)pos4.x, offsetY + (double)pos4.y, offsetZ + (double)pos4.z);
            RenderBuffers.LINES.vertexLine(offsetX + (double)pos1.x, offsetY + (double)pos1.y, offsetZ + (double)pos1.z, offsetX + (double)pos1.x, offsetY + (double)pos1.y, offsetZ + (double)pos1.z);
            RenderBuffers.LINES.end();
         }
      }

   }

   public static void renderHand(class_4587 matrixStack, float tickDelta, int lineColor, int color, float lineWidth, boolean lines, boolean fill, boolean shine) {
      if (mc.field_1690.method_31044().method_31034()) {
         class_2960 textureId = null;
         if (shine && mc.field_1724 instanceof class_742) {
            textureId = mc.field_1724.method_52814().comp_1626();
         }

         double d = (double)(Integer)mc.field_1690.method_41808().method_41753();
         matrixStack.method_34425(mc.field_1773.method_22973(d));
         matrixStack.method_34426();
         if ((Boolean)mc.field_1690.method_42448().method_41753()) {
            class_1657 playerEntity = (class_1657)mc.method_1560();
            float f = playerEntity.field_5973 - playerEntity.field_6039;
            float g = -(playerEntity.field_5973 + f * tickDelta);
            float h = class_3532.method_16439(tickDelta, playerEntity.field_7505, playerEntity.field_7483);
            matrixStack.method_46416(class_3532.method_15374(g * (float)Math.PI) * h * 0.5F, -Math.abs(class_3532.method_15362(g * (float)Math.PI) * h), 0.0F);
            matrixStack.method_22907(class_7833.field_40718.rotationDegrees(class_3532.method_15374(g * (float)Math.PI) * h * 3.0F));
            matrixStack.method_22907(class_7833.field_40714.rotationDegrees(Math.abs(class_3532.method_15362(g * (float)Math.PI - 0.2F) * h) * 5.0F));
         }

         float h1 = class_3532.method_16439(tickDelta, mc.field_1724.field_3914, mc.field_1724.field_3916);
         float i1 = class_3532.method_16439(tickDelta, mc.field_1724.field_3931, mc.field_1724.field_3932);
         matrixStack.method_22907(class_7833.field_40714.rotationDegrees((mc.field_1724.method_5695(tickDelta) - h1) * 0.1F));
         matrixStack.method_22907(class_7833.field_40716.rotationDegrees((mc.field_1724.method_5705(tickDelta) - i1) * 0.1F));
         float f1 = mc.field_1724.method_6055(tickDelta);
         class_1268 hand = (class_1268)MoreObjects.firstNonNull(mc.field_1724.field_6266, class_1268.field_5808);
         class_1799 itemStack = mc.field_1724.method_6047();
         class_1799 itemStack2 = mc.field_1724.method_6079();
         boolean bl = itemStack.method_31574(class_1802.field_8102) || itemStack2.method_31574(class_1802.field_8102);
         boolean bl2 = itemStack.method_31574(class_1802.field_8399) || itemStack2.method_31574(class_1802.field_8399);
         HandRenderType handRenderType = ChamsModelRenderer.HandRenderType.RENDER_BOTH_HANDS;
         if (!bl && !bl2) {
            handRenderType = ChamsModelRenderer.HandRenderType.RENDER_BOTH_HANDS;
         } else if (mc.field_1724.method_6115()) {
            class_1799 itemStack1 = mc.field_1724.method_6030();
            class_1268 hand1 = mc.field_1724.method_6058();
            if (!itemStack1.method_31574(class_1802.field_8102) && !itemStack1.method_31574(class_1802.field_8399)) {
               handRenderType = hand == class_1268.field_5808 && mc.field_1724.method_6079().method_31574(class_1802.field_8399) && class_1764.method_7781(mc.field_1724.method_6079()) ? ChamsModelRenderer.HandRenderType.RENDER_MAIN_HAND_ONLY : ChamsModelRenderer.HandRenderType.RENDER_BOTH_HANDS;
            } else {
               handRenderType = ChamsModelRenderer.HandRenderType.shouldOnlyRender(hand1);
            }
         } else if (itemStack.method_31574(class_1802.field_8399) && class_1764.method_7781(itemStack)) {
            handRenderType = ChamsModelRenderer.HandRenderType.RENDER_MAIN_HAND_ONLY;
         }

         class_1007 playerEntityRenderer = (class_1007)mc.method_1561().method_3953(mc.field_1724);
         if (handRenderType.renderMainHand) {
            boolean bl1 = hand == class_1268.field_5808;
            boolean bl3 = mc.field_1724.field_6266 == class_1268.field_5808;
            float j = bl3 ? f1 : 0.0F;
            float k = 1.0F - class_3532.method_16439(tickDelta, mc.field_1773.field_4012.field_4053, mc.field_1773.field_4012.field_4043);
            class_1306 arm = bl1 ? mc.field_1724.method_6068() : mc.field_1724.method_6068().method_5928();
            if (itemStack.method_7960() && bl1 && !mc.field_1724.method_5767()) {
               renderFirstPersonItem(matrixStack, tickDelta, playerEntityRenderer, arm, j, k, lineColor, color, lineWidth, lines, fill, shine, textureId);
            }
         }

         if (handRenderType.renderOffHand) {
            boolean bl1 = hand == class_1268.field_5810;
            boolean bl3 = mc.field_1724.field_6266 == class_1268.field_5808;
            float j = bl3 ? f1 : 0.0F;
            float k = 1.0F - class_3532.method_16439(tickDelta, mc.field_1773.field_4012.field_4051, mc.field_1773.field_4012.field_4052);
            class_1306 arm = bl1 ? mc.field_1724.method_6068().method_5928() : mc.field_1724.method_6068();
            if (itemStack.method_7960() && bl1 && !mc.field_1724.method_5767()) {
               renderFirstPersonItem(matrixStack, tickDelta, playerEntityRenderer, arm, j, k, lineColor, color, lineWidth, lines, fill, shine, textureId);
            }
         }

      }
   }

   public static void renderFirstPersonItem(class_4587 matrixStack, float tickDelta, class_1007 playerEntityRenderer, class_1306 arm, float swingProgress, float equipProgress, int lineColor, int color, float lineWidth, boolean lines, boolean fill, boolean shine, class_2960 textureId) {
      RenderSystem.disableDepthTest();
      double offsetX = mc.field_1773.method_19418().method_19326().field_1352;
      double offsetY = mc.field_1773.method_19418().method_19326().field_1351;
      double offsetZ = mc.field_1773.method_19418().method_19326().field_1350;
      matrices.method_22903();
      boolean bl = arm != class_1306.field_6182;
      float f = bl ? 1.0F : -1.0F;
      float g = class_3532.method_15355(swingProgress);
      float h = -0.3F * class_3532.method_15374(g * (float)Math.PI);
      float i = 0.4F * class_3532.method_15374(g * ((float)Math.PI * 2F));
      float j = -0.4F * class_3532.method_15374(swingProgress * (float)Math.PI);
      matrices.method_46416(f * (h + 0.64000005F), i + -0.6F + equipProgress * -0.6F, j + -0.71999997F);
      matrices.method_22907(class_7833.field_40716.rotationDegrees(f * 45.0F));
      float k = class_3532.method_15374(swingProgress * swingProgress * (float)Math.PI);
      float l = class_3532.method_15374(g * (float)Math.PI);
      matrices.method_22907(class_7833.field_40716.rotationDegrees(f * l * 70.0F));
      matrices.method_22907(class_7833.field_40718.rotationDegrees(f * k * -20.0F));
      matrices.method_46416(f * -1.0F, 3.6F, 3.5F);
      matrices.method_22907(class_7833.field_40718.rotationDegrees(f * 120.0F));
      matrices.method_22907(class_7833.field_40714.rotationDegrees(200.0F));
      matrices.method_22907(class_7833.field_40716.rotationDegrees(f * -135.0F));
      matrices.method_46416(f * 5.6F, 0.0F, 0.0F);
      playerEntityRenderer.method_4218(mc.field_1724);
      class_591 model = (class_591)playerEntityRenderer.method_4038();
      model.field_3447 = 0.0F;
      model.field_3400 = false;
      model.field_3396 = 0.0F;
      model.method_17087(mc.field_1724, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
      if (arm == class_1306.field_6183) {
         model.field_3401.field_3654 = 0.0F;
         render(matrixStack, model.field_3401, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
         model.field_3486.field_3654 = 0.0F;
         render(matrixStack, model.field_3486, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
      } else {
         model.field_27433.field_3654 = 0.0F;
         render(matrixStack, model.field_27433, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
         model.field_3484.field_3654 = 0.0F;
         render(matrixStack, model.field_3484, offsetX, offsetY, offsetZ, color, lineColor, lineWidth, lines, fill, shine, textureId);
      }

      matrices.method_22909();
   }

   public static void renderHand(class_4587 matrixStack, float tickDelta, Color lineColor, Color color, float lineWidth, boolean lines, boolean fill, boolean shine) {
      renderHand(matrixStack, tickDelta, lineColor.getRGB(), color.getRGB(), lineWidth, lines, fill, shine);
   }

   public static void render(class_4587 matrixStack, class_1297 entity, float tickDelta, Color color, Color lineColor, float lineWidth, boolean lines, boolean fill, boolean shine) {
      render(matrixStack, entity, tickDelta, color.getRGB(), lineColor.getRGB(), lineWidth, lines, fill, shine);
   }

   public static void render(class_4587 matrixStack, class_630 part, Color color, Color lineColor, float lineWidth, boolean lines, boolean fill, boolean shine) {
      render(matrixStack, part, (double)0.0F, (double)0.0F, (double)0.0F, color.getRGB(), lineColor.getRGB(), lineWidth, lines, fill, shine, (class_2960)null);
   }

   public static enum HandRenderType {
      RENDER_BOTH_HANDS(true, true),
      RENDER_MAIN_HAND_ONLY(true, false),
      RENDER_OFF_HAND_ONLY(false, true);

      public final boolean renderMainHand;
      public final boolean renderOffHand;

      private HandRenderType(boolean renderMainHand, boolean renderOffHand) {
         this.renderMainHand = renderMainHand;
         this.renderOffHand = renderOffHand;
      }

      public static HandRenderType shouldOnlyRender(class_1268 hand) {
         return hand == class_1268.field_5808 ? RENDER_MAIN_HAND_ONLY : RENDER_OFF_HAND_ONLY;
      }

      // $FF: synthetic method
      private static HandRenderType[] $values() {
         return new HandRenderType[]{RENDER_BOTH_HANDS, RENDER_MAIN_HAND_ONLY, RENDER_OFF_HAND_ONLY};
      }
   }
}
