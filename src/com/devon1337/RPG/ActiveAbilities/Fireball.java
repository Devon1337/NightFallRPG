package com.devon1337.RPG.ActiveAbilities;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.PassiveType;
import com.devon1337.RPG.Player.NFPlayer;

public class Fireball extends Spell{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5526403694904240850L;
	// Predefined Variables
	static final String Name = ChatColor.GOLD + "Fire" + ChatColor.YELLOW + "ball", Description = "Shoot a directed shot at a player!";
	static final NFClasses classReq = NFClasses.MAGE;
	static final PassiveType[] availPassives = {null};
	static final Material spellIcon = Material.IRON_SWORD;
	static final SpellType spellType = SpellType.SkillShot;
	
	static final double DamageAmount = 3.0;
	
	public Fireball(int id) {
		super(Name, Description, id, spellType, spellIcon, 10, 1, classReq, availPassives);
	}
	
	public static double use(Player player, Player target) {
		NFPlayer NFtarget = NFPlayer.getPlayer(target.getUniqueId());
		double DamageDealt = (DamageAmount/NFtarget.getDamageResistance());
		NFtarget.setHp(NFtarget.getHp()-(DamageAmount/NFtarget.getDamageResistance()));
		target.setFireTicks(20*3);
		return DamageDealt;
	}
}