package org.itsmahir.pagon.api.utils.render.font;

public record Glyph(int textureWidth, int textureHeight, int width, int height, char value, GlyphCache owner) {
}
