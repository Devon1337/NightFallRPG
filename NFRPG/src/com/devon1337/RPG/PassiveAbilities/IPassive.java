package com.devon1337.RPG.PassiveAbilities;

import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.Player.NFPlayer;

public interface IPassive {
	public void run(Spell s, NFPlayer player, NFPlayer target);
	public void start(NFPlayer player);
	public void stop(NFPlayer player);
	
}
