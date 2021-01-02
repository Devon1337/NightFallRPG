package com.devon1337.RPG.PassiveAbilities;

import org.bukkit.Material;

import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.Player.NFPlayer;

public class LowerCD extends Passive implements IPassive {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9115564552843469498L;
	static final String NAME = "Lower Cooldown";
	static Material mat = Material.IRON_SWORD;

	public LowerCD() {
		super(NAME, 1, PassiveType.SpellPower, mat);
		super.setPassive(this);
	}
	
	public void run(Spell s, NFPlayer player, NFPlayer target) {
		int modifier =  (2 * (1 + this.level));
		s.setCooldownTime(s.getCooldown()-modifier);
	}
}
