package com.devon1337.RPG.NPC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.devon1337.RPG.Utils.Dialog;

public class NPC {

	public String Code, Name;
	public Faction faction;
	public static HashMap<UUID, Integer> Reputation = new HashMap<UUID, Integer>();
	public static HashMap<UUID, Dialog> CurDialog = new HashMap<UUID, Dialog>();
	public static ArrayList<Dialog> DIALOG_LIST = new ArrayList<Dialog>();
	
	public NPC(String Code, String Name) {
		this.Code = Code;
		this.Name = Name;
	}
	
	public String getCode() {
		return Code;
	}
	
	public String getName() {
		return Name;
	}
	
	public void setName(String Name) {
		this.Name = Name;
	}
	
	public NPC getNPC() {
		return this;
	}
	
	public static boolean hasReputation(Player player) {
		return Reputation.containsKey(player.getUniqueId());
	}
	
	public boolean hasDialog(Player player) {
		return CurDialog.containsKey(player.getUniqueId());
	}
	
	public int getReputation(Player player) {
		if(hasReputation(player)) {
			return Reputation.get(player.getUniqueId());
		} else {
			if(faction != null) {
				Reputation.put(player.getUniqueId(), faction.getReputation(player));
				return Reputation.get(player.getUniqueId());
			} else {
				Reputation.put(player.getUniqueId(), 0);
				return 0;
			}
		}
		
	}
	
	public Faction getFaction() {
		return faction;
	}
	
	public void setFaction(Faction faction) {
		this.faction = faction;
	}
	
	public static void setReputation(Player player, int rep) {
		
	}
	
	public Dialog getCurDialog(Player player) {
		return CurDialog.get(player.getUniqueId());
	}
	
	public void setCurDialog(Player player, Dialog dlog) {
		if(dlog != null) {
			CurDialog.remove(player.getUniqueId());
			CurDialog.put(player.getUniqueId(), dlog);
		}
	}
	
	public void initializePlayerReputation(Player player, int rep) {
		
	}
	
	
}
