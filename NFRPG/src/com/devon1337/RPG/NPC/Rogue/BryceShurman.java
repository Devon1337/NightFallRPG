package com.devon1337.RPG.NPC.Rogue;

import java.util.ArrayList;

import com.devon1337.RPG.NPC.AllFactions;
import com.devon1337.RPG.NPC.Faction;
import com.devon1337.RPG.NPC.INPC;
import com.devon1337.RPG.NPC.NPC;
import com.devon1337.RPG.NPC.WorldController;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Quests.Quest;
import com.devon1337.RPG.Utils.Dialog.Dialog;
import com.devon1337.RPG.Utils.Dialog.DialogFlags;

public class BryceShurman extends NPC implements INPC {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6688608763113087924L;
	static Faction faction = WorldController.getFaction(AllFactions.Rogue);
	static String Code = "ROGUE_SHURMAN", Name = "Bryce Shurman";
	
	public BryceShurman() {
		super(Name);	
		super.setLines(init_dialog());
		super.setFaction(faction);
		WorldController.initializeNPC(this, faction);
		//super.setDefaultDialog(init_dialog().get(0));
	}
	
	
	public ArrayList<Dialog> init_dialog() {

		ArrayList<Dialog> allDialog = new ArrayList<Dialog>();
		
		/*
		// Shurman: "Seem's like your whole party picked up and scrammed while you were passed out..."
		allDialog.add(new Dialog("ROGUE_START_BAR_KEEP", "Seem's like your whole party picked up and scrammed while you were passed out...", null, this));
		allDialog.get(0).addFlag(DialogFlags.START);
		allDialog.get(0).addFlag(DialogFlags.NOINPUT);
		
		// Shurman: "I can't hold a tab that big, i'll need it paid off"
		allDialog.add(new Dialog("ROGUE_START_BAR_KEEP", "I can't hold a tab that big, i'll need it paid off", null, this));
		allDialog.get(1).addFlag(DialogFlags.CONTINUE);
		allDialog.get(1).addFlag(DialogFlags.NOINPUT);
		
		allDialog.get(0).setNextAutoDialog(allDialog.get(1));
		allDialog.get(1).setNextAutoDialog(WorldController.npcExist("ROGUE_SHEARMAN").getDIALOG_LIST().get(0));
		*/
		
		
		// Warrior King: "You look new around here, where are you from?"
		/*
		Response[] WARRIOR_KING_GREETING_R = {
				// Mage Opening
				new Response("/nfprint I_come_from_the_crystal_you_peons_know_as_fliandrian's_crystal",
						"I come from the crystal", 800, WorldController.getFaction(AllFactions.Mage), null),
				
				// Druid Opening
				new Response("/nfprint I_was_a_fruit_of_the_great_world_tree.", "I came from the world tree", 800,
						WorldController.getFaction(AllFactions.Druid), null),
				
				// Rogue Opening
				new Response("/nfprint I_live_with_the_rats_in_the_sewers.", "I came from the water temple", 800,
						WorldController.getFaction(AllFactions.Rogue), null),
				
				// Generic Opening
				new Response("/nfprint I_ran_into_your_arena_while_in_this_desert", "I ran into your arena", 0, null, null)
				};
				*/
		
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