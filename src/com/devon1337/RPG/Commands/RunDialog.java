package com.devon1337.RPG.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.Utils.DialogueSystem;

public class RunDialog implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		// TODO Auto-generated method stub
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		Player player = (Player) sender;
		
		if (sender instanceof Player) {
			if(args.length == 1 || (args.length == 3 && args[1].equalsIgnoreCase("input"))) {
				if(DialogueSystem.getDialog(Integer.parseInt(args[0])) != null) {
					if(args.length == 3 && DialogueSystem.hasDialog(player)) {
						DialogueSystem.removeDialog(player);
						player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, 1.3f);
						player.sendMessage("");
						sendFakeMessage(player, args[2]);
						
						scheduler.scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable() {
							@Override
							public void run() {
								DialogueSystem.removeDialog(player);
								DialogueSystem.DisplayDialog(player, DialogueSystem.getDialog(Integer.parseInt(args[0])));
							}
						}, 20 * 1);
					} else {
						DialogueSystem.DisplayDialog(player, DialogueSystem.getDialog(Integer.parseInt(args[0])));
					}
					
					
					return true;
				}
			} else if(args.length == 2 && args[0].equalsIgnoreCase("set")) {
				DialogueSystem.setEdgeDistance(Integer.parseInt(args[1]));
				return true;
			} else if(!DialogueSystem.hasDialog(player)) {
				player.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "You had waited too long...");
				return true;
			}
		}

		return false;
	}
	
	public void sendFakeMessage(Player player, String message) {
		message = message.replace('_', ' ');
		
		player.sendMessage(ChatColor.LIGHT_PURPLE + message);
	}
	
}
