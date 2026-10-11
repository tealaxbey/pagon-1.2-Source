package org.itsmahir.pagon.api.utils.render.world;

public enum RenderType {
   FILL,
   LINES,
   BOTH;

   public boolean lines() {
      return this == LINES || this == BOTH;
   }

   public boolean sides() {
      return this == FILL || this == BOTH;
   }

   // $FF: synthetic method
   private static RenderType[] $values() {
      return new RenderType[]{FILL, LINES, BOTH};
   }
}
