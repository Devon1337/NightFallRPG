package com.devon1337.RPG.Menus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import com.devon1337.RPG.ActiveAbilities.GlobalSpellbook;
import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.PassiveAbilities.Passive;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.IMenu;
import com.devon1337.RPG.Utils.InventoryAssistant;
import com.devon1337.RPG.Utils.Menu;

public class LevelUpMenu extends Menu implements InventoryHolder, IMenu {

	private Inventory LUM;

	public static String Title = "Level up!";
	int Page;

	public LevelUpMenu() {
		super(Title, false, null);
		super.setMenu(this);
	}

	public void init_items(Player player) {
		int i = 0;

		// Checks if player is null
		if (player == null) {
			Logging.OutputToConsole("WARNING PLAYER IS NULLED");
		}

		// grabs spells players gets during level up
		for (Spell s : GlobalSpellbook.getSpells()) {
			if (s.getLevel() == NFPlayer.getPlayer(player.getUniqueId()).getLevel()
					&& s.getSpellClass() == NFPlayer.getPlayer(player.getUniqueId()).getPClass().getClassEnum()) {
				this.LUM.setItem(11 + (2 * i), s.getItem());
				i++;
			}

		}

		// grabs passives players gets during level up
		for (Passive p : GlobalSpellbook.getPassives()) {
			if (p.getLevel() == NFPlayer.getPlayer(player.getUniqueId()).getLevel()) {
				this.LUM.setItem(i, p.getItem());
				i++;
			}

		}
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

	@Override
	public Inventory open(Player player) {
		this.LUM = Bukkit.createInventory(this, InventoryAssistant.getInventorySize(3), Title);
		init_items(player);
		return this.LUM;
	}
}