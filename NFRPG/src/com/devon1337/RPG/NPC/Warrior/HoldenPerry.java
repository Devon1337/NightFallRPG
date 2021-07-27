package com.devon1337.RPG.NPC.Warrior;

import java.util.ArrayList;

import com.devon1337.RPG.NPC.AllFactions;
import com.devon1337.RPG.NPC.Faction;
import com.devon1337.RPG.NPC.INPC;
import com.devon1337.RPG.NPC.NPC;
import com.devon1337.RPG.NPC.NPCCodes;
import com.devon1337.RPG.NPC.WorldController;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Quests.Quest;
import com.devon1337.RPG.Utils.Dialog.Dialog;
import com.devon1337.RPG.Utils.Dialog.DialogCodes;
import com.devon1337.RPG.Utils.Dialog.DialogFlags;

public class HoldenPerry extends NPC implements INPC {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6688608763113087924L;
	static Faction faction = WorldController.getFaction(AllFactions.Warrior);
	static NPCCodes code = NPCCodes.WARRIOR_HOLDEN;
	static String name = "Holden Perry";
	
	public HoldenPerry() {
		super(code, name);	
		super.setLines(init_dialog());
		super.setFaction(faction);
		WorldController.initializeNPC(this, faction);
		super.setDefaultDialog(init_dialog().get(2));
	}
	
	
	public ArrayList<Dialog> init_dialog() {

		ArrayList<Dialog> allDialog = new ArrayList<Dialog>();
		
		// Holden Perry: "Heard you fell asleep"
		allDialog.add(new Dialog("heard you fell asleep", this, null, DialogCodes.WARRIOR_QUEST_2_1));
		allDialog.get(0).addFlag(DialogFlags.NEXT_LINE);
		
		// Holden Perry: "We're low on mob supplies if you can help with that before the next group leaves to ganaboru"
		allDialog.add(new Dialog("We're low on mob supplies if you can help with that before the next group leaves to ganaboru", this, null, DialogCodes.WARRIOR_QUEST_2_2));
		allDialog.get(0).setNextLine(allDialog.get(1));
		
		allDialog.add(new Dialog("Can I help you?", this, null, DialogCodes.HOLDEN_PERRY_GREETING));
		
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
