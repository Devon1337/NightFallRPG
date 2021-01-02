package com.devon1337.RPG.Commands;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.Menu;

public class NFQuest implements CommandExecutor {
	
	public static ArrayList<Player> qbUpdate = new ArrayList<>();

	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;
			Menu menu = Menu.getMenu(6);
			menu.openNFInventory(NFPlayer.getPlayer(player.getUniqueId()));
			return true;
		}
		
		return false;
	}

	public static void generateNewQuestBook(Player player) {
		if (qbUpdate.contains(player)) {
			player.getInventory().setItem(4, createQuestBook(player));
			qbUpdate.remove(player);
		}
	}

	public static void addPlayer(Player player) {
		if (!qbUpdate.contains(player)) {
			qbUpdate.add(player);
		}
	}

	public static ItemStack createQuestBook(Player player) {


		ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta bookMeta = (BookMeta) writtenBook.getItemMeta();
		bookMeta.setTitle("Quests");
		bookMeta.setAuthor("NightFallRPG");
		NamespacedKey key = new NamespacedKey(NightFallRPG.getPlugin(), "nightfallrpg-questbook");
		bookMeta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 1);;
		//bookMeta.setPages(pages);
		writtenBook.setItemMeta((ItemMeta) bookMeta);

		return writtenBook;
	}
}