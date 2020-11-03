package com.devon1337.RPG.raid;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class RMEngine {

	// Current Clients
	public static ArrayList<RMEngine> sessionClients = new ArrayList<RMEngine>();
	
	// Object Details
	public Player player;
	public NFRaid raid;
	public ArrayList<NFRaidDebug> debug = new ArrayList<NFRaidDebug>();
	
	
	// Player
	// NFRaid
	// NFRaid Debug
	
	// Append existing Raid AND start session
	public RMEngine(Player player, NFRaid raid) {
		this.player = player;
		this.raid = raid;
		sessionClients.add(this);
	}
	
	//Getters/Setters
	public Player getPlayer() {
		return this.player;
	}
	
	public NFRaid getRaid() {
		return this.raid;
	}
	
	// Closing The Session
	public void closeSession(Player player) {
		for(RMEngine e : sessionClients) {
			if(player == e.getPlayer()) {
				sessionClients.remove(e);
			}
		}
		
	}
	
	public void forceCloseSession(NFRaid raid) {
		for(RMEngine e : sessionClients) {
			if(raid == e.getRaid()) {
				sessionClients.remove(e);
			}
		}
	}
	
	
	
}
