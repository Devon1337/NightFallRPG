package com.devon1337.RPG.Menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import com.devon1337.RPG.Classes.GroupClass;
import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.InventoryAssistant;

// Used for selecting the players class
public class SelectClass implements InventoryHolder {
	
	public static final String TITLE = ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Select a Class";
	private Inventory scMenu = Bukkit.createInventory(this, InventoryAssistant.getInventorySize(1),
			TITLE);
	Player player;
	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return scMenu;
	}
	
	public SelectClass(Player player) {
		this.player = player;
		init_items();
		player.openInventory(this.scMenu);
	}

	public void init_items() {
		int i = 1;
		for(GroupClass gc : GroupClass.getClasses()) {
			scMenu.setItem(i, gc.getItem());
			i++;
		}
		
	}
	
	public static String getTitle() {
		return TITLE;
	}
	
	public static void response(ItemStack selectedItem, Player player) {
		for(GroupClass gc : GroupClass.getClasses()) {
			Logging.OutputToPlayer("Do the names equal?: " + selectedItem.getItemMeta().getDisplayName().equals(gc.getItem().getItemMeta().getDisplayName()), player);
			if(selectedItem.getItemMeta().getDisplayName().equals(gc.getItem().getItemMeta().getDisplayName())) {
				Logging.OutputToPlayer("You have selected the class: " + gc.getName(), player);
				NFPlayer.getPlayer(player.getUniqueId()).addClass(gc);
				player.closeInventory();
			}
		}
	}
	
	
	
}