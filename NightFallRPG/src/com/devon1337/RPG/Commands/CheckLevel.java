package com.devon1337.RPG.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.Player.PlayerLevel;
import com.devon1337.RPG.Player.Exceptions.IncorrectTarget;

public class CheckLevel implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		// TODO Auto-generated method stub
		Player player = (Player) sender;
		Player target = null;
		if(sender instanceof Player) {
			if(args.length > 0) {
				switch(args[0]) {
				
				// level check {username}
				case "check":
					
					if(args.length == 2) {
						target = player;
					} else if(args.length == 3) {
						if(NightFallRPG.getOfflinePlayer(args[1]) != null) {
							target = (Player) NightFallRPG.getOfflinePlayer(args[1]);
						} else {
							player.sendMessage(ChatColor.DARK_RED + "Player not found!");
							return true;
						}
					} else {
						player.sendMessage(ChatColor.DARK_RED + "Too many arguments: /level check {username}");
						return true;
					}
					
					if(target != null) {
					player.sendMessage(target.getName() + " level: " + PlayerLevel.getLevel(target) + " xp: " + PlayerLevel.getXP(target));
					return true;
					} else {
						try {
							throw new IncorrectTarget("Exception: IncorrectTarget -- Target is null!");
						} catch (IncorrectTarget e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					break;
				// level set {username} [level_amount]
				case "set":
					target = null;
					
					
					break;
					
					
				}
			}
		}
		return false;
	}

}
