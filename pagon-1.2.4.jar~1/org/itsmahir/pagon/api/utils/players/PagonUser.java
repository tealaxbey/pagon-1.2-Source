package org.itsmahir.pagon.api.utils.players;

public class PagonUser {
   private final String name;
   private final String uuid;
   private String selectedCape;

   public PagonUser(String name, String uuid, String selectedCape) {
      this.name = name;
      this.uuid = uuid;
      this.selectedCape = selectedCape;
   }

   public String getName() {
      return this.name;
   }

   public String getUUID() {
      return this.uuid;
   }

   public String getSelectedCape() {
      return this.selectedCape;
   }

   public void setSelectedCape(String selectedCape) {
      this.selectedCape = selectedCape;
   }
}
