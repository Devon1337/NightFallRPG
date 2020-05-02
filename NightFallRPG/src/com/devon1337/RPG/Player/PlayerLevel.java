package com.devon1337.RPG.Player;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class PlayerLevel {
	
	public static HashMap<Player, Integer> pLevel = new HashMap<Player, Integer>();
	public static HashMap<Player, Double> pExperience = new HashMap<Player, Double>();
	public static HashMap<Player, Double> pXpRate = new HashMap<Player, Double>();
	
	public static int getLevel(Player player) { return pLevel.get(player); }
	public static void setLevel(Player player, int level) { pLevel.remove(player); pLevel.put(player, level); }
	
	public static double getXP(Player player) { return pExperience.get(player); }
	public static void setXP(Player player, double XP) { pExperience.remove(player); pExperience.put(player, XP); }
	
	public static double getXPRate(Player player) { return pXpRate.get(player); }
	public static void setXPRate(Player player, double Rate) { pXpRate.remove(player); pXpRate.put(player, Rate); }
	
	public static boolean exists(Player player) { 
		if(pLevel.containsKey(player)) {
			return true;
		}
		return false;
	}
	
	
	
}
