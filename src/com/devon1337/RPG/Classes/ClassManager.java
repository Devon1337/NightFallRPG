package com.devon1337.RPG.Classes;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;

public class ClassManager {

	public static Rogue rogue = new Rogue();
	public static Druid druid = new Druid();
	public static Warrior warrior = new Warrior();
	public static Mage mage = new Mage();
	
	public boolean isPlayerInTeam(Player player, boolean remove) {
		
		ArrayList<Player> holder = rogue.getPlayers();
		
		for(Player p : holder) {
			if(p.equals(player)) {
				if(remove) {
					rogue.removePlayer(player);
				}
				return true;
			}
		}
		
		holder = druid.getPlayers();
		for(Player p : holder) {
			if(p.equals(player)) {
				if(remove) {
					druid.removePlayer(player);
				}
				return true;
			}
		}
		
		holder = warrior.getPlayers();
		for(Player p : holder) {
			if(p.equals(player)) {
				if(remove) {
					warrior.removePlayer(player);
				}
				return true;
			}
		}
		
		holder = mage.getPlayers();
		for(Player p : holder) {
			if(p.equals(player)) {
				if(remove) {
					mage.removePlayer(player);
				}
				return true;
			}
		}
		
		return false;
		
	}
	
	public static NFClasses getTeam(Player player) {
		if(rogue.playerExists(player)) {
			return NFClasses.ROGUE;
		}
		if(druid.playerExists(player)) {
			return NFClasses.DRUID;
		}
		if(warrior.playerExists(player)) {
			return NFClasses.WARRIOR;
		}
		if(mage.playerExists(player)) {
			return NFClasses.MAGE;
		}
		
		return null;
		
	}
	
}