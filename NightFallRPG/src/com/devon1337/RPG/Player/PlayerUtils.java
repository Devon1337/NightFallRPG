package com.devon1337.RPG.Player;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class PlayerUtils {
	
	public static HashMap<String, String> buildKeys = new HashMap<String, String>();
	// TODO: {class}{race}{passiveabilities}{activeabilities}
	
	
	public int level, xp;
	
	public void generateBuildKey(Player player) {
		buildKeys.put(player.getName(), "000000");
	}
	
	// UNUSED
	/*
	public void getLevel() {
		
	}
	
	public void setLevel() {
		
	}
	
	public void getXP() { 
		
	}
	
	public void setXP() {
		
	}
	*/
	
	public String getBuildKey(Player player) { return buildKeys.get(player.getName()); }
	
	public void setBuildKey(String newKey, Player player) { buildKeys.put(player.getName(), newKey); }
}