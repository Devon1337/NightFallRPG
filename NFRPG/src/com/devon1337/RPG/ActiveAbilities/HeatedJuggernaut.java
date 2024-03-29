package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.inventivetalent.glow.GlowAPI;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.Passive;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.EngineInterfaces.Update;

public class HeatedJuggernaut extends Spell implements ISpell, Update{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3286505449887297282L;
	
	// Predefined Variables
	static final String Name = "Heated Juggernaut", Description = "";
	static final NFClasses classReq = NFClasses.WARRIOR;
	static final ArrayList<Passive> availPassives = new ArrayList<Passive>();
	static final Material spellIcon = Material.IRON_SWORD;
	static final SpellType spellType = SpellType.GroupCast;
	
	public HeatedJuggernaut() {
		super(Name, Description, spellType, spellIcon, 10, 5, classReq, availPassives);
		super.setSpell(this);
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
		new HeatedJuggernaut();
	}
}