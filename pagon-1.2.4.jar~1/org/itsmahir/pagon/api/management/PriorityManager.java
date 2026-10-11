package org.itsmahir.pagon.api.management;

public class PriorityManager {
   public static PriorityManager INSTANCE;
   public boolean usageLock = false;
   public String usageLockCause = "NONE";

   public void lockUsageLock(String cause) {
      this.usageLock = true;
      this.usageLockCause = cause;
   }

   public void unlockUsageLock() {
      this.usageLock = false;
      this.usageLockCause = "NONE";
   }

   public boolean isUsageLocked() {
      return this.usageLock;
   }
}
