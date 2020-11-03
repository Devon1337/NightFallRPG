package com.devon1337.RPG.PassiveAbilities;

import org.bukkit.Material;
import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.Player.NFPlayer;

public class Lifesteal extends Passive {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9115564552843469498L;
	int extraLevels;
	static Material mat = Material.IRON_SWORD;

	public Lifesteal() {
		super("Lifesteal", 0, 1, NFClasses.ROGUE, PassiveType.Lifesteal, mat);
	}
	
	public static void run(NFPlayer player, double DamageAmount, int level) {
		double modifier = 1.0*(1.5*(1-level));
		
		double healAmount = (DamageAmount*modifier);
		if((player.getHp() + healAmount) > player.getMaxHp()) {
			healAmount = healAmount - ((player.getHp() + healAmount) - player.getMaxHp());
		}
		player.setHp(player.getHp() + healAmount);
	}
	
}