package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

public class SpellLoader {

	public static ArrayList<Spell> spells = new ArrayList<Spell>();
	
	public static void run() {
		spells.add(new Assassinate());
		spells.add(new Blood_Shield());
		spells.add(new Charge());
		spells.add(new Confusion());
		spells.add(new Entanglement());
		spells.add(new Fireball());
		spells.add(new MOTW());
		spells.add(new Rejuvenate());
		spells.add(new Shield_Slam());
		spells.add(new Starfire());
		spells.add(new Tranquility());
		spells.add(new Vanish());
		spells.add(new Wrath());
		spells.add(new NightmareSlasher());
		spells.add(new Shield_Bash());
		spells.add(new Empty());
		spells.add(new HeatedJuggernaut());
		spells.add(new Explosive_Strike());
		spells.add(new Plague_Touch());
	}
	
	
}
