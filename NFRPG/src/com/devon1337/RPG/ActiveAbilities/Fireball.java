package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.PassiveAbilities.Passive;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.NFParticle;
import com.devon1337.RPG.Utils.EngineInterfaces.Update;

public class Fireball extends Spell implements ISpell, Update{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5526403694904240850L;
	// Predefined Variables
	static final String Name = ChatColor.GOLD + "Fire" + ChatColor.YELLOW + "ball", Description = "Shoot a directed shot at a player!";
	static final NFClasses classReq = NFClasses.MAGE;
	static final ArrayList<Passive> availPassives = new ArrayList<Passive>();
	static final Material spellIcon = Material.ORANGE_DYE;
	static final SpellType spellType = SpellType.SkillShot;
	
	static final double DamageAmount = 3.0;
	
	public Fireball() {
		super(Name, Description, spellType, spellIcon, 10, 1, classReq, availPassives);
		super.setSpell(this);
	}
	
	public double use(Player player, ArrayList<Player> targets) {
		Logging.OutputToConsole("Spell Ran!");
		for(Player target : targets) {
		NFPlayer NFtarget = NFPlayer.getPlayer(target.getUniqueId());
		Logging.OutputToPlayer("Target = " + target.getName(), player);
		double DamageDealt = (DamageAmount/NFtarget.getDamageResistance());
		NFtarget.setHp(NFtarget.getHp()-DamageDealt);
		target.setFireTicks(20*3);
		new NFParticle(player);
		
		return DamageDealt;
		}
		
		return 0.0;
	}
	
	public static String getSpellName() {
		return Name;
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
		new Fireball();
	}
}