package com.devon1337.RPG.Commands;

import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.Player.Exceptions.IncorrectTarget;
import com.devon1337.RPG.Player.PlayerLevel;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckLevel implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		Player player = (Player) sender;
		Player target = null;
		if (sender instanceof Player && args.length > 0) {
			String str;
			switch ((str = args[0]).hashCode()) {
			case 113762:
				if (!str.equals("set")) {
					break;
				}

				target = null;
				break;
			case 94627080:
				if (!str.equals("check"))
					break;
				if (args.length == 2) {
					target = player;
				} else if (args.length == 3) {
					if (NightFallRPG.getOfflinePlayer(args[1]) != null) {
						target = (Player) NightFallRPG.getOfflinePlayer(args[1]);
					} else {
						player.sendMessage(ChatColor.DARK_RED + "Player not found!");
						return true;
					}
				} else {
					player.sendMessage(ChatColor.DARK_RED + "Too many arguments: /level check {username}");
					return true;
				}
				if (target != null) {
					player.sendMessage(String.valueOf(target.getName()) + " level: " + PlayerLevel.getLevel(target)
							+ " xp: " + PlayerLevel.getXP(target));
					return true;
				}
				try {
					throw new IncorrectTarget("Exception: IncorrectTarget -- Target is null!");
				} catch (IncorrectTarget e) {
					e.printStackTrace();
					break;
				}
			}

		}
		return false;
	}
}
