package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.Passive;

public class Rejuvenate extends Spell implements ISpell{

	/**
	 * 
	 */
	private static final long serialVersionUID = -748517731224684592L;
	
	// Predefined Variables
	static final String Name = "Rejuvenate", Description = "Heal your party!";
	static final NFClasses classReq = NFClasses.DRUID;
	static final ArrayList<Passive> availPassives = new ArrayList<Passive>();
	static final Material spellIcon = Material.IRON_SWORD;
	static final PotionEffect RejuEffect = new PotionEffect(PotionEffectType.REGENERATION, 20*3, 5);
	static final SpellType spellType = SpellType.GroupCast;
	
	public Rejuvenate() {
		super(Name, Description, spellType, spellIcon, 10, 1, classReq, availPassives);
		super.setSpell(this);
	}
	
	public double use(Player player, ArrayList<Player> targets) {
		
		for(Player p : targets) {
			p.addPotionEffect(RejuEffect);
		}
		
		return 0;
		
	}

	@Override
	public ISpell getISpell() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void addPassive(Passive p) {
		availPassives.add(p);
	}
}