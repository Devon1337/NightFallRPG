package com.devon1337.RPG.NPC.MISC;

import java.util.ArrayList;

import com.devon1337.RPG.NPC.INPC;
import com.devon1337.RPG.NPC.NPC;
import com.devon1337.RPG.NPC.NPCCodes;
import com.devon1337.RPG.NPC.WorldController;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Quests.Quest;
import com.devon1337.RPG.Utils.Dialog.Dialog;
import com.devon1337.RPG.Utils.Dialog.DialogCodes;
import com.devon1337.RPG.Utils.Dialog.DialogFlags;

public class CaravanTravel extends NPC implements INPC {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6688608763113087924L;
	static NPCCodes code = NPCCodes.CARAVAN_TRAVEL;
	static String name = "Traveler";
	
	public CaravanTravel() {
		super(code, name);	
		super.setLines(init_dialog());
		WorldController.initializeNPC(this, null);
	}
	
	
	public ArrayList<Dialog> init_dialog() {

		ArrayList<Dialog> allDialog = new ArrayList<Dialog>();
		
		// Coot: "The birds are a lot bigger than i've ever seen"
		allDialog.add(new Dialog("The birds are a lot bigger than i've ever seen", super.getNPC(), null, DialogCodes.DRUID_TALADAN_GENERIC_FIRE_REMARK));
		allDialog.get(0).addFlag(DialogFlags.NOINPUT);
		allDialog.get(0).addFlag(DialogFlags.GENERIC);
		
		return allDialog;
		
	}


	@Override
	public void StartQuest(Quest quest, NFPlayer player) {
		// TODO Auto-generated method stub
		
		// Adds priority text lines
		for(Dialog d : super.getAllLines()) {
			if(d.getFlags().contains(DialogFlags.MISSION_DIALOG) && d.getQuest().getTag().equals(quest.getTag())) {
				super.addPriorityLine(d, player);
			}
		}
		
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