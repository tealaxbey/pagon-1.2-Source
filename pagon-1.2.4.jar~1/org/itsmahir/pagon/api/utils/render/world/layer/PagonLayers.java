package org.itsmahir.pagon.api.utils.render.world.layer;

import net.minecraft.class_1921;
import net.minecraft.class_290;
import net.minecraft.class_4668;
import net.minecraft.class_918;
import net.minecraft.class_1921.class_4688;
import net.minecraft.class_293.class_5596;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.lwjgl.opengl.GL11;

public class PagonLayers implements IMinecraft {
   public static final class_1921 ENCHANT;

   static {
      ENCHANT = class_1921.method_24048("glint", class_290.field_1585, class_5596.field_27382, 256, class_4688.method_23598().method_34578(class_4668.field_29422).method_34577(new class_4668.class_4683(class_918.field_43087, true, false)).method_23616(class_4668.field_21350).method_23603(class_4668.field_21344).method_23604(new DepthTest()).method_23615(class_4668.field_21368).method_23614(class_4668.field_21381).method_23617(false));
   }

   protected static class DepthTest extends class_4668.class_4672 {
      public DepthTest() {
         super("depth_test", 519);
      }

      public void method_23516() {
         GL11.glEnable(2929);
         GL11.glDepthFunc(514);
      }

      public void method_23518() {
         GL11.glDisable(2929);
         GL11.glDepthFunc(515);
         GL11.glDepthFunc(519);
      }
   }
}
