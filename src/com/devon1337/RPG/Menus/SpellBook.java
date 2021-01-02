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

import lombok.Getter;
import lombok.Setter;

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
	int Pages = 0;

	public PlayerUtils Putils = new PlayerUtils();
	@Getter @Setter
	public boolean assignMode = false;
	
	@Getter @Setter
	int spellSlot;
	
	public SpellBook() {
		super(Title);
		super.setMenu(this);
	}
	
	public void init_items(NFClasses classes, Player player) {
		this.SB.clear();
		
		int i = 10;
		for (Spell s : GlobalSpellbook.getSpells()) {
			if (s.getSpellClass() == classes && s.getLevel() == NFPlayer.getPlayer(player.getUniqueId()).getLevel()) {
				this.SB.setItem(i, s.getItem());
				i+=2;
			}
		}
		
		for (i = 0; i < 27; i++) {
			if(!(i == 10 || i == 12 || i == 14 || i == 16)) {
				this.SB.setItem(i, createGuiItem(Material.GRAY_STAINED_GLASS_PANE, 1, " ", ""));
			}
		}
	}

	public ItemStack createGuiItem(Material material, int amount, String name, String... lore) {
		ItemStack item = new ItemStack(material, amount);

		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
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
	public boolean Response(NFPlayer player, int slot) {
		// TODO Auto-generated method stub
		
		// Picking the Spell
		if(!this.isAssignMode() && this.SB.getContents()[slot] != null) {
			Logging.OutputToPlayer("Assign mode on " + slot, player.getPlayer());
			this.spellSlot = slot;
			this.setAssignMode(true);
			return true;
			
		// Assigning the Spell	
		} else if(this.isAssignMode() && player.getPlayer().getInventory().getContents()[slot].getType() == Material.BARRIER) {
			Logging.OutputToPlayer("Assign mode off " + this.SB.getItem(spellSlot).getItemMeta().getDisplayName(), player.getPlayer());
			player.getPlayer().getInventory().setItem(slot, this.SB.getItem(spellSlot));
			this.setAssignMode(false);
			return true;
		}
		
		return false;
		
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
		init_items(p2.getPClass().getClassEnum(), player);
		return this.SB;
	}
}
