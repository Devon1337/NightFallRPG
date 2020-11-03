package com.devon1337.RPG.ActiveAbilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.PassiveType;

public class Confusion extends Spell{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4633664208239906640L;
	// Predefined Variables
	static final String Name = "Confusion", Description = "Checkmate dumbass";
	static final NFClasses classReq = NFClasses.DRUID;
	static final PassiveType[] availPassives = {null};
	static final Material spellIcon = Material.OAK_LEAVES;
	static final SpellType spellType = SpellType.GroupCast;
	
	public Confusion(int id) {
		super(Name, Description, id, spellType, spellIcon, 10, 1, classReq, availPassives);
	}
	
	public static void use(Player player, Player target) {
		target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 240, 4));
	}
}