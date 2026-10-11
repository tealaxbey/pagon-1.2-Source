package org.itsmahir.pagon.api.command;

import org.itsmahir.pagon.api.wrapper.IMinecraft;
import org.itsmahir.pagon.impl.PagonMod;

public abstract class Command implements IMinecraft {
   String name;
   String desc;
   String[] alias;

   public Command(String name, String desc, String[] alias) {
      this.name = name;
      this.desc = desc;
      this.alias = alias;
      PagonMod.EVENT_BUS.register(this);
   }

   public abstract void run(String[] var1);

   public String getName() {
      return this.name;
   }

   public String getDesc() {
      return this.desc;
   }

   public String[] getAlias() {
      return this.alias;
   }

   public String[] getFill(String[] args) {
      return new String[0];
   }
}
