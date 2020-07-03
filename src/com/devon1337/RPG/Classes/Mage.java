package com.devon1337.RPG.Classes;

import java.util.ArrayList;
import java.util.Timer;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.devon1337.RPG.Player.PlayerStats;

public class Mage {
	
	public static ArrayList<String> pList = new ArrayList<String>();
	public static ArrayList<UUID> pSel = new ArrayList<UUID>();
	
	public PlayerStats stats = new PlayerStats();
	
	public static Timer recur = new Timer();

	public void getUpgradeOptions() {

	}

	public int getPlayerAmount() { return pSel.size(); }
	
	public Player getPlayer(int i) { return Bukkit.getPlayer(pSel.get(i)); }
	
	public ArrayList<UUID> getPlayers() { return pSel; }
	
	public void addPlayer(Player player) { pList.add(player.getName()); pSel.add(player.getUniqueId()); }

	public void removePlayer(Player player) { pList.remove(player.getName()); pSel.remove(player.getUniqueId()); }

	public boolean playerExists(Player player) { return pList.contains(player.getName()); }
	
}