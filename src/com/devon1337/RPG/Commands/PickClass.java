package com.devon1337.RPG.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.devon1337.RPG.Classes.ClassManager;
import com.devon1337.RPG.Menus.SelectClass;

public class PickClass implements CommandExecutor {

	public SelectClass selClass = new SelectClass();
	public ClassManager classes = new ClassManager();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		// TODO Auto-generated method stub

		Player player = (Player) sender;
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, 1.3f);
		if (sender instanceof Player) {
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("r")) {
					classes.isPlayerInTeam(player, true);
				}
			}
			if (!classes.isPlayerInTeam(player, false)) {
				selClass.initializeItems();
				selClass.openInventory(player);
				return true;
			} else {
				player.sendMessage(ChatColor.DARK_RED + "You are already in a team!");
				return true;
			}
		}

		return false;
	}

}