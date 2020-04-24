package com.devon1337.RPG.Classes;

import java.util.ArrayList;
import java.util.Timer;

import org.bukkit.entity.Player;

import com.devon1337.RPG.Player.PlayerStats;

public class Druid {

	public static ArrayList<String> pList = new ArrayList<String>();
	public static ArrayList<Player> pSel = new ArrayList<Player>();
	
	public PlayerStats stats = new PlayerStats();
	
	public static Timer recur = new Timer();

	public void getUpgradeOptions() {

	}

	public int getPlayerAmount() { return pSel.size(); }
	
	public Player getPlayer(int i) { return pSel.get(i); }
	
	public ArrayList<Player> getPlayers() { return pSel; }
	
	public void addPlayer(Player player) { pList.add(player.getName()); pSel.add(player); }

	public void removePlayer(Player player) { pList.remove(player.getName()); pSel.remove(player); }

	public boolean playerExists(Player player) { return pList.contains(player.getName()); }
	
}