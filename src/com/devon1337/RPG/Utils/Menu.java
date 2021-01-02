package com.devon1337.RPG.Utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.devon1337.RPG.Player.NFPlayer;

import lombok.Getter;
import lombok.Setter;

public abstract class Menu {

	String TITLE;
	Inventory Inv;
	
	@Getter @Setter
	IMenu menu;
	int ID;
	
	static ArrayList<Menu> Global_Menu = new ArrayList<Menu>();
	
	public Menu(String TITLE) {
		this.TITLE = TITLE;
		this.ID = Global_Menu.size();
		Global_Menu.add(this);
	}
	
	public void openNFInventory(NFPlayer player) {
		Player p1 = Bukkit.getPlayer(player.getUUID());
		player.setMenu(this);
		this.Inv = menu.open(Bukkit.getPlayer(player.getUUID()));
		
		// Checks if the player has permission the enter the menu!
		if (this.Inv != null) {
			p1.openInventory(Inv);
		} else {
			p1.sendMessage(ChatColor.DARK_RED + "You do not have access to this menu!");
		}
	}
	
	public void runResponse(NFPlayer player, int slot, InventoryClickEvent e) {
		e.setCancelled(!menu.Response(player, slot * (1+menu.getPage())));
	}
	
	public String getTitle() {
		return this.TITLE;
	}
	
	public Inventory getInventory() {
		return this.Inv;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public static Menu getMenu(int ID) {
		for(Menu menu : Global_Menu) {
			if(menu.getID() == ID) {
				return menu;
			}
		}
		
		return null;
	}
	
	public static Menu getMenuInv(Inventory Inv) {
		for(Menu menu : Global_Menu) {
			if(menu.getInventory().equals(Inv)) {
				return menu;
			}
		}
		return null;
	}
	
}
