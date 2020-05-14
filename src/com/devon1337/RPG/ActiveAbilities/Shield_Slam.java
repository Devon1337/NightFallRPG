package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.Utils.AOEMapping.AreaMap;

public class Shield_Slam {

	public final int COOLDOWN_AMOUNT = 10;
	public final int CLASS_TYPE = 2; // -- Warrior
	public final Material ITEM = Material.YELLOW_DYE;
	public final Material CD_ITEM = Material.DIAMOND_SHOVEL;
	
	public static HashMap<Player, Integer> pCooldowns = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> pStun = new HashMap<Player, Integer>();
	public static HashMap<Player, Float> pOSpeed = new HashMap<Player, Float>();
	
	public void use(Player player) {
		if(!pCooldowns.containsKey(player)) {
			pCooldowns.put(player, COOLDOWN_AMOUNT);
			@SuppressWarnings("unused")
			AreaMap aoe = new AreaMap(Bukkit.getWorld("world"), player.getLocation(), 6, "You were sapped by " + player.getName(), player, true, 3);
			
		} else {
			player.sendMessage(ChatColor.DARK_RED + "Shield Slam has " + pCooldowns.get(player) + " seconds left!");
		}
	}
	
	public void loadStunList(ArrayList<Player> players) {
		
		for(int i = 0; i < players.size(); i++) {
			pStun.put(players.get(i), 3);
			pOSpeed.put(players.get(0), players.get(0).getWalkSpeed());
			players.get(0).setWalkSpeed(0);
			players.get(0).sendMessage(ChatColor.DARK_RED + "Stunned!");
			
		}
		
	}
	
	public void updateStuns() {
		for (Map.Entry<Player, Integer> entry : pStun.entrySet()) {
			System.out.println("Shield Slam Stun: " + entry.getKey() + ": " + entry.getValue() + " seconds -> "
					+ (entry.getValue() - 1) + " seconds.");
			pStun.replace(entry.getKey(), entry.getValue() - 1);
			if(entry.getValue() < 0) {
				pStun.remove(entry.getKey());
				entry.getKey().setWalkSpeed(pOSpeed.get(entry.getKey()));
			}
		}
	}
	
	public void updateCooldowns() {
		updateStuns();
		for(Map.Entry<Player, Integer> entry : pCooldowns.entrySet()) {
			System.out.println("Shield Slam: " + entry.getKey() + ": " + entry.getValue() + " seconds -> " + (entry.getValue()-1) + " seconds.");
			pCooldowns.remove(entry.getKey());
			pCooldowns.put(entry.getKey(), entry.getValue()-1);
			if(entry.getValue()-1 < 0) {
				pCooldowns.remove(entry.getKey());
			}
		}
	}
	
	public int getCooldown(Player player) {
		if(pCooldowns.containsKey(player)) {
		return pCooldowns.get(player);
		}
		
		return 0;
	}
	
	public void setCooldown(Player player, int cooldown_amount) {
		if(pCooldowns.containsKey(player)) {
			pCooldowns.remove(player);
		}
		pCooldowns.put(player, cooldown_amount);
	}
	
	
	
}
