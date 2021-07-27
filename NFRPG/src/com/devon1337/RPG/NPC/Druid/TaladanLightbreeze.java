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

public class TaladanLightbreeze extends NPC implements INPC {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6688608763113087924L;
	static Faction faction = WorldController.getFaction(AllFactions.Druid);
	static NPCCodes code = NPCCodes.DRUID_LIGHTBREEZE;
	static String name = "Taladan Lightbreeze";
	
	public TaladanLightbreeze() {
		super(code, name);	
		super.setLines(init_dialog());
		super.setFaction(faction);
		WorldController.initializeNPC(this, faction);
	}
	
	
	public ArrayList<Dialog> init_dialog() {

		ArrayList<Dialog> allDialog = new ArrayList<Dialog>();
		
		// Taladan: "I don't know how good that camp fire is but its been here forever"
		allDialog.add(new Dialog("I don't know how good that fire is... but its been here forever", super.getNPC(), null, DialogCodes.DRUID_TALADAN_GENERIC_FIRE_REMARK));
		allDialog.get(0).addFlag(DialogFlags.NOINPUT);
		allDialog.get(0).addFlag(DialogFlags.GENERIC);
		
		// Taladan: "Take the time to look around the world tree you might run into something!"
		allDialog.add(new Dialog("Take the time to look around the world tree you might run into something!", super.getNPC(), null, DialogCodes.DRUID_TALADAN_GENERIC_SECRETS));
		allDialog.get(1).addFlag(DialogFlags.NOINPUT);
		allDialog.get(1).addFlag(DialogFlags.GENERIC);
		
		// Taladan: "You were called for"
		allDialog.add(new Dialog("You were called for", super.getNPC(), null, DialogCodes.DRUID_TALADAN_CALLED_FOR));
		allDialog.get(2).addFlag(DialogFlags.NOINPUT);
		allDialog.get(2).addFlag(DialogFlags.MISSION_DIALOG);
		
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
