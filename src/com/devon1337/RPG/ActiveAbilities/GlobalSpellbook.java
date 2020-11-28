package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import com.devon1337.RPG.PassiveAbilities.Passive;

public class GlobalSpellbook {

	public static ArrayList<Spell> AllSpells = new ArrayList<Spell>();
	public static ArrayList<Passive> AllPassives = new ArrayList<Passive>();
	
	public static void addSpell(Spell s) {
		AllSpells.add(s);
	}
	
	public static void addPassive(Passive p) {
		AllPassives.add(p);
	}
	
	public static int getSpellSize() {
		return AllSpells.size();
	}
	
	public static ArrayList<Spell> getSpells() {	
		return AllSpells;
	}
	
	public static int getPassiveSize() {
		return AllPassives.size();
	}
	
	public static ArrayList<Passive> getPassives() {	
		return AllPassives;
	}
	
	public static Spell getSpell(int id) {
		for(Spell s : AllSpells) {
			if(s.getId() == id) {
				return s;
			}
		}
		
		return null;
	}
	
	public static void updateCooldowns() {
		
	}
	
	
}