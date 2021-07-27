package com.devon1337.RPG.Menus.LevelUpMenus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.Menus.MenuIndex;
import com.devon1337.RPG.PassiveAbilities.Passive;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.IMenu;
import com.devon1337.RPG.Utils.InventoryAssistant;
import com.devon1337.RPG.Utils.Menu;

public class DruidLevelUpMenu extends Menu implements InventoryHolder, IMenu {

	private Inventory LUM;

	public static String Title = "Level up!";
	ArrayList<Spell> spells = new ArrayList<>();
	ArrayList<Passive> passives = new ArrayList<>();
	int Page;
	
	// Predefined Variables
	static final Material BACKGROUND_MAT = Material.OAK_LEAVES;
	

	public DruidLevelUpMenu() {
		super(Title, false, MenuIndex.DRUID_LEVELUP);
		super.setMenu(this);
	}

	public void init_items(Player player) {
		//int i = 0;

		// Checks if player is null
		if (player == null) {
			Logging.OutputToConsole("WARNING PLAYER IS NULLED");
		}
		
		// Adds background Material
		for (int j = 0; j < 7; j++) {
			for (int k = 0; k < 9; k++) {
				if ((j == 0 || j == 7) || (!(j == 0 || j == 7) && k == 0 || k == 9)) {
					this.LUM.setItem((9 * k) + j, createGuiItem(BACKGROUND_MAT, 1, " "));
				}
			}
		}
		
		
		
		
		/*
		// grabs spells players gets during level up
		for (Spell s : GlobalSpellbook.getSpells()) {
			if (s.getLevel() < NFPlayer.getPlayer(player.getUniqueId()).getLevel()
					&& s.getSpellClass() == NFPlayer.getPlayer(player.getUniqueId()).getPClass().getClassEnum()) {
				
				spells.add(s);
				
				this.LUM.setItem(11 + (2 * i), s.getItem());
				i++;
			}

		}

		// grabs passives players gets during level up
		for (Passive p : GlobalSpellbook.getPassives()) {
			if (p.getLevel() < NFPlayer.getPlayer(player.getUniqueId()).getLevel()) {
				
				passives.add(p);
				
				this.LUM.setItem(i, p.getItem());
				i++;
			}

		}
		*/
	}

	public IMenu getIMenu() {
		return (IMenu) this;
	}

	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return LUM;
	}

	@Override
	public boolean Response(NFPlayer player, int slot) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getPage() {
		// TODO Auto-generated method stub
		return this.Page;
	}
	
	public ItemStack createGuiItem(Material material, int amount, String name, String... lore) {
		ItemStack item = new ItemStack(material, amount);

		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		item.setItemMeta(meta);
		return item;
	}

	@Override
	public Inventory open(Player player) {
		this.LUM = Bukkit.createInventory(this, InventoryAssistant.getInventorySize(7), Title);
		init_items(player);
		return this.LUM;
	}
}