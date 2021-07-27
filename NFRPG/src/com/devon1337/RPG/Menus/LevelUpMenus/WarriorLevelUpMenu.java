package com.devon1337.RPG.Menus.LevelUpMenus;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.ActiveAbilities.GlobalSpellbook;
import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.Menus.MenuIndex;
import com.devon1337.RPG.PassiveAbilities.Passive;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.IMenu;
import com.devon1337.RPG.Utils.InventoryAssistant;
import com.devon1337.RPG.Utils.Menu;

import lombok.Getter;

public class WarriorLevelUpMenu extends Menu implements InventoryHolder, IMenu {

	@Getter
	private Inventory levelUpMenu;

	public static String Title = "Level up!";
	// An Icon will be used to display the players available/unavailable spells.
	private HashMap<Integer, Icon> IconMapping = new HashMap<Integer, Icon>();
	int Page;
	
	// Predefined Variables
	static final Material BACKGROUND_MAT = Material.SAND;
	

	public WarriorLevelUpMenu() {
		super(Title, false, MenuIndex.WARRIOR_LEVELUP);
		super.setMenu(this);
	}

	public void init_items(NFPlayer player) {
		// Checks if player is null
		if (player == null) {
			Logging.OutputToConsole("WARNING PLAYER IS NULLED");
		}
		
		// Player Details
		int playerLevel = player.getLevel();
		NFClasses team = player.getPClass().getClassEnum();
		
		// Adds background Material
		for (int j = 0; j < 5; j++) {
			for (int k = 0; k < 9; k++) {
				if ((j == 0 || j == 7) || (!(j == 0 || j == 7) && k == 0 || k == 9)) {
					this.levelUpMenu.setItem((9 * k) + j, createGuiItem(BACKGROUND_MAT, 1, " "));
				}
			}
		}
		
		// grabs spells players gets during level up
		for (Icon i : GlobalSpellbook.getAllSorted()) {

			Spell s = i.getSpell();
			Passive p = i.getPassive();

			// Used to determine if its a spell or passive and if it belongs to said team
			if (s != null && s.getSpellClass() == team) {

				// Runs through available slots in the menu
				for (int l = 0; l < this.levelUpMenu.getSize(); l++) {

					// Applies correct icon
					if ((s.getLevel() <= playerLevel)) {
						this.IconMapping.put(l, i);
						this.levelUpMenu.setItem(l, i.getItem());

					} else if ((s.getLevel() > playerLevel)) {
						this.IconMapping.put(l, i);
						this.levelUpMenu.setItem(l, i.getDeniedItem());

					}
				}

				// Runs if its a passive ability
			} else if (p.getTeam() == team) {

				// Runs through available slots in the menu
				for (int l = 0; l < this.levelUpMenu.getSize(); l++) {

					// Applies correct icon
					if ((p.getLevel() <= playerLevel)) {
						this.IconMapping.put(l, i);
						this.levelUpMenu.setItem(l, i.getItem());

					} else if ((p.getLevel() > playerLevel)) {
						this.IconMapping.put(l, i);
						this.levelUpMenu.setItem(l, i.getDeniedItem());

					}
				}

			}
		}
	}

	public IMenu getIMenu() {
		return (IMenu) this;
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
		this.levelUpMenu = Bukkit.createInventory(this, InventoryAssistant.getInventorySize(6), Title);
		init_items(NFPlayer.getPlayer(player.getUniqueId()));
		return this.levelUpMenu;
	}
}