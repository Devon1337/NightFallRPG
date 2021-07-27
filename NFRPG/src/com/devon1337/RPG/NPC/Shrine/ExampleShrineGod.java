package com.devon1337.RPG.NPC.Shrine;

import java.util.ArrayList;

import com.devon1337.RPG.NPC.INPC;
import com.devon1337.RPG.NPC.NPC;
import com.devon1337.RPG.NPC.NPCCodes;
import com.devon1337.RPG.NPC.WorldController;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Quests.Quest;
import com.devon1337.RPG.Utils.Dialog.Dialog;
import com.devon1337.RPG.Utils.Dialog.DialogFlags;

public class ExampleShrineGod extends NPC implements INPC {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6688608763113087924L;
	static NPCCodes code = NPCCodes.TEST_SHRINE_GOD;
	static String name = "Test Shrine God";
	
	public ExampleShrineGod() {
		super(code, name);	
		super.setLines(init_dialog());
		super.setFaction(faction);
		WorldController.initializeNPC(this, faction);
		super.setDefaultDialog(init_dialog().get(0));
	}

	public ArrayList<Dialog> init_dialog() {

		ArrayList<Dialog> allDialog = new ArrayList<Dialog>();
		
		allDialog.add(new Dialog("Time check passed!", this));
		allDialog.get(0).addFlag(DialogFlags.NOINPUT);
		
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
