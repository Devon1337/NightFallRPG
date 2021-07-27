package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.Passive;
import com.devon1337.RPG.Utils.EngineInterfaces.Update;

public class Confusion extends Spell implements ISpell, Update{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4633664208239906640L;
	// Predefined Variables
	static final String Name = "Confusion", Description = "Checkmate dumbass";
	static final NFClasses classReq = NFClasses.DRUID;
	static final ArrayList<Passive> availPassives = new ArrayList<Passive>();
	static final Material spellIcon = Material.OAK_LEAVES;
	static final SpellType spellType = SpellType.GroupCast;
	
	public Confusion() {
		super(Name, Description, spellType, spellIcon, 10, 1, classReq, availPassives);
		super.setSpell(this);
	}
	
	public void use(Player player, Player target) {
		target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 240, 4));
	}
	
	public static String getSpellName() {
		return Name;
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
		new Confusion();
	}
}
