package com.devon1337.RPG.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//	Author: Alex

public class NFTeleport implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player player = (Player) sender;
		Player target = Bukkit.getPlayer(args[0]);

		if (player.isOp() && arg2.equals("tp")) {
			if (target == null) {
				player.sendMessage(ChatColor.DARK_RED + "Invalid Player!");
				return true;
			}

			player.teleport(target.getLocation());

			return true;
		} else {
			player.sendMessage(ChatColor.DARK_RED + "Invalid Command!");
			return true;
		}
	}

}
