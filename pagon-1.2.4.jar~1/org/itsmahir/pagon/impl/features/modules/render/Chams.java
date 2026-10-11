package org.itsmahir.pagon.impl.features.modules.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1477;
import net.minecraft.class_1493;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_3887;
import net.minecraft.class_4050;
import net.minecraft.class_4587;
import net.minecraft.class_742;
import net.minecraft.class_922;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.render.RenderCrystalEvent;
import org.itsmahir.pagon.api.event.events.render.RenderEntityEvent;
import org.itsmahir.pagon.api.event.events.render.RenderHandEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.RotationManager;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.ducks.ILivingEntity;
import org.itsmahir.pagon.api.utils.render.ChamsModelRenderer;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.world.buffers.RenderBuffers;
import org.itsmahir.pagon.api.utils.world.EntityUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;

public class Chams extends Module {
   public static Chams INSTANCE;
   public Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Fill").withModes("Normal", "Fill", "Wireframe").register(this);
   public Value<Number> range = (new ValueBuilder<Number>()).withDescriptor("Range").withValue(50).withRange(10, 200).withPlaces(1).register(this);
   public Value<Number> width = (new ValueBuilder<Number>()).withDescriptor("Width").withValue(1.5F).withRange(0.5F, 5.0F).withPlaces(1).register(this);
   public Value<Boolean> walls = (new ValueBuilder<Boolean>()).withDescriptor("ThroughWalls").withValue(true).register(this);
   public Value<Boolean> texture = (new ValueBuilder<Boolean>()).withDescriptor("Texture").withValue(false).register(this);
   public Value<Boolean> players = (new ValueBuilder<Boolean>()).withDescriptor("Players").withValue(true).register(this);
   public Value<Boolean> self;
   public Value<Boolean> hands;
   public Value<Boolean> monsters;
   public Value<Boolean> animals;
   public Value<Boolean> crystals;
   public Value<Boolean> shine;
   public Value<Number> opacity;
   public Value<PagonColor> fill;
   public Value<PagonColor> wireColor;

   public Chams() {
      super("Chams", Feature.Category.Render);
      this.self = (new ValueBuilder<Boolean>()).withDescriptor("Self").withValue(true).withParent(this.players).withParentEnabled(true).register(this);
      this.hands = (new ValueBuilder<Boolean>()).withDescriptor("Hands").withValue(true).register(this);
      this.monsters = (new ValueBuilder<Boolean>()).withDescriptor("Monsters").withValue(true).register(this);
      this.animals = (new ValueBuilder<Boolean>()).withDescriptor("Animals").withValue(true).register(this);
      this.crystals = (new ValueBuilder<Boolean>()).withDescriptor("Crystals").withValue(true).register(this);
      this.shine = (new ValueBuilder<Boolean>()).withDescriptor("Shine").withValue(false).register(this);
      this.opacity = (new ValueBuilder<Number>()).withDescriptor("Opacity").withValue(255).withRange(0, 255).withPlaces(0).withPageParent(this.mode).withPage("Fill").register(this);
      this.fill = (new ValueBuilder<PagonColor>()).withDescriptor("Fill").withValue(new PagonColor(255, 0, 0, 60)).register(this);
      this.wireColor = (new ValueBuilder<PagonColor>()).withDescriptor("Frame").withValue(new PagonColor(255, 255, 255)).register(this);
      INSTANCE = this;
   }

   private boolean isFillMode() {
      return ((String)this.mode.getValue()).equals("Fill");
   }

   private boolean isWireMode() {
      return ((String)this.mode.getValue()).equals("Wireframe");
   }

   private boolean isNormalMode() {
      return ((String)this.mode.getValue()).equals("Normal");
   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldEvent event) {
      if (!this.isNormalMode()) {
         RenderBuffers.preRender();
         if (!(Boolean)this.walls.getValue()) {
            RenderSystem.enableDepthTest();
         }

         for(class_1297 entity : mc.field_1687.method_18112()) {
            double x = Math.abs(mc.field_1773.method_19418().method_19326().field_1352 - entity.method_23317());
            double z = Math.abs(mc.field_1773.method_19418().method_19326().field_1350 - entity.method_23321());
            double d = (double)(((Integer)mc.field_1690.method_42503().method_41753() + 1) * 16);
            class_243 start = mc.field_1724.method_19538();
            if (!(start.method_1025(entity.method_19538()) > (double)(((Number)this.range.getValue()).floatValue() * ((Number)this.range.getValue()).floatValue())) && !(x > d) && !(z > d) && RenderUtil.isFrustumVisible(entity.method_5829())) {
               label49: {
                  if (entity instanceof class_1309) {
                     class_1309 livingEntity = (class_1309)entity;
                     if (this.checkChams(livingEntity)) {
                        break label49;
                     }
                  }

                  if (!(entity instanceof class_1511) || !(Boolean)this.crystals.getValue()) {
                     continue;
                  }
               }

               this.renderEntityChams(event.getMatrices(), entity, event.getTickDelta());
            }
         }

         RenderBuffers.postRender();
         if (!(Boolean)this.walls.getValue()) {
            RenderSystem.enableDepthTest();
         }

      }
   }

   @SubscribeEvent
   public void onRenderHand(RenderHandEvent event) {
      if (!this.isNormalMode()) {
         if ((Boolean)this.hands.getValue()) {
            RenderBuffers.preRender();
            int op = ((Number)this.opacity.getValue()).intValue();
            int color1 = ColorUtil.newAlpha(((PagonColor)this.fill.getValue()).getColor().getRGB(), op);
            int lineColor1 = ColorUtil.newAlpha(color1, 145);
            ChamsModelRenderer.renderHand(event.getMatrices(), event.getTickDelta(), lineColor1, color1, ((Number)this.width.getValue()).floatValue(), this.isWireMode(), this.isFillMode(), (Boolean)this.shine.getValue());
            RenderBuffers.postRender();
         }

      }
   }

   @SubscribeEvent
   public void onRenderCrystal(RenderCrystalEvent event) {
      if (!this.isNormalMode()) {
         if ((Boolean)this.crystals.getValue() && mc.field_1724.method_5858(event.endCrystalEntity) <= (double)(((Number)this.range.getValue()).floatValue() * ((Number)this.range.getValue()).floatValue())) {
            event.setCancelled(true);
         }

      }
   }

   @SubscribeEvent
   public void onRenderEntity(RenderEntityEvent event) {
      if (!this.isNormalMode()) {
         if (!(Boolean)this.texture.getValue() && this.checkChams(event.entity) && !(mc.field_1724.method_5858(event.entity) > (double)(((Number)this.range.getValue()).floatValue() * ((Number)this.range.getValue()).floatValue()))) {
            event.setCancelled(true);
            event.matrixStack.method_22903();
            event.model.field_3447 = event.entity.method_6055(event.g);
            event.model.field_3449 = event.entity.method_5765();
            event.model.field_3448 = event.entity.method_6109();
            float h = class_3532.method_17821(event.g, event.entity.field_6220, event.entity.field_6283);
            float j = class_3532.method_17821(event.g, event.entity.field_6259, event.entity.field_6241);
            if (event.entity == class_310.method_1551().field_1724 && !RotationManager.INSTANCE.FROM_INV) {
               ILivingEntity accessor = (ILivingEntity)event.entity;
               j = class_3532.method_17821(event.g, accessor.pagon_getPrevHeadYaw(), accessor.pagon_getHeadYaw());
            }

            float k = j - h;
            if (event.entity.method_5765()) {
               class_1297 var8 = event.entity.method_5854();
               if (var8 instanceof class_1309) {
                  class_1309 livingEntity2 = (class_1309)var8;
                  h = class_3532.method_17821(event.g, livingEntity2.field_6220, livingEntity2.field_6283);
                  k = j - h;
                  float l = class_3532.method_15393(k);
                  if (l < -85.0F) {
                     l = -85.0F;
                  }

                  if (l >= 85.0F) {
                     l = 85.0F;
                  }

                  h = j - l;
                  if (l * l > 2500.0F) {
                     h += l * 0.2F;
                  }

                  k = j - h;
               }
            }

            float m = class_3532.method_16439(event.g, event.entity.field_6004, event.entity.method_36455());
            if (event.entity == class_310.method_1551().field_1724 && !RotationManager.INSTANCE.FROM_INV) {
               ILivingEntity accessor = (ILivingEntity)event.entity;
               m = class_3532.method_17821(event.g, accessor.pagon_getPrevHeadPitch(), accessor.pagon_getHeadPitch());
            }

            if (class_922.method_38563(event.entity)) {
               m *= -1.0F;
               k *= -1.0F;
            }

            class_2350 direction;
            if (event.entity.method_41328(class_4050.field_18078) && (direction = event.entity.method_18401()) != null) {
               float n = event.entity.method_18381(class_4050.field_18076) - 0.1F;
               event.matrixStack.method_46416((float)(-direction.method_10148()) * n, 0.0F, (float)(-direction.method_10165()) * n);
            }

            float l = this.getAnimationProgress(event.entity, event.g);
            if (event.entity instanceof class_1657) {
               ChamsModelRenderer.setupPlayerTransforms((class_742)event.entity, event.matrixStack, l, h, event.g);
            } else {
               ChamsModelRenderer.setupTransforms(event.entity, event.matrixStack, l, h, event.g);
            }

            event.matrixStack.method_22905(-1.0F, -1.0F, 1.0F);
            event.matrixStack.method_22905(0.9375F, 0.9375F, 0.9375F);
            event.matrixStack.method_46416(0.0F, -1.501F, 0.0F);
            float n = 0.0F;
            float o = 0.0F;
            if (!event.entity.method_5765() && event.entity.method_5805()) {
               n = event.entity.field_42108.method_48570(event.g);
               o = event.entity.field_42108.method_48572(event.g);
               if (event.entity.method_6109()) {
                  o *= 3.0F;
               }

               if (n > 1.0F) {
                  n = 1.0F;
               }
            }

            event.model.method_2816(event.entity, o, n, event.g);
            event.model.method_2819(event.entity, o, n, l, k, m);
            if (!event.entity.method_7325()) {
               for(Object featureRenderer : event.features) {
                  ((class_3887)featureRenderer).method_4199(event.matrixStack, event.vertexConsumerProvider, event.i, event.entity, o, n, event.g, l, k, m);
               }
            }

            event.matrixStack.method_22909();
         }
      }
   }

   public void renderEntityChams(class_4587 matrixStack, class_1297 entity, float tickDelta) {
      int op = ((Number)this.opacity.getValue()).intValue();
      int color1 = ColorUtil.newAlpha(((PagonColor)this.fill.getValue()).getColor().getRGB(), op);
      int lineColor1 = ColorUtil.newAlpha(color1, 145);
      ChamsModelRenderer.render(matrixStack, entity, tickDelta, color1, lineColor1, ((Number)this.width.getValue()).floatValue(), this.isWireMode(), this.isFillMode(), (Boolean)this.shine.getValue());
   }

   private float getAnimationProgress(class_1309 entity, float f) {
      if (entity instanceof class_1477) {
         return class_3532.method_16439(f, ((class_1477)entity).field_6900, ((class_1477)entity).field_6904);
      } else {
         float var10000;
         if (entity instanceof class_1493) {
            class_1493 wolf = (class_1493)entity;
            var10000 = wolf.method_6714();
         } else {
            var10000 = (float)entity.field_6012 + f;
         }

         return var10000;
      }
   }

   public boolean checkChams(class_1309 entity) {
      if (entity instanceof class_1657) {
         if (entity != mc.field_1724) {
            return (Boolean)this.players.getValue();
         } else {
            return (Boolean)this.self.getValue() && (!mc.field_1690.method_31044().method_31034() || Freecam.INSTANCE.isEnabled());
         }
      } else {
         return EntityUtils.isMonster(entity) && (Boolean)this.monsters.getValue() || (EntityUtils.isNeutral(entity) || EntityUtils.isPassive(entity)) && (Boolean)this.animals.getValue();
      }
   }

   public String getDescription() {
      return "Renders entities through walls";
   }
}
