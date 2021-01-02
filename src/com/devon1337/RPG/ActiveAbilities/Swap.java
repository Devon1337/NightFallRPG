package com.devon1337.RPG.ActiveAbilities;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Swap {
	public final int COOLDOWN_AMOUNT = 10;
	public final int DURATION_AMOUNT = 4;
	public final int CLASS_TYPE = 0;
	public final Material ITEM = Material.LIGHT_BLUE_DYE;
	public final Material CD_ITEM = Material.LIME_DYE;

	public static HashMap<Player, Integer> pCooldowns = new HashMap<>();



	public void use(Player player) {
		if (pCooldowns.containsKey(player)) {

			player.sendMessage(ChatColor.DARK_RED + "Tranquility has " + pCooldowns.get(player) + " seconds left!");
		}
	}

	public void updateCooldowns() {
		for (Map.Entry<Player, Integer> entry : pCooldowns.entrySet()) {

			System.out.println("Tranquility: " + entry.getKey() + ": " + entry.getValue() + " seconds -> "
					+ (((Integer) entry.getValue()).intValue() - 1) + " seconds.");
			pCooldowns.put(entry.getKey(), Integer.valueOf(((Integer) entry.getValue()).intValue() - 1));

			if (((Integer) entry.getValue()).intValue() == 6) {
				
			}

			if (((Integer) entry.getValue()).intValue() - 1 < 0) {
				pCooldowns.remove(entry.getKey());
			}
		}
	}

	public int getCooldown(Player player) {
		if (pCooldowns.containsKey(player)) {
			return ((Integer) pCooldowns.get(player)).intValue();
		}

		return 0;
	}

	public void setCooldown(Player player, int cooldown_amount) {
		if (pCooldowns.containsKey(player)) {
			pCooldowns.remove(player);
		}
		pCooldowns.put(player, Integer.valueOf(cooldown_amount));
	}
	
}