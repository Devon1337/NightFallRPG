package com.devon1337.RPG.Objects;


import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.Menus.QuestMenu;
import com.devon1337.RPG.Player.NFPlayer;

public class QuestBookObject extends NFObject implements IObject {
	
	static String Name = "Player Questbook!";
	static final Material DEFAULT_MAT = Material.WRITTEN_BOOK;
	static final ItemStack item = generateItem();
	static String Description = " ";
	
	public QuestBookObject() {
		super(item, Name, Description);
		super.setObj(this);
		super.setItem(item);
		// TODO Auto-generated constructor stub
	}
	
	public static ItemStack generateItem() {
		ItemStack item = new ItemStack(DEFAULT_MAT, 1);
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(NightFallRPG.getPlugin(), "nightfallrpg-questbook");
		meta.setDisplayName(Name);
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 1);
		item.setItemMeta(meta);
		return item;
	}

	@Override
	public void onLeftClick(NFPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRightClick(NFPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHover(NFPlayer player) {
		// TODO Auto-generated method stub
		QuestMenu.getMenu(6).openNFInventory(player);
	}

}
