package com.devon1337.RPG.Classes;

import org.bukkit.Material;

import com.devon1337.RPG.NFClasses;

public class Warrior extends GroupClass {

	static String name = "Warrior", description = "Your strength is in numbers!";
	static NFClasses classes = NFClasses.WARRIOR;
	static Material icon = Material.DIAMOND_SWORD;
	
	// Base Reputation Values: {Mage, Rogue, Druid, Warrior}
	// -1.0f (Hated) - 0.0f (Unknown) - 1.0f (Loved) -> 10.0f (Self)
	static int[] ReputationValues = {0, 0, 0, 1000};
	
	public Warrior() {
		super(name,description,classes,icon,ReputationValues);
	}
	
}
