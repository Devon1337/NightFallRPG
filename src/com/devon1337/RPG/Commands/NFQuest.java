package com.devon1337.RPG.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import com.devon1337.RPG.Quests.Quest;
import com.devon1337.RPG.Quests.QuestTracker;

public class NFQuest implements CommandExecutor {

	QuestTracker qt = new QuestTracker();
	public static ArrayList<Player> qbUpdate = new ArrayList<Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
			if (arg3.length == 1) {
				System.out.println("player has quest "
						+ QuestTracker.hasQuest(player, QuestTracker.grabQuest(Integer.parseInt(arg3[0]))));
			}
		} else if (arg3.length >= 2) {
			player = Bukkit.getPlayerExact(arg3[1]);
		}

		if (arg3.length == 1 && !QuestTracker.hasQuest(player, QuestTracker.grabQuest(Integer.parseInt(arg3[0])))
				&& player != null && !arg3[0].equalsIgnoreCase("confirm")) {

			qt.addQuest(player, new Quest(QuestTracker.grabQuest(Integer.parseInt(arg3[0])), player, 1,
					QuestTracker.grabQuest(Integer.parseInt(arg3[0])).getStepAmount()));
			return true;
		} else if (arg3.length == 2 && player != null
				&& !QuestTracker.hasQuest(player, QuestTracker.grabQuest(Integer.parseInt(arg3[0])))) {
			qt.addQuest(player,
					new Quest(QuestTracker.grabQuest(Integer.parseInt(arg3[0])), Bukkit.getPlayerExact(arg3[1]), 1,
							QuestTracker.grabQuest(Integer.parseInt(arg3[0])).getStepAmount()));
			return true;
		} else if (arg3.length == 2 && player == null
				&& !QuestTracker.hasQuest(player, QuestTracker.grabQuest(Integer.parseInt(arg3[0])))) {
			System.out.println("Player doesnt exist!");
			// player.sendMessage("Player does not exist!");
			return true;
		} else if (arg3.length == 0 && player != null) {
			createQuestBook(player);
			return true;
		} else if (arg3.length == 3 && QuestTracker.hasQuest(player, QuestTracker.grabQuest(Integer.parseInt(arg3[2])))
				&& player != null && arg3[0].equalsIgnoreCase("confirm")
				&& QuestTracker.playersQuest(Integer.parseInt(arg3[2]), player).getStatus() != 2) {
			QuestTracker.playersQuest(Integer.parseInt(arg3[2]), player).setStatus(2);
			return true;
		}

		return false;
	}

	public static void generateNewQuestBook(Player player) {
		//player.getInventory().setItem(3, new ItemStack(Material.AIR));
		if(qbUpdate.contains(player)) {
		player.getInventory().setItem(2, createQuestBook(player));
		qbUpdate.remove(player);
		}
	}

	public static void addPlayer(Player player) {
		if(!qbUpdate.contains(player)) {
			qbUpdate.add(player);
		}
		
	}

	public static ItemStack createQuestBook(Player player) {
		ArrayList<String> pages = new ArrayList<String>();

		for (int i = 0; i < QuestTracker.getQuests(player).size(); i++) {
			if (QuestTracker.getQuests(player).get(i).Status == 1) {
				pages.add(ChatColor.GOLD + QuestTracker.getQuests(player).get(i).getTitle() + "\n\n"
						+ ChatColor.DARK_PURPLE + QuestTracker.getQuests(player).get(i).Description);
			}
		}

		for (int i = 0; i < QuestTracker.getQuests(player).size(); i++) {
			if (QuestTracker.getQuests(player).get(i).Status == 2) {
				pages.add(ChatColor.GRAY + QuestTracker.getQuests(player).get(i).getTitle() + "\n\n" + ChatColor.GRAY
						+ QuestTracker.getQuests(player).get(i).Description);
			}
		}

		ArrayList<String> tx = new ArrayList<String>();
		tx.add("dont-hide");
		
		ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta bookMeta = (BookMeta) writtenBook.getItemMeta();
		bookMeta.setTitle("Quests");
		bookMeta.setAuthor("NightFallRPG");
		bookMeta.setLore(tx);
		bookMeta.setPages(pages);
		writtenBook.setItemMeta(bookMeta);

		return writtenBook;
	}

}