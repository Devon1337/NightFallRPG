package com.devon1337.RPG.Commands;

import com.devon1337.RPG.NPC.NPCCodes;
import com.devon1337.RPG.NPC.Warrior.BlankNPC;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.Dialog.Dialog;
import com.devon1337.RPG.Utils.Dialog.DialogFlags;
import com.devon1337.RPG.Utils.Dialog.DialogueSystem;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NFMessage implements CommandExecutor {
	public static HashMap<UUID, UUID> currentReply = new HashMap<>();

	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player player = (Player) sender;
		Player target = Bukkit.getPlayerExact(args[0]);
		String msg = "";
		for (int i = 1; i < args.length; i++) {
			msg = String.valueOf(msg) + " " + args[i];
		}
		if (sender instanceof Player && args.length >= 2 && target != null) {
			Dialog dlog = new Dialog(msg, new BlankNPC(NPCCodes.PLAYER_MESSAGE, player.getName()));
			dlog.addFlag(DialogFlags.MESSAGE);
			dlog.addFlag(DialogFlags.FORCE_LEFT);
			DialogueSystem.DisplayDialog(NFPlayer.getPlayer(player.getUniqueId()), dlog);
			dlog.removeFlag(DialogFlags.FORCE_LEFT);
			DialogueSystem.DisplayDialog(NFPlayer.getPlayer(target.getUniqueId()), dlog);
			currentReply.put(player.getUniqueId(), target.getUniqueId());
			currentReply.put(target.getUniqueId(), player.getUniqueId());
			return true;
		}

		return false;
	}

	public static Player getCurrentReply(Player player) {
		if (currentReply.containsKey(player.getUniqueId())) {
			return Bukkit.getPlayer(currentReply.get(player.getUniqueId()));
		}
		return null;
	}
}
