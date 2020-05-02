package com.devon1337.RPG.Player;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class PlayerStats {

	
	
	public static HashMap<Player, Float> pMoveSpeed = new HashMap<Player, Float>(); 
	
	public void storeMoveSpeed(Player player, float moveSpeed) {
		if(moveSpeed < 10) {
		pMoveSpeed.put(player, moveSpeed);
		}
	}
	
	public boolean mapContains(Player player) {
		return pMoveSpeed.containsKey(player);
	}
	
	public float getMoveSpeed(Player player) {
		if(pMoveSpeed.containsKey(player)) {
			return pMoveSpeed.get(player);
		} else {
			return 0.1f;
		}
		
	}
}