package com.devon1337.RPG.Utils;

import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class NFParticle {
	// Testing Particle Effects
	public NFParticle(Player player) {
		player.getWorld().spawnParticle(Particle.FLAME, player.getLocation(), 20, 10, 10, 0);
	}
	
}
