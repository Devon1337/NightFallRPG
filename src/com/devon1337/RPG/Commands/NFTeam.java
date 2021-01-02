package com.devon1337.RPG.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.devon1337.RPG.Classes.GroupClass;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.FastTravel;

public class NFTeam implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player player = (Player) sender;

		if (sender instanceof Player && args.length == 3 && args[0].equals("join")) {
			switch (args[1]) {
			case "druid":
				
				NFPlayer.getPlayer(player.getUniqueId()).setPClass(GroupClass.getClass(0));
				player.teleport(FastTravel.getWayPoint(args[2]).getLocation());
				
				return true;
			case "mage":
				NFPlayer.getPlayer(player.getUniqueId()).setPClass(GroupClass.getClass(1));
				player.teleport(FastTravel.getWayPoint(args[2]).getLocation());
				return true;
			case "warrior":
				NFPlayer.getPlayer(player.getUniqueId()).setPClass(GroupClass.getClass(2));
				player.teleport(FastTravel.getWayPoint(args[2]).getLocation());
				return true;
			case "rogue":
				NFPlayer.getPlayer(player.getUniqueId()).setPClass(GroupClass.getClass(3));
				player.teleport(FastTravel.getWayPoint(args[2]).getLocation());
				return true;
			}
		}

		return false;
	}

}
