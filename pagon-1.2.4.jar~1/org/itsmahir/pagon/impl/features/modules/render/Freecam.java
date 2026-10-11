package org.itsmahir.pagon.impl.features.modules.render;

import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_5498;
import org.itsmahir.pagon.api.event.eventbus.SubscribeEvent;
import org.itsmahir.pagon.api.event.events.TickEvent;
import org.itsmahir.pagon.api.event.events.key.KeyboardEvent;
import org.itsmahir.pagon.api.event.events.key.MouseEvent;
import org.itsmahir.pagon.api.event.events.render.ScreenEvent;
import org.itsmahir.pagon.api.feature.Feature;
import org.itsmahir.pagon.api.feature.module.Module;
import org.itsmahir.pagon.api.utils.NullUtils;
import org.itsmahir.pagon.api.utils.players.rotation.RotationUtils;
import org.itsmahir.pagon.api.utils.render.RenderUtil;
import org.itsmahir.pagon.api.value.Value;
import org.itsmahir.pagon.api.value.builder.ValueBuilder;
import org.itsmahir.pagon.impl.features.modules.movement.NoSlow;
import org.joml.Vector3d;

public class Freecam extends Module {
   public static Freecam INSTANCE;
   Value<Number> speed = (new ValueBuilder<Number>()).withDescriptor("Speed").withValue((double)1.0F).withRange(0.1, (double)4.0F).withPlaces(2).register(this);
   Value<Number> vertical = (new ValueBuilder<Number>()).withDescriptor("Vertical Speed").withValue((double)0.5F).withRange(0.1, (double)2.0F).withPlaces(2).register(this);
   public Value<Boolean> rotate = (new ValueBuilder<Boolean>()).withDescriptor("Rotate").withValue(false).register(this);
   public final Vector3d pos = new Vector3d();
   public final Vector3d prevPos = new Vector3d();
   private class_5498 perspective;
   public float yaw;
   public float pitch;
   public float prevYaw;
   public float prevPitch;
   private double fovScale;
   private boolean bobView;
   private boolean forward;
   private boolean backward;
   private boolean right;
   private boolean left;
   private boolean up;
   private boolean down;

   public Freecam() {
      super("Freecam", Feature.Category.Render);
      INSTANCE = this;
   }

   public void onEnable() {
      super.onEnable();
      if (mc.field_1724 != null && mc.field_1687 != null) {
         this.fovScale = (double)((Double)mc.field_1690.method_42454().method_41753()).floatValue();
         this.bobView = (Boolean)mc.field_1690.method_42448().method_41753();
         mc.field_1690.method_42454().method_41748((double)0.0F);
         mc.field_1690.method_42448().method_41748(false);
         this.yaw = mc.field_1724.method_36454();
         this.pitch = mc.field_1724.method_36455();
         this.perspective = mc.field_1690.method_31044();
         RenderUtil.set(this.pos, mc.field_1773.method_19418().method_19326());
         RenderUtil.set(this.prevPos, mc.field_1773.method_19418().method_19326());
         this.prevYaw = this.yaw;
         this.prevPitch = this.pitch;
         this.forward = mc.field_1690.field_1894.method_1434();
         this.backward = mc.field_1690.field_1881.method_1434();
         this.right = mc.field_1690.field_1849.method_1434();
         this.left = mc.field_1690.field_1913.method_1434();
         this.up = mc.field_1690.field_1903.method_1434();
         this.down = mc.field_1690.field_1832.method_1434();
         this.unpress();
      } else {
         this.toggle();
      }
   }

   @SubscribeEvent
   private void onOpenScreen(ScreenEvent.SetScreen event) {
      if (!this.checkGuiMove()) {
         this.unpress();
      }

      this.prevPos.set(this.pos);
      this.prevYaw = this.yaw;
      this.prevPitch = this.pitch;
   }

   public void onDisable() {
      super.onDisable();
      if (!NullUtils.nullCheck()) {
         mc.field_1690.method_31043(this.perspective);
         mc.field_1690.method_42454().method_41748(this.fovScale);
         mc.field_1690.method_42448().method_41748(this.bobView);
         mc.field_1690.field_1894.method_23481(this.forward);
         mc.field_1690.field_1881.method_23481(this.backward);
         mc.field_1690.field_1849.method_23481(this.right);
         mc.field_1690.field_1913.method_23481(this.left);
         mc.field_1690.field_1903.method_23481(this.up);
         mc.field_1690.field_1832.method_23481(this.down);
      }
   }

   private void unpress() {
      mc.field_1690.field_1894.method_23481(false);
      mc.field_1690.field_1881.method_23481(false);
      mc.field_1690.field_1849.method_23481(false);
      mc.field_1690.field_1913.method_23481(false);
      mc.field_1690.field_1903.method_23481(false);
      mc.field_1690.field_1832.method_23481(false);
   }

   @SubscribeEvent
   public void onPlayerTick(TickEvent.PlayerTickEvent.Pre event) {
      if (!NullUtils.nullCheck()) {
         if ((Boolean)this.rotate.getValue() && mc.field_1765 != null && mc.field_1765.method_17784() != null && (mc.field_1690.field_1904.method_1434() || mc.field_1690.field_1886.method_1434())) {
            RotationUtils.setRotation(RotationUtils.getRotationsTo(mc.field_1724.method_33571(), mc.field_1765.method_17784()));
         }

      }
   }

   @SubscribeEvent
   public void onTick(TickEvent.AfterClientTickEvent event) {
      if (mc.field_1719.method_5757()) {
         mc.method_1560().field_5960 = true;
      }

      if (!this.perspective.method_31034()) {
         mc.field_1690.method_31043(class_5498.field_26664);
      }

      class_243 forward = class_243.method_1030(0.0F, this.yaw);
      class_243 right = class_243.method_1030(0.0F, this.yaw + 90.0F);
      double velX = (double)0.0F;
      double velY = (double)0.0F;
      double velZ = (double)0.0F;
      double s = (double)0.5F;
      if (mc.field_1690.field_1867.method_1434()) {
         s = (double)1.0F;
      }

      boolean a = false;
      if (this.forward) {
         velX += forward.field_1352 * s * (double)((Number)this.speed.getValue()).floatValue();
         velZ += forward.field_1350 * s * (double)((Number)this.speed.getValue()).floatValue();
         a = true;
      }

      if (this.backward) {
         velX -= forward.field_1352 * s * (double)((Number)this.speed.getValue()).floatValue();
         velZ -= forward.field_1350 * s * (double)((Number)this.speed.getValue()).floatValue();
         a = true;
      }

      boolean b = false;
      if (this.right) {
         velX += right.field_1352 * s * (double)((Number)this.speed.getValue()).floatValue();
         velZ += right.field_1350 * s * (double)((Number)this.speed.getValue()).floatValue();
         b = true;
      }

      if (this.left) {
         velX -= right.field_1352 * s * (double)((Number)this.speed.getValue()).floatValue();
         velZ -= right.field_1350 * s * (double)((Number)this.speed.getValue()).floatValue();
         b = true;
      }

      if (a && b) {
         double diagonal = (double)1.0F / Math.sqrt((double)2.0F);
         velX *= diagonal;
         velZ *= diagonal;
      }

      if (this.up) {
         velY += s * (double)((Number)this.vertical.getValue()).floatValue();
      }

      if (this.down) {
         velY -= s * (double)((Number)this.vertical.getValue()).floatValue();
      }

      this.prevPos.set(this.pos);
      this.pos.set(this.pos.x + velX, this.pos.y + velY, this.pos.z + velZ);
   }

   @SubscribeEvent
   public void onKey(KeyboardEvent event) {
      if (!this.checkGuiMove()) {
         boolean cancel = true;
         if (mc.field_1690.field_1894.method_1417(event.getKey(), 0)) {
            this.forward = event.getAction() != 0;
            mc.field_1690.field_1894.method_23481(false);
         } else if (mc.field_1690.field_1881.method_1417(event.getKey(), 0)) {
            this.backward = event.getAction() != 0;
            mc.field_1690.field_1881.method_23481(false);
         } else if (mc.field_1690.field_1849.method_1417(event.getKey(), 0)) {
            this.right = event.getAction() != 0;
            mc.field_1690.field_1849.method_23481(false);
         } else if (mc.field_1690.field_1913.method_1417(event.getKey(), 0)) {
            this.left = event.getAction() != 0;
            mc.field_1690.field_1913.method_23481(false);
         } else if (mc.field_1690.field_1903.method_1417(event.getKey(), 0)) {
            this.up = event.getAction() != 0;
            mc.field_1690.field_1903.method_23481(false);
         } else if (mc.field_1690.field_1832.method_1417(event.getKey(), 0)) {
            this.down = event.getAction() != 0;
            mc.field_1690.field_1832.method_23481(false);
         } else {
            cancel = false;
         }

         if (cancel) {
            event.setCancelled(true);
         }

      }
   }

   @SubscribeEvent
   private void onMouseButton(MouseEvent event) {
      if (!this.checkGuiMove()) {
         boolean cancel = true;
         if (mc.field_1690.field_1894.method_1433(event.getButton())) {
            this.forward = !event.getType().equals(MouseEvent.Type.LIFT);
            mc.field_1690.field_1894.method_23481(false);
         } else if (mc.field_1690.field_1881.method_1433(event.getButton())) {
            this.backward = !event.getType().equals(MouseEvent.Type.LIFT);
            mc.field_1690.field_1881.method_23481(false);
         } else if (mc.field_1690.field_1849.method_1433(event.getButton())) {
            this.right = !event.getType().equals(MouseEvent.Type.LIFT);
            mc.field_1690.field_1849.method_23481(false);
         } else if (mc.field_1690.field_1913.method_1433(event.getButton())) {
            this.left = !event.getType().equals(MouseEvent.Type.LIFT);
            mc.field_1690.field_1913.method_23481(false);
         } else if (mc.field_1690.field_1903.method_1433(event.getButton())) {
            this.up = !event.getType().equals(MouseEvent.Type.LIFT);
            mc.field_1690.field_1903.method_23481(false);
         } else if (mc.field_1690.field_1832.method_1433(event.getButton())) {
            this.down = !event.getType().equals(MouseEvent.Type.LIFT);
            mc.field_1690.field_1832.method_23481(false);
         } else {
            cancel = false;
         }

         if (cancel) {
            event.setCancelled(true);
         }

      }
   }

   public void changeLookDirection(double deltaX, double deltaY) {
      this.prevYaw = this.yaw;
      this.prevPitch = this.pitch;
      this.yaw = (float)((double)this.yaw + deltaX);
      this.pitch = (float)((double)this.pitch + deltaY);
      this.pitch = class_3532.method_15363(this.pitch, -90.0F, 90.0F);
   }

   public double getX(float tickDelta) {
      return class_3532.method_16436((double)tickDelta, this.prevPos.x, this.pos.x);
   }

   public double getY(float tickDelta) {
      return class_3532.method_16436((double)tickDelta, this.prevPos.y, this.pos.y);
   }

   public double getZ(float tickDelta) {
      return class_3532.method_16436((double)tickDelta, this.prevPos.z, this.pos.z);
   }

   public double getYaw(float tickDelta) {
      return (double)class_3532.method_16439(tickDelta, this.prevYaw, this.yaw);
   }

   public double getPitch(float tickDelta) {
      return (double)class_3532.method_16439(tickDelta, this.prevPitch, this.pitch);
   }

   private boolean checkGuiMove() {
      return mc.field_1755 != null && (!NoSlow.INSTANCE.isEnabled() || !(Boolean)NoSlow.INSTANCE.guiMove.getValue());
   }

   public String getDescription() {
      return "Freecam: lets you move your camera around to get a better perspective";
   }
}
