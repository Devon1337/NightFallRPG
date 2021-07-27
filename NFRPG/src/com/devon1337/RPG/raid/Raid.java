package com.devon1337.RPG.raid;

import com.devon1337.RPG.Utils.Point;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Raid {
	String raidName = "test";
	NFRaid raidEnum;
	boolean enabled = false;
	@SuppressWarnings("unused")
	private final int PLAYER_LIMIT = 5;
	Point[] spawnPos = new Point[5];
	Player[] currentPlayers = new Player[5];

	public Raid(NFRaid raidEnum, String raidName) {
		this.raidEnum = raidEnum;
		this.raidName = raidName;
	}

	public void addSpawn(Point p1) {
		for (int i = 0; i < 5; i++) {
			if (this.currentPlayers[i] == null) {
				//p1.setName(String.valueOf(this.raidName) + i);
				this.spawnPos[i] = p1;
				Bukkit.broadcastMessage("Spawn point created! " + p1.getName());
				i = 5;
			}
		}
	}

	public void addPlayer(Player player) {
		for (int i = 0; i < 5; i++) {
			if (this.currentPlayers[i] == null) {
				this.currentPlayers[i] = player;
				player.sendMessage("You have been added to the raid: " + this.raidName + "!");
			}
		}
	}

	public NFRaid getEnum() {
		return this.raidEnum;
	}

	public Player[] getPlayers() {
		return this.currentPlayers;
	}

	public String getName() {
		return this.raidName;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}