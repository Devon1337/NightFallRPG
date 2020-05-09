package com.devon1337.RPG.Menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import com.devon1337.RPG.Utils.InventoryAssistant;

public class TradeWindow implements InventoryHolder {
	
	private final Inventory tradeWindow;
	public final String TITLE = ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Trade Window";
	public InventoryAssistant utils = new InventoryAssistant();
	
	public TradeWindow() {
		tradeWindow = Bukkit.createInventory(this, utils.getInventorySize(3), TITLE);
	}

	public void openTradeWindow(Player player, Player target) {
		player.openInventory(tradeWindow);
		target.openInventory(tradeWindow);
	}
	
	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
