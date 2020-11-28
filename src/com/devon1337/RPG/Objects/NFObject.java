package com.devon1337.RPG.Objects;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

import com.devon1337.RPG.Debugging.Logging;

public abstract class NFObject {

	int ID;
	String Name, Description;
	ItemStack item;
	static ArrayList<NFObject> Global_Objects = new ArrayList<NFObject>();
	
	public NFObject(ItemStack item, String Name, String Description) {
		this.item = item;
		this.Name = Name;
		this.Description = Description;
		this.ID = Global_Objects.size();
		Logging.OutputToConsole("Created object " + Name + "!");
		Global_Objects.add(this);
	}
	
	public ItemStack getItem() {
		return this.item;
	}
	
	public int getId() {
		return this.ID;
	}
	
	public String getName() {
		return this.Name;
	}
	
	public String getDescription() {
		return this.Description;
	}
	
	public static ArrayList<NFObject> getAllObjects() {
		return Global_Objects;
	}
	
	public static NFObject getObject(int index) {
		return Global_Objects.get(index);
	}
	
	
	
}
