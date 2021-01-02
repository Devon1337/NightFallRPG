package com.devon1337.RPG.Commands;

import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.FastTravel;
import com.devon1337.RPG.Utils.Menu;
import com.devon1337.RPG.Utils.NFTType;
import com.devon1337.RPG.Utils.Point;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NFTravel implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {

		if (sender instanceof Player && args.length >= 1) {
			Player player = (Player) sender;
			switch (args[0]) {

			case "add":
				if (args.length == 3) {
					FastTravel.addWayPoint(new Point(player, args[1], NFTType.getType(args[2])));
				} else {
					player.sendMessage(ChatColor.DARK_RED + "Not enough arguments, /nftravel add {name} {highway}!");
				}
				return true;

			case "remove":
				if (args.length == 2 && FastTravel.getWayPoint(args[1]) != null) {
					FastTravel.removeWayPoint(FastTravel.getWayPoint(args[1]));
				} else {
					player.sendMessage(ChatColor.DARK_RED + "Not enough arguments, /nftravel remove {waypoint_id}!");
				}
				return true;

			case "goto":
				Point p = FastTravel.getWayPoint(args[1]);
				if (args.length == 2 && p != null) {
					player.teleport(p.getLocation());
				}

				return true;
			default:
				player.sendMessage(ChatColor.DARK_RED + "Incorrect syntax, /nftravel {add/remove}!");
				break;
			}

			// Opening Travel UI
		} else if (args.length == 0 && sender instanceof Player) {
			Player player = (Player) sender;
			Menu menu = Menu.getMenu(1);
			menu.openNFInventory(NFPlayer.getPlayer(player.getUniqueId()));
			return true;

		} else {
			sender.sendMessage(ChatColor.DARK_RED + "Incorrect syntax, /nftravel {add/remove}!");

		}

		return false;
	}
}