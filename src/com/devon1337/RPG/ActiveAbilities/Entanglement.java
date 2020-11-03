package com.devon1337.RPG.ActiveAbilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.PassiveAbilities.PassiveType;

public class Entanglement extends Spell{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2699829289876041409L;
	// Predefined Variables
	static final String Name = "Entanglement", Description = "Trap your enemies using the roots from the trees.";
	static final NFClasses classReq = NFClasses.DRUID;
	static final PassiveType[] availPassives = {null};
	static final Material spellIcon = Material.IRON_SWORD;
	static final SpellType spellType = SpellType.SkillShot;
	
	public Entanglement(int id) {
		super(Name, Description, id, spellType, spellIcon, 10, 1, classReq, availPassives);
	}
	
	public static void use(Player player, Player target) {
		float originalSpeed = target.getWalkSpeed();
		target.setWalkSpeed(0);
		Bukkit.getScheduler().scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable() {
			@Override
			public void run() {
				player.setWalkSpeed(originalSpeed);
			}
		}, 20L);
		
	}
}