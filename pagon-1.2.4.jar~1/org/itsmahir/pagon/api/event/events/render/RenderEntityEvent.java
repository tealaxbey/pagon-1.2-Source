package org.itsmahir.pagon.api.event.events.render;

import java.util.List;
import net.minecraft.class_1309;
import net.minecraft.class_1921;
import net.minecraft.class_3887;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_583;
import org.itsmahir.pagon.api.event.Event;

public class RenderEntityEvent<T extends class_1309> extends Event {
   public final class_1309 entity;
   public final float f;
   public final float g;
   public final class_4587 matrixStack;
   public final class_4597 vertexConsumerProvider;
   public final int i;
   public final class_1921 layer;
   public final class_583 model;
   public final List<class_3887<T, class_583<T>>> features;

   public RenderEntityEvent(class_1309 entity, float f, float g, class_4587 matrixStack, class_4597 vertexConsumerProvider, int i, class_583 model, class_1921 layer, List<class_3887<T, class_583<T>>> features) {
      this.entity = entity;
      this.f = f;
      this.g = g;
      this.matrixStack = matrixStack;
      this.vertexConsumerProvider = vertexConsumerProvider;
      this.i = i;
      this.model = model;
      this.layer = layer;
      this.features = features;
   }

   public class_1309 getEntity() {
      return this.entity;
   }

   public float getF() {
      return this.f;
   }

   public float getG() {
      return this.g;
   }

   public class_4587 getMatrixStack() {
      return this.matrixStack;
   }

   public class_4597 getVertexConsumerProvider() {
      return this.vertexConsumerProvider;
   }

   public int getI() {
      return this.i;
   }

   public class_1921 getLayer() {
      return this.layer;
   }

   public class_583 getModel() {
      return this.model;
   }

   public List<class_3887<T, class_583<T>>> getFeatures() {
      return this.features;
   }
}
