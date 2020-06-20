package com.devon1337.RPG.ActiveAbilities;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.devon1337.RPG.Player.PlayerStats;
import com.devon1337.RPG.Utils.Raycast.Simulate;
import com.devon1337.RPG.Utils.Raycast.Exceptions.InvalidTarget;

public class Confusion {

	public final int COOLDOWN_AMOUNT = 6;
	public final int DURATION_AMOUNT = 12;
	public final int CLASS_TYPE = 3; // -- Mage
	public final Material ITEM = Material.BLUE_DYE;
	public final Material CD_ITEM = Material.DIAMOND_SHOVEL;

	public static HashMap<Player, Integer> pCooldowns = new HashMap<Player, Integer>();

	public PlayerStats stats = new PlayerStats();

	public void use(Player player, Simulate raycast) {
		if (!pCooldowns.containsKey(player)) {

			pCooldowns.put(player, COOLDOWN_AMOUNT);

		} else {
			player.sendMessage(ChatColor.DARK_RED + "Confusion has " + pCooldowns.get(player) + " seconds left!");
		}
	}

	public void ability(Player player, Simulate raycast) {
		try {
			Player target = raycast.getTarget();
			target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20 * DURATION_AMOUNT, 4));
		} catch (InvalidTarget e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateCooldowns() {

		for (Map.Entry<Player, Integer> entry : pCooldowns.entrySet()) {

			System.out.println("Confusion: " + entry.getKey() + ": " + entry.getValue() + " seconds -> "
					+ (entry.getValue() - 1) + " seconds.");
			pCooldowns.put(entry.getKey(), entry.getValue() - 1);

			if (entry.getValue() - 1 < 0) {
				pCooldowns.remove(entry.getKey());
			}
		}
	}

	public boolean Exists(Player player) {
		return pCooldowns.containsKey(player);
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
