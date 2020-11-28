package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.PassiveType;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.AOEMapping.AreaMap;

public class Explosive_Strike extends Spell implements ISpell{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3273132808702990510L;
	
	// Predefined Variables
	static final String Name = "Explosive Strike", Description = "Hit a player causing an explosion that hits nearby enemies";
	static final NFClasses classReq = NFClasses.WARRIOR;
	static final PassiveType[] availPassives = {null};
	static final Material spellIcon = Material.IRON_SWORD;
	static final SpellType spellType = SpellType.WeaponArt;
	static final double DAMAGE = 3.0;
	static final double EXPLOSIVE_DAMAGE = 1.5;
	
	public Explosive_Strike() {
		super(Name, Description, spellType, spellIcon, 10, 1, classReq, availPassives);
	}
	
	public double use(Player player, ArrayList<Player> targets) {
		for(Player p : targets) {
			NFPlayer nfp = NFPlayer.getPlayer(p.getUniqueId());
			nfp.setHp(nfp.getHp() - DAMAGE);
			ArrayList<Player> p2 = new AreaMap(p.getWorld(), p.getLocation(), 4).getHostilePlayers(player);
			for(Player p3 : p2) {
				NFPlayer nfp2 = NFPlayer.getPlayer(p3.getUniqueId());
				nfp2.setHp(nfp2.getHp() - EXPLOSIVE_DAMAGE);
			}
		}
		
		return 0;
	}
	
	public static String getSpellName() {
		return Name;
	}

	@Override
	public ISpell getISpell() {
		// TODO Auto-generated method stub
		return null;
	}
	
}