package com.devon1337.RPG.Menus.LevelUpMenus;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.PassiveAbilities.Passive;

import lombok.Getter;

public class Icon {

	@Getter
	Spell spell;
	
	@Getter
	Passive passive;
	
	@Getter
	ItemStack item;
	@Getter
	ItemStack deniedItem;
	
	@Getter
	String title, description;
	
	@Getter
	final Material DENIED_MATERIAL = Material.BARRIER;
	final String DENIED_PASSIVE = "You cannot unlock this passive!", DENIED_SPELL = "You cannot unlock this spell!";
	
	public Icon(Spell spell, String title, String description) {
		this.spell = spell;
		this.title = title;
		this.description = description;
		
		// Please make this not repeat :(
		// Generates ItemStacks to represent the Icons
		this.item = new ItemStack(spell.getIcon(), spell.getLevel());
		this.deniedItem = new ItemStack(spell.getIcon(), spell.getLevel());

		// Grabs the Meta Data from the ItemStacks
		ItemMeta meta = item.getItemMeta();
		ItemMeta deniedMeta = deniedItem.getItemMeta();

		// Applies the Title to the Meta Data
		meta.setDisplayName(title);
		deniedMeta.setDisplayName(DENIED_SPELL);

		// Applies the Description to the Meta Data
		ArrayList<String> temp = new ArrayList<String>();
		temp.add(description);
		meta.setLore(temp);

		// Applies the Meta Data to the ItemStacks
		this.item.setItemMeta(meta);
		this.deniedItem.setItemMeta(deniedMeta);
		
		
	}
	
	public Icon(Passive passive, String title, String description) {
		this.passive = passive;
		this.title = title;
		this.description = description;
		
		// Please make this not repeat :(
		// Generates ItemStacks to represent the Icons
		this.item = new ItemStack(spell.getIcon(), spell.getLevel());
		this.deniedItem = new ItemStack(spell.getIcon(), spell.getLevel());

		// Grabs the Meta Data from the ItemStacks
		ItemMeta meta = item.getItemMeta();
		ItemMeta deniedMeta = deniedItem.getItemMeta();

		// Applies the Title to the Meta Data
		meta.setDisplayName(title);
		deniedMeta.setDisplayName(DENIED_PASSIVE);

		// Applies the Description to the Meta Data
		ArrayList<String> temp = new ArrayList<String>();
		temp.add(description);
		meta.setLore(temp);

		// Applies the Meta Data to the ItemStacks
		this.item.setItemMeta(meta);
		this.deniedItem.setItemMeta(deniedMeta);
	}
	
}
