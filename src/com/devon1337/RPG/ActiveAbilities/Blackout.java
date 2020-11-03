package com.devon1337.RPG.ActiveAbilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.PassiveType;

public class Blackout extends Spell {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3273132808702990510L;


	// Predefined Variables
	static final String Name = "Blackout", Description = "Blind nearby enemies";
	static final NFClasses classReq = NFClasses.ROGUE;
	static final PassiveType[] availPassives = { null };
	static final Material spellIcon = Material.BLACK_WOOL;
	static final SpellType spellType = SpellType.QuickCast;

	public Blackout(int id) {
		super(Name, Description, id, spellType, spellIcon, 10, 1, classReq, availPassives);
	}

	public static void use(Player player, Player target) {

	}
}
