package org.itsmahir.pagon.api.management.shaders;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_276;

public class ShaderFramebuffer extends class_276 {
   public ShaderFramebuffer(int width, int height) {
      super(false);
      RenderSystem.assertOnRenderThreadOrInit();
      this.method_1234(width, height, true);
      this.method_1236(0.0F, 0.0F, 0.0F, 0.0F);
   }
}
