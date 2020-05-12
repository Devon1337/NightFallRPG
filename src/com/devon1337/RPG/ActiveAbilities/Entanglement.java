package com.devon1337.RPG.ActiveAbilities;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.Player.PlayerStats;
import com.devon1337.RPG.Utils.Raycast.Simulate;
import com.devon1337.RPG.Utils.Raycast.Exceptions.InvalidTarget;

public class Entanglement {

	public final int COOLDOWN_AMOUNT = 10;
	public final int DURATION_AMOUNT = 4;
	public final int CLASS_TYPE = 0; // -- Mage
	public final Material ITEM = Material.BROWN_DYE;
	public final Material CD_ITEM = Material.DIAMOND_SHOVEL;

	public static HashMap<Player, Integer> pCooldowns = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> pStun = new HashMap<Player, Integer>();
	public static HashMap<Player, Float> pOSpeed = new HashMap<Player, Float>();

	public PlayerStats stats = new PlayerStats();

	public void use(Player player, Simulate raycast) {
		if (!pCooldowns.containsKey(player)) {
			// stats.storeMoveSpeed(player, player.getWalkSpeed());
			pCooldowns.put(player, COOLDOWN_AMOUNT);

		} else {
			player.sendMessage(ChatColor.DARK_RED + "Entanglement has " + pCooldowns.get(player) + " seconds left!");
		}
	}

	public void ability(Player player, Simulate raycast) {

		try {

			if (raycast.getTarget() != null) {
				player.sendMessage(ChatColor.GREEN + "You have hit your target!");
				// sim.getTarget().setHealth(sim.getTarget().getHealth() - 6);
				pOSpeed.put(raycast.getTarget(), raycast.getTarget().getWalkSpeed());
				pStun.put(raycast.getTarget(), DURATION_AMOUNT);
				raycast.getTarget().setWalkSpeed(0);

			} else {
				player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Missed!");
			}
		} catch (InvalidTarget e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,
		// 20*DURATION_AMOUNT, 4));

	}

	public void updateCooldowns() {
		for (Map.Entry<Player, Integer> entry : pCooldowns.entrySet()) {
			System.out.println("Entanglement: " + entry.getKey() + ": " + entry.getValue() + " seconds -> "
					+ (entry.getValue() - 1) + " seconds.");
			pCooldowns.remove(entry.getKey());
			pCooldowns.put(entry.getKey(), entry.getValue() - 1);
			if (entry.getValue() - 1 < 0) {
				pCooldowns.remove(entry.getKey());
			}

			if (entry.getValue() == COOLDOWN_AMOUNT - DURATION_AMOUNT) {
				entry.getKey().setWalkSpeed(stats.getMoveSpeed(entry.getKey()));
			}
		}
	}

	public void updateStuns() {
		for (Map.Entry<Player, Integer> entry : pStun.entrySet()) {
			System.out.println("Entanglement Stun: " + entry.getKey() + ": " + entry.getValue() + " seconds -> "
					+ (entry.getValue() - 1) + " seconds.");
			pStun.replace(entry.getKey(), entry.getValue() - 1);
			if(entry.getValue() < 0) {
				pStun.remove(entry.getKey());
				entry.getKey().setWalkSpeed(pOSpeed.get(entry.getKey()));
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