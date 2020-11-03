 package com.devon1337.RPG.Player;
 
 import java.util.HashMap;
 import org.bukkit.entity.Player;
 
 
 public class PlayerUtils
 {
   public static HashMap<String, String> buildKeys = new HashMap<>();
   
   public int level;
   
   public int xp;
   
   public void generateBuildKey(Player player) {
     buildKeys.put(player.getName(), "000000");
   }
 
   public String getBuildKey(Player player) {
     return buildKeys.get(player.getName());
   } public void setBuildKey(String newKey, Player player) {
     buildKeys.put(player.getName(), newKey);
   }
 }
