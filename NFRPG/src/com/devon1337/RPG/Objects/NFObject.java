package com.devon1337.RPG.Objects;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.Player.NFPlayer;

import lombok.Getter;
import lombok.Setter;

public abstract class NFObject {

	int ID;
	String Name, Description;
	
	@Getter @Setter
	ItemStack item;
	static ArrayList<NFObject> Global_Objects = new ArrayList<NFObject>();
	
	@Getter @Setter
	IObject obj;
	
	public NFObject(ItemStack item, String Name, String Description) {
		this.item = item;
		this.Name = Name;
		this.Description = Description;
		this.ID = Global_Objects.size();
		Logging.OutputToConsole("Created object " + Name + "!");
		Global_Objects.add(this);
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
	
	public void rightClickEvent() {
		
	}
	
	public void leftClickEvent() {
		
	}
	
	public void hoverEvent(NFPlayer player) {
		obj.onHover(player);
	}
	
	
	public static NFObject getObject(int index) {
		return Global_Objects.get(index);
	}
	
	
	
}
