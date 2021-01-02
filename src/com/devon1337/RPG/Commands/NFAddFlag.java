package com.devon1337.RPG.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.Player.AccountFlags;
import com.devon1337.RPG.Player.NFPlayer;

public class NFAddFlag implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player player = (Player) sender;
		switch(args[0]) {
		case "developer":
			NFPlayer p = NFPlayer.getPlayer(player.getUniqueId());
			if(!p.getFlags().contains(AccountFlags.Developer)) {
				p.getFlags().add(AccountFlags.Developer);
				Logging.OutputToPlayer("Added Developer Flag to account.", player);
			} else {
				p.getFlags().remove(AccountFlags.Developer);
				Logging.OutputToPlayer("Removed Developer Flag to account.", player);
			}
			return true;
			
		}

		return false;
	}

}
