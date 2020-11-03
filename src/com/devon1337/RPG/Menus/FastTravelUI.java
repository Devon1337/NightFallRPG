package com.devon1337.RPG.Menus;

import com.devon1337.RPG.Utils.FastTravel;
import com.devon1337.RPG.Utils.InventoryAssistant;
import com.devon1337.RPG.Utils.NFTType;
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

public class FastTravelUI implements InventoryHolder {
	private final Inventory FTUI;
	public final String TITLE = ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Fast Travel...";

	public FastTravelUI(Player player) {
		this.FTUI = Bukkit.createInventory(this, InventoryAssistant.getInventorySize(1), this.TITLE);
		init_items(player);
	}

	public void init_items(Player player) {
		for (int i = 0; i < FastTravel.grabList().size(); i++) {
			if ((player.hasPermission("nightfall.nftravel.druid")
					&& FastTravel.getWayPoint(i).getType() == NFTType.Druid)
					|| (player.hasPermission("nightfall.nftravel.mage")
							&& FastTravel.getWayPoint(i).getType() == NFTType.Mage)
					|| (player.hasPermission("nightfall.nftravel.gm")
							&& FastTravel.getWayPoint(i).getType() == NFTType.GM)
					|| player.isOp()) {
				this.FTUI.setItem(i, createGuiItem(FastTravel.getWayPoint(i).getBlock(), 1,
						FastTravel.getWayPoint(i).getName(), new String[] { "Click to Travel!" }));
			}
		}
	}

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
		return this.FTUI;
	}
}