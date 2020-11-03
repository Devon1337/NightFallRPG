package com.devon1337.RPG.Utils.Events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AreaMappedEvent extends Event implements Cancellable {
	
	
	
	   private static final HandlerList HANDLERS = new HandlerList();
	   
	   public HandlerList getHandlers() {
	     return HANDLERS;
	   }
	 
	   public boolean isCancelled() {
	     return false;
	   }
	   
	   public void setCancelled(boolean arg0) {}

}