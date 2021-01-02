package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.PassiveAbilities.Passive;

public class Entanglement extends Spell implements ISpell{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2699829289876041409L;
	// Predefined Variables
	static final String Name = "Entanglement", Description = "Trap your enemies using the roots from the trees.";
	static final NFClasses classReq = NFClasses.DRUID;
	static final ArrayList<Passive> availPassives = new ArrayList<Passive>();
	static final Material spellIcon = Material.IRON_SWORD;
	static final SpellType spellType = SpellType.SkillShot;
	
	public Entanglement() {
		super(Name, Description, spellType, spellIcon, 10, 1, classReq, availPassives);
		super.setSpell(this);
	}
	

	public static String getSpellName() {
		return Name;
	}

	@Override
	public double use(Player player, ArrayList<Player> targets) {
		// TODO Auto-generated method stub
		for (Player target : targets) {
			float originalSpeed = target.getWalkSpeed();
			target.setWalkSpeed(0);
			Bukkit.getScheduler().scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable() {
				@Override
				public void run() {
					player.setWalkSpeed(originalSpeed);
				}
			}, 20L);
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
}