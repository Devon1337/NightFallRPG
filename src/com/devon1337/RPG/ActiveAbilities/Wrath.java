package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.PassiveType;
import com.devon1337.RPG.Player.NFPlayer;

public class Wrath extends Spell implements ISpell{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2581186073760315345L;
	// Predefined Variables
	static final String Name = "Wrath", Description = "Pew pew but for magic users";
	static final NFClasses classReq = NFClasses.ROGUE;
	static final PassiveType[] availPassives = {PassiveType.Lifesteal};
	static final Material spellIcon = Material.IRON_SWORD;
	static final SpellType spellType = SpellType.SkillShot;
	
	static final double DamageAmount = 4.0;
	
	public Wrath() {
		super(Name, Description, spellType, spellIcon, 10, 1, classReq, availPassives);
	}
	
	public double use(Player player, Player target) {
		NFPlayer NFtarget = NFPlayer.getPlayer(target.getUniqueId());
		double DamageDealt = (DamageAmount/NFtarget.getDamageResistance());
		NFtarget.setHp(NFtarget.getHp()-(DamageAmount/NFtarget.getDamageResistance()));
		return DamageDealt;
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
