package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.PassiveType;

public class Rejuvenate extends Spell{

	/**
	 * 
	 */
	private static final long serialVersionUID = -748517731224684592L;

	double hpReq = 3.0f;
	
	// Predefined Variables
	static final String Name = "Rejuvenate", Description = "Heal your party!";
	static final NFClasses classReq = NFClasses.DRUID;
	static final PassiveType[] availPassives = {null};
	static final Material spellIcon = Material.IRON_SWORD;
	static final PotionEffect RejuEffect = new PotionEffect(PotionEffectType.REGENERATION, 10, 1);
	static final SpellType spellType = SpellType.GroupCast;
	
	public Rejuvenate(int id) {
		super(Name, Description, id, spellType, spellIcon, 10, 1, classReq, availPassives);
	}
	
	public static void use(Player player, ArrayList<Player> targets) {
		
		for(Player p : targets) {
			p.addPotionEffect(RejuEffect);
		}
		
	}
}