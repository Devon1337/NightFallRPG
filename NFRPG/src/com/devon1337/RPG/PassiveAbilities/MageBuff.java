package com.devon1337.RPG.PassiveAbilities;

import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.Player.NFPlayer;

public class MageBuff extends Passive implements IPassive {


	private static final long serialVersionUID = 4186077839948127519L;

	static final String NAME = "Mage Buff";
	static Material mat = Material.IRON_SWORD;
	static NFClasses team = NFClasses.MAGE;
	static UseType type = UseType.SelfCast;
	
	static PotionEffect effect = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9999, 1, false, false);
	
	public MageBuff() {
		super(NAME, 1, PassiveType.MageBuff, mat, team, -1, type);
	}
	
	public void run(Spell s, NFPlayer player, NFPlayer target) {
	}

	@Override
	public void start(NFPlayer player) {
		player.getPlayer().addPotionEffect(effect);
	}

	@Override
	public void stop(NFPlayer player) {
		player.getPlayer().removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
	}

}
