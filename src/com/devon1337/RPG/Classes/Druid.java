 package com.devon1337.RPG.Classes;

import org.bukkit.Material;

import com.devon1337.RPG.NFClasses;

public class Druid extends GroupClass {

	static String name = "Druid", description = "Test Description";
	static NFClasses classes = NFClasses.DRUID;
	static Material icon = Material.STICK;
	
	// Base Reputation Values: {Mage, Rogue, Druid, Warrior}
	// -1.0f (Hated) - 0.0f (Unknown) - 1.0f (Loved) -> 10.0f (Self)
	static int[] ReputationValues = {0, 0, 1000, 0};
	
	public Druid() {
		super(name,description,classes,icon,ReputationValues);
	}
	
}