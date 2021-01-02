package com.devon1337.RPG.PassiveAbilities;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.devon1337.RPG.ActiveAbilities.GlobalSpellbook;
import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.Player.NFPlayer;

import lombok.Getter;
import lombok.Setter;

public abstract class Passive implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2281975162444200530L;
	String name, description;
	int level;
	PassiveType pType;
	ItemStack item;
	
	@Getter @Setter
	IPassive passive;
	
	public static Material DefaultIcon = Material.IRON_SWORD;
	
	public Passive(String name, int level, PassiveType pType, Material mat) {
		this.name = name;
		this.level = level;
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
	
	public void run(Spell s, NFPlayer player, NFPlayer target) {
		this.passive.run(s, player, target);
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
