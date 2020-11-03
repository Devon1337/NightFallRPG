package com.devon1337.RPG.Menus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import com.devon1337.RPG.ActiveAbilities.GlobalSpellbook;
import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.PassiveAbilities.Passive;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.InventoryAssistant;


public class LevelUpMenu implements InventoryHolder {
	private final Inventory LUM;

	public final String TITLE = "PICK YOUR SHIT";
	
	public LevelUpMenu(Player player) {
		this.LUM = Bukkit.createInventory(this, InventoryAssistant.getInventorySize(3), this.TITLE);
		init_items(player);
		player.openInventory(LUM);
	}
	
	public void init_items(Player player) {
		int i = 0;
		
		
		// Checks if player is null
		if(player == null) {
			Logging.OutputToConsole("WARNING PLAYER IS NULLED");
		}
		
		// grabs spells players gets during level up
		for(Spell s : GlobalSpellbook.getSpells()) {
			Logging.OutputToConsole("s: " + s.getLevel());
			Logging.OutputToConsole("player level: " + NFPlayer.getPlayer(player.getUniqueId()).getLevel());
			if(s.getLevel() == NFPlayer.getPlayer(player.getUniqueId()).getLevel() && s.getClassReq() == NFPlayer.getPlayer(player.getUniqueId()).getPlayerClass()) {
				this.LUM.setItem(11+(2*i), s.getItem());
				i++;
				}
				
		}
		
		// grabs passives players gets during level up
		for(Passive p : GlobalSpellbook.getPassives()) {
			if(p.getLevel() == NFPlayer.getPlayer(player.getUniqueId()).getLevel()) {
				this.LUM.setItem(i, p.getItem());
				i++;
				}
			
		}
	}

	public void getResponse() {
		
	}
	
	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return null;
	}
}