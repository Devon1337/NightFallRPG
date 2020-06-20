package com.devon1337.RPG.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.devon1337.RPG.Utils.FriendsList;

public class FLCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender,  Command cmd,  String arg2, String[] args) {
		Player player = (Player) sender;
		
		if(sender instanceof Player) {
			if(args.length == 2 && args[0].equalsIgnoreCase("add")) {
				Player target = Bukkit.getPlayerExact(args[1]);
				if(target != null) {
					FriendsList.addFriend(player, target);
				} else {
					player.sendMessage(ChatColor.RED + "Player does not exist!");
				}
			} else if(args.length == 2 && args[0].equalsIgnoreCase("remove")) {
				Player target = Bukkit.getPlayerExact(args[1]);
				if(target != null) {
					FriendsList.removeFriend(player, target);
				} else {
					player.sendMessage(ChatColor.RED + "Player does not exist!");
				}
			}
			return true;
		}
		
		return false;
	}
	
}
