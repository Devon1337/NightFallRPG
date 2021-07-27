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

public class DefaultWarriorCitizen1 extends NPC implements INPC {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6688608763113087924L;
	static Faction faction = WorldController.getFaction(AllFactions.Warrior);
	static NPCCodes code = NPCCodes.GENERIC_WARRIOR_1;
	static String name = "Generic Warrior";
	
	public DefaultWarriorCitizen1() {
		super(code, name);	
		super.setLines(init_dialog());
		super.setFaction(faction);
		WorldController.initializeNPC(this, faction);
	}
	
	
	public ArrayList<Dialog> init_dialog() {

		ArrayList<Dialog> allDialog = new ArrayList<Dialog>();
		
		// Citizen: "It's amazing how clean the pit is with all the fighting going on"
		allDialog.add(new Dialog("I don't know how good that fire is... but its been here forever", super.getNPC(), null, DialogCodes.WARRIOR_CITIZEN_1_GENERIC_1));
		allDialog.get(0).addFlag(DialogFlags.NOINPUT);
		allDialog.get(0).addFlag(DialogFlags.GENERIC);
		
		// Citizen: "The guards often rumor about going to a mystical place called Ganaboru"
		allDialog.add(new Dialog("Take the time to look around the world tree you might run into something!", super.getNPC(), null, DialogCodes.WARRIOR_CITIZEN_1_GENERIC_2));
		allDialog.get(1).addFlag(DialogFlags.NOINPUT);
		allDialog.get(1).addFlag(DialogFlags.GENERIC);
		
		// Citizen: "I wouldn't head out now, might run into trouble"
		allDialog.add(new Dialog("I wouldn't head out now, might run into trouble", super.getNPC(), null, DialogCodes.WARRIOR_CITIZEN_1_GENERIC_3));
		allDialog.get(2).addFlag(DialogFlags.NOINPUT);
		allDialog.get(2).addFlag(DialogFlags.GENERIC);
		
		// Citizen: "Our walls have always been secured by the 2 guards posted on the exits"
		allDialog.add(new Dialog("Our walls have always been secured by the 2 guards posted on the exits", super.getNPC(), null, DialogCodes.WARRIOR_CITIZEN_1_GENERIC_4));
		allDialog.get(3).addFlag(DialogFlags.NOINPUT);
		allDialog.get(3).addFlag(DialogFlags.GENERIC);
		
		// Citizen: "I heard the pit boss is looking for someone..."
		allDialog.add(new Dialog("I heard the pit boss is looking for someone...", super.getNPC(), null, DialogCodes.WARRIOR_CITIZEN_1_GENERIC_5));
		allDialog.get(4).addFlag(DialogFlags.NOINPUT);
		allDialog.get(4).addFlag(DialogFlags.GENERIC);
		
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