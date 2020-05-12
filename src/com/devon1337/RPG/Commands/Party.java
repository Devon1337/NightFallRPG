package com.devon1337.RPG.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.devon1337.RPG.Utils.PartySystem;

public class Party implements CommandExecutor {

	public static PartySystem pSys = new PartySystem();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player player = (Player) sender;
		
		if(args.length == 1 && args[0].equals("create") && !pSys.inParty(player)) {
			pSys.createParty(player);
			return true;
		} else if(args.length == 1 && args[0].equals("leave") && pSys.inParty(player)) {
			pSys.removeParty(player);
			return true;
		} else if(args.length == 2 && args[0].equals("invite") && pSys.inParty(player)) {
			if(Bukkit.getPlayerExact(args[1]) != null) {
			pSys.sendInvite(player, Bukkit.getPlayerExact(args[1]));
			return true;
			}
		} else if(args.length == 1 && args[0].equals("join") && !pSys.inParty(player)) {
			pSys.acceptInvite(player);
			return true;
		} else if(args.length == 1 && args[0].equals("decline") && !pSys.inParty(player)) {
			pSys.declineInvite(player);
			return true;
		} else if(args.length == 1 && args[0].equals("list") && pSys.inParty(player)) {
			pSys.listParty(pSys.getId(player), player);
			return true;
		}
		
		
		return false;
	}

}
