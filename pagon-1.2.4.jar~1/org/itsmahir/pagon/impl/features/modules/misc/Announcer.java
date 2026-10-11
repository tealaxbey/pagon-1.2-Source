package org.itsmahir.pagon.impl.features.modules.misc;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_124;
import net.minecraft.class_1309;
import net.minecraft.class_1747;
import net.minecraft.class_2596;
import net.minecraft.class_2703;
import net.minecraft.class_2886;
import net.minecraft.class_640;
import net.minecraft.class_7828;
import net.minecraft.class_9334;
import net.minecraft.class_2703.class_5893;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.LivingEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.network.PacketEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.Timer;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;
import org.itsmahir.pagon.api.utils.targeting.TargetUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.PagonMod;

public class Announcer extends Module {
   public static Announcer INSTANCE;
   Value<String> mode = (new ValueBuilder<String>()).withDescriptor("Mode").withValue("Self").withModes("Self", "Chat", "Whisper").register(this);
   Value<Boolean> breakBlock = (new ValueBuilder<Boolean>()).withDescriptor("Break").withValue(true).register(this);
   Value<Boolean> place = (new ValueBuilder<Boolean>()).withDescriptor("Place").withValue(true).register(this);
   Value<Boolean> eat = (new ValueBuilder<Boolean>()).withDescriptor("Eat").withValue(true).register(this);
   Value<Boolean> walk = (new ValueBuilder<Boolean>()).withDescriptor("Walk").withValue(true).register(this);
   Value<Boolean> joins = (new ValueBuilder<Boolean>()).withDescriptor("Joins").withValue(true).register(this);
   Value<Boolean> timeMessages = (new ValueBuilder<Boolean>()).withDescriptor("World Time").withValue(true).register(this);
   public static String[] breakMessages = new String[]{"I just mined {amount} {name} thanks to Pagon!", "Я только что добыл {amount} {name} блока благодаря Pagon!"};
   public static String[] placeMessages = new String[]{"I just built a castle made out of {amount} {name} thanks to Pagon!", "Я только что построил замок из {amount} {name} благодаря Pagon!"};
   public static String[] eatMessages = new String[]{"I just ate {amount} {name} thanks to Pagon!", "Я только что съел {amount} {name} благодаря Pagon!"};
   public static String[] walkMessages = new String[]{"I just magically teleported {blocks} blocks thanks to Pagon!", "Я просто волшебным образом телепортировал {blocks} блоков благодаря Pagon!"};
   public static String[] joinMessages = new String[]{"Welcome to %serverip% %name%!", "Welcome to hell, %name%.", "Welcome %name% everyone!", "Hey, %name%", "AbdulaSalaha blesses you, %name%", "Greetings, %name%"};
   public static String[] leaveMessages = new String[]{"Goodbye %name%!", "Cya %name%", "Good to see you, %name%", "Catch ya later, %name%", "Farewell, %name%"};
   public static String[] morningMessages = new String[]{"I survived another night!", "Good Morning!", "You survived another night!", "The sun is rising in the east, hurrah, hurrah!"};
   public static String[] noonMessages = new String[]{"Let's go tanning!", "Let's go to the beach!", "Enjoy the sun outside! It is currently very bright!", "It's the brightest time of the day!"};
   public static String[] afterNoonMessages = new String[]{"IT'S HIGH NOON! is what ive would of said if i used future client", "Good afternoon!"};
   public static String[] sunsetMessages = new String[]{"You can say \"Sunset has now ended! You may eat your lunch now if you are a muslim.\" if you use futureshit"};
   public static String[] midNightMessages = new String[]{"It's so dark outside...", "Its scary out there!"};
   public static String[] nightMessages = new String[]{"Let's get comfy!", "Netflix and chill!", "You survived another day!"};
   public static String[] bedtimeMessages = new String[]{"BEDTIME NOW!!!", "if ur name is mcswag bedtime is IMMEDIATE.", "You may now sleep!"};
   public static String[] dayLightMessages = new String[]{"Good bye, zombies!", "All monsters will be smited down thanks to my cheat."};
   Value<Number> delay = (new ValueBuilder<Number>()).withDescriptor("Delay", "delay").withValue(5).withRange(1, 15).register(this);
   public Queue<String> messages = new LinkedList();
   public static int blockBrokeDelay = 0;
   static int blockPlacedDelay = 0;
   static int jumpDelay = 0;
   static int attackDelay = 0;
   static int eattingDelay = 0;
   static long lastPositionUpdate;
   static double lastPositionX;
   static double lastPositionY;
   static double lastPositionZ;
   private static double speed;
   Timer timer = new Timer();
   Timer timeCooldown = new Timer();
   String heldItem = "";
   int blocksPlaced = 0;
   int blocksBroken = 0;
   int eaten = 0;
   Random random = new Random();

   public Announcer() {
      super("Announcer", Feature.Category.Misc);
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Send event) {
      if (!NullUtils.nullCheck()) {
         if (event.getPacket() instanceof class_2886 && mc.field_1724.method_6047().method_7909() instanceof class_1747) {
            if (!(Boolean)this.place.getValue()) {
               return;
            }

            ++this.blocksPlaced;
            int randomNum = ThreadLocalRandom.current().nextInt(1, 11);
            if (blockPlacedDelay >= 150 * ((Number)this.delay.getValue()).intValue() && this.blocksPlaced > randomNum) {
               String msg = placeMessages[this.random.nextInt(placeMessages.length)].replace("{amount}", "" + this.blocksPlaced).replace("{name}", mc.field_1724.method_6047().method_7964().getString());
               this.sendMessage(msg);
               this.blocksPlaced = 0;
               blockPlacedDelay = 0;
            }
         }

      }
   }

   @SubscribeEvent
   public void onPacket(PacketEvent.Receive event) {
      if (!NullUtils.nullCheck()) {
         class_2596 var3 = event.getPacket();
         if (var3 instanceof class_2703) {
            class_2703 packet = (class_2703)var3;
            if (packet.method_46327().contains(class_5893.field_29136)) {
               for(class_2703.class_2705 entry : packet.method_46330()) {
                  if (entry.comp_1107() != null && !entry.comp_1107().getName().equalsIgnoreCase("") && !entry.comp_1107().getName().equalsIgnoreCase(mc.method_1548().method_1676())) {
                     this.sendMessage(joinMessages[this.random.nextInt(joinMessages.length)].replace("%name%", entry.comp_1107().getName()));
                  }
               }
            }
         }

         var3 = event.getPacket();
         if (var3 instanceof class_7828) {
            class_7828 packet = (class_7828)var3;

            for(UUID uuid : packet.comp_1105) {
               class_640 entry = mc.field_1724.field_3944.method_2871(uuid);
               if (entry != null && !entry.method_2966().getName().equalsIgnoreCase("") && !entry.method_2966().getName().equalsIgnoreCase(mc.method_1548().method_1676())) {
                  this.sendMessage(leaveMessages[this.random.nextInt(leaveMessages.length)].replace("%name%", entry.method_2966().getName()));
               }
            }
         }

      }
   }

   @SubscribeEvent
   public void onBlockBreak(LivingEvent.BreakBlock event) {
      if (!NullUtils.nullCheck()) {
         ++this.blocksBroken;
         int randomNum = ThreadLocalRandom.current().nextInt(1, 11);
         if (blockBrokeDelay >= 300 * ((Number)this.delay.getValue()).intValue() && this.blocksBroken > randomNum) {
            Random random = new Random();
            String msg = breakMessages[random.nextInt(breakMessages.length)].replace("{amount}", "" + this.blocksBroken).replace("{name}", mc.field_1687.method_8320(event.getPos()).method_26204().method_9518().getString());
            this.sendMessage(msg);
            this.blocksBroken = 0;
            blockBrokeDelay = 0;
         }

      }
   }

   public void doTime() {
      if (this.timeCooldown.isPassed(100L)) {
         if (mc.field_1724.field_6012 >= 200) {
            if (mc.field_1687.method_8510() == 0L) {
               this.sendMessage(morningMessages[this.random.nextInt(morningMessages.length)]);
               this.timeCooldown.resetDelay();
            }

            if (mc.field_1687.method_8510() == 4000L) {
               this.sendMessage(noonMessages[this.random.nextInt(noonMessages.length)]);
               this.timeCooldown.resetDelay();
            }

            if (mc.field_1687.method_8510() == 6000L) {
               this.sendMessage(afterNoonMessages[this.random.nextInt(afterNoonMessages.length)]);
               this.timeCooldown.resetDelay();
            }

            if (mc.field_1687.method_8510() == 13000L) {
               this.sendMessage(bedtimeMessages[this.random.nextInt(bedtimeMessages.length)]);
               this.timeCooldown.resetDelay();
            }

            if (mc.field_1687.method_8510() == 14000L) {
               this.sendMessage(nightMessages[this.random.nextInt(nightMessages.length)]);
               this.timeCooldown.resetDelay();
            }

            if (mc.field_1687.method_8510() == 16000L) {
               this.sendMessage(sunsetMessages[this.random.nextInt(sunsetMessages.length)]);
               this.timeCooldown.resetDelay();
            }

            if (mc.field_1687.method_8510() == 18000L) {
               this.sendMessage(midNightMessages[this.random.nextInt(midNightMessages.length)]);
               this.timeCooldown.resetDelay();
            }

            if (mc.field_1687.method_8510() == 23000L) {
               this.sendMessage(dayLightMessages[this.random.nextInt(dayLightMessages.length)]);
               this.timeCooldown.resetDelay();
            }

         }
      }
   }

   @SubscribeEvent
   public void onUpdate(TickEvent.ClientTickEvent event) {
      if (!NullUtils.nullCheck()) {
         ++blockBrokeDelay;
         ++blockPlacedDelay;
         ++jumpDelay;
         ++attackDelay;
         ++eattingDelay;
         this.heldItem = mc.field_1724.method_6047().method_7964().getString();
         if ((Boolean)this.walk.getValue() && lastPositionUpdate + 5000L * (long)((Number)this.delay.getValue()).intValue() < System.currentTimeMillis()) {
            double d0 = lastPositionX - mc.field_1724.method_23317();
            double d2 = lastPositionY - mc.field_1724.method_23318();
            double d3 = lastPositionZ - mc.field_1724.method_23321();
            speed = Math.sqrt(d0 * d0 + d2 * d2 + d3 * d3);
            if (!(speed <= (double)1.0F) && !(speed > (double)5000.0F)) {
               String walkAmount = (new DecimalFormat("0.00")).format(speed);
               Random random = new Random();
               this.sendMessage(walkMessages[random.nextInt(walkMessages.length)].replace("{blocks}", "" + walkAmount));
               lastPositionUpdate = System.currentTimeMillis();
               lastPositionX = mc.field_1724.method_23317();
               lastPositionY = mc.field_1724.method_23318();
               lastPositionZ = mc.field_1724.method_23321();
            }
         }

         if (!((String)this.mode.getValue()).equals("Self") && this.timer.isPassed((long)((Number)this.delay.getValue()).intValue() * 1000L) && !this.messages.isEmpty()) {
            if (((String)this.mode.getValue()).equals("Whisper")) {
               class_1309 entity = TargetUtils.getTarget((double)100.0F);
               if (entity != null) {
                  String var10000 = entity.method_5477().getString();
                  ChatUtils.sendChatMessage("/w " + var10000 + " " + (String)this.messages.poll());
               }
            } else {
               ChatUtils.sendChatMessage((String)this.messages.poll());
            }

            this.timer.resetDelay();
         }

         if ((Boolean)this.timeMessages.getValue()) {
            this.doTime();
         }

      }
   }

   @SubscribeEvent
   public void onEntityEat(LivingEvent.Eat event) {
      if (!NullUtils.nullCheck()) {
         if ((Boolean)this.eat.getValue()) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 11);
            if (event.getStack().method_57353().method_57832(class_9334.field_50075)) {
               ++this.eaten;
               if (eattingDelay >= 300 * ((Number)this.delay.getValue()).intValue() && this.eaten > randomNum) {
                  Random random = new Random();
                  this.sendMessage(eatMessages[random.nextInt(eatMessages.length)].replace("{amount}", "" + this.eaten).replace("{name}", "" + event.getItem().method_7848().getString()));
                  this.eaten = 0;
                  eattingDelay = 0;
               }
            }

         }
      }
   }

   public void onDisable() {
      super.onDisable();
      if (!NullUtils.nullCheck()) {
         ;
      }
   }

   public void onEnable() {
      super.onEnable();
      if (!NullUtils.nullCheck()) {
         ;
      }
   }

   public void sendMessage(String message) {
      if (PagonMod.NAME != PagonMod.NAME_UNICODE) {
         message = message.replace("Pagon", PagonMod.NAME);
      }

      String ip = !mc.method_1542() && mc.method_1562() != null ? mc.method_1562().method_48296().method_10755().toString().toLowerCase() : "Singleplayer";
      message = message.replace("%serverip%", ip);
      if (((String)this.mode.getValue()).equals("Self")) {
         String var10000 = String.valueOf(class_124.field_1075);
         ChatUtils.sendMessage(var10000 + message);
      } else {
         this.messages.add(message);
      }

   }

   public String getDescription() {
      return "Announcer: Flex on pagonless dogs with this module";
   }
}
