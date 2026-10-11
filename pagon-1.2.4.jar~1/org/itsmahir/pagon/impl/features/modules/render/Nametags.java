package org.itsmahir.pagon.impl.features.modules.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1738;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1887;
import net.minecraft.class_1890;
import net.minecraft.class_1893;
import net.minecraft.class_243;
import net.minecraft.class_308;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_5321;
import net.minecraft.class_640;
import net.minecraft.class_6880;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import net.minecraft.class_9304;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.gui.font.Fonts;
import org.itsmahir.pagon.api.management.FriendManager;
import org.itsmahir.pagon.api.management.PopManager;
import org.itsmahir.pagon.api.utils.color.ColorUtil;
import org.itsmahir.pagon.api.utils.color.PagonColor;
import org.itsmahir.pagon.api.utils.color.RainbowUtil;
import org.itsmahir.pagon.api.utils.players.PlayerUtils;
import org.itsmahir.pagon.api.utils.render.Interpolator;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.world.HoleUtils;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

public class Nametags extends Module {
   public static Nametags INSTANCE;
   Value<String> page = (new ValueBuilder<String>()).withDescriptor("Page").withValue("Colors").withModes("Colors", "Items").withAction((s) -> this.handlePage((String)s.getValue())).register(this);
   public Value<PagonColor> borderColor = (new ValueBuilder<PagonColor>()).withDescriptor("Border Color").withValue(new PagonColor(255, 0, 0, 255)).register(this);
   public Value<Boolean> safeBorder = (new ValueBuilder<Boolean>()).withDescriptor("Safe Border").withValue(false).register(this);
   public Value<PagonColor> safeColor = (new ValueBuilder<PagonColor>()).withDescriptor("Safe Color").withValue(new PagonColor(0, 255, 0, 255)).register(this);
   public Value<PagonColor> boxColor = (new ValueBuilder<PagonColor>()).withDescriptor("Back Color").withValue(new PagonColor(25, 25, 25, 255)).register(this);
   public Value<PagonColor> normalColor = (new ValueBuilder<PagonColor>()).withDescriptor("Normal Color").withValue(new PagonColor(255, 255, 255, 255)).register(this);
   public Value<PagonColor> friendsColor = (new ValueBuilder<PagonColor>()).withDescriptor("Friends Color").withValue(new PagonColor(0, 255, 255, 255)).register(this);
   public Value<Boolean> friendBorder = (new ValueBuilder<Boolean>()).withDescriptor("Friend Border").withValue(true).register(this);
   public Value<PagonColor> SneakingColor = (new ValueBuilder<PagonColor>()).withDescriptor("Sneaking Color").withValue(new PagonColor(255, 153, 0)).register(this);
   public Value<PagonColor> popColorA = (new ValueBuilder<PagonColor>()).withDescriptor("Pop Color A").withValue(new PagonColor(0, 255, 255, 255)).register(this);
   public Value<PagonColor> popColorB = (new ValueBuilder<PagonColor>()).withDescriptor("Pop Color B").withValue(new PagonColor(0, 0, 255, 255)).register(this);
   public Value<PagonColor> popColorC = (new ValueBuilder<PagonColor>()).withDescriptor("Pop Color C").withValue(new PagonColor(87, 8, 97)).register(this);
   public Value<Boolean> armor = (new ValueBuilder<Boolean>()).withDescriptor("Armor").withValue(true).register(this);
   public Value<Boolean> items = (new ValueBuilder<Boolean>()).withDescriptor("Items").withValue(true).register(this);
   public Value<Boolean> enchantments = (new ValueBuilder<Boolean>()).withDescriptor("Enchantments").withValue(false).register(this);
   public Value<Boolean> durability = (new ValueBuilder<Boolean>()).withDescriptor("Durability").withValue(true).register(this);
   public Value<Boolean> itemName = (new ValueBuilder<Boolean>()).withDescriptor("Item Name").withValue(true).register(this);
   public Value<Boolean> entityId = (new ValueBuilder<Boolean>()).withDescriptor("Entity Id").withValue(false).register(this);
   public Value<Boolean> gamemode = (new ValueBuilder<Boolean>()).withDescriptor("Gamemode").withValue(false).register(this);
   public Value<Boolean> ping = (new ValueBuilder<Boolean>()).withDescriptor("Ping").withValue(true).register(this);
   public Value<Boolean> health = (new ValueBuilder<Boolean>()).withDescriptor("Health").withValue(true).register(this);
   public Value<Boolean> totems = (new ValueBuilder<Boolean>()).withDescriptor("Totems").withValue(true).register(this);
   public Value<Boolean> background = (new ValueBuilder<Boolean>()).withDescriptor("Background").withValue(true).register(this);
   public Value<Boolean> bordered = (new ValueBuilder<Boolean>()).withDescriptor("Border").withValue(false).register(this);
   public Value<Number> scaling = (new ValueBuilder<Number>()).withDescriptor("Scaling").withValue(0.003F).withRange(0.001F, 0.01F).withPlaces(4).register(this);
   public Value<Number> range = (new ValueBuilder<Number>()).withDescriptor("Range").withValue(300).withRange(0, 300).register(this);
   public Value<Boolean> ColoredPing = (new ValueBuilder<Boolean>()).withDescriptor("Colored Ping").withValue(false).register(this);
   public Value<Boolean> tabHealth = (new ValueBuilder<Boolean>()).withDescriptor("Tab Health").withValue(false).register(this);
   public Value<Boolean> shortEnchants = (new ValueBuilder<Boolean>()).withDescriptor("Short Enchants").withValue(false).register(this);
   public Value<Boolean> rainbow32k = (new ValueBuilder<Boolean>()).withDescriptor("Rainbow 32ks").withValue(false).register(this);
   public static Set<class_5321<class_1887>> IMPORTANT;

   public Nametags() {
      super("Nametags", Feature.Category.Render);
      INSTANCE = this;
   }

   void handlePage(String page) {
      this.borderColor.setActive(page.equals("Colors"));
      this.safeBorder.setActive(page.equals("Colors"));
      this.safeColor.setActive(page.equals("Colors") && (Boolean)this.safeBorder.getValue());
      this.boxColor.setActive(page.equals("Colors"));
      this.normalColor.setActive(page.equals("Colors"));
      this.friendsColor.setActive(page.equals("Colors"));
      this.friendBorder.setActive(page.equals("Colors"));
      this.SneakingColor.setActive(page.equals("Colors"));
      this.popColorA.setActive(page.equals("Colors"));
      this.popColorB.setActive(page.equals("Colors"));
      this.popColorC.setActive(page.equals("Colors"));
      this.armor.setActive(page.equals("Items"));
      this.items.setActive(page.equals("Items"));
      this.enchantments.setActive(page.equals("Items"));
      this.durability.setActive(page.equals("Items"));
      this.itemName.setActive(page.equals("Items"));
      this.entityId.setActive(page.equals("Items"));
      this.gamemode.setActive(page.equals("Items"));
      this.ping.setActive(page.equals("Items"));
      this.ColoredPing.setActive(page.equals("Items"));
      this.health.setActive(page.equals("Items"));
      this.tabHealth.setActive(page.equals("Items") && (Boolean)this.health.getValue());
      this.totems.setActive(page.equals("Items"));
      this.background.setActive(page.equals("Items"));
      this.bordered.setActive(page.equals("Items"));
      this.shortEnchants.setActive(page.equals("Items") && (Boolean)this.enchantments.getValue());
      this.rainbow32k.setActive(page.equals("Items") && (Boolean)this.enchantments.getValue());
      this.range.setActive(page.equals("Items"));
   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldEvent event) {
      if (mc.field_1773 != null && mc.method_1560() != null) {
         class_4184 camera = mc.field_1773.method_19418();
         class_243 pos = camera.method_19326();
         event.getMatrices().method_22903();

         for(class_1297 entity : mc.field_1687.method_18112()) {
            if (entity instanceof class_1657) {
               class_1657 player = (class_1657)entity;
               if (player.method_5805() && (player != mc.field_1724 || Freecam.INSTANCE.isEnabled())) {
                  String info = this.getNametagInfo(player);
                  class_243 pinterpolate = Interpolator.getRenderPosition(player, event.getTickDelta());
                  double rx = player.method_23317() - pinterpolate.method_10216();
                  double ry = player.method_23318() - pinterpolate.method_10214();
                  double rz = player.method_23321() - pinterpolate.method_10215();
                  int width = (int)Fonts.getTextWidth(info);
                  float hwidth = (float)width / 2.0F;
                  double dx = pos.method_10216() - rx;
                  double dy = pos.method_10214() - ry;
                  double dz = pos.method_10215() - rz;
                  double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
                  if (!(dist > (double)((Number)this.range.getValue()).floatValue())) {
                     float scale = 0.02F + ((Number)this.scaling.getValue()).floatValue();
                     this.renderInfo(info, hwidth, player, rx, ry, rz, camera, scale, event.getMatrices());
                  }
               }
            }
         }

         event.getMatrices().method_22909();
      }
   }

   private void renderInfo(String info, float width, class_1657 entity, double x, double y, double z, class_4184 camera, float scaling, class_4587 matrices) {
      matrices.method_22903();
      matrices.method_22904(x, y + (double)entity.method_17682() + (double)(entity.method_5715() ? 0.4F : 0.43F), z);
      matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
      matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
      matrices.method_22905(-scaling, -scaling, 1.0F);
      GL11.glDepthFunc(519);
      if ((Boolean)this.background.getValue()) {
         double var10001 = (double)(-width - 1.0F);
         double var10003 = (double)(width * 2.0F + 2.0F);
         Objects.requireNonNull(mc.field_1772);
         RenderUtil.renderRect(matrices, var10001, (double)-1.0F, var10003, (double)(9.0F + 1.0F), 1426064384);
      }

      if ((Boolean)this.bordered.getValue()) {
         double var14 = (double)(-width - 1.0F);
         double var15 = (double)(width * 2.0F + 2.0F);
         Objects.requireNonNull(mc.field_1772);
         RenderUtil.renderOutline(matrices, var14, (double)-1.0F, var15, (double)(9.0F + 1.0F), (Boolean)this.friendBorder.getValue() && FriendManager.INSTANCE.isFriend(entity) ? ((PagonColor)this.friendsColor.getValue()).getColor().getRGB() : ((Boolean)this.safeBorder.getValue() && HoleUtils.isHole(PlayerUtils.getPos(entity)) ? ((PagonColor)this.safeColor.getValue()).getColor().getRGB() : ((PagonColor)this.borderColor.getValue()).getColor().getRGB()), true);
      }

      int color = this.getNametagColor(entity);
      Fonts.renderText(matrices, info, -width, 0.0F, new Color(color), true);
      this.renderItems(matrices, entity);
      GL11.glDepthFunc(515);
      matrices.method_22909();
   }

   private void renderItems(class_4587 matrixStack, class_1657 player) {
      List<class_1799> displayItems = new CopyOnWriteArrayList();
      if (!player.method_6079().method_7960()) {
         displayItems.add(player.method_6079());
      }

      player.method_31548().field_7548.forEach((armorStack) -> {
         if (!armorStack.method_7960()) {
            displayItems.add(armorStack);
         }

      });
      if (!player.method_6047().method_7960()) {
         displayItems.add(player.method_6047());
      }

      Collections.reverse(displayItems);
      float n10 = 0.0F;
      int n11 = 0;
      boolean gapple = true;

      for(class_1799 stack : displayItems) {
         n10 -= gapple ? 9.0F : 8.0F;
         gapple = false;
         if (stack.method_58657().method_57534().size() > n11) {
            n11 = stack.method_58657().method_57534().size();
         }
      }

      float m2 = this.enchantOffset(n11);

      for(class_1799 stack : displayItems) {
         boolean isArmor = stack.method_7909() instanceof class_1738;
         if ((Boolean)this.armor.getValue() && isArmor || (Boolean)this.items.getValue() && !isArmor) {
            float y = !isArmor && !(Boolean)this.enchantments.getValue() ? -18.5F : m2;
            matrixStack.method_22903();
            matrixStack.method_46416(n10, y, 0.0F);
            matrixStack.method_46416(8.0F, 8.0F, 0.0F);
            matrixStack.method_22905(16.0F, 16.0F, 0.0F);
            matrixStack.method_34425((new Matrix4f()).scaling(1.0F, -1.0F, 1.0E-4F));
            Vector3f[] shaderLights = (Vector3f[])RenderSystem.shaderLightDirections.clone();
            class_308.method_24210();
            RenderUtil.renderItem(stack, class_811.field_4317, matrixStack, mc.method_22940().method_23000(), mc.field_1687, 0);
            mc.method_22940().method_23000().method_22993();
            class_308.method_24211();
            RenderSystem.setShaderLights(shaderLights[0], shaderLights[1]);
            matrixStack.method_22909();
            this.renderItemOverlay(matrixStack, stack, (int)n10, (int)y);
            matrixStack.method_22905(0.5F, 0.5F, 0.5F);
            if (stack.method_7909() == class_1802.field_8367) {
               float y2 = (m2 + ((Boolean)this.armor.getValue() ? 1.0F : -13.5F)) * 2.0F;
               Fonts.renderText(matrixStack, "God", (n10 + 2.0F) * 2.0F, y2, new Color(-3977663), true);
            } else if ((Boolean)this.enchantments.getValue()) {
               this.renderEnchants(matrixStack, stack, n10 + 2.0F, m2);
            }

            matrixStack.method_22905(2.0F, 2.0F, 2.0F);
         }

         matrixStack.method_22905(0.5F, 0.5F, 0.5F);
         if ((Boolean)this.durability.getValue()) {
            this.renderDurability(matrixStack, stack, n10 + 2.0F, m2 - 4.5F);
         }

         matrixStack.method_22905(2.0F, 2.0F, 2.0F);
         n10 += 16.0F;
      }

      class_1799 heldItem = player.method_6047();
      if (!heldItem.method_7960()) {
         matrixStack.method_22905(0.5F, 0.5F, 0.5F);
         if ((Boolean)this.itemName.getValue()) {
            this.renderItemName(matrixStack, heldItem, 0.0F, (Boolean)this.durability.getValue() ? m2 - 10.0F : m2 - 5.5F);
         }

         matrixStack.method_22905(2.0F, 2.0F, 2.0F);
      }
   }

   private void renderItemOverlay(class_4587 matrixStack, class_1799 stack, int x, int y) {
      if (stack.method_7947() != 1) {
         String string = String.valueOf(stack.method_7947());
         Fonts.renderText(matrixStack, string, (float)(x + 17) - Fonts.getTextWidth(string), (float)y + 9.0F, Color.WHITE, true);
      }

      if (stack.method_31578()) {
         int i = stack.method_31579();
         int j = stack.method_31580();
         int k = x + 3;
         int l = y + 13;
         RenderUtil.renderRect(matrixStack, (double)k, (double)l, (double)12.0F, (double)1.0F, -16777216);
         RenderUtil.renderRect(matrixStack, (double)k, (double)l, (double)i, (double)1.0F, j | -16777216);
      }

   }

   private void renderDurability(class_4587 matrixStack, class_1799 itemStack, float x, float y) {
      if (itemStack.method_7963()) {
         int n = itemStack.method_7936();
         int n2 = itemStack.method_7919();
         int durability = (int)((float)(n - n2) / (float)n * 100.0F);
         Fonts.renderText(matrixStack, durability + "%", x * 2.0F, y * 2.0F, ColorUtil.hslToColor((float)(n - n2) / (float)n * 120.0F, 100.0F, 50.0F, 1.0F), true);
      }
   }

   private void renderEnchants(class_4587 matrixStack, class_1799 itemStack, float x, float y) {
      if (itemStack.method_7942()) {
         class_9304 enchants = class_1890.method_57532(itemStack);
         float n2 = 0.0F;

         for(class_6880<class_1887> enchantment : enchants.method_57534()) {
            boolean available = !(Boolean)this.shortEnchants.getValue();
            if ((Boolean)this.shortEnchants.getValue()) {
               for(class_5321<class_1887> key : IMPORTANT) {
                  if (enchantment.method_40225(key)) {
                     available = true;
                     break;
                  }
               }
            }

            if (available) {
               int lvl = enchants.method_57536(enchantment);
               StringBuilder enchantString = new StringBuilder();
               String translatedName = class_1887.method_8179(enchantment, lvl).getString();
               if (translatedName.contains("Vanish")) {
                  enchantString.append("Van");
               } else if (translatedName.contains("Bind")) {
                  enchantString.append("Bind");
               } else {
                  int maxLen = lvl > 1 ? 2 : 3;
                  if (translatedName.length() > maxLen) {
                     translatedName = translatedName.substring(0, maxLen);
                  }

                  enchantString.append(translatedName);
                  enchantString.append(lvl);
               }

               if ((Boolean)this.rainbow32k.getValue() && lvl > 42) {
                  RainbowUtil.render32k(matrixStack, enchantString.toString(), x * 2.0F, (y + n2) * 2.0F);
               } else {
                  Fonts.renderText(matrixStack, enchantString.toString(), x * 2.0F, (y + n2) * 2.0F, new Color(-1), true);
               }

               n2 += 4.5F;
            }
         }

      }
   }

   private float enchantOffset(int n) {
      if ((Boolean)this.enchantments.getValue() && n > 3) {
         float n2 = -14.0F;
         n2 -= (float)(n - 3) * 4.5F;
         return n2;
      } else {
         return (Boolean)this.armor.getValue() ? -18.0F : -3.5F;
      }
   }

   private void renderItemName(class_4587 matrixStack, class_1799 itemStack, float x, float y) {
      String itemName = itemStack.method_7964().getString();
      float width = Fonts.getTextWidth(itemName) / 4.0F;
      Fonts.renderText(matrixStack, itemName, (x - width) * 2.0F, y * 2.0F, Color.WHITE, true);
   }

   private String getNametagInfo(class_1657 player) {
      StringBuilder info = new StringBuilder(player.method_5477().getString());
      info.append(" ");
      if ((Boolean)this.entityId.getValue()) {
         info.append("ID: ");
         info.append(player.method_5628());
         info.append(" ");
      }

      if ((Boolean)this.gamemode.getValue()) {
         if (player.method_7337()) {
            info.append("[C] ");
         } else if (player.method_7325()) {
            info.append("[I] ");
         } else {
            info.append("[S] ");
         }
      }

      if ((Boolean)this.ping.getValue() && mc.method_1562() != null) {
         class_640 playerEntry = mc.method_1562().method_2871(player.method_7334().getId());
         if (playerEntry != null) {
            info.append(this.getPingColor((double)playerEntry.method_2959()));
            info.append(playerEntry.method_2959());
            info.append("ms ");
         }
      }

      if ((Boolean)this.health.getValue()) {
         double hp = (Boolean)this.tabHealth.getValue() ? (double)PlayerUtils.getTabHealth(player.method_5477().getString()) : (double)(player.method_6032() + player.method_6067());
         class_124 hcolor;
         if (hp > (double)18.0F) {
            hcolor = class_124.field_1060;
         } else if (hp > (double)16.0F) {
            hcolor = class_124.field_1077;
         } else if (hp > (double)12.0F) {
            hcolor = class_124.field_1054;
         } else if (hp > (double)8.0F) {
            hcolor = class_124.field_1065;
         } else if (hp > (double)4.0F) {
            hcolor = class_124.field_1061;
         } else {
            hcolor = class_124.field_1079;
         }

         BigDecimal bigDecimal = BigDecimal.valueOf(hp);
         bigDecimal = bigDecimal.setScale(1, RoundingMode.HALF_UP);
         info.append(hcolor);
         info.append(bigDecimal.doubleValue());
         info.append(" ");
      }

      if ((Boolean)this.totems.getValue() && player != mc.field_1724) {
         int totemPops = PopManager.INSTANCE.getPops((class_1297)player);
         if (totemPops > 0) {
            class_124 pcolor;
            if (totemPops > 5) {
               pcolor = class_124.field_1079;
            } else if (totemPops > 4) {
               pcolor = class_124.field_1061;
            } else if (totemPops > 3) {
               pcolor = class_124.field_1065;
            } else if (totemPops > 2) {
               pcolor = class_124.field_1054;
            } else if (totemPops > 1) {
               pcolor = class_124.field_1077;
            } else {
               pcolor = class_124.field_1060;
            }

            info.append(pcolor);
            info.append(-totemPops);
            info.append(" ");
         }
      }

      return info.toString().trim();
   }

   public class_124 getPingColor(double ping) {
      if ((Boolean)this.ColoredPing.getValue()) {
         if (ping <= (double)40.0F) {
            return class_124.field_1060;
         } else if (ping <= (double)70.0F) {
            return class_124.field_1077;
         } else {
            return ping <= (double)99.0F ? class_124.field_1054 : class_124.field_1061;
         }
      } else {
         return class_124.field_1070;
      }
   }

   private int getNametagColor(class_1657 player) {
      if (FriendManager.INSTANCE.isFriend(player)) {
         return ((PagonColor)this.friendsColor.getValue()).getColor().getRGB();
      } else if (player.method_5767()) {
         return -56064;
      } else {
         return player.method_5715() ? -26368 : ((PagonColor)this.normalColor.getValue()).getColor().getRGB();
      }
   }

   public String getDescription() {
      return "Nametags: renders descriptive nametags above players";
   }

   static {
      IMPORTANT = Set.of(class_1893.field_9111, class_1893.field_9118, class_1893.field_9101, class_1893.field_9107, class_1893.field_9129, class_1893.field_9119, class_1893.field_9121, class_1893.field_9131, class_1893.field_9117, class_1893.field_9103, class_1893.field_9099, class_1893.field_9097);
   }
}
