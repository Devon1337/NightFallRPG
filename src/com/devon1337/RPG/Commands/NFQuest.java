package com.devon1337.RPG.Commands;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class NFQuest implements CommandExecutor {
	
	public static ArrayList<Player> qbUpdate = new ArrayList<>();

	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		/*
		Player player = null;
		int QuestID = 0, Stage = 0;
		if (sender instanceof Player) {
			player = (Player) sender;
		}
		
		*/
		return false;
	}

	public static void generateNewQuestBook(Player player) {
		if (qbUpdate.contains(player)) {
			player.getInventory().setItem(2, createQuestBook(player));
			qbUpdate.remove(player);
		}
	}

	public static void addPlayer(Player player) {
		if (!qbUpdate.contains(player)) {
			qbUpdate.add(player);
		}
	}

	public static ItemStack createQuestBook(Player player) {

		ArrayList<String> tx = new ArrayList<>();
		tx.add("dont-hide");

		ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta bookMeta = (BookMeta) writtenBook.getItemMeta();
		bookMeta.setTitle("Quests");
		bookMeta.setAuthor("NightFallRPG");
		bookMeta.setLore(tx);
		//bookMeta.setPages(pages);
		writtenBook.setItemMeta((ItemMeta) bookMeta);

		return writtenBook;
	}
}