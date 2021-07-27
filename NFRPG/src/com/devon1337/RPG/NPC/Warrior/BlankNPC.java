package com.devon1337.RPG.NPC.Warrior;

import com.devon1337.RPG.NPC.NPC;
import com.devon1337.RPG.NPC.NPCCodes;

public class BlankNPC extends NPC {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6688608763113087924L;
	
	public BlankNPC(String name) {
		super(name);		
	}
	
	public BlankNPC(NPCCodes code, String name) {
		super(code, name);
	}
	
}
