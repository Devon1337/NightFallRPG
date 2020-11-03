package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.PassiveType;

public class Explosive_Strike extends Spell {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3273132808702990510L;
	
	// Predefined Variables
	static final String Name = "Explosive Strike", Description = "Hitting the player will cause an explosion dealing n damage to the target";
	static final NFClasses classReq = NFClasses.WARRIOR;
	static final PassiveType[] availPassives = {null};
	static final Material spellIcon = Material.IRON_SWORD;
	static final SpellType spellType = SpellType.WeaponArt;
	
	public Explosive_Strike(int id) {
		super(Name, Description, id, spellType, spellIcon, 10, 5, classReq, availPassives);
	}
	
	public static void use(Player player, ArrayList<Player> targets) {
		for(Player p : targets) {
			p.sendMessage("Hit");
		}
	}
	
}