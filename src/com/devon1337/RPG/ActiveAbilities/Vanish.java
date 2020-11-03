package com.devon1337.RPG.ActiveAbilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.PassiveType;

public class Vanish extends Spell{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4918640865681475180L;

	double hpReq = 3.0f;
	
	// Predefined Variables
	static final String Name = "Vanish", Description = "Become invisible!";
	static final NFClasses classReq = NFClasses.ROGUE;
	static final PassiveType[] availPassives = {null};
	static final Material spellIcon = Material.IRON_SWORD;
	static final SpellType spellType = SpellType.QuickCast;
	
	public Vanish(int id) {
		super(Name, Description, id, spellType, spellIcon, 10, 1, classReq, availPassives);
	}
	
	public static void use(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 240, 1));
	}
}
