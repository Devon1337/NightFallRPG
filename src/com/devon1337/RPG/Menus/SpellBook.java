package com.devon1337.RPG.Menus;

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

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.ActiveAbilities.ActiveAbilityManager;
import com.devon1337.RPG.ActiveAbilities.NFAbilities;
import com.devon1337.RPG.Player.PlayerUtils;
import com.devon1337.RPG.Utils.InventoryAssistant;

public class SpellBook implements InventoryHolder {

	private final Inventory spellBook;
	public final String TITLE = ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Spell Book";
	public PlayerUtils Putils = new PlayerUtils();
	public InventoryAssistant utils = new InventoryAssistant();
	public boolean assignMode = false;
	
	public SpellBook() {
		spellBook = Bukkit.createInventory(this, utils.getInventorySize(3), TITLE);
	}
	
	@SuppressWarnings("deprecation")
	public void init_items(NFClasses classes) {
		spellBook.clear();
		ArrayList<NFAbilities> abilities = ActiveAbilityManager.getClassSpells(classes);
		
		for(int i = 0; i < abilities.size(); i++) {
			spellBook.setItem(2*(i), createGuiItem(ActiveAbilityManager.getAbilitiesItem(abilities.get(i)), 1, ChatColor.GREEN + "" + abilities.get(i), "Will add description value later"));
		}
		
		spellBook.setItem(18, createGuiItem(Material.LEGACY_BOOK_AND_QUILL, 1, "Edit Spells"));
	}
	
	public void assignSpells(Player player, int slot, NFClasses classes) {
		
		//ArrayList<NFAbilities> abilities = ActiveAbilityManager.getClassSpells(classes);
		
		
		
		player.getInventory().setItem(slot, createGuiItem(Material.BARRIER, 1, "Spell Slot 1"));
		player.getInventory().setItem(slot, createGuiItem(Material.BARRIER, 1, "Spell Slot 2"));
		player.getInventory().setItem(slot, createGuiItem(Material.BARRIER, 1, "Spell Slot 3"));
	}
	
	public ItemStack createGuiItem(Material material, int amount, String name, String... lore) {
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
		player.openInventory(spellBook);
	}
	
	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return null;
	}

}
