package com.devon1337.RPG.Menus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.Objects.NFObject;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.IMenu;
import com.devon1337.RPG.Utils.InventoryAssistant;
import com.devon1337.RPG.Utils.Menu;

public class CreativeObjectMenu extends Menu implements InventoryHolder, IMenu{

	private Inventory COM;
	public static String Title = "Creative Object Menu [Debug]";
	int Page;
	
	public CreativeObjectMenu() {
		super(Title);
		super.setMenu(this);
	}
	
	public void init_items() {
		this.COM = Bukkit.createInventory(this, InventoryAssistant.getInventorySize(3), CreativeObjectMenu.Title);
		int i = 0;
		for(NFObject nfo : NFObject.getAllObjects()) {
			Logging.OutputToConsole("Object: " + nfo.getName());
			this.COM.setItem(i, createGuiItem(nfo.getItem().getType(), 1, nfo.getName(), nfo.getDescription()));
			i++;
		}
	}
	
	private ItemStack createGuiItem(Material material, int amount, String name, String Description) {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		ArrayList<String> metalore = new ArrayList<>();
		
		metalore.add("Item Description: ");
		metalore.add(Description);

		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });

		meta.setLore(metalore);
		item.setItemMeta(meta);
		return item;
	}

	public boolean Response(NFPlayer player, int index) {
		Bukkit.getPlayer(player.getUUID()).getInventory().addItem(NFObject.getObject(index).getItem());
		return false;
	}

	public Inventory open(Player player) {
		init_items();
		return this.COM;
	}
	
	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return this.COM;
	}
	
	public IMenu getIMenu() {
		return (IMenu) this;
	}
	
	public int getPage() {
		return this.Page;
	}
	
	
}
