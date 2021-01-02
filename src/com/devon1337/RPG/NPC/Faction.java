package com.devon1337.RPG.NPC;

import java.util.ArrayList;
import org.bukkit.entity.Player;

import lombok.Getter;

public class Faction {
	
	public String Name;
	
	@Getter
	public AllFactions faction;
	public ArrayList<NPC> NPC_LIST = new ArrayList<>();

	public Faction(AllFactions faction, String Name) {
		this.faction = faction;
		this.Name = Name;
	}

	public void linkFaction(NPC npc) {
		npc.setFaction(this);
	}

	public void addNPC(NPC npc) {
		this.NPC_LIST.add(npc);
	}

	public ArrayList<NPC> getNPC() {
		ArrayList<NPC> temp = new ArrayList<>();

		for (int i = 0; i < this.NPC_LIST.size(); i++) {
			temp.add(this.NPC_LIST.get(i));
		}

		return temp;
	}

	public static void setReputation(Player player, int rep) {
	}
}