package com.devon1337.RPG.ActiveAbilities;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Rejuvenate {
	public final int COOLDOWN_AMOUNT = 10;
	public final int CLASS_TYPE = 0; // -- Druid
	public final Material ITEM = Material.PURPLE_DYE;
	public final Material CD_ITEM = Material.DIAMOND_SHOVEL;

	public static HashMap<Player, Integer> pCooldowns = new HashMap<Player, Integer>();

	public void use(Player player, Player target) {
		if (!pCooldowns.containsKey(player)) {
			pCooldowns.put(player, COOLDOWN_AMOUNT);

			target.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 5, 1));
			target.sendMessage("You are being healed by " + player.getName() + "!");

		} else {
			player.sendMessage(ChatColor.DARK_RED + "Rejuvenate has " + pCooldowns.get(player) + " seconds left!");
		}

	}

	public void updateCooldowns() {

		for (Map.Entry<Player, Integer> entry : pCooldowns.entrySet()) {

			System.out.println("Rejuvenate: " + entry.getKey() + ": " + entry.getValue() + " seconds -> "
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
