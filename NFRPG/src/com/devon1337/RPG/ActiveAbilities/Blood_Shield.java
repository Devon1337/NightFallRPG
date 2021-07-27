package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.Passive;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.EngineInterfaces.Update;

public class Blood_Shield extends Spell implements ISpell, Update {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 6415646772849009418L;
	
		// Predefined Variables
		static final String Name = ChatColor.DARK_RED + "Soul Drain", Description = "Drain the soul of nearby enemies";
		static final NFClasses classReq = NFClasses.MAGE;
		static final ArrayList<Passive> availPassives = new ArrayList<Passive>();
		static final Material spellIcon = Material.POTION;
		static final double DamageAmount = 3.0D;
		static final SpellType spellType = SpellType.GroupCast;
		
		public Blood_Shield() {
			super(Name, Description, spellType, spellIcon, 10, 1, classReq, availPassives);
			super.setSpell(this);
		}
		
		public double use(Player sender, ArrayList<Player> targets) {
			double DamageDealt = 0;
			for(Player p : targets) {
				NFPlayer NFtarget = NFPlayer.getPlayer(p.getUniqueId());
				DamageDealt += (DamageAmount/NFtarget.getDamageResistance());
				NFtarget.setHp(NFtarget.getHp()-(DamageAmount/NFtarget.getDamageResistance()));
			}
			return DamageDealt;
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
			new Blood_Shield();
		}
}