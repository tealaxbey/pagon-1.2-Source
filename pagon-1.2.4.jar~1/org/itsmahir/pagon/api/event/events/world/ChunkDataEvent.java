package org.itsmahir.pagon.api.event.events.world;

import net.minecraft.class_2818;
import org.itsmahir.pagon.api.event.Event;

public class ChunkDataEvent extends Event {
   private class_2818 chunk;
   boolean seenChunk;

   public ChunkDataEvent(class_2818 chunk, boolean seenChunk) {
      this.chunk = chunk;
      this.seenChunk = seenChunk;
   }

   public class_2818 getChunk() {
      return this.chunk;
   }

   public boolean isSeenChunk() {
      return this.seenChunk;
   }

   public void setChunk(class_2818 chunk) {
      this.chunk = chunk;
   }

   public void setSeenChunk(boolean seenChunk) {
      this.seenChunk = seenChunk;
   }
}
