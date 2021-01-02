package com.devon1337.RPG.Classes;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.ActiveAbilities.GlobalSpellbook;
import com.devon1337.RPG.ActiveAbilities.Spell;

import net.md_5.bungee.api.ChatColor;

public class GroupClass {

	static ArrayList<GroupClass> allClasses = new ArrayList<GroupClass>();
	
	// Add class status in the NFPlayer class but create a class object to store base item details and debug data
	String name, description;
	NFClasses classes;
	ItemStack icon;
	int[] reputation;
	
	public GroupClass(String name, String description, NFClasses classes, Material item, int[] reputation) {
		this.name = name;
		this.description = description;
		this.classes = classes;
		this.icon = generateItem(item, 1);	
		allClasses.add(this);
	}
	
	// Generates the ItemStack for icon usage
	public ItemStack generateItem(Material Item, int count) {
		ItemStack item = new ItemStack(Item, count);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(this.name);
		ArrayList<String> metalore = new ArrayList<String>();
		metalore.add(this.description);
		metalore.add(" ");
		
		
			for (Spell s : getEarlySpells()) {
				if (s != null) {
				metalore.add(s.getName() + ChatColor.DARK_PURPLE + " ~ " + s.getDescription());
				}
			}
		meta.addItemFlags(new ItemFlag[] {ItemFlag.HIDE_ATTRIBUTES});
		meta.setLore(metalore);
		item.setItemMeta(meta);
		return item;
	}
	
	public Spell[] getEarlySpells() {
		Spell[] sTemp = new Spell[3];
		int i = 0;
		for(Spell s : GlobalSpellbook.getSpells()) {
			if(s.getSpellClass() == this.classes && s.getLevel() == 1 && i < 3) {
				sTemp[i] = s;
				i++;
				
			}
		}
		
		return sTemp;
	}
	
	public float getReputation(int index) {
		if(index > reputation.length) return 0.0f;
		return reputation[index];
	}
	
	public ItemStack getItem() {
		return this.icon;
	}
	
	public String getName() { 
		return this.name;
	}
	
	public NFClasses getClassEnum() {
		return this.classes;
	}
	
	// Static Getter/Setter
	public static ArrayList<GroupClass> getClasses() {
		return allClasses;
	}
	
	public static GroupClass getClass(int index) {
		return allClasses.get(index);
	}
	
}
