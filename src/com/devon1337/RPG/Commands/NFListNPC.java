package com.devon1337.RPG.Commands;

import com.devon1337.RPG.NPC.WorldController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NFListNPC implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (sender instanceof Player) {

			if (args.length == 0) {
				WorldController.DisplayAllNPC((Player) sender);
			}

			return true;
		}

		return false;
	}
}