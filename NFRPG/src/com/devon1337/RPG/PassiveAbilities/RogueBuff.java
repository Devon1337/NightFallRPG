package com.devon1337.RPG.PassiveAbilities;

import org.bukkit.Material;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.Player.NFPlayer;

public class RogueBuff extends Passive implements IPassive {


	private static final long serialVersionUID = 4186077839948127519L;

	static final String NAME = "Rogue Buff";
	static Material mat = Material.IRON_SWORD;
	static NFClasses team = NFClasses.ROGUE;
	static UseType type = UseType.SelfCast;
	
	float speedModifier = 0.2f;
	
	public RogueBuff() {
		super(NAME, 1, PassiveType.RogueBuff, mat, team, -1, type);
	}
	
	public void run(Spell s, NFPlayer player, NFPlayer target) {
	}

	@Override
	public void start(NFPlayer player) {
		player.setSpeedModifier(player.getSpeedModifier() + speedModifier);
	}

	@Override
	public void stop(NFPlayer player) {
		player.setSpeedModifier(player.getSpeedModifier() - speedModifier);
	}

}
