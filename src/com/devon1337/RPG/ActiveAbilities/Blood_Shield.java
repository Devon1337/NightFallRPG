package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.PassiveType;
import com.devon1337.RPG.Player.NFPlayer;

public class Blood_Shield extends Spell {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 6415646772849009418L;
	
		// Predefined Variables
		static final String Name = "Blood Shield", Description = "Drain the blood of nearby enemies and heal the damage done!";
		static final NFClasses classReq = NFClasses.MAGE;
		static final PassiveType[] availPassives = {PassiveType.Lifesteal};
		static final Material spellIcon = Material.POTION;
		static final double DamageAmount = 3.0D;
		static final SpellType spellType = SpellType.GroupCast;
		
		public Blood_Shield(int id) {
			super(Name, Description, id, spellType, spellIcon, 10, 1, classReq, availPassives);
		}
		
		public static double use(Player sender, ArrayList<Player> targets) {
			double DamageDealt = 0;
			for(Player p : targets) {
				NFPlayer NFtarget = NFPlayer.getPlayer(p.getUniqueId());
				DamageDealt += (DamageAmount/NFtarget.getDamageResistance());
				NFtarget.setHp(NFtarget.getHp()-(DamageAmount/NFtarget.getDamageResistance()));
			}
			return DamageDealt;
		}
}