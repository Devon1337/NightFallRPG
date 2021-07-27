package com.devon1337.RPG.PassiveAbilities;

import java.util.Random;

import org.bukkit.Material;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.Player.NFPlayer;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

public class DisguiseMob extends Passive implements IPassive {


	private static final long serialVersionUID = 4186077839948127519L;

	static final String NAME = "Disguise Mob";
	static Material mat = Material.IRON_SWORD;
	static NFClasses team = NFClasses.DRUID;
	static UseType type = UseType.SelfCast;
	static Random rand = new Random();
	static DisguiseType[] disguises = {DisguiseType.COW, DisguiseType.SHEEP, DisguiseType.PIG, DisguiseType.WOLF};
	
	public DisguiseMob() {
		super(NAME, 1, PassiveType.DisguiseMob, mat, team, -1, type);
	}
	
	public void run(Spell s, NFPlayer player, NFPlayer target) {
	}

	@Override
	public void start(NFPlayer player) {
		MobDisguise md = new MobDisguise(disguises[rand.nextInt(disguises.length)]).setEntity(player.getPlayer());
		md.startDisguise();
	}

	@Override
	public void stop(NFPlayer player) {
		if(DisguiseAPI.isDisguised(player.getPlayer())) {
			DisguiseAPI.undisguiseToAll(player.getPlayer());
		}
	}

}
