package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.PassiveType;

public class Empty extends Spell implements ISpell{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1228750587458249568L;
	// Predefined Variables
	static final String Name = ChatColor.DARK_RED + "" + ChatColor.BOLD + "Empty Slot", Description = "Do /spellbook to equip a spell";
	static final NFClasses classReq = NFClasses.NOCLASS;
	static final PassiveType[] availPassives = {null};
	static final Material spellIcon = Material.BARRIER;
	static final SpellType spellType = SpellType.QuickCast;
	
	public Empty() {
		super(Name, Description, spellType, spellIcon, 10, 5, classReq, availPassives);
	}

	@Override
	public double use(Player player, ArrayList<Player> targets) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ISpell getISpell() {
		// TODO Auto-generated method stub
		return null;
	}
}