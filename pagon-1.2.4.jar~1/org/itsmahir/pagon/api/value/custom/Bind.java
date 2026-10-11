package org.itsmahir.pagon.api.value.custom;

public class Bind {
   int key = -1;
   boolean isMouse = false;

   public int getKey() {
      return this.key;
   }

   public void setKey(int key) {
      this.key = key;
   }

   public void setIsMouse(boolean mouse) {
      this.isMouse = mouse;
   }

   public boolean getIsMouse() {
      return this.isMouse;
   }
}
