package com.devon1337.RPG.Menus;

import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.FastTravel;
import com.devon1337.RPG.Utils.IMenu;
import com.devon1337.RPG.Utils.InventoryAssistant;
import com.devon1337.RPG.Utils.Menu;
import com.devon1337.RPG.Utils.NFTType;
import com.devon1337.RPG.Utils.Point;

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

public class FastTravelUI extends Menu implements InventoryHolder, IMenu {

	private Inventory FTUI;
	public static String Title = ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Fast Travel...";
	int Page;
	ArrayList<Point> availableTeleports = new ArrayList<Point>();

	public FastTravelUI() {
		super(Title, false, null);
		super.setMenu(this);
	}

	// Creates Items used for GUI
	public void init_items(Player player) {
		this.FTUI = Bukkit.createInventory(this, InventoryAssistant.getInventorySize(3), FastTravelUI.Title);
		availableTeleports.clear();
		for (int i = 0; i < FastTravel.grabList().size(); i++) {
			if ((player.hasPermission("nightfall.nftravel.druid")
					&& FastTravel.getPerciseWayPoint(i).getType() == NFTType.Druid)
					|| (player.hasPermission("nightfall.nftravel.mage")
							&& FastTravel.getPerciseWayPoint(i).getType() == NFTType.Mage)
					|| (player.hasPermission("nightfall.nftravel.gm")
							&& FastTravel.getPerciseWayPoint(i).getType() == NFTType.GM)
					|| player.isOp() || (FastTravel.getPerciseWayPoint(i).getType() == NFTType.Engine && player.hasPermission("nightfall.nftravel.engine"))) {
				this.FTUI.setItem(i, createGuiItem(FastTravel.getPerciseWayPoint(i).getBlock(), 1,
						FastTravel.getPerciseWayPoint(i).getName(), new String[] { "Click to Travel!" }));
				this.availableTeleports.add(FastTravel.getPerciseWayPoint(i));
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

	@Override
	public boolean Response(NFPlayer player, int slot) {
		Point tpLoc = availableTeleports.get(slot);
		Bukkit.getPlayer(player.getUUID()).teleport(tpLoc.getLocation());
		return false;
	}

	@Override
	public int getPage() {
		// TODO Auto-generated method stub
		return this.Page;
	}

	@Override
	public Inventory open(Player player) {
		init_items(player);
		return this.FTUI;
	}

	public IMenu getIMenu() {
		return (IMenu) this;
	}

	public Inventory getInventory() {
		return this.FTUI;
	}

}