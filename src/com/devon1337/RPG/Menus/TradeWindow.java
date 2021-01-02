package com.devon1337.RPG.Menus;

import com.devon1337.RPG.Utils.InventoryAssistant;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class TradeWindow implements InventoryHolder {
	private final Inventory tradeWindow;
	public final String TITLE = ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Trade Window";

	public TradeWindow() {
		this.tradeWindow = Bukkit.createInventory(this, InventoryAssistant.getInventorySize(3), this.TITLE);
	}

	public void openTradeWindow(Player player, Player target) {
		player.openInventory(this.tradeWindow);
		target.openInventory(this.tradeWindow);
	}

	public Inventory getInventory() {
		return null;
	}
}