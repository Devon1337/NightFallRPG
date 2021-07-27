package com.devon1337.RPG.Commands;

import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.Utils.NFTType;
import com.devon1337.RPG.Utils.Point;
import com.devon1337.RPG.raid.MatchmakingController;
import com.devon1337.RPG.raid.NFRaid;
import com.devon1337.RPG.raid.Raid;
import java.util.HashMap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NFRaidMaker implements CommandExecutor {
	HashMap<Player, Raid> editorSelection = new HashMap<>();

	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (sender instanceof Player || args.length > 0) {
			Player player = (Player) sender;
			String str;
			switch ((str = args[0]).hashCode()) {
			case -1247033510:
				if (!str.equals("addSpawn")) {
					break;
				}

				if (this.editorSelection.containsKey(player)) {
					((Raid) this.editorSelection.get(player))
							.addSpawn(new Point(player, "raid_temp_name", NFTType.Raid));
					Logging.OutputToPlayer("You have created a spawn point!", player);
				}
				return true;
			case -906021636:
				if (!str.equals("select"))
					break;
				this.editorSelection.put(player, MatchmakingController.getRaid(NFRaid.getString(args[1])));
				Logging.OutputToPlayer(
						"You have selected " + MatchmakingController.getRaid(NFRaid.getString(args[1])).getName() + "!",
						player);
				return true;
			case -868304044:
				if (!str.equals("toggle"))
					break;
				if (this.editorSelection.containsKey(player)) {
					((Raid) this.editorSelection.get(player))
							.setEnabled(!((Raid) this.editorSelection.get(player)).isEnabled());
					if (!((Raid) this.editorSelection.get(player)).isEnabled()) {
						Logging.OutputToPlayer(
								"You have disabled " + ((Raid) this.editorSelection.get(player)).getName()
										+ " from matchmaking. Happy Trails Partner ~+",
								player);
					} else {
						Logging.OutputToPlayer("You have enabled " + ((Raid) this.editorSelection.get(player)).getName()
								+ " from matchmaking. Goodnight gamer ~-", player);
					}
				}
				return true;
			case -304759465:
				if (!str.equals("removeSpawn"))
					break;
				return true;
			case 108960:
				if (!str.equals("new"))
					break;
				MatchmakingController.addRaid(new Raid(NFRaid.getString(args[1]), args[1]));
				Logging.OutputToPlayer("You have created the raid!", player);
				return true;
			case 3267882:
				if (!str.equals("join"))
					break;
				if (this.editorSelection.containsKey(player)) {
					Logging.OutputToPlayer("You are in the raid editor right now, please deselect your current raid!",
							player);
				} else {
					MatchmakingController.addToQueue(player, MatchmakingController.getRaid(NFRaid.getString(args[1])));
					Logging.OutputToPlayer("You have the queue for: "
							+ MatchmakingController.getRaid(NFRaid.getString(args[1])).getName(), player);
				}
				return true;
			case 1019878589:
				if (!str.equals("deselect"))
					break;
				if (this.editorSelection.containsKey(player)) {
					this.editorSelection.remove(player);
					Logging.OutputToPlayer("You have deselected your current raid.", player);
				}
				return true;
			}

		}
		return false;
	}
}