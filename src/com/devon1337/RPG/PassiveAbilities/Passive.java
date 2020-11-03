package com.devon1337.RPG.PassiveAbilities;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.ActiveAbilities.GlobalSpellbook;
import com.devon1337.RPG.Player.NFPlayer;

public abstract class Passive implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2281975162444200530L;
	String name, description;
	int id, level;
	NFClasses pClass;
	PassiveType pType;
	ItemStack item;
	
	
	public static Material DefaultIcon = Material.IRON_SWORD;
	
	public Passive(String name, int id, int level, NFClasses pClass, PassiveType pType, Material mat) {
		this.name = name;
		this.id = id;
		this.level = level;
		this.pClass = pClass;
		this.pType = pType;
		item = generateItem(mat, 1, this);
		GlobalSpellbook.addPassive(this);
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public ItemStack getItem() {
		return this.item;
	}
	
	public PassiveType getType() {
		return this.pType;
	}
	
	@SuppressWarnings("incomplete-switch")
	public void run(NFPlayer player, double DamageAmount) {
		switch(this.pType) {
		case Lifesteal:
			Lifesteal.run(player, DamageAmount, level);
			break;
		}
	}

	
	public static ItemStack generateItem(Material Item, int count, Passive p) {
		ItemStack item = new ItemStack(Item, count);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(p.getName());
		ArrayList<String> metalore = new ArrayList<String>();
		metalore.add(p.getDescription());
		meta.addItemFlags(new ItemFlag[] {ItemFlag.HIDE_ATTRIBUTES});
		meta.setLore(metalore);
		item.setItemMeta(meta);
		return item;
	}
	
	@SuppressWarnings("static-access")
	public Material getIcon() {
		return this.DefaultIcon;
	}
	
	
}
