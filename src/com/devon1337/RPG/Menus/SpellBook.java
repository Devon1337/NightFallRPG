package com.devon1337.RPG.Menus;

import com.devon1337.RPG.ActiveAbilities.GlobalSpellbook;
import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Player.PlayerUtils;
import com.devon1337.RPG.Utils.InventoryAssistant;
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

public class SpellBook implements InventoryHolder {
	private Inventory spellBook;
	public final String TITLE = ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Spell Book";
	public PlayerUtils Putils = new PlayerUtils();
	public boolean assignMode = false;

	public SpellBook(Player player) {
		this.spellBook = Bukkit.createInventory(this, InventoryAssistant.getInventorySize(3), this.TITLE);
		init_items(NFPlayer.getPlayer(player.getUniqueId()).getPlayerClass(), player);
		player.openInventory(this.spellBook);
	}

	@SuppressWarnings("deprecation")
	public void init_items(NFClasses classes, Player player) {
		this.spellBook.clear();
		int i = 0;
		for (Spell s : GlobalSpellbook.getSpells()) {
			Logging.OutputToPlayer("Spell Name:" + s.getName(), player);
			Logging.OutputToPlayer("Spell Class:" + s.getClassReq(), player);
			Logging.OutputToPlayer("Spell Level:" + s.getLevel(), player);
			Logging.OutputToPlayer("Player Class:" + NFPlayer.getPlayer(player.getUniqueId()).getPlayerClass(), player);
			Logging.OutputToPlayer("Player Level:" + NFPlayer.getPlayer(player.getUniqueId()).getLevel(), player);
			if(s.getClassReq() == classes && s.getLevel() == NFPlayer.getPlayer(player.getUniqueId()).getLevel()) {
				Logging.OutputToPlayer("Adding spell to spellbook", player);
				this.spellBook.setItem(i, s.getItem());
				i++;
			}
			
		}

		this.spellBook.setItem(18, createGuiItem(Material.LEGACY_BOOK_AND_QUILL, 1, "Edit Spells", new String[0]));
	}

	public void assignSpells(Player player, int slot, NFClasses classes) {
		player.getInventory().setItem(slot,
				createGuiItem(Material.BARRIER, 1, "Spell Slot 1", new String[] { "dont-hide" }));
		player.getInventory().setItem(slot,
				createGuiItem(Material.BARRIER, 1, "Spell Slot 2", new String[] { "dont-hide" }));
		player.getInventory().setItem(slot,
				createGuiItem(Material.BARRIER, 1, "Spell Slot 3", new String[] { "dont-hide" }));
	}

	public ItemStack createGuiItem(Material material, int amount, String name, String... lore) {
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

	public void openInventory(Player player) {
		player.openInventory(this.spellBook);
	}

	public Inventory getInventory() {
		return null;
	}
}
