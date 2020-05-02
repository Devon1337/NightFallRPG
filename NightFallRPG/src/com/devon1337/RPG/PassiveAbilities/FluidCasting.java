package com.devon1337.RPG.PassiveAbilities;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FluidCasting {

	@SuppressWarnings("deprecation")
	public void doSelfHeal(Player player) {
		player.setHealth(player.getMaxHealth());
	}
	
	public void doJumpBoost(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20*10, 3));
	}
	
	// TODO: Will break with rogue buff. should ignore
	public void doSwiftness(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*20, 3));
	}
	
	public void doLevitation(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20*8, 2));
	}
	
	public void doCurse(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20*3, 2));
	}
	
	
}
