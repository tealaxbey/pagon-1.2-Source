package org.itsmahir.pagon.api.utils.ducks;

public interface IClientPlayerEntity {
   float getLastSpoofedYaw();

   float getLastSpoofedPitch();

   void doTick();

   void doSendMovementPackets();
}
