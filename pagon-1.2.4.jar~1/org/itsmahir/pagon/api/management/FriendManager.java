package org.itsmahir.pagon.api.management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.minecraft.class_1297;
import org.itsmahir.pagon.api.config.ISavable;
import org.itsmahir.pagon.api.friends.Friend;

public class FriendManager implements ISavable {
   public static FriendManager INSTANCE;
   List<Friend> friends = new ArrayList();

   public FriendManager() {
      SavableManager.INSTANCE.getSavables().add(this);
   }

   public List<Friend> getFriends() {
      return this.friends;
   }

   public boolean isFriend(class_1297 entity) {
      Friend testFriend = new Friend(entity.method_5477().getString());
      return this.friends.contains(testFriend);
   }

   public void addFriend(class_1297 entity) {
      Friend friend = new Friend(entity.method_5477().getString());
      this.friends.add(friend);
   }

   public void removeFriend(class_1297 entity) {
      Friend friend = new Friend(entity.method_5477().getString());
      this.friends.remove(friend);
   }

   public void load(Map<String, Object> objects) {
      if (objects.get("friends") != null) {
         for(String s : (List)objects.get("friends")) {
            this.friends.add(new Friend(s));
         }
      }

   }

   public Friend getClosestMatchingFriend(String text) {
      Friend bestFriend = null;
      double lowestLength = Double.MAX_VALUE;

      for(Friend friend : this.getFriends()) {
         if (Objects.equals(text, "")) {
            return friend;
         }

         if (text.equals(friend.toString())) {
            return friend;
         }

         if (text.length() <= friend.toString().length() && friend.toString().startsWith(text) && lowestLength > (double)friend.toString().length()) {
            bestFriend = friend;
            lowestLength = (double)friend.toString().length();
         }
      }

      return bestFriend;
   }

   public Map<String, Object> save() {
      Map<String, Object> toSave = new HashMap();
      List<String> friendList = new ArrayList();

      for(Friend friend : this.friends) {
         friendList.add(friend.toString());
      }

      toSave.put("friends", friendList);
      return toSave;
   }

   public String getFileName() {
      return "friends.cfg";
   }

   public String getDirName() {
      return "misc";
   }
}
