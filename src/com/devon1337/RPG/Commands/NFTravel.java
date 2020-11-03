package com.devon1337.RPG.Commands;

import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.Menus.FastTravelUI;
import com.devon1337.RPG.Utils.FastTravel;
import com.devon1337.RPG.Utils.NFTType;
import com.devon1337.RPG.Utils.Point;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NFTravel implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player player = (Player) sender;

		Logging.OutputToConsole("nftravel command ran");

		if (sender instanceof Player && args.length >= 1) {
			String str;
			switch ((str = args[0]).hashCode()) {
			case -934610812:
				if (!str.equals("remove")) {
					break;
				}

				if (args.length == 2) {
					FastTravel.removeWayPoint(FastTravel.getWayPoint(Integer.parseInt(args[1])));
				} else {
					Logging.OutputToPlayer("Not enough arguments, /nftravel remove {waypoint_id}", player);
				}
				return true;

			case 3732:
				if (!str.equals("ui")) {
					break;
				}
				player.openInventory((new FastTravelUI(player)).getInventory());
				return true;
			case 96417:
				if (!str.equals("add"))
					break;
				if (args.length == 3) {
					FastTravel.addWayPoint(new Point(player, args[1], NFTType.getType(args[2])));
				} else {
					Logging.OutputToPlayer("Not enough arguments, /nftravel add {name} {highway}", player);
				}
				return true;
			case 3178851:
				if (!str.equals("goto"))
					break;
				if (args.length == 2) {
					FastTravel.warpToPoint(player, FastTravel.getWayPoint(Integer.parseInt(args[1])));
				} else {
					Logging.OutputToPlayer("Not enough arguments, /nftravel goto {waypoint_id}", player);
				}
				return true;
			case 3322014:
				if (!str.equals("list"))
					break;
				FastTravel.listWaypoints(player);
				return true;
			}
		}
		return false;
	}
}