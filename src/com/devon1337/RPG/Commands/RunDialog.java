package com.devon1337.RPG.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.devon1337.RPG.Utils.DialogueSystem;

public class RunDialog implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		// TODO Auto-generated method stub

		Player player = (Player) sender;
		player.sendMessage("");
		player.sendMessage("");
		player.sendMessage("");
		player.sendMessage("");

		if (sender instanceof Player) {
			if(args.length == 1) {
				if(DialogueSystem.getDialog(Integer.parseInt(args[0])) != null) {
					DialogueSystem.DisplayDialog(player, DialogueSystem.getDialog(Integer.parseInt(args[0])));
					return true;
				}
			}
		}

		return false;
	}
	
}
