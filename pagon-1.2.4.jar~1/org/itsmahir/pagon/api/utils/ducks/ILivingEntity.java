package org.itsmahir.pagon.api.utils.ducks;

import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_243;

public interface ILivingEntity {
   class_243 pagon_prevServerPos();

   boolean pagon_forceHasStatusEffect(class_1291 var1);

   class_1293 pagon_forceGetStatusEffect(class_1291 var1);

   float pagon_getHeadPitch();

   void pagon_setHeadPitch(float var1);

   float pagon_getPrevHeadPitch();

   void pagon_setPrevHeadPitch(float var1);

   float pagon_getHeadYaw();

   void pagon_setHeadYaw(float var1);

   float pagon_getPrevHeadYaw();

   void pagon_setPrevHeadYaw(float var1);

   boolean pagon_isInInventory();

   void pagon_setInInventory(boolean var1);
}
