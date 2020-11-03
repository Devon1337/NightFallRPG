 package com.devon1337.RPG.Player;
 
 import java.util.HashMap;
 import org.bukkit.entity.Player;
 
 
 public class PlayerLevel
 {
   public static HashMap<Player, Integer> pLevel = new HashMap<>();
   public static HashMap<Player, Double> pExperience = new HashMap<>();
   public static HashMap<Player, Double> pXpRate = new HashMap<>();
   
   public static int getLevel(Player player) { return ((Integer)pLevel.get(player)).intValue(); } public static void setLevel(Player player, int level) {
     pLevel.remove(player); pLevel.put(player, Integer.valueOf(level));
   }
   public static double getXP(Player player) { return ((Double)pExperience.get(player)).doubleValue(); } public static void setXP(Player player, double XP) {
     pExperience.remove(player); pExperience.put(player, Double.valueOf(XP));
   }
   public static double getXPRate(Player player) { return ((Double)pXpRate.get(player)).doubleValue(); } public static void setXPRate(Player player, double Rate) {
     pXpRate.remove(player); pXpRate.put(player, Double.valueOf(Rate));
   }
   public static boolean exists(Player player) {
     if (pLevel.containsKey(player)) {
       return true;
     }
     return false;
   }
 }
