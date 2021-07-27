package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import com.devon1337.RPG.Menus.LevelUpMenus.Icon;
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
	
	// Returns all spells and passives as icons
	public static ArrayList<Icon> getAllSorted() {
		ArrayList<Icon> temp = new ArrayList<Icon>();
		
		// Its 3 AM so I'm not worried about speed at this point but
		// TODO: Make this faster plz :)
		for(Spell s : AllSpells) {
			temp.add(new Icon(s, s.getName(), s.getDescription()));
		}
		
		for(Passive p : AllPassives) {
			temp.add(new Icon(p, p.getName(), p.getDescription()));
		}
		
		ArrayList<Icon> temp2 = new ArrayList<Icon>();

		for(int i = 0; i < 5; i++) {
			for(Icon l : temp) {
				if(l.getSpell() != null && l.getSpell().getLevel() == i) {
					temp2.add(l);
				} else if (l.getPassive().getLevel() == i) {
					temp2.add(l);
				}
			}	
		}
		
		return temp2;
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
	
	public static Passive getPassive(String name) {
		for(Passive p : AllPassives) {
			if(p.getName().equals(name)) {
				return p;
			}
		}
		
		return null;
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