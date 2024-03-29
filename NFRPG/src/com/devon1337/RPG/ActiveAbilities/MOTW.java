package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.Passive;
import com.devon1337.RPG.Utils.EngineInterfaces.Update;

public class MOTW extends Spell implements ISpell, Update{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4738855088951065537L;

	static final PotionEffect[] effects = { new PotionEffect(PotionEffectType.SPEED, 240, 2),
			new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 240, 2),
			new PotionEffect(PotionEffectType.NIGHT_VISION, 240, 1), new PotionEffect(PotionEffectType.JUMP, 240, 2),
			new PotionEffect(PotionEffectType.SLOW_FALLING, 240, 1),
			new PotionEffect(PotionEffectType.WATER_BREATHING, 240, 1),
			new PotionEffect(PotionEffectType.REGENERATION, 240, 1) };

	// Predefined Variables
	static final String Name = "Mark of the Wild", Description = "Become temporarily gifted!";
	static final NFClasses classReq = NFClasses.DRUID;
	static final ArrayList<Passive> availPassives = new ArrayList<Passive>();
	static final Material spellIcon = Material.BROWN_DYE;
	static final SpellType spellType = SpellType.QuickCast;

	public MOTW() {
		super(Name, Description, spellType, spellIcon, 10, 5, classReq, availPassives);
		super.setSpell(this);
	}

	public static void use(Player player) {
		
	}

	@Override
	public double use(Player player, ArrayList<Player> targets) {
		
		for (PotionEffect pe : effects) {
			player.addPotionEffect(pe);
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

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		new MOTW();
	}
}