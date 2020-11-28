package com.devon1337.RPG.WeaponEffects;

import org.bukkit.NamespacedKey;

public interface IPrefix {

	public NamespacedKey getKey();
	public float adjustWeaponType(float x);
	public IPrefix getIPrefix();
	public float getMaxMod();
	public float getMinMod();
	public void setModifier(float modifierAmount);
}
