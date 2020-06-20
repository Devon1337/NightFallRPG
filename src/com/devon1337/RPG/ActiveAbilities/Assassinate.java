package com.devon1337.RPG.ActiveAbilities;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class Assassinate {

	public final int COOLDOWN_AMOUNT = 10;
	public final int CLASS_TYPE = 1; // -- Rogue
	public final Material ITEM = Material.DIAMOND_HOE;
	public final Material CD_ITEM = Material.DIAMOND_SHOVEL;

	public static HashMap<Player, Integer> pCooldowns = new HashMap<Player, Integer>();

	public void use(Player player, Player target) {
		if (!pCooldowns.containsKey(player)) {
			pCooldowns.put(player, COOLDOWN_AMOUNT);

			double maxHP = target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
			if (target.getHealth() < maxHP / 3) {
				player.sendMessage(ChatColor.GREEN + "-- Assassinated --");
				target.setHealth(0);
			} else {
				player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Missed!");
			}
		} else {
			player.sendMessage(ChatColor.DARK_RED + "Assassinate has " + pCooldowns.get(player) + " seconds left!");
		}
	}

	public void updateCooldowns() {

		for (Map.Entry<Player, Integer> entry : pCooldowns.entrySet()) {

			System.out.println("Assassinate: " + entry.getKey() + ": " + entry.getValue() + " seconds -> "
					+ (entry.getValue() - 1) + " seconds.");
			pCooldowns.put(entry.getKey(), entry.getValue() - 1);

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