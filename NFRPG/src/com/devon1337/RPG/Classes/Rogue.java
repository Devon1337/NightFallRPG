package com.devon1337.RPG.Classes;

import org.bukkit.Material;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.ActiveAbilities.GlobalSpellbook;
import com.devon1337.RPG.PassiveAbilities.Passive;

public class Rogue extends GroupClass {

	static String name = "Rogue", description = "Test Description";
	static NFClasses classes = NFClasses.ROGUE;
	static Material icon = Material.IRON_SWORD;
	static Passive passive = GlobalSpellbook.getPassive("Rogue Buff");

	// Base Reputation Values: {Mage, Rogue, Druid, Warrior}
	// -1.0f (Hated) - 0.0f (Unknown) - 1.0f (Loved) -> 10.0f (Self)
	static int[] ReputationValues = { 0, 1000, 0, 0 };

	public Rogue() {
		super(null, name, description, classes, icon, ReputationValues);
		super.setPassive(passive);
	}

}