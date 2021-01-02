package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.Passive;

public class Assassinate extends Spell implements ISpell {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3273132808702990510L;

	double hpReq = 3.0f;
	
	// Predefined Variables
	static final String Name = "Assassinate", Description = "When a player is below 30% HP it will instantly kill the target!";
	static final NFClasses classReq = NFClasses.ROGUE;
	static final ArrayList<Passive> availPassives = new ArrayList<Passive>();
	static final Material spellIcon = Material.IRON_SWORD;
	static final SpellType spellType = SpellType.SkillShot;
	
	public Assassinate() {
		super(Name, Description, spellType, spellIcon, 10, 5, classReq,  availPassives);
		super.setSpell(this);
	}
	
	public double use(Player player, ArrayList<Player> targets) {
		
		double DamageDealt = 0.0;
		
		for (Player target : targets) {

			double maxHP = target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
			if (target.getHealth() < maxHP / 3.0D) {
				player.sendMessage(ChatColor.GREEN + "-- Assassinated --");
				DamageDealt += target.getHealth();
				target.setHealth(0.0D);
			} else {
				player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Missed!");
			}
		}
		
		return DamageDealt;
	}
	
	public static String getSpellName() {
		return Name;
	}
	
	public ISpell getISpell() {
		return (ISpell) this;
	}
	
	public static void addPassive(Passive p) {
		availPassives.add(p);
	}
}