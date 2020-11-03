package com.devon1337.RPG.ActiveAbilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.PassiveAbilities.PassiveType;

public class Charge extends Spell{

	
		/**
	 * 
	 */
	private static final long serialVersionUID = 8509135774450483521L;
		// Predefined Variables
		static final String Name = "Charge", Description = "Gotta go fast...";
		static final NFClasses classReq = NFClasses.WARRIOR;
		static final PassiveType[] availPassives = {null};
		static final Material spellIcon = Material.BLACK_DYE;
		static final float speedBuffAmount = 2.5f;
		static final SpellType spellType = SpellType.QuickCast;
		
		public Charge(int id) {
			super(Name, Description, id, spellType, spellIcon, 10, 1, classReq, availPassives);
		}
		
		public static void use(Player player) {
			
			player.setWalkSpeed(player.getWalkSpeed() * speedBuffAmount);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable() {
				@Override
				public void run() {
					player.setWalkSpeed(player.getWalkSpeed() / speedBuffAmount);
				}
			}, 20L*5L);
			
		}
	
}