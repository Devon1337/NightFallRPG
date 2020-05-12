package com.devon1337.RPG.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.devon1337.RPG.Classes.ClassManager;
import com.devon1337.RPG.Menus.SpellBook;

public class OpenSpellBook implements CommandExecutor {

	public static SpellBook spellbook = new SpellBook();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		// TODO Auto-generated method stub
		spellbook.init_items(ClassManager.getTeam((Player) sender));
		spellbook.openInventory((Player) sender);
		return true;
	}

	
	
}
