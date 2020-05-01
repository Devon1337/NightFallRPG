package com.devon1337.RPG.ActiveAbilities;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;



public class Vanish {
	
	public final int COOLDOWN_AMOUNT = 10;
	public final int DURATION_AMOUNT = 3;
	public final int CLASS_TYPE = 1; // -- Rogue
	public final Material ITEM = Material.PHANTOM_MEMBRANE;
	
	public static HashMap<Player, Integer> pCooldowns = new HashMap<Player, Integer>(); 

	public void use(Player player) {
		if(!pCooldowns.containsKey(player)) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20*DURATION_AMOUNT, 0));
		pCooldowns.put(player, COOLDOWN_AMOUNT);
		} else {
			player.sendMessage(ChatColor.DARK_RED + "Vanish has " + pCooldowns.get(player) + " seconds left!");
		}
	}
	
	public void updateCooldowns() {
		for(Map.Entry<Player, Integer> entry : pCooldowns.entrySet()) {
			System.out.println("Vanish: " + entry.getKey() + ": " + entry.getValue() + " seconds -> " + (entry.getValue()-1) + " seconds.");
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