package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.inventivetalent.glow.GlowAPI;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.PassiveType;
import com.devon1337.RPG.Player.NFPlayer;

public class HeatedJuggernaut extends Spell implements ISpell{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3286505449887297282L;
	// Predefined Variables
	static final String Name = "Heated Juggernaut", Description = "";
	static final NFClasses classReq = NFClasses.WARRIOR;
	static final PassiveType[] availPassives = {null};
	static final Material spellIcon = Material.IRON_SWORD;
	static final SpellType spellType = SpellType.GroupCast;
	
	public HeatedJuggernaut() {
		super(Name, Description, spellType, spellIcon, 10, 5, classReq, availPassives);
	}
	
	public double use(Player player, ArrayList<Player> targets) {
		for(Player p : targets) {
			NFPlayer.getPlayer(p.getUniqueId()).setDamageResistance(NFPlayer.getPlayer(p.getUniqueId()).getDamageResistance()-0.4f);
			p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20*5, 1));
			GlowAPI.setGlowing(player, GlowAPI.Color.WHITE, p);
		}
		
		return 0;
	}

	@Override
	public ISpell getISpell() {
		// TODO Auto-generated method stub
		return null;
	}
}