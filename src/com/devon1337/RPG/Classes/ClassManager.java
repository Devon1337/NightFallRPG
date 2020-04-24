package com.devon1337.RPG.Classes;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class ClassManager {

	public Rogue rogue = new Rogue();
	public Druid druid = new Druid();
	public Warrior warrior = new Warrior();
	public Mage mage = new Mage();
	
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
	
}