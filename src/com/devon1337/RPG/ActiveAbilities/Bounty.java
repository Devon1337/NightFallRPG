package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.PassiveType;
import com.devon1337.RPG.Player.NFPlayer;

public class Bounty extends Spell implements ISpell {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3273132808702990510L;


	// Predefined Variables
	static final String Name = ChatColor.GOLD + "Bounty", Description = "Make a player temporarily wanted and lower their resistance!";
	static final NFClasses classReq = NFClasses.ROGUE;
	static final PassiveType[] availPassives = { null };
	static final Material spellIcon = Material.BLACK_WOOL;
	static final SpellType spellType = SpellType.WeaponArt;

	public Bounty() {
		super(Name, Description, spellType, spellIcon, 10, 1, classReq, availPassives);
	}

	
	public static String getSpellName() {
		return Name;
	}


	@Override
	public double use(Player player, ArrayList<Player> targets) {
		// TODO Auto-generated method stub
		 
		for(Player target : targets) {
			NFPlayer t = NFPlayer.getPlayer(target.getUniqueId());
			t.setDamageResistance(t.getDamageResistance()-0.5f);
		}
		
		return 0;
	}


	@Override
	public ISpell getISpell() {
		// TODO Auto-generated method stub
		return null;
	}
}
