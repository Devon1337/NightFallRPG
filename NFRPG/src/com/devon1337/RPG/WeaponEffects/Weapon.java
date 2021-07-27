package com.devon1337.RPG.WeaponEffects;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import com.devon1337.RPG.WeaponEffects.Effects.Prefixes;
import com.devon1337.RPG.WeaponEffects.Effects.WType;

public class Weapon {

	ArrayList<WType> types;
	ArrayList<Prefixes> prefixes;
	String Name = "";
	ItemStack item;
	Random rand = new Random();
	
	public Weapon(ArrayList<WType> types, ArrayList<Prefixes> prefixes, Material item_mat) {
		this.types = types;
		this.prefixes = prefixes;
		this.item = createGuiItem(item_mat, 1);
	}
	
	private ItemStack createGuiItem(Material material, int amount) {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		
		for(Prefixes p : prefixes) {
			rand = new Random((long) p.getIPrefix().getMaxMod());
			float modifierValue = rand.nextFloat() + p.getIPrefix().getMinMod();
			this.Name += p.getName() + " ";
			meta.getPersistentDataContainer().set(p.getIPrefix().getKey(), PersistentDataType.FLOAT, modifierValue);
		}
		
		for(WType wp : types) {
			this.Name += wp.getName() + " ";
		}
		
		this.Name += material.toString().toLowerCase().replace('_', ' ') + " ";
		
		meta.setDisplayName(this.Name);
		
		
		
		ArrayList<String> metalore = new ArrayList<>();

		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });

		meta.setLore(metalore);
		item.setItemMeta(meta);
		return item;
	}
	
	public ItemStack getItem() {
		return this.item;
	}
	
	public String getName() {
		return this.Name;
	}
	
	
}
