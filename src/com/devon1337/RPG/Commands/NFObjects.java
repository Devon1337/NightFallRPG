package com.devon1337.RPG.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.Menu;

public class NFObjects implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if (sender instanceof Player) {
			Menu menu = Menu.getMenu(0);
			Player player = (Player) sender;
			menu.openNFInventory(NFPlayer.getPlayer(player.getUniqueId()));
		}
		return true;
	}
	
}
