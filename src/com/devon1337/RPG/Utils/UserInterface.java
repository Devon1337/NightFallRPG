package com.devon1337.RPG.Utils;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;

public class UserInterface {

	Scoreboard board;
	
	public UserInterface() {
		 board = Bukkit.getScoreboardManager().getNewScoreboard();
		 
	}
	
	
}
