package com.devon1337.RPG.WeaponEffects;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public interface IWeaponType {
	public void use(Player player, ArrayList<Player> targets, float Amount);
}
