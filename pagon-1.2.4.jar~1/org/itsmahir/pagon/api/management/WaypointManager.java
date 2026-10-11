package org.itsmahir.pagon.api.management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import org.itsmahir.pagon.api.config.ISavable;

public class WaypointManager implements ISavable {
   public static WaypointManager INSTANCE;
   public CopyOnWriteArrayList<WayPoint> wayPoints = new CopyOnWriteArrayList();

   public WaypointManager() {
      INSTANCE = this;
      SavableManager.INSTANCE.getSavables().add(this);
   }

   public void removeWayPoint(WayPoint macro) {
      this.wayPoints.remove(macro);
   }

   public CopyOnWriteArrayList<WayPoint> getWayPoints() {
      return this.wayPoints;
   }

   public WayPoint getWayPointByName(String name) {
      for(WayPoint wayPoint : this.getWayPoints()) {
         if (wayPoint.name.equalsIgnoreCase(name)) {
            return wayPoint;
         }
      }

      return null;
   }

   public void load(Map<String, Object> objects) {
      if (objects.get("waypoints") != null) {
         for(String s : (List)objects.get("waypoints")) {
            try {
               String[] line = s.split(":");
               String x = line[0];
               String y = line[1];
               String z = line[2];
               String name = line[3];
               String server = line[4];
               String dimension = line.length == 6 ? line[5] : "overworld";
               this.addWayPoint(new WayPoint(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(z), name, server, dimension));
            } catch (Exception var12) {
            }
         }
      }

   }

   public void addWayPoint(WayPoint wp) {
      if (!this.wayPoints.contains(wp)) {
         this.wayPoints.add(wp);
      }

   }

   public Map<String, Object> save() {
      Map<String, Object> toSave = new HashMap();
      List<String> waypointList = new ArrayList();

      for(WayPoint waypoint : this.wayPoints) {
         waypointList.add(waypoint.x + ":" + waypoint.y + ":" + waypoint.z + ":" + waypoint.name + ":" + waypoint.server + ":" + waypoint.dimension);
      }

      toSave.put("waypoints", waypointList);
      return toSave;
   }

   public WayPoint getClosestMatchingWaypoint(String text) {
      WayPoint bestWaypoint = null;
      double lowestLength = Double.MAX_VALUE;

      for(WayPoint loc : this.getWayPoints()) {
         if (Objects.equals(text, "")) {
            return loc;
         }

         if (text.equals(loc.toString())) {
            return loc;
         }

         if (text.length() <= loc.toString().length() && loc.toString().startsWith(text) && lowestLength > (double)loc.toString().length()) {
            bestWaypoint = loc;
            lowestLength = (double)loc.toString().length();
         }
      }

      return bestWaypoint;
   }

   public String getFileName() {
      return "waypoints.cfg";
   }

   public String getDirName() {
      return "misc";
   }

   public static class WayPoint {
      private int x;
      private int y;
      private int z;
      private String name;
      private String server;
      private String dimension;

      public WayPoint(int x, int y, int z, String name, String server, String dimension) {
         this.x = x;
         this.y = y;
         this.z = z;
         this.name = name;
         this.server = server;
         this.dimension = dimension;
      }

      public int getX() {
         return this.x;
      }

      public int getY() {
         return this.y;
      }

      public int getZ() {
         return this.z;
      }

      public String getName() {
         return this.name;
      }

      public String getServer() {
         return this.server;
      }

      public String getDimension() {
         return this.dimension;
      }

      public void setX(int x) {
         this.x = x;
      }

      public void setY(int y) {
         this.y = y;
      }

      public void setZ(int z) {
         this.z = z;
      }

      public void setName(String name) {
         this.name = name;
      }

      public void setServer(String server) {
         this.server = server;
      }

      public void setDimension(String dimension) {
         this.dimension = dimension;
      }
   }
}
