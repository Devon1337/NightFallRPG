package com.devon1337.RPG.Commands;

import com.devon1337.RPG.NPC.Warrior.BlankNPC;
import com.devon1337.RPG.Utils.Dialog;
import com.devon1337.RPG.Utils.DialogFlags;
import com.devon1337.RPG.Utils.DialogueSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reply implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player player = (Player) sender;
		Player target = NFMessage.getCurrentReply(player);
		String msg = "";
		for (int i = 0; i < args.length; i++) {
			msg = String.valueOf(msg) + " " + args[i];
		}

		if (sender instanceof Player && args.length >= 1 && target != null) {
			Dialog dlog = new Dialog(msg, new BlankNPC("PLAYER_MESSAGE", player.getName()));
			dlog.addFlag(DialogFlags.MESSAGE);
			dlog.addFlag(DialogFlags.FORCE_LEFT);
			DialogueSystem.DisplayDialog(player, dlog, new BlankNPC("PLAYER_MESSAGE", player.getName()));
			dlog.removeFlag(DialogFlags.FORCE_LEFT);
			DialogueSystem.DisplayDialog(target, dlog, new BlankNPC("PLAYER_MESSAGE", player.getName()));
			return true;
		}

		return false;
	}
}