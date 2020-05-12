package com.devon1337.RPG.ActiveAbilities;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.Utils.AOEMapping.AreaMap;

public class Blood_Shield {

	public final int COOLDOWN_AMOUNT = 10;
	public final int CLASS_TYPE = 3; // -- Mage
	public final Material ITEM = Material.PINK_DYE;
	public final Material CD_ITEM = Material.DIAMOND_SHOVEL;
	
	public static HashMap<Player, Integer> pCooldowns = new HashMap<Player, Integer>();
	
	
	public void use(Player player) {
		if(!pCooldowns.containsKey(player)) {
			pCooldowns.put(player, COOLDOWN_AMOUNT);
			@SuppressWarnings("unused")
			AreaMap aoe = new AreaMap(Bukkit.getWorld("world"), player.getLocation(), 6, "You were sapped by " + player.getName(), player);
			
		} else {
			player.sendMessage(ChatColor.DARK_RED + "Blood Shield has " + pCooldowns.get(player) + " seconds left!");
		}
	}
	
	public void updateCooldowns() {
		for(Map.Entry<Player, Integer> entry : pCooldowns.entrySet()) {
			System.out.println("Assassinate: " + entry.getKey() + ": " + entry.getValue() + " seconds -> " + (entry.getValue()-1) + " seconds.");
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
