package com.devon1337.RPG.WeaponEffects.Effects;

import com.devon1337.RPG.WeaponEffects.Weapon;

public abstract class WType {

	String name;
	int id;
	Weapon type;
	
	public WType(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
}
