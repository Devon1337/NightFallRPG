package com.devon1337.RPG.WeaponEffects.Effects;

import com.devon1337.RPG.WeaponEffects.IPrefix;
import com.devon1337.RPG.WeaponEffects.WeaponPrefix;

public abstract class Prefixes {

	String name;
	WeaponPrefix prefix;
	IPrefix inPre;
	
	public Prefixes(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setIPrefix(IPrefix inPre) {
		this.inPre = inPre;
	}
	
	public IPrefix getIPrefix() {
		return this.inPre;
	}
	
	
}
