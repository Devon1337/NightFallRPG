package com.devon1337.RPG.Utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.devon1337.RPG.Player.NFPlayer;

public abstract class Menu {

	String TITLE;
	Inventory Inv;
	IMenu IInv;
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
		this.Inv = IInv.open(Bukkit.getPlayer(player.getUUID()));
		p1.openInventory(Inv);
	}
	
	public void runResponse(NFPlayer player, int slot) {
		IInv.Response(player, slot * (1+IInv.getPage()));
	}
	
	public String getTitle() {
		return this.TITLE;
	}
	
	public void setIMenu(IMenu IInv) {
		this.IInv = IInv;
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
