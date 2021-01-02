package com.devon1337.RPG.NPC.Warrior;

import java.util.ArrayList;

import com.devon1337.RPG.NPC.Faction;
import com.devon1337.RPG.NPC.NPC;
import com.devon1337.RPG.Utils.Dialog;
import com.devon1337.RPG.Utils.DialogFlags;

public class Blackwood extends NPC {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6688608763113087924L;
	static Faction faction;
	static String Code = "WARRIOR_BLACKWOOD", Name = "Bastion Blackwood";
	
	public Blackwood() {
		super(Code, Name);		
	}
	
	
	public ArrayList<Dialog> init_dialog() {

		ArrayList<Dialog> allDialog = new ArrayList<Dialog>();
		
		// Warrior King: "%Player% Wake up!"
		allDialog.add(new Dialog("WARRIOR_QUEST_1_1", "%player% wake up!", null, this));
		allDialog.get(0).addFlag(DialogFlags.START);
		allDialog.get(0).addFlag(DialogFlags.NOINPUT);
		
		
		return allDialog;
		
		
	}
}
