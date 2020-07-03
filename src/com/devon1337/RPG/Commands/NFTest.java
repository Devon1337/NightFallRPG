package com.devon1337.RPG.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NPC.WorldController;

public class NFTest implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender,  Command cmd,  String arg2, String[] args) {
		Player player = (Player) sender;
		
		if(sender instanceof Player) {
			WorldController.displayAllFactionRep(player);
			return true;
		}
		
		return false;
	}
	
}
