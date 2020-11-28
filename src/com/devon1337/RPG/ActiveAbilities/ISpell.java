package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public interface ISpell {

	double use(Player player, ArrayList<Player> targets);
	
	ISpell getISpell();
	
}
