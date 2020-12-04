package com.devon1337.RPG.Menus;

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
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.IMenu;
import com.devon1337.RPG.Utils.InventoryAssistant;
import com.devon1337.RPG.Utils.Menu;

//	Author: Alex

public class BanMenu extends Menu implements InventoryHolder, IMenu {

	private Inventory BanUI;
	public static String Title = "Welcome Admin";
	int Page;

	public BanMenu() {
		super(Title);
		// TODO Auto-generated constructor stub
	}

	public void init_items() {
		this.BanUI = Bukkit.createInventory(this, InventoryAssistant.getInventorySize(3), GameMasterMenu.Title);

		this.BanUI.setItem(0, createGuiItem(Material.DIAMOND_AXE, 1, ChatColor.RED + "Ban", "Bans a player"));
	}

	@SuppressWarnings("unused")
	private ItemStack createGuiItem(Material material, int amount, String name, String... lore) {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		ArrayList<String> metalore = new ArrayList<>();
		// Why? revert if problems occur
//		byte b;
//		int i;
		String[] arrayOfString;
		for (int i = (arrayOfString = lore).length, b = 0; b < i;) {
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

	public IMenu getIMenu() {
		return (IMenu) this;
	}

	@Override
	public void Response(NFPlayer player, int slot) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Inventory open(Player player) {
		if (player.isOp()) {
			init_items();
			return this.BanUI;
		}
		return null;
	}

}
