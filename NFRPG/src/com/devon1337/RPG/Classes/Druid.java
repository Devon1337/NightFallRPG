package com.devon1337.RPG.Classes;

import org.bukkit.Material;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.ActiveAbilities.GlobalSpellbook;
import com.devon1337.RPG.Menus.MenuIndex;
import com.devon1337.RPG.PassiveAbilities.Passive;
import com.devon1337.RPG.Utils.Menu;

public class Druid extends GroupClass {

	static String name = "Druid", description = "Test Description";
	static NFClasses classes = NFClasses.DRUID;
	static Material icon = Material.STICK;
	static Passive passive = GlobalSpellbook.getPassive("Disguise Mob");

	// Base Reputation Values: {Mage, Rogue, Druid, Warrior}
	// -1.0f (Hated) - 0.0f (Unknown) - 1.0f (Loved) -> 10.0f (Self)
	static int[] ReputationValues = { 0, 0, 1000, 0 };

	public Druid() {
		super(Menu.getMenuFromEnum(MenuIndex.DRUID_LEVELUP), name, description, classes, icon, ReputationValues);
		super.setPassive(passive);
	}

	

}