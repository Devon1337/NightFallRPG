package com.devon1337.RPG.PassiveAbilities;

import org.bukkit.Material;

import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.Player.NFPlayer;

public class CCPower extends Passive implements IPassive {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9115564552843469498L;
	static final String NAME = "Crowd Control Power";
	static Material mat = Material.IRON_SWORD;

	public CCPower() {
		super(NAME, 1, PassiveType.CCPower, mat);
		super.setPassive(this);
	}
	
	public void run(Spell s, NFPlayer player, NFPlayer target) {
		double modifier =  (2 * (1 + this.level));
		s.setStunDuration(s.getStunDuration() + modifier);
	}
}
