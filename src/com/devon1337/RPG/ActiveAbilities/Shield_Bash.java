package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.PassiveType;

public class Shield_Bash extends Spell implements ISpell{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 222593823497970945L;
	// Predefined Variables
	static final String Name = "Shield Bash", Description = "Ram into your enemy";
	static final NFClasses classReq = NFClasses.WARRIOR;
	static final PassiveType[] availPassives = {null};
	static final Material spellIcon = Material.IRON_SWORD;
	static final SpellType spellType = SpellType.WeaponArt;
	
	public Shield_Bash() {
		super(Name, Description, spellType, spellIcon, 10, 1, classReq, availPassives);
	}
	
	public static void use(Player player, Player target) {
		Location loc = new Location(player.getWorld() ,target.getLocation().getBlockX() - player.getLocation().getBlockX(), (target.getLocation().getBlockY()-player.getLocation().getBlockY()) + 1,  target.getLocation().getBlockZ() - player.getLocation().getBlockZ());
		Vector vel = loc.toVector();
		target.setVelocity(vel.multiply(5));
		
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