package com.devon1337.RPG.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.Menu;

//	Author: Alex

public class NFBanMenu implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player p1 = (Player) sender;
		NFPlayer player = NFPlayer.getPlayer(p1.getUniqueId());

		if (player != null) {
			if (!(p1.isOp())) {
				p1.sendMessage(ChatColor.DARK_RED + "Get outta here weak bitch!");
			}
			Menu.getMenu(6).openNFInventory(player);
			return true;
		} else {
			p1.sendMessage(ChatColor.DARK_RED + "Invalid Command!");
			return true;
		}
	}

}
