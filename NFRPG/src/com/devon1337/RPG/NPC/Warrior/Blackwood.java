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
import com.devon1337.RPG.Utils.Dialog.Requirement;
import com.devon1337.RPG.Utils.Dialog.Response;

public class Blackwood extends NPC implements INPC {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6688608763113087924L;
	static Faction faction = WorldController.getFaction(AllFactions.Warrior);
	static NPCCodes code = NPCCodes.WARRIOR_BLACKWOOD;
	static String name = "Bastion Blackwood";
	
	public Blackwood() {
		super(code, name);	
		super.setLines(init_dialog());
		super.setFaction(faction);
		WorldController.initializeNPC(this, faction);
	}
	
	
	public ArrayList<Dialog> init_dialog() {

		ArrayList<Dialog> allDialog = new ArrayList<Dialog>();
		
		// Warrior King: "%Player% Wake up!"
		allDialog.add(new Dialog("%player% wake up!", super.getNPC(), null, DialogCodes.WARRIOR_QUEST_1_1));
		allDialog.get(0).addFlag(DialogFlags.NEXT_LINE);
		
		// Warrior King: "Seems like you fell asleep..."
		allDialog.add(new Dialog("Seems like you fell asleep...", super.getNPC(), null, DialogCodes.WARRIOR_QUEST_1_2));
		allDialog.get(1).addFlag(DialogFlags.NEXT_LINE);
		allDialog.get(0).setNextLine(allDialog.get(1));
			
		// Warrior King: "What do you have to say about yourself?" Player: "I'll help around while I can.", "I want to look around for a bit before!"
		Response[] WARRIOR_QUEST_1_3_R = {new Response("nfquest add %player% NAME_YOURSELF", "I'll help around while I can."), new Response("I want to look around for a bit before!")};
		allDialog.add(new Dialog("What do you have to say about yourself?", super.getNPC(), WARRIOR_QUEST_1_3_R, DialogCodes.WARRIOR_QUEST_1_3));
		allDialog.get(1).setNextLine(allDialog.get(2));
		
		// Warrior King: "You look new around here, where are you from?"
		Response[] WARRIOR_KING_GREETING_R = {
				new Response("I come from the crystal",
						new Requirement(WorldController.getFaction(AllFactions.Mage), 800, true)),
				new Response("I come from the tree",
						new Requirement(WorldController.getFaction(AllFactions.Druid), 800, true)),
				new Response("I come from the rat hole",
						new Requirement(WorldController.getFaction(AllFactions.Rogue), 800, true)),
				new Response("I ran into your arena while in this desert",
						new Requirement(WorldController.getFaction(AllFactions.Warrior), 800, true)) };


		allDialog.add(new Dialog("You look new around here, where are you from?", super.getNPC(), WARRIOR_KING_GREETING_R, DialogCodes.WARRIOR_KING_GREETING));
		allDialog.get(3).addFlag(DialogFlags.GENERIC);
		
		return allDialog;
		
	}


	@Override
	public void StartQuest(Quest quest, NFPlayer player) {
		// TODO Auto-generated method stub
		
		// Adds priority text lines
		for (Dialog d : super.getAllLines()) {
			if (d.getFlags().contains(DialogFlags.MISSION_DIALOG) && d.getQuest().getTag().equals(quest.getTag())) {
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
