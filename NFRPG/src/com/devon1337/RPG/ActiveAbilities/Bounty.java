package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.Passive;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.EngineInterfaces.Update;

public class Bounty extends Spell implements ISpell, Update {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3273132808702990510L;


	// Predefined Variables
	static final String Name = ChatColor.GOLD + "Bounty", Description = "Make a player temporarily wanted and lower their resistance!";
	static final NFClasses classReq = NFClasses.ROGUE;
	static final ArrayList<Passive> availPassives = new ArrayList<Passive>();
	static final Material spellIcon = Material.BLACK_WOOL;
	static final SpellType spellType = SpellType.WeaponArt;

	public Bounty() {
		super(Name, Description, spellType, spellIcon, 10, 1, classReq, availPassives);
		super.setSpell(this);
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
		new Bounty();
	}
}
