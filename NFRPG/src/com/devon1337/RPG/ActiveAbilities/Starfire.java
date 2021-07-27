package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.Passive;
import com.devon1337.RPG.Utils.EngineInterfaces.Update;

public class Starfire extends Spell implements ISpell, Update{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8632189451263239066L;
	// Predefined Variables
	static final String Name = "Starfire", Description = "Pew pew";
	static final NFClasses classReq = NFClasses.DRUID;
	static final ArrayList<Passive> availPassives = new ArrayList<Passive>();
	static final Material spellIcon = Material.IRON_SWORD;
	static final SpellType spellType = SpellType.SkillShot;
	
	public Starfire() {
		super(Name, Description, spellType, spellIcon, 10, 5, classReq, availPassives);
		super.setSpell(this);
	}
	
	public double use(Player player, ArrayList<Player> targets) {
		for(Player p : targets) {
			p.sendMessage("hit");
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
		new Starfire();
	}
	
}
