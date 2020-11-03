package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.PassiveType;

public class Starfire extends Spell{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8632189451263239066L;
	// Predefined Variables
	static final String Name = "Starfire", Description = "Pew pew";
	static final NFClasses classReq = NFClasses.DRUID;
	static final PassiveType[] availPassives = {null};
	static final Material spellIcon = Material.IRON_SWORD;
	static final SpellType spellType = SpellType.SkillShot;
	
	public Starfire(int id) {
		super(Name, Description, id, spellType, spellIcon, 10, 5, classReq, availPassives);
	}
	
	public static void use(Player player, ArrayList<Player> targets) {
		for(Player p : targets) {
			p.sendMessage("hit");
		}
		
		
	}
	
}
