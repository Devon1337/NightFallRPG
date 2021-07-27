package com.devon1337.RPG.Commands;

import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.Menu;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PickClass implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		Player player = (Player) sender;
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1.0F, 1.3F);
		if(sender instanceof Player) {
			Menu menu = Menu.getMenu(4);
			menu.openNFInventory(NFPlayer.getPlayer(player.getUniqueId()));
			return true;
		}
		return false;
	}
}