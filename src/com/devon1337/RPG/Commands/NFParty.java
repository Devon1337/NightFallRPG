package com.devon1337.RPG.Commands;

import com.devon1337.RPG.Utils.PartySystem;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NFParty implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player player = (Player) sender;

		// Old Code

//		if (args.length == 1 && args[0].equals("create") && !PartySystem.inParty(player)) {
//			PartySystem.createParty(player);
//			return true;
//		}
//		if (args.length == 1 && args[0].equals("leave") && PartySystem.inParty(player)) {
//			PartySystem.removeParty(player);
//			return true;
//		}
//		if (args.length == 2 && args[0].equals("invite") && PartySystem.inParty(player)) {
//			if (Bukkit.getPlayerExact(args[1]) != null) {
//				PartySystem.sendInvite(player, Bukkit.getPlayerExact(args[1]));
//				return true;
//			}
//		} else {
//			if (args.length == 1 && args[0].equals("join") && !PartySystem.inParty(player)) {
//				PartySystem.acceptInvite(player);
//				return true;
//			}
//			if (args.length == 1 && args[0].equals("decline") && !PartySystem.inParty(player)) {
//				PartySystem.declineInvite(player);
//				return true;
//			}
//			if (args.length == 1 && args[0].equals("list") && PartySystem.inParty(player)) {
//				PartySystem.listParty(PartySystem.getId(player), player);
//				return true;
//			}
//		}

		// Start of new code
		if (args.length == 1) {
			switch (args[0]) {

			case "leave":
				if (PartySystem.inParty(player)) {
					ArrayList<Player> partyMembers = PartySystem.getParty(PartySystem.getId(player));
					// Removes remaining member from party if you leave a party of 2
					if (partyMembers.size() == 2) {
						for (int i = 0; i < partyMembers.size(); i++) {
							PartySystem.removeParty(partyMembers.get(i));
						}
					} else {
						PartySystem.removeParty(player);
					}

					return true;
				} else {
					player.sendMessage(ChatColor.DARK_RED + "You are not in a party!");
				}
				break;

			case "list":
				if (PartySystem.inParty(player)) {
					PartySystem.listParty(PartySystem.getId(player), player);
					return true;
				} else {
					player.sendMessage(ChatColor.DARK_RED + "You are not in a party!");
				}
				break;

			case "join":
				if (!PartySystem.inParty(player)) {
					PartySystem.acceptInvite(player);
					return true;
				} else {
					player.sendMessage(ChatColor.DARK_RED + "You are already in a party!");
				}
				break;

			case "decline":
				if (!PartySystem.inParty(player)) {
					PartySystem.declineInvite(player);
					return true;
				} else {
					player.sendMessage(ChatColor.DARK_RED + "No pending Invites!");
				}
				break;

			}
			// TODO: Swap invite to switch and add kick player function
		} else if (args.length == 2 && args[0].equals("invite") && !PartySystem.inParty(Bukkit.getPlayerExact(args[1]))) {
			// Create party
			if (!PartySystem.inParty(player)) {
				PartySystem.createParty(player);
			}
			// invites target player from args[1]
			if (Bukkit.getPlayerExact(args[1]) != null) {
				PartySystem.sendInvite(player, Bukkit.getPlayerExact(args[1]));
				return true;
			}
		} else {
			player.sendMessage(ChatColor.DARK_RED + "Invalid Command!");
		}
		
		return true;
	}
}