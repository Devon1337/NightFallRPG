package com.devon1337.RPG.NPC.SelectClass;

import java.util.ArrayList;

import org.bukkit.ChatColor;

import com.devon1337.RPG.NPC.INPC;
import com.devon1337.RPG.NPC.NPC;
import com.devon1337.RPG.NPC.NPCCodes;
import com.devon1337.RPG.NPC.WorldController;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Quests.Quest;
import com.devon1337.RPG.Utils.Dialog.Dialog;
import com.devon1337.RPG.Utils.Dialog.DialogCodes;
import com.devon1337.RPG.Utils.Dialog.DialogFlags;
import com.devon1337.RPG.Utils.Dialog.Response;

public class DeprivedInfo extends NPC implements INPC {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6688608763113087924L;
	static NPCCodes code = NPCCodes.DEPRIVED_INFO;
	static String name = ChatColor.DARK_RED + "" + ChatColor.MAGIC + "The one";
	
	public DeprivedInfo() {
		super(code, name);	
		super.setLines(init_dialog());
		super.setFaction(faction);
		WorldController.initializeNPC(this, faction);
		super.setDefaultDialog(init_dialog().get(0));
	}

	public ArrayList<Dialog> init_dialog() {

		ArrayList<Dialog> allDialog = new ArrayList<Dialog>();
		
		allDialog.add(new Dialog("There is no glimmer for us", super.getNPC(), null, DialogCodes.DEPRIVED_INFO_1));
		allDialog.get(0).addFlag(DialogFlags.NOINPUT);
		
		allDialog.add(new Dialog("Only solace in the flame we walk upon", super.getNPC(), null, DialogCodes.DEPRIVED_INFO_2));
		
		
		allDialog.add(new Dialog("if youre not ready for whats ahead", super.getNPC(), null, DialogCodes.DEPRIVED_INFO_3));
		
		Response[] DEPRIVED_INFO_CONFIRMATION_1 = {new Response("I wish to walk upon the flame"), new Response("The books look interesting")};
		allDialog.add(new Dialog("I would read upon the books you see around me", super.getNPC(), DEPRIVED_INFO_CONFIRMATION_1, DialogCodes.DEPRIVED_INFO_4));
		
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

