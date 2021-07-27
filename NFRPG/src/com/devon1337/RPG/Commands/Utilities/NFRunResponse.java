package com.devon1337.RPG.Commands.Utilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.Dialog.Dialog;
import com.devon1337.RPG.Utils.Dialog.DialogCodes;
import com.devon1337.RPG.Utils.Dialog.DialogueSystem;
import com.devon1337.RPG.Utils.Dialog.Response;

public class NFRunResponse implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		// /runresponse string ENUM int
		if(sender instanceof Player && args.length == 3) {
			
			NFPlayer player = NFPlayer.getPlayer(Bukkit.getPlayer(args[0]).getUniqueId());
			Dialog line = DialogueSystem.getDialogLine(DialogCodes.valueOf(args[1]));
			Response response = line.getResponse()[Integer.parseInt(args[2])];
			
			if(player != null && line != null && response != null && DialogueSystem.hasDialog(player.getPlayer())) {
				
				player.getPlayer().sendMessage(ChatColor.DARK_GRAY + response.getMessage());
				
				DialogueSystem.forceRemoveDialog(player.getPlayer());
				
				if(response.getCommand() != null) {
					String command = DialogueSystem.FormatString(response.getCommand(), player); // Removes %player%-like markers
					Logging.OutputToConsole(command);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
				}
				
			} else if(!DialogueSystem.hasDialog(player.getPlayer())) {
				player.getPlayer().sendMessage(ChatColor.GRAY + "You forgot what you were responding to...");
			}
			
			return true;
			
		}
		
		return false;
		
	}

}
