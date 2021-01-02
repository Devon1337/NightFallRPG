package com.devon1337.RPG.PassiveAbilities;

import org.bukkit.Material;
import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.Player.NFPlayer;

public class Lifesteal extends Passive implements IPassive {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9115564552843469498L;
	static final String NAME = "Lifesteal";
	static Material mat = Material.IRON_SWORD;

	public Lifesteal() {
		super(NAME, 1, PassiveType.Lifesteal, mat);
		super.setPassive(this);
	}
	
	public void run(Spell s, NFPlayer player, NFPlayer target) {
		double modifier = 1.0*(1.5*(1-this.getLevel()));
		
		double healAmount = (s.getDamageAmount()*modifier);
		if((player.getHp() + healAmount) > player.getMaxHp()) {
			healAmount = healAmount - ((player.getHp() + healAmount) - player.getMaxHp());
		}
		player.setHp(player.getHp() + healAmount);
	}
	
}