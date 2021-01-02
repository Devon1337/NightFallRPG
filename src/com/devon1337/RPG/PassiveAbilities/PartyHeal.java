package com.devon1337.RPG.PassiveAbilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.PartySystem;

public class PartyHeal extends Passive implements IPassive {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9115564552843469498L;
	static final String NAME = "Party Heal";
	static Material mat = Material.IRON_SWORD;

	static final PotionEffect[] effects = { new PotionEffect(PotionEffectType.SPEED, 20, 2),
			new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20, 2),
			new PotionEffect(PotionEffectType.NIGHT_VISION, 20, 1), new PotionEffect(PotionEffectType.JUMP, 20, 2),
			new PotionEffect(PotionEffectType.SLOW_FALLING, 20, 1),
			new PotionEffect(PotionEffectType.WATER_BREATHING, 20, 1),
			new PotionEffect(PotionEffectType.REGENERATION, 20, 1) };

	public PartyHeal() {
		super(NAME, 1, PassiveType.PartyHeal, mat);
		super.setPassive(this);
	}

	public void run(Spell s, NFPlayer player, NFPlayer target) {

		Player i = player.getPlayerFromUUID();
		double modifier = 2;

		switch (this.getLevel()) {
		case 1:
			modifier = 4;
			if (PartySystem.inParty(i)) {
				for (Player p : PartySystem.getParty(PartySystem.getId(i))) {
					NFPlayer p1 = NFPlayer.getPlayer(p.getUniqueId());
					p1.healPlayer(modifier);
				}
			} else {
				modifier = modifier * 2;
				player.healPlayer(modifier);
			}
			break;

		case 2:

			if (PartySystem.inParty(i)) {
				modifier = 2;
				for (Player p : PartySystem.getParty(PartySystem.getId(i))) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, (int) modifier * 20, 1));
				}
			} else {
				modifier = modifier * 2;
				i.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, (int) modifier * 20, 1));
			}
			break;
		case 3:
			if (PartySystem.inParty(i)) {
				for (Player p : PartySystem.getParty(PartySystem.getId(i))) {
					for (PotionEffect pe : effects) {
						p.addPotionEffect(pe);
					}
				}
			} else {
				for (PotionEffect pe : effects) {
					i.addPotionEffect(pe);
				}
			}

			break;
		}
	}
}
