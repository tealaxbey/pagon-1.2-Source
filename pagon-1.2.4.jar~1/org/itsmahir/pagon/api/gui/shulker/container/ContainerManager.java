package org.itsmahir.pagon.api.gui.shulker.container;

import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_9276;
import net.minecraft.class_9288;
import net.minecraft.class_9323;
import net.minecraft.class_9334;

public class ContainerManager {
   private class_1799 containerStack;
   private class_9323 containerComponents;
   private String containerId;
   private boolean isContainerSupported;
   private ContainerType containerType;
   private ContainerContentsType containerContentsType;

   public ContainerManager(class_1799 containerStack) {
      this.containerStack = containerStack;
      this.containerId = containerStack.method_41409().method_55840();
      this.containerComponents = containerStack.method_57353();
      this.setContainerContentsType();
      this.setContainerType();
      this.setContainerSupported();
   }

   public class_1799 getDisplayStack() {
      if (!this.isContainerSupported) {
         return null;
      } else {
         Iterable<class_1799> itemIterable;
         switch (this.containerContentsType) {
            case CONTAINER:
               class_9288 containerComponent = (class_9288)this.containerStack.method_57824(class_9334.field_49622);
               itemIterable = containerComponent.method_59715();
               break;
            case BUNDLE:
               class_9276 bundleComponent = (class_9276)this.containerStack.method_57824(class_9334.field_49650);
               itemIterable = bundleComponent.method_59708();
               break;
            case NONE:
            default:
               return null;
         }

         return ItemStackManager.getDisplayStackFromIterable(itemIterable);
      }
   }

   public int getStackSize() {
      return this.containerStack.method_7947();
   }

   public float getCapacity() {
      switch (this.containerContentsType) {
         case CONTAINER -> {
            return this.getContainerCapacity();
         }
         case BUNDLE -> {
            return this.getBundleCapacity();
         }
         default -> {
            return 0.0F;
         }
      }
   }

   public ContainerType getContainerType() {
      return this.containerType;
   }

   private float getContainerCapacity() {
      return this.containerType != ContainerType.SHULKER_BOX ? 0.0F : this.getShulkerCapacity();
   }

   private float getShulkerCapacity() {
      class_9288 containerComponent = (class_9288)this.containerStack.method_57824(class_9334.field_49622);
      Iterable<class_1799> itemIterable = containerComponent.method_59714();
      float sumCapacity = 0.0F;

      for(class_1799 itemStack : itemIterable) {
         sumCapacity += (float)itemStack.method_7947() / (float)itemStack.method_7909().method_7882();
      }

      return sumCapacity / 27.0F;
   }

   private float getBundleCapacity() {
      class_9276 bundleComponent = (class_9276)this.containerStack.method_57824(class_9334.field_49650);
      return bundleComponent.method_57428().floatValue();
   }

   private void setContainerContentsType() {
      if (this.containerComponents.method_57832(class_9334.field_49622)) {
         this.containerContentsType = ContainerContentsType.CONTAINER;
      } else if (this.containerComponents.method_57832(class_9334.field_49650)) {
         this.containerContentsType = ContainerContentsType.BUNDLE;
      } else {
         this.containerContentsType = ContainerContentsType.NONE;
      }

   }

   private void setContainerType() {
      if (this.containerContentsType == ContainerContentsType.NONE) {
         this.containerType = ContainerType.NONE;
      } else if (this.containerId.matches("^minecraft:(.*_)?shulker_box$")) {
         this.containerType = ContainerType.SHULKER_BOX;
      } else if (this.containerStack.method_31574(class_1802.field_27023)) {
         this.containerType = ContainerType.BUNDLE;
      } else {
         this.containerType = ContainerType.OTHER;
      }

   }

   private void setContainerSupported() {
      switch (this.containerType) {
         case SHULKER_BOX:
            this.isContainerSupported = true;
            return;
         case BUNDLE:
            this.isContainerSupported = true;
            return;
         default:
            this.isContainerSupported = false;
      }
   }
}
