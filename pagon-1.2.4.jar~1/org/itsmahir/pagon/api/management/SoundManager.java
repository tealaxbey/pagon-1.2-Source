package org.itsmahir.pagon.api.management;

import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_3414;
import net.minecraft.class_3419;
import net.minecraft.class_7923;
import org.itsmahir.pagon.api.wrapper.IMinecraft;

public class SoundManager implements IMinecraft {
   public static SoundManager INSTANCE;
   public class_3414 CLICK_SOUND;
   public class_3414 KILL_SOUND;

   public SoundManager() {
      INSTANCE = this;
      class_2960 identifier = class_2960.method_60654("pagon:click");
      this.CLICK_SOUND = class_3414.method_47908(identifier);
      class_2378.method_10230(class_7923.field_41172, identifier, this.CLICK_SOUND);
      identifier = class_2960.method_60654("pagon:neverlose");
      this.KILL_SOUND = class_3414.method_47908(identifier);
      class_2378.method_10230(class_7923.field_41172, identifier, this.KILL_SOUND);
   }

   public void play(class_3414 sound) {
      this.play(sound, 1.0F);
   }

   public void play(class_3414 sound, float volume) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         mc.field_1687.method_8396(mc.field_1724, mc.field_1724.method_24515(), sound, class_3419.field_15245, volume, 1.0F);
      }

   }
}
