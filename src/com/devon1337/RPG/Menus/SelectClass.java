package com.devon1337.RPG.Menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import com.devon1337.RPG.Classes.GroupClass;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.IMenu;
import com.devon1337.RPG.Utils.InventoryAssistant;
import com.devon1337.RPG.Utils.Menu;

public class SelectClass extends Menu implements InventoryHolder, IMenu {

	public static String Title = ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Select a Class";
	private Inventory SC;

	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return SC;
	}

	public IMenu getIMenu() {
		return (IMenu) this;
	}
	
	public SelectClass() {
		super(Title);
		super.setMenu(this);
	}

	public void init_items() {
		int i = 0;
		for (GroupClass gc : GroupClass.getClasses()) {
			SC.setItem(i, gc.getItem());
			i++;
		}

	}

	public String getTitle() {
		return Title;
	}

	@Override
	public boolean Response(NFPlayer player, int slot) {
		// TODO Auto-generated method stub
		player.setPClass(GroupClass.getClass(slot));
		player.getPlayerFromUUID().closeInventory();
		return false;
	}

	@Override
	public int getPage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Inventory open(Player player) {
		SC = Bukkit.createInventory(this, InventoryAssistant.getInventorySize(1), Title);
		init_items();
		return this.SC;
	}

}