package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.PassiveAbilities.FluidCasting;

public class ActiveAbilityManager {

	public static HashMap<NFAbilities, Integer> activeAbilities = new HashMap<NFAbilities, Integer>();
	public static HashMap<NFAbilities, Material> abilityItems = new HashMap<NFAbilities, Material>();

	public static HashMap<NFAbilities, Runnable> classLoc = new HashMap<NFAbilities, Runnable>();
	
	
	public FluidCasting fCasting = new FluidCasting();
	
	
	public Assassinate assassinate = new Assassinate();
	public Charge charge = new Charge();
	public Confusion confusion = new Confusion();
	public Vanish vanish = new Vanish();
	public Fireball fireball = new Fireball();
	
	public int getCooldownTime(NFAbilities active, Player player) {
		switch(active) {
		case ASSASSINATE:
			return assassinate.getCooldown(player);
		case CHARGE:
			return charge.getCooldown(player);
		case CONFUSION:
			return confusion.getCooldown(player);
		case VANISH:
			return vanish.getCooldown(player);
		case FIREBALL:
			return fireball.getCooldown(player);
		default:
			break;
		}
		return 0;
	}
	
	public void updateCooldowns() {
		
	}
	
	public void runFluidCasting(Player player) {
		Random rand = new Random();
		
		int randomPercent = rand.nextInt(120);
		if(randomPercent < 20) {
			fCasting.doSelfHeal(player);
			player.sendMessage(ChatColor.GREEN + "you have healed!");
		} else if(randomPercent < 40 && randomPercent > 20) {
			fCasting.doJumpBoost(player);
			player.sendMessage(ChatColor.GREEN + "You can jump higher!");
		} else if(randomPercent < 60 && randomPercent > 40) {
			fCasting.doLevitation(player);
			player.sendMessage(ChatColor.YELLOW + "You are levitating!");
		} else if(randomPercent < 80 && randomPercent > 60) {
			fCasting.doSwiftness(player);
			player.sendMessage(ChatColor.GREEN + "You gained speed!");
		} else if(randomPercent < 100 && randomPercent > 80){
			fCasting.doCurse(player);
			player.sendMessage(ChatColor.RED + "You have been cursed!");
		} else if(randomPercent < 120 && randomPercent > 100) {
			player.sendMessage(ChatColor.GREEN + "No Effect Happened!");
		}
		
	}
	
	public void init_abilities() {
		activeAbilities.put(NFAbilities.ASSASSINATE, assassinate.CLASS_TYPE);
		abilityItems.put(NFAbilities.ASSASSINATE, assassinate.ITEM);
		activeAbilities.put(NFAbilities.CHARGE, charge.CLASS_TYPE);
		abilityItems.put(NFAbilities.CHARGE, charge.ITEM);
		activeAbilities.put(NFAbilities.CONFUSION, confusion.CLASS_TYPE);
		abilityItems.put(NFAbilities.CONFUSION, confusion.ITEM);
		activeAbilities.put(NFAbilities.VANISH, vanish.CLASS_TYPE);
		abilityItems.put(NFAbilities.VANISH, vanish.ITEM);
		activeAbilities.put(NFAbilities.FIREBALL, fireball.CLASS_TYPE);
		abilityItems.put(NFAbilities.FIREBALL, fireball.ITEM);
	}
	
	public static ArrayList<NFAbilities> getClassSpells(NFClasses pClass) {
		int classType = convertClassToInt(pClass);
		ArrayList<NFAbilities> tempAbilities = new ArrayList<NFAbilities>();
		for(HashMap.Entry<NFAbilities, Integer> abilities : activeAbilities.entrySet()) {
			int classTypeTemp = abilities.getValue();
			if(classTypeTemp == classType) {
				tempAbilities.add(abilities.getKey());
			}
		}
		
		return tempAbilities;
	}
	
	public static Material getAbilitiesItem(NFAbilities ability) {
		
		for(HashMap.Entry<NFAbilities, Material> abilities : abilityItems.entrySet()) {
			if(ability == abilities.getKey()) {
				return abilities.getValue();
			}
		}
		
		return Material.BARRIER;
	}
	
	private static int convertClassToInt(NFClasses pClass) {
		switch(pClass) {
		case ROGUE:
			return 1;
		case WARRIOR:
			return 2;
		case MAGE:
			return 3;
		case DRUID:
			return 0;
					
			
		}
		return -1;
	}
	
}
