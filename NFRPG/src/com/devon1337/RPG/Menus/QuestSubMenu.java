package com.devon1337.RPG.Menus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Quests.Quest;
import com.devon1337.RPG.Quests.Step;
import com.devon1337.RPG.Utils.IMenu;
import com.devon1337.RPG.Utils.InventoryAssistant;
import com.devon1337.RPG.Utils.Menu;

import lombok.Getter;
import lombok.Setter;

public class QuestSubMenu extends Menu implements InventoryHolder, IMenu {

	@Getter
	private Inventory inv;
	public static String Title = "QUEST_SUB_MENU";
	@Getter @Setter
	Quest q;
	
	public QuestSubMenu() {
		super(Title, true, null);
		super.setMenu(this);
	}

	@Override
	public boolean Response(NFPlayer player, int slot) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getPage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Inventory open(Player player) {
		this.inv = Bukkit.createInventory(this, InventoryAssistant.getInventorySize(1), q.getName());
		init_items(q);
		return this.inv;
	}
	
	public void init_items(Quest q) {
		int i = 0;
		Material mat = Material.BARRIER;
		for(Step s : q.getSteps()) {
			
			
			switch(s.getStatus()) {
			case Completed:
				mat = Material.GREEN_WOOL;
				break;
			case Active:
				mat = Material.ORANGE_WOOL;
				break;
			case Inactive:
				mat = Material.GRAY_WOOL;
				break;
			case Failed:
				mat = Material.RED_WOOL;
				break;
			}
			
			inv.setItem(i, createQuestItem(mat, s));
			i++;
		}
	}
	
	private ItemStack createQuestItem(Material material, Step s) {
		ItemStack item = new ItemStack(material, 1);
		ItemMeta meta = item.getItemMeta();
		if(s.getReq().isRequired()) {
			meta.setDisplayName(s.getTitle());
		} else {
			meta.setDisplayName("[Optional] " + s.getTitle());
		}
		ArrayList<String> metalore = new ArrayList<>();
		
		metalore.add("Quest Description: ");
		metalore.add("- " + s.getDescription());

		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });

		meta.setLore(metalore);
		item.setItemMeta(meta);
		return item;
	}
	
}
