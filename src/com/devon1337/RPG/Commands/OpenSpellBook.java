package com.devon1337.RPG.Commands;

import com.devon1337.RPG.Menus.SpellBook;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenSpellBook implements CommandExecutor {

	@SuppressWarnings("unused")
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		SpellBook spellbook = new SpellBook((Player) sender);
		return true;
	}
}