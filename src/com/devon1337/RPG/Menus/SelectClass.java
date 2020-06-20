package com.devon1337.RPG.Menus;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.Classes.Druid;
import com.devon1337.RPG.Classes.Mage;
import com.devon1337.RPG.Classes.Rogue;
import com.devon1337.RPG.Classes.Warrior;
import com.devon1337.RPG.Player.*;
import com.devon1337.RPG.Utils.InventoryAssistant;

public class SelectClass implements InventoryHolder {

	private final Inventory selClass;
	public final String TITLE[] = {ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Select a Class", ChatColor.DARK_RED + "" + ChatColor.BOLD + " Are you sure about this?", ChatColor.DARK_RED + "" + ChatColor.BOLD + " YOU GAIN NOTHING!" };
	public final String[] OPTIONS = {"Rogue","Mage","Druid","Warrior" };
	public static HashMap<Integer, String> ClassSelection = new HashMap<Integer, String>();
	public final Material[] ITEMS = {Material.DIAMOND_SWORD, Material.STICK, Material.COOKED_BEEF, Material.SHIELD};
	public PlayerUtils Putils = new PlayerUtils();
	public InventoryAssistant utils = new InventoryAssistant();
	public Rogue rogue = new Rogue();
	public Warrior warrior = new Warrior();
	public Druid druid = new Druid();
	public Mage mage = new Mage();

	public SelectClass() {
		selClass = Bukkit.createInventory(this, utils.getInventorySize(1), TITLE[0]);
	}
	
	public void initializeItems() {
		for(int i = 0; i < OPTIONS.length; i++) {
		selClass.setItem((2*i)+1, createGuiItem(ITEMS[i], 1, OPTIONS[i], "Select this class to play " + OPTIONS[i]));
		selClass.setItem(8, createGuiItem(Material.CLOCK, 1,"Deprived", "Select this class to play " + ChatColor.DARK_RED + "Deprived"));
		ClassSelection.put(((2*i)+1), OPTIONS[i]);
		}
	}
	
	public void inputResponse(int Slot, Player player) {
		if(ClassSelection.containsKey(Slot)) {
			player.closeInventory();
			BukkitScheduler scheduler = NightFallRPG.getPlugin().getServer().getScheduler();
			player.sendMessage(ChatColor.GREEN + "You have picked " + ClassSelection.get(Slot) + "!");
			switch(ClassSelection.get(Slot)) {
			case "Rogue":
				if(!rogue.playerExists(player)) {
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + player.getName() + " parent set rogue");
					scheduler.scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable() {

						@Override
						public void run() {
							Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "spawn " + player.getName());
						}

					}, 20 * 1);
				
				rogue.addPlayer(player);
				}
				break;
			case "Druid":
				if(!druid.playerExists(player)) {
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + player.getName() + " parent set druid");
					scheduler.scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable() {

						@Override
						public void run() {
							Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "spawn " + player.getName());
						}

					}, 20 * 1);
					druid.addPlayer(player);
				}
				break;
			case "Warrior":
				if(!warrior.playerExists(player)) {
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + player.getName() + " parent set warrior");
					scheduler.scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable() {

						@Override
						public void run() {
							Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "spawn " + player.getName());
						}

					}, 20 * 1);
					warrior.addPlayer(player);
				}
				break;
			case "Mage":
				if(!mage.playerExists(player)) {
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + player.getName() + " parent set mage");
					scheduler.scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable() {

						@Override
						public void run() {
							Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "spawn " + player.getName());
						}

					}, 20 * 1);
					mage.addPlayer(player);
				}
				break;
			case "Deprived":
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + player.getName() + " parent set deprived");
				scheduler.scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable() {

					@Override
					public void run() {
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "spawn " + player.getName());
					}

				}, 20 * 1);
				break;
			
			}
		}
	}

	// SUMMARY: Updates the users build key
	public void updateKey(int Input, Player player) {
		String newKey = " ";
		for (int i = 0; i < 6; i++) {
			if (i == 0) {
				newKey = Integer.toString(Input);
			}
			newKey = newKey + Putils.getBuildKey(player).charAt(i);
		}
		Putils.setBuildKey(newKey, player);
	}

	// SUMMARY: Create custom names and lore
	private ItemStack createGuiItem(Material material, int amount, String name, String... lore) {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		ArrayList<String> metalore = new ArrayList<String>();

		for (String lorecomments : lore) {
			metalore.add(lorecomments);
		}
		
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		
		meta.setLore(metalore);
		item.setItemMeta(meta);
		return item;
	}

	public void openInventory(Player player) {
		player.openInventory(selClass);
	}

	public String getTitle(int index) { return TITLE[index]; }
	
	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return selClass;
	}

}