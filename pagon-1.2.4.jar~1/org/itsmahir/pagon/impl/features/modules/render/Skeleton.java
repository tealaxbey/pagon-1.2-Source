package org.itsmahir.pagon.impl.features.modules.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_1007;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1306;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_572;
import net.minecraft.class_591;
import net.minecraft.class_630;
import net.minecraft.class_742;
import net.minecraft.class_757;
import net.minecraft.class_7833;
import net.minecraft.class_922;
import net.minecraft.class_572.class_573;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.RotationManager;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.ducks.ILivingEntity;
import org.itsmahir.pagon.api.utils.render.Interpolator;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.world.buffers.RenderBuffers;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.movement.ElytraFly;
import org.itsmahir.pagon.impl.features.modules.movement.FakeFly;
import org.itsmahir.pagon.impl.features.modules.movement.LongJump;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

public class Skeleton extends Module {
   public Value<PagonColor> color = (new ValueBuilder<PagonColor>()).withDescriptor("Color").withValue(new PagonColor(255, 255, 255)).register(this);
   public static Skeleton INSTANCE;

   public Skeleton() {
      super("Skeleton", Feature.Category.Render);
      INSTANCE = this;
   }

   @SubscribeEvent(4)
   public void onRenderWorld(RenderWorldEvent event) {
      if (mc.field_1773 != null && mc.method_1560() != null) {
         float g = event.getTickDelta();
         RenderSystem.setShader(class_757::method_34540);
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableDepthTest();
         class_310 var10000 = mc;
         RenderSystem.depthMask(class_310.method_1517());
         RenderSystem.enableCull();

         for(class_1297 entity : mc.field_1687.method_18112()) {
            if (entity != null && entity.method_5805() && entity instanceof class_1657) {
               class_1657 playerEntity = (class_1657)entity;
               if (!mc.field_1690.method_31044().method_31034() || playerEntity != mc.field_1724) {
                  class_243 skeletonPos = Interpolator.getInterpolatedPosition(entity, g);
                  class_1007 livingEntityRenderer = (class_1007)((class_922)mc.method_1561().method_3953(playerEntity));
                  class_591<class_742> playerModel = (class_591)livingEntityRenderer.method_4038();
                  float h = class_3532.method_17821(g, playerEntity.field_6220, playerEntity.field_6283);
                  float j = class_3532.method_17821(g, playerEntity.field_6259, playerEntity.field_6241);
                  if (entity == mc.field_1724 && !RotationManager.INSTANCE.FROM_INV) {
                     ILivingEntity accessor = (ILivingEntity)entity;
                     j = class_3532.method_17821(g, accessor.pagon_getPrevHeadYaw(), accessor.pagon_getHeadYaw());
                  }

                  class_572.class_573 armPose = class_1007.method_4210((class_742)playerEntity, class_1268.field_5808);
                  class_572.class_573 armPose2 = class_1007.method_4210((class_742)playerEntity, class_1268.field_5810);
                  if (armPose.method_30156()) {
                     armPose2 = playerEntity.method_6079().method_7960() ? class_573.field_3409 : class_573.field_3410;
                  }

                  if (playerEntity.method_6068() == class_1306.field_6183) {
                     playerModel.field_3395 = armPose;
                     playerModel.field_3399 = armPose2;
                  } else {
                     playerModel.field_3395 = armPose2;
                     playerModel.field_3399 = armPose;
                  }

                  float n = 0.0F;
                  float o = 0.0F;
                  if (!playerEntity.method_5765() && playerEntity.method_5805()) {
                     n = playerEntity.field_42108.method_48570(event.getTickDelta());
                     o = playerEntity.field_42108.method_48572(event.getTickDelta());
                     if (playerEntity.method_6109()) {
                        o *= 3.0F;
                     }

                     if (n > 1.0F) {
                        n = 1.0F;
                     }
                  }

                  float l = (float)playerEntity.field_6012 + event.getTickDelta();
                  float k = j - h;
                  float m = playerEntity.method_5695(g);
                  if (playerEntity == mc.field_1724 && !RotationManager.INSTANCE.FROM_INV) {
                     ILivingEntity accessor = (ILivingEntity)playerEntity;
                     m = class_3532.method_17821(g, accessor.pagon_getPrevHeadPitch(), accessor.pagon_getHeadPitch());
                  }

                  playerModel.method_17086((class_742)playerEntity, o, n, event.getTickDelta());
                  playerModel.method_17087((class_742)playerEntity, o, n, l, k, m);
                  boolean swimming = playerEntity.method_20232();
                  boolean sneaking = playerEntity.method_18276();
                  boolean flying = playerEntity.method_6128();
                  if (entity == mc.field_1724 && (LongJump.isGrimJumping() || FakeFly.isFakeFlying() || ElytraFly.isPacketFlying())) {
                     flying = false;
                  }

                  class_630 head = playerModel.field_3398;
                  class_630 leftArm = playerModel.field_27433;
                  class_630 rightArm = playerModel.field_3401;
                  class_630 leftLeg = playerModel.field_3397;
                  class_630 rightLeg = playerModel.field_3392;
                  playerModel.field_3400 = entity.method_18276();
                  class_4587 matrixStack = RenderUtil.matrixFrom(skeletonPos.field_1352, skeletonPos.field_1351, skeletonPos.field_1350);
                  matrixStack.method_22903();
                  if (swimming) {
                     matrixStack.method_46416(0.0F, 0.35F, 0.0F);
                  }

                  matrixStack.method_22907((new Quaternionf()).setAngleAxis((double)(h + 180.0F) * Math.PI / (double)180.0F, (double)0.0F, (double)-1.0F, (double)0.0F));
                  if (swimming || flying) {
                     matrixStack.method_22907((new Quaternionf()).setAngleAxis((double)(90.0F + m) * Math.PI / (double)180.0F, (double)-1.0F, (double)0.0F, (double)0.0F));
                  }

                  if (swimming) {
                     matrixStack.method_46416(0.0F, -0.95F, 0.0F);
                  }

                  Matrix4f matrix4f = matrixStack.method_23760().method_23761();
                  RenderBuffers.LINES.begin(matrix4f);
                  RenderBuffers.LINES.color(((PagonColor)this.color.getValue()).getColor());
                  RenderBuffers.LINES.vertex(0.0F, sneaking ? 0.6F : 0.7F, sneaking ? 0.23F : 0.0F);
                  RenderBuffers.LINES.vertex(0.0F, sneaking ? 1.05F : 1.4F, 0.0F);
                  RenderBuffers.LINES.vertex(-0.37F, sneaking ? 1.05F : 1.35F, 0.0F);
                  RenderBuffers.LINES.vertex(0.37F, sneaking ? 1.05F : 1.35F, 0.0F);
                  RenderBuffers.LINES.vertex(-0.15F, sneaking ? 0.6F : 0.7F, sneaking ? 0.23F : 0.0F);
                  RenderBuffers.LINES.vertex(0.15F, sneaking ? 0.6F : 0.7F, sneaking ? 0.23F : 0.0F);
                  matrixStack.method_22903();
                  matrixStack.method_46416(0.0F, sneaking ? 1.05F : 1.4F, 0.0F);
                  this.rotateSkeleton(matrixStack, head);
                  matrix4f = matrixStack.method_23760().method_23761();
                  RenderBuffers.LINES.vertex(matrix4f, 0.0F, 0.0F, 0.0F);
                  RenderBuffers.LINES.vertex(matrix4f, 0.0F, 0.25F, 0.0F);
                  matrixStack.method_22909();
                  matrixStack.method_22903();
                  matrixStack.method_46416(0.15F, sneaking ? 0.6F : 0.7F, sneaking ? 0.23F : 0.0F);
                  this.rotateSkeleton(matrixStack, rightLeg);
                  matrix4f = matrixStack.method_23760().method_23761();
                  RenderBuffers.LINES.vertex(matrix4f, 0.0F, 0.0F, 0.0F);
                  RenderBuffers.LINES.vertex(matrix4f, 0.0F, -0.6F, 0.0F);
                  matrixStack.method_22909();
                  matrixStack.method_22903();
                  matrixStack.method_46416(-0.15F, sneaking ? 0.6F : 0.7F, sneaking ? 0.23F : 0.0F);
                  this.rotateSkeleton(matrixStack, leftLeg);
                  matrix4f = matrixStack.method_23760().method_23761();
                  RenderBuffers.LINES.vertex(matrix4f, 0.0F, 0.0F, 0.0F);
                  RenderBuffers.LINES.vertex(matrix4f, 0.0F, -0.6F, 0.0F);
                  matrixStack.method_22909();
                  matrixStack.method_22903();
                  matrixStack.method_46416(0.37F, sneaking ? 1.05F : 1.35F, 0.0F);
                  this.rotateSkeleton(matrixStack, rightArm);
                  matrix4f = matrixStack.method_23760().method_23761();
                  RenderBuffers.LINES.vertex(matrix4f, 0.0F, 0.0F, 0.0F);
                  RenderBuffers.LINES.vertex(matrix4f, 0.0F, -0.55F, 0.0F);
                  matrixStack.method_22909();
                  matrixStack.method_22903();
                  matrixStack.method_46416(-0.37F, sneaking ? 1.05F : 1.35F, 0.0F);
                  this.rotateSkeleton(matrixStack, leftArm);
                  matrix4f = matrixStack.method_23760().method_23761();
                  RenderBuffers.LINES.vertex(matrix4f, 0.0F, 0.0F, 0.0F);
                  RenderBuffers.LINES.vertex(matrix4f, 0.0F, -0.55F, 0.0F);
                  matrixStack.method_22909();
                  RenderBuffers.LINES.draw();
                  if (swimming) {
                     matrixStack.method_46416(0.0F, 0.95F, 0.0F);
                  }

                  if (swimming || flying) {
                     matrixStack.method_22907((new Quaternionf()).setAngleAxis((double)(90.0F + m) * Math.PI / (double)180.0F, (double)1.0F, (double)0.0F, (double)0.0F));
                  }

                  if (swimming) {
                     matrixStack.method_46416(0.0F, -0.35F, 0.0F);
                  }

                  matrixStack.method_22907((new Quaternionf()).setAngleAxis((double)(h + 180.0F) * Math.PI / (double)180.0F, (double)0.0F, (double)1.0F, (double)0.0F));
                  matrixStack.method_22904(-skeletonPos.field_1352, -skeletonPos.field_1351, -skeletonPos.field_1350);
                  matrixStack.method_22909();
               }
            }
         }

         RenderSystem.disableCull();
         RenderSystem.disableBlend();
         RenderSystem.enableDepthTest();
         RenderSystem.depthMask(true);
         RenderSystem.setShader(class_757::method_34540);
      }
   }

   private void rotateSkeleton(class_4587 matrix, class_630 modelPart) {
      if (modelPart.field_3674 != 0.0F) {
         matrix.method_22907(class_7833.field_40718.rotation(modelPart.field_3674));
      }

      if (modelPart.field_3675 != 0.0F) {
         matrix.method_22907(class_7833.field_40715.rotation(modelPart.field_3675));
      }

      if (modelPart.field_3654 != 0.0F) {
         matrix.method_22907(class_7833.field_40713.rotation(modelPart.field_3654));
      }

   }

   public String getDescription() {
      return "Skeleton: renders players skeletons";
   }
}
