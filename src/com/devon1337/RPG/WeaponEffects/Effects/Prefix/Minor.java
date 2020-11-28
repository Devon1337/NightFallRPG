package com.devon1337.RPG.WeaponEffects.Effects.Prefix;

import org.bukkit.NamespacedKey;

import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.WeaponEffects.IPrefix;
import com.devon1337.RPG.WeaponEffects.Effects.Prefixes;

public class Minor extends Prefixes implements IPrefix {

	static String Name = "Minor";
	static NamespacedKey key = new NamespacedKey(NightFallRPG.getPlugin(), "weapon-prefix-minor");
	float modifierAmount = 0;
	final static float minMod = 1.1f, maxMod = 3.0f;
	
	public Minor() {
		super(Name);
	}
	
	public float adjustWeaponType(float x) {
		return x/modifierAmount;
	}
	
	public NamespacedKey getKey() {
		return key;
	}
	
	public IPrefix getIPrefix() {
		return (IPrefix) this;
	}
	
	public void setModifier(float modifierAmount) {
		if(modifierAmount < minMod) {
			this.modifierAmount = minMod;
		} else if(modifierAmount > maxMod) {
			this.modifierAmount = maxMod;
		} else {
			this.modifierAmount = modifierAmount;
		}
	}
	
	public float getMaxMod() {
		return maxMod;
	}
	
	public float getMinMod() {
		return minMod;
	}
	
}
