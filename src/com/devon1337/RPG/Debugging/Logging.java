package com.devon1337.RPG.Debugging;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Logging {

	public static Player player = Bukkit.getPlayerExact("Devon1337");
	
	public static void OutputToPlayer(String Message) {
		player.sendMessage("[NFRPG-Verbose] " + Message);
	}
	
	public static void OutputToConsole(String Message) {
		System.out.println("[NFRPG-Verbose] " + Message);
	}
	
	
}
