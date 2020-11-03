 package com.devon1337.RPG.Utils;
 
 import org.bukkit.entity.Player;
 
 public class MOTD
 {
   public static final String[] motday = new String[] { "Welcome %player!", "You have %n friends online!" };
   
   public static void printMOTD(Player player) {
     String temp = "";
     boolean changed = false;
     
     for (int i = 0; i < motday.length; i++) {
       if (motday[i].contains("%player")) {
         temp = motday[i].replaceAll("%player", player.getName());
         changed = true;
       } 
       
       if (motday[i].contains("%n") && FriendsList.getOnlineFriends(player) != null) {
         temp = motday[i].replaceAll("%n", (new StringBuilder(String.valueOf(FriendsList.getOnlineFriends(player).size()))).toString());
         changed = true;
       } else if (motday[i].contains("%n") && FriendsList.getOnlineFriends(player) == null) {
         temp = motday[i].replaceAll("%n", "0");
         changed = true;
       } 
       
       if (!changed) {
         temp = motday[i];
       }
 
       
       player.sendMessage(temp);
     } 
   }
 }