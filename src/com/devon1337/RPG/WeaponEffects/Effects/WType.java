package com.devon1337.RPG.WeaponEffects.Effects;

import com.devon1337.RPG.WeaponEffects.IWeaponType;

public abstract class WType {

	String name;
	IWeaponType iType;
	
	public WType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public IWeaponType getIWeapon() {
		return this.iType;
	}
	
	public void setIWeapon(IWeaponType iType) {
		this.iType = iType;
	}
	
}
