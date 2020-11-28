package com.devon1337.RPG.Commands;

import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.NPC.NPC;
import com.devon1337.RPG.Objects.EasterEggs.CappaTheShopStand;
import com.devon1337.RPG.Utils.DialogueSystem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NFInteractWith implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		Player player = Bukkit.getPlayerExact(args[0]);
		Logging.OutputToConsole((Player) sender + "");
		Logging.OutputToConsole(args[0]);

		if (player != null) {
			String str;
			switch ((str = args[1]).hashCode()) {
			case 94429507:
				if (!str.equals("cappa"))
					break;
				DialogueSystem.DisplayDialog(player, CappaTheShopStand.talkTo(player), new NPC("Cappa the Shop Stand"));
				return true;
			}

		}
		return false;
	}
}
