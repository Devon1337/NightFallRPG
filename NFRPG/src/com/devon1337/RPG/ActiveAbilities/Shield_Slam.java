package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.PassiveAbilities.Passive;
import com.devon1337.RPG.Utils.EngineInterfaces.Update;

public class Shield_Slam extends Spell implements ISpell, Update{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7398906946681886597L;
	// Predefined Variables
	static final String Name = "Shield Slam", Description = "Break er knee caps";
	static final NFClasses classReq = NFClasses.WARRIOR;
	static final ArrayList<Passive> availPassives = new ArrayList<Passive>();
	static final Material spellIcon = Material.IRON_SWORD;
	static final SpellType spellType = SpellType.GroupCast;
	
	public Shield_Slam() {
		super(Name, Description, spellType, spellIcon, 10, 1, classReq, availPassives);
		super.setSpell(this);
	}
	
	public double use(Player player, ArrayList<Player> targets) {
		float[] OriginalSpeed = new float[targets.size()];
		for(int i = 0; i < targets.size(); i++) {
			OriginalSpeed[i] = targets.get(i).getWalkSpeed();
			targets.get(i).setWalkSpeed(0);
		}
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < targets.size(); i++) {
					targets.get(i).setWalkSpeed(OriginalSpeed[i]);
				}
			}
		}, 20L);
		
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
		new Shield_Slam();
	}
}