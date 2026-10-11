package org.itsmahir.pagon.api.utils.targeting;

import com.google.common.collect.Streams;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import org.itsmahir.pagon.api.management.FriendManager;
import org.itsmahir.pagon.api.utils.world.HoleUtils;
import org.itsmahir.pagon.api.wrapper.IMinecraft;

public class TargetUtils implements IMinecraft {
   public static class_1309 getTarget(double targetRange) {
      return (class_1309)Streams.stream(mc.field_1687.method_18112()).filter(Objects::nonNull).filter((entity) -> entity instanceof class_1657).filter(TargetUtils::isAlive).filter((entity) -> entity.method_5628() != mc.field_1724.method_5628()).filter((entity) -> !FriendManager.INSTANCE.isFriend(entity)).filter((entity) -> mc.field_1724.method_19538().method_1022(entity.method_19538()) <= targetRange).min(Comparator.comparingDouble((entity) -> mc.field_1724.method_19538().method_1022(entity.method_19538()))).orElse((Object)null);
   }

   public static class_1309 getTargetFromCrystal(double targetRange, class_1511 crystal) {
      return (class_1309)Streams.stream(mc.field_1687.method_18112()).filter(Objects::nonNull).filter((entity) -> entity instanceof class_1657).filter(TargetUtils::isAlive).filter((entity) -> entity.method_5628() != mc.field_1724.method_5628()).filter((entity) -> !FriendManager.INSTANCE.isFriend(entity)).filter((entity) -> crystal.method_19538().method_1022(entity.method_19538()) <= targetRange).min(Comparator.comparingDouble((entity) -> crystal.method_19538().method_1022(entity.method_19538()))).orElse((Object)null);
   }

   public static Stream<class_1297> getTargets(double targetRange) {
      return Streams.stream(mc.field_1687.method_18112()).filter(Objects::nonNull).filter((entity) -> entity instanceof class_1657).filter(TargetUtils::isAlive).filter((entity) -> entity.method_5628() != mc.field_1724.method_5628()).filter((entity) -> !FriendManager.INSTANCE.isFriend(entity)).filter((entity) -> mc.field_1724.method_19538().method_1022(entity.method_19538()) <= targetRange);
   }

   public static Stream<class_1297> getPlayers() {
      return Streams.stream(mc.field_1687.method_18112()).filter(Objects::nonNull).filter((entity) -> entity instanceof class_1657).filter((entity) -> entity.method_5628() != mc.field_1724.method_5628());
   }

   public static class_1309 getTargetAnvil(double targetRange) {
      return (class_1309)Streams.stream(mc.field_1687.method_18112()).filter(Objects::nonNull).filter((entity) -> entity instanceof class_1657).filter(TargetUtils::isAlive).filter((entity) -> entity.method_5628() != mc.field_1724.method_5628()).filter((entity) -> !FriendManager.INSTANCE.isFriend(entity)).filter((entity) -> mc.field_1724.method_19538().method_1022(entity.method_19538()) <= targetRange).filter((entity) -> mc.field_1724.method_23318() > entity.method_23318()).min(Comparator.comparingDouble((entity) -> mc.field_1724.method_19538().method_1022(entity.method_19538()))).orElse((Object)null);
   }

   public static class_1309 getTargetHolefill(double targetRange) {
      return (class_1309)Streams.stream(mc.field_1687.method_18112()).filter(Objects::nonNull).filter((entity) -> entity instanceof class_1657).filter(TargetUtils::isAlive).filter((entity) -> entity.method_5628() != mc.field_1724.method_5628()).filter((entity) -> !FriendManager.INSTANCE.isFriend(entity)).filter((entity) -> mc.field_1724.method_19538().method_1022(entity.method_19538()) <= targetRange).filter((entity) -> !HoleUtils.isInBlock((class_1657)entity)).filter((entity) -> !HoleUtils.isHole(entity.method_24515())).min(Comparator.comparingDouble((entity) -> mc.field_1724.method_19538().method_1022(entity.method_19538()))).orElse((Object)null);
   }

   public static HoleUtils.Hole getTargetHole(double targetRange) {
      return (HoleUtils.Hole)HoleUtils.getHoles(targetRange, mc.field_1724.method_24515(), false).stream().filter((hole) -> mc.field_1724.method_24515().method_10262(hole.pos1) <= targetRange).min(Comparator.comparingDouble((hole) -> mc.field_1724.method_24515().method_10262(hole.pos1))).orElse((Object)null);
   }

   public static HoleUtils.Hole getTargetHoleVec3D(double targetRange, boolean self, boolean doubles) {
      return (HoleUtils.Hole)HoleUtils.getHolesHolesnap(targetRange, mc.field_1724.method_24515(), doubles, self).stream().filter((hole) -> HoleUtils.distanceTo(hole) <= targetRange).min(Comparator.comparingDouble(HoleUtils::distanceTo)).orElse((Object)null);
   }

   public static boolean isAlive(class_1297 entity) {
      return isLiving(entity) && entity.method_5805() && ((class_1309)entity).method_6032() > 0.0F;
   }

   public static boolean isLiving(class_1297 entity) {
      return entity instanceof class_1309;
   }
}
