package org.itsmahir.pagon.api.event.eventbus;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.itsmahir.pagon.api.event.Event;
import org.itsmahir.pagon.api.event.EventData;

public class EventManager {
   private final Map<Class<? extends Event>, CopyOnWriteArrayList<EventData>> REGISTRY_MAP = new HashMap();

   public void register(Object o) {
      for(Method method : o.getClass().getDeclaredMethods()) {
         if (!this.isMethodBad(method)) {
            this.register(method, o);
         }
      }

   }

   public void register(Object o, Class<? extends Event> clazz) {
      for(Method method : o.getClass().getDeclaredMethods()) {
         if (!this.isMethodBad(method, clazz)) {
            this.register(method, o);
         }
      }

   }

   private void register(Method method, Object o) {
      Class<?> clazz = method.getParameterTypes()[0];
      final EventData methodData = new EventData(o, method, ((SubscribeEvent)method.getAnnotation(SubscribeEvent.class)).value());
      if (!methodData.target.isAccessible()) {
         methodData.target.setAccessible(true);
      }

      if (this.REGISTRY_MAP.containsKey(clazz)) {
         if (!((CopyOnWriteArrayList)this.REGISTRY_MAP.get(clazz)).contains(methodData)) {
            ((CopyOnWriteArrayList)this.REGISTRY_MAP.get(clazz)).add(methodData);
            this.sortListValue(clazz);
         }
      } else {
         this.REGISTRY_MAP.put(clazz, new CopyOnWriteArrayList<EventData>() {
            {
               this.add(methodData);
            }
         });
      }

   }

   public void unregister(Object o) {
      for(CopyOnWriteArrayList<EventData> flexibalArray : this.REGISTRY_MAP.values()) {
         flexibalArray.removeIf((methodData) -> methodData.source.equals(o));
      }

      this.cleanMap(true);
   }

   public void unregister(Object o, Class<? extends Event> clazz) {
      if (this.REGISTRY_MAP.containsKey(clazz)) {
         for(EventData methodData : (CopyOnWriteArrayList)this.REGISTRY_MAP.get(clazz)) {
            if (methodData.source.equals(o)) {
               ((CopyOnWriteArrayList)this.REGISTRY_MAP.get(clazz)).remove(methodData);
            }
         }

         this.cleanMap(true);
      }

   }

   public void cleanMap(boolean b) {
      Iterator<Map.Entry<Class<? extends Event>, CopyOnWriteArrayList<EventData>>> iterator = this.REGISTRY_MAP.entrySet().iterator();

      while(iterator.hasNext()) {
         if (!b || ((CopyOnWriteArrayList)((Map.Entry)iterator.next()).getValue()).isEmpty()) {
            iterator.remove();
         }
      }

   }

   public void removeEnty(Class<? extends Event> clazz) {
      Iterator<Map.Entry<Class<? extends Event>, CopyOnWriteArrayList<EventData>>> iterator = this.REGISTRY_MAP.entrySet().iterator();

      while(iterator.hasNext()) {
         if (((Class)((Map.Entry)iterator.next()).getKey()).equals(clazz)) {
            iterator.remove();
            break;
         }
      }

   }

   private void sortListValue(Class<? extends Event> clazz) {
      CopyOnWriteArrayList<EventData> flexibleArray = new CopyOnWriteArrayList();

      for(byte b : Priority.VALUE_ARRAY) {
         for(EventData methodData : (CopyOnWriteArrayList)this.REGISTRY_MAP.get(clazz)) {
            if (methodData.priority == b) {
               flexibleArray.add(methodData);
            }
         }
      }

      this.REGISTRY_MAP.put(clazz, flexibleArray);
   }

   private boolean isMethodBad(Method method) {
      return method.getParameterTypes().length != 1 || !method.isAnnotationPresent(SubscribeEvent.class);
   }

   private boolean isMethodBad(Method method, Class<? extends Event> clazz) {
      return this.isMethodBad(method) || method.getParameterTypes()[0].equals(clazz);
   }

   public CopyOnWriteArrayList<EventData> get(Class<? extends Event> clazz) {
      return (CopyOnWriteArrayList)this.REGISTRY_MAP.get(clazz);
   }

   public void shutdown() {
      this.REGISTRY_MAP.clear();
   }
}
