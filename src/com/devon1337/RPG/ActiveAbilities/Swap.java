package com.devon1337.RPG.ActiveAbilities;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.Player.PlayerStats;

public class Swap {
	public final int COOLDOWN_AMOUNT = 10;
	public final int DURATION_AMOUNT = 4;
	public final int CLASS_TYPE = 0; // -- Druid
	public final Material ITEM = Material.LIGHT_BLUE_DYE;
	public final Material CD_ITEM = Material.LIME_DYE;

	public static HashMap<Player, Integer> pCooldowns = new HashMap<Player, Integer>();

	public PlayerStats stats = new PlayerStats();

	public void use(Player player) {
		if (!pCooldowns.containsKey(player)) {
			
			
			
		} else {
			player.sendMessage(ChatColor.DARK_RED + "Tranquility has " + pCooldowns.get(player) + " seconds left!");
		}
	}

	public void updateCooldowns() {

		for (Map.Entry<Player, Integer> entry : pCooldowns.entrySet()) {

			System.out.println("Tranquility: " + entry.getKey() + ": " + entry.getValue() + " seconds -> "
					+ (entry.getValue() - 1) + " seconds.");
			pCooldowns.put(entry.getKey(), entry.getValue() - 1);

			if (entry.getValue() == COOLDOWN_AMOUNT - DURATION_AMOUNT) {
				entry.getKey().setWalkSpeed(stats.getMoveSpeed(entry.getKey()));
			}

			if (entry.getValue() - 1 < 0) {
				pCooldowns.remove(entry.getKey());
			}
		}
	}

	public int getCooldown(Player player) {
		if (pCooldowns.containsKey(player)) {
			return pCooldowns.get(player);
		}

		return 0;
	}

	public void setCooldown(Player player, int cooldown_amount) {
		if (pCooldowns.containsKey(player)) {
			pCooldowns.remove(player);
		}
		pCooldowns.put(player, cooldown_amount);
	}
}
