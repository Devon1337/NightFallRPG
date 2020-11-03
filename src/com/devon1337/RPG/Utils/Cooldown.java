package com.devon1337.RPG.Utils;

import java.util.UUID;

import com.devon1337.RPG.ActiveAbilities.Spell;

public class Cooldown {

	UUID pUUID;
	Spell spell;
	int status;
	
	public Cooldown(UUID pUUID, Spell spell, int status) {
		this.pUUID = pUUID;
		this.spell = spell;
		this.status = status;
	}
	
	public UUID getUUID() {
		return this.pUUID;
	}
	
	public int getTime() {
		return status;
	}
	
	public void setTime(int status) {
		this.status = status;
	}
	
	public Spell getSpell() {
		return this.spell;
	}
	
}
