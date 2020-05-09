package com.devon1337.RPG.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.devon1337.RPG.Menus.TradeWindow;

public class Trade implements CommandExecutor {

	
	
	@Override
	public boolean onCommand(CommandSender sender,  Command cmd,  String arg2, String[] args) {
		Player player = (Player) sender;
		
		if(args.length > 0) {
			TradeWindow tradeWindow = new TradeWindow();
			
			if(Bukkit.getServer().getPlayer(args[0]) != null && Bukkit.getServer().getPlayer(args[0]).isOnline()) {
				tradeWindow.openTradeWindow(player, Bukkit.getServer().getPlayer(args[0]));
				return true;
			}
		} else {
			player.sendMessage(ChatColor.DARK_RED + "Incorrect Usage: /trade {UserName}");
		}
		
		// TODO Auto-generated method stub
		return false;
	}

}
