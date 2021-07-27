package com.devon1337.RPG.Commands;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Roll implements CommandExecutor {
	public Random rand = new Random();

	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player player = (Player) sender;

		if (args.length < 1) {
			Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "" + ChatColor.ITALIC + player.getName()
					+ " has rolled " + simRoll() + " [1-100]");
			return true;
		}
		if (args.length == 1 && Integer.parseInt(args[0]) != 0 && Integer.parseInt(args[0]) < 20001
				&& Integer.parseInt(args[0]) > 1) {
			if (Integer.parseInt(args[0]) == 420) {
				Bukkit.getServer().broadcastMessage(ChatColor.DARK_GREEN + "" + ChatColor.ITALIC + player.getName()
						+ " has rolled " + simRollCustom(Integer.parseInt(args[0])) + " [1-" + args[0] + "]");
			} else if (Integer.parseInt(args[0]) == 69) {
				String message = player.getName() + " has rolled " + simRollCustom(Integer.parseInt(args[0])) + " [1-"
						+ args[0] + "]";
				Bukkit.getServer().broadcastMessage(rainbowShader(message));
			} else if (Integer.parseInt(args[0]) == 666) {
				Bukkit.getServer()
						.broadcastMessage(ChatColor.DARK_RED + "" + ChatColor.ITALIC + ChatColor.MAGIC
								+ player.getName() + " has rolled " + ChatColor.RESET + ChatColor.DARK_RED
								+ ChatColor.ITALIC + simRollCustom(Integer.parseInt(args[0])) + " [1-" + args[0] + "]");
			} else if (Integer.parseInt(args[0]) == 1337) {
				String message = player.getName() + " has rolled " + simRollCustom(Integer.parseInt(args[0])) + " [1-"
						+ args[0] + "]";
				Bukkit.getServer().broadcastMessage(hackerShader(message));
			} else {
				Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "" + ChatColor.ITALIC + player.getName()
						+ " has rolled " + simRollCustom(Integer.parseInt(args[0])) + " [1-" + args[0] + "]");
			}
			return true;
		}
		return false;
	}

	public int simRoll() {
		return this.rand.nextInt(100) + 1;
	}

	public int simRollCustom(int number) {
		return this.rand.nextInt(number) + 1;
	}

	public String hackerShader(String message) {
		String tempMessage = "";

		int numberString = this.rand.nextInt(4) + 1;

		for (int i = 0; i < message.length(); i++) {

			if (numberString == 1 || numberString == 2) {
				tempMessage = String.valueOf(tempMessage) + ChatColor.GREEN + message.charAt(i);
			}
			if (numberString == 4 || numberString == 5 || numberString == 3) {
				tempMessage = String.valueOf(tempMessage) + ChatColor.BLACK + message.charAt(i);
			}
			numberString = this.rand.nextInt(3) + 1;
		}

		return tempMessage;
	}

	public String rainbowShader(String message) {
		String tempMessage = "";

		int numberString = this.rand.nextInt(6) + 1;

		for (int i = 0; i < message.length(); i++) {

			if (numberString == 1) {
				tempMessage = String.valueOf(tempMessage) + ChatColor.GREEN + message.charAt(i);
			} else if (numberString == 2) {
				tempMessage = String.valueOf(tempMessage) + ChatColor.BLUE + message.charAt(i);
			} else if (numberString == 3) {
				tempMessage = String.valueOf(tempMessage) + ChatColor.AQUA + message.charAt(i);
			} else if (numberString == 4) {
				tempMessage = String.valueOf(tempMessage) + ChatColor.YELLOW + message.charAt(i);
			} else if (numberString == 5) {
				tempMessage = String.valueOf(tempMessage) + ChatColor.RED + message.charAt(i);
			} else if (numberString == 6) {
				tempMessage = String.valueOf(tempMessage) + ChatColor.AQUA + message.charAt(i);
			} else if (numberString == 7) {
				tempMessage = String.valueOf(tempMessage) + ChatColor.BLUE + message.charAt(i);
				numberString = 0;
			}

			numberString++;
		}

		return tempMessage;
	}
}
