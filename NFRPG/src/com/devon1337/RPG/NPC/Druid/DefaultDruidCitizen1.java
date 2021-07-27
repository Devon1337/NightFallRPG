package com.devon1337.RPG.NPC.Druid;

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

public class DefaultDruidCitizen1 extends NPC implements INPC {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6688608763113087924L;
	static Faction faction = WorldController.getFaction(AllFactions.Druid);
	static NPCCodes code = NPCCodes.GENERIC_DRUID_1;
	static String name = "Generic Druid";
	
	public DefaultDruidCitizen1() {
		super(code, name);	
		super.setLines(init_dialog());
		super.setFaction(faction);
		WorldController.initializeNPC(this, faction);
	}
	
	
	public ArrayList<Dialog> init_dialog() {

		ArrayList<Dialog> allDialog = new ArrayList<Dialog>();
		
		// Citizen: "The tree supplies for us to live, It's becoming a lot harder when other's try to sap the tree's strength."
		allDialog.add(new Dialog("The tree supplies for us to live, It's becoming a lot harder when other's try to sap the tree's strength.", super.getNPC(), null, DialogCodes.DRUID_CITIZEN_1_GENERIC_1));
		allDialog.get(0).addFlag(DialogFlags.NOINPUT);
		allDialog.get(0).addFlag(DialogFlags.GENERIC);
		
		// Citizen: "The tree gives the best natural protection"
		allDialog.add(new Dialog("The tree gives the best natural protection", super.getNPC(), null, DialogCodes.DRUID_CITIZEN_1_GENERIC_2));
		allDialog.get(1).addFlag(DialogFlags.NOINPUT);
		allDialog.get(1).addFlag(DialogFlags.GENERIC);
		
		// Citizen: "How'd you get up here?"
		allDialog.add(new Dialog("How'd you get up here?", super.getNPC(), null, DialogCodes.DRUID_CITIZEN_1_GENERIC_3));
		allDialog.get(2).addFlag(DialogFlags.NOINPUT);
		allDialog.get(2).addFlag(DialogFlags.GENERIC);
		
		// Citizen: "Not many outsiders are allowed up here"
		allDialog.add(new Dialog("Not many outsiders are allowed up here", super.getNPC(), null, DialogCodes.DRUID_CITIZEN_1_GENERIC_4));
		allDialog.get(3).addFlag(DialogFlags.NOINPUT);
		allDialog.get(3).addFlag(DialogFlags.GENERIC);
		
		// Citizen: "Don't enjoy yourself too much..."
		allDialog.add(new Dialog("Don't enjoy yourself too much...", super.getNPC(), null, DialogCodes.DRUID_CITIZEN_1_GENERIC_5));
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
