package com.devon1337.RPG.NPC;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import com.devon1337.RPG.Debugging.Logging;

public class WorldController {

	public static ArrayList<Faction> Factions = new ArrayList<Faction>();
	public static ArrayList<NPC> StandAloneNPC = new ArrayList<NPC>();
	
	public static void initializeFaction(Faction faction) {
		Logging.OutputToConsole("Initializing Faction: " + faction.Code + "!");
		Factions.add(faction);
	}
	
	public static void initializeNPC(NPC npc, Faction faction) {	
		if(faction != null && getFaction(faction.Code) != null) {
			Faction.addNPC(npc);
			Faction tempFac = faction;
			tempFac.linkFaction(npc);
		} else if(faction == null) {
			StandAloneNPC.add(npc);
		}
	}
	
	public static Faction getFaction(String Code) {
		for(int i = 0; i < Factions.size(); i++) {
			if(Factions.get(i).Code.equals(Code)) {
				return Factions.get(i);
			}
		}
		
		return null;
	}
	
	@SuppressWarnings("static-access")
	public static void displayAllFactionRep(Player player) {
		for(int i = 0; i < Factions.size(); i++) {
			Logging.OutputToConsole("Running loop on: " + Factions.get(i).Name);
			if(Factions.get(i).hasReputation(player)) {
				player.sendMessage("Faction: " + Factions.get(i).Name + " Reputation: " + Factions.get(i).getReputation(player));
			} else {
				Factions.get(i).initializePlayer(player);
				player.sendMessage("Faction: " + Factions.get(i).Name + " Reputation: " + Factions.get(i).getReputation(player));
			}
		}
	}
}
