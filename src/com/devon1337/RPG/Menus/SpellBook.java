package com.devon1337.RPG.Menus;

import com.devon1337.RPG.ActiveAbilities.GlobalSpellbook;
import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Player.PlayerUtils;
import com.devon1337.RPG.Utils.IMenu;
import com.devon1337.RPG.Utils.InventoryAssistant;
import com.devon1337.RPG.Utils.Menu;

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

public class SpellBook extends Menu implements InventoryHolder, IMenu {

	private Inventory SB;
	public static String Title = ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Spell Book";
	int Pages;

	public PlayerUtils Putils = new PlayerUtils();
	public boolean assignMode = false;

	public SpellBook() {
		super(Title);
	}

	@SuppressWarnings("deprecation")
	public void init_items(NFClasses classes, Player player) {
		this.SB.clear();
		int i = 0;
		for (Spell s : GlobalSpellbook.getSpells()) {
			Logging.OutputToPlayer("Spell Name:" + s.getName(), player);
			Logging.OutputToPlayer("Spell Class:" + s.getClassReq(), player);
			Logging.OutputToPlayer("Spell Level:" + s.getLevel(), player);
			Logging.OutputToPlayer("Player Class:" + NFPlayer.getPlayer(player.getUniqueId()).getPlayerClass(), player);
			Logging.OutputToPlayer("Player Level:" + NFPlayer.getPlayer(player.getUniqueId()).getLevel(), player);
			if (s.getClassReq() == classes && s.getLevel() == NFPlayer.getPlayer(player.getUniqueId()).getLevel()) {
				Logging.OutputToPlayer("Adding spell to spellbook", player);
				this.SB.setItem(i, s.getItem());
				i++;
			}

		}

		this.SB.setItem(18, createGuiItem(Material.LEGACY_BOOK_AND_QUILL, 1, "Edit Spells", new String[0]));
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
		player.openInventory(this.SB);
	}

	public Inventory getInventory() {
		return null;
	}
	
	public IMenu getIMenu() {
		return (IMenu) this;
	}

	@Override
	public void Response(NFPlayer player, int slot) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPage() {
		// TODO Auto-generated method stub
		return Pages;
	}

	@Override
	public Inventory open(Player player) {
		// TODO Auto-generated method stub
		SB = Bukkit.createInventory(this, InventoryAssistant.getInventorySize(3), Title);
		NFPlayer p2 = NFPlayer.getPlayer(player.getUniqueId());
		init_items(p2.getPlayerClass(), player);
		return this.SB;
	}
}
