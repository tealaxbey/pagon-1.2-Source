package org.itsmahir.pagon.api.utils.render.font;

import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.chars.Char2ObjectArrayMap;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1011;
import net.minecraft.class_1043;
import net.minecraft.class_2960;
import net.minecraft.class_1011.class_1012;
import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.mixin.accessor.INativeImage;
import org.lwjgl.system.MemoryUtil;

public class GlyphCache implements IMinecraft {
   private final char start;
   private final char end;
   private final Font font;
   private final class_2960 id;
   private final int padding;
   private int width;
   private int height;
   private final Char2ObjectArrayMap<Glyph> glyphs = new Char2ObjectArrayMap();
   private boolean generated;
   private final boolean antiAlias;
   private final boolean fractionalMetrics;

   public GlyphCache(char from, char to, Font font, class_2960 id, int padding, boolean antiAlias, boolean fractionalMetrics) {
      this.start = from;
      this.end = to;
      this.font = font;
      this.id = id;
      this.padding = padding;
      this.antiAlias = antiAlias;
      this.fractionalMetrics = fractionalMetrics;
   }

   public Glyph getGlyph(char c) {
      if (!this.generated) {
         this.createBitmap();
      }

      return (Glyph)this.glyphs.get(c);
   }

   public void clear() {
      mc.method_1531().method_4615(this.id);
      this.glyphs.clear();
      this.generated = false;
   }

   public boolean contains(char c) {
      return c >= this.start && c < this.end;
   }

   public void createBitmap() {
      if (!this.generated) {
         List<Glyph> glyphs1 = new ArrayList();
         int range = this.end - this.start - 1;
         int ceiling = (int)(Math.ceil(Math.sqrt((double)range)) * (double)1.5F);
         int cached = 0;
         int charX = 0;
         int maxX = 0;
         int maxY = 0;
         int currX = 0;
         int currY = 0;
         int currentRowMaxY = 0;

         for(FontRenderContext frc = new FontRenderContext(new AffineTransform(), this.antiAlias, this.fractionalMetrics); cached <= range; ++charX) {
            char currentChar = (char)(this.start + cached);
            Rectangle2D stringBounds = this.font.getStringBounds(String.valueOf(currentChar), frc);
            int width = (int)Math.ceil(stringBounds.getWidth());
            int height = (int)Math.ceil(stringBounds.getHeight());
            ++cached;
            maxX = Math.max(maxX, currX + width);
            maxY = Math.max(maxY, currY + height);
            if (charX >= ceiling) {
               currX = 0;
               currY += currentRowMaxY + this.padding;
               charX = 0;
               currentRowMaxY = 0;
            }

            currentRowMaxY = Math.max(currentRowMaxY, height);
            glyphs1.add(new Glyph(currX, currY, width, height, currentChar, this));
            currX += width + this.padding;
         }

         BufferedImage bufferedImage = new BufferedImage(Math.max(maxX + this.padding, 1), Math.max(maxY + this.padding, 1), 2);
         this.width = bufferedImage.getWidth();
         this.height = bufferedImage.getHeight();
         Graphics2D g2d = bufferedImage.createGraphics();
         g2d.setColor(new Color(255, 255, 255, 0));
         g2d.fillRect(0, 0, this.width, this.height);
         g2d.setColor(Color.WHITE);
         g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, this.fractionalMetrics ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, this.antiAlias ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
         g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, this.antiAlias ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

         for(Glyph glyph : glyphs1) {
            g2d.setFont(this.font);
            FontMetrics fontMetrics = g2d.getFontMetrics();
            g2d.drawString(String.valueOf(glyph.value()), glyph.textureWidth(), glyph.textureHeight() + fontMetrics.getAscent());
            this.glyphs.put(glyph.value(), glyph);
         }

         this.registerTexture(this.id, bufferedImage);
         this.generated = true;
      }
   }

   public class_2960 getId() {
      return this.id;
   }

   public int getWidth() {
      return this.width;
   }

   public int getHeight() {
      return this.height;
   }

   public void registerTexture(class_2960 identifier, BufferedImage bufferedImage) {
      try {
         int imageWidth = bufferedImage.getWidth();
         int imageHeight = bufferedImage.getHeight();
         class_1011 image = new class_1011(class_1012.field_4997, imageWidth, imageHeight, false);
         long ptr = ((INativeImage)image).getPointer();
         IntBuffer backingBuffer = MemoryUtil.memIntBuffer(ptr, image.method_4307() * image.method_4323());
         WritableRaster raster = bufferedImage.getRaster();
         ColorModel colorModel = bufferedImage.getColorModel();
         int bands = raster.getNumBands();
         int dataType = raster.getDataBuffer().getDataType();
         Object var10000;
         switch (dataType) {
            case 0:
               var10000 = new byte[bands];
               break;
            case 1:
               var10000 = new short[bands];
               break;
            case 2:
            default:
               throw new IllegalArgumentException("Unknown data buffer type: " + dataType);
            case 3:
               var10000 = new int[bands];
               break;
            case 4:
               var10000 = new float[bands];
               break;
            case 5:
               var10000 = new double[bands];
         }

         Object dataBuffer = var10000;

         for(int y = 0; y < imageHeight; ++y) {
            for(int x = 0; x < imageWidth; ++x) {
               raster.getDataElements(x, y, dataBuffer);
               int a = colorModel.getAlpha(dataBuffer);
               int r = colorModel.getRed(dataBuffer);
               int g = colorModel.getGreen(dataBuffer);
               int b = colorModel.getBlue(dataBuffer);
               int argb = a << 24 | b << 16 | g << 8 | r;
               backingBuffer.put(argb);
            }
         }

         class_1043 texture = new class_1043(image);
         texture.method_4524();
         texture.method_4527(true, true);
         if (RenderSystem.isOnRenderThread()) {
            mc.method_1531().method_4616(identifier, texture);
         } else {
            RenderSystem.recordRenderCall(() -> mc.method_1531().method_4616(identifier, texture));
         }
      } catch (Throwable e) {
         e.printStackTrace();
      }

   }
}
