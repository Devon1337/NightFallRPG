 package com.devon1337.RPG.Utils;
 
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.UUID;
 import org.bukkit.Bukkit;
 import org.bukkit.ChatColor;
 import org.bukkit.entity.Player;
 
 
 
 public class FriendsList
 {
   public static HashMap<UUID, ArrayList<UUID>> fList = new HashMap<>();
   
   private static final String FRIEND_ONLINE_MESSAGE = ChatColor.DARK_PURPLE + " has came online!", FRIEND_OFFLINE_MESSAGE = ChatColor.DARK_GRAY + " has went offline!";
 
   
   public static void addFriend(Player player, Player target) {
     if (fList.containsKey(player.getUniqueId())) {
       player.sendMessage("You have added " + target.getName());
       fList.put(player.getUniqueId(), generateAppendArrayList(getFriends(player), target));
     } else {
       player.sendMessage("You have added " + target.getName());
       fList.put(player.getUniqueId(), generateNewArrayList(target.getUniqueId()));
     } 
   }
   
   @SuppressWarnings("rawtypes")
public static void removeFriend(Player player, Player target) {
    if (isFriend(player, target)) {
       if (((ArrayList)fList.get(player.getUniqueId())).size() == 1) {
         fList.remove(player.getUniqueId());
       } else {
         fList.put(player.getUniqueId(), removeArrayList(getFriends(player), target));
       } 
     }
   }
   
   @SuppressWarnings("rawtypes")
public static ArrayList<UUID> getOnlineFriends(Player player) {
     ArrayList<UUID> temp = new ArrayList<>();
     if (fList.containsKey(player.getUniqueId())) {
       for (int i = 0; i < ((ArrayList)fList.get(player.getUniqueId())).size(); i++) {
         if (Bukkit.getPlayer(((ArrayList<UUID>)fList.get(player.getUniqueId())).get(i)) != null) {
           temp.add(((ArrayList<UUID>)fList.get(player.getUniqueId())).get(i));
         }
       } 
     }
    return temp;
   }
   
   public static ArrayList<UUID> removeArrayList(ArrayList<UUID> arg1, Player player) {
     ArrayList<UUID> players = arg1;
     players.remove(player.getUniqueId());
     return players;
   }
   
   public static ArrayList<UUID> generateNewArrayList(UUID player) {
     ArrayList<UUID> players = new ArrayList<>();
     players.add(player);
     return players;
   }
   
   public static ArrayList<UUID> generateAppendArrayList(ArrayList<UUID> arg1, Player player) {
     ArrayList<UUID> players = arg1;
     players.add(player.getUniqueId());
     return players;
   }
   
   @SuppressWarnings("rawtypes")
public static boolean isFriend(Player player, Player target) {
     if (fList.containsKey(player.getUniqueId()) && ((ArrayList)fList.get(player.getUniqueId())).contains(target.getUniqueId())) {
       return true;
     }
    return false;
   }
 
   
   public static ArrayList<UUID> getFriends(Player player) {
     if (fList.containsKey(player.getUniqueId())) {
       return fList.get(player.getUniqueId());
     }
     return null;
   }
 
   
   @SuppressWarnings("rawtypes")
public static void goOnline(Player player) {
     for (int i = 0; i < fList.size(); i++) {
       UUID target = (UUID)fList.keySet().toArray()[i];
       if (((ArrayList)fList.get(target)).contains(player.getUniqueId())) {
        Bukkit.getPlayer(target).sendMessage(ChatColor.GRAY + player.getName() + FRIEND_ONLINE_MESSAGE);
       }
     } 
   }
   
   @SuppressWarnings("rawtypes")
public static void goOffline(Player player) {
     for (int i = 0; i < fList.size(); i++) {
       UUID target = (UUID)fList.keySet().toArray()[i];
       if (((ArrayList)fList.get(target)).contains(player.getUniqueId()))
         Bukkit.getPlayer(target).sendMessage(ChatColor.GRAY + player.getName() + FRIEND_OFFLINE_MESSAGE); 
     } 
   }
 }