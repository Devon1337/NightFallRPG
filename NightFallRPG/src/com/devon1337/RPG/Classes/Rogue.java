package com.devon1337.RPG.Classes;

import java.util.ArrayList;
import java.util.Timer;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import com.devon1337.RPG.Player.PlayerStats;
//import org.bukkit.potion.PotionEffect;
//import org.bukkit.potion.PotionEffectType;


public class Rogue {

	public static ArrayList<String> pList = new ArrayList<String>();
	public static ArrayList<Player> pSel = new ArrayList<Player>();
	
	public PlayerStats stats = new PlayerStats();
	
	public static Timer recur = new Timer();

	public void getUpgradeOptions() {

	}

	public int getPlayerAmount() { return pSel.size(); }
	
	public Player getPlayer(int i) { return pSel.get(i); }
	
	public ArrayList<Player> getPlayers() { return pSel; }
	
	public void addPlayer(Player player) { pList.add(player.getName()); pSel.add(player); addRoguePassive(player); }

	public void removePlayer(Player player) { pList.remove(player.getName()); pSel.remove(player); }

	public boolean playerExists(Player player) { return pList.contains(player.getName()); }

	public void addRoguePassive(Player player) {
		stats.storeMoveSpeed(player, player.getWalkSpeed());
		player.setWalkSpeed(getAdjustedSpeed(player) * 1.10f);
	}
	
	public void removeRoguePassive(Player player) {
		player.setWalkSpeed(stats.getMoveSpeed(player));
	}
	
	public void roguePassive() {
		for(int i = 0; i < getPlayerAmount(); i++) {
			stats.storeMoveSpeed(getPlayer(i), getPlayer(i).getWalkSpeed());
			getPlayer(i).setWalkSpeed(getAdjustedSpeed(getPlayer(i)) * 1.10f);
		}
		
		
	}
	
	public float getAdjustedSpeed(Player player) {
		if(player.getPotionEffect(PotionEffectType.SPEED) != null) {
			return player.getWalkSpeed() * (1.05f * (player.getPotionEffect(PotionEffectType.SPEED).getAmplifier()+1));
		} else {
			return player.getWalkSpeed();
		}
	}
	
	public void roguePassiveReset() {
		for(int i = 0; i < getPlayerAmount(); i++) {
			if(stats.mapContains(getPlayer(i))) {
			getPlayer(i).setWalkSpeed(stats.getMoveSpeed(getPlayer(i)));
			}
		}
	}
	
}