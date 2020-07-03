package com.devon1337.RPG.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.devon1337.RPG.Utils.PartySystem;

public class Party implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player player = (Player) sender;
		
		if(args.length == 1 && args[0].equals("create") && !PartySystem.inParty(player)) {
			PartySystem.createParty(player);
			return true;
		} else if(args.length == 1 && args[0].equals("leave") && PartySystem.inParty(player)) {
			PartySystem.removeParty(player);
			return true;
		} else if(args.length == 2 && args[0].equals("invite") && PartySystem.inParty(player)) {
			if(Bukkit.getPlayerExact(args[1]) != null) {
				PartySystem.sendInvite(player, Bukkit.getPlayerExact(args[1]));
			return true;
			}
		} else if(args.length == 1 && args[0].equals("join") && !PartySystem.inParty(player)) {
			PartySystem.acceptInvite(player);
			return true;
		} else if(args.length == 1 && args[0].equals("decline") && !PartySystem.inParty(player)) {
			PartySystem.declineInvite(player);
			return true;
		} else if(args.length == 1 && args[0].equals("list") && PartySystem.inParty(player)) {
			PartySystem.listParty(PartySystem.getId(player), player);
			return true;
		}
		
		
		return false;
	}

}
