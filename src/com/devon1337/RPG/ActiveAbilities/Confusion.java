package com.devon1337.RPG.ActiveAbilities;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.devon1337.RPG.Player.PlayerStats;
import com.devon1337.RPG.Utils.Raycast.Simulate;
import com.devon1337.RPG.Utils.Raycast.Exceptions.InvalidTarget;


public class Confusion {

	public final int COOLDOWN_AMOUNT = 10;
	public final int DURATION_AMOUNT = 4; 

	public static HashMap<Player, Integer> pCooldowns = new HashMap<Player, Integer>();
	
	public PlayerStats stats = new PlayerStats();
	
	public void use(Player player, Simulate raycast) {
		if(!pCooldowns.containsKey(player)) {
		stats.storeMoveSpeed(player, player.getWalkSpeed());
		pCooldowns.put(player, COOLDOWN_AMOUNT);
		ability(player, raycast);
		} else {
			player.sendMessage(ChatColor.DARK_RED + "Confusion has " + pCooldowns.get(player) + " seconds left!");
		}
	}
	
	public void ability(Player player, Simulate raycast) {
		try {
			Player target = raycast.getTarget();
			target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20*DURATION_AMOUNT, 4));
		} catch (InvalidTarget e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void updateCooldowns() {
		for(Map.Entry<Player, Integer> entry : pCooldowns.entrySet()) {
			System.out.println("Charge: " + entry.getKey() + ": " + entry.getValue() + " seconds -> " + (entry.getValue()-1) + " seconds.");
			pCooldowns.remove(entry.getKey());
			pCooldowns.put(entry.getKey(), entry.getValue()-1);
			if(entry.getValue()-1 < 0) {
				pCooldowns.remove(entry.getKey());
			}
			
			if(entry.getValue() == COOLDOWN_AMOUNT-DURATION_AMOUNT) {
				entry.getKey().setWalkSpeed(stats.getMoveSpeed(entry.getKey()));
			}
		}
	}
	
}
