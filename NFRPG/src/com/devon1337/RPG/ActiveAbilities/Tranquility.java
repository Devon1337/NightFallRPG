package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.Passive;
import com.devon1337.RPG.Utils.EngineInterfaces.Update;

public class Tranquility extends Spell implements ISpell, Update {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3261962465802016871L;

	static final PotionEffect[] effects = { new PotionEffect(PotionEffectType.ABSORPTION, 240, 4),
			new PotionEffect(PotionEffectType.REGENERATION, 240, 2),
			new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 240, 1), 
			new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 240, 1)};
	
	// Predefined Variables
	static final String Name = "Tranquility", Description = "Time to lowkey take a nap";
	static final NFClasses classReq = NFClasses.DRUID;
	static final ArrayList<Passive> availPassives = new ArrayList<Passive>();
	static final Material spellIcon = Material.IRON_SWORD;
	static final SpellType spellType = SpellType.QuickCast;
	
	public Tranquility() {
		super(Name, Description, spellType, spellIcon, 10, 5, classReq, availPassives);
		super.setSpell(this);
	}
	
	public void use(Player player, Player target) {
		for (PotionEffect pe : effects) {
			player.addPotionEffect(pe);
		}
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
	
	public static void addPassive(Passive p) {
		availPassives.add(p);
	
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		new Tranquility();
	}
	
}
