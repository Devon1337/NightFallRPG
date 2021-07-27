package com.devon1337.RPG.Utils.Raycast;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.Debugging.Logging;

public class RaycastHitEvent extends Event {

	Location endPosition;
	UUID shooter, target;
	Spell spell;
	
	public RaycastHitEvent(UUID shooter, UUID target, Spell spell, Arrow arrow) {
		Logging.OutputToConsole("Shooter: " + shooter + " target: " + target + " spell: " + spell.getId());
		this.shooter = shooter;
		this.target = target;
		this.spell = spell;
		this.endPosition = Bukkit.getPlayer(target).getLocation();
		arrow.remove();
		Bukkit.getPluginManager().callEvent(this);
	}
	
	// Handler data
	private static final HandlerList HANDLERS = new HandlerList();

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
    
    public Spell getSpell() {
    	return this.spell;
    }
    
    public Location getLocation() {
    	return this.endPosition;
    }
    
    public UUID getTarget() {
    	return this.target;
    }
    
    public UUID getShooter() {
    	return this.shooter;
    }
    
    
	
}
