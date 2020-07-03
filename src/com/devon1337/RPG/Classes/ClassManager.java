package com.devon1337.RPG.Classes;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;

public class ClassManager {

	public static Rogue rogue = new Rogue();
	public static Druid druid = new Druid();
	public static Warrior warrior = new Warrior();
	public static Mage mage = new Mage();
	
	public boolean isPlayerInTeam(Player player, boolean remove) {
		
		ArrayList<UUID> holder = rogue.getPlayers();
		
		for(UUID p : holder) {
			if(p.equals(player.getUniqueId())) {
				if(remove) {
					rogue.removePlayer(player);
				}
				return true;
			}
		}
		
		holder = druid.getPlayers();
		for(UUID p : holder) {
			if(p.equals(player.getUniqueId())) {
				if(remove) {
					druid.removePlayer(player);
				}
				return true;
			}
		}
		
		holder = warrior.getPlayers();
		for(UUID p : holder) {
			if(p.equals(player.getUniqueId())) {
				if(remove) {
					warrior.removePlayer(player);
				}
				return true;
			}
		}
		
		holder = mage.getPlayers();
		for(UUID p : holder) {
			if(p.equals(player.getUniqueId())) {
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