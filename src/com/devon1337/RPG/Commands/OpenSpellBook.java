package com.devon1337.RPG.Commands;

import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.Menu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenSpellBook implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		Player p1 = (Player) sender;
		NFPlayer player = NFPlayer.getPlayer(p1.getUniqueId());
		if (player != null) {
			Menu.getMenu(5).openNFInventory(player);
		}
		return true;
	}
}