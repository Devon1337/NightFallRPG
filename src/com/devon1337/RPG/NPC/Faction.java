package com.devon1337.RPG.NPC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.devon1337.RPG.Classes.ClassManager;
import com.devon1337.RPG.Debugging.Logging;

public class Faction {

	public String Code, Name;
	public int[] baseReps = new int[5];
	public static ArrayList<NPC> NPC_LIST = new ArrayList<NPC>();
	public HashMap<UUID, Integer> fReputation = new HashMap<UUID, Integer>();
	
	
	public Faction(String Code, String Name, int mRep, int dRep, int wRep, int rRep, int DepRep) {
		this.Code = Code;
		this.Name = Name;
		baseReps[0] = mRep;
		baseReps[1] = dRep;
		baseReps[2] = wRep;
		baseReps[3] = rRep;
		baseReps[4] = DepRep;
	}
	
	public void linkFaction(NPC npc) {
		npc.setFaction(this);
		Logging.OutputToConsole("Linking: " + npc.getCode() + " -> " + this.Code);
	}
	
	public void initializePlayer(Player player) {
		Logging.OutputToConsole("Creating Player Progress: " + player.getName() + "base reps: " + baseReps[0] + ", " + baseReps[1] + ", " + baseReps[2] + ", " + baseReps[3] + ", " + baseReps[4]);
		switch(ClassManager.getTeam(player)) {
		case MAGE:
			fReputation.put(player.getUniqueId(), baseReps[0]);
			break;
		case WARRIOR:
			fReputation.put(player.getUniqueId(), baseReps[2]);
			break;
		case ROGUE:
			fReputation.put(player.getUniqueId(), baseReps[3]);
			break;
		case DRUID:
			fReputation.put(player.getUniqueId(), baseReps[1]);
			break;
		case DEPRIVED:
			fReputation.put(player.getUniqueId(), baseReps[4]);
			break;
		}
	}
	
	public static void addNPC(NPC npc) {
		NPC_LIST.add(npc);
	}
	
	
	public boolean hasReputation(Player player) {
		Logging.OutputToConsole("Player: " + player + " contained in fRep: " + fReputation.containsKey(player.getUniqueId()));
		return fReputation.containsKey(player.getUniqueId());
	}
	
	public int getReputation(Player player) {
		if(hasReputation(player)) {
			return fReputation.get(player.getUniqueId());
		} else {
			initializePlayer(player);
			return fReputation.get(player.getUniqueId());
		}
	}
	
	public static void setReputation(Player player, int rep) {
		
	}
	
	
	
}
