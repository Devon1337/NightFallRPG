package com.devon1337.RPG.Classes;

import org.bukkit.Material;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.ActiveAbilities.GlobalSpellbook;
import com.devon1337.RPG.Menus.MenuIndex;
import com.devon1337.RPG.PassiveAbilities.Passive;
import com.devon1337.RPG.Utils.Menu;

public class Warrior extends GroupClass {

	static String name = "Warrior", description = "Your strength is in numbers!";
	static NFClasses classes = NFClasses.WARRIOR;
	static Material icon = Material.DIAMOND_SWORD;
	static Passive passive = GlobalSpellbook.getPassive("Iron Skin");

	// Base Reputation Values: {Mage, Rogue, Druid, Warrior}
	// -1.0f (Hated) - 0.0f (Unknown) - 1.0f (Loved) -> 10.0f (Self)
	static int[] ReputationValues = { 0, 0, 0, 1000 };

	public Warrior() {
		super(Menu.getMenuFromEnum(MenuIndex.WARRIOR_LEVELUP), name, description, classes, icon, ReputationValues);
		super.setPassive(passive);
	}

}
