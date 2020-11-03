package com.devon1337.RPG.ActiveAbilities;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.PassiveType;

public class Assassinate extends Spell{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3273132808702990510L;

	double hpReq = 3.0f;
	
	// Predefined Variables
	static final String Name = "Assassinate", Description = "When a player is below 30% HP it will instantly kill the target!";
	static final NFClasses classReq = NFClasses.ROGUE;
	static final PassiveType[] availPassives = {PassiveType.Lifesteal};
	static final Material spellIcon = Material.IRON_SWORD;
	static final SpellType spellType = SpellType.SkillShot;
	
	public Assassinate(int id) {
		super(Name, Description, id, spellType, spellIcon, 10, 5, classReq, availPassives);
	}
	
	public static void use(Player player, Player target) {
		
		double maxHP = target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
		if (target.getHealth() < maxHP / 3.0D) {
			player.sendMessage(ChatColor.GREEN + "-- Assassinated --");
			target.setHealth(0.0D);
		} else {
			player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Missed!");
		}	
	}
	
}