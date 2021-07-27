package com.devon1337.RPG.NPC.Warrior;

import java.util.ArrayList;

import com.devon1337.RPG.NPC.AllFactions;
import com.devon1337.RPG.NPC.Faction;
import com.devon1337.RPG.NPC.INPC;
import com.devon1337.RPG.NPC.NPC;
import com.devon1337.RPG.NPC.WorldController;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Quests.Quest;
import com.devon1337.RPG.Utils.Dialog.Dialog;

public class ArgonPenny extends NPC implements INPC{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6688608763113087924L;
	static Faction faction = WorldController.getFaction(AllFactions.Warrior);
	static String Code = "ARGON_PENNY", Name = "Argon Penny";
	
	public ArgonPenny() {
		super(Name);	
		super.setLines(init_dialog());
		super.setFaction(faction);
		WorldController.initializeNPC(this, faction);
		//super.setDefaultDialog(init_dialog().get(3));
	}
	
	
	public ArrayList<Dialog> init_dialog() {

		ArrayList<Dialog> allDialog = new ArrayList<Dialog>();
		/*
		// Warrior King: "%Player% Wake up!"
		allDialog.add(new Dialog("FORGE_REPAIR", "Anything I can help you with?", null, this));
		allDialog.get(0).addFlag(DialogFlags.START);
		allDialog.get(0).addFlag(DialogFlags.NOINPUT);
		*/
		return allDialog;

	}


	@Override
	public void StartQuest(Quest quest, NFPlayer player) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void FailQuest() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void CompleteQuest() {
		// TODO Auto-generated method stub
		
	}
}
