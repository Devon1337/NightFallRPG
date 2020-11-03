package com.devon1337.RPG.Menus;

import com.devon1337.RPG.Utils.InventoryAssistant;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GameMasterMenu implements InventoryHolder {
	@SuppressWarnings("unused")
	private final Inventory GMUI;

	public GameMasterMenu(Player player) {
		this.GMUI = Bukkit.createInventory(this, InventoryAssistant.getInventorySize(1),
				ChatColor.DARK_PURPLE + "Welcome " + player.getName());
	}

	public void init_items(Player player) {
	}

	@SuppressWarnings("unused")
	private ItemStack createGuiItem(Material material, int amount, String name, String... lore) {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		ArrayList<String> metalore = new ArrayList<>();
		byte b;
		int i;
		String[] arrayOfString;
		for (i = (arrayOfString = lore).length, b = 0; b < i;) {
			String lorecomments = arrayOfString[b];
			metalore.add(lorecomments);
			b++;
		}

		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		meta.setLore(metalore);
		item.setItemMeta(meta);
		return item;
	}

	public Inventory getInventory() {
		return null;
	}
}
