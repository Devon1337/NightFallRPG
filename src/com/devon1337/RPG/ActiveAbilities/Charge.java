package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.PassiveAbilities.Passive;

public class Charge extends Spell implements ISpell{

	
		/**
	 * 
	 */
	private static final long serialVersionUID = 8509135774450483521L;
		// Predefined Variables
		static final String Name = "Charge", Description = "Gotta go fast...";
		static final NFClasses classReq = NFClasses.WARRIOR;
		static final ArrayList<Passive> availPassives = new ArrayList<Passive>();
		static final Material spellIcon = Material.BLACK_DYE;
		static final float speedBuffAmount = 2.5f;
		static final SpellType spellType = SpellType.QuickCast;
		
		public Charge() {
			super(Name, Description, spellType, spellIcon, 10, 1, classReq, availPassives);
			super.setSpell(this);
		}
		
		public static String getSpellName() {
			return Name;
		}

		@Override
		public double use(Player player, ArrayList<Player> targets) {
			// TODO Auto-generated method stub
			player.setWalkSpeed(player.getWalkSpeed() * speedBuffAmount);

			Bukkit.getScheduler().scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable() {
				@Override
				public void run() {
					player.setWalkSpeed(player.getWalkSpeed() / speedBuffAmount);
				}
			}, 20L * 5L);
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