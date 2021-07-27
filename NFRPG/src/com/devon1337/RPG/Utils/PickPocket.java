package com.devon1337.RPG.Utils;

import com.devon1337.RPG.Player.NFPlayer;

public class PickPocket {

	public PickPocket(NFPlayer user, NFPlayer target) {
		user.getPlayerFromUUID().openInventory(target.getPlayerFromUUID().getInventory());
	}
	
}
