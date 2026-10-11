package org.itsmahir.pagon.impl.features.modules.render;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_2338;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_5602;
import net.minecraft.class_5617;
import net.minecraft.class_591;
import net.minecraft.class_742;
import net.minecraft.class_7833;
import net.minecraft.class_8685;
import net.minecraft.class_8685.class_7920;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.player.PopEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.math.MathUtil;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.WireframeEntityRenderer;
import org.itsmahir.pagon.api.utils.render.world.RenderType;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.mixin.accessor.ILimbAnimator;
import org.joml.Vector3f;

public class PopChams extends Module {
   public static PopChams INSTANCE;
   Value<Number> fadeStart = (new ValueBuilder<Number>()).withDescriptor("Fade Start").withValue(100).withRange(0, 4000).register(this);
   Value<Number> fadeTime = (new ValueBuilder<Number>()).withDescriptor("Fade Time").withValue(500).withRange(0, 2000).register(this);
   Value<Boolean> self = (new ValueBuilder<Boolean>()).withDescriptor("Self").withValue(false).register(this);
   Value<PagonColor> fill = (new ValueBuilder<PagonColor>()).withDescriptor("Fill Color").withValue(new PagonColor(0, 255, 218, 100)).register(this);
   Value<PagonColor> lineColor = (new ValueBuilder<PagonColor>()).withDescriptor("Line Color").withValue(new PagonColor(255, 255, 255, 255)).register(this);
   private final CopyOnWriteArrayList<PopCham> popList = new CopyOnWriteArrayList();

   public PopChams() {
      super("PopChams", Feature.Category.Render);
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldEvent event) {
      if (!NullUtils.nullCheck()) {
         this.popList.forEach((cham) -> this.renderEntity(event.getMatrices(), cham.player, cham.modelPlayer, cham.getTexture(), cham.startTime, cham));
      }
   }

   private void renderEntity(class_4587 matrices, class_1309 entity, class_591<class_1657> modelBase, class_2960 texture, long startTime, PopCham cham) {
      modelBase.field_3482.field_3665 = false;
      modelBase.field_3479.field_3665 = false;
      modelBase.field_3484.field_3665 = false;
      modelBase.field_3486.field_3665 = false;
      modelBase.field_3483.field_3665 = false;
      modelBase.field_3394.field_3665 = false;
      double x = entity.method_23317();
      double y = entity.method_23318();
      double z = entity.method_23321();
      matrices = RenderUtil.matrixFrom(x, y, z);
      matrices.method_22903();
      matrices.method_22907(class_7833.field_40716.rotation(MathUtil.rad(180.0F - entity.field_6283)));
      prepareScale(matrices);
      modelBase.method_17086((class_1657)entity, entity.field_42108.method_48569(), entity.field_42108.method_48566(), mc.method_60646().method_60637(false));
      float limbSpeed = Math.min(entity.field_42108.method_48566(), 1.0F);
      modelBase.method_17087((class_1657)entity, entity.field_42108.method_48569(), limbSpeed, (float)entity.field_6012, entity.field_6241 - entity.field_6283, entity.method_36455());
      int lineA = ((PagonColor)this.lineColor.getValue()).getAlpha();
      int fillA = ((PagonColor)this.fill.getValue()).getAlpha();
      if (System.currentTimeMillis() - startTime > ((Number)this.fadeStart.getValue()).longValue()) {
         long time = System.currentTimeMillis() - startTime - ((Number)this.fadeStart.getValue()).longValue();
         double normal = MathUtil.normalize((double)time, (double)0.0F, ((Number)this.fadeTime.getValue()).doubleValue());
         normal = class_3532.method_15350(normal, (double)0.0F, (double)1.0F);
         normal = -normal + (double)1.0F;
         lineA = (int)(normal * (double)lineA);
         fillA = (int)(normal * (double)fillA);
      }

      WireframeEntityRenderer.renderModel(matrices, modelBase, RenderType.BOTH, ColorUtil.newAlpha(((PagonColor)this.fill.getValue()).getColor(), fillA), ColorUtil.newAlpha(((PagonColor)this.lineColor.getValue()).getColor(), lineA));
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      matrices.method_22909();
      if (lineA == 0 && fillA == 0) {
         this.popList.remove(cham);
      }

   }

   private static void prepareScale(class_4587 matrixStack) {
      matrixStack.method_22905(-1.0F, -1.0F, 1.0F);
      matrixStack.method_22905(1.6F, 1.8F, 1.6F);
      matrixStack.method_46416(0.0F, -1.501F, 0.0F);
   }

   @SubscribeEvent
   public void onTotemPop(PopEvent.TotemPopEvent event) {
      if (!NullUtils.nullCheck()) {
         if ((Boolean)this.self.getValue() || !event.getEntity().equals(mc.field_1724)) {
            if (event.getEntity() instanceof class_1657) {
               class_1657 eventEntity = (class_1657)event.getEntity();
               class_1657 entity = new class_1657(mc.field_1687, class_2338.field_10980, eventEntity.method_43078(), new GameProfile(eventEntity.method_5667(), eventEntity.method_5477().getString())) {
                  public boolean method_7325() {
                     return false;
                  }

                  public boolean method_7337() {
                     return false;
                  }
               };
               entity.method_5719(eventEntity);
               entity.field_6283 = eventEntity.method_43078();
               entity.field_6241 = eventEntity.method_5791();
               entity.field_6251 = eventEntity.field_6251;
               entity.field_6279 = eventEntity.field_6279;
               entity.method_5660(eventEntity.method_5715());
               entity.field_42108.method_48567(eventEntity.field_42108.method_48566());
               ((ILimbAnimator)entity.field_42108).setLimbPos(eventEntity.field_42108.method_48569());
               this.popList.add(new PopCham(entity, ((class_742)eventEntity).method_52814()));
            }
         }
      }
   }

   public String getDescription() {
      return "PopChams: Shows ghosts when someone pops";
   }

   private class PopCham {
      private final class_1657 player;
      private final class_591<class_1657> modelPlayer;
      private class_2960 texture;
      long startTime;

      public PopCham(class_1657 player, class_8685 texture) {
         this.player = player;
         this.modelPlayer = new class_591((new class_5617.class_5618(IMinecraft.mc.method_1561(), IMinecraft.mc.method_1480(), IMinecraft.mc.method_1541(), IMinecraft.mc.method_1561().method_43336(), IMinecraft.mc.method_1478(), IMinecraft.mc.method_31974(), IMinecraft.mc.field_1772)).method_32167(class_5602.field_27577), texture.comp_1629().equals(class_7920.field_41122));
         this.modelPlayer.method_2838().method_41924(new Vector3f(-0.3F, -0.3F, -0.3F));
         this.texture = texture.comp_1626();
         this.startTime = System.currentTimeMillis();
      }

      public class_2960 getTexture() {
         return this.texture;
      }
   }
}
