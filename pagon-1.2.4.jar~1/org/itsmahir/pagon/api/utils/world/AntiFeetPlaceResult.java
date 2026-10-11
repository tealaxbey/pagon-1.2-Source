package org.itsmahir.pagon.api.utils.world;

public class AntiFeetPlaceResult {
   boolean placeAvailable;
   boolean isAntiFeetPlace;

   public boolean isPlaceAvailable() {
      return this.placeAvailable;
   }

   public boolean isAntiFeetPlace() {
      return this.isAntiFeetPlace;
   }

   public void setPlaceAvailable(boolean placeAvailable) {
      this.placeAvailable = placeAvailable;
   }

   public void setAntiFeetPlace(boolean isAntiFeetPlace) {
      this.isAntiFeetPlace = isAntiFeetPlace;
   }

   public AntiFeetPlaceResult(boolean placeAvailable, boolean isAntiFeetPlace) {
      this.placeAvailable = placeAvailable;
      this.isAntiFeetPlace = isAntiFeetPlace;
   }
}
