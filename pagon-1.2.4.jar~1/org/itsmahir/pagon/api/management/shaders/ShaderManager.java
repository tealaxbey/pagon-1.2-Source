package org.itsmahir.pagon.api.management.shaders;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import net.minecraft.class_1921;
import net.minecraft.class_276;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4618;
import net.minecraft.class_4668;
import net.minecraft.class_9799;
import net.minecraft.class_1921.class_4688;
import net.minecraft.class_1921.class_4750;
import net.minecraft.class_293.class_5596;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.mixin.accessor.IRenderLayerMultiPhase;
import org.itsmahir.pagon.mixin.accessor.IRenderLayerMultiPhaseParameters;
import org.itsmahir.pagon.mixin.accessor.IRenderPhaseTextureBase;
import org.ladysnake.satin.api.managed.ManagedShaderEffect;
import org.ladysnake.satin.api.managed.ShaderEffectManager;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL31;

public class ShaderManager implements IMinecraft {
   public static ShaderManager INSTANCE = new ShaderManager();
   private final class_4618 vertexConsumerProvider = new class_4618(class_4597.method_22991(new class_9799(256)));
   private final Function<class_4668.class_5939, class_1921> layerCreator = this.memoizeTexture((texture) -> class_1921.method_24048("snow_overlay", class_290.field_1575, class_5596.field_27382, 1536, class_4688.method_23598().method_34578(class_4668.field_29418).method_23603(class_4668.field_21345).method_34577(texture).method_23604(class_4668.field_21346).method_23610(this.target).method_24297(class_4750.field_21854)));
   private final class_4668.class_4678 target = new class_4668.class_4678("shader_target", () -> {
   }, () -> {
   });
   private ShaderFramebuffer framebuffer;
   private ShaderProgram glowProgram;
   private ShaderProgram solidProgram;
   private ManagedShaderEffect glowShaderEffect;
   private ManagedShaderEffect solidShaderEffect;
   private ManagedShaderEffect rainbowShaderEffect;
   private ManagedShaderEffect blurShaderEffect;

   public void prepare() {
      if (mc.method_1522() != null) {
         if (this.framebuffer == null || this.framebuffer.field_1482 != mc.method_1522().field_1482 || this.framebuffer.field_1481 != mc.method_1522().field_1481) {
            this.framebuffer = new ShaderFramebuffer(mc.method_1522().field_1482, mc.method_1522().field_1481);
         }

      }
   }

   public void reloadShaders() {
      if (this.glowShaderEffect == null) {
         this.glowShaderEffect = ShaderEffectManager.getInstance().manage(class_2960.method_60655("pagon", "shaders/post/glow.json"));
      }

      if (this.solidShaderEffect == null) {
         this.solidShaderEffect = ShaderEffectManager.getInstance().manage(class_2960.method_60655("pagon", "shaders/post/solid.json"));
      }

      if (this.rainbowShaderEffect == null) {
         this.rainbowShaderEffect = ShaderEffectManager.getInstance().manage(class_2960.method_60655("pagon", "shaders/post/solid.json"));
      }

      if (this.blurShaderEffect == null) {
         this.blurShaderEffect = ShaderEffectManager.getInstance().manage(class_2960.method_60655("pagon", "shaders/post/blur.json"));
      }

   }

   public void loadShaders() {
      try {
         String vertexSrc = this.readShader("assets/minecraft/shaders/program/blit.vsh");
         String glowFragSrc = this.readShader("assets/pagon/shaders/program/glow.fsh");
         String solidFragSrc = this.readShader("assets/pagon/shaders/program/solid.fsh");
         this.glowProgram = new ShaderProgram(vertexSrc, glowFragSrc, "GlowConfig", 32);
         this.solidProgram = new ShaderProgram(vertexSrc, solidFragSrc, "SolidConfig", 16);
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   private String readShader(String path) throws IOException {
      InputStream in = this.getClass().getClassLoader().getResourceAsStream(path);
      if (in == null) {
         throw new IOException("Shader not found: " + path);
      } else {
         BufferedReader reader = new BufferedReader(new InputStreamReader(in));
         StringBuilder sb = new StringBuilder();

         String line;
         while((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
         }

         reader.close();
         return sb.toString();
      }
   }

   public class_4597 createVertexConsumers(class_4597 parent, Color color) {
      return (layer) -> {
         class_4588 parentBuffer = parent.getBuffer(layer);
         if (layer instanceof class_1921.class_4687 && ((IRenderLayerMultiPhaseParameters)((IRenderLayerMultiPhase)layer).invokeGetPhases()).getOutlineMode() != class_4750.field_21853) {
            this.vertexConsumerProvider.method_23286(color.getRed(), color.getGreen(), color.getBlue(), 255);
            class_4588 outlineBuffer = this.vertexConsumerProvider.getBuffer((class_1921)this.layerCreator.apply(((IRenderLayerMultiPhaseParameters)((IRenderLayerMultiPhase)layer).invokeGetPhases()).getTexture()));
            return outlineBuffer != null ? outlineBuffer : parentBuffer;
         } else {
            return parentBuffer;
         }
      };
   }

   public class_4597 createVertexConsumers(class_4597 parent, Color color, int alpha) {
      return (layer) -> {
         class_4588 parentBuffer = parent.getBuffer(layer);
         if (layer instanceof class_1921.class_4687 && ((IRenderLayerMultiPhaseParameters)((IRenderLayerMultiPhase)layer).invokeGetPhases()).getOutlineMode() != class_4750.field_21853) {
            this.vertexConsumerProvider.method_23286(color.getRed(), color.getGreen(), color.getBlue(), alpha);
            class_4588 outlineBuffer = this.vertexConsumerProvider.getBuffer((class_1921)this.layerCreator.apply(((IRenderLayerMultiPhaseParameters)((IRenderLayerMultiPhase)layer).invokeGetPhases()).getTexture()));
            return outlineBuffer != null ? outlineBuffer : parentBuffer;
         } else {
            return parentBuffer;
         }
      };
   }

   public void render(String mode, int size, Consumer<ByteBuffer> configurer) {
      ShaderProgram program = mode.equalsIgnoreCase("Glow") ? this.glowProgram : this.solidProgram;
      if (program != null) {
         class_276 mcFramebuffer = mc.method_1522();
         GlStateManager._glBindFramebuffer(36009, this.framebuffer.field_1476);
         this.framebuffer.method_1235(false);
         this.framebuffer.method_1230(false);
         RenderSystem.enableBlend();
         RenderSystem.blendFuncSeparate(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA, class_4535.ZERO, class_4534.ONE);
         ByteBuffer buffer = ByteBuffer.allocateDirect(size).order(ByteOrder.nativeOrder());
         configurer.accept(buffer);
         buffer.rewind();
         program.uploadUBO(buffer);
         program.render(this.framebuffer.field_1482, this.framebuffer.field_1481, this.framebuffer);
         this.framebuffer.method_1240();
         GlStateManager._glBindFramebuffer(36009, mcFramebuffer.field_1476);
         mcFramebuffer.method_1235(false);
         RenderSystem.enableBlend();
         RenderSystem.blendFuncSeparate(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA, class_4535.ZERO, class_4534.ONE);
         RenderSystem.backupProjectionMatrix();
         this.framebuffer.method_22594(this.framebuffer.field_1482, this.framebuffer.field_1481, false);
         RenderSystem.restoreProjectionMatrix();
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableBlend();
      }
   }

   private Function<class_4668.class_5939, class_1921> memoizeTexture(final Function<class_4668.class_5939, class_1921> function) {
      return new Function<class_4668.class_5939, class_1921>() {
         private final Map<class_2960, class_1921> cache = new HashMap();

         public class_1921 apply(class_4668.class_5939 texture) {
            return (class_1921)this.cache.computeIfAbsent((class_2960)((IRenderPhaseTextureBase)texture).invokeGetId().get(), (id) -> (class_1921)function.apply(texture));
         }
      };
   }

   public ManagedShaderEffect getGlowShaderEffect() {
      if (this.glowShaderEffect == null) {
         this.reloadShaders();
      }

      return this.glowShaderEffect;
   }

   public ManagedShaderEffect getSolidShaderEffect() {
      if (this.solidShaderEffect == null) {
         this.reloadShaders();
      }

      return this.solidShaderEffect;
   }

   public ManagedShaderEffect getRainbowShaderEffect() {
      if (this.rainbowShaderEffect == null) {
         this.reloadShaders();
      }

      return this.rainbowShaderEffect;
   }

   public ManagedShaderEffect getBlurShaderEffect() {
      if (this.blurShaderEffect == null) {
         this.reloadShaders();
      }

      return this.blurShaderEffect;
   }

   public void applyShader(ManagedShaderEffect shaderEffect, Runnable setup, Runnable render) {
      this.prepare();
      mc.method_1522().method_1235(false);
      render.run();
      mc.method_1522().method_1240();
      shaderEffect.setUniformValue("RenderMode", 2);
      setup.run();
   }

   public class_4618 getVertexConsumerProvider() {
      return this.vertexConsumerProvider;
   }

   public Function<class_4668.class_5939, class_1921> getLayerCreator() {
      return this.layerCreator;
   }

   public class_4668.class_4678 getTarget() {
      return this.target;
   }

   public ShaderFramebuffer getFramebuffer() {
      return this.framebuffer;
   }

   public ShaderProgram getGlowProgram() {
      return this.glowProgram;
   }

   public ShaderProgram getSolidProgram() {
      return this.solidProgram;
   }

   public static class ShaderProgram {
      private final int programId;
      private final int uboBindingIndex;
      private int uboId;

      public ShaderProgram(String vertexSrc, String fragmentSrc, String uboName, int uboSize) {
         int vertId = this.compileShader(35633, vertexSrc);
         int fragId = this.compileShader(35632, fragmentSrc);
         this.programId = GL20.glCreateProgram();
         GL20.glAttachShader(this.programId, vertId);
         GL20.glAttachShader(this.programId, fragId);
         GL20.glLinkProgram(this.programId);
         GL20.glDeleteShader(vertId);
         GL20.glDeleteShader(fragId);
         if (GL20.glGetProgrami(this.programId, 35714) == 0) {
            throw new RuntimeException("Shader link error: " + GL20.glGetProgramInfoLog(this.programId));
         } else {
            int blockIndex = GL31.glGetUniformBlockIndex(this.programId, uboName);
            if (blockIndex == -1) {
               throw new RuntimeException("Uniform block '" + uboName + "' not found in shader");
            } else {
               this.uboBindingIndex = 0;
               GL31.glUniformBlockBinding(this.programId, blockIndex, this.uboBindingIndex);
               this.uboId = GL30.glGenBuffers();
               GL30.glBindBuffer(35345, this.uboId);
               GL30.glBufferData(35345, (long)uboSize, 35048);
               GL30.glBindBuffer(35345, 0);
            }
         }
      }

      private int compileShader(int type, String source) {
         int shader = GL20.glCreateShader(type);
         GL20.glShaderSource(shader, source);
         GL20.glCompileShader(shader);
         if (GL20.glGetShaderi(shader, 35713) == 0) {
            String log = GL20.glGetShaderInfoLog(shader);
            GL20.glDeleteShader(shader);
            throw new RuntimeException("Shader compile error: " + log);
         } else {
            return shader;
         }
      }

      public void uploadUBO(ByteBuffer data) {
         GL30.glBindBuffer(35345, this.uboId);
         GL30.glBufferSubData(35345, 0L, data);
         GL30.glBindBuffer(35345, 0);
      }

      public void render(int width, int height, class_276 framebuffer) {
         GL20.glUseProgram(this.programId);
         GL30.glBindBufferBase(35345, this.uboBindingIndex, this.uboId);
         int projMatLoc = GL20.glGetUniformLocation(this.programId, "ProjMat");
         if (projMatLoc >= 0) {
            FloatBuffer projMat = ByteBuffer.allocateDirect(64).order(ByteOrder.nativeOrder()).asFloatBuffer();
            projMat.put(new float[]{2.0F / (float)width, 0.0F, 0.0F, 0.0F, 0.0F, -2.0F / (float)height, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F});
            projMat.rewind();
            GL20.glUniformMatrix4fv(projMatLoc, false, projMat);
         }

         int inSizeLoc = GL20.glGetUniformLocation(this.programId, "InSize");
         if (inSizeLoc >= 0) {
            GL20.glUniform2f(inSizeLoc, (float)width, (float)height);
         }

         GlStateManager._activeTexture(33984);
         GlStateManager._bindTexture(framebuffer.method_30277());
         int samplerLoc = GL20.glGetUniformLocation(this.programId, "InSampler");
         if (samplerLoc >= 0) {
            GL20.glUniform1i(samplerLoc, 0);
         }

         int vertArrayId = GL30.glGenVertexArrays();
         GL30.glBindVertexArray(vertArrayId);
         float[] vertices = new float[]{-1.0F, -1.0F, 1.0F, -1.0F, 1.0F, 1.0F, -1.0F, 1.0F};
         int vbo = GL30.glGenBuffers();
         GL30.glBindBuffer(34962, vbo);
         FloatBuffer vertBuf = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder()).asFloatBuffer();
         vertBuf.put(vertices);
         vertBuf.rewind();
         GL30.glBufferData(34962, vertBuf, 35044);
         int posLoc = GL20.glGetAttribLocation(this.programId, "Position");
         if (posLoc >= 0) {
            GL20.glEnableVertexAttribArray(posLoc);
            GL20.glVertexAttribPointer(posLoc, 2, 5126, false, 0, 0L);
         }

         GL20.glDrawArrays(6, 0, 4);
         GL20.glDisableVertexAttribArray(posLoc);
         GL30.glBindVertexArray(0);
         GL30.glDeleteBuffers(vbo);
         GL30.glDeleteVertexArrays(vertArrayId);
         GL20.glUseProgram(0);
      }

      public void delete() {
         GL20.glDeleteProgram(this.programId);
         GL30.glDeleteBuffers(this.uboId);
      }
   }
}
