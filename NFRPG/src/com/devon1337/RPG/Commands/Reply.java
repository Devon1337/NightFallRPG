package com.devon1337.RPG.Commands;

import com.devon1337.RPG.NPC.NPCCodes;
import com.devon1337.RPG.NPC.Warrior.BlankNPC;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.Dialog.Dialog;
import com.devon1337.RPG.Utils.Dialog.DialogFlags;
import com.devon1337.RPG.Utils.Dialog.DialogueSystem;

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
			Dialog dlog = new Dialog(msg, new BlankNPC(NPCCodes.PLAYER_MESSAGE, player.getName()));
			dlog.addFlag(DialogFlags.MESSAGE);
			dlog.addFlag(DialogFlags.FORCE_LEFT);
			DialogueSystem.DisplayDialog(NFPlayer.getPlayer(player.getUniqueId()), dlog);
			dlog.removeFlag(DialogFlags.FORCE_LEFT);
			DialogueSystem.DisplayDialog(NFPlayer.getPlayer(target.getUniqueId()), dlog);
			return true;
		}

		return false;
	}
}