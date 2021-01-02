package com.devon1337.RPG.Menus;

import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.IMenu;
import com.devon1337.RPG.Utils.InventoryAssistant;
import com.devon1337.RPG.Utils.Menu;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GameMasterMenu extends Menu implements InventoryHolder, IMenu {
	
	private Inventory GMUI;
	public static String Title = "Welcome GameMaster";
	int Page;

	public GameMasterMenu() {
		super(Title);
		super.setMenu(this);
	}

	public void init_items() {
		this.GMUI = Bukkit.createInventory(this, InventoryAssistant.getInventorySize(3), GameMasterMenu.Title);
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
	
	public IMenu getIMenu() {
		return (IMenu) this;
	}

	@Override
	public boolean Response(NFPlayer player, int slot) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getPage() {
		// TODO Auto-generated method stub
		return this.Page;
	}

	@Override
	public Inventory open(Player player) {
		if(player.isOp()) {
			init_items();
			return this.GMUI;
		}
		return null;
		
	}
}
