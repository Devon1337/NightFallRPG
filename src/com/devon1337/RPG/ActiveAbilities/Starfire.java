package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.PassiveType;

public class Starfire extends Spell implements ISpell{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8632189451263239066L;
	// Predefined Variables
	static final String Name = "Starfire", Description = "Pew pew";
	static final NFClasses classReq = NFClasses.DRUID;
	static final PassiveType[] availPassives = {null};
	static final Material spellIcon = Material.IRON_SWORD;
	static final SpellType spellType = SpellType.SkillShot;
	
	public Starfire() {
		super(Name, Description, spellType, spellIcon, 10, 5, classReq, availPassives);
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
	
}
