package com.devon1337.RPG.WeaponEffects;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class WeaponDropEvent extends Event implements Cancellable {
	
		
		public Weapon droppedItem;
	
	   private static final HandlerList HANDLERS = new HandlerList();
	   
	   public HandlerList getHandlers() {
	     return HANDLERS;
	   }
	 
	   public boolean isCancelled() {
	     return false;
	   }
	   
	   public void setCancelled(boolean arg0) {}
}
