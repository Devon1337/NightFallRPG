package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.Passive;
import com.devon1337.RPG.Utils.EngineInterfaces.Update;

public class Plague_Touch extends Spell implements ISpell, Update{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3273132808702990510L;
	
	// Predefined Variables
	static final String Name = "Plagued Touch", Description = "Hitting a player will apply 1 stack of plague on the target.";
	static final NFClasses classReq = NFClasses.DRUID;
	static final ArrayList<Passive> availPassives = new ArrayList<Passive>();
	static final Material spellIcon = Material.IRON_SWORD;
	static final SpellType spellType = SpellType.WeaponArt;
	
	public Plague_Touch() {
		super(Name, Description, spellType, spellIcon, 10, 5, classReq, availPassives);
		super.setSpell(this);
	}
	
	public double use(Player player, ArrayList<Player> targets) {
		for(Player p : targets) {
			p.sendMessage("Hit");
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
		new Plague_Touch();
	}
	
}