package com.devon1337.RPG.Utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.devon1337.RPG.Player.NFPlayer;

public interface IMenu {

	boolean Response(NFPlayer player, int slot);
	int getPage();
	Inventory open(Player player);
}
