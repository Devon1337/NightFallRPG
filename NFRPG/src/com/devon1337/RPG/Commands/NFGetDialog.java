package com.devon1337.RPG.Commands;

import com.devon1337.RPG.NPC.NPC;
import com.devon1337.RPG.NPC.NPCCodes;
import com.devon1337.RPG.NPC.WorldController;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.Dialog.DialogueSystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NFGetDialog implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player player = (Player) sender;
		if (sender instanceof Player) {
			if (args.length == 1 && WorldController.npcExist(NPCCodes.valueOf(args[0])) != null) {
				NPC npc = WorldController.npcExist(NPCCodes.valueOf(args[0]));

				DialogueSystem.DisplayDialog(NFPlayer.getPlayer(player.getUniqueId()), npc.getActiveDialog(player));
				return true;
			}
			if (args.length == 1 && WorldController.npcExist(NPCCodes.valueOf(args[0])) == null) {
				player.sendMessage("NPC does not exist!");
			} else if (args.length == 3 && args[0].equalsIgnoreCase("set")) {
				WorldController.npcExist(NPCCodes.valueOf(args[1]));
			}

		}

		return false;
	}
}