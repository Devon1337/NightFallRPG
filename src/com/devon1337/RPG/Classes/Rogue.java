 package com.devon1337.RPG.Classes;

import org.bukkit.Material;

import com.devon1337.RPG.NFClasses;

public class Rogue extends GroupClass {

	static String name = "Rogue", description = "Test Description";
	static NFClasses classes = NFClasses.ROGUE;
	static Material icon = Material.IRON_SWORD;
	
	// Base Reputation Values: {Mage, Rogue, Druid, Warrior}
	// -1.0f (Hated) - 0.0f (Unknown) - 1.0f (Loved) -> 10.0f (Self)
	static int[] ReputationValues = {0, 1000, 0, 0};
	
	public Rogue() {
		super(name,description,classes,icon,ReputationValues);
	}
	
}