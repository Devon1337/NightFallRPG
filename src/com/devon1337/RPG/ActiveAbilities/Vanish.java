package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.Passive;

public class Vanish extends Spell implements ISpell{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4918640865681475180L;

	double hpReq = 3.0f;
	
	// Predefined Variables
	static final String Name = "Vanish", Description = "Become invisible!";
	static final NFClasses classReq = NFClasses.ROGUE;
	static final ArrayList<Passive> availPassives = new ArrayList<Passive>();
	static final Material spellIcon = Material.IRON_SWORD;
	static final SpellType spellType = SpellType.QuickCast;
	
	public Vanish() {
		super(Name, Description, spellType, spellIcon, 10, 1, classReq, availPassives);
		super.setSpell(this);
	}
	
	public static void use(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 240, 1));
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
