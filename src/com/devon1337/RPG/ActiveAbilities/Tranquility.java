package com.devon1337.RPG.ActiveAbilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.PassiveType;

public class Tranquility extends Spell{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3261962465802016871L;

	static final PotionEffect[] effects = { new PotionEffect(PotionEffectType.ABSORPTION, 240, 4),
			new PotionEffect(PotionEffectType.REGENERATION, 240, 2),
			new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 240, 1), 
			new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 240, 1)};
	
	// Predefined Variables
	static final String Name = "Tranquility", Description = "Time to lowkey take a nap";
	static final NFClasses classReq = NFClasses.DRUID;
	static final PassiveType[] availPassives = {null};
	static final Material spellIcon = Material.IRON_SWORD;
	static final SpellType spellType = SpellType.QuickCast;
	
	public Tranquility(int id) {
		super(Name, Description, id, spellType, spellIcon, 10, 5, classReq, availPassives);
	}
	
	public static void use(Player player, Player target) {
		for (PotionEffect pe : effects) {
			player.addPotionEffect(pe);
		}
	}
}
