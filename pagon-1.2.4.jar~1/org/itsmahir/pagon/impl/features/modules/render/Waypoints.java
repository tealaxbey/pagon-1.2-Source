package org.itsmahir.pagon.impl.features.modules.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_124;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.render.RenderWorldEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.management.WaypointManager;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.color.TextSection;
import org.itsmahir.pagon.api.utils.render.Interpolator;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.utils.render.world.buffers.RenderBuffers;
import org.itsmahir.pagon.impl.features.modules.client.gui.Hud;
import org.lwjgl.opengl.GL11;

public class Waypoints extends Module {
   public static Waypoints INSTANCE;

   public Waypoints() {
      super("Waypoints", Feature.Category.Render);
      INSTANCE = this;
   }

   @SubscribeEvent
   public void onRenderWorld(RenderWorldEvent event) {
      if (!NullUtils.nullCheck()) {
         if (!WaypointManager.INSTANCE.wayPoints.isEmpty()) {
            RenderBuffers.scheduleRender(() -> {
               for(WaypointManager.WayPoint loc : WaypointManager.INSTANCE.wayPoints) {
                  if (loc.getName() != null && (!mc.method_1542() || loc.getServer().equals("SinglePlayer")) && (mc.method_1562().method_45734() == null || mc.method_1562().method_45734().field_3761.contains(loc.getServer())) && mc.field_1687.method_27983().method_29177().method_12832().equals(loc.getDimension())) {
                     this.renderWaypoint(loc, event);
                  }
               }

               RenderSystem.enableBlend();
            });
         }

      }
   }

   private void renderWaypoint(WaypointManager.WayPoint loc, RenderWorldEvent event) {
      class_243 interpolate = Interpolator.getInterpolatedEyePos(mc.method_1560(), event.getTickDelta());
      class_4184 camera = mc.field_1773.method_19418();
      class_243 pos = camera.method_19326();
      double dx = pos.method_10216() - interpolate.method_10216() - (double)loc.getX();
      double dy = pos.method_10214() - interpolate.method_10214() - (double)loc.getY();
      double dz = pos.method_10215() - interpolate.method_10215() - (double)loc.getZ();
      double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
      TextSection[] text = new TextSection[1];
      String var10000 = String.valueOf(class_124.field_1080);
      String distance = var10000 + " (" + String.valueOf(class_124.field_1068) + class_3532.method_15357(dist) + "m" + String.valueOf(class_124.field_1080) + ")";
      text[0] = new TextSection(String.valueOf(class_124.field_1068) + loc.getName() + distance, new Color(255, 255, 255));
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      GL11.glDepthFunc(519);
      RenderUtil.drawWaypoint(text, (double)loc.getX(), (double)loc.getY() + 1.4, (double)loc.getZ(), mc.field_1773.method_19418(), Hud.getTextColor(0));
      GL11.glDepthFunc(515);
      RenderSystem.disableBlend();
   }

   public String getDescription() {
      return "Waypoints: renders waypoints (-waypoint create mcswag 0 64 0)";
   }
}
